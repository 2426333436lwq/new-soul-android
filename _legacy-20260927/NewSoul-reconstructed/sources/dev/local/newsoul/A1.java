/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 */
package dev.local.newsoul;

import android.animation.ValueAnimator;
import dev.local.newsoul.C1;

public final class A1
implements ValueAnimator.AnimatorUpdateListener {
    public final C1 a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;

    public /* synthetic */ A1(C1 c1, float f, float f2, float f4, float f5) {
        this.a = c1;
        this.b = f;
        this.c = f2;
        this.d = f4;
        this.e = f5;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        C1 c1 = this.a;
        if (c1.g == valueAnimator) {
            float f = ((Float)valueAnimator.getAnimatedValue()).floatValue();
            float f2 = this.c;
            float f4 = this.b;
            c1.e = f2 = (f2 - f4) * f + f4;
            f4 = this.e;
            float f5 = this.d;
            c1.f = f = (f4 - f5) * f + f5;
            c1.a.c(f2, f, true);
        }
    }
}

