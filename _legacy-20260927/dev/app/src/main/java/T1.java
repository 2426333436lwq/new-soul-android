package dev.local.newsoul;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: T1.class */
public final /* synthetic */ class T1 implements Runnable {
    public final Activity a;
    public final C0067q2 b;
    public final LinearLayout c;
    public final boolean d;
    public final String e;
    public final long f;
    public final boolean g;

    public /* synthetic */ T1(Activity activity, C0067q2 q2Var, LinearLayout linearLayout, boolean z, String str, long j, boolean z2) {
        this.a = activity;
        this.b = q2Var;
        this.c = linearLayout;
        this.d = z;
        this.e = str;
        this.f = j;
        this.g = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity = this.a;
        C0067q2 q2Var = this.b;
        try {
            P0 p0U = Q0.U(activity);
            boolean z = this.d;
            if (z) {
                int i = p0U.d;
                p0U = new P0(p0U.a, this.e, (ArrayList) p0U.c, i);
            }
            activity.getWindow().getDecorView().postDelayed(new Y1(q2Var, activity, this.g, p0U, z), Math.max(0L, 450 - (SystemClock.elapsedRealtime() - this.f)));
        } catch (Throwable th) {
            Log.e("SoulHook102", "Hook diagnostics failed", th);
            activity.runOnUiThread(new H(q2Var, activity, this.c, 11));
        }
    }
}
