/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import dev.local.newsoul.q2;
import dev.local.newsoul.r1_0;
import dev.local.newsoul.w1_0;
import java.util.function.IntConsumer;

/*
 * Renamed from dev.local.newsoul.q1
 */
public final class q1_0
implements View.OnLongClickListener {
    public final Activity a;
    public final boolean b;
    public final TextView c;
    public final String d;
    public final w1_0 e;
    public final q2 f;
    public final Runnable[] g;

    public /* synthetic */ q1_0(Activity activity, boolean bl, TextView textView, String string, w1_0 w1_02, q2 q22, Runnable[] runnableArray) {
        this.a = activity;
        this.b = bl;
        this.c = textView;
        this.d = string;
        this.e = w1_02;
        this.f = q22;
        this.g = runnableArray;
    }

    public final boolean onLongClick(View view) {
        Object object = this.a;
        q2 q22 = new q2((Activity)object, this.b);
        view = this.c;
        object = new r1_0((TextView)view, this.d, (Activity)object, this.e, this.f, this.g);
        q22.h(view, new String[]{"\u79fb\u9664\u63d2\u773c"}, true, (IntConsumer)object);
        return true;
    }
}

