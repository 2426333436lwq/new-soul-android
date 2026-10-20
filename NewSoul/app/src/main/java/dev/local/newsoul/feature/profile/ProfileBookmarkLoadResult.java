package dev.local.newsoul.feature.profile;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * 插眼列表加载成功回填（原 {@code n1} case 0，主线程执行）。
 *
 * <p>在 Activity 存活、账号仍一致、会话仍在登记表中的前提下：写回
 * {@code bookmarked} 与 {@code loaded}，把菜单项标题改为 {@code "取消插眼"} 或 {@code "插眼"}，
 * 并通知适配器刷新。异常经 {@link ProfileBookmarkActions#handleFailure} 提示。</p>
 */
final class ProfileBookmarkLoadResult implements Runnable {

    private final Activity activity;
    private final ProfileBookmarkSession session;
    private final Object host;
    private final boolean bookmarked;
    private final Field titleField;
    private final Field adapterField;

    ProfileBookmarkLoadResult(Activity activity, ProfileBookmarkSession session, Object host,
                              boolean bookmarked, Field titleField, Field adapterField) {
        this.activity = activity;
        this.session = session;
        this.host = host;
        this.bookmarked = bookmarked;
        this.titleField = titleField;
        this.adapterField = adapterField;
    }

    @Override
    public void run() {
        if (!ProfileBookmarkActions.isActivityAlive(activity)) {
            return;
        }
        boolean sameAccount;
        try {
            sameAccount = session.account.equals(ProfileBookmarkActions.currentAccount());
        } catch (Exception error) {
            sameAccount = false;
        }
        if (!sameAccount) {
            return;
        }
        if (ProfileBookmarkActions.SESSIONS.get(host) != session) {
            return;
        }
        try {
            session.bookmarked = bookmarked;
            session.loaded = true;
            titleField.set(session.menuItem, bookmarked ? "取消插眼" : "插眼");
            Object adapter = adapterField.get(host);
            if (adapter != null) {
                ProfileBookmarkActions.invokeAccessor(adapter, "notifyDataSetChanged");
            }
        } catch (Exception error) {
            ProfileBookmarkActions.handleFailure(activity, error);
        }
    }
}
