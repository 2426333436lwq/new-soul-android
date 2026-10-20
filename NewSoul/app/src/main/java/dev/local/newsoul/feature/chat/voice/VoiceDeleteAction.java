package dev.local.newsoul.feature.chat.voice;

/**
 * 删除动作（原 {@code Q2} 动作码 3）。
 *
 * <p>把「元数据改名为 {@code .deleted} 再删音频、失败则移回」的原子删除委托给
 * {@link VoiceLibraryStore#delete(String)}。</p>
 */
final class VoiceDeleteAction implements RunnableAction {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceDeleteAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        try {
            dialog.store.delete(record.id());
        } catch (Exception error) {
            throw new IllegalStateException(error);
        }
    }
}
