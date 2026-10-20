package dev.local.newsoul;

import android.util.Log;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V2 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ b3 b;
    public final /* synthetic */ C0067q2 c;

    public /* synthetic */ V2(b3 b3Var, C0067q2 c0067q2, int i) {
        this.a = i;
        this.b = b3Var;
        this.c = c0067q2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                C0067q2 c0067q2 = this.c;
                b3 b3Var = this.b;
                d3 d3Var = b3Var.q;
                try {
                    d3Var.a.post(new H(b3Var, c0067q2, b3Var.f.d(), 13));
                } catch (Throwable th) {
                    Log.w("SoulHook102", "voice library read failed", th);
                    d3Var.a.post(new V2(b3Var, c0067q2, 1));
                    return;
                }
                break;
            default:
                b3 b3Var2 = this.b;
                if (!b3Var2.m) {
                    C0067q2 c0067q3 = b3Var2.h;
                    C0067q2 c0067q4 = this.c;
                    if (c0067q3 == c0067q4) {
                        try {
                            b3Var2.j();
                            c0067q4.h.removeAllViews();
                            LinearLayout linearLayout = c0067q4.h;
                            linearLayout.addView(AbstractC0090w2.n(c0067q4.g, "读取语音库失败，请重试", 14, c0067q4.i));
                            linearLayout.addView(d3.a(c0067q4, "重试", new U2(3, b3Var2)));
                        } catch (Exception unused) {
                            b3Var2.b();
                        }
                        break;
                    }
                }
                break;
        }
    }
}
