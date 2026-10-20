package dev.local.newsoul;

import android.app.Activity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V0 implements InvocationHandler {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ Object c;

    public /* synthetic */ V0(Activity activity, Runnable runnable) {
        this.b = activity;
        this.c = runnable;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        switch (this.a) {
            case 0:
                if (method.getName().equals("accept")) {
                    this.b.runOnUiThread((Runnable) this.c);
                    return null;
                }
                if (method.getName().equals("toString")) {
                    return "NewSoulLoadedLikeCallback";
                }
                if (method.getName().equals("hashCode")) {
                    return Integer.valueOf(System.identityHashCode(obj));
                }
                if (method.getName().equals("equals")) {
                    return Boolean.valueOf(obj == objArr[0]);
                }
                return null;
            default:
                Activity activity = this.b;
                C0014d1 c0014d1 = (C0014d1) this.c;
                c0014d1.getClass();
                if (method.getDeclaringClass() == Object.class) {
                    if (method.getName().equals("hashCode")) {
                        return Integer.valueOf(System.identityHashCode(obj));
                    }
                    if (method.getName().equals("equals")) {
                        return Boolean.valueOf(obj == objArr[0]);
                    }
                    return "NewSoulForwardRecipients";
                }
                if (method.getName().equals("onItemClick")) {
                    try {
                        c0014d1.m(activity, ((Integer) objArr[0]).intValue());
                    } catch (Throwable th) {
                        C0014d1.c(activity, th);
                    }
                    break;
                }
                return null;
        }
    }

    public /* synthetic */ V0(C0014d1 c0014d1, Activity activity) {
        this.c = c0014d1;
        this.b = activity;
    }
}
