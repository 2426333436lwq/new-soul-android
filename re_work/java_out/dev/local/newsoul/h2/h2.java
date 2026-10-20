package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/h2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class h2 extends Ljava/lang/Object {

    public final ArrayList a;
    public final ArrayList b;
    public final ArrayList c;
    public final TextView d;
    public final TextView e;
    public final V1 f;

    public void super(LinearLayout p0) {
      super();
      new Ljava/util/ArrayList();
      this.a = new Ljava/util/ArrayList();
      new Ljava/util/ArrayList();
      this.b = new Ljava/util/ArrayList();
      new Ljava/util/ArrayList();
      this.c = new Ljava/util/ArrayList();
      new Ldev/local/newsoul/V1(new Ljava/lang/Object(), 0x1);
      this.f = new Ldev/local/newsoul/V1(new Ljava/lang/Object(), 0x1);
      this.e = Ldev/local/newsoul/w2.c(p2, "部分功能未就绪，原因尚未确认。");
      Ldev/local/newsoul/w2.c(p2, "部分功能未就绪，原因尚未确认。").setVisibility(0x8);
      this.d = Ldev/local/newsoul/w2.c(p2, "功能兼容性检测");
      Ldev/local/newsoul/w2.c(p2, "功能兼容性检测").setTextColor(p1.k);
      Ldev/local/newsoul/w2.c(p2, "功能兼容性检测").setMinHeight(Ldev/local/newsoul/w2.i(p1.g, 0x30));
      Ldev/local/newsoul/w2.c(p2, "功能兼容性检测").setGravity(0x10);
      new Ldev/local/newsoul/s0(p1, 0x2);
      Ldev/local/newsoul/w2.c(p2, "功能兼容性检测").setOnClickListener(new Ldev/local/newsoul/s0(p1, 0x2));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.c(p2, "功能兼容性检测"));
      Ldev/local/newsoul/w2.c(p2, "功能兼容性检测").setVisibility(0x8);
      return;
    }

    public final void a() {
      if (p0.e.isAttachedToWindow() == 0) goto :cond_11;
      goto :goto_14;
      :cond_11:
      p0.b();
      :goto_14:
      return;
    }

    public final void b() {
      :goto_9:
      if (v0.hasNext() == 0) goto :cond_2e;
      if ((CompoundButton) v0.next().isEnabled() != 0) goto :cond_2b;
      if (p0.b.contains((CompoundButton) v0.next()) != 0) goto :cond_2b;
      goto :goto_2c;
      :cond_2b:
      :goto_2c:
      goto :goto_9;
      :cond_2e:
      :goto_35:
      if (v0.hasNext() == 0) goto :cond_47;
      goto :goto_35;
      :cond_47:
      if ((v2 | ((CompoundButton) v0.next().isEnabled() ^ 0x1)) != 0) goto :cond_50;
      if ((p0.b.contains((CompoundButton) v0.next()) | (BooleanSupplier) v0.next().getAsBoolean()) == 0) goto :cond_4e;
      goto :goto_50;
      :cond_4e:
      goto :goto_51;
      :cond_50:
      :goto_50:
      :goto_51:
      if ((v3 | 0x1) == 0) goto :cond_5b;
      goto :goto_5c;
      :cond_5b:
      :goto_5c:
      if (p0.e.getVisibility() != v1) goto :cond_67;
      if (p0.d.getVisibility() != 0x8) goto :cond_67;
      return;
      :cond_67:
      p0.e.setAccessibilityLiveRegion(Ldev/local/newsoul/w2.h((View) p0.e.getParent()));
      if ((v3 | 0x1) == 0) goto :cond_77;
      goto :goto_78;
      :cond_77:
      :goto_78:
      p0.e.setVisibility(v1);
      p0.d.setVisibility(0x8);
      return;
    }

}