package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class L0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ FrameLayout b;

    public /* synthetic */ L0(FrameLayout frameLayout, int i) {
        this.a = i;
        this.b = frameLayout;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                M0 m0 = (M0) this.b;
                if (m0.r == valueAnimator) {
                    m0.n = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    m0.invalidate();
                    break;
                }
                break;
            case 1:
                Z0 z0 = (Z0) this.b;
                if (z0.r == valueAnimator) {
                    z0.i = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    z0.j();
                    break;
                }
                break;
            default:
                C0039j2 c0039j2 = (C0039j2) this.b;
                c0039j2.getClass();
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                c0039j2.d = fFloatValue;
                c0039j2.setAlpha(fFloatValue);
                c0039j2.requestLayout();
                break;
        }
    }
}
