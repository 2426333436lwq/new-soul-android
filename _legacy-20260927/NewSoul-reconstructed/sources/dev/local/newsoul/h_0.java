/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.view.View
 */
package dev.local.newsoul;

import android.util.Log;
import android.view.View;
import dev.local.newsoul.A2;
import dev.local.newsoul.B2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.Y;
import dev.local.newsoul.i_0;
import dev.local.newsoul.j_0;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Renamed from dev.local.newsoul.h
 */
public final class h_0
implements Runnable {
    public final int a;

    public /* synthetic */ h_0(int n) {
        this.a = n;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        switch (this.a) {
            default: {
                B2.o = false;
                var6_1 = new ArrayList<Map.Entry<K, V>>(B2.k.entrySet()).iterator();
                while (var6_1.hasNext() != false) {
                    var5_7 = var6_1.next();
                    if (var5_7.getKey() == null) continue;
                    B2.b((View)var5_7.getKey(), (A2)var5_7.getValue());
                }
                return;
            }
            case 3: {
                try {
                    if (V.f != 0L && !V.a()) {
                        V.l("\u5df2\u5230\u65f6\u95f4\u4e0a\u9650\uff0c\u8bb0\u5f55\u5df2\u505c\u6b62");
                    }
                    V.f();
                    return;
                }
                catch (Throwable var5_8) {
                    V.l("\u8bb0\u5f55\u5931\u8d25\uff0c\u5df2\u505c\u6b62");
                }
                return;
            }
            case 2: {
                try {
                    var5_9 = new File(V.i.getNoBackupFilesDir(), "newsoul-debug");
                    V.k = var6_2 = new Y(var5_9, Q0.M());
                    V.g = var6_2.b();
                    var3_14 = V.j.getLong("until", 0L);
                    var1_15 = System.currentTimeMillis();
                    V.h = V.j.getString("status", "\u672a\u5f00\u542f");
                    if (var3_14 > var1_15 && var3_14 - var1_15 <= 1800000L) {
                    }
                    ** GOTO lbl39
                }
                catch (Throwable var5_10) {
                    V.l("\u8bb0\u5f55\u4e0d\u53ef\u7528\uff0c\u8bf7\u6e05\u9664\u540e\u91cd\u8bd5");
                    return;
                }
                {
                    if (V.g < 0x400000L) {
                        V.f = var3_14;
                        V.h = "\u6b63\u5728\u8bb0\u5f55";
                        V.k("process_resume");
                        return;
                    }
lbl39:
                    // 3 sources

                    if (var3_14 == 0L) return;
                    V.l("\u5df2\u81ea\u52a8\u505c\u6b62");
                    return;
                }
            }
            case 1: {
                var5_11 = new ArrayList((List)Q0.z.invoke(Q0.y.invoke(null, null), null));
                var5_11 = var5_11.iterator();
lbl46:
                // 2 sources

                while (true) {
                    if (var5_11.hasNext() == false) return;
                    var6_3 = var5_11.next();
                    break;
                }
                {
                    catch (Throwable var5_12) {
                        Log.w((String)"SoulHook102", (String)"recall view refresh unavailable", (Throwable)var5_12);
                        return;
                    }
                    try {
                        Q0.A.invoke(var6_3, null);
                    }
                    catch (Throwable var6_4) {}
                    {
                        Log.w((String)"SoulHook102", (String)"recall view refresh skipped", (Throwable)var6_4);
                    }
                    ** continue;
                }
            }
            case 0: 
        }
        var5_13 = j_0.d;
        synchronized (var5_13) {
            try {
                var7_16 = var5_13.entrySet().iterator();
                while (var7_16.hasNext() != false) {
                    var6_5 = var7_16.next();
                    j_0.a((View)var6_5.getKey(), (i_0)var6_5.getValue());
                }
                return;
            }
            catch (Throwable var6_6) {}
            throw var6_6;
        }
    }
}

