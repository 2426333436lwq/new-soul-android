/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.Log
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.TextView
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dev.local.newsoul.Q0;
import dev.local.newsoul.j1_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.v1_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.e
 */
public final class e_0
implements XposedInterface.Hooker {
    public final int a;
    public final AccessibleObject b;
    public final Object c;
    public final AnnotatedElement d;

    public /* synthetic */ e_0(AnnotatedElement annotatedElement, AccessibleObject accessibleObject, Object object, int n) {
        this.a = n;
        this.d = annotatedElement;
        this.b = accessibleObject;
        this.c = object;
    }

    public /* synthetic */ e_0(Method method, Method method2, Method method3) {
        this.a = 1;
        this.b = method;
        this.c = method2;
        this.d = method3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2 = object.getThisObject();
                Object object3 = ((Field)this.d).get(object2);
                if (!(object3 instanceof Context)) {
                    return object.proceed();
                }
                if (!w2.o((Context)object3).getBoolean("block_splash", true)) {
                    Log.i((String)"SoulHook102", (String)"new soul: splash filter OFF; original startup continues");
                    return object.proceed();
                }
                ((Method)this.b).invoke(object2, null);
                Log.i((String)"SoulHook102", (String)"new soul: splash blocked; MainScheduler.v -> s");
                ((XposedModule)this.c).log(4, "SoulHook102", "new soul: splash blocked; MainScheduler.v -> s");
                return null;
            }
            case 2: {
                Field field = (Field)this.d;
                Object object4 = (Field)this.b;
                Field field2 = (Field)this.c;
                Object object5 = object.proceed();
                try {
                    field = (ImageView)field.get(object.getThisObject());
                    boolean bl = false;
                    if (object4.getInt(object.getArg(0)) == 5133072) {
                        bl = true;
                    }
                    if (bl && field.getDrawable() instanceof BitmapDrawable) {
                        object = (TextView)field2.get(object.getThisObject());
                        object4 = new v1_0((BitmapDrawable)field.getDrawable(), object.getCurrentTextColor());
                        field.setImageDrawable((Drawable)object4);
                    }
                    j1_0.b((View)field, bl);
                    return object5;
                }
                catch (Exception exception) {}
                Log.w((String)"SoulHook102", (String)"bookmark icon binding failed", (Throwable)exception);
                return object5;
            }
            case 1: {
                Object object6 = object.getArg(0);
                if (object6 == null) return object.proceed();
                if (!Q0.q("chat_hide_typing")) return object.proceed();
                Object object7 = (Method)this.b;
                Object var4_9 = null;
                if ((Integer)((Method)object7).invoke(object6, null) != 1) return object.proceed();
                object7 = ((Method)this.c).invoke(object6, null);
                if (object7 == null) return object.proceed();
                int n = (Integer)((Method)this.d).invoke(object7, null);
                if (n != 22) {
                    if (n != 23) return object.proceed();
                }
                object = new StringBuilder("typing command blocked; type=");
                ((StringBuilder)object).append(n);
                Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
                return var4_9;
            }
            case 0: 
        }
        Object object8 = object.proceed();
        object = object.getThisObject();
        if (!((Class)this.d).isInstance(object)) return object8;
        Object object9 = ((Method)this.b).invoke(object, null);
        if (!(object9 instanceof Context)) return object8;
        if (!j_0.b((Context)object9, "ad_interstitial")) return object8;
        ((Method)this.c).invoke(object, null);
        j_0.e("ad dialog dismissed");
        return object8;
    }
}

