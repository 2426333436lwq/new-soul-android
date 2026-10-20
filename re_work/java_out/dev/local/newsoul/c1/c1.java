package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/c1 ====
// super: Ljava/lang/Object
// implements: DialogInterface$OnDismissListene
// source: "SourceFile"

public final  class c1 extends Ljava/lang/Object implements Landroid/content/DialogInterface$OnDismissListener {

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

    public final void onDismiss(DialogInterface p0) {
      switch (p0.a) { /* :pswitch_data_3e */ }
      if ((b3) p0.b.h != (q2) p0.c) goto :cond_14;
      (b3) p0.b.b();
      :cond_14:
      return;
      :pswitch_15:
      (q2) p0.b.s = 0x0;
      if ((q2) p0.b.e.isShowing() == 0) goto :cond_2d;
      if ((View) p0.c == 0) goto :cond_2d;
      :cond_2d:
      return;
      :pswitch_2e:
      if ((d1) p0.b.t != (Dialog) p0.c) goto :cond_3d;
      (d1) p0.b.t = 0x0;
      :cond_3d:
      return;
      :pswitch_data_3e:

      // .packed-switch 0x0
      // :pswitch_2e
      // :pswitch_15
    }

}