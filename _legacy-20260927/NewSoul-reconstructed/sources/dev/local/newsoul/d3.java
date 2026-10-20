/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.C0;
import dev.local.newsoul.L2;
import dev.local.newsoul.O2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.h2_0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.u2_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class d3 {
    public static boolean g;
    public final Handler a = new Handler(Looper.getMainLooper());
    public final ExecutorService b = Executors.newSingleThreadExecutor();
    public final WeakHashMap c = new WeakHashMap();
    public Method d;
    public Method e;
    public b3 f;

    public static TextView a(q2 q22, String string, c3 c32) {
        int n = q22.i;
        Object object = q22.g;
        string = w2.n((Context)object, string, 15, n);
        string.setGravity(16);
        n = w2.i((Context)object, 12);
        string.setPadding(n, n, n, n);
        string.setMinHeight(w2.i((Context)object, 48));
        string.setSingleLine(false);
        string.setEllipsize(null);
        w2.g((View)string);
        object = q22.c;
        object.getClass();
        ((L2)object).b((View)string, 9, L2.e(9));
        string.setOnClickListener((View.OnClickListener)new C0(c32, q22, 5));
        return string;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(File var0, String var1_4) {
        block21: {
            block19: {
                block20: {
                    var2_6 = 0;
                    while (var2_6 < 6) {
                        if (Q0.N((String)var1_4) == false) throw new IOException("Invalid audio address");
                        var4_8 = (HttpURLConnection)new URL((String)var1_4).openConnection();
                        var4_8.setConnectTimeout(15000);
                        var4_8.setReadTimeout(20000);
                        var4_8.setInstanceFollowRedirects(false);
                        var3_7 = var4_8.getResponseCode();
                        if (var3_7 < 300 || var3_7 >= 400) break block17;
                        var5_9 = var4_8.getHeaderField("Location");
                        if (var5_9 != null) {
                            var7_12 = new URL((String)var1_4);
                            var6_11 = new URL(var7_12, (String)var5_9);
                            var1_4 = var6_11.toString();
                            var4_8.disconnect();
                            ++var2_6;
                            continue;
                        }
                        ** break block18
                    }
                    throw new IOException("Too many redirects");
lbl-1000:
                    // 1 sources

                    {
                        block17: {
                            var0 /* !! */  = new IOException("Invalid redirect");
                            throw var0 /* !! */ ;
                        }
                        if (var3_7 != 200) break block19;
                        if (var4_8.getContentLengthLong() > 0x1400000L) break block20;
                        var1_4 = var4_8.getInputStream();
                    }
                    var5_9 = new FileOutputStream(var0 /* !! */ );
                    x1_0.b((InputStream)var1_4, (FileOutputStream)var5_9);
                    var5_9.close();
                    var1_4.close();
                    var4_8.disconnect();
                    return;
                    {
                        catch (Throwable var0_1) {}
                    }
                    catch (Throwable var0_3) {
                        try {
                            var5_9.close();
                            throw var0_3;
                        }
                        catch (Throwable var5_10) {
                            try {
                                var0_3.addSuppressed(var5_10);
                                throw var0_3;
                            }
                            catch (Throwable var0_2) {}
                        }
                    }
                    if (var1_4 == null) throw var0_2;
                    try {
                        var1_4.close();
                        throw var0_2;
                    }
                    catch (Throwable var1_5) {
                        var0_2.addSuppressed(var1_5);
                        throw var0_2;
                    }
                    break block21;
                }
                var0 /* !! */  = new IOException("\u8bed\u97f3\u6587\u4ef6\u8fc7\u5927");
                throw var0 /* !! */ ;
            }
            var1_4 = new StringBuilder();
            var1_4.append("\u97f3\u9891\u4e0b\u8f7d\u5931\u8d25\uff08");
            var1_4.append(var3_7);
            var1_4.append("\uff09");
            var0 /* !! */  = new IOException(var1_4.toString());
            throw var0 /* !! */ ;
        }
        var4_8.disconnect();
        throw var0_1;
    }

    public static Object c(Object object) {
        AccessibleObject accessibleObject = null;
        for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                AccessibleObject accessibleObject2 = accessibleObject;
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!field.getType().getName().equals("cn.soulapp.imlib.Conversation")) {
                        accessibleObject2 = accessibleObject;
                    } else if (accessibleObject == null) {
                        accessibleObject2 = field;
                    } else {
                        throw new IllegalStateException("Ambiguous conversation field");
                    }
                }
                accessibleObject = accessibleObject2;
            }
        }
        if (accessibleObject != null) {
            accessibleObject.setAccessible(true);
            return ((Field)accessibleObject).get(object);
        }
        throw new NoSuchFieldException("cn.soulapp.imlib.Conversation");
    }

    public static Object e(Object object, String string) {
        return object.getClass().getMethod(string, null).invoke(object, null);
    }

    public static void f(Context context, String string, Throwable throwable) {
        Log.w((String)"SoulHook102", (String)"voice library operation failed; no automatic send retry", (Throwable)throwable);
        d3.i(context, string);
    }

    public static Object g(Object object, String string) {
        for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Object object2 = clazz.getDeclaredField(string);
                ((AccessibleObject)object2).setAccessible(true);
                object2 = ((Field)object2).get(object);
                return object2;
            }
            catch (NoSuchFieldException noSuchFieldException) {
                continue;
            }
        }
        throw new NoSuchFieldException(string);
    }

    public static void i(Context context, String string) {
        Toast.makeText((Context)context, (CharSequence)string, (int)0).show();
    }

    public final void d(Object object) {
        Activity activity = (Activity)d3.e(object, "getActivity");
        if (activity != null && ((Boolean)d3.e(object, "isResumed")).booleanValue()) {
            View view = (View)d3.e(object, "getView");
            if (view == null) {
                return;
            }
            for (int i = 0; i < 3; ++i) {
                WeakHashMap weakHashMap;
                String string = (new String[]{"menu_tab_voice_inner_outside", "menu_tab_voice_inner", "menu_tab_voice"})[i];
                int n = activity.getResources().getIdentifier(string, "id", activity.getPackageName());
                View view2 = n == 0 ? null : view.findViewById(n);
                if (view2 == null || (weakHashMap = this.c).containsKey(view2) || k_0.h(view2)) continue;
                view2.setOnLongClickListener((View.OnLongClickListener)new O2(this, new WeakReference<Object>(object), activity));
                if ("menu_tab_voice_inner_outside".equals(string)) {
                    view2.setOnTouchListener(null);
                }
                weakHashMap.put(view2, Boolean.TRUE);
            }
        }
    }

    public final void h(Object object, Object object2) {
        object = new b3(this, object);
        String string = (String)d3.g(object2, "url");
        int n = (Integer)d3.g(object2, "duration");
        if (Q0.N(string) && n > 0) {
            object2 = this.f;
            if (object2 != null) {
                ((b3)object2).b();
            }
            this.f = object;
            ((b3)object).e("\u4fdd\u5b58\u5230\u8bed\u97f3\u5e93", "", new h2_0(object, string, n), new u2_0(1, object));
            return;
        }
        throw new IllegalArgumentException("Invalid voice");
    }
}

