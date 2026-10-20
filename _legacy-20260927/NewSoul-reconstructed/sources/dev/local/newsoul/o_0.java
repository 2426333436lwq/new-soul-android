/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import dev.local.newsoul.a_0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.n_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.o
 */
public abstract class o_0 {
    public static boolean a;
    public static final String[] b;
    public static final WeakHashMap c;
    public static Method d;

    static {
        b = new String[]{"main_tab_planet", "main_tab_square", "main_tab_msg", "main_tab_me"};
        c = new WeakHashMap();
    }

    public static void a(Activity activity) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            View view;
            int n;
            block11: {
                block10: {
                    n = activity.getResources().getIdentifier("main_bottom_tab_bar", "id", activity.getPackageName());
                    if (n != 0) break block10;
                    view = null;
                    break block11;
                }
                view = activity.findViewById(n);
            }
            if (view == null) {
                return;
            }
            String[] stringArray = b;
            for (n = 0; n < 4; ++n) {
                Throwable throwable2;
                String string;
                block13: {
                    int n2;
                    block12: {
                        string = stringArray[n];
                        try {
                            n2 = activity.getResources().getIdentifier(string, "id", activity.getPackageName());
                            if (n2 != 0) break block12;
                            string = null;
                            break block13;
                        }
                        catch (Throwable throwable2) {}
                    }
                    string = view.findViewById(n2);
                }
                if (string == null) continue;
                WeakHashMap weakHashMap = c;
                if (weakHashMap.containsKey(string) || k_0.h((View)string)) continue;
                n_0 n_02 = new n_0(0, activity);
                string.setOnLongClickListener((View.OnLongClickListener)n_02);
                weakHashMap.put(string, Boolean.TRUE);
                continue;
                Log.w((String)"SoulHook102", (String)"bottom settings binding failed", (Throwable)throwable2);
                break;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean b(Activity activity) {
        try {
            Method method = d;
            if (method != null) {
                return Boolean.TRUE.equals(method.invoke(null, null));
            }
        }
        catch (ReflectiveOperationException reflectiveOperationException) {}
        if ((activity.getResources().getConfiguration().uiMode & 0x30) != 32) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c(XposedModule xposedModule, ClassLoader object) {
        block4: {
            try {
                try {
                    d = ((ClassLoader)object).loadClass("cn.soulapp.android.lib.common.utils.settings.SoulSettings").getMethod("isNightMode", null);
                }
                catch (ReflectiveOperationException reflectiveOperationException) {}
                break block4;
            }
            catch (Throwable throwable) {}
            Log.e((String)"SoulHook102", (String)"bottom settings entry unavailable", (Throwable)throwable);
            return;
        }
        object = ((ClassLoader)object).loadClass("cn.soulapp.android.component.startup.main.MainActivity").getDeclaredMethod("onCreate", Bundle.class);
        XposedInterface.HookBuilder hookBuilder = xposedModule.hook((Executable)object).setId("new-soul/bottom-settings-entry").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
        a_0 a_02 = new a_0(2);
        hookBuilder.intercept((XposedInterface.Hooker)a_02);
        xposedModule.deoptimize((Executable)object);
        a = true;
        Log.i((String)"SoulHook102", (String)"bottom settings entry installed");
    }
}

