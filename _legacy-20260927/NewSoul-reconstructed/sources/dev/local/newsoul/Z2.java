/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.d3;
import dev.local.newsoul.h3;
import dev.local.newsoul.q2_0;
import dev.local.newsoul.u2_0;

public final class Z2
implements View.OnClickListener {
    public final int a;
    public final b3 b;
    public final h3 c;

    public /* synthetic */ Z2(b3 b32, h3 h32, int n) {
        this.a = n;
        this.b = b32;
        this.c = h32;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                Object object2 = this.c;
                object = this.b;
                if (!((b3)object).n) {
                    try {
                        ((b3)object).j();
                        q2_0 q2_02 = new q2_0((b3)object, (h3)object2, 3);
                        object2 = new u2_0(3, object);
                        ((b3)object).a(q2_02, (c3)object2);
                    }
                    catch (Throwable throwable) {
                        d3.f((Context)((b3)object).c, "\u5220\u9664\u5931\u8d25", throwable);
                    }
                }
                return;
            }
            case 0: 
        }
        h3 h32 = this.c;
        object = this.b;
        if (((b3)object).n) {
            ((b3)object).c();
        } else {
            try {
                ((b3)object).d(h32);
            }
            catch (Throwable throwable) {
                ((b3)object).b();
            }
        }
    }
}

