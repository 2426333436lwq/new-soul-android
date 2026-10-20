package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/A1 ====
// super: Ljava/lang/Object
// implements: ValueAnimator$AnimatorUpdateListene
// source: "SourceFile"

public final  class A1 extends Ljava/lang/Object implements Landroid/animation/ValueAnimator$AnimatorUpdateListener {

    public final C1 a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;

    public void super(Ldev/local/newsoul/C1;FFFF p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void onAnimationUpdate(ValueAnimator p0) {
      if (p0.a.g == p1) goto :cond_7;
      goto :goto_29;
      :cond_7:
      p0.a.e = (((p0.c - p0.b) * (Float) p1.getAnimatedValue().floatValue()) + p0.b);
      p0.a.f = (((p0.e - p0.d) * (Float) p1.getAnimatedValue().floatValue()) + p0.d);
      p0.a.a.c((((p0.c - p0.b) * (Float) p1.getAnimatedValue().floatValue()) + p0.b), (((p0.e - p0.d) * (Float) p1.getAnimatedValue().floatValue()) + p0.d), 0x1);
      :goto_29:
      return;
    }

}