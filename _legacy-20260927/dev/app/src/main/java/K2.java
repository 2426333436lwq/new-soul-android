package dev.local.newsoul;

import android.window.OnBackInvokedCallback;

/* 本文件合并了仅大小写不同的两个类 K2 / k2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: K2.class */
public abstract /* synthetic */ class K2 {
}

/* JADX INFO: loaded from: k2.class */
final /* synthetic */ class k2 implements OnBackInvokedCallback {
    public final C0067q2 a;

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.a.b();
    }


    public k2(dev.local.newsoul.C0067q2 p0) {
        super();
        this.a = p0;
    }
}
