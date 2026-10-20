/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Insets
 *  android.graphics.Rect
 *  android.view.View
 *  android.view.WindowInsets
 *  android.view.WindowInsets$Type
 *  android.view.WindowInsetsController
 *  android.view.WindowManager
 *  android.view.WindowMetrics
 */
package dev.local.newsoul;

import android.graphics.Insets;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.WindowMetrics;

/*
 * Renamed from dev.local.newsoul.k
 */
public abstract class k_0 {
    public static /* bridge */ /* synthetic */ int a() {
        return WindowInsets.Type.ime();
    }

    public static /* bridge */ /* synthetic */ Insets b(WindowInsets windowInsets, int n) {
        return windowInsets.getInsetsIgnoringVisibility(n);
    }

    public static /* bridge */ /* synthetic */ Rect c(WindowMetrics windowMetrics) {
        return windowMetrics.getBounds();
    }

    public static /* bridge */ /* synthetic */ WindowInsets d(WindowMetrics windowMetrics) {
        return windowMetrics.getWindowInsets();
    }

    public static /* bridge */ /* synthetic */ WindowInsetsController e(View view) {
        return view.getWindowInsetsController();
    }

    public static /* bridge */ /* synthetic */ WindowMetrics f(WindowManager windowManager) {
        return windowManager.getCurrentWindowMetrics();
    }

    public static /* bridge */ /* synthetic */ void g(WindowInsetsController windowInsetsController, int n) {
        windowInsetsController.hide(n);
    }

    public static /* bridge */ /* synthetic */ boolean h(View view) {
        return view.hasOnLongClickListeners();
    }

    public static /* bridge */ /* synthetic */ boolean i(WindowInsets windowInsets, int n) {
        return windowInsets.isVisible(n);
    }

    public static /* bridge */ /* synthetic */ int j() {
        return WindowInsets.Type.systemBars();
    }
}

