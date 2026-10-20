/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.ScrollView
 */
package dev.local.newsoul;

import android.widget.ScrollView;
import dev.local.newsoul.b3;
import dev.local.newsoul.q2;

public final class Y2
implements Runnable {
    public final b3 a;
    public final q2 b;
    public final ScrollView c;
    public final int d;

    public /* synthetic */ Y2(b3 b32, q2 q22, ScrollView scrollView, int n) {
        this.a = b32;
        this.b = q22;
        this.c = scrollView;
        this.d = n;
    }

    @Override
    public final void run() {
        b3 b32 = this.a;
        if (!b32.m && b32.h == this.b) {
            this.c.scrollTo(0, this.d);
        }
    }
}

