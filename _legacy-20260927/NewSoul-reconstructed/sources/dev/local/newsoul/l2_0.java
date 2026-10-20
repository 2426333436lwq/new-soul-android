/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.PopupWindow
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.PopupWindow;
import dev.local.newsoul.q2;
import java.util.function.IntConsumer;

/*
 * Renamed from dev.local.newsoul.l2
 */
public final class l2_0
implements View.OnClickListener {
    public final q2 a;
    public final PopupWindow b;
    public final View c;
    public final IntConsumer d;
    public final int e;

    public /* synthetic */ l2_0(q2 q22, PopupWindow popupWindow, View view, IntConsumer intConsumer, int n) {
        this.a = q22;
        this.b = popupWindow;
        this.c = view;
        this.d = intConsumer;
        this.e = n;
    }

    public final void onClick(View object) {
        object = this.a;
        object.getClass();
        this.b.dismiss();
        View view = this.c;
        if (view.isAttachedToWindow() && view.isShown() && !(object = ((q2)object).a).isFinishing() && !object.isDestroyed()) {
            this.d.accept(this.e);
        }
    }
}

