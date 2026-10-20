package dev.local.newsoul;

import android.graphics.RenderNode;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

/* 本文件合并了仅大小写不同的两个类 H0 / h0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: H0.class */
public abstract /* synthetic */ class H0 {
    public static /* synthetic */ RenderNode d() {
        return new RenderNode("NewSoul glass backdrop");
    }


    public static int a(android.graphics.Insets p0) {
        return p0.top;
    }

    public static int b(android.graphics.drawable.StateListDrawable p0) {
        return p0.getStateCount();
    }

    public static android.graphics.RecordingCanvas c(android.graphics.RenderNode p0, int p1, int p2) {
        return p0.beginRecording(p1, p2);
    }

    public static android.graphics.drawable.Drawable e(android.graphics.drawable.StateListDrawable p0, int p1) {
        return p0.getStateDrawable(p1);
    }

    public static void f(android.graphics.Canvas p0, android.graphics.RenderNode p1) {
        p0.drawRenderNode(p1);
        return;
    }

    public static void g(android.graphics.RenderNode p0) {
        p0.discardDisplayList();
        return;
    }

    public static void h(android.graphics.RenderNode p0, int p1, int p2) {
        p0.setPosition(0, 0, p1, p2);
        return;
    }

    public static int i(android.graphics.Insets p0) {
        return p0.bottom;
    }

    public static void j(android.graphics.RenderNode p0) {
        p0.endRecording();
        return;
    }
}

/* JADX INFO: loaded from: h0.class */
final /* synthetic */ class h0 implements Runnable {
    public final int a;
    public final Activity b;
    public final C0067q2 c;
    public final ImageView d;
    public final TextView e;
    public final TextView f;
    public final TextView g;

    public /* synthetic */ h0(Activity activity, C0067q2 q2Var, ImageView imageView, TextView textView, TextView textView2, TextView textView3, int i) {
        this.a = i;
        this.b = activity;
        this.c = q2Var;
        this.d = imageView;
        this.e = textView;
        this.f = textView2;
        this.g = textView3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                Q0.V(this.b, this.c, this.d, this.e, this.f, this.g, "donation_wechat", "微信收款码");
                break;
            default:
                Q0.V(this.b, this.c, this.d, this.e, this.f, this.g, "donation_alipay", "支付宝收款码");
                break;
        }
    }
}
