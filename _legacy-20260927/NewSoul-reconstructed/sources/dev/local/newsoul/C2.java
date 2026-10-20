/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.os.Bundle
 *  android.util.Log
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import dev.local.newsoul.E2;
import dev.local.newsoul.Q0;

public final class C2
implements Application.ActivityLifecycleCallbacks {
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if ("cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) && (activity = activity.getWindow().getDecorView().findViewWithTag((Object)"new.soul.square.like")) != null) {
            ((E2)activity).a.h();
        }
    }

    public final void onActivityPaused(Activity activity) {
        if ("cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) && (activity = activity.getWindow().getDecorView().findViewWithTag((Object)"new.soul.square.like")) != null) {
            ((E2)activity).a.h();
        }
    }

    public final void onActivityResumed(Activity activity) {
        block3: {
            int n;
            if (!Q0.Y || !"cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) || (n = activity.getResources().getIdentifier("message_button", "id", activity.getPackageName())) == 0) break block3;
            try {
                Q0.d(activity.findViewById(n));
            }
            catch (Throwable throwable) {
                Log.e((String)"SoulHook102", (String)"square like UI unavailable", (Throwable)throwable);
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}

