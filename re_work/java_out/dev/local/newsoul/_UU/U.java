package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/U ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class U extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final boolean b;
    public final Object c;

    public void super(Landroid/app/Activity;Z p0) {
      super();
      this.a = 0x1;
      this.c = p1;
      this.b = p2;
      return;
    }

    public void super(ZLdev/local/newsoul/d0; p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_15c */ }
      if ((Activity) p0.c.isFinishing() != 0) goto :cond_d6;
      if ((Activity) p0.c.isDestroyed() == 0) goto :cond_17;
      goto :goto_d6;
      :cond_17:
      if (((Activity) p0.c.getWindow().getDecorView() instanceof ViewGroup) != 0) goto :cond_25;
      goto :goto_d6;
      :cond_25:
      if ((Activity) p0.c.getWindow().getDecorView().findViewWithTag("new.soul.privacy.photo.download") == 0) goto :cond_40;
      (Activity) p0.c.getWindow().getDecorView().findViewWithTag("new.soul.privacy.photo.download").bringToFront();
      (Activity) p0.c.getWindow().getDecorView().findViewWithTag("new.soul.privacy.photo.download").setElevation(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)));
      (Activity) p0.c.getWindow().getDecorView().findViewWithTag("new.soul.privacy.photo.download").setVisibility(0x0);
      goto :goto_d6;
      :cond_40:
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)));
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setTag("new.soul.privacy.photo.download");
      if (p0.b == 0) goto :cond_52;
      goto :goto_53;
      :cond_52:
      :goto_53:
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setContentDescription("保存原图");
      if (p0.b == 0) goto :cond_59;
      :cond_59:
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setText("保存视频");
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setTextColor(-0x1);
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setTextSize(0x41600000    # 14.0f);
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setGravity(0x11);
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setPadding(Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x10), 0x0, Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x10), 0x0);
      new Ldev/local/newsoul/L2(0x1, ((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)).getResources().getDisplayMetrics().density);
      new Ldev/local/newsoul/L2(0x1, ((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)).getResources().getDisplayMetrics().density).b(new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))), 0xb, 0x41b00000    # 22.0f);
      new Landroid/widget/FrameLayout$LayoutParams(-0x2, Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x2c), 0x800055);
      new Landroid/widget/FrameLayout$LayoutParams(-0x2, Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x2c), 0x800055).rightMargin = Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x14);
      new Landroid/widget/FrameLayout$LayoutParams(-0x2, Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x2c), 0x800055).bottomMargin = Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x4c);
      ((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)).addContentView(new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))), new Landroid/widget/FrameLayout$LayoutParams(-0x2, Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x2c), 0x800055));
      Ldev/local/newsoul/j1.b(new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))), 0x1);
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setElevation(((float) Ldev/local/newsoul/Q0.o(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), 0x20)));
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).bringToFront();
      new Ldev/local/newsoul/l1(p0.b, ((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))));
      new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20))).setOnClickListener(new Ldev/local/newsoul/l1(p0.b, ((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)), new Landroid/widget/TextView(((float) Ldev/local/newsoul/Q0.o((Activity) p0.c, 0x20)))));
      if (p0.b == 0) goto :cond_cb;
      goto :goto_cd;
      :cond_cb:
      :goto_cd:
      Ldev/local/newsoul/Q0.w("privacy save action attached: ".concat("photo"));
      :cond_d6:
      :goto_d6:
      return;
      :pswitch_d7:
      if (p0.b == 0) goto :cond_147;
      :try_start_df:
      if (V.k != 0) goto :cond_fb;
      new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug");
      new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      V.k = new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      :cond_fb:
      if (compare(V.k.b(), 0x3fe000) < 0) goto :cond_10e;
      Ldev/local/newsoul/V.l("记录已满，请先导出并清除");
      goto :goto_155;
      :cond_10e:
      V.f = (V.k.b() + 0x1b7740);
      V.h = "正在记录";
      if (V.j.edit().putLong("until", V.f, 0x1b7740).putString("status", V.h).commit() == 0) goto :cond_141;
      Ldev/local/newsoul/V.k("session_start");
      goto :goto_155;
      :cond_141:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :cond_147:
      Ldev/local/newsoul/V.l("已停止记录");
      :try_end_14f:
      goto :goto_155;
      :catchall_150:
      Ldev/local/newsoul/V.l("记录失败，已停止");
      :goto_155:
      return;
      :pswitch_data_15c:

      // .packed-switch 0x0
      // :pswitch_d7

      // .catchall {:try_start_df .. :try_end_14f} :catchall_150
    }

}