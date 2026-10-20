package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/J ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class J extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Activity b;
    public final P c;

    public void super(Landroid/app/Activity;Ldev/local/newsoul/P;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_20 */ }
      if (p0.c.c == 0) goto :cond_e;
      goto :goto_10;
      :cond_e:
      :goto_10:
      Ldev/local/newsoul/S.b(p0.b, p0.c, "导出失败，请重新选择保存位置");
      return;
      :pswitch_16:
      Ldev/local/newsoul/S.b(p0.b, p0.c, "配置已导出");
      return;
      :pswitch_data_20:

      // .packed-switch 0x0
      // :pswitch_16
    }

}