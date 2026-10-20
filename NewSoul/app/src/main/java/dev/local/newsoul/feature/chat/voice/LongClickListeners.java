package dev.local.newsoul.feature.chat.voice;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 长按监听存在性探测（原混淆类 {@code k.1} / {@code d3.d} 中调用的
 * {@code View#hasOnLongClickListeners()}）。
 *
 * <p>{@code View#hasOnLongClickListeners()} 是隐藏 API，直接调用可能被平台的 hidden-api
 * 限制拦截；此处优先反射调用该隐藏方法，失败时回退到读取 {@code View#getListenerInfo()}
 * 的 {@code mOnLongClickClickListener} 字段，两者都不可用时保守地判定为「不存在监听」。</p>
 */
final class LongClickListeners {

    private LongClickListeners() {
    }

    /** 判断视图当前是否已挂接长按监听。 */
    static boolean present(View view) {
        if (view == null) {
            return false;
        }
        Boolean hidden = invokeHiddenCheck(view);
        if (hidden != null) {
            return hidden;
        }
        return readListenerInfo(view);
    }

    /** 反射调用隐藏方法 {@code hasOnLongClickListeners()}；不可用时返回 {@code null}。 */
    private static Boolean invokeHiddenCheck(View view) {
        try {
            Method method = View.class.getDeclaredMethod("hasOnLongClickListeners");
            method.setAccessible(true);
            Object result = method.invoke(view);
            return result instanceof Boolean ? (Boolean) result : null;
        } catch (Throwable ignored) {
            return null;
        }
    }

    /** 回退方案：读取 {@code getListenerInfo()} 中的长按监听字段。 */
    private static boolean readListenerInfo(View view) {
        try {
            Method getter = View.class.getDeclaredMethod("getListenerInfo");
            getter.setAccessible(true);
            Object listenerInfo = getter.invoke(view);
            if (listenerInfo == null) {
                return false;
            }
            Field field = listenerInfo.getClass().getDeclaredField("mOnLongClickListener");
            field.setAccessible(true);
            return field.get(listenerInfo) != null;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
