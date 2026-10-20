package dev.local.newsoul.feature.startup;

import android.app.Activity;
import android.content.Intent;

import java.lang.reflect.Method;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;

/**
 * 启动广告跳过（原 {@code SoulHook.onPackageReady} 的 startup 段 + {@code Q0.n} + {@code y2.intercept}）。
 *
 * <p><b>原始证据</b>（{@code Q0.n(module, Activity, Intent, String)}）：</p>
 * <pre>
 *   if (block_splash) {
 *       intent.putExtra("display_ad", false);
 *       log("new soul: " + ... + " display_ad=false");
 *   }
 * </pre>
 *
 * <p>即原实现并<b>不</b>结束 Activity，而是在宿主读取启动广告开关之前，往当前
 * {@code Intent} 写入 {@code display_ad=false}。宿主自身的启动逻辑据此跳过广告页，
 * 比「finish 掉广告 Activity」温和得多 —— 不会打乱任务栈，也不会触发宿主的异常上报。</p>
 *
 * <p><b>三条触发路径</b>（hook id 与原模块一致）：</p>
 * <ul>
 *   <li>{@code new-soul/cold-launch-flag} —— {@code MainActivity.onCreate(Bundle)} 冷启动</li>
 *   <li>{@code new-soul/relaunch-flag} —— {@code MainActivity.onNewIntent(Intent)} 重入</li>
 *   <li>{@code new-soul/hot-splash-gate} —— {@code MainScheduler} 热启动广告闸门</li>
 * </ul>
 *
 * <p>{@code MainScheduler} 的签名无法从原 DEX 完全确定（原实现用「方法形状」探测并记录
 * {@code "Unexpected Soul hot startup signature"}），此处同样按形状探测，失败即优雅降级 ——
 * 仅失去热启动路径，冷启动仍生效。</p>
 */
public final class SplashSkipFeature implements dev.local.newsoul.feature.FeatureInstaller {

    /** 宿主读取该 extra 决定是否展示启动广告。 */
    private static final String EXTRA_DISPLAY_AD = "display_ad";

    @Override
    public String id() {
        return "splash-skip";
    }

    @Override
    public void install(ModuleContext context) {
        installMainActivityHooks(context);
        installHotSplashGate(context);
    }

    // ================================================================ 冷启动 / 重入

    private static void installMainActivityHooks(ModuleContext context) {
        Class<?> main = context.loadHostClass(HostClasses.STARTUP_MAIN_ACTIVITY);
        if (main == null) {
            Logger.e("new soul: splash hook unavailable");
            return;
        }

        int installed = 0;

        Method onCreate = Reflect.findDeclaredMethod(main, "onCreate", android.os.Bundle.class);
        if (onCreate == null) {
            // 宿主混淆了方法名时按形状兜底：返回 void 且 1 个参数
            onCreate = Reflect.findMethodByShape(main, "onCreate", void.class, 1);
        }
        if (onCreate != null
                && Hooks.intercept(context.module(), HookIds.COLD_LAUNCH_FLAG, onCreate,
                        new SplashIntentHooker("onCreate"))) {
            installed++;
        }

        Method onNewIntent = Reflect.findDeclaredMethod(main, "onNewIntent", Intent.class);
        if (onNewIntent != null
                && Hooks.intercept(context.module(), HookIds.RELAUNCH_FLAG, onNewIntent,
                        new SplashIntentHooker("onNewIntent"))) {
            installed++;
        }

        if (installed > 0) {
            Logger.i("new soul: splash hook installed (installed=" + installed + ")");
            Logger.i("new soul: startup intent hooks installed");
        } else {
            Logger.e("new soul: splash hook unavailable; original startup retained");
        }
    }

    /**
     * 向 Activity 的 Intent 写入 {@code display_ad=false}。
     *
     * <p>对应原 {@code y2.intercept} → {@code Q0.n}。</p>
     *
     * <p>注意：{@code getIntent()} 返回的是宿主持有的同一个 Intent 实例，直接
     * {@code putExtra} 即可被宿主后续读取；不需要（也不应该）替换 Activity 的 Intent，
     * 那会破坏宿主的任务栈恢复逻辑。</p>
     */
    private static final class SplashIntentHooker implements XposedInterface.Hooker {

        private final String methodName;

        SplashIntentHooker(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            // 先放行宿主 onCreate/onNewIntent，再改 Intent。
            // 理由（宿主 MainActivity.onCreate 开头是 Sophix PatchProxy.proxy：
            //   若在 proceed() 之前触碰 Activity，可能改变其热修复补丁判定，
            //   导致 onCreate 直接 return-void → 白屏。原版 y2.intercept 也是
            //   Q0.n 之后立刻 return chain.proceed()，即「先 proceed 后改写」。
            Object result = chain.proceed();
            try {
                Object self = chain.getThisObject();
                if (self instanceof Activity activity) {
                    suppressSplashAd(activity, methodName);
                }
            } catch (Throwable throwable) {
                Logger.w("splash skip failed: " + methodName, throwable);
            }
            return result;
        }
    }

    /** 写入跳过标志。 */
    private static void suppressSplashAd(Activity activity, String source) {
        try {
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            ConfigStore store = ConfigStore.from(activity);
            if (!store.getBoolean(SettingsKeys.BLOCK_SPLASH)) {
                return;
            }
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            intent.putExtra(EXTRA_DISPLAY_AD, false);
            Logger.i("new soul: " + source + " display_ad=false");
        } catch (Throwable throwable) {
            Logger.w("splash intent patch failed: " + source, throwable);
        }
    }

    // ================================================================ 热启动闸门

    /**
     * 热启动广告闸门（原 {@code new-soul/hot-splash-gate}）。
     *
     * <p>原实现在 {@code MainScheduler} 上按「返回布尔 + 无参」的形状探测闸门方法，
     * 命中后强制返回 {@code false}（不展示）。签名不符时记录
     * {@code "Unexpected Soul hot startup signature"} 并降级。</p>
     */
    private static void installHotSplashGate(ModuleContext context) {
        Class<?> scheduler = context.loadHostClass(HostClasses.STARTUP_MAIN_SCHEDULER);
        if (scheduler == null) {
            Logger.e("new soul: hot splash hook unavailable");
            return;
        }
        Method gate = null;
        for (String name : new String[]{"isShowAd", "isShowSplash", "shouldShowAd", "showAd"}) {
            gate = Reflect.findMethodByShape(scheduler, name, boolean.class, 0);
            if (gate != null) {
                break;
            }
        }
        if (gate == null) {
            // 方法名不可知时按形状兜底：无参且返回 boolean 的静态方法
            gate = findByShape(scheduler, boolean.class, 0);
        }
        if (gate == null) {
            Logger.e("new soul: hot splash hook unavailable"
                    + " (Unexpected Soul hot startup signature)");
            return;
        }
        if (Hooks.intercept(context.module(), HookIds.HOT_SPLASH_GATE, gate,
                new HotSplashGateHooker())) {
            Logger.i("new soul: hot splash hook installed");
        } else {
            Logger.e("new soul: hot splash hook unavailable");
        }
    }

    /**
     * 按形状（返回类型 + 参数个数）查找方法，不限方法名。
     *
     * <p>宿主热启动闸门的方法名在各版本间会变，{@link Reflect#findMethodByShape} 要求
     * 精确名字，此处补一个只按形状筛选的查找。命中多个时返回 {@code null} ——
     * 宁可降级也不要 hook 错方法。</p>
     */
    private static Method findByShape(Class<?> clazz, Class<?> returnType, int paramCount) {
        Method hit = null;
        for (Method method : Reflect.allMethods(clazz)) {
            if (returnType != null && !returnType.isAssignableFrom(method.getReturnType())) {
                continue;
            }
            if (method.getParameterCount() != paramCount) {
                continue;
            }
            if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (hit != null) {
                // 多个候选：无法确定，放弃
                return null;
            }
            hit = method;
        }
        if (hit != null) {
            hit.setAccessible(true);
        }
        return hit;
    }

    /** 命中时返回 false，表示「不展示广告」。 */
    private static final class HotSplashGateHooker implements XposedInterface.Hooker {

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            try {
                android.content.Context ctx =
                        dev.local.newsoul.framework.GlobalContextHolder.get();
                if (ctx != null
                        && ConfigStore.from(ctx).getBoolean(SettingsKeys.BLOCK_SPLASH)) {
                    Logger.i("new soul: hot splash blocked; gate=false");
                    return Boolean.FALSE;
                }
            } catch (Throwable throwable) {
                Logger.w("hot splash gate failed", throwable);
            }
            return chain.proceed();
        }
    }
}
