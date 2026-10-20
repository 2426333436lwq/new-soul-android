package dev.local.newsoul.feature.chat.forward;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 选择会话页条目点击代理（原模块 {@code V0} 的 case 1）。
 *
 * <p>模块接管列表点击后，宿主自带的条目点击监听被替换为本代理：把位置交给
 * {@link MessageForward#handleItemClick} 切换选中态，异常时提示「转发未完成」。</p>
 */
final class ForwardRecipientClickHandler implements InvocationHandler {

    private static final String TO_STRING = "NewSoulForwardRecipients";

    private static final String METHOD_ITEM_CLICK = "onItemClick";

    private final MessageForward forward;
    private final Activity activity;

    ForwardRecipientClickHandler(MessageForward forward, Activity activity) {
        this.forward = forward;
        this.activity = activity;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.getDeclaringClass() == Object.class) {
            String name = method.getName();
            if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            }
            if ("equals".equals(name)) {
                return proxy == args[0];
            }
            return TO_STRING;
        }
        if (METHOD_ITEM_CLICK.equals(method.getName())) {
            try {
                forward.handleItemClick(activity, ((Integer) args[0]).intValue());
            } catch (Throwable throwable) {
                MessageForward.reportFailure(activity, throwable);
            }
        }
        return null;
    }
}
