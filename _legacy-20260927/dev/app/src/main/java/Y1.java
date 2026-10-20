package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: Y1.class */
public final /* synthetic */ class Y1 implements Runnable {
    public final C0067q2 a;
    public final Activity b;
    public final boolean c;
    public final P0 d;
    public final boolean e;

    public /* synthetic */ Y1(C0067q2 q2Var, Activity activity, boolean z, P0 AbstractC0061p0, boolean z2) {
        this.a = q2Var;
        this.b = activity;
        this.c = z;
        this.d = AbstractC0061p0;
        this.e = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0067q2 q2Var = this.a;
        if (q2Var.e.isShowing()) {
            Activity activity = this.b;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            q2Var.e.dismiss();
            boolean z = this.c;
            C0067q2 q2Var2 = new C0067q2(activity, z);
            LinearLayout linearLayoutR = AbstractC0090w2.r(q2Var2, this.e ? "Soul 更新检测结果" : "宿主兼容检测", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", (K1) null);
            P0 AbstractC0061p0 = this.d;
            String strB = AbstractC0061p0.b();
            Context context = q2Var2.g;
            int i = q2Var2.i;
            TextView textViewN = AbstractC0090w2.n(context, strB, 16, i);
            textViewN.setTextIsSelectable(true);
            linearLayoutR.addView(textViewN);
            String strA = AbstractC0061p0.a(true);
            if (!strA.isEmpty()) {
                TextView textViewN2 = AbstractC0090w2.n(context, strA, 14, i);
                textViewN2.setPadding(0, AbstractC0090w2.i(activity, 12), 0, AbstractC0090w2.i(activity, 12));
                textViewN2.setTextIsSelectable(true);
                linearLayoutR.addView(textViewN2);
            }
            String strA2 = AbstractC0061p0.a(false);
            boolean zIsEmpty = strA2.isEmpty();
            int C0035i2 = q2Var2.k;
            if (!zIsEmpty) {
                TextView textViewN3 = AbstractC0090w2.n(context, "正常检测项", 15, C0035i2);
                textViewN3.setMinHeight(AbstractC0090w2.i(context, 48));
                textViewN3.setGravity(16);
                AbstractC0090w2.g(textViewN3);
                TextView textViewN4 = AbstractC0090w2.n(context, strA2, 13, i);
                textViewN4.setTextIsSelectable(true);
                textViewN4.setVisibility(8);
                textViewN3.setContentDescription("正常检测项，展开");
                textViewN3.setOnClickListener(new G1(textViewN4, textViewN3));
                linearLayoutR.addView(textViewN3, new LinearLayout.LayoutParams(-1, -2));
                linearLayoutR.addView(textViewN4, new LinearLayout.LayoutParams(-1, -2));
            }
            AbstractC0090w2.c(linearLayoutR, "已注册不代表功能实测通过。");
            TextView textViewN5 = AbstractC0090w2.n(context, "复制检测结果", 15, C0035i2);
            textViewN5.setGravity(17);
            textViewN5.setMinHeight(AbstractC0090w2.i(activity, 48));
            AbstractC0090w2.g(textViewN5);
            textViewN5.setOnClickListener(new C0(activity, AbstractC0061p0, 2));
            linearLayoutR.addView(textViewN5);
            q2Var2.d.setNeutralButton((CharSequence) "重新检测", (DialogInterface.OnClickListener) new J1(activity, z));
            q2Var2.g("功能兼容性检测", "返回");
            activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.C0085v1", 0).edit().putString("hook_diagnostics_last_shown_version", AbstractC0061p0.a).apply();
        }
    }
}
