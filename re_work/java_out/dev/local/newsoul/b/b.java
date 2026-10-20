package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/b ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class b extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Field b;
    public final Field c;
    public final Field d;
    public final Method e;

    public void super(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Method;I p0) {
      super();
      this.a = p5;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_154 */ }
      if (p1.getArg(0x0) == 0) goto :cond_d0;
      if (p0.b.getInt(p1.getArg(0x0)) == -0x35efa) goto :cond_23;
      goto :goto_d0;
      :cond_23:
      :try_start_2e:
      if (p0.c.get(p1.getThisObject()) != 0) goto :cond_3b;
      goto :goto_41;
      :cond_3b:
      :goto_41:
      if (0x0 == 0) goto :cond_b3;
      if (0x0.trim().isEmpty() == 0) goto :cond_4e;
      goto :goto_b3;
      :cond_4e:
      if ((ClipboardManager) -0x35efa.getSystemService(ClipboardManager.class) == 0) goto :cond_ab;
      (ClipboardManager) -0x35efa.getSystemService(ClipboardManager.class).setPrimaryClip(Landroid/content/ClipData.newPlainText("帖子文案", 0x0));
      if ((ClipboardManager) -0x35efa.getSystemService(ClipboardManager.class).getPrimaryClip() == 0) goto :cond_7e;
      if ((ClipboardManager) -0x35efa.getSystemService(ClipboardManager.class).getPrimaryClip().getItemCount() != p0.b.getInt(p1.getArg(0x0))) goto :cond_7e;
      if (0x0.contentEquals((ClipboardManager) -0x35efa.getSystemService(ClipboardManager.class).getPrimaryClip().getItemAt(0x0).getText()) == 0) goto :cond_7e;
      goto :goto_7f;
      :catchall_7c:
      goto :goto_bf;
      :cond_7e:
      :goto_7f:
      new Ljava/lang/StringBuilder("post text copied; chars=");
      Landroid/widget/Toast.makeText(-0x35efa, "已复制文案", 0x0).show();
      goto :goto_cd;
      :cond_ab:
      new Ljava/lang/IllegalStateException("Clipboard unavailable");
      throw new Ljava/lang/IllegalStateException("Clipboard unavailable");
      :cond_b3:
      :goto_b3:
      Landroid/widget/Toast.makeText(-0x35efa, "该帖子暂无可复制文案", 0x0).show();
      :try_end_be:
      goto :goto_d4;
      :goto_bf:
      Landroid/widget/Toast.makeText(-0x35efa, "复制失败，请重新打开分享页", 0x0).show();
      :goto_cd:
      goto :goto_d4;
      :cond_d0:
      :goto_d0:
      :goto_d4:
      return Landroid/widget/Toast.makeText(-0x35efa, "已复制文案", 0x0);
      :pswitch_d5:
      if ((p0.b.get(Boolean.FALSE.getThisObject()) instanceof Reference) == 0) goto :cond_eb;
      goto :goto_ec;
      :cond_eb:
      :goto_ec:
      if (((Reference) p0.b.get(Boolean.FALSE.getThisObject()).get() instanceof Context) != 0) goto :cond_f5;
      goto :goto_153;
      :cond_f5:
      if ((String) j.e.get(Boolean.FALSE.getThisObject()) != 0) goto :cond_115;
      :cond_115:
      if ((String) j.e.get(Boolean.FALSE.getThisObject()) == 0) goto :cond_14f;
      if (Ldev/local/newsoul/j.b((Context) (Reference) p0.b.get(Boolean.FALSE.getThisObject()).get(), (String) j.e.get(Boolean.FALSE.getThisObject())) != 0) goto :cond_120;
      goto :goto_14f;
      :cond_120:
      if (p0.d.get(Boolean.FALSE.getThisObject()) == 0) goto :cond_135;
      new Ldev/local/newsoul/g(p0.e, p0.d.get(Boolean.FALSE.getThisObject()), 0x0);
      :cond_135:
      new Ljava/lang/StringBuilder("ad blocked: ");
      Ldev/local/newsoul/j.e(new Ljava/lang/StringBuilder("ad blocked: ").toString());
      goto :goto_153;
      :cond_14f:
      :goto_14f:
      :goto_153:
      return " slot=";
      :pswitch_data_154:

      // .packed-switch 0x0
      // :pswitch_d5

      // .catchall {:try_start_2e .. :try_end_be} :catchall_7c
    }

}