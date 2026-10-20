package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.PopupWindow;

/* 本文件合并了仅大小写不同的两个类 M2 / m2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: M2.class */
public final /* synthetic */ class M2 implements Runnable {
    public final Activity a;
    public final C0067q2 b;
    public final TextView c;
    public final String d;
    public final TextView e;
    public final boolean f = false;
    public final Y g;
    public final String h;
    public final String[] i;

    public /* synthetic */ M2(Activity activity, C0067q2 q2Var, Button button, String str, TextView textView, Y y, String str2, String[] strArr) {
        this.a = activity;
        this.b = q2Var;
        this.c = button;
        this.d = str;
        this.e = textView;
        this.g = y;
        this.h = str2;
        this.i = strArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertDialog alertDialog;
        String str;
        Activity activity = this.a;
        if (activity.isFinishing() || activity.isDestroyed() || (alertDialog = this.b.e) == null || !alertDialog.isShowing()) {
            return;
        }
        TextView textView = this.c;
        textView.setEnabled(true);
        textView.setAlpha(1.0f);
        String str2 = this.d;
        TextView textView2 = this.e;
        if (str2 != null) {
            textView2.setText("检测失败，请重试");
            return;
        }
        boolean z = this.f;
        Y y = this.g;
        String str3 = this.h;
        if (z) {
            textView2.setText("发布渠道版本：" + ((String) y.a));
        } else if (str3 == null) {
            textView2.setText("最新版本：" + ((String) y.a) + "\n当前版本未知");
        } else {
            int iC = T0.c((String) y.a, str3);
            if (iC > 0) {
                str = "发现新版本";
            } else {
                str = iC == 0 ? "当前已是最新版" : "当前版本高于发布版本";
            }
            textView2.setText("最新版本：" + ((String) y.a) + "\n" + str);
        }
        if (z || str3 == null || T0.c((String) y.a, str3) > 0) {
            this.i[0] = (String) y.b;
        }
    }
}

/* JADX INFO: loaded from: m2.class */
final /* synthetic */ class m2 implements PopupWindow.OnDismissListener {
    public final View a;
    public final View.OnAttachStateChangeListener b;

    public /* synthetic */ m2(View view, ViewOnAttachStateChangeListenerC0063p2 p2Var) {
        this.a = view;
        this.b = p2Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.a.removeOnAttachStateChangeListener(this.b);
    }
}
