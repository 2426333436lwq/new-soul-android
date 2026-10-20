/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.graphics.Bitmap
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.text.TextWatcher
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.A0;
import dev.local.newsoul.C1;
import dev.local.newsoul.G;
import dev.local.newsoul.J;
import dev.local.newsoul.K;
import dev.local.newsoul.L2;
import dev.local.newsoul.P;
import dev.local.newsoul.Q0;
import dev.local.newsoul.S;
import dev.local.newsoul.V;
import dev.local.newsoul.Y2;
import dev.local.newsoul.Z;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.a3;
import dev.local.newsoul.b3;
import dev.local.newsoul.d3;
import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.h3;
import dev.local.newsoul.q2;
import dev.local.newsoul.q_0;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public final class H
implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public /* synthetic */ H(Activity activity, Object object, Object object2, int n) {
        this.a = n;
        this.c = activity;
        this.b = object;
        this.d = object2;
    }

    public /* synthetic */ H(TextView textView, Activity activity, Uri uri) {
        this.a = 5;
        this.d = textView;
        this.c = activity;
        this.b = uri;
    }

    public /* synthetic */ H(Object object, Activity activity, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = activity;
        this.d = object2;
    }

    public /* synthetic */ H(Object object, Object object2, Object object3, int n) {
        this.a = n;
        this.b = object;
        this.d = object2;
        this.c = object3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        block64: {
            var1_1 = 1;
            var4_2 = this.c;
            var3_3 = this.d;
            var6_12 = this.b;
            switch (this.a) {
                default: {
                    var5_18 = (b3)var6_12;
                    if (var5_18.m != false) return;
                    var6_12 = var5_18.h;
                    if (var6_12 != (var3_3 = (q2)var3_3)) {
                        return;
                    }
                    try {
                        var5_18.j();
                    }
                    catch (Exception var3_4) {
                        var5_18.b();
                    }
                    var3_3.h.removeAllViews();
                    var8_28 /* !! */  = var3_3.g;
                    var6_12 = new EditText(var8_28 /* !! */ );
                    var6_12.setTextColor(var3_3.i);
                    var1_1 = var3_3.j;
                    var6_12.setHintTextColor(var1_1);
                    var6_12.setHint((CharSequence)"\u641c\u7d22\u5907\u6ce8");
                    var6_12.setContentDescription((CharSequence)"\u641c\u7d22\u8bed\u97f3\u5907\u6ce8");
                    var6_12.setSingleLine(true);
                    var6_12.setMinHeight(w2.i(var8_28 /* !! */ , 48));
                    var6_12.setTextSize(15.0f);
                    var6_12.setPadding(w2.i(var8_28 /* !! */ , 14), w2.i(var8_28 /* !! */ , 10), w2.i(var8_28 /* !! */ , 14), w2.i(var8_28 /* !! */ , 10));
                    var7_32 = var3_3.c;
                    var7_32.getClass();
                    var7_32.b((View)var6_12, 8, L2.e(8));
                    var7_32 = var8_28 /* !! */ .getDrawable(17301583).mutate();
                    var7_32.setTint(var1_1);
                    var1_1 = w2.i(var8_28 /* !! */ , 20);
                    var7_32.setBounds(0, 0, var1_1, var1_1);
                    var6_12.setCompoundDrawablesRelative((Drawable)var7_32, null, null, null);
                    var6_12.setCompoundDrawablePadding(w2.i(var8_28 /* !! */ , 8));
                    var6_12.setText((CharSequence)var5_18.k);
                    var6_12.setSelection(var6_12.length());
                    var9_41 = var3_3.h;
                    var9_41.addView((View)var6_12, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                    var7_32 = new LinearLayout(var8_28 /* !! */ );
                    var7_32.setOrientation(1);
                    var7_32.setPadding(0, w2.i(var8_28 /* !! */ , 12), 0, 0);
                    var9_41.addView((View)var7_32, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                    var4_2 = (List)var4_2;
                    var8_28 /* !! */  = (ArrayList)var4_2;
                    var5_18.h((q2)var3_3, (LinearLayout)var7_32, (ArrayList)var8_28 /* !! */ );
                    var6_12.addTextChangedListener((TextWatcher)new a3(var5_18, (q2)var3_3, (LinearLayout)var7_32, (ArrayList)var8_28 /* !! */ ));
                    var7_32.setFocusableInTouchMode(true);
                    var7_32.requestFocus();
                    if (var9_41.getParent() instanceof ScrollView) {
                        var6_12 = (ScrollView)var9_41.getParent();
                        var6_12.post((Runnable)new Y2(var5_18, (q2)var3_3, (ScrollView)var6_12, var5_18.j));
                    }
                    var1_1 = var4_2.size();
                    var3_3 = new StringBuilder("voice library shown; count=");
                    var3_3.append(var1_1);
                    Log.i((String)"SoulHook102", (String)var3_3.toString());
                    return;
                    return;
                }
                case 12: {
                    var5_19 = (File)var3_3;
                    var3_3 = (h3)var4_2;
                    var4_2 = (b3)var6_12;
                    var6_12 = var4_2.c;
                    var4_2.j();
                    var7_33 = d3.g(var4_2.a, "Q");
                    if (var7_33 == null) ** GOTO lbl82
                    try {
                        var4_2.q.e.invoke(var7_33, new Object[]{var5_19.getAbsolutePath(), var3_3.c, null});
                        d3.i((Context)var6_12, "\u5df2\u63d0\u4ea4\u53d1\u9001\uff0c\u8bf7\u67e5\u770b\u804a\u5929\u4e2d\u7684\u6d88\u606f\u72b6\u6001");
                        Log.i((String)"SoulHook102", (String)"voice library send submitted explicitly");
                    }
                    catch (Throwable var3_5) {}
                    ** GOTO lbl87
lbl82:
                    // 1 sources

                    try {
                        var3_3 = new IllegalStateException("\u804a\u5929\u5c1a\u672a\u51c6\u5907\u597d");
                        throw var3_3;
                    }
                    catch (Throwable var3_6) {
                        var1_1 = 0;
                    }
lbl87:
                    // 2 sources

                    try {
                        d3.f((Context)var6_12, "\u53d1\u9001\u672a\u5b8c\u6210\uff0c\u8bf7\u67e5\u770b\u6d88\u606f\u72b6\u6001", (Throwable)var3_7);
                        return;
                    }
                    finally {
                        var4_2.b();
                    }
                }
                case 11: {
                    var5_20 = (q2)var6_12;
                    if (var5_20.e.isShowing() == false) return;
                    if ((var4_2 = (Activity)var4_2).isFinishing() != false) return;
                    if (var4_2.isDestroyed()) {
                        return;
                    }
                    var3_3 = (LinearLayout)var3_3;
                    var3_3.removeAllViews();
                    var1_1 = var5_20.i;
                    var4_2 = var5_20.g;
                    var3_3.addView((View)w2.n((Context)var4_2, "\u68c0\u6d4b\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u68c0\u6d4b", 16, var1_1));
                    var3_3.addView((View)w2.n((Context)var4_2, "\u672a\u80fd\u5b8c\u6210\u672c\u6b21\u68c0\u67e5\uff0c\u5df2\u6709\u8bbe\u7f6e\u672a\u6539\u53d8\u3002", 13, var5_20.j));
                    var5_20.e.getButton(-3).setVisibility(0);
                    var5_20.e.getButton(-3).setEnabled(true);
                    return;
                }
                case 10: {
                    var3_3 = ((SharedPreferences)var3_3).getString("chat_recall_style", "below");
                    ((C1)var6_12).b((String)var3_3, (String[])var4_2, false);
                    return;
                }
                case 9: {
                    ((TextView)var6_12).setEnabled(true);
                    u1_0.e((Activity)var4_2, (Exception)var3_3);
                    return;
                }
                case 8: {
                    var6_12 = (q2)var6_12;
                    if (!u1_0.i((q2)var6_12)) {
                        return;
                    }
                    var5_21 = var6_12.h;
                    var5_21.removeAllViews();
                    var1_1 = var6_12.j;
                    var5_21.addView((View)w2.n(var6_12.g, "\u8bfb\u53d6\u5931\u8d25\uff0c\u539f\u6709\u8bb0\u5f55\u5df2\u4fdd\u7559", 14, var1_1));
                    var6_12 = u1_0.a((q2)var6_12, "\u91cd\u65b0\u52a0\u8f7d");
                    var6_12.setOnClickListener((View.OnClickListener)new A0(1, (Runnable[])var3_3));
                    var5_21.addView((View)var6_12);
                    Log.w((String)"SoulHook102", (String)"bookmark list read failed", (Throwable)((Exception)var4_2));
                    return;
                }
                case 7: {
                    ((t1_0)var6_12).g = false;
                    u1_0.e((Activity)var4_2, (Exception)var3_3);
                    return;
                }
                case 6: {
                    var5_22 = (t1_0)var6_12;
                    var6_12 = (Activity)var4_2;
                    var4_2 = u1_0.a;
                    try {
                        u1_0.h(var5_22.a);
                        var8_29 = new x1_0(var6_12.getFilesDir(), var5_22.a, 0);
                        var2_42 = var5_22.f;
                        var7_34 = var5_22.b;
                        if (!var2_42) ** GOTO lbl148
                        var8_29.i((String)var7_34);
                        ** GOTO lbl149
                    }
                    catch (Exception var3_9) {
                        ** GOTO lbl153
                    }
lbl148:
                    // 1 sources

                    var8_29.f((String)var7_34, var5_22.c);
lbl149:
                    // 2 sources

                    var7_34 = new q_0(var5_22, (Activity)var6_12, var2_42, var3_3);
                    var4_2.post((Runnable)var7_34);
                    return;
lbl153:
                    // 1 sources

                    var4_2.post((Runnable)new H((Object)var5_22, (Activity)var6_12, (Object)var3_9, 7));
                    return;
                }
                case 5: {
                    var3_3 = (TextView)var3_3;
                    var3_3.setEnabled(true);
                    var3_3.setText((CharSequence)"\u4fdd\u5b58\u539f\u56fe");
                    var3_3 = (Uri)var6_12 == null ? "\u4fdd\u5b58\u5931\u8d25" : "\u5df2\u4fdd\u5b58\u5230 Pictures/New Soul";
                    Toast.makeText((Context)((Activity)var4_2), (CharSequence)var3_3, (int)0).show();
                    return;
                }
                case 4: {
                    var5_23 = (Future)var6_12;
                    var4_2 = (Activity)var4_2;
                    var3_3 = (TextView)var3_3;
                    try {
                        var5_23 = var5_23.get();
                        if (var5_23 instanceof Bitmap) {
                            var6_12 = Q0.T((Activity)var4_2, (Bitmap)var5_23);
                            var5_23 = new H((TextView)var3_3, (Activity)var4_2, (Uri)var6_12);
                            var4_2.runOnUiThread((Runnable)var5_23);
                            Q0.w("privacy photo saved: ".concat(String.valueOf(var6_12)));
                            return;
                        }
                    }
                    catch (Throwable var5_24) {
                        ** GOTO lbl180
                    }
                    var5_23 = new IllegalStateException("Resource is not bitmap");
                    throw var5_23;
lbl180:
                    // 1 sources

                    Q0.S.log(6, "SoulHook102", "privacy photo download failed", var5_24);
                    var4_2.runOnUiThread((Runnable)new g_0(var3_3, var4_2, 4));
                    return;
                }
                case 3: {
                    var4_2 = (Activity)var4_2;
                    var6_12 = (f0_0)var6_12;
                    var5_25 = (a0_0)var3_3;
                    var3_3 = g0_0.b;
                    try {
                        var7_35 = V.e();
                        var8_30 = new Z(var4_2, var6_12, var5_25, var7_35, 0);
                        var3_3.post((Runnable)var8_30);
                        return;
                    }
                    catch (Exception var6_13) {
                        var3_3.post((Runnable)new g_0(var4_2, var5_25, 1));
                    }
                    return;
                }
                case 2: {
                    g0_0.b((Activity)var4_2, (a0_0)var6_12, (String)var3_3);
                    return;
                }
                case 1: {
                    var6_12 = (Uri)var6_12;
                    var4_2 = (Activity)var4_2;
                    var5_26 = (a0_0)var3_3;
                    if (!"content".equals(var6_12.getScheme())) ** GOTO lbl228
                    var3_3 = var4_2.getContentResolver().openOutputStream((Uri)var6_12, "wt");
                    if (var3_3 == null) ** GOTO lbl218
                    try {
                        var3_3.write(var5_26.c);
                        var3_3.flush();
                    }
                    catch (Throwable var6_14) {}
                    var3_3.close();
                    var3_3 = "\u8bb0\u5f55\u5df2\u5bfc\u51fa\uff0c\u53ef\u53d1\u9001\u7ed9\u4f5c\u8005";
lbl218:
                    // 2 sources

                    var6_12 = new IOException();
                    throw var6_12;
                    if (var3_3 == null) throw var6_14;
                    try {
                        var3_3.close();
                        throw var6_14;
                    }
                    catch (Throwable var3_10) {
                        try {
                            var6_14.addSuppressed(var3_10);
                            throw var6_14;
lbl228:
                            // 1 sources

                            var3_3 = new IOException();
                            throw var3_3;
                        }
                        catch (Exception var3_11) {
                            var3_3 = "\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u4f4d\u7f6e";
                        }
                    }
                    g0_0.b.post((Runnable)new H((Activity)var4_2, (Object)var5_26, var3_3, 2));
                    return;
                }
                case 0: 
            }
            var6_12 = (Uri)var6_12;
            var3_3 = (P)var3_3;
            var4_2 = (Activity)var4_2;
            var5_27 = S.b;
            if (!"content".equals(var6_12.getScheme())) ** GOTO lbl300
            if (var3_3.c == null) ** GOTO lbl267
            var7_36 = var4_2.getContentResolver().openOutputStream((Uri)var6_12, "wt");
            if (var7_36 == null) break block64;
            try {
                var7_36.write(var3_3.c.getBytes(StandardCharsets.UTF_8));
                var7_36.flush();
            }
            catch (Throwable var6_15) {}
            var7_36.close();
            var6_12 = new J((Activity)var4_2, (P)var3_3, 0);
            var5_27.post((Runnable)var6_12);
            return;
        }
        var6_12 = new IOException();
        throw var6_12;
        if (var7_36 == null) throw var6_15;
        try {
            var7_36.close();
            throw var6_15;
        }
        catch (Throwable var7_37) {
            block65: {
                var6_15.addSuppressed(var7_37);
                throw var6_15;
lbl267:
                // 1 sources

                var6_12 = var4_2.getContentResolver().openInputStream((Uri)var6_12);
                if (var6_12 == null) break block65;
                var7_38 = G.a;
                var7_38 = new ByteArrayOutputStream();
                var8_31 = new byte[4096];
                while ((var1_1 = var6_12.read(var8_31)) != -1) {
                    if (var1_1 > 131072 - var7_38.size()) {
                        var7_38 = new IOException();
                        throw var7_38;
                    }
                    var7_38.write(var8_31, 0, var1_1);
                }
                var7_38 = var7_38.toByteArray();
                var6_12.close();
                var6_12 = G.c(StandardCharsets.UTF_8.newDecoder().decode(ByteBuffer.wrap((byte[])var7_38)).toString());
                var7_38 = new K((Activity)var4_2, (P)var3_3, (Map)var6_12);
                var5_27.post((Runnable)var7_38);
                return;
            }
            try {
                var7_40 = new IOException();
                throw var7_40;
            }
            catch (Throwable var7_39) {
                if (var6_12 == null) throw var7_39;
                try {
                    var6_12.close();
                    throw var7_39;
                }
                catch (Throwable var6_16) {
                    try {
                        var7_39.addSuppressed(var6_16);
                        throw var7_39;
lbl300:
                        // 1 sources

                        var6_12 = new IllegalArgumentException();
                        throw var6_12;
                    }
                    catch (Exception var6_17) {
                        var5_27.post((Runnable)new J((Activity)var4_2, (P)var3_3, 1));
                    }
                }
            }
        }
    }
}

