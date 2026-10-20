package dev.local.newsoul.ui;

import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * 对话框按键监听（原 {@code n2}）。
 *
 * <p>拦截返回键：抬起且未被取消时按关闭语义处理，并消费该事件。</p>
 */
final class DialogKeyListener implements DialogInterface.OnKeyListener {

    private final ModuleDialog dialog;

    DialogKeyListener(ModuleDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public boolean onKey(DialogInterface target, int keyCode, KeyEvent event) {
        if (keyCode != ModuleDialog.KEYCODE_BACK && keyCode != ModuleDialog.KEYCODE_BACK_ALT) {
            return false;
        }
        if (event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
            dialog.close();
        }
        return true;
    }
}
