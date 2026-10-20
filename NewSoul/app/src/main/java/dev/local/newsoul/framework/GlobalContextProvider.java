package dev.local.newsoul.framework;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * 宿主全局上下文提供者（原 {@code Q0.l} = {@code lf.b.b()}）。
 *
 * <p>多个聊天类 hook 的回调只能拿到宿主内部对象，无法直接取 {@link Context}；
 * 宿主提供的静态无参方法 {@code b()} 返回应用级上下文。解析一次后缓存 {@link Method}。</p>
 */
public final class GlobalContextProvider {

    private final Method provider;

    private GlobalContextProvider(Method provider) {
        this.provider = provider;
    }

    /** 从宿主类加载器解析提供者方法；不可用时 {@link #isReady()} 为 {@code false}。 */
    public static GlobalContextProvider resolve(ModuleContext context) {
        Class<?> clazz = context.loadHostClass(HostClasses.PRIVATE_CONTEXT_PROVIDER);
        return new GlobalContextProvider(Reflect.findDeclaredMethod(clazz, "b"));
    }

    public boolean isReady() {
        return provider != null;
    }

    /** 取得宿主全局上下文；不可用时返回 {@code null}。 */
    public Context get() {
        Object value = Reflect.invoke(null, provider);
        return value instanceof Context ? (Context) value : null;
    }
}