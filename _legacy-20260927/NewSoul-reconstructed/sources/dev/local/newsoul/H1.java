/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.B2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.t_0;
import java.util.function.BooleanSupplier;

public final class H1
implements BooleanSupplier {
    public final int a;

    public /* synthetic */ H1(int n) {
        this.a = n;
    }

    @Override
    public final boolean getAsBoolean() {
        switch (this.a) {
            default: {
                boolean bl = Q0.U && Q0.V;
                return bl;
            }
            case 19: {
                return Q0.f;
            }
            case 18: {
                return Q0.e;
            }
            case 17: {
                return g1_0.a;
            }
            case 16: {
                return Q0.k;
            }
            case 15: {
                return t_0.b;
            }
            case 14: {
                boolean bl = Q0.Q != Q0.R;
                return bl;
            }
            case 13: {
                boolean bl = Q0.Q || Q0.R;
                return bl;
            }
            case 12: {
                return Q0.P;
            }
            case 11: {
                return Q0.P;
            }
            case 10: {
                return Q0.c;
            }
            case 9: {
                return Q0.b;
            }
            case 8: {
                return t_0.a;
            }
            case 7: {
                return Q0.L;
            }
            case 6: {
                boolean bl = Q0.Y && Q0.B;
                return bl;
            }
            case 5: {
                return Q0.Y;
            }
            case 4: {
                boolean bl = Q0.Y && Q0.B;
                return bl;
            }
            case 3: {
                boolean bl = B2.a && B2.i;
                return bl;
            }
            case 2: {
                return Q0.O;
            }
            case 1: {
                return Q0.M;
            }
            case 0: 
        }
        return B2.i;
    }
}

