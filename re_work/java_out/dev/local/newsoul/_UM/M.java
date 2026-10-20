package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/M ====
// super: Ljava/lang/Object
// implements: DialogInterface$OnDismissListene
// source: "SourceFile"

public final  class M extends Ljava/lang/Object implements Landroid/content/DialogInterface$OnDismissListener {

    public final boolean[] a;
    public final Activity b;
    public final P c;

    public void super(ZLandroid/app/Activity;Ldev/local/newsoul/P;[] p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void onDismiss(DialogInterface p0) {
      if (p0.a[0x0] != 0) goto :cond_10;
      Ldev/local/newsoul/S.b(p0.b, p0.c, "已取消导入");
      :cond_10:
      return;
    }

}