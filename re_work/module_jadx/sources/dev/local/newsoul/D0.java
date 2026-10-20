package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class D0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Serializable b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ D0(Serializable serializable, Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = serializable;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        switch (this.a) {
            case 0:
                int[] iArr = (int[]) this.b;
                if (iArr[0] < 0) {
                    return;
                }
                ((TextView) this.c).setEnabled(false);
                ((Dialog) this.d).dismiss();
                int iNextInt = iArr[0];
                C0071s c0071s = (C0071s) this.e;
                c0071s.getClass();
                ThreadLocal threadLocal = AbstractC0075t.c;
                r rVar = c0071s.a;
                if (rVar != null) {
                    Method method = (Method) rVar.b;
                    Object obj = rVar.c;
                    Activity activity = (Activity) rVar.a;
                    if (activity.isFinishing() || activity.isDestroyed()) {
                        return;
                    }
                    try {
                        threadLocal.set(Integer.valueOf(iNextInt));
                        AbstractC0075t.f.getInvoker(method).invoke(obj, new Object[0]);
                        break;
                    } catch (Throwable th) {
                        try {
                            AbstractC0075t.f.log(6, "SoulHook102", "selected game send failed", th);
                            Toast.makeText(activity, "发送未完成，请检查聊天页面", 0).show();
                        } finally {
                            threadLocal.remove();
                        }
                        break;
                    }
                    return;
                }
                boolean z2 = c0071s.b;
                if (iNextInt == 0) {
                    iNextInt = new SecureRandom().nextInt(z2 ? 6 : 3) + 1;
                }
                try {
                    threadLocal.set(Integer.valueOf(iNextInt));
                    z = AbstractC0075t.i.getInt(AbstractC0075t.h.newInstance(0)) == iNextInt;
                    String str = "game local dry-run: result=" + iNextInt + " verified=" + z + "; no message sent";
                    Log.i("SoulHook102", str);
                    AbstractC0075t.f.log(4, "SoulHook102", str);
                    threadLocal.remove();
                } catch (Throwable th2) {
                    try {
                        AbstractC0075t.f.log(6, "SoulHook102", "game local dry-run failed", th2);
                        threadLocal.remove();
                        z = false;
                    } catch (Throwable th3) {
                        threadLocal.remove();
                        throw th3;
                    }
                }
                Toast.makeText(c0071s.c, z ? (z2 ? iNextInt + " 点" : AbstractC0075t.g[iNextInt - 1]) + " · 未发送" : "预览校验失败", 0).show();
                return;
            default:
                String str2 = (String) this.b;
                C0089w1 c0089w1 = (C0089w1) this.c;
                C0067q2 c0067q2 = (C0067q2) this.d;
                try {
                    AbstractC0081u1.h(str2);
                    if (!AbstractC0081u1.f) {
                        throw new IllegalStateException("当前版本暂无法打开主页");
                    }
                    AbstractC0081u1.e.invoke(null, c0089w1.a, "newsoul_bookmark");
                    c0067q2.e.dismiss();
                    return;
                } catch (Exception e) {
                    AbstractC0081u1.e((Activity) this.e, e);
                    return;
                }
        }
    }
}
