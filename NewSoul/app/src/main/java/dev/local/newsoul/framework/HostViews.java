package dev.local.newsoul.framework;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 宿主视图定位（原 {@code Z1} 的 {@code e}/{@code f}/{@code g} 与 {@code w2.k}）。
 *
 * <p>宿主布局里的关键锚点只有资源名（{@code topic_title}、{@code rlWebRoot} 等），
 * 没有稳定的 {@code R.id} 常量可供编译期引用，因此必须按「资源名 → id → findViewById」
 * 三跳定位。资源名在宿主各版本间比混淆类名稳定得多。</p>
 */
public final class HostViews {

    /** 每个 Activity 的资源名 → view 缓存，避免重复解析 id。 */
    private static final Map<Context, Map<String, View>> CACHE = new WeakHashMap<>();

    private HostViews() {
    }

    /** 按宿主资源名查找视图；找不到返回 {@code null}。 */
    public static View findByName(Activity activity, String resourceName) {
        if (activity == null || resourceName == null) {
            return null;
        }
        Map<String, View> local;
        synchronized (CACHE) {
            local = CACHE.get(activity);
            if (local == null) {
                local = new WeakHashMap<>();
                CACHE.put(activity, local);
            }
            View cached = local.get(resourceName);
            if (cached != null) {
                return cached;
            }
        }
        View found = resolve(activity, resourceName);
        if (found != null) {
            synchronized (CACHE) {
                local.put(resourceName, found);
            }
        }
        return found;
    }

    private static View resolve(Activity activity, String resourceName) {
        try {
            String hostPackage = activity.getPackageName();
            int id = activity.getResources().getIdentifier(resourceName, "id", hostPackage);
            if (id == 0) {
                return null;
            }
            return activity.findViewById(id);
        } catch (Throwable throwable) {
            Logger.w("host view lookup failed: " + resourceName, throwable);
            return null;
        }
    }

    /** 深度优先按类名查找子视图（宿主未提供资源名的场景）。 */
    public static View findByClassName(View root, String className) {
        if (root == null) {
            return null;
        }
        if (className.equals(root.getClass().getName())) {
            return root;
        }
        if (root instanceof ViewGroup group) {
            for (int i = 0; i < group.getChildCount(); i++) {
                View found = findByClassName(group.getChildAt(i), className);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /** 遍历视图树。 */
    public static void walk(View root, java.util.function.Consumer<View> visitor) {
        if (root == null) {
            return;
        }
        visitor.accept(root);
        if (root instanceof ViewGroup group) {
            for (int i = 0; i < group.getChildCount(); i++) {
                walk(group.getChildAt(i), visitor);
            }
        }
    }

    /** 判断视图在当前时刻是否可安全操作（已附着且未回收）。 */
    public static boolean isOperable(View view) {
        return view != null && view.isAttachedToWindow() && view.getParent() != null;
    }
}
