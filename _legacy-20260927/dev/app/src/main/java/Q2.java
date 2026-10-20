package dev.local.newsoul;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Q2 implements c3, P2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ b3 b;
    public final /* synthetic */ h3 c;

    public /* synthetic */ Q2(b3 b3Var, h3 h3Var, int i) {
        this.a = i;
        this.b = b3Var;
        this.c = h3Var;
    }

    @Override // dev.local.newsoul.P2
    public void a(String str) {
        b3 b3Var = this.b;
        b3Var.getClass();
        b3Var.a(new r(b3Var, this.c, str), new U2(0, b3Var));
    }

    @Override // dev.local.newsoul.c3
    public void run() throws IllegalAccessException, InvocationTargetException {
        final int i = 0;
        final int i2 = 1;
        switch (this.a) {
            case 0:
                final b3 b3Var = this.b;
                b3Var.j();
                if (b3Var.l != null) {
                    b3Var.i();
                    return;
                }
                h3 h3VarG = b3Var.f.g(this.c.a);
                MediaPlayer mediaPlayer = new MediaPlayer();
                b3Var.l = mediaPlayer;
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(1).build());
                mediaPlayer.setOnPreparedListener(new R2(b3Var));
                mediaPlayer.setOnCompletionListener(new S2(b3Var));
                mediaPlayer.setOnErrorListener(new T2(b3Var));
                try {
                    mediaPlayer.setDataSource(h3VarG.e.getAbsolutePath());
                    mediaPlayer.prepareAsync();
                    return;
                } catch (Throwable th) {
                    b3Var.i();
                    throw th;
                }
            case 1:
            default:
                this.b.d(this.c);
                return;
            case 2:
                this.b.d(this.c);
                return;
            case 3:
                h3 h3Var = this.c;
                C0093x1 c0093x1 = b3Var.f;
                String str = h3Var.a;
                synchronized (c0093x1) {
                    h3 h3VarG2 = c0093x1.g(str);
                    File file = new File(c0093x1.a, str.concat(".meta"));
                    File file2 = new File(c0093x1.a, str.concat(".deleted"));
                    Files.move(file.toPath(), file2.toPath(), StandardCopyOption.ATOMIC_MOVE);
                    try {
                        Files.delete(h3VarG2.e.toPath());
                        Files.deleteIfExists(file2.toPath());
                    } catch (IOException e) {
                        Files.move(file2.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE);
                        throw e;
                    }
                }
                return;
            case 4:
                b3 b3Var3 = this.b;
                b3Var3.getClass();
                h3 h3Var2 = this.c;
                b3Var3.e("修改备注", h3Var2.b, new Q2(b3Var3, h3Var2, i2), new Q2(b3Var3, h3Var2, 2));
                return;
            case 5:
                final b3 b3Var4 = this.b;
                C0067q2 c0067q2G = b3Var4.g();
                LinearLayout linearLayout = c0067q2G.h;
                final h3 h3Var3 = this.c;
                linearLayout.addView(AbstractC0090w2.n(c0067q2G.g, "删除「" + h3Var3.b + "」？", 16, c0067q2G.i));
                c0067q2G.d.setNegativeButton("取消", new L(1));
                c0067q2G.g("删除语音", "删除");
                b3Var4.k(new Q2(b3Var4, h3Var3, 8));
                c0067q2G.e.getButton(-2).setOnClickListener(new Z2(b3Var4, h3Var3, i));
                c0067q2G.e.getButton(-1).setOnClickListener(new Z2(b3Var4, h3Var3, i2));
                return;
            case 6:
                this.b.d(this.c);
                return;
            case 7:
                b3 b3Var5 = this.b;
                if (b3Var5.n) {
                    return;
                }
                b3Var5.j();
                b3Var5.i();
                b3Var5.n = true;
                b3Var5.q.b.execute(new RunnableC0024g(b3Var5, this.c, 16));
                return;
        }
    }
}
