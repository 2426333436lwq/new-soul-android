package dev.local.newsoul.feature.chat.gift;

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
 * 礼物聊天限制功能域（原 {@code Q0.z} 安装段与 {@code a} 的 case 5/6/7/8）。
 *
 * <p>宿主对受限会话施以「送礼限制」：{@code ChatLimitModel.isLimit()} 表示当前模型处于
 * 限制状态，{@code Conversation.getIntExt("chat_limit_session_type")} 给出会话限制等级，
 * 礼物对话框（{@code ChatUtils} 的静态方法）与铃声礼物弹窗（{@code LoveBellProConfig}
 * 的 {@code getShowPopup()}）分别决定是否弹出限制提示。模块在各判定点放行或改写结果，
 * 使开启对应开关后不再被礼物限制拦截。</p>
 *
 * <p>安装按四段独立降级（与原实现一致）：model / session / dialog / bell，任一段失败
 * 只记录该段日志并跳过，不影响其它段；汇总日志
 * {@code gift chat hooks: model=..; session=..; dialog=..; bell=..}。</p>
 */
public final class GiftChatLimitFeature implements FeatureInstaller {

    private static final String ID = "chat.gift-limit";

    /** {@code getIntExt} 返回的会话限制等级下界（原 {@code 2}）。 */
    private static final int SESSION_TYPE_MIN = 0x2;

    /** {@code getIntExt} 返回的会话限制等级上界（原 {@code 5}）。 */
    private static final int SESSION_TYPE_MAX = 0x5;

    /** 改写后的会话限制等级（原 {@code 1}，表示无限制）。 */
    private static final int SESSION_TYPE_CLEARED = 0x1;

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
        boolean sessionReady = false;
        boolean dialogReady = false;
        boolean bellReady = false;
        GiftLimitModel limitModel = null;

        // ---- model 段：ChatLimitModel.isLimit() ----
        try {
            Class<?> limitClass = loader.loadClass(HostClasses.CHAT_LIMIT_MODEL);
            Method typeGetter = limitClass.getMethod("getType", (Class<?>[]) null);
            Method identityGetter = limitClass.getMethod("getItemIdentity", (Class<?>[]) null);
            Method isLimit = limitClass.getMethod("isLimit", (Class<?>[]) null);
            limitModel = new GiftLimitModel(typeGetter, identityGetter);
            if (!Hooks.intercept(module, HookIds.GIFT_CHAT_MODEL, isLimit,
                    modelHooker(limitModel, globalContext))) {
                throw new IllegalStateException("gift chat model hook failed");
            }
            Hooks.deoptimize(module, HookIds.GIFT_CHAT_MODEL, isLimit);
            modelReady = true;
        } catch (Throwable throwable) {
            Logger.e("model", throwable);
        }

        // ---- session 段：Conversation.getIntExt(String) ----
        try {
            Class<?> conversation = loader.loadClass(HostClasses.IMLIB_CONVERSATION);
            Method getIntExt = conversation.getMethod("getIntExt", String.class);
            Hooks.intercept(module, HookIds.GIFT_CHAT_SESSION, getIntExt,
                    sessionHooker(globalContext));
            Hooks.deoptimize(module, HookIds.GIFT_CHAT_SESSION, getIntExt);
            sessionReady = true;
        } catch (Throwable throwable) {
            Logger.e("session", throwable);
        }

        // ---- dialog 段（仅当 model 段成功）：ChatUtils 的礼物对话框方法 ----
        if (modelReady) {
            try {
                Class<?> limitClass = loader.loadClass(HostClasses.CHAT_LIMIT_MODEL);
                Class<?> conversation = loader.loadClass(HostClasses.IMLIB_CONVERSATION);
                Class<?> listener = loader.loadClass(HostClasses.CHAT_LIMIT_GIFT_DIALOG_LISTENER);
                Class<?>[] expected = {limitClass, conversation, listener, String.class, String.class,
                        Boolean.TYPE};
                Method dialog = findGiftDialogMethod(loader, expected);
                if (!Hooks.intercept(module, HookIds.GIFT_CHAT_DIALOG, dialog,
                        dialogHooker(limitModel, globalContext))) {
                    throw new IllegalStateException("gift chat dialog hook failed");
                }
                Hooks.deoptimize(module, HookIds.GIFT_CHAT_DIALOG, dialog);
                dialogReady = true;
            } catch (Throwable throwable) {
                Logger.e("dialog", throwable);
            }
        }

        // ---- bell 段：LoveBellProConfig.getShowPopup() + 调用方去优化 ----
        try {
            Class<?> config = loader.loadClass(HostClasses.HOME_LOVE_BELL_PRO_CONFIG);
            Method getShowPopup = config.getDeclaredMethod("getShowPopup", (Class<?>[]) null);
            Class<?> returnType = getShowPopup.getReturnType();
            if (returnType != Boolean.class && returnType != Boolean.TYPE) {
                throw new NoSuchMethodException("unexpected getShowPopup return type");
            }
            if (!Hooks.intercept(module, HookIds.BELL_GIFT_POPUP_CONFIG, getShowPopup,
                    bellHooker(globalContext))) {
                throw new IllegalStateException("bell gift popup hook failed");
            }
            Hooks.deoptimize(module, HookIds.BELL_GIFT_POPUP_CONFIG, getShowPopup);
            bellReady = true;

            // 对 LoveBellProGiftDialog 内部类中参数含 config 的方法去优化
            try {
                Class<?> dialog = loader.loadClass(HostClasses.HOME_LOVE_BELL_PRO_GIFT_DIALOG);
                for (Class<?> inner : dialog.getDeclaredClasses()) {
                    for (Method method : inner.getDeclaredMethods()) {
                        if (Arrays.asList(method.getParameterTypes()).contains(config)) {
                            Hooks.deoptimize(module, HookIds.BELL_GIFT_POPUP_CONFIG, method);
                        }
                    }
                }
            } catch (Throwable throwable) {
                Logger.e("bell callers", throwable);
            }
        } catch (Throwable throwable) {
            Logger.e("bell popup config", throwable);
        }

        // ---- 附加去优化：限制判定链路的宿主方法 ----
        deoptCallers(module, loader, HostClasses.CHAT_STYLE_BASE_PROCESS, "checkLimitOpt");
        deoptCallers(module, loader, HostClasses.CHAT_MSG_LIMIT_MANAGER, null);
        deoptCallers(module, loader, HostClasses.CHAT_CONVERSATION_FRAGMENT, "v4");

        Logger.i("gift chat hooks: model=" + modelReady
                + "; session=" + sessionReady
                + "; dialog=" + dialogReady
                + "; bell=" + bellReady);
    }

    /**
     * 在 {@code ChatUtils} 中查找唯一的「礼物对话框」静态方法。
     *
     * <p>按原实现：参数须与 {@code (ChatLimitModel, Conversation, LimitGiftDialog$OnStartGiftSendListener,
     * String, String, boolean)} 完全一致；命中多个抛 {@code "ambiguous gift dialog entry"}，
     * 没有则抛 {@code "gift dialog signature"}。</p>
     */
    private static Method findGiftDialogMethod(ClassLoader loader, Class<?>[] expected)
            throws ClassNotFoundException, NoSuchMethodException {
        Class<?> chatUtils = loader.loadClass(HostClasses.CHAT_UTILS);
        Method found = null;
        for (Method method : chatUtils.getDeclaredMethods()) {
            if (!Modifier.isStatic(method.getModifiers())
                    || method.getReturnType() != Void.TYPE
                    || !Arrays.equals(method.getParameterTypes(), expected)) {
                continue;
            }
            if (found == null) {
                found = method;
            } else {
                throw new IllegalStateException("ambiguous gift dialog entry");
            }
        }
        if (found == null) {
            throw new NoSuchMethodException("gift dialog signature");
        }
        return found;
    }

    /** 对指定类中同名（{@code methodName} 非空时）且非 abstract / native 的方法逐一去优化。 */
    private static void deoptCallers(XposedModule module, ClassLoader loader, String className,
                                     String methodName) {
        try {
            Class<?> clazz = loader.loadClass(className);
            for (Method method : clazz.getDeclaredMethods()) {
                if (methodName != null && !methodName.equals(method.getName())) {
                    continue;
                }
                if (Modifier.isAbstract(method.getModifiers())
                        || Modifier.isNative(method.getModifiers())) {
                    continue;
                }
                Hooks.deoptimize(module, ID, method);
            }
        } catch (Throwable throwable) {
            Logger.e("callers " + className, throwable);
        }
    }

    // ------------------------------------------------------------ hooker

    /**
     * {@code ChatLimitModel.isLimit()}（原 case 5）：结果命中且模型属于可绕过类型时改写为
     * {@code false}，并记录 {@code chat gate observed; type=..; bypass=..}。
     */
    static XposedInterface.Hooker modelHooker(GiftLimitModel limitModel,
                                              GlobalContextProvider globalContext) {
        return chain -> {
            Object result = chain.proceed();
            boolean bypass = Boolean.TRUE.equals(result)
                    && limitModel.isActive(chain.getThisObject(), globalContext);
            if (Boolean.TRUE.equals(result)) {
                Logger.i("chat gate observed; type=" + limitModel.typeOf(chain.getThisObject())
                        + "; bypass=" + bypass);
            }
            return bypass ? Boolean.FALSE : result;
        };
    }

    /**
     * {@code Conversation.getIntExt(String)}（原 case 6）：查询 {@code chat_limit_session_type}
     * 且返回值落在限制等级区间时，开启开关则改写为无限制等级。
     */
    static XposedInterface.Hooker sessionHooker(GlobalContextProvider globalContext) {
        return chain -> {
            Object result = chain.proceed();
            Object key = chain.getArg(0);
            if (key instanceof String && SettingsKeys.CHAT_LIMIT_SESSION_TYPE.equals(key)
                    && result instanceof Integer) {
                int value = (Integer) result;
                if (value >= SESSION_TYPE_MIN && value <= SESSION_TYPE_MAX
                        && isEnabled(globalContext, SettingsKeys.CHAT_REMOVE_GIFT_GATE)) {
                    result = Integer.valueOf(SESSION_TYPE_CLEARED);
                }
            }
            return result;
        };
    }

    /**
     * {@code LoveBellProConfig.getShowPopup()}（原 case 7）：开启开关时把 {@code true}
     * 改写为 {@code false}（不再弹出铃声礼物引导弹窗）。
     */
    static XposedInterface.Hooker bellHooker(GlobalContextProvider globalContext) {
        return chain -> {
            Object result = chain.proceed();
            if (isEnabled(globalContext, SettingsKeys.CHAT_REMOVE_BELL_GIFT_GATE)) {
                if (Boolean.TRUE.equals(result)) {
                    Logger.i("special bell gift popup bypassed");
                }
                result = Boolean.FALSE;
            }
            return result;
        };
    }

    /**
     * 礼物对话框方法（原 case 8）：模型处于可绕过类型时拦截，使限制提示对话框不弹出。
     */
    static XposedInterface.Hooker dialogHooker(GiftLimitModel limitModel,
                                               GlobalContextProvider globalContext) {
        return chain -> {
            if (limitModel.isActive(chain.getArg(0), globalContext)) {
                Logger.i("gift chat dialog suppressed");
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
