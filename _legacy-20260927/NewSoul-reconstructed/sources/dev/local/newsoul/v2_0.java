/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Color
 *  android.os.Build$VERSION
 *  android.text.SpannableStringBuilder
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.HorizontalScrollView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ScrollView
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import dev.local.newsoul.L2;
import dev.local.newsoul.M0;
import dev.local.newsoul.Z0;
import dev.local.newsoul.k0_0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.s2_0;
import dev.local.newsoul.t2_0;
import dev.local.newsoul.u2;
import dev.local.newsoul.w2;
import dev.local.newsoul.x0_0;

/*
 * Renamed from dev.local.newsoul.v2
 */
public final class v2_0 {
    public static final String[] n = new String[]{"\u804a\u5929", "\u5e7f\u573a", "\u4e3b\u9875", "\u5e7f\u544a", "\u5176\u4ed6"};
    public final q2 a;
    public final q2[] b = new q2[5];
    public final ScrollView[] c = new ScrollView[5];
    public final TextView[] d = new TextView[5];
    public final Z0 e;
    public final HorizontalScrollView f;
    public final t2_0 g;
    public final M0 h;
    public int i = -1;
    public int j;
    public int k;
    public float l;
    public boolean m;

    public v2_0(Activity object, boolean bl) {
        object = new q2((Activity)object, bl);
        this.a = object;
        object.t = "\u957f\u6309 Soul \u5e95\u90e8\u5bfc\u822a\u680f\u7684\u56fe\u6807\uff0c\u53ef\u4ee5\u5feb\u901f\u6253\u5f00\u63d2\u4ef6\u8bbe\u7f6e\u3002";
        Object object2 = object.c;
        object2.c = true;
        k0_0 k0_02 = new k0_0(this);
        object = object.g;
        this.e = new Z0((Context)object, k0_02);
        k0_02 = new HorizontalScrollView((Context)object);
        this.f = k0_02;
        k0_02.setHorizontalScrollBarEnabled(false);
        k0_02.setFillViewport(true);
        object2 = new M0((Context)object, (L2)object2, false);
        this.h = object2;
        object2.setPadding(w2.i((Context)object, 4), w2.i((Context)object, 4), w2.i((Context)object, 4), w2.i((Context)object, 4));
        object2 = new t2_0(this, (Context)object, (Context)object);
        this.g = object2;
        object2.setGravity(16);
        w2.v((LinearLayout)object2, null, 5);
        object2.setContentDescription("\u8bbe\u7f6e\u5206\u7c7b");
        for (int i = 0; i < 5; ++i) {
            object2 = w2.n((Context)object, n[i], 15, this.a.j);
            this.d[i] = object2;
            object2.setSingleLine(true);
            object2.setGravity(17);
            object2.setPadding(w2.i((Context)object, 12), w2.i((Context)object, 8), w2.i((Context)object, 12), w2.i((Context)object, 8));
            object2.setMinHeight(w2.i((Context)object, 48));
            w2.w((TextView)object2, i);
            object2.setOnClickListener(new x0_0(this, i, 2));
            this.g.addView((View)object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
        }
        this.h.addView((View)this.g, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -2));
        this.f.addView((View)this.h, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        this.g.addOnLayoutChangeListener(new s2_0(this));
    }

    public final void a() {
        int n = this.j;
        HorizontalScrollView horizontalScrollView = this.d;
        TextView textView = horizontalScrollView[n];
        Object object = horizontalScrollView[this.k];
        if (textView != null && object != null && textView.getWidth() != 0) {
            float f = textView.getLeft();
            float f2 = (float)(object.getLeft() - textView.getLeft()) * this.l + f;
            f = textView.getWidth();
            int n2 = Math.round((float)(object.getWidth() - textView.getWidth()) * this.l + f);
            float f4 = this.g.getLeft();
            f = n2;
            boolean bl = this.m;
            this.h.c(f4 + f2, f, bl);
            object = this.a;
            bl = object.b;
            n = bl ? w2.b : -16746133;
            int n3 = bl ? -14596798 : -2493459;
            n3 = w2.q(n, bl, n3);
            for (n = 0; n < ((TextView[])horizontalScrollView).length; ++n) {
                f = n == this.j ? 1.0f - this.l : 0.0f;
                f4 = f;
                if (n == this.k) {
                    f4 = f + this.l;
                }
                textView = horizontalScrollView[n];
                int n4 = object.j;
                f = Color.red((int)n4);
                int n5 = Math.round((float)(Color.red((int)n3) - Color.red((int)n4)) * f4 + f);
                f = Color.green((int)n4);
                int n6 = Math.round((float)(Color.green((int)n3) - Color.green((int)n4)) * f4 + f);
                f = Color.blue((int)n4);
                textView.setTextColor(Color.rgb((int)n5, (int)n6, (int)Math.round((float)(Color.blue((int)n3) - Color.blue((int)n4)) * f4 + f)));
            }
            n = Math.round(f2);
            n3 = w2.i(object.g, 8) + (n + n2);
            horizontalScrollView = this.f;
            if (f2 < (float)horizontalScrollView.getScrollX()) {
                horizontalScrollView.scrollTo(Math.round(f2), 0);
            } else {
                n = horizontalScrollView.getScrollX();
                if (n3 > horizontalScrollView.getWidth() + n) {
                    horizontalScrollView.scrollTo(n3 - horizontalScrollView.getWidth(), 0);
                }
            }
        }
    }

    public final void b(int n, boolean bl) {
        Z0 z0 = this.e;
        if (n >= 0) {
            if (n < z0.b.length) {
                z0.b();
                if (z0.f >= 0 && bl) {
                    z0.a();
                    int n2 = z0.g;
                    int n3 = z0.h;
                    bl = false;
                    boolean bl2 = false;
                    if (n2 == n3 && !z0.k) {
                        n3 = z0.f;
                        if (n3 != n) {
                            z0.g = n3;
                            z0.h = n;
                            z0.i = 0.0f;
                            bl = bl2;
                            if (Math.abs(n - n3) > 1) {
                                bl = true;
                            }
                            z0.j = bl;
                            z0.g(z0.g);
                            z0.g(z0.h);
                            z0.j();
                            z0.k(true);
                        }
                    } else if (!(z0.k || n != n3 && n != n2)) {
                        if (n == n3) {
                            bl = true;
                        }
                        z0.k(bl);
                    } else {
                        z0.i(n);
                    }
                } else {
                    z0.c(n);
                }
            }
        } else {
            ((Object)((Object)z0)).getClass();
        }
    }

    public final void c() {
        int n;
        q2 q22 = this.a;
        Object object = new u2(this, q22.g);
        object.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        Context context = q22.g;
        layoutParams.rightMargin = n = w2.i(context, 16);
        layoutParams.leftMargin = n;
        layoutParams.topMargin = w2.i(context, 4);
        layoutParams.bottomMargin = w2.i(context, 8);
        object.addView((View)this.f, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
        object.addView((View)this.e, (ViewGroup.LayoutParams)layoutParams);
        this.b(0, false);
        q22.i("New Soul", "\u5b8c\u6210", (ViewGroup)object);
        object = q22.p.iterator();
        while (object.hasNext()) {
            ((q2)object.next()).e = q22.e;
        }
        object = q22.a;
        n = object.getResources().getDisplayMetrics().widthPixels;
        if (Build.VERSION.SDK_INT >= 30) {
            n = k_0.c(k_0.f(object.getWindowManager())).width();
        }
        q22.e.getWindow().setLayout(Math.min(w2.i(context, 560), n - w2.i(context, 24)), -2);
        this.d();
    }

    public final void d() {
        q2 q22 = this.a;
        if (q22.f != null && this.i >= 0) {
            while (q22.f.getChildCount() > 1) {
                q22.f.removeViewAt(1);
            }
            q2 q23 = this.b[this.i];
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(q22.t);
            if (q23.t != null) {
                spannableStringBuilder.append((CharSequence)"\n\n").append((CharSequence)n[this.i]).append((CharSequence)"\uff1a\n").append(q23.t);
            }
            spannableStringBuilder = w2.l(q22, "\u63d2\u4ef6\u8bbe\u7f6e", (CharSequence)spannableStringBuilder);
            q22.f.addView((View)spannableStringBuilder);
        }
    }
}

