package dev.local.newsoul;

import android.app.Activity;
import android.content.DialogInterface;

/* JADX INFO: loaded from: J1.class */
public final /* synthetic */ class J1 implements DialogInterface.OnClickListener {
    public final Activity a;
    public final boolean b;

    public /* synthetic */ J1(Activity activity, boolean z) {
        this.a = activity;
        this.b = z;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AbstractC0090w2.t(this.a, this.b, false, (String) null);
    }
}
