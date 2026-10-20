package dev.local.newsoul.ui;

import android.window.OnBackInvokedCallback;

/**
 * 预测式返回手势回调（原 {@code k2}，API 33+）。
 *
 * <p>系统返回手势触发时按关闭语义处理对话框。</p>
 */
final class BackInvokedCloseHandler implements OnBackInvokedCallback {

    private final ModuleDialog dialog;

    BackInvokedCloseHandler(ModuleDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onBackInvoked() {
        dialog.close();
    }
}
