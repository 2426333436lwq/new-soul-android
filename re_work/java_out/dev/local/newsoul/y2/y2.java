package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/y2 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class y2 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final XposedModule b;

    public void super(Lio/github/libxposed/api/XposedModule;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_38 */ }
      Ldev/local/newsoul/Q0.n(p0.b, (Activity) p1.getThisObject(), (Intent) p1.getArg(0x0), "onNewIntent");
      return (Activity) p1.getThisObject();
      :pswitch_1e:
      Ldev/local/newsoul/Q0.f((Activity) p1.getThisObject());
      Ldev/local/newsoul/Q0.n(p0.b, (Activity) p1.getThisObject(), (Activity) p1.getThisObject().getIntent(), "onCreate");
      return (Activity) p1.getThisObject();
      :pswitch_data_38:

      // .packed-switch 0x0
      // :pswitch_1e
    }

}