package dev.local.newsoul.ui;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/**
 * 把视图的无障碍类型声明为按钮（原 {@code e2}）。
 *
 * <p>用于让不可点击外观的文本/图片控件在无障碍服务中表现为 {@link Button}。</p>
 */
final class ButtonAccessibilityDelegate extends View.AccessibilityDelegate {

    @Override
    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(host, event);
        event.setClassName(Button.class.getName());
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        info.setClassName(Button.class.getName());
    }
}
