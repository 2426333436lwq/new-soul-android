package dev.local.newsoul.feature.chat.voice;

import android.view.View;

/**
 * 「返回语音库」按钮点击（原 {@code A0} 默认分支）。
 *
 * <p>忙碌时关闭并提示；否则重新打开语音库列表，失败直接关闭对话框。</p>
 */
final class VoiceLibraryOpenClick implements View.OnClickListener {

    private final VoiceLibraryDialog dialog;

    VoiceLibraryOpenClick(VoiceLibraryDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onClick(View view) {
        if (dialog.busy) {
            dialog.closeWhileBusy();
            return;
        }
        try {
            dialog.openLibrary();
        } catch (Throwable ignored) {
            dialog.close();
        }
    }
}
