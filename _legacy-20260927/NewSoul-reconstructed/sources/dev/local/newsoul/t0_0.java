/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.EditText
 */
package dev.local.newsoul;

import android.widget.EditText;
import dev.local.newsoul.d2_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.y0_0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.function.BooleanSupplier;

/*
 * Renamed from dev.local.newsoul.t0
 */
public final class t0_0
implements BooleanSupplier {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;

    public /* synthetic */ t0_0(Object object, Object object2, Object object3, Object object4, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
        this.d = object3;
        this.e = object4;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final boolean getAsBoolean() {
        switch (this.a) {
            default: {
                var10_1 = (ArrayList)this.c;
                var9_2 = (Runnable)this.e;
                var8_3 = (EditText)this.b;
                var11_4 = var8_3.getText().toString().trim();
                var4_5 = var11_4.isEmpty();
                var3_7 = true;
                if (var4_5) {
                    return var3_7;
                }
                try {
                    var7_9 = String.join((CharSequence)"\n", var10_1);
                    var6_10 = new StringBuilder();
                    var6_10.append((String)var7_9);
                    var6_10.append("\n");
                    var6_10.append((String)var11_4);
                    var7_9 = var6_10.toString();
                    var6_10 = var7_9;
                    if (var7_9 == null) {
                        var6_10 = "";
                    }
                    if (var6_10.length() > 12000) ** GOTO lbl73
                    var7_9 = new LinkedHashSet();
                    var6_10 = var6_10.split("\\R");
                    var2_13 = ((String[])var6_10).length;
                    for (var1_14 = 0; var1_14 < var2_13; ++var1_14) {
                        var12_15 = var6_10[var1_14].trim().toLowerCase(Locale.ROOT);
                        if (var12_15.isEmpty()) continue;
                        if (var12_15.length() <= 100) {
                            var7_9.add(var12_15);
                            continue;
                        }
                        ** GOTO lbl39
                    }
                    ** GOTO lbl41
                }
                catch (IllegalArgumentException var6_11) {
                    ** GOTO lbl75
                }
lbl39:
                // 1 sources

                var6_10 = new IllegalArgumentException("\u6bcf\u4e2a\u5c4f\u853d\u8bcd\u6700\u591a100\u4e2a\u5b57\u7b26");
                throw var6_10;
lbl41:
                // 1 sources

                if (var7_9.size() > 100) ** GOTO lbl71
                var6_10 = new ArrayList(var7_9);
                Collections.unmodifiableList(var6_10);
                var6_10 = new HashSet();
                var7_9 = var10_1.iterator();
                while (var7_9.hasNext()) {
                    var6_10.add(((String)var7_9.next()).toLowerCase(Locale.ROOT));
                }
                var11_4 = var11_4.split("\\R");
                var2_13 = var11_4.length;
                var1_14 = 0;
                while (true) {
                    var7_9 = (String[])this.d;
                    if (var1_14 >= var2_13) break;
                    {
                        var12_15 = var11_4[var1_14].trim();
                        if (var12_15.isEmpty() || !var6_10.add(var12_15.toLowerCase(Locale.ROOT))) ** GOTO lbl62
                        var10_1.add(var12_15);
                    }
                    var7_9[0] = var12_15;
lbl62:
                    // 2 sources

                    ++var1_14;
                }
                {
                    var8_3.setText((CharSequence)"");
                    var8_3.setError(null);
                    var9_2.run();
                    var6_10 = new g_0(var7_9, (d2_0)var9_2, 11);
                    var8_3.postDelayed((Runnable)var6_10, 1800L);
                    return var3_7;
lbl71:
                    // 1 sources

                    var6_10 = new IllegalArgumentException("\u6700\u591a\u4fdd\u5b58100\u4e2a\u5c4f\u853d\u8bcd");
                    throw var6_10;
lbl73:
                    // 1 sources

                    var6_10 = new IllegalArgumentException("\u5c4f\u853d\u8bcd\u5185\u5bb9\u8fc7\u957f");
                    throw var6_10;
                }
lbl75:
                // 1 sources

                var8_3.setError((CharSequence)var6_11.getMessage());
                return false;
            }
            case 0: 
        }
        var6_12 = (y0_0)this.b;
        var5_16 = var6_12.f.equals(var6_12.b());
        var3_8 = var4_6 = true;
        if ((var5_16 ^ true) != false) return var3_8;
        var6_12 = (y0_0)this.c;
        var3_8 = var4_6;
        if ((var6_12.f.equals(var6_12.b()) ^ true) != false) return var3_8;
        var6_12 = (y0_0)this.d;
        var3_8 = var4_6;
        if ((var6_12.f.equals(var6_12.b()) ^ true) != false) return var3_8;
        var6_12 = (y0_0)this.e;
        if ((var6_12.f.equals(var6_12.b()) ^ true) == false) return false;
        return var4_6;
    }
}

