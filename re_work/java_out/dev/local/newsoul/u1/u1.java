package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/u1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class u1 extends Ljava/lang/Object {

    public static final Handler a;
    public static final ExecutorService b;
    public static final WeakHashMap c;
    public static Method d;
    public static Method e;
    public static boolean f;

    static void static {}() {
      new Landroid/os/Handler(__result__);
      u1.a = new Landroid/os/Handler(__result__);
      u1.b = __result__;
      new Ljava/util/WeakHashMap();
      u1.c = new Ljava/util/WeakHashMap();
      return;
    }

    public static TextView a(String p0) {
      Ldev/local/newsoul/w2.n(p0.g, p1, 0x10, p0.i).setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0x10, p0.i).setGravity(0x10);
      Ldev/local/newsoul/w2.n(p0.g, p1, 0x10, p0.i).setPadding(Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0xa), Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0xa));
      p0.c.b(Ldev/local/newsoul/w2.n(p0.g, p1, 0x10, p0.i), 0xc, Ldev/local/newsoul/L2.e(0xc));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p0.g, p1, 0x10, p0.i));
      return Ldev/local/newsoul/L2.e(0xc);
    }

    public static boolean b(Activity p0) {
      if (p0 == 0) goto :cond_10;
      if (p0.isFinishing() != 0) goto :cond_10;
      if (p0.isDestroyed() != 0) goto :cond_10;
      goto :goto_11;
      :cond_10:
      :goto_11:
      return p0.isFinishing();
    }

    public static Object c(String p0) {
      return p0.getClass();
    }

    public static String d() {
      if (u1.d != 0) goto :cond_6;
      goto :goto_d;
      :cond_6:
      :goto_d:
      if (0x0 == 0) goto :cond_1a;
      if (0x0.trim().isEmpty() != 0) goto :cond_1a;
      return 0x0.trim().isEmpty();
      :cond_1a:
      new Ljava/lang/IllegalStateException("请先登录 Soul");
      throw new Ljava/lang/IllegalStateException("请先登录 Soul");
    }

    public static void e(Exception p0) {
      if ((p1 instanceof IllegalStateException) != 0) goto :cond_13;
      if ((p1 instanceof IllegalArgumentException) == 0) goto :cond_10;
      goto :goto_13;
      :cond_10:
      goto :goto_17;
      :cond_13:
      :goto_13:
      :goto_17:
      if (Ldev/local/newsoul/u1.b(p0) == 0) goto :cond_25;
      Landroid/widget/Toast.makeText(p0, "操作失败，原有记录已保留", 0x0).show();
      :cond_25:
      return;
    }

    public static Field f(String p0) {
      :goto_0:
      if (p0 == 0) goto :cond_10;
      :try_start_2:
      p0.getDeclaredField(p1).setAccessible(0x1);
      :try_end_a:
      return v0;
      :catch_b:
      goto :goto_0;
      :cond_10:
      new Ljava/lang/NoSuchFieldException(p1);
      throw new Ljava/lang/NoSuchFieldException(p1);

      // try :try_start_2..:try_end_a catch (Ljava/lang/NoSuchFieldException) -> :catch_b
    }

    public static void g(ClassLoader p0) {
      :try_start_12:
      u1.d = v1.loadClass("cn.soulapp.imlib.j0").getMethod("g", 0x0);
      u1.e = v1.loadClass("cn.soulapp.android.component.home.user.UserHomeActivity").getDeclaredMethod(v5, new Class[][] {v7, v7});
      new Ldev/local/newsoul/e(Ldev/local/newsoul/u1.f(v1.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a"), "c"), Ldev/local/newsoul/u1.f(v1.loadClass(v1.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v8)), v6), Ldev/local/newsoul/u1.f(v1.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a"), v6), 0x2);
      :goto_d6:
      if (v10 >= v8) goto :cond_e2;
      goto :goto_d6;
      :catchall_e0:
      goto :goto_13b;
      :cond_e2:
      new Ldev/local/newsoul/k1(v7, 0x1);
      XposedInterface$ExceptionMode.PROTECTIVE.<init>(v14, v15, v16, v17, v7, v19, v20, v21, v22);
      :goto_125:
      if (v9 >= v4) goto :cond_12f;
      goto :goto_125;
      :cond_12f:
      u1.f = 0x1;
      Ldev/local/newsoul/V.i(v3, v11);
      :try_end_13a:
      goto :goto_143;
      :goto_13b:
      Ldev/local/newsoul/V.i(v3, e);
      :goto_143:
      return;

      // .catchall {:try_start_12 .. :try_end_13a} :catchall_e0
    }

    public static void h(String p0) {
      if (p0.equals(__result__) == 0) goto :cond_b;
      return;
      :cond_b:
      new Ljava/lang/IllegalStateException("账号已切换，请重新打开页面");
      throw new Ljava/lang/IllegalStateException("账号已切换，请重新打开页面");
    }

    public static boolean i(q2 p0) {
      if (Ldev/local/newsoul/u1.b(p0.a) == 0) goto :cond_14;
      if (p0.e == 0) goto :cond_14;
      if (p0.e.isShowing() == 0) goto :cond_14;
      goto :goto_15;
      :cond_14:
      :goto_15:
      return Ldev/local/newsoul/u1.b(p0.a);
    }

}