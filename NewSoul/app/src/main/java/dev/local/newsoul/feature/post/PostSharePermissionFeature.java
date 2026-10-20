package dev.local.newsoul.feature.post;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 帖子分享权限功能域（原 {@code Q0.C} 分享权限安装）。
 *
 * <p>宿主分享面板通过私有方法 {@code SeedsShareDialogFragment.r0()} 施加分享限制。本功能域对该
 * 方法安装 {@code new-soul/post-share-permission}（{@link PostSharePermissionHooker}），
 * 并按开关决定是否拦截；随后对 {@code initViews(View)} 去优化。</p>
 *
 * <p>安装前校验签名，宿主变化即优雅降级：</p>
 * <ul>
 *   <li>帖子字段 {@code E} 的类型必须<b>严格等于</b> {@code Post}，否则
 *       {@code NoSuchFieldException("Unexpected share post field")}；</li>
 *   <li>{@code r0()} 必须无参、返回 {@code void} 且为 {@code private}，否则
 *       {@code NoSuchMethodException("Unexpected share permission signature")}；</li>
 *   <li>失败统一记录 {@code "post share control unavailable"}。</li>
 * </ul>
 */
public final class PostSharePermissionFeature implements FeatureInstaller {

    private static final String ID = "post.share-permission";

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        try {
            ClassLoader loader = context.classLoader();
            Class<?> shareFragment = loader.loadClass(HostClasses.SQUARE_SEEDS_SHARE_DIALOG_FRAGMENT);
            Class<?> postClass = loader.loadClass(HostClasses.SQUARE_POST_BEAN);

            Field sharePostField = shareFragment.getDeclaredField("E");
            if (sharePostField.getType() != postClass) {
                throw new NoSuchFieldException("Unexpected share post field");
            }
            sharePostField.setAccessible(true);

            Method permission = shareFragment.getDeclaredMethod("r0", (Class<?>[]) null);
            Method initViews = shareFragment.getDeclaredMethod("initViews", View.class);
            if (permission.getReturnType() != void.class
                    || !Modifier.isPrivate(permission.getModifiers())) {
                throw new NoSuchMethodException("Unexpected share permission signature");
            }

            GlobalContextProvider globalContext = GlobalContextProvider.resolve(context);
            if (!Hooks.intercept(context.module(), HookIds.POST_SHARE_PERMISSION, permission,
                    new PostSharePermissionHooker(sharePostField, globalContext))) {
                throw new IllegalStateException("post share permission hook failed");
            }
            Hooks.deoptimize(context.module(), HookIds.POST_SHARE_PERMISSION, initViews);

            Log.i(LOG_TAG, "post share control installed");
        } catch (Throwable throwable) {
            Logger.e("post share control unavailable", throwable);
        }
    }
}
