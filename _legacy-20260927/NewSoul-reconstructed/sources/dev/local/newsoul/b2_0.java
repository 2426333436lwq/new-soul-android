/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View$MeasureSpec
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.w2;

/*
 * Renamed from dev.local.newsoul.b2
 */
public final class b2_0
extends LinearLayout {
    public final Activity a;
    public final TextView b;
    public final EditText c;

    public b2_0(Context context, Activity activity, TextView textView, EditText editText) {
        this.a = activity;
        this.b = textView;
        this.c = editText;
        super(context);
    }

    public final void onMeasure(int n, int n2) {
        int n3 = View.MeasureSpec.getSize((int)n);
        Activity activity = this.a;
        int n4 = w2.i((Context)activity, 64);
        TextView textView = this.b;
        int n5 = (int)Math.ceil(textView.getPaint().measureText(textView.getText().toString()));
        int n6 = textView.getPaddingLeft();
        n5 = Math.max(n4, textView.getPaddingRight() + (n6 + n5));
        n4 = View.MeasureSpec.getMode((int)n);
        n6 = 0;
        n3 = n4 != 0 && n3 < w2.i((Context)activity, 128) + n5 ? 1 : 0;
        this.setOrientation(n3);
        EditText editText = this.c;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)editText.getLayoutParams();
        n5 = n3 != 0 ? -1 : 0;
        layoutParams.width = n5;
        float f = n3 != 0 ? 0.0f : 1.0f;
        layoutParams.weight = f;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)textView.getLayoutParams();
        n5 = n3 != 0 ? 0 : w2.i((Context)activity, 8);
        layoutParams2.setMarginStart(n5);
        n5 = n3 != 0 ? 0 : w2.i((Context)activity, 8);
        layoutParams2.leftMargin = n5;
        n5 = n6;
        if (n3 != 0) {
            n5 = w2.i((Context)activity, 8);
        }
        layoutParams2.topMargin = n5;
        n5 = n3 != 0 ? 0x800005 : 16;
        layoutParams2.gravity = n5;
        layoutParams.height = -2;
        layoutParams2.height = -2;
        super.onMeasure(n, n2);
        if (n3 == 0) {
            layoutParams.height = n3 = Math.max(editText.getMeasuredHeight(), textView.getMeasuredHeight());
            layoutParams2.height = n3;
            super.onMeasure(n, n2);
        }
    }
}

