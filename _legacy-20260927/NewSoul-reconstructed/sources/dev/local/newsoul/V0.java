/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package dev.local.newsoul;

import android.app.Activity;
import dev.local.newsoul.d1_0;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class V0
implements InvocationHandler {
    public final int a;
    public final Activity b;
    public final Object c;

    public /* synthetic */ V0(Activity activity, Runnable runnable) {
        this.a = 0;
        this.b = activity;
        this.c = runnable;
    }

    public /* synthetic */ V0(d1_0 d1_02, Activity activity) {
        this.a = 1;
        this.c = d1_02;
        this.b = activity;
    }

    @Override
    public final Object invoke(Object object, Method method, Object[] objectArray) {
        switch (this.a) {
            default: {
                Activity activity = this.b;
                d1_0 d1_02 = (d1_0)this.c;
                d1_02.getClass();
                Class<?> clazz = method.getDeclaringClass();
                boolean bl = false;
                if (clazz == Object.class) {
                    if (method.getName().equals("hashCode")) {
                        object = System.identityHashCode(object);
                    } else if (method.getName().equals("equals")) {
                        if (object == objectArray[0]) {
                            bl = true;
                        }
                        object = bl;
                    } else {
                        object = "NewSoulForwardRecipients";
                    }
                } else {
                    if (method.getName().equals("onItemClick")) {
                        try {
                            d1_02.m(activity, (Integer)objectArray[0]);
                        }
                        catch (Throwable throwable) {
                            d1_0.c(activity, throwable);
                        }
                    }
                    object = null;
                }
                return object;
            }
            case 0: 
        }
        boolean bl = method.getName().equals("accept");
        Object object2 = null;
        if (bl) {
            this.b.runOnUiThread((Runnable)this.c);
        } else if (method.getName().equals("toString")) {
            object2 = "NewSoulLoadedLikeCallback";
        } else if (method.getName().equals("hashCode")) {
            object2 = System.identityHashCode(object);
        } else if (method.getName().equals("equals")) {
            bl = false;
            if (object == objectArray[0]) {
                bl = true;
            }
            object2 = bl;
        }
        return object2;
    }
}

