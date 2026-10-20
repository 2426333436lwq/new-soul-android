package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/X0 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class X0 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Method b;
    public final String c;
    public final XposedModule d;
    public final int e;

    public void super(ILjava/lang/reflect/Method;Ljava/lang/String;Lio/github/libxposed/api/XposedModule;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      if (p0.a < 0) goto :cond_11;
      :try_start_a:
      goto :goto_16;
      :catchall_f:
      goto :goto_5e;
      :cond_11:
      :goto_16:
      if ((Context) e == 0) goto :cond_66;
      :try_end_28:
      if ((Context) e == 0) goto :cond_66;
      p1.getArgs().toArray()[p0.e] = Boolean.FALSE;
      new Ljava/lang/StringBuilder("watermark bypass: ");
      goto :goto_6a;
      :goto_5e:
      p0.d.log(0x5, "SoulHook102", "download preference unavailable", p1.getArgs().toArray());
      :cond_66:
      :goto_6a:
      return p1.getArgs().toArray();

      // .catchall {:try_start_a .. :try_end_28} :catchall_f
    }

}