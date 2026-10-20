package dev.local.newsoul.feature.chat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 聊天功能域的宿主对象访问原语（原模块 {@code F.b} / {@code F.g}）。
 *
 * <p>宿主对象（消息、会话、菜单项等）以 {@code Object} 形式在 hook 回调之间传递，
 * 无法在编译期绑定类型。这里提供两种最小访问方式：</p>
 * <ul>
 *   <li>{@link #call(Object, String)} —— 调用<b>公有无参</b>方法（含继承链），对应原 {@code F.b}；</li>
 *   <li>{@link #readField(Object, String)} —— 读取<b>本类声明</b>字段（含私有），对应原 {@code F.g}。</li>
 * </ul>
 *
 * <p>找不到成员或调用失败时抛出反射异常，由调用方决定降级策略 —— 与原实现一致
 * （不在此处吞掉异常，因为调用方需要区分「正常返回 null」与「签名变化」）。</p>
 */
public final class HostAccess {

    private HostAccess() {
    }

    /** 调用宿主对象的公有无参方法。 */
    public static Object call(Object target, String name) throws ReflectiveOperationException {
        Method method = target.getClass().getMethod(name, (Class<?>[]) null);
        return method.invoke(target, (Object[]) null);
    }

    /** 读取宿主对象的声明字段（沿本类，不查父类 —— 与原 {@code F.g} 一致）。 */
    public static Object readField(Object target, String name) throws ReflectiveOperationException {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }
}