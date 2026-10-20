package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/z2 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class z2 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Class b;

    public void super(Ljava/lang/Class;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_8c */ }
      if ((A2) B2.k.get((View) p0.b.getField("itemView").get(p1.getArg(0x0))) == 0) goto :cond_29;
      Ldev/local/newsoul/B2.b((View) p0.b.getField("itemView").get(p1.getArg(0x0)), (A2) B2.k.get((View) p0.b.getField("itemView").get(p1.getArg(0x0))));
      :cond_29:
      return p1.proceed();
      :pswitch_2a:
      Ldev/local/newsoul/B2.e((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0)), (A2) B2.k.remove((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0))));
      return (View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0));
      :pswitch_4d:
      Ldev/local/newsoul/B2.e((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0)), (A2) B2.k.remove((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0))));
      new Ljava/lang/Object();
      new Ljava/lang/Object().a = (View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x1);
      Ldev/local/newsoul/B2.b((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0)), new Ljava/lang/Object());
      new Ldev/local/newsoul/g((View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0)), new Ljava/lang/Object(), 0xc);
      return (View) p0.b.getField("itemView").get((View) p0.b.getField("itemView").get(p1.getArg(0x0)).getArg(0x0));
      :pswitch_data_8c:

      // .packed-switch 0x0
      // :pswitch_4d
      // :pswitch_2a
    }

}