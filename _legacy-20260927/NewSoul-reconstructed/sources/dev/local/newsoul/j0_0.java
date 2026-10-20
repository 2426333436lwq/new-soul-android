/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 *  android.widget.ImageView
 */
package dev.local.newsoul;

import android.content.DialogInterface;
import android.widget.ImageView;

/*
 * Renamed from dev.local.newsoul.j0
 */
public final class j0_0
implements DialogInterface.OnDismissListener {
    public final int a;
    public final Object b;

    public /* synthetic */ j0_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final void onDismiss(DialogInterface object) {
        switch (this.a) {
            default: {
                object = ((Thread[])this.b)[0];
                if (object != null) {
                    ((Thread)object).interrupt();
                }
                return;
            }
            case 1: {
                ((Runnable)this.b).run();
                return;
            }
            case 0: 
        }
        ((ImageView)this.b).setImageDrawable(null);
    }
}

