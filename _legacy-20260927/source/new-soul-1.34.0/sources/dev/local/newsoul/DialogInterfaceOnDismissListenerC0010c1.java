package dev.local.newsoul;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

/* JADX INFO: renamed from: dev.local.newsoul.c1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class DialogInterfaceOnDismissListenerC0010c1 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ DialogInterfaceOnDismissListenerC0010c1(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        View view;
        switch (this.a) {
            case 0:
                C0014d1 c0014d1 = (C0014d1) this.b;
                if (c0014d1.t == ((Dialog) this.c)) {
                    c0014d1.t = null;
                }
                break;
            case 1:
                C0067q2 c0067q2 = (C0067q2) this.b;
                c0067q2.s = null;
                if (c0067q2.e.isShowing() && (view = (View) this.c) != null) {
                    view.requestFocus();
                    break;
                }
                break;
            default:
                b3 b3Var = (b3) this.b;
                if (b3Var.h == ((C0067q2) this.c)) {
                    b3Var.b();
                }
                break;
        }
    }
}
