package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: dev.local.newsoul.n1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0054n1 implements Runnable {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ RunnableC0054n1(Activity activity, C0077t1 c0077t1, Object obj, boolean z, Field field, Field field2) {
        this.b = activity;
        this.d = c0077t1;
        this.e = obj;
        this.c = z;
        this.f = field;
        this.g = field2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zEquals;
        boolean zEquals2;
        switch (this.a) {
            case 0:
                boolean z = this.c;
                Field field = (Field) this.f;
                Field field2 = (Field) this.g;
                Activity activity = this.b;
                if (AbstractC0081u1.b(activity)) {
                    C0077t1 c0077t1 = (C0077t1) this.d;
                    try {
                        zEquals = c0077t1.a.equals(AbstractC0081u1.d());
                    } catch (Exception unused) {
                        zEquals = false;
                    }
                    if (zEquals) {
                        WeakHashMap weakHashMap = AbstractC0081u1.c;
                        Object obj = this.e;
                        if (weakHashMap.get(obj) == c0077t1) {
                            try {
                                c0077t1.f = z;
                                c0077t1.e = true;
                                field.set(c0077t1.d, z ? "取消插眼" : "插眼");
                                Object obj2 = field2.get(obj);
                                if (obj2 != null) {
                                    AbstractC0081u1.c(obj2, "notifyDataSetChanged");
                                }
                            } catch (Exception e) {
                                AbstractC0081u1.e(activity, e);
                                return;
                            }
                            break;
                        }
                    }
                }
                break;
            default:
                final C0067q2 c0067q2 = (C0067q2) this.d;
                if (AbstractC0081u1.i(c0067q2)) {
                    LinearLayout linearLayout = c0067q2.h;
                    linearLayout.removeAllViews();
                    final String str = (String) this.e;
                    try {
                        zEquals2 = str.equals(AbstractC0081u1.d());
                    } catch (Exception unused2) {
                        zEquals2 = false;
                    }
                    int i = c0067q2.j;
                    Context context = c0067q2.g;
                    if (zEquals2) {
                        List<C0089w1> list = (List) this.f;
                        if (list.isEmpty()) {
                            linearLayout.addView(AbstractC0090w2.n(context, "暂无插眼", 14, i));
                        }
                        for (final C0089w1 c0089w1 : list) {
                            final TextView textViewA = AbstractC0081u1.a(c0067q2, c0089w1.b);
                            textViewA.setContentDescription(c0089w1.b + "，打开主页；长按移除插眼");
                            final Activity activity2 = this.b;
                            textViewA.setOnClickListener(new D0(str, c0089w1, c0067q2, activity2, 1));
                            final boolean z2 = this.c;
                            final Runnable[] runnableArr = (Runnable[]) this.g;
                            textViewA.setOnLongClickListener(new View.OnLongClickListener() { // from class: dev.local.newsoul.q1
                                @Override // android.view.View.OnLongClickListener
                                public final boolean onLongClick(View view) {
                                    final Activity activity3 = activity2;
                                    final TextView textView = textViewA;
                                    final String str2 = str;
                                    final C0089w1 c0089w2 = c0089w1;
                                    final C0067q2 c0067q3 = c0067q2;
                                    final Runnable[] runnableArr2 = runnableArr;
                                    new C0067q2(activity3, z2).h(textView, new String[]{"移除插眼"}, true, new IntConsumer() { // from class: dev.local.newsoul.r1
                                        @Override // java.util.function.IntConsumer
                                        public final void accept(int i2) {
                                            TextView textView2 = textView;
                                            textView2.setEnabled(false);
                                            AbstractC0081u1.b.execute(new RunnableC0058o1(str2, activity3, c0089w2, c0067q3, runnableArr2, textView2, 1));
                                        }
                                    });
                                    return true;
                                }
                            });
                            AbstractC0090w2.b(c0067q2, linearLayout, textViewA);
                        }
                    } else {
                        linearLayout.addView(AbstractC0090w2.n(context, "账号已切换，请重新打开列表", 14, i));
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ RunnableC0054n1(C0067q2 c0067q2, String str, ArrayList arrayList, Activity activity, boolean z, Runnable[] runnableArr) {
        this.d = c0067q2;
        this.e = str;
        this.f = arrayList;
        this.b = activity;
        this.c = z;
        this.g = runnableArr;
    }
}
