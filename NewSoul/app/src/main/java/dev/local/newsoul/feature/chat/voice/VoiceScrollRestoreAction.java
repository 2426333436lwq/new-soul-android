package dev.local.newsoul.feature.chat.voice;

import android.widget.ScrollView;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 列表页滚动位置恢复（原 {@code Y2}）。
 *
 * <p>仅当对话框仍为当前对话框时恢复上次记录的滚动位置。</p>
 */
final class VoiceScrollRestoreAction implements Runnable {

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;
    private final ScrollView scrollView;

    VoiceScrollRestoreAction(VoiceLibraryDialog dialog, ModuleDialog target, ScrollView scrollView) {
        this.dialog = dialog;
        this.target = target;
        this.scrollView = scrollView;
    }

    @Override
    public void run() {
        if (dialog.closed || dialog.dialog != target) {
            return;
        }
        scrollView.scrollTo(0, dialog.scrollY);
    }
}
