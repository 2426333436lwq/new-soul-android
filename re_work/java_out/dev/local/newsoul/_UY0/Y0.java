package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Y0 ====
// super: Landroid/animation/AnimatorListenerAdapter
// source: "SourceFile"

public final  class Y0 extends Landroid/animation/AnimatorListenerAdapter {

    public final int a;
    public final Z0 b;

    public void super(Ldev/local/newsoul/Z0;I p0) {
      super();
      this.b = p1;
      this.a = p2;
      return;
    }

    public final void onAnimationEnd(Animator p0) {
      if (p0.b.r == p1) goto :cond_7;
      return;
      :cond_7:
      p0.b.r = 0x0;
      p0.b.c(p0.a);
      return;
    }

}