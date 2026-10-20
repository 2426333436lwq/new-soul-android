/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.C1;
import dev.local.newsoul.v2_0;
import dev.local.newsoul.y0_0;

/*
 * Renamed from dev.local.newsoul.x0
 */
public final class x0_0
implements View.OnClickListener {
    public final int a;
    public final int b;
    public final Object c;

    public /* synthetic */ x0_0(Object object, int n, int n2) {
        this.a = n2;
        this.c = object;
        this.b = n;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                ((v2_0)this.c).b(this.b, true);
                return;
            }
            case 1: {
                object = ((C1)((Object)this.c)).h;
                if (object != null) {
                    object.accept(this.b);
                }
                return;
            }
            case 0: 
        }
        y0_0 y0_02 = (y0_0)this.c;
        if (y0_02.e()) {
            int n = y0_02.g;
            String[] stringArray = y0_02.e;
            long l = Integer.parseInt(stringArray[n]);
            n = y0_02.g;
            long l2 = n == 0 ? 0L : Integer.MIN_VALUE;
            stringArray[n] = String.valueOf(Math.max(l2, Math.min(Integer.MAX_VALUE, l + (long)this.b)));
            object = y0_02.c;
            object.setText((CharSequence)stringArray[y0_02.g]);
            object.setSelection(object.length());
        }
    }
}

