/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

/*
 * Renamed from dev.local.newsoul.u
 */
public final class u_0
implements AutoCloseable {
    public static final ThreadLocal g = new ThreadLocal();
    public final Object a;
    public final Object b;
    public final Object c;
    public final u_0 d;
    public boolean e;
    public boolean f;

    public u_0(Object object, Object object2, Object object3) {
        this.a = object;
        this.b = object2;
        this.c = object3;
        object = g;
        this.d = (u_0)((ThreadLocal)object).get();
        ((ThreadLocal)object).set(this);
    }

    @Override
    public final void close() {
        if (this.f) {
            return;
        }
        this.f = true;
        ThreadLocal threadLocal = g;
        u_0 u_02 = this.d;
        if (u_02 == null) {
            threadLocal.remove();
        } else {
            threadLocal.set(u_02);
        }
    }
}

