/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.res.ColorStateList
 *  android.graphics.Typeface
 *  android.os.SystemClock
 *  android.view.MotionEvent
 *  android.view.VelocityTracker
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.animation.DecelerateInterpolator
 *  android.webkit.WebView
 *  android.widget.AbsSeekBar
 *  android.widget.AbsSpinner
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.HorizontalScrollView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ScrollView
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.AbsSeekBar;
import android.widget.AbsSpinner;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import dev.local.newsoul.A0;
import dev.local.newsoul.B2;
import dev.local.newsoul.C1;
import dev.local.newsoul.H;
import dev.local.newsoul.H1;
import dev.local.newsoul.I;
import dev.local.newsoul.K1;
import dev.local.newsoul.L0;
import dev.local.newsoul.L1;
import dev.local.newsoul.L2;
import dev.local.newsoul.N1;
import dev.local.newsoul.P1;
import dev.local.newsoul.Q;
import dev.local.newsoul.Q0;
import dev.local.newsoul.Q1;
import dev.local.newsoul.R1;
import dev.local.newsoul.S;
import dev.local.newsoul.Y0;
import dev.local.newsoul.Z;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.g2_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.h2;
import dev.local.newsoul.j2_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.k0_0;
import dev.local.newsoul.l1_0;
import dev.local.newsoul.m1_0;
import dev.local.newsoul.m_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.s0_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.v2_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.z0_0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public final class Z0
extends FrameLayout {
    public boolean A;
    public boolean B;
    public float C;
    public float D;
    public final k0_0 a;
    public final ViewGroup[] b;
    public final int c;
    public final int d;
    public final int e;
    public int f = -1;
    public int g = -1;
    public int h = -1;
    public float i;
    public boolean j;
    public boolean k;
    public boolean l;
    public float m;
    public float n;
    public final float[] o;
    public final float[] p;
    public final boolean[] q;
    public ValueAnimator r;
    public VelocityTracker s;
    public int t = -1;
    public long u;
    public float v;
    public float w;
    public float x;
    public boolean y;
    public boolean z;

    public Z0(Context context, k0_0 k0_02) {
        super(context);
        this.a = k0_02;
        this.b = new ViewGroup[5];
        this.o = new float[5];
        this.p = new float[5];
        this.q = new boolean[5];
        context = ViewConfiguration.get((Context)context);
        this.c = context.getScaledTouchSlop();
        this.d = Math.max(context.getScaledMinimumFlingVelocity(), Math.round(this.getResources().getDisplayMetrics().density * 400.0f));
        this.e = context.getScaledMaximumFlingVelocity();
        this.setClipChildren(true);
    }

    public static boolean h(View view, float f, float f2) {
        if (!(view.getVisibility() != 0 || f < 0.0f || f2 < 0.0f || f >= (float)view.getWidth() || f2 >= (float)view.getHeight())) {
            if (!(view instanceof CompoundButton && !(view instanceof CheckBox) || view instanceof AbsSpinner || view instanceof EditText || view instanceof HorizontalScrollView || view instanceof AbsSeekBar || view instanceof WebView || view instanceof TextView && ((TextView)view).isTextSelectable() || view.canScrollHorizontally(-1) || view.canScrollHorizontally(1))) {
                if (view instanceof ViewGroup) {
                    view = (ViewGroup)view;
                    for (int i = view.getChildCount() - 1; i >= 0; --i) {
                        View view2 = view.getChildAt(i);
                        if (!Z0.h(view2, (float)view.getScrollX() + f - (float)view2.getLeft() - view2.getTranslationX(), (float)view.getScrollY() + f2 - (float)view2.getTop() - view2.getTranslationY())) continue;
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public final void a() {
        ValueAnimator valueAnimator = this.r;
        this.r = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public final void b() {
        VelocityTracker velocityTracker = this.s;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.s = null;
        this.t = -1;
        this.B = false;
        this.A = false;
        this.z = false;
        this.y = false;
        this.x = 0.0f;
    }

    public final void c(int n) {
        this.a();
        this.g(n);
        int n2 = this.f != n ? 1 : 0;
        this.h = n;
        this.g = n;
        this.f = n;
        this.i = 0.0f;
        this.k = false;
        this.j = false;
        this.j();
        if (n2 != 0) {
            TextView textView;
            v2_0 v2_02 = (v2_0)this.a.a;
            v2_02.i = n;
            v2_02.b[n].e();
            for (n2 = 0; n2 < ((TextView[])(textView = v2_02.d)).length; ++n2) {
                int n3;
                TextView textView2 = textView[n2];
                boolean bl = n2 == n;
                textView2.setSelected(bl);
                textView2 = textView[n2];
                q2 q22 = v2_02.a;
                if (n2 == n) {
                    bl = q22.b;
                    n3 = bl ? w2.b : -16746133;
                    int n4 = bl ? -14596798 : -2493459;
                    n3 = w2.q(n3, bl, n4);
                } else {
                    n3 = q22.j;
                }
                textView2.setTextColor(n3);
                textView = textView[n2];
                textView2 = Typeface.DEFAULT;
                n3 = n2 == n ? 1 : 0;
                textView.setTypeface((Typeface)textView2, n3);
            }
            v2_02.d();
        }
    }

    public final void d() {
        int n;
        boolean bl = this.t >= 0;
        if (bl) {
            MotionEvent motionEvent = MotionEvent.obtain((long)this.u, (long)SystemClock.uptimeMillis(), (int)3, (float)this.v, (float)this.w, (int)0);
            try {
                super.dispatchTouchEvent(motionEvent);
            }
            finally {
                motionEvent.recycle();
            }
        }
        if ((n = this.f) >= 0) {
            if (!this.y) {
                n = this.i >= 0.5f ? this.h : this.g;
            }
            this.a();
            if (n >= 0) {
                this.c(n);
            }
        }
        this.b();
        this.A = bl;
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        boolean bl;
        int n = motionEvent.getActionMasked();
        if (n == 0) {
            bl = this.r != null || this.g != this.h || this.k;
            this.a();
            this.b();
            this.B = bl;
            this.C = this.i;
            float f = this.k ? Math.signum((float)this.h - this.n) : (float)Integer.compare(this.h, this.g);
            this.D = f;
            if (f == 0.0f) {
                this.D = 1.0f;
            }
            this.t = motionEvent.getPointerId(0);
            this.u = motionEvent.getDownTime();
            this.v = motionEvent.getX();
            this.w = f = motionEvent.getY();
            bl = !this.B && Z0.h((View)this, this.v, f);
            this.z = bl;
            this.s = VelocityTracker.obtain();
        }
        if ((velocityTracker = this.s) != null) {
            velocityTracker.addMovement(motionEvent);
        }
        if (n == 5) {
            if (!this.y && !this.B) {
                this.z = true;
            } else {
                this.y = false;
                this.A = true;
                this.k(false);
            }
        }
        if (this.A) {
            if (n == 1 || n == 3) {
                this.b();
            }
            return true;
        }
        bl = super.dispatchTouchEvent(motionEvent);
        if (n == 1 || n == 3) {
            this.b();
        }
        return bl;
    }

    /*
     * Unable to fully structure code
     */
    public final void e(MotionEvent var1_1) {
        if (!this.y && !this.z && this.t >= 0 && var1_1.getPointerCount() == 1) {
            var7_2 = var1_1.findPointerIndex(this.t);
            if (var7_2 < 0) {
                this.z = true;
                return;
            }
            var2_3 = var1_1.getX(var7_2) - this.v;
            var5_4 = var1_1.getY(var7_2);
            var6_5 = this.w;
            var3_6 = this.c;
            var4_7 = Math.abs(var2_3);
            if ((var5_4 = Math.abs(var5_4 - var6_5)) > var3_6 && var5_4 >= 0.8f * var4_7) {
                while (true) {
                    var7_2 = 2;
                    break;
                }
            } else if (var4_7 > var3_6 && var4_7 > 1.5f * var5_4) {
                var7_2 = 1;
            } else {
                if (Math.max(var4_7, var5_4) > var3_6 * 2.0f) ** continue;
                var7_2 = 0;
            }
            if (var7_2 == 2) {
                this.z = true;
            }
            if (var7_2 != 1) {
                return;
            }
            this.y = true;
            if (!this.B) {
                this.g = this.f;
                this.j = false;
            }
            if (this.getParent() != null) {
                this.getParent().requestDisallowInterceptTouchEvent(true);
            }
            this.f(var2_3);
        }
    }

    public final void f(float f) {
        int n;
        this.x = f;
        boolean bl = this.B;
        float f2 = 0.0f;
        if (bl) {
            f2 = this.getWidth();
            float f4 = this.j ? 0.18f : 1.0f;
            f4 = f2 * f4;
            f = f4 == 0.0f ? this.C : Math.max(0.0f, Math.min(1.0f, this.C - f * this.D / f4));
            this.i = f;
            this.j();
            return;
        }
        int n2 = this.f;
        int n3 = this.b.length;
        if (f == 0.0f) {
            n = n2;
        } else {
            n = 1;
            if (!(f < 0.0f)) {
                n = -1;
            }
            n = Math.max(0, Math.min(n3 - 1, n2 + n));
        }
        this.h = n;
        this.g(n);
        float f5 = f2;
        if (this.h != this.f) {
            f5 = this.getWidth() == 0 ? f2 : Math.min(1.0f, Math.abs(f) / (float)this.getWidth());
        }
        this.i = f5;
        this.j();
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void g(int var1_1) {
        block12: {
            block13: {
                block11: {
                    block14: {
                        block15: {
                            block16: {
                                block17: {
                                    var18_2 = this.b;
                                    if (var18_2[var1_1] != null) break block12;
                                    var10_3 = (v2_0)this.a.a;
                                    var11_4 = var10_3.b;
                                    var12_5 = var11_4[var1_1];
                                    var19_6 = var10_3.c;
                                    if (var12_5 != null) break block13;
                                    var10_3 = var10_3.a;
                                    var21_7 = new q2(var10_3.a, var10_3.b);
                                    var21_7.q = true;
                                    var24_8 = var21_7.c;
                                    var24_8.c = true;
                                    var20_9 = var21_7.h;
                                    var20_9.setClipChildren(false);
                                    var20_9.setClipToPadding(false);
                                    var21_7.o = var10_3;
                                    var21_7.e = var10_3.e;
                                    var11_4[var1_1] = var21_7;
                                    var10_3.p.add(var21_7);
                                    var23_10 = var21_7.l;
                                    var3_11 = var21_7.i;
                                    var13_12 = var21_7.a;
                                    var22_13 = var21_7.g;
                                    if (var1_1 == 0) break block14;
                                    var9_14 = var21_7.b;
                                    var4_15 = var21_7.k;
                                    if (var1_1 == 1) break block15;
                                    var7_16 = var21_7.j;
                                    if (var1_1 == 2) break block16;
                                    if (var1_1 == 3) break block17;
                                    var11_4 = w2.o((Context)var13_12);
                                    var12_5 = w2.r(var21_7, "\u7248\u672c\u4e0e\u68c0\u6d4b", null, null);
                                    try {
                                        var14_17 /* !! */  = var13_12.createPackageContext("dev.local.newsoul.public", 0);
                                        var14_17 /* !! */  = var14_17 /* !! */ .getPackageManager().getPackageInfo((String)var14_17 /* !! */ .getPackageName(), (int)0).versionName;
                                        var15_22 = new StringBuilder("\u7248\u672c ");
                                        var15_22.append((String)var14_17 /* !! */ );
                                        var12_5.addView((View)w2.n(var22_13, var15_22.toString(), 15, var3_11));
                                        ** GOTO lbl44
                                    }
                                    catch (Exception var14_18) {
                                        ** continue;
                                    }
lbl42:
                                    // 1 sources

                                    while (true) {
                                        var12_5.addView((View)w2.n(var22_13, "\u7248\u672c\u4fe1\u606f\u6682\u4e0d\u53ef\u7528", 15, var7_16));
lbl44:
                                        // 2 sources

                                        var15_22 = w2.n(var22_13, "\u68c0\u67e5\u66f4\u65b0", 15, var4_15);
                                        w2.g((View)var15_22);
                                        var15_22.setOnClickListener(new s0_0(var21_7, 4));
                                        var14_17 /* !! */  = w2.n(var22_13, "\u529f\u80fd\u517c\u5bb9\u6027\u68c0\u6d4b", 15, var4_15);
                                        w2.g((View)var14_17 /* !! */ );
                                        var14_17 /* !! */ .setContentDescription((CharSequence)"\u68c0\u6d4b\u5f53\u524d Soul \u7248\u672c\u7684\u529f\u80fd\u517c\u5bb9\u6027");
                                        var14_17 /* !! */ .setOnClickListener((View.OnClickListener)new N1((Activity)var13_12, var9_14, 1));
                                        w2.a(var21_7, (LinearLayout)var12_5, (TextView)var15_22, (TextView)var14_17 /* !! */ );
                                        var12_5 = w2.r(var21_7, "\u4ea4\u6d41\u4e0e\u652f\u6301", null, null);
                                        var14_17 /* !! */  = w2.n(var22_13, "\u52a0\u5165 TG \u7fa4\u7ec4", 15, var4_15);
                                        w2.g((View)var14_17 /* !! */ );
                                        var14_17 /* !! */ .setContentDescription((CharSequence)"\u52a0\u5165 TG \u7fa4\u7ec4\uff0c\u6253\u5f00 t.me \u94fe\u63a5");
                                        var14_17 /* !! */ .setOnClickListener((View.OnClickListener)new A0(2, var13_12));
                                        var15_22 = w2.n(var22_13, "\u652f\u6301\u4f5c\u8005", 15, var4_15);
                                        w2.g((View)var15_22);
                                        var15_22.setOnClickListener(new N1((Activity)var13_12, var9_14, 2));
                                        w2.a(var21_7, (LinearLayout)var12_5, (TextView)var14_17 /* !! */ , (TextView)var15_22);
                                        var12_5 = w2.r(var21_7, "\u8fd0\u884c\u9009\u9879", null, null);
                                        w2.d((LinearLayout)var12_5, (SharedPreferences)var11_4, "page_log", "\u8bb0\u5f55\u9875\u9762\u65e5\u5fd7", "\u7528\u4e8e\u6392\u67e5\u9875\u9762\u95ee\u9898\uff0c\u65e5\u5fd7\u5305\u542b\u9875\u9762\u7c7b\u540d\u3002", var3_11);
                                        w2.e(w2.d((LinearLayout)var12_5, (SharedPreferences)var11_4, "startup_toast", "\u663e\u793a\u542f\u52a8\u63d0\u793a", "", var3_11), var21_7, new m_0((Activity)var13_12, 4));
                                        var11_4 = w2.n(var22_13, "\u8c03\u8bd5", 15, var4_15);
                                        w2.g((View)var11_4);
                                        var11_4.setOnClickListener((View.OnClickListener)new N1((Activity)var13_12, var9_14, 3));
                                        w2.x(var21_7, (TextView)var11_4);
                                        var12_5.addView((View)var11_4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                                        var11_4 = S.a;
                                        var13_12 = w2.r(var21_7, "\u914d\u7f6e\u8fc1\u79fb", "\u5bfc\u51fa\u63d2\u4ef6\u8bbe\u7f6e\uff0c\u4e0d\u5305\u542b\u5bc6\u94a5\u3001\u804a\u5929\u8bb0\u5f55\u6216\u8d26\u53f7\u51ed\u636e\u3002\u5bfc\u5165\u53ea\u8986\u76d6\u6587\u4ef6\u4e2d\u652f\u6301\u7684\u8bbe\u7f6e\uff0c\u73b0\u6709\u5bc6\u94a5\u4fdd\u6301\u4e0d\u53d8\u3002", null);
                                        var11_4 = w2.n(var22_13, "\u5bfc\u51fa\u914d\u7f6e", 15, var4_15);
                                        var12_5 = w2.n(var22_13, "\u5bfc\u5165\u914d\u7f6e", 15, var4_15);
                                        w2.g((View)var11_4);
                                        w2.g((View)var12_5);
                                        w2.a(var21_7, (LinearLayout)var13_12, (TextView)var11_4, (TextView)var12_5);
                                        var13_12 = new Q((LinearLayout)var13_12, (TextView)var11_4, (TextView)var12_5);
                                        var11_4.setOnClickListener((View.OnClickListener)new I(var21_7, (Q)var13_12, 1));
                                        var12_5.setOnClickListener((View.OnClickListener)new I(var21_7, (Q)var13_12, 0));
                                        var12_5 = var10_3;
                                        while (true) {
                                            var10_3 = var12_5;
                                            break block11;
                                            break;
                                        }
                                        break;
                                    }
                                }
                                var25_26 = w2.o((Context)var13_12);
                                var21_7.t = "\u52fe\u9009\u8868\u793a\u5c4f\u853d\u5bf9\u5e94\u5e7f\u544a\u3002\u70b9\u51fb\u5206\u7ec4\u65c1\u7684\u66f4\u591a\u6309\u94ae\u6216\u957f\u6309\u5206\u7ec4\u6807\u9898\uff0c\u53ef\u5c4f\u853d\u6216\u53d6\u6d88\u5c4f\u853d\u672c\u7ec4\uff1b\u672a\u5c31\u7eea\u9879\u4f1a\u8df3\u8fc7\u3002\u5df2\u663e\u793a\u7684\u5e7f\u544a\u53ef\u80fd\u9700\u91cd\u65b0\u8fdb\u5165\u9875\u9762\u6216\u91cd\u542f Soul \u540e\u6d88\u5931\u3002";
                                var14_19 /* !! */  = "\u6fc0\u52b1\u5e7f\u544a";
                                var13_12 = new LinearLayout[][]{{"\u542f\u52a8\u4e0e\u6d6e\u5c42", "block_splash", "ad_float", "ad_interstitial"}, {"\u5e7f\u573a\u4e0e\u6d4f\u89c8", "ad_feed", "ad_detail", "ad_video", "ad_tag", "ad_tag_web", "ad_search"}, {"\u804a\u5929\u4e0e\u901a\u77e5", "ad_group", "ad_conversation", "ad_envelope", "ad_notice", "ad_chat_tools"}, {"\u5176\u4ed6\u9875\u9762\u63a8\u5e7f", "ad_planet", "ad_me"}, {"\u6fc0\u52b1\u5e7f\u544a", "ad_reward_chat", "ad_reward_drama", "ad_reward_web"}};
                                var12_5 = new HashMap<K, V>();
                                var15_23 = j_0.a;
                                for (var2_27 = 0; var2_27 < 19; ++var2_27) {
                                    var11_4 = var15_23[var2_27];
                                    var12_5.put(var11_4[0], var11_4);
                                }
                                var2_27 = 0;
                                var11_4 = var10_3;
                                var10_3 = var12_5;
                                while (true) {
                                    var12_5 = var11_4;
                                    if (var2_27 >= 5) ** continue;
                                    var16_33 = var13_12[var2_27];
                                    var15_23 = new ArrayList<E>();
                                    var26_39 = w2.r(var21_7, var16_33[0], null, new K1(var21_7, var25_26, (ArrayList)var15_23));
                                    if (var14_19 /* !! */ .equals(var16_33[0])) {
                                        w2.c(var26_39, "\u5c4f\u853d\u540e\u4e0d\u4f1a\u83b7\u5f97\u5956\u52b1\u6216\u89e3\u9501\u5185\u5bb9\u3002");
                                    }
                                    var12_5 = var14_19 /* !! */ ;
                                    var14_19 /* !! */  = var16_33;
                                    for (var4_15 = 1; var4_15 < var14_19 /* !! */ .length; ++var4_15) {
                                        var17_36 /* !! */  = (String[])var10_3.get(var14_19 /* !! */ [var4_15]);
                                        Objects.requireNonNull(var17_36 /* !! */ , var14_19 /* !! */ [var4_15]);
                                        var27_40 = new L1(var17_36 /* !! */ );
                                        var16_33 = new LinearLayout(var22_13);
                                        var16_33.setGravity(16);
                                        var16_33.setMinimumHeight(w2.i(var22_13, 52));
                                        var28_41 = new CheckBox(var22_13);
                                        var28_41.setTextSize(16.0f);
                                        var29_42 /* !! */  = new int[]{};
                                        var28_41.setTextColor(new ColorStateList((int[][])new int[][]{{-16842910}, var29_42 /* !! */ }, new int[]{var7_16, var3_11}));
                                        var30_43 = new int[]{0x10100A0};
                                        var29_42 /* !! */  = new int[]{};
                                        var9_14 = var24_8.a;
                                        var5_30 = var9_14 != false ? -10329489 : -4735550;
                                        var8_32 = var24_8.a();
                                        var6_31 = var9_14 != false ? -8224106 : -10066317;
                                        var28_41.setButtonTintList(new ColorStateList((int[][])new int[][]{{-16842910}, var30_43, var29_42 /* !! */ }, new int[]{var5_30, var8_32, var6_31}));
                                        var24_8.b((View)var28_41, 12, L2.e(12));
                                        var28_41.setGravity(8388627);
                                        var28_41.setMinHeight(w2.i(var22_13, 52));
                                        var28_41.setPadding(w2.i(var22_13, 4), w2.i(var22_13, 4), w2.i(var22_13, 8), w2.i(var22_13, 4));
                                        var16_33.addView((View)var28_41, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
                                        if (!var17_36 /* !! */ [2].isEmpty()) {
                                            var16_33.addView((View)w2.l(var21_7, var17_36 /* !! */ [1], var17_36 /* !! */ [2]));
                                        }
                                        var26_39.addView((View)var16_33, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                                        var29_42 /* !! */  = (int[])new g2_0(var17_36 /* !! */ [0], var17_36 /* !! */ [1], var27_40);
                                        var17_36 /* !! */  = (h2)var23_10.get(var26_39);
                                        var16_33 = var17_36 /* !! */ ;
                                        if (var17_36 /* !! */  == null) {
                                            var16_33 = new h2(var21_7, var26_39);
                                            var23_10.put(var26_39, var16_33);
                                        }
                                        var16_33.a.add(var28_41);
                                        var16_33 = new m1_0(var29_42 /* !! */ , var27_40, var28_41, var25_26, var16_33, 3);
                                        var28_41.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new Q1((g2_0)var29_42 /* !! */ , var27_40, (m1_0)var16_33, var25_26));
                                        var21_7.a((Runnable)var16_33);
                                        var16_33.run();
                                        var15_23.add(var29_42 /* !! */ );
                                    }
                                    var15_23 = (h2)var23_10.get(var26_39);
                                    var26_39.removeView((View)var15_23.e);
                                    var14_19 /* !! */  = var15_23.d;
                                    var26_39.removeView((View)var14_19 /* !! */ );
                                    var26_39.addView((View)var15_23.e);
                                    var26_39.addView((View)var14_19 /* !! */ );
                                    ++var2_27;
                                    var14_19 /* !! */  = var12_5;
                                }
                            }
                            var11_4 = u1_0.a;
                            var12_5 = w2.r(var21_7, "\u4e3b\u9875\u63d2\u773c", "\u5728\u7528\u6237\u4e3b\u9875\u6253\u5f00\u53f3\u4e0a\u89d2\u83dc\u5355\uff0c\u70b9\u51fb\u300c\u63d2\u773c\u300d\u3002\n\n\u5217\u8868\u4e2d\u70b9\u51fb\u6635\u79f0\u6253\u5f00\u4e3b\u9875\uff0c\u957f\u6309\u8bb0\u5f55\u53ef\u79fb\u9664\u3002\u8bb0\u5f55\u4fdd\u5b58\u5728\u672c\u673a\uff0c\u6309\u5f53\u524d\u767b\u5f55\u8d26\u53f7\u5206\u522b\u4fdd\u5b58\uff1b\u4e0d\u4f1a\u5173\u6ce8\u5bf9\u65b9\u3002\u91cd\u65b0\u6253\u5f00\u4e3b\u9875\u4ecd\u9075\u5faa Soul \u539f\u6709\u7684\u8bbf\u95ee\u884c\u4e3a\u3002", null);
                            var11_4 = u1_0.a(var21_7, "\u63d2\u773c\u5217\u8868");
                            var11_4.setOnClickListener((View.OnClickListener)new s0_0(var21_7, 1));
                            var12_5.addView((View)var11_4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                            if (!u1_0.f) {
                                var12_5.addView((View)w2.n(var22_13, "\u5f53\u524d\u7248\u672c\u7684\u4e3b\u9875\u63d2\u773c\u5165\u53e3\u5c1a\u672a\u5c31\u7eea", 13, var7_16));
                            }
                            var11_4 = z0_0.a;
                            var11_4 = w2.r(var21_7, "\u4e2a\u4eba\u4e3b\u9875", null, null);
                            var12_5 = w2.n(var22_13, "\u4fe1\u606f\u4f2a\u88c5", 16, var3_11);
                            var12_5.setGravity(16);
                            var12_5.setMinHeight(w2.i(var22_13, 48));
                            var12_5.setPadding(w2.i(var22_13, 12), w2.i(var22_13, 10), w2.i(var22_13, 12), w2.i(var22_13, 10));
                            var24_8.b((View)var12_5, 12, L2.e(12));
                            w2.g((View)var12_5);
                            var12_5.setOnClickListener((View.OnClickListener)new s0_0(var21_7, 0));
                            var11_4.addView((View)var12_5, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                            break block11;
                        }
                        var12_5 = w2.o((Context)var13_12);
                        var14_20 = w2.r(var21_7, "\u5e16\u5b50\u7b5b\u9009", "\u6027\u522b\u7b5b\u9009\u4e0e\u5c4f\u853d\u8bcd\u8fc7\u6ee4\u72ec\u7acb\u5f00\u542f\uff0c\u9690\u85cf\u7684\u5e16\u5b50\u4e0d\u53c2\u4e0e\u70b9\u8d5e\u3002\u6027\u522b\u6309\u4f5c\u8005\u8d44\u6599\u5224\u65ad\u3002\n\n\u5c4f\u853d\u8bcd\u5339\u914d\u6b63\u6587\u3001\u6807\u9898\u548c\u8bdd\u9898\uff0c\u82f1\u6587\u4e0d\u533a\u5206\u5927\u5c0f\u5199\uff1b\u4e0d\u8bc6\u522b\u56fe\u7247\u5185\u6587\u5b57\u3001\u8bed\u97f3\u6216\u8bc4\u8bba\u3002", null);
                        var11_4 = B2.i != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                        var2_28 = var21_7.i;
                        var11_4 = w2.d((LinearLayout)var14_20, (SharedPreferences)var12_5, "feed_filter_enabled", "\u6309\u6027\u522b\u7b5b\u9009", (String)var11_4, var2_28);
                        w2.f(var21_7, (r2_0)var11_4, new H1(0));
                        var16_34 = new LinearLayout(var22_13);
                        var16_34.setOrientation(1);
                        var16_34.setTag((Object)var21_7);
                        var14_20.addView((View)var16_34, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                        var17_37 /* !! */  = w2.n(var22_13, "\u663e\u793a\u6027\u522b", 16, var3_11);
                        var17_37 /* !! */ .setPadding(0, w2.i(var22_13, 12), 0, w2.i(var22_13, 10));
                        var16_34.addView((View)var17_37 /* !! */ );
                        var15_24 = new String[]{"ANY", "FEMALE", "MALE"};
                        var17_37 /* !! */  = new C1(var21_7, var17_37 /* !! */ , new String[]{"\u4e0d\u9650", "\u4ec5\u5973\u751f", "\u4ec5\u7537\u751f"}, 15);
                        var16_34.addView((View)var17_37 /* !! */ , (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                        var23_10 = new m1_0(var12_5, var17_37 /* !! */ , var15_24, (View)w2.d(var16_34, (SharedPreferences)var12_5, "feed_filter_keep_unknown", "\u4fdd\u7559\u6027\u522b\u672a\u77e5\u7684\u5e16\u5b50", "", var21_7.i).getParent(), w2.c(var16_34, "\u4e0d\u9650\u5305\u542b\u6027\u522b\u672a\u77e5\u7684\u5e16\u5b50\u3002"), 2);
                        var17_37 /* !! */ .h = new P1((SharedPreferences)var12_5, (String[])var15_24, (C1)var17_37 /* !! */ , (m1_0)var23_10);
                        var23_10.run();
                        var21_7.a((Runnable)var23_10);
                        var11_4.a(new g_0(var16_34, var11_4, 10));
                        var11_4 = B2.a != false && B2.i != false ? "" : "\u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                        w2.f(var21_7, w2.d((LinearLayout)var14_20, (SharedPreferences)var12_5, "feed_keywords_enabled_v1", "\u542f\u7528\u5c4f\u853d\u8bcd\u8fc7\u6ee4", (String)var11_4, var2_28), new H1(3));
                        var11_4 = w2.n(var22_13, "", 16, var4_15);
                        var15_24 = new g_0(var12_5, var11_4, 6);
                        var15_24.run();
                        var21_7.a((Runnable)var15_24);
                        var11_4.setGravity(16);
                        var11_4.setMinHeight(w2.i(var22_13, 48));
                        w2.g((View)var11_4);
                        var14_20.addView((View)var11_4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                        var11_4.setOnClickListener((View.OnClickListener)new l1_0((Activity)var13_12, var9_14, (g_0)var15_24));
                        var16_34 = w2.r(var21_7, "\u70b9\u8d5e", "\u4e00\u952e\u70b9\u8d5e\u4e0e\u81ea\u52a8\u70b9\u8d5e\u5206\u522b\u4fdd\u5b58\u5f00\u5173\u3002\u81ea\u52a8\u70b9\u8d5e\u5f00\u542f\u4e14\u529f\u80fd\u5c31\u7eea\u65f6\uff0c\u4e00\u952e\u70b9\u8d5e\u914d\u7f6e\u6682\u4e0d\u751f\u6548\uff1b\u4e00\u952e\u70b9\u8d5e\u5f00\u5173\u4ecd\u53ef\u4fee\u6539\uff0c\u539f\u8303\u56f4\u8bbe\u7f6e\u4fdd\u7559\u3002\n\n\u4e00\u952e\u70b9\u8d5e\uff1a\u5355\u51fb\u5e7f\u573a\u7231\u5fc3\u5f00\u59cb\uff0c\u518d\u6b21\u5355\u51fb\u505c\u6b62\u3002\u5173\u95ed\u4e00\u952e\u70b9\u8d5e\u4e0d\u4f1a\u5173\u95ed\u81ea\u52a8\u70b9\u8d5e\u3002\u8303\u56f4\u5173\u95ed\u65f6\u53ea\u70b9\u8d5e\u5c4f\u5e55\u5185\u5e16\u5b50\uff0c\u5f00\u542f\u65f6\u5305\u542b\u5c4f\u5e55\u5916\u5df2\u52a0\u8f7d\u5e16\u5b50\u3002\n\n\u81ea\u52a8\u70b9\u8d5e\uff1a\u5904\u7406\u5f53\u524d\u5217\u8868\u5df2\u52a0\u8f7d\u7684\u5168\u90e8\u5e16\u5b50\uff0c\u65b0\u52a0\u8f7d\u7684\u5e16\u5b50\u4e5f\u4f1a\u7ee7\u7eed\u5904\u7406\u3002\u79bb\u5f00\u5e7f\u573a\u6216\u6253\u5f00\u8bbe\u7f6e\u65f6\u6682\u505c\uff0c\u8fd4\u56de\u540e\u7ee7\u7eed\uff1b\u957f\u6309\u5e7f\u573a\u7231\u5fc3\u4e5f\u53ef\u5f00\u542f\u6216\u5173\u95ed\uff0c\u5931\u8d25\u65f6\u81ea\u52a8\u5173\u95ed\u3002\n\n\u4e24\u79cd\u65b9\u5f0f\u90fd\u8df3\u8fc7\u5df2\u70b9\u8d5e\u548c\u88ab\u7b5b\u9009\u9690\u85cf\u7684\u5e16\u5b50\uff0c\u4e0d\u81ea\u52a8\u52a0\u8f7d\u66f4\u591a\u3002", null);
                        var14_20 = w2.d(var16_34, (SharedPreferences)var12_5, "square_auto_like", "\u81ea\u52a8\u70b9\u8d5e", "", var2_28);
                        w2.f(var21_7, (r2_0)var14_20, new H1(4));
                        var11_4 = w2.d(var16_34, (SharedPreferences)var12_5, "square_visible_like", "\u4e00\u952e\u70b9\u8d5e", "", var2_28);
                        w2.f(var21_7, (r2_0)var11_4, new H1(5));
                        var13_12 = w2.d(var16_34, (SharedPreferences)var12_5, "square_like_all_loaded", "\u5305\u542b\u5c4f\u5e55\u5916\u5df2\u52a0\u8f7d\u5e16\u5b50", "", var2_28);
                        w2.f(var21_7, (r2_0)var13_12, new H1(6));
                        var15_24 = (View)var13_12.getParent();
                        var17_37 /* !! */  = (LinearLayout.LayoutParams)var15_24.getLayoutParams();
                        var17_37 /* !! */ .leftMargin = w2.i(var22_13, 16);
                        var15_24.setLayoutParams((ViewGroup.LayoutParams)var17_37 /* !! */ );
                        var3_11 = var16_34.indexOfChild((View)var15_24);
                        var16_34.removeView((View)var15_24);
                        var17_37 /* !! */  = new FrameLayout(var22_13);
                        var17_37 /* !! */ .addView((View)var15_24, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams)var15_24.getLayoutParams()));
                        var17_37 /* !! */ .setClipChildren(true);
                        var16_34.addView((View)var17_37 /* !! */ , var3_11, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                        var21_7.n.add(var17_37 /* !! */ );
                        var15_24 = w2.c(var16_34, "");
                        var15_24 = new m1_0(var14_20, var11_4, var17_37 /* !! */ , var15_24, new Z(var14_20, var11_4, var13_12, var15_24, 2), 1);
                        var14_20.a((Runnable)var15_24);
                        var11_4.a((Runnable)var15_24);
                        var13_12.a((Runnable)var15_24);
                        var11_4 = w2.r(var21_7, "\u4e0b\u8f7d\u4e0e\u5206\u4eab", "\u65e0\u6c34\u5370\u4e0b\u8f7d\uff1a\u4f7f\u7528 Soul \u539f\u6709\u4fdd\u5b58\u6309\u94ae\uff0c\u4ecd\u5b58\u5165\u539f\u76f8\u518c\u76ee\u5f55\u3002\u539f\u6587\u4ef6\u81ea\u5e26\u7684\u6c34\u5370\u65e0\u6cd5\u53bb\u9664\u3002\n\n\u5206\u4eab\u9650\u5236\uff1a\u89e3\u9664\u5e16\u5b50\u5206\u4eab\u9762\u677f\u7684\u672c\u5730\u9650\u5236\uff0c\u4fee\u6539\u540e\u91cd\u65b0\u6253\u5f00\u5206\u4eab\u9762\u677f\u751f\u6548\uff1b\u5b9e\u9645\u5206\u4eab\u4ee5\u670d\u52a1\u5668\u5904\u7406\u4e3a\u51c6\u3002", null);
                        w2.f(var21_7, w2.d((LinearLayout)var11_4, (SharedPreferences)var12_5, "download_image_no_watermark", "\u56fe\u7247\u65e0\u6c34\u5370", "", var2_28), new H1(7));
                        w2.f(var21_7, w2.d((LinearLayout)var11_4, (SharedPreferences)var12_5, "download_video_no_watermark", "\u89c6\u9891\u65e0\u6c34\u5370", "", var2_28), new H1(1));
                        w2.f(var21_7, w2.d((LinearLayout)var11_4, (SharedPreferences)var12_5, "post_remove_share_limit", "\u53bb\u9664\u5206\u4eab\u9650\u5236", "", var2_28), new H1(2));
                        break block11;
                    }
                    var12_5 = w2.o((Context)var13_12);
                    var14_21 /* !! */  = w2.r(var21_7, "\u804a\u5929\u5c0f\u5de5\u5177", "\u9ab0\u5b50\u4e0e\u731c\u62f3\n\u79c1\u804a\u53d1\u9001\u524d\u9009\u62e9\u7ed3\u679c\uff1b\u9884\u89c8\u4e0d\u4f1a\u53d1\u9001\u6d88\u606f\u3002", null);
                    var11_4 = t_0.a != false ? "" : " \u00b7 \u672a\u5c31\u7eea";
                    var2_29 = var21_7.i;
                    var11_4 = w2.d(var14_21 /* !! */ , (SharedPreferences)var12_5, "game_dice", "\u81ea\u5b9a\u4e49\u9ab0\u5b50", (String)var11_4, var2_29);
                    w2.f(var21_7, (r2_0)var11_4, new H1(8));
                    w2.e((r2_0)var11_4, var21_7, new m_0((Activity)var13_12, 2));
                    var11_4 = t_0.b != false ? "" : " \u00b7 \u672a\u5c31\u7eea";
                    var11_4 = w2.d(var14_21 /* !! */ , (SharedPreferences)var12_5, "game_finger", "\u81ea\u5b9a\u4e49\u731c\u62f3", (String)var11_4, var2_29);
                    w2.f(var21_7, (r2_0)var11_4, new H1(15));
                    w2.e((r2_0)var11_4, var21_7, new m_0((Activity)var13_12, 3));
                    var13_12 = w2.r(var21_7, "\u6d88\u606f\u64a4\u56de", "\u4ec5\u4fdd\u7559\u5f00\u542f\u540e\u6536\u5230\u7684\u666e\u901a\u79c1\u804a\u6d88\u606f\uff0c\u81ea\u5df1\u7684\u64a4\u56de\u4e0d\u53d7\u5f71\u54cd\u3002\u5df2\u88ab\u6e05\u9664\u7684\u5386\u53f2\u6d88\u606f\u65e0\u6cd5\u6062\u590d\uff1b\u5a92\u4f53\u4ecd\u53d6\u51b3\u4e8e\u672c\u5730\u6587\u4ef6\u6216\u539f\u5730\u5740\u662f\u5426\u53ef\u7528\u3002\u5173\u95ed\u540e\u505c\u6b62\u62e6\u622a\uff0c\u5df2\u4fdd\u7559\u6d88\u606f\u4e0d\u81ea\u52a8\u5220\u9664\u3002", null);
                    var11_4 = Q0.k != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    var14_21 /* !! */  = w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_anti_recall", "\u804a\u5929\u9632\u64a4\u56de", (String)var11_4, var2_29);
                    w2.f(var21_7, (r2_0)var14_21 /* !! */ , new H1(16));
                    w2.e((r2_0)var14_21 /* !! */ , var21_7, new g_0(var21_7, var12_5, 8));
                    var11_4 = new LinearLayout(var22_13);
                    var11_4.setOrientation(1);
                    var11_4.setTag((Object)var21_7);
                    var13_12.addView((View)var11_4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                    var16_35 /* !! */  = w2.n(var22_13, "\u64a4\u56de\u6807\u8bb0\u6837\u5f0f", 15, var3_11);
                    var16_35 /* !! */ .setPadding(0, w2.i(var22_13, 12), 0, w2.i(var22_13, 8));
                    var11_4.addView((View)var16_35 /* !! */ );
                    var15_25 /* !! */  = new String[]{"below", "nickname"};
                    var16_35 /* !! */  = new C1(var21_7, var16_35 /* !! */ , new String[]{"\u6c14\u6ce1\u4e0b\u65b9\u5c0f\u5b57", "\u6635\u79f0\u65c1\u6807\u7b7e"}, 13);
                    var11_4.addView((View)var16_35 /* !! */ , (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                    var17_38 = new H((Object)var16_35 /* !! */ , var12_5, (Object)var15_25 /* !! */ , 10);
                    var16_35 /* !! */ .h = new R1((SharedPreferences)var12_5, var15_25 /* !! */ , (C1)var16_35 /* !! */ );
                    var17_38.run();
                    var21_7.a(var17_38);
                    var14_21 /* !! */ .a(new g_0(var11_4, var14_21 /* !! */ , 10));
                    var11_4 = g1_0.a != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_own_recall_no_time_limit", "\u64a4\u56de\u4e0d\u53d7\u4e24\u5206\u949f\u9650\u5236", "\u4ec5\u79fb\u9664\u81ea\u5df1\u6d88\u606f\u7684\u672c\u5730\u65f6\u95f4\u68c0\u67e5\uff0c\u5b9e\u9645\u80fd\u5426\u64a4\u56de\u4ee5\u670d\u52a1\u5668\u5904\u7406\u4e3a\u51c6\u3002".concat((String)var11_4), var2_29), new H1(17));
                    var13_12 = w2.r(var21_7, "\u804a\u5929\u9650\u5236", "\u804a\u5929\u9650\u5236\u4fee\u6539\u540e\u91cd\u65b0\u8fdb\u5165\u4f1a\u8bdd\uff1b\u5b9e\u9645\u53d1\u9001\u4ecd\u4ee5\u670d\u52a1\u5668\u5904\u7406\u4e3a\u51c6\u3002", null);
                    var11_4 = Q0.e != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_remove_gift_gate", "\u53bb\u9664\u9001\u793c\u804a\u5929\u9650\u5236", "\u79fb\u9664\u5df2\u9002\u914d\u7684\u672c\u5730\u9001\u793c\u5f00\u804a\u4e0e\u6d88\u606f\u989d\u5ea6\u95e8\u69db\u3002".concat((String)var11_4), var2_29), new H1(18));
                    var11_4 = Q0.f != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_remove_bell_gift_gate", "\u53bb\u9664\u5947\u9047\u94c3\u9001\u793c\u9650\u5236", (String)var11_4, var2_29), new H1(19));
                    var11_4 = Q0.U != false && Q0.V != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_remove_social_score_limit", "\u53bb\u9664\u793c\u4eea\u5206\u9650\u5236", "\u89e3\u9664\u672c\u5730\u793c\u4eea\u5206\u5f00\u804a\u3001\u7b49\u5f85\u56de\u590d\u3001\u56fe\u7247\u548c\u901a\u8bdd\u9650\u5236\u53ca\u76f8\u5173\u5f39\u7a97\uff0c\u4e0d\u4fee\u6539\u5b9e\u9645\u793c\u4eea\u5206\u3002".concat((String)var11_4), var2_29), new H1(20));
                    var13_12 = w2.r(var21_7, "\u804a\u5929\u4e0e\u5339\u914d", null, null);
                    var11_4 = Q0.b != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "chat_hide_typing", "\u7981\u6b62\u4e0a\u62a5\u8f93\u5165\u72b6\u6001", "\u4e0d\u5411\u5bf9\u65b9\u53d1\u9001\u6b63\u5728\u8f93\u5165\u6216\u7ed3\u675f\u8f93\u5165\u72b6\u6001\u3002".concat((String)var11_4), var2_29), new H1(9));
                    var11_4 = Q0.c != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    w2.f(var21_7, w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "match_mute_background_music", "\u5173\u95ed\u5339\u914d\u80cc\u666f\u97f3\u4e50", "\u4e0b\u6b21\u8fdb\u5165\u5339\u914d\u751f\u6548\uff0c\u4fdd\u7559\u5339\u914d\u632f\u52a8\u3002".concat((String)var11_4), var2_29), new H1(10));
                    var13_12 = w2.r(var21_7, "\u9690\u79c1\u7167\u7247 / \u89c6\u9891", "\u4ec5\u9002\u7528\u4e8e\u6536\u5230\u7684\u9690\u79c1\u56fe\u7247\u548c\u89c6\u9891\uff1b\u5f00\u542f\u540e\u91cd\u65b0\u6253\u5f00\u5a92\u4f53\u751f\u6548\u3002\u5df2\u64a4\u56de\u3001\u5df2\u8fc7\u671f\u6216\u670d\u52a1\u5668\u4e0d\u518d\u63d0\u4f9b\u7684\u6587\u4ef6\u65e0\u6cd5\u6062\u590d\u3002", null);
                    var11_4 = Q0.P != false ? "" : " \u00b7 \u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea";
                    var14_21 /* !! */  = w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "privacy_allow_download", "\u5141\u8bb8\u4e0b\u8f7d", "\u4fdd\u5b58\u76ee\u5f55\uff1a\n\u56fe\u7247\uff1aPictures/New Soul\n\u89c6\u9891\uff1aMovies/New Soul".concat((String)var11_4), var2_29);
                    var15_25 /* !! */  = w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "privacy_allow_screenshot", "\u5141\u8bb8\u622a\u5c4f", (String)var11_4, var2_29);
                    var11_4 = w2.d((LinearLayout)var13_12, (SharedPreferences)var12_5, "privacy_allow_repeat", "\u5141\u8bb8\u591a\u6b21\u67e5\u770b", "\u67e5\u770b\u65f6\u4e0d\u6263\u51cf\u672c\u5730\u6b21\u6570\uff0c\u4e5f\u4e0d\u5411\u5bf9\u65b9\u53d1\u9001\u5df2\u9605\u72b6\u6001\u3002", var2_29);
                    w2.f(var21_7, (r2_0)var14_21 /* !! */ , new H1(11));
                    w2.f(var21_7, (r2_0)var15_25 /* !! */ , new H1(12));
                    w2.f(var21_7, (r2_0)var11_4, new H1(13));
                    var14_21 /* !! */  = w2.c((LinearLayout)var13_12, "");
                    var12_5 = (h2)var23_10.get(var13_12);
                    var12_5.b.add(var11_4);
                    var12_5.c.add(new H1(14));
                    var11_4 = new g_0(var14_21 /* !! */ , var12_5, 7);
                    var21_7.a((Runnable)var11_4);
                    var11_4.run();
                    var11_4 = var12_5.e;
                    var13_12.removeView((View)var11_4);
                    var12_5 = var12_5.d;
                    var13_12.removeView((View)var12_5);
                    var13_12.addView((View)var11_4);
                    var13_12.addView((View)var12_5);
                }
                var10_3 = new ScrollView(var10_3.g);
                var10_3.setFillViewport(false);
                var10_3.setClipToPadding(false);
                var10_3.addView((View)var20_9);
                var19_6[var1_1] = var10_3;
                var21_7.e();
            }
            var10_3 = var19_6[var1_1];
            var18_2[var1_1] = var10_3;
            var10_3.setVisibility(4);
            this.addView((View)var10_3, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        }
        var10_3 = var18_2[var1_1];
        return;
    }

    public final void i(int n) {
        float f;
        float f2;
        this.a();
        this.g(n);
        this.n = f2 = this.m;
        f2 = f = Math.signum((float)n - f2);
        if (f == 0.0f) {
            f2 = 1.0f;
        }
        int n2 = 0;
        while (true) {
            ViewGroup viewGroup = this.b;
            int n3 = ((ViewGroup[])viewGroup).length;
            boolean bl = true;
            if (n2 >= n3) break;
            n3 = (viewGroup = viewGroup[n2]) != null && viewGroup.getVisibility() == 0 ? 1 : 0;
            boolean bl2 = bl;
            if (n3 == 0) {
                bl2 = n2 == n ? bl : false;
            }
            this.q[n2] = bl2;
            f = n3 != 0 ? viewGroup.getTranslationX() : (float)this.getWidth() * f2 * 0.18f;
            this.o[n2] = f;
            f = n3 != 0 ? viewGroup.getAlpha() : 0.0f;
            this.p[n2] = f;
            ++n2;
        }
        this.g = this.f;
        this.h = n;
        this.i = 0.0f;
        this.k = true;
        this.j = true;
        this.j();
        this.k(true);
    }

    public final void j() {
        int n;
        boolean bl;
        Object object;
        int n2;
        boolean bl2;
        int n3;
        Object[] objectArray;
        int n4 = this.g;
        if (n4 < 0) {
            return;
        }
        float f = this.k ? Math.signum((float)this.h - this.n) : (float)Integer.compare(this.h, n4);
        float f2 = f;
        if (this.k) {
            f2 = f;
            if (f == 0.0f) {
                f2 = 1.0f;
            }
        }
        float f4 = this.getWidth();
        f = this.j ? 0.18f : 1.0f;
        float f5 = f4 * f;
        int n5 = 0;
        n4 = 0;
        while (true) {
            objectArray = this.b;
            n3 = objectArray.length;
            bl2 = true;
            n2 = 1;
            if (n4 >= n3) break;
            object = objectArray[n4];
            if (object != null) {
                bl = this.k ? this.q[n4] : n4 == this.g || n4 == this.h;
                n = 4;
                n3 = bl ? 0 : 4;
                object.setVisibility(n3);
                n3 = n4 == this.f ? n2 : 0;
                if (n3 != 0) {
                    n = 0;
                }
                object.setImportantForAccessibility(n);
                n3 = n3 != 0 ? 262144 : 393216;
                object.setDescendantFocusability(n3);
                if (!bl) {
                    object.setTranslationX(0.0f);
                    object.setAlpha(1.0f);
                } else if (this.k) {
                    n3 = this.h;
                    objectArray = this.o;
                    f = n4 == n3 ? 0.0f : (float)(objectArray[n4] - f2 * f5);
                    f4 = n4 == n3 ? 1.0f : 0.0f;
                    ViewGroup viewGroup = objectArray[n4];
                    object.setTranslationX((f - viewGroup) * this.i + viewGroup);
                    f = this.p[n4];
                    object.setAlpha((f4 - f) * this.i + f);
                } else if (n4 == this.g) {
                    object.setTranslationX(-f2 * f5 * this.i);
                    f = this.j ? 1.0f - this.i : 1.0f;
                    object.setAlpha(f);
                } else {
                    object.setTranslationX((1.0f - this.i) * (f2 * f5));
                    f = this.j ? this.i : 1.0f;
                    object.setAlpha(f);
                }
            }
            ++n4;
        }
        if (this.k) {
            f = this.n;
            f = ((float)this.h - f) * this.i + f;
        } else {
            n4 = this.g;
            f = (float)n4 + (float)(this.h - n4) * this.i;
        }
        this.m = f;
        n3 = Math.max(0, Math.min(objectArray.length - 1, (int)Math.floor(f)));
        n2 = Math.min(objectArray.length - 1, n3 + 1);
        f = Math.max(0.0f, Math.min(1.0f, this.m - (float)n3));
        bl = bl2;
        if (this.g == this.h) {
            bl = this.k ? bl2 : false;
        }
        object = (v2_0)this.a.a;
        objectArray = ((v2_0)object).b;
        n = objectArray.length;
        for (n4 = n5; n4 < n; ++n4) {
            Object object22 = objectArray[n4];
            if (object22 == null) continue;
            for (Object object22 : ((q2)object22).n) {
                if (((j2_0)((Object)object22)).a == null) continue;
                ((j2_0)((Object)object22)).b();
            }
        }
        ((v2_0)object).j = n3;
        ((v2_0)object).k = n2;
        ((v2_0)object).l = f;
        ((v2_0)object).m = bl;
        ((v2_0)object).a();
    }

    public final void k(boolean bl) {
        if (this.k && !bl) {
            this.i(this.f);
            return;
        }
        this.l = bl;
        this.a();
        int n = bl ? this.h : this.g;
        if (ValueAnimator.areAnimatorsEnabled() && this.isAttachedToWindow() && this.getWidth() != 0) {
            ValueAnimator valueAnimator;
            float f = bl ? 1.0f : 0.0f;
            this.r = valueAnimator = ValueAnimator.ofFloat((float[])new float[]{this.i, f});
            valueAnimator.setDuration((long)Math.max(90, Math.round(Math.abs(f - this.i) * 210.0f)));
            valueAnimator.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
            valueAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new L0(this, 1));
            valueAnimator.addListener((Animator.AnimatorListener)new Y0(this, n));
            valueAnimator.start();
            return;
        }
        this.c(n);
    }

    public final void onDetachedFromWindow() {
        this.d();
        super.onDetachedFromWindow();
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 2) {
            this.e(motionEvent);
        }
        boolean bl = this.y || this.B;
        return bl;
    }

    public final void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3 || n2 != n4) {
            this.d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int n = motionEvent.getActionMasked();
        if (n == 2) {
            this.e(motionEvent);
            n = motionEvent.findPointerIndex(this.t);
            if (this.y && n >= 0) {
                this.f(motionEvent.getX(n) - this.v);
            }
        } else if (n == 1 || n == 3) {
            boolean bl = this.y;
            boolean bl2 = false;
            if (bl) {
                float f;
                motionEvent = this.s;
                if (motionEvent != null && n != 3) {
                    motionEvent.computeCurrentVelocity(1000, (float)this.e);
                    f = this.s.getXVelocity(this.t);
                } else {
                    f = 0.0f;
                }
                bl = this.B;
                int n2 = this.d;
                if (bl) {
                    float f2 = this.i;
                    float f4 = -f;
                    float f5 = this.D;
                    f = n2;
                    bl = n == 3;
                    bl = Q0.c(f2, f4 * f5, f, bl);
                } else {
                    int n3 = this.f;
                    int n4 = this.b.length;
                    float f6 = this.x;
                    float f7 = this.getWidth();
                    float f8 = n2;
                    if (n != 3 && !(f7 <= 0.0f)) {
                        n2 = -1;
                        if (f6 == 0.0f) {
                            n = n3;
                        } else {
                            n = f6 < 0.0f ? 1 : -1;
                            n = Math.max(0, Math.min(n4 - 1, n + n3));
                        }
                        if (n != n3) {
                            f7 = Math.min(1.0f, Math.abs(f6) / f7);
                            if (!(f6 < 0.0f)) {
                                n2 = 1;
                            }
                            if (Q0.c(f7, f * (float)n2, f8, false)) {
                                n3 = n;
                            }
                        }
                    }
                    bl = n3 != this.f;
                }
                this.y = false;
                this.k(bl);
            } else if (this.B) {
                bl = bl2;
                if (n != 3) {
                    bl = bl2;
                    if (this.l) {
                        bl = true;
                    }
                }
                this.k(bl);
            }
        }
        return true;
    }

    public final void onVisibilityAggregated(boolean bl) {
        super.onVisibilityAggregated(bl);
        if (!bl) {
            this.d();
        }
    }

    public final void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (!bl) {
            this.d();
        }
    }
}

