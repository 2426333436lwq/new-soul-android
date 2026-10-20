package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/F2 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class F2 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final I2 b;

    public void super(Ldev/local/newsoul/I2;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_58 */ }
      if (p0.b.m == 0) goto :cond_10;
      p0.b.d("点赞响应超时，已停止");
      :cond_10:
      return;
      :pswitch_11:
      p0.b.f();
      return;
      :pswitch_17:
      if (p0.b.l != 0) goto :cond_1e;
      goto :goto_56;
      :cond_1e:
      p0.b.m();
      if (p0.b.m != 0) goto :cond_4f;
      if (p0.b.g() != 0) goto :cond_30;
      goto :goto_38;
      :cond_30:
      :goto_38:
      p0.b.c.a(0x0, 0x0);
      if (0x0 <= 0) goto :cond_4f;
      if (p0.b.b() == 0) goto :cond_4f;
      if (p0.b.t.isEmpty() == 0) goto :cond_4f;
      p0.b.l(0x1);
      :cond_4f:
      :goto_56:
      return;
      :pswitch_data_58:

      // .packed-switch 0x0
      // :pswitch_17
      // :pswitch_11
    }

}