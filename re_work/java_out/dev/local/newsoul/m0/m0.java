package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/m0 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class m0 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final boolean a;
    public final Method b;
    public final String c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public void super(ZLjava/lang/reflect/Method;Ljava/lang/String;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field; p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      this.h = p8;
      this.i = p9;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      :try_start_16:
      :try_end_21:
      if (v12 == 0) goto :cond_b8;
      if (v1.a == 0) goto :cond_30;
      :try_start_28:
      goto :goto_30;
      :catchall_2d:
      goto :goto_a4;
      :cond_30:
      :goto_30:
      if (v4.get(v10) == 0) goto :cond_3d;
      goto :goto_3e;
      :cond_3d:
      :goto_3e:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/p0.k(new Ljava/lang/StringBuilder().toString());
      if (v3.getInt(v10) != 0) goto :cond_65;
      goto :goto_66;
      :cond_65:
      :goto_66:
      (TextView) v5.get(v0.invoke(v12, v1.a)).a(v4.get(v10), 0x1, "profile_disguise_follow_", p0.i, p0.l);
      (TextView) v6.get(v0.invoke(v12, v1.a)).a(v4.get(v10), 0x1, "profile_disguise_followed_", p0.j, p0.l);
      Ldev/local/newsoul/p0.b(v7.get(v0.invoke(v12, v1.a)), v8.get(v0.invoke(v12, v1.a)), v4.get(v10), 0x1, p0.l);
      :try_end_a3:
      goto :goto_b8;
      :goto_a4:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/p0.j(new Ljava/lang/StringBuilder().toString(), v3.getInt(v10));
      :cond_b8:
      :goto_b8:
      return v3.getInt(v10);

      // .catchall {:try_start_16 .. :try_end_21} :catchall_2d
      // .catchall {:try_start_28 .. :try_end_a3} :catchall_2d
    }

}