/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Log
 *  android.view.View
 *  android.widget.TextView
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.L2;
import dev.local.newsoul.V;
import dev.local.newsoul.e_0;
import dev.local.newsoul.k1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.s1_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Renamed from dev.local.newsoul.u1
 */
public abstract class u1_0 {
    public static final Handler a = new Handler(Looper.getMainLooper());
    public static final ExecutorService b = Executors.newSingleThreadExecutor();
    public static final WeakHashMap c = new WeakHashMap();
    public static Method d;
    public static Method e;
    public static boolean f;

    public static TextView a(q2 object, String string) {
        Context context = ((q2)object).g;
        string = w2.n(context, string, 16, ((q2)object).i);
        string.setMinHeight(w2.i(context, 48));
        string.setGravity(16);
        string.setPadding(w2.i(context, 12), w2.i(context, 10), w2.i(context, 12), w2.i(context, 10));
        object = ((q2)object).c;
        object.getClass();
        ((L2)object).b((View)string, 12, L2.e(12));
        w2.g((View)string);
        return string;
    }

    public static boolean b(Activity activity) {
        boolean bl = activity != null && !activity.isFinishing() && !activity.isDestroyed();
        return bl;
    }

    public static Object c(Object object, String string) {
        return object.getClass().getMethod(string, null).invoke(object, null);
    }

    public static String d() {
        Method method = d;
        String string = null;
        if (method != null) {
            string = (String)method.invoke(null, null);
        }
        if (string != null && !string.trim().isEmpty()) {
            return string;
        }
        throw new IllegalStateException("\u8bf7\u5148\u767b\u5f55 Soul");
    }

    public static void e(Activity activity, Exception object) {
        Log.w((String)"SoulHook102", (String)"profile bookmark operation failed", (Throwable)object);
        object = !(object instanceof IllegalStateException) && !(object instanceof IllegalArgumentException) ? "\u64cd\u4f5c\u5931\u8d25\uff0c\u539f\u6709\u8bb0\u5f55\u5df2\u4fdd\u7559" : ((Throwable)object).getMessage();
        if (u1_0.b(activity)) {
            Toast.makeText((Context)activity, (CharSequence)object, (int)0).show();
        }
    }

    public static Field f(Class clazz, String string) {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(string);
                ((AccessibleObject)field).setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException noSuchFieldException) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(string);
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void g(XposedModule xposedModule, ClassLoader methodArray) {
        Throwable throwable2;
        block6: {
            int n;
            int n2;
            Object object;
            Method method;
            Field field;
            Field field2;
            Field field3;
            Field field4;
            Field field5;
            Field field6;
            Field field7;
            Constructor<?> constructor;
            GenericDeclaration genericDeclaration;
            XposedInterface.HookBuilder hookBuilder;
            AnnotatedElement annotatedElement;
            XposedInterface.ExceptionMode exceptionMode;
            try {
                d = methodArray.loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
                e = methodArray.loadClass("cn.soulapp.android.component.home.user.UserHomeActivity").getDeclaredMethod("y", String.class, String.class);
                exceptionMode = methodArray.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
                annotatedElement = methodArray.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment");
                hookBuilder = methodArray.loadClass(annotatedElement.getName().concat("$Operate"));
                genericDeclaration = Integer.TYPE;
                constructor = hookBuilder.getConstructor(new Class[]{genericDeclaration, String.class, Boolean.TYPE});
                field7 = u1_0.f(exceptionMode, "D");
                field6 = u1_0.f(exceptionMode, "E");
                field5 = u1_0.f(exceptionMode, "C");
                field4 = u1_0.f(annotatedElement, "q");
                field3 = u1_0.f(hookBuilder, "a");
                field2 = u1_0.f(hookBuilder, "b");
                field = u1_0.f(hookBuilder, "f");
                annotatedElement = u1_0.f(exceptionMode, "y");
                method = exceptionMode.getDeclaredMethod("initViews", View.class);
                genericDeclaration = exceptionMode.getDeclaredMethod("P0", new Class[]{hookBuilder, View.class, genericDeclaration});
                Object object2 = methodArray.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a");
                object = u1_0.f(object2, "c");
                exceptionMode = u1_0.f(object2, "a");
                Method method2 = ((Class)object2).getDeclaredMethod("a", new Class[]{hookBuilder});
                hookBuilder = xposedModule.hook((Executable)method2).setId("new-soul/profile-bookmark-icon").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                object2 = new e_0((AnnotatedElement)object, field3, exceptionMode, 2);
                hookBuilder.intercept((XposedInterface.Hooker)object2);
                xposedModule.deoptimize((Executable)method2);
                exceptionMode = methodArray.loadClass("cn.soulapp.android.square.adapter.TypeAdapter").getDeclaredMethods();
                n2 = ((Method[])exceptionMode).length;
                int n3 = 0;
                for (n = 0; n < n2; ++n) {
                    xposedModule.deoptimize((Executable)exceptionMode[n]);
                }
            }
            catch (Throwable throwable2) {
                break block6;
            }
            {
                object = xposedModule.hook(genericDeclaration).setId("new-soul/profile-bookmark-click");
                exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
                hookBuilder = object.setExceptionMode(exceptionMode);
                object = new k1_0(field3, 1);
                hookBuilder.intercept((XposedInterface.Hooker)object);
                exceptionMode = xposedModule.hook((Executable)method).setId("new-soul/profile-bookmark-menu").setExceptionMode(exceptionMode);
                object = new s1_0(field7, field6, field5, constructor, field3, field, field4, field2, (Field)annotatedElement);
                exceptionMode.intercept((XposedInterface.Hooker)object);
                xposedModule.deoptimize((Executable)method);
                xposedModule.deoptimize(genericDeclaration);
                methodArray = methodArray.loadClass("cn.soulapp.android.square.a0").getDeclaredMethods();
                n2 = methodArray.length;
                for (n = n3; n < n2; ++n) {
                    xposedModule.deoptimize((Executable)methodArray[n]);
                }
            }
            {
                f = true;
                V.i("profile.bookmarks", null);
                Log.i((String)"SoulHook102", (String)"profile bookmark menu and navigation installed");
                return;
            }
        }
        V.i("profile.bookmarks", throwable2);
        Log.e((String)"SoulHook102", (String)"profile bookmarks unavailable", (Throwable)throwable2);
    }

    public static void h(String string) {
        if (string.equals(u1_0.d())) {
            return;
        }
        throw new IllegalStateException("\u8d26\u53f7\u5df2\u5207\u6362\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u9875\u9762");
    }

    public static boolean i(q2 q22) {
        boolean bl = u1_0.b(q22.a) && (q22 = q22.e) != null && q22.isShowing();
        return bl;
    }
}

