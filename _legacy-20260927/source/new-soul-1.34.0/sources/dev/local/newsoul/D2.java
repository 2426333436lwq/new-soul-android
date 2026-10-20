package dev.local.newsoul;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class D2 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int a;

    public /* synthetic */ D2(int i) {
        this.a = i;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.a) {
            case 0:
                view.post(new T(1, view));
                break;
            default:
                Drawable drawable = (Drawable) L2.d.get(view);
                if (drawable != null) {
                    drawable.invalidateSelf();
                }
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.a) {
            case 0:
                I2 i2I = Q0.i(view);
                if (i2I != null) {
                    i2I.h();
                }
                break;
            default:
                L2.f((Drawable) L2.d.get(view));
                break;
        }
    }
}
