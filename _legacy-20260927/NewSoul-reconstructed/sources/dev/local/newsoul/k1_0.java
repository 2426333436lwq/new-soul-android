/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import dev.local.newsoul.H;
import dev.local.newsoul.Q0;
import dev.local.newsoul.t1_0;
import dev.local.newsoul.u1_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;

/*
 * Renamed from dev.local.newsoul.k1
 */
public final class k1_0
implements XposedInterface.Hooker {
    public final int a;
    public final Field b;

    public /* synthetic */ k1_0(Field field, int n) {
        this.a = n;
        this.b = field;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2 = object.getArg(0);
                if (this.b.getInt(object2) != 5133072) {
                    return object.proceed();
                }
                try {
                    Object object3 = object.getThisObject();
                    t1_0 t1_02 = (t1_0)u1_0.c.get(object3);
                    Activity activity = (Activity)u1_0.c(object3, "getActivity");
                    if (t1_02 != null && activity != null) {
                        if (!t1_02.e) {
                            object2 = t1_02.h ? "\u8bfb\u53d6\u63d2\u773c\u5217\u8868\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u83dc\u5355" : "\u63d2\u773c\u5217\u8868\u5c1a\u672a\u52a0\u8f7d\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                            if (u1_0.b(activity)) {
                                Toast.makeText((Context)activity, (CharSequence)object2, (int)0).show();
                            }
                            object2 = Boolean.TRUE;
                            return object2;
                        }
                        if (t1_02.g) {
                            object2 = Boolean.TRUE;
                            return object2;
                        }
                    } else {
                        object2 = Boolean.TRUE;
                        return object2;
                    }
                    u1_0.h(t1_02.a);
                    t1_02.g = true;
                    object2 = u1_0.b;
                    H h = new H((Object)t1_02, activity, object3, 6);
                    object2.execute(h);
                    return Boolean.TRUE;
                }
                catch (Exception exception) {}
                Log.w((String)"SoulHook102", (String)"profile bookmark click failed", (Throwable)exception);
                u1_0.e((Activity)u1_0.c(object.getThisObject(), "getActivity"), exception);
                return Boolean.TRUE;
            }
            case 0: {
                Object object4 = object.getThisObject();
                if (this.b.get(object4) == null) return object.proceed();
                if (!Q0.q("post_remove_share_limit")) return object.proceed();
                Log.i((String)"SoulHook102", (String)"post share restriction bypassed");
                return null;
            }
        }
        catch (Exception exception) {}
        return Boolean.TRUE;
    }
}

