package dev.local.newsoul;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import java.util.function.IntConsumer;
import android.app.Activity;
import android.widget.TextView;

/* 本文件合并了仅大小写不同的两个类 R1 / r1（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: R1.class */
public final /* synthetic */ class R1 implements IntConsumer {
    public final SharedPreferences a;
    public final String[] b;
    public final C1 c;

    public /* synthetic */ R1(SharedPreferences sharedPreferences, String[] strArr, C1 DialogInterfaceOnDismissListenerC0010c1) {
        this.a = sharedPreferences;
        this.b = strArr;
        this.c = DialogInterfaceOnDismissListenerC0010c1;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        String[] strArr = this.b;
        editorEdit.putString("chat_recall_style", strArr[i]).apply();
        this.c.b(strArr[i], strArr, true);
        if (Q0.y == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new RunnableC0028h(1));
    }
}

/* JADX INFO: loaded from: r1.class */
final /* synthetic */ class r1 implements IntConsumer {
    public final TextView a;
    public final String b;
    public final Activity c;
    public final C0089w1 d;
    public final C0067q2 e;
    public final Runnable[] f;

    public /* synthetic */ r1(TextView textView, String str, Activity activity, C0089w1 w1Var, C0067q2 q2Var, Runnable[] runnableArr) {
        this.a = textView;
        this.b = str;
        this.c = activity;
        this.d = w1Var;
        this.e = q2Var;
        this.f = runnableArr;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        TextView textView = this.a;
        textView.setEnabled(false);
        AbstractC0081u1.b.execute(new RunnableC0058o1(this.b, this.c, this.d, this.e, this.f, textView, 1));
    }
}
