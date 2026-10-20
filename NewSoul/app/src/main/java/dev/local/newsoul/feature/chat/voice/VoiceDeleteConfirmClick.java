package dev.local.newsoul.feature.chat.voice;

import android.view.View;

/**
 * 删除确认按钮点击（原 {@code Z2}）。
 *
 * <p>取消分支回到详情页；确认分支在非忙碌时提交删除后台任务，失败提示「删除失败」。</p>
 */
final class VoiceDeleteConfirmClick implements View.OnClickListener {

    /** 删除失败提示（原字面量）。 */
    private static final String DELETE_FAILURE_MESSAGE = "删除失败";

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;
    private final boolean confirm;

    VoiceDeleteConfirmClick(VoiceLibraryDialog dialog, VoiceRecord record, boolean confirm) {
        this.dialog = dialog;
        this.record = record;
        this.confirm = confirm;
    }

    @Override
    public void onClick(View view) {
        if (!confirm) {
            if (dialog.busy) {
                dialog.closeWhileBusy();
                return;
            }
            try {
                dialog.showDetails(record);
            } catch (Throwable ignored) {
                dialog.close();
            }
            return;
        }
        if (dialog.busy) {
            return;
        }
        try {
            dialog.requireValid();
            dialog.submitBackground(new VoiceDeleteAction(dialog, record),
                    new VoicePostSaveAction(VoicePostSaveAction.OPEN_LIBRARY, dialog));
        } catch (Throwable error) {
            VoiceLibrary.failure(dialog.activity, DELETE_FAILURE_MESSAGE, error);
        }
    }
}
