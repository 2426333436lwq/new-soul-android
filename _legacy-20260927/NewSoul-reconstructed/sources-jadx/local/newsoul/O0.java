package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class O0 implements Application.ActivityLifecycleCallbacks {
    public boolean a;
    public final /* synthetic */ Application b;
    public final /* synthetic */ String c;

    public O0(Application application, String str) {
        this.b = application;
        this.c = str;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        if (this.a || !"cn.soulapp.android".equals(activity.getPackageName())) {
            return;
        }
        this.a = true;
        this.b.unregisterActivityLifecycleCallbacks(this);
        activity.getWindow().getDecorView().post(new RunnableC0024g(activity, this.c, 3));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
