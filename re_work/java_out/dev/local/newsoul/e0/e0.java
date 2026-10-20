package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/e0 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class e0 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final f0 a;

    public void super(f0 p0) {
      super();
      this.a = p1;
      return;
    }

    public final void run() {
      if (p0.a.a.e.isShowing() != 0) goto :cond_d;
      return;
      :cond_d:
      p0.a.b();
      return;
    }

}