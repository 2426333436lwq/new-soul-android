package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/l2 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class l2 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final q2 a;
    public final PopupWindow b;
    public final View c;
    public final IntConsumer d;
    public final int e;

    public void super(Ldev/local/newsoul/q2;Landroid/widget/PopupWindow;Landroid/view/View;Ljava/util/function/IntConsumer;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void onClick(View p0) {
      p0.b.dismiss();
      if (p0.c.isAttachedToWindow() == 0) goto :cond_2d;
      if (p0.c.isShown() == 0) goto :cond_2d;
      if (p0.a.a.isFinishing() != 0) goto :cond_2d;
      if (p0.a.a.isDestroyed() != 0) goto :cond_2d;
      p0.d.accept(p0.e);
      :cond_2d:
      return;
    }

}