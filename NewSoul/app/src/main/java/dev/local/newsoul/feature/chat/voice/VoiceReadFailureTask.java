package dev.local.newsoul.feature.chat.voice;

import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.ViewKit;

/**
 * 语音库读取失败任务（原 {@code V2} 索引 1）。
 *
 * <p>在原对话框上展示失败提示与「重试」项；若此时对话框已切换或状态失效，则直接关闭。</p>
 */
final class VoiceReadFailureTask implements Runnable {

    /** 失败提示（原字面量）。 */
    private static final String FAILURE_TEXT = "读取语音库失败，请重试";

    /** 失败提示字号（px，原 {@code 14}）。 */
    private static final int FAILURE_TEXT_SIZE = 14;

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;

    VoiceReadFailureTask(VoiceLibraryDialog dialog, ModuleDialog target) {
        this.dialog = dialog;
        this.target = target;
    }

    @Override
    public void run() {
        if (dialog.closed || dialog.dialog != target) {
            return;
        }
        try {
            dialog.requireValid();
            target.content().removeAllViews();
            target.content().addView(ViewKit.text(target.context(), FAILURE_TEXT,
                    FAILURE_TEXT_SIZE, target.titleColor()));
            target.content().addView(VoiceLibrary.actionItem(target, "重试",
                    new VoicePostSaveAction(VoicePostSaveAction.OPEN_LIBRARY, dialog)));
        } catch (Exception ignored) {
            dialog.close();
        }
    }
}
