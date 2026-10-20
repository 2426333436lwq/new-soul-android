package dev.local.newsoul;

/* JADX INFO: renamed from: dev.local.newsoul.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0095y implements AutoCloseable {
    public final C0099z a;
    public final C0099z b;
    public boolean c;

    public C0095y(C0099z c0099z, C0099z c0099z2) {
        this.a = c0099z;
        this.b = c0099z2;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        C0099z c0099z = this.b;
        if (c0099z == null) {
            A.b.remove();
        } else {
            A.b.set(c0099z);
        }
    }
}
