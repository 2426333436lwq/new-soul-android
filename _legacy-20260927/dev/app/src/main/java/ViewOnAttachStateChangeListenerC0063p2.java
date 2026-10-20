package dev.local.newsoul;

import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: renamed from: dev.local.newsoul.ViewOnAttachStateChangeListenerC0063p2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnAttachStateChangeListenerC0063p2 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ViewOnAttachStateChangeListenerC0063p2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    private final void a(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.a) {
            case 0:
                break;
            default:
                ((I2) this.b).i();
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.a) {
            case 0:
                ((PopupWindow) this.b).dismiss();
                break;
            default:
                ((I2) this.b).h();
                break;
        }
    }
}
