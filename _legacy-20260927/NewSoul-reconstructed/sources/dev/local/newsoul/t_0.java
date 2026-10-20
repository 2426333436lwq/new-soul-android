/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Handler
 *  android.os.Looper
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import dev.local.newsoul.F0;
import dev.local.newsoul.m_0;
import dev.local.newsoul.p_0;
import dev.local.newsoul.r;
import dev.local.newsoul.s_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.t
 */
public abstract class t_0 {
    public static boolean a;
    public static boolean b;
    public static final ThreadLocal c;
    public static final Set d;
    public static final Handler e;
    public static XposedModule f;
    public static final String[] g;
    public static Constructor h;
    public static Field i;

    static {
        c = new ThreadLocal();
        d = Collections.newSetFromMap(new WeakHashMap());
        e = new Handler(Looper.getMainLooper());
        g = new String[]{"\u5e03", "\u526a\u5200", "\u77f3\u5934"};
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean a(Class object, Field field, String string, boolean bl) {
        Throwable throwable2;
        block3: {
            try {
                Method method = ((Class)object).getDeclaredMethod(string, null);
                if (method.getReturnType() == Void.TYPE) {
                    XposedInterface.HookBuilder hookBuilder = f.hook((Executable)method).setId("new-soul/game-picker/".concat(string)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    object = new p_0(field, bl, method);
                    hookBuilder.intercept((XposedInterface.Hooker)object);
                    f.deoptimize((Executable)method);
                    return true;
                }
            }
            catch (Throwable throwable2) {
                break block3;
            }
            object = new IllegalStateException("send signature changed");
            throw object;
        }
        f.log(6, "SoulHook102", "game hook unavailable: ".concat(string), throwable2);
        return false;
    }

    public static void b(Activity activity, r object, boolean bl) {
        Set set;
        if (!(activity.isFinishing() || activity.isDestroyed() || (set = d).contains(activity))) {
            set.add(activity);
            boolean bl2 = object == null;
            try {
                s_0 s_02 = new s_0(activity, (r)object, bl);
                object = new m_0(activity, 1);
                F0.c(activity, bl, bl2, s_02, (m_0)object);
            }
            catch (Throwable throwable) {
                set.remove(activity);
                f.log(6, "SoulHook102", "game picker failed", throwable);
            }
        }
    }
}

