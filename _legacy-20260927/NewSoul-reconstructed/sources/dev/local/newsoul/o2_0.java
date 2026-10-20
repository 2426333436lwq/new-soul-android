/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package dev.local.newsoul;

import android.content.DialogInterface;
import dev.local.newsoul.q2;

/*
 * Renamed from dev.local.newsoul.o2
 */
public final class o2_0
implements DialogInterface.OnClickListener {
    public final q2 a;

    public /* synthetic */ o2_0(q2 q22) {
        this.a = q22;
    }

    public final void onClick(DialogInterface dialogInterface, int n) {
        this.a.e.dismiss();
    }
}

