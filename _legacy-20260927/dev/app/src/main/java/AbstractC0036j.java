package dev.local.newsoul;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0036j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0036j {
    public static Method f;
    public static XposedModule g;
    public static volatile Context h;
    public static final String[][] a = {new String[]{"block_splash", "冷启动与热启动开屏", "包括首次启动和从后台返回时的广告。"}, new String[]{"ad_feed", "广场信息流", ""}, new String[]{"ad_detail", "帖子详情", ""}, new String[]{"ad_video", "竖屏视频流", ""}, new String[]{"ad_tag", "话题横幅", ""}, new String[]{"ad_tag_web", "话题网页广告", ""}, new String[]{"ad_search", "搜索顶部横幅", ""}, new String[]{"ad_group", "群组横幅推广", ""}, new String[]{"ad_planet", "星球游戏推广", ""}, new String[]{"ad_me", "我的页面推广", ""}, new String[]{"ad_conversation", "聊天会话列表", "保留聊天消息，只隐藏广告。"}, new String[]{"ad_envelope", "通知信封广告", ""}, new String[]{"ad_notice", "系统通知广告", ""}, new String[]{"ad_chat_tools", "聊天面板推广", "已缓存的推广图标可能需要重启 Soul 才会消失。"}, new String[]{"ad_float", "悬浮广告", ""}, new String[]{"ad_interstitial", "插屏与底部弹窗", ""}, new String[]{"ad_reward_chat", "聊天激励广告", ""}, new String[]{"ad_reward_drama", "短剧激励广告", ""}, new String[]{"ad_reward_web", "网页与任务激励", ""}};
    public static final Set b = Collections.synchronizedSet(new HashSet());
    public static final HashMap c = new HashMap();
    public static final Map<Object, Object> d = Collections.synchronizedMap(new WeakHashMap<>());
    public static final Map<Object, Object> e = Collections.synchronizedMap(new WeakHashMap<>());
    public static final Handler i = new Handler(Looper.getMainLooper());

    static {
        String[] strArr = {"2", "26", "5", "20", "27", "24", "31", "35", "36", "18", "9"};
        String[] strArr2 = {"ad_tag", "ad_tag_web", "ad_detail", "ad_video", "ad_group", "ad_planet", "ad_me", "ad_envelope", "ad_notice", "ad_chat_tools", "ad_search"};
        for (int i2 = 0; i2 < 11; i2++) {
            c.put(strArr[i2], strArr2[i2]);
        }
    }

    public static void a(View view, C0032i c0032i) {
        if (!b(view.getContext(), c0032i.a)) {
            if (c0032i.c) {
                c0032i.c = false;
                view.setVisibility(c0032i.b);
                return;
            }
            return;
        }
        if (c0032i.c) {
            return;
        }
        int visibility = view.getVisibility();
        view.setVisibility(8);
        c0032i.b = visibility;
        c0032i.c = true;
    }

    public static boolean b(Context context, String str) {
        return AbstractC0090w2.o(context).getBoolean(str, "block_splash".equals(str));
    }

    public static void c(String str, Throwable th) {
        Log.e("SoulHook102", "ad control unavailable: " + str, th);
        g.log(6, "SoulHook102", "ad control unavailable: " + str, th);
    }

    public static Field d(Class cls, String str) throws NoSuchFieldException {
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException(str);
    }

    public static void e(String str) {
        Log.i("SoulHook102", str);
        g.log(4, "SoulHook102", str);
    }

    public static void f(ClassLoader classLoader, String str) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str);
            g.hook(clsLoadClass.getMethod("onStart", null)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0016e(clsLoadClass, clsLoadClass.getMethod("getContext", null), clsLoadClass.getMethod("dismiss", null), 0));
            b.add("ad_interstitial");
        } catch (Throwable th) {
            c(str, th);
        }
    }

    public static void g(ClassLoader classLoader, String str, final String str2, final boolean z) {
        try {
            int i2 = 0;
            for (Method method : classLoader.loadClass(str).getDeclaredMethods()) {
                if (z) {
                    if (method.getReturnType() == Void.TYPE && method.getParameterCount() >= 2 && method.getParameterTypes()[0].getSimpleName().equals("BaseViewHolder")) {
                        g.hook(method).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new d(str2, z));
                        i2++;
                    }
                } else if (method.getName().equals("onBind") || method.getName().equals("onCreate")) {
                    g.hook(method).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new d(str2, z));
                    i2++;
                }
            }
            if (i2 == 0) {
                throw new IllegalStateException("no bind methods");
            }
            b.add(str2);
        } catch (Throwable th) {
            c(str, th);
        }
    }

    public static void h(ClassLoader classLoader, String str, String str2, String str3, String str4) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str);
            Field fieldD = d(clsLoadClass, str2);
            Field fieldD2 = d(clsLoadClass, str3);
            Field fieldD3 = d(clsLoadClass, str4);
            for (Constructor<?> constructor : clsLoadClass.getDeclaredConstructors()) {
                g.hook(constructor).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(1));
            }
            g.hook(clsLoadClass.getDeclaredMethod("loadAds", null)).setId("new-soul/ad-loader/" + str).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0004b(fieldD3, fieldD, fieldD2, classLoader.loadClass("cn.soulapp.android.ad.core.services.plaforms.listener.SoulAdRequestListener").getMethod("onAdFailed", Integer.TYPE, String.class), 0));
            boolean zEquals = str.equals("lb.h");
            Set set = b;
            if (zEquals) {
                set.addAll(Arrays.asList("ad_reward_chat", "ad_reward_drama", "ad_reward_web"));
                return;
            }
            set.addAll(c.values());
            set.add("ad_float");
            set.add("ad_interstitial");
        } catch (Throwable th) {
            c(str, th);
        }
    }

    public static void i(View view, String str) {
        Map map = d;
        C0032i c0032i = (C0032i) map.get(view);
        if (c0032i == null || !c0032i.a.equals(str)) {
            c0032i = new C0032i(view, str);
            map.put(view, c0032i);
        }
        a(view, c0032i);
    }
}
