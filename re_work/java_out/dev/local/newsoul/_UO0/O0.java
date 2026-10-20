package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/O0 ====
// super: Ljava/lang/Object
// implements: Application$ActivityLifecycleCallback
// source: "SourceFile"

public final  class O0 extends Ljava/lang/Object implements Landroid/app/Application$ActivityLifecycleCallbacks {

    public boolean a;
    public final Application b;
    public final String c;

    public void super(String p0) {
      super();
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void onActivityCreated(Bundle p0) {
      return;
    }

    public final void onActivityDestroyed(Activity p0) {
      return;
    }

    public final void onActivityPaused(Activity p0) {
      return;
    }

    public final void onActivityResumed(Activity p0) {
      if (p0.a != 0) goto :cond_2c;
      if ("cn.soulapp.android".equals(p1.getPackageName()) != 0) goto :cond_11;
      goto :goto_2c;
      :cond_11:
      p0.a = 0x1;
      p0.b.unregisterActivityLifecycleCallbacks(p0);
      new Ldev/local/newsoul/g(p1, p0.c, 0x3);
      :cond_2c:
      :goto_2c:
      return;
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