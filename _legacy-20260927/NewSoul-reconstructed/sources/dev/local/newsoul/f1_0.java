/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * Renamed from dev.local.newsoul.f1
 */
public final class f1_0
implements XposedInterface.Hooker {
    public final int a;
    public final AccessibleObject b;
    public final AccessibleObject c;
    public final AccessibleObject d;
    public final Executable e;

    public /* synthetic */ f1_0(AccessibleObject accessibleObject, AccessibleObject accessibleObject2, AccessibleObject accessibleObject3, Executable executable, int n) {
        this.a = n;
        this.b = accessibleObject;
        this.c = accessibleObject2;
        this.d = accessibleObject3;
        this.e = executable;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain var1_1) {
        switch (this.a) {
            default: {
                var8_3 = (Field)this.b;
                var6_5 = (Field)this.c;
                var7_9 /* !! */  = (Constructor)this.e;
                try {
                    if (var8_3.get(var1_1 /* !! */ .getThisObject()) == null) return var1_1 /* !! */ .proceed();
                    var10_11 /* !! */  = (List)var6_5.get(var1_1 /* !! */ .getThisObject());
                    var8_3 = new ArrayList<List>();
                }
                catch (Throwable var6_6) {
                    ** GOTO lbl43
                }
                var9_13 = (Field)this.d;
                if (var10_11 /* !! */  != null) {
                    for (List var10_11 : var10_11 /* !! */ ) {
                        if (var9_13.getInt(var10_11 /* !! */ ) == -220922) continue;
                        var8_3.add(var10_11 /* !! */ );
                    }
                }
                var4_16 = var8_3.size();
                var2_17 = 0;
                while (true) {
                    var3_18 = var4_16;
                    if (var2_17 >= var8_3.size()) break;
                    if (var9_13.getInt(var8_3.get(var2_17)) == 26) {
                        var3_18 = var2_17;
                        break;
                    }
                    ++var2_17;
                }
                {
                    var7_9 /* !! */  = var7_9 /* !! */ .newInstance(new Object[]{51, "\u590d\u5236\u6587\u6848", Boolean.FALSE});
                    var9_13.setInt(var7_9 /* !! */ , -220922);
                    var8_3.add(var3_18, (List)var7_9 /* !! */ );
                    var6_5.set(var1_1 /* !! */ .getThisObject(), var8_3);
                    var2_17 = var8_3.size();
                    var6_5 = new StringBuilder();
                    var6_5.append("post copy action added; count=");
                    var6_5.append(var2_17);
                    Log.i((String)"SoulHook102", (String)var6_5.toString());
                    return var1_1 /* !! */ .proceed();
                }
lbl43:
                // 1 sources

                Log.e((String)"SoulHook102", (String)"post copy action unavailable", (Throwable)var6_6);
                return var1_1 /* !! */ .proceed();
            }
            case 0: 
        }
        var10_12 = (Method)this.b;
        var8_4 /* !! */  = (Method)this.c;
        var7_10 = (Method)this.d;
        var9_14 = (Method)this.e;
        var6_7 = var1_1 /* !! */ .getArg(0);
        try {
            var10_12 = (String)var10_12.invoke(null, null);
            if (var6_7 == null) return var1_1 /* !! */ .proceed();
            if (var10_12 == null) return var1_1 /* !! */ .proceed();
            if (var10_12.isEmpty() != false) return var1_1 /* !! */ .proceed();
            if (var10_12.equals(var8_4 /* !! */ .invoke(var6_7, null)) == false) return var1_1 /* !! */ .proceed();
            if ((Boolean)var7_10.invoke(var6_7, null) != false) return var1_1 /* !! */ .proceed();
            var5_19 = w2.o((Context)var9_14.invoke(null, null)).getBoolean("chat_own_recall_no_time_limit", true);
            if (var5_19 == false) return var1_1 /* !! */ .proceed();
            var7_10 = g1_0.b;
            var8_4 /* !! */  = var7_10.get();
            var7_10.set(var6_7);
        }
        catch (Throwable var6_8) {
            Log.w((String)"SoulHook102", (String)"own recall eligibility unavailable", (Throwable)var6_8);
            return var1_1 /* !! */ .proceed();
        }
        try {
            var1_1 /* !! */  = var1_1 /* !! */ .proceed();
            return var1_1 /* !! */ ;
        }
        finally {
            if (var8_4 /* !! */  == null) {
                var7_10.remove();
            } else {
                var7_10.set(var8_4 /* !! */ );
            }
        }
    }
}

