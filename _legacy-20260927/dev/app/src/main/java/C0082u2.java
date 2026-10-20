package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.widget.LinearLayout;

/* JADX INFO: renamed from: dev.local.newsoul.C0082u2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0082u2 extends LinearLayout {
    public final /* synthetic */ C0086v2 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0082u2(C0086v2 c0086v2, Context context) {
        super(context);
        this.a = c0086v2;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int iHeight;
        C0086v2 c0086v2 = this.a;
        Activity activity = c0086v2.a.a;
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics currentWindowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            iHeight = (currentWindowMetrics.getBounds().height() - insetsIgnoringVisibility.top) - insetsIgnoringVisibility.bottom;
        } else {
            iHeight = activity.getResources().getDisplayMetrics().heightPixels;
        }
        int iMax = Math.max(AbstractC0090w2.i(c0086v2.a.g, 112), Math.min(AbstractC0090w2.i(c0086v2.a.g, 620), Math.round(iHeight * 0.86f) - AbstractC0090w2.i(c0086v2.a.g, 132)));
        if (View.MeasureSpec.getMode(i2) != 0) {
            iMax = Math.min(iMax, View.MeasureSpec.getSize(i2));
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(iMax, 1073741824));
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.a.a.e();
        }
    }
}
