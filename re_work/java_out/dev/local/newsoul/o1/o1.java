package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/o1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class o1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    public void super(String p0) {
      super();
      this.a = 0x2;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      this.f = p5;
      this.g = p6;
      return;
    }

    public void super(Ljava/lang/Object;Landroid/app/Activity;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p7;
      this.c = p1;
      this.b = p2;
      this.d = p3;
      this.e = p4;
      this.f = p5;
      this.g = p6;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_b6 */ }
      :try_start_6:
      :try_end_a:
      goto :goto_11;
      :catch_d:
      :goto_11:
      v0.<init>(p0.b, (q2) p0.c, (Button) (TextView) p0.d, v0, (TextView) p0.e, v1, (String) p0.f, (String[]) p0.g);
      p0.b.runOnUiThread(v0);
      return;
      :pswitch_39:
      :try_start_4d:
      Ldev/local/newsoul/u1.h(v0);
      new Ldev/local/newsoul/x1(v1.getFilesDir(), v0, 0x0);
      new Ldev/local/newsoul/x1(v1.getFilesDir(), v0, 0x0).i(v2.a);
      new Ldev/local/newsoul/g(v3, v4, 0x5);
      :try_end_68:
      goto :goto_78;
      :catch_69:
      new Ldev/local/newsoul/H((TextView) p0.g, v1, e, 0x9);
      :goto_78:
      return;
      :pswitch_79:
      (t1) p0.c.h = 0x1;
      if (Ldev/local/newsoul/u1.b(p0.b) == 0) goto :cond_b4;
      if (u1.c.get(p0.d) == (t1) p0.c) goto :cond_9b;
      goto :goto_b4;
      :cond_9b:
      :try_start_9b:
      (Field) p0.e.set((t1) p0.c.d, "插眼不可用");
      if ((Field) p0.f.get(p0.d) == 0) goto :cond_ad;
      :try_end_ad:
      :catch_ad:
      :cond_ad:
      Ldev/local/newsoul/u1.e(p0.b, (Exception) p0.g);
      :cond_b4:
      :goto_b4:
      return;
      :pswitch_data_b6:

      // .packed-switch 0x0
      // :pswitch_79
      // :pswitch_39

      // try :try_start_6..:try_end_a catch (Ljava/lang/Exception) -> :catch_d
      // try :try_start_4d..:try_end_68 catch (Ljava/lang/Exception) -> :catch_69
      // try :try_start_9b..:try_end_ad catch (Ljava/lang/Exception) -> :catch_ad
    }

}