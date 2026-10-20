package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/R2 ====
// super: Ljava/lang/Object
// implements: MediaPlayer$OnPreparedListene
// source: "SourceFile"

public final  class R2 extends Ljava/lang/Object implements Landroid/media/MediaPlayer$OnPreparedListener {

    public final b3 a;

    public void super(b3 p0) {
      super();
      this.a = p1;
      return;
    }

    public final void onPrepared(MediaPlayer p0) {
      if (p0.a.m != 0) goto :cond_1e;
      if (p0.a.l == p1) goto :cond_b;
      goto :goto_1e;
      :cond_b:
      :try_start_b:
      p0.a.j();
      p1.start();
      if (p0.a.p == 0) goto :cond_1e;
      p0.a.p.setText("停止试听");
      :try_end_1a:
      goto :goto_1e;
      :catchall_1b:
      p0.a.i();
      :cond_1e:
      :goto_1e:
      return;

      // .catchall {:try_start_b .. :try_end_1a} :catchall_1b
    }

}