package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/C ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class C extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final F b;

    public void super(Ldev/local/newsoul/F;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_164 */ }
      if (p0.b.getClass() == 0) goto :cond_1a;
      :cond_1a:
      if (0x0 == 0) goto :cond_27;
      :try_start_1c:
      0x0.a.b = (0x0.a.b + 0x1);
      goto :goto_27;
      :catchall_25:
      goto :goto_92;
      :cond_27:
      :goto_27:
      if (0x0 != 0) goto :cond_37;
      goto :goto_47;
      :cond_37:
      new Ljava/lang/StringBuilder("");
      :goto_47:
      new Ljava/lang/StringBuilder("hook_hit=true; ");
      Ldev/local/newsoul/Q0.r("repeat.menu.enter", new Ljava/lang/StringBuilder("hook_hit=true; ").toString());
      :try_end_59:
      :try_start_59:
      :try_end_5d:
      if (0x0 == 0) goto :cond_62;
      :try_start_5f:
      Ldev/local/newsoul/A.b("hook_hit=true; ");
      :try_end_62:
      :cond_62:
      :try_start_62:
      Ldev/local/newsoul/A.b(p0.b.a("hook_hit=true; ", e.getArg(0x0), e.getArg(0x2)));
      :try_end_71:
      if (0x0 == 0) goto :cond_76;
      0x0.close();
      :cond_76:
      goto :goto_8a;
      :catchall_78:
      :try_start_79:
      Ldev/local/newsoul/Q0.s("repeat.menu.skipped", e);
      :try_end_85:
      if (0x0 == 0) goto :cond_8a;
      0x0.close();
      :cond_8a:
      :goto_8a:
      return p0.b;
      :catchall_8b:
      :try_start_8c:
      Ldev/local/newsoul/Q0.s("repeat.menu.host_failed", e);
      throw e;
      :try_end_92:
      :goto_92:
      if (0x0 == 0) goto :cond_9c;
      :try_start_94:
      0x0.close();
      :try_end_97:
      goto :goto_9c;
      :catchall_98:
      e.addSuppressed(e);
      :cond_9c:
      :goto_9c:
      throw e;
      :pswitch_9d:
      if (e.getArg(0x7) != 0) goto :cond_af;
      goto :goto_162;
      :cond_af:
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() == 0x4e5301) goto :cond_d1;
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() == 0x4e5302) goto :cond_d1;
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() == 0x4e5303) goto :cond_d1;
      goto :goto_162;
      :cond_d1:
      new Ljava/lang/StringBuilder("action=");
      Ldev/local/newsoul/Q0.r("repeat.action.enter", new Ljava/lang/StringBuilder("action=").toString());
      if ((E) p0.b.a.get(e.getArg(0x7)) == 0) goto :cond_161;
      if ((E) p0.b.a.get(e.getArg(0x7)).e == 0) goto :cond_f4;
      goto :goto_161;
      :cond_f4:
      (E) p0.b.a.get(e.getArg(0x7)).e = 0x1;
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() != 0x4e5303) goto :cond_112;
      :try_start_fd:
      p0.b.n.h((E) p0.b.a.get(e.getArg(0x7)).a, Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, "getChatMessage")));
      goto :goto_161;
      :catchall_110:
      goto :goto_13a;
      :cond_112:
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() != 0x4e5302) goto :cond_136;
      e.m.g(e.k((E) p0.b.a.get(e.getArg(0x7)).a), (E) p0.b.a.get(e.getArg(0x7)).a.c, (E) p0.b.a.get(e.getArg(0x7)).a.d, (Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, "getChatMessage"))), "getMsgType").intValue(), Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, "getChatMessage")))));
      goto :goto_161;
      :cond_136:
      (Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, "getChatMessage"))), "getMsgType").j((E) p0.b.a.get(e.getArg(0x7)).a);
      :try_end_139:
      goto :goto_161;
      :goto_13a:
      Ldev/local/newsoul/Q0.s("repeat.action.failed", (Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b((E) p0.b.a.get(e.getArg(0x7)).b, "getChatMessage"))), "getMsgType"));
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() != 0x4e5303) goto :cond_152;
      goto :goto_159;
      :cond_152:
      if ((Integer) p0.b.c.invoke(e.getArg(0x7), 0x0).intValue() != 0x4e5302) goto :cond_157;
      goto :goto_159;
      :cond_157:
      :goto_159:
      Landroid/widget/Toast.makeText((Context) e.proceed().proceed().getArg(0x2), "无法打开转发，请重新选择", 0x0).show();
      :cond_161:
      :goto_161:
      :goto_162:
      return "无法打开转发，请重新选择";
      :pswitch_data_164:

      // .packed-switch 0x0
      // :pswitch_9d

      // .catchall {:try_start_1c .. :try_end_59} :catchall_25
      // .catchall {:try_start_59 .. :try_end_5d} :catchall_8b
      // .catchall {:try_start_5f .. :try_end_62} :catchall_25
      // .catchall {:try_start_62 .. :try_end_71} :catchall_78
      // .catchall {:try_start_79 .. :try_end_85} :catchall_25
      // .catchall {:try_start_8c .. :try_end_92} :catchall_25
      // .catchall {:try_start_94 .. :try_end_97} :catchall_98
      // .catchall {:try_start_fd .. :try_end_139} :catchall_110
    }

}