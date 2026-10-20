/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.f0_0;

/*
 * Renamed from dev.local.newsoul.d0
 */
public final class d0_0
implements Runnable {
    public final int a;
    public final f0_0 b;

    public /* synthetic */ d0_0(f0_0 f0_02, int n) {
        this.a = n;
        this.b = f0_02;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                f0_0 f0_02 = this.b;
                f0_02.h = false;
                f0_02.d.setVisibility(8);
                f0_02.b();
                return;
            }
            case 0: 
        }
        f0_0 f0_03 = this.b;
        f0_03.h = false;
        f0_03.b();
    }
}

