package dev.local.newsoul;

import android.widget.ScrollView;
import android.app.Activity;
import android.content.Intent;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/* 本文件合并了仅大小写不同的两个类 Y2 / y2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: Y2.class */
public final /* synthetic */ class Y2 implements Runnable {
    public final b3 a;
    public final C0067q2 b;
    public final ScrollView c;
    public final int d;

    public /* synthetic */ Y2(b3 b3Var, C0067q2 q2Var, ScrollView scrollView, int i) {
        this.a = b3Var;
        this.b = q2Var;
        this.c = scrollView;
        this.d = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        b3 b3Var = this.a;
        if (b3Var.m || b3Var.h != this.b) {
            return;
        }
        this.c.scrollTo(0, this.d);
    }
}

/* JADX INFO: loaded from: y2.class */
final /* synthetic */ class y2 implements XposedInterface.Hooker {
    public final int a;
    public final XposedModule b;

    public /* synthetic */ y2(XposedModule xposedModule, int i) {
        this.a = i;
        this.b = xposedModule;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            case 0:
                Activity activity = (Activity) chain.getThisObject();
                Q0.f(activity);
                Q0.n(this.b, activity, activity.getIntent(), "onCreate");
                break;
            default:
                Q0.n(this.b, (Activity) chain.getThisObject(), (Intent) chain.getArg(0), "onNewIntent");
                break;
        }
        return chain.proceed();
    }
}
