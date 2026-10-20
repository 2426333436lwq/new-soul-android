package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/x0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class x0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final int b;
    public final Object c;

    public void super(Ljava/lang/Object;II p0) {
      super();
      this.a = p3;
      this.c = p1;
      this.b = p2;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_64 */ }
      (v2) p0.c.b(p0.b, 0x1);
      return;
      :pswitch_10:
      if ((C1) p0.c.h == 0) goto :cond_1d;
      (C1) p0.c.h.accept(p0.b);
      :cond_1d:
      return;
      :pswitch_1e:
      if ((y0) p0.c.e() != 0) goto :cond_29;
      goto :goto_63;
      :cond_29:
      if ((y0) p0.c.g != 0) goto :cond_3b;
      goto :goto_3e;
      :cond_3b:
      :goto_3e:
      (y0) p0.c.e[(y0) p0.c.g] = Ljava/lang/String.valueOf(Ljava/lang/Math.max(0x0, v5, Ljava/lang/Math.min(0x7fffffff, v7, (((long) Ljava/lang/Integer.parseInt((y0) p0.c.e[(y0) p0.c.g])) + ((long) p0.b)), v3), v3), v3);
      (y0) p0.c.c.setText((y0) p0.c.e[(y0) p0.c.g]);
      (y0) p0.c.c.setSelection((y0) p0.c.c.length());
      :goto_63:
      return;
      :pswitch_data_64:

      // .packed-switch 0x0
      // :pswitch_1e
      // :pswitch_10
    }

}