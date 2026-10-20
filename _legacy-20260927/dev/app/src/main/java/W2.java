package dev.local.newsoul;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/* JADX INFO: loaded from: W2.class */
public final /* synthetic */ class W2 implements c3 {
    public final b3 a;
    public final Context b;
    public final String c;
    public final String d;
    public final int e;

    public /* synthetic */ W2(b3 b3Var, Context context, String str, String str2, int i) {
        this.a = b3Var;
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = i;
    }

    public final void run() throws IOException {
        String str = this.c;
        String str2 = this.d;
        int i = this.e;
        b3 b3Var = this.a;
        b3Var.getClass();
        File fileCreateTempFile = File.createTempFile("newsoul-voice-", ".audio", this.b.getCacheDir());
        try {
            d3.b(fileCreateTempFile, str);
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
                    b3Var.f.a(fileInputStream, str2, i);
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
    }
}
