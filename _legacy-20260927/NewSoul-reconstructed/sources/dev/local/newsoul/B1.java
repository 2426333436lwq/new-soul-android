/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 *  android.widget.FrameLayout
 */
package dev.local.newsoul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.FrameLayout;
import dev.local.newsoul.C1;
import dev.local.newsoul.j2_0;

public final class B1
extends AnimatorListenerAdapter {
    public final int a;
    public final FrameLayout b;

    public /* synthetic */ B1(FrameLayout frameLayout, int n) {
        this.a = n;
        this.b = frameLayout;
    }

    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            default: {
                j2_0 j2_02 = (j2_0)this.b;
                if (j2_02.a == animator) {
                    j2_02.a = null;
                    j2_02.b();
                }
                return;
            }
            case 0: 
        }
        C1 c1 = (C1)this.b;
        if (c1.g == animator) {
            c1.g = null;
            c1.a.c(c1.e, c1.f, false);
        }
    }
}

