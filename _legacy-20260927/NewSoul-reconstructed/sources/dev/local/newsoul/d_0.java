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
import dev.local.newsoul.j_0;
import io.github.libxposed.api.XposedInterface;

/*
 * Renamed from dev.local.newsoul.d
 */
public final class d_0
implements XposedInterface.Hooker {
    public final boolean a;
    public final String b;

    public /* synthetic */ d_0(String string, boolean bl) {
        this.a = bl;
        this.b = string;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        Object object2 = object.proceed();
        object = this.a ? object.getArg(0) : object.getThisObject();
        try {
            View view = (View)j_0.d(object.getClass(), "itemView").get(object);
            object = view;
        }
        catch (Exception exception) {
            Object var2_6 = null;
            try {
                object = j_0.d(object.getClass(), "binding").get(object);
                object = (View)object.getClass().getMethod("getRoot", null).invoke(object, null);
            }
            catch (Exception exception2) {
                object = var2_6;
            }
        }
        if (object != null) {
            j_0.i((View)object, this.b);
        }
        return object2;
    }
}

