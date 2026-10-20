package dev.local.newsoul.feature.chat.voice;

import android.content.DialogInterface;
import android.widget.LinearLayout;

import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.ViewKit;

/**
 * 删除确认页（原 {@code Q2} 动作码 5）。
 *
 * <p>展示「删除「备注」？」确认框；取消回到详情，确认走 {@link VoiceDeleteConfirmClick}。</p>
 */
final class VoiceDeletePromptAction implements RunnableAction {

    /** 删除确认标题（原字面量）。 */
    private static final String TITLE = "删除语音";

    /** 删除确认按钮文本（原字面量）。 */
    private static final String POSITIVE = "删除";

    /** 删除确认正文字号（px，原 {@code 16}）。 */
    private static final int TEXT_SIZE = 16;

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoiceDeletePromptAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        ModuleDialog target = dialog.freshDialog();
        target.content().addView(ViewKit.text(target.context(),
                "删除「" + record.remark() + "」？", TEXT_SIZE, target.titleColor()));
        target.builder().setNegativeButton("取消", new VoiceDialogNoOpClick());
        target.show(TITLE, POSITIVE);
        dialog.bindDialogListeners(new VoiceShowDetailsAction(dialog, record));
        target.alert().getButton(DialogInterface.BUTTON_NEGATIVE)
                .setOnClickListener(new VoiceDeleteConfirmClick(dialog, record, false));
        target.alert().getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(new VoiceDeleteConfirmClick(dialog, record, true));
    }
}
