package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Q1 ====
// super: Ljava/lang/Object
// implements: CompoundButton$OnCheckedChangeListene
// source: "SourceFile"

public final  class Q1 extends Ljava/lang/Object implements Landroid/widget/CompoundButton$OnCheckedChangeListener {

    public final g2 a;
    public final BooleanSupplier b;
    public final Runnable c;
    public final SharedPreferences d;

    public void super(SharedPreferences p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      return;
    }

    public final void onCheckedChanged(Landroid/widget/CompoundButton;Z p0) {
      if (p0.a.d == 0) goto :cond_7;
      goto :goto_4a;
      :cond_7:
      if (p0.b.getAsBoolean() != 0) goto :cond_15;
      p0.c.run();
      goto :goto_4a;
      :cond_15:
      p0.d.edit().putBoolean(p0.c.a, p2).apply();
      new Ldev/local/newsoul/h(0x0);
      new Ljava/lang/StringBuilder("setting changed: ");
      :goto_4a:
      return;
    }

}