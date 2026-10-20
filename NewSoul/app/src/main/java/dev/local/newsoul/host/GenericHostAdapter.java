package dev.local.newsoul.host;

import dev.local.newsoul.feature.Features;
import dev.local.newsoul.framework.HostInfo;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 通用宿主适配器。
 *
 * <p>不锁定任何具体宿主版本：所有 hook 目标都以「类名字符串 + 运行时签名探测」的方式定位，
 * 签名不匹配即优雅降级。它是注册表中的兜底项，因此 {@link #matches} 恒为 {@code true}。</p>
 */
public final class GenericHostAdapter implements HostAdapter {

    @Override
    public String name() {
        return "generic";
    }

    @Override
    public boolean matches(HostInfo host) {
        return true;
    }

    @Override
    public void install(ModuleContext context) {
        Features.installAll(context);
    }
}