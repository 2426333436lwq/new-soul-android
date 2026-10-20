/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface$OnClickListener
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
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
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.C0;
import dev.local.newsoul.G1;
import dev.local.newsoul.J1;
import dev.local.newsoul.P0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;

public final class Y1
implements Runnable {
    public final q2 a;
    public final Activity b;
    public final boolean c;
    public final P0 d;
    public final boolean e;

    public /* synthetic */ Y1(q2 q22, Activity activity, boolean bl, P0 p0, boolean bl2) {
        this.a = q22;
        this.b = activity;
        this.c = bl;
        this.d = p0;
        this.e = bl2;
    }

    @Override
    public final void run() {
        Activity activity;
        Object object = this.a;
        if (((q2)object).e.isShowing() && !(activity = this.b).isFinishing() && !activity.isDestroyed()) {
            ((q2)object).e.dismiss();
            boolean bl = this.c;
            q2 q22 = new q2(activity, bl);
            object = this.e ? "Soul \u66f4\u65b0\u68c0\u6d4b\u7ed3\u679c" : "\u5bbf\u4e3b\u517c\u5bb9\u68c0\u6d4b";
            LinearLayout linearLayout = w2.r(q22, (String)object, "\u68c0\u67e5\u5f53\u524d Soul \u7684\u529f\u80fd\u9002\u914d\u72b6\u6001\uff0c\u4e0d\u4f1a\u89e6\u53d1\u53d1\u9001\u3001\u70b9\u8d5e\u6216\u4e0b\u8f7d\u3002\u529f\u80fd\u662f\u5426\u771f\u6b63\u751f\u6548\u4ecd\u9700\u8fdb\u5165\u5bf9\u5e94\u9875\u9762\u9a8c\u8bc1\u3002", null);
            object = this.d;
            String string = ((P0)object).b();
            Context context = q22.g;
            int n = q22.i;
            string = w2.n(context, string, 16, n);
            string.setTextIsSelectable(true);
            linearLayout.addView((View)string);
            string = ((P0)object).a(true);
            if (!string.isEmpty()) {
                string = w2.n(context, string, 14, n);
                string.setPadding(0, w2.i((Context)activity, 12), 0, w2.i((Context)activity, 12));
                string.setTextIsSelectable(true);
                linearLayout.addView((View)string);
            }
            String string2 = ((P0)object).a(false);
            boolean bl2 = string2.isEmpty();
            int n2 = q22.k;
            if (!bl2) {
                string = w2.n(context, "\u6b63\u5e38\u68c0\u6d4b\u9879", 15, n2);
                string.setMinHeight(w2.i(context, 48));
                string.setGravity(16);
                w2.g((View)string);
                string2 = w2.n(context, string2, 13, n);
                string2.setTextIsSelectable(true);
                string2.setVisibility(8);
                string.setContentDescription((CharSequence)"\u6b63\u5e38\u68c0\u6d4b\u9879\uff0c\u5c55\u5f00");
                string.setOnClickListener((View.OnClickListener)new G1((TextView)string2, (TextView)string));
                linearLayout.addView((View)string, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                linearLayout.addView((View)string2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
            }
            w2.c(linearLayout, "\u5df2\u6ce8\u518c\u4e0d\u4ee3\u8868\u529f\u80fd\u5b9e\u6d4b\u901a\u8fc7\u3002");
            context = w2.n(context, "\u590d\u5236\u68c0\u6d4b\u7ed3\u679c", 15, n2);
            context.setGravity(17);
            context.setMinHeight(w2.i((Context)activity, 48));
            w2.g((View)context);
            context.setOnClickListener((View.OnClickListener)new C0(activity, object, 2));
            linearLayout.addView((View)context);
            q22.d.setNeutralButton((CharSequence)"\u91cd\u65b0\u68c0\u6d4b", (DialogInterface.OnClickListener)new J1(activity, bl));
            q22.g("\u529f\u80fd\u517c\u5bb9\u6027\u68c0\u6d4b", "\u8fd4\u56de");
            activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).edit().putString("hook_diagnostics_last_shown_version", ((P0)object).a).apply();
        }
    }
}

