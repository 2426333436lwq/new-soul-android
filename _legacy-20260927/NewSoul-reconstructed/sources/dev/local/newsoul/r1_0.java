/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.widget.TextView;
import dev.local.newsoul.o1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w1_0;
import java.util.function.IntConsumer;

/*
 * Renamed from dev.local.newsoul.r1
 */
public final class r1_0
implements IntConsumer {
    public final TextView a;
    public final String b;
    public final Activity c;
    public final w1_0 d;
    public final q2 e;
    public final Runnable[] f;

    public /* synthetic */ r1_0(TextView textView, String string, Activity activity, w1_0 w1_02, q2 q22, Runnable[] runnableArray) {
        this.a = textView;
        this.b = string;
        this.c = activity;
        this.d = w1_02;
        this.e = q22;
        this.f = runnableArray;
    }

    @Override
    public final void accept(int n) {
        Object object = this.a;
        object.setEnabled(false);
        object = new o1_0(this.b, this.c, this.d, this.e, this.f, object, 1);
        u1_0.b.execute((Runnable)object);
    }
}

