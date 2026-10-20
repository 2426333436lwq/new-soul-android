package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/D2 ====
// super: Ljava/lang/Object
// implements: View$OnAttachStateChangeListene
// source: "SourceFile"

public final  class D2 extends Ljava/lang/Object implements Landroid/view/View$OnAttachStateChangeListener {

    public final int a;

    public void super(int p0) {
      super();
      this.a = p1;
      return;
    }

    public final void onViewAttachedToWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_1e */ }
      if ((Drawable) L2.d.get(p1) == 0) goto :cond_12;
      (Drawable) L2.d.get(p1).invalidateSelf();
      :cond_12:
      return;
      :pswitch_13:
      new Ldev/local/newsoul/T(0x1, (Drawable) L2.d.get(p1));
      return;
      :pswitch_data_1e:

      // .packed-switch 0x0
      // :pswitch_13
    }

    public final void onViewDetachedFromWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_1c */ }
      Ldev/local/newsoul/L2.f((Drawable) L2.d.get(p1));
      return;
      :pswitch_11:
      if (Ldev/local/newsoul/Q0.i(p1) == 0) goto :cond_1a;
      Ldev/local/newsoul/Q0.i(p1).h();
      :cond_1a:
      return;
      :pswitch_data_1c:

      // .packed-switch 0x0
      // :pswitch_11
    }

}