package dev.local.newsoul.ui;

import android.view.View;
import android.widget.PopupWindow;

/**
 * 弹出菜单锚点状态监听（原 {@code p2} 的索引 0 分支）。
 *
 * <p>锚点脱离窗口时关闭菜单，避免悬空显示。</p>
 */
final class PopupAnchorListener implements View.OnAttachStateChangeListener {

    private final PopupWindow popup;

    PopupAnchorListener(PopupWindow popup) {
        this.popup = popup;
    }

    @Override
    public void onViewAttachedToWindow(View view) {
        // 挂接时无需处理。
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        popup.dismiss();
    }
}
