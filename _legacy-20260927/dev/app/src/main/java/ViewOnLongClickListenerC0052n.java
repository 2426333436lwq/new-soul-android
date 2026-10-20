package dev.local.newsoul;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/* JADX INFO: renamed from: dev.local.newsoul.ViewOnLongClickListenerC0052n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnLongClickListenerC0052n implements View.OnLongClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ViewOnLongClickListenerC0052n(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        switch (this.a) {
            case 0:
                Activity activity = (Activity) this.b;
                if (activity.isFinishing() || activity.isDestroyed() || !view.isShown()) {
                    return false;
                }
                AbstractC0090w2.u(activity, AbstractC0056o.b(activity));
                return true;
            default:
                boolean z = Q0.B;
                I2 i2 = (I2) this.b;
                Activity activity2 = i2.a;
                if (z) {
                    boolean z2 = !i2.b();
                    AbstractC0090w2.o(activity2).edit().putBoolean("square_auto_like", z2).apply();
                    i2.e(false);
                    int size = i2.k().size();
                    E2 e2 = i2.c;
                    e2.a(size, false);
                    F2 f2 = i2.u;
                    e2.removeCallbacks(f2);
                    if (i2.l) {
                        e2.post(f2);
                    }
                    Toast.makeText(activity2, z2 ? "自动点赞已开启" : "自动点赞已关闭", 0).show();
                    Log.i("SoulHook102", "square auto like toggled by long press: enabled=" + z2);
                } else {
                    Toast.makeText(activity2, "当前版本自动点赞未就绪", 0).show();
                }
                return true;
        }
    }
}
