package dev.local.newsoul.feature.chat.voice;

/**
 * 发送到当前聊天（原 {@code Q2} 动作码 7）。
 *
 * <p>忙碌时忽略；否则停止试听、置忙碌标志并把 {@link VoiceSendTask} 提交到后台线程。</p>
 */
final class VoiceSendAction implements RunnableAction {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceSendAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        if (dialog.busy) {
            return;
        }
        dialog.requireValid();
        dialog.stopPlayback();
        dialog.busy = true;
        dialog.library.worker().execute(new VoiceSendTask(dialog, record));
    }
}
