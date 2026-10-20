/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Insets
 *  android.os.Build$VERSION
 *  android.view.View$MeasureSpec
 *  android.view.WindowMetrics
 *  android.widget.LinearLayout
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.os.Build;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.LinearLayout;
import dev.local.newsoul.H0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.v2_0;
import dev.local.newsoul.w2;

public final class u2
extends LinearLayout {
    public final v2_0 a;

    public u2(v2_0 v2_02, Context context) {
        this.a = v2_02;
        super(context);
    }

    public final void onMeasure(int n, int n2) {
        int n3;
        int n4;
        v2_0 v2_02 = this.a;
        Activity activity = v2_02.a.a;
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics windowMetrics = k_0.f(activity.getWindowManager());
            activity = k_0.b(k_0.d(windowMetrics), k_0.j());
            n4 = k_0.c(windowMetrics).height() - H0.a((Insets)activity) - H0.i((Insets)activity);
        } else {
            n4 = activity.getResources().getDisplayMetrics().heightPixels;
        }
        n4 = n3 = Math.max(w2.i(v2_02.a.g, 112), Math.min(w2.i(v2_02.a.g, 620), Math.round((float)n4 * 0.86f) - w2.i(v2_02.a.g, 132)));
        if (View.MeasureSpec.getMode((int)n2) != 0) {
            n4 = Math.min(n3, View.MeasureSpec.getSize((int)n2));
        }
        super.onMeasure(n, View.MeasureSpec.makeMeasureSpec((int)n4, (int)0x40000000));
    }

    public final void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (bl) {
            this.a.a.e();
        }
    }
}

