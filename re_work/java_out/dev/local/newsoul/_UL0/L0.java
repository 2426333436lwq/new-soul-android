package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/L0 ====
// super: Ljava/lang/Object
// implements: ValueAnimator$AnimatorUpdateListene
// source: "SourceFile"

public final  class L0 extends Ljava/lang/Object implements Landroid/animation/ValueAnimator$AnimatorUpdateListener {

    public final int a;
    public final FrameLayout b;

    public void super(Landroid/widget/FrameLayout;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final void onAnimationUpdate(ValueAnimator p0) {
      switch (p0.a) { /* :pswitch_data_52 */ }
      (j2) p0.b.d = (Float) p1.getAnimatedValue().floatValue();
      (j2) p0.b.setAlpha((Float) p1.getAnimatedValue().floatValue());
      (j2) p0.b.requestLayout();
      return;
      :pswitch_1f:
      if ((Z0) p0.b.r == p1) goto :cond_28;
      goto :goto_37;
      :cond_28:
      (Z0) p0.b.i = (Float) p1.getAnimatedValue().floatValue();
      (Z0) p0.b.j();
      :goto_37:
      return;
      :pswitch_38:
      if ((M0) p0.b.r == p1) goto :cond_41;
      goto :goto_50;
      :cond_41:
      (M0) p0.b.n = (Float) p1.getAnimatedValue().floatValue();
      (M0) p0.b.invalidate();
      :goto_50:
      return;
      :pswitch_data_52:

      // .packed-switch 0x0
      // :pswitch_38
      // :pswitch_1f
    }

}