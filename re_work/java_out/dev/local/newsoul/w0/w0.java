package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/w0 ====
// super: Ljava/lang/Object
// implements: IntConsume
// source: "SourceFile"

public final  class w0 extends Ljava/lang/Object implements Ljava/util/function/IntConsumer {

    public final y0 a;
    public final C1 b;

    public void super(C1 p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void accept(int p0) {
      if (p1 != p0.a.g) goto :cond_7;
      goto :goto_1d;
      :cond_7:
      if (p0.a.e() != 0) goto :cond_e;
      goto :goto_1d;
      :cond_e:
      p0.a.g = p1;
      p0.b.b(y0.h[p1], y0.h, 0x1);
      p0.a.d();
      :goto_1d:
      return;
    }

}