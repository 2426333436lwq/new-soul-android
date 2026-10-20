package dev.local.newsoul.host;

import dev.local.newsoul.framework.HostInfo;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 宿主版本适配器。
 *
 * <p>模块不把宿主版本写死：{@link HostAdapters} 按注册顺序挑选第一个 {@link #matches} 成立的适配器。
 * 需要针对某个 Soul 版本做特殊处理时，实现本接口并在 {@link HostAdapters} 中注册即可，
 * 无需改动通用实现。</p>
 */
public interface HostAdapter {

    /** 适配器名称（用于日志与诊断）。 */
    String name();

    /** 是否适用于该宿主。 */
    boolean matches(HostInfo host);

    /** 安装该适配方案下的全部 hook。 */
    void install(ModuleContext context);
}