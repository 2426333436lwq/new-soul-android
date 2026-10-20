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
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;

public final class O1
implements View.OnClickListener {
    public final q2 a;
    public final Activity b;
    public final boolean c;
    public final boolean d;
    public final String e;

    public /* synthetic */ O1(q2 q22, Activity activity, boolean bl, boolean bl2, String string) {
        this.a = q22;
        this.b = activity;
        this.c = bl;
        this.d = bl2;
        this.e = string;
    }

    public final void onClick(View view) {
        this.a.e.dismiss();
        w2.t(this.b, this.c, this.d, this.e);
    }
}

