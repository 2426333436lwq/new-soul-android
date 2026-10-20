package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/n ====
// super: Ljava/lang/Object
// implements: View$OnLongClickListene
// source: "SourceFile"

public final  class n extends Ljava/lang/Object implements Landroid/view/View$OnLongClickListener {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final boolean onLongClick(View p0) {
      switch (p0.a) { /* :pswitch_data_92 */ }
      if (Q0.B != 0) goto :cond_1b;
      Landroid/widget/Toast.makeText((I2) p0.b.a, "当前版本自动点赞未就绪", 0x0).show();
      goto :goto_6e;
      :cond_1b:
      Ldev/local/newsoul/w2.o((I2) p0.b.a).edit().putBoolean("square_auto_like", ((I2) p0.b.b() ^ 0x1)).apply();
      (I2) p0.b.e(0x0);
      (I2) p0.b.c.a((I2) p0.b.k().size(), 0x0);
      if ((I2) p0.b.l == 0) goto :cond_4d;
      :cond_4d:
      if (((I2) p0.b.b() ^ 0x1) == 0) goto :cond_52;
      goto :goto_54;
      :cond_52:
      :goto_54:
      Landroid/widget/Toast.makeText((I2) p0.b.a, "自动点赞已开启", 0x0).show();
      new Ljava/lang/StringBuilder("square auto like toggled by long press: enabled=");
      :goto_6e:
      return "自动点赞已开启";
      :pswitch_6f:
      if ((Activity) p0.b.isFinishing() != 0) goto :cond_8f;
      if ((Activity) p0.b.isDestroyed() != 0) goto :cond_8f;
      if (((I2) p0.b.b() ^ 0x1).isShown() != 0) goto :cond_86;
      goto :goto_8f;
      :cond_86:
      Ldev/local/newsoul/w2.u((Activity) p0.b, Ldev/local/newsoul/o.b((Activity) p0.b));
      goto :goto_90;
      :cond_8f:
      :goto_8f:
      :goto_90:
      return (Activity) p0.b;
      :pswitch_data_92:

      // .packed-switch 0x0
      // :pswitch_6f
    }

}