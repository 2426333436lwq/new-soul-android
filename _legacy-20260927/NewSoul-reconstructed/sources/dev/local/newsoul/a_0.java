/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import dev.local.newsoul.D2;
import dev.local.newsoul.H;
import dev.local.newsoul.P;
import dev.local.newsoul.Q;
import dev.local.newsoul.Q0;
import dev.local.newsoul.S;
import dev.local.newsoul.U;
import dev.local.newsoul.V;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.h_0;
import dev.local.newsoul.i_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.l_0;
import dev.local.newsoul.m_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.u_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import java.util.ArrayList;
import java.util.List;

/*
 * Renamed from dev.local.newsoul.a
 */
public final class a_0
implements XposedInterface.Hooker {
    public final int a;

    public /* synthetic */ a_0(int n) {
        this.a = n;
    }

    /*
     * Unable to fully structure code
     */
    public final Object intercept(XposedInterface.Chain var1_1) {
        var11_4 = null;
        var10_5 = null;
        var12_11 = null;
        var15_12 = null;
        var14_13 = null;
        var13_14 = null;
        var16_15 = null;
        var5_16 = true;
        switch (this.a) {
            default: {
                var10_5 = var1_1.proceed();
                var1_1 = Q0.i((View)var1_1.getThisObject());
                if (var1_1 != null) {
                    var1_1.m();
                }
                return var10_5;
            }
            case 20: {
                var10_5 = var1_1.proceed();
                ((View)var1_1.getThisObject()).addOnAttachStateChangeListener((View.OnAttachStateChangeListener)new D2(0));
                return var10_5;
            }
            case 19: {
                var10_5 = (Activity)var1_1.getThisObject();
                if (!Q0.e0) {
                    var10_5.getApplication().registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new Object());
                    Q0.e0 = true;
                    Log.i((String)"SoulHook102", (String)"square like lifecycle registered");
                }
                return var1_1.proceed();
            }
            case 18: {
                if (Q0.q("chat_remove_social_score_limit")) {
                    Log.i((String)"SoulHook102", (String)"social score dialog suppressed");
                    var1_1 = var16_15;
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 17: {
                var10_5 = (Activity)var1_1.getThisObject();
                var5_16 = Q0.R((Activity)var10_5);
                var2_17 = var10_5.getIntent().getIntExtra("type", -1);
                var10_5 = new StringBuilder("privacy snap preview: repeat=");
                var10_5.append(var5_16);
                var10_5.append(" type=");
                var10_5.append(var2_17);
                Q0.w(var10_5.toString());
                return var1_1.proceed();
            }
            case 16: {
                var10_5 = (Activity)var1_1.getThisObject();
                var11_4 = w2.o((Context)var10_5);
                if (Q0.R((Activity)var10_5)) {
                    var10_5.getIntent().putExtra("limitViewTimes", 0);
                }
                var1_1 = var1_1.proceed();
                if (var11_4.getBoolean("privacy_allow_screenshot", false)) {
                    var10_5.getWindow().clearFlags(8192);
                }
                var3_24 = var10_5.getIntent().getIntExtra("type", -1);
                if (var11_4.getBoolean("privacy_allow_download", false) && (var2_18 = var10_5.getIntent().getIntExtra("type", -1)) >= 1 && var2_18 <= 4) {
                    var2_18 = var10_5.getIntent().getIntExtra("type", -1);
                    var5_16 = var2_18 == 1 || var2_18 == 3;
                    var12_11 = var10_5.getWindow().getDecorView();
                    for (var2_18 = 0; var2_18 < 4; ++var2_18) {
                        var8_27 = (new long[]{0L, 250L, 750L, 1500L})[var2_18];
                        var12_11.postDelayed((Runnable)new U((Activity)var10_5, var5_16), var8_27);
                    }
                }
                var5_16 = var11_4.getBoolean("privacy_allow_download", false);
                var6_29 = var11_4.getBoolean("privacy_allow_screenshot", false);
                var7_30 = var11_4.getBoolean("privacy_allow_repeat", false);
                var10_5 = new StringBuilder("privacy photo preview: download=");
                var10_5.append(var5_16);
                var10_5.append(" screenshot=");
                var10_5.append(var6_29);
                var10_5.append(" repeat=");
                var10_5.append(var7_30);
                var10_5.append(" type=");
                var10_5.append(var3_24);
                Q0.w(var10_5.toString());
                return var1_1;
            }
            case 15: {
                var10_5 = var1_1.proceed();
                var11_4 = g1_0.b;
                if (var11_4.get() != var1_1.getThisObject()) {
                    var1_1 = var10_5;
                } else {
                    var11_4.remove();
                    var8_28 = System.currentTimeMillis();
                    if (var8_28 - (Long)var10_5 <= 120000L) {
                        var1_1 = var10_5;
                    } else {
                        Log.i((String)"SoulHook102", (String)"own recall local age check bypassed");
                        var1_1 = var8_28;
                    }
                }
                return var1_1;
            }
            case 14: {
                var10_5 = (Activity)var1_1.getThisObject();
                var12_11 = (a0_0)g0_0.d.get(var10_5);
                if (var12_11 != null && var12_11.a == (var2_19 = ((Integer)var1_1.getArg(0)).intValue())) {
                    if (!var12_11.d) {
                        var1_1 = var11_4;
                    } else {
                        var12_11.d = false;
                        var13_14 = (Intent)var1_1.getArg(2);
                        if ((Integer)var1_1.getArg(1) == -1 && var13_14 != null && var13_14.getData() != null) {
                            var13_14 = var13_14.getData();
                            var1_1 = (f0_0)var12_11.b.get();
                            if (var1_1 != null) {
                                var1_1.a("\u6b63\u5728\u4fdd\u5b58\u8bb0\u5f55\u2026");
                            }
                            V.a.execute(new H((Object)var13_14, (Activity)var10_5, var12_11, 1));
                            var1_1 = var11_4;
                        } else {
                            g0_0.b((Activity)var10_5, (a0_0)var12_11, "\u5df2\u53d6\u6d88\u5bfc\u51fa");
                            var1_1 = var11_4;
                        }
                    }
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 13: {
                V.h((Context)var1_1.getThisObject());
                return var1_1.proceed();
            }
            case 12: {
                var11_4 = (Activity)var1_1.getThisObject();
                var12_11 = (P)S.d.get(var11_4);
                if (var12_11 != null && var12_11.a == (var2_20 = ((Integer)var1_1.getArg(0)).intValue())) {
                    if (!var12_11.f) {
                        var1_1 = var10_5;
                    } else {
                        var12_11.f = false;
                        if ((Integer)var1_1.getArg(1) == -1) {
                            if ((var1_1 = (Intent)var1_1.getArg(2)) != null && var1_1.getData() != null) {
                                var13_14 = var1_1.getData();
                                var1_1 = var12_11.c != null ? "\u6b63\u5728\u5bfc\u51fa\u914d\u7f6e\u2026" : "\u6b63\u5728\u8bfb\u53d6\u914d\u7f6e\u2026";
                                var14_13 = (Q)var12_11.e.get();
                                if (var14_13 != null) {
                                    var14_13.a((String)var1_1, true);
                                }
                                var1_1 = new H((Object)var13_14, var12_11, var11_4, 0);
                                S.c.execute((Runnable)var1_1);
                                var1_1 = var10_5;
                            } else {
                                S.b((Activity)var11_4, (P)var12_11, "\u672a\u53d6\u5f97\u914d\u7f6e\u6587\u4ef6\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9");
                                var1_1 = var10_5;
                            }
                        } else {
                            S.b((Activity)var11_4, (P)var12_11, "\u5df2\u53d6\u6d88\u914d\u7f6e\u64cd\u4f5c");
                            var1_1 = var10_5;
                        }
                    }
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 11: {
                var10_5 = new u_0(var1_1.getThisObject(), var1_1.getArg(1), var1_1.getArg(0));
                try {
                    var1_1 = var1_1.proceed();
                    return var1_1;
                }
                finally {
                    var10_5.close();
                }
            }
            case 10: {
                try {
                    var10_5 = (Context)Q0.l.invoke(null, null);
                    var11_4 = var1_1.getArg(0);
                    if (!w2.o((Context)var10_5).getBoolean("chat_anti_recall", true) || !Q0.v(var11_4) || !Q0.u(var11_4)) ** GOTO lbl177
                    var11_4 = Q0.L(var11_4);
                    if (!(var10_5 = var10_5.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0)).contains((String)var11_4) && !var10_5.edit().putBoolean((String)var11_4, true).commit()) ** GOTO lbl177
                    Log.i((String)"SoulHook102", (String)"recall retained; source=roam");
                    var1_1 = var12_11;
                    ** GOTO lbl178
                }
                catch (Throwable var10_7) {
                    Log.e((String)"SoulHook102", (String)"roam recall interception failed", (Throwable)var10_7);
                }
lbl177:
                // 3 sources

                var1_1 = var1_1.proceed();
lbl178:
                // 2 sources

                return var1_1;
            }
            case 9: {
                try {
                    var10_5 = (Context)Q0.l.invoke(null, null);
                    if (!w2.o((Context)var10_5).getBoolean("chat_anti_recall", true) || !((var11_4 = var1_1.getArg(0)) instanceof ArrayList)) ** GOTO lbl208
                    var12_11 = (List)var11_4;
                    var2_21 = var12_11.size();
                    var4_31 = var2_21 - 1;
                    var2_21 = 0;
                }
                catch (Throwable var10_9) {
                    var2_21 = 0;
                    ** GOTO lbl210
                }
                while (true) {
                    var3_25 = var2_21;
                    if (var4_31 < 0) ** GOTO lbl213
                    try {
                        var11_4 = var12_11.get(var4_31);
                        var3_25 = var2_21;
                    }
                    catch (Throwable var10_8) {
                        ** GOTO lbl210
                    }
                    if (Q0.S((Context)var10_5, var1_1.getThisObject(), var11_4)) {
                        var12_11.remove(var4_31);
                        var3_25 = 1;
                    }
                    --var4_31;
                    var2_21 = var3_25;
                }
lbl208:
                // 1 sources

                var3_25 = 0;
                ** GOTO lbl213
lbl210:
                // 2 sources

                Log.e((String)"SoulHook102", (String)"recall interception failed", (Throwable)var10_10);
                var3_25 = var2_21;
lbl213:
                // 3 sources

                var1_1 = var1_1.proceed();
                if (var3_25 != 0 && Q0.y != null) {
                    new Handler(Looper.getMainLooper()).post((Runnable)new h_0(1));
                }
                return var1_1;
            }
            case 8: {
                if (Q0.e(var1_1.getArg(0))) {
                    Log.i((String)"SoulHook102", (String)"gift chat dialog suppressed");
                    var1_1 = var15_12;
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 7: {
                var1_1 = var10_5 = var1_1.proceed();
                if (Q0.q("chat_remove_bell_gift_gate")) {
                    if (Boolean.TRUE.equals(var10_5)) {
                        Log.i((String)"SoulHook102", (String)"special bell gift popup bypassed");
                    }
                    var1_1 = Boolean.FALSE;
                }
                return var1_1;
            }
            case 6: {
                var10_5 = var11_4 = var1_1.proceed();
                if ("chat_limit_session_type".equals(var1_1.getArg(0))) {
                    var2_22 = (Integer)var11_4;
                    var10_5 = var11_4;
                    if (var2_22 >= 2) {
                        var10_5 = var11_4;
                        if (var2_22 <= 5) {
                            var10_5 = var11_4;
                            if (Q0.q("chat_remove_gift_gate")) {
                                var10_5 = 1;
                            }
                        }
                    }
                }
                return var10_5;
            }
            case 5: {
                var10_5 = var1_1.proceed();
                var11_4 = Boolean.TRUE;
                if (!var11_4.equals(var10_5) || !Q0.e(var1_1.getThisObject())) {
                    var5_16 = false;
                }
                if (var11_4.equals(var10_5)) {
                    var11_4 = String.valueOf(Q0.i.invoke(var1_1.getThisObject(), null));
                    var1_1 = new StringBuilder("chat gate observed; type=");
                    var1_1.append((String)var11_4);
                    var1_1.append("; bypass=");
                    var1_1.append(var5_16);
                    Log.i((String)"SoulHook102", (String)var1_1.toString());
                }
                var1_1 = var10_5;
                if (var5_16) {
                    var1_1 = Boolean.FALSE;
                }
                return var1_1;
            }
            case 4: {
                var10_5 = (Integer)t_0.c.get();
                if (var10_5 != null && var10_5.intValue() > 0) {
                    var11_4 = new StringBuilder("game message constructed with selected result=");
                    var11_4.append(var10_5);
                    var11_4 = var11_4.toString();
                    Log.i((String)"SoulHook102", (String)var11_4);
                    t_0.f.log(4, "SoulHook102", (String)var11_4);
                    var1_1 = var1_1.proceed(new Object[]{var10_5});
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 3: {
                if (Q0.q("match_mute_background_music")) {
                    Log.i((String)"SoulHook102", (String)"soul match background music blocked");
                    var1_1 = var14_13;
                } else {
                    var1_1 = var1_1.proceed();
                }
                return var1_1;
            }
            case 2: {
                var10_5 = var1_1.proceed();
                var1_1 = (Activity)var1_1.getThisObject();
                try {
                    var11_4 = var1_1.getWindow().getDecorView();
                    var12_11 = var11_4.getViewTreeObserver();
                    var13_14 = new l_0((Activity)var1_1);
                    var12_11.addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)var13_14);
                    var12_11 = new m_0((Activity)var1_1, 0);
                    var11_4.post((Runnable)var12_11);
                }
                catch (Throwable var1_3) {
                    Log.w((String)"SoulHook102", (String)"bottom settings observation failed", (Throwable)var1_3);
                }
                return var10_5;
            }
            case 1: {
                var11_4 = var1_1.proceed();
                var12_11 = Thread.currentThread().getStackTrace();
                var3_26 = ((StackTraceElement[])var12_11).length;
                var2_23 = 0;
                while (true) {
                    var10_5 = var13_14;
                    if (var2_23 >= var3_26) ** GOTO lbl332
                    var10_5 = var12_11[var2_23].getClassName();
                    if (!var10_5.equals("pi.d")) ** GOTO lbl316
                    var10_5 = "ad_conversation";
                    ** GOTO lbl332
lbl316:
                    // 1 sources

                    if (!var10_5.startsWith("cn.soulapp.android.component.chat.limitdialog.RewardAdHelper")) ** GOTO lbl319
                    var10_5 = "ad_reward_chat";
                    ** GOTO lbl332
lbl319:
                    // 1 sources

                    if (!var10_5.startsWith("cn.soulapp.android.component.square.video.drama.o0")) ** GOTO lbl322
                    var10_5 = "ad_reward_drama";
                    ** GOTO lbl332
lbl322:
                    // 1 sources

                    if (var10_5.startsWith("cn.soulapp.android.ad.h5.moduel.") || var10_5.equals("cc.c") || var10_5.startsWith("cn.soul.android.soul_rn_sdk.plugin.SRNAdPlugin") || var10_5.startsWith("cn.soul.android.soul_rn_sdk.plugin.SoulRnAdPlugin")) break;
                    if (!var10_5.startsWith("cn.soulapp.android.ad.base.view.AdFloatViewProvider")) ** GOTO lbl326
                    var10_5 = "ad_float";
                    ** GOTO lbl332
lbl326:
                    // 1 sources

                    if (!var10_5.equals("cn.soulapp.android.ad.base.view.e")) ** GOTO lbl329
                    var10_5 = "ad_interstitial";
                    ** GOTO lbl332
lbl329:
                    // 1 sources

                    ++var2_23;
                }
                var10_5 = "ad_reward_web";
lbl332:
                // 7 sources

                if (var10_5 != null) {
                    j_0.e.put(var1_1.getThisObject(), var10_5);
                }
                return var11_4;
            }
            case 0: 
        }
        var11_4 = (View)var1_1.getThisObject();
        var10_5 = (i_0)j_0.d.get(var11_4);
        if (var10_5 != null && j_0.b(var11_4.getContext(), var10_5.a)) {
            var10_5.b = (Integer)var1_1.getArg(0);
            var10_5.c = true;
            var1_1 = var1_1.proceed(new Object[]{8});
        } else {
            var1_1 = var1_1.proceed();
        }
        return var1_1;
    }
}

