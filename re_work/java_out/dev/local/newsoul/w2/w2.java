package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/w2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class w2 extends Ljava/lang/Object {

    public static final WeakHashMap a;
    public static final int b;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      w2.a = new Ljava/util/WeakHashMap();
      w2.b = Landroid/graphics/Color.rgb(0x0, 0xb5, 0xa2);
      return;
    }

    public static void a(TextView p0) {
      new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setOrientation(0x0);
      new Landroid/widget/LinearLayout(p0.g).setGravity(0x10);
      Ldev/local/newsoul/w2.x(p0, p2);
      Ldev/local/newsoul/w2.x(p0, p3);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.g).addView(p2, new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Landroid/view/View(p0.g);
      if (p0.b == 0) goto :cond_2e;
      goto :goto_31;
      :cond_2e:
      :goto_31:
      new Landroid/view/View(p0.g).setBackgroundColor(-0xc5c5b6);
      new Landroid/view/View(p0.g).setImportantForAccessibility(0x2);
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x1), Ldev/local/newsoul/w2.i(p0.g, 0x1c));
      new Landroid/widget/LinearLayout(p0.g).addView(new Landroid/view/View(p0.g), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x1), Ldev/local/newsoul/w2.i(p0.g, 0x1c)));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.g).addView(p3, new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p1.addView(new Landroid/widget/LinearLayout(p0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      return;
    }

    public static void b(TextView p0) {
      p0.c.b(p2, 0x4, Ldev/local/newsoul/L2.e(0x4));
      p2.setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x38));
      p2.setPadding(Ldev/local/newsoul/w2.i(p0.g, 0x10), Ldev/local/newsoul/w2.i(p0.g, 0xe), Ldev/local/newsoul/w2.i(p0.g, 0x10), Ldev/local/newsoul/w2.i(p0.g, 0xe));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).rightMargin = Ldev/local/newsoul/w2.i(p0.g, 0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).leftMargin = Ldev/local/newsoul/w2.i(p0.g, 0x2);
      if (p1.getChildCount() != 0) goto :cond_47;
      goto :goto_49;
      :cond_47:
      :goto_49:
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/w2.i(p0.g, 0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).bottomMargin = Ldev/local/newsoul/w2.i(p0.g, 0x2);
      p1.addView(p2, new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      return;
    }

    public static TextView c(String p0) {
      Ldev/local/newsoul/w2.n(p0.getContext(), p1, 0xd, (q2) p0.getTag().j).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.getContext(), 0x4), 0x0, Ldev/local/newsoul/w2.i(p0.getContext(), 0x8));
      Ldev/local/newsoul/w2.n(p0.getContext(), p1, 0xd, (q2) p0.getTag().j).setLineSpacing(((float) Ldev/local/newsoul/w2.i(p0.getContext(), 0x2)), 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.addView(Ldev/local/newsoul/w2.n(p0.getContext(), p1, 0xd, (q2) p0.getTag().j), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      return new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
    }

    public static r2 d(Landroid/widget/LinearLayout;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I p0) {
      new Ldev/local/newsoul/r2(p0.getContext());
      new Ldev/local/newsoul/r2(p0.getContext()).setContentDescription(p3);
      new Ldev/local/newsoul/r2(p0.getContext()).setPadding(Ldev/local/newsoul/w2.i(p0.getContext(), 0x4), 0x0, 0x0, 0x0);
      new Ldev/local/newsoul/r2(p0.getContext()).setMinWidth(Ldev/local/newsoul/w2.i(p0.getContext(), 0x30));
      new Ldev/local/newsoul/r2(p0.getContext()).setMinHeight(Ldev/local/newsoul/w2.i(p0.getContext(), 0x30));
      if (p1 == 0) goto :cond_6c;
      if (p2.startsWith("ad_") != 0) goto :cond_6c;
      if (p2.startsWith("privacy_") != 0) goto :cond_6c;
      if (p2.equals("square_like_all_loaded") != 0) goto :cond_6c;
      if (p2.equals("square_auto_like") != 0) goto :cond_6c;
      if (p2.equals("feed_keywords_enabled_v1") != 0) goto :cond_6c;
      if (p2.equals("feed_filter_enabled") != 0) goto :cond_6c;
      if (p2.equals("feed_filter_keep_unknown") != 0) goto :cond_6c;
      goto :goto_6d;
      :cond_6c:
      :goto_6d:
      if (p1 == 0) goto :cond_76;
      if (p1.getBoolean(p2, 0x1) == 0) goto :cond_76;
      goto :goto_77;
      :cond_76:
      :goto_77:
      new Ldev/local/newsoul/r2(p0.getContext()).setChecked(0x1);
      if ((q2) p0.getTag().c.a == 0) goto :cond_a8;
      goto :goto_ab;
      :cond_a8:
      :goto_ab:
      if ((q2) p0.getTag().c.a == 0) goto :cond_b1;
      goto :goto_b4;
      :cond_b1:
      :goto_b4:
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e, 0x10100a0}, new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0xa98c91, -0x9d9d91, -0xff4a5e, -0x645b56});
      new Ldev/local/newsoul/r2(p0.getContext()).setThumbTintList(new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e, 0x10100a0}, new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0xa98c91, -0x9d9d91, -0xff4a5e, -0x645b56}));
      if ((q2) p0.getTag().c.a == 0) goto :cond_cc;
      goto :goto_cf;
      :cond_cc:
      :goto_cf:
      if ((q2) p0.getTag().c.a == 0) goto :cond_d5;
      goto :goto_d8;
      :cond_d5:
      :goto_d8:
      if ((q2) p0.getTag().c.a == 0) goto :cond_de;
      goto :goto_e1;
      :cond_de:
      :goto_e1:
      if ((q2) p0.getTag().c.a == 0) goto :cond_e7;
      goto :goto_ea;
      :cond_e7:
      :goto_ea:
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e, 0x10100a0}, new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0xcab6bb, -0xc4c4b7, -0xc799a1, -0xafafa0});
      new Ldev/local/newsoul/r2(p0.getContext()).setTrackTintList(new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e, 0x10100a0}, new int[][] {-0x101009e}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0xcab6bb, -0xc4c4b7, -0xc799a1, -0xafafa0}));
      new Landroid/widget/LinearLayout(p0.getContext());
      new Landroid/widget/LinearLayout(p0.getContext()).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0.getContext()).setMinimumHeight(Ldev/local/newsoul/w2.i(p0.getContext(), 0x34));
      if (p4.contains("未就绪") == 0) goto :cond_114;
      goto :goto_115;
      :cond_114:
      :goto_115:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setMinHeight(Ldev/local/newsoul/w2.i(p0.getContext(), 0x30));
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setGravity(0x10);
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setImportantForAccessibility(0x2);
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setFocusable(0x0);
      new Landroid/widget/LinearLayout(p0.getContext()).setImportantForAccessibility(0x2);
      new Ldev/local/newsoul/S1(new Ldev/local/newsoul/r2(p0.getContext()), 0x0);
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setOnClickListener(new Ldev/local/newsoul/S1(new Ldev/local/newsoul/r2(p0.getContext()), 0x0));
      Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5).setFocusable(0x0);
      new Ldev/local/newsoul/S1(new Ldev/local/newsoul/r2(p0.getContext()), 0x1);
      new Landroid/widget/LinearLayout(p0.getContext()).setOnClickListener(new Ldev/local/newsoul/S1(new Ldev/local/newsoul/r2(p0.getContext()), 0x1));
      new Landroid/widget/LinearLayout(p0.getContext()).setFocusable(0x0);
      Ldev/local/newsoul/w2.p(Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5));
      Ldev/local/newsoul/w2.p(new Landroid/widget/LinearLayout(p0.getContext()));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.getContext()).addView(Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      if (p4.replace("当前版本未就绪", "").replace("未就绪", "").replaceAll("[\\s·]+$", "").trim().isEmpty() != 0) goto :cond_18b;
      new Landroid/widget/LinearLayout(p0.getContext()).addView(Ldev/local/newsoul/w2.l((q2) p0.getTag(), p3, p4.replace("当前版本未就绪", "").replace("未就绪", "").replaceAll("[\\s·]+$", "").trim()));
      :cond_18b:
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2);
      new Landroid/widget/LinearLayout(p0.getContext()).addView(new Ldev/local/newsoul/r2(p0.getContext()), new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.addView(new Landroid/widget/LinearLayout(p0.getContext()), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/U1(p1, new Ldev/local/newsoul/r2(p0.getContext()), p2);
      new Ldev/local/newsoul/r2(p0.getContext()).setOnCheckedChangeListener(new Ldev/local/newsoul/U1(p1, new Ldev/local/newsoul/r2(p0.getContext()), p2));
      if (p1 == 0) goto :cond_1b0;
      new Ldev/local/newsoul/q(new Ldev/local/newsoul/r2(p0.getContext()), p1, p2, 0x1);
      :cond_1b0:
      0x1.<init>(Ldev/local/newsoul/w2.n(p0.getContext(), new Ljava/lang/StringBuilder().toString(), 0x10, p5), new Ldev/local/newsoul/r2(p0.getContext()), new Landroid/widget/LinearLayout(p0.getContext()), p3, p1, 0x4);
      new Ldev/local/newsoul/r2(p0.getContext()).a(0x1);
      if (p1 != 0) goto :cond_1c3;
      return p0;
      :cond_1c3:
      if ((h2) (q2) p0.getTag().l.get(p0) != 0) goto :cond_1d5;
      new Ldev/local/newsoul/h2((q2) p0.getTag(), p0);
      :cond_1d5:
      new Ldev/local/newsoul/V1((h2) (q2) p0.getTag().l.get(p0), 0x0);
      new Ldev/local/newsoul/r2(p0.getContext()).a(new Ldev/local/newsoul/V1((h2) (q2) p0.getTag().l.get(p0), 0x0));
      return new Ldev/local/newsoul/V1((h2) (q2) p0.getTag().l.get(p0), 0x0);
    }

    public static void e(Runnable p0) {
      Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k).setMinWidth(Ldev/local/newsoul/w2.i(p1.g, 0x30));
      Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k).setMinHeight(Ldev/local/newsoul/w2.i(p1.g, 0x30));
      Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k).setGravity(0x11);
      Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k).setContentDescription("预览".concat(Ljava/lang/String.valueOf(p0.getContentDescription())));
      p1.c.b(Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k), 0xa, Ldev/local/newsoul/L2.e(0xa));
      new Ldev/local/newsoul/C0(p0, p2, 0x3);
      Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k).setOnClickListener(new Ldev/local/newsoul/C0(p0, p2, 0x3));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k));
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2).leftMargin = Ldev/local/newsoul/w2.i(p1.g, 0x8);
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2).rightMargin = Ldev/local/newsoul/w2.i(p1.g, 0x4);
      (LinearLayout) p0.getParent().addView(Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k), (LinearLayout) p0.getParent().indexOfChild(p0), new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2));
      new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k), p0, 0xa);
      p0.a(new Ldev/local/newsoul/g(Ldev/local/newsoul/w2.n(p1.g, "预览", 0xe, p1.k), p0, 0xa));
      return;
    }

    public static void f(BooleanSupplier p0) {
      new Ldev/local/newsoul/g(p1, p2, 0x9);
      new Ldev/local/newsoul/g(p1, p2, 0x9).run();
      p0.a(new Ldev/local/newsoul/g(p1, p2, 0x9));
      return;
    }

    public static void g(View p0) {
      p0.setFocusable(0x1);
      Ldev/local/newsoul/w2.p(p0);
      new Landroid/view/View$AccessibilityDelegate();
      p0.setAccessibilityDelegate(new Landroid/view/View$AccessibilityDelegate());
      return;
    }

    public static boolean h(View p0) {
      if (p0.isAttachedToWindow() == 0) goto :cond_2b;
      if (p0.isShown() != 0) goto :cond_e;
      goto :goto_2b;
      :cond_e:
      :goto_e:
      if (p0 == 0) goto :cond_29;
      if (p0.getImportantForAccessibility() != 0x4) goto :cond_18;
      return p0.getImportantForAccessibility();
      :cond_18:
      if ((p0.getParent() instanceof View) == 0) goto :cond_27;
      goto :goto_e;
      :cond_27:
      goto :goto_e;
      :cond_29:
      return (p0.getParent() instanceof View);
      :cond_2b:
      :goto_2b:
      return (p0.getParent() instanceof View);
    }

    public static int i(Landroid/content/Context;I p0) {
      return v0;
    }

    public static FrameLayout j(Landroid/app/Activity;Z p0) {
      if (p1 == 0) goto :cond_c;
      goto :goto_d;
      :cond_c:
      :goto_d:
      if (p1 == 0) goto :cond_18;
      goto :goto_1e;
      :cond_18:
      :goto_1e:
      if (p1 == 0) goto :cond_31;
      goto :goto_37;
      :cond_31:
      :goto_37:
      new Landroid/widget/FrameLayout(p0);
      new Landroid/widget/FrameLayout(p0).setBackgroundColor(Landroid/graphics/Color.rgb(0x12, 0x12, 0x1f));
      new Landroid/widget/LinearLayout(p0);
      new Landroid/widget/LinearLayout(p0).setOrientation(0x0);
      new Landroid/widget/LinearLayout(p0).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0).setPadding(Ldev/local/newsoul/w2.i(p0, 0x16), 0x0, Ldev/local/newsoul/w2.i(p0, 0x16), 0x0);
      new Ldev/local/newsoul/i2(p0, Ldev/local/newsoul/w2.q(Landroid/graphics/Color.rgb(0x68, 0x68, 0x81), p1, new int[][] {Landroid/graphics/Color.rgb(0x12, 0x12, 0x1f)}), 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0, 0x1c), Ldev/local/newsoul/w2.i(p0, 0x1c));
      new Landroid/widget/LinearLayout(p0).addView(new Ldev/local/newsoul/i2(p0, Ldev/local/newsoul/w2.q(Landroid/graphics/Color.rgb(0x68, 0x68, 0x81), p1, new int[][] {Landroid/graphics/Color.rgb(0x12, 0x12, 0x1f)}), 0x0), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0, 0x1c), Ldev/local/newsoul/w2.i(p0, 0x1c)));
      Ldev/local/newsoul/w2.n(p0, "New Soul", 0x11, Ldev/local/newsoul/w2.q(Landroid/graphics/Color.rgb(0x68, 0x68, 0x81), p1, new int[][] {Landroid/graphics/Color.rgb(0x12, 0x12, 0x1f)})).setTypeface(Typeface.DEFAULT, 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f).leftMargin = Ldev/local/newsoul/w2.i(p0, 0x12);
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/w2.n(p0, "New Soul", 0x11, Ldev/local/newsoul/w2.q(Landroid/graphics/Color.rgb(0x68, 0x68, 0x81), p1, new int[][] {Landroid/graphics/Color.rgb(0x12, 0x12, 0x1f)})), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Ldev/local/newsoul/i2(p0, Landroid/graphics/Color.rgb(0x35, 0x35, 0x47), 0x1);
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0, 0xc), Ldev/local/newsoul/w2.i(p0, 0x14));
      new Landroid/widget/LinearLayout(p0).addView(new Ldev/local/newsoul/i2(p0, Landroid/graphics/Color.rgb(0x35, 0x35, 0x47), 0x1), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0, 0xc), Ldev/local/newsoul/w2.i(p0, 0x14)));
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x1);
      new Landroid/widget/FrameLayout(p0).addView(new Landroid/widget/LinearLayout(p0), new Landroid/widget/FrameLayout$LayoutParams(-0x1, -0x1));
      new Landroid/view/View(p0);
      if (p1 == 0) goto :cond_c1;
      goto :goto_c7;
      :cond_c1:
      :goto_c7:
      new Landroid/view/View(p0).setBackgroundColor(Landroid/graphics/Color.rgb(0x24, 0x24, 0x34));
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, 0x1, 0x50);
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, 0x1, 0x50).rightMargin = Ldev/local/newsoul/w2.i(p0, 0x16);
      new Landroid/widget/FrameLayout$LayoutParams(-0x1, 0x1, 0x50).leftMargin = Ldev/local/newsoul/w2.i(p0, 0x16);
      new Landroid/widget/FrameLayout(p0).addView(new Landroid/view/View(p0), new Landroid/widget/FrameLayout$LayoutParams(-0x1, 0x1, 0x50));
      new Landroid/widget/LinearLayout(p0).setImportantForAccessibility(0x4);
      new Landroid/widget/FrameLayout(p0).setContentDescription("New Soul");
      new Ldev/local/newsoul/N1(p0, p1, 0x0);
      new Landroid/widget/FrameLayout(p0).setOnClickListener(new Ldev/local/newsoul/N1(p0, p1, 0x0));
      Ldev/local/newsoul/w2.g(new Landroid/widget/FrameLayout(p0));
      return new Ldev/local/newsoul/N1(p0, p1, 0x0);
    }

    public static View k(String p0) {
      if (p0.getResources().getIdentifier(p1, "id", p0.getPackageName()) != 0) goto :cond_12;
      goto :goto_16;
      :cond_12:
      :goto_16:
      return p0.getResources();
    }

    public static ImageButton l(CharSequence p0) {
      new Landroid/widget/ImageButton(p0.g);
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x30), Ldev/local/newsoul/w2.i(p0.g, 0x30));
      new Landroid/widget/ImageButton(p0.g).setLayoutParams(new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x30), Ldev/local/newsoul/w2.i(p0.g, 0x30)));
      new Landroid/widget/ImageButton(p0.g).setPadding(Ldev/local/newsoul/w2.i(p0.g, 0xf), Ldev/local/newsoul/w2.i(p0.g, 0xf), Ldev/local/newsoul/w2.i(p0.g, 0xf), Ldev/local/newsoul/w2.i(p0.g, 0xf));
      new Landroid/widget/ImageButton(p0.g).setScaleType(ImageView$ScaleType.FIT_CENTER);
      if (p0.a.getResources().getIdentifier("c_ic_icon_question", "drawable", p0.a.getPackageName()) == 0) goto :cond_51;
      new Landroid/widget/ImageButton(p0.g).setImageDrawable(p0.a.getDrawable(p0.a.getResources().getIdentifier("c_ic_icon_question", "drawable", p0.a.getPackageName())));
      goto :goto_57;
      :cond_51:
      new Landroid/widget/ImageButton(p0.g).setImageResource(0x1080040);
      :goto_57:
      new Landroid/widget/ImageButton(p0.g).setImageTintList(Landroid/content/res/ColorStateList.valueOf(p0.j));
      p0.c.b(new Landroid/widget/ImageButton(p0.g), 0xc, Ldev/local/newsoul/L2.e(0xc));
      new Ljava/lang/StringBuilder();
      new Landroid/widget/ImageButton(p0.g).setContentDescription(new Ljava/lang/StringBuilder().toString());
      new Ldev/local/newsoul/G1(p0, p2, p1, 0x0);
      new Landroid/widget/ImageButton(p0.g).setOnClickListener(new Ldev/local/newsoul/G1(p0, p2, p1, 0x0));
      return new Landroid/widget/ImageButton(p0.g);
    }

    public static TextView m(String p0) {
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.k).setGravity(0x11);
      p0.c.b(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.k), 0xc, Ldev/local/newsoul/L2.e(0xc));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.k).setContentDescription(p2);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.k));
      return 0xc;
    }

    public static TextView n(Landroid/content/Context;Ljava/lang/String;II p0) {
      new Landroid/widget/TextView(p0);
      new Landroid/widget/TextView(p0).setText(p1);
      new Landroid/widget/TextView(p0).setTextSize(((float) p2));
      new Landroid/widget/TextView(p0).setTextColor(p3);
      return new Landroid/widget/TextView(p0);
    }

    public static SharedPreferences o(Context p0) {
      return "dev.local.soulhookdemo.public.settings.v1";
    }

    public static void p(View p0) {
      if (p0.getForeground() != 0) goto :cond_37;
      if ((p0.getBackground() instanceof RippleDrawable) == 0) goto :cond_f;
      goto :goto_37;
      :cond_f:
      new Landroid/util/TypedValue();
      if (p0.getContext().getTheme().resolveAttribute(0x101030e, new Landroid/util/TypedValue(), 0x1) == 0) goto :cond_37;
      if (new Landroid/util/TypedValue().resourceId == 0) goto :cond_37;
      p0.setForeground(p0.getContext().getDrawable(new Landroid/util/TypedValue().resourceId));
      :cond_37:
      :goto_37:
      return;
    }

    public static int q(IZ[I p0) {
      if (p1 == 0) goto :cond_6;
      goto :goto_8;
      :cond_6:
      :goto_8:
      :goto_a:
      if (v3 > 0x64) goto :cond_86;
      :goto_58:
      if (v7 >= v5) goto :cond_80;
      if (compare(((Ljava/lang/Math.max(((double) Landroid/graphics/Color.luminance(0x64)), v10, ((double) Landroid/graphics/Color.luminance(p2[v7])), v12) + 0x3fa999999999999aL    # 0.05) / (Ljava/lang/Math.min(((double) Landroid/graphics/Color.luminance(0x64)), v10, ((double) Landroid/graphics/Color.luminance(p2[v7])), v12) + 0x3fa999999999999aL    # 0.05)), 0x4012000000000000L    # 4.5) >= 0) goto :cond_7d;
      :cond_7d:
      goto :goto_58;
      :cond_80:
      if (v6 == 0) goto :cond_83;
      return p2;
      :cond_83:
      goto :goto_a;
      :cond_86:
      return p2;
    }

    public static LinearLayout r(K1 p0) {
      new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout(p0.g).setPadding(Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0x4), Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0x6));
      if (p0.q == 0) goto :cond_31;
      goto :goto_33;
      :cond_31:
      :goto_33:
      p0.c.b(new Landroid/widget/LinearLayout(p0.g), 0x3, 0x41600000    # 14.0f);
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setTypeface(Typeface.DEFAULT, 0x1);
      if (Build$VERSION.SDK_INT < 0x1c) goto :cond_4f;
      Ldev/local/newsoul/N0.b(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i));
      :cond_4f:
      new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0.g).setMinimumHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.g, 0x8), 0x0, Ldev/local/newsoul/w2.i(p0.g, 0x8));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0.g).setPadding(0x0, 0x0, 0x0, 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.g).addView(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      if (p2 == 0) goto :cond_8a;
      new Landroid/widget/LinearLayout(p0.g).addView(p0.l(p1, p2));
      :cond_8a:
      if (p3 == 0) goto :cond_f9;
      Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j).setGravity(0x11);
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j).setContentDescription(new Ljava/lang/StringBuilder().toString());
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x30), Ldev/local/newsoul/w2.i(p0.g, 0x30));
      Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j).setLayoutParams(new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.g, 0x30), Ldev/local/newsoul/w2.i(p0.g, 0x30)));
      p0.c.b(Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j), 0xc, Ldev/local/newsoul/L2.e(0xc));
      new Ldev/local/newsoul/C0(p3, Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j), 0x4);
      Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j).setOnClickListener(new Ldev/local/newsoul/C0(p3, Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j), 0x4));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j));
      new Landroid/widget/LinearLayout(p0.g).addView(Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setGravity(0x10);
      new Ldev/local/newsoul/Z1(p3, Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setOnLongClickListener(new Ldev/local/newsoul/Z1(p3, Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j)));
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/w2.n(p0.g, "⋮", 0x18, p0.j).setTooltipText(new Ljava/lang/StringBuilder().toString());
      :cond_f9:
      new Landroid/widget/LinearLayout(p0.g).addView(new Landroid/widget/LinearLayout(p0.g));
      new Landroid/view/View(p0.g);
      if (p0.b == 0) goto :cond_109;
      goto :goto_10c;
      :cond_109:
      :goto_10c:
      new Landroid/view/View(p0.g).setBackgroundColor(-0xc5c5b6);
      if (p0.q != 0) goto :cond_122;
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1));
      new Landroid/widget/LinearLayout(p0.g).addView(new Landroid/view/View(p0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1)));
      goto :goto_123;
      :cond_122:
      :goto_123:
      new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout(p0.g).setTag(p0);
      new Landroid/widget/LinearLayout(p0.g).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.g, 0x4), 0x0, 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout(p0.g).addView(new Landroid/widget/LinearLayout(p0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      if (p0.h.getChildCount() <= 0) goto :cond_153;
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/w2.i(p0.g, 0x10);
      :cond_153:
      p0.h.addView(new Landroid/widget/LinearLayout(p0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      return p0.h;
    }

    public static void s(String p0) {
      if (Landroid/text/TextUtils.equals(p0.getText(), p1) == 0) goto :cond_b;
      return;
      :cond_b:
      if (Ldev/local/newsoul/w2.h(p0) == 0) goto :cond_1d;
      if (p0.getText().length() <= 0) goto :cond_1d;
      goto :goto_1e;
      :cond_1d:
      :goto_1e:
      p0.setAccessibilityLiveRegion(0x1);
      p0.setText(p1);
      return;
    }

    public static void t(String p0) {
      new Ldev/local/newsoul/q2(p0, p1);
      if (p2 == 0) goto :cond_a;
      goto :goto_c;
      :cond_a:
      :goto_c:
      new Landroid/widget/ProgressBar(new Ldev/local/newsoul/q2(p0, p1).g);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0, 0x38));
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0, p1), "检测到 Soul 版本变化", 0x0, 0x0).addView(new Landroid/widget/ProgressBar(new Ldev/local/newsoul/q2(p0, p1).g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0, 0x38)));
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0, p1), "检测到 Soul 版本变化", 0x0, 0x0).addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0, p1).g, "正在检查当前 Soul 版本的功能适配状态…", 0xd, new Ldev/local/newsoul/q2(p0, p1).j));
      new Ldev/local/newsoul/q2(p0, p1).g("功能兼容性检测", "关闭");
      new Ldev/local/newsoul/q2(p0, p1).e.getButton(-0x3).setVisibility(0x8);
      v9.<init>(new Ldev/local/newsoul/q2(p0, p1), p0, p1, p2, p3);
      new Ldev/local/newsoul/q2(p0, p1).e.getButton(-0x3).setOnClickListener(v9);
      v11.<init>(p0, new Ldev/local/newsoul/q2(p0, p1), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0, p1), "检测到 Soul 版本变化", 0x0, 0x0), p2, p3, new Ldev/local/newsoul/q2(p0, p1).e.getButton(-0x3), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p0, p1), "检测到 Soul 版本变化", 0x0, 0x0), p1);
      new Ljava/lang/Thread(v11, "NewSoul-HookScan");
      new Ljava/lang/Thread(v11, "NewSoul-HookScan").start();
      return;
    }

    public static void u(Landroid/app/Activity;Z p0) {
      :try_start_2:
      new Ldev/local/newsoul/v2(p0, p1);
      new Ldev/local/newsoul/v2(p0, p1).c();
      :try_end_f:
      goto :goto_16;
      :catchall_10:
      :goto_16:
      return;

      // .catchall {:try_start_2 .. :try_end_f} :catchall_10
    }

    public static void v(Landroid/widget/LinearLayout;Landroid/widget/TextView;I p0) {
      p0.setId(__result__);
      if (p1 == 0) goto :cond_10;
      p1.setLabelFor(p0.getId());
      :cond_10:
      p0.setImportantForAccessibility(0x1);
      new Ldev/local/newsoul/f2(p2, 0x0);
      p0.setAccessibilityDelegate(new Ldev/local/newsoul/f2(p2, 0x0));
      return;
    }

    public static void w(Landroid/widget/TextView;I p0) {
      p0.setFocusable(0x1);
      Ldev/local/newsoul/w2.p(p0);
      new Ldev/local/newsoul/f2(p1, 0x1);
      p0.setAccessibilityDelegate(new Ldev/local/newsoul/f2(p1, 0x1));
      return;
    }

    public static void x(TextView p0) {
      p0.c.b(p1, 0x9, Ldev/local/newsoul/L2.e(0x9));
      p1.setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x34));
      p1.setGravity(0x11);
      p1.setSingleLine(0x0);
      p1.setEllipsize(0x0);
      p1.setPadding(p1.getPaddingLeft(), Ldev/local/newsoul/w2.i(p0.g, 0x8), p1.getPaddingRight(), Ldev/local/newsoul/w2.i(p0.g, 0x8));
      return;
    }

}