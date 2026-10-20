/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import dev.local.newsoul.SoulHook;
import io.github.libxposed.api.XposedInterface;

/*
 * Renamed from dev.local.newsoul.x2
 */
public final class x2_0
implements XposedInterface.Hooker {
    public final int a;
    public final SoulHook b;

    public /* synthetic */ x2_0(SoulHook soulHook, int n) {
        this.a = n;
        this.b = soulHook;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            default: {
                return SoulHook.b(this.b, chain);
            }
            case 0: 
        }
        return SoulHook.c(this.b, chain);
    }
}

