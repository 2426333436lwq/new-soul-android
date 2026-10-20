package dev.local.newsoul;

import android.util.Log;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

/* 本文件合并了仅大小写不同的两个类 U0 / u0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: U0.class */
public final /* synthetic */ class U0 implements Runnable {
    public final int a;
    public final W0 b;
    public final H2 c;

    public /* synthetic */ U0(W0 w0, H2 C0031h2, int i) {
        this.a = i;
        this.b = w0;
        this.c = C0031h2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                W0 w0 = this.b;
                w0.getClass();
                try {
                    Q0.K.invoke(w0.b, null);
                } catch (ReflectiveOperationException e) {
                    Log.w("SoulHook102", "loaded like UI refresh failed", e);
                }
                this.c.b(true);
                break;
            default:
                W0 C0089w1 = this.b;
                C0089w1.getClass();
                try {
                    Q0.K.invoke(C0089w1.b, null);
                } catch (ReflectiveOperationException C0019e2) {
                    Log.w("SoulHook102", "loaded like UI refresh failed", C0019e2);
                }
                this.c.b(false);
                break;
        }
    }
}

/* JADX INFO: loaded from: u0.class */
final /* synthetic */ class u0 implements View.OnClickListener {
    public final C0096y0 a;
    public final C0096y0 b;
    public final C0096y0 c;
    public final C0096y0 d;
    public final SharedPreferences e;
    public final C0067q2 f;
    public final Activity g;

    public /* synthetic */ u0(C0096y0 y0Var, C0096y0 y0Var2, C0096y0 y0Var3, C0096y0 y0Var4, SharedPreferences sharedPreferences, C0067q2 q2Var, Activity activity) {
        this.a = y0Var;
        this.b = y0Var2;
        this.c = y0Var3;
        this.d = y0Var4;
        this.e = sharedPreferences;
        this.f = q2Var;
        this.g = activity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        C0096y0 y0Var = this.a;
        boolean zF = y0Var.f();
        C0096y0 y0Var2 = this.b;
        boolean zF2 = y0Var2.f();
        C0096y0 y0Var3 = this.c;
        boolean zF3 = y0Var3.f();
        C0096y0 y0Var4 = this.d;
        boolean zF4 = y0Var4.f();
        if (zF && zF2 && zF3 && zF4) {
            SharedPreferences.Editor editorEdit = this.e.edit();
            y0Var.a(editorEdit);
            y0Var2.a(editorEdit);
            y0Var3.a(editorEdit);
            y0Var4.a(editorEdit);
            editorEdit.apply();
            AbstractC0061p0.h();
            this.f.e.dismiss();
            Toast.makeText(this.g, "已保存", 0).show();
        }
    }
}
