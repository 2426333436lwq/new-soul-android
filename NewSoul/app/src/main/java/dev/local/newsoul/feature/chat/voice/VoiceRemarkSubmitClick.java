package dev.local.newsoul.feature.chat.voice;

import android.view.View;
import android.widget.EditText;

/**
 * 备注保存按钮点击（原 {@code G1} 默认分支）。
 *
 * <p>忙碌时忽略；校验会话状态后把裁剪过的备注交给回调；非法备注回填输入框错误。</p>
 */
final class VoiceRemarkSubmitClick implements View.OnClickListener {

    /** 非备注非法类错误的兜底提示（原字面量）。 */
    private static final String FALLBACK_ERROR = "保存失败，请重试";

    private final VoiceLibraryDialog dialog;
    private final TextConsumer consumer;
    private final EditText editor;

    VoiceRemarkSubmitClick(VoiceLibraryDialog dialog, TextConsumer consumer, EditText editor) {
        this.dialog = dialog;
        this.consumer = consumer;
        this.editor = editor;
    }

    @Override
    public void onClick(View view) {
        if (dialog.busy) {
            return;
        }
        try {
            dialog.requireValid();
            consumer.accept(VoiceLibraryStore.requireRemark(editor.getText().toString()));
        } catch (Throwable error) {
            editor.setError(error instanceof IllegalArgumentException
                    ? error.getMessage() : FALLBACK_ERROR);
        }
    }
}
