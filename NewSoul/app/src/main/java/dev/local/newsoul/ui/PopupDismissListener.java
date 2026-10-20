package dev.local.newsoul.ui;

import android.view.View;
import android.widget.PopupWindow;

/**
 * 弹出菜单关闭监听（原 {@code m2}）。
 *
 * <p>菜单关闭后移除锚点上的状态监听，防止重复注册。</p>
 */
final class PopupDismissListener implements PopupWindow.OnDismissListener {

    private final View anchor;
    private final View.OnAttachStateChangeListener anchorListener;

    PopupDismissListener(View anchor, View.OnAttachStateChangeListener anchorListener) {
        this.anchor = anchor;
        this.anchorListener = anchorListener;
    }

    @Override
    public void onDismiss() {
        anchor.removeOnAttachStateChangeListener(anchorListener);
    }
}
