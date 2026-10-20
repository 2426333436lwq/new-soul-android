package dev.local.newsoul;

import android.view.View;

/* JADX INFO: loaded from: Z2.class */
public final /* synthetic */ class Z2 implements View.OnClickListener {
    public final int a;
    public final b3 b;
    public final h3 c;

    public /* synthetic */ Z2(b3 b3Var, h3 h3Var, int i) {
        this.a = i;
        this.b = b3Var;
        this.c = h3Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                h3 h3Var = this.c;
                b3 b3Var = this.b;
                if (b3Var.n) {
                    b3Var.c();
                } else {
                    try {
                        b3Var.d(h3Var);
                    } catch (Throwable th) {
                        b3Var.b();
                        return;
                    }
                }
                break;
            default:
                h3 h3Var2 = this.c;
                b3 b3Var2 = this.b;
                if (!b3Var2.n) {
                    try {
                        b3Var2.j();
                        b3Var2.a(new Q2(b3Var2, h3Var2, 3), new U2(3, b3Var2));
                    } catch (Throwable th2) {
                        d3.f(b3Var2.c, "删除失败", th2);
                    }
                    break;
                }
                break;
        }
    }
}
