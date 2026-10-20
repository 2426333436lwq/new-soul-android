package dev.local.newsoul;

import android.media.MediaPlayer;
import android.view.View;

/* 本文件合并了仅大小写不同的两个类 S2 / s2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: S2.class */
public final /* synthetic */ class S2 implements MediaPlayer.OnCompletionListener {
    public final b3 a;

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.a.i();
    }


    public S2(dev.local.newsoul.b3 p0) {
        super();
        this.a = p0;
    }
}

/* JADX INFO: loaded from: s2.class */
final /* synthetic */ class s2 implements View.OnLayoutChangeListener {
    public final C0086v2 a;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int C0035i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a.a();
    }


    public s2(dev.local.newsoul.C0086v2 p0) {
        super();
        this.a = p0;
    }
}
