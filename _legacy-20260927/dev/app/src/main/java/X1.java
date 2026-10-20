package dev.local.newsoul;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: X1.class */
public final /* synthetic */ class X1 implements BooleanSupplier {
    public final ArrayList a;
    public final List b;

    public /* synthetic */ X1(ArrayList arrayList, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = arrayList2;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        return !this.a.equals(this.b);
    }
}
