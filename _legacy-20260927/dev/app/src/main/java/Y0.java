package dev.local.newsoul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class Y0 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Z0 b;

    public Y0(Z0 z0, int i) {
        this.b = z0;
        this.a = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        Z0 z0 = this.b;
        if (z0.r != animator) {
            return;
        }
        z0.r = null;
        z0.c(this.a);
    }
}
