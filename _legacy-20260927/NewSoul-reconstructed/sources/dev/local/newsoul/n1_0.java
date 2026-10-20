/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.D0;
import dev.local.newsoul.q1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w1_0;
import dev.local.newsoul.w2;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.n1
 */
public final class n1_0
implements Runnable {
    public final int a;
    public final Activity b;
    public final boolean c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ n1_0(Activity activity, t1_0 t1_02, Object object, boolean bl, Field field, Field field2) {
        this.a = 0;
        this.b = activity;
        this.d = t1_02;
        this.e = object;
        this.c = bl;
        this.f = field;
        this.g = field2;
    }

    public /* synthetic */ n1_0(q2 q22, String string, ArrayList arrayList, Activity activity, boolean bl, Runnable[] runnableArray) {
        this.a = 1;
        this.d = q22;
        this.e = string;
        this.f = arrayList;
        this.b = activity;
        this.c = bl;
        this.g = runnableArray;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        Exception exception4;
        Activity activity;
        block14: {
            boolean bl;
            switch (this.a) {
                default: {
                    boolean bl2;
                    q2 q22 = (q2)this.d;
                    if (!u1_0.i(q22)) {
                        return;
                    }
                    LinearLayout linearLayout = q22.h;
                    linearLayout.removeAllViews();
                    String string = (String)this.e;
                    try {
                        bl2 = string.equals(u1_0.d());
                    }
                    catch (Exception exception2) {
                        bl2 = false;
                    }
                    int n = q22.j;
                    Object object = q22.g;
                    if (!bl2) {
                        linearLayout.addView((View)w2.n((Context)object, "\u8d26\u53f7\u5df2\u5207\u6362\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u5217\u8868", 14, n));
                        return;
                    }
                    List list = (List)this.f;
                    if (list.isEmpty()) {
                        linearLayout.addView((View)w2.n((Context)object, "\u6682\u65e0\u63d2\u773c", 14, n));
                    }
                    object = list.iterator();
                    while (object.hasNext()) {
                        w1_0 w1_02 = (w1_0)object.next();
                        list = u1_0.a(q22, w1_02.b);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(w1_02.b);
                        stringBuilder.append("\uff0c\u6253\u5f00\u4e3b\u9875\uff1b\u957f\u6309\u79fb\u9664\u63d2\u773c");
                        list.setContentDescription(stringBuilder.toString());
                        stringBuilder = this.b;
                        list.setOnClickListener(new D0((Serializable)((Object)string), w1_02, q22, stringBuilder, 1));
                        list.setOnLongClickListener(new q1_0((Activity)stringBuilder, this.c, (TextView)list, string, w1_02, q22, (Runnable[])this.g));
                        w2.b(q22, linearLayout, (TextView)list);
                    }
                    return;
                }
                case 0: 
            }
            boolean bl3 = this.c;
            Field field = (Field)this.f;
            Field field2 = (Field)this.g;
            activity = this.b;
            if (!u1_0.b(activity)) return;
            Object object = (t1_0)this.d;
            Object object2 = ((t1_0)object).a;
            try {
                bl = ((String)object2).equals(u1_0.d());
            }
            catch (Exception exception3) {
                bl = false;
            }
            if (!bl) return;
            Object object3 = u1_0.c;
            object2 = this.e;
            if (((WeakHashMap)object3).get(object2) != object) {
                return;
            }
            try {
                ((t1_0)object).f = bl3;
                ((t1_0)object).e = true;
                object3 = ((t1_0)object).d;
                object = bl3 ? "\u53d6\u6d88\u63d2\u773c" : "\u63d2\u773c";
            }
            catch (Exception exception4) {
                break block14;
            }
            field.set(object3, object);
            object = field2.get(object2);
            if (object == null) return;
            u1_0.c(object, "notifyDataSetChanged");
            return;
        }
        u1_0.e(activity, exception4);
    }
}

