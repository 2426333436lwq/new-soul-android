package dev.local.newsoul.feature.startup;

import android.app.Activity;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.local.newsoul.SoulHook;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;

/**
 * 启动提示与页面日志。
 *
 * <p>对应原模块中挂在 {@code android.app.Activity.onResume()} 上的
 * {@code soul-demo/activity-onResume} hook：宿主任意 Activity 恢复时，</p>
 * <ul>
 *   <li>{@code page_log} 开启时记录当前页面类名；</li>
 *   <li>{@code startup_toast} 开启且本次进程内尚未提示过时，弹出「New Soul 已生效」。</li>
 * </ul>
 *
 * <p>提示使用 {@link AtomicBoolean} 做一次性保证，与原模块的 {@code toastShown} 语义一致。</p>
 */
public final class StartupToastFeature implements FeatureInstaller {

    /** 与原模块一致：hook {@code android.app.Activity.onResume()}。 */
    public static final String HOOK_ID = "soul-demo/activity-onResume";

    private static final String TOAST_TEXT = "New Soul 已生效";

    private final AtomicBoolean toastShown = new AtomicBoolean(false);

    @Override
    public String id() {
        return "startup-toast";
    }

    @Override
    public void install(ModuleContext context) {
        // 注意：这里必须直接反射 android.app.Activity，不能用 Reflect.findDeclaredMethod
        // —— 那个方法带框架类守卫（防止把 hook 误挂到 Activity.onCreate 上），
        // 会拒绝 Activity 本身。而原模块正是 hook Activity.onResume 基类，
        // 由 LSPosed 分发到所有子类实例。
        Method onResume;
        try {
            onResume = Activity.class.getDeclaredMethod("onResume");
            onResume.setAccessible(true);
        } catch (Throwable throwable) {
            Logger.w("Activity.onResume unavailable; startup toast disabled", throwable);
            return;
        }
        boolean installed = Hooks.intercept(context.module(), HOOK_ID, onResume, chain -> {
            Object result = chain.proceed();
            Object thisObject = chain.getThisObject();
            if (thisObject instanceof Activity) {
                onActivityResumed(context, (Activity) thisObject);
            }
            return result;
        });
        if (installed) {
            Logger.i("hook installed: android.app.Activity.onResume");
        }
    }

    private void onActivityResumed(ModuleContext context, Activity activity) {
        if (!SoulHook.HOST_PACKAGE.equals(activity.getPackageName())) {
            return;
        }
        if (context.config().getBoolean(SettingsKeys.PAGE_LOG)) {
            Logger.i("onResume: " + activity.getClass().getName());
        }
        if (context.config().getBoolean(SettingsKeys.STARTUP_TOAST)
                && toastShown.compareAndSet(false, true)) {
            Toast.makeText(activity, TOAST_TEXT, Toast.LENGTH_LONG).show();
        }
    }
}