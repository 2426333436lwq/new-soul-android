/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.InsetDrawable
 *  android.graphics.drawable.RippleDrawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.DecelerateInterpolator
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.A1;
import dev.local.newsoul.B1;
import dev.local.newsoul.L2;
import dev.local.newsoul.M0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import dev.local.newsoul.x0_0;
import java.util.function.IntConsumer;

public final class C1
extends FrameLayout {
    public final M0 a;
    public final LinearLayout b;
    public final TextView[] c;
    public int d;
    public float e;
    public float f;
    public ValueAnimator g;
    public IntConsumer h;

    public C1(q2 q22, TextView object, String[] stringArray, int n) {
        LinearLayout linearLayout;
        Object object2;
        Context context = q22.g;
        super(context);
        this.d = -1;
        L2 l2 = q22.c;
        this.a = object2 = new M0(context, l2, true);
        int n2 = w2.i(context, 4);
        object2.setPadding(n2, n2, n2, n2);
        this.addView((View)object2, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -2));
        this.b = linearLayout = new LinearLayout(context);
        linearLayout.setGravity(16);
        object2.addView((View)linearLayout, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -2));
        w2.v(linearLayout, object, stringArray.length);
        this.c = new TextView[stringArray.length];
        object = new int[]{0x10100A1};
        object2 = new int[0];
        n2 = q22.k;
        int n3 = q22.i;
        object2 = new ColorStateList((int[][])new int[][]{(int[])object, (int[])object2}, new int[]{n2, n3});
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            this.c[n2] = object = w2.n(context, stringArray[n2], n, n3);
            object.setGravity(17);
            object.setTextColor((ColorStateList)object2);
            object.setPadding(w2.i(context, 14), w2.i(context, 10), w2.i(context, 14), w2.i(context, 10));
            object.setMinHeight(w2.i(context, 48));
            float f = l2.b;
            q22 = new InsetDrawable((Drawable)L2.g(-1, 999.0f * f, false), 0, Math.round(f * 3.0f), 0, Math.round(f * 3.0f));
            int n4 = l2.a ? 0x18FFFFFF : 402684267;
            object.setForeground((Drawable)new RippleDrawable(ColorStateList.valueOf((int)n4), null, (Drawable)q22));
            w2.w(object, n2);
            object.setOnClickListener((View.OnClickListener)new x0_0((Object)this, n2, 1));
            this.b.addView((View)object, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 1.0f));
        }
    }

    public final void a(boolean bl) {
        float f;
        float f2;
        Object object = this.g;
        this.g = null;
        if (object != null) {
            object.cancel();
        }
        int n = this.d;
        TextView[] textViewArray = this.c;
        object = this.a;
        if (n < 0) {
            f2 = 0.0f;
        } else {
            n = object.getPaddingLeft();
            f2 = textViewArray[this.d].getLeft() + n;
        }
        n = this.d;
        float f4 = n < 0 ? 0.0f : (float)textViewArray[n].getWidth();
        if (bl && this.isAttachedToWindow() && ValueAnimator.areAnimatorsEnabled() && !((f = this.f) <= 0.0f) && !(f4 <= 0.0f)) {
            float f5 = this.e;
            object = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
            this.g = object;
            object.setDuration(200L);
            object.setInterpolator((TimeInterpolator)new DecelerateInterpolator(1.5f));
            object.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new A1(this, f5, f2, f, f4));
            object.addListener((Animator.AnimatorListener)new B1(this, 0));
            object.start();
            return;
        }
        this.e = f2;
        this.f = f4;
        ((M0)((Object)object)).c(f2, f4, false);
    }

    public final void b(String textView, String[] stringArray, boolean bl) {
        int n;
        block4: {
            for (n = 0; n < stringArray.length; ++n) {
                if (!stringArray[n].equals(textView)) {
                    continue;
                }
                break block4;
            }
            n = -1;
        }
        if (n == this.d) {
            return;
        }
        this.d = n;
        for (n = 0; n < ((TextView[])(textView = this.c)).length; ++n) {
            textView = textView[n];
            boolean bl2 = n == this.d;
            textView.setSelected(bl2);
        }
        this.a(bl);
    }

    public final void onDetachedFromWindow() {
        ValueAnimator valueAnimator = this.g;
        this.g = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        super.onDetachedFromWindow();
    }

    public final void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (this.g == null || bl) {
            this.a(false);
        }
    }
}

