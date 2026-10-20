package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/M1 ====
// super: Ljava/lang/Object
// implements: ViewTreeObserver$OnPreDrawListene
// source: "SourceFile"

public final  class M1 extends Ljava/lang/Object implements Landroid/view/ViewTreeObserver$OnPreDrawListener {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final boolean onPreDraw() {
      switch (p0.a) { /* :pswitch_data_18 */ }
      (I2) p0.b.m();
      return 0x1;
      :pswitch_e:
      (Runnable) p0.b.run();
      return 0x1;
      :pswitch_data_18:

      // .packed-switch 0x0
      // :pswitch_e
    }

}