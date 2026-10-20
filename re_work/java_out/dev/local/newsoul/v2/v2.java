package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/v2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class v2 extends Ljava/lang/Object {

    public static final String[] n;
    public final q2 a;
    public final q2[] b;
    public final ScrollView[] c;
    public final TextView[] d;
    public final Z0 e;
    public final HorizontalScrollView f;
    public final t2 g;
    public final M0 h;
    public int i;
    public int j;
    public int k;
    public float l;
    public boolean m;

    static void static {}() {
      v2.n = new String[][] {"聊天", "广场", "主页", "广告", "其他"};
      return;
    }

    public void super(Landroid/app/Activity;Z p0) {
      super();
      this.b = new q2[][0x5];
      this.c = new ScrollView[][0x5];
      this.d = new TextView[][0x5];
      this.i = -0x1;
      new Ldev/local/newsoul/q2(p1, p2);
      this.a = new Ldev/local/newsoul/q2(p1, p2);
      new Ldev/local/newsoul/q2(p1, p2).t = "长按 Soul 底部导航栏的图标，可以快速打开插件设置。";
      new Ldev/local/newsoul/q2(p1, p2).c.c = 0x1;
      new Ldev/local/newsoul/k0(new Ljava/lang/Object());
      new Ldev/local/newsoul/Z0(new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/k0(new Ljava/lang/Object()));
      this.e = new Ldev/local/newsoul/Z0(new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/k0(new Ljava/lang/Object()));
      new Landroid/widget/HorizontalScrollView(new Ldev/local/newsoul/q2(p1, p2).g);
      this.f = new Landroid/widget/HorizontalScrollView(new Ldev/local/newsoul/q2(p1, p2).g);
      new Landroid/widget/HorizontalScrollView(new Ldev/local/newsoul/q2(p1, p2).g).setHorizontalScrollBarEnabled(0x0);
      new Landroid/widget/HorizontalScrollView(new Ldev/local/newsoul/q2(p1, p2).g).setFillViewport(0x1);
      new Ldev/local/newsoul/M0(new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).c, 0x0);
      this.h = new Ldev/local/newsoul/M0(new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).c, 0x0);
      new Ldev/local/newsoul/M0(new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).c, 0x0).setPadding(Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2(p1, p2).g, 0x4), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2(p1, p2).g, 0x4), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2(p1, p2).g, 0x4), Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2(p1, p2).g, 0x4));
      new Ldev/local/newsoul/t2(new Ljava/lang/Object(), new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).g);
      this.g = new Ldev/local/newsoul/t2(new Ljava/lang/Object(), new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).g);
      new Ldev/local/newsoul/t2(new Ljava/lang/Object(), new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).g).setGravity(0x10);
      Ldev/local/newsoul/w2.v(new Ldev/local/newsoul/t2(new Ljava/lang/Object(), new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).g), 0x0, 0x5);
      new Ldev/local/newsoul/t2(new Ljava/lang/Object(), new Ldev/local/newsoul/q2(p1, p2).g, new Ldev/local/newsoul/q2(p1, p2).g).setContentDescription("设置分类");
      :goto_6f:
      if (v4 >= v0) goto :cond_c5;
      p0.d[v4] = Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j);
      Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j).setSingleLine(p2);
      Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j).setGravity(0x11);
      Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j).setPadding(Ldev/local/newsoul/w2.i(v2, 0xc), Ldev/local/newsoul/w2.i(v2, 0x8), Ldev/local/newsoul/w2.i(v2, 0xc), Ldev/local/newsoul/w2.i(v2, 0x8));
      Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j).setMinHeight(Ldev/local/newsoul/w2.i(v2, 0x30));
      Ldev/local/newsoul/w2.w(Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j), v4);
      new Ldev/local/newsoul/x0(p0, v4, 0x2);
      Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j).setOnClickListener(new Ldev/local/newsoul/x0(p0, v4, 0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2);
      p0.g.addView(Ldev/local/newsoul/w2.n(v2, v2.n[v4], 0xf, p0.a.j), new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2));
      goto :goto_6f;
      :cond_c5:
      new Landroid/widget/FrameLayout$LayoutParams(v1, -0x2);
      p0.h.addView(p0.g, new Landroid/widget/FrameLayout$LayoutParams(v1, -0x2));
      new Landroid/widget/FrameLayout$LayoutParams(-0x2, -0x2);
      p0.f.addView(p0.h, new Landroid/widget/FrameLayout$LayoutParams(-0x2, -0x2));
      new Ldev/local/newsoul/s2(p0);
      p0.g.addOnLayoutChangeListener(new Ldev/local/newsoul/s2(p0));
      return;
    }

    public final void a() {
      if (p0.d[p0.j] == 0) goto :cond_105;
      if (p0.d[p0.k] == 0) goto :cond_105;
      if (p0.d[p0.j].getWidth() != 0) goto :cond_16;
      goto :goto_105;
      :cond_16:
      p0.h.c((((float) p0.g.getLeft()) + ((((float) (p0.d[p0.k].getLeft() - p0.d[p0.j].getLeft())) * p0.l) + ((float) p0.d[p0.j].getLeft()))), ((float) Ljava/lang/Math.round(((((float) (p0.d[p0.k].getWidth() - p0.d[p0.j].getWidth())) * p0.l) + ((float) p0.d[p0.j].getWidth())))), p0.m);
      if (p0.a.b == 0) goto :cond_59;
      goto :goto_5c;
      :cond_59:
      :goto_5c:
      if (p0.a.b == 0) goto :cond_62;
      goto :goto_65;
      :cond_62:
      :goto_65:
      :goto_6f:
      if (-0xdebabe >= p0.d.length) goto :cond_d1;
      if (-0xdebabe != p0.j) goto :cond_7c;
      goto :goto_7d;
      :cond_7c:
      :goto_7d:
      if (-0xdebabe != p0.k) goto :cond_84;
      :cond_84:
      p0.d[-0xdebabe].setTextColor(Landroid/graphics/Color.rgb(Ljava/lang/Math.round(((((float) (Landroid/graphics/Color.red(p0.a.b) - Landroid/graphics/Color.red(p0.a.j))) * (0x3f800000    # 1.0f - p0.l)) + ((float) Landroid/graphics/Color.red(p0.a.j)))), Ljava/lang/Math.round(((((float) (Landroid/graphics/Color.green(p0.a.b) - Landroid/graphics/Color.green(p0.a.j))) * (0x3f800000    # 1.0f - p0.l)) + ((float) Landroid/graphics/Color.green(p0.a.j)))), Ljava/lang/Math.round(((((float) (Landroid/graphics/Color.blue(p0.a.b) - Landroid/graphics/Color.blue(p0.a.j))) * (0x3f800000    # 1.0f - p0.l)) + ((float) Landroid/graphics/Color.blue(p0.a.j))))));
      goto :goto_6f;
      :cond_d1:
      if (compare(((((float) (p0.d[p0.k].getLeft() - p0.d[p0.j].getLeft())) * p0.l) + ((float) p0.d[p0.j].getLeft())), ((float) p0.f.getScrollX())) >= 0) goto :cond_f2;
      p0.f.scrollTo(Ljava/lang/Math.round(((((float) (p0.d[p0.k].getLeft() - p0.d[p0.j].getLeft())) * p0.l) + ((float) p0.d[p0.j].getLeft()))), w2.b);
      goto :goto_105;
      :cond_f2:
      if (Ljava/lang/Math.round(((((float) (p0.d[p0.k].getLeft() - p0.d[p0.j].getLeft())) * p0.l) + ((float) p0.d[p0.j].getLeft()))) <= (p0.f.getWidth() + p0.f.getScrollX())) goto :cond_105;
      p0.f.scrollTo((Ljava/lang/Math.round(((((float) (p0.d[p0.k].getLeft() - p0.d[p0.j].getLeft())) * p0.l) + ((float) p0.d[p0.j].getLeft()))) - p0.f.getWidth()), w2.b);
      :cond_105:
      :goto_105:
      return;
    }

    public final void b(IZ p0) {
      if (p1 < 0) goto :cond_62;
      if (p1 < p0.e.b.length) goto :cond_a;
      goto :goto_65;
      :cond_a:
      p0.e.b();
      if (p0.e.f < 0) goto :cond_5e;
      if (p2 != 0) goto :cond_14;
      goto :goto_5e;
      :cond_14:
      p0.e.a();
      if (p0.e.g != p0.e.h) goto :cond_4b;
      if (p0.e.k == 0) goto :cond_24;
      goto :goto_4b;
      :cond_24:
      if (p0.e.f != p1) goto :cond_29;
      goto :goto_65;
      :cond_29:
      p0.e.g = p0.e.f;
      p0.e.h = p1;
      p0.e.i = 0x0;
      if (Ljava/lang/Math.abs((p1 - p0.e.f)) <= 0x1) goto :cond_38;
      :cond_38:
      p0.e.j = 0x0;
      p0.e.g(p0.e.g);
      p0.e.g(p0.e.h);
      p0.e.j();
      p0.e.k(0x1);
      goto :goto_65;
      :cond_4b:
      :goto_4b:
      if (p0.e.k != 0) goto :cond_5a;
      if (p0.e.h == 0x0) goto :cond_53;
      if (p0.e.h != p0.e.f) goto :cond_5a;
      :cond_53:
      if (p0.e.h != 0x0) goto :cond_56;
      :cond_56:
      p0.e.k(0x0);
      goto :goto_65;
      :cond_5a:
      p0.e.i(p0.e.h);
      goto :goto_65;
      :cond_5e:
      :goto_5e:
      p0.e.c(p0.e.h);
      goto :goto_65;
      :cond_62:
      :goto_65:
      return;
    }

    public final void c() {
      new Ldev/local/newsoul/u2(p0, p0.a.g);
      new Ldev/local/newsoul/u2(p0, p0.a.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).rightMargin = Ldev/local/newsoul/w2.i(p0.a.g, 0x10);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).leftMargin = Ldev/local/newsoul/w2.i(p0.a.g, 0x10);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/w2.i(p0.a.g, 0x4);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).bottomMargin = Ldev/local/newsoul/w2.i(p0.a.g, 0x8);
      new Ldev/local/newsoul/u2(p0, p0.a.g).addView(p0.f, new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, 0x0, 0x3f800000    # 1.0f);
      new Ldev/local/newsoul/u2(p0, p0.a.g).addView(p0.e, new Landroid/widget/LinearLayout$LayoutParams(-0x1, 0x0, 0x3f800000    # 1.0f));
      p0.b(0x0, 0x0);
      p0.a.i("New Soul", "完成", new Ldev/local/newsoul/u2(p0, p0.a.g));
      :goto_51:
      if (v0.hasNext() == 0) goto :cond_62;
      (q2) v0.next().e = v1.e;
      goto :goto_51;
      :cond_62:
      if (Build$VERSION.SDK_INT < 0x1e) goto :cond_84;
      :cond_84:
      v1.e.getWindow().setLayout(Ljava/lang/Math.min(Ldev/local/newsoul/w2.i(v6, 0x230), (v1.a.getResources().getDisplayMetrics().widthPixels - Ldev/local/newsoul/w2.i(v6, 0x18))), v4);
      p0.d();
      return;
    }

    public final void d() {
      if (p0.a.f == 0) goto :cond_51;
      if (p0.i >= 0) goto :cond_b;
      goto :goto_51;
      :cond_b:
      :goto_b:
      if (p0.a.f.getChildCount() <= 0x1) goto :cond_1a;
      p0.a.f.removeViewAt(0x1);
      goto :goto_b;
      :cond_1a:
      new Landroid/text/SpannableStringBuilder(p0.a.t);
      if (p0.b[p0.i].t == 0) goto :cond_46;
      :cond_46:
      p0.a.f.addView(Ldev/local/newsoul/w2.l(p0.a, "插件设置", new Landroid/text/SpannableStringBuilder(p0.a.t)));
      :cond_51:
      :goto_51:
      return;
    }

}