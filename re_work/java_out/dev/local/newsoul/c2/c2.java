package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/c2 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class c2 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final d2 a;
    public final String[] b;
    public final ArrayList c;
    public final int d;
    public final int[] e;
    public final TextView f;

    public void super(TextView p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      return;
    }

    public final void onClick(View p0) {
      p0.b[0x0] = (String) p0.c.remove(p0.d);
      p0.e[0x0] = p0.d;
      new Ljava/lang/StringBuilder("已删除“");
      p0.f.setText(new Ljava/lang/StringBuilder("已删除“").toString());
      p0.f.setVisibility(0x0);
      p0.a.run();
      return;
    }

}