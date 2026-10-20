/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.window.OnBackInvokedCallback
 *  android.window.OnBackInvokedDispatcher
 */
package dev.local.newsoul;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import dev.local.newsoul.I0;
import dev.local.newsoul.k2_0;

public final class F1
implements View.OnAttachStateChangeListener {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ F1(Object object, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
    }

    private final void a(View view) {
    }

    public final void onViewAttachedToWindow(View object) {
        switch (this.a) {
            default: {
                return;
            }
            case 0: 
        }
        View view = (View)this.b;
        object = (Runnable)this.c;
        view.removeCallbacks((Runnable)object);
        view.post((Runnable)object);
    }

    public final void onViewDetachedFromWindow(View view) {
        switch (this.a) {
            default: {
                I0.i((OnBackInvokedDispatcher)this.b, (k2_0)((OnBackInvokedCallback)this.c));
                view.removeOnAttachStateChangeListener((View.OnAttachStateChangeListener)this);
                return;
            }
            case 0: 
        }
        ((View)this.b).removeCallbacks((Runnable)this.c);
    }
}

