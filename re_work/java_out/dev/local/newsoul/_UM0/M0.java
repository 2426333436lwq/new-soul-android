package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/M0 ====
// super: Landroid/widget/FrameLayout
// source: "SourceFile"

public final  class M0 extends Landroid/widget/FrameLayout {

    public final boolean a;
    public final boolean b;
    public final L2 c;
    public final Paint d;
    public final RectF e;
    public final RectF f;
    public final Path g;
    public final float h;
    public final LinearGradient i;
    public final LinearGradient j;
    public final Matrix k;
    public float l;
    public float m;
    public float n;
    public final K0 o;
    public final Paint p;
    public final U2 q;
    public ValueAnimator r;
    public boolean s;

    public void super(Landroid/content/Context;Ldev/local/newsoul/L2;Z p0) {
      super(v3);
      p0.<init>(p1);
      p0.d = new Landroid/graphics/Paint(0x3);
      new Landroid/graphics/RectF();
      p0.e = new Landroid/graphics/RectF();
      new Landroid/graphics/RectF();
      p0.f = new Landroid/graphics/RectF();
      new Landroid/graphics/Path();
      p0.g = new Landroid/graphics/Path();
      new Landroid/graphics/Matrix();
      p0.k = new Landroid/graphics/Matrix();
      new Ldev/local/newsoul/K0();
      p0.o = new Ldev/local/newsoul/K0();
      new Landroid/graphics/Paint(0x1);
      p0.p = new Landroid/graphics/Paint(0x1);
      new Ldev/local/newsoul/U2(0x4, p0);
      p0.q = new Ldev/local/newsoul/U2(0x4, p0);
      p0.b = p3;
      p0.c = p2;
      p0.a = p2.a;
      p0.h = p0.getResources().getDisplayMetrics().density;
      if (p2.a == 0) goto :cond_63;
      :goto_61:
      goto :goto_67;
      :cond_63:
      goto :goto_61;
      :goto_67:
      if (p2.a == 0) goto :cond_6e;
      :goto_6c:
      goto :goto_72;
      :cond_6e:
      goto :goto_6c;
      :goto_72:
      p0.getResources().getDisplayMetrics().density.<init>(0x0, 0x0, 0x0, 0x3f800000    # 1.0f, v7, p2.a, Shader$TileMode.CLAMP);
      p0.i = p0.getResources().getDisplayMetrics().density;
      if (p2.a == 0) goto :cond_9b;
      new int[][0x3][0x0] = 0x24ffffff;
      new int[][0x3][0x1] = 0x4ffffff;
      new int[][0x3][0x2] = 0x12ffffff;
      :goto_98:
      goto :goto_ad;
      :cond_9b:
      new int[][0x3][0x0] = -0x4c000001;
      new int[][0x3][0x1] = 0xc55555d;
      new int[][0x3][0x2] = 0x1855555d;
      goto :goto_98;
      :goto_ad:
      new float[][0x3] = /* array-data :array_dc */;
      -0x1e1c16.<init>(0x0, 0x0, 0x3f800000    # 1.0f, 0x3f800000    # 1.0f, 0x0, new float[][0x3], Shader$TileMode.CLAMP);
      p0.j = -0x1e1c16;
      if (p2.a == 0) goto :cond_c9;
      goto :goto_cc;
      :cond_c9:
      :goto_cc:
      new Landroid/graphics/PorterDuffColorFilter(-0xff4a5e, PorterDuff$Mode.SRC_IN);
      p0.setWillNotDraw(0x0);
      p0.setImportantForAccessibility(0x2);
      return;
      :array_dc:

      // .array-data 4
      // 0x0
      // 0x3f0ccccd    # 0.55f
      // 0x3f800000    # 1.0f
    }

    public static void a(Canvas p0) {
      p1.drawColor(p0.c.d());
      p0.b(p1);
      if (p0.c.a == 0) goto :cond_16;
      goto :goto_19;
      :cond_16:
      :goto_19:
      p0.d.setColor(-0xdfdfd0);
      p1.drawRoundRect(p0.e, (p0.e.height() / 0x40000000    # 2.0f), (p0.e.height() / 0x40000000    # 2.0f), p0.d);
      if (p0.b == 0) goto :cond_32;
      goto :goto_4b;
      :cond_32:
      super.dispatchDraw(p1);
      p1.restoreToCount(p1.saveLayer(0x0, 0x0, ((float) p0.getWidth()), ((float) p0.getHeight()), p0.p));
      :goto_4b:
      return;
    }

    public final void b(Canvas p0) {
      p1.drawRoundRect(p0.f, (p0.f.height() / 0x40000000    # 2.0f), (p0.f.height() / 0x40000000    # 2.0f), p0.d);
      p0.d.setStyle(Paint$Style.STROKE);
      p0.d.setStrokeWidth((p0.h * 0x3f000000    # 0.5f));
      p1.drawRoundRect(p0.f, (p0.f.height() / 0x40000000    # 2.0f), (p0.f.height() / 0x40000000    # 2.0f), p0.d);
      p0.d.setStyle(Paint$Style.FILL);
      return;
    }

    public final void c(FFZ p0) {
      p0.l = p1;
      p0.m = p2;
      if (p3 == 0) goto :cond_1e;
      if (p0.c.getClass() == 0) goto :cond_1e;
      if (p0.b == 0) goto :cond_1b;
      goto :goto_1f;
      :cond_1b:
      goto :goto_1f;
      :cond_1e:
      :goto_1f:
      if (p0.c.getClass() == 0) goto :cond_67;
      if (p0.isAttachedToWindow() != 0) goto :cond_29;
      goto :goto_67;
      :cond_29:
      if (p0.s == p3) goto :cond_7a;
      p0.s = p3;
      p0.r = 0x0;
      if (p0.r == 0) goto :cond_38;
      p0.r.cancel();
      :cond_38:
      new float[][0x2][0x0] = p0.n;
      new float[][0x2][0x1] = 0x3f800000    # 1.0f;
      p0.r = Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]);
      if (p3 == 0) goto :cond_4c;
      goto :goto_4e;
      :cond_4c:
      :goto_4e:
      new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).setInterpolator(new Landroid/view/animation/DecelerateInterpolator(0x3fc00000    # 1.5f));
      new Ldev/local/newsoul/L0(p0, 0x0);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addUpdateListener(new Ldev/local/newsoul/L0(p0, 0x0));
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).start();
      goto :goto_7a;
      :cond_67:
      :goto_67:
      p0.r = new float[][0x2];
      if (p0.r == 0) goto :cond_70;
      p0.r.cancel();
      :cond_70:
      p0.n = new Ldev/local/newsoul/L0(p0, 0x0);
      if (0x3fc00000    # 1.5f == 0) goto :cond_77;
      if (Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]) == 0) goto :cond_77;
      goto :goto_78;
      :cond_77:
      :goto_78:
      p0.s = 0x1;
      :cond_7a:
      :goto_7a:
      p0.invalidate();
      return;
    }

    public final void dispatchDraw(Canvas p0) {
      if (compare(p0.m, 0x0) > 0) goto :cond_b;
      super.dispatchDraw(p1);
      return;
      :cond_b:
      if (p0.b == 0) goto :cond_38;
      p0.o.a(p1, p0.getWidth(), p0.getHeight(), p0.e, (p0.e.height() / 0x40000000    # 2.0f), p0.n, p0.h, p0.a, p0.q);
      super.dispatchDraw(p1);
      return;
      :cond_38:
      p0.g.reset();
      p0.g.addRoundRect(p0.e, (p0.e.height() / 0x40000000    # 2.0f), (p0.e.height() / 0x40000000    # 2.0f), Path$Direction.CW);
      super.dispatchDraw(p1);
      p1.restoreToCount(p1.save());
      p0.o.a(p1, p0.getWidth(), p0.getHeight(), p0.e, (p0.e.height() / 0x40000000    # 2.0f), p0.n, p0.h, p0.a, p0.q);
      return;
    }

    public final void onDetachedFromWindow() {
      p0.r = 0x0;
      if (p0.r == 0) goto :cond_a;
      p0.r.cancel();
      :cond_a:
      p0.n = 0x0;
      p0.s = 0x0;
      if (p0.o.c == 0) goto :cond_1d;
      Ldev/local/newsoul/H0.g((RenderNode) p0.o.c.a);
      :cond_1d:
      p0.o.c = 0x0;
      p0.o.e = 0x0;
      p0.o.f = 0x0;
      super.onDetachedFromWindow();
      return;
    }

    public final void onDraw(Canvas p0) {
      super.onDraw(p1);
      p0.f.set((((0x40a00000    # 5.0f * p0.n) + 0x3f800000    # 1.0f) * p0.h), (((p0.n * 0x40800000    # 4.0f) + 0x3f800000    # 1.0f) * p0.h), (((float) p0.getWidth()) - (((0x40a00000    # 5.0f * p0.n) + 0x3f800000    # 1.0f) * p0.h)), (((float) p0.getHeight()) - (((p0.n * 0x40800000    # 4.0f) + 0x3f800000    # 1.0f) * p0.h)));
      p0.k.setScale(p0.f.width(), p0.f.height());
      p0.i.setLocalMatrix(p0.k);
      p0.j.setLocalMatrix(p0.k);
      p0.b(p1);
      if (compare(p0.m, 0x0) > 0) goto :cond_4d;
      return;
      :cond_4d:
      p0.e.set(Ljava/lang/Math.max(p0.h, (p0.l - ((0x41100000    # 9.0f * p0.h) * p0.n))), (((float) p0.getPaddingTop()) + ((p0.c.b * 0x40400000    # 3.0f) - ((p0.h * 0x40c00000    # 6.0f) * p0.n))), Ljava/lang/Math.min((((float) p0.getWidth()) - p0.h), ((p0.l + p0.m) + ((0x41100000    # 9.0f * p0.h) * p0.n))), (((float) (p0.getHeight() - p0.getPaddingBottom())) - ((p0.c.b * 0x40400000    # 3.0f) - ((p0.h * 0x40c00000    # 6.0f) * p0.n))));
      return;
    }

}