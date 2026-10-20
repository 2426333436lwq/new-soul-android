package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/X2 ====
// super: Ljava/lang/Object
// implements: DialogInterface$OnCancelListene
// source: "SourceFile"

public final  class X2 extends Ljava/lang/Object implements Landroid/content/DialogInterface$OnCancelListener {

    public final b3 a;
    public final q2 b;
    public final c3 c;

    public void super(c3 p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void onCancel(DialogInterface p0) {
      if (p0.a.m != 0) goto :cond_21;
      if (p0.a.h == p0.b) goto :cond_f;
      goto :goto_21;
      :cond_f:
      if (p0.a.n == 0) goto :cond_17;
      p0.a.c();
      goto :goto_21;
      :cond_17:
      :try_start_17:
      p0.a.j();
      p0.c.run();
      :try_end_1d:
      goto :goto_21;
      :catchall_1e:
      p0.a.b();
      :cond_21:
      :goto_21:
      return;

      // .catchall {:try_start_17 .. :try_end_1d} :catchall_1e
    }

}