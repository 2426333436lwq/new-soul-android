/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.widget.PopupWindow
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.PopupWindow;
import dev.local.newsoul.I2;

/*
 * Renamed from dev.local.newsoul.p2
 */
public final class p2_0
implements View.OnAttachStateChangeListener {
    public final int a;
    public final Object b;

    public /* synthetic */ p2_0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    private final void a(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        switch (this.a) {
            default: {
                ((I2)this.b).i();
            }
            case 0: 
        }
    }

    public final void onViewDetachedFromWindow(View view) {
        switch (this.a) {
            default: {
                ((I2)this.b).h();
                return;
            }
            case 0: 
        }
        ((PopupWindow)this.b).dismiss();
    }
}

