package dev.local.newsoul.feature.chat.socialscore;

import android.content.Context;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Prefs;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 社交分数限制功能域（原 {@code Q0.E} 安装段与 hooker {@code f} case 2、{@code a} case 18）。
 *
 * <p>宿主以社交分数对会话施加封锁：{@code SocialGraceScoreBean} 的四个 getter
 * （{@code getCanNotSendBeforeReply} / {@code getCreateSessionLimit} / {@code getForbidCall} /
 * {@code getForbidPic}）分别表示「回复前禁言 / 禁建会话 / 禁通话 / 禁图片」，社交分弹窗
 * 由 {@code SocialScoreUtils.b(FragmentManager, SocialScoreUtils$a)} 弹出。模块在各判定点
 * 把 {@code true} 改写为 {@code false}，或在开关开启时直接抑制弹窗。</p>
 *
 * <p>安装分两段独立降级（与原实现一致）：model 段（四个 getter + 相关宿主方法去优化）与
 * dialog 段（弹窗方法 + 该类全部方法去优化）；汇总日志
 * {@code social score hooks: model=..; dialog=..}。</p>
 */
public final class SocialScoreLimitFeature implements FeatureInstaller {

    private static final String ID = "chat.social-score";

    /** 社交分封锁判定方法名（原 {@code Q0.E} 的四个候选）。 */
    private static final String[] GATE_NAMES = {
            "getCanNotSendBeforeReply", "getCreateSessionLimit", "getForbidCall", "getForbidPic"
    };

    /** 需要去优化的宿主类（类名以 {@code $d1} 结尾时整类去优化）。 */
    private static final String[] DEOPT_TARGETS = {
            HostClasses.UJ_C0, HostClasses.UJ_C0_Q, HostClasses.CHAT_CONVERSATION_FRAGMENT_D1
    };

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        ClassLoader loader = context.classLoader();
        GlobalContextProvider globalContext = GlobalContextProvider.resolve(context);

        boolean modelReady = false;
        boolean dialogReady = false;

        // ---- model 段：SocialGraceScoreBean 四个封锁 getter ----
        try {
            Class<?> bean = loader.loadClass(HostClasses.CHAT_SOCIAL_GRACE_SCORE_BEAN);
            for (String name : GATE_NAMES) {
                Method gate = bean.getDeclaredMethod(name, (Class<?>[]) null);
                if (gate.getReturnType() != Boolean.TYPE) {
                    throw new NoSuchMethodException("Unexpected social score gate " + name);
                }
                if (!Hooks.intercept(module, HookIds.SOCIAL_SCORE_PREFIX + name, gate,
                        gateHooker(gate, globalContext))) {
                    throw new IllegalStateException("social score gate hook failed: " + name);
                }
            }
            deoptimizeGateCallers(module, loader);
            modelReady = true;
        } catch (Throwable throwable) {
            Logger.e("social score model unavailable", throwable);
        }

        // ---- dialog 段：SocialScoreUtils.b(FragmentManager, SocialScoreUtils$a) ----
        try {
            Class<?> utils = loader.loadClass(HostClasses.PLANET_SOCIAL_SCORE_UTILS);
            Class<?> fragmentManager = loader.loadClass(HostClasses.FRAGMENT_MANAGER);
            Class<?> callback = loader.loadClass(HostClasses.PLANET_SOCIAL_SCORE_UTILS_A);
            Method showDialog = utils.getDeclaredMethod("b", fragmentManager, callback);
            if (!Modifier.isStatic(showDialog.getModifiers())
                    || showDialog.getReturnType() != Void.TYPE) {
                throw new NoSuchMethodException("Unexpected social score dialog signature");
            }
            if (!Hooks.intercept(module, HookIds.SOCIAL_SCORE_DIALOG, showDialog,
                    dialogHooker(globalContext))) {
                throw new IllegalStateException("social score dialog hook failed");
            }
            for (Method method : utils.getDeclaredMethods()) {
                Hooks.deoptimize(module, HookIds.SOCIAL_SCORE_DIALOG, method);
            }
            dialogReady = true;
        } catch (Throwable throwable) {
            Logger.e("social score dialog unavailable", throwable);
        }

        Logger.i("social score hooks: model=" + modelReady + "; dialog=" + dialogReady);
    }

    /**
     * 对社交分判定链路中的宿主方法去优化。
     *
     * <p>原实现语义：目标类方法「参数含 {@code SocialGraceScoreBean}」，或类名以
     * {@code $d1} 结尾（整类）时去优化。</p>
     */
    private static void deoptimizeGateCallers(XposedModule module, ClassLoader loader)
            throws ClassNotFoundException {
        Class<?> bean = loader.loadClass(HostClasses.CHAT_SOCIAL_GRACE_SCORE_BEAN);
        for (String className : DEOPT_TARGETS) {
            Class<?> clazz = loader.loadClass(className);
            boolean wholeClass = className.endsWith("$d1");
            for (Method method : clazz.getDeclaredMethods()) {
                if (wholeClass
                        || Arrays.asList(method.getParameterTypes()).contains(bean)) {
                    Hooks.deoptimize(module, HookIds.SOCIAL_SCORE_PREFIX + method.getName(), method);
                }
            }
        }
    }

    // ------------------------------------------------------------ hooker

    /**
     * 社交分封锁 getter（原 {@code f} case 2）：命中 {@code true} 且开关开启时改写为
     * {@code false}，并记录 {@code social score gate bypassed: <方法名>}。
     */
    static XposedInterface.Hooker gateHooker(Method gate, GlobalContextProvider globalContext) {
        return chain -> {
            Object result = chain.proceed();
            if (Boolean.TRUE.equals(result)
                    && isEnabled(globalContext, SettingsKeys.CHAT_REMOVE_SOCIAL_SCORE_LIMIT)) {
                Logger.i("social score gate bypassed: " + gate.getName());
                result = Boolean.FALSE;
            }
            return result;
        };
    }

    /** 社交分弹窗方法（原 {@code a} case 18）：开关开启时拦截，使弹窗不出现。 */
    static XposedInterface.Hooker dialogHooker(GlobalContextProvider globalContext) {
        return chain -> {
            if (isEnabled(globalContext, SettingsKeys.CHAT_REMOVE_SOCIAL_SCORE_LIMIT)) {
                Logger.i("social score dialog suppressed");
                return null;
            }
            return chain.proceed();
        };
    }

    /** 读取功能开关；全局 Context 不可用时返回 {@code false}（原 {@code Q0.q} 的失败路径）。 */
    private static boolean isEnabled(GlobalContextProvider globalContext, String key) {
        Context context = globalContext != null ? globalContext.get() : null;
        if (context == null) {
            return false;
        }
        return Prefs.getBoolean(context, key, true);
    }
}
