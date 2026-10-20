package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class C2 implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        View viewFindViewWithTag;
        if (!"cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) || (viewFindViewWithTag = activity.getWindow().getDecorView().findViewWithTag("new.soul.square.like")) == null) {
            return;
        }
        ((E2) viewFindViewWithTag).a.h();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        View viewFindViewWithTag;
        if (!"cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) || (viewFindViewWithTag = activity.getWindow().getDecorView().findViewWithTag("new.soul.square.like")) == null) {
            return;
        }
        ((E2) viewFindViewWithTag).a.h();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        int identifier;
        try {
            if (Q0.Y && "cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) && (identifier = activity.getResources().getIdentifier("message_button", "id", activity.getPackageName())) != 0) {
                Q0.d(activity.findViewById(identifier));
            }
        } catch (Throwable th) {
            Log.e("SoulHook102", "square like UI unavailable", th);
        }
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
