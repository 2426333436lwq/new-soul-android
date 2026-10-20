package dev.local.newsoul;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: dev.local.newsoul.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0001a0 {
    public final int a = AbstractC0025g0.e.nextInt(4096) + 16384;
    public final WeakReference b;
    public byte[] c;
    public boolean d;

    public C0001a0(C0021f0 c0021f0) {
        this.b = new WeakReference(c0021f0);
    }
}
