package dev.local.newsoul;

import android.view.View;

/* JADX INFO: loaded from: F2.class */
public final /* synthetic */ class F2 implements Runnable {
    public final int a;
    public final I2 b;

    public /* synthetic */ F2(I2 C0035i2, int i) {
        this.a = i;
        this.b = C0035i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                I2 C0035i2 = this.b;
                if (C0035i2.l) {
                    View viewG = C0035i2.g();
                    C0035i2.m();
                    boolean z = C0035i2.m;
                    E2 C0019e2 = C0035i2.c;
                    if (!z) {
                        int size = viewG == null ? 0 : C0035i2.k().size();
                        C0019e2.a(size, false);
                        if (size > 0 && C0035i2.b() && C0035i2.t.isEmpty()) {
                            C0035i2.l(true);
                        }
                    }
                    C0019e2.postDelayed(C0035i2.u, 700L);
                    break;
                }
                break;
            case 1:
                this.b.f();
                break;
            default:
                I2 i3 = this.b;
                if (i3.m) {
                    i3.d("点赞响应超时，已停止");
                }
                break;
        }
    }
}
