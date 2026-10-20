package dev.local.newsoul;

/* JADX INFO: renamed from: dev.local.newsoul.C0079u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0079u implements AutoCloseable {
    public static final ThreadLocal g = new ThreadLocal();
    public final Object a;
    public final Object b;
    public final Object c;
    public final C0079u d;
    public boolean e;
    public boolean f;

    public C0079u(Object obj, Object obj2, Object obj3) {
        this.a = obj;
        this.b = obj2;
        this.c = obj3;
        ThreadLocal threadLocal = g;
        this.d = (C0079u) threadLocal.get();
        threadLocal.set(this);
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        if (this.f) {
            return;
        }
        this.f = true;
        ThreadLocal threadLocal = g;
        C0079u c0079u = this.d;
        if (c0079u == null) {
            threadLocal.remove();
        } else {
            threadLocal.set(c0079u);
        }
    }
}
