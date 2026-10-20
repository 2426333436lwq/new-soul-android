package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/v ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class v extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final boolean b;
    public final String c;
    public final int d;

    public void super(IZLjava/lang/String;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      if (__result__ != 0) goto :cond_12;
      goto :goto_98;
      :cond_12:
      if (p0.a >= 0) goto :cond_18;
      goto :goto_1c;
      :cond_18:
      :goto_1c:
      if (p0.b != 0) goto :cond_33;
      :try_start_26:
      Ldev/local/newsoul/A.a(p0.b, 0x0).a.c = (Ldev/local/newsoul/A.a(p0.b, 0x0).a.c + 0x1);
      Ldev/local/newsoul/A.b(0x0);
      goto :goto_33;
      :catchall_30:
      goto :goto_c7;
      :cond_33:
      :goto_33:
      new Ljava/lang/StringBuilder();
      if ((0x0 instanceof List) == 0) goto :cond_57;
      goto :goto_58;
      :cond_57:
      :goto_58:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.r(new Ljava/lang/StringBuilder().toString(), new Ljava/lang/StringBuilder().toString());
      :try_end_7b:
      :try_start_7b:
      :try_end_7f:
      :try_start_7f:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.r(new Ljava/lang/StringBuilder().toString(), Ldev/local/newsoul/A.a(p0.b, 0x0).a.a());
      :try_end_95:
      Ldev/local/newsoul/A.a(p0.b, 0x0).close();
      :goto_98:
      return p0.c;
      :catchall_99:
      :try_start_9a:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.s(new Ljava/lang/StringBuilder().toString(), e);
      throw e;
      :try_end_af:
      :catchall_af:
      :try_start_b0:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.r(new Ljava/lang/StringBuilder().toString(), Ldev/local/newsoul/A.a(p0.b, 0x0).a.a());
      throw e;
      :try_end_c7:
      :goto_c7:
      :try_start_c7:
      Ldev/local/newsoul/A.a(p0.b, 0x0).close();
      :try_end_ca:
      goto :goto_cf;
      :catchall_cb:
      e.addSuppressed(e);
      :goto_cf:
      throw e;

      // .catchall {:try_start_26 .. :try_end_7b} :catchall_30
      // .catchall {:try_start_7b .. :try_end_7f} :catchall_99
      // .catchall {:try_start_7f .. :try_end_95} :catchall_30
      // .catchall {:try_start_9a .. :try_end_af} :catchall_af
      // .catchall {:try_start_b0 .. :try_end_c7} :catchall_30
      // .catchall {:try_start_c7 .. :try_end_ca} :catchall_cb
    }

}