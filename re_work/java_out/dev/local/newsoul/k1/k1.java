package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/k1 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class k1 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Field b;

    public void super(Ljava/lang/reflect/Field;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_aa */ }
      if (p0.b.getInt(p1.getArg(0x0)) == 0x4e5310) goto :cond_1d;
      goto :goto_87;
      :cond_1d:
      :try_start_1d:
      if ((t1) u1.c.get(p1.proceed().getThisObject()) == 0) goto :cond_6e;
      if ((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity") != 0) goto :cond_34;
      goto :goto_6e;
      :cond_34:
      if ((t1) u1.c.get(p1.proceed().getThisObject()).e != 0) goto :cond_53;
      if ((t1) u1.c.get(p1.proceed().getThisObject()).h == 0) goto :cond_41;
      goto :goto_43;
      :catch_3f:
      goto :goto_71;
      :cond_41:
      :goto_43:
      if (Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")) == 0) goto :cond_50;
      Landroid/widget/Toast.makeText((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity"), "读取插眼列表失败，请重新打开菜单", e).show();
      :cond_50:
      goto :goto_87;
      :cond_53:
      if (Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")).g == 0) goto :cond_5a;
      goto :goto_87;
      :cond_5a:
      Ldev/local/newsoul/u1.h(Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")).a);
      Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")).g = 0x1;
      new Ldev/local/newsoul/H(Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")), (Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity"), "读取插眼列表失败，请重新打开菜单", 0x6);
      u1.b.execute(new Ldev/local/newsoul/H(Ldev/local/newsoul/u1.b((Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity")), (Activity) Ldev/local/newsoul/u1.c(p1.proceed().getThisObject(), "getActivity"), "读取插眼列表失败，请重新打开菜单", 0x6));
      goto :goto_85;
      :cond_6e:
      :goto_6e:
      :try_end_70:
      goto :goto_87;
      :goto_71:
      :try_start_78:
      Ldev/local/newsoul/u1.e((Activity) Ldev/local/newsoul/u1.c(Boolean.TRUE.getThisObject(), "getActivity"), u1.b);
      :try_end_85:
      :catch_85:
      :goto_85:
      :goto_87:
      return "getActivity";
      :pswitch_88:
      if (p0.b.get(Boolean.TRUE.getThisObject()) == 0) goto :cond_a5;
      if (Ldev/local/newsoul/Q0.q("post_remove_share_limit") == 0) goto :cond_a5;
      goto :goto_a9;
      :cond_a5:
      :goto_a9:
      return "post share restriction bypassed";
      :pswitch_data_aa:

      // .packed-switch 0x0
      // :pswitch_88

      // try :try_start_1d..:try_end_70 catch (Ljava/lang/Exception) -> :catch_3f
      // try :try_start_78..:try_end_85 catch (Ljava/lang/Exception) -> :catch_85
    }

}