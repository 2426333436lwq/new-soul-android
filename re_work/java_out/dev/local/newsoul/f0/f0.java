package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/f0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class f0 extends Ljava/lang/Object {

    public final q2 a;
    public final r2 b;
    public final TextView c;
    public final TextView d;
    public final TextView e;
    public final TextView f;
    public boolean g;
    public boolean h;

    public void super(Landroid/app/Activity;Z p0) {
      super();
      new Ldev/local/newsoul/q2(p1, p2);
      this.a = new Ldev/local/newsoul/q2(p1, p2);
      new Ldev/local/newsoul/q2(p1, p2).t = "遇到问题时，开启调试模式，再返回出现问题的页面复现一次，然后导出记录并发给作者。如需排查启动问题，可在开启后重启 Soul。\n\n每次最多记录 30 分钟，总大小上限 4 MB，到达上限会自动停止。关闭调试不会删除已有记录；清除记录会同时停止调试。\n\n记录应用与系统版本、页面、功能执行状态及错误位置，不记录聊天正文、语音内容、账号凭据或 API 密钥。";
      this.b = Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0).d(0x0, "debug_session", "调试模式", "", new Ldev/local/newsoul/q2(p1, p2).i);
      this.c = Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0), "");
      this.e = Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "导出记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k);
      this.f = Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "清除记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "导出记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "清除记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k));
      Ldev/local/newsoul/w2.a(new Ldev/local/newsoul/q2(p1, p2), Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "导出记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k), Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "清除记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k));
      this.d = Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0), "");
      Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0), "").setVisibility(0x8);
      Ldev/local/newsoul/w2.c(Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0), "").setAccessibilityLiveRegion(0x1);
      new Ldev/local/newsoul/b0(new Ljava/lang/Object());
      Ldev/local/newsoul/w2.r(new Ldev/local/newsoul/q2(p1, p2), "记录", 0x0, 0x0).d(0x0, "debug_session", "调试模式", "", new Ldev/local/newsoul/q2(p1, p2).i).setOnCheckedChangeListener(new Ldev/local/newsoul/b0(new Ljava/lang/Object()));
      new Ldev/local/newsoul/c0(new Ljava/lang/Object(), 0x0);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "导出记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k).setOnClickListener(new Ldev/local/newsoul/c0(new Ljava/lang/Object(), 0x0));
      new Ldev/local/newsoul/c0(new Ljava/lang/Object(), 0x1);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p1, p2).g, "清除记录", 0xf, new Ldev/local/newsoul/q2(p1, p2).k).setOnClickListener(new Ldev/local/newsoul/c0(new Ljava/lang/Object(), 0x1));
      new Ldev/local/newsoul/q2(p1, p2).g("调试", "关闭");
      new Ljava/lang/Object().b();
      new Ldev/local/newsoul/e0(new Ljava/lang/Object());
      return;
    }

    public final void a(String p0) {
      p0.d.setText(p1);
      p0.d.setVisibility(0x0);
      p0.b();
      return;
    }

    public final void b() {
      p0.g = 0x1;
      p0.b.setChecked(__result__);
      p0.g = 0x0;
      if (__result__ == 0) goto :cond_40;
      new Ljava/lang/StringBuilder("正在记录 · 剩余 ");
      goto :goto_42;
      :cond_40:
      :goto_42:
      new Ljava/lang/StringBuilder();
      p0.c.setText(new Ljava/lang/StringBuilder().toString());
      if (p0.h != 0) goto :cond_82;
      if (g0.d.containsKey(p0.a.a) == 0) goto :cond_80;
      goto :goto_82;
      :cond_80:
      goto :goto_83;
      :cond_82:
      :goto_82:
      :goto_83:
      p0.b.setEnabled((0x0 ^ 0x1));
      if (0x0 != 0) goto :cond_96;
      if (g0.d.containsKey(p0.a.a) == 0) goto :cond_96;
      goto :goto_97;
      :cond_96:
      :goto_97:
      p0.e.setEnabled(0x1);
      if (0x0 != 0) goto :cond_ab;
      if (g0.d.containsKey(p0.a.a) != 0) goto :cond_ac;
      if (g0.d.containsKey(p0.a.a) == 0) goto :cond_ab;
      goto :goto_ac;
      :cond_ab:
      :cond_ac:
      :goto_ac:
      p0.f.setEnabled(0x1);
      if (p0.e.isEnabled() == 0) goto :cond_bd;
      goto :goto_be;
      :cond_bd:
      :goto_be:
      p0.e.setAlpha(0x3f800000    # 1.0f);
      if (p0.f.isEnabled() == 0) goto :cond_ca;
      :cond_ca:
      p0.f.setAlpha(0x3f000000    # 0.5f);
      return;
    }

}