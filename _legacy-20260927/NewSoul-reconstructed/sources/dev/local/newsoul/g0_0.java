/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  android.os.Handler
 *  android.os.Looper
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.a_0;
import dev.local.newsoul.f0_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Executable;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.g0
 */
public abstract class g0_0 {
    public static XposedModule a;
    public static final Handler b;
    public static final HashSet c;
    public static final WeakHashMap d;
    public static final SecureRandom e;

    static {
        b = new Handler(Looper.getMainLooper());
        c = new HashSet();
        d = new WeakHashMap();
        e = new SecureRandom();
    }

    public static void a(Activity object) {
        if (a != null) {
            Object object2;
            block6: {
                for (object = object.getClass(); object != null; object = ((Class)object).getSuperclass()) {
                    try {
                        object2 = Integer.TYPE;
                        object = object2 = ((Class)object).getDeclaredMethod("onActivityResult", new Class[]{object2, object2, Intent.class});
                        break block6;
                    }
                    catch (NoSuchMethodException noSuchMethodException) {
                        continue;
                    }
                }
                object = null;
            }
            if (object != null) {
                object2 = c;
                if (((HashSet)object2).contains(object)) {
                    return;
                }
                a.hook((Executable)object).setId("new-soul/debug-export-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new a_0(14));
                ((HashSet)object2).add(object);
                return;
            }
            throw new NoSuchMethodException();
        }
        throw new IllegalStateException();
    }

    public static void b(Activity activity, a0_0 object, String string) {
        WeakHashMap weakHashMap = d;
        if (weakHashMap.get(activity) != object) {
            return;
        }
        weakHashMap.remove(activity);
        ((a0_0)object).c = null;
        object = (f0_0)((a0_0)object).b.get();
        if (object != null && !activity.isDestroyed()) {
            ((f0_0)object).a(string);
        }
    }
}

