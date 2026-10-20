/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface$OnClickListener
 *  android.media.AudioAttributes$Builder
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnErrorListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 */
package dev.local.newsoul;

import android.content.DialogInterface;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.LinearLayout;
import dev.local.newsoul.L;
import dev.local.newsoul.P2;
import dev.local.newsoul.R2;
import dev.local.newsoul.S2;
import dev.local.newsoul.T2;
import dev.local.newsoul.Z2;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.g_0;
import dev.local.newsoul.h3;
import dev.local.newsoul.q2;
import dev.local.newsoul.r;
import dev.local.newsoul.u2_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/*
 * Renamed from dev.local.newsoul.Q2
 */
public final class q2_0
implements c3,
P2 {
    public final int a;
    public final b3 b;
    public final h3 c;

    public /* synthetic */ q2_0(b3 b32, h3 h32, int n) {
        this.a = n;
        this.b = b32;
        this.c = h32;
    }

    @Override
    public void a(String string) {
        b3 b32 = this.b;
        b32.getClass();
        b32.a(new r(b32, this.c, string), new u2_0(0, b32));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        MediaPlayer mediaPlayer;
        switch (this.a) {
            default: {
                this.b.d(this.c);
                return;
            }
            case 7: {
                b3 b32 = this.b;
                if (b32.n) {
                    return;
                }
                b32.j();
                b32.i();
                b32.n = true;
                b32.q.b.execute(new g_0(b32, this.c, 16));
                return;
            }
            case 6: {
                this.b.d(this.c);
                return;
            }
            case 5: {
                b3 b33 = this.b;
                q2 q22 = b33.g();
                LinearLayout linearLayout = q22.h;
                h3 h32 = this.c;
                String string = h32.b;
                CharSequence charSequence = new StringBuilder("\u5220\u9664\u300c");
                charSequence.append(string);
                charSequence.append("\u300d\uff1f");
                charSequence = charSequence.toString();
                int n = q22.i;
                linearLayout.addView((View)w2.n(q22.g, (String)charSequence, 16, n));
                q22.d.setNegativeButton((CharSequence)"\u53d6\u6d88", (DialogInterface.OnClickListener)new L(1));
                q22.g("\u5220\u9664\u8bed\u97f3", "\u5220\u9664");
                b33.k(new q2_0(b33, h32, 8));
                q22.e.getButton(-2).setOnClickListener((View.OnClickListener)new Z2(b33, h32, 0));
                q22.e.getButton(-1).setOnClickListener((View.OnClickListener)new Z2(b33, h32, 1));
                return;
            }
            case 4: {
                b3 b34 = this.b;
                b34.getClass();
                h3 h33 = this.c;
                b34.e("\u4fee\u6539\u5907\u6ce8", h33.b, new q2_0(b34, h33, 1), new q2_0(b34, h33, 2));
                return;
            }
            case 3: {
                Object object = this.b;
                Object object2 = this.c;
                object = ((b3)object).f;
                String string = ((h3)object2).a;
                synchronized (object) {
                    try {
                        h3 h34 = ((x1_0)object).g(string);
                        object2 = new File(((x1_0)object).a, string.concat(".meta"));
                        File file = new File(((x1_0)object).a, string.concat(".deleted"));
                        Files.move(((File)object2).toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE);
                        try {
                            Files.delete(h34.e.toPath());
                        }
                        catch (IOException iOException) {
                            Files.move(file.toPath(), ((File)object2).toPath(), StandardCopyOption.ATOMIC_MOVE);
                            throw iOException;
                        }
                        Files.deleteIfExists(file.toPath());
                        return;
                    }
                    catch (Throwable throwable) {}
                    throw throwable;
                }
            }
            case 2: {
                this.b.d(this.c);
                return;
            }
            case 0: 
        }
        b3 b35 = this.b;
        b35.j();
        if (b35.l != null) {
            b35.i();
            return;
        }
        h3 h35 = b35.f.g(this.c.a);
        b35.l = mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(1).build());
        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener)new R2(b35));
        mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener)new S2(b35));
        mediaPlayer.setOnErrorListener((MediaPlayer.OnErrorListener)new T2(b35));
        try {
            mediaPlayer.setDataSource(h35.e.getAbsolutePath());
            mediaPlayer.prepareAsync();
            return;
        }
        catch (Throwable throwable) {
            b35.i();
            throw throwable;
        }
    }
}

