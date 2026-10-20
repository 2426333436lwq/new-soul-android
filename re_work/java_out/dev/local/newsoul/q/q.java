package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/q ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class q extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final KeyEvent$Callback b;
    public final boolean c;
    public final Object d;
    public final Object e;

    public void super(Object p0) {
      super();
      this.a = 0x0;
      this.b = p1;
      this.c = p2;
      this.e = p3;
      this.d = p4;
      return;
    }

    public void super(Ldev/local/newsoul/r2;Landroid/content/SharedPreferences;Ljava/lang/String;Z p0) {
      super();
      this.a = 0x2;
      this.b = p1;
      this.e = p2;
      this.d = p3;
      this.c = p4;
      return;
    }

    public void super(Object p0) {
      super();
      this.a = 0x1;
      this.e = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_78 */ }
      (r2) p0.b.b = 0x1;
      :try_start_1b:
      v2.setChecked(v0);
      :try_end_1e:
      v2.b = v1;
      return;
      :catchall_21:
      v2.b = v1;
      throw e;
      :pswitch_25:
      (t1) p0.e.g = 0x0;
      if (Ldev/local/newsoul/u1.b((Activity) p0.b) == 0) goto :cond_62;
      :try_start_3a:
      :try_end_42:
      goto :goto_44;
      :catch_43:
      :goto_44:
      if ((t1) p0.e != 0) goto :cond_47;
      goto :goto_62;
      :cond_47:
      if (p0.c == 0) goto :cond_4e;
      goto :goto_50;
      :cond_4e:
      :goto_50:
      if (Ldev/local/newsoul/u1.b((Activity) p0.b) == 0) goto :cond_5d;
      Landroid/widget/Toast.makeText((Activity) p0.b, "已取消插眼", 0x0).show();
      :cond_5d:
      :try_start_5d:
      :try_end_62:
      :catch_62:
      :cond_62:
      :goto_62:
      return;
      :pswitch_63:
      new Ldev/local/newsoul/r((Activity) p0.b, (Method) p0.e, p0.d);
      Ldev/local/newsoul/t.b((Activity) p0.b, new Ldev/local/newsoul/r((Activity) p0.b, (Method) p0.e, p0.d), p0.c);
      return;
      :pswitch_data_78:

      // .packed-switch 0x0
      // :pswitch_63
      // :pswitch_25

      // .catchall {:try_start_1b .. :try_end_1e} :catchall_21
      // try :try_start_3a..:try_end_42 catch (Ljava/lang/Exception) -> :catch_43
      // try :try_start_5d..:try_end_62 catch (Ljava/lang/Exception) -> :catch_62
    }

}