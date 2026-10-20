/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Log
 *  android.view.View
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import dev.local.newsoul.a_0;
import dev.local.newsoul.b_0;
import dev.local.newsoul.d_0;
import dev.local.newsoul.e_0;
import dev.local.newsoul.i_0;
import dev.local.newsoul.w2;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.j
 */
public abstract class j_0 {
    public static final String[][] a;
    public static final Set b;
    public static final HashMap c;
    public static final Map d;
    public static final Map e;
    public static Method f;
    public static XposedModule g;
    public static volatile Context h;
    public static final Handler i;

    static {
        String[] stringArray = new String[]{"ad_detail", "\u5e16\u5b50\u8be6\u60c5", ""};
        String[] stringArray2 = new String[]{"ad_video", "\u7ad6\u5c4f\u89c6\u9891\u6d41", ""};
        String[] stringArray3 = new String[]{"ad_conversation", "\u804a\u5929\u4f1a\u8bdd\u5217\u8868", "\u4fdd\u7559\u804a\u5929\u6d88\u606f\uff0c\u53ea\u9690\u85cf\u5e7f\u544a\u3002"};
        String[] stringArray4 = new String[]{"ad_envelope", "\u901a\u77e5\u4fe1\u5c01\u5e7f\u544a", ""};
        String[] stringArray5 = new String[]{"ad_chat_tools", "\u804a\u5929\u9762\u677f\u63a8\u5e7f", "\u5df2\u7f13\u5b58\u7684\u63a8\u5e7f\u56fe\u6807\u53ef\u80fd\u9700\u8981\u91cd\u542f Soul \u624d\u4f1a\u6d88\u5931\u3002"};
        String[] stringArray6 = new String[]{"ad_float", "\u60ac\u6d6e\u5e7f\u544a", ""};
        String[] stringArray7 = new String[]{"ad_interstitial", "\u63d2\u5c4f\u4e0e\u5e95\u90e8\u5f39\u7a97", ""};
        a = new String[][]{{"block_splash", "\u51b7\u542f\u52a8\u4e0e\u70ed\u542f\u52a8\u5f00\u5c4f", "\u5305\u62ec\u9996\u6b21\u542f\u52a8\u548c\u4ece\u540e\u53f0\u8fd4\u56de\u65f6\u7684\u5e7f\u544a\u3002"}, {"ad_feed", "\u5e7f\u573a\u4fe1\u606f\u6d41", ""}, stringArray, stringArray2, {"ad_tag", "\u8bdd\u9898\u6a2a\u5e45", ""}, {"ad_tag_web", "\u8bdd\u9898\u7f51\u9875\u5e7f\u544a", ""}, {"ad_search", "\u641c\u7d22\u9876\u90e8\u6a2a\u5e45", ""}, {"ad_group", "\u7fa4\u7ec4\u6a2a\u5e45\u63a8\u5e7f", ""}, {"ad_planet", "\u661f\u7403\u6e38\u620f\u63a8\u5e7f", ""}, {"ad_me", "\u6211\u7684\u9875\u9762\u63a8\u5e7f", ""}, stringArray3, stringArray4, {"ad_notice", "\u7cfb\u7edf\u901a\u77e5\u5e7f\u544a", ""}, stringArray5, stringArray6, stringArray7, {"ad_reward_chat", "\u804a\u5929\u6fc0\u52b1\u5e7f\u544a", ""}, {"ad_reward_drama", "\u77ed\u5267\u6fc0\u52b1\u5e7f\u544a", ""}, {"ad_reward_web", "\u7f51\u9875\u4e0e\u4efb\u52a1\u6fc0\u52b1", ""}};
        b = Collections.synchronizedSet(new HashSet());
        c = new HashMap();
        d = Collections.synchronizedMap(new WeakHashMap());
        e = Collections.synchronizedMap(new WeakHashMap());
        i = new Handler(Looper.getMainLooper());
        for (int i = 0; i < 11; ++i) {
            c.put((new String[]{"2", "26", "5", "20", "27", "24", "31", "35", "36", "18", "9"})[i], (new String[]{"ad_tag", "ad_tag_web", "ad_detail", "ad_video", "ad_group", "ad_planet", "ad_me", "ad_envelope", "ad_notice", "ad_chat_tools", "ad_search"})[i]);
        }
    }

    public static void a(View view, i_0 i_02) {
        if (j_0.b(view.getContext(), i_02.a)) {
            if (!i_02.c) {
                int n = view.getVisibility();
                view.setVisibility(8);
                i_02.b = n;
                i_02.c = true;
            }
        } else if (i_02.c) {
            i_02.c = false;
            view.setVisibility(i_02.b);
        }
    }

    public static boolean b(Context context, String string) {
        return w2.o(context).getBoolean(string, "block_splash".equals(string));
    }

    public static void c(String string, Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder("ad control unavailable: ");
        stringBuilder.append(string);
        Log.e((String)"SoulHook102", (String)stringBuilder.toString(), (Throwable)throwable);
        XposedModule xposedModule = g;
        stringBuilder = new StringBuilder("ad control unavailable: ");
        stringBuilder.append(string);
        xposedModule.log(6, "SoulHook102", stringBuilder.toString(), throwable);
    }

    public static Field d(Class clazz, String string) {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(string);
                ((AccessibleObject)field).setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException noSuchFieldException) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(string);
    }

    public static void e(String string) {
        Log.i((String)"SoulHook102", (String)string);
        g.log(4, "SoulHook102", string);
    }

    public static void f(ClassLoader object, String string) {
        try {
            Class<?> clazz = ((ClassLoader)object).loadClass(string);
            Object object2 = clazz.getMethod("onStart", null);
            Method method = clazz.getMethod("getContext", null);
            object = clazz.getMethod("dismiss", null);
            XposedInterface.HookBuilder hookBuilder = g.hook((Executable)object2).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            object2 = new e_0(clazz, method, object, 0);
            hookBuilder.intercept((XposedInterface.Hooker)object2);
            b.add("ad_interstitial");
        }
        catch (Throwable throwable) {
            j_0.c(string, throwable);
        }
    }

    /*
     * WARNING - combined exceptions agressively - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void g(ClassLoader object, String string, String string2, boolean bl) {
        try {
            object = ((ClassLoader)object).loadClass(string).getDeclaredMethods();
            int n = ((Object)object).length;
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                int n3;
                block9: {
                    Object object2;
                    block10: {
                        block8: {
                            object2 = object[i];
                            if (!bl) break block8;
                            n3 = n2;
                            if (((Method)object2).getReturnType() != Void.TYPE) break block9;
                            n3 = n2;
                            if (((Method)object2).getParameterCount() < 2) break block9;
                            n3 = n2;
                            if (!((Method)object2).getParameterTypes()[0].getSimpleName().equals("BaseViewHolder")) break block9;
                            break block10;
                        }
                        if (((Method)object2).getName().equals("onBind")) break block10;
                        n3 = n2;
                        if (!((Method)object2).getName().equals("onCreate")) break block9;
                    }
                    object2 = g.hook((Executable)object2).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    d_0 d_02 = new d_0(string2, bl);
                    object2.intercept((XposedInterface.Hooker)d_02);
                    n3 = n2 + 1;
                }
                n2 = n3;
            }
            if (n2 != 0) {
                b.add(string2);
                return;
            }
            object = new IllegalStateException("no bind methods");
            throw object;
        }
        catch (Throwable throwable) {}
        j_0.c(string, throwable);
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void h(ClassLoader object, String string, String object2, String object3, String object4) {
        Throwable throwable2;
        block6: {
            int n;
            Object object5;
            Object object6;
            try {
                object6 = ((ClassLoader)object).loadClass(string);
                object2 = j_0.d(object6, (String)object2);
                object3 = j_0.d(object6, (String)object3);
                object4 = j_0.d(object6, (String)object4);
                object5 = object6.getDeclaredConstructors();
                n = ((Constructor<?>[])object5).length;
            }
            catch (Throwable throwable2) {
                break block6;
            }
            for (int i = 0; i < n; ++i) {
                XposedInterface.HookBuilder hookBuilder = object5[i];
                {
                    hookBuilder = g.hook(hookBuilder).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    a_0 a_02 = new a_0(1);
                    hookBuilder.intercept((XposedInterface.Hooker)a_02);
                    continue;
                }
            }
            {
                object = ((ClassLoader)object).loadClass("cn.soulapp.android.ad.core.services.plaforms.listener.SoulAdRequestListener").getMethod("onAdFailed", Integer.TYPE, String.class);
                object6 = g.hook((Executable)object6.getDeclaredMethod("loadAds", null));
                object5 = new StringBuilder();
                ((StringBuilder)object5).append("new-soul/ad-loader/");
                ((StringBuilder)object5).append(string);
                object5 = object6.setId(((StringBuilder)object5).toString()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                object6 = new b_0((Field)object4, (Field)object2, (Field)object3, (Method)object, 0);
                object5.intercept((XposedInterface.Hooker)object6);
                boolean bl = string.equals("lb.h");
                object = b;
                if (bl) {
                    object.addAll(Arrays.asList("ad_reward_chat", "ad_reward_drama", "ad_reward_web"));
                    return;
                }
                object.addAll(c.values());
                object.add("ad_float");
                object.add("ad_interstitial");
                return;
            }
        }
        j_0.c(string, throwable2);
    }

    public static void i(View view, String string) {
        i_0 i_02;
        block3: {
            Map map;
            block2: {
                map = d;
                i_0 i_03 = (i_0)map.get(view);
                if (i_03 == null) break block2;
                i_02 = i_03;
                if (i_03.a.equals(string)) break block3;
            }
            i_02 = new i_0(view, string);
            map.put(view, i_02);
        }
        j_0.a(view, i_02);
    }
}

