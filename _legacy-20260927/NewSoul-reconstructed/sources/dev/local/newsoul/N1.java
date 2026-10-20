/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface$OnDismissListener
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.h0_0;
import dev.local.newsoul.i0_0;
import dev.local.newsoul.j0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;

public final class N1
implements View.OnClickListener {
    public final int a;
    public final Activity b;
    public final boolean c;

    public /* synthetic */ N1(Activity activity, boolean bl, int n) {
        this.a = n;
        this.b = activity;
        this.c = bl;
    }

    public final void onClick(View view) {
        Object object = this.b;
        boolean bl = this.c;
        switch (this.a) {
            default: {
                view = g0_0.a;
                V.h((Context)object);
                new f0_0((Activity)object, bl);
                return;
            }
            case 2: {
                Object object2 = this.b;
                object = new q2((Activity)object2, bl);
                ((q2)object).t = "\u9009\u62e9\u6536\u6b3e\u65b9\u5f0f\u540e\uff0c\u53ef\u7528\u53e6\u4e00\u53f0\u8bbe\u5907\u626b\u7801\uff0c\u6216\u4fdd\u5b58\u622a\u56fe\u540e\u8bc6\u522b\u3002";
                Object object3 = ((q2)object).g;
                LinearLayout linearLayout = new LinearLayout(object3);
                linearLayout.setOrientation(0);
                linearLayout.setGravity(17);
                w2.v(linearLayout, null, 2);
                TextView textView = Q0.g((q2)object, "\u5fae\u4fe1");
                view = Q0.g((q2)object, "\u652f\u4ed8\u5b9d");
                w2.w(textView, 0);
                w2.w((TextView)view, 1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams.rightMargin = Q0.p(object3, 6);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams2.leftMargin = Q0.p(object3, 6);
                linearLayout.addView((View)textView, (ViewGroup.LayoutParams)layoutParams);
                linearLayout.addView(view, (ViewGroup.LayoutParams)layoutParams2);
                layoutParams2 = ((q2)object).h;
                layoutParams2.addView((View)linearLayout, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                layoutParams = new TextView(object3);
                layoutParams.setText((CharSequence)"");
                layoutParams.setTextSize((float)13);
                layoutParams.setTextColor(((q2)object).j);
                layoutParams.setVisibility(8);
                layoutParams.setAccessibilityLiveRegion(1);
                layoutParams.setGravity(17);
                layoutParams.setPadding(0, Q0.p(object3, 14), 0, Q0.p(object3, 8));
                layoutParams2.addView((View)layoutParams, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                linearLayout = new ImageView(object3);
                linearLayout.setAdjustViewBounds(true);
                linearLayout.setScaleType(ImageView.ScaleType.FIT_CENTER);
                linearLayout.setVisibility(8);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = Q0.p(object3, 14);
                layoutParams2.addView((View)linearLayout, (ViewGroup.LayoutParams)layoutParams3);
                object3 = new h0_0((Activity)object2, (q2)object, (ImageView)linearLayout, (TextView)layoutParams, textView, (TextView)view, 0);
                object2 = new h0_0((Activity)object2, (q2)object, (ImageView)linearLayout, (TextView)layoutParams, (TextView)view, textView, 1);
                textView.setOnClickListener((View.OnClickListener)new i0_0((Runnable)object3, 0));
                view.setOnClickListener((View.OnClickListener)new i0_0((Runnable)object2, 1));
                ((q2)object).g("\u652f\u6301\u4f5c\u8005", "\u8fd4\u56de");
                ((q2)object).e.setOnDismissListener((DialogInterface.OnDismissListener)new j0_0(0, linearLayout));
                return;
            }
            case 1: {
                w2.t((Activity)object, bl, false, null);
                return;
            }
            case 0: 
        }
        w2.u((Activity)object, bl);
    }
}

