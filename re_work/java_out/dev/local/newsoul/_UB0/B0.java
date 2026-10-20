package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/B0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class B0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int[] a;
    public final int b;
    public final Runnable c;

    public void super(IILdev/local/newsoul/Z;[] p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void onClick(View p0) {
      p0.a[0x0] = p0.b;
      p0.c.run();
      return;
    }

}