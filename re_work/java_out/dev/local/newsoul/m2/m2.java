package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/m2 ====
// super: Ljava/lang/Object
// implements: PopupWindow$OnDismissListene
// source: "SourceFile"

public final  class m2 extends Ljava/lang/Object implements Landroid/widget/PopupWindow$OnDismissListener {

    public final View a;
    public final View$OnAttachStateChangeListener b;

    public void super(p2 p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void onDismiss() {
      p0.a.removeOnAttachStateChangeListener(p0.b);
      return;
    }

}