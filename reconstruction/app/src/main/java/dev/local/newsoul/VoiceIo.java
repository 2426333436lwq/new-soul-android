package dev.local.newsoul;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 语音库 / 文件落盘的 IO 线程池。对应 d3（FileDownloader）+ x1 的 IO 部分。 */
final class VoiceIo {

    private VoiceIo() {}

    static final ExecutorService EXEC = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "newsoul-voice-io");
        t.setPriority(Thread.MIN_PRIORITY);
        return t;
    });

    static void save(Object content) {
        try {
            Context ctx = ContextHolder.get();
            if (ctx == null || content == null) return;

            String src = String.valueOf(content);
            if (src.isEmpty()) return;

            String name = VoiceLibrary.fileNameFor(src) + extOf(src);
            File out = new File(VoiceLibrary.dir(ctx), name);
            if (out.exists()) return;   // 已保存过

            File in = new File(src);
            if (!in.exists()) return;

            copy(in, out);
        } catch (Throwable ignore) {
        }
    }

    private static String extOf(String path) {
        int i = path.lastIndexOf('.');
        return i >= 0 ? path.substring(i).toLowerCase(java.util.Locale.ROOT) : ".amr";
    }

    private static void copy(File in, File out) throws Exception {
        try (InputStream is = new java.io.FileInputStream(in);
             OutputStream os = new FileOutputStream(out)) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = is.read(buf)) > 0) {
                os.write(buf, 0, n);
            }
        }
    }
}
