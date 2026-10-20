package dev.local.newsoul;

import android.view.View;
import android.widget.CompoundButton;

/* 本文件合并了仅大小写不同的两个类 B0 / b0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: B0.class */
public final /* synthetic */ class B0 implements View.OnClickListener {
    public final int[] a;
    public final int b;
    public final Runnable c;

    public /* synthetic */ B0(int[] iArr, int i, Z z) {
        this.a = iArr;
        this.b = i;
        this.c = z;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a[0] = this.b;
        this.c.run();
        view.performHapticFeedback(4);
    }
}

/* JADX INFO: loaded from: b0.class */
final /* synthetic */ class b0 implements CompoundButton.OnCheckedChangeListener {
    public final C0021f0 a;

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        C0021f0 f0Var = this.a;
        if (f0Var.g || f0Var.h) {
            return;
        }
        f0Var.h = true;
        f0Var.b();
        V.a.execute(new U(z, new RunnableC0013d0(f0Var, 0)));
    }


    public b0(dev.local.newsoul.C0021f0 p0) {
        super();
        this.a = p0;
    }
}
