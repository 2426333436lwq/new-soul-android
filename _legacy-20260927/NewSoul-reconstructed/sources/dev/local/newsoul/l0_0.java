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
 * Renamed from dev.local.newsoul.l0
 */
public final class l0_0
implements XposedInterface.Hooker {
    public final Field a;
    public final Field b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;

    public /* synthetic */ l0_0(Field field, Field field2, Field field3, Field field4, Field field5, Field field6, Field field7) {
        this.a = field;
        this.b = field2;
        this.c = field3;
        this.d = field4;
        this.e = field5;
        this.f = field6;
        this.g = field7;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        Object object = this.a;
        AccessibleObject accessibleObject = this.b;
        Object object2 = this.c;
        Field field = this.d;
        Field field2 = this.e;
        Field field3 = this.f;
        Field field4 = this.g;
        Object object3 = chain.proceed();
        Object object4 = chain.getThisObject();
        int n = ((Field)object).getInt(object4);
        Object object5 = new StringBuilder("header type=");
        ((StringBuilder)object5).append(n);
        p0_0.k(((StringBuilder)object5).toString());
        boolean bl = ((Field)object).getInt(object4) == 0;
        try {
            object = accessibleObject.get(object4);
            object2 = ((Field)object2).get(object4);
            object5 = chain.getArg(0);
            accessibleObject = p0_0.m;
            p0_0.a((TextView)object, object5, bl, "profile_disguise_follow_", p0_0.i, (Method)accessibleObject);
            p0_0.a((TextView)object2, object5, bl, "profile_disguise_followed_", p0_0.j, (Method)accessibleObject);
            p0_0.b(field.get(object4), field2.get(object4), chain.getArg(0), bl, p0_0.m);
            p0_0.b(field3.get(object4), field4.get(object4), chain.getArg(0), bl, p0_0.m);
        }
        catch (Throwable throwable) {
            p0_0.j("header bind", throwable);
        }
        return object3;
    }
}

