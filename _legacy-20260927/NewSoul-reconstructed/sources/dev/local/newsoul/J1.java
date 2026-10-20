/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.DialogInterface;
import dev.local.newsoul.w2;

public final class J1
implements DialogInterface.OnClickListener {
    public final Activity a;
    public final boolean b;

    public /* synthetic */ J1(Activity activity, boolean bl) {
        this.a = activity;
        this.b = bl;
    }

    public final void onClick(DialogInterface dialogInterface, int n) {
        w2.t(this.a, this.b, false, null);
    }
}

