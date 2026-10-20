package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Z0 ====
// super: Landroid/widget/FrameLayout
// source: "SourceFile"

public final  class Z0 extends Landroid/widget/FrameLayout {

    public boolean A;
    public boolean B;
    public float C;
    public float D;
    public final k0 a;
    public final ViewGroup[] b;
    public final int c;
    public final int d;
    public final int e;
    public int f;
    public int g;
    public int h;
    public float i;
    public boolean j;
    public boolean k;
    public boolean l;
    public float m;
    public float n;
    public final float[] o;
    public final float[] p;
    public final boolean[] q;
    public ValueAnimator r;
    public VelocityTracker s;
    public int t;
    public long u;
    public float v;
    public float w;
    public float x;
    public boolean y;
    public boolean z;

    public void super(k0 p0) {
      super(p1);
      this.f = -0x1;
      this.g = -0x1;
      this.h = -0x1;
      this.t = -0x1;
      this.a = p2;
      this.b = new ViewGroup[][0x5];
      this.o = new float[][0x5];
      this.p = new float[][0x5];
      this.q = new boolean[][0x5];
      this.c = Landroid/view/ViewConfiguration.get(p1).getScaledTouchSlop();
      this.d = Ljava/lang/Math.max(Landroid/view/ViewConfiguration.get(p1).getScaledMinimumFlingVelocity(), Ljava/lang/Math.round((new Landroid/widget/FrameLayout(p1).getResources().getDisplayMetrics().density * 0x43c80000    # 400.0f)));
      this.e = Landroid/view/ViewConfiguration.get(p1).getScaledMaximumFlingVelocity();
      new Landroid/widget/FrameLayout(p1).setClipChildren(0x1);
      return;
    }

    public static boolean h(Landroid/view/View;FF p0) {
      if (p0.getVisibility() != 0) goto :cond_9b;
      if (compare(p1, 0x0) < 0) goto :cond_9b;
      if (compare(p2, 0x0) < 0) goto :cond_9b;
      if (compare(p1, ((float) p0.getWidth())) >= 0) goto :cond_9b;
      if (compare(p2, ((float) p0.getHeight())) < 0) goto :cond_24;
      goto :goto_9b;
      :cond_24:
      if ((p0 instanceof CompoundButton) == 0) goto :cond_2d;
      if ((p0 instanceof CheckBox) == 0) goto :cond_9a;
      :cond_2d:
      if ((p0 instanceof AbsSpinner) != 0) goto :cond_9a;
      if ((p0 instanceof EditText) != 0) goto :cond_9a;
      if ((p0 instanceof HorizontalScrollView) != 0) goto :cond_9a;
      if ((p0 instanceof AbsSeekBar) != 0) goto :cond_9a;
      if ((p0 instanceof WebView) != 0) goto :cond_9a;
      if ((p0 instanceof TextView) == 0) goto :cond_4e;
      if ((TextView) p0.isTextSelectable() != 0) goto :cond_9a;
      :cond_4e:
      if (p0.canScrollHorizontally(-0x1) != 0) goto :cond_9a;
      if (p0.canScrollHorizontally(0x1) == 0) goto :cond_5c;
      goto :goto_9a;
      :cond_5c:
      if ((p0 instanceof ViewGroup) == 0) goto :cond_99;
      :goto_67:
      if ((p0 instanceof ViewGroup) < 0) goto :cond_99;
      if (Ldev/local/newsoul/Z0.h(p0.getChildAt((p0 instanceof ViewGroup)), (((((float) p0.getScrollX()) + p1) - ((float) p0.getChildAt((p0 instanceof ViewGroup)).getLeft())) - p0.getChildAt((p0 instanceof ViewGroup)).getTranslationX()), (((((float) p0.getScrollY()) + p2) - ((float) p0.getChildAt((p0 instanceof ViewGroup)).getTop())) - p0.getChildAt((p0 instanceof ViewGroup)).getTranslationY())) == 0) goto :cond_96;
      return (p0 instanceof ViewGroup);
      :cond_96:
      goto :goto_67;
      :cond_99:
      return ((p0 instanceof ViewGroup) + -0x1);
      :cond_9a:
      :goto_9a:
      return ((p0 instanceof ViewGroup) + -0x1);
      :cond_9b:
      :goto_9b:
      return ((p0 instanceof ViewGroup) + -0x1);
    }

    public final void a() {
      p0.r = 0x0;
      if (p0.r == 0) goto :cond_a;
      p0.r.cancel();
      :cond_a:
      return;
    }

    public final void b() {
      if (p0.s == 0) goto :cond_7;
      p0.s.recycle();
      :cond_7:
      p0.s = 0x0;
      p0.t = -0x1;
      p0.B = 0x0;
      p0.A = 0x0;
      p0.z = 0x0;
      p0.y = 0x0;
      p0.x = 0x0;
      return;
    }

    public final void c(int p0) {
      p0.a();
      p0.g(p1);
      if (p0.f == p1) goto :cond_e;
      goto :goto_f;
      :cond_e:
      :goto_f:
      p0.h = p1;
      p0.g = p1;
      p0.f = p1;
      p0.i = 0x0;
      p0.k = 0x0;
      p0.j = 0x0;
      p0.j();
      if (0x1 == 0) goto :cond_79;
      (v2) p0.a.a.i = p1;
      (v2) p0.a.a.b[p1].e();
      :goto_31:
      if (0x0 >= 0x1.d.length) goto :cond_76;
      if (0x0 != p1) goto :cond_3c;
      goto :goto_3d;
      :cond_3c:
      :goto_3d:
      0x1.d[0x0].setSelected(0x1);
      if (0x0 != p1) goto :cond_62;
      if (0x1.a.b == 0) goto :cond_4d;
      goto :goto_50;
      :cond_4d:
      :goto_50:
      if (0x1.a.b == 0) goto :cond_56;
      goto :goto_59;
      :cond_56:
      :goto_59:
      goto :goto_64;
      :cond_62:
      :goto_64:
      0x1.d[0x0].setTextColor(Ldev/local/newsoul/w2.q(w2.b, 0x1.a.b, new int[][] {-0xdebabe}));
      if (0x0 != p1) goto :cond_6f;
      goto :goto_70;
      :cond_6f:
      :goto_70:
      0x1.d[0x0].setTypeface(Typeface.DEFAULT, 0x1);
      goto :goto_31;
      :cond_76:
      0x1.d();
      :cond_79:
      return;
    }

    public final void d() {
      if (p0.t < 0) goto :cond_6;
      goto :goto_7;
      :cond_6:
      :goto_7:
      if (0x1 == 0) goto :cond_25;
      :try_start_19:
      :try_end_1c:
      v1.recycle();
      goto :goto_25;
      :catchall_20:
      v1.recycle();
      throw e;
      :cond_25:
      :goto_25:
      if (p0.f >= 0) goto :cond_2a;
      goto :goto_44;
      :cond_2a:
      if (p0.y == 0) goto :cond_2f;
      goto :goto_3c;
      :cond_2f:
      if (compare(p0.i, 0x3f000000    # 0.5f) < 0) goto :cond_3a;
      goto :goto_3c;
      :cond_3a:
      :goto_3c:
      p0.a();
      if (p0.h < 0) goto :cond_44;
      p0.c(p0.h);
      :cond_44:
      :goto_44:
      p0.b();
      p0.A = 0x1;
      return;

      // .catchall {:try_start_19 .. :try_end_1c} :catchall_20
    }

    public final boolean dispatchTouchEvent(MotionEvent p0) {
      if (p1.getActionMasked() != 0) goto :cond_78;
      if (p0.r != 0) goto :cond_19;
      if (p0.g != p0.h) goto :cond_19;
      if (p0.k == 0) goto :cond_17;
      goto :goto_19;
      :cond_17:
      goto :goto_1a;
      :cond_19:
      :goto_19:
      :goto_1a:
      p0.a();
      p0.b();
      p0.B = 0x0;
      p0.C = p0.i;
      if (p0.k == 0) goto :cond_35;
      goto :goto_3e;
      :cond_35:
      :goto_3e:
      p0.D = Ljava/lang/Math.signum((((float) p0.h) - p0.n));
      if (compare(Ljava/lang/Math.signum((((float) p0.h) - p0.n)), 0x0) != 0) goto :cond_49;
      p0.D = 0x3f800000    # 1.0f;
      :cond_49:
      p0.t = p1.getPointerId(0x0);
      p0.u = p1.getDownTime();
      p0.v = p1.getX();
      p0.w = p1.getY();
      if (p0.B != 0) goto :cond_6f;
      if (Ldev/local/newsoul/Z0.h(p0, p0.v, p1.getY()) == 0) goto :cond_6f;
      goto :goto_70;
      :cond_6f:
      :goto_70:
      p0.z = 0x1;
      p0.s = Ldev/local/newsoul/Z0.h(p0, p0.v, p1.getY());
      :cond_78:
      if (p0.s == 0) goto :cond_7f;
      p0.s.addMovement(p1);
      :cond_7f:
      if (p1.getActionMasked() != 0x5) goto :cond_95;
      if (p0.y != 0) goto :cond_8e;
      if (p0.B == 0) goto :cond_8b;
      goto :goto_8e;
      :cond_8b:
      p0.z = 0x1;
      goto :goto_95;
      :cond_8e:
      :goto_8e:
      p0.y = 0x0;
      p0.A = 0x1;
      p0.k(0x0);
      :cond_95:
      :goto_95:
      if (p0.A == 0) goto :cond_a2;
      if (p1.getActionMasked() == 0x1) goto :cond_9e;
      if (p1.getActionMasked() != 0x3) goto :cond_a1;
      :cond_9e:
      p0.b();
      :cond_a1:
      return p1.getActionMasked();
      :cond_a2:
      if (p1.getActionMasked() == 0x1) goto :cond_aa;
      if (p1.getActionMasked() != 0x3) goto :cond_ad;
      :cond_aa:
      p0.b();
      :cond_ad:
      return p1.getActionMasked();
    }

    public final void e(MotionEvent p0) {
      if (p0.y != 0) goto :cond_86;
      if (p0.z != 0) goto :cond_86;
      if (p0.t < 0) goto :cond_86;
      if (p1.getPointerCount() == 0x1) goto :cond_15;
      goto :goto_86;
      :cond_15:
      if (p1.findPointerIndex(p0.t) >= 0) goto :cond_20;
      p0.z = 0x1;
      return;
      :cond_20:
      if (compare(Ljava/lang/Math.abs((p1.getY(p1.findPointerIndex(p0.t)) - p0.w)), ((float) p0.c)) <= 0) goto :cond_49;
      if (compare(Ljava/lang/Math.abs((p1.getY(p1.findPointerIndex(p0.t)) - p0.w)), (0x3f4ccccd    # 0.8f * Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)))) < 0) goto :cond_49;
      :goto_47:
      goto :goto_63;
      :cond_49:
      if (compare(Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)), ((float) p0.c)) <= 0) goto :cond_56;
      if (compare(Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)), (0x3fc00000    # 1.5f * 0x2)) <= 0) goto :cond_56;
      goto :goto_63;
      :cond_56:
      if (compare(Ljava/lang/Math.max(Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)), 0x1), (((float) p0.c) * 0x40000000    # 2.0f)) <= 0) goto :cond_62;
      goto :goto_47;
      :cond_62:
      :goto_63:
      if (compare(Ljava/lang/Math.max(Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)), 0x1), (((float) p0.c) * 0x40000000    # 2.0f)) != 0x2) goto :cond_67;
      p0.z = 0x1;
      :cond_67:
      if (compare(Ljava/lang/Math.max(Ljava/lang/Math.abs((p1.getX(p1.findPointerIndex(p0.t)) - p0.v)), 0x1), (((float) p0.c) * 0x40000000    # 2.0f)) == 0x1) goto :cond_6a;
      return;
      :cond_6a:
      p0.y = 0x1;
      if (p0.B != 0) goto :cond_76;
      p0.g = p0.f;
      p0.j = 0x0;
      :cond_76:
      if (p0.getParent() == 0) goto :cond_83;
      p0.getParent().requestDisallowInterceptTouchEvent(0x1);
      :cond_83:
      p0.f((p1.getX(p1.findPointerIndex(p0.t)) - p0.v));
      :cond_86:
      :goto_86:
      return;
    }

    public final void f(float p0) {
      p0.x = p1;
      if (p0.B == 0) goto :cond_34;
      if (p0.j == 0) goto :cond_16;
      goto :goto_17;
      :cond_16:
      :goto_17:
      if (compare((((float) p0.getWidth()) * 0x3e3851ec    # 0.18f), 0x0) != 0) goto :cond_1f;
      goto :goto_2e;
      :cond_1f:
      :goto_2e:
      p0.i = p0.C;
      p0.j();
      return;
      :cond_34:
      if (compare(p0.C, 0x0) != 0) goto :cond_3e;
      goto :goto_50;
      :cond_3e:
      if (compare(p0.C, 0x0) >= 0) goto :cond_45;
      goto :goto_46;
      :cond_45:
      :goto_46:
      :goto_50:
      p0.h = p0.f;
      p0.g(p0.f);
      if (p0.h == p0.f) goto :cond_70;
      if (p0.getWidth() != 0) goto :cond_62;
      goto :goto_70;
      :cond_62:
      :cond_70:
      :goto_70:
      p0.i = 0x0;
      p0.j();
      return;
    }

    public final void g(int p0) {
      if (p0.b[p1] != 0) goto :cond_930;
      if ((v2) p0.a.a.b[p1] != 0) goto :cond_918;
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).q = 0x1;
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.c = 0x1;
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).h.setClipChildren(0x0);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).h.setClipToPadding(0x0);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).o = (v2) p0.a.a.a;
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).e = (v2) p0.a.a.a.e;
      (v2) p0.a.a.b[p1] = new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b);
      if (p1 == 0) goto :cond_66a;
      if (p1 == 0x1) goto :cond_42f;
      if (p1 == 0x2) goto :cond_39d;
      if (p1 == 0x3) goto :cond_193;
      :try_start_6b:
      :try_end_6d:
      :try_start_70:
      new Ljava/lang/StringBuilder(0x3);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ljava/lang/StringBuilder(0x3).toString(), 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i));
      :try_end_98:
      goto :goto_a8;
      :catch_9b:
      :catch_9d:
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "版本信息暂不可用", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j));
      :goto_a8:
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "检查更新", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x4);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "检查更新", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x4));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "功能兼容性检测", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "功能兼容性检测", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setContentDescription("检测当前 Soul 版本的功能兼容性");
      new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x1);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "功能兼容性检测", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x1));
      Ldev/local/newsoul/w2.a(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l, Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "检查更新", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "功能兼容性检测", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "加入 TG 群组", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "加入 TG 群组", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setContentDescription("加入 TG 群组，打开 t.me 链接");
      new Ldev/local/newsoul/A0(0x2, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "加入 TG 群组", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/A0(0x2, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "支持作者", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x2);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "支持作者", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x2));
      Ldev/local/newsoul/w2.a(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "交流与支持", 0x0, 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "加入 TG 群组", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "支持作者", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/m(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, 0x4);
      Ldev/local/newsoul/w2.e(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "运行选项", 0x0, 0x0).d(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c, "startup_toast", "显示启动提示", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/m(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, 0x4));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "调试", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x3);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "调试", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/N1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).b, 0x3));
      Ldev/local/newsoul/w2.x(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "调试", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "运行选项", 0x0, 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "调试", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      Ldev/local/newsoul/w2.a(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/Q(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k));
      new Ldev/local/newsoul/I(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/Q(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k)), 0x1);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/I(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/Q(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k)), 0x1));
      new Ldev/local/newsoul/I(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/Q(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k)), 0x0);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k).setOnClickListener(new Ldev/local/newsoul/I(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/Q(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导出配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "导入配置", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).k)), 0x0));
      :cond_18f:
      goto :goto_8fd;
      :cond_193:
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).t = "勾选表示屏蔽对应广告。点击分组旁的更多按钮或长按分组标题，可屏蔽或取消屏蔽本组；未就绪项会跳过。已显示的广告可能需重新进入页面或重启 Soul 后消失。";
      new Ljava/util/HashMap();
      :goto_1f1:
      if (new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).h >= 0x13) goto :cond_201;
      goto :goto_1f1;
      :cond_201:
      :goto_206:
      if (p0.b >= (new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).h + 0x1)) goto :cond_18f;
      new Ljava/util/ArrayList();
      new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList());
      if ((v2) p0.a.a.c.equals(0xf[p0.b][0x0]) == 0) goto :cond_22a;
      :cond_22a:
      :goto_22b:
      if ((v2) p0.a.a.c.equals(0xf[p0.b][0x0]) >= 0xf[p0.b].length) goto :cond_36f;
      new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]));
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setGravity(0x10);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setMinimumHeight(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x34));
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g);
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setTextSize(0x41800000    # 16.0f);
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][0x0]}, new int[][] {new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i});
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setTextColor(new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][0x0]}, new int[][] {new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i}));
      if (new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.a == 0) goto :cond_2a2;
      goto :goto_2a5;
      :cond_2a2:
      :goto_2a5:
      if (new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.a == 0) goto :cond_2af;
      goto :goto_2b2;
      :cond_2af:
      :goto_2b2:
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0x9d9d91, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.a(), -0x7d7d6a});
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setButtonTintList(new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0x9d9d91, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.a(), -0x7d7d6a}));
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.b(new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), 0xc, Ldev/local/newsoul/L2.e(0xc));
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setGravity(0x800013);
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setMinHeight(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x34));
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setPadding(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x4), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x4), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x8), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x4));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).addView(new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      if ((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x2].isEmpty() != 0) goto :cond_30c;
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).addView(Ldev/local/newsoul/w2.l(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x1], (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x2]));
      goto :goto_30d;
      :cond_30c:
      :goto_30d:
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())).addView(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/g2((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x0], (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x1], new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])));
      if ((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))) != 0) goto :cond_331;
      new Ldev/local/newsoul/h2(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())));
      :cond_331:
      (h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).a.<init>(new Ldev/local/newsoul/g2((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x0], (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x1], new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]))), new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])), new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), p0, (h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x3);
      new Ldev/local/newsoul/Q1(new Ldev/local/newsoul/g2((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x0], (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x1], new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]))), new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])), (h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).a, p0);
      new Landroid/widget/CheckBox(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setOnCheckedChangeListener(new Ldev/local/newsoul/Q1(new Ldev/local/newsoul/g2((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x0], (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])[0x1], new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]))), new Ldev/local/newsoul/L1((String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])])), (h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).a, p0));
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).a);
      (h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).a.run();
      goto :goto_22b;
      :cond_36f:
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())).removeView((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).e);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())).removeView((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).d);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())).addView((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).e);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList())).addView((h2) new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).l.get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).d);
      goto :goto_206;
      :cond_39d:
      new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x1);
      Ldev/local/newsoul/u1.a(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "插眼列表").setOnClickListener(new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x1));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "主页插眼", "在用户主页打开右上角菜单，点击「插眼」。\n\n列表中点击昵称打开主页，长按记录可移除。记录保存在本机，按当前登录账号分别保存；不会关注对方。重新打开主页仍遵循 Soul 原有的访问行为。", 0x0).addView(Ldev/local/newsoul/u1.a(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "插眼列表"), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      if (u1.f != 0) goto :cond_3da;
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "主页插眼", "在用户主页打开右上角菜单，点击「插眼」。\n\n列表中点击昵称打开主页，长按记录可移除。记录保存在本机，按当前登录账号分别保存；不会关注对方。重新打开主页仍遵循 Soul 原有的访问行为。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "当前版本的主页插眼入口尚未就绪", 0xd, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j));
      :cond_3da:
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setGravity(0x10);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setMinHeight(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x30));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setPadding(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xc), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xc), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa));
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).c.b(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), 0xc, Ldev/local/newsoul/L2.e(0xc));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i));
      new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x0);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setOnClickListener(new Ldev/local/newsoul/s0(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0x0));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "个人主页", 0x0, 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "信息伪装", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      goto :goto_8fd;
      :cond_42f:
      if (B2.i == 0) goto :cond_44c;
      goto :goto_44e;
      :cond_44c:
      :goto_44e:
      new Ldev/local/newsoul/H1(0x0);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_filter_enabled", "按性别筛选", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x0));
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setOrientation(0x1);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setTag(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).addView(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setPadding(0x0, Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xc), 0x0, Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa));
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i));
      new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).addView(new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      "不限包含性别未知的帖子。".<init>(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf), new String[][] {"ANY", "FEMALE", "MALE"}, (View) new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_filter_keep_unknown", "保留性别未知的帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent(), Ldev/local/newsoul/w2.c(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), "不限包含性别未知的帖子。"), 0x2);
      new Ldev/local/newsoul/P1(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), new String[][] {"ANY", "FEMALE", "MALE"}, new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf), "不限包含性别未知的帖子。");
      new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf).h = new Ldev/local/newsoul/P1(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), new String[][] {"ANY", "FEMALE", "MALE"}, new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, "显示性别", 0x10, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"不限", "仅女生", "仅男生"}, 0xf), "不限包含性别未知的帖子。");
      "不限包含性别未知的帖子。".run();
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a("不限包含性别未知的帖子。");
      new Ldev/local/newsoul/g(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_filter_enabled", "按性别筛选", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), 0xa);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_filter_enabled", "按性别筛选", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).a(new Ldev/local/newsoul/g(new Landroid/widget/LinearLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_filter_enabled", "按性别筛选", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), 0xa));
      if (B2.a == 0) goto :cond_50c;
      if (B2.i == 0) goto :cond_50c;
      goto :goto_510;
      :cond_50c:
      :goto_510:
      new Ldev/local/newsoul/H1(0x3);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "feed_keywords_enabled_v1", "启用屏蔽词过滤", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x3));
      new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x6);
      new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x6).run();
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a(new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x6));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).setGravity(0x10);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).setMinHeight(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x30));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/l1(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa), (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]), new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x6));
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))).setOnClickListener(new Ldev/local/newsoul/l1(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa), (String[]) 0x3.get(0xf[p0.b][(v2) p0.a.a.c.equals(0xf[p0.b][0x0])]), new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, 0x10, Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), 0xf[p0.b][0x0], 0x0, new Ldev/local/newsoul/K1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), p0, new Ljava/util/ArrayList()))), 0x6)));
      new Ldev/local/newsoul/H1(0x4);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_auto_like", "自动点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x4));
      new Ldev/local/newsoul/H1(0x5);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_visible_like", "一键点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x5));
      new Ldev/local/newsoul/H1(0x6);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x6));
      (LinearLayout$LayoutParams) (View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent().getLayoutParams().leftMargin = Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0x10);
      (View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent().setLayoutParams((LinearLayout$LayoutParams) (View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent().getLayoutParams());
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).removeView((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent());
      new Landroid/widget/FrameLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g);
      new Landroid/widget/FrameLayout$LayoutParams((ViewGroup$MarginLayoutParams) (View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent().getLayoutParams());
      new Landroid/widget/FrameLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).addView((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent(), new Landroid/widget/FrameLayout$LayoutParams((ViewGroup$MarginLayoutParams) (View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent().getLayoutParams()));
      new Landroid/widget/FrameLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g).setClipChildren(0x1);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).addView(new Landroid/widget/FrameLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).indexOfChild((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent()), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).n.<init>(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_auto_like", "自动点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_visible_like", "一键点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), 0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).indexOfChild((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent()).<init>(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_auto_like", "自动点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_visible_like", "一键点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Landroid/widget/FrameLayout(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g), Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).n, 0x1);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_auto_like", "自动点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).a(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).indexOfChild((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent()));
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_visible_like", "一键点赞", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).a(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).indexOfChild((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent()));
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).a(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).indexOfChild((View) Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "square_like_all_loaded", "包含屏幕外已加载帖子", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).getParent()));
      new Ldev/local/newsoul/H1(0x7);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "download_image_no_watermark", "图片无水印", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x7));
      new Ldev/local/newsoul/H1(0x1);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "download_video_no_watermark", "视频无水印", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x1));
      new Ldev/local/newsoul/H1(0x2);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)), "post_remove_share_limit", "去除分享限制", "", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x2));
      goto :goto_8fd;
      :cond_66a:
      if (t.a == 0) goto :cond_68b;
      goto :goto_68d;
      :cond_68b:
      :goto_68d:
      new Ldev/local/newsoul/H1(0x8);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天小工具", "骰子与猜拳\n私聊发送前选择结果；预览不会发送消息。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "game_dice", "自定义骰子", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x8));
      new Ldev/local/newsoul/m(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0), 0x2);
      Ldev/local/newsoul/w2.e(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天小工具", "骰子与猜拳\n私聊发送前选择结果；预览不会发送消息。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "game_dice", "自定义骰子", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/m(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0), 0x2));
      if (t.b == 0) goto :cond_6b7;
      goto :goto_6b9;
      :cond_6b7:
      :goto_6b9:
      new Ldev/local/newsoul/H1(0xf);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天小工具", "骰子与猜拳\n私聊发送前选择结果；预览不会发送消息。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "game_finger", "自定义猜拳", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0xf));
      new Ldev/local/newsoul/m(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0), 0x3);
      Ldev/local/newsoul/w2.e(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天小工具", "骰子与猜拳\n私聊发送前选择结果；预览不会发送消息。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "game_finger", "自定义猜拳", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/m(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0), 0x3));
      if (Q0.k == 0) goto :cond_6ea;
      goto :goto_6ec;
      :cond_6ea:
      :goto_6ec:
      new Ldev/local/newsoul/H1(0x10);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_anti_recall", "聊天防撤回", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x10));
      new Ldev/local/newsoul/g(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), 0x8);
      Ldev/local/newsoul/w2.e(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_anti_recall", "聊天防撤回", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), new Ldev/local/newsoul/g(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), 0x8));
      new Landroid/widget/LinearLayout("");
      new Landroid/widget/LinearLayout("").setOrientation(0x1);
      new Landroid/widget/LinearLayout("").setTag(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).addView(new Landroid/widget/LinearLayout(""), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).setPadding(0x0, Ldev/local/newsoul/w2.i("", 0xc), 0x0, Ldev/local/newsoul/w2.i("", 0x8));
      new Landroid/widget/LinearLayout("").addView(Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i));
      new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout("").addView(new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/H(new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd), Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), new String[][] {"below", "nickname"}, 0xa);
      new Ldev/local/newsoul/R1(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), new String[][] {"below", "nickname"}, new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd));
      new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd).h = new Ldev/local/newsoul/R1(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), new String[][] {"below", "nickname"}, new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd));
      new Ldev/local/newsoul/H(new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd), Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), new String[][] {"below", "nickname"}, 0xa).run();
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a(new Ldev/local/newsoul/H(new Ldev/local/newsoul/C1(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.n("", "撤回标记样式", 0xf, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new String[][] {"气泡下方小字", "昵称旁标签"}, 0xd), Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), new String[][] {"below", "nickname"}, 0xa));
      new Ldev/local/newsoul/g(new Landroid/widget/LinearLayout(""), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_anti_recall", "聊天防撤回", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), 0xa);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_anti_recall", "聊天防撤回", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i).a(new Ldev/local/newsoul/g(new Landroid/widget/LinearLayout(""), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_anti_recall", "聊天防撤回", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), 0xa));
      if (g1.a == 0) goto :cond_782;
      goto :goto_784;
      :cond_782:
      :goto_784:
      new Ldev/local/newsoul/H1(0x11);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_own_recall_no_time_limit", "撤回不受两分钟限制", "仅移除自己消息的本地时间检查，实际能否撤回以服务器处理为准。".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x11));
      if (Q0.e == 0) goto :cond_7b1;
      goto :goto_7b3;
      :cond_7b1:
      :goto_7b3:
      new Ldev/local/newsoul/H1(0x12);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天限制", "聊天限制修改后重新进入会话；实际发送仍以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_remove_gift_gate", "去除送礼聊天限制", "移除已适配的本地送礼开聊与消息额度门槛。".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x12));
      if (Q0.f == 0) goto :cond_7d8;
      goto :goto_7da;
      :cond_7d8:
      :goto_7da:
      new Ldev/local/newsoul/H1(0x13);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天限制", "聊天限制修改后重新进入会话；实际发送仍以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_remove_bell_gift_gate", "去除奇遇铃送礼限制", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x13));
      if (Q0.U == 0) goto :cond_7fc;
      if (Q0.V == 0) goto :cond_7fc;
      goto :goto_7fe;
      :cond_7fc:
      :goto_7fe:
      new Ldev/local/newsoul/H1(0x14);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天限制", "聊天限制修改后重新进入会话；实际发送仍以服务器处理为准。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_remove_social_score_limit", "去除礼仪分限制", "解除本地礼仪分开聊、等待回复、图片和通话限制及相关弹窗，不修改实际礼仪分。".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x14));
      if (Q0.b == 0) goto :cond_829;
      goto :goto_82b;
      :cond_829:
      :goto_82b:
      new Ldev/local/newsoul/H1(0x9);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天与匹配", 0x0, 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "chat_hide_typing", "禁止上报输入状态", "不向对方发送正在输入或结束输入状态。".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0x9));
      if (Q0.c == 0) goto :cond_84f;
      goto :goto_851;
      :cond_84f:
      :goto_851:
      new Ldev/local/newsoul/H1(0xa);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "聊天与匹配", 0x0, 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "match_mute_background_music", "关闭匹配背景音乐", "下次进入匹配生效，保留匹配振动。".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0xa));
      if (Q0.P == 0) goto :cond_87e;
      goto :goto_880;
      :cond_87e:
      :goto_880:
      new Ldev/local/newsoul/H1(0xb);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "privacy_allow_download", "允许下载", "保存目录：\n图片：Pictures/New Soul\n视频：Movies/New Soul".concat(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0xb));
      new Ldev/local/newsoul/H1(0xc);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "privacy_allow_screenshot", "允许截屏", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j, new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0xc));
      new Ldev/local/newsoul/H1(0xd);
      Ldev/local/newsoul/w2.f(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).d(Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", 0x0)), "privacy_allow_repeat", "允许多次查看", "查看时不扣减本地次数，也不向对方发送已阅状态。", new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i), new Ldev/local/newsoul/H1(0xd));
      new Ldev/local/newsoul/H1(0xe);
      new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), (h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)), 0x7);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).a(new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), (h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)), 0x7));
      new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0), new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).j), (h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)), 0x7).run();
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).removeView((h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)).e);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).removeView((h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)).d);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).addView((h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)).e);
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0).addView((h2) Ldev/local/newsoul/w2.o(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).g, 0xa)).get(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b), "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", 0x0)).d);
      :goto_8fd:
      new Landroid/widget/ScrollView(-0x2.g);
      new Landroid/widget/ScrollView(-0x2.g).setFillViewport(0x0);
      new Landroid/widget/ScrollView(-0x2.g).setClipToPadding(0x0);
      new Landroid/widget/ScrollView(-0x2.g).addView(0x1);
      "post_remove_share_limit"[p1] = new Landroid/widget/ScrollView(-0x2.g);
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).e();
      goto :goto_91c;
      :cond_918:
      :goto_91c:
      new Ldev/local/newsoul/q2((v2) p0.a.a.a.a, (v2) p0.a.a.a.b).i[p1] = "post_remove_share_limit"[p1];
      "post_remove_share_limit"[p1].setVisibility(0x4);
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x1);
      p0.addView("post_remove_share_limit"[p1], new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x1));
      goto :goto_933;
      :cond_930:
      :goto_933:
      return;

      // try :try_start_6b..:try_end_6d catch (Ljava/lang/Exception) -> :catch_9b
      // try :try_start_70..:try_end_98 catch (Ljava/lang/Exception) -> :catch_9d
    }

    public final void i(int p0) {
      p0.a();
      p0.g(p1);
      p0.n = p0.m;
      if (compare(Ljava/lang/Math.signum((((float) p1) - p0.m)), 0x0) != 0) goto :cond_17;
      :cond_17:
      :goto_19:
      if (v3 >= p0.b.length) goto :cond_5a;
      if (p0.b[v3] == 0) goto :cond_2b;
      if (p0.b[v3].getVisibility() != 0) goto :cond_2b;
      goto :goto_2c;
      :cond_2b:
      :goto_2c:
      if (0x1 != 0) goto :cond_32;
      if (v3 != p1) goto :cond_31;
      goto :goto_32;
      :cond_31:
      :cond_32:
      :goto_32:
      p0.q[v3] = 0x1;
      if (0x1 == 0) goto :cond_3d;
      goto :goto_47;
      :cond_3d:
      :goto_47:
      p0.o[v3] = p0.b[v3].getTranslationX();
      if (0x1 == 0) goto :cond_52;
      goto :goto_53;
      :cond_52:
      :goto_53:
      p0.p[v3] = p0.b[v3].getAlpha();
      goto :goto_19;
      :cond_5a:
      p0.g = p0.f;
      p0.h = p1;
      p0.i = 0x0;
      p0.k = p0.b[v3].getTranslationX();
      p0.j = p0.b[v3].getTranslationX();
      p0.j();
      p0.k(p0.b[v3].getTranslationX());
      return;
    }

    public final void j() {
      if (p0.g >= 0) goto :cond_5;
      return;
      :cond_5:
      if (p0.k == 0) goto :cond_14;
      goto :goto_1b;
      :cond_14:
      :goto_1b:
      if (p0.k == 0) goto :cond_27;
      if (compare(Ljava/lang/Math.signum((((float) p0.h) - p0.n)), 0x0) != 0) goto :cond_27;
      :cond_27:
      if (p0.j == 0) goto :cond_34;
      goto :goto_35;
      :cond_34:
      :goto_35:
      :goto_38:
      if (v5 >= p0.b.length) goto :cond_e1;
      if (p0.b[v5] != 0) goto :cond_44;
      goto :goto_dd;
      :cond_44:
      if (p0.k == 0) goto :cond_4d;
      goto :goto_59;
      :cond_4d:
      if (v5 == p0.g) goto :cond_58;
      if (v5 != p0.h) goto :cond_56;
      goto :goto_58;
      :cond_56:
      goto :goto_59;
      :cond_58:
      :goto_58:
      :goto_59:
      if (0x3e3851ec    # 0.18f == 0) goto :cond_5e;
      goto :goto_5f;
      :cond_5e:
      :goto_5f:
      p0.b[v5].setVisibility(0x3e3851ec    # 0.18f);
      if (v5 != p0.f) goto :cond_67;
      goto :goto_68;
      :cond_67:
      :goto_68:
      if (0x1 == 0) goto :cond_6b;
      :cond_6b:
      p0.b[v5].setImportantForAccessibility(0x4);
      if (0x1 == 0) goto :cond_73;
      goto :goto_75;
      :cond_73:
      :goto_75:
      p0.b[v5].setDescendantFocusability(0x40000);
      if (0x3e3851ec    # 0.18f != 0) goto :cond_81;
      p0.b[v5].setTranslationX(0x0);
      p0.b[v5].setAlpha(0x3f800000    # 1.0f);
      goto :goto_dd;
      :cond_81:
      if (p0.k == 0) goto :cond_ae;
      if (v5 != p0.h) goto :cond_8d;
      goto :goto_92;
      :cond_8d:
      :goto_92:
      if (v5 != p0.h) goto :cond_96;
      goto :goto_97;
      :cond_96:
      :goto_97:
      p0.b[v5].setTranslationX((((0x0 - p0.o[v5]) * p0.i) + p0.o[v5]));
      p0.b[v5].setAlpha((((0x3f800000    # 1.0f - p0.p[v5]) * p0.i) + p0.p[v5]));
      goto :goto_dd;
      :cond_ae:
      if (v5 != p0.g) goto :cond_c8;
      p0.b[v5].setTranslationX(((-Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())) * p0.i));
      if (p0.j == 0) goto :cond_c3;
      goto :goto_c4;
      :cond_c3:
      :goto_c4:
      p0.b[v5].setAlpha((0x3f800000    # 1.0f - p0.i));
      goto :goto_dd;
      :cond_c8:
      p0.b[v5].setTranslationX(((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth()))));
      if (p0.j == 0) goto :cond_d9;
      goto :goto_da;
      :cond_d9:
      :goto_da:
      p0.b[v5].setAlpha(p0.i);
      :goto_dd:
      goto :goto_38;
      :cond_e1:
      if (p0.k == 0) goto :cond_f0;
      goto :goto_fb;
      :cond_f0:
      :goto_fb:
      p0.m = (((((float) p0.h) - p0.n) * p0.i) + p0.n);
      if (p0.g != p0.h) goto :cond_12d;
      if (p0.k == 0) goto :cond_12c;
      goto :goto_12d;
      :cond_12c:
      :cond_12d:
      :goto_12d:
      :goto_136:
      if (0x3e3851ec    # 0.18f >= ((float) Ljava/lang/Math.max(0x3e3851ec    # 0.18f, Ljava/lang/Math.min((p0.b[v5].length - ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())))), ((int) Ljava/lang/Math.floor(((double) (((((float) p0.h) - p0.n) * p0.i) + p0.n)), p0.i)))))) goto :cond_159;
      if (p0.h[0x3e3851ec    # 0.18f] == 0) goto :cond_156;
      :cond_142:
      :goto_142:
      if (p0.h[0x3e3851ec    # 0.18f].hasNext() == 0) goto :cond_156;
      if ((j2) p0.h[0x3e3851ec    # 0.18f].next().a == 0) goto :cond_142;
      (j2) p0.h[0x3e3851ec    # 0.18f].next().b();
      goto :goto_142;
      :cond_156:
      goto :goto_136;
      :cond_159:
      p0.k.j = Ljava/lang/Math.max(0x3e3851ec    # 0.18f, Ljava/lang/Math.min((p0.b[v5].length - ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())))), ((int) Ljava/lang/Math.floor(((double) (((((float) p0.h) - p0.n) * p0.i) + p0.n)), p0.i))));
      p0.k.k = Ljava/lang/Math.min((p0.b[v5].length - ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())))), (Ljava/lang/Math.max(0x3e3851ec    # 0.18f, Ljava/lang/Math.min((p0.b[v5].length - ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())))), ((int) Ljava/lang/Math.floor(((double) (((((float) p0.h) - p0.n) * p0.i) + p0.n)), p0.i)))) + 0x1));
      p0.k.l = Ljava/lang/Math.max(0x0, Ljava/lang/Math.min(0x3f800000    # 1.0f, (p0.m - ((float) Ljava/lang/Math.max(0x3e3851ec    # 0.18f, Ljava/lang/Math.min((p0.b[v5].length - ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())))), ((int) Ljava/lang/Math.floor(((double) (((((float) p0.h) - p0.n) * p0.i) + p0.n)), p0.i))))))));
      p0.k.m = ((0x3f800000    # 1.0f - p0.i) * (Ljava/lang/Math.signum((((float) p0.h) - p0.n)) * ((float) p0.getWidth())));
      p0.k.a();
      return;
    }

    public final void k(boolean p0) {
      if (p0.k == 0) goto :cond_d;
      if (p1 != 0) goto :cond_d;
      p0.i(p0.f);
      return;
      :cond_d:
      p0.l = p1;
      p0.a();
      if (p1 == 0) goto :cond_17;
      goto :goto_19;
      :cond_17:
      :goto_19:
      if (__result__ == 0) goto :cond_76;
      if (p0.isAttachedToWindow() == 0) goto :cond_76;
      if (p0.getWidth() != 0) goto :cond_2c;
      goto :goto_76;
      :cond_2c:
      if (p1 == 0) goto :cond_31;
      goto :goto_32;
      :cond_31:
      :goto_32:
      new float[][0x2][0x0] = p0.i;
      new float[][0x2][0x1] = 0x3f800000    # 1.0f;
      p0.r = Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]);
      new Landroid/view/animation/DecelerateInterpolator();
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).setInterpolator(new Landroid/view/animation/DecelerateInterpolator());
      new Ldev/local/newsoul/L0(p0, 0x1);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addUpdateListener(new Ldev/local/newsoul/L0(p0, 0x1));
      new Ldev/local/newsoul/Y0(p0, p0.h);
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).addListener(new Ldev/local/newsoul/Y0(p0, p0.h));
      Landroid/animation/ValueAnimator.ofFloat(new float[][0x2]).start();
      return;
      :cond_76:
      :goto_76:
      p0.c(p0.h);
      return;
    }

    public final void onDetachedFromWindow() {
      p0.d();
      super.onDetachedFromWindow();
      return;
    }

    public final boolean onInterceptTouchEvent(MotionEvent p0) {
      if (p1.getActionMasked() != 0x2) goto :cond_a;
      p0.e(p1);
      :cond_a:
      if (p0.y != 0) goto :cond_15;
      if (p0.B == 0) goto :cond_13;
      goto :goto_15;
      :cond_13:
      goto :goto_16;
      :cond_15:
      :goto_15:
      :goto_16:
      return p1.getActionMasked();
    }

    public final void onSizeChanged(IIII p0) {
      super.onSizeChanged(p1, p2, p3, p4);
      if (p1 != p3) goto :cond_7;
      if (p2 == p4) goto :cond_a;
      :cond_7:
      p0.d();
      :cond_a:
      return;
    }

    public final boolean onTouchEvent(MotionEvent p0) {
      if (p1.getActionMasked() != 0x2) goto :cond_23;
      p0.e(p1);
      if (p0.y == 0) goto :cond_c2;
      if (p1.findPointerIndex(p0.t) < 0) goto :cond_c2;
      p0.f((p1.getX(p1.findPointerIndex(p0.t)) - p0.v));
      goto :goto_c2;
      :cond_23:
      if (p0.v == 0x1) goto :cond_28;
      if (p0.v != 0x3) goto :cond_c2;
      :cond_28:
      if (p0.y == 0) goto :cond_b4;
      if (p0.s == 0) goto :cond_45;
      if (p0.v == 0x3) goto :cond_45;
      p0.s.computeCurrentVelocity(0x3e8, ((float) p0.e));
      goto :goto_46;
      :cond_45:
      :goto_46:
      if (p0.B == 0) goto :cond_5d;
      if (p0.v != 0x3) goto :cond_57;
      goto :goto_58;
      :cond_57:
      :goto_58:
      goto :goto_ae;
      :cond_5d:
      if (p0.v != Ldev/local/newsoul/Q0.c(p0.i, (-p0.s.getXVelocity(p0.t) * p0.D), ((float) p0.d), 0x1)) goto :cond_6d;
      goto :goto_a7;
      :cond_6d:
      if (compare(((float) p0.getWidth()), p0.i) > 0) goto :cond_72;
      goto :goto_a7;
      :cond_72:
      if (compare(p0.x, p0.i) != 0) goto :cond_79;
      goto :goto_8a;
      :cond_79:
      if (compare(p0.x, p0.i) >= 0) goto :cond_80;
      goto :goto_81;
      :cond_80:
      :goto_81:
      :goto_8a:
      if (0x1 != p0.f) goto :cond_8d;
      goto :goto_a7;
      :cond_8d:
      if (compare(p0.x, p0.i) >= 0) goto :cond_9d;
      goto :goto_9e;
      :cond_9d:
      :goto_9e:
      if (Ldev/local/newsoul/Q0.c(Ljava/lang/Math.min(0x3f800000    # 1.0f, (Ljava/lang/Math.abs(p0.x) / ((float) p0.getWidth()))), ((-p0.s.getXVelocity(p0.t) * p0.D) * ((float) -0x1)), ((float) p0.d), 0x0) == 0) goto :cond_a7;
      :cond_a7:
      :goto_a7:
      if (p0.f == p0.f) goto :cond_ad;
      goto :goto_ae;
      :cond_ad:
      :goto_ae:
      p0.y = 0x0;
      p0.k(0x1);
      goto :goto_c2;
      :cond_b4:
      if (p0.B == 0) goto :cond_c2;
      if (Ldev/local/newsoul/Q0.c(Ljava/lang/Math.min(0x3f800000    # 1.0f, (Ljava/lang/Math.abs(p0.x) / ((float) p0.getWidth()))), ((-p0.s.getXVelocity(p0.t) * p0.D) * ((float) -0x1)), ((float) p0.d), 0x0) == 0x1) goto :cond_bf;
      if (p0.l == 0) goto :cond_bf;
      :cond_bf:
      p0.k(0x0);
      :cond_c2:
      :goto_c2:
      return Ldev/local/newsoul/Q0.c(Ljava/lang/Math.min(0x3f800000    # 1.0f, (Ljava/lang/Math.abs(p0.x) / ((float) p0.getWidth()))), ((-p0.s.getXVelocity(p0.t) * p0.D) * ((float) -0x1)), ((float) p0.d), 0x0);
    }

    public final void onVisibilityAggregated(boolean p0) {
      super.onVisibilityAggregated(p1);
      if (p1 != 0) goto :cond_8;
      p0.d();
      :cond_8:
      return;
    }

    public final void onWindowFocusChanged(boolean p0) {
      super.onWindowFocusChanged(p1);
      if (p1 != 0) goto :cond_8;
      p0.d();
      :cond_8:
      return;
    }

}