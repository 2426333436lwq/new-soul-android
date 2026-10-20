package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/g1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class g1 extends Ljava/lang/Object {

    public static boolean a;
    public static final ThreadLocal b;

    static void static {}() {
      new Ljava/lang/ThreadLocal();
      g1.b = new Ljava/lang/ThreadLocal();
      return;
    }

    public static void a(ClassLoader p0) {
      :try_start_2:
      new Ldev/local/newsoul/a(0xf);
      XposedInterface$ExceptionMode.PROTECTIVE.<init>(p1.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", 0x0), p1.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getFrom", 0x0), p1.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("isAnonymityChat", 0x0), p1.loadClass("lf.b").getDeclaredMethod("b", 0x0), 0x0);
      g1.a = 0x1;
      :try_end_80:
      goto :goto_88;
      :catchall_81:
      p0.log(0x6, v0, "own recall time limit unavailable", e);
      :goto_88:
      return;

      // .catchall {:try_start_2 .. :try_end_80} :catchall_81
    }

}