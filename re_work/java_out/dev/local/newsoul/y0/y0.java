package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/y0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class y0 extends Ljava/lang/Object {

    public static final String[] h;
    public final String a;
    public final r2 b;
    public final EditText c;
    public final String d;
    public final String[] e;
    public final String f;
    public int g;

    static void static {}() {
      y0.h = new String[][] {"absolute", "delta"};
      return;
    }

    public void super(String p0) {
      super(v10);
      p0.<init>();
      p0.e = new String[][0x2];
      p0.a = p4;
      p0.d = p3;
      p0.b = Ldev/local/newsoul/w2.r(p1, p3, 0x0, 0x0).d(0x0, p4.concat("enabled"), "伪装".concat(p3), "", p1.i);
      Ldev/local/newsoul/w2.r(p1, p3, 0x0, 0x0).d(0x0, p4.concat("enabled"), "伪装".concat(p3), "", p1.i).setChecked(p2.getBoolean(p4.concat("enabled"), 0x0));
      new Landroid/widget/LinearLayout(p1.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout(p1.g).setPadding(0x0, Ldev/local/newsoul/w2.i(p1.g, 0x8), 0x0, 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      Ldev/local/newsoul/w2.r(p1, p3, 0x0, 0x0).addView(new Landroid/widget/LinearLayout(p1.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/C1(p1, 0x0, new String[][] {"直接修改", "原有增减"}, 0xd);
      new Ldev/local/newsoul/C1(p1, 0x0, new String[][] {"直接修改", "原有增减"}, 0xd).setContentDescription(p3.concat("修改方式"));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout(p1.g).addView(new Ldev/local/newsoul/C1(p1, 0x0, new String[][] {"直接修改", "原有增减"}, 0xd), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      p0.g = "delta".equals(p2.getString(p4.concat("mode"), "absolute"));
      :goto_9a:
      if (v9 >= 0x2) goto :cond_bf;
      new Ljava/lang/StringBuilder();
      v0.e[v9] = Ljava/lang/String.valueOf(v2.getInt(new Ljava/lang/StringBuilder().toString(), v6));
      goto :goto_9a;
      :cond_bf:
      v8.b(Ljava/lang/String.valueOf(v2.getInt(new Ljava/lang/StringBuilder().toString(), v6))[v0.g], Ljava/lang/String.valueOf(v2.getInt(new Ljava/lang/StringBuilder().toString(), v6)), v6);
      new Landroid/widget/EditText(v10);
      v0.c = new Landroid/widget/EditText(v10);
      new Landroid/widget/EditText(v10).setTextColor(v1.i);
      new Landroid/widget/EditText(v10).setHintTextColor(v1.j);
      new Landroid/widget/EditText(v10).setTextSize(0x41800000    # 16.0f);
      new Landroid/widget/EditText(v10).setMinHeight(Ldev/local/newsoul/w2.i(v10, 0x30));
      new Landroid/widget/EditText(v10).setSingleLine(v7);
      new Landroid/widget/EditText(v10).setPadding(Ldev/local/newsoul/w2.i(v10, 0xe), Ldev/local/newsoul/w2.i(v10, 0xa), Ldev/local/newsoul/w2.i(v10, 0xe), Ldev/local/newsoul/w2.i(v10, 0xa));
      new Landroid/graphics/drawable/StateListDrawable();
      if (v1.c.a == 0) goto :cond_129;
      goto :goto_12c;
      :cond_129:
      :goto_12c:
      if (v1.c.a == 0) goto :cond_134;
      :goto_131:
      goto :goto_138;
      :cond_134:
      goto :goto_131;
      :goto_138:
      if (v1.c.a == 0) goto :cond_13e;
      goto :goto_141;
      :cond_13e:
      :goto_141:
      :goto_146:
      if (-0x9f9f92 >= 0x3) goto :cond_172;
      if (-0x9f9f92 != 0x1) goto :cond_157;
      goto :goto_159;
      :cond_157:
      :goto_159:
      Ldev/local/newsoul/L2.g(v6, (Ldev/local/newsoul/L2.e(v11) * v1.c.b), v6).setStroke(Ljava/lang/Math.max(0x1, (0x3fc00000    # 1.5f * v1.c.b).round()), v18[-0x9f9f92]);
      new Landroid/graphics/drawable/StateListDrawable().addState(new int[][][] {new int[][] {-0x101009e}, new int[][] {0x101009c}, new int[][v6]}[-0x9f9f92], Ldev/local/newsoul/L2.g(v6, (Ldev/local/newsoul/L2.e(v11) * v1.c.b), v6));
      goto :goto_146;
      :cond_172:
      new Drawable[][0x2][0x0] = v1.c.c(0x8, Ldev/local/newsoul/L2.e(v11));
      new Drawable[][0x2][0x1] = new Landroid/graphics/drawable/StateListDrawable();
      new Landroid/graphics/drawable/LayerDrawable(new Drawable[][0x2]);
      Ldev/local/newsoul/L2.i(new Landroid/widget/EditText(v10), new Landroid/graphics/drawable/LayerDrawable(new Drawable[][0x2]));
      new Landroid/widget/EditText(v10).setBackground(new Landroid/graphics/drawable/LayerDrawable(new Drawable[][0x2]));
      new Landroid/widget/EditText(v10).setPadding(new Landroid/widget/EditText(v10).getPaddingLeft(), new Landroid/widget/EditText(v10).getPaddingTop(), new Landroid/widget/EditText(v10).getPaddingRight(), new Landroid/widget/EditText(v10).getPaddingBottom());
      new Landroid/widget/LinearLayout(v10);
      new Landroid/widget/LinearLayout(v10).setGravity(0x10);
      new Ljava/lang/StringBuilder("减少");
      new Ljava/lang/StringBuilder("增加");
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(v10, 0x30), -0x2);
      new Landroid/widget/LinearLayout(v10).addView(v0.c(v1, "−", new Ljava/lang/StringBuilder("减少").toString(), -0x1), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(v10, 0x30), -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f).rightMargin = Ldev/local/newsoul/w2.i(v10, 0x8);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f).leftMargin = Ldev/local/newsoul/w2.i(v10, 0x8);
      new Landroid/widget/LinearLayout(v10).addView(v0.c, new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(v10, 0x30), -0x2);
      new Landroid/widget/LinearLayout(v10).addView(v0.c(v1, "+", new Ljava/lang/StringBuilder("增加").toString(), 0x1), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(v10, 0x30), -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/w2.i(v10, 0xc);
      v5.addView(new Landroid/widget/LinearLayout(v10), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      p0.d();
      new Ldev/local/newsoul/g(v5, v0.b, 0xa);
      v0.b.a(new Ldev/local/newsoul/g(v5, v0.b, 0xa));
      new Ldev/local/newsoul/v0(v0, v1);
      v0.b.setOnCheckedChangeListener(new Ldev/local/newsoul/v0(v0, v1));
      new Ldev/local/newsoul/w0(v0, v8);
      v8.h = new Ldev/local/newsoul/w0(v0, v8);
      v0.f = p0.b();
      return;
    }

    public final void a(SharedPreferences$Editor p0) {
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      :goto_38:
      if (v0 >= 0x2) goto :cond_5a;
      new Ljava/lang/StringBuilder();
      goto :goto_38;
      :cond_5a:
      return;
    }

    public final String b() {
      if (p0.g != 0) goto :cond_13;
      goto :goto_16;
      :cond_13:
      :goto_16:
      if (p0.g != 0x1) goto :cond_24;
      goto :goto_26;
      :cond_24:
      :goto_26:
      new Ljava/lang/StringBuilder();
      return new Ljava/lang/StringBuilder().toString();
    }

    public final TextView c(Ldev/local/newsoul/q2;Ljava/lang/String;Ljava/lang/String;I p0) {
      Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i).setGravity(0x11);
      Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i).setMinHeight(Ldev/local/newsoul/w2.i(p1.g, 0x30));
      Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i).setContentDescription(p3);
      p1.c.b(Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i), 0xa, Ldev/local/newsoul/L2.e(0xa));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i));
      new Ldev/local/newsoul/x0(p0, p4, 0x0);
      Ldev/local/newsoul/w2.n(p1.g, p2, 0x16, p1.i).setOnClickListener(new Ldev/local/newsoul/x0(p0, p4, 0x0));
      return Ldev/local/newsoul/L2.e(0xa);
    }

    public final void d() {
      if (p0.g != 0) goto :cond_7;
      goto :goto_9;
      :cond_7:
      :goto_9:
      new Ljava/lang/StringBuilder();
      p0.c.setContentDescription(new Ljava/lang/StringBuilder().toString());
      if (p0.g != 0x1) goto :cond_27;
      goto :goto_28;
      :cond_27:
      :goto_28:
      p0.c.setInputType((0x1000 | 0x2));
      p0.c.setGravity(0x11);
      p0.c.setText(p0.e[p0.g]);
      p0.c.setSelection(p0.c.length());
      p0.c.setError(0x0);
      return;
    }

    public final boolean e() {
      :try_start_3:
      if (p0.g != 0x1) goto :cond_16;
      goto :goto_17;
      :cond_16:
      :goto_17:
      p0.e[p0.g] = Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.P(v0.getText().toString(), 0x1));
      v0.setError(0x0);
      :try_end_25:
      return v0;
      :catch_26:
      v0.setError(e.getMessage());
      return v0;

      // try :try_start_3..:try_end_25 catch (Ljava/lang/IllegalArgumentException) -> :catch_26
    }

    public final boolean f() {
      if (p0.b.isChecked() == 0) goto :cond_d;
      return p0.e();
      :cond_d:
      :try_start_e:
      if (p0.g != p0.b.isChecked()) goto :cond_22;
      goto :goto_23;
      :cond_22:
      :goto_23:
      p0.e[p0.g] = Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.P(p0.c.getText().toString(), p0.b.isChecked()));
      :try_end_2d:
      :catch_2d:
      return p0.b.isChecked();

      // try :try_start_e..:try_end_2d catch (Ljava/lang/IllegalArgumentException) -> :catch_2d
    }

}