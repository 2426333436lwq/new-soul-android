package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/b3 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class b3 extends Ljava/lang/Object {

    public final Object a;
    public final Object b;
    public final Activity c;
    public final String d;
    public final String e;
    public final x1 f;
    public final boolean g;
    public q2 h;
    public q2 i;
    public int j;
    public String k;
    public MediaPlayer l;
    public boolean m;
    public boolean n;
    public boolean o;
    public TextView p;
    public final d3 q;

    public void super(Object p0) {
      super();
      this.q = p1;
      this.k = "";
      this.a = p2;
      this.c = (Activity) Ldev/local/newsoul/d3.e(p2, "getActivity");
      this.d = (String) p1.d.invoke(0x0, 0x0);
      this.e = (String) Ldev/local/newsoul/d3.g(p2, "L");
      this.b = Ldev/local/newsoul/d3.c(p2);
      new Ljava/lang/Object().j();
      new Ldev/local/newsoul/x1((Activity) Ldev/local/newsoul/d3.e(p2, "getActivity").getFilesDir(), (String) p1.d.invoke(0x0, 0x0), 0x1);
      this.f = new Ldev/local/newsoul/x1((Activity) Ldev/local/newsoul/d3.e(p2, "getActivity").getFilesDir(), (String) p1.d.invoke(0x0, 0x0), 0x1);
      if ((Activity) Ldev/local/newsoul/d3.e(p2, "getActivity").getResources().getIdentifier("tv_title", "id", (Activity) Ldev/local/newsoul/d3.e(p2, "getActivity").getPackageName()) != 0) goto :cond_52;
      goto :goto_56;
      :cond_52:
      :goto_56:
      if ((0x0 instanceof TextView) == 0) goto :cond_72;
      if (compare(((double) Landroid/graphics/Color.luminance((TextView) 0x0.getCurrentTextColor())), 0x3fdccccccccccccdL    # 0.45) <= 0) goto :cond_83;
      :goto_70:
      goto :goto_83;
      :cond_72:
      if ((((double) Landroid/graphics/Color.luminance((TextView) 0x0.getCurrentTextColor())).getResources().getConfiguration().uiMode & 0x30) != 0x20) goto :cond_83;
      goto :goto_70;
      :cond_83:
      :goto_83:
      this.g = 0x1;
      return;
    }

    public final void a(c3 p0) {
      if (p0.n == 0) goto :cond_5;
      return;
      :cond_5:
      p0.n = 0x1;
      p0.o = 0x1;
      p0.h.h.addView(Ldev/local/newsoul/w2.n(p0.h.g, "操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。", 0xd, p0.h.j));
      if (p0.h.e.getButton(-0x2) == 0) goto :cond_31;
      p0.h.e.getButton(-0x2).setText("关闭");
      :cond_31:
      if (p0.h.e.getButton(-0x1) == 0) goto :cond_4f;
      p0.h.e.getButton(-0x1).setEnabled(0x0);
      p0.h.e.getButton(-0x1).setText("处理中…");
      :cond_4f:
      v8.<init>(p0, p1, p0.h, Ldev/local/newsoul/w2.n(p0.h.g, "操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。", 0xd, p0.h.j), p2, 0x5);
      p0.q.b.execute(v8);
      return;
    }

    public final void b() {
      if (p0.m == 0) goto :cond_5;
      return;
      :cond_5:
      p0.m = 0x1;
      p0.i();
      if (p0.h == 0) goto :cond_16;
      if (p0.h.e == 0) goto :cond_16;
      p0.h.e.dismiss();
      :cond_16:
      if (p0.q.f != p0) goto :cond_1f;
      p0.q.f = 0x0;
      :cond_1f:
      return;
    }

    public final void c() {
      if (p0.o == 0) goto :cond_b;
      Ldev/local/newsoul/d3.i(p0.c, "操作仍在后台进行，请稍后打开语音库查看结果");
      :cond_b:
      p0.b();
      return;
    }

    public final void d(h3 p0) {
      p0.j();
      p0.g().t = "点击试听可播放本机语音。修改备注和删除只影响本机语音库；只有点击“发送到当前聊天”才会发送。";
      Ldev/local/newsoul/w2.n(p0.g().g, p1.b, 0x11, p0.g().i).setGravity(0x11);
      Ldev/local/newsoul/w2.n(p0.g().g, p1.b, 0x11, p0.g().i).setTypeface(Typeface.DEFAULT, 0x1);
      Ldev/local/newsoul/w2.n(p0.g().g, p1.b, 0x11, p0.g().i).setPadding(0x0, Ldev/local/newsoul/w2.i(p0.g().g, 0x14), 0x0, Ldev/local/newsoul/w2.i(p0.g().g, 0x8));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.g().h.addView(Ldev/local/newsoul/w2.n(p0.g().g, p1.b, 0x11, p0.g().i), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/w2.n(p0.g().g, new Ljava/lang/StringBuilder().toString(), 0xd, p0.g().j).setGravity(0x11);
      Ldev/local/newsoul/w2.n(p0.g().g, new Ljava/lang/StringBuilder().toString(), 0xd, p0.g().j).setPadding(0x0, 0x0, 0x0, Ldev/local/newsoul/w2.i(p0.g().g, 0x18));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.g().h.addView(Ldev/local/newsoul/w2.n(p0.g().g, new Ljava/lang/StringBuilder().toString(), 0xd, p0.g().j), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/Q2(p0, p1, 0x0);
      p0.p = Ldev/local/newsoul/d3.a(p0.g(), "试听", new Ldev/local/newsoul/Q2(p0, p1, 0x0));
      new Ldev/local/newsoul/Q2(p0, p1, 0x4);
      p0.p.setTextColor(p0.g().k);
      Ldev/local/newsoul/d3.a(p0.g(), "修改备注", new Ldev/local/newsoul/Q2(p0, p1, 0x4)).setTextColor(p0.g().k);
      Ldev/local/newsoul/w2.a(p0.g(), p0.g().h, p0.p, Ldev/local/newsoul/d3.a(p0.g(), "修改备注", new Ldev/local/newsoul/Q2(p0, p1, 0x4)));
      new Ldev/local/newsoul/Q2(p0, p1, 0x5);
      if (p0.g == 0) goto :cond_a7;
      goto :goto_aa;
      :cond_a7:
      :goto_aa:
      Ldev/local/newsoul/d3.a(p0.g(), "删除", new Ldev/local/newsoul/Q2(p0, p1, 0x5)).setTextColor(-0x5c5d);
      Ldev/local/newsoul/d3.a(p0.g(), "删除", new Ldev/local/newsoul/Q2(p0, p1, 0x5)).setGravity(0x11);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.g().h.addView(Ldev/local/newsoul/d3.a(p0.g(), "删除", new Ldev/local/newsoul/Q2(p0, p1, 0x5)), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/Q2(p0, p1, 0x7);
      Ldev/local/newsoul/d3.a(p0.g(), "发送到当前聊天", new Ldev/local/newsoul/Q2(p0, p1, 0x7)).setTextColor(p0.g().k);
      Ldev/local/newsoul/d3.a(p0.g(), "发送到当前聊天", new Ldev/local/newsoul/Q2(p0, p1, 0x7)).setGravity(0x11);
      p0.g().c.b(Ldev/local/newsoul/d3.a(p0.g(), "发送到当前聊天", new Ldev/local/newsoul/Q2(p0, p1, 0x7)), 0x7, Ldev/local/newsoul/L2.e(0x7));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).topMargin = Ldev/local/newsoul/w2.i(p0.g().g, 0x10);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2).bottomMargin = Ldev/local/newsoul/w2.i(p0.g().g, 0xc);
      p0.g().h.addView(Ldev/local/newsoul/d3.a(p0.g(), "发送到当前聊天", new Ldev/local/newsoul/Q2(p0, p1, 0x7)), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      p0.g().g("语音详情", "返回语音库");
      new Ldev/local/newsoul/U2(0x3, p0);
      p0.k(new Ldev/local/newsoul/U2(0x3, p0));
      p0.g().e.getButton(-0x1).setTextColor(p0.g().k);
      p0.g().e.getButton(-0x1).setMinHeight(Ldev/local/newsoul/w2.i(p0.g().g, 0x30));
      new Ldev/local/newsoul/A0(0x5, p0);
      p0.g().e.getButton(-0x1).setOnClickListener(new Ldev/local/newsoul/A0(0x5, p0));
      return;
    }

    public final void e(c3 p0) {
      new Landroid/widget/EditText(p0.g().g);
      new Landroid/widget/EditText(p0.g().g).setTextColor(p0.g().i);
      new Landroid/widget/EditText(p0.g().g).setHintTextColor(p0.g().j);
      p0.g().c.b(new Landroid/widget/EditText(p0.g().g), 0x8, Ldev/local/newsoul/L2.e(0x8));
      new Landroid/widget/EditText(p0.g().g).setPadding(Ldev/local/newsoul/w2.i(p0.g().g, 0xc), Ldev/local/newsoul/w2.i(p0.g().g, 0xa), Ldev/local/newsoul/w2.i(p0.g().g, 0xc), Ldev/local/newsoul/w2.i(p0.g().g, 0xa));
      new Landroid/widget/EditText(p0.g().g).setHint("给这条语音写个备注");
      new Landroid/widget/EditText(p0.g().g).setText(p2);
      new Landroid/widget/EditText(p0.g().g).setMinLines(0x2);
      new Landroid/widget/EditText(p0.g().g).setMaxLines(0x4);
      new Landroid/text/InputFilter$LengthFilter(0x78);
      new InputFilter[][0x1][0x0] = new Landroid/text/InputFilter$LengthFilter(0x78);
      new Landroid/widget/EditText(p0.g().g).setFilters(new InputFilter[][0x1]);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      p0.g().h.addView(new Landroid/widget/EditText(p0.g().g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ldev/local/newsoul/L(0x1);
      p0.g().g(p1, "保存");
      p0.k(p4);
      new Ldev/local/newsoul/C0(p0, p4, 0x6);
      p0.g().e.getButton(-0x2).setOnClickListener(new Ldev/local/newsoul/C0(p0, p4, 0x6));
      new Ldev/local/newsoul/G1(p0, p3, new Landroid/widget/EditText(p0.g().g), 0x2);
      p0.g().e.getButton(-0x1).setOnClickListener(new Ldev/local/newsoul/G1(p0, p3, new Landroid/widget/EditText(p0.g().g), 0x2));
      return;
    }

    public final void f() {
      p0.j();
      p0.g().t = "保存语音\n长按聊天中的语音消息，选择「存入语音库」。\n\n打开语音库\n进入普通私聊，长按聊天输入区的语音图标。\n\n使用已保存的语音\n点击条目可试听、修改备注或删除。只有点击「发送到当前聊天」才会发送。\n\n语音保存在本机，按账号分别管理。";
      p0.g().h.addView(Ldev/local/newsoul/w2.n(p0.g().g, "正在读取语音库…", 0xe, p0.g().j));
      p0.i = p0.g();
      p0.g().g("语音库", "关闭");
      new Ldev/local/newsoul/U2(0x1, p0);
      p0.k(new Ldev/local/newsoul/U2(0x1, p0));
      new Ldev/local/newsoul/V2(p0, p0.g(), 0x0);
      p0.q.b.execute(new Ldev/local/newsoul/V2(p0, p0.g(), 0x0));
      return;
    }

    public final q2 g() {
      p0.i();
      if (p0.h == 0) goto :cond_3f;
      if (p0.h.e == 0) goto :cond_3f;
      if (p0.h != p0.i) goto :cond_2a;
      if ((p0.h.h.getParent() instanceof ScrollView) == 0) goto :cond_2a;
      p0.j = (ScrollView) p0.h.h.getParent().getScrollY();
      :cond_2a:
      p0.h.e.setOnCancelListener(0x0);
      p0.h.e.setOnDismissListener(0x0);
      p0.h.e.dismiss();
      :cond_3f:
      p0.p = 0x0;
      new Ldev/local/newsoul/q2(p0.c, p0.g);
      p0.h = new Ldev/local/newsoul/q2(p0.c, p0.g);
      return new Ldev/local/newsoul/q2(p0.c, p0.g);
    }

    public final void h(ArrayList p0) {
      p2.removeAllViews();
      :goto_15:
      if (v1.hasNext() == 0) goto :cond_b0;
      if ((h3) v1.next().b.toLowerCase(Locale.ROOT).contains(v0) != 0) goto :cond_34;
      goto :goto_15;
      :cond_34:
      new Ljava/lang/StringBuilder();
      new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6);
      new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText());
      new Landroid/text/style/StyleSpan(0x1);
      new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()).setSpan(new Landroid/text/style/StyleSpan(0x1), v2, (h3) v1.next().b.length(), v2);
      new Landroid/text/style/ForegroundColorSpan(p1.j);
      new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()).setSpan(new Landroid/text/style/ForegroundColorSpan(p1.j), ((h3) v1.next().b.length() + 0x1), new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()).length(), v2);
      new Landroid/text/style/RelativeSizeSpan(0x3f5eb852    # 0.87f);
      new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()).setSpan(new Landroid/text/style/RelativeSizeSpan(0x3f5eb852    # 0.87f), ((h3) v1.next().b.length() + 0x1), new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()).length(), v2);
      Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).setText(new Landroid/text/SpannableString(Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).getText()));
      Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).setTextSize(0x41800000    # 16.0f);
      Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)).setLineSpacing(((float) Ldev/local/newsoul/w2.i(p1.g, 0x4)), 0x3f800000    # 1.0f);
      Ldev/local/newsoul/w2.b(p1, p2, Ldev/local/newsoul/d3.a(p1, new Ljava/lang/StringBuilder().toString(), new Ldev/local/newsoul/Q2(p0, (h3) v1.next(), 0x6)));
      goto :goto_15;
      :cond_b0:
      if ((v3 + 0x1) != 0) goto :cond_c6;
      if (p3.isEmpty() == 0) goto :cond_bb;
      goto :goto_bd;
      :cond_bb:
      :goto_bd:
      p2.addView(Ldev/local/newsoul/w2.n(((float) Ldev/local/newsoul/w2.i(p1.g, 0x4)), "还没有保存语音", 0xe, 0x3f800000    # 1.0f));
      :cond_c6:
      return;
    }

    public final void i() {
      if (p0.l == 0) goto :cond_a;
      p0.l.release();
      p0.l = 0x0;
      :cond_a:
      if (p0.p == 0) goto :cond_13;
      p0.p.setText("试听");
      :cond_13:
      return;
    }

    public final void j() {
      if (p0.m != 0) goto :cond_76;
      if (p0.c == 0) goto :cond_76;
      if (p0.c.isFinishing() != 0) goto :cond_76;
      if (p0.c.isDestroyed() != 0) goto :cond_76;
      if ((Boolean) Ldev/local/newsoul/d3.e(p0.a, "isResumed").booleanValue() == 0) goto :cond_76;
      if (p0.d.equals(p0.q.d.invoke(0x0, 0x0)) == 0) goto :cond_76;
      if (p0.e == 0) goto :cond_76;
      if (p0.e.isEmpty() != 0) goto :cond_76;
      if (p0.d.equals(p0.e) != 0) goto :cond_76;
      if (p0.e.equals(Ldev/local/newsoul/d3.g(p0.a, "L")) == 0) goto :cond_76;
      if (p0.b == 0) goto :cond_76;
      if (p0.b != Ldev/local/newsoul/d3.c(p0.a)) goto :cond_76;
      if ((Integer) Ldev/local/newsoul/d3.e(p0.b, "getChatType").intValue() != 0) goto :cond_76;
      if (p0.e.equals(Ldev/local/newsoul/d3.e(p0.b, "getToUserId")) == 0) goto :cond_76;
      return;
      :cond_76:
      new Ljava/lang/IllegalStateException("聊天或账号已变化，请重新打开语音库");
      throw new Ljava/lang/IllegalStateException("聊天或账号已变化，请重新打开语音库");
    }

    public final void k(c3 p0) {
      p0.h.e.setCanceledOnTouchOutside(0x0);
      new Ldev/local/newsoul/X2(p0, p0.h, p1);
      p0.h.e.setOnCancelListener(new Ldev/local/newsoul/X2(p0, p0.h, p1));
      new Ldev/local/newsoul/c1(p0, p0.h, 0x2);
      p0.h.e.setOnDismissListener(new Ldev/local/newsoul/c1(p0, p0.h, 0x2));
      return;
    }

}