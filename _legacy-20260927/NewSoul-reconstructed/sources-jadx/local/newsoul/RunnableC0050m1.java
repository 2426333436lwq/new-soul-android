package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.m1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0050m1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ RunnableC0050m1(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
        this.e = obj4;
        this.f = obj5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        switch (this.a) {
            case 0:
                C0077t1 c0077t1 = (C0077t1) this.b;
                Activity activity = (Activity) this.c;
                Object obj = this.d;
                Field field = (Field) this.e;
                Field field2 = (Field) this.f;
                try {
                    AbstractC0081u1.h(c0077t1.a);
                    C0093x1 c0093x1 = new C0093x1(activity.getFilesDir(), c0077t1.a, 0);
                    String str = c0077t1.b;
                    synchronized (C0093x1.b) {
                        try {
                            Iterator it = c0093x1.h().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                } else if (((C0089w1) it.next()).a.equals(str)) {
                                    z = true;
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    AbstractC0081u1.a.post(new RunnableC0054n1(activity, c0077t1, obj, z, field, field2));
                    return;
                } catch (Exception e) {
                    AbstractC0081u1.a.post(new RunnableC0058o1(c0077t1, activity, obj, field, field2, e, 0));
                    return;
                }
            case 1:
                Switch r0 = (Switch) this.b;
                C0070r2 c0070r2 = (C0070r2) r0;
                boolean z2 = c0070r2.c;
                Switch r3 = (Switch) this.c;
                if (!z2) {
                    c0070r2 = (C0070r2) r3;
                    if (!c0070r2.c) {
                        c0070r2 = null;
                    }
                }
                ((C0039j2) this.d).c(r3.isEnabled() && r3.isChecked() && !(r0.isEnabled() && r0.isChecked()), c0070r2);
                TextView textView = (TextView) this.e;
                Runnable runnable = (Runnable) this.f;
                textView.removeCallbacks(runnable);
                if (textView.isAttachedToWindow()) {
                    textView.post(runnable);
                    return;
                } else {
                    runnable.run();
                    return;
                }
            case 2:
                String string = ((SharedPreferences) this.b).getString("feed_filter_gender", "FEMALE");
                ((C1) this.c).b(string, (String[]) this.d, false);
                boolean zEquals = "ANY".equals(string);
                ((View) this.e).setVisibility(zEquals ? 8 : 0);
                ((TextView) this.f).setVisibility(zEquals ? 0 : 8);
                return;
            case 3:
                BooleanSupplier booleanSupplier = (BooleanSupplier) this.c;
                CheckBox checkBox = (CheckBox) this.d;
                SharedPreferences sharedPreferences = (SharedPreferences) this.e;
                C0027g2 c0027g2 = (C0027g2) this.b;
                c0027g2.d = true;
                String str2 = c0027g2.b;
                try {
                    boolean asBoolean = booleanSupplier.getAsBoolean();
                    checkBox.setEnabled(asBoolean);
                    String str3 = c0027g2.a;
                    checkBox.setChecked(sharedPreferences.getBoolean(str3, "block_splash".equals(str3)));
                    checkBox.setText(str2 + (asBoolean ? "" : "（未就绪）"));
                    checkBox.setContentDescription(str2 + (asBoolean ? "，勾选表示屏蔽" : "，未就绪，原因尚未确认"));
                    c0027g2.d = false;
                    ((C0031h2) this.f).a();
                    return;
                } catch (Throwable th2) {
                    c0027g2.d = false;
                    throw th2;
                }
            case 4:
                C0070r2 c0070r3 = (C0070r2) this.c;
                boolean zIsEnabled = c0070r3.isEnabled();
                TextView textView2 = (TextView) this.b;
                textView2.setEnabled(zIsEnabled);
                textView2.setClickable(c0070r3.isEnabled());
                boolean zIsEnabled2 = c0070r3.isEnabled();
                LinearLayout linearLayout = (LinearLayout) this.d;
                linearLayout.setEnabled(zIsEnabled2);
                linearLayout.setClickable(c0070r3.isEnabled());
                SharedPreferences sharedPreferences2 = (SharedPreferences) this.f;
                String str4 = "";
                String str5 = (sharedPreferences2 == null || c0070r3.isEnabled()) ? "" : "（未就绪）";
                StringBuilder sb = new StringBuilder();
                String str6 = (String) this.e;
                sb.append(str6);
                sb.append(str5);
                textView2.setText(sb.toString());
                if (sharedPreferences2 != null && !c0070r3.isEnabled()) {
                    str4 = "，未就绪，原因尚未确认";
                }
                c0070r3.setContentDescription(str6 + str4);
                return;
            case 5:
                c3 c3Var = (c3) this.c;
                b3 b3Var = (b3) this.b;
                b3Var.getClass();
                try {
                    c3Var.run();
                    th = null;
                } catch (Throwable th3) {
                    th = th3;
                }
                b3Var.q.a.post(new RunnableC0050m1(b3Var, (C0067q2) this.d, (TextView) this.e, th, (c3) this.f, 6));
                return;
            default:
                c3 c3Var2 = (c3) this.f;
                b3 b3Var2 = (b3) this.b;
                b3Var2.n = false;
                b3Var2.o = false;
                if (b3Var2.m) {
                    return;
                }
                C0067q2 c0067q2 = b3Var2.h;
                C0067q2 c0067q3 = (C0067q2) this.c;
                if (c0067q2 != c0067q3) {
                    return;
                }
                c0067q3.h.removeView((TextView) this.d);
                if (c0067q3.e.getButton(-2) != null) {
                    c0067q3.e.getButton(-2).setText("取消");
                }
                if (c0067q3.e.getButton(-1) != null) {
                    c0067q3.e.getButton(-1).setEnabled(true);
                    c0067q3.e.getButton(-1).setText("重试");
                }
                try {
                    b3Var2.j();
                    Throwable th4 = (Throwable) this.e;
                    if (th4 != null) {
                        throw th4;
                    }
                    c3Var2.run();
                    return;
                } catch (Throwable th5) {
                    d3.f(b3Var2.c, "操作未完成，请重试", th5);
                    return;
                }
        }
    }
}
