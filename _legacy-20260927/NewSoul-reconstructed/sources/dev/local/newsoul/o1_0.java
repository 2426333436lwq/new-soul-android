/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Handler
 *  android.widget.Button
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import dev.local.newsoul.H;
import dev.local.newsoul.M2;
import dev.local.newsoul.T0;
import dev.local.newsoul.Y;
import dev.local.newsoul.g_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w1_0;
import dev.local.newsoul.x1_0;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.o1
 */
public final class o1_0
implements Runnable {
    public final int a;
    public final Activity b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ o1_0(Activity activity, q2 q22, Button button, TextView textView, String string, String[] stringArray) {
        this.a = 2;
        this.b = activity;
        this.c = q22;
        this.d = button;
        this.e = textView;
        this.f = string;
        this.g = stringArray;
    }

    public /* synthetic */ o1_0(Object object, Activity activity, Object object2, Object object3, Object object4, Object object5, int n) {
        this.a = n;
        this.c = object;
        this.b = activity;
        this.d = object2;
        this.e = object3;
        this.f = object4;
        this.g = object5;
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
                String string;
                Y y;
                try {
                    y = T0.b();
                    string = null;
                }
                catch (Exception exception) {
                    y = null;
                    string = "\u68c0\u6d4b\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\u6216\u6253\u5f00\u53d1\u5e03\u9875\u3002";
                }
                String[] stringArray = (String[])this.g;
                Button button = (Button)((TextView)this.d);
                Activity activity = this.b;
                activity.runOnUiThread((Runnable)new M2(activity, (q2)this.c, button, string, (TextView)this.e, y, (String)this.f, stringArray));
                return;
            }
            case 1: {
                String string = (String)this.c;
                Activity activity = this.b;
                w1_0 w1_02 = (w1_0)this.d;
                q2 q22 = (q2)this.e;
                Runnable[] runnableArray = (Runnable[])this.f;
                Handler handler = u1_0.a;
                try {
                    u1_0.h(string);
                    Object object = new x1_0(activity.getFilesDir(), string, 0);
                    ((x1_0)object).i(w1_02.a);
                    object = new g_0(q22, runnableArray, 5);
                    handler.post((Runnable)object);
                    return;
                }
                catch (Exception exception) {
                    handler.post((Runnable)new H((Object)((TextView)this.g), activity, (Object)exception, 9));
                }
                return;
            }
            case 0: 
        }
        Field field = (Field)this.e;
        Field field2 = (Field)this.f;
        Object object = (t1_0)this.c;
        ((t1_0)object).h = true;
        Activity activity = this.b;
        if (!u1_0.b(activity)) return;
        WeakHashMap weakHashMap = u1_0.c;
        Object object2 = this.d;
        if (weakHashMap.get(object2) != object) {
            return;
        }
        try {
            field.set(((t1_0)object).d, "\u63d2\u773c\u4e0d\u53ef\u7528");
            object = field2.get(object2);
            if (object != null) {
                u1_0.c(object, "notifyDataSetChanged");
            }
        }
        catch (Exception exception) {}
        u1_0.e(activity, (Exception)this.g);
    }
}

