package dev.local.newsoul;

import android.content.DialogInterface;
import android.widget.ImageView;

/* JADX INFO: renamed from: dev.local.newsoul.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class DialogInterfaceOnDismissListenerC0037j0 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ DialogInterfaceOnDismissListenerC0037j0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        switch (this.a) {
            case 0:
                ((ImageView) this.b).setImageDrawable(null);
                break;
            case 1:
                ((Runnable) this.b).run();
                break;
            default:
                Thread thread = ((Thread[]) this.b)[0];
                if (thread != null) {
                    thread.interrupt();
                }
                break;
        }
    }
}
