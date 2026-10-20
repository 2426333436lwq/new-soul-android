package dev.local.newsoul.feature.chat.voice;

import android.view.View;

/**
 * 对话框动作按钮点击（原 {@code C0} 默认分支）。
 *
 * <p>忙碌时关闭并提示；否则校验会话状态后执行动作，失败直接关闭对话框。</p>
 */
final class VoiceDialogActionClick implements View.OnClickListener {

    private final VoiceLibraryDialog dialog;
    private final RunnableAction action;

    VoiceDialogActionClick(VoiceLibraryDialog dialog, RunnableAction action) {
        this.dialog = dialog;
        this.action = action;
    }

    @Override
    public void onClick(View view) {
        if (dialog.busy) {
            dialog.closeWhileBusy();
            return;
        }
        try {
            dialog.requireValid();
            action.run();
        } catch (Throwable ignored) {
            dialog.close();
        }
    }
}
