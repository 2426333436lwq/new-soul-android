package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/V2 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class V2 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final b3 b;
    public final q2 c;

    public void super(Ldev/local/newsoul/b3;Ldev/local/newsoul/q2;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_6c */ }
      if (p0.b.m != 0) goto :cond_3e;
      if (p0.b.h == p0.c) goto :cond_12;
      goto :goto_3e;
      :cond_12:
      :try_start_12:
      p0.b.j();
      :try_end_15:
      p0.c.h.removeAllViews();
      p0.c.h.addView(Ldev/local/newsoul/w2.n(p0.c.g, "读取语音库失败，请重试", 0xe, p0.c.i));
      new Ldev/local/newsoul/U2(0x3, p0.b);
      p0.c.h.addView(Ldev/local/newsoul/d3.a(p0.c, "重试", new Ldev/local/newsoul/U2(0x3, p0.b)));
      goto :goto_3e;
      :catch_3b:
      Ldev/local/newsoul/d3.a(p0.c, "重试", new Ldev/local/newsoul/U2(0x3, p0.b)).b();
      :cond_3e:
      :goto_3e:
      return;
      :pswitch_3f:
      :try_start_45:
      new Ldev/local/newsoul/H(p0.c.h, Ldev/local/newsoul/d3.a(p0.c, "重试", new Ldev/local/newsoul/U2(0x3, p0.b)), p0.c.h.f.d(), 0xd);
      :try_end_57:
      goto :goto_6b;
      :catchall_58:
      new Ldev/local/newsoul/V2(p0.c.h, Ldev/local/newsoul/d3.a(p0.c, "重试", new Ldev/local/newsoul/U2(0x3, p0.b)), 0x1);
      :goto_6b:
      return;
      :pswitch_data_6c:

      // .packed-switch 0x0
      // :pswitch_3f

      // try :try_start_12..:try_end_15 catch (Ljava/lang/Exception) -> :catch_3b
      // .catchall {:try_start_45 .. :try_end_57} :catchall_58
    }

}