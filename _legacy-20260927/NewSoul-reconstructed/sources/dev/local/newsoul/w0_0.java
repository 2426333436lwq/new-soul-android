/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.C1;
import dev.local.newsoul.y0_0;
import java.util.function.IntConsumer;

/*
 * Renamed from dev.local.newsoul.w0
 */
public final class w0_0
implements IntConsumer {
    public final y0_0 a;
    public final C1 b;

    public /* synthetic */ w0_0(y0_0 y0_02, C1 c1) {
        this.a = y0_02;
        this.b = c1;
    }

    @Override
    public final void accept(int n) {
        y0_0 y0_02 = this.a;
        if (n != y0_02.g && y0_02.e()) {
            y0_02.g = n;
            String[] stringArray = y0_0.h;
            String string = stringArray[n];
            this.b.b(string, stringArray, true);
            y0_02.d();
        }
    }
}

