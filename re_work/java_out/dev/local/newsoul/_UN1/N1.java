package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/N1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class N1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Activity b;
    public final boolean c;

    public void super(Landroid/app/Activity;ZI p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_116 */ }
      Ldev/local/newsoul/V.h(p0.b);
      new Ldev/local/newsoul/f0(p0.b, p0.c);
      return;
      :pswitch_19:
      new Ldev/local/newsoul/q2(v0.b, v5);
      new Ldev/local/newsoul/q2(v0.b, v5).t = "选择收款方式后，可用另一台设备扫码，或保存截图后识别。";
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g).setOrientation(v4);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g).setGravity(0x11);
      Ldev/local/newsoul/w2.v(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g), v2, 0x2);
      Ldev/local/newsoul/w2.w(Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "微信"), v4);
      Ldev/local/newsoul/w2.w(Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "支付宝"), v1);
      new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f).rightMargin = Ldev/local/newsoul/Q0.p(new Ldev/local/newsoul/q2(v0.b, v5).g, 0x6);
      new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f).leftMargin = Ldev/local/newsoul/Q0.p(new Ldev/local/newsoul/q2(v0.b, v5).g, 0x6);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g).addView(Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "微信"), new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g).addView(Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "支付宝"), new Landroid/widget/LinearLayout$LayoutParams(v4, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Ldev/local/newsoul/q2(v0.b, v5).h.addView(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2(v0.b, v5).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g);
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setText("");
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setTextSize(((float) 0xd));
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setTextColor(new Ldev/local/newsoul/q2(v0.b, v5).j);
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setVisibility(0x8);
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setAccessibilityLiveRegion(v1);
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setGravity(0x11);
      new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g).setPadding(v4, Ldev/local/newsoul/Q0.p(new Ldev/local/newsoul/q2(v0.b, v5).g, 0xe), v4, Ldev/local/newsoul/Q0.p(new Ldev/local/newsoul/q2(v0.b, v5).g, 0x8));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Ldev/local/newsoul/q2(v0.b, v5).h.addView(new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g);
      new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g).setAdjustViewBounds(v1);
      new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g).setScaleType(ImageView$ScaleType.FIT_CENTER);
      new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g).setVisibility(0x8);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/Q0.p(new Ldev/local/newsoul/q2(v0.b, v5).g, 0xe);
      new Ldev/local/newsoul/q2(v0.b, v5).h.addView(new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      ImageView$ScaleType.FIT_CENTER.<init>(v0.b, new Ldev/local/newsoul/q2(v0.b, v5), new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g), new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g), Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "微信"), Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "支付宝"), 0x0);
      0x0.<init>(v0.b, new Ldev/local/newsoul/q2(v0.b, v5), new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g), new Landroid/widget/TextView(new Ldev/local/newsoul/q2(v0.b, v5).g), Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "支付宝"), Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "微信"), 0x1);
      new Ldev/local/newsoul/i0(ImageView$ScaleType.FIT_CENTER, v4);
      Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "微信").setOnClickListener(new Ldev/local/newsoul/i0(ImageView$ScaleType.FIT_CENTER, v4));
      new Ldev/local/newsoul/i0(0x0, 0x1);
      Ldev/local/newsoul/Q0.g(new Ldev/local/newsoul/q2(v0.b, v5), "支付宝").setOnClickListener(new Ldev/local/newsoul/i0(0x0, 0x1));
      new Ldev/local/newsoul/q2(v0.b, v5).g("支持作者", "返回");
      new Ldev/local/newsoul/j0(v4, new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g));
      new Ldev/local/newsoul/q2(v0.b, v5).e.setOnDismissListener(new Ldev/local/newsoul/j0(v4, new Landroid/widget/ImageView(new Ldev/local/newsoul/q2(v0.b, v5).g)));
      return;
      :pswitch_10e:
      Ldev/local/newsoul/w2.t(v3, v5, v4, v2);
      return;
      :pswitch_112:
      Ldev/local/newsoul/w2.u(v3, v5);
      return;
      :pswitch_data_116:

      // .packed-switch 0x0
      // :pswitch_112
      // :pswitch_10e
      // :pswitch_19
    }

}