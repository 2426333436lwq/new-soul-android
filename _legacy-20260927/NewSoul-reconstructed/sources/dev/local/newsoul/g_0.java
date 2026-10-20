/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.net.Uri
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.Switch
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.A2;
import dev.local.newsoul.B2;
import dev.local.newsoul.H;
import dev.local.newsoul.Q0;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.b3;
import dev.local.newsoul.d3;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.h2;
import dev.local.newsoul.h3;
import dev.local.newsoul.j_0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.q0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.r0_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;
import dev.local.newsoul.z1_0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.function.BooleanSupplier;

/*
 * Renamed from dev.local.newsoul.g
 */
public final class g_0
implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ g_0(Object object, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        block57: {
            block15 : switch (this.a) {
                default: {
                    var7_1 = (h3)this.c;
                    var10_22 = (b3)this.b;
                    var11_26 = var10_22.q;
                    var9_29 = null;
                    try {
                        var7_1 = var10_22.f.g(var7_1.a);
                        var12_35 = var7_1.e;
                        var13_38 = new File(var10_22.c.getCacheDir(), "newsoul-voice-outgoing");
                        if (!var13_38.isDirectory() && !var13_38.mkdirs()) {
                            var7_1 = new IOException("Cannot create outgoing audio");
                            throw var7_1;
                        }
                    }
                    catch (Throwable var7_2) {
                        var8_39 = var9_29;
                        ** GOTO lbl51
                    }
                    var8_39 = new File(var13_38, String.valueOf(UUID.randomUUID()).concat(x1_0.c((File)var12_35)));
                    var9_29 = new FileInputStream((File)var12_35);
                    {
                        catch (Throwable var7_3) {}
                    }
                    var12_35 = new FileOutputStream(var8_39);
                    x1_0.b((InputStream)var9_29, (FileOutputStream)var12_35);
                    var12_35.close();
                    var9_29.close();
                    var9_29 = var11_26.a;
                    var12_35 = new H((Object)var10_22, (Object)var8_39, var7_1, 12);
                    var9_29.post((Runnable)var12_35);
                    return;
                    catch (Throwable var7_5) {
                        try {
                            var12_35.close();
                            throw var7_5;
                        }
                        catch (Throwable var12_36) {
                            try {
                                var7_5.addSuppressed(var12_36);
                                throw var7_5;
                            }
                            catch (Throwable var7_4) {}
                        }
                    }
                    try {
                        var9_29.close();
                        throw var7_4;
                    }
                    catch (Throwable var9_30) {
                        var7_4.addSuppressed(var9_30);
                        throw var7_4;
                    }
lbl51:
                    // 2 sources

                    if (var8_39 != null) {
                        var8_39.delete();
                    }
                    var11_26.a.post((Runnable)new g_0(var10_22, var7_6, 15));
                    return;
                }
                case 15: {
                    var7_7 = (b3)this.b;
                    var7_7.n = false;
                    if (var7_7.m != false) return;
                    d3.f((Context)var7_7.c, "\u8bfb\u53d6\u8bed\u97f3\u5931\u8d25", (Throwable)this.c);
                    return;
                }
                case 14: {
                    var8_40 /* !! */  = (WeakReference)this.b;
                    var7_8 = (d3)this.c;
                    try {
                        var8_40 /* !! */  = var8_40 /* !! */ .get();
                        if (var8_40 /* !! */  == null) return;
                        var7_8.d(var8_40 /* !! */ );
                        return;
                    }
                    catch (Throwable var7_9) {
                        Log.w((String)"SoulHook102", (String)"voice library entry unavailable", (Throwable)var7_9);
                    }
                    return;
                }
                case 13: {
                    var9_31 = (Activity)this.b;
                    var7_10 = var8_41 = ((String[])this.c)[0];
                    if (var8_41 == null) {
                        var7_10 = "https://wwbnj.lanzoul.com/b007utki2d";
                    }
                    try {
                        var8_41 = new Intent("android.intent.action.VIEW", Uri.parse((String)var7_10));
                        var9_31.startActivity(var8_41.addCategory("android.intent.category.BROWSABLE"));
                        return;
                    }
                    catch (RuntimeException var7_11) {
                        Toast.makeText((Context)var9_31, (CharSequence)"\u65e0\u6cd5\u6253\u5f00\u6d4f\u89c8\u5668\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", (int)0).show();
                    }
                    return;
                }
                case 12: {
                    var8_42 /* !! */  = B2.k;
                    var7_12 = (View)this.b;
                    var8_42 /* !! */  = var8_42 /* !! */ .get(var7_12);
                    var9_32 = (A2)this.c;
                    if (var8_42 /* !! */  != var9_32) return;
                    B2.b(var7_12, var9_32);
                    return;
                }
                case 11: {
                    ((String[])this.b)[0] = null;
                    ((Runnable)this.c).run();
                    return;
                }
                case 10: {
                    var7_13 = (Switch)this.c;
                    var1_47 = var7_13.isChecked() != false && var7_13.isEnabled() != false ? 0 : 8;
                    ((View)this.b).setVisibility(var1_47);
                    return;
                }
                case 9: {
                    var5_52 = ((BooleanSupplier)this.c).getAsBoolean();
                    ((Switch)this.b).setEnabled(var5_52);
                    return;
                }
                case 8: {
                    var7_14 = (q2)this.b;
                    var7_14 = new q2(var7_14.a, var7_14.b);
                    var10_23 = ((SharedPreferences)this.c).getString("chat_recall_style", "below");
                    var9_33 = var7_14.h;
                    var8_43 = var7_14.g;
                    var1_48 = var7_14.j;
                    var9_33.addView((View)w2.n(var8_43, "\u6548\u679c\u9884\u89c8 \u00b7 \u4e0d\u53d1\u9001\u6d88\u606f", 13, var1_48));
                    try {
                        var11_27 = z1_0.f(var8_43, var10_23);
                        var12_37 = new LinearLayout.LayoutParams(-1, -2);
                        var9_33.addView((View)var11_27, (ViewGroup.LayoutParams)var12_37);
                        var11_27 = new StringBuilder("recall style preview; style=");
                        var11_27.append(var10_23);
                        var11_27.append("; no message sent");
                        Log.i((String)"SoulHook102", (String)var11_27.toString());
                    }
                    catch (Throwable var10_24) {
                        var9_33.addView((View)w2.n(var8_43, "\u5f53\u524d\u7248\u672c\u65e0\u6cd5\u663e\u793a\u9884\u89c8", 13, var1_48));
                        Log.e((String)"SoulHook102", (String)"recall preview unavailable", (Throwable)var10_24);
                    }
                    var7_14.g("\u64a4\u56de\u6548\u679c\u9884\u89c8", "\u8fd4\u56de");
                    return;
                }
                case 7: {
                    var5_53 = Q0.Q;
                    var6_55 = Q0.R;
                    var7_15 = var5_53 != false && var6_55 != false ? "" : (var5_53 != false ? "\u90e8\u5206\u53ef\u7528\uff1a\u9690\u79c1\u7167\u7247 / \u89c6\u9891\u53ef\u7528\uff0c\u95ea\u7167\u5c1a\u672a\u5c31\u7eea" : (var6_55 != false ? "\u90e8\u5206\u53ef\u7528\uff1a\u95ea\u7167\u53ef\u7528\uff0c\u9690\u79c1\u7167\u7247 / \u89c6\u9891\u5c1a\u672a\u5c31\u7eea" : "\u591a\u6b21\u67e5\u770b\u5c1a\u672a\u5c31\u7eea\uff1a\u9690\u79c1\u7167\u7247 / \u89c6\u9891\u4e0e\u95ea\u7167\u5747\u672a\u5c31\u7eea"));
                    var8_44 = (TextView)this.b;
                    w2.s(var8_44, var7_15);
                    var1_49 = var7_15.isEmpty() != false ? 8 : 0;
                    var8_44.setVisibility(var1_49);
                    ((h2)this.c).a();
                    return;
                }
                case 6: {
                    var7_16 = ((SharedPreferences)this.b).getString("feed_keywords_v1", "").split("\\R");
                    var4_56 = ((String[])var7_16).length;
                    var2_57 = 0;
                    var1_50 = 0;
                    while (true) {
                        if (var2_57 >= var4_56) {
                            var7_16 = new StringBuilder("\u7f16\u8f91\u5c4f\u853d\u8bcd\uff08");
                            var7_16.append(var1_50);
                            var7_16.append("\uff09");
                            var7_16 = var7_16.toString();
                            ((TextView)this.c).setText((CharSequence)var7_16);
                            return;
                        }
                        var3_58 = var1_50;
                        if (!var7_16[var2_57].trim().isEmpty()) {
                            var3_58 = var1_50 + 1;
                        }
                        ++var2_57;
                        var1_50 = var3_58;
                    }
                }
                case 5: {
                    if (u1_0.i((q2)this.b) == false) return;
                    ((Runnable[])this.c)[0].run();
                    return;
                }
                case 4: {
                    var7_17 = (TextView)this.b;
                    var7_17.setEnabled(true);
                    var7_17.setText((CharSequence)"\u4fdd\u5b58\u539f\u56fe");
                    Toast.makeText((Context)((Activity)this.c), (CharSequence)"\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", (int)0).show();
                    return;
                }
                case 3: {
                    var7_18 = (Activity)this.b;
                    if (var7_18.isFinishing() != false) return;
                    if (var7_18.isDestroyed()) {
                        return;
                    }
                    var5_54 = (var7_18.getResources().getConfiguration().uiMode & 48) == 32;
                    w2.t(var7_18, var5_54, true, (String)this.c);
                    return;
                }
                case 2: {
                    var7_19 = r0_0.b;
                    var8_45 = (TextView)this.b;
                    if (var7_19.get(var8_45) != this.c) {
                        return;
                    }
                    var7_19.remove(var8_45);
                    var7_19 = var8_45;
                    for (var1_51 = 0; var1_51 < 12; ++var1_51) {
                        if (!var7_19.isClickable()) {
                            if (!((var7_19 = var7_19.getParent()) instanceof View)) break block15;
                            var7_19 = (View)var7_19;
                            continue;
                        }
                        break block57;
                    }
                    break;
                }
                case 1: {
                    g0_0.b((Activity)this.b, (a0_0)this.c, "\u5bfc\u51fa\u5931\u8d25\uff0c\u8bf7\u786e\u8ba4\u5df2\u6709\u8bb0\u5f55\u540e\u91cd\u8bd5");
                    return;
                }
                case 0: {
                    var7_20 = (Method)this.b;
                    var8_46 = this.c;
                    try {
                        var7_20.invoke(var8_46, new Object[]{-102, "new soul: disabled by user"});
                        return;
                    }
                    catch (Throwable var7_21) {
                        j_0.c("ad failure callback", var7_21);
                    }
                    return;
                }
            }
            var7_19 = var8_45;
        }
        if ((var10_25 = (q0_0)(var9_34 = r0_0.a).get(var8_45)) != null && var10_25.b.get() == var7_19 && var7_19 != null) {
            return;
        }
        if (var10_25 != null) {
            var9_34.remove(var8_45);
            var11_28 = (View)var10_25.b.get();
            if (var11_28 != null) {
                var11_28.setOnLongClickListener(null);
                var11_28.setLongClickable(var10_25.c);
            }
        }
        if (var7_19 == null) return;
        if (k_0.h((View)var7_19)) {
            return;
        }
        var10_25 = new q0_0(var8_45, (View)var7_19);
        var9_34.put(var8_45, var10_25);
        var7_19.setOnLongClickListener(var10_25);
    }
}

