package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/g0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class g0 extends Ljava/lang/Object {

    public static XposedModule a;
    public static final Handler b;
    public static final HashSet c;
    public static final WeakHashMap d;
    public static final SecureRandom e;

    static void static {}() {
      new Landroid/os/Handler(__result__);
      g0.b = new Landroid/os/Handler(__result__);
      new Ljava/util/HashSet();
      g0.c = new Ljava/util/HashSet();
      new Ljava/util/WeakHashMap();
      g0.d = new Ljava/util/WeakHashMap();
      new Ljava/security/SecureRandom();
      g0.e = new Ljava/security/SecureRandom();
      return;
    }

    public static void a(Activity p0) {
      if (g0.a == 0) goto :cond_50;
      :goto_8:
      if (p0 == 0) goto :cond_1e;
      :try_start_a:
      :try_end_18:
      goto :goto_1f;
      :catch_19:
      goto :goto_8;
      :cond_1e:
      :goto_1f:
      if (p0.getSuperclass() == 0) goto :cond_4a;
      if (g0.c.contains(p0.getSuperclass()) == 0) goto :cond_2a;
      return;
      :cond_2a:
      new Ldev/local/newsoul/a(0xe);
      return;
      :cond_4a:
      new Ljava/lang/NoSuchMethodException();
      throw new Ljava/lang/NoSuchMethodException();
      :cond_50:
      new Ljava/lang/IllegalStateException();
      throw new Ljava/lang/IllegalStateException();

      // try :try_start_a..:try_end_18 catch (Ljava/lang/NoSuchMethodException) -> :catch_19
    }

    public static void b(String p0) {
      if (g0.d.get(p0) == p1) goto :cond_9;
      return;
      :cond_9:
      p1.c = 0x0;
      if ((f0) p1.b.get() == 0) goto :cond_22;
      if (p0.isDestroyed() != 0) goto :cond_22;
      (f0) p1.b.get().a(p2);
      :cond_22:
      return;
    }

}