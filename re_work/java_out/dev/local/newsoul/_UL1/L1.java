package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/L1 ====
// super: Ljava/lang/Object
// implements: BooleanSupplie
// source: "SourceFile"

public final  class L1 extends Ljava/lang/Object implements Ljava/util/function/BooleanSupplier {

    public final String[] a;

    public void super(String[] p0) {
      super();
      this.a = p1;
      return;
    }

    public final boolean getAsBoolean() {
      if ("block_splash".equals(p0.a[0x0]) == 0) goto :cond_17;
      if (Q0.W == 0) goto :cond_1f;
      if (Q0.X == 0) goto :cond_1f;
      goto :goto_1f;
      :cond_17:
      :cond_1f:
      :goto_1f:
      return Q0.X;
    }

}