/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import dev.local.newsoul.H;
import dev.local.newsoul.T;
import dev.local.newsoul.V;
import dev.local.newsoul.a0_0;
import dev.local.newsoul.d0_0;
import dev.local.newsoul.f0_0;
import dev.local.newsoul.g0_0;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.c0
 */
public final class c0_0
implements View.OnClickListener {
    public final int a;
    public final f0_0 b;

    public /* synthetic */ c0_0(f0_0 f0_02, int n) {
        this.a = n;
        this.b = f0_02;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                object = this.b;
                ((f0_0)object).h = true;
                ((f0_0)object).b();
                object = new T(0, new d0_0((f0_0)object, 1));
                V.a.execute((Runnable)object);
                return;
            }
            case 0: 
        }
        f0_0 f0_02 = this.b;
        object = f0_02.a.a;
        WeakHashMap weakHashMap = g0_0.d;
        if (!weakHashMap.containsKey(object)) {
            a0_0 a0_02 = new a0_0(f0_02);
            weakHashMap.put(object, a0_02);
            f0_02.a("\u6b63\u5728\u51c6\u5907\u8bb0\u5f55\u2026");
            V.a.execute(new H((Activity)object, (Object)f0_02, (Object)a0_02, 3));
        }
    }
}

