/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package dev.local.newsoul;

import android.util.Log;
import dev.local.newsoul.Q0;
import dev.local.newsoul.W0;
import dev.local.newsoul.h2_0;

public final class U0
implements Runnable {
    public final int a;
    public final W0 b;
    public final h2_0 c;

    public /* synthetic */ U0(W0 w0, h2_0 h2_02, int n) {
        this.a = n;
        this.b = w0;
        this.c = h2_02;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                W0 w0 = this.b;
                w0.getClass();
                try {
                    Q0.K.invoke(w0.b, null);
                }
                catch (ReflectiveOperationException reflectiveOperationException) {
                    Log.w((String)"SoulHook102", (String)"loaded like UI refresh failed", (Throwable)reflectiveOperationException);
                }
                this.c.b(false);
                return;
            }
            case 0: 
        }
        W0 w0 = this.b;
        w0.getClass();
        try {
            Q0.K.invoke(w0.b, null);
        }
        catch (ReflectiveOperationException reflectiveOperationException) {
            Log.w((String)"SoulHook102", (String)"loaded like UI refresh failed", (Throwable)reflectiveOperationException);
        }
        this.c.b(true);
    }
}

