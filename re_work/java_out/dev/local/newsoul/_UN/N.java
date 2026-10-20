package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/N ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class N extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final boolean[] a;
    public final P b;
    public final q2 c;
    public final Activity d;
    public final Map e;

    public void super(ZLdev/local/newsoul/P;Ldev/local/newsoul/q2;Landroid/app/Activity;Ljava/util/Map;[] p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void onClick(View p0) {
      if (p0.a[0x0] == 0) goto :cond_8;
      goto :goto_31;
      :cond_8:
      p0.a[0x0] = 0x1;
      if ((Q) p0.b.e.get() == 0) goto :cond_1c;
      (Q) p0.b.e.get().a("正在导入配置…", 0x1);
      :cond_1c:
      p0.c.e.dismiss();
      new Ldev/local/newsoul/K(p0.d, p0.e, p0.b);
      S.c.execute(new Ldev/local/newsoul/K(p0.d, p0.e, p0.b));
      :goto_31:
      return;
    }

}