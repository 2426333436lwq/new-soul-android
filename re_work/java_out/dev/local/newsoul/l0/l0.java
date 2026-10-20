package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/l0 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class l0 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final Field a;
    public final Field b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;

    public void super(Field p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      :try_start_18:
      new Ljava/lang/StringBuilder(v9);
      Ldev/local/newsoul/p0.k(new Ljava/lang/StringBuilder(v9).toString());
      if (v2.getInt(p1.getThisObject()) != 0) goto :cond_38;
      goto :goto_39;
      :cond_38:
      :goto_39:
      (TextView) v3.get(p1.getThisObject()).a(v0.getArg(0x0), 0x1, "profile_disguise_follow_", p0.i, p0.m);
      (TextView) v4.get(p1.getThisObject()).a(v0.getArg(0x0), 0x1, "profile_disguise_followed_", p0.j, p0.m);
      Ldev/local/newsoul/p0.b(v5.get(p1.getThisObject()), v6.get(p1.getThisObject()), v0.getArg(0x0), 0x1, p0.m);
      Ldev/local/newsoul/p0.b(v7.get(p1.getThisObject()), v8.get(p1.getThisObject()), v0.getArg(0x0), 0x1, p0.m);
      :try_end_87:
      goto :goto_8e;
      :catchall_88:
      Ldev/local/newsoul/p0.j("header bind", e);
      :goto_8e:
      return v0;

      // .catchall {:try_start_18 .. :try_end_87} :catchall_88
    }

}