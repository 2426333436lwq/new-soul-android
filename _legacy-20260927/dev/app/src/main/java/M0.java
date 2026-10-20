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
import android.widget.TextView;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* 本文件合并了仅大小写不同的两个类 M0 / m0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: M0.class */
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
        int C0035i2 = z3 ? -15329755 : -1973270;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.i = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, i, C0035i2, tileMode);
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
        m0.superDispatchDraw(canvas);
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

    public final void c(float f, float C0023f2, boolean z) {
        float f3;
        this.l = f;
        this.m = C0023f2;
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

    private void superDispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
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
        U2 C0082u2 = this.q;
        if (z) {
            this.o.a(canvas, getWidth(), getHeight(), rectF, rectF.height() / 2.0f, this.n, this.h, this.a, C0082u2);
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
        this.o.a(canvas, getWidth(), getHeight(), rectF, rectF.height() / 2.0f, this.n, this.h, this.a, C0082u2);
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
        K0 C0041k0 = this.o;
        Y y = C0041k0.c;
        if (y != null) {
            H0.g((RenderNode) y.a);
        }
        C0041k0.c = null;
        C0041k0.e = null;
        C0041k0.f = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.n;
        float C0023f2 = this.h;
        float f3 = ((5.0f * f) + 1.0f) * C0023f2;
        float f4 = ((f * 4.0f) + 1.0f) * C0023f2;
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
        float f5 = 9.0f * C0023f2 * this.n;
        float fMax = Math.max(C0023f2, this.l - f5);
        float fMin = Math.min(getWidth() - C0023f2, this.l + this.m + f5);
        float f6 = (this.c.b * 3.0f) - ((C0023f2 * 6.0f) * this.n);
        this.e.set(fMax, getPaddingTop() + f6, fMin, (getHeight() - getPaddingBottom()) - f6);
    }
}

/* JADX INFO: loaded from: m0.class */
final /* synthetic */ class m0 implements XposedInterface.Hooker {
    public final boolean a;
    public final Method b;
    public final String c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public /* synthetic */ m0(boolean z, Method method, String str, Field field, Field field2, Field field3, Field field4, Field field5, Field field6) {
        this.a = z;
        this.b = method;
        this.c = str;
        this.d = field;
        this.e = field2;
        this.f = field3;
        this.g = field4;
        this.h = field5;
        this.i = field6;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Method method = this.b;
        String str = this.c;
        Field field = this.d;
        Field field2 = this.e;
        Field field3 = this.f;
        Field field4 = this.g;
        Field field5 = this.h;
        Field field6 = this.i;
        Object objProceed = chain.proceed();
        try {
            Object thisObject = chain.getThisObject();
            Object arg = chain.getArg(1);
            if (arg != null) {
                Object objInvoke = arg;
                if (this.a) {
                    objInvoke = method.invoke(arg, null);
                }
                AbstractC0061p0.k(str + " type=" + field.getInt(thisObject) + "; count=" + (field2.get(thisObject) != null));
                boolean z = field.getInt(thisObject) == 0;
                Object obj = field2.get(thisObject);
                Object obj2 = field3.get(objInvoke);
                Object obj3 = field4.get(objInvoke);
                Method method2 = AbstractC0061p0.l;
                AbstractC0061p0.a((TextView) obj2, obj, z, "profile_disguise_follow_", AbstractC0061p0.i, method2);
                AbstractC0061p0.a((TextView) obj3, obj, z, "profile_disguise_followed_", AbstractC0061p0.j, method2);
                AbstractC0061p0.b(field5.get(objInvoke), field6.get(objInvoke), obj, z, AbstractC0061p0.l);
            }
        } catch (Throwable th) {
            AbstractC0061p0.j(str + " bind", th);
        }
        return objProceed;
    }
}
