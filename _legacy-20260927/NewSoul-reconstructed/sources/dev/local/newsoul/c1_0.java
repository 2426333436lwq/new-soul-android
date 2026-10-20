/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 *  android.view.View
 */
package dev.local.newsoul;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import dev.local.newsoul.b3;
import dev.local.newsoul.d1_0;
import dev.local.newsoul.q2;

/*
 * Renamed from dev.local.newsoul.c1
 */
public final class c1_0
implements DialogInterface.OnDismissListener {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ c1_0(Object object, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
    }

    public final void onDismiss(DialogInterface object) {
        switch (this.a) {
            default: {
                object = (b3)this.b;
                if (((b3)object).h == (q2)this.c) {
                    ((b3)object).b();
                }
                return;
            }
            case 1: {
                object = (q2)this.b;
                ((q2)object).s = null;
                if (((q2)object).e.isShowing() && (object = (View)this.c) != null) {
                    object.requestFocus();
                }
                return;
            }
            case 0: 
        }
        object = (d1_0)this.b;
        if (((d1_0)object).t == (Dialog)this.c) {
            ((d1_0)object).t = null;
        }
    }
}

