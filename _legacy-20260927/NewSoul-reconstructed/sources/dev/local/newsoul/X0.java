/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;

public final class X0
implements XposedInterface.Hooker {
    public final int a;
    public final Method b;
    public final String c;
    public final XposedModule d;
    public final int e;

    public /* synthetic */ X0(int n, Method method, String string, XposedModule xposedModule, int n2) {
        this.a = n;
        this.b = method;
        this.c = string;
        this.d = xposedModule;
        this.e = n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        Throwable throwable2;
        block4: {
            int n;
            String string;
            Object[] objectArray;
            block3: {
                objectArray = this.b;
                string = this.c;
                n = this.a;
                if (n >= 0) {
                    try {
                        objectArray = object.getArg(n);
                        break block3;
                    }
                    catch (Throwable throwable2) {
                        break block4;
                    }
                }
                objectArray = objectArray.invoke(null, null);
            }
            Object object2 = (Context)objectArray;
            if (object2 == null) return object.proceed();
            objectArray = w2.a;
            boolean bl = object2.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getBoolean(string, true);
            if (!bl) return object.proceed();
            objectArray = object.getArgs().toArray();
            object2 = Boolean.FALSE;
            n = this.e;
            objectArray[n] = object2;
            object2 = String.valueOf(object.getArg(n));
            StringBuilder stringBuilder = new StringBuilder("watermark bypass: ");
            stringBuilder.append(string);
            stringBuilder.append("; original=");
            stringBuilder.append((String)object2);
            Log.i((String)"SoulHook102", (String)stringBuilder.toString());
            return object.proceed(objectArray);
        }
        this.d.log(5, "SoulHook102", "download preference unavailable", throwable2);
        return object.proceed();
    }
}

