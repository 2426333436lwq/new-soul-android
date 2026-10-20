package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/I2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class I2 extends Ljava/lang/Object {

    public final Activity a;
    public final View b;
    public final E2 c;
    public ViewTreeObserver d;
    public final M1 e;
    public final int f;
    public final int g;
    public final int h;
    public final HashMap i;
    public final ArrayList j;
    public View k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public W0 s;
    public final HashSet t;
    public final F2 u;
    public final F2 v;
    public final F2 w;

    public void super(View p0) {
      super();
      new Ldev/local/newsoul/M1(0x1, new Ljava/lang/Object());
      this.e = new Ldev/local/newsoul/M1(0x1, new Ljava/lang/Object());
      new Ljava/util/HashMap();
      this.i = new Ljava/util/HashMap();
      new Ljava/util/ArrayList();
      this.j = new Ljava/util/ArrayList();
      new Ljava/util/HashSet();
      this.t = new Ljava/util/HashSet();
      new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x0);
      this.u = new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x0);
      new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x1);
      this.v = new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x1);
      new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x2);
      this.w = new Ldev/local/newsoul/F2(new Ljava/lang/Object(), 0x2);
      this.a = p1;
      this.b = p2;
      this.f = p1.getResources().getIdentifier("pager_square", "id", p1.getPackageName());
      this.g = p1.getResources().getIdentifier("key_item_post", "id", p1.getPackageName());
      this.h = p1.getResources().getIdentifier("iv_like", "id", p1.getPackageName());
      new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object());
      this.c = new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object());
      new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object()).setVisibility(0x8);
      new Ldev/local/newsoul/p2(0x1, new Ljava/lang/Object());
      new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object()).addOnAttachStateChangeListener(new Ldev/local/newsoul/p2(0x1, new Ljava/lang/Object()));
      new Ldev/local/newsoul/A0(0x4, new Ljava/lang/Object());
      new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object()).setOnClickListener(new Ldev/local/newsoul/A0(0x4, new Ljava/lang/Object()));
      new Ldev/local/newsoul/n(0x1, new Ljava/lang/Object());
      new Ldev/local/newsoul/E2(p1, new Ljava/lang/Object()).setOnLongClickListener(new Ldev/local/newsoul/n(0x1, new Ljava/lang/Object()));
      return;
    }

    public final boolean a() {
      if (Q0.B == 0) goto :cond_1a;
      if (p0.b() != 0) goto :cond_19;
      if (Ldev/local/newsoul/w2.o(p0.a).getBoolean("square_like_all_loaded", 0x0) == 0) goto :cond_1a;
      :cond_19:
      :cond_1a:
      return Ldev/local/newsoul/w2.o(p0.a).getBoolean("square_like_all_loaded", 0x0);
    }

    public final boolean b() {
      if (Q0.B == 0) goto :cond_14;
      if (Ldev/local/newsoul/w2.o(p0.a).getBoolean("square_auto_like", 0x0) == 0) goto :cond_14;
      :cond_14:
      return Ldev/local/newsoul/w2.o(p0.a).getBoolean("square_auto_like", 0x0);
    }

    public final void c(HashSet p0) {
      if (Ldev/local/newsoul/Q0.b(p1) != 0) goto :cond_7;
      return;
      :cond_7:
      if (Q0.Z.isInstance(p1.getTag(p0.g)) == 0) goto :cond_8e;
      :try_start_15:
      if (compare(Q0.a0.getLong(p1.getTag(p0.g)), 0x0) <= 0) goto :cond_8d;
      if (Ldev/local/newsoul/Q0.a(p1.getTag(p0.g)) == 0) goto :cond_8d;
      if (Ldev/local/newsoul/B2.a(p0.a, p1.getTag(p0.g)) == 0) goto :cond_8d;
      if ((Boolean) Q0.b0.invoke(p1.getTag(p0.g), 0x0).booleanValue() != 0) goto :cond_8d;
      if (p0.i.containsKey(Ljava/lang/Long.valueOf(Q0.a0.getLong(p1.getTag(p0.g)), v6)) != 0) goto :cond_8d;
      if (p0.t.contains(Ljava/lang/Long.valueOf(Q0.a0.getLong(p1.getTag(p0.g)), v6)) != 0) goto :cond_8d;
      if (p1.findViewById(p0.h) == 0) goto :cond_8d;
      if (p1.findViewById(p0.h).isShown() == 0) goto :cond_8d;
      if (p1.findViewById(p0.h).isEnabled() == 0) goto :cond_8d;
      if (p1.findViewById(p0.h).hasOnClickListeners() == 0) goto :cond_8d;
      if (p3.add(Ljava/lang/Long.valueOf(Q0.a0.getLong(p1.getTag(p0.g)), v6)) == 0) goto :cond_8d;
      p3.add(Ljava/lang/Long.valueOf(Q0.a0.getLong(p1.getTag(p0.g)), v6)).<init>(p1, p1.findViewById(p0.h), p1.getTag(p0.g), Q0.a0.getLong(p1.getTag(p0.g)), v6);
      :try_end_84:
      goto :goto_8d;
      :catch_85:
      :cond_8d:
      :goto_8d:
      return;
      :cond_8e:
      if ((p1 instanceof ViewGroup) == 0) goto :cond_a5;
      :goto_95:
      if ((p1 instanceof ViewGroup) >= p1.getChildCount()) goto :cond_a5;
      p0.c(p1.getChildAt((p1 instanceof ViewGroup)), p2, p3.add(Ljava/lang/Long.valueOf(Q0.a0.getLong(p1.getTag(p0.g)), v6)));
      goto :goto_95;
      :cond_a5:
      return;

      // try :try_start_15..:try_end_84 catch (Ljava/lang/ReflectiveOperationException) -> :catch_85
    }

    public final void d(String p0) {
      if (p0.r == 0) goto :cond_29;
      Ldev/local/newsoul/w2.o(p0.a).edit().putBoolean("square_auto_like", 0x0).apply();
      new Ljava/lang/StringBuilder();
      :cond_29:
      p0.e(0x0);
      Landroid/widget/Toast.makeText(p0.a, p1, 0x1).show();
      return;
    }

    public final void e(boolean p0) {
      if (p0.m == 0) goto :cond_5e;
      new Ljava/lang/StringBuilder("square like batch ended: submitted=");
      if (p1 == 0) goto :cond_5e;
      if (p0.r != 0) goto :cond_5e;
      if (p0.q == 0) goto :cond_46;
      new Ljava/lang/StringBuilder("本批已成功点赞 ");
      :goto_3e:
      goto :goto_55;
      :cond_46:
      new Ljava/lang/StringBuilder("已触发 ");
      goto :goto_3e;
      :goto_55:
      Landroid/widget/Toast.makeText(p0.a, " 条点赞，结果以帖子状态为准", 0x0).show();
      :cond_5e:
      p0.m = 0x0;
      p0.p = (p0.p + 0x1);
      p0.s = 0x0;
      p0.j.clear();
      p0.k = 0x0;
      p0.c.setEnabled(0x1);
      return;
    }

    public final void f() {
      if (p0.m != 0) goto :cond_7;
      return;
      :cond_7:
      if (p0.r == 0) goto :cond_16;
      if (p0.b() != 0) goto :cond_16;
      p0.e(0x0);
      return;
      :cond_16:
      if (p0.g() != p0.k) goto :cond_31;
      if (p0.a() != p0.q) goto :cond_31;
      if (p0.n < p0.j.size()) goto :cond_34;
      :cond_31:
      goto :goto_1bc;
      :cond_34:
      if (p0.q == 0) goto :cond_114;
      if (p0.j() != 0) goto :cond_50;
      p0.e(0x1);
      goto :goto_113;
      :cond_50:
      p0.n = (p0.n + 0x1);
      :try_start_5f:
      new Ljava/util/ArrayList((List) Q0.H.invoke(p0.s.b, 0x0));
      :cond_79:
      if (v7.hasNext() == 0) goto :cond_c3;
      if (v7.next() != v15) goto :cond_79;
      :try_end_8b:
      if (compare(v7, v13.d) != 0) goto :cond_c3;
      :try_start_93:
      if (Ldev/local/newsoul/Q0.a(v15) == 0) goto :cond_c3;
      if (Ldev/local/newsoul/B2.a(p0.a, v15) == 0) goto :cond_c3;
      if ((Boolean) Q0.b0.invoke(v15, 0x0).booleanValue() != 0) goto :cond_c3;
      if (p0.i.containsKey(Ljava/lang/Long.valueOf(v13.d, p0.i)) != 0) goto :cond_c3;
      if (p0.t.contains(Ljava/lang/Long.valueOf(v13.d, p0.i)) == 0) goto :cond_c6;
      :cond_c3:
      goto :goto_fc;
      :cond_c6:
      p0.c.a(((0x1f4.size() - p0.n) + 0x1), 0x1);
      new Ldev/local/newsoul/H2(p0, v13, p0.j());
      Ldev/local/newsoul/Q0.W(p0.a, p0.s, v15, new Ldev/local/newsoul/H2(p0, v13, p0.j()));
      goto :goto_113;
      :catchall_fa:
      goto :goto_100;
      :goto_fc:
      :try_end_ff:
      goto :goto_113;
      :goto_100:
      p0.d("点赞未完成，已停止");
      :goto_113:
      return;
      :cond_114:
      p0.n = (p0.n + 0x1);
      :try_start_121:
      :try_end_123:
      :try_start_125:
      if (Ldev/local/newsoul/Q0.b(new Ldev/local/newsoul/H2(p0, v13, p0.j())) == 0) goto :cond_1ab;
      :goto_131:
      if (p0.t == 0) goto :cond_1ab;
      if (p0.t != Ldev/local/newsoul/Q0.b(new Ldev/local/newsoul/H2(p0, v13, p0.j()))) goto :cond_1a1;
      :try_end_13b:
      if (new Ldev/local/newsoul/H2(p0, v13, p0.j()) != e.c) goto :cond_1ab;
      :try_start_13f:
      :try_end_145:
      if (compare(p0.t, e.d) != 0) goto :cond_1ab;
      :try_start_14b:
      if (Ldev/local/newsoul/Q0.a(e.c) == 0) goto :cond_1ab;
      if (Ldev/local/newsoul/B2.a(p0.a, e.c) == 0) goto :cond_1ab;
      if ((Boolean) Q0.b0.invoke(e.c, Ljava/lang/Long.valueOf(v13.d, p0.i)).booleanValue() != 0) goto :cond_1ab;
      if (p0.i.isShown() == 0) goto :cond_1ab;
      if (p0.i.isEnabled() == 0) goto :cond_1ab;
      if (p0.i.containsKey(Ljava/lang/Long.valueOf(e.d, v15)) != 0) goto :cond_1ab;
      if (p0.i.performClick() == 0) goto :cond_199;
      p0.o = (p0.o + 0x1);
      goto :goto_1ab;
      :catchall_197:
      goto :goto_1a6;
      :cond_199:
      goto :goto_1ab;
      :cond_1a1:
      :try_end_1a5:
      goto :goto_131;
      :goto_1a6:
      :cond_1ab:
      :goto_1ab:
      p0.c.a((p0.s.size() - p0.n), 0x1);
      return;
      :goto_1bc:
      p0.e(p0.s);
      return;

      // .catchall {:try_start_5f .. :try_end_8b} :catchall_fa
      // .catchall {:try_start_93 .. :try_end_ff} :catchall_fa
      // .catchall {:try_start_121 .. :try_end_123} :catchall_197
      // .catchall {:try_start_125 .. :try_end_13b} :catchall_197
      // .catchall {:try_start_13f .. :try_end_145} :catchall_197
      // .catchall {:try_start_14b .. :try_end_1a5} :catchall_197
    }

    public final View g() {
      if (p0.l == 0) goto :cond_4d;
      if (p0.a.isFinishing() != 0) goto :cond_4d;
      if (p0.a.isDestroyed() != 0) goto :cond_4d;
      if (p0.a.hasWindowFocus() == 0) goto :cond_4d;
      if (p0.f == 0) goto :cond_4d;
      if (p0.g == 0) goto :cond_4d;
      if (p0.h == 0) goto :cond_4d;
      if (Ldev/local/newsoul/w2.o(p0.a).getBoolean("square_visible_like", 0x1) != 0) goto :cond_39;
      if (p0.b() != 0) goto :cond_39;
      goto :goto_4d;
      :cond_39:
      if (Ldev/local/newsoul/Q0.b(p0.b) != 0) goto :cond_42;
      return p0.a;
      :cond_42:
      if (Ldev/local/newsoul/Q0.b(p0.a.findViewById(p0.f)) == 0) goto :cond_4d;
      :cond_4d:
      :goto_4d:
      return p0.a.findViewById(p0.f);
    }

    public final void h() {
      p0.l = 0x0;
      if (p0.d == 0) goto :cond_14;
      if (p0.d.isAlive() == 0) goto :cond_14;
      p0.d.removeOnPreDrawListener(p0.e);
      :cond_14:
      p0.d = 0x0;
      p0.e(0x0);
      p0.c.setVisibility(0x8);
      return;
    }

    public final void i() {
      p0.l = 0x1;
      if (p0.d == 0) goto :cond_d;
      if (p0.d.isAlive() != 0) goto :cond_1a;
      :cond_d:
      p0.d = p0.b.getViewTreeObserver();
      p0.b.getViewTreeObserver().addOnPreDrawListener(p0.e);
      :cond_1a:
      p0.m();
      return;
    }

    public final boolean j() {
      if (Ldev/local/newsoul/Q0.t(p0.g()) == 0) goto :cond_1c;
      if (p0.s == 0) goto :cond_1c;
      if (Ldev/local/newsoul/Q0.t(p0.g()).a != p0.s.a) goto :cond_1c;
      if (Ldev/local/newsoul/Q0.t(p0.g()).b != p0.s.b) goto :cond_1c;
      goto :goto_1d;
      :cond_1c:
      :goto_1d:
      return 0x1;
    }

    public final ArrayList k() {
      new Ljava/util/ArrayList();
      new Ljava/lang/Object();
      if (p0.g() == 0) goto :cond_ae;
      if (p0.a() == 0) goto :cond_ae;
      new Ljava/util/HashSet();
      if (Ldev/local/newsoul/Q0.t(p0.g()) == 0) goto :cond_b8;
      :try_start_2a:
      new Ljava/util/ArrayList((List) Q0.H.invoke(Ldev/local/newsoul/Q0.t(p0.g()).b, 0x0));
      :cond_3e:
      :goto_3e:
      if (Ldev/local/newsoul/Q0.t(p0.g()).hasNext() == 0) goto :cond_b8;
      if (Q0.Z.isInstance(Ldev/local/newsoul/Q0.t(p0.g()).next()) != 0) goto :cond_51;
      goto :goto_3e;
      :cond_51:
      if (compare(Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), 0x0) <= 0) goto :cond_3e;
      if (Ldev/local/newsoul/Q0.a(Ldev/local/newsoul/Q0.t(p0.g()).next()) == 0) goto :cond_3e;
      if (Ldev/local/newsoul/B2.a(p0.a, Ldev/local/newsoul/Q0.t(p0.g()).next()) == 0) goto :cond_3e;
      if ((Boolean) Q0.b0.invoke(Ldev/local/newsoul/Q0.t(p0.g()).next(), v6).booleanValue() != 0) goto :cond_3e;
      if (p0.i.containsKey(Ljava/lang/Long.valueOf(Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), v12)) != 0) goto :cond_3e;
      if (p0.t.contains(Ljava/lang/Long.valueOf(Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), v12)) != 0) goto :cond_3e;
      if (new Ljava/util/HashSet().add(Ljava/lang/Long.valueOf(Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), v12)) == 0) goto :cond_3e;
      new Ljava/util/HashSet().add(Ljava/lang/Long.valueOf(Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), v12)).<init>(0x0, 0x0, Ldev/local/newsoul/Q0.t(p0.g()).next(), Q0.a0.getLong(Ldev/local/newsoul/Q0.t(p0.g()).next()), v12);
      :try_end_a4:
      goto :goto_3e;
      :catch_a5:
      goto :goto_b8;
      :cond_ae:
      if (e == 0) goto :cond_b8;
      new Ljava/util/HashSet();
      p0.c(e, new Ljava/util/ArrayList(), new Ljava/util/HashSet());
      :cond_b8:
      :goto_b8:
      return new Ljava/util/ArrayList();

      // try :try_start_2a..:try_end_a4 catch (Ljava/lang/ReflectiveOperationException) -> :catch_a5
    }

    public final void l(boolean p0) {
      p0.j.clear();
      if (p0.j.isEmpty() == 0) goto :cond_21;
      if (p1 != 0) goto :cond_20;
      Landroid/widget/Toast.makeText(p0.a, "当前范围内没有可点赞的帖子", 0x0).show();
      :cond_20:
      return;
      :cond_21:
      p0.k = p0.g();
      p0.q = p0.a();
      p0.r = p1;
      if (p0.a() == 0) goto :cond_38;
      goto :goto_39;
      :cond_38:
      :goto_39:
      p0.s = Ldev/local/newsoul/Q0.t(p0.k);
      p0.p = (p0.p + 0x1);
      p0.o = 0x0;
      p0.n = 0x0;
      p0.m = 0x1;
      p0.c.setEnabled(0x1);
      if (p0.q == 0) goto :cond_53;
      goto :goto_55;
      :cond_53:
      :goto_55:
      new Ljava/lang/StringBuilder("square like batch started: scope=");
      p0.f();
      return;
    }

    public final void m() {
      if (p0.g() == 0) goto :cond_9;
      goto :goto_a;
      :cond_9:
      :goto_a:
      if (p0.m == 0) goto :cond_1b;
      if (p0.r == 0) goto :cond_1b;
      if (p0.b() != 0) goto :cond_1b;
      p0.e(0x0);
      :cond_1b:
      if (p0.m == 0) goto :cond_2c;
      if (p0.q == 0) goto :cond_2c;
      if (p0.j() != 0) goto :cond_2c;
      p0.e(0x0);
      :cond_2c:
      if (0x1 != 0) goto :cond_35;
      if (p0.m == 0) goto :cond_35;
      p0.e(0x0);
      :cond_35:
      if (0x1 == 0) goto :cond_38;
      goto :goto_3a;
      :cond_38:
      :goto_3a:
      if (p0.c.getVisibility() == 0x0) goto :cond_45;
      p0.c.setVisibility(0x0);
      :cond_45:
      if (compare(p0.c.getAlpha(), p0.b.getAlpha()) == 0) goto :cond_5a;
      p0.c.setAlpha(p0.b.getAlpha());
      :cond_5a:
      if (compare(p0.c.getTranslationX(), p0.b.getTranslationX()) == 0) goto :cond_6d;
      p0.c.setTranslationX(p0.b.getTranslationX());
      :cond_6d:
      if (compare(p0.c.getTranslationY(), p0.b.getTranslationY()) == 0) goto :cond_80;
      p0.c.setTranslationY(p0.b.getTranslationY());
      :cond_80:
      return;
    }

}