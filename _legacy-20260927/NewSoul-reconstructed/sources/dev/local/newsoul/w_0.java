/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.w1_0;
import dev.local.newsoul.x_0;
import java.util.function.Predicate;

/*
 * Renamed from dev.local.newsoul.w
 */
public final class w_0
implements Predicate {
    public final int a;
    public final Object b;

    public /* synthetic */ w_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final boolean test(Object object) {
        switch (this.a) {
            default: {
                return ((w1_0)object).a.equals((String)this.b);
            }
            case 0: 
        }
        object = (x_0)object;
        boolean bl = ((x_0)object).a.get() == null || ((x_0)object).a.get() == this.b;
        return bl;
    }
}

