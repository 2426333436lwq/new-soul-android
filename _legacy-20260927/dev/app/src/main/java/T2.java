package dev.local.newsoul;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: T2.class */
public final /* synthetic */ class T2 implements MediaPlayer.OnErrorListener {
    public final b3 a;

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int C0035i2) {
        b3 b3Var = this.a;
        b3Var.i();
        d3.i(b3Var.c, "暂时无法试听");
        return true;
    }


    public T2(dev.local.newsoul.b3 p0) {
        super();
        this.a = p0;
    }
}
