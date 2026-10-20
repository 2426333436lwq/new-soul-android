package dev.local.newsoul.feature;

import dev.local.newsoul.framework.ModuleContext;

/**
 * 单个功能域的安装器。
 *
 * <p>每个实现负责在宿主进程中安装属于自己功能域的全部 hook。约定：</p>
 * <ul>
 *   <li>实现内部自行判断开关，关闭时不得安装 hook（或安装后立即放行）；</li>
 *   <li>宿主类/方法签名不匹配时必须优雅降级（记录日志并返回），不得抛异常；</li>
 *   <li>{@link Features#installAll} 会捕获实现抛出的异常，保证单个功能域失败不影响其它。</li>
 * </ul>
 */
public interface FeatureInstaller {

    /** 功能域标识（仅用于日志与诊断）。 */
    String id();

    /** 安装该功能域的全部 hook。 */
    void install(ModuleContext context);
}