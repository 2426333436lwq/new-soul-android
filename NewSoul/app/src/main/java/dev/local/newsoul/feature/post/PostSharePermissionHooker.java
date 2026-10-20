package dev.local.newsoul.feature.post;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.Prefs;
import io.github.libxposed.api.XposedInterface;

/**
 * 帖子分享权限 hooker（原 {@code k1} case 0，label {@code pswitch_88}）。
 *
 * <p>挂在 {@code SeedsShareDialogFragment.r0()} 上：分享页帖子字段 {@code E} 非空且
 * {@link SettingsKeys#POST_REMOVE_SHARE_LIMIT} 开关开启时，直接拦截（返回 {@code null}），
 * 使宿主不再施加分享限制，并记录 {@code "post share restriction bypassed"}；否则原样放行。</p>
 *
 * <p>开关读取的默认值为 {@code true}（原 {@code Q0.q} 的 {@code getBoolean(key, true)}），
 * 上下文经宿主全局上下文提供者取得，不可用时不改写。</p>
 */
public final class PostSharePermissionHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_BYPASSED = "post share restriction bypassed";

    private final Field sharePostField;
    private final GlobalContextProvider globalContext;

    public PostSharePermissionHooker(Field sharePostField, GlobalContextProvider globalContext) {
        this.sharePostField = sharePostField;
        this.globalContext = globalContext;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object sharePost = sharePostField.get(chain.getThisObject());
        if (sharePost != null && isRemoveLimitEnabled()) {
            Log.i(LOG_TAG, LOG_BYPASSED);
            return null;
        }
        return chain.proceed();
    }

    /** 读取 {@code post_remove_share_limit} 开关；上下文不可用时返回 {@code false}。 */
    private boolean isRemoveLimitEnabled() {
        Context context = globalContext == null ? null : globalContext.get();
        return context != null
                && Prefs.getBoolean(context, SettingsKeys.POST_REMOVE_SHARE_LIMIT, true);
    }
}
