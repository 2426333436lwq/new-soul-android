package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/A0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class A0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_78 */ }
      if ((b3) p0.b.n == 0) goto :cond_11;
      (b3) p0.b.c();
      goto :goto_18;
      :cond_11:
      :try_start_11:
      (b3) p0.b.f();
      :try_end_14:
      goto :goto_18;
      :catchall_15:
      (b3) p0.b.b();
      :goto_18:
      return;
      :pswitch_19:
      if ((I2) p0.b.b() == 0) goto :cond_30;
      Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0).show();
      goto :goto_3c;
      :cond_30:
      if (Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0).m == 0) goto :cond_39;
      Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0).e(0x1);
      goto :goto_3c;
      :cond_39:
      Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0).l(0x0);
      :goto_3c:
      return;
      :pswitch_3d:
      return;
      :pswitch_45:
      :try_start_49:
      new Landroid/content/Intent("android.intent.action.VIEW", Landroid/net/Uri.parse("https://t.me/+3Hl2lJ1X-oZlZTZl"));
      Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0).startActivity(new Landroid/content/Intent("android.intent.action.VIEW", Landroid/net/Uri.parse("https://t.me/+3Hl2lJ1X-oZlZTZl")));
      :try_end_59:
      goto :goto_64;
      :catch_5a:
      Landroid/widget/Toast.makeText(Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启，长按爱心可关闭", 0x0), "没有可打开链接的应用", 0x0).show();
      :goto_64:
      return;
      :pswitch_65:
      (Runnable[]) p0.b[0x0].run();
      return;
      :pswitch_70:
      (Dialog) p0.b.dismiss();
      return;
      :pswitch_data_78:

      // .packed-switch 0x0
      // :pswitch_70
      // :pswitch_65
      // :pswitch_45
      // :pswitch_3d
      // :pswitch_19

      // .catchall {:try_start_11 .. :try_end_14} :catchall_15
      // try :try_start_49..:try_end_59 catch (Landroid/content/ActivityNotFoundException) -> :catch_5a
    }

}