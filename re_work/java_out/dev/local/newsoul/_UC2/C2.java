package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/C2 ====
// super: Ljava/lang/Object
// implements: Application$ActivityLifecycleCallback
// source: "SourceFile"

public final  class C2 extends Ljava/lang/Object implements Landroid/app/Application$ActivityLifecycleCallbacks {

    public final void onActivityCreated(Bundle p0) {
      return;
    }

    public final void onActivityDestroyed(Activity p0) {
      if ("cn.soulapp.android.component.startup.main.MainActivity".equals(p1.getClass().getName()) == 0) goto :cond_27;
      if (p1.getWindow().getDecorView().findViewWithTag("new.soul.square.like") == 0) goto :cond_27;
      (E2) p1.getWindow().getDecorView().findViewWithTag("new.soul.square.like").a.h();
      :cond_27:
      return;
    }

    public final void onActivityPaused(Activity p0) {
      if ("cn.soulapp.android.component.startup.main.MainActivity".equals(p1.getClass().getName()) == 0) goto :cond_27;
      if (p1.getWindow().getDecorView().findViewWithTag("new.soul.square.like") == 0) goto :cond_27;
      (E2) p1.getWindow().getDecorView().findViewWithTag("new.soul.square.like").a.h();
      :cond_27:
      return;
    }

    public final void onActivityResumed(Activity p0) {
      :try_start_0:
      if (Q0.Y == 0) goto :cond_37;
      if ("cn.soulapp.android.component.startup.main.MainActivity".equals(p1.getClass().getName()) != 0) goto :cond_15;
      goto :goto_37;
      :cond_15:
      if (p1.getResources().getIdentifier("message_button", "id", p1.getPackageName()) == 0) goto :cond_37;
      Ldev/local/newsoul/Q0.d(p1.findViewById(p1.getResources().getIdentifier("message_button", "id", p1.getPackageName())));
      :try_end_2e:
      goto :goto_37;
      :catchall_2f:
      :cond_37:
      :goto_37:
      return;

      // .catchall {:try_start_0 .. :try_end_2e} :catchall_2f
    }

    public final void onActivitySaveInstanceState(Bundle p0) {
      return;
    }

    public final void onActivityStarted(Activity p0) {
      return;
    }

    public final void onActivityStopped(Activity p0) {
      return;
    }

}