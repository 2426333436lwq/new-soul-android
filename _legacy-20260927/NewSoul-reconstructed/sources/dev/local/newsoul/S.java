/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.os.Looper
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import dev.local.newsoul.G;
import dev.local.newsoul.P;
import dev.local.newsoul.Q;
import dev.local.newsoul.a_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Executable;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class S {
    public static XposedModule a;
    public static final Handler b;
    public static final ExecutorService c;
    public static final WeakHashMap d;
    public static final HashSet e;
    public static final SecureRandom f;

    static {
        b = new Handler(Looper.getMainLooper());
        c = Executors.newSingleThreadExecutor();
        d = new WeakHashMap();
        e = new HashSet();
        f = new SecureRandom();
    }

    public static void a(Activity object) {
        if (a != null) {
            Object object2;
            block6: {
                for (object = object.getClass(); object != null; object = ((Class)object).getSuperclass()) {
                    try {
                        object2 = Integer.TYPE;
                        object = object2 = ((Class)object).getDeclaredMethod("onActivityResult", new Class[]{object2, object2, Intent.class});
                        break block6;
                    }
                    catch (NoSuchMethodException noSuchMethodException) {
                        continue;
                    }
                }
                object = null;
            }
            if (object != null) {
                object2 = e;
                if (((HashSet)object2).contains(object)) {
                    return;
                }
                a.hook((Executable)object).setId("new-soul/config-file-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new a_0(12));
                ((HashSet)object2).add(object);
                return;
            }
            throw new NoSuchMethodException();
        }
        throw new IllegalStateException();
    }

    public static void b(Activity activity, P object, String string) {
        WeakHashMap weakHashMap = d;
        if (weakHashMap.get(activity) != object) {
            return;
        }
        weakHashMap.remove(activity);
        object = (Q)((P)object).e.get();
        if (object != null) {
            ((Q)object).a(string, false);
        } else if (!activity.isDestroyed() && !activity.isDestroyed()) {
            Toast.makeText((Context)activity, (CharSequence)string, (int)1).show();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c(q2 object, Q q, boolean bl) {
        Activity activity = ((q2)object).a;
        WeakHashMap weakHashMap = d;
        try {
            Object object2 = (P)weakHashMap.get(activity);
            if (object2 != null) {
                if (((P)object2).e.get() == q) return;
                q.a("\u914d\u7f6e\u64cd\u4f5c\u8fdb\u884c\u4e2d\uff0c\u8bf7\u5148\u5b8c\u6210\u6216\u53d6\u6d88", false);
                return;
            }
            S.a(activity);
            if (bl) {
                object2 = w2.a;
                object2 = G.a(activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getAll());
            } else {
                object2 = null;
            }
            P p = new P((q2)object, q, (String)object2);
            object = bl ? "android.intent.action.CREATE_DOCUMENT" : "android.intent.action.OPEN_DOCUMENT";
            object2 = new Intent((String)object);
            object2.addCategory("android.intent.category.OPENABLE");
            object = bl ? "application/json" : "*/*";
            object2.setType((String)object);
            if (bl) {
                object = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT);
                Object object3 = new Date();
                object3 = ((DateFormat)object).format((Date)object3);
                object = new StringBuilder("NewSoul-config-");
                ((StringBuilder)object).append((String)object3);
                ((StringBuilder)object).append(".json");
                object2.putExtra("android.intent.extra.TITLE", ((StringBuilder)object).toString());
            }
            weakHashMap.put(activity, p);
            object = bl ? "\u8bf7\u9009\u62e9\u4fdd\u5b58\u4f4d\u7f6e" : "\u8bf7\u9009\u62e9\u914d\u7f6e\u6587\u4ef6";
            q.a((String)object, true);
            try {
                activity.startActivityForResult((Intent)object2, p.a);
                return;
            }
            catch (RuntimeException runtimeException) {
                weakHashMap.remove(activity);
                throw runtimeException;
            }
        }
        catch (Exception exception) {
            q.a("\u65e0\u6cd5\u6253\u5f00\u914d\u7f6e\u6587\u4ef6\u64cd\u4f5c\uff0c\u8bf7\u91cd\u8bd5", false);
        }
    }

    public static boolean d(Activity activity, P object) {
        boolean bl;
        object = (q2)((P)object).d.get();
        boolean bl2 = activity.isFinishing();
        boolean bl3 = bl = false;
        if (!bl2) {
            bl3 = bl;
            if (!activity.isDestroyed()) {
                bl3 = bl;
                if (object != null) {
                    bl3 = bl;
                    if (((q2)object).a == activity) {
                        activity = ((q2)object).e;
                        bl3 = bl;
                        if (activity != null) {
                            if (!activity.isShowing()) {
                                bl3 = bl;
                            } else {
                                activity = ((q2)object).e.getWindow();
                                bl3 = bl;
                                if (activity != null) {
                                    bl3 = bl;
                                    if (activity.getDecorView().isAttachedToWindow()) {
                                        bl3 = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bl3;
    }
}

