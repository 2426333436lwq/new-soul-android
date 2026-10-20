/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.h3;
import dev.local.newsoul.x1_0;

public final class r
implements c3 {
    public final Object a;
    public final Object b;
    public final Object c;

    public /* synthetic */ r(Object object, Object object2, Object object3) {
        this.a = object;
        this.b = object2;
        this.c = object3;
    }

    @Override
    public void run() {
        Object object = (b3)this.a;
        h3 h32 = (h3)this.b;
        String string = (String)this.c;
        object = ((b3)object).f;
        String string2 = h32.a;
        synchronized (object) {
            h32 = ((x1_0)object).g(string2);
            h3 h33 = new h3(string2, x1_0.e(string), h32.c, h32.d, h32.e);
            ((x1_0)object).k(h33);
            return;
        }
    }
}

