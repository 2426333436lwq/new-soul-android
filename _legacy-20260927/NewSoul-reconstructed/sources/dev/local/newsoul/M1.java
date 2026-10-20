/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver$OnPreDrawListener
 */
package dev.local.newsoul;

import android.view.ViewTreeObserver;
import dev.local.newsoul.I2;

public final class M1
implements ViewTreeObserver.OnPreDrawListener {
    public final int a;
    public final Object b;

    public /* synthetic */ M1(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final boolean onPreDraw() {
        switch (this.a) {
            default: {
                ((I2)this.b).m();
                return true;
            }
            case 0: 
        }
        ((Runnable)this.b).run();
        return true;
    }
}

