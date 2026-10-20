package dev.local.newsoul;

import android.view.View;

/* JADX INFO: renamed from: dev.local.newsoul.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC0033i0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Runnable b;

    public /* synthetic */ ViewOnClickListenerC0033i0(Runnable runnable, int i) {
        this.a = i;
        this.b = runnable;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                this.b.run();
                break;
            case 1:
                this.b.run();
                break;
            case 2:
                this.b.run();
                break;
            default:
                this.b.run();
                break;
        }
    }
}
