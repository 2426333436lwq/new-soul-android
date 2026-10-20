package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/D0 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class D0 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Serializable b;
    public final Object c;
    public final Object d;
    public final Object e;

    public void super(Ljava/io/Serializable;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p5;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_156 */ }
      :try_start_11:
      Ldev/local/newsoul/u1.h(p1);
      if (u1.f == 0) goto :cond_2e;
      v1.e.dismiss();
      goto :goto_3d;
      :catch_2c:
      goto :goto_36;
      :cond_2e:
      new Ljava/lang/IllegalStateException("当前版本暂无法打开主页");
      throw new Ljava/lang/IllegalStateException("当前版本暂无法打开主页");
      :try_end_36:
      :goto_36:
      Ldev/local/newsoul/u1.e((Activity) p0.e, e);
      :goto_3d:
      return;
      :pswitch_3e:
      if ((int[]) p0.b[0x0] >= 0) goto :cond_49;
      goto :goto_14f;
      :cond_49:
      (TextView) p0.c.setEnabled(0x0);
      (Dialog) p0.d.dismiss();
      if ((s) p0.e.a == 0) goto :cond_af;
      if ((Activity) (s) p0.e.a.a.isFinishing() != 0) goto :cond_14f;
      if ((Activity) (s) p0.e.a.a.isDestroyed() == 0) goto :cond_81;
      goto :goto_14f;
      :cond_81:
      :try_start_81:
      t.c.set(Ljava/lang/Integer.valueOf((int[]) p0.b[0x0]));
      :try_end_93:
      :goto_93:
      t.c.remove();
      goto :goto_14f;
      :catchall_98:
      :try_start_99:
      t.f.log(0x6, "SoulHook102", "selected game send failed", (int[]) p0.b[0x0]);
      Landroid/widget/Toast.makeText((Activity) (s) p0.e.a.a, "发送未完成，请检查聊天页面", 0x0).show();
      :try_end_a9:
      goto :goto_93;
      :catchall_aa:
      t.c.remove();
      throw e;
      :cond_af:
      if ((int[]) p0.b[0x0] != 0) goto :cond_c3;
      new Ljava/security/SecureRandom();
      if ((Method) (s) p0.e.a.b.b == 0) goto :cond_bd;
      goto :goto_be;
      :cond_bd:
      :goto_be:
      :cond_c3:
      :try_start_c5:
      t.c.set(Ljava/lang/Integer.valueOf(new Ljava/security/SecureRandom()));
      if (t.i.getInt(t.h.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x0)})) != new Ljava/security/SecureRandom()) goto :cond_e4;
      goto :goto_e5;
      :cond_e4:
      :goto_e5:
      new Ljava/lang/StringBuilder(0x6);
      t.f.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder(0x6).toString());
      :try_end_107:
      t.c.remove();
      goto :goto_117;
      :catchall_10b:
      :try_start_10c:
      t.f.log(0x6, "SoulHook102", "game local dry-run failed", 0x6);
      :try_end_113:
      t.c.remove();
      :goto_117:
      if ((Method) (s) p0.e.a.b.b == 0) goto :cond_12b;
      new Ljava/lang/StringBuilder();
      goto :goto_130;
      :cond_12b:
      :goto_130:
      if (0x1 == 0) goto :cond_144;
      new Ljava/lang/StringBuilder();
      goto :goto_146;
      :cond_144:
      :goto_146:
      Landroid/widget/Toast.makeText((Method) (s) p0.e.a.b.c, new Ljava/lang/StringBuilder().toString(), 0x0).show();
      :cond_14f:
      :goto_14f:
      return;
      :catchall_150:
      new Ljava/lang/StringBuilder().remove();
      throw e;
      :pswitch_data_156:

      // .packed-switch 0x0
      // :pswitch_3e

      // try :try_start_11..:try_end_36 catch (Ljava/lang/Exception) -> :catch_2c
      // .catchall {:try_start_81 .. :try_end_93} :catchall_98
      // .catchall {:try_start_99 .. :try_end_a9} :catchall_aa
      // .catchall {:try_start_c5 .. :try_end_107} :catchall_10b
      // .catchall {:try_start_10c .. :try_end_113} :catchall_150
    }

}