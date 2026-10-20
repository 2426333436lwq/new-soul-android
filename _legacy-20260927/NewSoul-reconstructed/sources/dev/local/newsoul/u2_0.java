/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 */
package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import dev.local.newsoul.M0;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.d3;

/*
 * Renamed from dev.local.newsoul.U2
 */
public final class u2_0
implements c3 {
    public final int a;
    public final Object b;

    public /* synthetic */ u2_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public void a(Canvas canvas) {
        M0.a((M0)((Object)this.b), canvas);
    }

    @Override
    public void run() {
        switch (this.a) {
            default: {
                ((b3)this.b).f();
                return;
            }
            case 2: {
                b3 b32 = (b3)this.b;
                d3.i((Context)b32.c, "\u5df2\u4fdd\u5b58\u5230\u8bed\u97f3\u5e93");
                b32.f();
                return;
            }
            case 1: {
                ((b3)this.b).b();
                return;
            }
            case 0: 
        }
        ((b3)this.b).f();
    }
}

