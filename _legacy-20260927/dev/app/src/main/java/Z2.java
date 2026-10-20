package dev.local.newsoul;

import android.view.View;
import io.github.libxposed.api.XposedInterface;
import java.util.WeakHashMap;

/* 本文件合并了仅大小写不同的两个类 Z2 / z2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: Z2.class */
public final /* synthetic */ class Z2 implements View.OnClickListener {
    public final int a;
    public final b3 b;
    public final h3 c;

    public /* synthetic */ Z2(b3 b3Var, h3 h3Var, int i) {
        this.a = i;
        this.b = b3Var;
        this.c = h3Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                h3 h3Var = this.c;
                b3 b3Var = this.b;
                if (b3Var.n) {
                    b3Var.c();
                } else {
                    try {
                        b3Var.d(h3Var);
                    } catch (Throwable th) {
                        b3Var.b();
                        return;
                    }
                }
                break;
            default:
                h3 h3Var2 = this.c;
                b3 b3Var2 = this.b;
                if (!b3Var2.n) {
                    try {
                        b3Var2.j();
                        b3Var2.a(new Q2(b3Var2, h3Var2, 3), new U2(3, b3Var2));
                    } catch (Throwable th2) {
                        d3.f(b3Var2.c, "删除失败", th2);
                    }
                    break;
                }
                break;
        }
    }
}

/* JADX INFO: loaded from: z2.class */
final /* synthetic */ class z2 implements XposedInterface.Hooker {
    public final int a;
    public final Class b;

    public /* synthetic */ z2(Class cls, int i) {
        this.a = i;
        this.b = cls;
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [dev.local.newsoul.A2, java.lang.Object] */
    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            case 0:
                View view = (View) this.b.getField("itemView").get(chain.getArg(0));
                WeakHashMap weakHashMap = B2.k;
                B2.e(view, (A2) weakHashMap.remove(view));
                Object objProceed = chain.proceed();
                A2 obj = new A2();
                ((A2) obj).a = chain.getArg(1);
                weakHashMap.put(view, obj);
                B2.b(view, (A2) obj);
                view.post(new RunnableC0024g(view, (Object) obj, 12));
                return objProceed;
            case 1:
                View view2 = (View) this.b.getField("itemView").get(chain.getArg(0));
                B2.e(view2, (A2) B2.k.remove(view2));
                return chain.proceed();
            default:
                Object objProceed2 = chain.proceed();
                View view3 = (View) this.b.getField("itemView").get(chain.getArg(0));
                A2 C0003a2 = (A2) B2.k.get(view3);
                if (C0003a2 != null) {
                    B2.b(view3, C0003a2);
                }
                return objProceed2;
        }
    }
}
