package dev.local.newsoul.feature.chat.voice;

import android.content.DialogInterface;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 对话框取消监听（原 {@code X2}）。
 *
 * <p>对话框已切换或已关闭时不处理；忙碌时关闭并提示；否则校验会话状态后执行取消动作，
 * 失败直接关闭对话框。</p>
 */
final class VoiceCancelListener implements DialogInterface.OnCancelListener {

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;
    private final RunnableAction action;

    VoiceCancelListener(VoiceLibraryDialog dialog, ModuleDialog target, RunnableAction action) {
        this.dialog = dialog;
        this.target = target;
        this.action = action;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (dialog.closed || dialog.dialog != target) {
            return;
        }
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
