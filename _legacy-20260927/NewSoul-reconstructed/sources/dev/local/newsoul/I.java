/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.Q;
import dev.local.newsoul.S;
import dev.local.newsoul.q2;

public final class I
implements View.OnClickListener {
    public final int a;
    public final q2 b;
    public final Q c;

    public /* synthetic */ I(q2 q22, Q q, int n) {
        this.a = n;
        this.b = q22;
        this.c = q;
    }

    public final void onClick(View view) {
        switch (this.a) {
            default: {
                S.c(this.b, this.c, true);
                return;
            }
            case 0: 
        }
        S.c(this.b, this.c, false);
    }
}

