package dev.local.newsoul;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0061p0 {
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static XposedModule g;
    public static Field i;
    public static Field j;
    public static Field k;
    public static Method l;
    public static Method m;
    public static final WeakHashMap f = new WeakHashMap();
    public static final HashSet h = new HashSet();

    public static void a(TextView textView, Object obj, boolean z, String str, Field field, Method method) {
        if (textView == null) {
            return;
        }
        WeakHashMap weakHashMap = f;
        weakHashMap.remove(textView);
        AbstractC0068r0.a(textView, z && obj != null);
        if (!z || obj == null) {
            return;
        }
        C0057o0 c0057o0 = new C0057o0(field.getInt(obj), str, textView.getText(), method, null);
        weakHashMap.put(textView, c0057o0);
        k("bound ".concat(str));
        i(textView, c0057o0);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0037  */
    public static void b(Object obj, Object obj2, Object obj3, boolean z, Method method) {
        boolean z2;
        CharSequence text = obj2 instanceof TextView ? ((TextView) obj2).getText() : null;
        TextView textView = (TextView) obj;
        if (!z || text == null) {
            z2 = false;
        } else {
            String strTrim = text.toString().trim();
            if ("看过我".equals(strTrim) || "访客".equals(strTrim) || "访客数".equals(strTrim)) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        a(textView, obj3, z2, "profile_disguise_visitor_", k, method);
    }

    public static void c(String str, Throwable th) {
        V.i("profile.disguise.".concat(str), th);
        XposedModule xposedModule = g;
        if (xposedModule != null) {
            xposedModule.log(6, "SoulHook102", "follow disguise " + str + " unavailable", th);
        }
        Log.e("SoulHook102", "follow disguise " + str + " unavailable", th);
    }

    public static Field d(Class cls, String str) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField;
    }

    public static void e(XposedModule xposedModule, ClassLoader classLoader) {
        XposedModule xposedModule2;
        g = xposedModule;
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount");
            i = d(clsLoadClass, "followNum");
            Field declaredField = clsLoadClass.getDeclaredField("beFollowNum");
            declaredField.setAccessible(true);
            j = declaredField;
            Field declaredField2 = clsLoadClass.getDeclaredField("beViewNum");
            declaredField2.setAccessible(true);
            k = declaredField2;
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.client.component.middle.platform.utils.m2");
            Class cls = Integer.TYPE;
            l = clsLoadClass2.getDeclaredMethod("f", cls);
            m = clsLoadClass2.getDeclaredMethod("e", cls);
            l.setAccessible(true);
            m.setAccessible(true);
            a = g(xposedModule, classLoader, "revision", "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "S1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "o", "q", "g0", "h0", "b", "d", true);
            b = g(xposedModule, classLoader, "meta", "cn.soulapp.android.component.home.meta.view.MetaFollowFansContainerView", "w", "cn.soulapp.android.component.home.databinding.CUsrMetaFollowFansContainerBinding", "e", "g", "q", "r", "k", "b", false);
            try {
                Class<?> clsLoadClass3 = classLoader.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper");
                Class<?> declaringClass = i.getDeclaringClass();
                final Field fieldD = d(clsLoadClass3, "M");
                final Field declaredField3 = clsLoadClass3.getDeclaredField("j");
                declaredField3.setAccessible(true);
                final Field declaredField4 = clsLoadClass3.getDeclaredField("l");
                declaredField4.setAccessible(true);
                final Field declaredField5 = clsLoadClass3.getDeclaredField("n");
                declaredField5.setAccessible(true);
                final Field declaredField6 = clsLoadClass3.getDeclaredField("o");
                declaredField6.setAccessible(true);
                final Field declaredField7 = clsLoadClass3.getDeclaredField("q");
                declaredField7.setAccessible(true);
                final Field declaredField8 = clsLoadClass3.getDeclaredField("r");
                declaredField8.setAccessible(true);
                Method declaredMethod = clsLoadClass3.getDeclaredMethod("setUserFollowData", declaringClass);
                xposedModule2 = xposedModule;
                try {
                    xposedModule2.hook(declaredMethod).setId("new-soul/follow-disguise-header").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.l0
                        public final Object intercept(XposedInterface.Chain chain) {
                            Field field = fieldD;
                            Field field2 = declaredField3;
                            Field field3 = declaredField4;
                            Field field4 = declaredField5;
                            Field field5 = declaredField7;
                            Field field6 = declaredField6;
                            Field field7 = declaredField8;
                            Object objProceed = chain.proceed();
                            try {
                                Object thisObject = chain.getThisObject();
                                AbstractC0061p0.k("header type=" + field.getInt(thisObject));
                                boolean z = field.getInt(thisObject) == 0;
                                Object obj = field2.get(thisObject);
                                Object obj2 = field3.get(thisObject);
                                Object arg = chain.getArg(0);
                                Method method = AbstractC0061p0.m;
                                AbstractC0061p0.a((TextView) obj, arg, z, "profile_disguise_follow_", AbstractC0061p0.i, method);
                                AbstractC0061p0.a((TextView) obj2, arg, z, "profile_disguise_followed_", AbstractC0061p0.j, method);
                                AbstractC0061p0.b(field4.get(thisObject), field5.get(thisObject), chain.getArg(0), z, AbstractC0061p0.m);
                                AbstractC0061p0.b(field6.get(thisObject), field7.get(thisObject), chain.getArg(0), z, AbstractC0061p0.m);
                            } catch (Throwable th) {
                                AbstractC0061p0.j("header bind", th);
                            }
                            return objProceed;
                        }
                    });
                    xposedModule2.deoptimize(declaredMethod);
                    for (Method method : clsLoadClass3.getDeclaredMethods()) {
                        xposedModule2.deoptimize(method);
                    }
                    c = true;
                    V.i("profile.disguise.header", null);
                } catch (Throwable th) {
                    th = th;
                    c("header", th);
                }
            } catch (Throwable th2) {
                th = th2;
                xposedModule2 = xposedModule;
            }
            d = f(xposedModule, classLoader, "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "Q1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "V", true);
            boolean zF = f(xposedModule, classLoader, "cn.soulapp.android.component.home.meta.me2.userinfo.UserInfoProvider", "t0", "cn.soulapp.android.component.home.databinding.CUsrLayoutMeInfoNewBinding", "y", false);
            e = zF;
            xposedModule2.log(4, "SoulHook102", "days disguise registered: revision=" + d + "; meta=" + zF);
            xposedModule2.log(4, "SoulHook102", "follow disguise registered: revision=" + a + "; meta=" + b + "; header=" + c);
        } catch (Throwable th3) {
            c("fields", th3);
        }
    }

    public static boolean f(XposedModule xposedModule, ClassLoader classLoader, String str, String str2, String str3, String str4, final boolean z) {
        final String str5 = z ? "days-revision" : "days-meta";
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str);
            Class<?> clsLoadClass2 = classLoader.loadClass(str3);
            Class<?> clsLoadClass3 = classLoader.loadClass("com.soul.component.componentlib.service.user.bean.User");
            final Field field = clsLoadClass3.getField("registerDay");
            final Field fieldD = d(clsLoadClass, "b");
            Method declaredMethod = z ? clsLoadClass2.getDeclaredMethod("b", null) : null;
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            }
            final Field fieldD2 = d(z ? declaredMethod.getReturnType() : clsLoadClass2, str4);
            Method declaredMethod2 = clsLoadClass.getDeclaredMethod(str2, clsLoadClass3, clsLoadClass2);
            final Method method = declaredMethod;
            xposedModule.hook(declaredMethod2).setId("new-soul/".concat(str5)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.n0
                public final Object intercept(XposedInterface.Chain chain) {
                    String str6;
                    Method method2 = method;
                    Field field2 = fieldD2;
                    Field field3 = fieldD;
                    Field field4 = field;
                    String str7 = str5;
                    Object objProceed = chain.proceed();
                    try {
                        Object arg = chain.getArg(1);
                        Object arg2 = chain.getArg(0);
                        if (arg != null) {
                            if (z) {
                                arg = method2.invoke(arg, null);
                            }
                            TextView textView = (TextView) field2.get(arg);
                            if (textView != null) {
                                WeakHashMap weakHashMap = AbstractC0061p0.f;
                                weakHashMap.remove(textView);
                                AbstractC0068r0.a(textView, false);
                                if (field3.getInt(chain.getThisObject()) == 0 && arg2 != null && (str6 = (String) field4.get(arg2)) != null && textView.getText().toString().contains(str6) && !str6.isEmpty()) {
                                    C0057o0 c0057o0 = new C0057o0(Q0.P(str6, false), "profile_disguise_days_", textView.getText(), null, str6);
                                    weakHashMap.put(textView, c0057o0);
                                    AbstractC0068r0.a(textView, true);
                                    AbstractC0061p0.i(textView, c0057o0);
                                    AbstractC0061p0.k("bound " + str7);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        AbstractC0061p0.j(str7 + " bind", th);
                    }
                    return objProceed;
                }
            });
            xposedModule.deoptimize(declaredMethod2);
            for (Method method2 : clsLoadClass.getDeclaredMethods()) {
                xposedModule.deoptimize(method2);
            }
            V.i("profile.disguise." + str5, null);
            return true;
        } catch (Throwable th) {
            c(str5, th);
            return false;
        }
    }

    public static boolean g(XposedModule xposedModule, ClassLoader classLoader, final String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, final boolean z) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str2);
            Class<?> clsLoadClass2 = classLoader.loadClass(str4);
            Class<?> clsLoadClass3 = classLoader.loadClass("com.soul.component.componentlib.service.user.bean.User");
            Method declaredMethod = z ? clsLoadClass2.getDeclaredMethod("b", null) : null;
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            }
            Class<?> returnType = z ? declaredMethod.getReturnType() : clsLoadClass2;
            final Field fieldD = d(returnType, str5);
            final Field declaredField = returnType.getDeclaredField(str6);
            declaredField.setAccessible(true);
            final Field declaredField2 = returnType.getDeclaredField(str7);
            declaredField2.setAccessible(true);
            final Field declaredField3 = returnType.getDeclaredField(str8);
            declaredField3.setAccessible(true);
            final Field fieldD2 = d(clsLoadClass, str9);
            final Field declaredField4 = clsLoadClass.getDeclaredField(str10);
            declaredField4.setAccessible(true);
            Method declaredMethod2 = clsLoadClass.getDeclaredMethod(str3, clsLoadClass3, clsLoadClass2);
            XposedInterface.HookBuilder exceptionMode = xposedModule.hook(declaredMethod2).setId("new-soul/follow-disguise-".concat(str)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            final Method method = declaredMethod;
            exceptionMode.intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.m0
                public final Object intercept(XposedInterface.Chain chain) {
                    Method method2 = method;
                    String str11 = str;
                    Field field = fieldD2;
                    Field field2 = declaredField4;
                    Field field3 = fieldD;
                    Field field4 = declaredField;
                    Field field5 = declaredField2;
                    Field field6 = declaredField3;
                    Object objProceed = chain.proceed();
                    try {
                        Object thisObject = chain.getThisObject();
                        Object arg = chain.getArg(1);
                        if (arg != null) {
                            if (z) {
                                arg = method2.invoke(arg, null);
                            }
                            AbstractC0061p0.k(str11 + " type=" + field.getInt(thisObject) + "; count=" + (field2.get(thisObject) != null));
                            boolean z2 = field.getInt(thisObject) == 0;
                            Object obj = field2.get(thisObject);
                            Object obj2 = field3.get(arg);
                            Object obj3 = field4.get(arg);
                            Method method3 = AbstractC0061p0.l;
                            AbstractC0061p0.a((TextView) obj2, obj, z2, "profile_disguise_follow_", AbstractC0061p0.i, method3);
                            AbstractC0061p0.a((TextView) obj3, obj, z2, "profile_disguise_followed_", AbstractC0061p0.j, method3);
                            AbstractC0061p0.b(field5.get(arg), field6.get(arg), obj, z2, AbstractC0061p0.l);
                        }
                    } catch (Throwable th) {
                        AbstractC0061p0.j(str11 + " bind", th);
                    }
                    return objProceed;
                }
            });
            xposedModule.deoptimize(declaredMethod2);
            for (Method method2 : clsLoadClass.getDeclaredMethods()) {
                xposedModule.deoptimize(method2);
            }
            V.i("profile.disguise." + str, null);
            return true;
        } catch (Throwable th) {
            c(str, th);
            return false;
        }
    }

    public static void h() {
        for (Map.Entry entry : f.entrySet()) {
            try {
                i((TextView) entry.getKey(), (C0057o0) entry.getValue());
            } catch (Exception e2) {
                j("refresh", e2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.view.View, android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.CharSequence] */
    public static void i(TextView textView, C0057o0 c0057o0) {
        ?? string;
        SharedPreferences sharedPreferencesO = AbstractC0090w2.o(textView.getContext());
        boolean z = sharedPreferencesO.getBoolean(c0057o0.b + "enabled", false);
        CharSequence charSequence = c0057o0.c;
        if (!z) {
            textView.setText(charSequence);
            return;
        }
        StringBuilder sb = new StringBuilder();
        String str = c0057o0.b;
        sb.append(str);
        sb.append("mode");
        String string2 = sharedPreferencesO.getString(sb.toString(), "absolute");
        int i2 = sharedPreferencesO.getInt(str + string2, 0);
        int iMax = (int) Math.max(0L, Math.min(2147483647L, "delta".equals(string2) ? ((long) c0057o0.a) + ((long) i2) : i2));
        String str2 = c0057o0.e;
        if (str2 == null) {
            string = (CharSequence) c0057o0.d.invoke(null, Integer.valueOf(iMax));
        } else {
            string = charSequence.toString();
            int iIndexOf = (str2 == null || str2.isEmpty()) ? -1 : string.indexOf(str2);
            if (iIndexOf >= 0) {
                string = string.substring(0, iIndexOf) + iMax + string.substring(str2.length() + iIndexOf);
            }
        }
        textView.setText(string);
    }

    public static void j(String str, Throwable th) {
        if (g != null) {
            if (h.add("error:" + str)) {
                g.log(6, "SoulHook102", "follow disguise " + str + " failed", th);
            }
        }
    }

    public static void k(String str) {
        if (g == null || !h.add(str)) {
            return;
        }
        g.log(4, "SoulHook102", "follow disguise " + str);
    }
}
