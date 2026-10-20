package dev.local.newsoul;

import android.media.MediaPlayer;
import android.widget.TextView;

/* JADX INFO: loaded from: R2.class */
public final /* synthetic */ class R2 implements MediaPlayer.OnPreparedListener {
    public final b3 a;

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        b3 b3Var = this.a;
        if (b3Var.m || b3Var.l != mediaPlayer) {
            return;
        }
        try {
            b3Var.j();
            mediaPlayer.start();
            TextView textView = b3Var.p;
            if (textView != null) {
                textView.setText("停止试听");
            }
        } catch (Throwable th) {
            b3Var.i();
        }
    }


    public R2(dev.local.newsoul.b3 p0) {
        super();
        this.a = p0;
    }
}
