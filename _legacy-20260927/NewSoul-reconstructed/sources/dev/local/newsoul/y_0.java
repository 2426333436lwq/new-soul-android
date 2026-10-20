/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.A;
import dev.local.newsoul.z_0;

/*
 * Renamed from dev.local.newsoul.y
 */
public final class y_0
implements AutoCloseable {
    public final z_0 a;
    public final z_0 b;
    public boolean c;

    public y_0(z_0 z_02, z_0 z_03) {
        this.a = z_02;
        this.b = z_03;
    }

    @Override
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        z_0 z_02 = this.b;
        if (z_02 == null) {
            A.b.remove();
        } else {
            A.b.set(z_02);
        }
    }
}

