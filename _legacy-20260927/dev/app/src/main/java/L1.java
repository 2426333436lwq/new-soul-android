package dev.local.newsoul;

import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: L1.class */
public final /* synthetic */ class L1 implements BooleanSupplier {
    public final String[] a;

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        boolean zContains;
        String[] strArr = this.a;
        if ("block_splash".equals(strArr[0])) {
            zContains = false;
            if (Q0.W) {
                zContains = false;
                if (Q0.X) {
                    zContains = true;
                }
            }
        } else {
            zContains = AbstractC0036j.b.contains(strArr[0]);
        }
        return zContains;
    }


    public L1(java.lang.String[] p0) {
        super();
        this.a = p0;
    }
}
