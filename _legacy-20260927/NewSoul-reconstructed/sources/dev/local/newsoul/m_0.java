/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import dev.local.newsoul.SoulHook;
import dev.local.newsoul.o_0;
import dev.local.newsoul.t_0;

/*
 * Renamed from dev.local.newsoul.m
 */
public final class m_0
implements Runnable {
    public final int a;
    public final Activity b;

    public /* synthetic */ m_0(Activity activity, int n) {
        this.a = n;
        this.b = activity;
    }

    @Override
    public final void run() {
        switch (this.a) {
            default: {
                SoulHook.a(this.b);
                return;
            }
            case 4: {
                Toast.makeText((Context)this.b, (CharSequence)"\u63d0\u793a\u9884\u89c8\uff1aNew Soul \u5df2\u751f\u6548", (int)0).show();
                Log.i((String)"SoulHook102", (String)"settings preview clicked");
                return;
            }
            case 3: {
                t_0.b(this.b, null, false);
                return;
            }
            case 2: {
                t_0.b(this.b, null, true);
                return;
            }
            case 1: {
                t_0.d.remove(this.b);
                return;
            }
            case 0: 
        }
        o_0.a(this.b);
    }
}

