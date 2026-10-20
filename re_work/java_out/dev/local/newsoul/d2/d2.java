package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/d2 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class d2 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final EditText a;
    public final ArrayList b;
    public final TextView c;
    public final R0 d;
    public final q2 e;
    public final Activity f;
    public final String[] g;
    public final String[] h;
    public final int[] i;
    public final TextView j;
    public final TextView k;

    public void super(TextView p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p7;
      this.h = p8;
      this.i = p9;
      this.j = p10;
      this.k = p11;
      return;
    }

    public final void run() {
      if (p0.a.getText().toString().trim().matches("(?s).*\\\\R.*") == 0) goto :cond_1c;
      :goto_1a:
      goto :goto_23;
      :cond_1c:
      goto :goto_1a;
      :goto_23:
      :goto_2b:
      if (p0.a.getText().toString().trim().toLowerCase(Locale.ROOT).hasNext() == 0) goto :cond_3d;
      goto :goto_2b;
      :cond_3d:
      if ((v2 | (String) p0.a.getText().toString().trim().toLowerCase(Locale.ROOT).next().equalsIgnoreCase(p0.a.getText().toString().trim())) == 0) goto :cond_42;
      goto :goto_44;
      :cond_42:
      :goto_44:
      p0.c.setText("已添加");
      if (p0.a.getText().toString().trim().isEmpty() != 0) goto :cond_53;
      if ((v2 | (String) p0.a.getText().toString().trim().toLowerCase(Locale.ROOT).next().equalsIgnoreCase(p0.a.getText().toString().trim())) != 0) goto :cond_53;
      goto :goto_54;
      :cond_53:
      :goto_54:
      p0.c.setEnabled(0x1);
      p0.c.setSelected(p0.c.isEnabled());
      p0.d.removeAllViews();
      :goto_65:
      if (v13 >= v9.size()) goto :cond_14e;
      if ((String) v9.get(v13).toLowerCase(Locale.ROOT).contains(p0.a.getText().toString().trim().matches("(?s).*\\\\R.*")) != 0) goto :cond_85;
      goto :goto_149;
      :cond_85:
      new Landroid/widget/LinearLayout(p0.e.g);
      new Landroid/widget/LinearLayout(p0.e.g).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0.e.g).setMinimumHeight(Ldev/local/newsoul/w2.i(p0.f, 0x30));
      if ((String) v9.get(v13).codePointCount(v10, (String) v9.get(v13).length()) > 0x8) goto :cond_a8;
      goto :goto_a9;
      :cond_a8:
      :goto_a9:
      Ldev/local/newsoul/w2.n(p0.e.g, (String) v9.get(v13), 0x10, p0.e.i).setPadding(Ldev/local/newsoul/w2.i(p0.f, 0xa), Ldev/local/newsoul/w2.i(p0.f, 0x8), v10, Ldev/local/newsoul/w2.i(p0.f, 0x8));
      new Landroid/widget/LinearLayout$LayoutParams(v10, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.e.g).addView(Ldev/local/newsoul/w2.n(p0.e.g, (String) v9.get(v13), 0x10, p0.e.i), new Landroid/widget/LinearLayout$LayoutParams(v10, -0x2, 0x3f800000    # 1.0f));
      if (0x1 == 0) goto :cond_d2;
      goto :goto_d4;
      :cond_d2:
      :goto_d4:
      if (0x1 == 0) goto :cond_e5;
      goto :goto_e7;
      :cond_e5:
      :goto_e7:
      new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.f, 0x30), -0x1);
      new Landroid/widget/LinearLayout(p0.e.g).addView(Ldev/local/newsoul/w2.m(p0.e, "×", "删除屏蔽词：".concat((String) v9.get(v13))), new Landroid/widget/LinearLayout$LayoutParams(Ldev/local/newsoul/w2.i(p0.f, 0x30), -0x1));
      Ldev/local/newsoul/w2.m(p0.e, "×", "删除屏蔽词：".concat((String) v9.get(v13))).setMinHeight(Ldev/local/newsoul/w2.i(p0.f, 0x30));
      if (0x1 == 0) goto :cond_111;
      p0.e.c.b(new Landroid/widget/LinearLayout(p0.e.g), 0x5, 0x41800000    # 16.0f);
      new Landroid/widget/LinearLayout(p0.e.g).setSelected((String) v9.get(v13).equals(p0.g[0x0]));
      :cond_111:
      if (0x1 == 0) goto :cond_12c;
      :goto_12a:
      goto :goto_12e;
      :cond_12c:
      goto :goto_12a;
      :goto_12e:
      new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2);
      v12.addView(new Landroid/widget/LinearLayout(p0.e.g), new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2));
      -0x1.<init>(p0, p0.h, p0.b, v13, p0.i, p0.j);
      Ldev/local/newsoul/w2.m(p0.e, "×", "删除屏蔽词：".concat((String) v9.get(v13))).setOnClickListener(-0x1);
      :goto_149:
      goto :goto_65;
      :cond_14e:
      if (p0.a.getText().toString().trim().matches("(?s).*\\\\R.*").isEmpty() == 0) goto :cond_16a;
      new Ljava/lang/StringBuilder();
      goto :goto_189;
      :cond_16a:
      new Ljava/lang/StringBuilder("匹配 ");
      :goto_189:
      p0.k.setText(new Ljava/lang/StringBuilder().toString());
      if (new Landroid/widget/LinearLayout(p0.e.g) != 0) goto :cond_1bb;
      if (p0.a.getText().toString().trim().matches("(?s).*\\\\R.*").isEmpty() == 0) goto :cond_19b;
      goto :goto_19d;
      :cond_19b:
      :goto_19d:
      Ldev/local/newsoul/w2.n(p0.e.g, "暂无屏蔽词", 0xe, p0.e.j).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.f, Ldev/local/newsoul/w2.i(p0.f, 0x30)), 0x0, Ldev/local/newsoul/w2.i(p0.f, Ldev/local/newsoul/w2.i(p0.f, 0x30)));
      new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2);
      v12.addView(Ldev/local/newsoul/w2.n(p0.e.g, "暂无屏蔽词", 0xe, p0.e.j), new Landroid/view/ViewGroup$LayoutParams(-0x1, -0x2));
      :cond_1bb:
      return;
    }

}