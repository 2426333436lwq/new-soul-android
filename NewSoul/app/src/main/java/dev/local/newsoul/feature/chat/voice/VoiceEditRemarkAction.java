package dev.local.newsoul.feature.chat.voice;

/**
 * 打开修改备注页（原 {@code Q2} 动作码 4）。
 *
 * <p>保存回调为 {@link VoiceMetadataSubmit}，取消回调回到详情页。</p>
 */
final class VoiceEditRemarkAction implements RunnableAction {

    /** 修改备注页标题（原字面量）。 */
    private static final String TITLE = "修改备注";

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceEditRemarkAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        dialog.showRemarkEditor(TITLE, record.remark(),
                new VoiceMetadataSubmit(dialog, record),
                new VoiceShowDetailsAction(dialog, record));
    }
}
