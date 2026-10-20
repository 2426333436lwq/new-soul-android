/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnKeyListener
 *  android.view.KeyEvent
 */
package dev.local.newsoul;

import android.content.DialogInterface;
import android.view.KeyEvent;
import dev.local.newsoul.q2;

/*
 * Renamed from dev.local.newsoul.n2
 */
public final class n2_0
implements DialogInterface.OnKeyListener {
    public final q2 a;

    public /* synthetic */ n2_0(q2 q22) {
        this.a = q22;
    }

    public final boolean onKey(DialogInterface object, int n, KeyEvent keyEvent) {
        boolean bl;
        object = this.a;
        object.getClass();
        if (n != 4 && n != 111) {
            bl = false;
        } else {
            if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                ((q2)object).b();
            }
            bl = true;
        }
        return bl;
    }
}

