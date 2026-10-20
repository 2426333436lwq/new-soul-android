package dev.local.newsoul;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地语音库。
 * 对应原始 DEX 的 x1（VoiceLibraryStore）+ h3（VoiceFileEntry）。
 *
 * 存储位置：<宿主 files>/newsoul-voice-library/
 * 文件名指纹：SHA-256 前 16 位十六进制（原实现用 %02x 格式化）
 *
 * 入口（来自 UI 文案）：
 *   保存语音：长按聊天中的语音消息，选择「存入语音库」
 *   打开语音库：进入普通私聊，点输入框旁语音库按钮
 */
public final class VoiceLibrary {

    private VoiceLibrary() {}

    public static final String DIR_NAME = "newsoul-voice-library";

    private static final String[] EXTENSIONS = {".mp3", ".wav", ".m4a", ".amr", ".aac"};

    /** 语音库目录。 */
    public static File dir(Context ctx) {
        File d = new File(ctx.getFilesDir(), DIR_NAME);
        if (!d.exists()) d.mkdirs();
        return d;
    }

    /** 入队一条语音消息（在 hook 线程调用，内部切 IO）。 */
    public static void enqueue(Object chatMessage) {
        try {
            Object content = readContent(chatMessage);
            if (content == null) return;
            // 原实现通过 ExecutorService 落盘，避免阻塞 hook 线程
            VoiceIo.EXEC.execute(() -> save(content));
        } catch (Throwable ignore) {
        }
    }

    /** 列出库内文件。 */
    public static List<File> list(Context ctx) {
        List<File> out = new ArrayList<>();
        File[] fs = dir(ctx).listFiles(f -> {
            for (String e : EXTENSIONS) {
                if (f.getName().toLowerCase(java.util.Locale.ROOT).endsWith(e)) return true;
            }
            return false;
        });
        if (fs != null) {
            java.util.Arrays.sort(fs, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
            for (File f : fs) out.add(f);
        }
        return out;
    }

    /** 删除。 */
    public static boolean delete(File f) {
        return f != null && f.exists() && f.delete();
    }

    /** 用 SHA-256 生成稳定文件名（避免重复保存同一条）。 */
    public static String fileNameFor(String key) {
        return Reflect.sha256Hex(key).substring(0, 16);
    }

    // ------------------------------------------------------------------

    private static Object readContent(Object chatMessage) {
        try {
            Class<?> c = chatMessage.getClass();
            for (String n : new String[]{"getMsgContent", "getContent", "getLocalPath"}) {
                try {
                    var m = c.getMethod(n);
                    return m.invoke(chatMessage);
                } catch (NoSuchMethodException ignore) {
                }
            }
        } catch (Throwable ignore) {
        }
        return null;
    }

    private static void save(Object content) {
        // 具体落盘由 VoiceIo 处理：先复制到库目录，再写入 .bin 元数据
        VoiceIo.save(content);
    }
}
