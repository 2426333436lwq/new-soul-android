/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.E2;
import dev.local.newsoul.I2;

public final class F2
implements Runnable {
    public final int a;
    public final I2 b;

    public /* synthetic */ F2(I2 i2, int n) {
        this.a = n;
        this.b = i2;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                I2 i2 = this.b;
                if (i2.m) {
                    i2.d("\u70b9\u8d5e\u54cd\u5e94\u8d85\u65f6\uff0c\u5df2\u505c\u6b62");
                }
                return;
            }
            case 1: {
                this.b.f();
                return;
            }
            case 0: 
        }
        I2 i2 = this.b;
        if (i2.l) {
            View view = i2.g();
            i2.m();
            boolean bl = i2.m;
            E2 e2 = i2.c;
            if (!bl) {
                int n = view == null ? 0 : i2.k().size();
                e2.a(n, false);
                if (n > 0 && i2.b() && i2.t.isEmpty()) {
                    i2.l(true);
                }
            }
            e2.postDelayed(i2.u, 700L);
        }
    }
}

