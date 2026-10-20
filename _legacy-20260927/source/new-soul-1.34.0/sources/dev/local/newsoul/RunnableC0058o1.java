package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.WeakHashMap;
import org.json.JSONException;

/* JADX INFO: renamed from: dev.local.newsoul.o1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0058o1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ RunnableC0058o1(Activity activity, C0067q2 c0067q2, Button button, TextView textView, String str, String[] strArr) {
        this.a = 2;
        this.b = activity;
        this.c = c0067q2;
        this.d = button;
        this.e = textView;
        this.f = str;
        this.g = strArr;
    }

    @Override // java.lang.Runnable
    public final void run() throws JSONException, IOException {
        final Y yB;
        final String str;
        switch (this.a) {
            case 0:
                Field field = (Field) this.e;
                Field field2 = (Field) this.f;
                C0077t1 c0077t1 = (C0077t1) this.c;
                c0077t1.h = true;
                Activity activity = this.b;
                if (AbstractC0081u1.b(activity)) {
                    WeakHashMap weakHashMap = AbstractC0081u1.c;
                    Object obj = this.d;
                    if (weakHashMap.get(obj) == c0077t1) {
                        try {
                            field.set(c0077t1.d, "插眼不可用");
                            Object obj2 = field2.get(obj);
                            if (obj2 != null) {
                                AbstractC0081u1.c(obj2, "notifyDataSetChanged");
                            }
                            break;
                        } catch (Exception unused) {
                        }
                        AbstractC0081u1.e(activity, (Exception) this.g);
                        break;
                    }
                }
                break;
            case 1:
                String str2 = (String) this.c;
                Activity activity2 = this.b;
                C0089w1 c0089w1 = (C0089w1) this.d;
                C0067q2 c0067q2 = (C0067q2) this.e;
                Runnable[] runnableArr = (Runnable[]) this.f;
                Handler handler = AbstractC0081u1.a;
                try {
                    AbstractC0081u1.h(str2);
                    new C0093x1(activity2.getFilesDir(), str2, 0).i(c0089w1.a);
                    handler.post(new RunnableC0024g(c0067q2, runnableArr, 5));
                } catch (Exception e) {
                    handler.post(new H(this.g, activity2, (Object) e, 9));
                    return;
                }
                break;
            default:
                try {
                    str = null;
                    yB = T0.b();
                } catch (Exception unused2) {
                    yB = null;
                    str = "检测失败，请重试或打开发布页。";
                }
                final String[] strArr = (String[]) this.g;
                final Button button = (Button) ((TextView) this.d);
                final Activity activity3 = this.b;
                final C0067q2 c0067q3 = (C0067q2) this.c;
                final TextView textView = (TextView) this.e;
                final String str3 = (String) this.f;
                activity3.runOnUiThread(new Runnable() { // from class: dev.local.newsoul.M2
                    public final /* synthetic */ boolean f = false;

                    @Override // java.lang.Runnable
                    public final void run() {
                        AlertDialog alertDialog;
                        String str4;
                        Activity activity4 = activity3;
                        if (activity4.isFinishing() || activity4.isDestroyed() || (alertDialog = c0067q3.e) == null || !alertDialog.isShowing()) {
                            return;
                        }
                        TextView textView2 = button;
                        textView2.setEnabled(true);
                        textView2.setAlpha(1.0f);
                        String str5 = str;
                        TextView textView3 = textView;
                        if (str5 != null) {
                            textView3.setText("检测失败，请重试");
                            return;
                        }
                        boolean z = this.f;
                        Y y = yB;
                        String str6 = str3;
                        if (z) {
                            textView3.setText("发布渠道版本：" + ((String) y.a));
                        } else if (str6 == null) {
                            textView3.setText("最新版本：" + ((String) y.a) + "\n当前版本未知");
                        } else {
                            int iC = T0.c((String) y.a, str6);
                            if (iC > 0) {
                                str4 = "发现新版本";
                            } else {
                                str4 = iC == 0 ? "当前已是最新版" : "当前版本高于发布版本";
                            }
                            textView3.setText("最新版本：" + ((String) y.a) + "\n" + str4);
                        }
                        if (z || str6 == null || T0.c((String) y.a, str6) > 0) {
                            strArr[0] = (String) y.b;
                        }
                    }
                });
                break;
        }
    }

    public /* synthetic */ RunnableC0058o1(Object obj, Activity activity, Object obj2, Object obj3, Object obj4, Object obj5, int i) {
        this.a = i;
        this.c = obj;
        this.b = activity;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
        this.g = obj5;
    }
}
