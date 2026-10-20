package dev.local.newsoul.host;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.local.newsoul.framework.HostInfo;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 宿主适配器注册表。
 *
 * <p>注册顺序即优先级：越靠前的适配器越先被匹配。通用适配器
 * ({@link GenericHostAdapter}) 必须始终位于末尾作为兜底，从而保证新版本 Soul
 * 在没有专属适配器时依旧可用（走运行时签名探测）。</p>
 */
public final class HostAdapters {

    private static final List<HostAdapter> ADAPTERS = new ArrayList<>();

    static {
        // 未来的版本专属适配器在此注册（放在通用适配器之前）。
        register(new GenericHostAdapter());
    }

    private HostAdapters() {
    }

    public static void register(HostAdapter adapter) {
        if (adapter != null) {
            ADAPTERS.add(adapter);
        }
    }

    public static List<HostAdapter> all() {
        return Collections.unmodifiableList(ADAPTERS);
    }

    /** 选择首个匹配的适配器；无匹配时返回 {@code null}。 */
    public static HostAdapter select(HostInfo host) {
        for (HostAdapter adapter : ADAPTERS) {
            try {
                if (adapter.matches(host)) {
                    return adapter;
                }
            } catch (Throwable throwable) {
                Logger.w("adapter match failed: " + adapter.name(), throwable);
            }
        }
        return null;
    }

    /** 选择适配器并执行安装。 */
    public static void install(ModuleContext context) {
        HostInfo host = context.host();
        HostAdapter adapter = select(host);
        if (adapter == null) {
            Logger.w("no host adapter for " + host.describe() + "; module inactive");
            return;
        }
        Logger.i("host adapter=" + adapter.name() + "; host=" + host.describe());
        adapter.install(context);
    }
}