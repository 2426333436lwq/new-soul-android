package dev.local.newsoul;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class H2 implements P2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ H2(Object obj, Object obj2, int i) {
        this.b = obj;
        this.c = obj2;
        this.a = i;
    }

    @Override // dev.local.newsoul.P2
    public void a(final String str) {
        final b3 b3Var = (b3) this.b;
        b3Var.j();
        final Context applicationContext = b3Var.c.getApplicationContext();
        final String str2 = (String) this.c;
        final int i = this.a;
        b3Var.a(new c3() { // from class: dev.local.newsoul.W2
            @Override // dev.local.newsoul.c3
            public final void run() throws IOException {
                String str3 = str2;
                String str4 = str;
                int i2 = i;
                b3 b3Var2 = b3Var;
                b3Var2.getClass();
                File fileCreateTempFile = File.createTempFile("newsoul-voice-", ".audio", applicationContext.getCacheDir());
                try {
                    d3.b(fileCreateTempFile, str3);
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    try {
                        mediaMetadataRetriever.setDataSource(fileCreateTempFile.getAbsolutePath());
                        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
                        if (strExtractMetadata == null || Long.parseLong(strExtractMetadata) <= 0) {
                            throw new IOException("音频无法读取");
                        }
                        mediaMetadataRetriever.release();
                        FileInputStream fileInputStream = new FileInputStream(fileCreateTempFile);
                        try {
                            b3Var2.f.a(fileInputStream, str4, i2);
                            fileInputStream.close();
                            Files.deleteIfExists(fileCreateTempFile.toPath());
                            return;
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        mediaMetadataRetriever.release();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    Files.deleteIfExists(fileCreateTempFile.toPath());
                    throw th4;
                }
                Files.deleteIfExists(fileCreateTempFile.toPath());
                throw th4;
            }
        }, new U2(2, b3Var));
    }

    public void b(boolean z) {
        I2 i2 = (I2) this.b;
        HashSet hashSet = i2.t;
        J2 j2 = (J2) this.c;
        hashSet.remove(Long.valueOf(j2.d));
        if (!z) {
            i2.i.remove(Long.valueOf(j2.d));
        }
        if (i2.m) {
            if (this.a != i2.p) {
                return;
            }
            E2 e2 = i2.c;
            e2.removeCallbacks(i2.w);
            if (!z) {
                i2.d("点赞失败，已停止；本批成功 " + i2.o + " 条");
                return;
            }
            int i = i2.o + 1;
            i2.o = i;
            Log.i("SoulHook102", "loaded like response: success=" + i);
            e2.a(i2.j.size() - i2.n, true);
            e2.postDelayed(i2.v, 500L);
        }
    }
}
