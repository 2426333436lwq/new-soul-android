/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  android.view.View
 *  android.widget.FrameLayout
 *  android.widget.Switch
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.w2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public final class Z
implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;

    public /* synthetic */ Z(Object object, Object object2, Object object3, Object object4, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
        this.d = object3;
        this.e = object4;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                var10_1 = (Switch)this.b;
                var6_4 = var10_1.isEnabled();
                var5_7 = false;
                var2_8 = var6_4 != false && var10_1.isChecked() != false;
                var8_10 /* !! */  = (Switch)this.c;
                var3_13 = var8_10 /* !! */ .isEnabled() != false && var8_10 /* !! */ .isChecked() != false;
                var9_15 = (Switch)this.d;
                var4_18 = var5_7;
                if (var9_15.isEnabled()) {
                    var4_18 = var5_7;
                    if (var9_15.isChecked()) {
                        var4_18 = true;
                    }
                }
                var8_10 /* !! */  = var2_8 ? "\u81ea\u52a8\u70b9\u8d5e\u5df2\u5f00\u542f \u00b7 \u8303\u56f4\uff1a\u5df2\u52a0\u8f7d\u7684\u5168\u90e8\u5e16\u5b50\u3002\n\u4e00\u952e\u70b9\u8d5e\u914d\u7f6e\u6682\u4e0d\u751f\u6548\u3002" : (var3_13 ? (var4_18 ? "\u4e00\u952e\u70b9\u8d5e\u53ef\u7528 \u00b7 \u8303\u56f4\uff1a\u5df2\u52a0\u8f7d\u7684\u5168\u90e8\u5e16\u5b50\u3002" : "\u4e00\u952e\u70b9\u8d5e\u53ef\u7528 \u00b7 \u8303\u56f4\uff1a\u5c4f\u5e55\u5185\u5e16\u5b50\u3002") : (!var10_1.isChecked() && !var8_10 /* !! */ .isChecked() ? "\u4e00\u952e\u70b9\u8d5e\u548c\u81ea\u52a8\u70b9\u8d5e\u5747\u5df2\u5173\u95ed\u3002" : "\u5df2\u5f00\u542f\u7684\u70b9\u8d5e\u529f\u80fd\u5c1a\u672a\u5c31\u7eea\u3002"));
                w2.s((TextView)this.e, (String)var8_10 /* !! */ );
                return;
            }
            case 1: {
                var2_9 = 0;
                while (true) {
                    var9_16 = (ArrayList)this.b;
                    var3_14 = var9_16.size();
                    var6_5 = true;
                    var7_19 = true;
                    var8_11 /* !! */  = (int[])this.d;
                    if (var2_9 >= var3_14) break;
                    var9_16 = (View)var9_16.get(var2_9);
                    var6_5 = (Integer)((ArrayList)this.c).get(var2_9) == var8_11 /* !! */ [0] ? var7_19 : false;
                    var9_16.setSelected(var6_5);
                    if (var9_16 instanceof FrameLayout && (var8_11 /* !! */  = (int[])((FrameLayout)var9_16).findViewWithTag((Object)"tick")) != null) {
                        var3_14 = var6_5 != false ? 0 : 4;
                        var8_11 /* !! */ .setVisibility(var3_14);
                    }
                    ++var2_9;
                }
                if (var8_11 /* !! */ [0] < 0) {
                    var6_5 = false;
                }
                var9_16 = (TextView)this.e;
                var9_16.setEnabled(var6_5);
                var1_20 = var8_11 /* !! */ [0] >= 0 ? 1.0f : 0.35f;
                var9_16.setAlpha(var1_20);
                return;
            }
            case 0: 
        }
        var11_21 /* !! */  = (byte[])this.e;
        var9_17 = (Activity)this.b;
        var6_6 = var9_17.isDestroyed();
        var8_12 = (a0_0)this.d;
        if (var6_6 || var9_17.isFinishing()) ** GOTO lbl-1000
        var10_2 = (f0_0)this.c;
        if (var10_2.a.e.isShowing()) {
            try {
                g0_0.a(var9_17);
                var8_12.c = var11_21 /* !! */ ;
                var8_12.d = true;
                var11_21 /* !! */  = (byte[])new Intent;
                var11_21 /* !! */ ("android.intent.action.CREATE_DOCUMENT");
                var11_21 /* !! */  = (byte[])var11_21 /* !! */ .addCategory("android.intent.category.OPENABLE").setType("application/octet-stream");
                var13_22 = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT);
                var12_23 = new Comparable<Date>();
                var13_22 = var13_22.format((Date)var12_23);
                var12_23 = new Comparable<Date>("NewSoul-debug-");
                var12_23.append((String)var13_22);
                var12_23.append(".nsdebug");
                var9_17.startActivityForResult(var11_21 /* !! */ .putExtra("android.intent.extra.TITLE", var12_23.toString()), var8_12.a);
                var10_2.a("\u8bf7\u9009\u62e9\u4fdd\u5b58\u4f4d\u7f6e");
            }
            catch (Exception var10_3) {
                g0_0.b(var9_17, var8_12, "\u65e0\u6cd5\u6253\u5f00\u4fdd\u5b58\u4f4d\u7f6e\uff0c\u8bf7\u91cd\u8bd5");
            }
        } else lbl-1000:
        // 2 sources

        {
            g0_0.b(var9_17, var8_12, "\u5df2\u53d6\u6d88\u5bfc\u51fa");
        }
    }
}

