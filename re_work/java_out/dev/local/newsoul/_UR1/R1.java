package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/R1 ====
// super: Ljava/lang/Object
// implements: IntConsume
// source: "SourceFile"

public final  class R1 extends Ljava/lang/Object implements Ljava/util/function/IntConsumer {

    public final SharedPreferences a;
    public final String[] b;
    public final C1 c;

    public void super(C1 p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void accept(int p0) {
      p0.a.edit().putString("chat_recall_style", p0.b[p1]).apply();
      p0.c.b(p0.b[p1], p0.b, 0x1);
      if (Q0.y != 0) goto :cond_20;
      goto :goto_32;
      :cond_20:
      new Landroid/os/Handler(p0.a.edit().putString("chat_recall_style", p0.b[p1]));
      new Ldev/local/newsoul/h(0x1);
      :goto_32:
      return;
    }

}