package dev.local.newsoul.framework;

import android.util.Log;

import io.github.libxposed.api.XposedInterface;

/**
 * 模块统一日志门面。
 *
 * <p>日志 TAG 与原模块保持一致（{@code SoulHook102}），日志通过 libxposed 接口写入
 * LSPosed 日志，而不是 Android logcat，便于用户在 LSPosed 管理器中查看。</p>
 */
public final class Logger {

    /** 与原模块一致的日志 TAG。 */
    public static final String TAG = "SoulHook102";

    private static volatile XposedInterface sXposed;

    private Logger() {
    }

    /** 由模块入口在 {@code onModuleLoaded} 阶段注入框架接口。 */
    public static void attach(XposedInterface xposed) {
        sXposed = xposed;
    }

    public static void d(String message) {
        log(Log.DEBUG, message, null);
    }

    public static void i(String message) {
        log(Log.INFO, message, null);
    }

    public static void w(String message) {
        log(Log.WARN, message, null);
    }

    public static void w(String message, Throwable throwable) {
        log(Log.WARN, message, throwable);
    }

    public static void e(String message) {
        log(Log.ERROR, message, null);
    }

    public static void e(String message, Throwable throwable) {
        log(Log.ERROR, message, throwable);
    }

    private static void log(int priority, String message, Throwable throwable) {
        XposedInterface xposed = sXposed;
        if (xposed == null) {
            return;
        }
        try {
            if (throwable != null) {
                xposed.log(priority, TAG, message, throwable);
            } else {
                xposed.log(priority, TAG, message);
            }
        } catch (Throwable ignored) {
            // 日志失败不影响宿主，静默忽略
        }
    }
}