package dev.local.newsoul.ui;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 视图回收监听（原 {@code D2} 的索引 1 分支）。
 *
 * <p>视图挂接窗口时刷新其背景 drawable；脱离窗口时释放它。</p>
 */
final class DrawableAttachmentListener implements View.OnAttachStateChangeListener {

    @Override
    public void onViewAttachedToWindow(View view) {
        Drawable drawable = SurfaceStyle.rememberedDrawable(view);
        if (drawable != null) {
            drawable.invalidateSelf();
        }
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        SurfaceStyle.releaseDrawable(SurfaceStyle.rememberedDrawable(view));
    }
}
