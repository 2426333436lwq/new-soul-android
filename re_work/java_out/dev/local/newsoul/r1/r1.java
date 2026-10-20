package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/r1 ====
// super: Ljava/lang/Object
// implements: IntConsume
// source: "SourceFile"

public final  class r1 extends Ljava/lang/Object implements Ljava/util/function/IntConsumer {

    public final TextView a;
    public final String b;
    public final Activity c;
    public final w1 d;
    public final q2 e;
    public final Runnable[] f;

    public void super(Runnable p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      return;
    }

    public final void accept(int p0) {
      p0.a.setEnabled(0x0);
      0x0.<init>(p0.b, p0.c, p0.d, p0.e, p0.f, p0.a, 0x1);
      u1.b.execute(0x0);
      return;
    }

}