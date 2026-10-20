package dev.local.newsoul;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {
    public static final AtomicLong a = new AtomicLong();
    public static final ThreadLocal b = new ThreadLocal();
    public static final ArrayDeque c = new ArrayDeque();

    public static C0095y a(boolean z, Object obj) {
        C0099z c0099z = (C0099z) b.get();
        C0099z c0099z2 = z ? null : c0099z;
        if (!z && c0099z2 == null && obj != null) {
            ArrayDeque arrayDeque = c;
            synchronized (arrayDeque) {
                try {
                    Iterator it = arrayDeque.iterator();
                    while (it.hasNext()) {
                        C0091x c0091x = (C0091x) it.next();
                        Object obj2 = c0091x.a.get();
                        if (obj2 == null) {
                            it.remove();
                        } else if (obj2 == obj) {
                            c0099z2 = c0091x.b;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (c0099z2 == null) {
            c0099z2 = new C0099z();
        }
        b.set(c0099z2);
        return new C0095y(c0099z2, c0099z);
    }

    public static void b(Object obj) {
        C0099z c0099z = (C0099z) b.get();
        if (c0099z == null || obj == null) {
            return;
        }
        ArrayDeque arrayDeque = c;
        synchronized (arrayDeque) {
            try {
                arrayDeque.removeIf(new C0087w(0, obj));
                while (true) {
                    ArrayDeque arrayDeque2 = c;
                    if (arrayDeque2.size() >= 64) {
                        arrayDeque2.removeFirst();
                    } else {
                        arrayDeque2.addLast(new C0091x(obj, c0099z));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
