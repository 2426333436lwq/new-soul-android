package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Y ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class Y extends Ljava/lang/Object {

    public final Object a;
    public final Object b;

    public void super() {
      super();
      this.a = __result__;
      this.b = __result__;
      return;
    }

    public void super(PublicKey p0) {
      super();
      this.a = p1;
      this.b = p2;
      if (p1.isDirectory() != 0) goto :cond_1c;
      if (p1.mkdirs() == 0) goto :cond_14;
      goto :goto_1c;
      :cond_14:
      new Ljava/io/IOException("Storage unavailable");
      throw new Ljava/io/IOException("Storage unavailable");
      :cond_1c:
      :goto_1c:
      if (p1.listFiles() != 0) goto :cond_25;
      :cond_25:
      :goto_26:
      if (0x0 >= v0) goto :cond_3c;
      if (p1.listFiles()[0x0].getName().endsWith(".tmp") == 0) goto :cond_39;
      :cond_39:
      goto :goto_26;
      :cond_3c:
      return;
    }

    public void super(String p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public void a(U2 p0) {
      Ldev/local/newsoul/H0.h((RenderNode) p0.a, p2, p3);
      :try_start_f:
      p9.a(v0);
      :try_end_12:
      Ldev/local/newsoul/H0.j((RenderNode) p0.a);
      Ldev/local/newsoul/I0.e((RuntimeShader) p0.b, p4.centerX(), p4.centerY());
      Ldev/local/newsoul/I0.h((RuntimeShader) p0.b, (p4.width() / 0x40000000    # 2.0f), (p4.height() / 0x40000000    # 2.0f));
      Ldev/local/newsoul/I0.k((RuntimeShader) p0.b, ((float) p2), ((float) p3));
      Ldev/local/newsoul/I0.d((RuntimeShader) p0.b, Ljava/lang/Math.min(p5, (Ljava/lang/Math.min(p4.width(), p4.height()) / 0x40000000    # 2.0f)));
      Ldev/local/newsoul/I0.g((RuntimeShader) p0.b, p6);
      Ldev/local/newsoul/I0.j((RuntimeShader) p0.b, p7);
      if (p8 == 0) goto :cond_73;
      goto :goto_74;
      :cond_73:
      :goto_74:
      Ldev/local/newsoul/I0.l((RuntimeShader) p0.b, 0x3f800000    # 1.0f);
      Ldev/local/newsoul/J0.a((RenderNode) p0.a, Ldev/local/newsoul/I0.a((RuntimeShader) p0.b));
      Ldev/local/newsoul/H0.f(p1, (RenderNode) p0.a);
      return;
      :catchall_8e:
      Ldev/local/newsoul/H0.j((RenderNode) p0.a);
      throw e;

      // .catchall {:try_start_f .. :try_end_12} :catchall_8e
    }

    public long b() {
      if ((File) p0.a.listFiles() != 0) goto :cond_d;
      :cond_d:
      :goto_10:
      if (0x0 >= v2) goto :cond_1c;
      goto :goto_10;
      :cond_1c:
      return (File) p0.a.listFiles();
    }

}