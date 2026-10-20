package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/q2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class q2 extends Ljava/lang/Object {

    public final Activity a;
    public final boolean b;
    public final L2 c;
    public final AlertDialog$Builder d;
    public AlertDialog e;
    public LinearLayout f;
    public final Context g;
    public final LinearLayout h;
    public final int i;
    public final int j;
    public final int k;
    public final IdentityHashMap l;
    public final ArrayList m;
    public final ArrayList n;
    public q2 o;
    public final ArrayList p;
    public boolean q;
    public BooleanSupplier r;
    public q2 s;
    public CharSequence t;

    public void super(Landroid/app/Activity;Z p0) {
      super();
      new Ljava/util/IdentityHashMap();
      this.l = new Ljava/util/IdentityHashMap();
      new Ljava/util/ArrayList();
      this.m = new Ljava/util/ArrayList();
      new Ljava/util/ArrayList();
      this.n = new Ljava/util/ArrayList();
      new Ljava/util/ArrayList();
      this.p = new Ljava/util/ArrayList();
      this.a = p1;
      this.b = p2;
      if (p2 == 0) goto :cond_29;
      goto :goto_2f;
      :cond_29:
      :goto_2f:
      this.i = -0x191911;
      if (p2 == 0) goto :cond_37;
      goto :goto_3a;
      :cond_37:
      :goto_3a:
      if (p1.getResources().getIdentifier("color_s_04", "color", p1.getPackageName()) == 0) goto :cond_58;
      :try_start_4c:
      :try_end_58:
      :catch_58:
      :cond_58:
      if (p2 == 0) goto :cond_5e;
      goto :goto_5f;
      :cond_5e:
      :goto_5f:
      if (p2 == 0) goto :cond_65;
      goto :goto_68;
      :cond_65:
      :goto_68:
      this.j = Ldev/local/newsoul/w2.q(-0x7d7d6a, p2, new int[][] {-0xe7e7d8, -0xd7d7c8});
      if (p2 == 0) goto :cond_77;
      goto :goto_7a;
      :cond_77:
      :goto_7a:
      this.k = Ldev/local/newsoul/w2.q(w2.b, p2, new int[][] {-0xe7e7d8, -0xd7d7c8});
      if (p2 == 0) goto :cond_8c;
      goto :goto_8f;
      :cond_8c:
      :goto_8f:
      new Landroid/app/AlertDialog$Builder(p1, 0x1030226);
      this.d = new Landroid/app/AlertDialog$Builder(p1, 0x1030226);
      this.g = new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext();
      new Ldev/local/newsoul/L2(p2, new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext().getResources().getDisplayMetrics().density);
      this.c = new Ldev/local/newsoul/L2(p2, new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext().getResources().getDisplayMetrics().density);
      new Landroid/widget/LinearLayout(new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext());
      this.h = new Landroid/widget/LinearLayout(new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext());
      new Landroid/widget/LinearLayout(new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext()).setOrientation(0x1);
      new Landroid/widget/LinearLayout(new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext()).setTag(new Ljava/lang/Object());
      new Landroid/widget/LinearLayout(new Landroid/app/AlertDialog$Builder(p1, 0x1030226).getContext()).setPadding(Ldev/local/newsoul/w2.i(p1, 0x10), Ldev/local/newsoul/w2.i(p1, 0x8), Ldev/local/newsoul/w2.i(p1, 0x10), Ldev/local/newsoul/w2.i(p1, 0x10));
      return;

      // try :try_start_4c..:try_end_58 catch (Ljava/lang/RuntimeException) -> :catch_58
    }

    public final void a(Runnable p0) {
      return;
    }

    public final void b() {
      if (Build$VERSION.SDK_INT < 0x1e) goto :cond_2e;
      if (p0.e.getWindow().getDecorView().getRootWindowInsets() == 0) goto :cond_2e;
      if (Ldev/local/newsoul/k.i(p0.e.getWindow().getDecorView().getRootWindowInsets(), p0.e.getWindow().getDecorView().getRootWindowInsets()) == 0) goto :cond_2e;
      if (Ldev/local/newsoul/k.e(p0.e.getWindow().getDecorView()) == 0) goto :cond_2e;
      Ldev/local/newsoul/k.g(Ldev/local/newsoul/k.e(p0.e.getWindow().getDecorView()), Ldev/local/newsoul/k.e(p0.e.getWindow().getDecorView()));
      return;
      :cond_2e:
      p0.f();
      return;
    }

    public final void c(BooleanSupplier p0) {
      if (p0.e != 0) goto :cond_14;
      p0.r = p1;
      new Ldev/local/newsoul/L(0x1);
      return;
      :cond_14:
      new Ljava/lang/IllegalStateException("Protect the draft before showing it");
      throw new Ljava/lang/IllegalStateException("Protect the draft before showing it");
    }

    public final void d() {
      :goto_6:
      if (v0.hasNext() == 0) goto :cond_16;
      (j2) v0.next().b();
      goto :goto_6;
      :cond_16:
      :goto_1c:
      if (v0.hasNext() == 0) goto :cond_2c;
      (Runnable) v0.next().run();
      goto :goto_1c;
      :cond_2c:
      return;
    }

    public final void e() {
      if (p0.o == 0) goto :cond_8;
      p0.o.e();
      return;
      :cond_8:
      p0.d();
      :goto_11:
      if (p0.o.hasNext() == 0) goto :cond_21;
      (q2) p0.o.next().d();
      goto :goto_11;
      :cond_21:
      return;
    }

    public final void f() {
      if (p0.e.isShowing() == 0) goto :cond_76;
      if (p0.a.isFinishing() != 0) goto :cond_76;
      if (p0.a.isDestroyed() == 0) goto :cond_17;
      goto :goto_76;
      :cond_17:
      if (p0.r.getAsBoolean() != 0) goto :cond_25;
      p0.e.dismiss();
      return;
      :cond_25:
      if (p0.s == 0) goto :cond_32;
      if (p0.s.e.isShowing() == 0) goto :cond_32;
      return;
      :cond_32:
      new Ldev/local/newsoul/q2(p0.a, p0.b);
      p0.s = new Ldev/local/newsoul/q2(p0.a, p0.b);
      new Ldev/local/newsoul/q2(p0.a, p0.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0.a, p0.b).g, "有尚未保存的修改。放弃后这些修改不会生效。", 0xf, new Ldev/local/newsoul/q2(p0.a, p0.b).i));
      new Ldev/local/newsoul/o2(p0);
      new Ldev/local/newsoul/q2(p0.a, p0.b).g("放弃未保存的修改？", "继续编辑");
      new Ldev/local/newsoul/q2(p0.a, p0.b).e.setCanceledOnTouchOutside(0x0);
      new Ldev/local/newsoul/c1(p0, p0.e.getCurrentFocus(), 0x1);
      new Ldev/local/newsoul/q2(p0.a, p0.b).e.setOnDismissListener(new Ldev/local/newsoul/c1(p0, p0.e.getCurrentFocus(), 0x1));
      :cond_76:
      :goto_76:
      return;
    }

    public final void g(String p0) {
      new Landroid/widget/ScrollView(p0.g);
      new Landroid/widget/ScrollView(p0.g).addView(p0.h);
      p0.i(p1, p2, new Landroid/widget/ScrollView(p0.g));
      return;
    }

    public final void h(IntConsumer p0) {
      if (p1.isAttachedToWindow() == 0) goto :cond_1bd;
      if (p1.isShown() == 0) goto :cond_1bd;
      if (p0.a.isFinishing() != 0) goto :cond_1bd;
      if (p0.a.isDestroyed() == 0) goto :cond_24;
      goto :goto_1bd;
      :cond_24:
      new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout(p0.g).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.g, 0x4), 0x0, Ldev/local/newsoul/w2.i(p0.g, 0x4));
      new Landroid/widget/ScrollView(p0.g);
      p0.c.b(new Landroid/widget/ScrollView(p0.g), 0x1, 0x41600000    # 14.0f);
      new Landroid/widget/ScrollView(p0.g).setClipToOutline(0x1);
      new Landroid/widget/ScrollView(p0.g).addView(new Landroid/widget/LinearLayout(p0.g));
      new Landroid/widget/PopupWindow(p0.g);
      new Landroid/widget/PopupWindow(p0.g).setContentView(new Landroid/widget/ScrollView(p0.g));
      new Landroid/graphics/drawable/ColorDrawable(0x0);
      new Landroid/widget/PopupWindow(p0.g).setBackgroundDrawable(new Landroid/graphics/drawable/ColorDrawable(0x0));
      new Landroid/widget/PopupWindow(p0.g).setFocusable(0x1);
      new Landroid/widget/PopupWindow(p0.g).setOutsideTouchable(0x1);
      new Landroid/widget/PopupWindow(p0.g).setInputMethodMode(0x2);
      if (p0.b == 0) goto :cond_6e;
      goto :goto_70;
      :cond_6e:
      :goto_70:
      new Landroid/widget/PopupWindow(p0.g).setElevation(((float) Ldev/local/newsoul/w2.i(p0.g, 0x4)));
      :goto_80:
      if (v3 >= p2.length) goto :cond_13c;
      if (p3 == 0) goto :cond_8a;
      goto :goto_8c;
      :cond_8a:
      :goto_8c:
      Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).setGravity(0x800013);
      Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).setPadding(Ldev/local/newsoul/w2.i(p0.g, 0x10), Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0x10), Ldev/local/newsoul/w2.i(p0.g, 0xc));
      Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      p0.c.b(Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i), 0xc, Ldev/local/newsoul/L2.e(0xc));
      Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).setEnabled(p3);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i));
      0xc.<init>(p0, new Landroid/widget/PopupWindow(p0.g), p1, p4, v3);
      Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).setOnClickListener(0xc);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout(p0.g).addView(Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      if (v3 >= (p2.length - 0x1)) goto :cond_12d;
      new Landroid/view/View(p0.g);
      if (p0.b == 0) goto :cond_111;
      goto :goto_114;
      :cond_111:
      :goto_114:
      new Landroid/view/View(p0.g).setBackgroundColor(-0xc9c9ba);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1)).rightMargin = Ldev/local/newsoul/w2.i(p0.g, 0x10);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1)).leftMargin = Ldev/local/newsoul/w2.i(p0.g, 0x10);
      new Landroid/widget/LinearLayout(p0.g).addView(new Landroid/view/View(p0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, Ldev/local/newsoul/w2.i(p0.g, 0x1)));
      :cond_12d:
      goto :goto_80;
      :cond_13c:
      new Landroid/graphics/Rect();
      p1.getWindowVisibleDisplayFrame(new Landroid/graphics/Rect());
      new Landroid/widget/LinearLayout(p0.g).measure(Landroid/view/View$MeasureSpec.makeMeasureSpec(Ljava/lang/Math.min(Ljava/lang/Math.max(v4, (Ldev/local/newsoul/w2.i(p0.g, 0x20) + ((int) Ljava/lang/Math.ceil(((double) Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).getPaint().measureText(p2[v3])), p2[v3])))), Ljava/lang/Math.max(Ldev/local/newsoul/w2.i(p0.g, 0x30), (Ljava/lang/Math.min(p1.getRootView().getWidth(), new Landroid/graphics/Rect().width()) - Ldev/local/newsoul/w2.i(p0.g, 0x18)))), 0x40000000    # 2.0f), Landroid/view/View$MeasureSpec.makeMeasureSpec(0x0, 0x0));
      new Landroid/widget/PopupWindow(p0.g).setWidth(Ljava/lang/Math.min(Ljava/lang/Math.max(v4, (Ldev/local/newsoul/w2.i(p0.g, 0x20) + ((int) Ljava/lang/Math.ceil(((double) Ldev/local/newsoul/w2.n(p0.g, p2[v3], 0x10, p0.i).getPaint().measureText(p2[v3])), p2[v3])))), Ljava/lang/Math.max(Ldev/local/newsoul/w2.i(p0.g, 0x30), (Ljava/lang/Math.min(p1.getRootView().getWidth(), new Landroid/graphics/Rect().width()) - Ldev/local/newsoul/w2.i(p0.g, 0x18)))));
      new Landroid/widget/PopupWindow(p0.g).setHeight(Ljava/lang/Math.min(new Landroid/widget/LinearLayout(p0.g).getMeasuredHeight(), Ljava/lang/Math.max(Ldev/local/newsoul/w2.i(p0.g, 0x30), (new Landroid/graphics/Rect().height() - Ldev/local/newsoul/w2.i(p0.g, 0x18)))));
      new Ldev/local/newsoul/p2(0x0, new Landroid/widget/PopupWindow(p0.g));
      p1.addOnAttachStateChangeListener(new Ldev/local/newsoul/p2(0x0, new Landroid/widget/PopupWindow(p0.g)));
      new Ldev/local/newsoul/m2(p1, new Ldev/local/newsoul/p2(0x0, new Landroid/widget/PopupWindow(p0.g)));
      new Landroid/widget/PopupWindow(p0.g).setOnDismissListener(new Ldev/local/newsoul/m2(p1, new Ldev/local/newsoul/p2(0x0, new Landroid/widget/PopupWindow(p0.g))));
      :try_start_1ac:
      p3.showAsDropDown(p1, 0x0, Ldev/local/newsoul/w2.i(p0.g, p2), 0x800005);
      :try_end_1b7:
      return;
      :catch_1b8:
      p1.removeOnAttachStateChangeListener((v3 + 0x1));
      throw e;
      :cond_1bd:
      :goto_1bd:
      return;

      // try :try_start_1ac..:try_end_1b7 catch (Ljava/lang/RuntimeException) -> :catch_1b8
    }

    public final void i(ViewGroup p0) {
      new Landroid/widget/LinearLayout(p0.g);
      p0.f = new Landroid/widget/LinearLayout(p0.g);
      new Landroid/widget/LinearLayout(p0.g).setGravity(0x10);
      new Landroid/widget/LinearLayout(p0.g).setPadding(Ldev/local/newsoul/w2.i(p0.a, 0x18), Ldev/local/newsoul/w2.i(p0.a, 0xe), Ldev/local/newsoul/w2.i(p0.a, 0x18), Ldev/local/newsoul/w2.i(p0.a, 0x8));
      if (p0.p.isEmpty() == 0) goto :cond_41;
      Ldev/local/newsoul/w2.n(p0.g, p1, 0x12, p0.i).setTypeface(Typeface.DEFAULT, 0x1);
      goto :goto_64;
      :cond_41:
      :try_start_41:
      new Landroid/graphics/Typeface$Builder(Typeface.DEFAULT.getAssets(), "fonts/tivf-bold-round.ttf");
      :try_end_56:
      goto :goto_58;
      :catch_57:
      :goto_58:
      if (Typeface.DEFAULT == 0) goto :cond_5b;
      goto :goto_61;
      :cond_5b:
      :goto_61:
      Ldev/local/newsoul/w2.n(p0.g, p1, 0x12, p0.i).setTypeface(Typeface.DEFAULT);
      :goto_64:
      if (Build$VERSION.SDK_INT < 0x1c) goto :cond_6d;
      Ldev/local/newsoul/N0.b(Ldev/local/newsoul/w2.n(p0.g, p1, 0x12, p0.i));
      :cond_6d:
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(p0.g).addView(Ldev/local/newsoul/w2.n(p0.g, p1, 0x12, p0.i), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      if (p0.t == 0) goto :cond_83;
      new Landroid/widget/LinearLayout(p0.g).addView(Ldev/local/newsoul/w2.l(p0, p1, p0.t));
      :cond_83:
      if (p0.p.isEmpty() == 0) goto :cond_8c;
      goto :goto_8e;
      :cond_8c:
      :goto_8e:
      new Landroid/widget/LinearLayout(p0.g).setMinimumHeight(Ldev/local/newsoul/w2.i(p0.g, 0x3a));
      new Ldev/local/newsoul/L(0x1);
      p0.e = p0.d.setCustomTitle(new Landroid/widget/LinearLayout(p0.g)).setView(p3).setPositiveButton(p2, new Ldev/local/newsoul/L(0x1)).create();
      p0.d.setCustomTitle(new Landroid/widget/LinearLayout(p0.g)).setView(p3).setPositiveButton(p2, new Ldev/local/newsoul/L(0x1)).create().show();
      if (p0.r == 0) goto :cond_f9;
      p0.e.setCanceledOnTouchOutside(0x0);
      new Ldev/local/newsoul/s0(p0, 0x3);
      p0.e.getButton(-0x2).setOnClickListener(new Ldev/local/newsoul/s0(p0, 0x3));
      new Ldev/local/newsoul/n2(p0);
      p0.e.setOnKeyListener(new Ldev/local/newsoul/n2(p0));
      if (Build$VERSION.SDK_INT < 0x21) goto :cond_f9;
      new Ldev/local/newsoul/k2(p0);
      Ldev/local/newsoul/I0.f(Ldev/local/newsoul/I0.c(p0.e), new Ldev/local/newsoul/k2(p0));
      new Ldev/local/newsoul/F1(Ldev/local/newsoul/I0.c(p0.e), new Ldev/local/newsoul/k2(p0), 0x1);
      p0.e.getWindow().getDecorView().addOnAttachStateChangeListener(new Ldev/local/newsoul/F1(Ldev/local/newsoul/I0.c(p0.e), new Ldev/local/newsoul/k2(p0), 0x1));
      :cond_f9:
      Ldev/local/newsoul/L2.i(p0.e.getWindow().getDecorView(), p0.c.c(0x1, Ldev/local/newsoul/L2.e(0x1)));
      p0.e.getWindow().setBackgroundDrawable(p0.c.c(0x1, Ldev/local/newsoul/L2.e(0x1)));
      :goto_11c:
      if (0x0 >= 0x3) goto :cond_14a;
      if (p0.e.getButton(0x21[0x0]) == 0) goto :cond_147;
      new Ldev/local/newsoul/n2(p0).b(p0.e.getButton(0x21[0x0]), 0x9, Ldev/local/newsoul/L2.e(0x9));
      p0.e.getButton(0x21[0x0]).setTextColor(p0.k);
      p0.e.getButton(0x21[0x0]).setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      p0.e.getButton(0x21[0x0]).setMinWidth(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      :cond_147:
      goto :goto_11c;
      :cond_14a:
      return;

      // try :try_start_41..:try_end_56 catch (Ljava/lang/RuntimeException) -> :catch_57
    }

}