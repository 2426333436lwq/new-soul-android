package dev.local.newsoul.ui;

import android.view.View;
import android.view.ViewParent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * 可折叠面板容器（原 {@code j2}）。
 *
 * <p>继承 {@link FrameLayout}，通过透明度动画在显示/隐藏之间过渡：隐藏时高度随透明度收缩为零，
 * 并阻断触摸/按键分发与无障碍焦点。透明度动画时长 160ms（原 0xa0），使用减速插值器。</p>
 */
class AnimatedPanel extends FrameLayout {

    /** 动画时长（毫秒，原 0xa0）。 */
    private static final long ANIMATION_DURATION_MS = 0xA0L;
    /** 无障碍「清除焦点」动作 id（原 0x80）。 */
    private static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 0x80;
    /** 无障碍「请求焦点」动作 id（原 0x40）。 */
    private static final int ACTION_ACCESSIBILITY_FOCUS = 0x40;
    /** 显示时子视图可聚焦（原 0x40000）。 */
    private static final int FOCUS_BEFORE_DESCENDANTS = 0x40000;
    /** 隐藏时阻断子视图聚焦（原 0x60000）。 */
    private static final int FOCUS_BLOCK_DESCENDANTS = 0x60000;

    /** 当前透明度动画（原字段 {@code a}）。 */
    private ValueAnimator animator;
    /** 当前是否处于显示状态（原字段 {@code b}）。 */
    private boolean shown;
    /** 是否已完成过一次状态切换（原字段 {@code c}）。 */
    private boolean initialized;
    /** 当前透明度（原字段 {@code d}）。 */
    private float alpha;

    AnimatedPanel(Context context) {
        super(context);
    }

    /**
     * 递归清除视图及其子树的无障碍焦点（原静态方法 {@code a}）。
     *
     * @return 是否曾有视图处于无障碍焦点
     */
    static boolean clearAccessibilityFocus(View view) {
        boolean focused = view.isAccessibilityFocused();
        if (focused) {
            view.performAccessibilityAction(ACTION_CLEAR_ACCESSIBILITY_FOCUS, null);
        }
        if (view instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                focused |= clearAccessibilityFocus(group.getChildAt(i));
            }
        }
        return focused;
    }

    /** 结束动画并立即回到当前状态的终态（原方法 {@code b}）。 */
    void reset() {
        ValueAnimator current = animator;
        animator = null;
        if (current != null) {
            current.cancel();
        }
        alpha = shown ? 1.0f : 0.0f;
        setAlpha(alpha);
        setVisibility(shown ? VISIBLE : GONE);
        requestLayout();
    }

    /**
     * 请求切换到目标显隐状态（原方法 {@code c}）。
     *
     * @param show        目标是否显示
     * @param focusTarget 隐藏时接收焦点的视图；可为 {@code null}
     */
    void setShown(boolean show, View focusTarget) {
        if (initialized && shown == show) {
            if (focusTarget == null) {
                reset();
            }
            return;
        }

        boolean animate = false;
        if (initialized && focusTarget != null) {
            animate = isAnimatable(this);
        }
        if (initialized && show && focusTarget != null) {
            ViewParent parent = getParent();
            if (parent instanceof View) {
                animate = isAnimatable((View) parent);
            }
        }
        initialized = true;

        if (!show && shown) {
            long now = SystemClock.uptimeMillis();
            MotionEvent cancel = MotionEvent.obtain(now, now, MotionEvent.ACTION_CANCEL, 0f, 0f, 0);
            super.dispatchTouchEvent(cancel);
            cancel.recycle();
        }

        shown = show;
        setDescendantFocusability(show ? FOCUS_BEFORE_DESCENDANTS : FOCUS_BLOCK_DESCENDANTS);

        if (!show) {
            boolean hadFocus = hasFocus();
            boolean hadAccessibilityFocus = clearAccessibilityFocus(this);
            clearFocus();
            if (focusTarget != null) {
                if (hadFocus) {
                    focusTarget.requestFocus();
                }
                if (hadAccessibilityFocus) {
                    focusTarget.performAccessibilityAction(ACTION_ACCESSIBILITY_FOCUS, null);
                }
            }
        }
        setImportantForAccessibility(show
                ? IMPORTANT_FOR_ACCESSIBILITY_AUTO
                : IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);

        ValueAnimator current = animator;
        animator = null;
        if (current != null) {
            current.cancel();
        }

        if (!animate) {
            reset();
            return;
        }

        setVisibility(VISIBLE);
        float from = alpha;
        float to = show ? 1.0f : 0.0f;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        animator = valueAnimator;
        valueAnimator.setDuration(ANIMATION_DURATION_MS);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new AnimatorUpdateListener(this));
        valueAnimator.addListener(new AnimatorEndListener(this));
        valueAnimator.start();
    }

    /** 判断视图当前是否适合做显隐动画（原 {@code c} 中的内联条件）。 */
    private static boolean isAnimatable(View view) {
        return view.isAttachedToWindow()
                && view.isShown()
                && view.hasWindowFocus()
                && view.getWidth() > 0
                && ValueAnimator.areAnimatorsEnabled();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!shown) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!shown) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        reset();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), Math.round(getMeasuredHeight() * alpha));
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (oldWidth > 0 && width != oldWidth && animator != null) {
            reset();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        reset();
    }

    /** 动画更新：同步透明度并重排（原 {@code L0} 索引 2）。 */
    private static final class AnimatorUpdateListener
            implements ValueAnimator.AnimatorUpdateListener {

        private final AnimatedPanel panel;

        AnimatorUpdateListener(AnimatedPanel panel) {
            this.panel = panel;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (Float) animation.getAnimatedValue();
            panel.alpha = value;
            panel.setAlpha(value);
            panel.requestLayout();
        }
    }

    /** 动画结束：清理动画引用并回到终态（原 {@code B1} 索引 1）。 */
    private static final class AnimatorEndListener extends AnimatorListenerAdapter {

        private final AnimatedPanel panel;

        AnimatorEndListener(AnimatedPanel panel) {
            this.panel = panel;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (panel.animator == animation) {
                panel.animator = null;
                panel.reset();
            }
        }
    }
}
