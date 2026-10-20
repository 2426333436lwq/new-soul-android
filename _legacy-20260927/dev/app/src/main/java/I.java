package dev.local.newsoul;

import android.view.View;

/* JADX INFO: loaded from: I.class */
public final /* synthetic */ class I implements View.OnClickListener {
    public final int a;
    public final C0067q2 b;
    public final Q c;

    public /* synthetic */ I(C0067q2 q2Var, Q q, int i) {
        this.a = i;
        this.b = q2Var;
        this.c = q;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                S.c(this.b, this.c, false);
                break;
            default:
                S.c(this.b, this.c, true);
                break;
        }
    }
}
