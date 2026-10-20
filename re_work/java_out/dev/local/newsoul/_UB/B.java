package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/B ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class B extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Field b;
    public final Field c;

    public void super(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_c0 */ }
      :try_start_d:
      if (v1.getInt(p1.getArg(0x0)) != -0x35efa) goto :cond_26;
      :cond_26:
      Ldev/local/newsoul/j1.b((View) v0.get(p1.getThisObject()), 0x0);
      :try_end_29:
      goto :goto_32;
      :catchall_2a:
      :goto_32:
      return (View) v0.get(p1.getThisObject());
      :pswitch_33:
      Ldev/local/newsoul/z1.b((View) p0.b.get(v1.getInt(p1.getArg(0x0)).getArg(0x0)));
      :try_start_4a:
      if (Ldev/local/newsoul/Q0.v(v1.getInt(p1.getArg(0x0)).getArg(v4)) == 0) goto :cond_be;
      if (v2.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", -0x35efa).contains(Ldev/local/newsoul/Q0.L(v1.getInt(p1.getArg(0x0)).getArg(v4))) == 0) goto :cond_be;
      if ((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()) == 0) goto :cond_a3;
      :goto_7c:
      if (-0x35efa >= 0x2) goto :cond_a3;
      if (((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()).getClass().getField(v2.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", -0x35efa).contains(Ldev/local/newsoul/Q0.L(v1.getInt(p1.getArg(0x0)).getArg(v4)))[-0x35efa]).get((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject())) instanceof String) == 0) goto :cond_a0;
      if ((String) (View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()).getClass().getField(v2.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", -0x35efa).contains(Ldev/local/newsoul/Q0.L(v1.getInt(p1.getArg(0x0)).getArg(v4)))[-0x35efa]).get((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject())).isEmpty() != 0) goto :cond_a0;
      goto :goto_a3;
      :catchall_9e:
      goto :goto_b7;
      :cond_a0:
      goto :goto_7c;
      :cond_a3:
      :goto_a3:
      Ldev/local/newsoul/z1.a(v2, Ldev/local/newsoul/w2.o(v2.getContext()).getString("chat_recall_style", "below"), (String) (View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()).getClass().getField(v2.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", -0x35efa).contains(Ldev/local/newsoul/Q0.L(v1.getInt(p1.getArg(0x0)).getArg(v4)))[-0x35efa]).get((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject())));
      :try_end_b6:
      goto :goto_be;
      :goto_b7:
      :cond_be:
      :goto_be:
      return (String) (View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()).getClass().getField(v2.getContext().getSharedPreferences("dev.local.soulhookdemo.recalled.v1", -0x35efa).contains(Ldev/local/newsoul/Q0.L(v1.getInt(p1.getArg(0x0)).getArg(v4)))[-0x35efa]).get((View) v0.get(p1.getThisObject()).get(v1.getInt(p1.getArg(0x0)).getThisObject()));
      :pswitch_data_c0:

      // .packed-switch 0x0
      // :pswitch_33

      // .catchall {:try_start_d .. :try_end_29} :catchall_2a
      // .catchall {:try_start_4a .. :try_end_b6} :catchall_9e
    }

}