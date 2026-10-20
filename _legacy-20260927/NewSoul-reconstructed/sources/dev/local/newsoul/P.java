/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.Q;
import dev.local.newsoul.S;
import dev.local.newsoul.q2;
import java.lang.ref.WeakReference;

public final class P {
    public final int a = S.f.nextInt(36864) + 20480;
    public final boolean b;
    public final String c;
    public final WeakReference d;
    public final WeakReference e;
    public boolean f = true;

    public P(q2 q22, Q q, String string) {
        this.b = q22.b;
        this.c = string;
        this.d = new WeakReference<q2>(q22);
        this.e = new WeakReference<Q>(q);
    }
}

