package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/H2 ====
// super: Ljava/lang/Object
// implements: P
// source: "SourceFile"

public final  class H2 extends Ljava/lang/Object implements Ldev/local/newsoul/P2 {

    public final int a;
    public final Object b;
    public final Object c;

    public void super(Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.b = p1;
      this.c = p2;
      this.a = p3;
      return;
    }

    public void a(String p0) {
      (b3) p0.b.j();
      v7.<init>((b3) p0.b, (b3) p0.b.c.getApplicationContext(), (String) p0.c, p1, p0.a);
      new Ldev/local/newsoul/U2(0x2, (b3) p0.b);
      (b3) p0.b.a(v7, new Ldev/local/newsoul/U2(0x2, (b3) p0.b));
      return;
    }

    public void b(boolean p0) {
      if (p1 != 0) goto :cond_20;
      :cond_20:
      if ((I2) p0.b.m == 0) goto :cond_79;
      if (p0.a == (I2) p0.b.p) goto :cond_2b;
      goto :goto_79;
      :cond_2b:
      if (p1 == 0) goto :cond_61;
      (I2) p0.b.o = ((I2) p0.b.o + 0x1);
      new Ljava/lang/StringBuilder("loaded like response: success=");
      (I2) p0.b.c.a(((I2) p0.b.j.size() - (I2) p0.b.n), 0x1);
      goto :goto_79;
      :cond_61:
      new Ljava/lang/StringBuilder("点赞失败，已停止；本批成功 ");
      (I2) p0.b.d(new Ljava/lang/StringBuilder("点赞失败，已停止；本批成功 ").toString());
      :cond_79:
      :goto_79:
      return;
    }

}