/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.view.View
 */
package dev.local.newsoul;

import android.content.SharedPreferences;
import android.view.View;
import dev.local.newsoul.B2;
import dev.local.newsoul.C1;
import dev.local.newsoul.g2_0;
import dev.local.newsoul.h_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.m1_0;
import dev.local.newsoul.q2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntConsumer;

public final class P1
implements IntConsumer {
    public final int a;
    public final SharedPreferences b;
    public final Object c;
    public final Object d;
    public final Object e;

    public /* synthetic */ P1(SharedPreferences sharedPreferences, String[] stringArray, C1 c1, m1_0 m1_02) {
        this.a = 0;
        this.b = sharedPreferences;
        this.c = stringArray;
        this.d = c1;
        this.e = m1_02;
    }

    public /* synthetic */ P1(View view, q2 q22, SharedPreferences sharedPreferences, ArrayList arrayList) {
        this.a = 1;
        this.c = view;
        this.d = q22;
        this.b = sharedPreferences;
        this.e = arrayList;
    }

    @Override
    public final void accept(int n) {
        switch (this.a) {
            default: {
                View view = (View)this.c;
                if (view.isAttachedToWindow() && view.isShown()) {
                    q2 q22 = (q2)this.d;
                    if (!q22.a.isFinishing() && !q22.a.isDestroyed()) {
                        int n2 = 0;
                        boolean bl = n == 0;
                        view = this.b.edit();
                        Iterator iterator = ((List)this.e).iterator();
                        n = n2;
                        while (iterator.hasNext()) {
                            g2_0 g2_02 = (g2_0)iterator.next();
                            if (!g2_02.c.getAsBoolean()) continue;
                            view.putBoolean(g2_02.a, bl);
                            ++n;
                        }
                        if (n > 0) {
                            view.apply();
                            j_0.i.post((Runnable)new h_0(0));
                        }
                        q22.e();
                    }
                }
                return;
            }
            case 0: 
        }
        Object object = this.b.edit();
        String[] stringArray = (String[])this.c;
        object.putString("feed_filter_gender", stringArray[n]).apply();
        object = stringArray[n];
        ((C1)((Object)this.d)).b((String)object, stringArray, true);
        ((Runnable)this.e).run();
        B2.d();
    }
}

