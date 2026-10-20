package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/y ====
// super: Ljava/lang/Object
// implements: AutoCloseabl
// source: "SourceFile"

public final  class y extends Ljava/lang/Object implements Ljava/lang/AutoCloseable {

    public final z a;
    public final z b;
    public boolean c;

    public void super(z p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void close() {
      if (p0.c == 0) goto :cond_5;
      return;
      :cond_5:
      p0.c = 0x1;
      if (p0.b != 0) goto :cond_12;
      A.b.remove();
      goto :goto_17;
      :cond_12:
      A.b.set(A.b);
      :goto_17:
      return;
    }

}