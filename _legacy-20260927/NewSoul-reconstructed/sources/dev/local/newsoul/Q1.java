/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.util.Log
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 */
package dev.local.newsoul;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;
import dev.local.newsoul.L1;
import dev.local.newsoul.g2_0;
import dev.local.newsoul.h_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.m1_0;
import java.util.function.BooleanSupplier;

public final class Q1
implements CompoundButton.OnCheckedChangeListener {
    public final g2_0 a;
    public final BooleanSupplier b;
    public final Runnable c;
    public final SharedPreferences d;

    public /* synthetic */ Q1(g2_0 g2_02, L1 l1, m1_0 m1_02, SharedPreferences sharedPreferences) {
        this.a = g2_02;
        this.b = l1;
        this.c = m1_02;
        this.d = sharedPreferences;
    }

    public final void onCheckedChanged(CompoundButton object, boolean bl) {
        Object object2 = this.a;
        if (!((g2_0)object2).d) {
            if (!this.b.getAsBoolean()) {
                this.c.run();
            } else {
                object = this.d.edit();
                object2 = ((g2_0)object2).a;
                object.putBoolean((String)object2, bl).apply();
                j_0.i.post((Runnable)new h_0(0));
                object = new StringBuilder("setting changed: ");
                ((StringBuilder)object).append((String)object2);
                ((StringBuilder)object).append("=");
                ((StringBuilder)object).append(bl);
                Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
            }
        }
    }
}

