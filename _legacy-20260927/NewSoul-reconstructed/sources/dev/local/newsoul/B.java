/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.view.View
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.util.Log;
import android.view.View;
import dev.local.newsoul.Q0;
import dev.local.newsoul.j1_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.z1_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;

public final class B
implements XposedInterface.Hooker {
    public final int a;
    public final Field b;
    public final Field c;

    public /* synthetic */ B(Field field, Field field2, int n) {
        this.a = n;
        this.b = field;
        this.c = field2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Field field = this.b;
                Field field2 = this.c;
                Object object2 = object.proceed();
                try {
                    field = (View)field.get(object.getThisObject());
                    boolean bl = false;
                    if (field2.getInt(object.getArg(0)) == -220922) {
                        bl = true;
                    }
                    j1_0.b((View)field, bl);
                    return object2;
                }
                catch (Throwable throwable) {
                    Log.w((String)"SoulHook102", (String)"post action mark unavailable", (Throwable)throwable);
                }
                return object2;
            }
            case 0: 
        }
        Object object3 = this.c;
        int n = 0;
        Object object4 = object.getArg(0);
        View view = (View)this.b.get(object4);
        z1_0.b(view);
        object4 = object.proceed();
        try {
            Object object5 = object.getArg(1);
            if (!Q0.v(object5)) return object4;
            if (!view.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0).contains(Q0.L(object5))) return object4;
            object5 = ((Field)object3).get(object.getThisObject());
            object3 = "\u5bf9\u65b9";
            object = object3;
            if (object5 != null) {
                while (true) {
                    object = object3;
                    if (n >= 2) break;
                    object = (new String[]{"alias", "signature"})[n];
                    object = object5.getClass().getField((String)object).get(object5);
                    if (object instanceof String && !((String)object).isEmpty()) {
                        object = (String)object;
                        break;
                    }
                    ++n;
                }
            }
            z1_0.a(view, w2.o(view.getContext()).getString("chat_recall_style", "below"), (String)object);
            return object4;
        }
        catch (Throwable throwable) {}
        Log.e((String)"SoulHook102", (String)"recall message style unavailable", (Throwable)throwable);
        return object4;
    }
}

