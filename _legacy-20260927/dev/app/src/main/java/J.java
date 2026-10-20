package dev.local.newsoul;

import android.app.Activity;

/* JADX INFO: loaded from: J.class */
public final /* synthetic */ class J implements Runnable {
    public final int a;
    public final Activity b;
    public final P c;

    public /* synthetic */ J(Activity activity, P p, int i) {
        this.a = i;
        this.b = activity;
        this.c = p;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                S.b(this.b, this.c, "配置已导出");
                break;
            default:
                P p = this.c;
                S.b(this.b, p, p.c != null ? "导出失败，请重新选择保存位置" : "导入失败：文件无法读取、过大或格式无效，请重新选择");
                break;
        }
    }
}
