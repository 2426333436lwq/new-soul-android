/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnPreparedListener
 */
package dev.local.newsoul;

import android.media.MediaPlayer;
import dev.local.newsoul.b3;

public final class R2
implements MediaPlayer.OnPreparedListener {
    public final b3 a;

    public /* synthetic */ R2(b3 b32) {
        this.a = b32;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        block4: {
            b3 b32 = this.a;
            if (!b32.m && b32.l == mediaPlayer) {
                b32.j();
                mediaPlayer.start();
                mediaPlayer = b32.p;
                if (mediaPlayer == null) break block4;
                try {
                    mediaPlayer.setText((CharSequence)"\u505c\u6b62\u8bd5\u542c");
                }
                catch (Throwable throwable) {
                    b32.i();
                }
            }
        }
    }
}

