package dev.local.newsoul;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/* 本文件合并了仅大小写不同的两个类 A1 / a1（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: A1.class */
public final /* synthetic */ class A1 implements ValueAnimator.AnimatorUpdateListener {
    public final C1 a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;

    public /* synthetic */ A1(C1 DialogInterfaceOnDismissListenerC0010c1, float f, float C0023f2, float f3, float f4) {
        this.a = DialogInterfaceOnDismissListenerC0010c1;
        this.b = f;
        this.c = C0023f2;
        this.d = f3;
        this.e = f4;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        C1 DialogInterfaceOnDismissListenerC0010c1 = this.a;
        if (DialogInterfaceOnDismissListenerC0010c1.g != valueAnimator) {
            return;
        }
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f = this.c;
        float C0023f2 = this.b;
        float f3 = ((f - C0023f2) * fFloatValue) + C0023f2;
        DialogInterfaceOnDismissListenerC0010c1.e = f3;
        float f4 = this.e;
        float f5 = this.d;
        float f6 = ((f4 - f5) * fFloatValue) + f5;
        DialogInterfaceOnDismissListenerC0010c1.f = f6;
        DialogInterfaceOnDismissListenerC0010c1.a.c(f3, f6, true);
    }
}

/* JADX INFO: loaded from: a1.class */
final /* synthetic */ class a1 implements XposedInterface.Hooker {
    public final int a;
    public final C0014d1 b;

    public /* synthetic */ a1(C0014d1 d1Var, int i) {
        this.a = i;
        this.b = d1Var;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object objProceed;
        Object objProceed2;
        Object objProceed3;
        String str;
        switch (this.a) {
            case 0:
                C0014d1 d1Var = this.b;
                d1Var.getClass();
                Activity activity = (Activity) chain.getThisObject();
                if (C0014d1.f(activity)) {
                    try {
                        d1Var.m(activity, ((Integer) chain.getArg(0)).intValue());
                    } catch (Throwable th) {
                        C0014d1.c(activity, th);
                    }
                    objProceed = null;
                    return objProceed;
                } else {
                    objProceed = chain.proceed();
                }
                return objProceed;
            case 1:
                C0014d1 d1Var2 = this.b;
                d1Var2.getClass();
                Activity activity2 = (Activity) chain.getThisObject();
                if (C0014d1.f(activity2)) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        C0018e1 e1VarI = d1Var2.i(activity2);
                        if (e1VarI != null && (chain.getArg(1) instanceof List)) {
                            for (Object obj : (List) chain.getArg(1)) {
                                Object objD = C0014d1.d(obj, "conversation");
                                if (C0014d1.d(obj, "user") != null && objD != null && e1VarI.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                    arrayList.add(obj);
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        C0014d1.c(activity2, th2);
                    }
                    Object[] array = chain.getArgs().toArray();
                    array[1] = arrayList;
                    objProceed2 = chain.proceed(array);
                } else {
                    objProceed2 = chain.proceed();
                }
                return objProceed2;
            case 2:
                C0014d1 d1Var3 = this.b;
                d1Var3.getClass();
                Object objProceed4 = chain.proceed();
                Activity activity3 = (Activity) chain.getThisObject();
                if (C0014d1.f(activity3)) {
                    try {
                        if (d1Var3.i(activity3) == null) {
                            activity3.finish();
                        } else {
                            Object objD2 = C0014d1.d(activity3, "b");
                            objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                            d1Var3.e.invoke(objD2, Proxy.newProxyInstance(d1Var3.c.getClassLoader(), new Class[]{d1Var3.c}, new V0(d1Var3, activity3)));
                            for (int i = 0; i < 3; i++) {
                                int identifier = activity3.getResources().getIdentifier(new String[]{"ic_user_follow", "tv_mutily_choose", "tv_share"}[i], "id", activity3.getPackageName());
                                View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                if (viewFindViewById != null) {
                                    viewFindViewById.setVisibility(8);
                                }
                            }
                            View view = (View) C0014d1.d(activity3, "e");
                            view.setVisibility(0);
                            view.setOnClickListener(new C0(d1Var3, activity3, 1));
                            C0014d1.n(activity3);
                        }
                    } catch (Throwable th3) {
                        C0014d1.c(activity3, th3);
                        activity3.finish();
                    }
                }
                return objProceed4;
            case 3:
                C0014d1 d1Var4 = this.b;
                d1Var4.getClass();
                Activity activity4 = (Activity) chain.getThisObject();
                if (C0014d1.f(activity4)) {
                    try {
                        d1Var4.b(activity4);
                    } catch (Throwable th4) {
                        C0014d1.c(activity4, th4);
                    }
                    objProceed3 = null;
                    return objProceed3;
                } else {
                    objProceed3 = chain.proceed();
                }
                return objProceed3;
            case 4:
                this.b.getClass();
                return C0014d1.f((Activity) chain.getThisObject()) ? null : chain.proceed();
            default:
                C0014d1 d1Var5 = this.b;
                d1Var5.getClass();
                Activity activity5 = (Activity) chain.getThisObject();
                if (d1Var5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = d1Var5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                    d1Var5.s = null;
                    d1Var5.r = null;
                }
                Dialog dialog = d1Var5.t;
                if (dialog != null && dialog.getContext() == activity5) {
                    d1Var5.t.dismiss();
                    d1Var5.t = null;
                }
                return chain.proceed();
        }
    }
}
