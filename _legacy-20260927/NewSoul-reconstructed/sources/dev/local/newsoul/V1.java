/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.h2;

public final class V1
implements Runnable {
    public final int a;
    public final h2 b;

    public /* synthetic */ V1(h2 h22, int n) {
        this.a = n;
        this.b = h22;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                this.b.b();
                return;
            }
            case 0: 
        }
        this.b.a();
    }
}

