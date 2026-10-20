/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.os.Bundle
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import dev.local.newsoul.g_0;

public final class O0
implements Application.ActivityLifecycleCallbacks {
    public boolean a;
    public final Application b;
    public final String c;

    public O0(Application application, String string) {
        this.b = application;
        this.c = string;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        if (!this.a && "cn.soulapp.android".equals(activity.getPackageName())) {
            this.a = true;
            this.b.unregisterActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)this);
            activity.getWindow().getDecorView().post((Runnable)new g_0(activity, this.c, 3));
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}

