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
import dev.local.newsoul.Q0;
import dev.local.newsoul.o0_0;
import dev.local.newsoul.p0_0;
import dev.local.newsoul.r0_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.n0
 */
public final class n0_0
implements XposedInterface.Hooker {
    public final boolean a;
    public final Method b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final String f;

    public /* synthetic */ n0_0(boolean bl, Method method, Field field, Field field2, Field field3, String string) {
        this.a = bl;
        this.b = method;
        this.c = field;
        this.d = field2;
        this.e = field3;
        this.f = string;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        Throwable throwable2;
        Object object2;
        Object object3;
        String string;
        block4: {
            Object object4;
            Object object5;
            Field field;
            Object object6;
            Field field2;
            block3: {
                Method method = this.b;
                field2 = this.c;
                object6 = this.d;
                field = this.e;
                string = this.f;
                object3 = object.proceed();
                try {
                    object5 = object.getArg(1);
                    object4 = object.getArg(0);
                    if (object5 == null) return object3;
                    object2 = object5;
                    if (!this.a) break block3;
                }
                catch (Throwable throwable2) {
                    break block4;
                }
                object2 = method.invoke(object5, null);
            }
            if ((object5 = (TextView)field2.get(object2)) == null) return object3;
            object2 = p0_0.f;
            ((WeakHashMap)object2).remove(object5);
            r0_0.a((TextView)object5, false);
            if (((Field)object6).getInt(object.getThisObject()) != 0) return object3;
            if (object4 == null) return object3;
            object = (String)field.get(object4);
            if (object == null) return object3;
            if (!object5.getText().toString().contains((CharSequence)object)) return object3;
            if (((String)object).isEmpty()) return object3;
            int n = Q0.P((String)object, false);
            object6 = new o0_0(n, "profile_disguise_days_", object5.getText(), null, (String)object);
            ((WeakHashMap)object2).put(object5, object6);
            r0_0.a((TextView)object5, true);
            p0_0.i((TextView)object5, (o0_0)object6);
            object = new StringBuilder("bound ");
            ((StringBuilder)object).append(string);
            p0_0.k(((StringBuilder)object).toString());
            return object3;
        }
        object2 = new StringBuilder();
        ((StringBuilder)object2).append(string);
        ((StringBuilder)object2).append(" bind");
        p0_0.j(((StringBuilder)object2).toString(), throwable2);
        return object3;
    }
}

