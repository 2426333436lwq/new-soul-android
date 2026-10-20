package dev.local.newsoul;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.function.Consumer;
import java.util.WeakHashMap;

/* 本文件合并了仅大小写不同的两个类 C0 / c0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: C0.class */
public final /* synthetic */ class C0 implements View.OnClickListener {
    public final int a;
    public final Object b;
    public final Object c;

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
                C0014d1 d1Var = (C0014d1) this.b;
                d1Var.getClass();
                try {
                    d1Var.b(activity);
                } catch (Throwable th) {
                    C0014d1.c(activity, th);
                    return;
                }
                break;
            case 2:
                Activity activity2 = (Activity) this.b;
                ClipboardManager clipboardManager = (ClipboardManager) activity2.getSystemService("clipboard");
                if (clipboardManager != null) {
                    P0 AbstractC0061p0 = (P0) this.c;
                    StringBuilder sb = new StringBuilder(AbstractC0061p0.b());
                    String strA = AbstractC0061p0.a(true);
                    if (!strA.isEmpty()) {
                        sb.append("\n\n需要检查\n");
                        sb.append(strA);
                    }
                    String strA2 = AbstractC0061p0.a(false);
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
                Switch AbstractC0068r0 = (Switch) this.b;
                if (AbstractC0068r0.isChecked() && AbstractC0068r0.isEnabled()) {
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
                    } catch (Throwable th3) {
                        b3Var.b();
                    }
                }
                break;
        }
    }
}

/* JADX INFO: loaded from: c0.class */
final /* synthetic */ class c0 implements View.OnClickListener {
    public final int a;
    public final C0021f0 b;

    public /* synthetic */ c0(C0021f0 f0Var, int i) {
        this.a = i;
        this.b = f0Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                C0021f0 f0Var = this.b;
                Activity activity = f0Var.a.a;
                WeakHashMap weakHashMap = AbstractC0025g0.d;
                if (!weakHashMap.containsKey(activity)) {
                    C0001a0 a0Var = new C0001a0(f0Var);
                    weakHashMap.put(activity, a0Var);
                    f0Var.a("正在准备记录…");
                    V.a.execute(new H(activity, f0Var, a0Var, 3));
                    break;
                }
                break;
            default:
                C0021f0 f0Var2 = this.b;
                f0Var2.h = true;
                f0Var2.b();
                V.a.execute(new T(0, new RunnableC0013d0(f0Var2, 1)));
                break;
        }
    }
}
