package dev.local.newsoul.feature.chat.voice;

/**
 * 展示语音详情（原 {@code Q2} 动作码 1/2/6/default）。
 *
 * <p>列表条目点击与备注取消都回到详情页。</p>
 */
final class VoiceShowDetailsAction implements RunnableAction {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceShowDetailsAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        dialog.showDetails(record);
    }
}
