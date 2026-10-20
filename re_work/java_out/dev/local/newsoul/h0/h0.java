package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/h0 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class h0 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;
    public final q2 c;
    public final ImageView d;
    public final TextView e;
    public final TextView f;
    public final TextView g;

    public void super(Landroid/app/Activity;Ldev/local/newsoul/q2;Landroid/widget/ImageView;Landroid/widget/TextView;Landroid/widget/TextView;Landroid/widget/TextView;I p0) {
      super();
      this.a = p7;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      this.f = p5;
      this.g = p6;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_30 */ }
      p0.b.V(p0.c, p0.d, p0.e, p0.f, p0.g, "donation_alipay", "支付宝收款码");
      return;
      :pswitch_1b:
      v0.b.V(v0.c, v0.d, v0.e, v0.f, v0.g, "donation_wechat", "微信收款码");
      return;
      :pswitch_data_30:

      // .packed-switch 0x0
      // :pswitch_1b
    }

}