package dev.local.newsoul.feature.profile;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import io.github.libxposed.api.XposedModule;

/**
 * 资料页书签共享编排与本域安装器（原抽象类 {@code u1}）。
 *
 * <p>集中承载：主线程 Handler、单线程执行器、会话登记表、账号提供者方法，以及安装器本体。
 * 原 {@code u1} 另有「对话框菜单项工厂」与「对话框存活判断」两个辅助方法，
 * 它们只服务于设置域内的插眼列表管理对话框（不在本域范围内），故未随本域复刻。</p>
 */
final class ProfileBookmarkActions {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    /** 主线程 Handler（原 {@code u1.a}）。 */
    static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    /** 单线程执行器（原 {@code u1.b}）。 */
    static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    /** Fragment 实例 → 会话状态（原 {@code u1.c}）。 */
    static final WeakHashMap<Object, ProfileBookmarkSession> SESSIONS = new WeakHashMap<>();

    /** 当前登录账号提供者（原 {@code u1.d} 指向的静态方法）。 */
    private static Method accountProvider;

    /** 主页用户方法（原 {@code u1.e}，宿主加载后未被调用，按原样保留）。 */
    @SuppressWarnings("unused")
    private static Method homeProfileMethod;

    /** 是否已安装成功（原 {@code u1.f}）。 */
    static volatile boolean installed;

    private ProfileBookmarkActions() {
    }

    /** 宿主 Activity 是否仍可交互（原 {@code u1.b}）。 */
    static boolean isActivityAlive(Activity activity) {
        return activity != null && !activity.isFinishing() && !activity.isDestroyed();
    }

    /** 反射调用宿主公开无参方法（原 {@code u1.c}），失败时抛出。 */
    static Object invokeAccessor(Object target, String name) throws Exception {
        Method method = target.getClass().getMethod(name, (Class<?>[]) null);
        return method.invoke(target, (Object[]) null);
    }

    /** 取当前登录账号（原 {@code u1.d}），不可用时抛 {@code IllegalStateException("请先登录 Soul")}。 */
    static String currentAccount() throws Exception {
        Method provider = accountProvider;
        String value = provider == null ? null : (String) provider.invoke(null, (Object[]) null);
        if (value != null && !value.trim().isEmpty()) {
            return value;
        }
        throw new IllegalStateException("请先登录 Soul");
    }

    /** 统一的失败提示（原 {@code u1.e}）：先写日志，再按异常类型决定 Toast 文案。 */
    static void handleFailure(Activity activity, Exception error) {
        Log.w(LOG_TAG, "profile bookmark operation failed", error);
        String message;
        if (error instanceof IllegalStateException || error instanceof IllegalArgumentException) {
            message = error.getMessage();
        } else {
            message = "操作失败，原有记录已保留";
        }
        if (isActivityAlive(activity)) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }

    /** 沿父类链查找字段（原 {@code u1.f}），找不到抛 {@code NoSuchFieldException(name)}。 */
    static Field requireField(Class<?> clazz, String name) throws NoSuchFieldException {
        Class<?> current = clazz;
        while (current != null) {
            try {
                Field field = current.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchFieldException(name);
    }

    /** 校验会话快照账号与当前账号一致（原 {@code u1.h}），否则抛 {@code IllegalStateException}。 */
    static void requireAccount(String expected) throws Exception {
        if (expected.equals(currentAccount())) {
            return;
        }
        throw new IllegalStateException("账号已切换，请重新打开页面");
    }

    /**
     * 安装资料页书签（原 {@code u1.g}）。
     *
     * <p>严格按原顺序：加载账号提供者与主页方法 → 解析分享面板与操作项字段 →
     * 安装图标 hook 并去优化适配器 → 安装点击 hook → 安装菜单 hook 并去优化 →
     * 去优化 {@code cn.soulapp.android.square.a0} 全部方法 → 全部成功才置 {@link #installed}。</p>
     *
     * <p>任一步失败均落入统一兜底：记录 {@code "profile.bookmarks"} 与
     * {@code "profile bookmarks unavailable"}，不向 {@code installAll} 抛出。</p>
     */
    static void install(XposedModule module, ClassLoader loader) {
        try {
            Class<?> sessionProvider = loader.loadClass(HostClasses.IMLIB_SESSION_PROVIDER);
            accountProvider = sessionProvider.getMethod("g", (Class<?>[]) null);

            Class<?> homeActivity = loader.loadClass(HostClasses.HOME_USER_HOME_ACTIVITY);
            homeProfileMethod = homeActivity.getDeclaredMethod("y", String.class, String.class);

            Class<?> seedsFragment = loader.loadClass(HostClasses.SQUARE_SEEDS_SHARE_DIALOG_FRAGMENT);
            Class<?> baseSeedsFragment = loader.loadClass(HostClasses.SQUARE_BASE_SEEDS_DIALOG_FRAGMENT);
            Class<?> operateClass = loader.loadClass(baseSeedsFragment.getName() + "$Operate");
            Constructor<?> operateConstructor =
                    operateClass.getConstructor(int.class, String.class, boolean.class);

            Field seedsPostField = requireField(seedsFragment, "D");
            Field seedsTypeField = requireField(seedsFragment, "E");
            Field seedsModeField = requireField(seedsFragment, "C");
            Field baseItemsField = requireField(baseSeedsFragment, "q");
            Field operateIdField = requireField(operateClass, "a");
            Field operateTitleField = requireField(operateClass, "b");
            Field operateIconField = requireField(operateClass, "f");
            Field seedsAdapterField = requireField(seedsFragment, "y");

            Method initViews = seedsFragment.getDeclaredMethod("initViews", View.class);
            Method clickHandler =
                    seedsFragment.getDeclaredMethod("P0", operateClass, View.class, int.class);

            Class<?> typeAdapterInner = loader.loadClass(HostClasses.SQUARE_TYPE_ADAPTER_INNER);
            Field imageViewField = requireField(typeAdapterInner, "c");
            Field adapterTypeField = requireField(typeAdapterInner, "a");
            Method bindImage = typeAdapterInner.getDeclaredMethod("a", operateClass);

            if (!Hooks.intercept(module, HookIds.PROFILE_BOOKMARK_ICON, bindImage,
                    new ProfileBookmarkIconHooker(imageViewField, operateIdField, adapterTypeField))) {
                throw new IllegalStateException("profile bookmark icon hook failed");
            }
            Hooks.deoptimize(module, HookIds.PROFILE_BOOKMARK_ICON, bindImage);
            for (Method method : loader.loadClass(HostClasses.SQUARE_TYPE_ADAPTER).getDeclaredMethods()) {
                Hooks.deoptimize(module, HookIds.PROFILE_BOOKMARK_ICON, method);
            }

            if (!Hooks.intercept(module, HookIds.PROFILE_BOOKMARK_CLICK, clickHandler,
                    new ProfileBookmarkClickHooker(operateIdField))) {
                throw new IllegalStateException("profile bookmark click hook failed");
            }
            if (!Hooks.intercept(module, HookIds.PROFILE_BOOKMARK_MENU, initViews,
                    new ProfileBookmarkMenuHooker(seedsPostField, seedsTypeField, seedsModeField,
                            operateConstructor, operateIdField, operateIconField, baseItemsField,
                            operateTitleField, seedsAdapterField))) {
                throw new IllegalStateException("profile bookmark menu hook failed");
            }
            Hooks.deoptimize(module, HookIds.PROFILE_BOOKMARK_MENU, initViews);
            Hooks.deoptimize(module, HookIds.PROFILE_BOOKMARK_CLICK, clickHandler);
            for (Method method : loader.loadClass(HostClasses.SQUARE_A0).getDeclaredMethods()) {
                Hooks.deoptimize(module, HookIds.PROFILE_BOOKMARK_MENU, method);
            }

            installed = true;
            Logger.i("profile.bookmarks");
            Log.i(LOG_TAG, "profile bookmark menu and navigation installed");
        } catch (Throwable throwable) {
            Logger.i("profile.bookmarks");
            Log.e(LOG_TAG, "profile bookmarks unavailable", throwable);
        }
    }
}
