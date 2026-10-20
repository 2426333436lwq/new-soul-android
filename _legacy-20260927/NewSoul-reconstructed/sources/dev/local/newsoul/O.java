/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package dev.local.newsoul;

import android.app.Activity;
import dev.local.newsoul.P;
import dev.local.newsoul.S;
import dev.local.newsoul.p0_0;
import dev.local.newsoul.q2;

public final class O
implements Runnable {
    public final P a;
    public final boolean b;
    public final Activity c;

    public /* synthetic */ O(P p, boolean bl, Activity activity) {
        this.a = p;
        this.b = bl;
        this.c = activity;
    }

    @Override
    public final void run() {
        P p = this.a;
        Object object = (q2)p.d.get();
        boolean bl = this.b;
        Activity activity = this.c;
        if (bl) {
            if (object != null && S.d(activity, p)) {
                ((q2)object).e();
            }
            p0_0.h();
        }
        object = bl ? "\u914d\u7f6e\u5df2\u5bfc\u5165\n\u90e8\u5206\u529f\u80fd\u91cd\u542f Soul \u540e\u751f\u6548" : "\u914d\u7f6e\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u5bfc\u5165";
        S.b(activity, p, (String)object);
    }
}

