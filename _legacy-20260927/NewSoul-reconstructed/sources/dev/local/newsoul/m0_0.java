/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.TextView
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.widget.TextView;
import dev.local.newsoul.p0_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.m0
 */
public final class m0_0
implements XposedInterface.Hooker {
    public final boolean a;
    public final Method b;
    public final String c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public /* synthetic */ m0_0(boolean bl, Method method, String string, Field field, Field field2, Field field3, Field field4, Field field5, Field field6) {
        this.a = bl;
        this.b = method;
        this.c = string;
        this.d = field;
        this.e = field2;
        this.f = field3;
        this.g = field4;
        this.h = field5;
        this.i = field6;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        Throwable throwable2;
        Object object2;
        String string;
        block4: {
            Object object3;
            Object object4;
            Field field;
            Field field2;
            AccessibleObject accessibleObject;
            Object object5;
            Object object6;
            Field field3;
            block3: {
                Method method = this.b;
                string = this.c;
                field3 = this.d;
                object6 = this.e;
                object5 = this.f;
                accessibleObject = this.g;
                field2 = this.h;
                field = this.i;
                object2 = object.proceed();
                try {
                    object4 = object.getThisObject();
                    object3 = object.getArg(1);
                    if (object3 == null) return object2;
                    object = object3;
                    if (!this.a) break block3;
                }
                catch (Throwable throwable2) {
                    break block4;
                }
                object = method.invoke(object3, null);
            }
            int n = field3.getInt(object4);
            boolean bl = ((Field)object6).get(object4) != null;
            object3 = new StringBuilder();
            ((StringBuilder)object3).append(string);
            ((StringBuilder)object3).append(" type=");
            ((StringBuilder)object3).append(n);
            ((StringBuilder)object3).append("; count=");
            ((StringBuilder)object3).append(bl);
            p0_0.k(((StringBuilder)object3).toString());
            bl = field3.getInt(object4) == 0;
            object3 = ((Field)object6).get(object4);
            object5 = ((Field)object5).get(object);
            object6 = accessibleObject.get(object);
            accessibleObject = p0_0.l;
            p0_0.a((TextView)object5, object3, bl, "profile_disguise_follow_", p0_0.i, (Method)accessibleObject);
            p0_0.a((TextView)object6, object3, bl, "profile_disguise_followed_", p0_0.j, (Method)accessibleObject);
            p0_0.b(field2.get(object), field.get(object), object3, bl, p0_0.l);
            return object2;
        }
        object = new StringBuilder();
        ((StringBuilder)object).append(string);
        ((StringBuilder)object).append(" bind");
        p0_0.j(((StringBuilder)object).toString(), throwable2);
        return object2;
    }
}

