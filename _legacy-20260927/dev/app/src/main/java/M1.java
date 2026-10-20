package dev.local.newsoul;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class M1 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ M1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        switch (this.a) {
            case 0:
                ((Runnable) this.b).run();
                break;
            default:
                ((I2) this.b).m();
                break;
        }
        return true;
    }
}
