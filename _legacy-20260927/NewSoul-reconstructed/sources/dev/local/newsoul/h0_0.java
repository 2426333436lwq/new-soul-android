/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.widget.ImageView
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import dev.local.newsoul.Q0;
import dev.local.newsoul.q2;

/*
 * Renamed from dev.local.newsoul.h0
 */
public final class h0_0
implements Runnable {
    public final int a;
    public final Activity b;
    public final q2 c;
    public final ImageView d;
    public final TextView e;
    public final TextView f;
    public final TextView g;

    public /* synthetic */ h0_0(Activity activity, q2 q22, ImageView imageView, TextView textView, TextView textView2, TextView textView3, int n) {
        this.a = n;
        this.b = activity;
        this.c = q22;
        this.d = imageView;
        this.e = textView;
        this.f = textView2;
        this.g = textView3;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                Q0.V(this.b, this.c, this.d, this.e, this.f, this.g, "donation_alipay", "\u652f\u4ed8\u5b9d\u6536\u6b3e\u7801");
                return;
            }
            case 0: 
        }
        Q0.V(this.b, this.c, this.d, this.e, this.f, this.g, "donation_wechat", "\u5fae\u4fe1\u6536\u6b3e\u7801");
    }
}

