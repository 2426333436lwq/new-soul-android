/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 */
package dev.local.newsoul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import dev.local.newsoul.Z0;

public final class Y0
extends AnimatorListenerAdapter {
    public final int a;
    public final Z0 b;

    public Y0(Z0 z0, int n) {
        this.b = z0;
        this.a = n;
    }

    public final void onAnimationEnd(Animator animator) {
        Z0 z0 = this.b;
        if (z0.r != animator) {
            return;
        }
        z0.r = null;
        z0.c(this.a);
    }
}

