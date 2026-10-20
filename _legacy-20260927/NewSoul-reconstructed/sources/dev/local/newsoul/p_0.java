/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.SharedPreferences
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import dev.local.newsoul.q_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.p
 */
public final class p_0
implements XposedInterface.Hooker {
    public final Field a;
    public final boolean b;
    public final Method c;

    public /* synthetic */ p_0(Field field, boolean bl, Method method) {
        this.a = field;
        this.b = bl;
        this.c = method;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final Object intercept(XposedInterface.Chain object) {
        void var1_5;
        if (t_0.c.get() != null) {
            Object object2 = object.proceed();
            return var1_5;
        } else {
            Object object3 = object.getThisObject();
            Object object4 = this.a.get(object3);
            Activity activity = (Activity)object4.getClass().getMethod("getActivity", null).invoke(object4, null);
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                boolean bl;
                object4 = w2.a;
                SharedPreferences sharedPreferences = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
                if (sharedPreferences.getBoolean((String)(object4 = (bl = this.b) ? "game_dice" : "game_finger"), true)) {
                    t_0.e.post((Runnable)new q_0(activity, bl, this.c, object3));
                    return var1_5;
                }
            }
            Object object5 = object.proceed();
        }
        return var1_5;
    }
}

