/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.view.KeyEvent$Callback
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.Toast;
import dev.local.newsoul.r;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.u1_0;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.q
 */
public final class q_0
implements Runnable {
    public final int a;
    public final KeyEvent.Callback b;
    public final boolean c;
    public final Object d;
    public final Object e;

    public /* synthetic */ q_0(Activity activity, boolean bl, Method method, Object object) {
        this.a = 0;
        this.b = activity;
        this.c = bl;
        this.e = method;
        this.d = object;
    }

    public /* synthetic */ q_0(r2_0 r2_02, SharedPreferences sharedPreferences, String string, boolean bl) {
        this.a = 2;
        this.b = r2_02;
        this.e = sharedPreferences;
        this.d = string;
        this.c = bl;
    }

    public /* synthetic */ q_0(t1_0 t1_02, Activity activity, boolean bl, Object object) {
        this.a = 1;
        this.e = t1_02;
        this.b = activity;
        this.c = bl;
        this.d = object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                boolean bl = ((SharedPreferences)this.e).getBoolean((String)this.d, this.c);
                r2_0 r2_02 = (r2_0)this.b;
                r2_02.b = true;
                try {
                    r2_02.setChecked(bl);
                    return;
                }
                finally {
                    r2_02.b = false;
                }
            }
            case 1: {
                boolean bl;
                Object object = this.d;
                Object object2 = (t1_0)this.e;
                ((t1_0)object2).g = false;
                Activity activity = (Activity)this.b;
                if (!u1_0.b(activity)) return;
                object2 = ((t1_0)object2).a;
                try {
                    bl = ((String)object2).equals(u1_0.d());
                }
                catch (Exception exception) {
                    return;
                }
                if (!bl) {
                    return;
                }
                object2 = this.c ? "\u5df2\u53d6\u6d88\u63d2\u773c" : "\u5df2\u63d2\u773c";
                if (u1_0.b(activity)) {
                    Toast.makeText((Context)activity, (CharSequence)object2, (int)0).show();
                }
                try {
                    u1_0.c(object, "dismissAllowingStateLoss");
                    return;
                }
                catch (Exception exception) {
                    return;
                }
            }
            case 0: 
        }
        Activity activity = (Activity)this.b;
        t_0.b(activity, new r(activity, (Method)this.e, this.d), this.c);
    }
}

