package dev.local.newsoul.feature.chat.voice;

import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 后台任务结果处理（原 {@code m1} 索引 6）。
 *
 * <p>在主线程复位忙碌状态、移除提示行并恢复按钮；随后再次校验会话状态并执行成功动作。
 * 后台异常或校验失败统一以「操作未完成，请重试」提示。</p>
 */
final class VoiceBackgroundResultTask implements Runnable {

    /** 失败提示（原字面量）。 */
    private static final String FAILURE_MESSAGE = "操作未完成，请重试";

    /** 恢复后的负按钮文本（原字面量）。 */
    private static final String RESET_NEGATIVE_LABEL = "取消";

    /** 恢复后的正按钮文本（原字面量）。 */
    private static final String RESET_POSITIVE_LABEL = "重试";

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;
    private final TextView notice;
    private final Throwable error;
    private final RunnableAction onDone;

    VoiceBackgroundResultTask(VoiceLibraryDialog dialog, ModuleDialog target, TextView notice,
            Throwable error, RunnableAction onDone) {
        this.dialog = dialog;
        this.target = target;
        this.notice = notice;
        this.error = error;
        this.onDone = onDone;
    }

    @Override
    public void run() {
        dialog.busy = false;
        dialog.backgroundActive = false;
        if (dialog.closed) {
            return;
        }
        if (dialog.dialog != target) {
            return;
        }
        target.content().removeView(notice);
        Button negative = target.alert().getButton(DialogInterface.BUTTON_NEGATIVE);
        if (negative != null) {
            negative.setText(RESET_NEGATIVE_LABEL);
        }
        Button positive = target.alert().getButton(DialogInterface.BUTTON_POSITIVE);
        if (positive != null) {
            positive.setEnabled(true);
            positive.setText(RESET_POSITIVE_LABEL);
        }
        try {
            dialog.requireValid();
            if (error != null) {
                throw new RuntimeException(error);
            }
            onDone.run();
        } catch (Throwable caught) {
            VoiceLibrary.failure(dialog.activity, FAILURE_MESSAGE, error != null ? error : caught);
        }
    }
}
