package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/t2 ====
// super: Landroid/widget/LinearLayout
// source: "SourceFile"

public final  class t2 extends Landroid/widget/LinearLayout {

    public final Context a;
    public final v2 b;

    public void super(Context p0) {
      super(p2);
      this.b = p1;
      this.a = p3;
      return;
    }

    public final void onMeasure(II p0) {
      :goto_f:
      if (v5 >= v3) goto :cond_39;
      if (v2[v5] == 0) goto :cond_36;
      :cond_36:
      goto :goto_f;
      :cond_39:
      if (Landroid/view/View$MeasureSpec.getMode(p1) == 0) goto :cond_49;
      :cond_49:
      :goto_4a:
      if (p1 >= p0.getChildCount()) goto :cond_69;
      if (p1 >= ((v0 * 0x5) % 0x5)) goto :cond_62;
      goto :goto_63;
      :cond_62:
      :goto_63:
      (LinearLayout$LayoutParams) p0.getChildAt(p1).getLayoutParams().width = (((v0 * 0x5) / 0x5) + 0x1);
      goto :goto_4a;
      :cond_69:
      super.onMeasure(Landroid/view/View$MeasureSpec.makeMeasureSpec((v0 * 0x5), 0x40000000    # 2.0f), p2);
      return;
    }

}