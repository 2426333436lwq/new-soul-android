/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.util.Log
 *  android.view.View
 *  android.widget.GridLayout
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import dev.local.newsoul.A;
import dev.local.newsoul.F;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.e3;
import dev.local.newsoul.j1_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public final class D
implements XposedInterface.Hooker {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ D(Object object, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain var1_1) {
        block25: {
            block24: {
                block26: {
                    block23: {
                        switch (this.a) {
                            default: {
                                var10_4 = ((Method)this.b).invoke(null, null);
                                if (var10_4 instanceof Context == false) return var1_1 /* !! */ .proceed();
                                if (w2.o((Context)var10_4).getBoolean("block_splash", true) == false) return var1_1 /* !! */ .proceed();
                                Log.i((String)"SoulHook102", (String)"new soul: hot splash blocked; HotADManager gate=false");
                                ((XposedModule)this.c).log(4, "SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
                                return Boolean.FALSE;
                            }
                            case 0: 
                        }
                        var13_7 = (Field[])this.c;
                        var12_8 = (F)this.b;
                        var12_8.getClass();
                        var10_5 = var1_1 /* !! */ .getArg(0);
                        var10_5 = V.a() != false ? A.a(false, var10_5) : null;
                        if (var10_5 == null) ** GOTO lbl26
                        try {
                            var11_9 = var10_5.a;
                            ++var11_9.d;
                            var14_10 = Q0.X();
                            var11_9 = new StringBuilder("source=");
                            var11_9.append((String)var14_10);
                            Q0.r("repeat.render.enter", var11_9.toString());
lbl26:
                            // 2 sources

                            var11_9 = var1_1 /* !! */ .getArg(0);
                            var14_10 = var12_8.i(var11_9);
                            A.b(var14_10);
                            if (var14_10 != var11_9) break block23;
                        }
                        catch (Throwable var1_2) {
                            break block24;
                        }
                        var11_9 = var1_1 /* !! */ .proceed();
                        break block26;
                    }
                    var11_9 = var1_1 /* !! */ .proceed(new Object[]{var14_10});
                }
                try {
                    var14_10 = (List)var14_10;
                    if (var14_10 != null) ** GOTO lbl-1000
                    Q0.r("repeat.render", "entries=null");
                    if (var10_5 == null) return var11_9;
                }
                catch (Throwable var1_3) {
                    ** GOTO lbl-1000
                }
lbl43:
                // 2 sources

                while (true) {
                    var10_5.close();
                    return var11_9;
                }
lbl-1000:
                // 1 sources

                {
                    block27: {
                        var9_11 = V.a();
                        var4_12 = 5133058;
                        var5_13 = 5133057;
                        var6_14 = 5133059;
                        if (!var9_11) break block27;
                        var16_15 /* !! */  = var14_10.iterator();
                        var2_16 = 0;
                        while (var16_15 /* !! */ .hasNext()) {
                            block29: {
                                block28: {
                                    var15_19 /* !! */  = var16_15 /* !! */ .next();
                                    var7_18 = (Integer)var12_8.c.invoke(var15_19 /* !! */ , null);
                                    if (var7_18 == 5133057 || var7_18 == 5133058) break block28;
                                    var3_17 = var2_16;
                                    if (var7_18 != 5133059) break block29;
                                }
                                var3_17 = var2_16 + 1;
                            }
                            var2_16 = var3_17;
                        }
                        var3_17 = var14_10.size();
                        var15_19 /* !! */  = new StringBuilder();
                        var15_19 /* !! */ .append("count=");
                        var15_19 /* !! */ .append(var3_17);
                        var15_19 /* !! */ .append("; custom=");
                        var15_19 /* !! */ .append(var2_16);
                        Q0.r("repeat.render", var15_19 /* !! */ .toString());
                    }
                    var13_7 = var13_7.getDeclaredFields();
                    var8_20 = var13_7.length;
                    var7_18 = 0;
                    var3_17 = var6_14;
                    var2_16 = var5_13;
                    var5_13 = var7_18;
                    break block25;
                }
lbl-1000:
                // 1 sources

                {
                    Q0.s("repeat.render.failed", var1_3);
                    Log.w((String)"SoulHook102", (String)"chat action marks unavailable");
                    ** GOTO lbl132
                }
            }
            if (var10_5 == null) throw var1_2;
            try {
                var10_5.close();
                throw var1_2;
            }
            catch (Throwable var10_6) {
                var1_2.addSuppressed(var10_6);
            }
            throw var1_2;
        }
        while (true) {
            if (var5_13 >= var8_20) ** GOTO lbl132
            {
                block31: {
                    block32: {
                        block30: {
                            var15_19 /* !! */  = var13_7[var5_13];
                            if (GridLayout.class.isAssignableFrom(var15_19 /* !! */ .getType())) break block30;
                            var6_14 = var2_16;
                            var2_16 = var3_17;
                            var3_17 = var6_14;
                            break block31;
                        }
                        var15_19 /* !! */ .setAccessible(true);
                        var15_19 /* !! */  = (GridLayout)var15_19 /* !! */ .get(var1_1 /* !! */ .getThisObject());
                        var2_16 = var15_19 /* !! */  == null ? -1 : var15_19 /* !! */ .getChildCount();
                        var3_17 = var14_10.size();
                        var16_15 /* !! */  = new StringBuilder();
                        var16_15 /* !! */ .append("children=");
                        var16_15 /* !! */ .append(var2_16);
                        var16_15 /* !! */ .append("; entries=");
                        var16_15 /* !! */ .append(var3_17);
                        Q0.r("repeat.render.grid", var16_15 /* !! */ .toString());
                        if (var15_19 /* !! */  != null && var15_19 /* !! */ .getChildCount() == var14_10.size()) {
                            for (var2_16 = 0; var2_16 < var14_10.size(); ++var2_16) {
                                var3_17 = (Integer)var12_8.c.invoke(var14_10.get(var2_16), null);
                                var17_21 = j1_0.a(var15_19 /* !! */ .getChildAt(var2_16));
                                if (var3_17 == 5133059 && var17_21 != null) {
                                    var16_15 /* !! */  = new e3(var17_21.getResources().getDisplayMetrics().density);
                                    var17_21.setImageDrawable((Drawable)var16_15 /* !! */ );
                                }
                                var9_11 = var3_17 == 5133057 || var3_17 == 5133058 || var3_17 == 5133059;
                                j1_0.b((View)var17_21, var9_11);
                            }
                        }
                        break block32;
lbl132:
                        // 2 sources

                        if (var10_5 == null) return var11_9;
                        ** continue;
                    }
                    var2_16 = 5133059;
                    var3_17 = 5133057;
                    var4_12 = 5133058;
                }
                ++var5_13;
                var6_14 = var2_16;
                var2_16 = var3_17;
                var3_17 = var6_14;
                continue;
            }
            break;
        }
    }
}

