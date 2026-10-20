package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/F1 ====
// super: Ljava/lang/Object
// implements: View$OnAttachStateChangeListene
// source: "SourceFile"

public final  class F1 extends Ljava/lang/Object implements Landroid/view/View$OnAttachStateChangeListener {

    public final int a;
    public final Object b;
    public final Object c;

    public void super(Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    private final void a(View p0) {
      return;
    }

    public final void onViewAttachedToWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_16 */ }
      return;
      :pswitch_6:
      return;
      :pswitch_data_16:

      // .packed-switch 0x0
      // :pswitch_6
    }

    public final void onViewDetachedFromWindow(View p0) {
      switch (p0.a) { /* :pswitch_data_22 */ }
      Ldev/local/newsoul/I0.i((OnBackInvokedDispatcher) p0.b, (k2) (OnBackInvokedCallback) p0.c);
      p1.removeOnAttachStateChangeListener(p0);
      return;
      :pswitch_16:
      return;
      :pswitch_data_22:

      // .packed-switch 0x0
      // :pswitch_16
    }

}