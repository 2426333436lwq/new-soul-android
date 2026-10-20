package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/m ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class m extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;

    public void super(Landroid/app/Activity;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_3e */ }
      Ldev/local/newsoul/SoulHook.a(p0.b);
      return;
      :pswitch_b:
      Landroid/widget/Toast.makeText(p0.b, "提示预览：New Soul 已生效", 0x0).show();
      return;
      :pswitch_1f:
      Ldev/local/newsoul/t.b(p0.b, 0x0, 0x0);
      return;
      :pswitch_27:
      Ldev/local/newsoul/t.b(p0.b, 0x0, 0x1);
      return;
      :pswitch_2f:
      return;
      :pswitch_37:
      Ldev/local/newsoul/o.a(p0.b);
      return;
      :pswitch_data_3e:

      // .packed-switch 0x0
      // :pswitch_37
      // :pswitch_2f
      // :pswitch_27
      // :pswitch_1f
      // :pswitch_b
    }

}