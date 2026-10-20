package dev.local.newsoul;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0025g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0025g0 {
    public static XposedModule a;
    public static final Handler b = new Handler(Looper.getMainLooper());
    public static final HashSet c = new HashSet();
    public static final WeakHashMap d = new WeakHashMap();
    public static final SecureRandom e = new SecureRandom();

    public static void a(Activity activity) {
        try {
        Method declaredMethod;
        if (a == null) {
            throw new IllegalStateException();
        }
        Class<?> superclass = activity.getClass();
        while (true) {
            if (superclass == null) {
                declaredMethod = null;
                break;
            }
            try {
                Class cls = Integer.TYPE;
                declaredMethod = superclass.getDeclaredMethod("onActivityResult", cls, cls, Intent.class);
                break;
            } catch (NoSuchMethodException unused) {
                superclass = superclass.getSuperclass();
            }
        }
        if (declaredMethod == null) {
            throw new NoSuchMethodException();
        }
        HashSet hashSet = c;
        if (hashSet.contains(declaredMethod)) {
            return;
        }
        a.hook(declaredMethod).setId("new-soul/debug-export-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(14));
        hashSet.add(declaredMethod);
            } catch (NoSuchMethodException e) {
                sneakyThrow(e);
                throw new AssertionError();
            }
    }

    public static void b(Activity activity, C0001a0 c0001a0, String str) {
        WeakHashMap weakHashMap = d;
        if (weakHashMap.get(activity) != c0001a0) {
            return;
        }
        weakHashMap.remove(activity);
        c0001a0.c = null;
        C0021f0 c0021f0 = (C0021f0) c0001a0.b.get();
        if (c0021f0 == null || activity.isDestroyed()) {
            return;
        }
        c0021f0.a(str);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {
        throw (T) ex;
    }
}
