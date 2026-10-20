package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/D ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class D extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Object b;
    public final Object c;

    public void super(Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_1ee */ }
      if (((Method) p0.b.invoke(0x0, 0x0) instanceof Context) == 0) goto :cond_38;
      if (Ldev/local/newsoul/w2.o((Context) (Method) p0.b.invoke(0x0, 0x0)).getBoolean("block_splash", 0x1) != 0) goto :cond_26;
      goto :goto_38;
      :cond_26:
      (XposedModule) p0.c.log(0x4, "SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
      goto :goto_3c;
      :cond_38:
      :goto_38:
      :goto_3c:
      return Boolean.FALSE;
      :pswitch_3d:
      if (Boolean.FALSE.getArg(0x0) == 0) goto :cond_5b;
      goto :goto_5c;
      :cond_5b:
      :goto_5c:
      if (Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)) == 0) goto :cond_80;
      :try_start_5f:
      Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)).a.d = (Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)).a.d + 0x1);
      new Ljava/lang/StringBuilder("source=");
      Ldev/local/newsoul/Q0.r("repeat.render.enter", new Ljava/lang/StringBuilder("source=").toString());
      goto :goto_80;
      :catchall_7c:
      goto :goto_1e2;
      :cond_80:
      :goto_80:
      Ldev/local/newsoul/A.b((F) p0.b.i(e.getArg(0x0)));
      if ((F) p0.b.i(e.getArg(0x0)) != e.getArg(0x0)) goto :cond_92;
      goto :goto_9a;
      :cond_92:
      :try_end_9a:
      :goto_9a:
      :try_start_9a:
      :try_end_9c:
      if ((F) p0.b.i(e.getArg(0x0)) != 0) goto :cond_af;
      :try_start_a0:
      Ldev/local/newsoul/Q0.r("repeat.render", "entries=null");
      :try_end_a5:
      if (Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)) == 0) goto :cond_1e1;
      :goto_a7:
      Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)).close();
      goto :goto_1e1;
      :catchall_ac:
      goto :goto_1d1;
      :cond_af:
      :try_start_af:
      if (p1.proceed() == 0) goto :cond_104;
      :goto_c3:
      if (p1.proceed().hasNext() == 0) goto :cond_e4;
      if ((Integer) (F) p0.b.c.invoke(p1.proceed().next(), 0x0).intValue() == 0x4e5301) goto :cond_df;
      if ((Integer) (F) p0.b.c.invoke(p1.proceed().next(), 0x0).intValue() == 0x4e5302) goto :cond_df;
      if ((Integer) (F) p0.b.c.invoke(p1.proceed().next(), 0x0).intValue() != 0x4e5303) goto :cond_e1;
      :cond_df:
      :cond_e1:
      goto :goto_c3;
      :cond_e4:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.r("repeat.render", new Ljava/lang/StringBuilder().toString());
      :cond_104:
      :goto_10a:
      if (0x1 >= 0x0) goto :cond_1dd;
      if (GridLayout.class.isAssignableFrom(e[0x1].getType()) != 0) goto :cond_11f;
      goto :goto_1c9;
      :cond_11f:
      e[0x1].setAccessible(0x1);
      if ((GridLayout) e[0x1].get(p1.getThisObject()) != 0) goto :cond_136;
      :goto_133:
      goto :goto_13b;
      :cond_136:
      goto :goto_133;
      :goto_13b:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.r("repeat.render.grid", new Ljava/lang/StringBuilder().toString());
      if ((GridLayout) e[0x1].get(p1.getThisObject()) == 0) goto :cond_167;
      if ((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount() == (F) p0.b.i(e.getArg(0x0)).size()) goto :cond_171;
      :cond_167:
      goto :goto_1c9;
      :cond_171:
      :goto_172:
      if ((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount() >= (F) p0.b.i(e.getArg(0x0)).size()) goto :cond_167;
      if ((Integer) (F) p0.b.c.invoke((F) p0.b.i(e.getArg(0x0)).get((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount()), 0x4e5303).intValue() != 0x4e5303) goto :cond_1a9;
      if (Ldev/local/newsoul/j1.a((GridLayout) e[0x1].get(p1.getThisObject()).getChildAt((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount())) == 0) goto :cond_1a9;
      new Ldev/local/newsoul/e3(Ldev/local/newsoul/j1.a((GridLayout) e[0x1].get(p1.getThisObject()).getChildAt((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount())).getResources().getDisplayMetrics().density);
      Ldev/local/newsoul/j1.a((GridLayout) e[0x1].get(p1.getThisObject()).getChildAt((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount())).setImageDrawable(new Ldev/local/newsoul/e3(Ldev/local/newsoul/j1.a((GridLayout) e[0x1].get(p1.getThisObject()).getChildAt((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount())).getResources().getDisplayMetrics().density));
      :cond_1a9:
      if ((Integer) (F) p0.b.c.invoke((F) p0.b.i(e.getArg(0x0)).get((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount()), 0x4e5303).intValue() == 0x4e5301) goto :cond_1bb;
      if ((Integer) (F) p0.b.c.invoke((F) p0.b.i(e.getArg(0x0)).get((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount()), 0x4e5303).intValue() == 0x4e5302) goto :cond_1c1;
      if ((Integer) (F) p0.b.c.invoke((F) p0.b.i(e.getArg(0x0)).get((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount()), 0x4e5303).intValue() != 0x4e5303) goto :cond_1b9;
      goto :goto_1c1;
      :cond_1b9:
      goto :goto_1c2;
      :cond_1bb:
      :cond_1c1:
      :goto_1c1:
      :goto_1c2:
      Ldev/local/newsoul/j1.b(Ldev/local/newsoul/j1.a((GridLayout) e[0x1].get(p1.getThisObject()).getChildAt((GridLayout) e[0x1].get(p1.getThisObject()).getChildCount())), 0x0);
      :try_end_1c5:
      goto :goto_172;
      :goto_1c9:
      goto :goto_10a;
      :goto_1d1:
      :try_start_1d1:
      Ldev/local/newsoul/Q0.s("repeat.render.failed", e);
      :try_end_1dd:
      :cond_1dd:
      if (Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)) == 0) goto :cond_1e1;
      goto :goto_a7;
      :cond_1e1:
      :goto_1e1:
      return e;
      :goto_1e2:
      if (Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)) == 0) goto :cond_1ed;
      :try_start_1e4:
      Ldev/local/newsoul/A.a(0x0, Boolean.FALSE.getArg(0x0)).close();
      :try_end_1e7:
      goto :goto_1ed;
      :catchall_1e8:
      e.addSuppressed(e);
      :cond_1ed:
      :goto_1ed:
      throw e;
      :pswitch_data_1ee:

      // .packed-switch 0x0
      // :pswitch_3d

      // .catchall {:try_start_5f .. :try_end_9a} :catchall_7c
      // .catchall {:try_start_9a .. :try_end_9c} :catchall_ac
      // .catchall {:try_start_a0 .. :try_end_a5} :catchall_ac
      // .catchall {:try_start_af .. :try_end_1c5} :catchall_ac
      // .catchall {:try_start_1d1 .. :try_end_1dd} :catchall_7c
      // .catchall {:try_start_1e4 .. :try_end_1e7} :catchall_1e8
    }

}