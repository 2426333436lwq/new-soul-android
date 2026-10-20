package dev.local.newsoul.feature.chat.voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * 语音发送任务（原 {@code g} 末位分支，索引 16）。
 *
 * <p>在后台线程把语音库中的音频复制到应用缓存目录，再投递到主线程调用宿主原生发送方法。
 * 与原实现一致：读取/建目录等外层失败静默忽略；复制失败删除临时文件并投递失败任务。</p>
 */
final class VoiceSendTask implements Runnable {

    /** 出站音频缓存目录（原字面量）。 */
    private static final String OUTGOING_DIRECTORY = "newsoul-voice-outgoing";

    /** 缓存目录创建失败时的异常信息（原字面量）。 */
    private static final String CANNOT_CREATE_MESSAGE = "Cannot create outgoing audio";

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceSendTask(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        try {
            VoiceRecord fresh = dialog.store.read(record.id());
            File source = fresh.audioFile();
            File directory = new File(dialog.activity.getCacheDir(), OUTGOING_DIRECTORY);
            if (!directory.isDirectory() && !directory.mkdirs()) {
                throw new IOException(CANNOT_CREATE_MESSAGE);
            }
            File target = new File(directory,
                    UUID.randomUUID() + VoiceLibraryStore.detectExtension(source));
            try {
                copyBounded(source, target);
                dialog.library.mainHandler().post(new VoiceSendUiTask(dialog, target, fresh));
            } catch (Throwable copyError) {
                deleteQuietly(target);
                dialog.library.mainHandler().post(new VoiceSendFailureTask(dialog, copyError));
            }
        } catch (Throwable readError) {
            // 与原实现一致：外层失败静默忽略（不提示、不清理）。
        }
    }

    /** 限量复制音频到目标文件。 */
    private static void copyBounded(File source, File target) throws IOException {
        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(target)) {
            VoiceLibraryStore.copyBounded(input, output);
        }
    }

    /** 静默删除文件。 */
    private static void deleteQuietly(File file) {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (Throwable ignored) {
            // 清理失败不影响提示流程。
        }
    }
}
