package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Z2 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class Z2 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final b3 b;
    public final h3 c;

    public void super(Ldev/local/newsoul/b3;Ldev/local/newsoul/h3;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_3e */ }
      if (p0.b.n == 0) goto :cond_e;
      goto :goto_29;
      :cond_e:
      :try_start_e:
      p0.b.j();
      new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x3);
      new Ldev/local/newsoul/U2(0x3, p0.b);
      p0.b.a(new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x3), new Ldev/local/newsoul/U2(0x3, p0.b));
      :try_end_20:
      goto :goto_29;
      :catchall_21:
      Ldev/local/newsoul/d3.f(p0.b.c, "删除失败", e);
      :goto_29:
      return;
      :pswitch_2a:
      if (p0.b.n == 0) goto :cond_36;
      p0.b.c();
      goto :goto_3d;
      :cond_36:
      :try_start_36:
      p0.b.d(p0.c);
      :try_end_39:
      goto :goto_3d;
      :catchall_3a:
      p0.b.b();
      :goto_3d:
      return;
      :pswitch_data_3e:

      // .packed-switch 0x0
      // :pswitch_2a

      // .catchall {:try_start_e .. :try_end_20} :catchall_21
      // .catchall {:try_start_36 .. :try_end_39} :catchall_3a
    }

}