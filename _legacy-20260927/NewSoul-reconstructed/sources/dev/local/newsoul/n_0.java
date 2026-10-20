/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import dev.local.newsoul.E2;
import dev.local.newsoul.I2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.o_0;
import dev.local.newsoul.w2;

/*
 * Renamed from dev.local.newsoul.n
 */
public final class n_0
implements View.OnLongClickListener {
    public final int a;
    public final Object b;

    public /* synthetic */ n_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final boolean onLongClick(View object) {
        boolean bl;
        switch (this.a) {
            default: {
                boolean bl2 = Q0.B;
                I2 i2 = (I2)this.b;
                Activity activity = i2.a;
                if (!bl2) {
                    Toast.makeText((Context)activity, (CharSequence)"\u5f53\u524d\u7248\u672c\u81ea\u52a8\u70b9\u8d5e\u672a\u5c31\u7eea", (int)0).show();
                } else {
                    bl2 = i2.b() ^ true;
                    w2.o((Context)activity).edit().putBoolean("square_auto_like", bl2).apply();
                    i2.e(false);
                    int n = i2.k().size();
                    E2 e2 = i2.c;
                    e2.a(n, false);
                    object = i2.u;
                    e2.removeCallbacks((Runnable)object);
                    if (i2.l) {
                        e2.post((Runnable)object);
                    }
                    object = bl2 ? "\u81ea\u52a8\u70b9\u8d5e\u5df2\u5f00\u542f" : "\u81ea\u52a8\u70b9\u8d5e\u5df2\u5173\u95ed";
                    Toast.makeText((Context)activity, (CharSequence)object, (int)0).show();
                    object = new StringBuilder("square auto like toggled by long press: enabled=");
                    ((StringBuilder)object).append(bl2);
                    Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
                }
                return true;
            }
            case 0: 
        }
        Activity activity = (Activity)this.b;
        if (!activity.isFinishing() && !activity.isDestroyed() && object.isShown()) {
            w2.u(activity, o_0.b(activity));
            bl = true;
        } else {
            bl = false;
        }
        return bl;
    }
}

