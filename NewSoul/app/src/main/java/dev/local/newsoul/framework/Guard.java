package dev.local.newsoul.framework;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 全局兜底守卫。
 *
 * <p>模块运行在宿主进程内，任何逃逸的异常都会直接杀死宿主进程 —— 用户看到的就是
 * 「Soul 打不开 / 黑屏」。本类提供一个最后的防线：</p>
 *
 * <ol>
 *   <li><b>安装器隔离</b>：{@link #guard(String, Runnable)} 包裹单个功能域的安装过程，
 *       把 {@link Throwable} 拦在功能域边界内（含 {@link Error}，如
 *       {@code NoSuchMethodError} / {@code ExceptionInInitializerError}）。</li>
 *   <li><b>Hook 回调隔离</b>：{@link #guardIntercept(String, XposedInterfaceSupplier)} 供
 *       hooker 调用，保证 hook 体内抛出的任何异常都不会传到宿主调用栈。</li>
 * </ol>
 *
 * <p><b>为什么必须捕获 Error</b>：宿主版本不匹配时最常见的是
 * {@code NoClassDefFoundError}（宿主类不存在）与 {@code ExceptionInInitializerError}
 * （宿主静态初始化失败），它们不是 {@code Exception}，只捕获 {@code Exception}
 * 会让它们穿到宿主线程上。</p>
 *
 * <p>连续失败计数用于诊断：同一功能域反复抛错时只记一次详细日志，避免刷爆 logcat
 * 拖慢宿主（日志本身也可能导致卡顿）。</p>
 */
public final class Guard {

    /** 单次会话内是否已经输出过「模块可能不兼容」的总告警。 */
    private static final AtomicBoolean ANNOUNCED = new AtomicBoolean(false);

    /** 同一 key 的失败次数上限，超过后不再写详细日志。 */
    private static final int MAX_DETAILED_LOGS_PER_KEY = 3;

    private static final java.util.Map<String, Integer> FAILURES =
            java.util.Collections.synchronizedMap(new java.util.HashMap<>());

    private Guard() {
    }

    /** 供 {@link #guardIntercept} 使用的可抛异常动作。 */
    public interface ThrowingAction {
        Object run() throws Throwable;
    }

    /**
     * 执行一段可能抛任意 {@link Throwable} 的动作，异常被吞掉并记录。
     *
     * @return 动作返回值；抛异常时返回 {@code null}
     */
    public static Object guard(String key, ThrowingAction action) {
        if (action == null) {
            return null;
        }
        try {
            return action.run();
        } catch (Throwable throwable) {
            report(key, throwable);
            return null;
        }
    }

    /**
     * 执行一段无返回值的动作，异常被吞掉并记录。
     *
     * @return 是否成功执行完
     */
    public static boolean guard(String key, Runnable action) {
        if (action == null) {
            return false;
        }
        try {
            action.run();
            return true;
        } catch (Throwable throwable) {
            report(key, throwable);
            return false;
        }
    }

    /** 记录一次被拦下的异常。 */
    public static void report(String key, Throwable throwable) {
        try {
            int count = 1;
            synchronized (FAILURES) {
                Integer previous = FAILURES.get(key);
                count = previous == null ? 1 : previous + 1;
                FAILURES.put(key, count);
            }
            if (count <= MAX_DETAILED_LOGS_PER_KEY) {
                Logger.w("guarded failure [" + key + "] (" + count + ")", throwable);
            }
            // 首次出现不可恢复错误时给一条总体提示，便于定位「宿主打不开」的原因。
            if (isFatal(throwable) && ANNOUNCED.compareAndSet(false, true)) {
                Logger.e("module may be incompatible with this host build; "
                        + "further guarded failures will be logged quietly");
            }
        } catch (Throwable ignored) {
            // 兜底里再抛异常就彻底放弃记录
        }
    }

    /** 判断是否为「宿主不兼容」类错误（Error 而非 Exception）。 */
    private static boolean isFatal(Throwable throwable) {
        return throwable instanceof Error;
    }

    /** 诊断用：各 key 的失败次数快照。 */
    public static java.util.Map<String, Integer> failures() {
        synchronized (FAILURES) {
            return new java.util.HashMap<>(FAILURES);
        }
    }
}
