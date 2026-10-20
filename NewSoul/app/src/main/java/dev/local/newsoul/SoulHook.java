package dev.local.newsoul;

import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.host.HostAdapters;
import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;

/**
 * New Soul 模块入口。
 *
 * <p>该类名必须保持为 {@code dev.local.newsoul.SoulHook}，因为 LSPosed 通过
 * {@code META-INF/xposed/java_init.list} 按固定类名加载模块入口。</p>
 */
public final class SoulHook extends XposedModule {

    /** 宿主包名，与 {@code META-INF/xposed/scope.list} 保持一致。 */
    public static final String HOST_PACKAGE = "cn.soulapp.android";

    /** 多模块共存时的去重系统属性，与原模块保持一致。 */
    static final String ACTIVE_MODULE_PROPERTY = "newsoul.active.module";

    /** 本变体的模块包名，用于去重判定。 */
    static final String ACTIVE_MODULE_VALUE = "dev.local.newsoul.public";

    /** 是否为宿主进程（由 {@code onModuleLoaded} 判定）。 */
    private volatile boolean targetProcess;

    /** hook 是否已安装，防止重复安装。 */
    private volatile boolean installed;

    @Override
    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam param) {
        Logger.attach(this);
        targetProcess = !param.isSystemServer() && HOST_PACKAGE.equals(param.getProcessName());
        if (targetProcess) {
            Logger.i("module loaded; framework=" + getFrameworkName()
                    + "; version=" + getFrameworkVersion()
                    + "; API=" + getApiVersion());
        }
    }

    @Override
    public void onPackageReady(XposedModuleInterface.PackageReadyParam param) {
        if (!targetProcess || installed) {
            return;
        }
        if (!param.isFirstPackage() || !HOST_PACKAGE.equals(param.getPackageName())) {
            return;
        }

        // 多模块变体共存去重：先到者写入系统属性，后来者直接退出。
        synchronized (System.getProperties()) {
            String active = System.getProperty(ACTIVE_MODULE_PROPERTY);
            if (active != null) {
                Logger.i("Skipping duplicate module; active=" + active);
                return;
            }
            System.setProperty(ACTIVE_MODULE_PROPERTY, ACTIVE_MODULE_VALUE);
        }
        Logger.i("active variant=" + ACTIVE_MODULE_VALUE);

        try {
            ClassLoader hostClassLoader = param.getClassLoader();
            ModuleContext context = new ModuleContext(this, hostClassLoader, param.getPackageName());

            // 安全模式：宿主启动异常时无法通过 UI 关模块，因此提供一条"不进任何 hook"的通道。
            // 判定来源（任一命中即进入安全模式）：
            //   1. 系统属性 newsoul.safe.mode=true（adb setprop 或重启后仍生效）
            //   2. 设置里 newsoul_safe_mode=true（可用配置文件导入写入）
            if (isSafeMode(context)) {
                Logger.e("SAFE MODE active; no hooks installed");
                return;
            }

            HostAdapters.install(context);
            installed = true;
            Logger.i("hook installation complete");
        } catch (Throwable throwable) {
            Logger.e("hook installation failed", throwable);
        }
    }

    /**
     * 判断是否处于安全模式。
     *
     * <p>双重来源：系统属性（进程外可设，适合宿主完全无法启动时）与设置存储
     * （进程内可写）。任一为 {@code true} 即不安装任何 hook —— 此时模块行为等同于
     * 「装了但不生效」，可用于确认"黑屏是否由模块引起"。</p>
     */
    private static boolean isSafeMode(ModuleContext context) {
        try {
            if ("true".equalsIgnoreCase(System.getProperty("newsoul.safe.mode"))) {
                return true;
            }
        } catch (Throwable ignored) {
            // 属性读取失败按未开启处理
        }
        try {
            android.content.Context host = context.hostContext();
            if (host != null) {
                android.content.SharedPreferences prefs = host.getSharedPreferences(
                        dev.local.newsoul.config.SettingsKeys.PREFS_MAIN,
                        android.content.Context.MODE_PRIVATE);
                return prefs.getBoolean("newsoul_safe_mode", false);
            }
        } catch (Throwable ignored) {
            // 存储不可用按未开启处理
        }
        return false;
    }
}