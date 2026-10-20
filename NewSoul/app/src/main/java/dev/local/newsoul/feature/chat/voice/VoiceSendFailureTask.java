package dev.local.newsoul.feature.chat.voice;

/**
 * 语音准备阶段失败任务（原 {@code g} 索引 15）。
 *
 * <p>复位忙碌标志；对话框已关闭时不再提示，否则提示「读取语音失败」。</p>
 */
final class VoiceSendFailureTask implements Runnable {

    /** 失败提示（原字面量）。 */
    private static final String FAILURE_MESSAGE = "读取语音失败";

    private final VoiceLibraryDialog dialog;
    private final Throwable error;

    VoiceSendFailureTask(VoiceLibraryDialog dialog, Throwable error) {
        this.dialog = dialog;
        this.error = error;
    }

    @Override
    public void run() {
        dialog.busy = false;
        if (dialog.closed) {
            return;
        }
        VoiceLibrary.failure(dialog.activity, FAILURE_MESSAGE, error);
    }
}
