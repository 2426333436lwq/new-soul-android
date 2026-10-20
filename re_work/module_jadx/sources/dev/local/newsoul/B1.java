package dev.local.newsoul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class B1 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ FrameLayout b;

    public /* synthetic */ B1(FrameLayout frameLayout, int i) {
        this.a = i;
        this.b = frameLayout;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                C1 c1 = (C1) this.b;
                if (c1.g == animator) {
                    c1.g = null;
                    c1.a.c(c1.e, c1.f, false);
                    break;
                }
                break;
            default:
                C0039j2 c0039j2 = (C0039j2) this.b;
                if (c0039j2.a == animator) {
                    c0039j2.a = null;
                    c0039j2.b();
                }
                break;
        }
    }
}
