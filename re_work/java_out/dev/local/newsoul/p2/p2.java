package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/p2 ====
// super: Ljava/lang/Object
// implements: View$OnAttachStateChangeListene
// source: "SourceFile"

public final  class p2 extends Ljava/lang/Object implements Landroid/view/View$OnAttachStateChangeListener {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    private final void a(View p0) {
      return;
    }

    public final void onViewAttachedToWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_e */ }
      (I2) p0.b.i();
      :pswitch_c:
      return;
      :pswitch_data_e:

      // .packed-switch 0x0
      // :pswitch_c
    }

    public final void onViewDetachedFromWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_16 */ }
      (I2) p0.b.h();
      return;
      :pswitch_d:
      (PopupWindow) p0.b.dismiss();
      return;
      :pswitch_data_16:

      // .packed-switch 0x0
      // :pswitch_d
    }

}