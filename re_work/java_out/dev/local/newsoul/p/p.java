package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/p ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class p extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final Field a;
    public final boolean b;
    public final Method c;

    public void super(Method p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      if (t.c.get() == 0) goto :cond_d;
      goto :goto_62;
      :cond_d:
      if ((Activity) p0.a.get(p1.proceed().getThisObject()).getClass().getMethod("getActivity", 0x0).invoke(p0.a.get(p1.proceed().getThisObject()), 0x0) == 0) goto :cond_5e;
      if ((Activity) p0.a.get(p1.proceed().getThisObject()).getClass().getMethod("getActivity", 0x0).invoke(p0.a.get(p1.proceed().getThisObject()), 0x0).isFinishing() != 0) goto :cond_5e;
      if ((Activity) p0.a.get(p1.proceed().getThisObject()).getClass().getMethod("getActivity", 0x0).invoke(p0.a.get(p1.proceed().getThisObject()), 0x0).isDestroyed() != 0) goto :cond_5e;
      if (p0.b == 0) goto :cond_46;
      goto :goto_48;
      :cond_46:
      :goto_48:
      if ((Activity) p0.a.get(p1.proceed().getThisObject()).getClass().getMethod("getActivity", 0x0).invoke(p0.a.get(p1.proceed().getThisObject()), 0x0).getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0).getBoolean("game_dice", 0x1) != 0) goto :cond_50;
      goto :goto_5e;
      :cond_50:
      new Ldev/local/newsoul/q((Activity) p0.a.get(p1.proceed().getThisObject()).getClass().getMethod("getActivity", 0x0).invoke(p0.a.get(p1.proceed().getThisObject()), 0x0), p0.b, p0.c, p1.proceed().getThisObject());
      goto :goto_62;
      :cond_5e:
      :goto_5e:
      :goto_62:
      return p1.proceed().getThisObject();
    }

}