package dev.local.newsoul.feature.privacy;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ActionMarkBadge;
import dev.local.newsoul.ui.SurfaceStyle;
import dev.local.newsoul.ui.ViewKit;

/**
 * 隐私保存按钮注入任务（原 {@code U} 的 {@code run()} default 分支）。
 *
 * <p>在宿主 decor 视图上注入一个 {@code 保存原图 / 保存视频} 按钮：已存在同 tag 按钮时仅前置并
 * 显示，否则新建、套用模块圆角样式、加「新内容」角标并绑定点击监听。</p>
 *
 * <p>注意：原 {@code U} 的另一条分支（诊断子系统记录）属于作者专属部分，此处不实现。</p>
 */
public final class PrivacySaveButtonTask implements Runnable {

    /** 按钮视图 tag（逐字保留）。 */
    private static final String VIEW_TAG = "new.soul.privacy.photo.download";
    private static final String LABEL_PHOTO = "保存原图";
    private static final String LABEL_VIDEO = "保存视频";
    /** 悬浮高度 dp（原 {@code 0x20}）。 */
    private static final int ELEVATION_DP = 0x20;
    /** 水平内边距 dp（原 {@code 0x10}）。 */
    private static final int PADDING_DP = 0x10;
    /** 按钮背景类型（原 {@code 0xb}）。 */
    private static final int SURFACE_TYPE = 0x0B;
    /** 按钮圆角半径（原 {@code 22.0f}）。 */
    private static final float SURFACE_RADIUS = 22.0f;
    /** 按钮高度 dp（原 {@code 0x2c}）。 */
    private static final int HEIGHT_DP = 0x2C;
    /** 布局重力（原 {@code 0x800055}）。 */
    private static final int GRAVITY = 0x800055;
    private static final int RIGHT_MARGIN_DP = 0x14;
    private static final int BOTTOM_MARGIN_DP = 0x4C;
    private static final float TEXT_SIZE = 14.0f;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private final Activity activity;
    private final boolean savePhoto;
    private final ClassLoader hostClassLoader;

    public PrivacySaveButtonTask(Activity activity, boolean savePhoto, ClassLoader hostClassLoader) {
        this.activity = activity;
        this.savePhoto = savePhoto;
        this.hostClassLoader = hostClassLoader;
    }

    @Override
    public void run() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        View decorView = activity.getWindow().getDecorView();
        if (!(decorView instanceof ViewGroup)) {
            return;
        }
        ViewGroup decor = (ViewGroup) decorView;

        View existing = decor.findViewWithTag(VIEW_TAG);
        if (existing != null) {
            existing.bringToFront();
            existing.setElevation(dp(ELEVATION_DP));
            existing.setVisibility(View.VISIBLE);
            return;
        }

        TextView button = new TextView(activity);
        button.setTag(VIEW_TAG);
        String label = savePhoto ? LABEL_PHOTO : LABEL_VIDEO;
        button.setContentDescription(label);
        button.setText(label);
        button.setTextColor(TEXT_COLOR);
        button.setTextSize(TEXT_SIZE);
        button.setGravity(Gravity.CENTER);
        int padding = dp(PADDING_DP);
        button.setPadding(padding, 0, padding, 0);

        SurfaceStyle surface =
                new SurfaceStyle(true, activity.getResources().getDisplayMetrics().density);
        surface.apply(button, SURFACE_TYPE, SURFACE_RADIUS);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, dp(HEIGHT_DP), GRAVITY);
        params.rightMargin = dp(RIGHT_MARGIN_DP);
        params.bottomMargin = dp(BOTTOM_MARGIN_DP);
        activity.addContentView(button, params);

        ActionMarkBadge.apply(button, true);
        button.setElevation(dp(ELEVATION_DP));
        button.bringToFront();
        button.setOnClickListener(
                new PrivacySaveClickListener(savePhoto, activity, button, hostClassLoader));

        Logger.i("privacy save action attached: " + (savePhoto ? "photo" : "video"));
    }

    private int dp(int value) {
        return ViewKit.dp(activity, value);
    }
}
