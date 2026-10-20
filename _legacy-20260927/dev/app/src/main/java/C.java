package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/* 本文件合并了仅大小写不同的两个类 C / c（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: C.class */
public final /* synthetic */ class C implements XposedInterface.Hooker {
    public final int a;
    public final F b;

    public /* synthetic */ C(F f, int i) {
        this.a = i;
        this.b = f;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object objProceed;
        Object objA;
        switch (this.a) {
            case 0:
                F f = this.b;
                f.getClass();
                Object arg = chain.getArg(7);
                if (arg != null) {
                    int iIntValue = ((Integer) f.c.invoke(arg, null)).intValue();
                    if (iIntValue == 5133057 || iIntValue == 5133058 || iIntValue == 5133059) {
                        Q0.r("repeat.action.enter", "action=" + iIntValue);
                        E e = (E) f.a.get(arg);
                        if (e != null && !e.e) {
                            e.e = true;
                            Object obj = e.b;
                            try {
                                if (iIntValue == 5133059) {
                                    f.k(e);
                                    f.n.h(e.a, F.d(F.b(obj, "getChatMessage")));
                                } else if (iIntValue == 5133058) {
                                    Activity activityK = f.k(e);
                                    Object objB = F.b(obj, "getChatMessage");
                                    f.m.g(activityK, e.c, e.d, ((Integer) F.b(objB, "getMsgType")).intValue(), F.d(objB));
                                } else {
                                    f.j(e);
                                }
                            } catch (Throwable th) {
                                Q0.s("repeat.action.failed", th);
                                Log.w("SoulHook102", "repeat failed; no automatic retry");
                                Toast.makeText((Context) chain.getArg(2), iIntValue == 5133059 ? "无法保存语音，请重新选择" : iIntValue == 5133058 ? "无法打开转发，请重新选择" : "复读未完成，请检查消息状态", 0).show();
                            }
                        }
                        objProceed = null;
                    } else {
                        objProceed = chain.proceed();
                    }
                    return objProceed;
                } else {
                    objProceed = chain.proceed();
                }
                return objProceed;
            default:
                F C0023f2 = this.b;
                C0023f2.getClass();
                C0095y yVarA = V.a() ? A.a(false, (Object) null) : null;
                if (yVarA != null) {
                    try {
                        yVarA.a.b++;
                    } catch (Throwable th2) {
                        if (yVarA != null) {
                            try {
                                yVarA.close();
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
                Q0.r("repeat.menu.enter", "hook_hit=true; " + Q0.O(chain.getArg(2)) + (yVarA == null ? "" : "; source=" + Q0.X()));
                try {
                    Object objProceed2 = chain.proceed();
                    if (yVarA != null) {
                        A.b(objProceed2);
                    }
                    try {
                        objA = C0023f2.a(objProceed2, chain.getArg(0), chain.getArg(2));
                        A.b(objA);
                        if (yVarA != null) {
                            yVarA.close();
                        }
                    } catch (Throwable th4) {
                        Q0.s("repeat.menu.skipped", th4);
                        Log.w("SoulHook102", "repeat menu skipped");
                        objA = objProceed2;
                        if (yVarA != null) {
                            yVarA.close();
                            objA = objProceed2;
                        }
                    }
                    return objA;
                } catch (Throwable th5) {
                    Q0.s("repeat.menu.host_failed", th5);
                    throw th5;
                }
        }
    }
}

/* JADX INFO: loaded from: c.class */
final /* synthetic */ class c implements XposedInterface.Hooker {
    public final Class a;
    public final int b;
    public final boolean c;
    public final Class d;
    public final Class e;
    public final Method f;
    public final boolean g;

    public /* synthetic */ c(Class cls, int i, boolean z, Class cls2, Class cls3, Method method, boolean z2) {
        this.a = cls;
        this.b = i;
        this.c = z;
        this.d = cls2;
        this.e = cls3;
        this.f = method;
        this.g = z2;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x01fc  */
    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object objProceed;
        Class cls = this.a;
        int i = this.b;
        boolean z = this.c;
        Class cls2 = this.d;
        Class cls3 = this.e;
        Method method = this.f;
        boolean z2 = this.g;
        if (cls.isInstance(chain.getThisObject())) {
            Context context = AbstractC0036j.h;
            Context context2 = context;
            if (context == null) {
                try {
                    Object objInvoke = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", null).invoke(null, null);
                    context2 = context;
                    if (objInvoke instanceof Context) {
                        Context context3 = (Context) objInvoke;
                        if (context3 != null) {
                            Context applicationContext = context3.getApplicationContext();
                            if (applicationContext != null) {
                                context3 = applicationContext;
                            }
                            AbstractC0036j.h = context3;
                        }
                        context2 = AbstractC0036j.h;
                    }
                } catch (Throwable th) {
                    context2 = context;
                }
            }
            if (context2 == null || !AbstractC0036j.b(context2, "ad_feed")) {
                objProceed = chain.proceed();
            } else {
                Object arg = chain.getArg(i);
                if (z && (arg instanceof Collection)) {
                    Collection collection = (Collection) arg;
                    ArrayList arrayList = new ArrayList(collection.size());
                    int C0035i2 = 0;
                    for (Object obj : collection) {
                        if (obj != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 < 2) {
                                    Class cls4 = new Class[]{cls2, cls3}[i3];
                                    if (cls4 != null && cls4.isInstance(obj)) {
                                        C0035i2++;
                                        break;
                                    }
                                    i3++;
                                }
                            }
                        }
                        arrayList.add(obj);
                    }
                    if (C0035i2 == 0) {
                        objProceed = chain.proceed();
                    } else {
                        Object[] array = chain.getArgs().toArray();
                        array[i] = arrayList;
                        AbstractC0036j.e("feed ad models filtered=" + C0035i2 + " via " + method.getName());
                        objProceed = chain.proceed(array);
                    }
                } else if (!z2 || arg == null) {
                    objProceed = chain.proceed();
                } else {
                    for (int i4 = 0; i4 < 2; i4++) {
                        Class cls5 = new Class[]{cls2, cls3}[i4];
                        if (cls5 != null && cls5.isInstance(arg)) {
                            AbstractC0036j.e("feed ad model skipped via " + method.getName());
                            objProceed = null;
                        }
                    }
                    objProceed = chain.proceed();
                }
            }
        } else {
            objProceed = chain.proceed();
        }
        return objProceed;
    }
}
