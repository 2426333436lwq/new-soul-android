package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/b0 ====
// super: Ljava/lang/Object
// implements: CompoundButton$OnCheckedChangeListene
// source: "SourceFile"

public final  class b0 extends Ljava/lang/Object implements Landroid/widget/CompoundButton$OnCheckedChangeListener {

    public final f0 a;

    public void super(f0 p0) {
      super();
      this.a = p1;
      return;
    }

    public final void onCheckedChanged(Landroid/widget/CompoundButton;Z p0) {
      if (p0.a.g != 0) goto :cond_21;
      if (p0.a.h == 0) goto :cond_b;
      goto :goto_21;
      :cond_b:
      p0.a.h = 0x1;
      p0.a.b();
      new Ldev/local/newsoul/d0(p0.a, 0x0);
      new Ldev/local/newsoul/U(p2, new Ldev/local/newsoul/d0(p0.a, 0x0));
      V.a.execute(new Ldev/local/newsoul/U(p2, new Ldev/local/newsoul/d0(p0.a, 0x0)));
      :cond_21:
      :goto_21:
      return;
    }

}