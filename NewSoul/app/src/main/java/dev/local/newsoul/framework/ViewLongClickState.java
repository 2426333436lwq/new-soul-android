package dev.local.newsoul.framework;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 长按监听存在性探测（原混淆类 {@code k} 的静态方法 {@code h}）。
 *
 * <p>与原实现一致：优先反射调用隐藏 API {@code View#hasOnLongClickListeners()}，
 * 不可用时回退读取 {@code View#getListenerInfo()} 的 {@code mOnLongClickListener} 字段，
 * 两者都不可用时保守判定为「不存在监听」。原 {@code k} 已由
 * {@code feature.chat.voice} 包内的同名探测逻辑覆盖，但该处为包级可见，
 * 资料页伪装域需要跨包使用，故在此提供公开等价实现。</p>
 */
public final class ViewLongClickState {

    private ViewLongClickState() {
    }

    /** 判断视图当前是否已挂接长按监听。 */
    public static boolean present(View view) {
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
