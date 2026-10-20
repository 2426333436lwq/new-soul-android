package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/b1 ====
// super: Ljava/lang/Object
// implements: InvocationHandle
// source: "SourceFile"

public final  class b1 extends Ljava/lang/Object implements Ljava/lang/reflect/InvocationHandler {

    public final d1 a;
    public final Activity b;
    public final String c;
    public final e1 d;
    public final LinkedHashMap e;
    public final List f;
    public final Dialog g;

    public void super(Dialog p0) {
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

    public final Object invoke(Object p0) {
      if (p2.getDeclaringClass() != Object.class) goto :cond_49;
      if (p2.getName().equals("hashCode") == 0) goto :cond_2e;
      goto :goto_180;
      :cond_2e:
      if (p2.getName().equals("equals") == 0) goto :cond_45;
      if (Ljava/lang/Integer.valueOf(Ljava/lang/System.identityHashCode(p1)) != p3[0x0]) goto :cond_3f;
      :cond_3f:
      goto :goto_180;
      :cond_45:
      goto :goto_180;
      :cond_49:
      if (p3[0x0].getName().equals("onClickConfirm") == 0) goto :cond_17f;
      :try_start_56:
      if ("equals".isFinishing() != 0) goto :cond_10f;
      if ("equals".isDestroyed() != 0) goto :cond_10f;
      if ("hashCode".equals(p0.a.r) == 0) goto :cond_10f;
      :try_end_6e:
      if ("hashCode".equals(p0.a.r) != p0.d) goto :cond_10f;
      :try_start_72:
      if (Ldev/local/newsoul/d1.h("equals", p0.d).keySet().equals(p0.e.keySet()) == 0) goto :cond_10f;
      if (p0.d.a((String) p0.a.g.invoke(0x0, 0x0), (ArrayList) p0.f, p0.a.g.invoke(0x0, 0x0), p0.e) == 0) goto :cond_107;
      :goto_9d:
      if (p0.d.a((String) p0.a.g.invoke(0x0, 0x0), (ArrayList) p0.f, p0.a.g.invoke(0x0, 0x0), p0.e).hasNext() == 0) goto :cond_c7;
      if (p0.d.a.equals(p0.a.g.invoke(0x0, 0x0)) == 0) goto :cond_bf;
      p0.a.k(p0.d, (String) p0.d.a((String) p0.a.g.invoke(0x0, 0x0), (ArrayList) p0.f, p0.a.g.invoke(0x0, 0x0), p0.e).next());
      goto :goto_9d;
      :catchall_bd:
      goto :goto_117;
      :cond_bf:
      new Ljava/lang/IllegalStateException("Account changed");
      throw new Ljava/lang/IllegalStateException("Account changed");
      :cond_c7:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.d("message.forward.dispatched", new Ljava/lang/StringBuilder().toString());
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/d1.l("equals", new Ljava/lang/StringBuilder().toString());
      :try_end_100:
      :goto_100:
      p0.g.dismiss();
      "equals".finish();
      goto :goto_17f;
      :cond_107:
      :try_start_107:
      new Ljava/lang/IllegalStateException("Expired confirmation");
      throw new Ljava/lang/IllegalStateException("Expired confirmation");
      :cond_10f:
      new Ljava/lang/IllegalStateException("Selection changed");
      throw new Ljava/lang/IllegalStateException("Selection changed");
      :try_end_117:
      :goto_117:
      :try_start_117:
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.d("message.forward.failed", new Ljava/lang/StringBuilder().toString());
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/d1.l("equals", new Ljava/lang/StringBuilder().toString());
      :try_end_176:
      goto :goto_100;
      :catchall_177:
      p0.g.dismiss();
      "equals".finish();
      throw e;
      :cond_17f:
      :goto_17f:
      :goto_180:
      return "equals";

      // .catchall {:try_start_56 .. :try_end_6e} :catchall_bd
      // .catchall {:try_start_72 .. :try_end_100} :catchall_bd
      // .catchall {:try_start_107 .. :try_end_117} :catchall_bd
      // .catchall {:try_start_117 .. :try_end_176} :catchall_177
    }

}