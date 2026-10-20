package dev.local.newsoul.feature.chat.voice;

import java.io.IOException;

/**
 * 修改备注的后台任务（原 {@code r}）。
 *
 * <p>在语音库实例锁内重新读取记录并以新备注原地重写元数据，避免与其它写入竞争。</p>
 *
 * <p>原实现（{@code r.run}）位于 {@code synchronized} 块内且不捕获 {@link IOException}，
 * 异常直接向上传播由提交方统一提示；此处保持同样的「向上抛」语义，把受检异常
 * 包装为 {@link RuntimeException} 以便经 {@code Runnable} 边界传递。</p>
 */
final class VoiceMetadataTask implements RunnableAction {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;
    private final String remark;

    VoiceMetadataTask(VoiceLibraryDialog dialog, VoiceRecord record, String remark) {
        this.dialog = dialog;
        this.record = record;
        this.remark = remark;
    }

    @Override
    public void run() {
        try {
            synchronized (dialog.store) {
                String normalized = VoiceLibraryStore.requireRemark(remark);
                VoiceRecord fresh = dialog.store.read(record.id());
                dialog.store.writeMetadata(fresh.withRemark(normalized));
            }
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
