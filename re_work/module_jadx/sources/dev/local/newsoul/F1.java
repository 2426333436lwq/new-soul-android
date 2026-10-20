package dev.local.newsoul;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class F1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ F1(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    private final void a(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.a) {
            case 0:
                View view2 = (View) this.b;
                Runnable runnable = (Runnable) this.c;
                view2.removeCallbacks(runnable);
                view2.post(runnable);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.a) {
            case 0:
                ((View) this.b).removeCallbacks((Runnable) this.c);
                break;
            default:
                ((OnBackInvokedDispatcher) this.b).unregisterOnBackInvokedCallback((C0043k2) ((OnBackInvokedCallback) this.c));
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }
}
