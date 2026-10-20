/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import dev.local.newsoul.b3;
import dev.local.newsoul.d3;
import dev.local.newsoul.g_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.ref.WeakReference;

public final class N2
implements XposedInterface.Hooker {
    public final int a;
    public final d3 b;

    public /* synthetic */ N2(d3 d32, int n) {
        this.a = n;
        this.b = d32;
    }

    public final Object intercept(XposedInterface.Chain object) {
        d3 d32 = this.b;
        switch (this.a) {
            default: {
                b3 b32 = d32.f;
                if (b32 != null && b32.a == object.getThisObject()) {
                    d32.f.b();
                }
                return object.proceed();
            }
            case 0: 
        }
        Object object2 = object.proceed();
        object = new WeakReference<Object>(object.getThisObject());
        for (int i = 0; i < 3; ++i) {
            long l = (new long[]{0L, 350L, 1000L})[i];
            d32.a.postDelayed((Runnable)new g_0(object, d32, 14), l);
        }
        return object2;
    }
}

