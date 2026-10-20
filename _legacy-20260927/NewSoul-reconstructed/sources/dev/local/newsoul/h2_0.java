/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package dev.local.newsoul;

import android.util.Log;
import dev.local.newsoul.E2;
import dev.local.newsoul.I2;
import dev.local.newsoul.J2;
import dev.local.newsoul.P2;
import dev.local.newsoul.b3;
import dev.local.newsoul.u2_0;
import dev.local.newsoul.w2_0;
import java.io.Serializable;
import java.util.HashSet;

/*
 * Renamed from dev.local.newsoul.H2
 */
public final class h2_0
implements P2 {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ h2_0(Object object, Object object2, int n) {
        this.b = object;
        this.c = object2;
        this.a = n;
    }

    @Override
    public void a(String string) {
        b3 b32 = (b3)this.b;
        b32.j();
        b32.a(new w2_0(b32, b32.c.getApplicationContext(), (String)this.c, string, this.a), new u2_0(2, b32));
    }

    public void b(boolean bl) {
        int n;
        I2 i2 = (I2)this.b;
        Serializable serializable = i2.t;
        Object object = (J2)this.c;
        ((HashSet)serializable).remove(((J2)object).d);
        if (!bl) {
            i2.i.remove(((J2)object).d);
        }
        if (i2.m && this.a == (n = i2.p)) {
            object = i2.c;
            object.removeCallbacks((Runnable)i2.w);
            if (bl) {
                i2.o = n = i2.o + 1;
                serializable = new StringBuilder("loaded like response: success=");
                ((StringBuilder)serializable).append(n);
                Log.i((String)"SoulHook102", (String)((StringBuilder)serializable).toString());
                ((E2)((Object)object)).a(i2.j.size() - i2.n, true);
                object.postDelayed((Runnable)i2.v, 500L);
            } else {
                n = i2.o;
                serializable = new StringBuilder("\u70b9\u8d5e\u5931\u8d25\uff0c\u5df2\u505c\u6b62\uff1b\u672c\u6279\u6210\u529f ");
                ((StringBuilder)serializable).append(n);
                ((StringBuilder)serializable).append(" \u6761");
                i2.d(((StringBuilder)serializable).toString());
            }
        }
    }
}

