package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/T ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class T extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final void run() {
      switch (p0.a) { /* :pswitch_data_72 */ }
      Ldev/local/newsoul/Q0.d((View) p0.b);
      return;
      :pswitch_d:
      Ldev/local/newsoul/V.l("记录已清除");
      synchronized (V.c) {
      :try_start_19:
      v1.clear();
      V.m = 0x0;
      }
      :try_end_20:
      :try_start_20:
      if (V.k != 0) goto :cond_3c;
      new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug");
      new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      V.k = new Ldev/local/newsoul/Y(new Ljava/io/File(V.i.getNoBackupFilesDir(), "newsoul-debug"), V.i.getNoBackupFilesDir());
      :cond_3c:
      if ((File) V.k.a.listFiles() != 0) goto :cond_4a;
      :cond_4a:
      :goto_4b:
      if (v2 >= v3) goto :cond_60;
      if ((File) V.k.a.listFiles()[v2].delete() == 0) goto :cond_58;
      goto :goto_4b;
      :cond_58:
      new Ljava/io/IOException("Clear failed");
      throw new Ljava/io/IOException("Clear failed");
      :cond_60:
      V.g = 0x0;
      :try_end_64:
      goto :goto_69;
      :catchall_65:
      V.h = "清除失败，请重试";
      :goto_69:
      return;
      :catchall_6f:
      :try_start_70:
      }
      :try_end_71:
      throw v0;
      :pswitch_data_72:

      // .packed-switch 0x0
      // :pswitch_d

      // .catchall {:try_start_19 .. :try_end_20} :catchall_6f
      // .catchall {:try_start_20 .. :try_end_64} :catchall_65
      // .catchall {:try_start_70 .. :try_end_71} :catchall_6f
    }

}