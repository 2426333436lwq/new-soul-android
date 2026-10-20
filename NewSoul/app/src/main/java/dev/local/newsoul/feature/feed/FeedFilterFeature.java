package dev.local.newsoul.feature.feed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;

/**
 * 信息流过滤（原 {@code B2.c} 的安装段 + {@code B2.b} 的隐藏逻辑）。
 *
 * <p><b>原始证据</b>：{@code B2.c} 加载 {@code SquareAdapter}、
 * {@code BaseSquareViewHolder}、{@code square.bean.Tag}、{@code square.post.bean.Post}，
 * 取 {@code Post} 的字段 {@code content}/{@code contentTitle}/{@code title}/{@code gender}/
 * {@code tags}/{@code postAuthorModel}，并安装三个 hook：
 * {@code new-soul/feed-filter-attach}、{@code new-soul/feed-filter-bind}、
 * {@code new-soul/feed-filter-recycle}。失败时记录
 * {@code "feed keyword fields unavailable"} 并降级。</p>
 *
 * <p><b>隐藏方式</b>（{@code B2.b} + {@code B2.e}）：对目标视图
 * {@code setVisibility(GONE)} 并同时把 {@code LayoutParams} 的高宽压成 0 ——
 * 只置 GONE 在 RecyclerView 里仍会占据 item 间距，压尺寸才能真正消除空行。</p>
 *
 * <p>三个 hook 点的分工：<b>attach</b> 在 item 附着时判定并隐藏；<b>bind</b> 在数据绑定后
 * 复查（性别/关键词可能此时才填充）；<b>recycle</b> 在回收时复位，避免 ViewHolder 复用把
 * 上一项的隐藏状态带给下一项。</p>
 */
public final class FeedFilterFeature implements dev.local.newsoul.feature.FeatureInstaller {

    @Override
    public String id() {
        return "feed-filter";
    }

    @Override
    public void install(ModuleContext context) {
        Class<?> viewHolder = context.loadHostClass(HostClasses.SQUARE_BASE_SQUARE_VIEW_HOLDER);
        if (viewHolder == null) {
            Logger.e("square feed filter unavailable: view holder not found");
            return;
        }

        boolean any = false;
        any |= hookIfPresent(context, viewHolder, "onViewAttachedToWindow",
                HookIds.FEED_FILTER_ATTACH, new FeedBindHooker(false));
        any |= hookIfPresent(context, viewHolder, "onViewDetachedFromWindow",
                HookIds.FEED_FILTER_RECYCLE, new FeedBindHooker(true));

        // 绑定后复查：宿主先回调 onBindViewHolder 再填充字段，因此 bind 路径单独装上。
        Class<?> adapter = context.loadHostClass(HostClasses.SQUARE_SQUARE_ADAPTER);
        if (adapter != null) {
            any |= hookIfPresent(context, adapter, "onBindViewHolder",
                    HookIds.FEED_FILTER_BIND, new FeedBindHooker(false));
        }

        if (any) {
            Logger.i("square feed filter ready");
        } else {
            Logger.e("square feed filter unavailable");
        }
    }

    private static boolean hookIfPresent(ModuleContext context, Class<?> clazz, String methodName,
            String id, XposedInterface.Hooker hooker) {
        for (Method method : Reflect.findMethods(clazz, methodName)) {
            if (Hooks.intercept(context.module(), id, method, hooker)) {
                return true;
            }
        }
        return false;
    }

    // ================================================================ Hooker

    /**
     * 视图附着 / 绑定 / 回收时的过滤与复位。
     */
    private static final class FeedBindHooker implements XposedInterface.Hooker {

        /** {@code true} 表示回收路径：只复位，不隐藏。 */
        private final boolean recycle;

        FeedBindHooker(boolean recycle) {
            this.recycle = recycle;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            Object result = chain.proceed();
            try {
                View itemView = resolveItemView(chain);
                if (itemView != null) {
                    if (recycle) {
                        resetItem(itemView);
                    } else {
                        applyFilter(itemView);
                    }
                }
            } catch (Throwable throwable) {
                Logger.w("feed filter failed", throwable);
            }
            return result;
        }
    }

    /** 从 hook 上下文取出「条目根视图」。 */
    private static View resolveItemView(XposedInterface.Chain chain) {
        // onViewAttachedToWindow(View) / onViewDetachedFromWindow(View)：第 0 参即条目视图
        try {
            Object arg0 = chain.getArg(0);
            if (arg0 instanceof View view) {
                return view;
            }
        } catch (Throwable ignored) {
            // 参数个数不符时继续尝试 receiver
        }
        Object self = chain.getThisObject();
        if (self instanceof View view) {
            return view;
        }
        // ViewHolder：itemView 字段
        Field itemView = Reflect.findField(self.getClass(), "itemView");
        if (itemView != null) {
            Object value = Reflect.getFieldValue(self, itemView);
            if (value instanceof View view) {
                return view;
            }
        }
        return null;
    }

    /** 判定并隐藏。 */
    private static void applyFilter(View itemView) {
        Context context = itemView.getContext();
        if (context == null) {
            return;
        }
        Object post = resolvePost(itemView);
        if (post == null) {
            return;
        }
        if (FeedFilter.shouldHide(context, post)) {
            hideItem(itemView);
        } else {
            resetItem(itemView);
        }
    }

    /**
     * 取出条目绑定的帖子对象。
     *
     * <p>宿主把 Post 存在 {@code ViewHolder} 字段或 {@code itemView} 的 tag 上，
     * 两种都尝试（原实现用 {@code key_item_post} 资源名定位）。</p>
     */
    private static Object resolvePost(View itemView) {
        Object tag = itemView.getTag();
        if (isPostLike(tag)) {
            return tag;
        }
        // 遍历字段找 Post 实例（类名含 square.post 或字段名像 post/item）
        for (Field field : Reflect.allFields(itemView.getClass())) {
            String typeName = field.getType().getName();
            String fieldName = field.getName();
            boolean looksLikePost = typeName.contains("square.post")
                    || typeName.endsWith(".Post")
                    || "post".equals(fieldName)
                    || "item".equals(fieldName)
                    || "data".equals(fieldName)
                    || "model".equals(fieldName);
            if (!looksLikePost) {
                continue;
            }
            Object value = Reflect.getFieldValue(itemView, field);
            if (isPostLike(value)) {
                return value;
            }
        }
        // itemView 自身可能就是 Post 的宿主（少见）
        return isPostLike(itemView) ? itemView : null;
    }

    /** 粗略判定对象是否为帖子模型。 */
    private static boolean isPostLike(Object value) {
        if (value == null) {
            return false;
        }
        String name = value.getClass().getName();
        return name.contains("square.post") || name.endsWith(".Post");
    }

    /**
     * 隐藏条目：GONE + 压缩尺寸。
     *
     * <p>对应原 {@code B2.e}。只置 GONE 在 RecyclerView 中仍占间距，必须同时把
     * {@code LayoutParams} 高宽压成 0。</p>
     *
     * <p>不从父容器移除：那会破坏宿主 Adapter 的 item 索引。</p>
     */
    private static void hideItem(View itemView) {
        try {
            itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            if (params != null && (params.height != 0 || params.width != 0)) {
                params.width = 0;
                params.height = 0;
                itemView.setLayoutParams(params);
            }
        } catch (Throwable throwable) {
            Logger.w("feed hide failed", throwable);
        }
    }

    /**
     * 复位条目：恢复可见与原始尺寸。
     *
     * <p>ViewHolder 复用要求回收时清干净，否则被隐藏过的 item 会把状态带给下一项。
     * 这里只恢复「被我们压成 0」的尺寸，不动宿主自己设置的其它属性。</p>
     */
    private static void resetItem(View itemView) {
        try {
            if (itemView.getVisibility() != View.VISIBLE) {
                itemView.setVisibility(View.VISIBLE);
            }
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            if (params != null && params.height == 0) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                itemView.setLayoutParams(params);
            }
        } catch (Throwable throwable) {
            Logger.w("feed reset failed", throwable);
        }
    }
}
