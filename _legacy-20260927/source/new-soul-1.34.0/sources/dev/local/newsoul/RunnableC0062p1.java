package dev.local.newsoul;

import android.app.Activity;
import java.util.ArrayList;

/* JADX INFO: renamed from: dev.local.newsoul.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0062p1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Activity c;
    public final /* synthetic */ C0067q2 d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Runnable[] f;

    public /* synthetic */ RunnableC0062p1(String str, Activity activity, C0067q2 c0067q2, boolean z, Runnable[] runnableArr, int i) {
        this.a = i;
        this.b = str;
        this.c = activity;
        this.d = c0067q2;
        this.e = z;
        this.f = runnableArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayListH;
        switch (this.a) {
            case 0:
                AbstractC0081u1.b.execute(new RunnableC0062p1(this.b, this.c, this.d, this.e, this.f, 1));
                return;
            default:
                String str = this.b;
                Activity activity = this.c;
                C0067q2 c0067q2 = this.d;
                boolean z = this.e;
                Runnable[] runnableArr = this.f;
                try {
                    AbstractC0081u1.h(str);
                    C0093x1 c0093x1 = new C0093x1(activity.getFilesDir(), str, 0);
                    synchronized (C0093x1.b) {
                        arrayListH = c0093x1.h();
                        break;
                    }
                    AbstractC0081u1.a.post(new RunnableC0054n1(c0067q2, str, arrayListH, activity, z, runnableArr));
                    return;
                } catch (Exception e) {
                    AbstractC0081u1.a.post(new H(c0067q2, runnableArr, e, 8));
                    return;
                }
        }
    }
}
