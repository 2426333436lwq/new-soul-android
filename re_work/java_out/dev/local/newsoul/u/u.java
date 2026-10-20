package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/u ====
// super: Ljava/lang/Object
// implements: AutoCloseabl
// source: "SourceFile"

public final  class u extends Ljava/lang/Object implements Ljava/lang/AutoCloseable {

    public static final ThreadLocal g;
    public final Object a;
    public final Object b;
    public final Object c;
    public final u d;
    public boolean e;
    public boolean f;

    static void static {}() {
      new Ljava/lang/ThreadLocal();
      u.g = new Ljava/lang/ThreadLocal();
      return;
    }

    public void super(Object p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = (u) u.g.get();
      u.g.set(new Ljava/lang/Object());
      return;
    }

    public final void close() {
      if (p0.f == 0) goto :cond_5;
      return;
      :cond_5:
      p0.f = 0x1;
      if (p0.d != 0) goto :cond_12;
      u.g.remove();
      goto :goto_15;
      :cond_12:
      u.g.set(p0.d);
      :goto_15:
      return;
    }

}