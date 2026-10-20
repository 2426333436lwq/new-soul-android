/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.view.View;

/*
 * Renamed from dev.local.newsoul.i0
 */
public final class i0_0
implements View.OnClickListener {
    public final int a;
    public final Runnable b;

    public /* synthetic */ i0_0(Runnable runnable, int n) {
        this.a = n;
        this.b = runnable;
    }

    public final void onClick(View view) {
        switch (this.a) {
            default: {
                this.b.run();
                return;
            }
            case 2: {
                this.b.run();
                return;
            }
            case 1: {
                this.b.run();
                return;
            }
            case 0: 
        }
        this.b.run();
    }
}

