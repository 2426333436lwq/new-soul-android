package dev.local.newsoul.feature.chat.forward;

import android.app.Dialog;
import android.content.DialogInterface;

/**
 * 转发确认框关闭监听（原模块 {@code c1} 的 case 0）。
 *
 * <p>确认框关闭后清空 {@link MessageForward} 中持有的引用，避免下一次转发误判
 * 「已有确认框在展示」。</p>
 */
final class ForwardDialogDismissListener implements DialogInterface.OnDismissListener {

    private final MessageForward forward;
    private final Dialog dialog;

    ForwardDialogDismissListener(MessageForward forward, Dialog dialog) {
        this.forward = forward;
        this.dialog = dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        forward.clearDialog(dialog);
    }
}
