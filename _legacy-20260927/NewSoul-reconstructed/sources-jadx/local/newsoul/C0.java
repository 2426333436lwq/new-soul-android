package dev.local.newsoul;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ C0(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                ((int[]) this.b)[0] = 0;
                ((Runnable) this.c).run();
                break;
            case 1:
                Activity activity = (Activity) this.c;
                C0014d1 c0014d1 = (C0014d1) this.b;
                c0014d1.getClass();
                try {
                    c0014d1.b(activity);
                } catch (Throwable th) {
                    C0014d1.c(activity, th);
                    return;
                }
                break;
            case 2:
                Activity activity2 = (Activity) this.b;
                ClipboardManager clipboardManager = (ClipboardManager) activity2.getSystemService("clipboard");
                if (clipboardManager != null) {
                    P0 p0 = (P0) this.c;
                    StringBuilder sb = new StringBuilder(p0.b());
                    String strA = p0.a(true);
                    if (!strA.isEmpty()) {
                        sb.append("\n\n需要检查\n");
                        sb.append(strA);
                    }
                    String strA2 = p0.a(false);
                    if (!strA2.isEmpty()) {
                        sb.append("\n\n正常检测项\n");
                        sb.append(strA2);
                    }
                    sb.append("\n\n检测通过不代表功能实测通过。");
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("New Soul 功能兼容性检测", sb.toString()));
                    Toast.makeText(activity2, "检测结果已复制", 0).show();
                    break;
                }
                break;
            case 3:
                Switch r6 = (Switch) this.b;
                if (r6.isChecked() && r6.isEnabled()) {
                    ((Runnable) this.c).run();
                    break;
                }
                break;
            case 4:
                ((Consumer) this.b).accept((TextView) this.c);
                break;
            case 5:
                try {
                    ((c3) this.b).run();
                } catch (Throwable th2) {
                    d3.f(((C0067q2) this.c).a, "操作未完成，请重新打开语音库", th2);
                    return;
                }
                break;
            default:
                c3 c3Var = (c3) this.c;
                b3 b3Var = (b3) this.b;
                if (b3Var.n) {
                    b3Var.c();
                } else {
                    try {
                        b3Var.j();
                        c3Var.run();
                    } catch (Throwable unused) {
                        b3Var.b();
                    }
                }
                break;
        }
    }
}
