package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/H ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class H extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public void super(Landroid/app/Activity;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p4;
      this.c = p1;
      this.b = p2;
      this.d = p3;
      return;
    }

    public void super(Uri p0) {
      super();
      this.a = 0x5;
      this.d = p1;
      this.c = p2;
      this.b = p3;
      return;
    }

    public void super(Ljava/lang/Object;Landroid/app/Activity;Ljava/lang/Object;I p0) {
      super();
      this.a = p4;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      return;
    }

    public void super(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p4;
      this.b = p1;
      this.d = p2;
      this.c = p3;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_406 */ }
      if ((b3) p0.b.m != 0) goto :cond_116;
      if ((b3) p0.b.h == (q2) p0.d) goto :cond_27;
      goto :goto_116;
      :cond_27:
      :try_start_27:
      (b3) p0.b.j();
      :try_end_2a:
      (q2) p0.d.h.removeAllViews();
      new Landroid/widget/EditText((q2) p0.d.g);
      new Landroid/widget/EditText((q2) p0.d.g).setTextColor((q2) p0.d.i);
      new Landroid/widget/EditText((q2) p0.d.g).setHintTextColor((q2) p0.d.j);
      new Landroid/widget/EditText((q2) p0.d.g).setHint("搜索备注");
      new Landroid/widget/EditText((q2) p0.d.g).setContentDescription("搜索语音备注");
      new Landroid/widget/EditText((q2) p0.d.g).setSingleLine(0x1);
      new Landroid/widget/EditText((q2) p0.d.g).setMinHeight(Ldev/local/newsoul/w2.i((q2) p0.d.g, 0x30));
      new Landroid/widget/EditText((q2) p0.d.g).setTextSize(0x41700000    # 15.0f);
      new Landroid/widget/EditText((q2) p0.d.g).setPadding(Ldev/local/newsoul/w2.i((q2) p0.d.g, 0xe), Ldev/local/newsoul/w2.i((q2) p0.d.g, 0xa), Ldev/local/newsoul/w2.i((q2) p0.d.g, 0xe), Ldev/local/newsoul/w2.i((q2) p0.d.g, 0xa));
      (q2) p0.d.c.b(new Landroid/widget/EditText((q2) p0.d.g), 0x8, Ldev/local/newsoul/L2.e(0x8));
      (q2) p0.d.g.getDrawable(0x108004f).mutate().setTint((q2) p0.d.j);
      (q2) p0.d.g.getDrawable(0x108004f).mutate().setBounds(0x0, 0x0, Ldev/local/newsoul/w2.i((q2) p0.d.g, 0x14), Ldev/local/newsoul/w2.i((q2) p0.d.g, 0x14));
      new Landroid/widget/EditText((q2) p0.d.g).setCompoundDrawablesRelative((q2) p0.d.g.getDrawable(0x108004f).mutate(), 0x0, 0x0, 0x0);
      new Landroid/widget/EditText((q2) p0.d.g).setCompoundDrawablePadding(Ldev/local/newsoul/w2.i((q2) p0.d.g, 0x8));
      new Landroid/widget/EditText((q2) p0.d.g).setText((b3) p0.b.k);
      new Landroid/widget/EditText((q2) p0.d.g).setSelection(new Landroid/widget/EditText((q2) p0.d.g).length());
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      (q2) p0.d.h.addView(new Landroid/widget/EditText((q2) p0.d.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout((q2) p0.d.g);
      new Landroid/widget/LinearLayout((q2) p0.d.g).setOrientation(0x1);
      new Landroid/widget/LinearLayout((q2) p0.d.g).setPadding(0x0, Ldev/local/newsoul/w2.i((q2) p0.d.g, 0xc), 0x0, 0x0);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      (q2) p0.d.h.addView(new Landroid/widget/LinearLayout((q2) p0.d.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      (b3) p0.b.h((q2) p0.d, new Landroid/widget/LinearLayout((q2) p0.d.g), (ArrayList) (List) p0.c);
      new Ldev/local/newsoul/a3((b3) p0.b, (q2) p0.d, new Landroid/widget/LinearLayout((q2) p0.d.g), (ArrayList) (List) p0.c);
      new Landroid/widget/EditText((q2) p0.d.g).addTextChangedListener(new Ldev/local/newsoul/a3((b3) p0.b, (q2) p0.d, new Landroid/widget/LinearLayout((q2) p0.d.g), (ArrayList) (List) p0.c));
      new Landroid/widget/LinearLayout((q2) p0.d.g).setFocusableInTouchMode(0x1);
      if (((q2) p0.d.h.getParent() instanceof ScrollView) == 0) goto :cond_fd;
      new Ldev/local/newsoul/Y2((b3) p0.b, (q2) p0.d, (ScrollView) (q2) p0.d.h.getParent(), (b3) p0.b.j);
      :cond_fd:
      new Ljava/lang/StringBuilder("voice library shown; count=");
      goto :goto_116;
      :catch_113:
      (b3) p0.b.b();
      :cond_116:
      :goto_116:
      return;
      :pswitch_117:
      :try_start_11f:
      (b3) p0.b.j();
      :try_end_12a:
      if (new Ljava/lang/StringBuilder("voice library shown; count=").toString() == 0) goto :cond_151;
      :try_start_12c:
      Ldev/local/newsoul/d3.i(new Ljava/lang/StringBuilder("voice library shown; count="), "已提交发送，请查看聊天中的消息状态");
      :try_end_14b:
      :cond_14b:
      :goto_14b:
      (b3) p0.b.b();
      goto :goto_166;
      :catchall_14f:
      goto :goto_15b;
      :cond_151:
      :try_start_151:
      new Ljava/lang/IllegalStateException("聊天尚未准备好");
      throw new Ljava/lang/IllegalStateException("聊天尚未准备好");
      :try_end_159:
      :catchall_159:
      :goto_15b:
      :try_start_15b:
      Ldev/local/newsoul/d3.f(new Ljava/lang/StringBuilder("voice library shown; count="), "发送未完成，请查看消息状态", e);
      :try_end_160:
      if (0x1 != 0) goto :cond_14b;
      goto :goto_14b;
      :goto_166:
      return;
      :catchall_167:
      if (0x1 != 0) goto :cond_16d;
      :cond_16d:
      (b3) p0.b.b();
      throw e;
      :pswitch_171:
      if ((q2) (b3) p0.b.e.isShowing() == 0) goto :cond_1be;
      if ((Activity) (List) p0.c.isFinishing() != 0) goto :cond_1be;
      if ((Activity) (List) p0.c.isDestroyed() == 0) goto :cond_18a;
      goto :goto_1be;
      :cond_18a:
      (LinearLayout) (q2) p0.d.removeAllViews();
      (LinearLayout) (q2) p0.d.addView(Ldev/local/newsoul/w2.n((q2) (b3) p0.b.g, "检测失败，请重新检测", 0x10, (q2) (b3) p0.b.i));
      (LinearLayout) (q2) p0.d.addView(Ldev/local/newsoul/w2.n((q2) (b3) p0.b.g, "未能完成本次检查，已有设置未改变。", 0xd, (q2) (b3) p0.b.j));
      (q2) (b3) p0.b.e.getButton(-0x3).setVisibility(0x0);
      (q2) (b3) p0.b.e.getButton(-0x3).setEnabled(0x1);
      :cond_1be:
      :goto_1be:
      return;
      :pswitch_1bf:
      (C1) (q2) (b3) p0.b.b((SharedPreferences) (q2) p0.d.getString("chat_recall_style", "below"), (String[]) (Activity) (List) p0.c, 0x0);
      return;
      :pswitch_1d1:
      (TextView) (q2) (b3) p0.b.setEnabled(0x1);
      Ldev/local/newsoul/u1.e((Activity) (Activity) (List) p0.c, (Exception) (q2) p0.d);
      return;
      :pswitch_1de:
      if (Ldev/local/newsoul/u1.i((q2) (q2) (b3) p0.b) != 0) goto :cond_1e7;
      goto :goto_213;
      :cond_1e7:
      (q2) (q2) (b3) p0.b.h.removeAllViews();
      (q2) (q2) (b3) p0.b.h.addView(Ldev/local/newsoul/w2.n((q2) (q2) (b3) p0.b.g, "读取失败，原有记录已保留", new Landroid/widget/LinearLayout((q2) p0.d.g), (q2) (q2) (b3) p0.b.j));
      new Ldev/local/newsoul/A0(0x1, (Runnable[]) (q2) p0.d);
      Ldev/local/newsoul/u1.a((q2) (q2) (b3) p0.b, "重新加载").setOnClickListener(new Ldev/local/newsoul/A0(0x1, (Runnable[]) (q2) p0.d));
      (q2) (q2) (b3) p0.b.h.addView(Ldev/local/newsoul/u1.a((q2) (q2) (b3) p0.b, "重新加载"));
      :goto_213:
      return;
      :pswitch_214:
      (t1) (q2) (q2) (b3) p0.b.g = 0x0;
      Ldev/local/newsoul/u1.e((Activity) (Activity) (List) p0.c, (Exception) (q2) p0.d);
      return;
      :pswitch_220:
      :try_start_226:
      Ldev/local/newsoul/u1.h((q2) (q2) (b3) p0.b.a);
      new Ldev/local/newsoul/x1((Activity) (List) p0.c.getFilesDir(), (q2) (q2) (b3) p0.b.a, 0x0);
      :try_end_238:
      if ("voice library shown; count=" == 0) goto :cond_242;
      :try_start_23c:
      Ldev/local/newsoul/u1.i((q2) (q2) (b3) p0.b).i((q2) (q2) (b3) p0.b.b);
      goto :goto_247;
      :catch_240:
      goto :goto_250;
      :cond_242:
      e.f((q2) (q2) (b3) p0.b.b, (q2) (q2) (b3) p0.b.c);
      :goto_247:
      new Ldev/local/newsoul/q((q2) (q2) (b3) p0.b, (Activity) (List) p0.c, "voice library shown; count=", (q2) p0.d);
      :try_end_24f:
      goto :goto_259;
      :goto_250:
      new Ldev/local/newsoul/H((q2) (q2) (b3) p0.b, (Activity) (List) p0.c, e, 0x7);
      :goto_259:
      return;
      :pswitch_25a:
      (TextView) (q2) p0.d.setEnabled(0x1);
      (TextView) (q2) p0.d.setText("保存原图");
      if ((Uri) (q2) (q2) (b3) p0.b != 0) goto :cond_26b;
      goto :goto_26d;
      :cond_26b:
      :goto_26d:
      Landroid/widget/Toast.makeText((Activity) (Activity) (List) p0.c, "保存失败", 0x0).show();
      return;
      :pswitch_277:
      :try_start_27f:
      if (((Uri) (q2) (q2) (b3) p0.b.get() instanceof Bitmap) == 0) goto :cond_2a3;
      new Ldev/local/newsoul/H((TextView) (q2) p0.d, (Activity) (List) p0.c, Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get()));
      (Activity) (List) p0.c.runOnUiThread(new Ldev/local/newsoul/H((TextView) (q2) p0.d, (Activity) (List) p0.c, Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())));
      Ldev/local/newsoul/Q0.w("保存失败".concat(Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get()))));
      goto :goto_2bc;
      :catchall_2a1:
      goto :goto_2ab;
      :cond_2a3:
      new Ljava/lang/IllegalStateException("Resource is not bitmap");
      throw new Ljava/lang/IllegalStateException("Resource is not bitmap");
      :try_end_2ab:
      :goto_2ab:
      Q0.S.log(0x6, "SoulHook102", "privacy photo download failed", e);
      new Ldev/local/newsoul/g((TextView) (q2) p0.d, (Activity) (List) p0.c, 0x4);
      (Activity) (List) p0.c.runOnUiThread(new Ldev/local/newsoul/g((TextView) (q2) p0.d, (Activity) (List) p0.c, 0x4));
      :goto_2bc:
      return;
      :pswitch_2bd:
      :try_start_2c6:
      Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).<init>((Activity) (List) p0.c, 0xc, (TextView) (q2) p0.d, "保存失败".concat(Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get()))), 0x0);
      :try_end_2d7:
      goto :goto_2e0;
      :catch_2d8:
      new Ldev/local/newsoul/g((Activity) (List) p0.c, (TextView) (q2) p0.d, 0x1);
      :goto_2e0:
      return;
      :pswitch_2e1:
      Ldev/local/newsoul/g0.b((Activity) (Activity) (List) p0.c, (a0) (Uri) (q2) (q2) (b3) p0.b, (String) (TextView) (q2) p0.d);
      return;
      :pswitch_2eb:
      :try_start_2f1:
      if (Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()) == 0) goto :cond_328;
      :try_end_303:
      if (Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()) == 0) goto :cond_316;
      :try_start_305:
      Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()).write((TextView) (q2) p0.d.c);
      Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()).flush();
      :try_end_30d:
      :try_start_30d:
      Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()).close();
      :try_end_312:
      goto :goto_330;
      :catchall_313:
      goto :goto_31c;
      :cond_316:
      :try_start_316:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :try_end_31c:
      :goto_31c:
      if (Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()) == 0) goto :cond_327;
      :try_start_31e:
      Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()).close();
      :try_end_321:
      goto :goto_327;
      :catchall_322:
      :try_start_324:
      e.addSuppressed(Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()));
      :cond_327:
      :goto_327:
      throw e;
      :cond_328:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :try_end_32e:
      :catch_32e:
      :goto_330:
      new Ldev/local/newsoul/H((Activity) (List) p0.c, (TextView) (q2) p0.d, e, 0x2);
      return;
      :pswitch_33c:
      :try_start_344:
      if (Ljava/lang/String.valueOf(Ldev/local/newsoul/Q0.T((Activity) (List) p0.c, (Bitmap) (Uri) (q2) (q2) (b3) p0.b.get())).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()).equals((Uri) (q2) (q2) (b3) p0.b.getScheme()) == 0) goto :cond_3f6;
      if ((TextView) (q2) p0.d.c == 0) goto :cond_38c;
      :try_end_35a:
      if ((TextView) (q2) p0.d.c == 0) goto :cond_37a;
      :try_start_35c:
      (TextView) (q2) p0.d.c.write((TextView) (q2) p0.d.c.getBytes(StandardCharsets.UTF_8));
      (TextView) (q2) p0.d.c.flush();
      :try_end_36a:
      :try_start_36a:
      (TextView) (q2) p0.d.c.close();
      new Ldev/local/newsoul/J((Activity) (List) p0.c, (TextView) (q2) p0.d, 0x0);
      :try_end_375:
      goto :goto_404;
      :catchall_377:
      goto :goto_380;
      :cond_37a:
      :try_start_37a:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :try_end_380:
      :goto_380:
      if ((TextView) (q2) p0.d.c == 0) goto :cond_38b;
      :try_start_382:
      (TextView) (q2) p0.d.c.close();
      :try_end_385:
      goto :goto_38b;
      :catchall_386:
      :try_start_388:
      e.addSuppressed((TextView) (q2) p0.d.c);
      :cond_38b:
      :goto_38b:
      throw e;
      :cond_38c:
      :try_end_394:
      if ((TextView) (q2) p0.d.c == 0) goto :cond_3e2;
      :try_start_396:
      new Ljava/io/ByteArrayOutputStream();
      :goto_3a1:
      if ((TextView) (q2) p0.d.c.read((Uri) (q2) (q2) (b3) p0.b.getScheme()) == e) goto :cond_3ba;
      if ((TextView) (q2) p0.d.c.read((Uri) (q2) (q2) (b3) p0.b.getScheme()) > (0x20000 - e.size())) goto :cond_3b4;
      e.write((Uri) (q2) (q2) (b3) p0.b.getScheme(), 0x0, (TextView) (q2) p0.d.c.read((Uri) (q2) (q2) (b3) p0.b.getScheme()));
      goto :goto_3a1;
      :cond_3b4:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :cond_3ba:
      :try_end_3be:
      :try_start_3be:
      (TextView) (q2) p0.d.c.close();
      new Ldev/local/newsoul/K((Activity) (List) p0.c, (TextView) (q2) p0.d, Ldev/local/newsoul/G.c(StandardCharsets.UTF_8.newDecoder().decode(Ljava/nio/ByteBuffer.wrap(e)).toString()));
      :try_end_3df:
      goto :goto_404;
      :goto_3e0:
      goto :goto_3ea;
      :cond_3e2:
      :try_start_3e2:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();
      :try_end_3e8:
      :catchall_3e8:
      goto :goto_3e0;
      :goto_3ea:
      if ((TextView) (q2) p0.d.c == 0) goto :cond_3f5;
      :try_start_3ec:
      (TextView) (q2) p0.d.c.close();
      :try_end_3ef:
      goto :goto_3f5;
      :catchall_3f0:
      :try_start_3f2:
      e.addSuppressed((TextView) (q2) p0.d.c);
      :cond_3f5:
      :goto_3f5:
      throw e;
      :cond_3f6:
      new Ljava/lang/IllegalArgumentException();
      throw new Ljava/lang/IllegalArgumentException();
      :try_end_3fc:
      :catch_3fc:
      new Ldev/local/newsoul/J((Activity) (List) p0.c, (TextView) (q2) p0.d, 0x1);
      :goto_404:
      return;
      :pswitch_data_406:

      // .packed-switch 0x0
      // :pswitch_33c
      // :pswitch_2eb
      // :pswitch_2e1
      // :pswitch_2bd
      // :pswitch_277
      // :pswitch_25a
      // :pswitch_220
      // :pswitch_214
      // :pswitch_1de
      // :pswitch_1d1
      // :pswitch_1bf
      // :pswitch_171
      // :pswitch_117

      // try :try_start_27..:try_end_2a catch (Ljava/lang/Exception) -> :catch_113
      // .catchall {:try_start_11f .. :try_end_12a} :catchall_159
      // .catchall {:try_start_12c .. :try_end_14b} :catchall_14f
      // .catchall {:try_start_151 .. :try_end_159} :catchall_159
      // .catchall {:try_start_15b .. :try_end_160} :catchall_167
      // try :try_start_226..:try_end_238 catch (Ljava/lang/Exception) -> :catch_240
      // try :try_start_23c..:try_end_24f catch (Ljava/lang/Exception) -> :catch_240
      // .catchall {:try_start_27f .. :try_end_2ab} :catchall_2a1
      // try :try_start_2c6..:try_end_2d7 catch (Ljava/lang/Exception) -> :catch_2d8
      // try :try_start_2f1..:try_end_303 catch (Ljava/lang/Exception) -> :catch_32e
      // .catchall {:try_start_305 .. :try_end_30d} :catchall_313
      // try :try_start_30d..:try_end_312 catch (Ljava/lang/Exception) -> :catch_32e
      // .catchall {:try_start_316 .. :try_end_31c} :catchall_313
      // .catchall {:try_start_31e .. :try_end_321} :catchall_322
      // try :try_start_324..:try_end_32e catch (Ljava/lang/Exception) -> :catch_32e
      // try :try_start_344..:try_end_35a catch (Ljava/lang/Exception) -> :catch_3fc
      // .catchall {:try_start_35c .. :try_end_36a} :catchall_377
      // try :try_start_36a..:try_end_375 catch (Ljava/lang/Exception) -> :catch_3fc
      // .catchall {:try_start_37a .. :try_end_380} :catchall_377
      // .catchall {:try_start_382 .. :try_end_385} :catchall_386
      // try :try_start_388..:try_end_394 catch (Ljava/lang/Exception) -> :catch_3fc
      // .catchall {:try_start_396 .. :try_end_3be} :catchall_3e8
      // try :try_start_3be..:try_end_3df catch (Ljava/lang/Exception) -> :catch_3fc
      // .catchall {:try_start_3e2 .. :try_end_3e8} :catchall_3e8
      // .catchall {:try_start_3ec .. :try_end_3ef} :catchall_3f0
      // try :try_start_3f2..:try_end_3fc catch (Ljava/lang/Exception) -> :catch_3fc
    }

}