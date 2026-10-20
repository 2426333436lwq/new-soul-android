package dev.local.newsoul;

import android.app.Activity;

/* JADX INFO: loaded from: O.class */
public final /* synthetic */ class O implements Runnable {
    public final P a;
    public final boolean b;
    public final Activity c;

    public /* synthetic */ O(P p, boolean z, Activity activity) {
        this.a = p;
        this.b = z;
        this.c = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        P p = this.a;
        C0067q2 q2Var = (C0067q2) p.d.get();
        boolean z = this.b;
        Activity activity = this.c;
        if (z) {
            if (q2Var != null && S.d(activity, p)) {
                q2Var.e();
            }
            AbstractC0061p0.h();
        }
        S.b(activity, p, z ? "配置已导入\n部分功能重启 Soul 后生效" : "配置保存失败，请重新导入");
    }
}
