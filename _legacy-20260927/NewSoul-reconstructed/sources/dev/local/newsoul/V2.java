/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.util.Log
 *  android.view.View
 */
package dev.local.newsoul;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import dev.local.newsoul.H;
import dev.local.newsoul.b3;
import dev.local.newsoul.d3;
import dev.local.newsoul.q2;
import dev.local.newsoul.u2_0;
import dev.local.newsoul.w2;
import java.util.ArrayList;

public final class V2
implements Runnable {
    public final int a;
    public final b3 b;
    public final q2 c;

    public /* synthetic */ V2(b3 b32, q2 q22, int n) {
        this.a = n;
        this.b = b32;
        this.c = q22;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                q2 q22;
                q2 q23;
                b3 b32 = this.b;
                if (!b32.m && (q23 = b32.h) == (q22 = this.c)) {
                    try {
                        b32.j();
                    }
                    catch (Exception exception) {
                        b32.b();
                    }
                    q22.h.removeAllViews();
                    q23 = q22.h;
                    int n = q22.i;
                    q23.addView((View)w2.n(q22.g, "\u8bfb\u53d6\u8bed\u97f3\u5e93\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5", 14, n));
                    q23.addView((View)d3.a(q22, "\u91cd\u8bd5", new u2_0(3, b32)));
                }
                return;
            }
            case 0: 
        }
        q2 q24 = this.c;
        b3 b33 = this.b;
        d3 d32 = b33.q;
        try {
            ArrayList arrayList = b33.f.d();
            Handler handler = d32.a;
            H h = new H((Object)b33, (Object)q24, (Object)arrayList, 13);
            handler.post((Runnable)h);
        }
        catch (Throwable throwable) {
            Log.w((String)"SoulHook102", (String)"voice library read failed", (Throwable)throwable);
            d32.a.post((Runnable)new V2(b33, q24, 1));
        }
    }
}

