package dev.local.newsoul.ui;

import android.content.DialogInterface;

/**
 * 「放弃修改」按钮监听（原 {@code o2}）。
 *
 * <p>点击后关闭当前对话框（等价于放弃草稿）。</p>
 */
final class DiscardDismissListener implements DialogInterface.OnClickListener {

    private final ModuleDialog dialog;

    DiscardDismissListener(ModuleDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onClick(DialogInterface target, int which) {
        dialog.alert().dismiss();
    }
}
