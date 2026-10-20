/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.Z;

public final class B0
implements View.OnClickListener {
    public final int[] a;
    public final int b;
    public final Runnable c;

    public /* synthetic */ B0(int[] nArray, int n, Z z) {
        this.a = nArray;
        this.b = n;
        this.c = z;
    }

    public final void onClick(View view) {
        this.a[0] = this.b;
        this.c.run();
        view.performHapticFeedback(4);
    }
}

