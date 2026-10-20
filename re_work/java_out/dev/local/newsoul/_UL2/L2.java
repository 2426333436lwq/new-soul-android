package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/L2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class L2 extends Ljava/lang/Object {

    public static final WeakHashMap d;
    public static final WeakHashMap e;
    public final boolean a;
    public final float b;
    public boolean c;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      L2.d = new Ljava/util/WeakHashMap();
      new Ljava/util/WeakHashMap();
      L2.e = new Ljava/util/WeakHashMap();
      return;
    }

    public void super(ZF p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public static float e(int p0) {
      if (p0 == 0) goto :cond_2a;
      if ((p0 + -0x1) == 0) goto :cond_29;
      if ((p0 + -0x1) == 0x1) goto :cond_28;
      if ((p0 + -0x1) == 0x4) goto :cond_28;
      if ((p0 + -0x1) == 0x6) goto :cond_28;
      if ((p0 + -0x1) == 0x7) goto :cond_25;
      if ((p0 + -0x1) == 0x9) goto :cond_24;
      if ((p0 + -0x1) == 0xa) goto :cond_23;
      return 0xa;
      :cond_23:
      return 0xa;
      :cond_24:
      return 0xa;
      :cond_25:
      return 0xa;
      :cond_28:
      return 0xa;
      :cond_29:
      return 0xa;
      :cond_2a:
      throw 0x0;
    }

    public static void f(Drawable p0) {
      if ((p0 instanceof G0) != 0) goto :cond_4d;
      if ((p0 instanceof InsetDrawable) == 0) goto :cond_12;
      Ldev/local/newsoul/L2.f((InsetDrawable) p0.getDrawable());
      goto :goto_4c;
      :cond_12:
      if (((InsetDrawable) p0.getDrawable() instanceof LayerDrawable) == 0) goto :cond_29;
      :goto_19:
      if (0x0 >= (InsetDrawable) p0.getDrawable().getNumberOfLayers()) goto :cond_4c;
      Ldev/local/newsoul/L2.f((InsetDrawable) p0.getDrawable().getDrawable(0x0));
      goto :goto_19;
      :cond_29:
      if (((InsetDrawable) p0.getDrawable() instanceof StateListDrawable) == 0) goto :cond_4c;
      if (Build$VERSION.SDK_INT < 0x1d) goto :cond_45;
      :goto_35:
      if ((0x0 + 0x1) >= Ldev/local/newsoul/H0.b((StateListDrawable) (InsetDrawable) p0.getDrawable())) goto :cond_4c;
      Ldev/local/newsoul/L2.f(Ldev/local/newsoul/H0.e((StateListDrawable) (InsetDrawable) p0.getDrawable(), (0x0 + 0x1)));
      goto :goto_35;
      :cond_45:
      Ldev/local/newsoul/L2.f((StateListDrawable) (InsetDrawable) p0.getDrawable().getCurrent());
      :cond_4c:
      :goto_4c:
      return;
      :cond_4d:
      throw 0x0;
    }

    public static GradientDrawable g(IFZ p0) {
      new Landroid/graphics/drawable/GradientDrawable();
      new Landroid/graphics/drawable/GradientDrawable().setColor(p0);
      if (p2 == 0) goto :cond_2b;
      new float[][0x8][0x0] = p1;
      new float[][0x8][0x1] = p1;
      new float[][0x8][0x2] = p1;
      new float[][0x8][0x3] = p1;
      new float[][0x8][0x4] = 0x0;
      new float[][0x8][0x5] = 0x0;
      new float[][0x8][0x6] = 0x0;
      new float[][0x8][0x7] = 0x0;
      new Landroid/graphics/drawable/GradientDrawable().setCornerRadii(new float[][0x8]);
      goto :goto_2e;
      :cond_2b:
      new Landroid/graphics/drawable/GradientDrawable().setCornerRadius(0x7);
      :goto_2e:
      return 0x0;
    }

    public static void i(Drawable p0) {
      if ((Drawable) L2.d.put(p0, p1) == p1) goto :cond_d;
      Ldev/local/newsoul/L2.f((Drawable) L2.d.put(p0, p1));
      :cond_d:
      if (L2.e.put(p0, Boolean.TRUE) == 0) goto :cond_18;
      return;
      :cond_18:
      new Ldev/local/newsoul/D2(0x1);
      p0.addOnAttachStateChangeListener(new Ldev/local/newsoul/D2(0x1));
      return;
    }

    public final int a() {
      if (p0.a == 0) goto :cond_8;
      goto :goto_b;
      :cond_8:
      :goto_b:
      return -0xff4a5e;
    }

    public final void b(Landroid/view/View;IF p0) {
      Ldev/local/newsoul/L2.i(p1, p0.c(p2, p3));
      p1.setBackground(p0.c(p2, p3));
      p1.setPadding(p1.getPaddingLeft(), p1.getPaddingTop(), p1.getPaddingRight(), p1.getPaddingBottom());
      if (p2 != 0x4) goto :cond_33;
      p1.setForeground(0x0);
      if (p0.a == 0) goto :cond_2d;
      goto :goto_2f;
      :cond_2d:
      :goto_2f:
      p1.setElevation((0x3f000000    # 0.5f * p0.b));
      :cond_33:
      if (p2 != 0x3) goto :cond_42;
      if (p0.c == 0) goto :cond_3e;
      if (p0.a != 0) goto :cond_3e;
      goto :goto_3f;
      :cond_3e:
      :goto_3f:
      p1.setElevation(p0.b);
      :cond_42:
      if ((p1 instanceof TextView) == 0) goto :cond_a9;
      if ((p1 instanceof EditText) != 0) goto :cond_a9;
      if (p2 == 0x5) goto :cond_59;
      if (p2 == 0x7) goto :cond_59;
      if (p2 == 0x9) goto :cond_59;
      if (p2 != 0xa) goto :cond_a9;
      :cond_59:
      if (p2 != 0xa) goto :cond_5e;
      goto :goto_60;
      :cond_5e:
      :goto_60:
      if (p2 != 0xa) goto :cond_70;
      goto :goto_71;
      :cond_70:
      :goto_71:
      p1.setPadding(Ljava/lang/Math.max(p1.getPaddingLeft(), Ljava/lang/Math.round((((float) 0xc) * p0.b))), Ljava/lang/Math.max(p1.getPaddingTop(), Ljava/lang/Math.round((0x41200000    # 10.0f * p0.b))), Ljava/lang/Math.max(p1.getPaddingRight(), Ljava/lang/Math.round((((float) 0xc) * p0.b))), Ljava/lang/Math.max(p1.getPaddingBottom(), Ljava/lang/Math.round((0x41200000    # 10.0f * p0.b))));
      (TextView) p1.setMinHeight(Ljava/lang/Math.max((TextView) p1.getMinHeight(), Ljava/lang/Math.round((p0.b * 0x42400000    # 48.0f))));
      :cond_a9:
      if ((p1 instanceof TextView) == 0) goto :cond_f1;
      if (p2 == 0x5) goto :cond_af;
      if (p2 != 0x7) goto :cond_f1;
      :cond_af:
      if (p0.a == 0) goto :cond_d4;
      goto :goto_d7;
      :cond_d4:
      :goto_d7:
      if (p2 != 0x7) goto :cond_de;
      goto :goto_e7;
      :cond_de:
      if (p0.a == 0) goto :cond_e4;
      goto :goto_e7;
      :cond_e4:
      :goto_e7:
      new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][] {0x10100a1}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0x888878, -0xe7c3c5, -0xe7c3c5, -0x191911});
      (TextView) p1.setTextColor(new Landroid/content/res/ColorStateList(new int[][][] {new int[][] {-0x101009e}, new int[][] {0x10100a1}, new int[][] {0x10100a0}, new int[][0x0]}, new int[][] {-0x888878, -0xe7c3c5, -0xe7c3c5, -0x191911}));
      :cond_f1:
      return;
    }

    public final Drawable c(IF p0) {
      if (p1 != 0xd) goto :cond_10;
      return p0;
      :cond_10:
      if (p1 != 0x4) goto :cond_52;
      if (p0.a == 0) goto :cond_1e;
      goto :goto_21;
      :cond_1e:
      :goto_21:
      if (p0.a == 0) goto :cond_36;
      goto :goto_38;
      :cond_36:
      :goto_38:
      Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0).setStroke(Ljava/lang/Math.max(0x1, Ljava/lang/Math.round((p0.b * 0x3f000000    # 0.5f))), 0xfffffff);
      if (p0.a == 0) goto :cond_43;
      goto :goto_46;
      :cond_43:
      :goto_46:
      new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x18ffffff), Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), Ldev/local/newsoul/L2.g(-0x1, (p2 * p0.b), 0x0));
      return p0;
      :cond_52:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x9) goto :cond_5a;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0xa) goto :cond_5d;
      :cond_5a:
      goto :goto_139;
      :cond_5d:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0xa) goto :cond_78;
      Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0).<init>(p0.c(0x9, Ljava/lang/Math.max(0x1, Ljava/lang/Math.round((p0.b * 0x3f000000    # 0.5f)))), 0x0, Ljava/lang/Math.round((-0x1 * 0x40c00000    # 6.0f)), 0x0, Ljava/lang/Math.round((-0x1 * 0x40c00000    # 6.0f)));
      return p0;
      :cond_78:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0xc) goto :cond_7f;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x6) goto :cond_82;
      :cond_7f:
      goto :goto_122;
      :cond_82:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x5) goto :cond_96;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x8) goto :cond_96;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x7) goto :cond_8d;
      goto :goto_96;
      :cond_8d:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x7) goto :cond_90;
      goto :goto_91;
      :cond_90:
      :goto_91:
      goto :goto_fc;
      :cond_96:
      :goto_96:
      new Landroid/graphics/drawable/StateListDrawable();
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {-0x101009e, 0x10100a1}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0)));
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {-0x101009e, 0x10100a0}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0)));
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x7) goto :cond_c2;
      goto :goto_c3;
      :cond_c2:
      :goto_c3:
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {-0x101009e}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0)));
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {0x10100a1}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), 0x0));
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {0x10100a0}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), 0x0));
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][] {0x101009c}, p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), 0x0));
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x7) goto :cond_f3;
      goto :goto_f4;
      :cond_f3:
      :goto_f4:
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][0x0], p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), 0x0));
      :goto_fc:
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x5) goto :cond_10a;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x7) goto :cond_10a;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0x9) goto :cond_10a;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) == 0xb) goto :cond_10a;
      if (Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0) != 0x8) goto :cond_109;
      goto :goto_10a;
      :cond_109:
      return p0;
      :cond_10a:
      :goto_10a:
      if (p0.a == 0) goto :cond_112;
      goto :goto_115;
      :cond_112:
      :goto_115:
      new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x22ffffff), p0.h(Ldev/local/newsoul/L2.g(-0xdbdbca, (p2 * p0.b), 0x0), (p2 * p0.b), 0x1, 0x0), Ldev/local/newsoul/L2.g(-0x1, (p2 * p0.b), 0x0));
      return p0;
      :goto_122:
      if (p0.a == 0) goto :cond_12a;
      goto :goto_12d;
      :cond_12a:
      :goto_12d:
      new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x22ffffff), 0x8, Ldev/local/newsoul/L2.g(new int[][0x0], (p2 * p0.b), 0x0));
      return p0;
      :goto_139:
      if (p0.a == 0) goto :cond_141;
      goto :goto_144;
      :cond_141:
      :goto_144:
      new Landroid/graphics/drawable/RippleDrawable(Landroid/content/res/ColorStateList.valueOf(0x22ffffff), 0x8, Ldev/local/newsoul/L2.g(new int[][0x0], (p2 * p0.b), 0x0));
      return p0;
    }

    public final int d() {
      if (p0.a == 0) goto :cond_8;
      goto :goto_11;
      :cond_8:
      if (p0.c == 0) goto :cond_10;
      goto :goto_11;
      :cond_10:
      :goto_11:
      return -0xa0908;
    }

    public final Drawable h(IFZZ p0) {
      if (p1 != 0x2) goto :cond_6;
      goto :goto_7;
      :cond_6:
      :goto_7:
      if (p1 != 0xb) goto :cond_f;
      goto :goto_13;
      :cond_f:
      :goto_13:
      if (p1 != 0x3) goto :cond_34;
      if (p0.c == 0) goto :cond_2e;
      if (p0.a == 0) goto :cond_2c;
      :goto_2a:
      goto :goto_47;
      :cond_2c:
      goto :goto_2a;
      :cond_2e:
      if (p0.a == 0) goto :cond_32;
      :goto_30:
      goto :goto_47;
      :cond_32:
      goto :goto_47;
      :cond_34:
      if (p1 != -0x1) goto :cond_40;
      if (p0.a == 0) goto :cond_3c;
      goto :goto_2a;
      :cond_3c:
      goto :goto_2a;
      :cond_40:
      if (p1 == 0x5) goto :cond_44;
      if (p1 != 0x8) goto :cond_47;
      :cond_44:
      if (p0.a == 0) goto :cond_32;
      goto :goto_30;
      :cond_47:
      :goto_47:
      if (p3 == 0) goto :cond_5c;
      if (p1 != 0x5) goto :cond_5c;
      if (p4 == 0) goto :cond_59;
      if (p0.a == 0) goto :cond_57;
      goto :goto_5c;
      :cond_57:
      goto :goto_5c;
      :cond_59:
      :cond_5c:
      :goto_5c:
      if (p1 != 0x7) goto :cond_69;
      if (p4 == 0) goto :cond_65;
      if (p0.a == 0) goto :cond_68;
      goto :goto_68;
      :cond_65:
      :cond_68:
      :goto_68:
      :cond_69:
      if (p3 == 0) goto :cond_88;
      if (p1 != 0x8) goto :cond_88;
      if (p4 == 0) goto :cond_81;
      goto :goto_85;
      :cond_81:
      :goto_85:
      Ldev/local/newsoul/L2.g(-0x4b2a32, p2, 0x1).setStroke(Ljava/lang/Math.max(0x1, Ljava/lang/Math.round(p0.b)), -0x7e6a72);
      :cond_88:
      return -0xbd9fa6;
    }

}