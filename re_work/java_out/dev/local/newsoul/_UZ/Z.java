package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Z ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class Z extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;

    public void super(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p5;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_14e */ }
      if ((Switch) p0.b.isEnabled() == 0) goto :cond_19;
      if ((Switch) p0.b.isChecked() == 0) goto :cond_19;
      goto :goto_1a;
      :cond_19:
      :goto_1a:
      if ((Switch) p0.c.isEnabled() == 0) goto :cond_2c;
      if ((Switch) p0.c.isChecked() == 0) goto :cond_2c;
      goto :goto_2d;
      :cond_2c:
      :goto_2d:
      if ((Switch) p0.d.isEnabled() == 0) goto :cond_3e;
      if ((Switch) p0.d.isChecked() == 0) goto :cond_3e;
      :cond_3e:
      if (0x1 == 0) goto :cond_43;
      goto :goto_5f;
      :cond_43:
      if (0x1 == 0) goto :cond_4d;
      if (0x0 == 0) goto :cond_4a;
      goto :goto_5f;
      :cond_4a:
      goto :goto_5f;
      :cond_4d:
      if ("一键点赞可用 · 范围：屏幕内帖子。".isChecked() != 0) goto :cond_5d;
      if ((Switch) p0.c.isChecked() == 0) goto :cond_5a;
      goto :goto_5d;
      :cond_5a:
      goto :goto_5f;
      :cond_5d:
      :goto_5d:
      :goto_5f:
      Ldev/local/newsoul/w2.s((TextView) p0.e, "一键点赞和自动点赞均已关闭。");
      return;
      :pswitch_67:
      :goto_69:
      if (0x1 >= (ArrayList) p0.b.size()) goto :cond_ae;
      if ((Integer) (ArrayList) p0.c.get(0x1).intValue() != (int[]) p0.d["一键点赞和自动点赞均已关闭。"]) goto :cond_91;
      goto :goto_92;
      :cond_91:
      :goto_92:
      (View) (ArrayList) p0.b.get(0x1).setSelected(0x1);
      if (((View) (ArrayList) p0.b.get(0x1) instanceof FrameLayout) == 0) goto :cond_ab;
      if ((FrameLayout) (View) (ArrayList) p0.b.get(0x1).findViewWithTag("tick") == 0) goto :cond_ab;
      if (0x1 == 0) goto :cond_a7;
      goto :goto_a8;
      :cond_a7:
      :goto_a8:
      (FrameLayout) (View) (ArrayList) p0.b.get(0x1).findViewWithTag("tick").setVisibility("一键点赞和自动点赞均已关闭。");
      :cond_ab:
      goto :goto_69;
      :cond_ae:
      if ((int[]) p0.d["一键点赞和自动点赞均已关闭。"]["一键点赞和自动点赞均已关闭。"] < 0) goto :cond_b3;
      goto :goto_b4;
      :cond_b3:
      :goto_b4:
      (TextView) p0.e.setEnabled(0x1);
      if ((int[]) p0.d["一键点赞和自动点赞均已关闭。"]["一键点赞和自动点赞均已关闭。"] < 0) goto :cond_c2;
      goto :goto_c5;
      :cond_c2:
      :goto_c5:
      (TextView) p0.e.setAlpha(0x3f800000    # 1.0f);
      return;
      :pswitch_c9:
      if ((Activity) p0.b.isDestroyed() != 0) goto :cond_147;
      if ((Activity) p0.b.isFinishing() != 0) goto :cond_147;
      if ((f0) p0.c.a.e.isShowing() != 0) goto :cond_f2;
      goto :goto_147;
      :cond_f2:
      :try_start_f2:
      Ldev/local/newsoul/g0.a((Activity) p0.b);
      (a0) p0.d.c = (byte[]) p0.e;
      (a0) p0.d.d = 0x1;
      new Landroid/content/Intent("android.intent.action.CREATE_DOCUMENT");
      new Ljava/text/SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT);
      new Ljava/util/Date();
      new Ljava/lang/StringBuilder("NewSoul-debug-");
      (Activity) p0.b.startActivityForResult(new Landroid/content/Intent("android.intent.action.CREATE_DOCUMENT").addCategory("android.intent.category.OPENABLE").setType("application/octet-stream").putExtra("android.intent.extra.TITLE", new Ljava/lang/StringBuilder("NewSoul-debug-").toString()), (a0) p0.d.a);
      (f0) p0.c.a("请选择保存位置");
      :try_end_140:
      goto :goto_14c;
      :catch_141:
      Ldev/local/newsoul/g0.b((Activity) p0.b, (a0) p0.d, "无法打开保存位置，请重试");
      goto :goto_14c;
      :cond_147:
      :goto_147:
      Ldev/local/newsoul/g0.b((Activity) p0.b, (a0) p0.d, "已取消导出");
      :goto_14c:
      return;
      :pswitch_data_14e:

      // .packed-switch 0x0
      // :pswitch_c9
      // :pswitch_67

      // try :try_start_f2..:try_end_140 catch (Ljava/lang/Exception) -> :catch_141
    }

}