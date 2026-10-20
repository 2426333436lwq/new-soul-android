package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/A ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class A extends Ljava/lang/Object {

    public static final AtomicLong a;
    public static final ThreadLocal b;
    public static final ArrayDeque c;

    static void static {}() {
      new Ljava/util/concurrent/atomic/AtomicLong();
      A.a = new Ljava/util/concurrent/atomic/AtomicLong();
      new Ljava/lang/ThreadLocal();
      A.b = new Ljava/lang/ThreadLocal();
      new Ljava/util/ArrayDeque();
      A.c = new Ljava/util/ArrayDeque();
      return;
    }

    public static y a(ZLjava/lang/Object; p0) {
      if (p0 == 0) goto :cond_c;
      goto :goto_d;
      :cond_c:
      :goto_d:
      if (p0 != 0) goto :cond_3d;
      if (0x0 != 0) goto :cond_3d;
      if (p1 == 0) goto :cond_3d;
      synchronized (A.c) {
      :try_start_16:
      :cond_1a:
      :goto_1a:
      if (v2.hasNext() == 0) goto :cond_39;
      if ((x) v2.next().a.get() != 0) goto :cond_34;
      v2.remove();
      goto :goto_1a;
      :catchall_32:
      goto :goto_3b;
      :cond_34:
      if ((x) v2.next().a.get() != e) goto :cond_1a;
      goto :goto_1a;
      :cond_39:
      }
      goto :goto_3d;
      :goto_3b:
      }
      :try_end_3c:
      throw e;
      :cond_3d:
      :goto_3d:
      if ((x) v2.next().b != 0) goto :cond_44;
      new Ldev/local/newsoul/z();
      :cond_44:
      A.b.set((x) v2.next().b);
      new Ldev/local/newsoul/y((x) v2.next().b, (z) A.b.get());
      return (z) A.b.get();

      // .catchall {:try_start_16 .. :try_end_3c} :catchall_32
    }

    public static void b(Object p0) {
      if ((z) A.b.get() == 0) goto :cond_35;
      if (p0 != 0) goto :cond_d;
      goto :goto_35;
      :cond_d:
      synchronized (A.c) {
      :try_start_10:
      new Ldev/local/newsoul/w(0x0, p0);
      :goto_19:
      if (A.c.size() < 0x40) goto :cond_29;
      goto :goto_19;
      :catchall_27:
      goto :goto_33;
      :cond_29:
      new Ldev/local/newsoul/x(e, (z) A.b.get());
      A.c.addLast(new Ldev/local/newsoul/x(e, (z) A.b.get()));
      }
      return;
      :goto_33:
      }
      :try_end_34:
      throw e;
      :cond_35:
      :goto_35:
      return;

      // .catchall {:try_start_10 .. :try_end_34} :catchall_27
    }

}