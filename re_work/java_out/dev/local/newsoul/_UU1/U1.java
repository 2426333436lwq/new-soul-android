package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/U1 ====
// super: Ljava/lang/Object
// implements: CompoundButton$OnCheckedChangeListene
// source: "SourceFile"

public final  class U1 extends Ljava/lang/Object implements Landroid/widget/CompoundButton$OnCheckedChangeListener {

    public final SharedPreferences a;
    public final r2 b;
    public final String c;

    public void super(String p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void onCheckedChanged(Landroid/widget/CompoundButton;Z p0) {
      if (p0.a != 0) goto :cond_5;
      goto :goto_42;
      :cond_5:
      if (p0.b.b == 0) goto :cond_c;
      goto :goto_42;
      :cond_c:
      p0.a.edit().putBoolean(p0.c, p2).apply();
      new Ldev/local/newsoul/h(0x0);
      new Ljava/lang/StringBuilder("setting changed: ");
      :goto_42:
      return;
    }

}