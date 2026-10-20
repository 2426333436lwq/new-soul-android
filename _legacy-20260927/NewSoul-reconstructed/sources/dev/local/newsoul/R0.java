/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 */
package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import dev.local.newsoul.w2;

public final class R0
extends ViewGroup {
    public final int a;

    public R0(Context context) {
        super(context);
        this.a = w2.i(context, 8);
    }

    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(-1, -2);
    }

    public final void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        int n5 = n3 - n;
        n = this.getLayoutDirection();
        n4 = 1;
        if (n != 1) {
            n4 = 0;
        }
        n3 = n = (n2 = 0);
        int n6 = n;
        for (int i = 0; i < this.getChildCount(); ++i) {
            View view = this.getChildAt(i);
            int n7 = view.getMeasuredWidth();
            int n8 = view.getMeasuredHeight();
            int n9 = this.a;
            int n10 = n6;
            int n11 = n3;
            n = n2;
            if (n2 > 0) {
                n10 = n6;
                n11 = n3;
                n = n2;
                if (n2 + n7 > n5) {
                    n10 = n6 + (n3 + n9);
                    n = 0;
                    n11 = 0;
                }
            }
            n2 = n4 != 0 ? n5 - n - n7 : n;
            view.layout(n2, n10, n2 + n7, n10 + n8);
            n2 = n + (n7 + n9);
            n3 = Math.max(n11, n8);
            n6 = n10;
        }
    }

    public final void onMeasure(int n, int n2) {
        int n3;
        int n4 = View.MeasureSpec.getSize((int)n);
        int n5 = n = (n3 = 0);
        for (int i = 0; i < this.getChildCount(); ++i) {
            View view = this.getChildAt(i);
            int n6 = view.getLayoutParams().width;
            int n7 = n6 == -1 ? n4 : Math.min(n4, n6);
            view.measure(View.MeasureSpec.makeMeasureSpec((int)n7, (int)0x40000000), View.MeasureSpec.makeMeasureSpec((int)0, (int)0));
            int n8 = this.a;
            n6 = n3;
            int n9 = n;
            int n10 = n5;
            if (n5 > 0) {
                n6 = n3;
                n9 = n;
                n10 = n5;
                if (n5 + n7 > n4) {
                    n6 = n3 + (n + n8);
                    n9 = 0;
                    n10 = 0;
                }
            }
            n5 = n10 + (n7 + n8);
            n = Math.max(n9, view.getMeasuredHeight());
            n3 = n6;
        }
        this.setMeasuredDimension(n4, View.resolveSize((int)(n3 + n), (int)n2));
    }
}

