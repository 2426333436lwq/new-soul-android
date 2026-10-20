package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/N2 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class N2 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final d3 b;

    public void super(Ldev/local/newsoul/d3;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_46 */ }
      if (p0.b.f == 0) goto :cond_19;
      if (p0.b.f.a != p1.getThisObject()) goto :cond_19;
      p0.b.f.b();
      :cond_19:
      return p0.b.f.a;
      :pswitch_1e:
      new Ljava/lang/ref/WeakReference(p1.getThisObject());
      new long[][p0.b.f.a] = /* array-data :array_4c */;
      :goto_31:
      if (v4 >= p0.b.f.a) goto :cond_44;
      new Ldev/local/newsoul/g(v3, p0.b, 0xe);
      goto :goto_31;
      :cond_44:
      return p0.b.f.a;
      :pswitch_data_46:
      :array_4c:

      // .array-data 8
      // 0x0
      // 0x15e
      // 0x3e8
    }

}