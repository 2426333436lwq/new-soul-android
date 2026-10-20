/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.A2;
import dev.local.newsoul.B2;
import dev.local.newsoul.g_0;
import io.github.libxposed.api.XposedInterface;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.z2
 */
public final class z2_0
implements XposedInterface.Hooker {
    public final int a;
    public final Class b;

    public /* synthetic */ z2_0(Class clazz, int n) {
        this.a = n;
        this.b = clazz;
    }

    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2 = object.proceed();
                View view = (View)this.b.getField("itemView").get(object.getArg(0));
                object = (A2)B2.k.get(view);
                if (object != null) {
                    B2.b(view, (A2)object);
                }
                return object2;
            }
            case 1: {
                View view = (View)this.b.getField("itemView").get(object.getArg(0));
                B2.e(view, (A2)B2.k.remove(view));
                return object.proceed();
            }
            case 0: 
        }
        View view = (View)this.b.getField("itemView").get(object.getArg(0));
        WeakHashMap weakHashMap = B2.k;
        B2.e(view, (A2)weakHashMap.remove(view));
        Object object3 = object.proceed();
        Object object4 = new Object();
        ((A2)object4).a = object.getArg(1);
        weakHashMap.put(view, object4);
        B2.b(view, (A2)object4);
        view.post((Runnable)new g_0(view, object4, 12));
        return object3;
    }
}

