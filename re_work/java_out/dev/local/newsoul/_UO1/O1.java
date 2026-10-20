package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/O1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class O1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final q2 a;
    public final Activity b;
    public final boolean c;
    public final boolean d;
    public final String e;

    public void super(String p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void onClick(View p0) {
      p0.a.e.dismiss();
      Ldev/local/newsoul/w2.t(p0.b, p0.c, p0.d, p0.e);
      return;
    }

}