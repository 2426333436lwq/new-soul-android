/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.Q0;
import dev.local.newsoul.j_0;
import java.util.function.BooleanSupplier;

public final class L1
implements BooleanSupplier {
    public final String[] a;

    public /* synthetic */ L1(String[] stringArray) {
        this.a = stringArray;
    }

    @Override
    public final boolean getAsBoolean() {
        boolean bl;
        String[] stringArray = this.a;
        boolean bl2 = false;
        if ("block_splash".equals(stringArray[0])) {
            bl = bl2;
            if (Q0.W) {
                bl = bl2;
                if (Q0.X) {
                    bl = true;
                }
            }
        } else {
            bl = j_0.b.contains(stringArray[0]);
        }
        return bl;
    }
}

