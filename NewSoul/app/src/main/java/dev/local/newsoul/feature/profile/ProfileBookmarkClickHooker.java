package dev.local.newsoul.feature.profile;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

import io.github.libxposed.api.XposedInterface;

/**
 * 资料页书签点击 hooker（原 {@code k1} default 分支）。
 *
 * <p>挂在分享面板的 {@code P0(Operate, View, int)} 上：第 0 个实参的操作项标识若不是插眼标识
 * {@code 0x4e5310}，直接放行；命中时拦截（返回 {@code Boolean.TRUE}，不放行原点击逻辑）并按会话状态分流：</p>
 * <ul>
 *   <li>会话或 Activity 缺失 → 静默拦截；</li>
 *   <li>列表尚未加载 → 按 {@code loadFailed} 提示 {@code "读取插眼列表失败，请重新打开菜单"}
 *       或 {@code "插眼列表尚未加载，请稍后重试"};</li>
 *   <li>切换进行中 → 静默拦截；</li>
 *   <li>否则先校验账号一致，置进行中标记，提交 {@link ProfileBookmarkToggleTask}；</li>
 *   <li>异常仅以 {@code "profile bookmark click failed"} 降级，并尝试向 Activity 提示失败。</li>
 * </ul>
 */
public final class ProfileBookmarkClickHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_FAILED = "profile bookmark click failed";

    /** 插眼操作项标识（原 {@code 0x4e5310}）。 */
    private static final int BOOKMARK_TYPE = 0x4e5310;

    private final Field operateIdField;

    public ProfileBookmarkClickHooker(Field operateIdField) {
        this.operateIdField = operateIdField;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object menuItem = chain.getArg(0);
        if (operateIdField.getInt(menuItem) != BOOKMARK_TYPE) {
            return chain.proceed();
        }
        try {
            Object host = chain.getThisObject();
            ProfileBookmarkSession session = ProfileBookmarkActions.SESSIONS.get(host);
            Activity activity =
                    (Activity) ProfileBookmarkActions.invokeAccessor(host, "getActivity");
            if (session == null || activity == null) {
                return Boolean.TRUE;
            }
            if (!session.loaded) {
                String message = session.loadFailed
                        ? "读取插眼列表失败，请重新打开菜单"
                        : "插眼列表尚未加载，请稍后重试";
                if (ProfileBookmarkActions.isActivityAlive(activity)) {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
                return Boolean.TRUE;
            }
            if (session.inProgress) {
                return Boolean.TRUE;
            }
            ProfileBookmarkActions.requireAccount(session.account);
            session.inProgress = true;
            ProfileBookmarkActions.EXECUTOR.execute(
                    new ProfileBookmarkToggleTask(session, activity, host));
            return Boolean.TRUE;
        } catch (Exception error) {
            Log.w(LOG_TAG, LOG_FAILED, error);
            try {
                Activity activity = (Activity) ProfileBookmarkActions
                        .invokeAccessor(chain.getThisObject(), "getActivity");
                ProfileBookmarkActions.handleFailure(activity, error);
            } catch (Exception ignored) {
                // 二次获取 Activity 失败时静默忽略
            }
            return Boolean.TRUE;
        }
    }
}
