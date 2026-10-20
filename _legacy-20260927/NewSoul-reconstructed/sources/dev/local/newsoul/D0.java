/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.q2;
import dev.local.newsoul.s_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w1_0;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.SecureRandom;

public final class D0
implements View.OnClickListener {
    public final int a;
    public final Serializable b;
    public final Object c;
    public final Object d;
    public final Object e;

    public /* synthetic */ D0(Serializable serializable, Object object, Object object2, Object object3, int n) {
        this.a = n;
        this.b = serializable;
        this.c = object;
        this.d = object2;
        this.e = object3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onClick(View var1_1) {
        switch (this.a) {
            default: {
                var1_1 = (String)this.b;
                var7_3 = (w1_0)this.c;
                var6_7 = (q2)this.d;
                try {
                    u1_0.h((String)var1_1);
                    if (u1_0.f) {
                        u1_0.e.invoke(null, new Object[]{var7_3.a, "newsoul_bookmark"});
                        var6_7.e.dismiss();
                        return;
                    }
                }
                catch (Exception var1_2) {
                    ** GOTO lbl18
                }
                var1_1 = new IllegalStateException("\u5f53\u524d\u7248\u672c\u6682\u65e0\u6cd5\u6253\u5f00\u4e3b\u9875");
                throw var1_1;
lbl18:
                // 1 sources

                u1_0.e((Activity)this.e, var1_2);
                return;
            }
            case 0: 
        }
        var1_1 = (int[])this.b;
        if (var1_1[0] < 0) {
            return;
        }
        ((TextView)this.c).setEnabled(false);
        ((Dialog)this.d).dismiss();
        var3_12 = var1_1[0];
        var6_8 = (s_0)this.e;
        var6_8.getClass();
        var1_1 = t_0.c;
        var8_13 = var6_8.a;
        if (var8_13 != null) {
            var7_4 = (Method)var8_13.b;
            var6_8 = var8_13.c;
            var8_13 = (Activity)var8_13.a;
            if (var8_13.isFinishing() != false) return;
            if (var8_13.isDestroyed()) {
                return;
            }
            try {
                var1_1.set((int)var3_12);
                t_0.f.getInvoker(var7_4).invoke(var6_8, new Object[0]);
                return;
            }
            catch (Throwable var6_9) {
                try {
                    t_0.f.log(6, "SoulHook102", "selected game send failed", var6_9);
                    Toast.makeText((Context)var8_13, (CharSequence)"\u53d1\u9001\u672a\u5b8c\u6210\uff0c\u8bf7\u68c0\u67e5\u804a\u5929\u9875\u9762", (int)0).show();
                    return;
                }
                finally {
                    var1_1.remove();
                }
            }
        }
        var5_14 = var6_8.b;
        var2_15 = var3_12;
        if (var3_12 == false) {
            var7_5 = new SecureRandom();
            var2_15 = var5_14 != false ? (Object)6 : (Object)3;
            var2_15 = var7_5.nextInt((int)var2_15) + 1;
        }
        try {
            var1_1.set((int)var2_15);
            var7_5 = t_0.h.newInstance(new Object[]{0});
            var4_16 = t_0.i.getInt(var7_5) == var2_15;
            var7_5 = new StringBuilder("game local dry-run: result=");
            var7_5.append((int)var2_15);
            var7_5.append(" verified=");
            var7_5.append(var4_16);
            var7_5.append("; no message sent");
            var7_5 = var7_5.toString();
            Log.i((String)"SoulHook102", (String)var7_5);
            t_0.f.log(4, "SoulHook102", (String)var7_5);
            var1_1.remove();
        }
        catch (Throwable var7_6) {
            try {
                t_0.f.log(6, "SoulHook102", "game local dry-run failed", var7_6);
                var4_16 = false;
            }
            finally {
                var1_1.remove();
            }
        }
        if (var5_14) {
            var1_1 = new StringBuilder();
            var1_1.append((int)var2_15);
            var1_1.append(" \u70b9");
            var1_1 = var1_1.toString();
        } else {
            var1_1 = t_0.g[var2_15 - true];
        }
        if (var4_16) {
            var7_5 = new StringBuilder();
            var7_5.append((String)var1_1);
            var7_5.append(" \u00b7 \u672a\u53d1\u9001");
            var1_1 = var7_5.toString();
        } else {
            var1_1 = "\u9884\u89c8\u6821\u9a8c\u5931\u8d25";
        }
        Toast.makeText((Context)var6_8.c, (CharSequence)var1_1, (int)0).show();
    }
}

