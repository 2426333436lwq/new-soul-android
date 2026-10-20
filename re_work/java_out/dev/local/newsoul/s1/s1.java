package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/s1 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class s1 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final Field a;
    public final Field b;
    public final Field c;
    public final Constructor d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public void super(Field p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      this.h = p8;
      this.i = p9;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      if (u1.f != 0) goto :cond_1c;
      goto :goto_139;
      :cond_1c:
      :try_start_21:
      if ((Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity") == 0) goto :cond_10f;
      if ("cn.soulapp.android.component.home.user.UserHomeActivity".equals((Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getClass().getName()) == 0) goto :cond_10f;
      if (p1.proceed().get(u1.f) == 0) goto :cond_10f;
      if (p0.b.get(u1.f) != 0) goto :cond_10f;
      if (p0.c.getInt(u1.f) != 0x2) goto :cond_10f;
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "alias").get(p1.proceed().get(u1.f)) == 0) goto :cond_7c;
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "alias").get(p1.proceed().get(u1.f)).trim().isEmpty() == 0) goto :cond_8d;
      goto :goto_7c;
      :catch_79:
      goto :goto_111;
      :cond_7c:
      :goto_7c:
      :cond_8d:
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "userIdEcpt").get(p1.proceed().get(u1.f)) == 0) goto :cond_10f;
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "userIdEcpt").get(p1.proceed().get(u1.f)).trim().isEmpty() != 0) goto :cond_10f;
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "alias").get(p1.proceed().get(u1.f)) == 0) goto :cond_10f;
      if ((String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "alias").get(p1.proceed().get(u1.f)).trim().isEmpty() != 0) goto :cond_10f;
      if ((Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getResources().getIdentifier("icon_share_collect", "drawable", (Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getPackageName()) == 0) goto :cond_10f;
      p0.e.setInt(p0.d.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x0), "插眼…", Boolean.FALSE}), 0x4e5310);
      p0.f.setInt(p0.d.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x0), "插眼…", Boolean.FALSE}), (Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getResources().getIdentifier("icon_share_collect", "drawable", (Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getPackageName()));
      new Ljava/util/ArrayList();
      if ((List) p0.g.get(u1.f) == 0) goto :cond_fa;
      :cond_e6:
      :goto_e6:
      if ((List) p0.g.get(u1.f).hasNext() == 0) goto :cond_fa;
      if (p0.e.getInt((List) p0.g.get(u1.f).next()) == 0x4e5310) goto :cond_e6;
      goto :goto_e6;
      :cond_fa:
      new Ljava/util/ArrayList().add(0x0, p0.d.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x0), "插眼…", Boolean.FALSE}));
      p0.g.set(u1.f, new Ljava/util/ArrayList());
      new Ldev/local/newsoul/t1((Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getResources().getIdentifier("icon_share_collect", "drawable", (Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity").getPackageName()), (String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "userIdEcpt").get(p1.proceed().get(u1.f)), (String) Ldev/local/newsoul/u1.f(p1.proceed().get(u1.f).getClass(), "alias").get(p1.proceed().get(u1.f)), p0.d.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x0), "插眼…", Boolean.FALSE}));
      :try_end_105:
      :try_start_105:
      :try_end_10a:
      goto :goto_10f;
      :catch_10c:
      goto :goto_111;
      :cond_10f:
      :goto_10f:
      goto :goto_119;
      :goto_111:
      goto :goto_10f;
      :goto_119:
      if ("profile bookmark menu unavailable" == 0) goto :cond_139;
      if ((Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity") == 0) goto :cond_139;
      p0.e.<init>("profile bookmark menu unavailable", (Activity) Ldev/local/newsoul/u1.c(u1.f, "getActivity"), u1.f, p0.h, p0.i, 0x0);
      u1.b.execute(p0.e);
      :cond_139:
      :goto_139:
      return p1.proceed();

      // try :try_start_21..:try_end_105 catch (Ljava/lang/Exception) -> :catch_79
      // try :try_start_105..:try_end_10a catch (Ljava/lang/Exception) -> :catch_10c
    }

}