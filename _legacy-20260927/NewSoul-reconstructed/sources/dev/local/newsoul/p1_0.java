/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package dev.local.newsoul;

import android.app.Activity;
import dev.local.newsoul.H;
import dev.local.newsoul.n1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.x1_0;
import java.util.ArrayList;

/*
 * Renamed from dev.local.newsoul.p1
 */
public final class p1_0
implements Runnable {
    public final int a;
    public final String b;
    public final Activity c;
    public final q2 d;
    public final boolean e;
    public final Runnable[] f;

    public /* synthetic */ p1_0(String string, Activity activity, q2 q22, boolean bl, Runnable[] runnableArray, int n) {
        this.a = n;
        this.b = string;
        this.c = activity;
        this.d = q22;
        this.e = bl;
        this.f = runnableArray;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                Object object;
                Object object2;
                String string = this.b;
                Activity activity = this.c;
                q2 q22 = this.d;
                boolean bl = this.e;
                Runnable[] runnableArray = this.f;
                try {
                    u1_0.h(string);
                    object2 = new x1_0(activity.getFilesDir(), string, 0);
                    object = x1_0.b;
                    synchronized (object) {
                        object2 = ((x1_0)object2).h();
                    }
                }
                catch (Exception exception) {
                    u1_0.a.post((Runnable)new H((Object)q22, (Object)runnableArray, (Object)exception, 8));
                    return;
                }
                {
                    object = u1_0.a;
                    n1_0 n1_02 = new n1_0(q22, string, (ArrayList)object2, activity, bl, runnableArray);
                    object.post((Runnable)n1_02);
                    return;
                }
            }
            case 0: 
        }
        p1_0 p1_02 = new p1_0(this.b, this.c, this.d, this.e, this.f, 1);
        u1_0.b.execute(p1_02);
    }
}

