package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/u0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class u0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final y0 a;
    public final y0 b;
    public final y0 c;
    public final y0 d;
    public final SharedPreferences e;
    public final q2 f;
    public final Activity g;

    public void super(Activity p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      return;
    }

    public final void onClick(View p0) {
      if (p0.a.f() == 0) goto :cond_4c;
      if (p0.b.f() == 0) goto :cond_4c;
      if (p0.c.f() == 0) goto :cond_4c;
      if (p0.d.f() != 0) goto :cond_21;
      goto :goto_4c;
      :cond_21:
      p0.a.a(p0.e.edit());
      p0.b.a(p0.e.edit());
      p0.c.a(p0.e.edit());
      p0.d.a(p0.e.edit());
      p0.e.edit().apply();
      p0.f.e.dismiss();
      Landroid/widget/Toast.makeText(p0.g, "已保存", 0x0).show();
      :cond_4c:
      :goto_4c:
      return;
    }

}