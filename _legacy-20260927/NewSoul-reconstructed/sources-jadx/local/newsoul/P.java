package dev.local.newsoul;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class P {
    public final boolean b;
    public final String c;
    public final WeakReference d;
    public final WeakReference e;
    public boolean f = true;
    public final int a = S.f.nextInt(36864) + 20480;

    public P(C0067q2 c0067q2, Q q, String str) {
        this.b = c0067q2.b;
        this.c = str;
        this.d = new WeakReference(c0067q2);
        this.e = new WeakReference(q);
    }
}
