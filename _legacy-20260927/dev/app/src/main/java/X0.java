package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: X0.class */
public final /* synthetic */ class X0 implements XposedInterface.Hooker {
    public final int a;
    public final Method b;
    public final String c;
    public final XposedModule d;
    public final int e;

    public /* synthetic */ X0(int i, Method method, String str, XposedModule xposedModule, int C0035i2) {
        this.a = i;
        this.b = method;
        this.c = str;
        this.d = xposedModule;
        this.e = C0035i2;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x00c6  */
    public final Object intercept(XposedInterface.Chain chain) {
        Object objProceed;
        Method method = this.b;
        String str = this.c;
        int i = this.a;
        try {
            Context context = (Context) (i >= 0 ? chain.getArg(i) : method.invoke(null, null));
            if (context != null) {
                WeakHashMap weakHashMap = AbstractC0090w2.a;
                if (context.getSharedPreferences("dev.local.soulhookdemo.public.settings.C0085v1", 0).getBoolean(str, true)) {
                    Object[] array = chain.getArgs().toArray();
                    Boolean bool = Boolean.FALSE;
                    int C0035i2 = this.e;
                    array[C0035i2] = bool;
                    Log.i("SoulHook102", "watermark bypass: " + str + "; original=" + String.valueOf(chain.getArg(C0035i2)));
                    objProceed = chain.proceed(array);
                } else {
                    objProceed = chain.proceed();
                }
            } else {
                objProceed = chain.proceed();
            }
        } catch (Throwable th) {
            this.d.log(5, "SoulHook102", "download preference unavailable", th);
        }
        return objProceed;
    }
}
