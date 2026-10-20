package dev.local.newsoul.feature.profile;

import android.app.Activity;
import android.os.Handler;

/**
 * 切换插眼任务（原 {@code H} case 6，后台线程执行）。
 *
 * <p>在单线程执行器上运行：校验账号一致 → 打开存储 → 按会话当前 {@code bookmarked}
 * 决定删除还是新增/更新记录（新增用会话里的昵称快照），结果经主线程 Handler 回投递到
 * {@link ProfileBookmarkToggleResult}；异常则回投递
 * {@link ProfileBookmarkOperationFailure}。</p>
 */
final class ProfileBookmarkToggleTask implements Runnable {

    private final ProfileBookmarkSession session;
    private final Activity activity;
    private final Object host;

    ProfileBookmarkToggleTask(ProfileBookmarkSession session, Activity activity, Object host) {
        this.session = session;
        this.activity = activity;
        this.host = host;
    }

    @Override
    public void run() {
        Handler handler = ProfileBookmarkActions.MAIN_HANDLER;
        try {
            ProfileBookmarkActions.requireAccount(session.account);
            ProfileBookmarkStore store =
                    new ProfileBookmarkStore(activity.getFilesDir(), session.account);
            boolean bookmarked = session.bookmarked;
            String userId = session.userId;
            if (bookmarked) {
                store.remove(userId);
            } else {
                store.addOrUpdate(userId, session.displayName);
            }
            handler.post(new ProfileBookmarkToggleResult(session, activity, bookmarked, host));
        } catch (Exception error) {
            handler.post(new ProfileBookmarkOperationFailure(session, activity, error));
        }
    }
}
