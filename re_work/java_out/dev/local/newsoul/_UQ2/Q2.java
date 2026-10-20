package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Q2 ====
// super: Ljava/lang/Object
// implements: c, P
// source: "SourceFile"

public final  class Q2 extends Ljava/lang/Object implements Ldev/local/newsoul/c3, Ldev/local/newsoul/P2 {

    public final int a;
    public final b3 b;
    public final h3 c;

    public void super(Ldev/local/newsoul/b3;Ldev/local/newsoul/h3;I p0) {
      super();
      this.a = p3;
      this.b = p1;
      this.c = p2;
      return;
    }

    public void a(String p0) {
      new Ldev/local/newsoul/r(p0.b, p0.c, p1);
      new Ldev/local/newsoul/U2(0x0, p0.b);
      p0.b.a(new Ldev/local/newsoul/r(p0.b, p0.c, p1), new Ldev/local/newsoul/U2(0x0, p0.b));
      return;
    }

    public void run() {
      switch (p0.a) { /* :pswitch_data_17c */ }
      :pswitch_9:
      p0.b.d(p0.c);
      return;
      :pswitch_11:
      if (p0.b.n == 0) goto :cond_18;
      goto :goto_2e;
      :cond_18:
      p0.b.j();
      p0.b.i();
      p0.b.n = v2;
      new Ldev/local/newsoul/g(p0.b, p0.c, v0);
      p0.b.q.b.execute(new Ldev/local/newsoul/g(p0.b, p0.c, v0));
      :goto_2e:
      return;
      :pswitch_2f:
      p0.b.d(p0.c);
      return;
      :pswitch_37:
      new Ljava/lang/StringBuilder("删除「");
      p0.b.g().h.addView(Ldev/local/newsoul/w2.n(p0.b.g().g, new Ljava/lang/StringBuilder("删除「").toString(), v0, p0.b.g().i));
      new Ldev/local/newsoul/L(v2);
      p0.b.g().g("删除语音", "删除");
      new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x8);
      p0.b.k(new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x8));
      new Ldev/local/newsoul/Z2(p0.b, p0.c, p0.b);
      p0.b.g().e.getButton(-0x2).setOnClickListener(new Ldev/local/newsoul/Z2(p0.b, p0.c, p0.b));
      new Ldev/local/newsoul/Z2(p0.b, p0.c, v2);
      p0.b.g().e.getButton(-0x1).setOnClickListener(new Ldev/local/newsoul/Z2(p0.b, p0.c, v2));
      return;
      :pswitch_9d:
      new Ldev/local/newsoul/Q2(p0.b, p0.c, v2);
      new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x2);
      p0.b.e("修改备注", p0.c.b, new Ldev/local/newsoul/Q2(p0.b, p0.c, v2), new Ldev/local/newsoul/Q2(p0.b, p0.c, 0x2));
      return;
      :pswitch_b7:
      synchronized (p0.b.f) {
      :try_start_c4:
      new Ljava/io/File(v0.a, p0.b.n.concat(v5));
      new Ljava/io/File(v0.a, p0.b.n.concat(v4));
      new CopyOption[][v2][p0.b] = StandardCopyOption.ATOMIC_MOVE;
      :try_end_ef:
      :try_start_ef:
      Ljava/nio/file/Files.delete(v6.e.toPath());
      :try_end_f8:
      :try_start_f8:
      :try_end_ff:
      }
      return;
      :catchall_101:
      goto :goto_116;
      :catch_103:
      :try_start_104:
      new CopyOption[][v2][e] = StandardCopyOption.ATOMIC_MOVE;
      throw p0.b.n;
      :try_end_116:
      :goto_116:
      }
      throw e;
      :pswitch_118:
      p0.b.d(p0.c);
      return;
      :pswitch_120:
      p0.b.j();
      if (p0.b.l == 0) goto :cond_12d;
      p0.b.i();
      goto :goto_176;
      :cond_12d:
      new Landroid/media/MediaPlayer();
      p0.b.l = new Landroid/media/MediaPlayer();
      new Landroid/media/AudioAttributes$Builder();
      new Landroid/media/MediaPlayer().setAudioAttributes(new Landroid/media/AudioAttributes$Builder().setUsage(v2).setContentType(v2).build());
      new Ldev/local/newsoul/R2(p0.b);
      new Landroid/media/MediaPlayer().setOnPreparedListener(new Ldev/local/newsoul/R2(p0.b));
      new Ldev/local/newsoul/S2(p0.b);
      new Landroid/media/MediaPlayer().setOnCompletionListener(new Ldev/local/newsoul/S2(p0.b));
      new Ldev/local/newsoul/T2(p0.b);
      new Landroid/media/MediaPlayer().setOnErrorListener(new Ldev/local/newsoul/T2(p0.b));
      :try_start_16a:
      p0.b.n.setDataSource(p0.b.l.e.getAbsolutePath());
      p0.b.n.prepareAsync();
      :try_end_176:
      :goto_176:
      return;
      :catchall_177:
      p0.b.i();
      throw e;
      :pswitch_data_17c:

      // .packed-switch 0x0
      // :pswitch_120
      // :pswitch_9
      // :pswitch_118
      // :pswitch_b7
      // :pswitch_9d
      // :pswitch_37
      // :pswitch_2f
      // :pswitch_11

      // .catchall {:try_start_c4 .. :try_end_ef} :catchall_101
      // try :try_start_ef..:try_end_f8 catch (Ljava/io/IOException) -> :catch_103
      // .catchall {:try_start_ef .. :try_end_f8} :catchall_101
      // .catchall {:try_start_f8 .. :try_end_ff} :catchall_101
      // .catchall {:try_start_104 .. :try_end_116} :catchall_101
      // .catchall {:try_start_16a .. :try_end_176} :catchall_177
    }

}