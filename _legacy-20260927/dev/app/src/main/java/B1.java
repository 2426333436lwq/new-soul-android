package dev.local.newsoul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.FrameLayout;
import android.app.Activity;
import android.app.Dialog;
import android.os.SystemClock;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* 本文件合并了仅大小写不同的两个类 B1 / b1（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: B1.class */
public final class B1 extends AnimatorListenerAdapter {
    public final int a;
    public final FrameLayout b;

    public /* synthetic */ B1(FrameLayout frameLayout, int i) {
        this.a = i;
        this.b = frameLayout;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                C1 DialogInterfaceOnDismissListenerC0010c1 = (C1) this.b;
                if (DialogInterfaceOnDismissListenerC0010c1.g == animator) {
                    DialogInterfaceOnDismissListenerC0010c1.g = null;
                    DialogInterfaceOnDismissListenerC0010c1.a.c(DialogInterfaceOnDismissListenerC0010c1.e, DialogInterfaceOnDismissListenerC0010c1.f, false);
                    break;
                }
                break;
            default:
                C0039j2 j2Var = (C0039j2) this.b;
                if (j2Var.a == animator) {
                    j2Var.a = null;
                    j2Var.b();
                }
                break;
        }
    }
}

/* JADX INFO: loaded from: b1.class */
final /* synthetic */ class b1 implements InvocationHandler {
    public final C0014d1 a;
    public final Activity b;
    public final String c;
    public final C0018e1 d;
    public final LinkedHashMap e;
    public final List f;
    public final Dialog g;

    public /* synthetic */ b1(C0014d1 d1Var, Activity activity, String str, C0018e1 e1Var, LinkedHashMap linkedHashMap, ArrayList arrayList, Dialog dialog) {
        this.a = d1Var;
        this.b = activity;
        this.c = str;
        this.d = e1Var;
        this.e = linkedHashMap;
        this.f = arrayList;
        this.g = dialog;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        Object objValueOf;
        Activity activity = this.b;
        String str = this.c;
        LinkedHashMap linkedHashMap = this.e;
        List list = this.f;
        Dialog dialog = this.g;
        C0014d1 d1Var = this.a;
        d1Var.getClass();
        if (method.getDeclaringClass() != Object.class) {
            if (method.getName().equals("onClickConfirm")) {
                try {
                    if (!activity.isFinishing() && !activity.isDestroyed() && str.equals(d1Var.r)) {
                        C0018e1 e1VarI = d1Var.i(activity);
                        C0018e1 e1Var = this.d;
                        if (e1VarI == e1Var && C0014d1.h(activity, e1Var).keySet().equals(linkedHashMap.keySet())) {
                            ArrayList<String> arrayListA = e1Var.a((String) d1Var.g.invoke(null, null), (ArrayList) list, SystemClock.elapsedRealtime());
                            if (arrayListA == null) {
                                throw new IllegalStateException("Expired confirmation");
                            }
                            int i = 0;
                            for (String str2 : arrayListA) {
                                if (!e1Var.a.equals(d1Var.g.invoke(null, null))) {
                                    throw new IllegalStateException("Account changed");
                                }
                                d1Var.k(e1Var, str2);
                                i++;
                            }
                            int C0035i2 = e1Var.c;
                            StringBuilder sb = new StringBuilder();
                            sb.append("type=");
                            sb.append(C0035i2);
                            sb.append("; targets=");
                            sb.append(i);
                            V.d("message.forward.dispatched", sb.toString());
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("已提交转发给 ");
                            sb2.append(i);
                            sb2.append(" 人，请在目标会话查看");
                            C0014d1.l(activity, sb2.toString());
                            dialog.dismiss();
                            activity.finish();
                        }
                    }
                    throw new IllegalStateException("Selection changed");
                } catch (Throwable th) {
                    try {
                        V.d("message.forward.failed", ("submitted=0; total=" + list.size()) + "\n" + V.c(th));
                        C0014d1.l(activity, "转发中断，已提交 0/" + list.size() + "，请先核对目标会话");
                    } catch (Throwable th2) {
                        dialog.dismiss();
                        activity.finish();
                        throw th2;
                    }
                }
            }
            objValueOf = null;
        } else if (method.getName().equals("hashCode")) {
            objValueOf = Integer.valueOf(System.identityHashCode(obj));
        } else if (method.getName().equals("equals")) {
            objValueOf = Boolean.valueOf(obj == objArr[0]);
        } else {
            objValueOf = "NewSoulMessageForwardConfirm";
        }
        return objValueOf;
    }
}
