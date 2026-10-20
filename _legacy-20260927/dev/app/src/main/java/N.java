package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: N.class */
public final /* synthetic */ class N implements View.OnClickListener {
    public final boolean[] a;
    public final P b;
    public final C0067q2 c;
    public final Activity d;
    public final Map e;

    public /* synthetic */ N(boolean[] zArr, P p, C0067q2 q2Var, Activity activity, Map map) {
        this.a = zArr;
        this.b = p;
        this.c = q2Var;
        this.d = activity;
        this.e = map;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean[] zArr = this.a;
        if (zArr[0]) {
            return;
        }
        zArr[0] = true;
        P p = this.b;
        Q q = (Q) p.e.get();
        if (q != null) {
            q.a("正在导入配置…", true);
        }
        this.c.e.dismiss();
        S.c.execute(new K(this.d, this.e, p));
    }
}
