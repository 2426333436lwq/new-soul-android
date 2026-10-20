package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/b2 ====
// super: Landroid/widget/LinearLayout
// source: "SourceFile"

public final  class b2 extends Landroid/widget/LinearLayout {

    public final Activity a;
    public final TextView b;
    public final EditText c;

    public void super(EditText p0) {
      super(p1);
      this.a = p2;
      this.b = p3;
      this.c = p4;
      return;
    }

    public final void onMeasure(II p0) {
      if (Landroid/view/View$MeasureSpec.getMode(p1) == 0) goto :cond_44;
      if (Landroid/view/View$MeasureSpec.getSize(p1) >= (Ldev/local/newsoul/w2.i(p0.a, 0x80) + Ljava/lang/Math.max(Ldev/local/newsoul/w2.i(p0.a, 0x40), (p0.b.getPaddingRight() + (p0.b.getPaddingLeft() + ((int) Ljava/lang/Math.ceil(((double) p0.b.getPaint().measureText(p0.b.getText().toString())), p0.b.getText().toString()))))))) goto :cond_44;
      goto :goto_45;
      :cond_44:
      :goto_45:
      p0.setOrientation(0x1);
      if (0x1 == 0) goto :cond_54;
      goto :goto_55;
      :cond_54:
      :goto_55:
      (LinearLayout$LayoutParams) p0.c.getLayoutParams().width = -0x1;
      if (0x1 == 0) goto :cond_5b;
      goto :goto_5d;
      :cond_5b:
      :goto_5d:
      (LinearLayout$LayoutParams) p0.c.getLayoutParams().weight = 0x0;
      if (0x1 == 0) goto :cond_6b;
      goto :goto_6f;
      :cond_6b:
      :goto_6f:
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().setMarginStart(0x0);
      if (0x1 == 0) goto :cond_76;
      goto :goto_7a;
      :cond_76:
      :goto_7a:
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().leftMargin = 0x0;
      if (0x1 == 0) goto :cond_82;
      :cond_82:
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().topMargin = 0x0;
      if (0x1 == 0) goto :cond_8a;
      goto :goto_8c;
      :cond_8a:
      :goto_8c:
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().gravity = 0x800005;
      (LinearLayout$LayoutParams) p0.c.getLayoutParams().height = -0x2;
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().height = -0x2;
      super.onMeasure(p1, p2);
      if (0x1 != 0) goto :cond_ab;
      (LinearLayout$LayoutParams) p0.c.getLayoutParams().height = Ljava/lang/Math.max(p0.c.getMeasuredHeight(), p0.b.getMeasuredHeight());
      (LinearLayout$LayoutParams) p0.b.getLayoutParams().height = Ljava/lang/Math.max(p0.c.getMeasuredHeight(), p0.b.getMeasuredHeight());
      super.onMeasure(p1, p2);
      :cond_ab:
      return;
    }

}