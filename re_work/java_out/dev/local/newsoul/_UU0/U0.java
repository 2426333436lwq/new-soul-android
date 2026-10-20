package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/U0 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class U0 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final W0 b;
    public final H2 c;

    public void super(Ldev/local/newsoul/W0;Ldev/local/newsoul/H2;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_40 */ }
      :try_start_a:
      :try_end_12:
      goto :goto_1b;
      :catch_13:
      :goto_1b:
      p0.c.b(0x0);
      return;
      :pswitch_22:
      :try_start_27:
      :try_end_2f:
      goto :goto_38;
      :catch_30:
      :goto_38:
      p0.c.b(0x1);
      return;
      :pswitch_data_40:

      // .packed-switch 0x0
      // :pswitch_22

      // try :try_start_a..:try_end_12 catch (Ljava/lang/ReflectiveOperationException) -> :catch_13
      // try :try_start_27..:try_end_2f catch (Ljava/lang/ReflectiveOperationException) -> :catch_30
    }

}