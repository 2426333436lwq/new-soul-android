package dev.local.newsoul;

import android.app.Activity;
import android.view.View;

/* JADX INFO: loaded from: O1.class */
public final /* synthetic */ class O1 implements View.OnClickListener {
    public final C0067q2 a;
    public final Activity b;
    public final boolean c;
    public final boolean d;
    public final String e;

    public /* synthetic */ O1(C0067q2 q2Var, Activity activity, boolean z, boolean z2, String str) {
        this.a = q2Var;
        this.b = activity;
        this.c = z;
        this.d = z2;
        this.e = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.e.dismiss();
        AbstractC0090w2.t(this.b, this.c, this.d, this.e);
    }
}
