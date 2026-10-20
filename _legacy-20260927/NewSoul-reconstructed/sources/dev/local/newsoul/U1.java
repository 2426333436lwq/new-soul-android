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
import dev.local.newsoul.B2;
import dev.local.newsoul.h_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.r2_0;

public final class U1
implements CompoundButton.OnCheckedChangeListener {
    public final SharedPreferences a;
    public final r2_0 b;
    public final String c;

    public /* synthetic */ U1(SharedPreferences sharedPreferences, r2_0 r2_02, String string) {
        this.a = sharedPreferences;
        this.b = r2_02;
        this.c = string;
    }

    public final void onCheckedChanged(CompoundButton object, boolean bl) {
        object = this.a;
        if (object != null && !this.b.b) {
            Object object2 = object.edit();
            object = this.c;
            object2.putBoolean((String)object, bl).apply();
            j_0.i.post((Runnable)new h_0(0));
            B2.d();
            object2 = new StringBuilder("setting changed: ");
            ((StringBuilder)object2).append((String)object);
            ((StringBuilder)object2).append("=");
            ((StringBuilder)object2).append(bl);
            Log.i((String)"SoulHook102", (String)((StringBuilder)object2).toString());
        }
    }
}

