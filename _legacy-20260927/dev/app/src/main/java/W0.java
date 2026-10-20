package dev.local.newsoul;

import android.view.View;
import java.util.function.IntConsumer;

/* 本文件合并了仅大小写不同的两个类 W0 / w0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: W0.class */
public final class W0 {
    public final View a;
    public final Object b;

    public W0(View view, Object obj) {
        this.a = view;
        this.b = obj;
    }
}

/* JADX INFO: loaded from: w0.class */
final /* synthetic */ class w0 implements IntConsumer {
    public final C0096y0 a;
    public final C1 b;

    public /* synthetic */ w0(C0096y0 y0Var, C1 DialogInterfaceOnDismissListenerC0010c1) {
        this.a = y0Var;
        this.b = DialogInterfaceOnDismissListenerC0010c1;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        C0096y0 y0Var = this.a;
        if (i != y0Var.g && y0Var.e()) {
            y0Var.g = i;
            String[] strArr = C0096y0.h;
            this.b.b(strArr[i], strArr, true);
            y0Var.d();
        }
    }
}
