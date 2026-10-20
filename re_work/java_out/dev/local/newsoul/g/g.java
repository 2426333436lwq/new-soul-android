package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/g ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class g extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Object b;
    public final Object c;

    public void super(Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_324 */ }
      :try_start_10:
      :try_end_18:
      :try_start_1a:
      new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing");
      if (new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").isDirectory() != 0) goto :cond_3e;
      if (new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs() == 0) goto :cond_34;
      goto :goto_3e;
      :cond_34:
      new Ljava/io/IOException("Cannot create outgoing audio");
      throw new Ljava/io/IOException("Cannot create outgoing audio");
      :catchall_3c:
      goto :goto_8b;
      :cond_3e:
      :goto_3e:
      new Ljava/io/File(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing"), Ljava/lang/String.valueOf(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs()).concat(Ldev/local/newsoul/x1.c(v4)));
      :try_end_53:
      :try_start_53:
      new Ljava/io/FileInputStream(v4);
      :try_end_58:
      :try_start_58:
      new Ljava/io/FileOutputStream(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs());
      :try_end_5d:
      :try_start_5d:
      Ldev/local/newsoul/x1.b(v3, v4);
      :try_end_60:
      :try_start_60:
      v4.close();
      :try_end_63:
      :try_start_63:
      v3.close();
      new Ldev/local/newsoul/H(v1, new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs(), e, 0xc);
      :try_end_72:
      goto :goto_9c;
      :catchall_73:
      goto :goto_8b;
      :catchall_76:
      goto :goto_82;
      :catchall_78:
      :try_start_79:
      v4.close();
      :try_end_7c:
      goto :goto_81;
      :catchall_7d:
      :try_start_7e:
      e.addSuppressed(v4);
      :goto_81:
      throw e;
      :try_end_82:
      :goto_82:
      :try_start_82:
      new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs().close();
      :try_end_85:
      goto :goto_8a;
      :catchall_86:
      :try_start_87:
      e.addSuppressed(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs());
      :goto_8a:
      throw e;
      :try_end_8b:
      :goto_8b:
      if (new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs() == 0) goto :cond_90;
      :cond_90:
      new Ldev/local/newsoul/g(v1, e, 0xf);
      :goto_9c:
      return;
      :pswitch_9d:
      (b3) p0.b.n = 0x0;
      if ((b3) p0.b.m != 0) goto :cond_b3;
      Ldev/local/newsoul/d3.f((b3) p0.b.c, "读取语音失败", (Throwable) p0.c);
      :cond_b3:
      return;
      :pswitch_b4:
      :try_start_bc:
      if ((b3) p0.b.m.get() == 0) goto :cond_ce;
      (b3) p0.b.d((b3) p0.b.m.get());
      :try_end_c5:
      goto :goto_ce;
      :catchall_c6:
      :cond_ce:
      :goto_ce:
      return;
      :pswitch_cf:
      if ((String[]) p0.c[0x0] != 0) goto :cond_de;
      :cond_de:
      :try_start_de:
      new Landroid/content/Intent("android.intent.action.VIEW", Landroid/net/Uri.parse((String[]) p0.c[0x0]));
      (Activity) p0.b.startActivity(new Landroid/content/Intent("android.intent.action.VIEW", Landroid/net/Uri.parse((String[]) p0.c[0x0])).addCategory("android.intent.category.BROWSABLE"));
      :try_end_f2:
      goto :goto_fc;
      :catch_f3:
      Landroid/widget/Toast.makeText((Activity) p0.b, "无法打开浏览器，请稍后重试", 0x0).show();
      :goto_fc:
      return;
      :pswitch_fd:
      if (B2.k.get((View) p0.b) != (A2) p0.c) goto :cond_110;
      Ldev/local/newsoul/B2.b((View) p0.b, (A2) p0.c);
      :cond_110:
      return;
      :pswitch_111:
      (String[]) p0.b[0x0] = 0x0;
      (Runnable) p0.c.run();
      return;
      :pswitch_121:
      if ((Switch) p0.c.isChecked() == 0) goto :cond_133;
      if ((Switch) p0.c.isEnabled() == 0) goto :cond_133;
      goto :goto_135;
      :cond_133:
      :goto_135:
      (View) p0.b.setVisibility(0x0);
      return;
      :pswitch_13d:
      (Switch) p0.b.setEnabled((BooleanSupplier) p0.c.getAsBoolean());
      return;
      :pswitch_14d:
      new Ldev/local/newsoul/q2((q2) p0.b.a, (q2) p0.b.b);
      new Ldev/local/newsoul/q2((q2) p0.b.a, (q2) p0.b.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.b.a, (q2) p0.b.b).g, "效果预览 · 不发送消息", 0xd, new Ldev/local/newsoul/q2((q2) p0.b.a, (q2) p0.b.b).j));
      :try_start_17b:
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      v4.addView(Ldev/local/newsoul/z1.f(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing"), new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing").mkdirs()), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Ljava/lang/StringBuilder((Switch) p0.c.isChecked());
      :try_end_19d:
      goto :goto_1ad;
      :catchall_19e:
      v4.addView(Ldev/local/newsoul/w2.n(new Ljava/io/File(v1.c.getCacheDir(), "newsoul-voice-outgoing"), "当前版本无法显示预览", "newsoul-voice-outgoing", v8));
      :goto_1ad:
      (A2) p0.c.g("撤回效果预览", "返回");
      return;
      :pswitch_1b5:
      if (Q0.Q == 0) goto :cond_1c0;
      if (Q0.R == 0) goto :cond_1c0;
      goto :goto_1cc;
      :cond_1c0:
      if ("" == 0) goto :cond_1c5;
      goto :goto_1cc;
      :cond_1c5:
      if (Q0.R == 0) goto :cond_1ca;
      goto :goto_1cc;
      :cond_1ca:
      :goto_1cc:
      Ldev/local/newsoul/w2.s((TextView) p0.b, "部分可用：闪照可用，隐私照片 / 视频尚未就绪");
      if ("部分可用：闪照可用，隐私照片 / 视频尚未就绪".isEmpty() == 0) goto :cond_1dc;
      goto :goto_1dd;
      :cond_1dc:
      :goto_1dd:
      (TextView) p0.b.setVisibility(0x8);
      (h2) p0.c.a();
      return;
      :pswitch_1e8:
      :goto_1fd:
      if ((A2) p0.c >= (TextView) p0.b) goto :cond_210;
      if (0x8[(A2) p0.c].trim().isEmpty() != 0) goto :cond_20d;
      :cond_20d:
      goto :goto_1fd;
      :cond_210:
      new Ljava/lang/StringBuilder("编辑屏蔽词（");
      (TextView) p0.c.setText(new Ljava/lang/StringBuilder("编辑屏蔽词（").toString());
      return;
      :pswitch_22b:
      if (Ldev/local/newsoul/u1.i((q2) p0.b) == 0) goto :cond_23f;
      (Runnable[]) p0.c[0x0].run();
      :cond_23f:
      return;
      :pswitch_240:
      (TextView) p0.b.setEnabled(0x1);
      (TextView) p0.b.setText("保存原图");
      Landroid/widget/Toast.makeText((Activity) p0.c, "保存失败，请稍后重试", 0x0).show();
      return;
      :pswitch_25c:
      if ((Activity) p0.b.isFinishing() != 0) goto :cond_288;
      if ((Activity) p0.b.isDestroyed() == 0) goto :cond_26d;
      goto :goto_288;
      :cond_26d:
      if (((Activity) p0.b.getResources().getConfiguration().uiMode & 0x30) != 0x20) goto :cond_280;
      goto :goto_281;
      :cond_280:
      :goto_281:
      Ldev/local/newsoul/w2.t((Activity) p0.b, 0x1, 0x1, (String) p0.c);
      :cond_288:
      :goto_288:
      return;
      :pswitch_289:
      if (r0.b.get((TextView) p0.b) == p0.c) goto :cond_298;
      goto :goto_2f8;
      :cond_298:
      :goto_29d:
      if (r0.b >= 0xc) goto :cond_2b0;
      if (r0.b.get((TextView) p0.b).isClickable() == 0) goto :cond_2a8;
      goto :goto_2b7;
      :cond_2a8:
      if ((r0.b.get((TextView) p0.b).getParent() instanceof View) != 0) goto :cond_2b2;
      :cond_2b0:
      goto :goto_2b7;
      :cond_2b2:
      goto :goto_29d;
      :goto_2b7:
      if ((q0) r0.a.get((TextView) p0.b) == 0) goto :cond_2cc;
      if ((q0) r0.a.get((TextView) p0.b).b.get() != (View) (TextView) p0.b) goto :cond_2cc;
      if ((View) (TextView) p0.b == 0) goto :cond_2cc;
      goto :goto_2f8;
      :cond_2cc:
      if ((q0) r0.a.get((TextView) p0.b) == 0) goto :cond_2e4;
      if ((View) (q0) r0.a.get((TextView) p0.b).b.get() == 0) goto :cond_2e4;
      (View) (q0) r0.a.get((TextView) p0.b).b.get().setOnLongClickListener(0x0);
      (View) (q0) r0.a.get((TextView) p0.b).b.get().setLongClickable((q0) r0.a.get((TextView) p0.b).c);
      :cond_2e4:
      if ((View) (TextView) p0.b == 0) goto :cond_2f8;
      if (Ldev/local/newsoul/k.h((View) (TextView) p0.b) == 0) goto :cond_2ed;
      goto :goto_2f8;
      :cond_2ed:
      new Ldev/local/newsoul/q0((TextView) p0.b, (View) (TextView) p0.b);
      (View) (TextView) p0.b.setOnLongClickListener(new Ldev/local/newsoul/q0((TextView) p0.b, (View) (TextView) p0.b));
      :cond_2f8:
      :goto_2f8:
      return;
      :pswitch_2f9:
      Ldev/local/newsoul/g0.b((Activity) p0.b, (a0) p0.c, "导出失败，请确认已有记录后重试");
      return;
      :pswitch_307:
      :try_start_30f:
      :try_end_31c:
      goto :goto_323;
      :catchall_31d:
      Ldev/local/newsoul/j.c("ad failure callback", e);
      :goto_323:
      return;
      :pswitch_data_324:

      // .packed-switch 0x0
      // :pswitch_307
      // :pswitch_2f9
      // :pswitch_289
      // :pswitch_25c
      // :pswitch_240
      // :pswitch_22b
      // :pswitch_1e8
      // :pswitch_1b5
      // :pswitch_14d
      // :pswitch_13d
      // :pswitch_121
      // :pswitch_111
      // :pswitch_fd
      // :pswitch_cf
      // :pswitch_b4
      // :pswitch_9d

      // .catchall {:try_start_10 .. :try_end_18} :catchall_3c
      // .catchall {:try_start_1a .. :try_end_53} :catchall_3c
      // .catchall {:try_start_53 .. :try_end_58} :catchall_73
      // .catchall {:try_start_58 .. :try_end_5d} :catchall_76
      // .catchall {:try_start_5d .. :try_end_60} :catchall_78
      // .catchall {:try_start_60 .. :try_end_63} :catchall_76
      // .catchall {:try_start_63 .. :try_end_72} :catchall_73
      // .catchall {:try_start_79 .. :try_end_7c} :catchall_7d
      // .catchall {:try_start_7e .. :try_end_82} :catchall_76
      // .catchall {:try_start_82 .. :try_end_85} :catchall_86
      // .catchall {:try_start_87 .. :try_end_8b} :catchall_73
      // .catchall {:try_start_bc .. :try_end_c5} :catchall_c6
      // try :try_start_de..:try_end_f2 catch (Ljava/lang/RuntimeException) -> :catch_f3
      // .catchall {:try_start_17b .. :try_end_19d} :catchall_19e
      // .catchall {:try_start_30f .. :try_end_31c} :catchall_31d
    }

}