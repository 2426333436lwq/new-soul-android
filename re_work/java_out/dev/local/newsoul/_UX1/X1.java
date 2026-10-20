package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/X1 ====
// super: Ljava/lang/Object
// implements: BooleanSupplie
// source: "SourceFile"

public final  class X1 extends Ljava/lang/Object implements Ljava/util/function/BooleanSupplier {

    public final ArrayList a;
    public final List b;

    public void super(ArrayList p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final boolean getAsBoolean() {
      return (p0.a.equals(p0.b) ^ 0x1);
    }

}