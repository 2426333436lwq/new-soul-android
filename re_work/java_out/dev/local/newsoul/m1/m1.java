package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/m1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class m1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;

    public void super(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p6;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      this.f = p5;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_286 */ }
      (b3) p0.b.n = 0x0;
      (b3) p0.b.o = 0x0;
      if ((b3) p0.b.m != 0) goto :cond_71;
      if ((b3) p0.b.h == (q2) p0.c) goto :cond_1f;
      goto :goto_71;
      :cond_1f:
      (q2) p0.c.h.removeView((TextView) p0.d);
      if ((q2) p0.c.e.getButton(-0x2) == 0) goto :cond_3c;
      (q2) p0.c.e.getButton(-0x2).setText("取消");
      :cond_3c:
      if ((q2) p0.c.e.getButton(-0x1) == 0) goto :cond_5a;
      (q2) p0.c.e.getButton(-0x1).setEnabled(0x1);
      (q2) p0.c.e.getButton(-0x1).setText("重试");
      :cond_5a:
      :try_start_5a:
      (b3) p0.b.j();
      :try_end_5d:
      if ((Throwable) p0.e != 0) goto :cond_69;
      :try_start_63:
      (c3) p0.f.run();
      goto :goto_71;
      :catchall_67:
      goto :goto_6a;
      :cond_69:
      throw (Throwable) p0.e;
      :try_end_6a:
      :goto_6a:
      Ldev/local/newsoul/d3.f((b3) p0.b.c, "操作未完成，请重试", e);
      :cond_71:
      :goto_71:
      return;
      :pswitch_72:
      :try_start_7e:
      e.run();
      :try_end_81:
      :goto_82:
      goto :goto_86;
      :catchall_84:
      goto :goto_82;
      :goto_86:
      (b3) p0.b.<init>((q2) p0.c, (q2) p0.d, (TextView) p0.e, e, (c3) p0.f, 0x6);
      return;
      :pswitch_a4:
      (TextView) p0.b.setEnabled((r2) p0.c.isEnabled());
      (TextView) p0.b.setClickable((r2) p0.c.isEnabled());
      (LinearLayout) p0.d.setEnabled((r2) p0.c.isEnabled());
      (LinearLayout) p0.d.setClickable((r2) p0.c.isEnabled());
      if ((SharedPreferences) p0.f == 0) goto :cond_de;
      if ((r2) p0.c.isEnabled() == 0) goto :cond_db;
      goto :goto_de;
      :cond_db:
      goto :goto_df;
      :cond_de:
      :goto_de:
      :goto_df:
      new Ljava/lang/StringBuilder();
      (TextView) p0.b.setText(new Ljava/lang/StringBuilder().toString());
      if ((SharedPreferences) p0.f == 0) goto :cond_100;
      if ((r2) p0.c.isEnabled() == 0) goto :cond_fe;
      goto :goto_100;
      :cond_fe:
      :cond_100:
      :goto_100:
      new Ljava/lang/StringBuilder();
      (r2) p0.c.setContentDescription(new Ljava/lang/StringBuilder().toString());
      return;
      :pswitch_113:
      (g2) p0.b.d = 0x1;
      :try_start_129:
      (r2) p0.c.isEnabled().setEnabled((r2) p0.c.getAsBoolean());
      (r2) p0.c.isEnabled().setChecked((TextView) p0.b.getBoolean(new Ljava/lang/StringBuilder().toString().a, "block_splash".equals(new Ljava/lang/StringBuilder().toString().a)));
      if ((r2) p0.c.getAsBoolean() == 0) goto :cond_146;
      goto :goto_148;
      :catchall_144:
      goto :goto_17d;
      :cond_146:
      :goto_148:
      new Ljava/lang/StringBuilder();
      (r2) p0.c.isEnabled().setText(new Ljava/lang/StringBuilder().toString());
      if (e == 0) goto :cond_15f;
      goto :goto_161;
      :cond_15f:
      :goto_161:
      new Ljava/lang/StringBuilder();
      (r2) p0.c.isEnabled().setContentDescription(new Ljava/lang/StringBuilder().toString());
      :try_end_173:
      new Ljava/lang/StringBuilder().toString().d = new Ljava/lang/StringBuilder();
      (h2) p0.f.a();
      return;
      :goto_17d:
      new Ljava/lang/StringBuilder().toString().d = new Ljava/lang/StringBuilder();
      throw "，勾选表示屏蔽";
      :pswitch_180:
      (C1) p0.c.b((SharedPreferences) p0.b.getString("feed_filter_gender", "FEMALE"), (String[]) p0.d, 0x0);
      if ("ANY".equals((SharedPreferences) p0.b.getString("feed_filter_gender", "FEMALE")) == 0) goto :cond_1a4;
      goto :goto_1a5;
      :cond_1a4:
      :goto_1a5:
      (View) p0.e.setVisibility(0x8);
      if ("ANY".equals((SharedPreferences) p0.b.getString("feed_filter_gender", "FEMALE")) == 0) goto :cond_1af;
      goto :goto_1b0;
      :cond_1af:
      :goto_1b0:
      (TextView) p0.f.setVisibility(0x0);
      return;
      :pswitch_1b8:
      if ((r2) (Switch) p0.b.c == 0) goto :cond_1c8;
      goto :goto_1d1;
      :cond_1c8:
      if ((r2) (Switch) p0.c.c == 0) goto :cond_1d0;
      goto :goto_1d1;
      :cond_1d0:
      :goto_1d1:
      if ((Switch) p0.c.isEnabled() == 0) goto :cond_1eb;
      if ((Switch) p0.c.isChecked() == 0) goto :cond_1eb;
      if ((Switch) p0.b.isEnabled() == 0) goto :cond_1e9;
      if ((Switch) p0.b.isChecked() != 0) goto :cond_1eb;
      :cond_1e9:
      goto :goto_1ec;
      :cond_1eb:
      :goto_1ec:
      (j2) p0.d.c(0x1, (r2) (Switch) p0.c);
      if ((TextView) p0.e.isAttachedToWindow() == 0) goto :cond_208;
      goto :goto_20b;
      :cond_208:
      (Runnable) p0.f.run();
      :goto_20b:
      return;
      :pswitch_20c:
      :try_start_221:
      Ldev/local/newsoul/u1.h((TextView) p0.e.a);
      new Ldev/local/newsoul/x1(v8.getFilesDir(), (TextView) p0.e.a, 0x0);
      synchronized (x1.b) {
      :try_end_237:
      :try_start_237:
      :cond_23f:
      if ((Runnable) p0.f.hasNext() == 0) goto :cond_259;
      if ((w1) (Runnable) p0.f.next().a.equals((TextView) p0.e.isAttachedToWindow()) == 0) goto :cond_23f;
      }
      :goto_255:
      goto :goto_25c;
      :catchall_257:
      goto :goto_270;
      :cond_259:
      }
      :try_end_25a:
      goto :goto_255;
      :goto_25c:
      :try_start_25c:
      v13.<init>(v8, (TextView) p0.e, v9, (Runnable) p0.f, v10, v11);
      :try_end_26c:
      goto :goto_284;
      :catch_26d:
      goto :goto_272;
      :goto_270:
      :try_start_270:
      }
      :try_end_271:
      :try_start_271:
      throw e;
      :try_end_272:
      :goto_272:
      v13.<init>((TextView) p0.e, v8, v9, v10, v11, e, 0x0);
      :goto_284:
      return;
      :pswitch_data_286:

      // .packed-switch 0x0
      // :pswitch_20c
      // :pswitch_1b8
      // :pswitch_180
      // :pswitch_113
      // :pswitch_a4
      // :pswitch_72

      // .catchall {:try_start_5a .. :try_end_5d} :catchall_67
      // .catchall {:try_start_63 .. :try_end_6a} :catchall_67
      // .catchall {:try_start_7e .. :try_end_81} :catchall_84
      // .catchall {:try_start_129 .. :try_end_173} :catchall_144
      // try :try_start_221..:try_end_237 catch (Ljava/lang/Exception) -> :catch_26d
      // .catchall {:try_start_237 .. :try_end_25a} :catchall_257
      // try :try_start_25c..:try_end_26c catch (Ljava/lang/Exception) -> :catch_26d
      // .catchall {:try_start_270 .. :try_end_271} :catchall_257
      // try :try_start_271..:try_end_272 catch (Ljava/lang/Exception) -> :catch_26d
    }

}