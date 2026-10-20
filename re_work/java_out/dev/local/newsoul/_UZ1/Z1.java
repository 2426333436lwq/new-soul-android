package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Z1 ====
// super: Ljava/lang/Object
// implements: View$OnLongClickListene
// source: "SourceFile"

public final  class Z1 extends Ljava/lang/Object implements Landroid/view/View$OnLongClickListener {

    public final Consumer a;
    public final TextView b;

    public void super(TextView p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final boolean onLongClick(View p0) {
      p0.a.accept(p0.b);
      return p0.b;
    }

}