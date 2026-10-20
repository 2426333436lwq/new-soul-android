package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/j2 ====
// super: Landroid/widget/FrameLayout
// source: "SourceFile"

public final  class j2 extends Landroid/widget/FrameLayout {

    public ValueAnimator a;
    public boolean b;
    public boolean c;
    public float d;

    public static boolean a(View p0) {
      if (p0.isAccessibilityFocused() == 0) goto :cond_c;
      :cond_c:
      if ((p0 instanceof ViewGroup) == 0) goto :cond_25;
      :goto_13:
      if ((p0 instanceof ViewGroup) >= p0.getChildCount()) goto :cond_25;
      goto :goto_13;
      :cond_25:
      return (p0.isAccessibilityFocused() | Ldev/local/newsoul/j2.a(p0.getChildAt((p0 instanceof ViewGroup))));
    }

    public final void b() {
      p0.a = 0x0;
      if (p0.a == 0) goto :cond_a;
      p0.a.cancel();
      :cond_a:
      if (p0.b == 0) goto :cond_11;
      goto :goto_12;
      :cond_11:
      :goto_12:
      p0.d = 0x3f800000    # 1.0f;
      p0.setAlpha(0x3f800000    # 1.0f);
      if (p0.b == 0) goto :cond_1d;
      goto :goto_1f;
      :cond_1d:
      :goto_1f:
      p0.setVisibility(0x0);
      p0.requestLayout();
      return;
    }

    public final void c(ZLdev/local/newsoul/r2; p0) {
      if (p0.c == 0) goto :cond_11;
      if (p0.b != p1) goto :cond_11;
      if (p2 != 0) goto :cond_10;
      p0.b();
      :cond_10:
      return;
      :cond_11:
      if (p0.c == 0) goto :cond_35;
      if (p2 == 0) goto :cond_35;
      if (p0.isAttachedToWindow() == 0) goto :cond_35;
      if (p0.isShown() == 0) goto :cond_35;
      if (p0.hasWindowFocus() == 0) goto :cond_35;
      if (p0.getWidth() <= 0) goto :cond_35;
      if (p0.getWidth() == 0) goto :cond_35;
      goto :goto_36;
      :cond_35:
      :goto_36:
      if (p0.c == 0) goto :cond_6d;
      if (p1 == 0) goto :cond_6d;
      if (p2 == 0) goto :cond_6d;
      if ((p0.getParent() instanceof View) == 0) goto :cond_6d;
      if ((View) p0.getParent().isShown() == 0) goto :cond_6c;
      if ((View) p0.getParent().isAttachedToWindow() == 0) goto :cond_6c;
      if ((View) p0.getParent().hasWindowFocus() == 0) goto :cond_6c;
      if ((View) p0.getParent().getWidth() <= 0) goto :cond_6c;
      if ((View) p0.getParent().getWidth() == 0) goto :cond_6c;
      goto :goto_6d;
      :cond_6c:
      :cond_6d:
      :goto_6d:
      p0.c = 0x1;
      if (p1 != 0) goto :cond_88;
      if (p0.b == 0) goto :cond_88;
      (View) p0.getParent().getWidth().obtain(v6, (View) p0.getParent().getWidth(), v8, 0x3, 0x0, 0x0, 0x0).recycle();
      :cond_88:
      p0.b = p1;
      if (p1 == 0) goto :cond_8f;
      goto :goto_91;
      :cond_8f:
      :goto_91:
      p0.setDescendantFocusability(0x40000);
      if (p1 != 0) goto :cond_b0;
      p0.clearFocus();
      if (p2 == 0) goto :cond_b0;
      if (p0.hasFocus() == 0) goto :cond_a9;
      :cond_a9:
      if (Ldev/local/newsoul/j2.a(p0) == 0) goto :cond_b0;
      :cond_b0:
      if (p1 == 0) goto :cond_b4;
      goto :goto_b5;
      :cond_b4:
      :goto_b5:
      p0.setImportantForAccessibility(0x0);
      p0.a = 0x0;
      if (p0.a == 0) goto :cond_c1;
      p0.a.cancel();
      :cond_c1:
      if (0x1 != 0) goto :cond_c7;
      p0.b();
      return;
      :cond_c7:
      p0.setVisibility(0x0);
      if (p1 == 0) goto :cond_d1;
      goto :goto_d2;
      :cond_d1:
      :goto_d2:
      new float[][0x2][0x0] = p0.d;
      new float[][0x2][0x1] = 0x3f800000    # 1.0f;
      p0.a = Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]);
      new Landroid/view/animation/DecelerateInterpolator();
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).setInterpolator(new Landroid/view/animation/DecelerateInterpolator());
      new Ldev/local/newsoul/L0(p0, 0x2);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addUpdateListener(new Ldev/local/newsoul/L0(p0, 0x2));
      new Ldev/local/newsoul/B1(p0, 0x1);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addListener(new Ldev/local/newsoul/B1(p0, 0x1));
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).start();
      return;
    }

    public final boolean dispatchKeyEvent(KeyEvent p0) {
      if (p0.b == 0) goto :cond_d;
      if (super.dispatchKeyEvent(p1) == 0) goto :cond_b;
      goto :goto_d;
      :cond_b:
      goto :goto_e;
      :cond_d:
      :goto_d:
      :goto_e:
      return p0.b;
    }

    public final boolean dispatchTouchEvent(MotionEvent p0) {
      if (p0.b == 0) goto :cond_d;
      if (super.dispatchTouchEvent(p1) == 0) goto :cond_b;
      goto :goto_d;
      :cond_b:
      goto :goto_e;
      :cond_d:
      :goto_d:
      :goto_e:
      return p0.b;
    }

    public final void onDetachedFromWindow() {
      p0.b();
      super.onDetachedFromWindow();
      return;
    }

    public final void onMeasure(II p0) {
      super.onMeasure(p1, p2);
      p0.setMeasuredDimension(p0.getMeasuredWidth(), Ljava/lang/Math.round((((float) p0.getMeasuredHeight()) * p0.d)));
      return;
    }

    public final void onSizeChanged(IIII p0) {
      super.onSizeChanged(p1, p2, p3, p4);
      if (p3 <= 0) goto :cond_e;
      if (p1 == p3) goto :cond_e;
      if (p0.a == 0) goto :cond_e;
      p0.b();
      :cond_e:
      return;
    }

    public final void onWindowFocusChanged(boolean p0) {
      super.onWindowFocusChanged(p1);
      p0.b();
      return;
    }

}