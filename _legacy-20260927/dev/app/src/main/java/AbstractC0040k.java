package dev.local.newsoul;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0040k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0040k {


    public static int a() {
        return android.view.WindowInsets.Type.ime();
    }

    public static android.graphics.Insets b(android.view.WindowInsets p0, int p1) {
        return p0.getInsetsIgnoringVisibility(p1);
    }

    public static android.graphics.Rect c(android.view.WindowMetrics p0) {
        return p0.getBounds();
    }

    public static android.view.WindowInsets d(android.view.WindowMetrics p0) {
        return p0.getWindowInsets();
    }

    public static android.view.WindowInsetsController e(android.view.View p0) {
        return p0.getWindowInsetsController();
    }

    public static android.view.WindowMetrics f(android.view.WindowManager p0) {
        return p0.getCurrentWindowMetrics();
    }

    public static void g(android.view.WindowInsetsController p0, int p1) {
        p0.hide(p1);
        return;
    }

    public static boolean h(android.view.View p0) {
        return p0.hasOnLongClickListeners();
    }

    public static boolean i(android.view.WindowInsets p0, int p1) {
        return p0.isVisible(p1);
    }

    public static int j() {
        return android.view.WindowInsets.Type.systemBars();
    }
}
