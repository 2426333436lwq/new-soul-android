package dev.local.newsoul.feature.profile;

import android.app.Activity;

/**
 * 切换插眼失败回填（原 {@code H} case 7，主线程执行）。
 *
 * <p>清除进行中标记，并统一调用 {@link ProfileBookmarkActions#handleFailure} 提示失败原因。</p>
 */
final class ProfileBookmarkOperationFailure implements Runnable {

    private final ProfileBookmarkSession session;
    private final Activity activity;
    private final Exception error;

    ProfileBookmarkOperationFailure(ProfileBookmarkSession session, Activity activity,
                                    Exception error) {
        this.session = session;
        this.activity = activity;
        this.error = error;
    }

    @Override
    public void run() {
        session.inProgress = false;
        ProfileBookmarkActions.handleFailure(activity, error);
    }
}
