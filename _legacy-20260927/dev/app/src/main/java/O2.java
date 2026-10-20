package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import java.lang.ref.WeakReference;
import android.content.DialogInterface;

/* 本文件合并了仅大小写不同的两个类 O2 / o2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: O2.class */
public final /* synthetic */ class O2 implements View.OnLongClickListener {
    public final d3 a;
    public final WeakReference b;
    public final Activity c;

    public /* synthetic */ O2(d3 d3Var, WeakReference weakReference, Activity activity) {
        this.a = d3Var;
        this.b = weakReference;
        this.c = activity;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        boolean z = false;
        WeakReference weakReference = this.b;
        d3 d3Var = this.a;
        d3Var.getClass();
        try {
            Object obj = weakReference.get();
            if (obj == null) {
                z = false;
            } else {
                b3 b3Var = new b3(d3Var, obj);
                b3 b3Var2 = d3Var.f;
                if (b3Var2 != null) {
                    b3Var2.b();
                }
                d3Var.f = b3Var;
                b3Var.f();
                z = true;
            }
        } catch (Throwable th) {
            d3.f(this.c, "暂时无法打开语音库", th);
        }
        return z;
    }
}

/* JADX INFO: loaded from: o2.class */
final /* synthetic */ class o2 implements DialogInterface.OnClickListener {
    public final C0067q2 a;

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.e.dismiss();
    }


    public o2(dev.local.newsoul.C0067q2 p0) {
        super();
        this.a = p0;
    }
}
