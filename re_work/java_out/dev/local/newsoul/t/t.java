package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/t ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class t extends Ljava/lang/Object {

    public static boolean a;
    public static boolean b;
    public static final ThreadLocal c;
    public static final Set d;
    public static final Handler e;
    public static XposedModule f;
    public static final String[] g;
    public static Constructor h;
    public static Field i;

    static void static {}() {
      new Ljava/lang/ThreadLocal();
      t.c = new Ljava/lang/ThreadLocal();
      new Ljava/util/WeakHashMap();
      t.d = Ljava/util/Collections.newSetFromMap(new Ljava/util/WeakHashMap());
      new Landroid/os/Handler(Ljava/util/Collections.newSetFromMap(new Ljava/util/WeakHashMap()));
      t.e = new Landroid/os/Handler(Ljava/util/Collections.newSetFromMap(new Ljava/util/WeakHashMap()));
      t.g = new String[][] {"布", "剪刀", "石头"};
      return;
    }

    public static boolean a(Ljava/lang/Class;Ljava/lang/reflect/Field;Ljava/lang/String;Z p0) {
      :try_start_3:
      if (p0.getDeclaredMethod(p2, v1).getReturnType() != Void.TYPE) goto :cond_34;
      new Ldev/local/newsoul/p(p1, p3, p0.getDeclaredMethod(p2, v1));
      return t.f.hook(p0.getDeclaredMethod(p2, v1)).setId(v0.concat(p2)).setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE);
      :catchall_32:
      goto :goto_3c;
      :cond_34:
      new Ljava/lang/IllegalStateException("send signature changed");
      throw new Ljava/lang/IllegalStateException("send signature changed");
      :try_end_3c:
      :goto_3c:
      t.f.log(0x6, "SoulHook102", "game hook unavailable: ".concat(p2), e);
      return "SoulHook102";

      // .catchall {:try_start_3 .. :try_end_3c} :catchall_32
    }

    public static void b(Landroid/app/Activity;Ldev/local/newsoul/r;Z p0) {
      if (p0.isFinishing() != 0) goto :cond_3a;
      if (p0.isDestroyed() != 0) goto :cond_3a;
      if (t.d.contains(p0) == 0) goto :cond_15;
      goto :goto_3a;
      :cond_15:
      if (p1 != 0) goto :cond_1c;
      goto :goto_1d;
      :cond_1c:
      :goto_1d:
      :try_start_1d:
      new Ldev/local/newsoul/s(p0, p1, p2);
      new Ldev/local/newsoul/m(p0, 0x1);
      Ldev/local/newsoul/F0.c(p0, p2, 0x1, new Ldev/local/newsoul/s(p0, p1, p2), new Ldev/local/newsoul/m(p0, 0x1));
      :try_end_2b:
      goto :goto_3a;
      :catchall_2c:
      t.f.log(0x6, "SoulHook102", "game picker failed", e);
      :cond_3a:
      :goto_3a:
      return;

      // .catchall {:try_start_1d .. :try_end_2b} :catchall_2c
    }

}