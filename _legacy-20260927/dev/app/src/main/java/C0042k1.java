package dev.local.newsoul;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: dev.local.newsoul.C0042k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0042k1 implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ Field b;

    public /* synthetic */ C0042k1(Field field, int i) {
        this.a = i;
        this.b = field;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        switch (this.a) {
            case 0:
                if (this.b.get(chain.getThisObject()) == null || !Q0.q("post_remove_share_limit")) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "post share restriction bypassed");
                return null;
            default:
                if (this.b.getInt(chain.getArg(0)) != 5133072) {
                    return chain.proceed();
                }
                try {
                    Object thisObject = chain.getThisObject();
                    C0077t1 c0077t1 = (C0077t1) AbstractC0081u1.c.get(thisObject);
                    Activity activity = (Activity) AbstractC0081u1.c(thisObject, "getActivity");
                    if (c0077t1 != null && activity != null) {
                        if (!c0077t1.e) {
                            String str = c0077t1.h ? "读取插眼列表失败，请重新打开菜单" : "插眼列表尚未加载，请稍后重试";
                            if (AbstractC0081u1.b(activity)) {
                                Toast.makeText(activity, str, 0).show();
                            }
                            return Boolean.TRUE;
                        }
                        if (c0077t1.g) {
                            return Boolean.TRUE;
                        }
                        AbstractC0081u1.h(c0077t1.a);
                        c0077t1.g = true;
                        AbstractC0081u1.b.execute(new H((Object) c0077t1, activity, thisObject, 6));
                        return Boolean.TRUE;
                    }
                    return Boolean.TRUE;
                } catch (Exception e) {
                    Log.w("SoulHook102", "profile bookmark click failed", e);
                    try {
                        AbstractC0081u1.e((Activity) AbstractC0081u1.c(chain.getThisObject(), "getActivity"), e);
                        return Boolean.TRUE;
                    } catch (Exception unused) {
                    }
                }
                return Boolean.TRUE;
        }
    }
}
