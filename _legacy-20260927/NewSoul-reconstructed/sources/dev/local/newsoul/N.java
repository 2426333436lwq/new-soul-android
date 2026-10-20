/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import dev.local.newsoul.K;
import dev.local.newsoul.P;
import dev.local.newsoul.Q;
import dev.local.newsoul.S;
import dev.local.newsoul.q2;
import java.util.Map;

public final class N
implements View.OnClickListener {
    public final boolean[] a;
    public final P b;
    public final q2 c;
    public final Activity d;
    public final Map e;

    public /* synthetic */ N(boolean[] blArray, P p, q2 q22, Activity activity, Map map) {
        this.a = blArray;
        this.b = p;
        this.c = q22;
        this.d = activity;
        this.e = map;
    }

    public final void onClick(View object) {
        object = this.a;
        if (object[0] == false) {
            object[0] = (View)true;
            P p = this.b;
            object = (Q)p.e.get();
            if (object != null) {
                ((Q)object).a("\u6b63\u5728\u5bfc\u5165\u914d\u7f6e\u2026", true);
            }
            this.c.e.dismiss();
            object = new K(this.d, this.e, p);
            S.c.execute((Runnable)object);
        }
    }
}

