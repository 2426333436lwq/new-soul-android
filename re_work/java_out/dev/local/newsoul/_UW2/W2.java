package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/W2 ====
// super: Ljava/lang/Object
// implements: c
// source: "SourceFile"

public final  class W2 extends Ljava/lang/Object implements Ldev/local/newsoul/c3 {

    public final b3 a;
    public final Context b;
    public final String c;
    public final String d;
    public final int e;

    public void super(Ldev/local/newsoul/b3;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      return;
    }

    public final void run() {
      :try_start_19:
      Ldev/local/newsoul/d3.b(v4, v0);
      new Landroid/media/MediaMetadataRetriever();
      :try_end_21:
      :try_start_21:
      v0.setDataSource(v4.getAbsolutePath());
      if (v0.extractMetadata(0x9) == 0) goto :cond_60;
      :try_end_34:
      if (compare(v0.extractMetadata(0x9), 0x0) <= 0) goto :cond_60;
      :try_start_3a:
      v0.release();
      new Ljava/io/FileInputStream(v4);
      :try_end_42:
      :try_start_42:
      v3.f.a(v0, v1, v2);
      :try_end_47:
      :try_start_47:
      v0.close();
      :try_end_4a:
      return;
      :catchall_52:
      goto :goto_6c;
      :catchall_54:
      :try_start_55:
      e.close();
      :try_end_58:
      goto :goto_5d;
      :catchall_59:
      :try_start_5a:
      v1.addSuppressed(e);
      :goto_5d:
      throw v1;
      :try_end_5e:
      :catchall_5e:
      goto :goto_68;
      :cond_60:
      :try_start_60:
      new Ljava/io/IOException("音频无法读取");
      throw new Ljava/io/IOException("音频无法读取");
      :try_end_68:
      :goto_68:
      :try_start_68:
      e.release();
      throw e;
      :try_end_6c:
      :goto_6c:
      throw e;

      // .catchall {:try_start_19 .. :try_end_21} :catchall_52
      // .catchall {:try_start_21 .. :try_end_34} :catchall_5e
      // .catchall {:try_start_3a .. :try_end_42} :catchall_52
      // .catchall {:try_start_42 .. :try_end_47} :catchall_54
      // .catchall {:try_start_47 .. :try_end_4a} :catchall_52
      // .catchall {:try_start_55 .. :try_end_58} :catchall_59
      // .catchall {:try_start_5a .. :try_end_5e} :catchall_52
      // .catchall {:try_start_60 .. :try_end_68} :catchall_5e
      // .catchall {:try_start_68 .. :try_end_6c} :catchall_52
    }

}