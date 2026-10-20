package dev.local.newsoul;

import android.util.Log;
import android.view.View;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class B implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ Field b;
    public final /* synthetic */ Field c;

    public /* synthetic */ B(Field field, Field field2, int i) {
        this.a = i;
        this.b = field;
        this.c = field2;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        switch (this.a) {
            case 0:
                Field field = this.c;
                View view = (View) this.b.get(chain.getArg(0));
                AbstractC0101z1.b(view);
                Object objProceed = chain.proceed();
                try {
                    Object arg = chain.getArg(1);
                    if (Q0.v(arg) && view.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0).contains(Q0.L(arg))) {
                        Object obj = field.get(chain.getThisObject());
                        String str = "对方";
                        if (obj != null) {
                            String[] strArr = {"alias", "signature"};
                            for (int i = 0; i < 2; i++) {
                                Object obj2 = obj.getClass().getField(strArr[i]).get(obj);
                                if ((obj2 instanceof String) && !((String) obj2).isEmpty()) {
                                    str = (String) obj2;
                                }
                            }
                        }
                        AbstractC0101z1.a(view, AbstractC0090w2.o(view.getContext()).getString("chat_recall_style", "below"), str);
                    }
                    return objProceed;
                } catch (Throwable th) {
                    Log.e("SoulHook102", "recall message style unavailable", th);
                }
                return objProceed;
            default:
                Field field2 = this.b;
                Field field3 = this.c;
                Object objProceed2 = chain.proceed();
                try {
                    AbstractC0038j1.b((View) field2.get(chain.getThisObject()), field3.getInt(chain.getArg(0)) == -220922);
                    return objProceed2;
                } catch (Throwable th2) {
                    Log.w("SoulHook102", "post action mark unavailable", th2);
                }
                return objProceed2;
        }
    }
}
