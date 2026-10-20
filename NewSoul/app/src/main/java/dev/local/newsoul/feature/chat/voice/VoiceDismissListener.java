package dev.local.newsoul.feature.chat.voice;

import android.content.DialogInterface;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 对话框消散监听（原 {@code c1} 索引 2）。
 *
 * <p>仅当消散的是当前对话框时关闭会话。</p>
 */
final class VoiceDismissListener implements DialogInterface.OnDismissListener {

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;

    VoiceDismissListener(VoiceLibraryDialog dialog, ModuleDialog target) {
        this.dialog = dialog;
        this.target = target;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (dialog.dialog == target) {
            dialog.close();
        }
    }
}
