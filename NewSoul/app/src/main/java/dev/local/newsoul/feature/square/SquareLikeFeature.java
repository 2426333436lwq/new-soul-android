package dev.local.newsoul.feature.square;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.reflect.Method;

import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.HostViews;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;

/**
 * 广场点赞（原 {@code Q0.F} 的安装段 + {@code I2} 的执行逻辑）。
 *
 * <p><b>原始证据</b>：{@code Q0.F} 加载 {@code SquareFloatingButton}、
 * {@code MainActivity}、{@code square.post.bean.Post}，取 {@code getAdResponse}/
 * {@code hasDeclare}/{@code id}/{@code incentiveModel} 字段，并安装
 * {@code new-soul/square-like-lifecycle}、{@code new-soul/square-like-envelope-create}、
 * {@code new-soul/square-like-envelope-visibility}。</p>
 *
 * <p><b>本实现的两条触发路径</b>：</p>
 * <ol>
 *   <li><b>手动</b>：hook {@code MainActivity} 的 {@code onResume}，在广场页就绪后
 *       绑定长按手势到广场容器（长按 = 一键点赞当前范围）。原模块用
 *       {@code SquareFloatingButton}（悬浮按钮）触发；按钮在各版本签名不稳，
 *       长按手势依赖的资源名 {@code pager_square} 更稳定。</li>
 *   <li><b>自动</b>：{@code square_auto_like} 开启时，用 {@code ViewTreeObserver}
 *       监测布局变化，加载完成后自动执行一次点赞。</li>
 * </ol>
 *
 * <p><b>为什么用 Activity 生命周期而不是 RecyclerView 滚动回调</b>：滚动回调类名
 * （{@code square.video.adapter.a} 等）在原 DEX 中证据不足，且不同卡片类型各异；
 * {@code onResume} + 布局变化是跨版本可靠的切入点。</p>
 */
public final class SquareLikeFeature implements dev.local.newsoul.feature.FeatureInstaller {

    /** 自动点赞的防抖间隔：宿主一次加载会触发多次布局变化。 */
    private static final long AUTO_LIKE_DEBOUNCE_MS = 1200L;

    /** 两次自动点赞之间的最小间隔，避免连续滚动时刷屏式点赞。 */
    private static final long AUTO_LIKE_COOLDOWN_MS = 2500L;

    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    @Override
    public String id() {
        return "square-like";
    }

    @Override
    public void install(ModuleContext context) {
        Class<?> mainActivity = context.loadHostClass(HostClasses.STARTUP_MAIN_ACTIVITY);
        if (mainActivity == null) {
            Logger.e("square like unavailable: MainActivity not found");
            return;
        }
        // 直接反射 android.app.Activity.onResume，不用 Reflect.findMethods(mainActivity, ...)。
        // 原因：宿主 MainActivity 自己不声明 onResume（继承自 MartianActivity），
        // 而 Reflect 带框架类守卫（防止误 hook 框架基类），allMethods 走到
        // android.app.Activity 就停，永远找不到 onResume。
        // 原模块同样是 hook Activity.onResume 基类，由 LSPosed 分发到所有实例。
        Method onResume;
        try {
            onResume = android.app.Activity.class.getDeclaredMethod("onResume");
            onResume.setAccessible(true);
        } catch (Throwable throwable) {
            Logger.e("square like unavailable: Activity.onResume not found", throwable);
            return;
        }

        boolean installed = Hooks.intercept(context.module(), HookIds.SQUARE_LIKE_LIFECYCLE,
                onResume, new SquareLifecycleHooker());
        if (installed) {
            Logger.i("square like lifecycle installed");
        } else {
            Logger.e("square like unavailable");
        }
    }

    // ================================================================ Hooker

    /**
     * 在 {@code MainActivity.onResume} 后绑定广场手势与自动点赞。
     */
    private static final class SquareLifecycleHooker implements XposedInterface.Hooker {

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            Object result = chain.proceed();
            try {
                Object self = chain.getThisObject();
                if (self instanceof Activity activity) {
                    // 等宿主布局完成再绑定
                    MAIN.post(() -> attach(activity));
                }
            } catch (Throwable throwable) {
                Logger.w("square like attach failed", throwable);
            }
            return result;
        }
    }

    private static void attach(Activity activity) {
        try {
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            View square = HostViews.findByName(activity, SquareLikeEngine.RES_PAGER_SQUARE);
            if (square == null) {
                // 不在广场页，正常返回
                return;
            }

            // 注意：这里**不接管**广场容器的手势。
            // 原实现在容器上 setOnLongClickListener 会顶掉宿主自己的长按处理，
            // 导致信息流手势失效（表现为点不动/黑屏感）。改为只在"自动点赞"开启时
            // 挂布局监听——那是纯增量行为，不覆盖宿主任何监听器。

            // ---- 自动点赞 ----
            if (SquareLikeEngine.isAutoLikeOn(activity)) {
                watchLayoutForAutoLike(activity, square);
            }
        } catch (Throwable throwable) {
            Logger.w("square like attach failed", throwable);
        }
    }

    /**
     * 自动点赞：监听布局变化，稳定后触发一次。
     *
     * <p>安全约束：</p>
     * <ul>
     *   <li>用 {@code WeakHashMap} 记录已挂监听的视图，<b>不</b>调用 {@code View.setTag}
     *       —— 宿主的 tag 槽位语义未知，占用可能破坏其 ViewHolder 复用。</li>
     *   <li>防抖用独立 token 的 {@code Handler}，不再对监听器本身
     *       {@code removeCallbacksAndMessages}（那个 token 永远匹配不上，是无效调用）。</li>
     *   <li>监听器内任何异常都吞掉，绝不让它冒泡到宿主的布局回调里。</li>
     * </ul>
     */
    private static void watchLayoutForAutoLike(Activity activity, View square) {
        try {
            synchronized (WATCHED) {
                if (WATCHED.containsKey(square)) {
                    return;
                }
                WATCHED.put(square, Boolean.TRUE);
            }

            square.getViewTreeObserver().addOnGlobalLayoutListener(
                    new android.view.ViewTreeObserver.OnGlobalLayoutListener() {
                        private long lastRun;

                        @Override
                        public void onGlobalLayout() {
                            try {
                                long now = android.os.SystemClock.uptimeMillis();
                                if (now - lastRun < AUTO_LIKE_COOLDOWN_MS) {
                                    return;
                                }
                                lastRun = now;
                                MAIN.removeCallbacks(debounced);
                                MAIN.postDelayed(debounced, AUTO_LIKE_DEBOUNCE_MS);
                            } catch (Throwable throwable) {
                                Logger.w("square auto like layout failed", throwable);
                            }
                        }

                        private final Runnable debounced = () -> {
                            try {
                                if (activity.isFinishing() || activity.isDestroyed()) {
                                    return;
                                }
                                if (!SquareLikeEngine.isAutoLikeOn(activity)) {
                                    return;
                                }
                                int liked = SquareLikeEngine.like(activity);
                                if (liked > 0) {
                                    Logger.i("square auto like; count=" + liked);
                                }
                            } catch (Throwable throwable) {
                                Logger.w("square auto like failed", throwable);
                            }
                        };
                    });
        } catch (Throwable throwable) {
            Logger.w("square auto like watch failed", throwable);
        }
    }

    /** 已挂自动点赞监听的视图（弱引用，随视图回收自动清理）。 */
    private static final java.util.Map<View, Boolean> WATCHED =
            java.util.Collections.synchronizedMap(new java.util.WeakHashMap<>());

    /** 反馈点赞结果（与原文案「N 条点赞，」对齐）。 */
    private static void report(Activity activity, int liked) {
        if (liked < 0) {
            android.widget.Toast.makeText(activity, "点赞中断，请稍后重试",
                    android.widget.Toast.LENGTH_SHORT).show();
            return;
        }
        if (liked == 0) {
            android.widget.Toast.makeText(activity, "没有可点赞的帖子",
                    android.widget.Toast.LENGTH_SHORT).show();
            return;
        }
        android.widget.Toast.makeText(activity, liked + " 条点赞，已完成",
                android.widget.Toast.LENGTH_SHORT).show();
    }
}
