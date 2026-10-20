/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.DialogInterface;
import dev.local.newsoul.P;
import dev.local.newsoul.S;

public final class M
implements DialogInterface.OnDismissListener {
    public final boolean[] a;
    public final Activity b;
    public final P c;

    public /* synthetic */ M(boolean[] blArray, Activity activity, P p) {
        this.a = blArray;
        this.b = activity;
        this.c = p;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (!this.a[0]) {
            S.b(this.b, this.c, "\u5df2\u53d6\u6d88\u5bfc\u5165");
        }
    }
}

