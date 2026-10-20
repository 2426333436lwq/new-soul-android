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
 *  android.widget.Button
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.I1;
import dev.local.newsoul.T0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.i0_0;
import dev.local.newsoul.j0_0;
import dev.local.newsoul.p1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.z0_0;

/*
 * Renamed from dev.local.newsoul.s0
 */
public final class s0_0
implements View.OnClickListener {
    public final int a;
    public final q2 b;

    public /* synthetic */ s0_0(q2 q22, int n) {
        this.a = n;
        this.b = q22;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                object = this.b;
                Activity activity = ((q2)object).a;
                q2 q22 = new q2(activity, ((q2)object).b);
                try {
                    object = activity.getPackageManager().getPackageInfo((String)"dev.local.newsoul.public", (int)0).versionName;
                    T0.c((String)object, (String)object);
                }
                catch (Exception exception) {
                    object = null;
                }
                q22.t = "\u70b9\u51fb\u84dd\u594f\u4e91\u6253\u5f00\u4e0b\u8f7d\u9875\u9762\uff0c\u63d0\u53d6\u7801\uff1a3gin\u3002\u4e0b\u8f7d ZIP \u540e\u89e3\u538b\uff0c\u4f7f\u7528\u5176\u4e2d\u7684 APK \u8986\u76d6\u5b89\u88c5\u3002\u68c0\u67e5\u66f4\u65b0\u4e0d\u4f1a\u81ea\u52a8\u4e0b\u8f7d\u6216\u5b89\u88c5\u3002".concat("");
                LinearLayout linearLayout = q22.h;
                Object object2 = object == null ? "\u672a\u77e5" : object;
                object2 = "\u5f53\u524d\u7248\u672c\uff1a".concat((String)object2);
                Thread[] threadArray = q22.g;
                int n = q22.i;
                linearLayout.addView((View)w2.n((Context)threadArray, (String)object2, 15, n));
                TextView textView = w2.n((Context)threadArray, "\u6b63\u5728\u68c0\u67e5\u2026", 14, n);
                textView.setPadding(0, w2.i((Context)threadArray, 8), 0, w2.i((Context)threadArray, 8));
                linearLayout.addView((View)textView);
                object2 = new String[]{null};
                g_0 g_02 = new g_0(activity, object2, 13);
                TextView textView2 = w2.n((Context)threadArray, "\u84dd\u594f\u4e91", 15, q22.k);
                textView2.setMinHeight(w2.i((Context)threadArray, 52));
                textView2.setGravity(17);
                w2.g((View)textView2);
                textView2.setOnClickListener((View.OnClickListener)new i0_0(g_02, 3));
                linearLayout.addView((View)textView2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                q22.d.setNegativeButton((CharSequence)"\u91cd\u65b0\u68c0\u67e5", null);
                q22.g("\u68c0\u67e5\u66f4\u65b0", "\u8fd4\u56de");
                linearLayout = q22.e.getButton(-2);
                threadArray = new Thread[]{null};
                object = new I1((Button)linearLayout, (String[])object2, textView, threadArray, activity, q22, (String)object);
                linearLayout.setOnClickListener((View.OnClickListener)new i0_0((Runnable)object, 2));
                q22.e.setOnDismissListener((DialogInterface.OnDismissListener)new j0_0(2, threadArray));
                ((I1)object).run();
                return;
            }
            case 3: {
                this.b.f();
                return;
            }
            case 2: {
                object = this.b;
                w2.t(((q2)object).a, ((q2)object).b, false, null);
                return;
            }
            case 1: {
                q2 q23;
                boolean bl;
                String string;
                Runnable[] runnableArray = this.b;
                object = runnableArray.a;
                try {
                    string = u1_0.d();
                    bl = runnableArray.b;
                    q23 = new q2((Activity)object, bl);
                    q23.t = "\u70b9\u51fb\u6635\u79f0\u6253\u5f00\u4e3b\u9875\uff0c\u957f\u6309\u8bb0\u5f55\u53ef\u79fb\u9664\u3002\u63d2\u773c\u6309\u767b\u5f55\u8d26\u53f7\u5206\u522b\u4fdd\u5b58\u5728\u672c\u673a\u3002";
                }
                catch (Exception exception) {
                    u1_0.e((Activity)object, exception);
                }
                q23.h.addView((View)w2.n(q23.g, "\u6b63\u5728\u52a0\u8f7d\u2026", 14, q23.j));
                q23.g("\u63d2\u773c\u5217\u8868", "\u5173\u95ed");
                runnableArray = new Runnable[1];
                object = new p1_0(string, (Activity)object, q23, bl, runnableArray, 0);
                runnableArray[0] = object;
                ((p1_0)object).run();
                return;
            }
            case 0: 
        }
        object = this.b;
        z0_0.a(((q2)object).a, ((q2)object).b);
    }
}

