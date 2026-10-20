package dev.local.newsoul;

/* JADX INFO: renamed from: dev.local.newsoul.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0017e0 implements Runnable {
    public final /* synthetic */ C0021f0 a;

    public RunnableC0017e0(C0021f0 c0021f0) {
        this.a = c0021f0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0021f0 c0021f0 = this.a;
        if (c0021f0.a.e.isShowing()) {
            c0021f0.b();
            AbstractC0025g0.b.postDelayed(this, 1000L);
        }
    }
}
