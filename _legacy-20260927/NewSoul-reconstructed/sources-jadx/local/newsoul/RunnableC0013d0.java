package dev.local.newsoul;

/* JADX INFO: renamed from: dev.local.newsoul.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0013d0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ C0021f0 b;

    public /* synthetic */ RunnableC0013d0(C0021f0 c0021f0, int i) {
        this.a = i;
        this.b = c0021f0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                C0021f0 c0021f0 = this.b;
                c0021f0.h = false;
                c0021f0.b();
                break;
            default:
                C0021f0 c0021f1 = this.b;
                c0021f1.h = false;
                c0021f1.d.setVisibility(8);
                c0021f1.b();
                break;
        }
    }
}
