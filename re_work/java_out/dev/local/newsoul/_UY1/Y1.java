package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Y1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class Y1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final q2 a;
    public final Activity b;
    public final boolean c;
    public final P0 d;
    public final boolean e;

    public void super(Ldev/local/newsoul/q2;Landroid/app/Activity;ZLdev/local/newsoul/P0;Z p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void run() {
      if (p0.a.e.isShowing() == 0) goto :cond_113;
      if (p0.b.isFinishing() != 0) goto :cond_113;
      if (p0.b.isDestroyed() == 0) goto :cond_1c;
      goto :goto_113;
      :cond_1c:
      p0.a.e.dismiss();
      new Ldev/local/newsoul/q2(p0.b, p0.c);
      if (p0.e == 0) goto :cond_2f;
      goto :goto_31;
      :cond_2f:
      :goto_31:
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.b(), 0x10, new Ldev/local/newsoul/q2(p0.b, p0.c).i).setTextIsSelectable(0x1);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0.b, p0.c), "Soul 更新检测结果", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.b(), 0x10, new Ldev/local/newsoul/q2(p0.b, p0.c).i));
      if (p0.d.a(0x1).isEmpty() != 0) goto :cond_73;
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x1), 0xe, new Ldev/local/newsoul/q2(p0.b, p0.c).i).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.b, 0xc), 0x0, Ldev/local/newsoul/w2.i(p0.b, 0xc));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x1), 0xe, new Ldev/local/newsoul/q2(p0.b, p0.c).i).setTextIsSelectable(0x1);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0.b, p0.c), "Soul 更新检测结果", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x1), 0xe, new Ldev/local/newsoul/q2(p0.b, p0.c).i));
      :cond_73:
      if (p0.d.a(0x0).isEmpty() == 0) goto :cond_84;
      goto :goto_c4;
      :cond_84:
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setMinHeight(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2(p0.b, p0.c).g, 0x30));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setGravity(0x10);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x0), 0xd, new Ldev/local/newsoul/q2(p0.b, p0.c).i).setTextIsSelectable(0x1);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x0), 0xd, new Ldev/local/newsoul/q2(p0.b, p0.c).i).setVisibility(0x8);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setContentDescription("正常检测项，展开");
      new Ldev/local/newsoul/G1(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x0), 0xd, new Ldev/local/newsoul/q2(p0.b, p0.c).i), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setOnClickListener(new Ldev/local/newsoul/G1(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x0), 0xd, new Ldev/local/newsoul/q2(p0.b, p0.c).i), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k)));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0.b, p0.c), "Soul 更新检测结果", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "正常检测项", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0.b, p0.c), "Soul 更新检测结果", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, p0.d.a(0x0), 0xd, new Ldev/local/newsoul/q2(p0.b, p0.c).i), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      :goto_c4:
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "复制检测结果", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setGravity(0x11);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "复制检测结果", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setMinHeight(Ldev/local/newsoul/w2.i(p0.b, 0x30));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "复制检测结果", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k));
      new Ldev/local/newsoul/C0(p0.b, p0.d, 0x2);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "复制检测结果", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k).setOnClickListener(new Ldev/local/newsoul/C0(p0.b, p0.d, 0x2));
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0.b, p0.c), "Soul 更新检测结果", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.b, p0.c).g, "复制检测结果", 0xf, new Ldev/local/newsoul/q2(p0.b, p0.c).k));
      new Ldev/local/newsoul/J1(p0.b, p0.c);
      new Ldev/local/newsoul/q2(p0.b, p0.c).g("功能兼容性检测", "返回");
      p0.b.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0).edit().putString("hook_diagnostics_last_shown_version", p0.d.a).apply();
      :cond_113:
      :goto_113:
      return;
    }

}