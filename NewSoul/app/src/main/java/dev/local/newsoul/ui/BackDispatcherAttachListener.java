package dev.local.newsoul.ui;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/**
 * 返回手势回调生命周期监听（原 {@code F1} 的索引 1 分支，API 33+）。
 *
 * <p>装饰视图脱离窗口时注销返回回调并移除自身监听。</p>
 */
final class BackDispatcherAttachListener implements View.OnAttachStateChangeListener {

    private final OnBackInvokedDispatcher dispatcher;
    private final OnBackInvokedCallback callback;

    BackDispatcherAttachListener(OnBackInvokedDispatcher dispatcher,
            OnBackInvokedCallback callback) {
        this.dispatcher = dispatcher;
        this.callback = callback;
    }

    @Override
    public void onViewAttachedToWindow(View view) {
        // 挂接时无需处理。
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        dispatcher.unregisterOnBackInvokedCallback(callback);
        view.removeOnAttachStateChangeListener(this);
    }
}
