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

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0061p0, reason: case insensitive filesystem */
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
    public static final WeakHashMap<Object, Object> f = new WeakHashMap<>();
    public static final HashSet h = new HashSet();

    public static void a(TextView textView, Object obj, boolean z, String str, Field field, Method method) {
        try {
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
            } catch (IllegalAccessException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
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
                    xposedModule2.hook(declaredMethod).setId("new-soul/follow-disguise-header").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new l0(declaredField8, declaredField8, declaredField8, declaredField8, declaredField8, declaredField8, declaredField8));
                    xposedModule2.deoptimize(declaredMethod);
                    for (Method method : clsLoadClass3.getDeclaredMethods()) {
                        xposedModule2.deoptimize(method);
                    }
                    c = true;
                    V.i("profile.disguise.header", null);
                } catch (Throwable th) {
                    c("header", th);
                }
            } catch (Throwable th2) {
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
            xposedModule.hook(declaredMethod2).setId("new-soul/".concat(str5)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept( new n0(z, method, fieldD2, fieldD, field, str5));
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
            exceptionMode.intercept( new m0(z, method, str, fieldD2, declaredField4, fieldD, declaredField, declaredField2, declaredField3));
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
        for (Map.Entry<Object, Object> entry : f.entrySet()) {
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
        try {
        CharSequence string;
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
            String stringS = charSequence.toString();
            int iIndexOf = (str2 == null || str2.isEmpty()) ? -1 : stringS.indexOf(str2);
            if (iIndexOf >= 0) {
                stringS = stringS.substring(0, iIndexOf) + iMax + stringS.substring(str2.length() + iIndexOf);
            }
            string = stringS;
        }
        textView.setText(string);
            } catch (IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
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

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {
        throw (T) ex;
    }
}
