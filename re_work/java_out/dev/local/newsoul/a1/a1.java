package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/a1 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class a1 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final d1 b;

    public void super(Ldev/local/newsoul/d1;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_1e6 */ }
      if (p0.b.a.isInstance((Activity) p1.getThisObject()) == 0) goto :cond_3d;
      if (Ldev/local/newsoul/d1.f((Activity) p1.getThisObject()) == 0) goto :cond_3d;
      if ((Activity) p1.getThisObject().isChangingConfigurations() != 0) goto :cond_3d;
      if (p0.b.r == 0) goto :cond_3d;
      if (p0.b.r.equals((Activity) p1.getThisObject().getIntent().getStringExtra("dev.local.newsoul.message_forward_token")) == 0) goto :cond_3d;
      p0.b.s = 0x0;
      p0.b.r = 0x0;
      :cond_3d:
      if (p0.b.t == 0) goto :cond_4e;
      if (p0.b.t.getContext() != (Activity) p1.getThisObject()) goto :cond_4e;
      p0.b.t.dismiss();
      p0.b.t = 0x0;
      :cond_4e:
      return p0.b;
      :pswitch_53:
      if (Ldev/local/newsoul/d1.f((Activity) p1.getThisObject()) == 0) goto :cond_66;
      goto :goto_6a;
      :cond_66:
      :goto_6a:
      return Ldev/local/newsoul/d1.f((Activity) p1.getThisObject());
      :pswitch_6b:
      if (Ldev/local/newsoul/d1.f((Activity) 0x0.getThisObject()) != 0) goto :cond_81;
      goto :goto_8a;
      :cond_81:
      :try_start_81:
      p0.b.b((Activity) 0x0.getThisObject());
      :try_end_84:
      goto :goto_89;
      :catchall_85:
      Ldev/local/newsoul/d1.c((Activity) 0x0.getThisObject(), e);
      :goto_89:
      :goto_8a:
      return p0.b;
      :pswitch_8b:
      if (Ldev/local/newsoul/d1.f((Activity) 0x0.proceed().getThisObject()) == 0) goto :cond_13a;
      :try_start_a0:
      if (p0.b.i((Activity) 0x0.proceed().getThisObject()) != 0) goto :cond_ae;
      (Activity) 0x0.proceed().getThisObject().finish();
      goto :goto_13a;
      :catchall_ab:
      goto :goto_134;
      :cond_ae:
      new Ldev/local/newsoul/V0(e, (Activity) 0x0.proceed().getThisObject());
      :goto_f7:
      if ((Activity) p1.getThisObject().getIntent().getStringExtra("dev.local.newsoul.message_forward_token") >= 0x3) goto :cond_11c;
      if ((Activity) 0x0.proceed().getThisObject().getResources().getIdentifier(p0.b.i((Activity) 0x0.proceed().getThisObject())[(Activity) p1.getThisObject().getIntent().getStringExtra("dev.local.newsoul.message_forward_token")], "id", (Activity) 0x0.proceed().getThisObject().getPackageName()) != 0) goto :cond_10e;
      goto :goto_112;
      :cond_10e:
      :goto_112:
      if (0x0 == 0) goto :cond_119;
      0x0.setVisibility(0x8);
      :cond_119:
      goto :goto_f7;
      :cond_11c:
      (View) Ldev/local/newsoul/d1.d((Activity) 0x0.proceed().getThisObject(), "e").setVisibility(0x0);
      new Ldev/local/newsoul/C0(e, (Activity) 0x0.proceed().getThisObject(), 0x1);
      (View) Ldev/local/newsoul/d1.d((Activity) 0x0.proceed().getThisObject(), "e").setOnClickListener(new Ldev/local/newsoul/C0(e, (Activity) 0x0.proceed().getThisObject(), 0x1));
      Ldev/local/newsoul/d1.n((Activity) 0x0.proceed().getThisObject());
      :try_end_133:
      goto :goto_13a;
      :goto_134:
      Ldev/local/newsoul/d1.c((Activity) 0x0.proceed().getThisObject(), e);
      (Activity) 0x0.proceed().getThisObject().finish();
      :cond_13a:
      :goto_13a:
      return e;
      :pswitch_13b:
      if (Ldev/local/newsoul/d1.f((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject()) != 0) goto :cond_151;
      goto :goto_1b9;
      :cond_151:
      new Ljava/util/ArrayList();
      :try_start_157:
      if (p0.b.i((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject()) == 0) goto :cond_1ab;
      if (((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List) == 0) goto :cond_1ab;
      :cond_16f:
      :goto_16f:
      if (((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List).hasNext() == 0) goto :cond_1ab;
      if (Ldev/local/newsoul/d1.d(((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List).next(), "user") == 0) goto :cond_16f;
      if (Ldev/local/newsoul/d1.d(((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List).next(), "conversation") == 0) goto :cond_16f;
      if (p0.b.i((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject()).c((String) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List).next(), "conversation"), "getToUserId"), (Integer) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(((Activity) 0x0.proceed().getThisObject().proceed().getArg(0x0) instanceof List).next(), "conversation"), "getChatType").intValue()) == 0) goto :cond_16f;
      :try_end_1a6:
      goto :goto_16f;
      :catchall_1a7:
      Ldev/local/newsoul/d1.c((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject(), e);
      :cond_1ab:
      (Activity) 0x0.proceed().getThisObject().proceed().getArgs().toArray()[0x0] = Ldev/local/newsoul/d1.f((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject());
      :goto_1b9:
      return p0.b.i((Activity) (Activity) 0x0.proceed().getThisObject().getThisObject());
      :pswitch_1ba:
      if (Ldev/local/newsoul/d1.f((Activity) (Activity) 0x0.proceed().getThisObject().proceed().getThisObject()) != 0) goto :cond_1d0;
      goto :goto_1e4;
      :cond_1d0:
      :try_start_1d1:
      p0.b.m((Activity) (Activity) 0x0.proceed().getThisObject().proceed().getThisObject(), (Integer) (Activity) 0x0.proceed().getThisObject().proceed().proceed().getArg(Ldev/local/newsoul/d1.f((Activity) (Activity) 0x0.proceed().getThisObject().proceed().getThisObject())).intValue());
      :try_end_1de:
      goto :goto_1e3;
      :catchall_1df:
      Ldev/local/newsoul/d1.c((Activity) (Activity) 0x0.proceed().getThisObject().proceed().getThisObject(), e);
      :goto_1e3:
      :goto_1e4:
      return p0.b;
      :pswitch_data_1e6:

      // .packed-switch 0x0
      // :pswitch_1ba
      // :pswitch_13b
      // :pswitch_8b
      // :pswitch_6b
      // :pswitch_53

      // .catchall {:try_start_81 .. :try_end_84} :catchall_85
      // .catchall {:try_start_a0 .. :try_end_133} :catchall_ab
      // .catchall {:try_start_157 .. :try_end_1a6} :catchall_1a7
      // .catchall {:try_start_1d1 .. :try_end_1de} :catchall_1df
    }

}