package dev.local.newsoul.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.WeakHashMap;

/**
 * 可复用的「新内容」角标（原抽象类 {@code j1} 及其内部 Drawable {@code i1}、布局监听 {@code h1}）。
 *
 * <p>角标以 {@link View#getOverlay()} 覆盖层的形式挂在目标视图右上角，不改变原有布局层级。
 * 每个视图的角标实例缓存在 {@link WeakHashMap} 中，重复调用只做增删切换，避免重复创建。</p>
 *
 * <p>绘制参数与原实现逐字一致：菱形大小取 {@code min(10dp, min(宽, 高) * 0.26)}，中心位于
 * 右上角内缩 {@code (0.65 * size + 1dp)}，四段 {@code quadTo} 的控制点偏移为半宽的 {@code 0.17}；
 * 先以 {@code 0.5dp} 描边（{@code -0x1f0008}），再以 {@code -0xc7203c} 填充。</p>
 */
public final class ActionMarkBadge {

    /** 描边色（原 {@code -0x1f0008}）。 */
    private static final int COLOR_STROKE = 0xFFE0FFF8;
    /** 填充色（原 {@code -0xc7203c}）。 */
    private static final int COLOR_FILL = 0xFF38DFC4;

    /** 精度不足以致不可见的返回码（原 {@code -0x3}）。 */
    private static final int OPACITY_TRANSPARENT = -3;

    /** 单视图角标缓存（原静态字段 {@code j1.a}）。 */
    private static final WeakHashMap<View, BadgeDrawable> BADGES = new WeakHashMap<>();

    private ActionMarkBadge() {
    }

    /** 深度优先查找视图树中首个 {@link ImageView}（原 {@code j1.a}）；找不到返回 {@code null}。 */
    public static ImageView findImageView(View view) {
        if (view instanceof ImageView) {
            return (ImageView) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                ImageView found = findImageView(group.getChildAt(index));
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /**
     * 显示或隐藏角标（原 {@code j1.b}）。
     *
     * <p>{@code show} 为 {@code false} 时移除已存在的角标；为 {@code true} 时复用已有实例或新建并
     * 挂接布局监听，保证角标随视图尺寸变化同步 {@code setBounds}。</p>
     */
    public static void apply(View view, boolean show) {
        if (view == null) {
            return;
        }
        BadgeDrawable badge = BADGES.get(view);
        if (!show) {
            if (badge != null) {
                view.getOverlay().remove(badge);
                badge.visible = false;
            }
            return;
        }
        if (badge != null) {
            if (!badge.visible) {
                view.getOverlay().add(badge);
                badge.visible = true;
            }
            return;
        }
        BadgeDrawable created = new BadgeDrawable(view.getResources().getDisplayMetrics().density);
        BADGES.put(view, created);
        view.getOverlay().add(created);
        created.setBounds(0, 0, view.getWidth(), view.getHeight());
        view.addOnLayoutChangeListener(new BadgeLayoutListener());
        view.invalidate();
    }

    /** 视图尺寸变化时同步角标边界（原 {@code h1}）。 */
    private static final class BadgeLayoutListener implements View.OnLayoutChangeListener {

        @Override
        public void onLayoutChange(View view, int left, int top, int right, int bottom,
                int oldLeft, int oldTop, int oldRight, int oldBottom) {
            BadgeDrawable badge = BADGES.get(view);
            if (badge != null) {
                badge.setBounds(0, 0, right - left, bottom - top);
            }
        }
    }

    /** 角标绘制实现（原 {@code i1}）。 */
    private static final class BadgeDrawable extends Drawable {

        private final Paint paint;
        private final float density;
        private int alpha = 0xFF;
        private boolean visible = true;

        BadgeDrawable(float density) {
            this.density = density;
            this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            float size = Math.min(10.0f * density,
                    Math.min(bounds.width(), bounds.height()) * 0.26f);
            if (size <= 0f) {
                return;
            }
            float centerX = bounds.right - 0.65f * size - density;
            float centerY = bounds.top + 0.65f * size + density;
            float half = size / 2.0f;
            float control = 0.17f * half;

            Path path = new Path();
            path.moveTo(centerX, centerY - half);
            path.quadTo(centerX + control, centerY - control, centerX + half, centerY);
            path.quadTo(centerX + control, centerY + control, centerX, centerY + half);
            path.quadTo(centerX - control, centerY + control, centerX - half, centerY);
            path.quadTo(centerX - control, centerY - control, centerX, centerY - half);
            path.close();

            paint.setAlpha(alpha);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(density * 0.5f);
            paint.setColor(COLOR_STROKE);
            canvas.drawPath(path, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(COLOR_FILL);
            canvas.drawPath(path, paint);
        }

        @Override
        public int getOpacity() {
            return OPACITY_TRANSPARENT;
        }

        @Override
        public void setAlpha(int alpha) {
            this.alpha = alpha;
            invalidateSelf();
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }
}
