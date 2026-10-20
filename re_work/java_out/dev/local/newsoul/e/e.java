package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/e ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class e extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final AccessibleObject b;
    public final Object c;
    public final AnnotatedElement d;

    public void super(Ljava/lang/reflect/AnnotatedElement;Ljava/lang/reflect/AccessibleObject;Ljava/lang/Object;I p0) {
      super();
      this.a = p4;
      this.d = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public void super(Method p0) {
      super();
      this.a = 0x1;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_13a */ }
      if (((Field) p0.d.get(p1.getThisObject()) instanceof Context) != 0) goto :cond_1a;
      goto :goto_4b;
      :cond_1a:
      if (Ldev/local/newsoul/w2.o((Context) (Field) p0.d.get(p1.getThisObject())).getBoolean("block_splash", 0x1) != 0) goto :cond_35;
      goto :goto_4b;
      :cond_35:
      (XposedModule) p0.c.log(0x4, "SoulHook102", "new soul: splash blocked; MainScheduler.v -> s");
      :goto_4b:
      return "new soul: splash filter OFF; original startup continues";
      :pswitch_4c:
      :try_start_5c:
      if (Ldev/local/newsoul/w2.o((Context) (Field) p0.d.get(p1.getThisObject())).getBoolean("block_splash", 0x1).getInt(p1.proceed().proceed().getArg(0x0)) != 0x4e5310) goto :cond_75;
      :cond_75:
      if (0x0 == 0) goto :cond_9e;
      if (((ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject()).getDrawable() instanceof BitmapDrawable) == 0) goto :cond_9e;
      new Ldev/local/newsoul/v1((BitmapDrawable) (ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject()).getDrawable(), (TextView) "SoulHook102".get(p1.proceed().proceed().getThisObject()).getCurrentTextColor());
      (ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject()).setImageDrawable(new Ldev/local/newsoul/v1((BitmapDrawable) (ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject()).getDrawable(), (TextView) "SoulHook102".get(p1.proceed().proceed().getThisObject()).getCurrentTextColor()));
      goto :goto_9e;
      :catch_9c:
      goto :goto_a2;
      :cond_9e:
      :goto_9e:
      Ldev/local/newsoul/j1.b((ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject()), 0x0);
      :try_end_a1:
      goto :goto_a9;
      :goto_a2:
      :goto_a9:
      return (ImageView) "new soul: splash filter OFF; original startup continues".get(p1.proceed().proceed().getThisObject());
      :pswitch_aa:
      if (e.getArg(0x0) == 0) goto :cond_ff;
      if (Ldev/local/newsoul/Q0.q("chat_hide_typing") == 0) goto :cond_ff;
      if ((Integer) (Method) p0.b.invoke(e.getArg(0x0), 0x0).intValue() != 0x1) goto :cond_ff;
      if ((Method) p0.c.invoke(e.getArg(0x0), 0x0) == 0) goto :cond_ff;
      if ((Integer) (Method) p0.d.invoke((Method) p0.c.invoke(e.getArg(0x0), 0x0), 0x0).intValue() == 0x16) goto :cond_eb;
      if ((Integer) (Method) p0.d.invoke((Method) p0.c.invoke(e.getArg(0x0), 0x0), 0x0).intValue() != 0x17) goto :cond_ff;
      :cond_eb:
      new Ljava/lang/StringBuilder("typing command blocked; type=");
      goto :goto_103;
      :cond_ff:
      :goto_103:
      return "SoulHook102";
      :pswitch_104:
      if ((Class) p0.d.isInstance(new Ljava/lang/StringBuilder("typing command blocked; type=").toString().getThisObject()) == 0) goto :cond_139;
      if (((Method) p0.b.invoke(new Ljava/lang/StringBuilder("typing command blocked; type=").toString().getThisObject(), 0x0) instanceof Context) == 0) goto :cond_139;
      if (Ldev/local/newsoul/j.b((Context) (Method) p0.b.invoke(new Ljava/lang/StringBuilder("typing command blocked; type=").toString().getThisObject(), 0x0), "ad_interstitial") == 0) goto :cond_139;
      Ldev/local/newsoul/j.e("ad dialog dismissed");
      :cond_139:
      return new Ljava/lang/StringBuilder("typing command blocked; type=").toString().proceed();
      :pswitch_data_13a:

      // .packed-switch 0x0
      // :pswitch_104
      // :pswitch_aa
      // :pswitch_4c

      // try :try_start_5c..:try_end_a1 catch (Ljava/lang/Exception) -> :catch_9c
    }

}