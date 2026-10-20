package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/p1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class p1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final String b;
    public final Activity c;
    public final q2 d;
    public final boolean e;
    public final Runnable[] f;

    public void super(Ljava/lang/String;Landroid/app/Activity;Ldev/local/newsoul/q2;Z[Ljava/lang/Runnable;I p0) {
      super();
      this.a = p6;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      this.f = p5;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_60 */ }
      :try_start_11:
      Ldev/local/newsoul/u1.h(v4);
      new Ldev/local/newsoul/x1(v6.getFilesDir(), v4, 0x0);
      synchronized (x1.b) {
      :try_end_21:
      :try_start_21:
      }
      :try_end_26:
      :try_start_26:
      v11.<init>(v9, v4, v5, v6, v7, v10);
      :try_end_33:
      goto :goto_45;
      :catch_34:
      goto :goto_39;
      :catchall_36:
      :try_start_37:
      }
      :try_end_38:
      :try_start_38:
      throw e;
      :try_end_39:
      :goto_39:
      new Ldev/local/newsoul/H(v9, v10, e, 0x8);
      :goto_45:
      return;
      :pswitch_46:
      e.<init>(v1.b, v1.c, v1.d, v1.e, v1.f, 0x1);
      u1.b.execute(e);
      return;
      :pswitch_data_60:

      // .packed-switch 0x0
      // :pswitch_46

      // try :try_start_11..:try_end_21 catch (Ljava/lang/Exception) -> :catch_34
      // .catchall {:try_start_21 .. :try_end_26} :catchall_36
      // try :try_start_26..:try_end_33 catch (Ljava/lang/Exception) -> :catch_34
      // .catchall {:try_start_37 .. :try_end_38} :catchall_36
      // try :try_start_38..:try_end_39 catch (Ljava/lang/Exception) -> :catch_34
    }

}