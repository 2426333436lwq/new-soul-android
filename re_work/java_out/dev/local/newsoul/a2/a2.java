package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/a2 ====
// super: Ljava/lang/Object
// implements: TextWatche
// source: "SourceFile"

public final  class a2 extends Ljava/lang/Object implements Landroid/text/TextWatcher {

    public final int a;
    public final Runnable b;

    public void super(Ljava/lang/Runnable;I p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    private final void a(Editable p0) {
      return;
    }

    private final void b(Ljava/lang/CharSequence;III p0) {
      return;
    }

    private final void c(Ljava/lang/CharSequence;III p0) {
      return;
    }

    private final void d(Ljava/lang/CharSequence;III p0) {
      return;
    }

    public final void afterTextChanged(Editable p0) {
      switch (p0.a) { /* :pswitch_data_c */ }
      return;
      :pswitch_6:
      p0.b.run();
      return;
      :pswitch_data_c:

      // .packed-switch 0x0
      // :pswitch_6
    }

    public final void beforeTextChanged(Ljava/lang/CharSequence;III p0) {
      return;
    }

    public final void onTextChanged(Ljava/lang/CharSequence;III p0) {
      switch (p0.a) { /* :pswitch_data_c */ }
      p0.b.run();
      :pswitch_a:
      return;
      :pswitch_data_c:

      // .packed-switch 0x0
      // :pswitch_a
    }

}