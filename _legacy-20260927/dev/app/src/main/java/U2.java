package dev.local.newsoul;

import android.graphics.Canvas;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class U2 implements c3 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ U2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public void a(Canvas canvas) {
        M0.a((M0) this.b, canvas);
    }

    @Override // dev.local.newsoul.c3
    public void run() {
        switch (this.a) {
            case 0:
                ((b3) this.b).f();
                break;
            case 1:
                ((b3) this.b).b();
                break;
            case 2:
                b3 b3Var = (b3) this.b;
                d3.i(b3Var.c, "已保存到语音库");
                b3Var.f();
                break;
            default:
                ((b3) this.b).f();
                break;
        }
    }
}
