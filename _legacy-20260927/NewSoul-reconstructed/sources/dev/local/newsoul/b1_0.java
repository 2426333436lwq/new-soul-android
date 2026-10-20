/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.os.SystemClock
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.SystemClock;
import dev.local.newsoul.V;
import dev.local.newsoul.d1_0;
import dev.local.newsoul.e1_0;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/*
 * Renamed from dev.local.newsoul.b1
 */
public final class b1_0
implements InvocationHandler {
    public final d1_0 a;
    public final Activity b;
    public final String c;
    public final e1_0 d;
    public final LinkedHashMap e;
    public final List f;
    public final Dialog g;

    public /* synthetic */ b1_0(d1_0 d1_02, Activity activity, String string, e1_0 e1_02, LinkedHashMap linkedHashMap, ArrayList arrayList, Dialog dialog) {
        this.a = d1_02;
        this.b = activity;
        this.c = string;
        this.d = e1_02;
        this.e = linkedHashMap;
        this.f = arrayList;
        this.g = dialog;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final Object invoke(Object object, Method object2, Object[] object3) {
        Throwable throwable2;
        int n;
        int n2;
        Dialog dialog;
        List list;
        Activity activity;
        block15: {
            block12: {
                block13: {
                    activity = this.b;
                    String string = this.c;
                    LinkedHashMap linkedHashMap = this.e;
                    list = this.f;
                    dialog = this.g;
                    d1_0 d1_02 = this.a;
                    d1_02.getClass();
                    Class<?> clazz = ((Method)object2).getDeclaringClass();
                    int n3 = 0;
                    n2 = 0;
                    boolean bl = false;
                    if (clazz == Object.class) {
                        if (((Method)object2).getName().equals("hashCode")) {
                            return System.identityHashCode(object);
                        }
                        if (!((Method)object2).getName().equals("equals")) {
                            return "NewSoulMessageForwardConfirm";
                        }
                        if (object != object3[0]) return bl;
                        bl = true;
                        return bl;
                    }
                    if (!((Method)object2).getName().equals("onClickConfirm")) return null;
                    n = n2;
                    try {
                        block14: {
                            if (activity.isFinishing()) break block12;
                            n = n2;
                            if (activity.isDestroyed()) break block12;
                            n = n2;
                            if (!string.equals(d1_02.r)) break block12;
                            n = n2;
                            object2 = d1_02.i(activity);
                            if (object2 != (object = this.d)) break block12;
                            n = n2;
                            if (!d1_0.h(activity, (e1_0)object).keySet().equals(linkedHashMap.keySet())) break block12;
                            n = n2;
                            object2 = (String)d1_02.g.invoke(null, null);
                            n = n2;
                            long l = SystemClock.elapsedRealtime();
                            n = n2;
                            if ((object2 = ((e1_0)object).a((String)object2, (ArrayList)list, l)) == null) break block13;
                            n = n2;
                            object3 = ((ArrayList)object2).iterator();
                            n2 = n3;
                            while (true) {
                                n = n2;
                                if (!object3.hasNext()) break block14;
                                n = n2;
                                object2 = (String)object3.next();
                                n = n2;
                                if (!((e1_0)object).a.equals(d1_02.g.invoke(null, null))) break;
                                n = n2++;
                                d1_02.k((e1_0)object, (String)object2);
                            }
                            n = n2;
                            n = n2;
                            object = new IllegalStateException("Account changed");
                            n = n2;
                            throw object;
                        }
                        n = n2;
                        n3 = ((e1_0)object).c;
                        n = n2;
                        n = n2;
                        object = new StringBuilder();
                        n = n2;
                        ((StringBuilder)object).append("type=");
                        n = n2;
                        ((StringBuilder)object).append(n3);
                        n = n2;
                        ((StringBuilder)object).append("; targets=");
                        n = n2;
                        ((StringBuilder)object).append(n2);
                        n = n2;
                        V.d("message.forward.dispatched", ((StringBuilder)object).toString());
                        n = n2;
                        n = n2;
                        object = new StringBuilder();
                        n = n2;
                        ((StringBuilder)object).append("\u5df2\u63d0\u4ea4\u8f6c\u53d1\u7ed9 ");
                        n = n2;
                        ((StringBuilder)object).append(n2);
                        n = n2;
                        ((StringBuilder)object).append(" \u4eba\uff0c\u8bf7\u5728\u76ee\u6807\u4f1a\u8bdd\u67e5\u770b");
                        n = n2;
                        d1_0.l((Context)activity, ((StringBuilder)object).toString());
                        return null;
                    }
                    catch (Throwable throwable2) {}
                }
                n = n2;
                n = n2;
                object = new IllegalStateException("Expired confirmation");
                n = n2;
                throw object;
                break block15;
            }
            n = n2;
            n = n2;
            object = new IllegalStateException("Selection changed");
            n = n2;
            throw object;
        }
        try {
            n2 = list.size();
            object = new StringBuilder();
            ((StringBuilder)object).append("submitted=");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append("; total=");
            ((StringBuilder)object).append(n2);
            object = ((StringBuilder)object).toString();
            object3 = V.c(throwable2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String)object);
            stringBuilder.append("\n");
            stringBuilder.append((String)object3);
            V.d("message.forward.failed", stringBuilder.toString());
            n2 = list.size();
            object = new StringBuilder();
            ((StringBuilder)object).append("\u8f6c\u53d1\u4e2d\u65ad\uff0c\u5df2\u63d0\u4ea4 ");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append("/");
            ((StringBuilder)object).append(n2);
            ((StringBuilder)object).append("\uff0c\u8bf7\u5148\u6838\u5bf9\u76ee\u6807\u4f1a\u8bdd");
            d1_0.l((Context)activity, ((StringBuilder)object).toString());
            return null;
        }
        finally {
            dialog.dismiss();
            activity.finish();
        }
    }
}

