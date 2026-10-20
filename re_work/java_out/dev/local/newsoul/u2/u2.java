package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/u2 ====
// super: Landroid/widget/LinearLayout
// source: "SourceFile"

public final  class u2 extends Landroid/widget/LinearLayout {

    public final v2 a;

    public void super(Context p0) {
      super(p2);
      this.a = p1;
      return;
    }

    public final void onMeasure(II p0) {
      if (Build$VERSION.SDK_INT < 0x1e) goto :cond_33;
      goto :goto_3d;
      :cond_33:
      :goto_3d:
      if (Landroid/view/View$MeasureSpec.getMode(p2) == 0) goto :cond_7b;
      :cond_7b:
      super.onMeasure(p1, Landroid/view/View$MeasureSpec.makeMeasureSpec(Ljava/lang/Math.max(Ldev/local/newsoul/w2.i(p0.a.a.g, 0x70), Ljava/lang/Math.min(Ldev/local/newsoul/w2.i(p0.a.a.g, 0x26c), (Ljava/lang/Math.round((((float) ((Ldev/local/newsoul/k.c(Ldev/local/newsoul/k.f(p0.a.a.a.getWindowManager())).height() - Ldev/local/newsoul/H0.a(Ldev/local/newsoul/k.b(Ldev/local/newsoul/k.d(Ldev/local/newsoul/k.f(p0.a.a.a.getWindowManager())), Ldev/local/newsoul/k.d(Ldev/local/newsoul/k.f(p0.a.a.a.getWindowManager()))))) - Ldev/local/newsoul/H0.i(Ldev/local/newsoul/k.b(Ldev/local/newsoul/k.d(Ldev/local/newsoul/k.f(p0.a.a.a.getWindowManager())), Ldev/local/newsoul/k.d(Ldev/local/newsoul/k.f(p0.a.a.a.getWindowManager())))))) * 0x3f5c28f6    # 0.86f)) - Ldev/local/newsoul/w2.i(p0.a.a.g, 0x84)))), 0x40000000    # 2.0f));
      return;
    }

    public final void onWindowFocusChanged(boolean p0) {
      super.onWindowFocusChanged(p1);
      if (p1 == 0) goto :cond_c;
      p0.a.a.e();
      :cond_c:
      return;
    }

}