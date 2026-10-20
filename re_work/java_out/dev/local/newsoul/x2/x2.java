package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/x2 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class x2 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final SoulHook b;

    public void super(Ldev/local/newsoul/SoulHook;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_14 */ }
      return p0.b;
      :pswitch_c:
      return p0.b;
      :pswitch_data_14:

      // .packed-switch 0x0
      // :pswitch_c
    }

}