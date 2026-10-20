package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.Toast;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: dev.local.newsoul.RunnableC0064q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0064q implements Runnable {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ KeyEvent.Callback b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ RunnableC0064q(Activity activity, boolean z, Method method, Object obj) {
        this.b = activity;
        this.c = z;
        this.e = method;
        this.d = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zEquals;
        switch (this.a) {
            case 0:
                Activity activity = (Activity) this.b;
                AbstractC0075t.b(activity, new r(activity, (Method) this.e, this.d), this.c);
                return;
            case 1:
                Object obj = this.d;
                C0077t1 c0077t1 = (C0077t1) this.e;
                c0077t1.g = false;
                Activity activity2 = (Activity) this.b;
                if (AbstractC0081u1.b(activity2)) {
                    try {
                        zEquals = c0077t1.a.equals(AbstractC0081u1.d());
                        break;
                    } catch (Exception unused) {
                        zEquals = false;
                    }
                    if (zEquals) {
                        String str = this.c ? "已取消插眼" : "已插眼";
                        if (AbstractC0081u1.b(activity2)) {
                            Toast.makeText(activity2, str, 0).show();
                        }
                        try {
                            AbstractC0081u1.c(obj, "dismissAllowingStateLoss");
                            return;
                        } catch (Exception unused2) {
                            return;
                        }
                    }
                    return;
                }
                return;
            default:
                boolean z = ((SharedPreferences) this.e).getBoolean((String) this.d, this.c);
                C0070r2 c0070r2 = (C0070r2) this.b;
                c0070r2.b = true;
                try {
                    c0070r2.setChecked(z);
                    return;
                } finally {
                    c0070r2.b = false;
                }
        }
    }

    public /* synthetic */ RunnableC0064q(C0077t1 c0077t1, Activity activity, boolean z, Object obj) {
        this.e = c0077t1;
        this.b = activity;
        this.c = z;
        this.d = obj;
    }

    public /* synthetic */ RunnableC0064q(C0070r2 c0070r2, SharedPreferences sharedPreferences, String str, boolean z) {
        this.b = c0070r2;
        this.e = sharedPreferences;
        this.d = str;
        this.c = z;
    }
}
