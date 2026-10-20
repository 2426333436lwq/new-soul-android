/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import dev.local.newsoul.A2;
import dev.local.newsoul.h_0;
import dev.local.newsoul.k0_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.z2_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

public abstract class B2 {
    public static boolean a;
    public static Field b;
    public static Field c;
    public static Field d;
    public static Field e;
    public static Field f;
    public static String g;
    public static k0_0 h;
    public static boolean i;
    public static final Handler j;
    public static final WeakHashMap k;
    public static Class l;
    public static Field m;
    public static Field n;
    public static boolean o;
    public static int p;

    static {
        h = new k0_0("");
        j = new Handler(Looper.getMainLooper());
        k = new WeakHashMap();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean a(Context object, Object object2) {
        String string;
        SharedPreferences sharedPreferences;
        boolean bl;
        boolean bl2;
        block20: {
            k0_0 k0_022;
            block21: {
                boolean bl3 = i;
                bl = bl2 = true;
                if (!bl3) return bl;
                if (!l.isInstance(object2)) {
                    return bl2;
                }
                sharedPreferences = w2.o((Context)object);
                if (!a || !sharedPreferences.getBoolean("feed_keywords_enabled_v1", false)) break block21;
                synchronized (B2.class) {
                    Throwable throwable2;
                    block22: {
                        block19: {
                            block18: {
                                block17: {
                                    try {
                                        object = sharedPreferences.getString("feed_keywords_v1", "");
                                        bl = Objects.equals(object, g);
                                        if (!bl) {
                                            try {
                                                h = k0_022 = new k0_0((String)object);
                                            }
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                h = k0_022 = new k0_0("");
                                            }
                                            g = object;
                                        }
                                        if (!(bl = ((List)B2.h.a).isEmpty())) break block17;
                                    }
                                    catch (Throwable throwable2) {}
                                    break block21;
                                }
                                try {
                                    if (h.a((String)b.get(object2))) return false;
                                    if (h.a((String)c.get(object2))) return false;
                                    if (!h.a((String)d.get(object2))) break block18;
                                    break block19;
                                }
                                catch (ReflectiveOperationException reflectiveOperationException) {}
                                break block22;
                            }
                            object = e.get(object2);
                            if (object instanceof Iterable) {
                                for (k0_0 k0_022 : (Iterable)object) {
                                    if (k0_022 == null || !f.getDeclaringClass().isInstance(k0_022) || !(bl = h.a((String)f.get(k0_022)))) continue;
                                    return false;
                                }
                            }
                            break block21;
                        }
                        return false;
                    }
                    throw throwable2;
                }
            }
            if (!sharedPreferences.getBoolean("feed_filter_enabled", false)) {
                return true;
            }
            string = sharedPreferences.getString("feed_filter_gender", "FEMALE");
            k0_022 = null;
            try {
                object2 = m.get(object2);
                object = k0_022;
                if (object2 == null) break block20;
                int n = B2.n.getInt(object2);
                if (n == 1) {
                    object = "FEMALE";
                    break block20;
                }
                object = k0_022;
                if (n == 0) {
                    object = "MALE";
                }
            }
            catch (ReflectiveOperationException reflectiveOperationException) {
                object = k0_022;
            }
        }
        bl = sharedPreferences.getBoolean("feed_filter_keep_unknown", false);
        if ("ANY".equals(string)) {
            return bl2;
        }
        if ("MALE".equals(object)) return string.equals(object);
        if ("FEMALE".equals(object)) return string.equals(object);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(View view, A2 object) {
        if (i) {
            View view2 = view;
            while (view2 != null) {
                block9: {
                    if (view2.getId() != -1) {
                        boolean bl;
                        try {
                            bl = "pager_square".equals(view2.getResources().getResourceEntryName(view2.getId()));
                            if (!bl) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            break block9;
                        }
                        bl = B2.a(view.getContext(), ((A2)object).a);
                        view2 = view.getLayoutParams();
                        if (!bl && !((A2)object).b && view2 != null) {
                            int n;
                            ((A2)object).c = view2.height;
                            ((A2)object).d = view.getVisibility();
                            if (view2 instanceof ViewGroup.MarginLayoutParams) {
                                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view2;
                                ((A2)object).e = marginLayoutParams.topMargin;
                                ((A2)object).f = marginLayoutParams.bottomMargin;
                                marginLayoutParams.bottomMargin = 0;
                                marginLayoutParams.topMargin = 0;
                            }
                            ((A2)object).b = true;
                            p = n = p + 1;
                            if (n <= 5 || n % 25 == 0) {
                                object = new StringBuilder("feed filter rows hidden=");
                                ((StringBuilder)object).append(n);
                                Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
                            }
                            view2.height = 0;
                            view.setVisibility(8);
                            view.setLayoutParams((ViewGroup.LayoutParams)view2);
                            return;
                        }
                        if (!bl) return;
                        B2.e(view, (A2)object);
                        return;
                    }
                }
                if (view2.getParent() instanceof View) {
                    view2 = (View)view2.getParent();
                    continue;
                }
                view2 = null;
            }
        }
        B2.e(view, (A2)object);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c(XposedModule object, ClassLoader object2) {
        Throwable throwable2;
        block4: {
            AnnotatedElement annotatedElement;
            try {
                annotatedElement = ((ClassLoader)object2).loadClass("cn.soulapp.android.square.post.bean.Post");
                l = annotatedElement;
                annotatedElement = ((Class)annotatedElement).getField("postAuthorModel");
                m = annotatedElement;
                n = ((Field)annotatedElement).getType().getField("gender");
                try {
                    b = l.getField("content");
                    c = l.getField("title");
                    d = l.getField("contentTitle");
                    e = l.getField("tags");
                    f = ((ClassLoader)object2).loadClass("cn.soulapp.android.square.bean.Tag").getField("name");
                    a = true;
                }
                catch (ReflectiveOperationException reflectiveOperationException) {
                    Log.w((String)"SoulHook102", (String)"feed keyword fields unavailable", (Throwable)reflectiveOperationException);
                }
            }
            catch (Throwable throwable2) {
                break block4;
            }
            annotatedElement = ((ClassLoader)object2).loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
            object2 = ((ClassLoader)object2).loadClass("cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder");
            Object object3 = object.hook((Executable)((Class)annotatedElement).getDeclaredMethod("d", new Class[]{object2, Object.class})).setId("new-soul/feed-filter-bind");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            Object object4 = object3.setExceptionMode(exceptionMode);
            object3 = new z2_0((Class)object2, 0);
            object4.intercept((XposedInterface.Hooker)object3);
            object3 = object.hook((Executable)((Class)annotatedElement).getDeclaredMethod("j", new Class[]{object2})).setId("new-soul/feed-filter-recycle").setExceptionMode(exceptionMode);
            object4 = new z2_0((Class)object2, 1);
            object3.intercept((XposedInterface.Hooker)object4);
            annotatedElement = object.hook((Executable)((Class)annotatedElement).getDeclaredMethod("h", new Class[]{object2})).setId("new-soul/feed-filter-attach").setExceptionMode(exceptionMode);
            object = new z2_0((Class)object2, 2);
            annotatedElement.intercept((XposedInterface.Hooker)object);
            i = true;
            Log.i((String)"SoulHook102", (String)"square feed filter ready");
            return;
        }
        Log.e((String)"SoulHook102", (String)"square feed filter unavailable", (Throwable)throwable2);
    }

    public static void d() {
        if (o) {
            return;
        }
        o = true;
        j.postDelayed((Runnable)new h_0(4), 80L);
    }

    public static void e(View view, A2 a2) {
        if (a2 != null && a2.b) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = a2.c;
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)layoutParams;
                    marginLayoutParams.topMargin = a2.e;
                    marginLayoutParams.bottomMargin = a2.f;
                }
                view.setLayoutParams(layoutParams);
            }
            view.setVisibility(a2.d);
            a2.b = false;
        }
    }
}

