/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.LinearGradient
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Path$Direction
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.graphics.RectF
 *  android.graphics.RenderNode
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.view.animation.DecelerateInterpolator
 *  android.widget.FrameLayout
 */
package dev.local.newsoul;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
import dev.local.newsoul.H0;
import dev.local.newsoul.K0;
import dev.local.newsoul.L0;
import dev.local.newsoul.L2;
import dev.local.newsoul.u2_0;

public final class M0
extends FrameLayout {
    public final boolean a;
    public final boolean b;
    public final L2 c;
    public final Paint d = new Paint(3);
    public final RectF e = new RectF();
    public final RectF f = new RectF();
    public final Path g = new Path();
    public final float h;
    public final LinearGradient i;
    public final LinearGradient j;
    public final Matrix k = new Matrix();
    public float l;
    public float m;
    public float n;
    public final K0 o = new K0();
    public final Paint p;
    public final u2_0 q;
    public ValueAnimator r;
    public boolean s;

    public M0(Context object, L2 l2, boolean bl) {
        super(object);
        boolean bl2;
        Paint paint;
        this.p = paint = new Paint(1);
        this.q = new u2_0(4, (Object)this);
        this.b = bl;
        this.c = l2;
        this.a = bl2 = l2.a;
        this.h = this.getResources().getDisplayMetrics().density;
        bl = l2.a;
        int n = bl ? -15461342 : -1710099;
        int n2 = bl ? -15329755 : -1973270;
        l2 = Shader.TileMode.CLAMP;
        this.i = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, n, n2, (Shader.TileMode)l2);
        if (bl2) {
            object = new int[3];
            object[0] = (Context)0x24FFFFFF;
            object[1] = (Context)0x4FFFFFF;
            object[2] = (Context)0x12FFFFFF;
        } else {
            object = new int[3];
            object[0] = (Context)-1275068417;
            object[1] = (Context)0xC55555D;
            object[2] = (Context)408245597;
        }
        this.j = new LinearGradient(0.0f, 0.0f, 1.0f, 1.0f, (int[])object, new float[]{0.0f, 0.55f, 1.0f}, (Shader.TileMode)l2);
        n = bl2 ? -16730718 : -16746133;
        paint.setColorFilter((ColorFilter)new PorterDuffColorFilter(n, PorterDuff.Mode.SRC_IN));
        this.setWillNotDraw(false);
        this.setImportantForAccessibility(2);
    }

    public static void a(M0 m0, Canvas canvas) {
        L2 l2 = m0.c;
        canvas.drawColor(l2.d());
        m0.b(canvas);
        Paint paint = m0.d;
        int n = l2.a ? -14671824 : -526086;
        paint.setColor(n);
        l2 = m0.e;
        canvas.drawRoundRect((RectF)l2, l2.height() / 2.0f, l2.height() / 2.0f, paint);
        if (!m0.b) {
            n = canvas.saveLayer(0.0f, 0.0f, (float)m0.getWidth(), (float)m0.getHeight(), m0.p);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(n);
        }
    }

    public final void b(Canvas canvas) {
        this.c.getClass();
        RectF rectF = this.f;
        Paint paint = this.d;
        paint.setShader((Shader)this.i);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.h * 0.5f);
        paint.setShader((Shader)this.j);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
    }

    public final void c(float f, float f2, boolean bl) {
        boolean bl2 = true;
        this.l = f;
        this.m = f2;
        this.c.getClass();
        boolean bl3 = ValueAnimator.areAnimatorsEnabled();
        f = bl && bl3 ? (this.b ? 0.35f : 1.0f) : 0.0f;
        if (bl3 && this.isAttachedToWindow()) {
            if (this.s != bl) {
                this.s = bl;
                ValueAnimator valueAnimator = this.r;
                this.r = null;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                this.r = valueAnimator = ValueAnimator.ofFloat((float[])new float[]{this.n, f});
                long l = bl ? 120L : 180L;
                valueAnimator.setDuration(l);
                valueAnimator.setInterpolator((TimeInterpolator)new DecelerateInterpolator(1.5f));
                valueAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new L0(this, 0));
                valueAnimator.start();
            }
        } else {
            ValueAnimator valueAnimator = this.r;
            this.r = null;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.n = f;
            bl = bl && bl3 ? bl2 : false;
            this.s = bl;
        }
        this.invalidate();
    }

    public final void dispatchDraw(Canvas canvas) {
        if (this.m <= 0.0f) {
            super.dispatchDraw(canvas);
            return;
        }
        this.c.getClass();
        RectF rectF = this.e;
        boolean bl = this.b;
        u2_0 u2_02 = this.q;
        if (bl) {
            int n = this.getWidth();
            int n2 = this.getHeight();
            float f = rectF.height() / 2.0f;
            float f2 = this.n;
            this.o.a(canvas, n, n2, rectF, f, f2, this.h, this.a, u2_02);
            super.dispatchDraw(canvas);
            return;
        }
        Path path = this.g;
        path.reset();
        path.addRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, Path.Direction.CW);
        int n = canvas.save();
        canvas.clipOutPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(n);
        n = this.getWidth();
        int n3 = this.getHeight();
        float f = rectF.height() / 2.0f;
        float f4 = this.n;
        this.o.a(canvas, n, n3, rectF, f, f4, this.h, this.a, u2_02);
    }

    public final void onDetachedFromWindow() {
        Object object = this.r;
        this.r = null;
        if (object != null) {
            object.cancel();
        }
        this.n = 0.0f;
        this.s = false;
        K0 k0 = this.o;
        object = k0.c;
        if (object != null) {
            H0.g((RenderNode)object.a);
        }
        k0.c = null;
        k0.e = null;
        k0.f = null;
        super.onDetachedFromWindow();
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.n;
        float f2 = this.h;
        float f4 = (5.0f * f + 1.0f) * f2;
        f = (f * 4.0f + 1.0f) * f2;
        RectF rectF = this.f;
        rectF.set(f4, f, (float)this.getWidth() - f4, (float)this.getHeight() - f);
        Matrix matrix = this.k;
        matrix.setScale(rectF.width(), rectF.height());
        matrix.postTranslate(rectF.left, rectF.top);
        this.i.setLocalMatrix(matrix);
        this.j.setLocalMatrix(matrix);
        this.b(canvas);
        if (this.m <= 0.0f) {
            return;
        }
        f = 9.0f * f2 * this.n;
        f4 = Math.max(f2, this.l - f);
        f = Math.min((float)this.getWidth() - f2, this.l + this.m + f);
        f2 = this.c.b * 3.0f - f2 * 6.0f * this.n;
        this.e.set(f4, (float)this.getPaddingTop() + f2, f, (float)(this.getHeight() - this.getPaddingBottom()) - f2);
    }
}

