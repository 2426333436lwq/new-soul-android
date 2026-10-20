package dev.local.newsoul;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class I2 {
    public final Activity a;
    public final View b;
    public final E2 c;
    public ViewTreeObserver d;
    public final int f;
    public final int g;
    public final int h;
    public View k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public W0 s;
    public final F2 u;
    public final F2 v;
    public final F2 w;
    public final M1 e = new M1(1, this);
    public final HashMap i = new HashMap();
    public final ArrayList j = new ArrayList();
    public final HashSet t = new HashSet();

    /* JADX WARN: Type inference failed for: r0v4, types: [dev.local.newsoul.F2] */
    /* JADX WARN: Type inference failed for: r0v5, types: [dev.local.newsoul.F2] */
    /* JADX WARN: Type inference failed for: r0v6, types: [dev.local.newsoul.F2] */
    public I2(Activity activity, View view) {
        final int i = 0;
        this.u = new F2(this, i);
        final int i2 = 1;
        this.v = new F2(this, i2);
        final int i3 = 2;
        this.w = new F2(this, i3);
        this.a = activity;
        this.b = view;
        this.f = activity.getResources().getIdentifier("pager_square", "id", activity.getPackageName());
        this.g = activity.getResources().getIdentifier("key_item_post", "id", activity.getPackageName());
        this.h = activity.getResources().getIdentifier("iv_like", "id", activity.getPackageName());
        E2 e2 = new E2(activity, this);
        this.c = e2;
        e2.setVisibility(8);
        e2.addOnAttachStateChangeListener(new ViewOnAttachStateChangeListenerC0063p2(1, this));
        e2.setOnClickListener(new A0(4, this));
        e2.setOnLongClickListener(new ViewOnLongClickListenerC0052n(1, this));
    }

    public final boolean a() {
        if (Q0.B) {
            return b() || AbstractC0090w2.o(this.a).getBoolean("square_like_all_loaded", false);
        }
        return false;
    }

    public final boolean b() {
        return Q0.B && AbstractC0090w2.o(this.a).getBoolean("square_auto_like", false);
    }

    public final void c(View view, ArrayList arrayList, HashSet hashSet) {
        if (Q0.b(view)) {
            Object tag = view.getTag(this.g);
            if (!Q0.Z.isInstance(tag)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        c(viewGroup.getChildAt(i), arrayList, hashSet);
                    }
                    return;
                }
                return;
            }
            try {
                long j = Q0.a0.getLong(tag);
                View viewFindViewById = view.findViewById(this.h);
                if (j > 0 && Q0.a(tag) && B2.a(this.a, tag) && !((Boolean) Q0.b0.invoke(tag, null)).booleanValue() && !this.i.containsKey(Long.valueOf(j)) && !this.t.contains(Long.valueOf(j)) && viewFindViewById != null && viewFindViewById.isShown() && viewFindViewById.isEnabled() && viewFindViewById.hasOnClickListeners() && hashSet.add(Long.valueOf(j))) {
                    arrayList.add(new J2(view, viewFindViewById, tag, j));
                }
            } catch (ReflectiveOperationException e) {
                Log.w("SoulHook102", "square like row skipped", e);
            }
        }
    }

    public final void d(String str) {
        boolean z = this.r;
        Activity activity = this.a;
        if (z) {
            AbstractC0090w2.o(activity).edit().putBoolean("square_auto_like", false).apply();
            str = str + "，自动点赞已关闭";
        }
        e(false);
        Toast.makeText(activity, str, 1).show();
    }

    public final void e(boolean z) {
        StringBuilder sb;
        String str;
        E2 e2 = this.c;
        e2.removeCallbacks(this.v);
        e2.removeCallbacks(this.w);
        if (this.m) {
            Log.i("SoulHook102", "square like batch ended: submitted=" + this.o);
            if (z && !this.r) {
                if (this.q) {
                    int i = this.o;
                    sb = new StringBuilder("本批已成功点赞 ");
                    sb.append(i);
                    str = " 条";
                } else {
                    int i2 = this.o;
                    sb = new StringBuilder("已触发 ");
                    sb.append(i2);
                    str = " 条点赞，结果以帖子状态为准";
                }
                sb.append(str);
                Toast.makeText(this.a, sb.toString(), 0).show();
            }
        }
        this.m = false;
        this.p++;
        this.s = null;
        this.j.clear();
        this.k = null;
        e2.setEnabled(true);
    }

    public final void f() {
        if (this.m) {
            if (this.r && !b()) {
                e(false);
                return;
            }
            if (g() == this.k && a() == this.q) {
                int i = this.n;
                ArrayList arrayList = this.j;
                if (i < arrayList.size()) {
                    boolean z = this.q;
                    HashMap map = this.i;
                    F2 f2 = this.v;
                    Activity activity = this.a;
                    E2 e2 = this.c;
                    if (z) {
                        HashSet hashSet = this.t;
                        if (!j()) {
                            e(true);
                            return;
                        }
                        int i2 = this.n;
                        this.n = i2 + 1;
                        J2 j2 = (J2) arrayList.get(i2);
                        int i3 = this.p;
                        try {
                            W0 w0 = this.s;
                            Object obj = j2.c;
                            w0.getClass();
                            Iterator it = new ArrayList((List) Q0.H.invoke(w0.b, null)).iterator();
                            while (it.hasNext()) {
                                if (it.next() == obj) {
                                    long j = Q0.a0.getLong(obj);
                                    long j3 = j2.d;
                                    if (j != j3 || !Q0.a(obj) || !B2.a(activity, obj) || ((Boolean) Q0.b0.invoke(obj, null)).booleanValue() || map.containsKey(Long.valueOf(j3)) || hashSet.contains(Long.valueOf(j3))) {
                                        break;
                                    }
                                    map.put(Long.valueOf(j3), Long.valueOf(SystemClock.elapsedRealtime()));
                                    hashSet.add(Long.valueOf(j3));
                                    e2.a((arrayList.size() - this.n) + 1, true);
                                    e2.postDelayed(this.w, 30000L);
                                    Q0.W(activity, this.s, obj, new H2(this, j2, i3));
                                    return;
                                }
                            }
                            e2.postDelayed(f2, 500L);
                            return;
                        } catch (Throwable th) {
                            hashSet.remove(Long.valueOf(j2.d));
                            Log.w("SoulHook102", "loaded like failed; batch stopped", th);
                            d("点赞未完成，已停止");
                            return;
                        }
                    }
                    int i4 = this.n;
                    this.n = i4 + 1;
                    J2 j4 = (J2) arrayList.get(i4);
                    try {
                        View view = j4.a;
                        View view2 = j4.b;
                        if (Q0.b(view)) {
                            View view3 = this.k;
                            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                                if (parent == view3) {
                                    Object tag = view.getTag(this.g);
                                    Object obj2 = j4.c;
                                    if (tag != obj2) {
                                        break;
                                    }
                                    long j5 = Q0.a0.getLong(obj2);
                                    long j6 = j4.d;
                                    if (j5 != j6 || !Q0.a(obj2) || !B2.a(activity, obj2) || ((Boolean) Q0.b0.invoke(obj2, null)).booleanValue() || !view2.isShown() || !view2.isEnabled() || map.containsKey(Long.valueOf(j6))) {
                                        break;
                                    }
                                    map.put(Long.valueOf(j6), Long.valueOf(SystemClock.elapsedRealtime()));
                                    if (view2.performClick()) {
                                        this.o++;
                                        break;
                                    } else {
                                        map.remove(Long.valueOf(j6));
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        Log.w("SoulHook102", "square like click failed; not retried", th2);
                    }
                    e2.a(arrayList.size() - this.n, true);
                    e2.postDelayed(f2, 500L);
                    return;
                }
            }
            e(true);
        }
    }

    public final View g() {
        int i;
        if (!this.l) {
            return null;
        }
        Activity activity = this.a;
        if (activity.isFinishing() || activity.isDestroyed() || !activity.hasWindowFocus() || (i = this.f) == 0 || this.g == 0 || this.h == 0) {
            return null;
        }
        if ((!AbstractC0090w2.o(activity).getBoolean("square_visible_like", true) && !b()) || !Q0.b(this.b)) {
            return null;
        }
        View viewFindViewById = activity.findViewById(i);
        if (Q0.b(viewFindViewById)) {
            return viewFindViewById;
        }
        return null;
    }

    public final void h() {
        this.l = false;
        ViewTreeObserver viewTreeObserver = this.d;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            this.d.removeOnPreDrawListener(this.e);
        }
        this.d = null;
        E2 e2 = this.c;
        e2.removeCallbacks(this.u);
        e(false);
        e2.setVisibility(8);
    }

    public final void i() {
        this.l = true;
        ViewTreeObserver viewTreeObserver = this.d;
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            ViewTreeObserver viewTreeObserver2 = this.b.getViewTreeObserver();
            this.d = viewTreeObserver2;
            viewTreeObserver2.addOnPreDrawListener(this.e);
        }
        m();
        E2 e2 = this.c;
        F2 f2 = this.u;
        e2.removeCallbacks(f2);
        e2.post(f2);
    }

    public final boolean j() {
        W0 w0;
        W0 w0T = Q0.t(g());
        return w0T != null && (w0 = this.s) != null && w0T.a == w0.a && w0T.b == w0.b;
    }

    public final ArrayList k() {
        ArrayList arrayList = new ArrayList();
        View viewG = g();
        HashMap map = this.i;
        map.entrySet().removeIf(new G2());
        if (viewG != null && a()) {
            W0 w0T = Q0.t(viewG);
            HashSet hashSet = new HashSet();
            if (w0T != null) {
                try {
                    for (Object obj : new ArrayList((List) Q0.H.invoke(w0T.b, null))) {
                        if (Q0.Z.isInstance(obj)) {
                            long j = Q0.a0.getLong(obj);
                            if (j > 0 && Q0.a(obj) && B2.a(this.a, obj) && !((Boolean) Q0.b0.invoke(obj, null)).booleanValue() && !map.containsKey(Long.valueOf(j)) && !this.t.contains(Long.valueOf(j)) && hashSet.add(Long.valueOf(j))) {
                                arrayList.add(new J2(null, null, obj, j));
                            }
                        }
                    }
                } catch (ReflectiveOperationException e) {
                    Log.w("SoulHook102", "loaded like snapshot unavailable", e);
                }
            }
        } else if (viewG != null) {
            c(viewG, arrayList, new HashSet());
        }
        return arrayList;
    }

    public final void l(boolean z) {
        ArrayList arrayList = this.j;
        arrayList.clear();
        arrayList.addAll(k());
        if (arrayList.isEmpty()) {
            if (z) {
                return;
            }
            Toast.makeText(this.a, "当前范围内没有可点赞的帖子", 0).show();
            return;
        }
        this.k = g();
        boolean zA = a();
        this.q = zA;
        this.r = z;
        this.s = zA ? Q0.t(this.k) : null;
        this.p++;
        this.o = 0;
        this.n = 0;
        this.m = true;
        this.c.setEnabled(true);
        String str = this.q ? "loaded" : "visible";
        Log.i("SoulHook102", "square like batch started: scope=" + str + "; count=" + arrayList.size());
        f();
    }

    public final void m() {
        boolean z = g() != null;
        if (this.m && this.r && !b()) {
            e(false);
        }
        if (this.m && this.q && !j()) {
            e(false);
        }
        if (!z && this.m) {
            e(false);
        }
        int i = z ? 0 : 8;
        E2 e2 = this.c;
        if (e2.getVisibility() != i) {
            e2.setVisibility(i);
        }
        float alpha = e2.getAlpha();
        View view = this.b;
        if (alpha != view.getAlpha()) {
            e2.setAlpha(view.getAlpha());
        }
        if (e2.getTranslationX() != view.getTranslationX()) {
            e2.setTranslationX(view.getTranslationX());
        }
        if (e2.getTranslationY() != view.getTranslationY()) {
            e2.setTranslationY(view.getTranslationY());
        }
    }
}
