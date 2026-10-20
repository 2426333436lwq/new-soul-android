package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/C0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class C0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Object b;
    public final Object c;

    public void super(Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_da */ }
      if ((b3) p0.b.n == 0) goto :cond_15;
      (b3) p0.b.c();
      goto :goto_1f;
      :cond_15:
      :try_start_15:
      (b3) p0.b.j();
      (c3) p0.c.run();
      :try_end_1b:
      goto :goto_1f;
      :catchall_1c:
      (b3) p0.b.b();
      :goto_1f:
      return;
      :pswitch_20:
      :try_start_24:
      (c3) p0.c.run();
      :try_end_27:
      goto :goto_34;
      :catchall_28:
      Ldev/local/newsoul/d3.f((q2) p0.c.a, "操作未完成，请重新打开语音库", e);
      :goto_34:
      return;
      :pswitch_35:
      (Consumer) p0.b.accept((TextView) p0.c);
      return;
      :pswitch_41:
      if ((Switch) p0.b.isChecked() == 0) goto :cond_58;
      if ((Switch) p0.b.isEnabled() == 0) goto :cond_58;
      (Runnable) p0.c.run();
      :cond_58:
      return;
      :pswitch_59:
      if ((ClipboardManager) (Activity) p0.b.getSystemService("clipboard") != 0) goto :cond_68;
      goto :goto_b6;
      :cond_68:
      new Ljava/lang/StringBuilder((P0) p0.c.b());
      if ((P0) p0.c.a(0x1).isEmpty() != 0) goto :cond_88;
      :cond_88:
      if ((P0) p0.c.a(0x0).isEmpty() != 0) goto :cond_9b;
      :cond_9b:
      (ClipboardManager) (Activity) p0.b.getSystemService("clipboard").setPrimaryClip(Landroid/content/ClipData.newPlainText("New Soul 功能兼容性检测", new Ljava/lang/StringBuilder((P0) p0.c.b()).toString()));
      Landroid/widget/Toast.makeText((Activity) p0.b, "检测结果已复制", 0x0).show();
      :goto_b6:
      return;
      :pswitch_b7:
      :try_start_c2:
      (Activity) p0.b.b((ClipboardManager) (Activity) p0.b.getSystemService("clipboard"));
      :try_end_c5:
      goto :goto_ca;
      :catchall_c6:
      Ldev/local/newsoul/d1.c((ClipboardManager) (Activity) p0.b.getSystemService("clipboard"), e);
      :goto_ca:
      return;
      :pswitch_cb:
      (int[]) p0.b[0x0] = 0x0;
      (Runnable) p0.c.run();
      return;
      :pswitch_data_da:

      // .packed-switch 0x0
      // :pswitch_cb
      // :pswitch_b7
      // :pswitch_59
      // :pswitch_41
      // :pswitch_35
      // :pswitch_20

      // .catchall {:try_start_15 .. :try_end_1b} :catchall_1c
      // .catchall {:try_start_24 .. :try_end_27} :catchall_28
      // .catchall {:try_start_c2 .. :try_end_c5} :catchall_c6
    }

}