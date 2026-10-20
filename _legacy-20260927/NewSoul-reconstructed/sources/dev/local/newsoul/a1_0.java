/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnClickListener
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import dev.local.newsoul.C0;
import dev.local.newsoul.V0;
import dev.local.newsoul.d1_0;
import dev.local.newsoul.e1_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/*
 * Renamed from dev.local.newsoul.a1
 */
public final class a1_0
implements XposedInterface.Hooker {
    public final int a;
    public final d1_0 b;

    public /* synthetic */ a1_0(d1_0 d1_02, int n) {
        this.a = n;
        this.b = d1_02;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2;
                d1_0 d1_02 = this.b;
                d1_02.getClass();
                Activity activity = (Activity)object.getThisObject();
                if (d1_02.a.isInstance(activity) && d1_0.f(activity) && !activity.isChangingConfigurations() && (object2 = d1_02.r) != null && ((String)object2).equals(activity.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                    d1_02.s = null;
                    d1_02.r = null;
                }
                if ((object2 = d1_02.t) == null) return object.proceed();
                if (object2.getContext() != activity) return object.proceed();
                d1_02.t.dismiss();
                d1_02.t = null;
                return object.proceed();
            }
            case 4: {
                this.b.getClass();
                if (!d1_0.f((Activity)object.getThisObject())) return object.proceed();
                return null;
            }
            case 3: {
                d1_0 d1_03 = this.b;
                d1_03.getClass();
                Activity activity = (Activity)object.getThisObject();
                if (!d1_0.f(activity)) {
                    return object.proceed();
                }
                try {
                    d1_03.b(activity);
                    return null;
                }
                catch (Throwable throwable) {
                    d1_0.c(activity, throwable);
                }
                return null;
            }
            case 2: {
                d1_0 d1_04 = this.b;
                d1_04.getClass();
                Object object3 = object.proceed();
                Activity activity = (Activity)object.getThisObject();
                if (!d1_0.f(activity)) return object3;
                try {
                    if (d1_04.i(activity) == null) {
                        activity.finish();
                        return object3;
                    }
                    object = d1_0.d(activity, "b");
                    object.getClass().getMethod("k", Boolean.TYPE).invoke(object, Boolean.TRUE);
                    ClassLoader classLoader = d1_04.c.getClassLoader();
                    Class clazz = d1_04.c;
                    Object object4 = new V0(d1_04, activity);
                    object4 = Proxy.newProxyInstance(classLoader, new Class[]{clazz}, (InvocationHandler)object4);
                    d1_04.e.invoke(object, object4);
                    for (int i = 0; i < 3; ++i) {
                        object = (new String[]{"ic_user_follow", "tv_mutily_choose", "tv_share"})[i];
                        int n = activity.getResources().getIdentifier((String)object, "id", activity.getPackageName());
                        object = n == 0 ? null : activity.findViewById(n);
                        if (object == null) continue;
                        object.setVisibility(8);
                    }
                    object = (View)d1_0.d(activity, "e");
                    object.setVisibility(0);
                    object4 = new C0(d1_04, activity, 1);
                    object.setOnClickListener((View.OnClickListener)object4);
                    d1_0.n(activity);
                    return object3;
                }
                catch (Throwable throwable) {}
                d1_0.c(activity, throwable);
                activity.finish();
                return object3;
            }
            case 1: {
                d1_0 d1_05 = this.b;
                d1_05.getClass();
                Object[] objectArray = (Object[])object.getThisObject();
                if (!d1_0.f((Activity)objectArray)) {
                    return object.proceed();
                }
                ArrayList arrayList = new ArrayList();
                try {
                    e1_0 e1_02 = d1_05.i((Activity)objectArray);
                    if (e1_02 != null && object.getArg(1) instanceof List) {
                        for (Object e : (List)object.getArg(1)) {
                            Object object5 = d1_0.d(e, "conversation");
                            if (d1_0.d(e, "user") == null || object5 == null || !e1_02.c((String)d1_0.a(object5, "getToUserId"), (Integer)d1_0.a(object5, "getChatType"))) continue;
                            arrayList.add(e);
                        }
                    }
                }
                catch (Throwable throwable) {
                    d1_0.c((Activity)objectArray, throwable);
                }
                objectArray = object.getArgs().toArray();
                objectArray[1] = arrayList;
                return object.proceed(objectArray);
            }
            case 0: 
        }
        d1_0 d1_06 = this.b;
        d1_06.getClass();
        Activity activity = (Activity)object.getThisObject();
        if (!d1_0.f(activity)) {
            return object.proceed();
        }
        try {
            d1_06.m(activity, (Integer)object.getArg(0));
            return null;
        }
        catch (Throwable throwable) {
            d1_0.c(activity, throwable);
        }
        return null;
    }
}

