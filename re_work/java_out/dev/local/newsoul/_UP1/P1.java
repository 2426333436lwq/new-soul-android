package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/P1 ====
// super: Ljava/lang/Object
// implements: IntConsume
// source: "SourceFile"

public final  class P1 extends Ljava/lang/Object implements Ljava/util/function/IntConsumer {

    public final int a;
    public final SharedPreferences b;
    public final Object c;
    public final Object d;
    public final Object e;

    public void super(m1 p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public void super(ArrayList p0) {
      super();
      this.a = 0x1;
      this.c = p1;
      this.d = p2;
      this.b = p3;
      this.e = p4;
      return;
    }

    public final void accept(int p0) {
      switch (p0.a) { /* :pswitch_data_9a */ }
      if ((View) p0.c.isAttachedToWindow() == 0) goto :cond_6e;
      if ((View) p0.c.isShown() == 0) goto :cond_6e;
      if ((q2) p0.d.a.isFinishing() != 0) goto :cond_6e;
      if ((q2) p0.d.a.isDestroyed() == 0) goto :cond_2a;
      goto :goto_6e;
      :cond_2a:
      if (p1 != 0) goto :cond_2f;
      goto :goto_30;
      :cond_2f:
      :goto_30:
      :goto_3e:
      if (v3.hasNext() == 0) goto :cond_5b;
      if ((g2) v3.next().c.getAsBoolean() != 0) goto :cond_53;
      goto :goto_3e;
      :cond_53:
      goto :goto_3e;
      :cond_5b:
      if ((0x0 + 0x1) <= 0) goto :cond_6b;
      v2.apply();
      new Ldev/local/newsoul/h(0x0);
      :cond_6b:
      (q2) p0.d.e();
      :cond_6e:
      :goto_6e:
      return;
      :pswitch_6f:
      p0.b.edit().putString("feed_filter_gender", (String[]) p0.c[0x1]).apply();
      (C1) p0.d.b((String[]) p0.c[0x1], (String[]) p0.c, 0x1);
      (Runnable) p0.e.run();
      return;
      :pswitch_data_9a:

      // .packed-switch 0x0
      // :pswitch_6f
    }

}