package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/n0 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class n0 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final boolean a;
    public final Method b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final String f;

    public void super(ZLjava/lang/reflect/Method;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/String; p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      :try_start_15:
      :try_end_1e:
      if (v10 == 0) goto :cond_a5;
      if (v1.a == 0) goto :cond_2c;
      :try_start_25:
      goto :goto_2c;
      :catchall_2a:
      goto :goto_91;
      :cond_2c:
      :goto_2c:
      if ((TextView) v3.get(v2.invoke(v10, v1.a)) == 0) goto :cond_a5;
      Ldev/local/newsoul/r0.a((TextView) v3.get(v2.invoke(v10, v1.a)), v11);
      if (v4.getInt(p1.getThisObject()) != 0) goto :cond_a5;
      if (v12 == 0) goto :cond_a5;
      if ((String) v5.get(v12) == 0) goto :cond_a5;
      if ((TextView) v3.get(v2.invoke(v10, v1.a)).getText().toString().contains((String) v5.get(v12)) == 0) goto :cond_a5;
      if ((String) v5.get(v12).isEmpty() != 0) goto :cond_a5;
      (String) v5.get(v12).isEmpty().<init>(Ldev/local/newsoul/Q0.P((String) v5.get(v12), v11), "profile_disguise_days_", (TextView) v3.get(v2.invoke(v10, v1.a)).getText(), 0x0, (String) v5.get(v12));
      Ldev/local/newsoul/r0.a((TextView) v3.get(v2.invoke(v10, v1.a)), v9);
      Ldev/local/newsoul/p0.i((TextView) v3.get(v2.invoke(v10, v1.a)), (String) v5.get(v12).isEmpty());
      new Ljava/lang/StringBuilder(v7);
      Ldev/local/newsoul/p0.k(new Ljava/lang/StringBuilder(v7).toString());
      :try_end_90:
      goto :goto_a5;
      :goto_91:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/p0.j(new Ljava/lang/StringBuilder().toString(), (String) v5.get(v12));
      :cond_a5:
      :goto_a5:
      return (String) v5.get(v12);

      // .catchall {:try_start_15 .. :try_end_1e} :catchall_2a
      // .catchall {:try_start_25 .. :try_end_90} :catchall_2a
    }

}