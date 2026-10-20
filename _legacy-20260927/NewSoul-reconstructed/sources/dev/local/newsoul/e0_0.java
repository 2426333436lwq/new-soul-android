/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;

/*
 * Renamed from dev.local.newsoul.e0
 */
public final class e0_0
implements Runnable {
    public final f0_0 a;

    public e0_0(f0_0 f0_02) {
        this.a = f0_02;
    }

    @Override
    public final void run() {
        f0_0 f0_02 = this.a;
        if (!f0_02.a.e.isShowing()) {
            return;
        }
        f0_02.b();
        g0_0.b.postDelayed((Runnable)this, 1000L);
    }
}

