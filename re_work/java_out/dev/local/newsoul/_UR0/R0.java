package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/R0 ====
// super: Landroid/view/ViewGroup
// source: "SourceFile"

public final  class R0 extends Landroid/view/ViewGroup {

    public final int a;

    public void super(Context p0) {
      super(p1);
      this.a = Ldev/local/newsoul/w2.i(p1, 0x8);
      return;
    }

    public final ViewGroup$LayoutParams generateDefaultLayoutParams() {
      new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2);
      return new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2);
    }

    public final void onLayout(ZIIII p0) {
      if (p0.getLayoutDirection() != 0x1) goto :cond_a;
      goto :goto_b;
      :cond_a:
      :goto_b:
      :goto_f:
      if (p0.getLayoutDirection() >= p0.getChildCount()) goto :cond_44;
      if (p5 <= 0) goto :cond_2d;
      if ((p5 + p0.getChildAt(p0.getLayoutDirection()).getMeasuredWidth()) <= (p4 - p2)) goto :cond_2d;
      :cond_2d:
      if (0x1 == 0) goto :cond_33;
      goto :goto_34;
      :cond_33:
      :goto_34:
      p0.getChildAt(p0.getLayoutDirection()).layout((((p4 - p2) - p5) - p0.getChildAt(p0.getLayoutDirection()).getMeasuredWidth()), v0, ((((p4 - p2) - p5) - p0.getChildAt(p0.getLayoutDirection()).getMeasuredWidth()) + p0.getChildAt(p0.getLayoutDirection()).getMeasuredWidth()), (v0 + p0.getChildAt(p0.getLayoutDirection()).getMeasuredHeight()));
      goto :goto_f;
      :cond_44:
      return;
    }

    public final void onMeasure(II p0) {
      :goto_9:
      if (v1 >= p0.getChildCount()) goto :cond_48;
      if (p0.getChildAt(v1).getLayoutParams().width != -0x1) goto :cond_1e;
      goto :goto_22;
      :cond_1e:
      :goto_22:
      p0.getChildAt(v1).measure(Landroid/view/View$MeasureSpec.makeMeasureSpec(p1, 0x40000000    # 2.0f), Landroid/view/View$MeasureSpec.makeMeasureSpec(v0, v0));
      if (v4 <= 0) goto :cond_3b;
      if ((v4 + p1) <= p1) goto :cond_3b;
      :cond_3b:
      goto :goto_9;
      :cond_48:
      p0.setMeasuredDimension(p1, Landroid/view/View.resolveSize((v2 + Ljava/lang/Math.max(v3, p0.getChildAt(v1).getMeasuredHeight())), p2));
      return;
    }

}