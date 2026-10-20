/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 */
package dev.local.newsoul;

import android.graphics.drawable.Drawable;
import android.view.View;
import dev.local.newsoul.I2;
import dev.local.newsoul.L2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.T;

public final class D2
implements View.OnAttachStateChangeListener {
    public final int a;

    public /* synthetic */ D2(int n) {
        this.a = n;
    }

    public final void onViewAttachedToWindow(View view) {
        switch (this.a) {
            default: {
                view = (Drawable)L2.d.get(view);
                if (view != null) {
                    view.invalidateSelf();
                }
                return;
            }
            case 0: 
        }
        view.post((Runnable)new T(1, view));
    }

    public final void onViewDetachedFromWindow(View object) {
        switch (this.a) {
            default: {
                L2.f((Drawable)L2.d.get(object));
                return;
            }
            case 0: 
        }
        object = Q0.i((View)object);
        if (object != null) {
            ((I2)object).h();
        }
    }
}

