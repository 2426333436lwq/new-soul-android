package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Y2 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class Y2 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final b3 a;
    public final q2 b;
    public final ScrollView c;
    public final int d;

    public void super(Ldev/local/newsoul/b3;Ldev/local/newsoul/q2;Landroid/widget/ScrollView;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      return;
    }

    public final void run() {
      if (p0.a.m != 0) goto :cond_14;
      if (p0.a.h != p0.b) goto :cond_14;
      p0.c.scrollTo(0x0, p0.d);
      :cond_14:
      return;
    }

}