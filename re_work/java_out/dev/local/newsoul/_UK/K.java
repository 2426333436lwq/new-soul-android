package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/K ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class K extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;
    public final Map c;
    public final P d;

    public void super(Map p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.d = p2;
      this.c = p3;
      return;
    }

    public void super(P p0) {
      super();
      this.a = 0x1;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_118 */ }
      :try_start_b:
      :goto_1b:
      if (v2.hasNext() == 0) goto :cond_5f;
      if (((Map$Entry) v2.next().getValue() instanceof Boolean) == 0) goto :cond_3f;
      goto :goto_1b;
      :cond_3f:
      if (((Boolean) (Map$Entry) v2.next().getValue().booleanValue() instanceof Integer) == 0) goto :cond_53;
      goto :goto_1b;
      :cond_53:
      goto :goto_1b;
      :cond_5f:
      :try_end_63:
      :catch_63:
      new Ldev/local/newsoul/O(p0.d, v1, v0);
      return;
      :pswitch_70:
      if (Ldev/local/newsoul/S.d(p0.b, p0.d) != 0) goto :cond_81;
      Ldev/local/newsoul/S.b(p0.b, p0.d, "已取消导入");
      goto :goto_116;
      :cond_81:
      if ((Q) p0.d.e.get() == 0) goto :cond_90;
      (Q) p0.d.e.get().a("配置已读取，等待确认导入", "已取消导入");
      :cond_90:
      new Ldev/local/newsoul/q2(p0.b, p0.d.b);
      new Ljava/lang/StringBuilder("将导入 ");
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.d.b).g, new Ljava/lang/StringBuilder("将导入 ").toString(), 0xf, new Ldev/local/newsoul/q2(p0.b, p0.d.b).i));
      if (Boolean.TRUE.equals(p0.c.get("square_auto_like")) == 0) goto :cond_d8;
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.d.b).g, "此配置会开启自动点赞。", 0xe, new Ldev/local/newsoul/q2(p0.b, p0.d.b).i));
      :cond_d8:
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.d.b).g, "当前不可用的配置项仅保存，不启用对应功能。", 0xd, new Ldev/local/newsoul/q2(p0.b, p0.d.b).j));
      new Ldev/local/newsoul/L(v1);
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).g("导入配置", "确认导入");
      new boolean[]["已取消导入"][v1] = v1;
      new Ldev/local/newsoul/M(new boolean[]["已取消导入"], p0.b, p0.d);
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).e.setOnDismissListener(new Ldev/local/newsoul/M(new boolean[]["已取消导入"], p0.b, p0.d));
      -0x1.<init>(new boolean[]["已取消导入"], p0.d, new Ldev/local/newsoul/q2(p0.b, p0.d.b), p0.b, p0.c);
      new Ldev/local/newsoul/q2(p0.b, p0.d.b).e.getButton(-0x1).setOnClickListener(-0x1);
      :goto_116:
      return;
      :pswitch_data_118:

      // .packed-switch 0x0
      // :pswitch_70

      // try :try_start_b..:try_end_63 catch (Ljava/lang/Exception) -> :catch_63
    }

}