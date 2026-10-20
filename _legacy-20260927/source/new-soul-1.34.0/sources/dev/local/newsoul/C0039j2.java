package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/* JADX INFO: renamed from: dev.local.newsoul.j2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0039j2 extends FrameLayout {
    public ValueAnimator a;
    public boolean b;
    public boolean c;
    public float d;

    public static boolean a(View view) {
        boolean zIsAccessibilityFocused = view.isAccessibilityFocused();
        if (zIsAccessibilityFocused) {
            view.performAccessibilityAction(128, null);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                zIsAccessibilityFocused |= a(viewGroup.getChildAt(i));
            }
        }
        return zIsAccessibilityFocused;
    }

    public final void b() {
        ValueAnimator valueAnimator = this.a;
        this.a = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float f = this.b ? 1.0f : 0.0f;
        this.d = f;
        setAlpha(f);
        setVisibility(this.b ? 0 : 8);
        requestLayout();
    }

    public final void c(boolean z, C0070r2 c0070r2) {
        int i = 2;
        int i2 = 1;
        boolean z2 = this.c;
        if (z2 && this.b == z) {
            if (c0070r2 == null) {
                b();
                return;
            }
            return;
        }
        boolean z3 = z2 && c0070r2 != null && isAttachedToWindow() && isShown() && hasWindowFocus() && getWidth() > 0 && ValueAnimator.areAnimatorsEnabled();
        if (this.c && z && c0070r2 != null && (getParent() instanceof View)) {
            View view = (View) getParent();
            z3 = view.isShown() && view.isAttachedToWindow() && view.hasWindowFocus() && view.getWidth() > 0 && ValueAnimator.areAnimatorsEnabled();
        }
        this.c = true;
        if (!z && this.b) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            super.dispatchTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
        }
        this.b = z;
        setDescendantFocusability(z ? 262144 : 393216);
        if (!z) {
            boolean zHasFocus = hasFocus();
            boolean zA = a(this);
            clearFocus();
            if (c0070r2 != null) {
                if (zHasFocus) {
                    c0070r2.requestFocus();
                }
                if (zA) {
                    c0070r2.performAccessibilityAction(64, null);
                }
            }
        }
        setImportantForAccessibility(z ? 0 : 4);
        ValueAnimator valueAnimator = this.a;
        this.a = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z3) {
            b();
            return;
        }
        setVisibility(0);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.d, z ? 1.0f : 0.0f);
        this.a = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(160L);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new L0(this, i));
        valueAnimatorOfFloat.addListener(new B1(this, i2));
        valueAnimatorOfFloat.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return !this.b || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return !this.b || super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        b();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), Math.round(getMeasuredHeight() * this.d));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i3 <= 0 || i == i3 || this.a == null) {
            return;
        }
        b();
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        b();
    }
}
