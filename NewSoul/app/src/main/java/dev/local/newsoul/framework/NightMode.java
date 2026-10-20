package dev.local.newsoul.framework;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * 昼夜模式判定（原混淆类 {@code o} 的方法 {@code b(Activity)} 与 {@code c}）。
 *
 * <p>原 {@code o} 兼具底部设置入口的安装职责；此处仅复刻其中与资料页伪装编辑对话框
 * 相关的两部分：{@link #install} 反射解析宿主 {@code SoulSettings.isNightMode()} 并缓存；
 * {@link #isNightMode} 优先走缓存方法，失败时回退读取系统 {@code uiMode}。</p>
 *
 * <p>至底部设置入口复刻时，可在 {@link #install} 基础上按需接线（当前只解析方法）。</p>
 */
public final class NightMode {

    /** 缓存的宿主 {@code SoulSettings.isNightMode} 方法（原 {@code o.d}）。 */
    private static Method isNightModeMethod;

    private NightMode() {
    }

    /**
     * 解析并缓存宿主昼夜模式方法（原 {@code o.c} 前半段）。
     *
     * <p>加载失败时静默保持未缓存，交由 {@link #isNightMode} 的系统回退兜底。</p>
     */
    public static void install(ClassLoader loader) {
        try {
            Class<?> settings = loader.loadClass(HostClasses.LIB_SOUL_SETTINGS);
            isNightModeMethod = settings.getMethod("isNightMode", (Class<?>[]) null);
        } catch (Throwable ignored) {
            // 宿主未提供该方法时保持未缓存。
        }
    }

    /**
     * 判断给定 Activity 当前是否处于夜间模式（原 {@code o.b}）。
     *
     * <p>优先调用缓存方法；反射失败或未缓存时回退
     * {@code (uiMode & 0x30) == 0x20}。</p>
     */
    public static boolean isNightMode(Activity activity) {
        try {
            if (isNightModeMethod != null) {
                return Boolean.TRUE.equals(isNightModeMethod.invoke(null, (Object[]) null));
            }
        } catch (ReflectiveOperationException ignored) {
            // 宿主方法调用失败时走系统回退。
        }
        int uiMode = activity.getResources().getConfiguration().uiMode;
        return (uiMode & 0x30) == 0x20;
    }
}
