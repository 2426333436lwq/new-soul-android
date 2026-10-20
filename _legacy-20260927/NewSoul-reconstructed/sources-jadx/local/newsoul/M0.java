package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class M0 extends FrameLayout {
    public final boolean a;
    public final boolean b;
    public final L2 c;
    public final Paint d;
    public final RectF e;
    public final RectF f;
    public final Path g;
    public final float h;
    public final LinearGradient i;
    public final LinearGradient j;
    public final Matrix k;
    public float l;
    public float m;
    public float n;
    public final K0 o;
    public final Paint p;
    public final U2 q;
    public ValueAnimator r;
    public boolean s;

    public M0(Context context, L2 l2, boolean z) {
        super(context);
        this.d = new Paint(3);
        this.e = new RectF();
        this.f = new RectF();
        this.g = new Path();
        this.k = new Matrix();
        this.o = new K0();
        Paint paint = new Paint(1);
        this.p = paint;
        this.q = new U2(4, this);
        this.b = z;
        this.c = l2;
        boolean z2 = l2.a;
        this.a = z2;
        this.h = getResources().getDisplayMetrics().density;
        boolean z3 = l2.a;
        int i = z3 ? -15461342 : -1710099;
        int i2 = z3 ? -15329755 : -1973270;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.i = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, i, i2, tileMode);
        this.j = new LinearGradient(0.0f, 0.0f, 1.0f, 1.0f, z2 ? new int[]{620756991, 83886079, 318767103} : new int[]{-1275068417, 206919005, 408245597}, new float[]{0.0f, 0.55f, 1.0f}, tileMode);
        paint.setColorFilter(new PorterDuffColorFilter(z2 ? -16730718 : -16746133, PorterDuff.Mode.SRC_IN));
        setWillNotDraw(false);
        setImportantForAccessibility(2);
    }

    public static void a(M0 m0, Canvas canvas) {
        L2 l2 = m0.c;
        canvas.drawColor(l2.d());
        m0.b(canvas);
        Paint paint = m0.d;
        paint.setColor(l2.a ? -14671824 : -526086);
        RectF rectF = m0.e;
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        if (m0.b) {
            return;
        }
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, m0.getWidth(), m0.getHeight(), m0.p);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSaveLayer);
    }

    public final void b(Canvas canvas) {
        this.c.getClass();
        RectF rectF = this.f;
        Paint paint = this.d;
        paint.setShader(this.i);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.h * 0.5f);
        paint.setShader(this.j);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
    }

    public final void c(float f, float f2, boolean z) {
        float f3;
        this.l = f;
        this.m = f2;
        this.c.getClass();
        boolean zAreAnimatorsEnabled = ValueAnimator.areAnimatorsEnabled();
        if (z && zAreAnimatorsEnabled) {
            f3 = this.b ? 0.35f : 1.0f;
        } else {
            f3 = 0.0f;
        }
        if (!zAreAnimatorsEnabled || !isAttachedToWindow()) {
            ValueAnimator valueAnimator = this.r;
            this.r = null;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.n = f3;
            this.s = z && zAreAnimatorsEnabled;
        } else if (this.s != z) {
            this.s = z;
            ValueAnimator valueAnimator2 = this.r;
            this.r = null;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.n, f3);
            this.r = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(z ? 120L : 180L);
            valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
            valueAnimatorOfFloat.addUpdateListener(new L0(this, 0));
            valueAnimatorOfFloat.start();
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.m <= 0.0f) {
            super.dispatchDraw(canvas);
            return;
        }
        this.c.getClass();
        RectF rectF = this.e;
        boolean z = this.b;
        U2 u2 = this.q;
        if (z) {
            this.o.a(canvas, getWidth(), getHeight(), rectF, rectF.height() / 2.0f, this.n, this.h, this.a, u2);
            super.dispatchDraw(canvas);
            return;
        }
        Path path = this.g;
        path.reset();
        path.addRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, Path.Direction.CW);
        int iSave = canvas.save();
        canvas.clipOutPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
        this.o.a(canvas, getWidth(), getHeight(), rectF, rectF.height() / 2.0f, this.n, this.h, this.a, u2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ValueAnimator valueAnimator = this.r;
        this.r = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.n = 0.0f;
        this.s = false;
        K0 k0 = this.o;
        Y y = k0.c;
        if (y != null) {
            ((RenderNode) y.a).discardDisplayList();
        }
        k0.c = null;
        k0.e = null;
        k0.f = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.n;
        float f2 = this.h;
        float f3 = ((5.0f * f) + 1.0f) * f2;
        float f4 = ((f * 4.0f) + 1.0f) * f2;
        RectF rectF = this.f;
        rectF.set(f3, f4, getWidth() - f3, getHeight() - f4);
        Matrix matrix = this.k;
        matrix.setScale(rectF.width(), rectF.height());
        matrix.postTranslate(rectF.left, rectF.top);
        this.i.setLocalMatrix(matrix);
        this.j.setLocalMatrix(matrix);
        b(canvas);
        if (this.m <= 0.0f) {
            return;
        }
        float f5 = 9.0f * f2 * this.n;
        float fMax = Math.max(f2, this.l - f5);
        float fMin = Math.min(getWidth() - f2, this.l + this.m + f5);
        float f6 = (this.c.b * 3.0f) - ((f2 * 6.0f) * this.n);
        this.e.set(fMax, getPaddingTop() + f6, fMin, (getHeight() - getPaddingBottom()) - f6);
    }
}
