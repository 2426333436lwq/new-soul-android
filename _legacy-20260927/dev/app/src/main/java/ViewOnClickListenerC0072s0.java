package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: dev.local.newsoul.ViewOnClickListenerC0072s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC0072s0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ C0067q2 b;

    public /* synthetic */ ViewOnClickListenerC0072s0(C0067q2 c0067q2, int i) {
        this.a = i;
        this.b = c0067q2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        switch (this.a) {
            case 0:
                C0067q2 c0067q2 = this.b;
                AbstractC0100z0.a(c0067q2.a, c0067q2.b);
                break;
            case 1:
                C0067q2 c0067q3 = this.b;
                Activity activity = c0067q3.a;
                try {
                    String strD = AbstractC0081u1.d();
                    boolean z = c0067q3.b;
                    C0067q2 c0067q4 = new C0067q2(activity, z);
                    c0067q4.t = "点击昵称打开主页，长按记录可移除。插眼按登录账号分别保存在本机。";
                    c0067q4.h.addView(AbstractC0090w2.n(c0067q4.g, "正在加载…", 14, c0067q4.j));
                    c0067q4.g("插眼列表", "关闭");
                    RunnableC0062p1 runnableC0062p1 = new RunnableC0062p1(strD, activity, c0067q4, z, new Runnable[]{runnableC0062p1}, 0);
                    runnableC0062p1.run();
                } catch (Exception e) {
                    AbstractC0081u1.e(activity, e);
                    return;
                }
                break;
            case 2:
                C0067q2 c0067q5 = this.b;
                AbstractC0090w2.t(c0067q5.a, c0067q5.b, false, null);
                break;
            case 3:
                this.b.f();
                break;
            default:
                C0067q2 c0067q6 = this.b;
                Activity activity2 = c0067q6.a;
                C0067q2 c0067q7 = new C0067q2(activity2, c0067q6.b);
                try {
                    String str2 = activity2.getPackageManager().getPackageInfo("dev.local.newsoul.public", 0).versionName;
                    T0.c(str2, str2);
                    str = str2;
                } catch (Exception unused) {
                    str = null;
                }
                c0067q7.t = "点击蓝奏云打开下载页面，提取码：3gin。下载 ZIP 后解压，使用其中的 APK 覆盖安装。检查更新不会自动下载或安装。".concat("");
                LinearLayout linearLayout = c0067q7.h;
                String strConcat = "当前版本：".concat(str == null ? "未知" : str);
                Context context = c0067q7.g;
                int i = c0067q7.i;
                linearLayout.addView(AbstractC0090w2.n(context, strConcat, 15, i));
                TextView textViewN = AbstractC0090w2.n(context, "正在检查…", 14, i);
                textViewN.setPadding(0, AbstractC0090w2.i(context, 8), 0, AbstractC0090w2.i(context, 8));
                linearLayout.addView(textViewN);
                String[] strArr = {null};
                RunnableC0024g runnableC0024g = new RunnableC0024g(activity2, strArr, 13);
                TextView textViewN2 = AbstractC0090w2.n(context, "蓝奏云", 15, c0067q7.k);
                textViewN2.setMinHeight(AbstractC0090w2.i(context, 52));
                textViewN2.setGravity(17);
                AbstractC0090w2.g(textViewN2);
                textViewN2.setOnClickListener(new ViewOnClickListenerC0033i0(runnableC0024g, 3));
                linearLayout.addView(textViewN2, new LinearLayout.LayoutParams(-1, -2));
                c0067q7.d.setNegativeButton("重新检查", (DialogInterface.OnClickListener) null);
                c0067q7.g("检查更新", "返回");
                Button button = c0067q7.e.getButton(-2);
                Thread[] threadArr = {null};
                I1 i1 = new I1(button, strArr, textViewN, threadArr, activity2, c0067q7, str);
                button.setOnClickListener(new ViewOnClickListenerC0033i0(i1, 2));
                c0067q7.e.setOnDismissListener(new DialogInterfaceOnDismissListenerC0037j0(2, threadArr));
                i1.run();
                break;
        }
    }
}
