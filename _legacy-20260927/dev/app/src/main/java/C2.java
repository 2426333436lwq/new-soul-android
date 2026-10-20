package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

/* 本文件合并了仅大小写不同的两个类 C2 / c2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: C2.class */
public final class C2 implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        E2 e2FindViewWithTag;
        if (!"cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) || (e2FindViewWithTag = activity.getWindow().getDecorView().findViewWithTag("new.soul.square.like")) == null) {
            return;
        }
        e2FindViewWithTag.a.h();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        E2 e2FindViewWithTag;
        if (!"cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) || (e2FindViewWithTag = activity.getWindow().getDecorView().findViewWithTag("new.soul.square.like")) == null) {
            return;
        }
        e2FindViewWithTag.a.h();
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

/* JADX INFO: loaded from: c2.class */
final /* synthetic */ class c2 implements View.OnClickListener {
    public final RunnableC0015d2 a;
    public final String[] b;
    public final ArrayList c;
    public final int d;
    public final int[] e;
    public final TextView f;

    public /* synthetic */ c2(RunnableC0015d2 d2Var, String[] strArr, ArrayList arrayList, int i, int[] iArr, TextView textView) {
        this.a = d2Var;
        this.b = strArr;
        this.c = arrayList;
        this.d = i;
        this.e = iArr;
        this.f = textView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RunnableC0015d2 d2Var = this.a;
        d2Var.getClass();
        ArrayList arrayList = this.c;
        int i = this.d;
        String str = (String) arrayList.remove(i);
        this.b[0] = str;
        this.e[0] = i;
        String str2 = "已删除“" + str + "” · 撤销";
        TextView textView = this.f;
        textView.setText(str2);
        textView.setVisibility(0);
        d2Var.run();
    }
}
