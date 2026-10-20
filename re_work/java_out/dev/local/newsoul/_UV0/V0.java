package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/V0 ====
// super: Ljava/lang/Object
// implements: InvocationHandle
// source: "SourceFile"

public final  class V0 extends Ljava/lang/Object implements Ljava/lang/reflect/InvocationHandler {

    public final int a;
    public final Activity b;
    public final Object c;

    public void super(Runnable p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.c = p2;
      return;
    }

    public void super(Activity p0) {
      super();
      this.a = 0x1;
      this.c = p1;
      this.b = p2;
      return;
    }

    public final Object invoke(Object p0) {
      switch (p0.a) { /* :pswitch_data_b6 */ }
      if (p2.getDeclaringClass() != Object.class) goto :cond_45;
      if (p2.getName().equals("hashCode") == 0) goto :cond_2c;
      goto :goto_62;
      :cond_2c:
      if (p2.getName().equals("equals") == 0) goto :cond_42;
      if (Ljava/lang/Integer.valueOf(Ljava/lang/System.identityHashCode(p1)) != p3[0x0]) goto :cond_3d;
      :cond_3d:
      goto :goto_62;
      :cond_42:
      goto :goto_62;
      :cond_45:
      if (p3[0x0].getName().equals("onItemClick") == 0) goto :cond_61;
      :try_start_51:
      "hashCode".m("equals", (Integer) p3[0x0].intValue());
      :try_end_5c:
      goto :goto_61;
      :catchall_5d:
      Ldev/local/newsoul/d1.c("equals", e);
      :cond_61:
      :goto_61:
      :goto_62:
      return "equals";
      :pswitch_63:
      if ("onItemClick".getName().equals("accept") == 0) goto :cond_7a;
      p0.b.runOnUiThread((Runnable) p0.c);
      goto :goto_b4;
      :cond_7a:
      if ((Runnable) p0.c.getName().equals("toString") == 0) goto :cond_89;
      goto :goto_b4;
      :cond_89:
      if ((Runnable) p0.c.getName().equals("hashCode") == 0) goto :cond_9e;
      goto :goto_b4;
      :cond_9e:
      if ((Runnable) p0.c.getName().equals("equals") == 0) goto :cond_b4;
      if (Ljava/lang/System.identityHashCode(p0.b) != p3[0x0]) goto :cond_b0;
      :cond_b0:
      :cond_b4:
      :goto_b4:
      return "equals";
      :pswitch_data_b6:

      // .packed-switch 0x0
      // :pswitch_63

      // .catchall {:try_start_51 .. :try_end_5c} :catchall_5d
    }

}