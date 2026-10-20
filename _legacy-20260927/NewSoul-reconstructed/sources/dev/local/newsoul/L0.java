/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.widget.FrameLayout
 */
package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.widget.FrameLayout;
import dev.local.newsoul.M0;
import dev.local.newsoul.Z0;
import dev.local.newsoul.j2_0;

public final class L0
implements ValueAnimator.AnimatorUpdateListener {
    public final int a;
    public final FrameLayout b;

    public /* synthetic */ L0(FrameLayout frameLayout, int n) {
        this.a = n;
        this.b = frameLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            default: {
                float f;
                j2_0 j2_02 = (j2_0)this.b;
                ((Object)((Object)j2_02)).getClass();
                j2_02.d = f = ((Float)valueAnimator.getAnimatedValue()).floatValue();
                j2_02.setAlpha(f);
                j2_02.requestLayout();
                return;
            }
            case 1: {
                Z0 z0 = (Z0)this.b;
                if (z0.r == valueAnimator) {
                    z0.i = ((Float)valueAnimator.getAnimatedValue()).floatValue();
                    z0.j();
                }
                return;
            }
            case 0: 
        }
        M0 m0 = (M0)this.b;
        if (m0.r == valueAnimator) {
            m0.n = ((Float)valueAnimator.getAnimatedValue()).floatValue();
            m0.invalidate();
        }
    }
}

