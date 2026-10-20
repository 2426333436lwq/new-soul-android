package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/j0 ====
// super: Ljava/lang/Object
// implements: DialogInterface$OnDismissListene
// source: "SourceFile"

public final  class j0 extends Ljava/lang/Object implements Landroid/content/DialogInterface$OnDismissListener {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void onDismiss(DialogInterface p0) {
      switch (p0.a) { /* :pswitch_data_24 */ }
      if ((Thread[]) p0.b[0x0] == 0) goto :cond_11;
      (Thread[]) p0.b[0x0].interrupt();
      :cond_11:
      return;
      :pswitch_12:
      (Runnable) p0.b.run();
      return;
      :pswitch_1a:
      (ImageView) p0.b.setImageDrawable(0x0);
      return;
      :pswitch_data_24:

      // .packed-switch 0x0
      // :pswitch_1a
      // :pswitch_12
    }

}