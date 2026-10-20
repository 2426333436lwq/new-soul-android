/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.os.Handler
 *  android.os.Looper
 */
package dev.local.newsoul;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import dev.local.newsoul.C1;
import dev.local.newsoul.Q0;
import dev.local.newsoul.h_0;
import java.util.function.IntConsumer;

public final class R1
implements IntConsumer {
    public final SharedPreferences a;
    public final String[] b;
    public final C1 c;

    public /* synthetic */ R1(SharedPreferences sharedPreferences, String[] stringArray, C1 c1) {
        this.a = sharedPreferences;
        this.b = stringArray;
        this.c = c1;
    }

    @Override
    public final void accept(int n) {
        Object object = this.a.edit();
        String[] stringArray = this.b;
        object.putString("chat_recall_style", stringArray[n]).apply();
        object = stringArray[n];
        this.c.b((String)object, stringArray, true);
        if (Q0.y != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new h_0(1));
        }
    }
}

