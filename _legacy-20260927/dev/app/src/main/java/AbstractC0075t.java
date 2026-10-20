package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0075t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0075t {
    public static boolean a;
    public static boolean b;
    public static XposedModule f;
    public static Constructor h;
    public static Field i;
    public static final ThreadLocal c = new ThreadLocal();
    public static final Set d = Collections.newSetFromMap(new WeakHashMap());
    public static final Handler e = new Handler(Looper.getMainLooper());
    public static final String[] g = {"布", "剪刀", "石头"};

    public static boolean a(Class cls, final Field field, String str, final boolean z) {
        try {
            final Method declaredMethod = cls.getDeclaredMethod(str, null);
            if (declaredMethod.getReturnType() != Void.TYPE) {
                throw new IllegalStateException("send signature changed");
            }
            f.hook(declaredMethod).setId("new-soul/game-picker/".concat(str)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept( new p(field, z, declaredMethod));
            f.deoptimize(declaredMethod);
            return true;
        } catch (Throwable th) {
            f.log(6, "SoulHook102", "game hook unavailable: ".concat(str), th);
            return false;
        }
    }

    public static void b(Activity activity, r rVar, boolean z) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Set set = d;
        if (set.contains(activity)) {
            return;
        }
        set.add(activity);
        try {
            F0.c(activity, z, rVar == null, new C0071s(activity, rVar, z), new RunnableC0048m(activity, 1));
        } catch (Throwable th) {
            set.remove(activity);
            f.log(6, "SoulHook102", "game picker failed", th);
        }
    }
}
