package dev.local.newsoul.feature.square;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.HostViews;
import dev.local.newsoul.framework.Logger;

/**
 * 广场点赞引擎（原 {@code I2}）。
 *
 * <p><b>原始证据</b>：{@code I2.&lt;init&gt;(Activity, View)} 用资源名
 * {@code pager_square}（广场容器）、{@code key_item_post}（帖子根视图）、
 * {@code iv_like}（点赞按钮）定位；{@code I2.a()} 读 {@code square_like_all_loaded}，
 * {@code I2.b()} 读 {@code square_auto_like}，{@code I2.g()} 读 {@code square_visible_like}。
 * 批量执行时打日志 {@code "square like batch started: scope="} 与 {@code "N 条点赞，"},
 * 失败时打 {@code "loaded like failed; batch stopped"} 并<b>停止整批</b>。</p>
 *
 * <p><b>三种范围</b>（互斥优先级由 UI 文案确定）：</p>
 * <ul>
 *   <li>{@code square_visible_like} —— 当前屏幕上可见的卡片</li>
 *   <li>{@code square_like_all_loaded} —— 已加载的全部卡片</li>
 *   <li>{@code square_auto_like} —— 滚动/加载时自动点赞；开启且就绪时，
 *       一键点赞配置暂不生效，但开关仍可修改、原范围设置保留</li>
 * </ul>
 *
 * <p><b>与信息流过滤联动</b>（来自 UI 文案「隐藏的帖子不参与点赞」）：被
 * {@link dev.local.newsoul.feature.feed.FeedFilter} 判为隐藏的条目一律跳过。</p>
 *
 * <p><b>停止策略</b>：任一卡片点赞失败即中止整批（原实现行为），避免
 * 在宿主状态异常时疯狂重试触发风控。</p>
 */
public final class SquareLikeEngine {

    /** 广场容器资源名。 */
    public static final String RES_PAGER_SQUARE = "pager_square";
    /** 帖子根视图资源名（宿主 ViewHolder 的 tag key）。 */
    public static final String RES_KEY_ITEM_POST = "key_item_post";
    /** 点赞按钮资源名。 */
    public static final String RES_IV_LIKE = "iv_like";

    private SquareLikeEngine() {
    }

    // ------------------------------------------------------------------ 范围判定

    /** 当前应执行的范围。 */
    public enum Scope {
        /** 不点赞。 */
        NONE,
        /** 仅可见卡片。 */
        VISIBLE,
        /** 已加载全部卡片。 */
        LOADED,
    }

    /**
     * 解析当前生效范围。
     *
     * <p>优先级：{@code square_auto_like} 开启时按 {@code square_like_all_loaded}
     * 决定范围（自动点赞只是一个触发时机，范围仍由范围开关决定）；否则按
     * {@code square_visible_like} / {@code square_like_all_loaded} 取其一。</p>
     */
    public static Scope resolveScope(Context context) {
        if (context == null) {
            return Scope.NONE;
        }
        ConfigStore store = ConfigStore.from(context);
        boolean auto = store.getBoolean(SettingsKeys.SQUARE_AUTO_LIKE);
        boolean allLoaded = store.getBoolean(SettingsKeys.SQUARE_LIKE_ALL_LOADED);
        boolean visible = store.getBoolean(SettingsKeys.SQUARE_VISIBLE_LIKE);

        if (auto) {
            // 自动点赞开启：范围沿用「全部已加载」的配置，缺省则仅可见
            return allLoaded ? Scope.LOADED : Scope.VISIBLE;
        }
        if (allLoaded) {
            return Scope.LOADED;
        }
        if (visible) {
            return Scope.VISIBLE;
        }
        return Scope.NONE;
    }

    /** 自动点赞是否开启（用于滚动时触发）。 */
    public static boolean isAutoLikeOn(Context context) {
        return context != null
                && ConfigStore.from(context).getBoolean(SettingsKeys.SQUARE_AUTO_LIKE);
    }

    // ------------------------------------------------------------------ 执行

    /**
     * 按当前范围执行一键点赞。
     *
     * <p>必须在主线程调用（视图操作）。</p>
     *
     * @return 成功点赞的条数；{@code -1} 表示因失败中止
     */
    public static int like(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return 0;
        }
        Scope scope = resolveScope(activity);
        if (scope == Scope.NONE) {
            return 0;
        }
        return like(activity, scope);
    }

    /** 按指定范围执行点赞。 */
    public static int like(Activity activity, Scope scope) {
        if (activity == null || scope == Scope.NONE) {
            return 0;
        }
        try {
            View root = HostViews.findByName(activity, RES_PAGER_SQUARE);
            if (root == null) {
                Logger.i("square like unavailable: pager_square not found");
                return 0;
            }

            List<View> itemRoots = collectItemRoots(root);
            if (itemRoots.isEmpty()) {
                Logger.i("square like snapshot unavailable");
                return 0;
            }

            Logger.i("square like batch started: scope="
                    + (scope == Scope.LOADED ? "loaded" : "visible") + "; count=" + itemRoots.size());

            int liked = 0;
            for (View item : itemRoots) {
                if (scope == Scope.VISIBLE && !isOnScreen(item, root)) {
                    continue;
                }
                if (isFilteredOut(item)) {
                    // 被信息流过滤隐藏的帖子不参与点赞
                    continue;
                }
                View likeButton = findLikeButton(item);
                if (likeButton == null) {
                    continue;
                }
                if (clickLike(likeButton)) {
                    liked++;
                } else {
                    // 原实现：任一失败即停止整批
                    Logger.i("loaded like failed; batch stopped");
                    return -1;
                }
            }
            Logger.i("square like batch done; count=" + liked);
            return liked;
        } catch (Throwable throwable) {
            Logger.w("square like failed", throwable);
            return -1;
        }
    }

    // ------------------------------------------------------------------ 收集

    /**
     * 收集广场中的帖子根视图。
     *
     * <p>优先按 tag key {@code key_item_post} 识别；该 tag 在宿主 ViewHolder 里设置，
     * 是比类名更稳定的锚点。取不到时退化为「直接子项」。 </p>
     */
    private static List<View> collectItemRoots(View root) {
        List<View> result = new ArrayList<>();
        int key = tagKey(root.getContext(), RES_KEY_ITEM_POST);
        collect(root, key, result);
        return result;
    }

    private static void collect(View view, int tagKey, List<View> out) {
        if (tagKey != 0 && view.getTag(tagKey) != null) {
            out.add(view);
            return;
        }
        if (view instanceof ViewGroup group) {
            for (int i = 0; i < group.getChildCount(); i++) {
                collect(group.getChildAt(i), tagKey, out);
            }
        }
    }

    /** 解析宿主资源名到 tag key（宿主用资源 id 作为 setTag 的 key）。 */
    private static int tagKey(Context context, String resourceName) {
        try {
            return context.getResources().getIdentifier(resourceName, "id",
                    context.getPackageName());
        } catch (Throwable throwable) {
            return 0;
        }
    }

    // ------------------------------------------------------------------ 判定

    /** 是否在屏幕上可见（对应 {@code square_visible_like} 范围）。 */
    private static boolean isOnScreen(View item, View root) {
        if (!item.isShown() || item.getVisibility() != View.VISIBLE) {
            return false;
        }
        try {
            android.graphics.Rect bounds = new android.graphics.Rect();
            if (!item.getGlobalVisibleRect(bounds)) {
                return false;
            }
            android.graphics.Rect rootBounds = new android.graphics.Rect();
            if (!root.getGlobalVisibleRect(rootBounds)) {
                return false;
            }
            return android.graphics.Rect.intersects(bounds, rootBounds);
        } catch (Throwable throwable) {
            return item.isShown();
        }
    }

    /** 条目是否被信息流过滤隐藏。 */
    private static boolean isFilteredOut(View item) {
        if (item.getVisibility() != View.VISIBLE) {
            return true;
        }
        ViewGroup.LayoutParams params = item.getLayoutParams();
        return params != null && params.height == 0;
    }

    // ------------------------------------------------------------------ 点赞按钮

    /**
     * 定位点赞按钮。
     *
     * <p>宿主把点赞按钮 id 命名为 {@code iv_like}，但在不同卡片类型下层级不同，
     * 因此递归查找。找不到 id 时按「可点击的 ImageView 且描述含赞」兜底。</p>
     */
    public static View findLikeButton(View item) {
        int id = tagKey(item.getContext(), RES_IV_LIKE);
        if (id != 0) {
            View found = item.findViewById(id);
            if (found != null) {
                return found;
            }
        }
        return findClickableLike(item);
    }

    private static View findClickableLike(View view) {
        if (view instanceof android.widget.ImageView && view.isClickable()) {
            CharSequence description = view.getContentDescription();
            if (description != null) {
                String text = description.toString();
                if (text.contains("赞") || text.contains("like") || text.contains("Like")) {
                    return view;
                }
            }
        }
        if (view instanceof ViewGroup group) {
            for (int i = 0; i < group.getChildCount(); i++) {
                View found = findClickableLike(group.getChildAt(i));
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /**
     * 执行点赞。
     *
     * <p>先判断是否已点赞（避免取消赞）：宿主的点赞按钮在已赞状态下通常是
     * {@code isSelected()} 或 tag 标记；判断不出来时直接点击 —— 用户的预期是
     * 「批量点赞」，重复点击最多是无操作。</p>
     *
     * @return 是否成功触发
     */
    private static boolean clickLike(View likeButton) {
        try {
            if (isAlreadyLiked(likeButton)) {
                return true;
            }
            if (!likeButton.isEnabled()) {
                return false;
            }
            likeButton.performClick();
            return true;
        } catch (Throwable throwable) {
            Logger.w("square like click failed; not retried", throwable);
            return false;
        }
    }

    /** 判断是否已点赞。 */
    private static boolean isAlreadyLiked(View likeButton) {
        try {
            if (likeButton.isSelected()) {
                return true;
            }
            Object tag = likeButton.getTag();
            if (tag instanceof Boolean liked) {
                return liked;
            }
            // 部分版本把状态放在 ViewHolder 的 boolean 字段里
            for (Field field : dev.local.newsoul.framework.Reflect.allFields(likeButton.getClass())) {
                if (field.getType() == boolean.class
                        && ("liked".equals(field.getName()) || "isLiked".equals(field.getName()))) {
                    return dev.local.newsoul.framework.Reflect.getBoolean(likeButton, field, false);
                }
            }
        } catch (Throwable ignored) {
            // 判断失败按未点赞处理
        }
        return false;
    }
}
