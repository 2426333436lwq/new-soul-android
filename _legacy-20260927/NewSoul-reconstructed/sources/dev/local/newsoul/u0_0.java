/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;
import dev.local.newsoul.p0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.y0_0;

/*
 * Renamed from dev.local.newsoul.u0
 */
public final class u0_0
implements View.OnClickListener {
    public final y0_0 a;
    public final y0_0 b;
    public final y0_0 c;
    public final y0_0 d;
    public final SharedPreferences e;
    public final q2 f;
    public final Activity g;

    public /* synthetic */ u0_0(y0_0 y0_02, y0_0 y0_03, y0_0 y0_04, y0_0 y0_05, SharedPreferences sharedPreferences, q2 q22, Activity activity) {
        this.a = y0_02;
        this.b = y0_03;
        this.c = y0_04;
        this.d = y0_05;
        this.e = sharedPreferences;
        this.f = q22;
        this.g = activity;
    }

    public final void onClick(View object) {
        object = this.a;
        boolean bl = ((y0_0)object).f();
        y0_0 y0_02 = this.b;
        boolean bl2 = y0_02.f();
        y0_0 y0_03 = this.c;
        boolean bl3 = y0_03.f();
        y0_0 y0_04 = this.d;
        boolean bl4 = y0_04.f();
        if (bl && bl2 && bl3 && bl4) {
            SharedPreferences.Editor editor = this.e.edit();
            ((y0_0)object).a(editor);
            y0_02.a(editor);
            y0_03.a(editor);
            y0_04.a(editor);
            editor.apply();
            p0_0.h();
            this.f.e.dismiss();
            Toast.makeText((Context)this.g, (CharSequence)"\u5df2\u4fdd\u5b58", (int)0).show();
        }
    }
}

