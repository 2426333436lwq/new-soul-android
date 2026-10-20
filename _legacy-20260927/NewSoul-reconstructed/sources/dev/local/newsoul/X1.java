/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public final class X1
implements BooleanSupplier {
    public final ArrayList a;
    public final List b;

    public /* synthetic */ X1(ArrayList arrayList, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = arrayList2;
    }

    @Override
    public final boolean getAsBoolean() {
        return this.a.equals(this.b) ^ true;
    }
}

