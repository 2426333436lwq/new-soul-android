package dev.local.newsoul.framework;

import android.content.Context;

import java.lang.reflect.Method;

import dev.local.newsoul.config.ConfigStore;
import io.github.libxposed.api.XposedModule;

/**
 * 模块在宿主进程内的运行时上下文。
 *
 * <p>聚合了框架入口、宿主类加载器、宿主版本信息与配置存储，供各功能安装器使用。
 * 上下文在每个宿主进程中只创建一次；版本信息、Context 与 ConfigStore 均惰性解析 ——
 * {@code onPackageReady} 触发时部分宿主资源可能尚未完全就绪。</p>
 */
public final class ModuleContext {

    private static final String ACTIVITY_THREAD_CLASS = "android.app.ActivityThread";

    private final XposedModule module;
    private final ClassLoader hostClassLoader;
    private final String hostPackageName;

    private volatile Context hostContext;
    private volatile ConfigStore configStore;
    private volatile HostInfo hostInfo;

    public ModuleContext(XposedModule module, ClassLoader hostClassLoader, String hostPackageName) {
        this.module = module;
        this.hostClassLoader = hostClassLoader;
        this.hostPackageName = hostPackageName == null ? "" : hostPackageName;
    }

    public XposedModule module() {
        return module;
    }

    /** 宿主类加载器（用于反射加载 {@code cn.soulapp.android.*}）。 */
    public ClassLoader classLoader() {
        return hostClassLoader;
    }

    public String hostPackageName() {
        return hostPackageName;
    }

    /** 宿主版本信息；无法获取版本号时退化为「仅包名」。 */
    public HostInfo host() {
        HostInfo cached = hostInfo;
        if (cached != null) {
            return cached;
        }
        synchronized (this) {
            if (hostInfo == null) {
                Context context = hostContext();
                hostInfo = context == null
                        ? new HostInfo(hostPackageName, "", 0L)
                        : HostInfo.from(context.getPackageManager(), hostPackageName);
            }
            return hostInfo;
        }
    }

    /** 宿主 Application 上下文；不可用时返回 {@code null}。 */
    public Context hostContext() {
        Context cached = hostContext;
        if (cached != null) {
            return cached;
        }
        synchronized (this) {
            if (hostContext == null) {
                hostContext = resolveHostContext();
            }
            return hostContext;
        }
    }

    /** 设置存储；上下文可用时返回实例，否则各读取方法回退默认值。 */
    public ConfigStore config() {
        ConfigStore cached = configStore;
        if (cached != null) {
            return cached;
        }
        synchronized (this) {
            if (configStore == null) {
                configStore = new ConfigStore(hostContext());
            }
            return configStore;
        }
    }

    /** 按名称加载宿主类，失败返回 {@code null}。 */
    public Class<?> loadHostClass(String className) {
        return Reflect.loadOrNull(hostClassLoader, className);
    }

    public boolean getBoolean(String key) {
        return config().getBoolean(key);
    }

    public int getInt(String key) {
        return config().getInt(key);
    }

    public String getString(String key) {
        return config().getString(key);
    }

    private static Context resolveHostContext() {
        try {
            Class<?> activityThread = Class.forName(ACTIVITY_THREAD_CLASS);
            Method currentApplication = activityThread.getDeclaredMethod("currentApplication");
            currentApplication.setAccessible(true);
            return (Context) currentApplication.invoke(null);
        } catch (Throwable ignored) {
            return null;
        }
    }
}