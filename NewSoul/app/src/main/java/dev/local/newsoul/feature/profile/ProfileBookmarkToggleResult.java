package dev.local.newsoul.feature.profile;

import android.app.Activity;
import android.widget.Toast;

/**
 * 切换插眼结果回填（原 {@code q} case 1，主线程执行）。
 *
 * <p>清除进行中标记；在 Activity 存活且账号仍一致时，按切换前状态提示
 * {@code "已取消插眼"} 或 {@code "已插眼"}，随后关闭分享面板
 * （{@code dismissAllowingStateLoss}，异常静默忽略）。</p>
 */
final class ProfileBookmarkToggleResult implements Runnable {

    private final ProfileBookmarkSession session;
    private final Activity activity;
    private final boolean bookmarked;
    private final Object host;

    ProfileBookmarkToggleResult(ProfileBookmarkSession session, Activity activity,
                                boolean bookmarked, Object host) {
        this.session = session;
        this.activity = activity;
        this.bookmarked = bookmarked;
        this.host = host;
    }

    @Override
    public void run() {
        session.inProgress = false;
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
        String message = bookmarked ? "已取消插眼" : "已插眼";
        if (ProfileBookmarkActions.isActivityAlive(activity)) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
        try {
            ProfileBookmarkActions.invokeAccessor(host, "dismissAllowingStateLoss");
        } catch (Exception ignored) {
            // 面板关闭失败时静默忽略
        }
    }
}
