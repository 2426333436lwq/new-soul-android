package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class A0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ A0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                ((Dialog) this.b).dismiss();
                break;
            case 1:
                ((Runnable[]) this.b)[0].run();
                break;
            case 2:
                Activity activity = (Activity) this.b;
                try {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/+3Hl2lJ1X-oZlZTZl")));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(activity, "没有可打开链接的应用", 0).show();
                    return;
                }
                break;
            case 3:
                ((BooleanSupplier) this.b).getAsBoolean();
                break;
            case 4:
                I2 i2 = (I2) this.b;
                if (i2.b()) {
                    Toast.makeText(i2.a, "自动点赞已开启，长按爱心可关闭", 0).show();
                } else if (!i2.m) {
                    i2.l(false);
                } else {
                    i2.e(true);
                }
                break;
            default:
                b3 b3Var = (b3) this.b;
                if (b3Var.n) {
                    b3Var.c();
                } else {
                    try {
                        b3Var.f();
                    } catch (Throwable unused2) {
                        b3Var.b();
                    }
                }
                break;
        }
    }
}
