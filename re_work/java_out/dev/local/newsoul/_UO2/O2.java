package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/O2 ====
// super: Ljava/lang/Object
// implements: View$OnLongClickListene
// source: "SourceFile"

public final  class O2 extends Ljava/lang/Object implements Landroid/view/View$OnLongClickListener {

    public final d3 a;
    public final WeakReference b;
    public final Activity c;

    public void super(Activity p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final boolean onLongClick(View p0) {
      :try_start_7:
      if (p1.get() != 0) goto :cond_f;
      goto :goto_2a;
      :cond_f:
      new Ldev/local/newsoul/b3(v0, 0x0);
      if (v0.f == 0) goto :cond_1b;
      v0.f.b();
      :cond_1b:
      v0.f = new Ldev/local/newsoul/b3(v0, 0x0);
      new Ldev/local/newsoul/b3(v0, 0x0).f();
      :try_end_20:
      goto :goto_29;
      :catchall_21:
      Ldev/local/newsoul/d3.f(p0.c, "暂时无法打开语音库", e);
      :goto_29:
      :goto_2a:
      return v0;

      // .catchall {:try_start_7 .. :try_end_20} :catchall_21
    }

}