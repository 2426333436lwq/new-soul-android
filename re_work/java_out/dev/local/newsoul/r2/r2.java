package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/r2 ====
// super: Landroid/widget/Switch
// source: "SourceFile"

public final  class r2 extends Landroid/widget/Switch {

    public final ArrayList a;
    public boolean b;
    public boolean c;

    public void super(Context p0) {
      super(p1);
      new Ljava/util/ArrayList();
      this.a = new Ljava/util/ArrayList();
      return;
    }

    public final void a(Runnable p0) {
      p1.run();
      return;
    }

    public final boolean onTouchEvent(MotionEvent p0) {
      if (p1.getActionMasked() != 0x1) goto :cond_b;
      p0.c = 0x1;
      :cond_b:
      :try_start_b:
      :try_end_f:
      p0.c = p0.c;
      return p0.c;
      :catchall_12:
      p0.c = p0.c;
      throw e;

      // .catchall {:try_start_b .. :try_end_f} :catchall_12
    }

    public final void setChecked(boolean p0) {
      super.setChecked(p1);
      if (p0.a == 0) goto :cond_1b;
      :goto_b:
      if (p0.a.hasNext() == 0) goto :cond_1b;
      (Runnable) p0.a.next().run();
      goto :goto_b;
      :cond_1b:
      return;
    }

    public final void setEnabled(boolean p0) {
      super.setEnabled(p1);
      if (p0.a == 0) goto :cond_1b;
      :goto_b:
      if (p0.a.hasNext() == 0) goto :cond_1b;
      (Runnable) p0.a.next().run();
      goto :goto_b;
      :cond_1b:
      return;
    }

    public final void toggle() {
      p0.c = 0x1;
      :try_start_5:
      super.toggle();
      :try_end_8:
      p0.c = v0;
      return;
      :catchall_b:
      p0.c = v0;
      throw e;

      // .catchall {:try_start_5 .. :try_end_8} :catchall_b
    }

}