package dev.local.newsoul.feature.chat.voice;

import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ModuleDialog;

/**
 * 语音库读取任务（原 {@code V2} 索引 0）。
 *
 * <p>在后台线程读取记录列表，随后把列表渲染任务投递到主线程；读取失败记录
 * {@code voice library read failed} 并投递失败任务。</p>
 */
final class VoiceReadTask implements Runnable {

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;

    VoiceReadTask(VoiceLibraryDialog dialog, ModuleDialog target) {
        this.dialog = dialog;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            dialog.library.mainHandler().post(
                    new VoiceListScreenTask(dialog, target, dialog.store.list()));
        } catch (Throwable error) {
            Logger.w("voice library read failed", error);
            dialog.library.mainHandler().post(new VoiceReadFailureTask(dialog, target));
        }
    }
}
