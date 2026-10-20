/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.view.View
 *  android.widget.CheckBox
 *  android.widget.LinearLayout
 *  android.widget.Switch
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import dev.local.newsoul.C1;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.d3;
import dev.local.newsoul.g2_0;
import dev.local.newsoul.h2;
import dev.local.newsoul.j2_0;
import dev.local.newsoul.n1_0;
import dev.local.newsoul.o1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w1_0;
import dev.local.newsoul.x1_0;
import java.lang.reflect.Field;
import java.util.function.BooleanSupplier;

/*
 * Renamed from dev.local.newsoul.m1
 */
public final class m1_0
implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;

    public /* synthetic */ m1_0(Object object, Object object2, Object object3, Object object4, Object object5, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
        this.d = object3;
        this.e = object4;
        this.f = object5;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                var5_1 = (c3)this.f;
                var4_9 = (b3)this.b;
                var4_9.n = false;
                var4_9.o = false;
                if (var4_9.m != false) return;
                var7_18 = var4_9.h;
                var6_22 = (q2)this.c;
                if (var7_18 != var6_22) {
                    return;
                }
                var6_22.h.removeView((View)((TextView)this.d));
                if (var6_22.e.getButton(-2) != null) {
                    var6_22.e.getButton(-2).setText((CharSequence)"\u53d6\u6d88");
                }
                if (var6_22.e.getButton(-1) != null) {
                    var6_22.e.getButton(-1).setEnabled(true);
                    var6_22.e.getButton(-1).setText((CharSequence)"\u91cd\u8bd5");
                }
                try {
                    var4_9.j();
                }
                catch (Throwable var5_2) {}
                var6_22 = (Throwable)this.e;
                if (var6_22 != null) throw var6_22;
                var5_1.run();
                return;
                d3.f((Context)var4_9.c, "\u64cd\u4f5c\u672a\u5b8c\u6210\uff0c\u8bf7\u91cd\u8bd5", var5_2);
                return;
            }
            case 5: {
                var4_10 = (c3)this.c;
                var5_3 = (b3)this.b;
                var5_3.getClass();
                try {
                    var4_10.run();
                    var4_10 = null;
                }
                catch (Throwable var4_11) {}
                var5_3.q.a.post((Runnable)new m1_0(var5_3, (q2)this.d, (TextView)this.e, var4_10, (c3)this.f, 6));
                return;
            }
            case 4: {
                var6_23 = (r2_0)this.c;
                var3_28 = var6_23.isEnabled();
                var7_19 = (TextView)this.b;
                var7_19.setEnabled(var3_28);
                var7_19.setClickable(var6_23.isEnabled());
                var3_28 = var6_23.isEnabled();
                var4_12 /* !! */  = (LinearLayout)this.d;
                var4_12 /* !! */ .setEnabled(var3_28);
                var4_12 /* !! */ .setClickable(var6_23.isEnabled());
                var8_33 = (SharedPreferences)this.f;
                var5_4 = "";
                var4_12 /* !! */  = var8_33 != null && !var6_23.isEnabled() ? "\uff08\u672a\u5c31\u7eea\uff09" : "";
                var10_36 = new StringBuilder();
                var9_39 = (String)this.e;
                var10_36.append(var9_39);
                var10_36.append((String)var4_12 /* !! */ );
                var7_19.setText((CharSequence)var10_36.toString());
                var4_12 /* !! */  = var5_4;
                if (var8_33 != null) {
                    var4_12 /* !! */  = var6_23.isEnabled() != false ? var5_4 : "\uff0c\u672a\u5c31\u7eea\uff0c\u539f\u56e0\u5c1a\u672a\u786e\u8ba4";
                }
                var5_4 = new StringBuilder();
                var5_4.append(var9_39);
                var5_4.append((String)var4_12 /* !! */ );
                var6_23.setContentDescription(var5_4.toString());
                return;
            }
            case 3: {
                var8_34 = (BooleanSupplier)this.c;
                var6_24 = (CheckBox)this.d;
                var4_13 /* !! */  = (SharedPreferences)this.e;
                var5_5 = (g2_0)this.b;
                var5_5.d = true;
                var7_20 = var5_5.b;
                try {
                    var3_29 = var8_34.getAsBoolean();
                    var6_24.setEnabled(var3_29);
                    var8_34 = var5_5.a;
                    var6_24.setChecked(var4_13 /* !! */ .getBoolean((String)var8_34, "block_splash".equals(var8_34)));
                    var4_13 /* !! */  = var3_29 != false ? "" : "\uff08\u672a\u5c31\u7eea\uff09";
                }
                catch (Throwable var4_14) {
                    ** GOTO lbl102
                }
                var8_34 = new StringBuilder();
                var8_34.append(var7_20);
                var8_34.append((String)var4_13 /* !! */ );
                var6_24.setText((CharSequence)var8_34.toString());
                var4_13 /* !! */  = var3_29 != false ? "\uff0c\u52fe\u9009\u8868\u793a\u5c4f\u853d" : "\uff0c\u672a\u5c31\u7eea\uff0c\u539f\u56e0\u5c1a\u672a\u786e\u8ba4";
                var8_34 = new StringBuilder();
                var8_34.append(var7_20);
                var8_34.append((String)var4_13 /* !! */ );
                var6_24.setContentDescription((CharSequence)var8_34.toString());
                var5_5.d = false;
                ((h2)this.f).a();
                return;
lbl102:
                // 1 sources

                var5_5.d = false;
                throw var4_14;
            }
            case 2: {
                var4_15 = ((SharedPreferences)this.b).getString("feed_filter_gender", "FEMALE");
                var6_25 = (C1)this.c;
                var5_6 = (String[])this.d;
                var2_42 = 0;
                var6_25.b(var4_15, var5_6, false);
                var3_30 = "ANY".equals(var4_15);
                var1_43 = var3_30 != false ? 8 : 0;
                ((View)this.e).setVisibility(var1_43);
                var1_43 = var3_30 != false ? var2_42 : 8;
                ((TextView)this.f).setVisibility(var1_43);
                return;
            }
            case 1: {
                var6_26 = (Switch)this.b;
                var4_16 = (r2_0)var6_26;
                var3_31 = var4_16.c;
                var5_7 = (Switch)this.c;
                if (!var3_31) {
                    var4_16 = (r2_0)var5_7;
                    if (!var4_16.c) {
                        var4_16 = null;
                    }
                }
                var3_31 = var5_7.isEnabled() != false && var5_7.isChecked() != false && (var6_26.isEnabled() == false || var6_26.isChecked() == false);
                ((j2_0)this.d).c(var3_31, (r2_0)var4_16);
                var5_7 = (TextView)this.e;
                var4_16 = (Runnable)this.f;
                var5_7.removeCallbacks((Runnable)var4_16);
                if (var5_7.isAttachedToWindow()) {
                    var5_7.post((Runnable)var4_16);
                    return;
                }
                var4_16.run();
                return;
            }
            case 0: 
        }
        var7_21 = (t1_0)this.b;
        var4_17 = (Activity)this.c;
        var6_27 = this.d;
        var5_8 = (Field)this.e;
        var8_35 = (Field)this.f;
        u1_0.h(var7_21.a);
        var11_44 = new x1_0(var4_17.getFilesDir(), var7_21.a, 0);
        var10_37 = var7_21.b;
        var9_40 = x1_0.b;
        ** synchronized (var9_40)
lbl-1000:
        // 1 sources

        {
            block31: {
                var11_44 = var11_44.h().iterator();
                while (var11_44.hasNext()) {
                    if (!((w1_0)var11_44.next()).a.equals(var10_37)) continue;
                    // MONITOREXIT @DISABLED, blocks:[5, 23, 9] lbl153 : MonitorExitStatement: MONITOREXIT : var9_40
                    break block31;
                }
                // MONITOREXIT @DISABLED, blocks:[5, 9] lbl155 : MonitorExitStatement: MONITOREXIT : var9_40
                var3_32 = false;
            }
            var3_32 = true;
        }
        try {
            var10_37 = u1_0.a;
            var9_40 = new n1_0(var4_17, var7_21, var6_27, var3_32, var5_8, var8_35);
            var10_37.post((Runnable)var9_40);
            return;
        }
        catch (Exception var9_41) {}
        {
            catch (Throwable var10_38) {}
        }
        throw var10_38;
        u1_0.a.post((Runnable)new o1_0(var7_21, var4_17, var6_27, var5_8, var8_35, var9_41, 0));
    }
}

