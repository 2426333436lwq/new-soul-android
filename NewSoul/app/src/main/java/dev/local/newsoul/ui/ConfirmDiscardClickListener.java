package dev.local.newsoul.ui;

import android.view.View;

/**
 * 草稿保护下「取消」按钮监听（原 {@code s0} 的 case 3 分支）。
 *
 * <p>点击取消时改走放弃草稿确认流程。</p>
 */
final class ConfirmDiscardClickListener implements View.OnClickListener {

    private final ModuleDialog dialog;

    ConfirmDiscardClickListener(ModuleDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onClick(View view) {
        dialog.confirmDiscard();
    }
}
