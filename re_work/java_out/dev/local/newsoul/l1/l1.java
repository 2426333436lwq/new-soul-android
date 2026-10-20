package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/l1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class l1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Activity b;
    public final boolean c;
    public final Object d;

    public void super(g p0) {
      super();
      this.a = 0x1;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      return;
    }

    public void super(ZLandroid/app/Activity;Landroid/widget/TextView; p0) {
      super();
      this.a = 0x0;
      this.c = p1;
      this.b = p2;
      this.d = p3;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_3e6 */ }
      new Ldev/local/newsoul/q2(p0.b, p0.c);
      new Ldev/local/newsoul/q2(p0.b, p0.c).t = "输入时查找已有词，点击“添加”加入草稿；也可粘贴多行批量添加。点击词条右侧可删除。只有已添加的词会在点击“保存”后生效。\n\n最多 100 个词，每词 100 字符。英文不区分大小写。正文、标题或话题包含任意词即隐藏。";
      new Ljava/util/ArrayList();
      :goto_30:
      if (v6 >= v5) goto :cond_48;
      if (v2[v6].trim().isEmpty() != 0) goto :cond_45;
      :cond_45:
      goto :goto_30;
      :cond_48:
      new Ljava/util/ArrayList(v13);
      new Landroid/widget/EditText(v0.g);
      new Landroid/widget/EditText(v0.g).setTextSize(0x41800000    # 16.0f);
      new Landroid/widget/EditText(v0.g).setTextColor(v0.i);
      new Landroid/widget/EditText(v0.g).setHintTextColor(v0.j);
      v0.c.b(new Landroid/widget/EditText(v0.g), 0x8, Ldev/local/newsoul/L2.e(0x8));
      new Landroid/widget/EditText(v0.g).setPadding(Ldev/local/newsoul/w2.i(v14, 0xe), Ldev/local/newsoul/w2.i(v14, 0xc), Ldev/local/newsoul/w2.i(v14, 0xe), Ldev/local/newsoul/w2.i(v14, 0xc));
      new Landroid/widget/EditText(v0.g).setGravity(0x800013);
      new Landroid/widget/EditText(v0.g).setInputType(0x20001);
      new Landroid/widget/EditText(v0.g).setMinLines(0x1);
      new Landroid/widget/EditText(v0.g).setMaxLines(0x4);
      new Landroid/widget/EditText(v0.g).setMinHeight(Ldev/local/newsoul/w2.i(v14, 0x30));
      new Landroid/widget/EditText(v0.g).setHint("查找或添加屏蔽词");
      new Landroid/widget/EditText(v0.g).setContentDescription("查找或添加屏蔽词");
      Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表").setMinHeight(Ldev/local/newsoul/w2.i(v14, 0x30));
      Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表").setMinWidth(Ldev/local/newsoul/w2.i(v14, 0x40));
      Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表").setPadding(Ldev/local/newsoul/w2.i(v14, 0x12), Ldev/local/newsoul/w2.i(v14, 0x8), Ldev/local/newsoul/w2.i(v14, 0x12), Ldev/local/newsoul/w2.i(v14, 0x8));
      Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表").setSelected(0x1);
      v0.c.b(Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), 0x5, Ldev/local/newsoul/L2.e(0x5));
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"));
      new Ldev/local/newsoul/b2(v0.g, v14, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/EditText(v0.g));
      new Ldev/local/newsoul/b2(v0.g, v14, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/EditText(v0.g)).setGravity(0x10);
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Ldev/local/newsoul/b2(v0.g, v14, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/EditText(v0.g)).addView(new Landroid/widget/EditText(v0.g), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2);
      new Ldev/local/newsoul/b2(v0.g, v14, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/EditText(v0.g)).addView(Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/LinearLayout$LayoutParams(-0x2, -0x2));
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      v0.h.addView(new Ldev/local/newsoul/b2(v0.g, v14, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Landroid/widget/EditText(v0.g)), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      new Landroid/widget/LinearLayout(v0.g);
      new Landroid/widget/LinearLayout(v0.g).setGravity(0x10);
      new Landroid/widget/LinearLayout(v0.g).setPadding(0x0, Ldev/local/newsoul/w2.i(v14, 0x12), 0x0, Ldev/local/newsoul/w2.i(v14, 0x8));
      new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f);
      new Landroid/widget/LinearLayout(v0.g).addView(Ldev/local/newsoul/w2.n(v0.g, "屏蔽词", 0x10, v0.i), new Landroid/widget/LinearLayout$LayoutParams(0x0, -0x2, 0x3f800000    # 1.0f));
      new Landroid/widget/LinearLayout(v0.g).addView(Ldev/local/newsoul/w2.n(v0.g, v4, 0xd, v0.j));
      v0.h.addView(new Landroid/widget/LinearLayout(v0.g));
      new Ldev/local/newsoul/R0(v0.g);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      v0.h.addView(new Ldev/local/newsoul/R0(v0.g), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词").setMinHeight(Ldev/local/newsoul/w2.i(v14, 0x30));
      Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词").setVisibility(0x8);
      new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2);
      v0.h.addView(Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词"), new Landroid/widget/LinearLayout$LayoutParams(-0x1, -0x2));
      -0x2.<init>(new Landroid/widget/EditText(v0.g), v13, Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表"), new Ldev/local/newsoul/R0(v0.g), v0, v14, new String[][] {0x0}, new String[][] {0x0}, new int[][] {0x0}, Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词"), Ldev/local/newsoul/w2.n(v0.g, v4, 0xd, v0.j));
      -0x2.<init>(new String[][] {0x0}, v13, new Landroid/widget/EditText(v0.g), new int[][] {0x0}, Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词"), -0x2);
      Ldev/local/newsoul/w2.m(v0, v4, "撤销上一次删除屏蔽词").setOnClickListener(-0x2);
      new Ldev/local/newsoul/a2(-0x2, 0x1);
      new Landroid/widget/EditText(v0.g).addTextChangedListener(new Ldev/local/newsoul/a2(-0x2, 0x1));
      new Ldev/local/newsoul/a2(-0x2, 0x1).<init>(new Landroid/widget/EditText(v0.g), v13, new String[][] {0x0}, -0x2, 0x1);
      new Ldev/local/newsoul/A0(0x3, new Ldev/local/newsoul/a2(-0x2, 0x1));
      Ldev/local/newsoul/w2.m(v0, "添加", "添加到屏蔽词列表").setOnClickListener(new Ldev/local/newsoul/A0(0x3, new Ldev/local/newsoul/a2(-0x2, 0x1)));
      -0x2.run();
      new Ldev/local/newsoul/X1(v13, new Ljava/util/ArrayList(v13));
      v0.c(new Ldev/local/newsoul/X1(v13, new Ljava/util/ArrayList(v13)));
      v0.g("编辑屏蔽词", "保存");
      v0.e.getWindow().setSoftInputMode(0x13);
      v0.h.setFocusableInTouchMode(0x1);
      0x1.<init>(v13, v15, (g) (Runnable) p0.d, v14, v0, new Landroid/widget/EditText(v0.g));
      v0.e.getButton(-0x1).setOnClickListener(0x1);
      return;
      :pswitch_225:
      if (v1.c == 0) goto :cond_305;
      if (v1.b.getIntent().getStringExtra("url") == 0) goto :cond_2fa;
      if (v1.b.getIntent().getStringExtra("url").isEmpty() == 0) goto :cond_245;
      goto :goto_2fa;
      :cond_245:
      (TextView) v1.d.setEnabled(0x0);
      (TextView) v1.d.setText("保存中…");
      :try_start_251:
      :try_end_284:
      :goto_285:
      if (v13 == 0) goto :cond_2d0;
      :try_start_287:
      v13.getDeclaredMethod(v11, new Class[][] {v12}).setAccessible(0x1);
      :try_end_293:
      :try_start_293:
      if ((v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9) instanceof Future) == 0) goto :cond_2c3;
      new Ldev/local/newsoul/H((Future) v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9), v1.b, v1.b.getIntent().getStringExtra("url").isEmpty(), 0x4);
      new Ljava/lang/Thread(new Ldev/local/newsoul/H((Future) v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9), v1.b, v1.b.getIntent().getStringExtra("url").isEmpty(), 0x4), "NewSoul-privacy-photo-save");
      new Ljava/lang/Thread(new Ldev/local/newsoul/H((Future) v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9), v1.b, v1.b.getIntent().getStringExtra("url").isEmpty(), 0x4), "NewSoul-privacy-photo-save").start();
      goto :goto_3e4;
      :catchall_2c1:
      goto :goto_2e0;
      :cond_2c3:
      new Ljava/lang/IllegalStateException("Glide target is not Future");
      throw new Ljava/lang/IllegalStateException("Glide target is not Future");
      :catch_2cb:
      goto :goto_285;
      :cond_2d0:
      new Ljava/lang/NoSuchMethodException(new Ldev/local/newsoul/H((Future) v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9), v1.b, v1.b.getIntent().getStringExtra("url").isEmpty(), 0x4).getName().concat(".load"));
      throw new Ljava/lang/NoSuchMethodException(new Ldev/local/newsoul/H((Future) v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}).getClass().getMethod("submit", v9).invoke(v14.invoke(v2[v6], new Object[][] {v1.b.getIntent().getStringExtra("url")}), v9), v1.b, v1.b.getIntent().getStringExtra("url").isEmpty(), 0x4).getName().concat(".load"));
      :try_end_2e0:
      :goto_2e0:
      v1.b.getIntent().getStringExtra("url").isEmpty().setEnabled(0x1);
      v1.b.getIntent().getStringExtra("url").isEmpty().setText("保存原图");
      Q0.S.log(0x6, "SoulHook102", "privacy photo download setup failed", e);
      Landroid/widget/Toast.makeText(v1.b, "保存功能当前不可用", 0x0).show();
      goto :goto_3e4;
      :cond_2fa:
      :goto_2fa:
      Landroid/widget/Toast.makeText(v1.b, "原图地址不可用", 0x0).show();
      goto :goto_3e4;
      :cond_305:
      if (v1.b.getIntent().getStringExtra("privacy photo download setup failed") == 0) goto :cond_3db;
      if (v1.b.getIntent().getStringExtra("privacy photo download setup failed").isEmpty() == 0) goto :cond_31d;
      goto :goto_3db;
      :cond_31d:
      :try_start_31d:
      if (v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f) < 0) goto :cond_32e;
      goto :goto_32f;
      :cond_32e:
      :goto_32f:
      if (v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).lastIndexOf(0x2e) <= v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).lastIndexOf(0x2f)) goto :cond_357;
      if ((v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).length() - v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).lastIndexOf(0x2e)) > 0x6) goto :cond_357;
      if (v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).substring(v1.b.getIntent().getStringExtra("privacy photo download setup failed").substring(0x0, v1.b.getIntent().getStringExtra("privacy photo download setup failed").indexOf(0x3f)).lastIndexOf(0x2e)).toLowerCase(Locale.ROOT).matches("\\\\.(mp4|mov|m4v|webm)") == 0) goto :cond_357;
      goto :goto_359;
      :cond_357:
      :goto_359:
      new Ljava/lang/StringBuilder("NewSoul_");
      new Landroid/app/DownloadManager$Request(Landroid/net/Uri.parse(v1.b.getIntent().getStringExtra("privacy photo download setup failed")));
      new Ljava/lang/StringBuilder("New Soul/");
      if ((DownloadManager) v1.b.getSystemService("download") == 0) goto :cond_3c2;
      Landroid/widget/Toast.makeText(v1.b, "已开始保存到 Movies/New Soul", 0x0).show();
      new Ljava/lang/StringBuilder("privacy video download enqueued: id=");
      Ldev/local/newsoul/Q0.w(new Ljava/lang/StringBuilder("privacy video download enqueued: id=").toString());
      goto :goto_3e4;
      :catchall_3c0:
      goto :goto_3ca;
      :cond_3c2:
      new Ljava/lang/IllegalStateException("DownloadManager unavailable");
      throw new Ljava/lang/IllegalStateException("DownloadManager unavailable");
      :try_end_3ca:
      :goto_3ca:
      Q0.S.log(0x6, "SoulHook102", "privacy video download failed", e);
      Landroid/widget/Toast.makeText(v1.b, "视频保存失败，请稍后重试", 0x0).show();
      goto :goto_3e4;
      :cond_3db:
      :goto_3db:
      Landroid/widget/Toast.makeText(v1.b, "视频地址不可用", 0x0).show();
      :goto_3e4:
      return;
      :pswitch_data_3e6:

      // .packed-switch 0x0
      // :pswitch_225

      // .catchall {:try_start_251 .. :try_end_284} :catchall_2c1
      // try :try_start_287..:try_end_293 catch (Ljava/lang/NoSuchMethodException) -> :catch_2cb
      // .catchall {:try_start_287 .. :try_end_293} :catchall_2c1
      // .catchall {:try_start_293 .. :try_end_2e0} :catchall_2c1
      // .catchall {:try_start_31d .. :try_end_3ca} :catchall_3c0
    }

}