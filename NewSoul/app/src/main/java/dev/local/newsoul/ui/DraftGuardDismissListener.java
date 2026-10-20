package dev.local.newsoul.ui;

import android.content.DialogInterface;
import android.view.View;

/**
 * 放弃草稿确认框关闭监听（原 {@code c1} 的索引 1 分支）。
 *
 * <p>关闭时清空对话框记录的确认框引用；若主对话框仍在显示，则把焦点交还给先前失焦的视图。</p>
 */
final class DraftGuardDismissListener implements DialogInterface.OnDismissListener {

    private final ModuleDialog dialog;
    private final View focused;

    DraftGuardDismissListener(ModuleDialog dialog, View focused) {
        this.dialog = dialog;
        this.focused = focused;
    }

    @Override
    public void onDismiss(DialogInterface target) {
        dialog.discardDialog = null;
        if (dialog.alert().isShowing() && focused != null) {
            focused.requestFocus();
        }
    }
}
