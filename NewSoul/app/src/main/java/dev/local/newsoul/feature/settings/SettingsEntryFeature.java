package dev.local.newsoul.feature.settings;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.WeakHashMap;

import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.HostViews;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.ui.SettingsPanel;
import io.github.libxposed.api.XposedInterface;

/**
 * 设置入口注入（原 {@code SoulHook.installSettingsHook} + {@code x2.intercept} +
 * {@code I1.run} + {@code E1.run}）。
 *
 * <p><b>触发链路</b>（全部来自原 DEX 证据）：</p>
 * <ol>
 *   <li>hook 宿主<b>私有</b>方法 {@code cn.soulapp.android.h5.activity.H5Activity.m1(boolean)}</li>
 *   <li>宿主打开自身 H5 设置页时会调用该方法，模块因此被唤醒</li>
 *   <li>在宿主视图树中定位四个锚点：{@code topic_title} / {@code rlWebRoot} /
 *       {@code contentLayout} / {@code titleLay}</li>
 *   <li>校验类型与父子关系通过后，在标题栏右侧插入「New Soul」入口按钮</li>
 * </ol>
 *
 * <p><b>为什么 hook 私有方法</b>：{@code m1} 是宿主设置页的入口，只在用户真正打开设置页时
 * 触发，模块不必常驻 UI，降低被检测概率。原实现亦显式断言
 * {@code Modifier.isPrivate(...)}，签名不符即降级而非误挂。</p>
 *
 * <p><b>守卫</b>（缺一即放弃注入，与原实现一致）：Activity 未销毁 / 未 finishing、
 * 同一 Activity 只注入一次、四个锚点类型正确、{@code contentLayout.getParent() == rlWebRoot}、
 * {@code contentLayout} 的 LayoutParams 是 {@code RelativeLayout.LayoutParams}。</p>
 */
public final class SettingsEntryFeature implements dev.local.newsoul.feature.FeatureInstaller {

    /** 原实现用 WeakHashMap 记录"已注入过"的 Activity，避免重复插入。 */
    private static final Map<Object, Boolean> INJECTED = new WeakHashMap<>();

    /** 视图树可能尚未就绪，注入要 post 到主线程消息队列。 */
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    private static final String ANCHOR_TITLE = "topic_title";
    private static final String ANCHOR_ROOT = "rlWebRoot";
    private static final String ANCHOR_CONTENT = "contentLayout";
    private static final String ANCHOR_TITLE_LAY = "titleLay";

    @Override
    public String id() {
        return "settings-entry";
    }

    @Override
    public void install(ModuleContext context) {
        try {
            Class<?> h5 = context.loadHostClass(HostClasses.H5_ACTIVITY);
            if (h5 == null) {
                Logger.e("settings entry unavailable: H5Activity not found");
                return;
            }
            Method m1 = h5.getDeclaredMethod("m1", boolean.class);
            if (!Modifier.isPrivate(m1.getModifiers())) {
                throw new IllegalStateException("Unexpected H5Activity.m1 signature: " + m1);
            }
            m1.setAccessible(true);
            Hooks.deoptimize(context.module(), "settings-entry", m1);

            boolean ok = Hooks.intercept(context.module(), "soul-demo/private-h5-m1", m1,
                    new EntryHooker());
            if (ok) {
                Logger.i("private hook installed: H5Activity.m1(boolean)");
            } else {
                Logger.e("settings entry unavailable; check app version");
            }
        } catch (Throwable throwable) {
            Logger.e("settings entry unavailable; check app version", throwable);
        }
    }

    /** 对应 {@code x2.intercept}。 */
    private static final class EntryHooker implements XposedInterface.Hooker {

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            try {
                Object self = chain.getThisObject();
                if (self instanceof Activity activity) {
                    scheduleInject(activity);
                }
            } catch (Throwable throwable) {
                Logger.w("settings entry hook failed", throwable);
            }
            return chain.proceed();
        }
    }

    private static void scheduleInject(Activity activity) {
        if (activity.isDestroyed() || activity.isFinishing()) {
            return;
        }
        synchronized (INJECTED) {
            if (INJECTED.containsKey(activity)) {
                return;
            }
        }
        MAIN.post(() -> inject(activity));
    }

    /**
     * 对应 {@code I1.run}：定位锚点、校验、插入按钮。
     */
    private static void inject(Activity activity) {
        try {
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            View title = HostViews.findByName(activity, ANCHOR_TITLE);
            View root = HostViews.findByName(activity, ANCHOR_ROOT);
            View content = HostViews.findByName(activity, ANCHOR_CONTENT);
            View titleLay = HostViews.findByName(activity, ANCHOR_TITLE_LAY);

            // ---- 类型与结构守卫（与原实现逐条对应） ----
            if (!(title instanceof TextView)) {
                return;
            }
            if (!(root instanceof RelativeLayout)) {
                return;
            }
            if (content == null || titleLay == null) {
                return;
            }
            if (content.getParent() != root) {
                return;
            }
            if (!(content.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
                return;
            }

            synchronized (INJECTED) {
                if (INJECTED.containsKey(activity)) {
                    return;
                }
                INJECTED.put(activity, Boolean.TRUE);
            }

            RelativeLayout container = (RelativeLayout) root;
            TextView entry = buildEntry(activity);

            // 定位：贴在标题栏（titleLay）右侧，而不是整个根容器。
            // 原因：根容器里还叠着宿主自己的设置列表，直接 addView 到根容器会被
            // 列表盖住 —— 视觉上能看到（透过透明区域），但触摸事件被列表吃掉，
            // 点了没反应。挂到标题栏这一层可以保证在列表之上。
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (titleLay instanceof RelativeLayout) {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
            }
            int margin = (int) (8 * activity.getResources().getDisplayMetrics().density);
            params.rightMargin = margin;

            ViewGroup target = titleLay instanceof ViewGroup ? (ViewGroup) titleLay : container;
            target.addView(entry, params);

            // 关键：抬高 z 序并确保可点击。宿主的列表可能后添加，会覆盖在普通子视图之上；
            // elevation 是让我们的按钮保持在最上层的最可靠方式（API 21+）。
            entry.setElevation(1000f);
            entry.bringToFront();
            entry.setClickable(true);
            entry.setFocusable(true);
            entry.setEnabled(true);

            // 再补一层保险：若宿主父容器拦截触摸，直接在容器上做命中测试兜底。
            installFallbackHitTest(activity, container, entry);

            Logger.i("settings entry inserted; trigger=private H5Activity.m1; host="
                    + target.getClass().getSimpleName());
        } catch (Throwable throwable) {
            Logger.e("Settings entry failed", throwable);
        }
    }

    /** 构建入口按钮（沿用宿主标题栏文字颜色，保证两套主题下都可读）。 */
    private static TextView buildEntry(Activity activity) {
        TextView entry = new TextView(activity);
        entry.setText("New Soul");
        entry.setTextSize(14f);
        entry.setPadding(dp(activity, 10), dp(activity, 6), dp(activity, 10), dp(activity, 6));
        entry.setClickable(true);
        entry.setFocusable(true);
        try {
            View title = HostViews.findByName(activity, ANCHOR_TITLE);
            if (title instanceof TextView) {
                entry.setTextColor(((TextView) title).getCurrentTextColor());
            }
        } catch (Throwable ignored) {
            // 取色失败时保持默认颜色
        }
        entry.setOnClickListener(v -> {
            try {
                SettingsPanel.show(activity);
            } catch (Throwable throwable) {
                Logger.e("settings panel failed", throwable);
            }
        });
        return entry;
    }

    private static int dp(Activity activity, int value) {
        return Math.round(value * activity.getResources().getDisplayMetrics().density);
    }

    /**
     * 兜底点击通道。
     *
     * <p>宿主设置页的容器可能注册了 {@code OnTouchListener} 或设置了
     * {@code disallowIntercept}，把触摸事件在到达我们的按钮之前吃掉。
     * 这里给容器挂一个不消费事件的监听器：只在触摸点落在按钮范围内时手动转发一次点击。</p>
     */
    private static void installFallbackHitTest(Activity activity, ViewGroup container, View entry) {
        try {
            container.setOnTouchListener((v, event) -> {
                try {
                    if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                        if (hitTest(entry, event.getRawX(), event.getRawY())) {
                            entry.performClick();
                        }
                    }
                } catch (Throwable throwable) {
                    Logger.w("settings entry hit test failed", throwable);
                }
                // 返回 false：不消费事件，宿主原有交互完全不受影响
                return false;
            });
        } catch (Throwable throwable) {
            Logger.w("settings entry fallback unavailable", throwable);
        }
    }

    /** 判断原始触摸坐标是否落在目标视图范围内。 */
    private static boolean hitTest(View target, float rawX, float rawY) {
        if (target == null || !target.isShown()) {
            return false;
        }
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + target.getWidth();
        int bottom = top + target.getHeight();
        // 稍微放宽命中区域，手指点不准也能触发
        int slop = (int) (12 * target.getResources().getDisplayMetrics().density);
        return rawX >= left - slop && rawX <= right + slop
                && rawY >= top - slop && rawY <= bottom + slop;
    }
}
