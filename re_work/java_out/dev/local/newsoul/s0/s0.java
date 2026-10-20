package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/s0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class s0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final q2 b;

    public void super(Ldev/local/newsoul/q2;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_130 */ }
      new Ldev/local/newsoul/q2(p0.b.a, p0.b.b);
      :try_start_12:
      :try_end_21:
      goto :goto_24;
      :catch_23:
      :goto_24:
      v8.t = "点击蓝奏云打开下载页面，提取码：3gin。下载 ZIP 后解压，使用其中的 APK 覆盖安装。检查更新不会自动下载或安装。".concat("");
      if (v1 != 0) goto :cond_35;
      goto :goto_36;
      :cond_35:
      :goto_36:
      v8.h.addView(Ldev/local/newsoul/w2.n(v8.g, "当前版本：".concat("未知"), 0xf, v8.i));
      Ldev/local/newsoul/w2.n(v8.g, "正在检查…", 0xe, v8.i).setPadding(p1, Ldev/local/newsoul/w2.i(v8.g, 0x8), p1, Ldev/local/newsoul/w2.i(v8.g, 0x8));
      v8.h.addView(Ldev/local/newsoul/w2.n(v8.g, "正在检查…", 0xe, v8.i));
      new Ldev/local/newsoul/g(v5, new String[][] {v0}, 0xd);
      Ldev/local/newsoul/w2.n(v8.g, "蓝奏云", 0xf, v8.k).setMinHeight(Ldev/local/newsoul/w2.i(v8.g, 0x34));
      Ldev/local/newsoul/w2.n(v8.g, "蓝奏云", 0xf, v8.k).setGravity(0x11);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(v8.g, "蓝奏云", 0xf, v8.k));
      new Ldev/local/newsoul/i0(new Ldev/local/newsoul/g(v5, new String[][] {v0}, 0xd), 0x3);
      Ldev/local/newsoul/w2.n(v8.g, "蓝奏云", 0xf, v8.k).setOnClickListener(new Ldev/local/newsoul/i0(new Ldev/local/newsoul/g(v5, new String[][] {v0}, 0xd), 0x3));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      v8.h.addView(Ldev/local/newsoul/w2.n(v8.g, "蓝奏云", 0xf, v8.k), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      v8.g("检查更新", "返回");
      "蓝奏云".<init>(v8.e.getButton(-0x2), new String[][] {v0}, Ldev/local/newsoul/w2.n(v8.g, "正在检查…", 0xe, v8.i), new Thread[][] {v0}, v5, v8, v1);
      new Ldev/local/newsoul/i0("蓝奏云", 0x2);
      v8.e.getButton(-0x2).setOnClickListener(new Ldev/local/newsoul/i0("蓝奏云", 0x2));
      new Ldev/local/newsoul/j0(0x2, new Thread[][] {v0});
      v8.e.setOnDismissListener(new Ldev/local/newsoul/j0(0x2, new Thread[][] {v0}));
      "蓝奏云".run();
      return;
      :pswitch_d1:
      p0.b.f();
      return;
      :pswitch_d7:
      Ldev/local/newsoul/w2.t(p0.b.a, p0.b.b, 0x0, 0x0);
      return;
      :pswitch_e3:
      :try_start_e7:
      :try_end_eb:
      new Ldev/local/newsoul/q2("未知", p1.b);
      new Ldev/local/newsoul/q2("未知", p1.b).t = "点击昵称打开主页，长按记录可移除。插眼按登录账号分别保存在本机。";
      new Ldev/local/newsoul/q2("未知", p1.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2("未知", p1.b).g, "正在加载…", 0xe, new Ldev/local/newsoul/q2("未知", p1.b).j));
      new Ldev/local/newsoul/q2("未知", p1.b).g("插眼列表", "关闭");
      new Ldev/local/newsoul/q2("未知", p1.b).j.<init>(v8.h, "未知", new Ldev/local/newsoul/q2("未知", p1.b), p1.b, new Runnable[][0x1], 0x0);
      new Runnable[][0x1][0x0] = new Ldev/local/newsoul/q2("未知", p1.b).j;
      new Ldev/local/newsoul/q2("未知", p1.b).j.run();
      goto :goto_124;
      :catch_120:
      Ldev/local/newsoul/u1.e("未知", e);
      :goto_124:
      return;
      :pswitch_125:
      Ldev/local/newsoul/z0.a(p0.b.a, p0.b.b);
      return;
      :pswitch_data_130:

      // .packed-switch 0x0
      // :pswitch_125
      // :pswitch_e3
      // :pswitch_d7
      // :pswitch_d1

      // try :try_start_12..:try_end_21 catch (Ljava/lang/Exception) -> :catch_23
      // try :try_start_e7..:try_end_eb catch (Ljava/lang/Exception) -> :catch_120
    }

}