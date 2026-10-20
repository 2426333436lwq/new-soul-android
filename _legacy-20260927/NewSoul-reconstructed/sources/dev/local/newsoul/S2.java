/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 */
package dev.local.newsoul;

import android.media.MediaPlayer;
import dev.local.newsoul.b3;

public final class S2
implements MediaPlayer.OnCompletionListener {
    public final b3 a;

    public /* synthetic */ S2(b3 b32) {
        this.a = b32;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.a.i();
    }
}

