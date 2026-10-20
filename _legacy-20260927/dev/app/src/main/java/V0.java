package dev.local.newsoul;

import android.app.Activity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

/* 本文件合并了仅大小写不同的两个类 V0 / v0（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: V0.class */
public final /* synthetic */ class V0 implements InvocationHandler {
    public final int a = 0;
    public final Activity b;
    public final Object c;

    public /* synthetic */ V0(Activity activity, Runnable runnable) {
        this.b = activity;
        this.c = runnable;
    }

    public /* synthetic */ V0(C0014d1 d1Var, Activity activity) {
        this.c = d1Var;
        this.b = activity;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        Object objValueOf;
        switch (this.a) {
            case 0:
                Object objValueOf2 = null;
                if (method.getName().equals("accept")) {
                    this.b.runOnUiThread((Runnable) this.c);
                } else if (method.getName().equals("toString")) {
                    objValueOf2 = "NewSoulLoadedLikeCallback";
                } else if (method.getName().equals("hashCode")) {
                    objValueOf2 = Integer.valueOf(System.identityHashCode(obj));
                } else if (method.getName().equals("equals")) {
                    objValueOf2 = Boolean.valueOf(obj == objArr[0]);
                }
                return objValueOf2;
            default:
                Activity activity = this.b;
                C0014d1 d1Var = (C0014d1) this.c;
                d1Var.getClass();
                if (method.getDeclaringClass() != Object.class) {
                    if (method.getName().equals("onItemClick")) {
                        try {
                            d1Var.m(activity, ((Integer) objArr[0]).intValue());
                        } catch (Throwable th) {
                            C0014d1.c(activity, th);
                        }
                    }
                    objValueOf = null;
                    break;
                } else if (method.getName().equals("hashCode")) {
                    objValueOf = Integer.valueOf(System.identityHashCode(obj));
                } else if (method.getName().equals("equals")) {
                    objValueOf = Boolean.valueOf(obj == objArr[0]);
                } else {
                    objValueOf = "NewSoulForwardRecipients";
                }
                return objValueOf;
        }
    }
}

/* JADX INFO: loaded from: v0.class */
final /* synthetic */ class v0 implements CompoundButton.OnCheckedChangeListener {
    public final C0096y0 a;
    public final C0067q2 b;

    public /* synthetic */ v0(C0096y0 y0Var, C0067q2 q2Var) {
        this.a = y0Var;
        this.b = q2Var;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText = this.a.c;
        if (!z) {
            editText.setError(null);
        }
        if (z || !editText.hasFocus()) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) this.b.g.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        editText.clearFocus();
    }
}
