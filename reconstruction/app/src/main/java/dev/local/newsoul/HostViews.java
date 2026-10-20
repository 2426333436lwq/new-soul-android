package dev.local.newsoul;

import android.app.Activity;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/** 宿主 View 定位器。对应原始 DEX 的 Z1（HostViewLocator）+ w2.k。 */
public final class HostViews {

    private HostViews() {}

    /** 每个 Activity 只注入一次的守卫。对应原实现的 w2.a WeakHashMap。 */
    private static final Map<Object, Boolean> INJECTED = new WeakHashMap<>();

    /** 按宿主资源名查找 View。 */
    public static View byName(Activity a, String name) {
        int id = Reflect.hostId(a, name, "id");
        if (id == 0) return null;
        return a.findViewById(id);
    }

    /** 按宿主布局/字符串资源名查找。 */
    public static View byRes(Activity a, String name, String type) {
        int id = Reflect.hostId(a, name, type);
        if (id == 0) return null;
        return a.findViewById(id);
    }

    /** 记录已注入。 */
    public static boolean markInjected(Object key) {
        if (INJECTED.containsKey(key)) {
            return false;
        }
        INJECTED.put(key, Boolean.TRUE);
        return true;
    }

    /** 清除记录（Activity 销毁时）。 */
    public static void forget(Object key) {
        INJECTED.remove(key);
    }

    // ------------------------------------------------------------------

    /** 深度优先按类名找子 View（用于宿主没有 id 的场景）。 */
    public static View findByClass(View root, String className) {
        if (root == null) return null;
        if (root.getClass().getName().equals(className)) return root;
        if (root instanceof android.view.ViewGroup g) {
            for (int i = 0; i < g.getChildCount(); i++) {
                View r = findByClass(g.getChildAt(i), className);
                if (r != null) return r;
            }
        }
        return null;
    }

    /** 按内容描述找（无障碍标签）。 */
    public static View byContentDescription(View root, String desc) {
        if (root == null) return null;
        if (desc.equals(root.getContentDescription())) return root;
        if (root instanceof android.view.ViewGroup g) {
            for (int i = 0; i < g.getChildCount(); i++) {
                View r = byContentDescription(g.getChildAt(i), desc);
                if (r != null) return r;
            }
        }
        return null;
    }
}
