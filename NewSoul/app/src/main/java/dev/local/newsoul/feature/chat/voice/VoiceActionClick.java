package dev.local.newsoul.feature.chat.voice;

import android.view.View;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 动作项点击（原 {@code C0} 索引 5）。
 *
 * <p>执行动作；失败提示「操作未完成，请重新打开语音库」。</p>
 */
final class VoiceActionClick implements View.OnClickListener {

    /** 失败提示（原字面量）。 */
    private static final String FAILURE_MESSAGE = "操作未完成，请重新打开语音库";

    private final RunnableAction action;
    private final ModuleDialog dialog;

    VoiceActionClick(RunnableAction action, ModuleDialog dialog) {
        this.action = action;
        this.dialog = dialog;
    }

    @Override
    public void onClick(View view) {
        try {
            action.run();
        } catch (Throwable error) {
            VoiceLibrary.failure(dialog.activity(), FAILURE_MESSAGE, error);
        }
    }
}
