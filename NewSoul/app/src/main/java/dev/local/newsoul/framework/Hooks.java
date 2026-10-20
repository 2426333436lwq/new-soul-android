package dev.local.newsoul.framework;

import android.content.Context;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * Hook 安装门面。
 *
 * <p>统一约定：每个 hook 都带稳定 ID（{@code new-soul/...}，便于在 LSPosed 管理器中定位），
 * 并一律使用 {@link XposedInterface.ExceptionMode#PROTECTIVE} 异常模式 ——
 * 模块自身抛异常时不会影响宿主。</p>
 *
 * <p>所有安装方法在失败时只记录日志并返回失败标志，不向调用方抛出异常，
 * 以支持「宿主签名变化即优雅降级」的整体策略。</p>
 */
public final class Hooks {

    private Hooks() {
    }

    /**
     * 安装单个 hook，成功返回 {@code true}。
     *
     * <p><b>所有 hook 都经过 {@link #wrap} 加保护壳</b>：hooker 体内抛出的任何
     * {@link Throwable}（含 {@code Error}）都不会传到宿主调用栈。这一点是硬性要求 ——
     * 模块跑在宿主进程里，一次逃逸的异常就足以让 Soul 启动黑屏。即使某个 hooker
     * 自己写了 try/catch，这里仍然兜一层，防止遗漏。</p>
     */
    public static boolean intercept(XposedModule module, String id, Executable target,
                                    XposedInterface.Hooker hooker) {
        if (module == null || target == null || hooker == null) {
            return false;
        }
        try {
            module.hook(target)
                    .setId(id)
                    .setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE)
                    .intercept(wrap(id, hooker));
            return true;
        } catch (Throwable throwable) {
            Logger.w("hook failed: " + id + " -> " + target, throwable);
            return false;
        }
    }

    /**
     * 给 hooker 套一层异常保护壳。
     *
     * <p>保护壳只保证「不把异常甩给宿主」，不改变正常路径语义：
     * {@code chain.proceed()} 的返回值原样透传。</p>
     */
    public static XposedInterface.Hooker wrap(String id, XposedInterface.Hooker hooker) {
        return chain -> {
            try {
                return hooker.intercept(chain);
            } catch (Throwable throwable) {
                Guard.report("hook:" + id, throwable);
                // 回落到宿主原实现，保证行为尽可能接近未安装模块
                try {
                    return chain.proceed();
                } catch (Throwable inner) {
                    Guard.report("hook-proceed:" + id, inner);
                    return null;
                }
            }
        };
    }

    /**
     * 对目标类上所有同名方法（含继承链）安装同一 hook。
     *
     * @return 成功安装的数量
     */
    public static int interceptAll(XposedModule module, Class<?> clazz, String methodName,
                                   String idPrefix, XposedInterface.Hooker hooker) {
        if (module == null || clazz == null || methodName == null) {
            return 0;
        }
        int installed = 0;
        List<Method> methods = Reflect.findMethods(clazz, methodName);
        for (int i = 0; i < methods.size(); i++) {
            if (intercept(module, idPrefix + i, methods.get(i), hooker)) {
                installed++;
            }
        }
        return installed;
    }

    /** 安装静态初始化器（{@code <clinit>}）hook，成功返回 {@code true}。 */
    public static boolean interceptClassInitializer(XposedModule module, String id, Class<?> clazz,
                                                    XposedInterface.Hooker hooker) {
        if (module == null || clazz == null || hooker == null) {
            return false;
        }
        try {
            module.hookClassInitializer(clazz)
                    .setId(id)
                    .setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE)
                    .intercept(wrap(id, hooker));
            return true;
        } catch (Throwable throwable) {
            Logger.w("hook class initializer failed: " + id + " -> " + clazz.getName(), throwable);
            return false;
        }
    }

    /**
     * 对目标方法去优化，使其后续调用进入 hook。
     *
     * <p>部分宿主方法在安装 hook 前已被 AOT / JIT 内联，必须显式去优化才能确保拦截生效。
     * 失败时只记录日志（在部分运行时不支持该能力，此时 hook 仍可能通过其它方式生效）。</p>
     */
    public static boolean deoptimize(XposedModule module, String id, Executable target) {
        if (module == null || target == null) {
            return false;
        }
        try {
            module.deoptimize(target);
            return true;
        } catch (Throwable throwable) {
            Logger.w("deoptimize failed: " + id + " -> " + target, throwable);
            return false;
        }
    }

    /**
     * 安装「布尔标志位覆盖」hook。
     *
     * <p>用于下载类接口：宿主方法以布尔参数表达「是否加处理（水印）」，模块在开关开启时
     * 把该实参改写为 {@code false} 以跳过处理。目标方法必须是 <b>静态且返回 void</b>，
     * 与原模块的签名约束一致 —— 不符合时抛出 {@link IllegalStateException} 并被本方法捕获记录。</p>
     *
     * <p>上下文解析：{@code contextArgIndex >= 0} 时从该实参取 {@link Context}，
     * 否则调用 {@code contextProvider}（静态无参方法）获取全局 Context。
     * 两者都拿不到 Context 时直接放行，不做任何改写。</p>
     *
     * @param settingKey        开关键名，同时用于日志
     * @param flagArgIndex      需要改写为 {@code false} 的实参下标
     * @param contextArgIndex   承载 Context 的实参下标；负值表示改用 {@code contextProvider}
     * @param contextProvider   静态 Context 提供者；{@code null} 时无法解析上下文
     * @param unavailableMessage 安装失败时的日志文案
     */
    public static boolean installFlagOverride(XposedModule module, String id, Method target,
                                              String settingKey, int flagArgIndex, int contextArgIndex,
                                              Method contextProvider, String unavailableMessage) {
        if (module == null) {
            return false;
        }
        if (target == null) {
            Logger.e(unavailableMessage);
            return false;
        }
        try {
            if (!Modifier.isStatic(target.getModifiers()) || target.getReturnType() != void.class) {
                throw new IllegalStateException("Unexpected download signature: " + target);
            }
            module.hook(target)
                    .setId(id)
                    .setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE)
                    .intercept(wrap(id, chain -> {
                        Context context = resolveContext(chain, contextArgIndex, contextProvider);
                        if (context == null || !Prefs.getBoolean(context, settingKey, true)) {
                            return chain.proceed();
                        }
                        Object[] args = chain.getArgs().toArray();
                        Object original = flagArgIndex >= 0 && flagArgIndex < args.length
                                ? args[flagArgIndex] : null;
                        if (flagArgIndex >= 0 && flagArgIndex < args.length) {
                            args[flagArgIndex] = Boolean.FALSE;
                        }
                        Logger.i("watermark bypass: " + settingKey + "; original=" + original);
                        return chain.proceed(args);
                    }));
            Logger.i("watermark control installed: " + settingKey);
            return true;
        } catch (Throwable throwable) {
            Logger.e(unavailableMessage, throwable);
            return false;
        }
    }

    /** 解析 hook 时机可用的宿主 {@link Context}；不可用时返回 {@code null}。 */
    private static Context resolveContext(XposedInterface.Chain chain, int contextArgIndex,
                                          Method contextProvider) {
        try {
            Object value;
            if (contextArgIndex >= 0) {
                value = chain.getArg(contextArgIndex);
            } else if (contextProvider != null) {
                value = contextProvider.invoke(null, (Object[]) null);
            } else {
                return null;
            }
            return value instanceof Context ? (Context) value : null;
        } catch (Throwable throwable) {
            Logger.w("download preference unavailable", throwable);
            return null;
        }
    }
}