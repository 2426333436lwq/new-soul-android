package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/F0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class F0 extends Ljava/lang/Object {

    public static final int a;

    static void static {}() {
      F0.a = Landroid/graphics/Color.rgb(0x0, 0xc6, 0xb8);
      return;
    }

    public static int a(Landroid/content/Context;I p0) {
      return v0;
    }

    public static TextView b(Landroid/content/Context;Ljava/lang/String;II p0) {
      new Landroid/widget/TextView(p0);
      new Landroid/widget/TextView(p0).setText(p1);
      new Landroid/widget/TextView(p0).setTextSize(((float) p2));
      new Landroid/widget/TextView(p0).setTextColor(p3);
      new Landroid/widget/TextView(p0).setGravity(0x10);
      return new Landroid/widget/TextView(p0);
    }

    public static void c(m p0) {
      if ((p0.getResources().getConfiguration().uiMode & 0x30) != 0x20) goto :cond_17;
      goto :goto_18;
      :cond_17:
      :goto_18:
      new Ldev/local/newsoul/L2(0x1, p0.getResources().getDisplayMetrics().density);
      if (0x1 == 0) goto :cond_2d;
      goto :goto_30;
      :cond_2d:
      :goto_30:
      if (0x1 == 0) goto :cond_36;
      goto :goto_39;
      :cond_36:
      :goto_39:
      new Landroid/app/Dialog(p0);
      new Landroid/widget/LinearLayout(p0);
      new Landroid/widget/LinearLayout(p0).setOrientation(0x1);
      new Landroid/widget/LinearLayout(p0).setPadding(Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0xa), Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0x14));
      new Ldev/local/newsoul/L2(0x1, p0.getResources().getDisplayMetrics().density).b(new Landroid/widget/LinearLayout(p0), 0x1, 0x41c00000    # 24.0f);
      new Landroid/view/View(p0);
      if (0x1 == 0) goto :cond_70;
      goto :goto_73;
      :cond_70:
      :goto_73:
      new Landroid/graphics/drawable/GradientDrawable();
      new Landroid/graphics/drawable/GradientDrawable().setColor(-0xbabaa9);
      new Landroid/graphics/drawable/GradientDrawable().setCornerRadius(((float) Ldev/local/newsoul/F0.a(p0, 0x3)));
      new Landroid/view/View(p0).setBackground(new Landroid/graphics/drawable/GradientDrawable());
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x20), Ldev/local/newsoul/F0.a(p0, 0x4));
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x20), Ldev/local/newsoul/F0.a(p0, 0x4)).gravity = 0x1;
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x20), Ldev/local/newsoul/F0.a(p0, 0x4)).bottomMargin = Ldev/local/newsoul/F0.a(p0, 0x8);
      new Landroid/widget/LinearLayout(p0).addView(new Landroid/view/View(p0), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x20), Ldev/local/newsoul/F0.a(p0, 0x4)));
      new Landroid/widget/LinearLayout(p0);
      new Landroid/widget/LinearLayout(p0).setGravity(0x10);
      if (p1 == 0) goto :cond_b1;
      goto :goto_b3;
      :cond_b1:
      :goto_b3:
      Ldev/local/newsoul/F0.b(p0, "骰子", 0x14, -0x1a1a12).setTypeface(0x0, 0x1);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, Ldev/local/newsoul/F0.a(p0, 0x30), 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "骰子", 0x14, -0x1a1a12), new Landroid/widget/LinearLayout$LayoutParams(0x0, Ldev/local/newsoul/F0.a(p0, 0x30), 0x3f800000    # 1.0f));
      if (p2 == 0) goto :cond_eb;
      Ldev/local/newsoul/F0.b(p0, "仅预览", 0xc, -0x6d6d58).setPadding(Ldev/local/newsoul/F0.a(p0, 0x8), 0x0, Ldev/local/newsoul/F0.a(p0, 0x8), 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, Ldev/local/newsoul/F0.a(p0, 0x20));
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "仅预览", 0xc, -0x6d6d58), new Landroid/widget/LinearLayout$LayoutParams(-0x2, Ldev/local/newsoul/F0.a(p0, 0x20)));
      :cond_eb:
      Ldev/local/newsoul/F0.b(p0, "×", 0x1c, -0x6d6d58).setGravity(0x11);
      new Ldev/local/newsoul/L2(0x1, p0.getResources().getDisplayMetrics().density).b(Ldev/local/newsoul/F0.b(p0, "×", 0x1c, -0x6d6d58), 0xc, Ldev/local/newsoul/L2.e(0xc));
      Ldev/local/newsoul/F0.b(p0, "×", 0x1c, -0x6d6d58).setContentDescription("取消");
      new Ldev/local/newsoul/A0(0x0, new Landroid/app/Dialog(p0));
      Ldev/local/newsoul/F0.b(p0, "×", 0x1c, -0x6d6d58).setOnClickListener(new Ldev/local/newsoul/A0(0x0, new Landroid/app/Dialog(p0)));
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x30), Ldev/local/newsoul/F0.a(p0, 0x30));
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "×", 0x1c, -0x6d6d58), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x30), Ldev/local/newsoul/F0.a(p0, 0x30)));
      new Landroid/widget/LinearLayout(p0).addView(new Landroid/widget/LinearLayout(p0));
      new Ljava/util/ArrayList();
      new Ljava/util/ArrayList();
      if (p2 == 0) goto :cond_134;
      goto :goto_136;
      :cond_134:
      :goto_136:
      Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb).setGravity(0x11);
      Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb).setTypeface(0x0, 0x1);
      Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb).setEnabled(0x0);
      Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb).setAlpha(0x3eb33333    # 0.35f);
      new Ldev/local/newsoul/L2(0x1, p0.getResources().getDisplayMetrics().density).b(Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb), 0x7, 0x41800000    # 16.0f);
      0x7.<init>(new Ljava/util/ArrayList(), new Ljava/util/ArrayList(), new int[][] {-0x1}, Ldev/local/newsoul/F0.b(p0, "预览", 0x11, -0xf9c5cb), 0x1);
      if (p1 == 0) goto :cond_17d;
      new int[][0x6][0x0] = 0x1;
      new int[][0x6][0x1] = 0x2;
      new int[][0x6][0x2] = 0x3;
      new int[][0x6][0x3] = 0x4;
      new int[][0x6][0x4] = 0x5;
      new int[][0x6][0x5] = 0x6;
      goto :goto_189;
      :cond_17d:
      new int[][0x3][0x0] = 0x3;
      new int[][0x3][0x1] = 0x2;
      new int[][0x3][0x2] = 0x1;
      :goto_189:
      :goto_18a:
      if (p1 == 0) goto :cond_18e;
      goto :goto_18f;
      :cond_18e:
      :goto_18f:
      if (0x6 >= 0x2) goto :cond_309;
      new Landroid/widget/LinearLayout(p0);
      :goto_197:
      if (0x5 >= 0x3) goto :cond_2d8;
      new Landroid/widget/FrameLayout(p0);
      if (p1 == 0) goto :cond_1be;
      new Ljava/lang/StringBuilder();
      goto :goto_1d0;
      :cond_1be:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x3) goto :cond_1c8;
      goto :goto_1d0;
      :cond_1c8:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x2) goto :cond_1ce;
      goto :goto_1d0;
      :cond_1ce:
      :goto_1d0:
      new Landroid/widget/FrameLayout(p0).setContentDescription("剪刀");
      if (p1 == 0) goto :cond_1e4;
      new Ldev/local/newsoul/E0(p0, new int[][0x6][((0x6 * 0x3) + 0x5)]);
      goto :goto_234;
      :cond_1e4:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x3) goto :cond_1ea;
      goto :goto_1f2;
      :cond_1ea:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x2) goto :cond_1f0;
      goto :goto_1f2;
      :cond_1f0:
      :goto_1f2:
      if (p0.getResources().getIdentifier("finger_scissor", "drawable", p0.getPackageName()) == 0) goto :cond_219;
      new Landroid/widget/ImageView(p0);
      new Landroid/widget/ImageView(p0).setImageResource(p0.getResources().getIdentifier("finger_scissor", "drawable", p0.getPackageName()));
      new Landroid/widget/ImageView(p0).setScaleType(ImageView$ScaleType.FIT_CENTER);
      goto :goto_234;
      :cond_219:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x3) goto :cond_221;
      goto :goto_229;
      :cond_221:
      if (new int[][0x6][((0x6 * 0x3) + 0x5)] != 0x2) goto :cond_227;
      goto :goto_229;
      :cond_227:
      :goto_229:
      Ldev/local/newsoul/F0.b(p0, "✌", 0x24, -0x1a1a12).setGravity(0x11);
      :goto_234:
      "✌".setImportantForAccessibility(0x2);
      new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x3c), Ldev/local/newsoul/F0.a(p0, 0x3c), 0x11);
      new Landroid/widget/FrameLayout(p0).addView("✌", new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x3c), Ldev/local/newsoul/F0.a(p0, 0x3c), 0x11));
      Ldev/local/newsoul/F0.b(p0, "✓", 0xc, -0xf9c5cb).setGravity(0x11);
      Ldev/local/newsoul/F0.b(p0, "✓", 0xc, -0xf9c5cb).setTag("tick");
      new Landroid/graphics/drawable/GradientDrawable();
      new Landroid/graphics/drawable/GradientDrawable().setColor(F0.a);
      new Landroid/graphics/drawable/GradientDrawable().setCornerRadius(((float) Ldev/local/newsoul/F0.a(p0, 0xa)));
      Ldev/local/newsoul/F0.b(p0, "✓", 0xc, -0xf9c5cb).setBackground(new Landroid/graphics/drawable/GradientDrawable());
      Ldev/local/newsoul/F0.b(p0, "✓", 0xc, -0xf9c5cb).setImportantForAccessibility(0x2);
      new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0x14), 0x35);
      new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0x14), 0x35).rightMargin = Ldev/local/newsoul/F0.a(p0, 0x7);
      new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0x14), 0x35).topMargin = Ldev/local/newsoul/F0.a(p0, 0x7);
      new Landroid/widget/FrameLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "✓", 0xc, -0xf9c5cb), new Landroid/widget/FrameLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x14), Ldev/local/newsoul/F0.a(p0, 0x14), 0x35));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, Ldev/local/newsoul/F0.a(p0, 0x5c), 0x3f800000    # 1.0f);
      if (0x5 == 0x2) goto :cond_2af;
      new Landroid/widget/LinearLayout$LayoutParams(0x0, Ldev/local/newsoul/F0.a(p0, 0x5c), 0x3f800000    # 1.0f).rightMargin = Ldev/local/newsoul/F0.a(p0, 0xa);
      :cond_2af:
      0x2.addView(new Landroid/widget/FrameLayout(p0), new Landroid/widget/LinearLayout$LayoutParams(0x0, Ldev/local/newsoul/F0.a(p0, 0x5c), 0x3f800000    # 1.0f));
      new Landroid/widget/FrameLayout(p0).setFocusable(0x1);
      new Ldev/local/newsoul/B0(new int[][] {-0x1}, new int[][0x6][((0x6 * 0x3) + 0x5)], 0x7);
      new Landroid/widget/FrameLayout(p0).setOnClickListener(new Ldev/local/newsoul/B0(new int[][] {-0x1}, new int[][0x6][((0x6 * 0x3) + 0x5)], 0x7));
      goto :goto_197;
      :cond_2d8:
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/F0.a(p0, 0xa);
      new Landroid/widget/LinearLayout(p0).addView(0x2, new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      goto :goto_18a;
      :cond_309:
      new Landroid/widget/LinearLayout(p0);
      new Landroid/widget/LinearLayout(p0).setGravity(0x11);
      Ldev/local/newsoul/F0.b(p0, "⤨", 0x19, 0x2).setImportantForAccessibility(0x2);
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x1e), Ldev/local/newsoul/F0.a(p0, 0x2c));
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "⤨", 0x19, 0x2), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/F0.a(p0, 0x1e), Ldev/local/newsoul/F0.a(p0, 0x2c)));
      new Landroid/widget/LinearLayout(p0).addView(Ldev/local/newsoul/F0.b(p0, "随机", 0xf, -0x1a1a12));
      new Landroid/widget/LinearLayout(p0).setContentDescription("随机结果");
      new Landroid/widget/LinearLayout(p0).setFocusable(0x1);
      new Ldev/local/newsoul/C0(new int[][] {-0x1}, 0x7, 0x0);
      new Landroid/widget/LinearLayout(p0).setOnClickListener(new Ldev/local/newsoul/C0(new int[][] {-0x1}, 0x7, 0x0));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x30));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x30)).topMargin = Ldev/local/newsoul/F0.a(p0, 0xc);
      new Landroid/widget/LinearLayout(p0).addView(new Landroid/widget/LinearLayout(p0), new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x30)));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x34));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x34)).topMargin = Ldev/local/newsoul/F0.a(p0, 0x10);
      new Landroid/widget/LinearLayout(p0).addView(new Ldev/local/newsoul/E0(p0, new int[][0x6][((0x6 * 0x3) + 0x5)]), new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x34)));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x34)).<init>(new int[][] {-0x1}, new Ldev/local/newsoul/E0(p0, new int[][0x6][((0x6 * 0x3) + 0x5)]), " 点", p3, 0x0);
      new Ldev/local/newsoul/E0(p0, new int[][0x6][((0x6 * 0x3) + 0x5)]).setOnClickListener(new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/F0.a(p0, 0x34)));
      :goto_3b4:
      if (new Ljava/util/ArrayList().hasNext() == 0) goto :cond_3c7;
      new Ljava/lang/StringBuilder().toString().b((View) new Ljava/util/ArrayList().next(), 0x5, " 点");
      goto :goto_3b4;
      :cond_3c7:
      0x2.run();
      " 点".setContentView(new Ljava/util/ArrayList());
      new Ldev/local/newsoul/j0(0x1, p4);
      " 点".setOnDismissListener(new Ldev/local/newsoul/j0(0x1, p4));
      " 点".setCanceledOnTouchOutside(0x1);
      " 点".getWindow().setBackgroundDrawableResource(0x106000d);
      " 点".getWindow().addFlags(new Ljava/lang/StringBuilder().toString());
      " 点".getWindow().getAttributes().dimAmount = 0x3f000000    # 0.5f;
      " 点".getWindow().setAttributes(" 点".getWindow().getAttributes());
      " 点".getWindow().setGravity(0x51);
      " 点".show();
      " 点".getWindow().setLayout(Ljava/lang/Math.min((p0.getResources().getDisplayMetrics().widthPixels - Ldev/local/newsoul/F0.a(p0, 0x18)), Ldev/local/newsoul/F0.a(p0, 0x1a4)), 0x2);
      " 点".getWindow().getAttributes().y = Ldev/local/newsoul/F0.a(p0, 0xc);
      " 点".getWindow().setAttributes(" 点".getWindow().getAttributes());
      return;
    }

}