package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/C1 ====
// super: Landroid/widget/FrameLayout
// source: "SourceFile"

public final  class C1 extends Landroid/widget/FrameLayout {

    public final M0 a;
    public final LinearLayout b;
    public final TextView[] c;
    public int d;
    public float e;
    public float f;
    public ValueAnimator g;
    public IntConsumer h;

    public void super(Ldev/local/newsoul/q2;Landroid/widget/TextView;[Ljava/lang/String;I p0) {
      super(v3);
      new Landroid/widget/FrameLayout(p1.g).d = -0x1;
      new Ldev/local/newsoul/M0(p1.g, p1.c, 0x1);
      new Landroid/widget/FrameLayout(p1.g).a = new Ldev/local/newsoul/M0(p1.g, p1.c, 0x1);
      new Ldev/local/newsoul/M0(p1.g, p1.c, 0x1).setPadding(Ldev/local/newsoul/w2.i(p1.g, 0x4), Ldev/local/newsoul/w2.i(p1.g, 0x4), Ldev/local/newsoul/w2.i(p1.g, 0x4), Ldev/local/newsoul/w2.i(p1.g, 0x4));
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/FrameLayout(p1.g).addView(new Ldev/local/newsoul/M0(p1.g, p1.c, 0x1), new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout(p1.g);
      new Landroid/widget/FrameLayout(p1.g).b = new Landroid/widget/LinearLayout(p1.g);
      new Landroid/widget/LinearLayout(p1.g).setGravity(0x10);
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x2);
      new Ldev/local/newsoul/M0(p1.g, p1.c, 0x1).addView(new Landroid/widget/LinearLayout(p1.g), new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x2));
      Ldev/local/newsoul/w2.v(new Landroid/widget/LinearLayout(p1.g), p2, p3.length);
      new Landroid/widget/FrameLayout(p1.g).c = new TextView[][p3.length];
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {0x10100a1}, new int[][0x0]}, new int[][] {p1.k, p1.i});
      :goto_64:
      if (v7 >= v2.length) goto :cond_f5;
      v0.c[v7] = Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1);
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setGravity(0x11);
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setTextColor(v5);
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setPadding(Ldev/local/newsoul/w2.i(v3, 0xe), Ldev/local/newsoul/w2.i(v3, 0xa), Ldev/local/newsoul/w2.i(v3, 0xe), Ldev/local/newsoul/w2.i(v3, 0xa));
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setMinHeight(Ldev/local/newsoul/w2.i(v3, 0x30));
      Ldev/local/newsoul/w2.i(v3, 0x30).<init>(Ldev/local/newsoul/L2.g(v4, (0x4479c000    # 999.0f * v6.b), v8), 0x0, Ljava/lang/Math.round((v6.b * 0x40400000    # 3.0f)), 0x0, Ljava/lang/Math.round((v6.b * 0x40400000    # 3.0f)));
      if (v6.a == 0) goto :cond_cb;
      goto :goto_ce;
      :cond_cb:
      :goto_ce:
      new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x18ffffff), 0x0, Ldev/local/newsoul/w2.i(v3, 0x30));
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setForeground(new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x18ffffff), 0x0, Ldev/local/newsoul/w2.i(v3, 0x30)));
      Ldev/local/newsoul/w2.w(Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1), v7);
      new Ldev/local/newsoul/x0(v0, v7, 0x1);
      Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1).setOnClickListener(new Ldev/local/newsoul/x0(v0, v7, 0x1));
      new Landroid/widget/LinearLayout$LayoutParams(v8, v4, 0x3f800000    # 1.0f);
      v0.b.addView(Ldev/local/newsoul/w2.n(v3, v2[v7], p4, v1), new Landroid/widget/LinearLayout$LayoutParams(v8, v4, 0x3f800000    # 1.0f));
      goto :goto_64;
      :cond_f5:
      return;
    }

    public final void a(boolean p0) {
      p0.g = 0x0;
      if (p0.g == 0) goto :cond_b;
      p0.g.cancel();
      :cond_b:
      if (p0.d >= 0) goto :cond_16;
      goto :goto_25;
      :cond_16:
      :goto_25:
      if (p0.d >= 0) goto :cond_2b;
      goto :goto_33;
      :cond_2b:
      :goto_33:
      if (p1 == 0) goto :cond_7f;
      if (p0.isAttachedToWindow() == 0) goto :cond_7f;
      if (p0.isAttachedToWindow() == 0) goto :cond_7f;
      if (compare(p0.f, 0x0) <= 0) goto :cond_7f;
      if (compare(0x0, 0x0) > 0) goto :cond_4c;
      goto :goto_7f;
      :cond_4c:
      new float[][0x2] = /* array-data :array_88 */;
      p0.g = Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]);
      new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).setInterpolator(new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f));
      new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f).<init>(p0, p0.e, 0x0, p0.f, 0x0);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addUpdateListener(new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f));
      new Ldev/local/newsoul/B1(p0, 0x0);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addListener(new Ldev/local/newsoul/B1(p0, 0x0));
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).start();
      return;
      :cond_7f:
      :goto_7f:
      p0.e = 0x0;
      p0.f = 0x0;
      p0.a.c(0x0, 0x0, 0x0);
      return;
      :array_88:

      // .array-data 4
      // 0x0
      // 0x3f800000    # 1.0f
    }

    public final void b(Ljava/lang/String;[Ljava/lang/String;Z p0) {
      :goto_2:
      if (v1 >= p2.length) goto :cond_11;
      if (p2[v1].equals(p1) == 0) goto :cond_e;
      goto :goto_12;
      :cond_e:
      goto :goto_2;
      :cond_11:
      :goto_12:
      if ((v1 + 0x1) != p0.d) goto :cond_17;
      return;
      :cond_17:
      p0.d = (v1 + 0x1);
      :goto_1a:
      if (p0.d >= p0.c.length) goto :cond_2e;
      if (p0.d != p0.d) goto :cond_27;
      goto :goto_28;
      :cond_27:
      :goto_28:
      p0.c[p0.d].setSelected(0x1);
      goto :goto_1a;
      :cond_2e:
      p0.a(p3);
      return;
    }

    public final void onDetachedFromWindow() {
      p0.g = 0x0;
      if (p0.g == 0) goto :cond_a;
      p0.g.cancel();
      :cond_a:
      super.onDetachedFromWindow();
      return;
    }

    public final void onLayout(ZIIII p0) {
      p0.onLayout(p1, p2, p3, p4, p5);
      if (p0.g == 0) goto :cond_9;
      if (p1 == 0) goto :cond_d;
      :cond_9:
      p0.a(0x0);
      :cond_d:
      return;
    }

}