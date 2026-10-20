/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import dev.local.newsoul.L2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.Y;
import dev.local.newsoul.d0_0;
import dev.local.newsoul.j1_0;
import dev.local.newsoul.l1_0;
import java.io.File;
import java.io.IOException;

public final class U
implements Runnable {
    public final int a;
    public final boolean b;
    public final Object c;

    public /* synthetic */ U(Activity activity, boolean bl) {
        this.a = 1;
        this.c = activity;
        this.b = bl;
    }

    public /* synthetic */ U(boolean bl, d0_0 d0_02) {
        this.a = 0;
        this.b = bl;
        this.c = d0_02;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                var4_1 = (Activity)this.c;
                if (!var4_1.isFinishing() && !var4_1.isDestroyed() && (var2_3 /* !! */  = var4_1.getWindow().getDecorView()) instanceof ViewGroup) {
                    if ((var2_3 /* !! */  = var2_3 /* !! */ .findViewWithTag((Object)"new.soul.privacy.photo.download")) != null) {
                        var2_3 /* !! */ .bringToFront();
                        var2_3 /* !! */ .setElevation((float)Q0.o(var4_1, 32));
                        var2_3 /* !! */ .setVisibility(0);
                    } else {
                        var5_5 = new TextView((Context)var4_1);
                        var5_5.setTag((Object)"new.soul.privacy.photo.download");
                        var1_6 = this.b;
                        var3_8 = "\u4fdd\u5b58\u89c6\u9891";
                        var2_3 /* !! */  = var1_6 != false ? "\u4fdd\u5b58\u539f\u56fe" : "\u4fdd\u5b58\u89c6\u9891";
                        var5_5.setContentDescription((CharSequence)var2_3 /* !! */ );
                        var2_3 /* !! */  = var3_8;
                        if (var1_6) {
                            var2_3 /* !! */  = "\u4fdd\u5b58\u539f\u56fe";
                        }
                        var5_5.setText((CharSequence)var2_3 /* !! */ );
                        var5_5.setTextColor(-1);
                        var5_5.setTextSize(14.0f);
                        var5_5.setGravity(17);
                        var5_5.setPadding(Q0.o(var4_1, 16), 0, Q0.o(var4_1, 16), 0);
                        new L2(true, var4_1.getResources().getDisplayMetrics().density).b((View)var5_5, 11, 22.0f);
                        var2_3 /* !! */  = new FrameLayout.LayoutParams(-2, Q0.o(var4_1, 44), 0x800055);
                        var2_3 /* !! */ .rightMargin = Q0.o(var4_1, 20);
                        var2_3 /* !! */ .bottomMargin = Q0.o(var4_1, 76);
                        var4_1.addContentView((View)var5_5, (ViewGroup.LayoutParams)var2_3 /* !! */ );
                        j1_0.b((View)var5_5, true);
                        var5_5.setElevation((float)Q0.o(var4_1, 32));
                        var5_5.bringToFront();
                        var5_5.setOnClickListener((View.OnClickListener)new l1_0(var1_6, var4_1, var5_5));
                        var2_3 /* !! */  = var1_6 != false ? "photo" : "video";
                        Q0.w("privacy save action attached: ".concat((String)var2_3 /* !! */ ));
                    }
                }
                return;
            }
            case 0: 
        }
        var1_7 = this.b;
        var2_4 = (Runnable)this.c;
        if (!var1_7) ** GOTO lbl55
        try {
            if (V.k == null) {
                var3_9 /* !! */  = new File(V.i.getNoBackupFilesDir(), "newsoul-debug");
                V.k = var4_2 = new Y(var3_9 /* !! */ , Q0.M());
            }
            if (V.k.b() >= 4186112L) {
                V.l("\u8bb0\u5f55\u5df2\u6ee1\uff0c\u8bf7\u5148\u5bfc\u51fa\u5e76\u6e05\u9664");
            } else {
                V.f = System.currentTimeMillis() + 1800000L;
                V.h = "\u6b63\u5728\u8bb0\u5f55";
                if (V.j.edit().putLong("until", V.f).putString("status", V.h).commit()) {
                    V.k("session_start");
                    V.f();
                } else {
                    var3_9 /* !! */  = new IOException();
                    throw var3_9 /* !! */ ;
lbl55:
                    // 1 sources

                    V.l("\u5df2\u505c\u6b62\u8bb0\u5f55");
                    V.f();
                }
            }
        }
        catch (Throwable var3_10) {
            V.l("\u8bb0\u5f55\u5931\u8d25\uff0c\u5df2\u505c\u6b62");
        }
        V.b.post(var2_4);
    }
}

