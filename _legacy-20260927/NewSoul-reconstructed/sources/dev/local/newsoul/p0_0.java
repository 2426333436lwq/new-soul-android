/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.widget.TextView
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.util.Log;
import android.widget.TextView;
import dev.local.newsoul.V;
import dev.local.newsoul.l0_0;
import dev.local.newsoul.m0_0;
import dev.local.newsoul.n0_0;
import dev.local.newsoul.o0_0;
import dev.local.newsoul.r0_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.p0
 */
public abstract class p0_0 {
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static final WeakHashMap f;
    public static XposedModule g;
    public static final HashSet h;
    public static Field i;
    public static Field j;
    public static Field k;
    public static Method l;
    public static Method m;

    static {
        f = new WeakHashMap();
        h = new HashSet();
    }

    public static void a(TextView textView, Object object, boolean bl, String string, Field field, Method method) {
        if (textView == null) {
            return;
        }
        WeakHashMap weakHashMap = f;
        weakHashMap.remove(textView);
        boolean bl2 = bl && object != null;
        r0_0.a(textView, bl2);
        if (bl && object != null) {
            object = new o0_0(field.getInt(object), string, textView.getText(), method, null);
            weakHashMap.put(textView, object);
            p0_0.k("bound ".concat(string));
            p0_0.i(textView, (o0_0)object);
        }
    }

    public static void b(Object object, Object object2, Object object3, boolean bl, Method method) {
        object2 = object2 instanceof TextView ? ((TextView)object2).getText() : null;
        object = (TextView)object;
        bl = bl && object2 != null && ("\u770b\u8fc7\u6211".equals(object2 = object2.toString().trim()) || "\u8bbf\u5ba2".equals(object2) || "\u8bbf\u5ba2\u6570".equals(object2));
        p0_0.a((TextView)object, object3, bl, "profile_disguise_visitor_", k, method);
    }

    public static void c(String string, Throwable throwable) {
        V.i("profile.disguise.".concat(string), throwable);
        Object object = g;
        if (object != null) {
            StringBuilder stringBuilder = new StringBuilder("follow disguise ");
            stringBuilder.append(string);
            stringBuilder.append(" unavailable");
            object.log(6, "SoulHook102", stringBuilder.toString(), throwable);
        }
        object = new StringBuilder("follow disguise ");
        ((StringBuilder)object).append(string);
        ((StringBuilder)object).append(" unavailable");
        Log.e((String)"SoulHook102", (String)((StringBuilder)object).toString(), (Throwable)throwable);
    }

    public static Field d(Class annotatedElement, String string) {
        annotatedElement = ((Class)annotatedElement).getDeclaredField(string);
        ((AccessibleObject)annotatedElement).setAccessible(true);
        return annotatedElement;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void e(XposedModule xposedModule, ClassLoader object) {
        boolean bl;
        block9: {
            void var7_6;
            block8: {
                Method method;
                Field field;
                Field field2;
                Field field3;
                Field field4;
                Field field5;
                Field field6;
                XposedInterface.HookBuilder hookBuilder;
                AnnotatedElement annotatedElement;
                AnnotatedElement annotatedElement2;
                g = xposedModule;
                try {
                    annotatedElement2 = ((ClassLoader)object).loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount");
                    i = p0_0.d(annotatedElement2, "followNum");
                    annotatedElement = ((Class)annotatedElement2).getDeclaredField("beFollowNum");
                    ((AccessibleObject)annotatedElement).setAccessible(true);
                    j = annotatedElement;
                    annotatedElement2 = ((Class)annotatedElement2).getDeclaredField("beViewNum");
                    ((AccessibleObject)annotatedElement2).setAccessible(true);
                    k = annotatedElement2;
                    annotatedElement2 = ((ClassLoader)object).loadClass("cn.soulapp.android.client.component.middle.platform.utils.m2");
                    annotatedElement = Integer.TYPE;
                    l = ((Class)annotatedElement2).getDeclaredMethod("f", new Class[]{annotatedElement});
                    m = ((Class)annotatedElement2).getDeclaredMethod("e", new Class[]{annotatedElement});
                    ((AccessibleObject)l).setAccessible(true);
                    ((AccessibleObject)m).setAccessible(true);
                }
                catch (Throwable throwable) {
                    p0_0.c("fields", throwable);
                    return;
                }
                a = p0_0.g(xposedModule, (ClassLoader)object, "revision", "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "S1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "o", "q", "g0", "h0", "b", "d", true);
                b = p0_0.g(xposedModule, (ClassLoader)object, "meta", "cn.soulapp.android.component.home.meta.view.MetaFollowFansContainerView", "w", "cn.soulapp.android.component.home.databinding.CUsrMetaFollowFansContainerBinding", "e", "g", "q", "r", "k", "b", false);
                try {
                    annotatedElement2 = ((ClassLoader)object).loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper");
                    hookBuilder = i.getDeclaringClass();
                    field6 = p0_0.d((Class)annotatedElement2, "M");
                    field5 = ((Class)annotatedElement2).getDeclaredField("j");
                    ((AccessibleObject)field5).setAccessible(true);
                    field4 = ((Class)annotatedElement2).getDeclaredField("l");
                    ((AccessibleObject)field4).setAccessible(true);
                    field3 = ((Class)annotatedElement2).getDeclaredField("n");
                    ((AccessibleObject)field3).setAccessible(true);
                    annotatedElement = ((Class)annotatedElement2).getDeclaredField("o");
                    ((AccessibleObject)annotatedElement).setAccessible(true);
                    field2 = ((Class)annotatedElement2).getDeclaredField("q");
                    ((AccessibleObject)field2).setAccessible(true);
                    field = ((Class)annotatedElement2).getDeclaredField("r");
                    ((AccessibleObject)field).setAccessible(true);
                    method = ((Class)annotatedElement2).getDeclaredMethod("setUserFollowData", new Class[]{hookBuilder});
                }
                catch (Throwable throwable) {
                    // empty catch block
                    break block8;
                }
                try {
                    hookBuilder = xposedModule.hook((Executable)method).setId("new-soul/follow-disguise-header").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    l0_0 l0_02 = new l0_0(field6, field5, field4, field3, field2, (Field)annotatedElement, field);
                    hookBuilder.intercept((XposedInterface.Hooker)l0_02);
                    xposedModule.deoptimize((Executable)method);
                    annotatedElement2 = ((Class)annotatedElement2).getDeclaredMethods();
                    int n = ((AnnotatedElement)annotatedElement2).length;
                    for (int i = 0; i < n; ++i) {
                        xposedModule.deoptimize((Executable)annotatedElement2[i]);
                    }
                }
                catch (Throwable throwable) {
                    break block8;
                }
                {
                    c = true;
                    V.i("profile.disguise.header", null);
                    break block9;
                }
            }
            p0_0.c("header", (Throwable)var7_6);
        }
        d = p0_0.f(xposedModule, (ClassLoader)object, "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "Q1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "V", true);
        e = bl = p0_0.f(xposedModule, (ClassLoader)object, "cn.soulapp.android.component.home.meta.me2.userinfo.UserInfoProvider", "t0", "cn.soulapp.android.component.home.databinding.CUsrLayoutMeInfoNewBinding", "y", false);
        boolean bl2 = d;
        object = new StringBuilder("days disguise registered: revision=");
        ((StringBuilder)object).append(bl2);
        ((StringBuilder)object).append("; meta=");
        ((StringBuilder)object).append(bl);
        xposedModule.log(4, "SoulHook102", ((StringBuilder)object).toString());
        boolean bl3 = a;
        bl = b;
        bl2 = c;
        object = new StringBuilder("follow disguise registered: revision=");
        ((StringBuilder)object).append(bl3);
        ((StringBuilder)object).append("; meta=");
        ((StringBuilder)object).append(bl);
        ((StringBuilder)object).append("; header=");
        ((StringBuilder)object).append(bl2);
        xposedModule.log(4, "SoulHook102", ((StringBuilder)object).toString());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean f(XposedModule object, ClassLoader object2, String object3, String string, String object4, String object5, boolean bl) {
        Throwable throwable2;
        String string2;
        block5: {
            Field field;
            Field field2;
            Class<?> clazz;
            Class<?> clazz2;
            string2 = bl ? "days-revision" : "days-meta";
            try {
                clazz2 = object2.loadClass((String)object3);
                object4 = object2.loadClass((String)object4);
                clazz = object2.loadClass("com.soul.component.componentlib.service.user.bean.User");
                field2 = clazz.getField("registerDay");
                field = p0_0.d(clazz2, "b");
                object2 = bl ? ((Class)object4).getDeclaredMethod("b", null) : null;
            }
            catch (Throwable throwable2) {
                break block5;
            }
            if (object2 != null) {
                object2.setAccessible(true);
            }
            object3 = bl ? object2.getReturnType() : object4;
            object3 = p0_0.d(object3, (String)object5);
            object4 = clazz2.getDeclaredMethod(string, new Class[]{clazz, object4});
            string = object.hook((Executable)object4).setId("new-soul/".concat(string2)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            object5 = new n0_0(bl, (Method)object2, (Field)object3, field, field2, string2);
            string.intercept((XposedInterface.Hooker)object5);
            object.deoptimize((Executable)object4);
            object2 = clazz2.getDeclaredMethods();
            int n = ((Method[])object2).length;
            for (int i = 0; i < n; ++i) {
                object.deoptimize((Executable)object2[i]);
            }
            object = new StringBuilder();
            ((StringBuilder)object).append("profile.disguise.");
            ((StringBuilder)object).append(string2);
            V.i(((StringBuilder)object).toString(), null);
            return true;
        }
        p0_0.c(string2, throwable2);
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean g(XposedModule object, ClassLoader object2, String string, String object3, String object4, String object5, String object6, String object7, String object8, String object9, String object10, String object11, boolean bl) {
        Throwable throwable2;
        block5: {
            Class<?> clazz;
            Class<?> clazz2;
            try {
                clazz2 = object2.loadClass((String)object3);
                object5 = object2.loadClass((String)object5);
                clazz = object2.loadClass("com.soul.component.componentlib.service.user.bean.User");
                object2 = bl ? ((Class)object5).getDeclaredMethod("b", null) : null;
            }
            catch (Throwable throwable2) {
                break block5;
            }
            if (object2 != null) {
                object2.setAccessible(true);
            }
            object3 = bl ? object2.getReturnType() : object5;
            object6 = p0_0.d(object3, (String)object6);
            object7 = ((Class)object3).getDeclaredField((String)object7);
            ((AccessibleObject)object7).setAccessible(true);
            object8 = ((Class)object3).getDeclaredField((String)object8);
            ((AccessibleObject)object8).setAccessible(true);
            object3 = ((Class)object3).getDeclaredField((String)object9);
            ((AccessibleObject)object3).setAccessible(true);
            object9 = p0_0.d(clazz2, (String)object10);
            object10 = clazz2.getDeclaredField((String)object11);
            ((AccessibleObject)object10).setAccessible(true);
            object11 = clazz2.getDeclaredMethod((String)object4, new Class[]{clazz, object5});
            object5 = object.hook((Executable)object11).setId("new-soul/follow-disguise-".concat(string)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            object4 = new m0_0(bl, (Method)object2, string, (Field)object9, (Field)object10, (Field)object6, (Field)object7, (Field)object8, (Field)object3);
            object5.intercept((XposedInterface.Hooker)object4);
            object.deoptimize((Executable)object11);
            object2 = clazz2.getDeclaredMethods();
            int n = ((Method[])object2).length;
            for (int i = 0; i < n; ++i) {
                object.deoptimize((Executable)object2[i]);
            }
            object = new StringBuilder();
            ((StringBuilder)object).append("profile.disguise.");
            ((StringBuilder)object).append(string);
            V.i(((StringBuilder)object).toString(), null);
            return true;
        }
        p0_0.c(string, throwable2);
        return false;
    }

    public static void h() {
        for (Map.Entry entry : f.entrySet()) {
            try {
                p0_0.i((TextView)entry.getKey(), (o0_0)entry.getValue());
            }
            catch (Exception exception) {
                p0_0.j("refresh", exception);
            }
        }
    }

    public static void i(TextView textView, o0_0 object) {
        Object object2 = w2.o(textView.getContext());
        String string = ((o0_0)object).b;
        CharSequence charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append(string);
        ((StringBuilder)charSequence).append("enabled");
        boolean bl = object2.getBoolean(((StringBuilder)charSequence).toString(), false);
        charSequence = ((o0_0)object).c;
        if (!bl) {
            textView.setText(charSequence);
            return;
        }
        CharSequence charSequence2 = new StringBuilder();
        string = ((o0_0)object).b;
        charSequence2.append(string);
        charSequence2.append("mode");
        charSequence2 = object2.getString(charSequence2.toString(), "absolute");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append((String)charSequence2);
        int n = object2.getInt(stringBuilder.toString(), 0);
        long l = "delta".equals(charSequence2) ? (long)((o0_0)object).a + (long)n : (long)n;
        int n2 = (int)Math.max(0L, Math.min(Integer.MAX_VALUE, l));
        string = ((o0_0)object).e;
        if (string == null) {
            object = (CharSequence)((o0_0)object).d.invoke(null, n2);
        } else {
            object = charSequence.toString();
            n = string != null && !string.isEmpty() ? ((String)object).indexOf(string) : -1;
            if (n >= 0) {
                object2 = ((String)object).substring(0, n);
                object = ((String)object).substring(string.length() + n);
                charSequence = new StringBuilder();
                ((StringBuilder)charSequence).append((String)object2);
                ((StringBuilder)charSequence).append(n2);
                ((StringBuilder)charSequence).append((String)object);
                object = ((StringBuilder)charSequence).toString();
            }
        }
        textView.setText((CharSequence)object);
    }

    public static void j(String string, Throwable throwable) {
        if (g != null) {
            Serializable serializable = h;
            StringBuilder stringBuilder = new StringBuilder("error:");
            stringBuilder.append(string);
            if (((HashSet)serializable).add(stringBuilder.toString())) {
                stringBuilder = g;
                serializable = new StringBuilder("follow disguise ");
                ((StringBuilder)serializable).append(string);
                ((StringBuilder)serializable).append(" failed");
                stringBuilder.log(6, "SoulHook102", ((StringBuilder)serializable).toString(), throwable);
            }
        }
    }

    public static void k(String string) {
        if (g != null && h.add(string)) {
            XposedModule xposedModule = g;
            StringBuilder stringBuilder = new StringBuilder("follow disguise ");
            stringBuilder.append(string);
            xposedModule.log(4, "SoulHook102", stringBuilder.toString());
        }
    }
}

