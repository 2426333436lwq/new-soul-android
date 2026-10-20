package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/n1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class n1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;
    public final boolean c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    public void super(Field p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.d = p2;
      this.e = p3;
      this.c = p4;
      this.f = p5;
      this.g = p6;
      return;
    }

    public void super(Runnable p0) {
      super();
      this.a = 0x1;
      this.d = p1;
      this.e = p2;
      this.f = p3;
      this.b = p4;
      this.c = p5;
      this.g = p6;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_f4 */ }
      if (Ldev/local/newsoul/u1.i((q2) p0.d) != 0) goto :cond_11;
      goto :goto_a1;
      :cond_11:
      (q2) p0.d.h.removeAllViews();
      :try_start_1b:
      :try_end_23:
      goto :goto_25;
      :catch_24:
      :goto_25:
      if (Ldev/local/newsoul/u1.i((q2) p0.d) != 0) goto :cond_38;
      v9.addView(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j));
      goto :goto_a1;
      :cond_38:
      if ((List) p0.f.isEmpty() == 0) goto :cond_4b;
      v9.addView(Ldev/local/newsoul/w2.n((q2) p0.d.g, "暂无插眼", 0xe, (q2) p0.d.j));
      :cond_4b:
      :goto_4f:
      if (v11.hasNext() == 0) goto :cond_a1;
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/u1.a(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (w1) v11.next().b).setContentDescription(new Ljava/lang/StringBuilder().toString());
      v8.<init>(v10, (w1) v11.next(), Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), p0.b, 0x1);
      Ldev/local/newsoul/u1.a(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (w1) v11.next().b).setOnClickListener(v8);
      v14.<init>(p0.b, p0.c, Ldev/local/newsoul/u1.a(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (w1) v11.next().b), v10, (w1) v11.next(), Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (Runnable[]) p0.g);
      Ldev/local/newsoul/u1.a(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (w1) v11.next().b).setOnLongClickListener(v14);
      Ldev/local/newsoul/w2.b(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), v9, Ldev/local/newsoul/u1.a(Ldev/local/newsoul/w2.n((q2) p0.d.g, "账号已切换，请重新打开列表", 0xe, (q2) p0.d.j), (w1) v11.next().b));
      goto :goto_4f;
      :cond_a1:
      :goto_a1:
      return;
      :pswitch_a2:
      if (Ldev/local/newsoul/u1.b(p0.b) == 0) goto :cond_f3;
      :try_start_ba:
      :try_end_c2:
      goto :goto_c4;
      :catch_c3:
      :goto_c4:
      if (v10 == 0) goto :cond_f3;
      if (u1.c.get(p0.e) == Ldev/local/newsoul/u1.b(p0.b)) goto :cond_d1;
      goto :goto_f3;
      :cond_d1:
      :try_start_d1:
      Ldev/local/newsoul/u1.b(p0.b).f = p0.c;
      Ldev/local/newsoul/u1.b(p0.b).e = 0x1;
      if (p0.c == 0) goto :cond_df;
      goto :goto_e1;
      :catch_dd:
      goto :goto_f0;
      :cond_df:
      :goto_e1:
      (Field) p0.f.set(Ldev/local/newsoul/u1.b(p0.b).d, e);
      if ((Field) p0.g.get(p0.e) == 0) goto :cond_f3;
      :try_end_ef:
      goto :goto_f3;
      :goto_f0:
      Ldev/local/newsoul/u1.e(p0.b, (Field) p0.g.get(p0.e));
      :cond_f3:
      :goto_f3:
      return;
      :pswitch_data_f4:

      // .packed-switch 0x0
      // :pswitch_a2

      // try :try_start_1b..:try_end_23 catch (Ljava/lang/Exception) -> :catch_24
      // try :try_start_ba..:try_end_c2 catch (Ljava/lang/Exception) -> :catch_c3
      // try :try_start_d1..:try_end_ef catch (Ljava/lang/Exception) -> :catch_dd
    }

}