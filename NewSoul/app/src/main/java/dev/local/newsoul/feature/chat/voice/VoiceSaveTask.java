package dev.local.newsoul.feature.chat.voice;

import android.content.Context;
import android.media.MediaMetadataRetriever;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 保存语音的后台任务（原 {@code W2}）。
 *
 * <p>先把音频下载到缓存临时文件，再用 {@link MediaMetadataRetriever} 校验时长，最后落库；
 * 任何路径都清理临时文件。时长不可读时抛「音频无法读取」。</p>
 *
 * <p>本类在后台线程执行，检查异常统一包装为 {@link RuntimeException}，
 * 由 {@link VoiceBackgroundTask} 的 {@code catch (Throwable)} 捕获处理。</p>
 */
final class VoiceSaveTask implements RunnableAction {

    /** 临时文件前缀（原字面量）。 */
    private static final String TEMP_PREFIX = "newsoul-voice-";

    /** 临时文件后缀（原字面量）。 */
    private static final String TEMP_SUFFIX = ".audio";

    /** 时长不可读时的异常信息（原字面量）。 */
    private static final String UNREADABLE_MESSAGE = "音频无法读取";

    /** {@link MediaMetadataRetriever#extractMetadata(int)} 的时长键（原 {@code 9}）。 */
    private static final int METADATA_KEY_DURATION = 9;

    private final VoiceLibraryDialog dialog;
    private final Context appContext;
    private final String url;
    private final String remark;
    private final int duration;

    VoiceSaveTask(VoiceLibraryDialog dialog, Context appContext, String url, String remark,
            int duration) {
        this.dialog = dialog;
        this.appContext = appContext;
        this.url = url;
        this.remark = remark;
        this.duration = duration;
    }

    @Override
    public void run() {
        File temp;
        try {
            temp = File.createTempFile(TEMP_PREFIX, TEMP_SUFFIX, appContext.getCacheDir());
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
        try {
            VoiceLibrary.download(temp, url);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                retriever.setDataSource(temp.getAbsolutePath());
                String metadata = retriever.extractMetadata(METADATA_KEY_DURATION);
                if (metadata == null || Long.parseLong(metadata) <= 0) {
                    throw new IOException(UNREADABLE_MESSAGE);
                }
                retriever.release();
                try (FileInputStream input = new FileInputStream(temp)) {
                    dialog.store.store(input, remark, duration);
                }
                Files.deleteIfExists(temp.toPath());
            } catch (Throwable error) {
                retriever.release();
                throw error;
            }
        } catch (Throwable error) {
            deleteQuietly(temp);
            if (error instanceof RuntimeException) {
                throw (RuntimeException) error;
            }
            throw new RuntimeException(error);
        }
    }

    /** 静默删除临时文件。 */
    private static void deleteQuietly(File file) {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (Throwable ignored) {
            // 清理失败不影响主流程。
        }
    }
}
