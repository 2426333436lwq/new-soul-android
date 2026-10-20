package dev.local.newsoul;

import java.lang.ref.WeakReference;
import android.app.Activity;
import android.content.SharedPreferences;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* 本文件合并了仅大小写不同的两个类 P / p（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: P.class */
public final class P {
    public final boolean b;
    public final String c;
    public final WeakReference d;
    public final WeakReference e;
    public boolean f = true;
    public final int a = S.f.nextInt(36864) + 20480;

    public P(C0067q2 q2Var, Q q, String str) {
        this.b = q2Var.b;
        this.c = str;
        this.d = new WeakReference(q2Var);
        this.e = new WeakReference(q);
    }
}

/* JADX INFO: loaded from: p.class */
final /* synthetic */ class p implements XposedInterface.Hooker {
    public final Field a;
    public final boolean b;
    public final Method c;

    public /* synthetic */ p(Field field, boolean z, Method method) {
        this.a = field;
        this.b = z;
        this.c = method;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0099  */
    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object objProceed;
        if (AbstractC0075t.c.get() != null) {
            objProceed = chain.proceed();
        } else {
            Object thisObject = chain.getThisObject();
            Object obj = this.a.get(thisObject);
            Activity activity = (Activity) obj.getClass().getMethod("getActivity", null).invoke(obj, null);
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                objProceed = chain.proceed();
            } else {
                WeakHashMap weakHashMap = AbstractC0090w2.a;
                SharedPreferences sharedPreferences = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.C0085v1", 0);
                boolean z = this.b;
                if (sharedPreferences.getBoolean(z ? "game_dice" : "game_finger", true)) {
                    AbstractC0075t.e.post(new RunnableC0064q(activity, z, this.c, thisObject));
                    objProceed = null;
                } else {
                    objProceed = chain.proceed();
                }
            }
        }
        return objProceed;
    }
}
