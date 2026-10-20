package dev.local.newsoul.feature.chat.voice;

/**
 * 「修改备注」提交回调（原 {@code Q2} 动作码 1 的 {@code P2} 角色）。
 *
 * <p>把备注重写提交为后台任务，成功后回到语音库列表页。</p>
 */
final class VoiceMetadataSubmit implements TextConsumer {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceMetadataSubmit(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void accept(String remark) {
        dialog.submitBackground(new VoiceMetadataTask(dialog, record, remark),
                new VoicePostSaveAction(VoicePostSaveAction.OPEN_LIBRARY, dialog));
    }
}
