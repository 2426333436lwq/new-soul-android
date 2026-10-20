/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.util.Log;
import dev.local.newsoul.a_0;
import dev.local.newsoul.f1_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.g1
 */
public abstract class g1_0 {
    public static boolean a;
    public static final ThreadLocal b;

    static {
        b = new ThreadLocal();
    }

    public static void a(XposedModule xposedModule, ClassLoader object) {
        try {
            Object object2 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.msg.ImMessage");
            Method method = object2.getDeclaredMethod("getLocalTime", null);
            Method method2 = object2.getMethod("getFrom", null);
            Method method3 = object2.getMethod("isAnonymityChat", null);
            Method method4 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
            Method method5 = ((ClassLoader)object).loadClass("lf.b").getDeclaredMethod("b", null);
            object = ((ClassLoader)object).loadClass("cn.soulapp.android.component.chat.helper.WithDrawHandler").getDeclaredMethod("d", new Class[]{object2});
            object2 = xposedModule.hook((Executable)method).setId("new-soul/own-recall-age-read");
            method = XposedInterface.ExceptionMode.PROTECTIVE;
            XposedInterface.HookBuilder hookBuilder = object2.setExceptionMode((XposedInterface.ExceptionMode)method);
            object2 = new a_0(15);
            hookBuilder.intercept((XposedInterface.Hooker)object2);
            method = xposedModule.hook((Executable)object).setId("new-soul/own-recall-entry").setExceptionMode((XposedInterface.ExceptionMode)method);
            object2 = new f1_0(method4, method2, method3, method5, 0);
            method.intercept((XposedInterface.Hooker)object2);
            xposedModule.deoptimize((Executable)object);
            a = true;
            Log.i((String)"SoulHook102", (String)"own recall time limit hook installed");
        }
        catch (Throwable throwable) {
            xposedModule.log(6, "SoulHook102", "own recall time limit unavailable", throwable);
        }
    }
}

