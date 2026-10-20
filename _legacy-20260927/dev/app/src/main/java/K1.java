package dev.local.newsoul;

import android.content.SharedPreferences;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: K1.class */
public final /* synthetic */ class K1 implements Consumer {
    public final C0067q2 a;
    public final SharedPreferences b;
    public final List c;

    public /* synthetic */ K1(C0067q2 q2Var, SharedPreferences sharedPreferences, ArrayList arrayList) {
        this.a = q2Var;
        this.b = sharedPreferences;
        this.c = arrayList;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        View view = (View) obj;
        if (!view.isAttachedToWindow() || !view.isShown()) {
            return;
        }
        C0067q2 q2Var = this.a;
        if (q2Var.a.isFinishing() || q2Var.a.isDestroyed()) {
            return;
        }
        List list = this.c;
        Iterator it = list.iterator();
        boolean asBoolean = false;
        while (true) {
            boolean z = asBoolean;
            if (!it.hasNext()) {
                q2Var.h(view, new String[]{"屏蔽本组", "取消屏蔽本组"}, z, new P1(view, q2Var, this.b, (ArrayList) list));
                return;
            }
            asBoolean = z | ((C0027g2) it.next()).c.getAsBoolean();
        }
    }
}
