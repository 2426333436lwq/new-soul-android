/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.SystemClock
 *  android.util.Log
 *  android.widget.LinearLayout
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.LinearLayout;
import dev.local.newsoul.H;
import dev.local.newsoul.P0;
import dev.local.newsoul.Q0;
import dev.local.newsoul.Y1;
import dev.local.newsoul.q2;
import java.util.ArrayList;

public final class T1
implements Runnable {
    public final Activity a;
    public final q2 b;
    public final LinearLayout c;
    public final boolean d;
    public final String e;
    public final long f;
    public final boolean g;

    public /* synthetic */ T1(Activity activity, q2 q22, LinearLayout linearLayout, boolean bl, String string, long l, boolean bl2) {
        this.a = activity;
        this.b = q22;
        this.c = linearLayout;
        this.d = bl;
        this.e = string;
        this.f = l;
        this.g = bl2;
    }

    @Override
    public final void run() {
        boolean bl;
        P0 p0;
        q2 q22;
        Activity activity;
        block2: {
            int n;
            activity = this.a;
            q22 = this.b;
            try {
                p0 = Q0.U((Context)activity);
                bl = this.d;
                if (!bl) break block2;
                n = p0.d;
            }
            catch (Throwable throwable) {
                Log.e((String)"SoulHook102", (String)"Hook diagnostics failed", (Throwable)throwable);
                activity.runOnUiThread((Runnable)new H((Object)q22, activity, (Object)this.c, 11));
            }
            ArrayList arrayList = (ArrayList)p0.c;
            p0 = new P0(p0.a, this.e, arrayList, n);
        }
        long l = Math.max(0L, 450L - (SystemClock.elapsedRealtime() - this.f));
        activity.getWindow().getDecorView().postDelayed((Runnable)new Y1(q22, activity, this.g, p0, bl), l);
    }
}

