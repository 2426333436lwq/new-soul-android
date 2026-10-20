/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnErrorListener
 */
package dev.local.newsoul;

import android.content.Context;
import android.media.MediaPlayer;
import dev.local.newsoul.b3;
import dev.local.newsoul.d3;

public final class T2
implements MediaPlayer.OnErrorListener {
    public final b3 a;

    public /* synthetic */ T2(b3 b32) {
        this.a = b32;
    }

    public final boolean onError(MediaPlayer object, int n, int n2) {
        object = this.a;
        ((b3)object).i();
        d3.i((Context)((b3)object).c, "\u6682\u65f6\u65e0\u6cd5\u8bd5\u542c");
        return true;
    }
}

