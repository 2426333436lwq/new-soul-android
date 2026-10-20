package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/v0 ====
// super: Ljava/lang/Object
// implements: CompoundButton$OnCheckedChangeListene
// source: "SourceFile"

public final  class v0 extends Ljava/lang/Object implements Landroid/widget/CompoundButton$OnCheckedChangeListener {

    public final y0 a;
    public final q2 b;

    public void super(q2 p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void onCheckedChanged(Landroid/widget/CompoundButton;Z p0) {
      if (p2 != 0) goto :cond_a;
      p0.a.c.setError(0x0);
      :cond_a:
      if (p2 != 0) goto :cond_2b;
      if (p0.a.c.hasFocus() == 0) goto :cond_2b;
      if ((InputMethodManager) p0.b.g.getSystemService("input_method") == 0) goto :cond_28;
      :cond_28:
      p0.a.c.clearFocus();
      :cond_2b:
      return;
    }

}