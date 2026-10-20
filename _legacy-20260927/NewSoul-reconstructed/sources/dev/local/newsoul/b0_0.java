/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 */
package dev.local.newsoul;

import android.widget.CompoundButton;
import dev.local.newsoul.U;
import dev.local.newsoul.V;
import dev.local.newsoul.d0_0;
import dev.local.newsoul.f0_0;

/*
 * Renamed from dev.local.newsoul.b0
 */
public final class b0_0
implements CompoundButton.OnCheckedChangeListener {
    public final f0_0 a;

    public /* synthetic */ b0_0(f0_0 f0_02) {
        this.a = f0_02;
    }

    public final void onCheckedChanged(CompoundButton object, boolean bl) {
        object = this.a;
        if (!((f0_0)object).g && !((f0_0)object).h) {
            ((f0_0)object).h = true;
            ((f0_0)object).b();
            object = new U(bl, new d0_0((f0_0)object, 0));
            V.a.execute((Runnable)object);
        }
    }
}

