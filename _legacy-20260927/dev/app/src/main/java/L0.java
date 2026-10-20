package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* 本文件合并了仅大小写不同的两个类 L0 / l0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: L0.class */
public final /* synthetic */ class L0 implements ValueAnimator.AnimatorUpdateListener {
    public final int a;
    public final FrameLayout b;

    public /* synthetic */ L0(FrameLayout frameLayout, int i) {
        this.a = i;
        this.b = frameLayout;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                M0 m0 = (M0) this.b;
                if (m0.r == valueAnimator) {
                    m0.n = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    m0.invalidate();
                    break;
                }
                break;
            case 1:
                Z0 AbstractC0100z0 = (Z0) this.b;
                if (AbstractC0100z0.r == valueAnimator) {
                    AbstractC0100z0.i = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    AbstractC0100z0.j();
                    break;
                }
                break;
            default:
                C0039j2 j2Var = (C0039j2) this.b;
                j2Var.getClass();
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                j2Var.d = fFloatValue;
                j2Var.setAlpha(fFloatValue);
                j2Var.requestLayout();
                break;
        }
    }
}

/* JADX INFO: loaded from: l0.class */
final /* synthetic */ class l0 implements XposedInterface.Hooker {
    public final Field a;
    public final Field b;
    public final Field c;
    public final Field d;
    public final Field e;
    public final Field f;
    public final Field g;

    public /* synthetic */ l0(Field field, Field field2, Field field3, Field field4, Field field5, Field field6, Field field7) {
        this.a = field;
        this.b = field2;
        this.c = field3;
        this.d = field4;
        this.e = field5;
        this.f = field6;
        this.g = field7;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Field field = this.a;
        Field field2 = this.b;
        Field field3 = this.c;
        Field field4 = this.d;
        Field field5 = this.e;
        Field field6 = this.f;
        Field field7 = this.g;
        Object objProceed = chain.proceed();
        try {
            Object thisObject = chain.getThisObject();
            AbstractC0061p0.k("header type=" + field.getInt(thisObject));
            boolean z = field.getInt(thisObject) == 0;
            Object obj = field2.get(thisObject);
            Object obj2 = field3.get(thisObject);
            Object arg = chain.getArg(0);
            Method method = AbstractC0061p0.m;
            AbstractC0061p0.a((TextView) obj, arg, z, "profile_disguise_follow_", AbstractC0061p0.i, method);
            AbstractC0061p0.a((TextView) obj2, arg, z, "profile_disguise_followed_", AbstractC0061p0.j, method);
            AbstractC0061p0.b(field4.get(thisObject), field5.get(thisObject), chain.getArg(0), z, AbstractC0061p0.m);
            AbstractC0061p0.b(field6.get(thisObject), field7.get(thisObject), chain.getArg(0), z, AbstractC0061p0.m);
        } catch (Throwable th) {
            AbstractC0061p0.j("header bind", th);
        }
        return objProceed;
    }
}
