package dev.local.newsoul;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;
import java.util.function.BooleanSupplier;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/* 本文件合并了仅大小写不同的两个类 Q1 / q1（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: Q1.class */
public final /* synthetic */ class Q1 implements CompoundButton.OnCheckedChangeListener {
    public final C0027g2 a;
    public final BooleanSupplier b;
    public final Runnable c;
    public final SharedPreferences d;

    public /* synthetic */ Q1(C0027g2 g2Var, L1 ViewOnClickListenerC0046l1, RunnableC0050m1 m1Var, SharedPreferences sharedPreferences) {
        this.a = g2Var;
        this.b = ViewOnClickListenerC0046l1;
        this.c = m1Var;
        this.d = sharedPreferences;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        C0027g2 g2Var = this.a;
        if (g2Var.d) {
            return;
        }
        if (!this.b.getAsBoolean()) {
            this.c.run();
            return;
        }
        SharedPreferences.Editor editorEdit = this.d.edit();
        String str = g2Var.a;
        editorEdit.putBoolean(str, z).apply();
        AbstractC0036j.i.post(new RunnableC0028h(0));
        Log.i("SoulHook102", "setting changed: " + str + "=" + z);
    }
}

/* JADX INFO: loaded from: q1.class */
final /* synthetic */ class q1 implements View.OnLongClickListener {
    public final Activity a;
    public final boolean b;
    public final TextView c;
    public final String d;
    public final C0089w1 e;
    public final C0067q2 f;
    public final Runnable[] g;

    public /* synthetic */ q1(Activity activity, boolean z, TextView textView, String str, C0089w1 w1Var, C0067q2 q2Var, Runnable[] runnableArr) {
        this.a = activity;
        this.b = z;
        this.c = textView;
        this.d = str;
        this.e = w1Var;
        this.f = q2Var;
        this.g = runnableArr;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        Activity activity = this.a;
        C0067q2 q2Var = new C0067q2(activity, this.b);
        TextView textView = this.c;
        q2Var.h(textView, new String[]{"移除插眼"}, true, new r1(textView, this.d, activity, this.e, this.f, this.g));
        return true;
    }
}
