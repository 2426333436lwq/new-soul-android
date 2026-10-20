package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/e1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class e1 extends Ljava/lang/Object {

    public final String a;
    public final String b;
    public final int c;
    public final Object d;
    public final long e;
    public boolean f;

    public void super(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Object;J p0) {
      super();
      if (p1 == 0) goto :cond_2c;
      if (p1.isEmpty() != 0) goto :cond_2c;
      if (p2 == 0) goto :cond_2c;
      if (p2.isEmpty() != 0) goto :cond_2c;
      if (p4 == 0) goto :cond_2c;
      if (p3 == 0x1) goto :cond_21;
      if (p3 == 0x2) goto :cond_21;
      if (p3 == 0x3) goto :cond_21;
      if (p3 != 0x5) goto :cond_2c;
      :cond_21:
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
      :cond_2c:
      new Ljava/lang/IllegalArgumentException("Invalid forward");
      throw new Ljava/lang/IllegalArgumentException("Invalid forward");
    }

    public final synchronized ArrayList a(Ljava/lang/String;Ljava/util/ArrayList;J p0) {
      synchronized (p0) {
      :try_start_1:
      if (p0.b(p1, p3, p4) == 0) goto :cond_48;
      if (p2.isEmpty() != 0) goto :cond_48;
      if (p2.size() <= 0x9) goto :cond_17;
      goto :goto_48;
      :cond_17:
      new Ljava/util/LinkedHashSet();
      :cond_20:
      if (p2.hasNext() == 0) goto :cond_3e;
      if (p0.c((String) p2.next(), 0x0) == 0) goto :cond_3c;
      :try_end_37:
      if ((String) p2.next() != 0) goto :cond_20;
      goto :goto_3c;
      :catchall_3a:
      goto :goto_4a;
      :cond_3c:
      :goto_3c:
      }
      return p0.c((String) p2.next(), 0x0);
      :cond_3e:
      :try_start_3f:
      p0.f = p2;
      new Ljava/util/ArrayList(e);
      :try_end_46:
      }
      return p0.c((String) p2.next(), 0x0);
      :cond_48:
      :goto_48:
      }
      return p0.c((String) p2.next(), 0x0);
      :goto_4a:
      }
      throw e;

      // .catchall {:try_start_1 .. :try_end_37} :catchall_3a
      // .catchall {:try_start_3f .. :try_end_46} :catchall_3a
    }

    public final boolean b(Ljava/lang/String;J p0) {
      if (p0.f != 0) goto :cond_1c;
      if (p0.a.equals(p1) == 0) goto :cond_1c;
      if (compare(p2, p0.e) < 0) goto :cond_1c;
      if (compare((p2 - p0.e), 0x927c0) > 0) goto :cond_1c;
      goto :goto_1d;
      :cond_1c:
      :goto_1d:
      return 0x927c0;
    }

    public final boolean c(Ljava/lang/String;I p0) {
      if (p2 != 0) goto :cond_1c;
      if (p1 == 0) goto :cond_1c;
      if (p1.isEmpty() != 0) goto :cond_1c;
      if (p0.a.equals(p1) != 0) goto :cond_1c;
      if (p0.b.equals(p1) != 0) goto :cond_1c;
      goto :goto_1d;
      :cond_1c:
      :goto_1d:
      return v0;
    }

}