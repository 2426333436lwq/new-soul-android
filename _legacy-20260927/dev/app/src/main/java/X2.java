package dev.local.newsoul;

import android.content.DialogInterface;

/* JADX INFO: loaded from: X2.class */
public final /* synthetic */ class X2 implements DialogInterface.OnCancelListener {
    public final b3 a;
    public final C0067q2 b;
    public final c3 c;

    public /* synthetic */ X2(b3 b3Var, C0067q2 q2Var, c3 c3Var) {
        this.a = b3Var;
        this.b = q2Var;
        this.c = c3Var;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        c3 c3Var = this.c;
        b3 b3Var = this.a;
        if (b3Var.m || b3Var.h != this.b) {
            return;
        }
        if (b3Var.n) {
            b3Var.c();
            return;
        }
        try {
            b3Var.j();
            c3Var.run();
        } catch (Throwable th) {
            b3Var.b();
        }
    }
}
