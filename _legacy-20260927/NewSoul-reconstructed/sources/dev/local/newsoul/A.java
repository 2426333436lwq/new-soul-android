/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.w_0;
import dev.local.newsoul.x_0;
import dev.local.newsoul.y_0;
import dev.local.newsoul.z_0;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public abstract class A {
    public static final AtomicLong a = new AtomicLong();
    public static final ThreadLocal b = new ThreadLocal();
    public static final ArrayDeque c = new ArrayDeque();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static y_0 a(boolean bl, Object object) {
        z_0 z_02 = (z_0)b.get();
        z_0 z_03 = bl ? null : z_02;
        Object object2 = z_03;
        if (!bl) {
            object2 = z_03;
            if (z_03 == null) {
                object2 = z_03;
                if (object != null) {
                    object2 = c;
                    // MONITORENTER : object2
                    Iterator iterator = ((ArrayDeque)object2).iterator();
                    while (iterator.hasNext()) {
                        x_0 x_02 = (x_0)iterator.next();
                        Object t = x_02.a.get();
                        if (t == null) {
                            iterator.remove();
                            continue;
                        }
                        if (t != object) continue;
                        z_03 = x_02.b;
                    }
                    object2 = z_03;
                }
            }
        }
        object = object2;
        if (object2 == null) {
            object = new z_0();
        }
        b.set(object);
        return new y_0((z_0)object, z_02);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(Object object) {
        z_0 z_02 = (z_0)b.get();
        if (z_02 == null) return;
        if (object == null) {
            return;
        }
        ArrayDeque arrayDeque = c;
        synchronized (arrayDeque) {
            try {
                Object object2 = new w_0(0, object);
                arrayDeque.removeIf(object2);
                while (true) {
                    if (((ArrayDeque)(object2 = c)).size() < 64) {
                        x_0 x_02 = new x_0(object, z_02);
                        ((ArrayDeque)object2).addLast(x_02);
                        return;
                    }
                    ((ArrayDeque)object2).removeFirst();
                }
            }
            catch (Throwable throwable) {}
            throw throwable;
        }
    }
}

