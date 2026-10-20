package dev.local.newsoul.feature.profile;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * 插眼列表加载失败回填（原 {@code o1} case 0，主线程执行）。
 *
 * <p>标记 {@code loadFailed}；在 Activity 存活且会话仍在登记表时，把菜单项标题改为
 * {@code "插眼不可用"} 并通知适配器刷新（此处异常静默吞掉）；最后统一调用
 * {@link ProfileBookmarkActions#handleFailure} 提示失败原因。</p>
 */
final class ProfileBookmarkLoadFailure implements Runnable {

    private final ProfileBookmarkSession session;
    private final Activity activity;
    private final Object host;
    private final Field titleField;
    private final Field adapterField;
    private final Exception error;

    ProfileBookmarkLoadFailure(ProfileBookmarkSession session, Activity activity, Object host,
                              Field titleField, Field adapterField, Exception error) {
        this.session = session;
        this.activity = activity;
        this.host = host;
        this.titleField = titleField;
        this.adapterField = adapterField;
        this.error = error;
    }

    @Override
    public void run() {
        session.loadFailed = true;
        if (!ProfileBookmarkActions.isActivityAlive(activity)) {
            return;
        }
        if (ProfileBookmarkActions.SESSIONS.get(host) != session) {
            return;
        }
        try {
            titleField.set(session.menuItem, "插眼不可用");
            Object adapter = adapterField.get(host);
            if (adapter != null) {
                ProfileBookmarkActions.invokeAccessor(adapter, "notifyDataSetChanged");
            }
        } catch (Exception ignored) {
            // 标题回填失败时静默忽略
        }
        ProfileBookmarkActions.handleFailure(activity, error);
    }
}
