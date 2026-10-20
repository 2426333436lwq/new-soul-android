package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/S1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class S1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final r2 b;

    public void super(Ldev/local/newsoul/r2;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_1e */ }
      if (p0.b.isEnabled() == 0) goto :cond_10;
      p0.b.toggle();
      :cond_10:
      return;
      :pswitch_11:
      if (p0.b.isEnabled() == 0) goto :cond_1c;
      p0.b.toggle();
      :cond_1c:
      return;
      :pswitch_data_1e:

      // .packed-switch 0x0
      // :pswitch_11
    }

}