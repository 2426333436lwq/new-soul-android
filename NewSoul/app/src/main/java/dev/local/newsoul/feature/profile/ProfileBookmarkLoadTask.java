package dev.local.newsoul.feature.profile;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * 插眼列表加载任务（原 {@code m1} case 0，后台线程执行）。
 *
 * <p>在单线程执行器上运行：校验账号一致 → 打开存储 → 在 {@link ProfileBookmarkStore#LOCK}
 * 保护下读取列表并判断目标用户是否已插眼，结果经主线程 Handler 回投递到
 * {@link ProfileBookmarkLoadResult}；异常则回投递 {@link ProfileBookmarkLoadFailure}。</p>
 */
final class ProfileBookmarkLoadTask implements Runnable {

    private final ProfileBookmarkSession session;
    private final Activity activity;
    private final Object host;
    private final Field titleField;
    private final Field adapterField;

    ProfileBookmarkLoadTask(ProfileBookmarkSession session, Activity activity, Object host,
                            Field titleField, Field adapterField) {
        this.session = session;
        this.activity = activity;
        this.host = host;
        this.titleField = titleField;
        this.adapterField = adapterField;
    }

    @Override
    public void run() {
        try {
            ProfileBookmarkActions.requireAccount(session.account);
            ProfileBookmarkStore store =
                    new ProfileBookmarkStore(activity.getFilesDir(), session.account);
            String userId = session.userId;
            boolean found;
            synchronized (ProfileBookmarkStore.LOCK) {
                found = false;
                for (ProfileBookmarkEntry entry : store.readAll()) {
                    if (entry.key.equals(userId)) {
                        found = true;
                        break;
                    }
                }
            }
            ProfileBookmarkActions.MAIN_HANDLER.post(new ProfileBookmarkLoadResult(
                    activity, session, host, found, titleField, adapterField));
        } catch (Exception error) {
            ProfileBookmarkActions.MAIN_HANDLER.post(new ProfileBookmarkLoadFailure(
                    session, activity, host, titleField, adapterField, error));
        }
    }
}
