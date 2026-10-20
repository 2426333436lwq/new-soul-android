package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/n2 ====
// super: Ljava/lang/Object
// implements: DialogInterface$OnKeyListene
// source: "SourceFile"

public final  class n2 extends Ljava/lang/Object implements Landroid/content/DialogInterface$OnKeyListener {

    public final q2 a;

    public void super(q2 p0) {
      super();
      this.a = p1;
      return;
    }

    public final boolean onKey(KeyEvent p0) {
      if (p2 == 0x4) goto :cond_e;
      if (p2 == 0x6f) goto :cond_e;
      goto :goto_1f;
      :cond_e:
      if (p3.getAction() != 0x1) goto :cond_1e;
      if (p3.isCanceled() != 0) goto :cond_1e;
      0x0.b();
      :cond_1e:
      :goto_1f:
      return 0x1;
    }

}