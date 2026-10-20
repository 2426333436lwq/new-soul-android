package dev.local.newsoul;

import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;

/**
 * 模块入口。
 *
 * 与原始 APK 的 dev.local.newsoul.SoulHook 对应。
 * 原始实现要点（见 re_work/smali_out/.../SoulHook.smali）：
 *   - extends XposedModule
 *   - 静态字段 TAG="SoulHook102" / TARGET="cn.soulapp.android"
 *   - settingsHookReady / installed / targetProcess 三个布尔守卫
 *   - toastShown = AtomicBoolean（防止重复弹 Toast）
 *   - onModuleLoaded 里比较 getProcessName() 与 TARGET，只处理宿主进程
 *   - onPackageReady 里先做重复加载去重（System.setProperty("newsoul.active.module", ...)），
 *     再依次调用各子系统安装器
 *   - installSettingsHook hook 宿主私有方法 H5Activity.m1(boolean) 作为设置入口触发器
 */
public class SoulHook extends XposedModule {

    private static final String TAG = "SoulHook102";
    private static final String TARGET = "cn.soulapp.android";

    /** 设置 hook 是否已安装（对应原 smali 的 static field settingsHookReady） */
    static boolean settingsHookReady = false;

    /** 本实例是否已完成安装 */
    private boolean installed = false;

    /** 当前进程是否为目标进程 */
    private boolean targetProcess = false;

    /** 启动 Toast 只弹一次 */
    private final java.util.concurrent.atomic.AtomicBoolean toastShown =
            new java.util.concurrent.atomic.AtomicBoolean(false);

    public SoulHook() {
        super();
    }

    // ------------------------------------------------------------------ 生命周期

    @Override
    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam param) {
        // 原实现：记录 framework/api 版本，并判断是否运行在宿主进程
        targetProcess = TARGET.equals(param.getProcessName());
        info("module loaded; framework=" + getFrameworkVersion()
                + "; version=" + getFrameworkVersion()
                + "; API=" + getApiVersion()
                + "; targetProcess=" + targetProcess);
    }

    @Override
    public void onPackageReady(XposedModuleInterface.PackageReadyParam param) {
        if (!targetProcess) {
            return;
        }

        // ---- 重复加载去重（原实现用 System 属性） ----
        String active = System.getProperty("newsoul.active.module");
        if (active != null && !active.isEmpty()) {
            info("Skipping duplicate module; active=" + active);
            return;
        }
        System.setProperty("newsoul.active.module", "dev.local.newsoul.public");
        info("active variant=dev.local.newsoul.public");

        ClassLoader cl = param.getClassLoader();

        // ---- 按子系统安装 hook ----
        // 每个安装器内部自行 try/catch 并降级，互不影响
        tryInstall(() -> HookRegistry.installAds(this, cl),        "ad controls");
        tryInstall(() -> HookRegistry.installChat(this, cl),       "chat games");
        tryInstall(() -> HookRegistry.installChatLimit(this, cl),  "chat limit");
        tryInstall(() -> HookRegistry.installSocialScore(this, cl), "social score");
        tryInstall(() -> HookRegistry.installPrivacy(this, cl),    "privacy photo");
        tryInstall(() -> HookRegistry.installWatermark(this, cl),  "watermark");
        tryInstall(() -> HookRegistry.installSquareLike(this, cl), "square like");
        tryInstall(() -> HookRegistry.installFeedFilter(this, cl), "feed filter");
        tryInstall(() -> HookRegistry.installPostShare(this, cl),  "post copy");
        tryInstall(() -> HookRegistry.installStartup(this, cl),    "startup");
        tryInstall(() -> HookRegistry.installProfileDisguise(this, cl), "profile disguise");
        tryInstall(() -> HookRegistry.installTypingAndMusic(this, cl),  "typing/music");
        tryInstall(() -> HookRegistry.installVoiceLibrary(this, cl),    "voice library");

        installSettingsHook(cl);

        installed = true;
        info("hook installation complete; ready=" + settingsHookReady);
    }

    // ------------------------------------------------------------------ 设置入口

    /**
     * hook 宿主私有方法 cn.soulapp.android.h5.activity.H5Activity.m1(boolean)。
     *
     * 原实现逻辑（见 SoulHook.installSettingsHook 的 smali）：
     *   1. ClassLoader.loadClass("cn.soulapp.android.h5.activity.H5Activity")
     *   2. getDeclaredMethod("m1", boolean.class)
     *   3. 断言 Modifier.isPrivate(m.getModifiers())，否则抛 IllegalStateException
     *      ("Unexpected H5Activity.m1 signature")
     *   4. hook(...).setId("soul-demo/private-h5-m1").setExceptionMode(...).intercept(...)
     *   5. 成功后 settingsHookReady = true 并打日志
     *      "private hook installed: H5Activity.m1(boolean)"
     */
    private void installSettingsHook(ClassLoader cl) {
        try {
            Class<?> h5 = cl.loadClass("cn.soulapp.android.h5.activity.H5Activity");
            var m1 = h5.getDeclaredMethod("m1", boolean.class);
            if (!java.lang.reflect.Modifier.isPrivate(m1.getModifiers())) {
                throw new IllegalStateException("Unexpected H5Activity.m1 signature");
            }
            m1.setAccessible(true);

            hook(m1)
                    .setId("soul-demo/private-h5-m1")
                    .setExceptionMode(io.github.libxposed.api.XposedInterface.ExceptionMode.PROTECTIVE)
                    .intercept(new SettingsEntryHooker(this));

            settingsHookReady = true;
            info("private hook installed: H5Activity.m1(boolean)");
        } catch (Throwable t) {
            log(6, TAG, "Soul settings hook unavailable; check app version", t);
        }
    }

    // ------------------------------------------------------------------ 工具

    private void info(String msg) {
        log(4, TAG, msg);
    }

    /** 单个安装器失败不影响其他安装器（与原 module.prop 的 exceptionMode=protective 对应） */
    private void tryInstall(Runnable installer, String label) {
        try {
            installer.run();
            info(label + " installed; ready=" + settingsHookReady);
        } catch (Throwable t) {
            log(6, TAG, label + " unavailable", t);
        }
    }
}
