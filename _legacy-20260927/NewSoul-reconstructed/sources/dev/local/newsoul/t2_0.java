/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View$MeasureSpec
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.v2_0;
import dev.local.newsoul.w2;

/*
 * Renamed from dev.local.newsoul.t2
 */
public final class t2_0
extends LinearLayout {
    public final Context a;
    public final v2_0 b;

    public t2_0(v2_0 v2_02, Context context, Context context2) {
        this.b = v2_02;
        this.a = context2;
        super(context);
    }

    public final void onMeasure(int n, int n2) {
        int n3;
        Context context = this.a;
        int n4 = w2.i(context, 48);
        for (TextView textView : this.b.d) {
            n3 = n4;
            if (textView != null) {
                n3 = (int)Math.ceil(textView.getPaint().measureText(textView.getText().toString()));
                n3 = Math.max(n4, w2.i(context, 24) + n3);
            }
            n4 = n3;
        }
        int n5 = n4 *= 5;
        if (View.MeasureSpec.getMode((int)n) != 0) {
            n5 = Math.max(n4, View.MeasureSpec.getSize((int)n));
        }
        for (n = 0; n < this.getChildCount(); ++n) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.getChildAt(n).getLayoutParams();
            n3 = n5 / 5;
            n4 = n < n5 % 5 ? 1 : 0;
            layoutParams.width = n3 + n4;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)n5, (int)0x40000000), n2);
    }
}

