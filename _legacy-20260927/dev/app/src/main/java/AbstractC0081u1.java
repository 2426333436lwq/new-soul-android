package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0081u1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0081u1 {
    public static final Handler a = new Handler(Looper.getMainLooper());
    public static final ExecutorService b = Executors.newSingleThreadExecutor();
    public static final WeakHashMap c = new WeakHashMap();
    public static Method d;
    public static Method e;
    public static boolean f;

    public static TextView a(C0067q2 c0067q2, String str) {
        Context context = c0067q2.g;
        TextView textViewN = AbstractC0090w2.n(context, str, 16, c0067q2.i);
        textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
        textViewN.setGravity(16);
        textViewN.setPadding(AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10), AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10));
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textViewN, 12, L2.e(12));
        AbstractC0090w2.g(textViewN);
        return textViewN;
    }

    public static boolean b(Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static Object c(Object obj, String str) {
        try {
        return obj.getClass().getMethod(str, null).invoke(obj, null);
            } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static String d() {
        try {
        Method method = d;
        String str = method != null ? (String) method.invoke(null, null) : null;
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalStateException("请先登录 Soul");
        }
        return str;
            } catch (IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static void e(Activity activity, Exception exc) {
        Log.w("SoulHook102", "profile bookmark operation failed", exc);
        String message = ((exc instanceof IllegalStateException) || (exc instanceof IllegalArgumentException)) ? exc.getMessage() : "操作失败，原有记录已保留";
        if (b(activity)) {
            Toast.makeText(activity, message, 0).show();
        }
    }

    public static Field f(Class cls, String str) throws NoSuchFieldException {
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException(str);
    }

    public static void g(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            d = classLoader.loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
            e = classLoader.loadClass("cn.soulapp.android.component.home.user.UserHomeActivity").getDeclaredMethod("y", String.class, String.class);
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment");
            Class<?> clsLoadClass3 = classLoader.loadClass(clsLoadClass2.getName().concat("$Operate"));
            Class cls = Integer.TYPE;
            final Constructor<?> constructor = clsLoadClass3.getConstructor(cls, String.class, Boolean.TYPE);
            final Field fieldF = f(clsLoadClass, "D");
            final Field fieldF2 = f(clsLoadClass, "E");
            final Field fieldF3 = f(clsLoadClass, "C");
            final Field fieldF4 = f(clsLoadClass2, "q");
            final Field fieldF5 = f(clsLoadClass3, "a");
            final Field fieldF6 = f(clsLoadClass3, "b");
            final Field fieldF7 = f(clsLoadClass3, "f");
            final Field fieldF8 = f(clsLoadClass, "y");
            Method declaredMethod = clsLoadClass.getDeclaredMethod("initViews", View.class);
            Method declaredMethod2 = clsLoadClass.getDeclaredMethod("P0", clsLoadClass3, View.class, cls);
            Class<?> clsLoadClass4 = classLoader.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a");
            Field fieldF9 = f(clsLoadClass4, "c");
            Field fieldF10 = f(clsLoadClass4, "a");
            Method declaredMethod3 = clsLoadClass4.getDeclaredMethod("a", clsLoadClass3);
            xposedModule.hook(declaredMethod3).setId("new-soul/profile-bookmark-icon").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0016e(fieldF9, fieldF5, fieldF10, 2));
            xposedModule.deoptimize(declaredMethod3);
            for (Method method : classLoader.loadClass("cn.soulapp.android.square.adapter.TypeAdapter").getDeclaredMethods()) {
                xposedModule.deoptimize(method);
            }
            XposedInterface.HookBuilder id = xposedModule.hook(declaredMethod2).setId("new-soul/profile-bookmark-click");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new C0042k1(fieldF5, 1));
            xposedModule.hook(declaredMethod).setId("new-soul/profile-bookmark-menu").setExceptionMode(exceptionMode).intercept( new s1(fieldF, fieldF2, fieldF3, constructor, fieldF5, fieldF7, fieldF4, fieldF6, fieldF8));
            xposedModule.deoptimize(declaredMethod);
            xposedModule.deoptimize(declaredMethod2);
            for (Method method2 : classLoader.loadClass("cn.soulapp.android.square.a0").getDeclaredMethods()) {
                xposedModule.deoptimize(method2);
            }
            f = true;
            V.i("profile.bookmarks", null);
            Log.i("SoulHook102", "profile bookmark menu and navigation installed");
        } catch (Throwable th) {
            V.i("profile.bookmarks", th);
            Log.e("SoulHook102", "profile bookmarks unavailable", th);
        }
    }

    public static void h(String str) {
        if (!str.equals(d())) {
            throw new IllegalStateException("账号已切换，请重新打开页面");
        }
    }

    public static boolean i(C0067q2 c0067q2) {
        AlertDialog alertDialog;
        return b(c0067q2.a) && (alertDialog = c0067q2.e) != null && alertDialog.isShowing();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {
        throw (T) ex;
    }
}
