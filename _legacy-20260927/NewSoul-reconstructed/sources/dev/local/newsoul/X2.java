/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 */
package dev.local.newsoul;

import android.content.DialogInterface;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.q2;

public final class X2
implements DialogInterface.OnCancelListener {
    public final b3 a;
    public final q2 b;
    public final c3 c;

    public /* synthetic */ X2(b3 b32, q2 q22, c3 c32) {
        this.a = b32;
        this.b = q22;
        this.c = c32;
    }

    public final void onCancel(DialogInterface object) {
        c3 c32 = this.c;
        object = this.a;
        if (!((b3)object).m && ((b3)object).h == this.b) {
            if (((b3)object).n) {
                ((b3)object).c();
            } else {
                try {
                    ((b3)object).j();
                    c32.run();
                }
                catch (Throwable throwable) {
                    ((b3)object).b();
                }
            }
        }
    }
}

