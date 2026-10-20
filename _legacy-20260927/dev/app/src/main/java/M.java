package dev.local.newsoul;

import android.app.Activity;
import android.content.DialogInterface;

/* JADX INFO: loaded from: M.class */
public final /* synthetic */ class M implements DialogInterface.OnDismissListener {
    public final boolean[] a;
    public final Activity b;
    public final P c;

    public /* synthetic */ M(boolean[] zArr, Activity activity, P p) {
        this.a = zArr;
        this.b = activity;
        this.c = p;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        if (this.a[0]) {
            return;
        }
        S.b(this.b, this.c, "已取消导入");
    }
}
