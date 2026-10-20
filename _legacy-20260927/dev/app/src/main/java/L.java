package dev.local.newsoul;

import android.content.DialogInterface;
import io.github.libxposed.api.XposedModule;
import android.app.Activity;
import android.view.ViewTreeObserver;

/* 本文件合并了仅大小写不同的两个类 L / l（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: L.class */
public final /* synthetic */ class L implements DialogInterface.OnClickListener {
    public final int a;

    private final void a(DialogInterface dialogInterface, int i) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a) {
            case 0:
                XposedModule xposedModule = S.a;
                break;
        }
    }


    public L(int p0) {
        super();
        this.a = p0;
    }
}

/* JADX INFO: loaded from: l.class */
final /* synthetic */ class l implements ViewTreeObserver.OnGlobalLayoutListener {
    public final Activity a;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        AbstractC0056o.a(this.a);
    }


    public l(android.app.Activity p0) {
        super();
        this.a = p0;
    }
}
