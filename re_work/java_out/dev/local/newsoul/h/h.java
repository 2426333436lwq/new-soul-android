package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/h ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class h extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;

    public void super(int p0) {
      super();
      this.a = p1;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_126 */ }
      B2.o = 0x0;
      new Ljava/util/ArrayList(B2.k.entrySet());
      :cond_17:
      :goto_17:
      if (v0.hasNext() == 0) goto :cond_39;
      if ((Map$Entry) v0.next().getKey() == 0) goto :cond_17;
      Ldev/local/newsoul/B2.b((View) (Map$Entry) v0.next().getKey(), (A2) (Map$Entry) v0.next().getValue());
      goto :goto_17;
      :cond_39:
      return;
      :pswitch_3a:
      :try_start_3a:
      if (compare(V.f, 0x0) == 0) goto :cond_4d;
      if ((Map$Entry) v0.next().getValue() != 0) goto :cond_4d;
      Ldev/local/newsoul/V.l("已到时间上限，记录已停止");
      :cond_4d:
      :try_end_50:
      goto :goto_56;
      :catchall_51:
      Ldev/local/newsoul/V.l("记录失败，已停止");
      :goto_56:
      return;
      :pswitch_57:
      :try_start_57:
      new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug");
      new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      V.k = new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      V.g = new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir()).b();
      V.h = V.j.getString("status", "未开启");
      if (compare(V.j.getLong("until", 0x0, "newsoul-debug"), V.j.getLong("until", 0x0, "newsoul-debug")) <= 0) goto :cond_b1;
      if (compare((V.j.getLong("until", 0x0, "newsoul-debug") - V.j.getLong("until", 0x0, "newsoul-debug")), 0x1b7740) > 0) goto :cond_b1;
      if (compare(V.g, 0x400000) >= 0) goto :cond_b1;
      V.f = V.j.getLong("until", 0x0, "newsoul-debug");
      V.h = "正在记录";
      Ldev/local/newsoul/V.k("process_resume");
      goto :goto_c0;
      :cond_b1:
      if (compare("process_resume", 0x0) == 0) goto :cond_c0;
      Ldev/local/newsoul/V.l("已自动停止");
      :try_end_ba:
      goto :goto_c0;
      :catchall_bb:
      Ldev/local/newsoul/V.l("记录不可用，请清除后重试");
      :cond_c0:
      :goto_c0:
      return;
      :pswitch_c1:
      :try_start_c3:
      new Ljava/util/ArrayList((List) Q0.z.invoke(Q0.y.invoke(0x0, 0x0), 0x0));
      :goto_db:
      if ("until".hasNext() == 0) goto :cond_f8;
      :try_end_e5:
      :try_start_e5:
      :try_end_ea:
      goto :goto_db;
      :catchall_eb:
      :try_start_ec:
      :try_end_f1:
      goto :goto_db;
      :catchall_f2:
      :cond_f8:
      return;
      :pswitch_f9:
      synchronized (j.d) {
      :try_start_fc:
      :goto_104:
      if ("until".hasNext() == 0) goto :cond_122;
      Ldev/local/newsoul/j.a((View) (Map$Entry) "until".next().getKey(), (i) (Map$Entry) "until".next().getValue());
      goto :goto_104;
      :catchall_120:
      goto :goto_124;
      :cond_122:
      }
      return;
      :goto_124:
      }
      :try_end_125:
      throw e;
      :pswitch_data_126:

      // .packed-switch 0x0
      // :pswitch_f9
      // :pswitch_c1
      // :pswitch_57
      // :pswitch_3a

      // .catchall {:try_start_3a .. :try_end_50} :catchall_51
      // .catchall {:try_start_57 .. :try_end_ba} :catchall_bb
      // .catchall {:try_start_c3 .. :try_end_e5} :catchall_f2
      // .catchall {:try_start_e5 .. :try_end_ea} :catchall_eb
      // .catchall {:try_start_ec .. :try_end_f1} :catchall_f2
      // .catchall {:try_start_fc .. :try_end_125} :catchall_120
    }

}