package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/T2 ====
// super: Ljava/lang/Object
// implements: MediaPlayer$OnErrorListene
// source: "SourceFile"

public final  class T2 extends Ljava/lang/Object implements Landroid/media/MediaPlayer$OnErrorListener {

    public final b3 a;

    public void super(b3 p0) {
      super();
      this.a = p1;
      return;
    }

    public final boolean onError(Landroid/media/MediaPlayer;II p0) {
      p0.a.i();
      Ldev/local/newsoul/d3.i(p0.a.c, "暂时无法试听");
      return v0;
    }

}