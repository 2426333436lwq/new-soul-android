package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/c ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class c extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final Class a;
    public final int b;
    public final boolean c;
    public final Class d;
    public final Class e;
    public final Method f;
    public final boolean g;

    public void super(Ljava/lang/Class;IZLjava/lang/Class;Ljava/lang/Class;Ljava/lang/reflect/Method;Z p0) {
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
      if (p0.a.isInstance(p1.getThisObject()) == 0) goto :cond_f5;
      if (j.h != 0) goto :cond_41;
      :try_start_1d:
      if ((Ljava/lang/Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", 0x0).invoke(0x0, 0x0) instanceof Context) == 0) goto :cond_41;
      if ((Context) Ljava/lang/Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", 0x0).invoke(0x0, 0x0) != 0) goto :cond_36;
      goto :goto_3f;
      :cond_36:
      if ((Context) Ljava/lang/Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", 0x0).invoke(0x0, 0x0).getApplicationContext() == 0) goto :cond_3d;
      :cond_3d:
      j.h = (Context) Ljava/lang/Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", 0x0).invoke(0x0, 0x0);
      :goto_3f:
      :try_end_41:
      :catchall_41:
      :cond_41:
      if (j.h == 0) goto :cond_f5;
      if (Ldev/local/newsoul/j.b(j.h, "ad_feed") == 0) goto :cond_f5;
      if (p0.c == 0) goto :cond_c2;
      if ((p1.getArg(p0.b) instanceof Collection) == 0) goto :cond_c2;
      new Ljava/util/ArrayList((Collection) p1.getArg(p0.b).size());
      :goto_6b:
      if (p1.getArg(p0.b).hasNext() == 0) goto :cond_8f;
      if (p1.getArg(p0.b).next() != 0) goto :cond_78;
      goto :goto_8b;
      :cond_78:
      :goto_79:
      if (0x0 >= 0x2) goto :cond_8b;
      if ((p1.getArg(p0.b) instanceof Collection)[0x0] == 0) goto :cond_88;
      if ((p1.getArg(p0.b) instanceof Collection)[0x0].isInstance(p1.getArg(p0.b).next()) == 0) goto :cond_88;
      goto :goto_6b;
      :cond_88:
      goto :goto_79;
      :cond_8b:
      :goto_8b:
      goto :goto_6b;
      :cond_8f:
      if ((p0.e + 0x1) != 0) goto :cond_96;
      goto :goto_f9;
      :cond_96:
      p1.getArgs().toArray()[p0.b] = p0.d;
      new Ljava/lang/StringBuilder("feed ad models filtered=");
      Ldev/local/newsoul/j.e(new Ljava/lang/StringBuilder("feed ad models filtered=").toString());
      goto :goto_f9;
      :cond_c2:
      if (p1.getArg(p0.b).next() == 0) goto :cond_f0;
      if (p1.getArgs().toArray() != 0) goto :cond_cb;
      goto :goto_f0;
      :cond_cb:
      :goto_cb:
      if (0x0 >= 0x2) goto :cond_f0;
      if (new Class[][] {" via ", (p0.e + 0x1)}[0x0] == 0) goto :cond_ed;
      if (new Class[][] {" via ", (p0.e + 0x1)}[0x0].isInstance(p1.getArgs().toArray()) == 0) goto :cond_ed;
      new Ljava/lang/StringBuilder("feed ad model skipped via ");
      Ldev/local/newsoul/j.e(new Ljava/lang/StringBuilder("feed ad model skipped via ").toString());
      goto :goto_f9;
      :cond_ed:
      goto :goto_cb;
      :cond_f0:
      :goto_f0:
      goto :goto_f9;
      :cond_f5:
      :goto_f9:
      return new Ljava/lang/StringBuilder("feed ad model skipped via ");

      // .catchall {:try_start_1d .. :try_end_41} :catchall_41
    }

}