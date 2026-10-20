package dev.local.newsoul;

import android.widget.TextView;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* 本文件合并了仅大小写不同的两个类 N0 / n0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: N0.class */
public abstract /* synthetic */ class N0 {


    public static long a(android.content.pm.PackageInfo p0) {
        return p0.getLongVersionCode();
    }

    public static void b(android.widget.TextView p0) {
        p0.setAccessibilityHeading(true);
        return;
    }
}

/* JADX INFO: loaded from: n0.class */
final /* synthetic */ class n0 implements XposedInterface.Hooker {
    public final boolean a;
    public final Method b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final String f;

    public /* synthetic */ n0(boolean z, Method method, Field field, Field field2, Field field3, String str) {
        this.a = z;
        this.b = method;
        this.c = field;
        this.d = field2;
        this.e = field3;
        this.f = str;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        String str;
        Method method = this.b;
        Field field = this.c;
        Field field2 = this.d;
        Field field3 = this.e;
        String str2 = this.f;
        Object objProceed = chain.proceed();
        try {
            Object arg = chain.getArg(1);
            Object arg2 = chain.getArg(0);
            if (arg != null) {
                Object objInvoke = arg;
                if (this.a) {
                    objInvoke = method.invoke(arg, null);
                }
                TextView textView = (TextView) field.get(objInvoke);
                if (textView != null) {
                    WeakHashMap weakHashMap = AbstractC0061p0.f;
                    weakHashMap.remove(textView);
                    AbstractC0068r0.a(textView, false);
                    if (field2.getInt(chain.getThisObject()) == 0 && arg2 != null && (str = (String) field3.get(arg2)) != null && textView.getText().toString().contains(str) && !str.isEmpty()) {
                        C0057o0 o0Var = new C0057o0(Q0.P(str, false), "profile_disguise_days_", textView.getText(), (Method) null, str);
                        weakHashMap.put(textView, o0Var);
                        AbstractC0068r0.a(textView, true);
                        AbstractC0061p0.i(textView, o0Var);
                        AbstractC0061p0.k("bound " + str2);
                    }
                }
            }
        } catch (Throwable th) {
            AbstractC0061p0.j(str2 + " bind", th);
        }
        return objProceed;
    }
}
