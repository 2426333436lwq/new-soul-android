/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  android.view.View
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import dev.local.newsoul.Q0;
import dev.local.newsoul.j_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.f
 */
public final class f_0
implements XposedInterface.Hooker {
    public final int a;
    public final Object b;

    public /* synthetic */ f_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2;
                object = object2 = object.proceed();
                if (Boolean.TRUE.equals(object2)) {
                    object = object2;
                    if (Q0.q("chat_remove_social_score_limit")) {
                        object2 = ((Method)this.b).getName();
                        object = new StringBuilder("social score gate bypassed: ");
                        ((StringBuilder)object).append((String)object2);
                        Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
                        object = Boolean.FALSE;
                    }
                }
                return object;
            }
            case 1: {
                if (!Q0.R((Activity)object.getThisObject())) {
                    object = object.proceed();
                } else {
                    object = new StringBuilder("privacy repeat blocked consume: route=");
                    ((StringBuilder)object).append((String)this.b);
                    Q0.w(((StringBuilder)object).toString());
                    object = null;
                }
                return object;
            }
            case 0: 
        }
        Object object3 = object.proceed();
        View view = (View)object.getThisObject();
        object = view.getContext();
        if (object != null) {
            Context context = object.getApplicationContext();
            if (context != null) {
                object = context;
            }
            j_0.h = object;
        }
        j_0.i(view, (String)this.b);
        return object3;
    }
}

