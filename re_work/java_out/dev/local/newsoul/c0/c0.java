package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/c0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class c0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final f0 b;

    public void super(Ldev/local/newsoul/f0;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_48 */ }
      p0.b.h = 0x1;
      p0.b.b();
      new Ldev/local/newsoul/d0(p0.b, 0x1);
      new Ldev/local/newsoul/T(0x0, new Ldev/local/newsoul/d0(p0.b, 0x1));
      V.a.execute(new Ldev/local/newsoul/T(0x0, new Ldev/local/newsoul/d0(p0.b, 0x1)));
      return;
      :pswitch_1f:
      if (g0.d.containsKey(p0.b.a.a) == 0) goto :cond_2e;
      goto :goto_46;
      :cond_2e:
      new Ldev/local/newsoul/a0(p0.b);
      p0.b.a("正在准备记录…");
      new Ldev/local/newsoul/H(p0.b.a.a, p0.b, new Ldev/local/newsoul/a0(p0.b), 0x3);
      V.a.execute(new Ldev/local/newsoul/H(p0.b.a.a, p0.b, new Ldev/local/newsoul/a0(p0.b), 0x3));
      :goto_46:
      return;
      :pswitch_data_48:

      // .packed-switch 0x0
      // :pswitch_1f
    }

}