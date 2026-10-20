/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.r2_0;

public final class S1
implements View.OnClickListener {
    public final int a;
    public final r2_0 b;

    public /* synthetic */ S1(r2_0 r2_02, int n) {
        this.a = n;
        this.b = r2_02;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                object = this.b;
                if (object.isEnabled()) {
                    ((r2_0)((Object)object)).toggle();
                }
                return;
            }
            case 0: 
        }
        object = this.b;
        if (object.isEnabled()) {
            ((r2_0)((Object)object)).toggle();
        }
    }
}

