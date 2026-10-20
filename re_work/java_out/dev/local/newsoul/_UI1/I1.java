package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/I1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class I1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final TextView b;
    public final Activity c;
    public final Object d;
    public final View e;
    public final Object f;
    public final Object g;
    public final Serializable h;

    public void super(View p0) {
      super();
      this.a = 0x0;
      this.c = p1;
      this.d = p2;
      this.b = p3;
      this.e = p4;
      this.g = p5;
      this.h = p6;
      this.f = p7;
      return;
    }

    public void super(String p0) {
      super();
      this.a = 0x1;
      this.b = p1;
      this.d = p2;
      this.e = p3;
      this.f = p4;
      this.c = p5;
      this.g = p6;
      this.h = p7;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_fa */ }
      p0.b.setEnabled(0x0);
      p0.b.setAlpha(0x3f000000    # 0.5f);
      (String[]) p0.d[0x0] = 0x0;
      (TextView) p0.e.setText("正在检查…");
      v10.<init>(p0.c, (q2) p0.g, (Button) p0.b, (TextView) p0.e, (String) p0.h, (String[]) p0.d);
      new Ljava/lang/Thread(v10, "NewSoul-UpdateCheck");
      (Thread[]) p0.f[0x0] = new Ljava/lang/Thread(v10, "NewSoul-UpdateCheck");
      new Ljava/lang/Thread(v10, "NewSoul-UpdateCheck").start();
      return;
      :pswitch_48:
      :try_start_58:
      if (v0.isFinishing() != 0) goto :cond_f8;
      if (v0.isDestroyed() == 0) goto :cond_66;
      goto :goto_f8;
      :cond_66:
      :try_end_74:
      if (v7 != 0) goto :cond_8c;
      if (v0.isDestroyed() == 0) goto :cond_f8;
      :try_start_7e:
      v1.removeView(v0.isDestroyed());
      new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g);
      p0.e.setLayoutParams(new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g));
      goto :goto_f8;
      :catchall_8a:
      goto :goto_f3;
      :cond_8c:
      :try_end_94:
      if (compare(((double) v2), 0x3fdccccccccccccdL    # 0.45) <= 0) goto :cond_a1;
      goto :goto_a2;
      :cond_a1:
      :goto_a2:
      if (v0.isDestroyed() == 0) goto :cond_ad;
      :try_start_a8:
      if ((boolean[]) p0.h[0x0] != 0x1) goto :cond_ad;
      goto :goto_f8;
      :cond_ad:
      if (v0.isDestroyed() == 0) goto :cond_b2;
      v1.removeView(v0.isDestroyed());
      :cond_b2:
      (boolean[]) p0.h[0x0] = 0x1;
      Ldev/local/newsoul/w2.j(e, 0x1).setId(Ldev/local/newsoul/w2.j(e, 0x1));
      Ldev/local/newsoul/w2.j(e, 0x1).setTag(v5);
      new Landroid/widget/RelativeLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(e, 0x42));
      new Landroid/widget/RelativeLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(e, 0x42)).addRule(0x3, v3.getId());
      v1.addView(Ldev/local/newsoul/w2.j(e, 0x1), new Landroid/widget/RelativeLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(e, 0x42)));
      new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g);
      new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g).removeRule(0xa);
      new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g).addRule(0x3, Ldev/local/newsoul/w2.j(e, 0x1).getId());
      p0.e.setLayoutParams(new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) p0.g));
      :try_end_f2:
      goto :goto_f8;
      :goto_f3:
      :cond_f8:
      :goto_f8:
      return;
      :pswitch_data_fa:

      // .packed-switch 0x0
      // :pswitch_48

      // .catchall {:try_start_58 .. :try_end_74} :catchall_8a
      // .catchall {:try_start_7e .. :try_end_94} :catchall_8a
      // .catchall {:try_start_a8 .. :try_end_f2} :catchall_8a
    }

}