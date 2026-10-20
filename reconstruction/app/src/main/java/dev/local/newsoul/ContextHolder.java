package dev.local.newsoul;

import android.content.Context;

import java.lang.ref.WeakReference;

/** 全局 Context 持有者（模块没有 Application 实例时的替代方案）。 */
public final class ContextHolder {

    private static final WeakReference<Context> REF = new WeakReference<>(null);

    private ContextHolder() {}

    public static void set(Context c) {
        if (c != null) {
            REF.clear();
            // WeakReference 无法重新赋值，用可变字段
            HOLDER = c.getApplicationContext();
        }
    }

    public static Context get() {
        return HOLDER;
    }

    private static volatile Context HOLDER;
}
