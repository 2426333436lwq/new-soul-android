package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/K1 ====
// super: Ljava/lang/Object
// implements: Consume
// source: "SourceFile"

public final  class K1 extends Ljava/lang/Object implements Ljava/util/function/Consumer {

    public final q2 a;
    public final SharedPreferences b;
    public final List c;

    public void super(ArrayList p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void accept(Object p0) {
      if ((View) p1.isAttachedToWindow() == 0) goto :cond_50;
      if ((View) p1.isShown() == 0) goto :cond_50;
      if (p0.a.a.isFinishing() != 0) goto :cond_50;
      if (p0.a.a.isDestroyed() == 0) goto :cond_21;
      goto :goto_50;
      :cond_21:
      :goto_28:
      if (v2.hasNext() == 0) goto :cond_3c;
      goto :goto_28;
      :cond_3c:
      new Ldev/local/newsoul/P1((View) p1, p0.a, p0.b, (ArrayList) p0.a.a.isDestroyed());
      p0.a.h((View) p1, new String[][] {"屏蔽本组", "取消屏蔽本组"}, (v3 | (g2) v2.next().c.getAsBoolean()), new Ldev/local/newsoul/P1((View) p1, p0.a, p0.b, (ArrayList) p0.a.a.isDestroyed()));
      :cond_50:
      :goto_50:
      return;
    }

}