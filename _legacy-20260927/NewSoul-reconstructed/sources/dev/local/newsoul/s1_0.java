/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.util.Log
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.util.Log;
import dev.local.newsoul.m1_0;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
 * Renamed from dev.local.newsoul.s1
 */
public final class s1_0
implements XposedInterface.Hooker {
    public final Field a;
    public final Field b;
    public final Field c;
    public final Constructor d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public /* synthetic */ s1_0(Field field, Field field2, Field field3, Constructor constructor, Field field4, Field field5, Field field6, Field field7, Field field8) {
        this.a = field;
        this.b = field2;
        this.c = field3;
        this.d = constructor;
        this.e = field4;
        this.f = field5;
        this.g = field6;
        this.h = field7;
        this.i = field8;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        Activity activity;
        Object object2;
        Object object3;
        Object object4;
        block10: {
            block13: {
                Object object5;
                Object e;
                Field field;
                ArrayList<Object> arrayList;
                Field field2;
                Object object6;
                Object object7;
                block12: {
                    Object object8;
                    block11: {
                        object4 = this.a;
                        object3 = this.b;
                        object7 = this.c;
                        object6 = this.d;
                        field2 = this.e;
                        arrayList = this.f;
                        field = this.g;
                        if (!u1_0.f) {
                            return object.proceed();
                        }
                        object2 = object.getThisObject();
                        activity = null;
                        e = null;
                        try {
                            object5 = (Activity)u1_0.c(object2, "getActivity");
                            object8 = ((Field)object4).get(object2);
                            object4 = e;
                            if (object5 == null) break block10;
                            object4 = e;
                            if (!"cn.soulapp.android.component.home.user.UserHomeActivity".equals(object5.getClass().getName())) break block10;
                            object4 = e;
                            if (object8 == null) break block10;
                            object4 = e;
                            if (((Field)object3).get(object2) != null) break block10;
                            object4 = e;
                            if (((Field)object7).getInt(object2) != 2) break block10;
                            object7 = (String)u1_0.f(object8.getClass(), "userIdEcpt").get(object8);
                            object4 = (String)u1_0.f(object8.getClass(), "alias").get(object8);
                            if (object4 == null) break block11;
                            object3 = object4;
                            if (!((String)object4).trim().isEmpty()) break block12;
                        }
                        catch (Exception exception) {
                            object4 = activity;
                            break block13;
                        }
                    }
                    object3 = (String)u1_0.f(object8.getClass(), "signature").get(object8);
                }
                object4 = e;
                if (object7 == null) break block10;
                object4 = e;
                if (((String)object7).trim().isEmpty()) break block10;
                object4 = e;
                if (object3 == null) break block10;
                object4 = e;
                if (((String)object3).trim().isEmpty()) break block10;
                int n = object5.getResources().getIdentifier("icon_share_collect", "drawable", object5.getPackageName());
                object4 = e;
                if (n == 0) break block10;
                object4 = u1_0.d();
                e = ((Constructor)object6).newInstance(0, "\u63d2\u773c\u2026", Boolean.FALSE);
                field2.setInt(e, 5133072);
                ((Field)((Object)arrayList)).setInt(e, n);
                object6 = (List)field.get(object2);
                arrayList = new ArrayList<Object>();
                if (object6 != null) {
                    object5 = object6.iterator();
                    while (object5.hasNext()) {
                        object6 = object5.next();
                        if (field2.getInt(object6) == 5133072) continue;
                        arrayList.add(object6);
                    }
                }
                arrayList.add(0, e);
                field.set(object2, arrayList);
                object4 = new t1_0((String)object4, (String)object7, (String)object3, e);
                try {
                    u1_0.c.put(object2, object4);
                    break block10;
                }
                catch (Exception exception) {}
            }
            Log.w((String)"SoulHook102", (String)"profile bookmark menu unavailable", (Throwable)object3);
        }
        object3 = object.proceed();
        object = object3;
        if (object4 == null) return object;
        activity = (Activity)u1_0.c(object2, "getActivity");
        object = object3;
        if (activity == null) return object;
        object = new m1_0(object4, activity, object2, this.h, this.i, 0);
        u1_0.b.execute((Runnable)object);
        return object3;
    }
}

