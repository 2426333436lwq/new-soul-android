package dev.local.newsoul.feature.ad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.HostViews;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;

/**
 * 广告屏蔽（原 {@code SoulHook.onPackageReady} 中的广告安装段 + {@code D} 的可见性 hook）。
 *
 * <p><b>三重屏蔽策略</b>（依据原 DEX 证据还原）：</p>
 * <ol>
 *   <li><b>视图层</b>：hook 全部 {@code cn.soulapp.android.ad.*} 广告视图类的
 *       {@code onAttachedToWindow} / {@code onMeasure}，命中后把自身从父容器移除或置为
 *       {@code GONE}。原实现还会遍历 {@code ViewGroup} 子视图（{@code getChildCount} /
 *       {@code getChildAt}）定位真正的广告容器 —— 因为广告 View 常被包一层 FrameLayout，
 *       直接隐藏被 hook 的实例往往无效。</li>
 *   <li><b>适配器层</b>：hook {@code SquareAdapter} 等列表数据方法
 *       （{@code setData} / {@code replaceData} / {@code setNewData} / {@code setDiffNewData} /
 *       {@code setList} / {@code setNewInstance} / {@code addData}），从数据源剔除广告条目。
 *       原实现的探测失败日志为 {@code "no adapter mutation methods"}。</li>
 *   <li><b>请求层</b>：hook {@code SoulAdRequestListener}，让广告请求直接失败。</li>
 * </ol>
 *
 * <p><b>开关粒度</b>：18 个 {@code ad_*} 开关按广告位分类。视图类与开关的映射见
 * {@link #switchFor}；未命中的视图若任一广告开关开启，则按「全局屏蔽」处理。</p>
 */
public final class AdBlockFeature implements dev.local.newsoul.feature.FeatureInstaller {

    /** 广告视图类 → 对应开关键。 */
    private static final String[][] VIEW_SWITCHES = {
            {HostClasses.AD_FLOAT_VIEW, SettingsKeys.AD_FLOAT},
            {HostClasses.AD_FLOAT_VIEW_V2, SettingsKeys.AD_FLOAT},
            {HostClasses.AD_DRAGGABLE_FLOAT_VIEW, SettingsKeys.AD_FLOAT},
            {HostClasses.AD_FLOAT_ENTRANCE_VIEW, SettingsKeys.AD_FLOAT},
            {HostClasses.AD_FLOAT_VIEW_PROVIDER, SettingsKeys.AD_FLOAT},
            {HostClasses.AD_SEARCH_BANNER_VIEW, SettingsKeys.AD_SEARCH},
            {HostClasses.AD_INTERSTITIAL_DIALOG, SettingsKeys.AD_INTERSTITIAL},
            {HostClasses.AD_INTERSTITIAL_BOTTOM_DIALOG, SettingsKeys.AD_INTERSTITIAL},
            {HostClasses.AD_EXPRESS_ENVELOPE_MESSAGE, SettingsKeys.AD_ENVELOPE},
            {HostClasses.AD_EXPRESS_FEED_DRAW, SettingsKeys.AD_FEED},
            {HostClasses.AD_EXPRESS_FEED_WEB, SettingsKeys.AD_FEED},
            {HostClasses.AD_EXPRESS_GROUP_BANNER, SettingsKeys.AD_GROUP},
            {HostClasses.AD_EXPRESS_ME_CELL, SettingsKeys.AD_ME},
            {HostClasses.AD_EXPRESS_PLANT_GAME, SettingsKeys.AD_PLANET},
            {HostClasses.AD_EXPRESS_PLANT_GAME_TAG, SettingsKeys.AD_TAG},
            {HostClasses.AD_EXPRESS_POST_DETAIL, SettingsKeys.AD_DETAIL},
            {HostClasses.AD_EXPRESS_POST_FEED_COMPONENT, SettingsKeys.AD_FEED},
            {HostClasses.AD_EXPRESS_SYSTEM_NOTICE, SettingsKeys.AD_NOTICE},
            {HostClasses.AD_EXPRESS_TAG_BANNER, SettingsKeys.AD_TAG_WEB},
    };

    /** 列表适配器数据方法候选（原实现的探测列表）。 */
    private static final String[] ADAPTER_METHODS = {
            "setData", "replaceData", "setNewData", "setDiffNewData",
            "setList", "setNewInstance", "addData",
    };

    /** 这些适配器持有广告条目。 */
    private static final String[] ADAPTER_CLASSES = {
            HostClasses.SQUARE_SQUARE_ADAPTER,
            HostClasses.SQUARE_AD_EXPRESS_VIEW_HOLDER,
            HostClasses.SQUARE_AD_POST_VIEW_HOLDER,
    };

    /** 全局上下文提供者（列表框场景拿不到 View，需要它兜底取 Context）。 */
    private static volatile dev.local.newsoul.framework.GlobalContextProvider contextProvider;

    @Override
    public String id() {
        return "ad-block";
    }

    @Override
    public void install(ModuleContext context) {
        // 把宿主上下文提供者登记到全局持有者：适配器 hook 拿不到 View，
        // 只能靠它取 Context 读开关。
        try {
            dev.local.newsoul.framework.GlobalContextProvider provider =
                    dev.local.newsoul.framework.GlobalContextProvider.resolve(context);
            dev.local.newsoul.framework.GlobalContextHolder.attachProvider(provider);
            contextProvider = provider;
        } catch (Throwable throwable) {
            Logger.w("global context provider unavailable", throwable);
        }
        installViewHooks(context);
        installAdapterHooks(context);
        installRequestHooks(context);
    }

    /** 取全局 Context；不可用时返回 {@code null}。 */
    private static Context globalContext() {
        return dev.local.newsoul.framework.GlobalContextHolder.get();
    }

    // ================================================================ 视图层

    private static void installViewHooks(ModuleContext context) {
        int installed = 0;
        for (String[] pair : VIEW_SWITCHES) {
            String className = pair[0];
            String switchKey = pair[1];
            Class<?> clazz = context.loadHostClass(className);
            if (clazz == null) {
                continue;
            }
            // 只 hook onAttachedToWindow：
            //   · onMeasure / onDraw / dispatchTouchEvent 都在渲染与输入的关键路径上，
            //     在这些回调里摘除或改写视图会破坏布局遍历，直接把宿主拖成黑屏/ANR。
            //   · onAttachedToWindow 是唯一"视图已进入树但尚未参与本轮布局"的安全时机。
            Method target = Reflect.findAnyMethod(clazz, "onAttachedToWindow");
            if (target == null) {
                continue;
            }
            String id = dev.local.newsoul.framework.HookIds.AD_LOADER_PREFIX
                    + simpleName(className) + "/onAttachedToWindow";
            if (Hooks.intercept(context.module(), id, target,
                    new AdViewHooker(switchKey))) {
                installed++;
            }
        }
        Logger.i("ad view hooks installed: " + installed);
    }

    /**
     * 广告视图 hooker。
     *
     * <p>只在 {@code onAttachedToWindow} 上工作。命中后<b>先放行原方法</b>，再把隐藏动作
     * post 到下一个消息循环 —— 绝不在布局/绘制遍历中摘除视图，那会让宿主的
     * {@code ViewGroup} 子视图索引失效，表现为黑屏或 ANR。</p>
     */
    private static final class AdViewHooker implements XposedInterface.Hooker {

        private final String switchKey;

        AdViewHooker(String switchKey) {
            this.switchKey = switchKey;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            // 先放行宿主原逻辑，确保视图树状态一致后再处理隐藏。
            Object result = chain.proceed();
            try {
                Object self = chain.getThisObject();
                if (self instanceof View view) {
                    Context ctx = view.getContext();
                    if (ctx != null && isBlocked(ctx, switchKey)) {
                        hideAd(view);
                    }
                }
            } catch (Throwable throwable) {
                Logger.w("ad hide failed", throwable);
            }
            return result;
        }
    }

    /**
     * 判断该广告位是否应被屏蔽。
     *
     * <p>语义：<b>该广告位的开关开启</b>，或<b>任一广告开关开启</b>且本视图没有专属开关。
     * 后者保证新版本新增的广告位在用户开启任一广告开关后也能被覆盖。</p>
     */
    private static boolean isBlocked(Context context, String switchKey) {
        ConfigStore store = ConfigStore.from(context);
        if (store.getBoolean(switchKey)) {
            return true;
        }
        if (switchKey == null) {
            for (String key : SettingsKeys.AD_ALL) {
                if (store.getBoolean(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 隐藏广告视图。
     *
     * <p><b>只置 GONE + 压尺寸，不从父容器移除。</b>理由：广告视图常位于列表 item 内部，
     * 直接 {@code removeView} 会让宿主 Adapter 的子视图索引与绑定逻辑失配，
     * 轻则错位、重则崩溃（这类崩溃发生在下一次布局，表现为黑屏）。GONE + 尺寸归零
     * 已经能消除可见内容与占位，代价可接受。</p>
     *
     * <p>必须 post 到主线程：hook 点可能不在主线程，直接操作视图树会抛
     * {@code CalledFromWrongThreadException}。</p>
     */
    private static void hideAd(View view) {
        view.post(() -> {
            try {
                if (!view.isAttachedToWindow()) {
                    return;
                }
                view.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                if (params != null && (params.width != 0 || params.height != 0)) {
                    params.width = 0;
                    params.height = 0;
                    view.setLayoutParams(params);
                }
            } catch (Throwable throwable) {
                Logger.w("ad hide failed", throwable);
            }
        });
    }

    /**
     * 定位真正的广告容器。
     *
     * <p>原实现遍历 {@code getChildCount()} / {@code getChildAt()}：广告 View 常被包在
     * 一层仅含自身的容器里，隐藏外层容器才能消除留白。这里同样向上寻找
     * 「父容器只有这一个子视图」的场景。</p>
     */
    private static View resolveContainer(View view) {
        View current = view;
        for (int depth = 0; depth < 3; depth++) {
            if (!(current.getParent() instanceof ViewGroup parent)) {
                return current;
            }
            if (parent.getChildCount() != 1) {
                return current;
            }
            if (parent instanceof android.widget.FrameLayout
                    || parent.getClass() == ViewGroup.class) {
                current = parent;
                continue;
            }
            return current;
        }
        return current;
    }

    // ================================================================ 适配器层

    private static void installAdapterHooks(ModuleContext context) {
        int installed = 0;
        for (String className : ADAPTER_CLASSES) {
            Class<?> clazz = context.loadHostClass(className);
            if (clazz == null) {
                continue;
            }
            for (String methodName : ADAPTER_METHODS) {
                List<Method> methods = Reflect.findMethods(clazz, methodName);
                for (int i = 0; i < methods.size(); i++) {
                    Method target = methods.get(i);
                    // 只 hook 接收集合参数的方法：无参 getter 改了没意义。
                    Class<?>[] params = target.getParameterTypes();
                    if (params.length != 1 || !List.class.isAssignableFrom(params[0])) {
                        continue;
                    }
                    String id = dev.local.newsoul.framework.HookIds.AD_LOADER_PREFIX
                            + "adapter/" + simpleName(className) + "/" + methodName + "/" + i;
                    if (Hooks.intercept(context.module(), id, target,
                            new AdAdapterHooker(className, methodName))) {
                        installed++;
                    }
                }
            }
        }
        if (installed == 0) {
            Logger.i("no adapter mutation methods; view-level ad blocking only");
        } else {
            Logger.i("ad adapter hooks installed: " + installed);
        }
    }

    /** 从列表数据源剔除广告条目。 */
    private static final class AdAdapterHooker implements XposedInterface.Hooker {

        private final String className;
        private final String methodName;

        AdAdapterHooker(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            try {
                Object first = chain.getArg(0);
                if (first instanceof List<?> list && !list.isEmpty()) {
                    Object receiver = chain.getThisObject();
                    Context ctx = receiver instanceof View ? ((View) receiver).getContext() : null;
                    if (ctx == null) {
                        // 适配器本身不是 View，退化为"任一广告开关开启即过滤"
                        ctx = globalContext();
                    }
                    if (ctx != null && anyAdSwitchOn(ctx)) {
                        List<Object> filtered = filterAds(list);
                        if (filtered.size() != list.size()) {
                            Logger.i("ad entries removed: " + className + "." + methodName
                                    + "; " + list.size() + " -> " + filtered.size());
                            return chain.proceed(new Object[]{filtered});
                        }
                    }
                }
            } catch (Throwable throwable) {
                Logger.w("ad adapter filter failed: " + methodName, throwable);
            }
            return chain.proceed();
        }
    }

    private static boolean anyAdSwitchOn(Context context) {
        ConfigStore store = ConfigStore.from(context);
        for (String key : SettingsKeys.AD_ALL) {
            if (store.getBoolean(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤列表中的广告条目。
     *
     * <p>判定方式（按可靠性排序）：</p>
     * <ol>
     *   <li>元素的类名含 {@code .ad.} 或以 {@code Ad} 开头 —— 最可靠</li>
     *   <li>元素持有 {@code isAd} / {@code adType} / {@code adResponse} 字段且为真</li>
     *   <li>元素实现 {@code getAdResponse()} 且返回非 null</li>
     * </ol>
     * <p>全部失败时保留元素 —— 宁可漏杀不可误杀。</p>
     */
    private static List<Object> filterAds(List<?> source) {
        List<Object> result = new ArrayList<>(source.size());
        for (Object item : source) {
            if (item == null) {
                continue;
            }
            if (!isAdItem(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /** 判断列表元素是否为广告条目。 */
    public static boolean isAdItem(Object item) {
        if (item == null) {
            return false;
        }
        try {
            String className = item.getClass().getName();
            String simple = item.getClass().getSimpleName();
            if (className.contains(".ad.") || simple.startsWith("Ad") || simple.endsWith("AdBean")) {
                return true;
            }
            // 布尔标记字段
            for (String fieldName : new String[]{"isAd", "isAdvertisement", "ad", "isAdItem"}) {
                java.lang.reflect.Field field = Reflect.findField(item.getClass(), fieldName);
                if (field != null && field.getType() == boolean.class) {
                    if (Reflect.getBoolean(item, field, false)) {
                        return true;
                    }
                }
            }
            // getAdResponse() 非空即广告（原实现用于 SquareFloatingButton 的判定）
            Method getAdResponse = Reflect.findAnyMethod(item.getClass(), "getAdResponse");
            if (getAdResponse != null && getAdResponse.getParameterTypes().length == 0) {
                Object response = Reflect.invoke(item, getAdResponse);
                if (response != null) {
                    return true;
                }
            }
        } catch (Throwable ignored) {
            // 判定失败按非广告处理
        }
        return false;
    }

    // ================================================================ 请求层

    private static void installRequestHooks(ModuleContext context) {
        Class<?> listener = context.loadHostClass(HostClasses.AD_SOUL_AD_REQUEST_LISTENER);
        if (listener == null) {
            return;
        }
        int installed = 0;
        for (String methodName : new String[]{"loadAds", "onAdFailed", "onAdLoaded"}) {
            Method target = Reflect.findAnyMethod(listener, methodName);
            if (target == null) {
                continue;
            }
            String id = dev.local.newsoul.framework.HookIds.AD_LOADER_PREFIX
                    + "request/" + methodName;
            if (Hooks.intercept(context.module(), id, target, new AdRequestHooker(methodName))) {
                installed++;
            }
        }
        Logger.i("ad request hooks installed: " + installed);
    }

    /**
     * 广告请求拦截。
     *
     * <p>{@code loadAds}：短路返回，不发请求。
     * 其它方法：放行，避免破坏宿主回调契约。</p>
     */
    private static final class AdRequestHooker implements XposedInterface.Hooker {

        private final String methodName;

        AdRequestHooker(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            if (!"loadAds".equals(methodName)) {
                return chain.proceed();
            }
            try {
                Context ctx = globalContext();
                if (ctx != null && (anyAdSwitchOn(ctx)
                        || ConfigStore.from(ctx).getBoolean(SettingsKeys.AD_FLOAT)
                        || ConfigStore.from(ctx).getBoolean(SettingsKeys.AD_INTERSTITIAL))) {
                    Logger.i("ad request blocked: loadAds");
                    Class<?> ret = chain.getClass();
                    // 返回类型为 void 时返回 null 即可；否则交由宿主回调处理失败
                    return null;
                }
            } catch (Throwable throwable) {
                Logger.w("ad request block failed", throwable);
            }
            return chain.proceed();
        }
    }

    // ================================================================ 工具

    private static String simpleName(String className) {
        int index = className.lastIndexOf('.');
        return index >= 0 ? className.substring(index + 1) : className;
    }
}
