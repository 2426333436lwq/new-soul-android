package dev.local.newsoul.feature.chat.voice;

import android.widget.TextView;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 后台任务执行器（原 {@code m1} 索引 5）。
 *
 * <p>在后台线程执行动作并捕获所有异常，随后把结果处理任务投递回主线程。</p>
 */
final class VoiceBackgroundTask implements Runnable {

    private final VoiceLibraryDialog dialog;
    private final RunnableAction work;
    private final ModuleDialog target;
    private final TextView notice;
    private final RunnableAction onDone;

    VoiceBackgroundTask(VoiceLibraryDialog dialog, RunnableAction work, ModuleDialog target,
            TextView notice, RunnableAction onDone) {
        this.dialog = dialog;
        this.work = work;
        this.target = target;
        this.notice = notice;
        this.onDone = onDone;
    }

    @Override
    public void run() {
        Throwable error = null;
        try {
            work.run();
        } catch (Throwable throwable) {
            error = throwable;
        }
        dialog.library.mainHandler().post(
                new VoiceBackgroundResultTask(dialog, target, notice, error, onDone));
    }
}
