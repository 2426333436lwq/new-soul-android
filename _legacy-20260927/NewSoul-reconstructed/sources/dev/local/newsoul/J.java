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

public final class J
implements Runnable {
    public final int a;
    public final Activity b;
    public final P c;

    public /* synthetic */ J(Activity activity, P p, int n) {
        this.a = n;
        this.b = activity;
        this.c = p;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                P p = this.c;
                String string = p.c != null ? "\u5bfc\u51fa\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u4fdd\u5b58\u4f4d\u7f6e" : "\u5bfc\u5165\u5931\u8d25\uff1a\u6587\u4ef6\u65e0\u6cd5\u8bfb\u53d6\u3001\u8fc7\u5927\u6216\u683c\u5f0f\u65e0\u6548\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9";
                S.b(this.b, p, string);
                return;
            }
            case 0: 
        }
        S.b(this.b, this.c, "\u914d\u7f6e\u5df2\u5bfc\u51fa");
    }
}

