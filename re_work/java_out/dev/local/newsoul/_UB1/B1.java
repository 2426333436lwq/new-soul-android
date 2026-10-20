package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/B1 ====
// super: Landroid/animation/AnimatorListenerAdapter
// source: "SourceFile"

public final  class B1 extends Landroid/animation/AnimatorListenerAdapter {

    public final int a;
    public final FrameLayout b;

    public void super(Landroid/widget/FrameLayout;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void onAnimationEnd(Animator p0) {
      switch (p0.a) { /* :pswitch_data_2c */ }
      if ((j2) p0.b.a != p1) goto :cond_13;
      (j2) p0.b.a = 0x0;
      (j2) p0.b.b();
      :cond_13:
      return;
      :pswitch_14:
      if ((C1) p0.b.g == p1) goto :cond_1d;
      goto :goto_2a;
      :cond_1d:
      (C1) p0.b.g = 0x0;
      (C1) p0.b.a.c((C1) p0.b.e, (C1) p0.b.f, 0x0);
      :goto_2a:
      return;
      :pswitch_data_2c:

      // .packed-switch 0x0
      // :pswitch_14
    }

}