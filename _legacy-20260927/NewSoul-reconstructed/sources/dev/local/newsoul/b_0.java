/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.util.Log
 *  android.view.View
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import dev.local.newsoul.g_0;
import dev.local.newsoul.j_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Renamed from dev.local.newsoul.b
 */
public final class b_0
implements XposedInterface.Hooker {
    public final int a;
    public final Field b;
    public final Field c;
    public final Field d;
    public final Method e;

    public /* synthetic */ b_0(Field field, Field field2, Field field3, Method method, int n) {
        this.a = n;
        this.b = field;
        this.c = field2;
        this.d = field3;
        this.e = method;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        switch (this.a) {
            default: {
                Object object2 = this.c;
                Object object3 = this.d;
                Method method = this.e;
                Object object4 = object.getArg(0);
                if (object4 == null) return object.proceed();
                if (this.b.getInt(object4) != -220922) return object.proceed();
                boolean bl = true;
                object4 = ((View)object.getArg(1)).getContext();
                try {
                    object2 = ((Field)object2).get(object.getThisObject());
                    object3 = object2 == null ? null : (String)((Field)object3).get(object2);
                    if (object3 != null && !((String)object3).trim().isEmpty()) {
                        object2 = (ClipboardManager)object4.getSystemService(ClipboardManager.class);
                        if (object2 == null) {
                            object = new IllegalStateException("Clipboard unavailable");
                            throw object;
                        }
                        object2.setPrimaryClip(ClipData.newPlainText((CharSequence)"\u5e16\u5b50\u6587\u6848", (CharSequence)object3));
                        object2 = object2.getPrimaryClip();
                        if (object2 == null || object2.getItemCount() != 1 || !((String)object3).contentEquals(object2.getItemAt(0).getText())) {
                            bl = false;
                        }
                    } else {
                        Toast.makeText((Context)object4, (CharSequence)"\u8be5\u5e16\u5b50\u6682\u65e0\u53ef\u590d\u5236\u6587\u6848", (int)0).show();
                        return Boolean.FALSE;
                    }
                    int n = ((String)object3).length();
                    object3 = new StringBuilder("post text copied; chars=");
                    ((StringBuilder)object3).append(n);
                    ((StringBuilder)object3).append("; verified=");
                    ((StringBuilder)object3).append(bl);
                    Log.i((String)"SoulHook102", (String)((StringBuilder)object3).toString());
                    Toast.makeText((Context)object4, (CharSequence)"\u5df2\u590d\u5236\u6587\u6848", (int)0).show();
                    method.invoke(object.getThisObject(), null);
                    return Boolean.FALSE;
                }
                catch (Throwable throwable) {}
                Log.e((String)"SoulHook102", (String)"post text copy failed", (Throwable)throwable);
                Toast.makeText((Context)object4, (CharSequence)"\u590d\u5236\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u5206\u4eab\u9875", (int)0).show();
                return Boolean.FALSE;
            }
            case 0: 
        }
        Object object5 = object.getThisObject();
        Object object6 = this.b.get(object5);
        boolean bl = object6 instanceof Reference;
        Object var6_8 = null;
        Object var5_10 = bl ? ((Reference)object6).get() : null;
        if (!(var5_10 instanceof Context)) {
            return object.proceed();
        }
        String string = (String)j_0.f.invoke(this.c.get(object5), null);
        String string2 = (String)j_0.e.get(object5);
        object6 = string2;
        if (string2 == null) {
            object6 = (String)j_0.c.get(string);
        }
        if (object6 == null) return object.proceed();
        if (!j_0.b(var5_10, (String)object6)) return object.proceed();
        object = this.d.get(object5);
        if (object != null) {
            j_0.i.post((Runnable)new g_0(this.e, object, 0));
        }
        object = new StringBuilder("ad blocked: ");
        ((StringBuilder)object).append((String)object6);
        ((StringBuilder)object).append(" slot=");
        ((StringBuilder)object).append(string);
        j_0.e(((StringBuilder)object).toString());
        return var6_8;
    }
}

