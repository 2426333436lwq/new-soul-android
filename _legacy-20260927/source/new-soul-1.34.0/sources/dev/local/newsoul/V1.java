package dev.local.newsoul;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ C0031h2 b;

    public /* synthetic */ V1(C0031h2 c0031h2, int i) {
        this.a = i;
        this.b = c0031h2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.a();
                break;
            default:
                this.b.b();
                break;
        }
    }
}
