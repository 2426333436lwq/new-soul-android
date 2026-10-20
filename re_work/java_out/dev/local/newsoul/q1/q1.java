package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/q1 ====
// super: Ljava/lang/Object
// implements: View$OnLongClickListene
// source: "SourceFile"

public final  class q1 extends Ljava/lang/Object implements Landroid/view/View$OnLongClickListener {

    public final Activity a;
    public final boolean b;
    public final TextView c;
    public final String d;
    public final w1 e;
    public final q2 f;
    public final Runnable[] g;

    public void super(Runnable p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      return;
    }

    public final boolean onLongClick(View p0) {
      new Ldev/local/newsoul/q2(p0.a, p0.b);
      v8.<init>(p0.c, p0.d, p0.a, p0.e, p0.f, p0.g);
      new Ldev/local/newsoul/q2(p0.a, p0.b).h(p0.c, new String[][] {"移除插眼"}, 0x1, v8);
      return 0x1;
    }

}