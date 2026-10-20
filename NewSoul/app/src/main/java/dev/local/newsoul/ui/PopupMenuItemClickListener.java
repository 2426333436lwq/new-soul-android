package dev.local.newsoul.ui;

import android.view.View;
import android.widget.PopupWindow;

import java.util.function.IntConsumer;

/**
 * 弹出菜单项点击监听（原 {@code l2}）。
 *
 * <p>点击先关闭菜单；若锚点仍挂接可见且宿主 Activity 存活，则回调选项下标。</p>
 */
final class PopupMenuItemClickListener implements View.OnClickListener {

    private final ModuleDialog dialog;
    private final PopupWindow popup;
    private final View anchor;
    private final IntConsumer onPick;
    private final int index;

    PopupMenuItemClickListener(ModuleDialog dialog, PopupWindow popup, View anchor,
            IntConsumer onPick, int index) {
        this.dialog = dialog;
        this.popup = popup;
        this.anchor = anchor;
        this.onPick = onPick;
        this.index = index;
    }

    @Override
    public void onClick(View view) {
        popup.dismiss();
        if (!anchor.isAttachedToWindow() || !anchor.isShown()) {
            return;
        }
        if (dialog.activity().isFinishing() || dialog.activity().isDestroyed()) {
            return;
        }
        onPick.accept(index);
    }
}
