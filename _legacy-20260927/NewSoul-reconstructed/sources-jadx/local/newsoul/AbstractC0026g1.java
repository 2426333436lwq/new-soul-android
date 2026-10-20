package dev.local.newsoul;

import android.util.Log;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: dev.local.newsoul.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0026g1 {
    public static boolean a;
    public static final ThreadLocal b = new ThreadLocal();

    public static void a(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.imlib.msg.ImMessage");
            Method declaredMethod = clsLoadClass.getDeclaredMethod("getLocalTime", null);
            Method method = clsLoadClass.getMethod("getFrom", null);
            Method method2 = clsLoadClass.getMethod("isAnonymityChat", null);
            Method declaredMethod2 = classLoader.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
            Method declaredMethod3 = classLoader.loadClass("lf.b").getDeclaredMethod("b", null);
            Method declaredMethod4 = classLoader.loadClass("cn.soulapp.android.component.chat.helper.WithDrawHandler").getDeclaredMethod("d", clsLoadClass);
            XposedInterface.HookBuilder id = xposedModule.hook(declaredMethod).setId("new-soul/own-recall-age-read");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new C0000a(15));
            xposedModule.hook(declaredMethod4).setId("new-soul/own-recall-entry").setExceptionMode(exceptionMode).intercept(new C0022f1(declaredMethod2, method, method2, declaredMethod3, 0));
            xposedModule.deoptimize(declaredMethod4);
            a = true;
            Log.i("SoulHook102", "own recall time limit hook installed");
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "own recall time limit unavailable", th);
        }
    }
}
