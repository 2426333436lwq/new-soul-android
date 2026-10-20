package dev.local.newsoul.framework;

import android.content.Context;

/**
 * 模块级全局 {@link Context} 持有者。
 *
 * <p>部分 hook 点拿不到 {@link Context}：适配器实例、静态闸门方法、以及宿主在
 * {@code Application.onCreate} 之前触发的回调。本类提供统一取用通道。</p>
 *
 * <p><b>解析顺序</b>：</p>
 * <ol>
 *   <li>宿主私有提供者 {@code lf.b.b()}（原模块的做法，{@link GlobalContextProvider}）</li>
 *   <li>已缓存的应用上下文</li>
 * </ol>
 *
 * <p><b>为什么不注册 ActivityLifecycleCallbacks</b>：那需要在宿主
 * {@code Application} 上注册回调，会与宿主自身的生命周期管理产生耦合；
 * 一旦注册时机不当（早于 {@code Application.onCreate}）就可能拖垮启动。
 * 只读的宿主提供者已经足够，且失败是静默的。</p>
 */
public final class GlobalContextHolder {

    private static volatile Context application;
    private static volatile GlobalContextProvider provider;
    private static volatile boolean providerResolved;

    private GlobalContextHolder() {
    }

    /** 安装阶段登记宿主上下文提供者（解析一次，之后复用）。 */
    public static void attachProvider(GlobalContextProvider contextProvider) {
        provider = contextProvider;
        providerResolved = true;
    }

    /** 直接从已知 {@link Context} 登记（幂等，取应用级上下文避免泄漏 Activity）。 */
    public static void attach(Context context) {
        if (context == null) {
            return;
        }
        Context app = application;
        if (app != null) {
            return;
        }
        try {
            application = context.getApplicationContext();
        } catch (Throwable ignored) {
            application = context;
        }
    }

    /**
     * 取应用上下文；不可用时返回 {@code null}。
     *
     * <p>任何一步失败都返回 {@code null} 而不是抛异常 —— 调用方按「拿不到就不处理」降级。</p>
     */
    public static Context get() {
        Context cached = application;
        if (cached != null) {
            return cached;
        }
        try {
            if (!providerResolved) {
                providerResolved = true;
            }
            GlobalContextProvider current = provider;
            if (current != null && current.isReady()) {
                Context provided = current.get();
                if (provided != null) {
                    application = provided;
                    return provided;
                }
            }
        } catch (Throwable throwable) {
            Logger.w("global context unavailable", throwable);
        }
        return null;
    }

    /** 取应用上下文；不可用时抛出，供「必须要有 Context」的场景使用。 */
    public static Context require() {
        Context context = get();
        if (context == null) {
            throw new IllegalStateException("global context unavailable");
        }
        return context;
    }
}
