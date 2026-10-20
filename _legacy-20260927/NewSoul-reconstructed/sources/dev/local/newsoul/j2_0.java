/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.os.SystemClock
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.animation.DecelerateInterpolator
 *  android.widget.FrameLayout
 */
package dev.local.newsoul;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import dev.local.newsoul.B1;
import dev.local.newsoul.L0;
import dev.local.newsoul.r2_0;

/*
 * Renamed from dev.local.newsoul.j2
 */
public final class j2_0
extends FrameLayout {
    public ValueAnimator a;
    public boolean b;
    public boolean c;
    public float d;

    public static boolean a(View view) {
        boolean bl = view.isAccessibilityFocused();
        if (bl) {
            view.performAccessibilityAction(128, null);
        }
        boolean bl2 = bl;
        if (view instanceof ViewGroup) {
            view = (ViewGroup)view;
            int n = 0;
            while (true) {
                bl2 = bl;
                if (n >= view.getChildCount()) break;
                bl |= j2_0.a(view.getChildAt(n));
                ++n;
            }
        }
        return bl2;
    }

    public final void b() {
        ValueAnimator valueAnimator = this.a;
        this.a = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float f = this.b ? 1.0f : 0.0f;
        this.d = f;
        this.setAlpha(f);
        int n = this.b ? 0 : 8;
        this.setVisibility(n);
        this.requestLayout();
    }

    public final void c(boolean bl, r2_0 r2_02) {
        View view;
        boolean bl2 = this.c;
        if (bl2 && this.b == bl) {
            if (r2_02 == null) {
                this.b();
            }
            return;
        }
        int n = bl2 && r2_02 != null && this.isAttachedToWindow() && this.isShown() && this.hasWindowFocus() && this.getWidth() > 0 && ValueAnimator.areAnimatorsEnabled() ? 1 : 0;
        int n2 = n;
        if (this.c) {
            n2 = n;
            if (bl) {
                n2 = n;
                if (r2_02 != null) {
                    n2 = n;
                    if (this.getParent() instanceof View) {
                        view = (View)this.getParent();
                        n2 = view.isShown() && view.isAttachedToWindow() && view.hasWindowFocus() && view.getWidth() > 0 && ValueAnimator.areAnimatorsEnabled() ? 1 : 0;
                    }
                }
            }
        }
        this.c = true;
        if (!bl && this.b) {
            long l = SystemClock.uptimeMillis();
            view = MotionEvent.obtain((long)l, (long)l, (int)3, (float)0.0f, (float)0.0f, (int)0);
            super.dispatchTouchEvent((MotionEvent)view);
            view.recycle();
        }
        this.b = bl;
        n = bl ? 262144 : 393216;
        this.setDescendantFocusability(n);
        if (!bl) {
            bl2 = this.hasFocus();
            boolean bl3 = j2_0.a((View)this);
            this.clearFocus();
            if (r2_02 != null) {
                if (bl2) {
                    r2_02.requestFocus();
                }
                if (bl3) {
                    r2_02.performAccessibilityAction(64, null);
                }
            }
        }
        n = bl ? 0 : 4;
        this.setImportantForAccessibility(n);
        r2_02 = this.a;
        this.a = null;
        if (r2_02 != null) {
            r2_02.cancel();
        }
        if (n2 == 0) {
            this.b();
            return;
        }
        this.setVisibility(0);
        float f = this.d;
        float f2 = bl ? 1.0f : 0.0f;
        r2_02 = ValueAnimator.ofFloat((float[])new float[]{f, f2});
        this.a = r2_02;
        r2_02.setDuration(160L);
        r2_02.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
        r2_02.addUpdateListener(new L0(this, 2));
        r2_02.addListener((Animator.AnimatorListener)new B1(this, 1));
        r2_02.start();
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean bl = !this.b || super.dispatchKeyEvent(keyEvent);
        return bl;
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl = !this.b || super.dispatchTouchEvent(motionEvent);
        return bl;
    }

    public final void onDetachedFromWindow() {
        this.b();
        super.onDetachedFromWindow();
    }

    public final void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        this.setMeasuredDimension(this.getMeasuredWidth(), Math.round((float)this.getMeasuredHeight() * this.d));
    }

    public final void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n3 > 0 && n != n3 && this.a != null) {
            this.b();
        }
    }

    public final void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        this.b();
    }
}

