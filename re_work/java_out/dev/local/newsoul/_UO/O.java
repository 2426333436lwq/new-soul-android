package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/O ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class O extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final P a;
    public final boolean b;
    public final Activity c;

    public void super(Activity p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void run() {
      if (p0.b == 0) goto :cond_1e;
      if ((q2) p0.a.d.get() == 0) goto :cond_1b;
      if (Ldev/local/newsoul/S.d(p0.c, p0.a) == 0) goto :cond_1b;
      (q2) p0.a.d.get().e();
      :cond_1b:
      :cond_1e:
      if (p0.b == 0) goto :cond_23;
      goto :goto_25;
      :cond_23:
      :goto_25:
      Ldev/local/newsoul/S.b(p0.c, p0.a, "配置已导入\n部分功能重启 Soul 后生效");
      return;
    }

}