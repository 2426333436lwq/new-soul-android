package dev.local.newsoul;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0056o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0056o {
    public static boolean a;
    public static final String[] b = {"main_tab_planet", "main_tab_square", "main_tab_msg", "main_tab_me"};
    public static final WeakHashMap c = new WeakHashMap();
    public static Method d;

    public static void a(Activity activity) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        try {
            int identifier = activity.getResources().getIdentifier("main_bottom_tab_bar", "id", activity.getPackageName());
            View viewFindViewById = identifier == 0 ? null : activity.findViewById(identifier);
            if (viewFindViewById == null) {
                return;
            }
            String[] strArr = b;
            for (int i = 0; i < 4; i++) {
                int identifier2 = activity.getResources().getIdentifier(strArr[i], "id", activity.getPackageName());
                View viewFindViewById2 = identifier2 == 0 ? null : viewFindViewById.findViewById(identifier2);
                if (viewFindViewById2 != null) {
                    WeakHashMap weakHashMap = c;
                    if (!weakHashMap.containsKey(viewFindViewById2) && !viewFindViewById2.hasOnLongClickListeners()) {
                        viewFindViewById2.setOnLongClickListener(new ViewOnLongClickListenerC0052n(0, activity));
                        weakHashMap.put(viewFindViewById2, Boolean.TRUE);
                    }
                }
            }
        } catch (Throwable th) {
            Log.w("SoulHook102", "bottom settings binding failed", th);
        }
    }

    public static boolean b(Activity activity) {
        try {
            Method method = d;
            if (method != null) {
                return Boolean.TRUE.equals(method.invoke(null, null));
            }
        } catch (ReflectiveOperationException unused) {
        }
        return (activity.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static void c(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            try {
                d = classLoader.loadClass("cn.soulapp.android.lib.common.utils.settings.SoulSettings").getMethod("isNightMode", null);
            } catch (ReflectiveOperationException unused) {
            }
            Method declaredMethod = classLoader.loadClass("cn.soulapp.android.component.startup.main.MainActivity").getDeclaredMethod("onCreate", Bundle.class);
            xposedModule.hook(declaredMethod).setId("new-soul/bottom-settings-entry").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(2));
            xposedModule.deoptimize(declaredMethod);
            a = true;
            Log.i("SoulHook102", "bottom settings entry installed");
        } catch (Throwable th) {
            Log.e("SoulHook102", "bottom settings entry unavailable", th);
        }
    }
}
