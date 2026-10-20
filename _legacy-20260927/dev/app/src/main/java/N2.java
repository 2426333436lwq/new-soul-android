package dev.local.newsoul;

import io.github.libxposed.api.XposedInterface;
import java.lang.ref.WeakReference;
import android.content.DialogInterface;
import android.view.KeyEvent;

/* 本文件合并了仅大小写不同的两个类 N2 / n2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: N2.class */
public final /* synthetic */ class N2 implements XposedInterface.Hooker {
    public final int a;
    public final d3 b;

    public /* synthetic */ N2(d3 d3Var, int i) {
        this.a = i;
        this.b = d3Var;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        d3 d3Var = this.b;
        switch (this.a) {
            case 0:
                Object objProceed = chain.proceed();
                WeakReference weakReference = new WeakReference(chain.getThisObject());
                for (int i = 0; i < 3; i++) {
                    d3Var.a.postDelayed(new RunnableC0024g(weakReference, d3Var, 14), new long[]{0, 350, 1000}[i]);
                }
                return objProceed;
            default:
                b3 b3Var = d3Var.f;
                if (b3Var != null && b3Var.a == chain.getThisObject()) {
                    d3Var.f.b();
                }
                return chain.proceed();
        }
    }
}

/* JADX INFO: loaded from: n2.class */
final /* synthetic */ class n2 implements DialogInterface.OnKeyListener {
    public final C0067q2 a;

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        boolean z;
        C0067q2 q2Var = this.a;
        q2Var.getClass();
        if (i == 4 || i == 111) {
            if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                q2Var.b();
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }


    public n2(dev.local.newsoul.C0067q2 p0) {
        super();
        this.a = p0;
    }
}
