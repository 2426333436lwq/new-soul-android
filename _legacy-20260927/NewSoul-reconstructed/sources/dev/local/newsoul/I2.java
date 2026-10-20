/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.SystemClock
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;
import dev.local.newsoul.A0;
import dev.local.newsoul.B2;
import dev.local.newsoul.E2;
import dev.local.newsoul.F2;
import dev.local.newsoul.J2;
import dev.local.newsoul.M1;
import dev.local.newsoul.Q0;
import dev.local.newsoul.W0;
import dev.local.newsoul.h2_0;
import dev.local.newsoul.n_0;
import dev.local.newsoul.p2_0;
import dev.local.newsoul.w2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class I2 {
    public final Activity a;
    public final View b;
    public final E2 c;
    public ViewTreeObserver d;
    public final M1 e = new M1(1, this);
    public final int f;
    public final int g;
    public final int h;
    public final HashMap i = new HashMap();
    public final ArrayList j = new ArrayList();
    public View k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public W0 s;
    public final HashSet t = new HashSet();
    public final F2 u = new F2(this, 0);
    public final F2 v = new F2(this, 1);
    public final F2 w = new F2(this, 2);

    public I2(Activity object, View view) {
        this.a = object;
        this.b = view;
        this.f = object.getResources().getIdentifier("pager_square", "id", object.getPackageName());
        this.g = object.getResources().getIdentifier("key_item_post", "id", object.getPackageName());
        this.h = object.getResources().getIdentifier("iv_like", "id", object.getPackageName());
        object = new E2((Context)object, this);
        this.c = object;
        object.setVisibility(8);
        object.addOnAttachStateChangeListener((View.OnAttachStateChangeListener)new p2_0(1, this));
        object.setOnClickListener((View.OnClickListener)new A0(4, this));
        object.setOnLongClickListener((View.OnLongClickListener)new n_0(1, this));
    }

    public final boolean a() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2;
                boolean bl3 = Q0.B;
                bl = bl2 = false;
                if (!bl3) break block2;
                if (this.b()) break block3;
                bl = bl2;
                if (!w2.o((Context)this.a).getBoolean("square_like_all_loaded", false)) break block2;
            }
            bl = true;
        }
        return bl;
    }

    public final boolean b() {
        boolean bl;
        boolean bl2 = Q0.B;
        boolean bl3 = bl = false;
        if (bl2) {
            bl3 = bl;
            if (w2.o((Context)this.a).getBoolean("square_auto_like", false)) {
                bl3 = true;
            }
        }
        return bl3;
    }

    public final void c(View view, ArrayList arrayList, HashSet object) {
        if (!Q0.b(view)) {
            return;
        }
        Object object2 = view.getTag(this.g);
        if (Q0.Z.isInstance(object2)) {
            block9: {
                long l = Q0.a0.getLong(object2);
                View view2 = view.findViewById(this.h);
                if (l <= 0L) break block9;
                if (!Q0.a(object2) || !B2.a((Context)this.a, object2) || ((Boolean)Q0.b0.invoke(object2, null)).booleanValue() || this.i.containsKey(l) || this.t.contains(l) || view2 == null) break block9;
                try {
                    if (view2.isShown() && view2.isEnabled() && view2.hasOnClickListeners() && ((HashSet)object).add(l)) {
                        object = new J2(view, view2, object2, l);
                        arrayList.add(object);
                    }
                }
                catch (ReflectiveOperationException reflectiveOperationException) {
                    Log.w((String)"SoulHook102", (String)"square like row skipped", (Throwable)reflectiveOperationException);
                }
            }
            return;
        }
        if (view instanceof ViewGroup) {
            view = (ViewGroup)view;
            for (int i = 0; i < view.getChildCount(); ++i) {
                this.c(view.getChildAt(i), arrayList, (HashSet)object);
            }
        }
    }

    public final void d(String string) {
        boolean bl = this.r;
        Activity activity = this.a;
        CharSequence charSequence = string;
        if (bl) {
            w2.o((Context)activity).edit().putBoolean("square_auto_like", false).apply();
            charSequence = new StringBuilder();
            ((StringBuilder)charSequence).append(string);
            ((StringBuilder)charSequence).append("\uff0c\u81ea\u52a8\u70b9\u8d5e\u5df2\u5173\u95ed");
            charSequence = ((StringBuilder)charSequence).toString();
        }
        this.e(false);
        Toast.makeText((Context)activity, (CharSequence)charSequence, (int)1).show();
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public final void e(boolean bl) {
        E2 e2 = this.c;
        e2.removeCallbacks(this.v);
        e2.removeCallbacks(this.w);
        if (this.m) {
            int n = this.o;
            StringBuilder stringBuilder = new StringBuilder("square like batch ended: submitted=");
            stringBuilder.append(n);
            Log.i((String)"SoulHook102", (String)stringBuilder.toString());
            if (bl && !this.r) {
                void var3_6;
                StringBuilder stringBuilder2;
                if (this.q) {
                    n = this.o;
                    stringBuilder2 = new StringBuilder("\u672c\u6279\u5df2\u6210\u529f\u70b9\u8d5e ");
                    stringBuilder2.append(n);
                    String string = " \u6761";
                } else {
                    n = this.o;
                    stringBuilder2 = new StringBuilder("\u5df2\u89e6\u53d1 ");
                    stringBuilder2.append(n);
                    String string = " \u6761\u70b9\u8d5e\uff0c\u7ed3\u679c\u4ee5\u5e16\u5b50\u72b6\u6001\u4e3a\u51c6";
                }
                stringBuilder2.append((String)var3_6);
                String string = stringBuilder2.toString();
                Toast.makeText((Context)this.a, (CharSequence)string, (int)0).show();
            }
        }
        this.m = false;
        ++this.p;
        this.s = null;
        this.j.clear();
        this.k = null;
        e2.setEnabled(true);
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void f() {
        block23: {
            E2 e2;
            Object object;
            Object object2;
            block22: {
                Throwable throwable3;
                block21: {
                    View view;
                    View view2;
                    Object object3;
                    int n;
                    if (!this.m) {
                        return;
                    }
                    if (this.r && !this.b()) {
                        this.e(false);
                        return;
                    }
                    if (this.g() != this.k || this.a() != this.q || (n = this.n) >= ((ArrayList)(object2 = this.j)).size()) break block23;
                    boolean bl = this.q;
                    HashMap hashMap = this.i;
                    object = this.v;
                    Activity activity = this.a;
                    e2 = this.c;
                    if (bl) {
                        Throwable throwable22;
                        J2 j2;
                        HashSet hashSet;
                        block20: {
                            block18: {
                                long l;
                                Object object4;
                                block19: {
                                    hashSet = this.t;
                                    if (!this.j()) {
                                        this.e(true);
                                        return;
                                    }
                                    n = this.n;
                                    this.n = n + 1;
                                    j2 = (J2)((ArrayList)object2).get(n);
                                    n = this.p;
                                    try {
                                        W0 w0 = this.s;
                                        object4 = j2.c;
                                        w0.getClass();
                                        Object object5 = new ArrayList((List)Q0.H.invoke(w0.b, null));
                                        object5 = ((ArrayList)object5).iterator();
                                        while (object5.hasNext()) {
                                            if (object5.next() != object4) continue;
                                            long l2 = Q0.a0.getLong(object4);
                                            if (l2 != (l = j2.d)) break block18;
                                            break block19;
                                        }
                                        break block18;
                                    }
                                    catch (Throwable throwable22) {
                                        break block20;
                                    }
                                }
                                if (Q0.a(object4) && B2.a((Context)activity, object4) && !((Boolean)Q0.b0.invoke(object4, null)).booleanValue() && !hashMap.containsKey(l) && !hashSet.contains(l)) {
                                    hashMap.put(l, SystemClock.elapsedRealtime());
                                    hashSet.add(l);
                                    e2.a(((ArrayList)object2).size() - this.n + 1, true);
                                    e2.postDelayed(this.w, 30000L);
                                    object2 = this.s;
                                    object = new h2_0(this, j2, n);
                                    Q0.W(activity, (W0)object2, object4, (h2_0)object);
                                    return;
                                }
                            }
                            e2.postDelayed((Runnable)object, 500L);
                            return;
                        }
                        hashSet.remove(j2.d);
                        Log.w((String)"SoulHook102", (String)"loaded like failed; batch stopped", (Throwable)throwable22);
                        this.d("\u70b9\u8d5e\u672a\u5b8c\u6210\uff0c\u5df2\u505c\u6b62");
                        return;
                    }
                    n = this.n;
                    this.n = n + 1;
                    J2 j2 = (J2)((ArrayList)object2).get(n);
                    try {
                        object3 = j2.a;
                        view2 = j2.b;
                    }
                    catch (Throwable throwable3) {
                        break block21;
                    }
                    {
                        if (!Q0.b(object3)) break block22;
                        view = this.k;
                    }
                    for (Object object6 = object3.getParent(); object6 != null; object6 = object6.getParent()) {
                        long l;
                        long l3;
                        if (object6 != view) continue;
                        if ((object3 = object3.getTag(this.g)) != (object6 = j2.c) || (l3 = Q0.a0.getLong(object6)) != (l = j2.d) || !Q0.a(object6) || !B2.a((Context)activity, object6) || ((Boolean)Q0.b0.invoke(object6, null)).booleanValue() || !view2.isShown() || !view2.isEnabled() || hashMap.containsKey(l)) break block22;
                        hashMap.put(l, SystemClock.elapsedRealtime());
                        if (view2.performClick()) {
                            ++this.o;
                            break block22;
                        } else {
                            hashMap.remove(l);
                        }
                        break block22;
                    }
                    break block22;
                }
                Log.w((String)"SoulHook102", (String)"square like click failed; not retried", (Throwable)throwable3);
            }
            e2.a(((ArrayList)object2).size() - this.n, true);
            e2.postDelayed((Runnable)object, 500L);
            return;
        }
        this.e(true);
    }

    public final View g() {
        Activity activity;
        boolean bl = this.l;
        Activity activity2 = activity = null;
        if (bl) {
            Activity activity3 = this.a;
            activity2 = activity;
            if (!activity3.isFinishing()) {
                activity2 = activity;
                if (!activity3.isDestroyed()) {
                    activity2 = activity;
                    if (activity3.hasWindowFocus()) {
                        int n = this.f;
                        activity2 = activity;
                        if (n != 0) {
                            activity2 = activity;
                            if (this.g != 0) {
                                activity2 = activity;
                                if (this.h != 0) {
                                    if (!w2.o((Context)activity3).getBoolean("square_visible_like", true) && !this.b()) {
                                        activity2 = activity;
                                    } else {
                                        if (!Q0.b(this.b)) {
                                            return null;
                                        }
                                        activity3 = activity3.findViewById(n);
                                        activity2 = activity;
                                        if (Q0.b((View)activity3)) {
                                            activity2 = activity3;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return activity2;
    }

    public final void h() {
        this.l = false;
        Object object = this.d;
        if (object != null && object.isAlive()) {
            this.d.removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this.e);
        }
        this.d = null;
        object = this.c;
        object.removeCallbacks((Runnable)this.u);
        this.e(false);
        object.setVisibility(8);
    }

    public final void i() {
        this.l = true;
        Object object = this.d;
        if (object == null || !object.isAlive()) {
            this.d = object = this.b.getViewTreeObserver();
            object.addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this.e);
        }
        this.m();
        object = this.c;
        F2 f2 = this.u;
        object.removeCallbacks((Runnable)f2);
        object.post((Runnable)f2);
    }

    public final boolean j() {
        W0 w0;
        W0 w02 = Q0.t(this.g());
        boolean bl = w02 != null && (w0 = this.s) != null && w02.a == w0.a && w02.b == w0.b;
        return bl;
    }

    public final ArrayList k() {
        ArrayList<Object> arrayList;
        block8: {
            arrayList = new ArrayList<Object>();
            Object object = this.g();
            HashMap hashMap = this.i;
            hashMap.entrySet().removeIf(new Object());
            if (object != null && this.a()) {
                Object object2 = Q0.t((View)object);
                object = new HashSet();
                if (object2 != null) {
                    long l;
                    Object e;
                    Object object3 = new ArrayList((List)Q0.H.invoke(((W0)object2).b, null));
                    object2 = ((ArrayList)object3).iterator();
                    while (object2.hasNext()) {
                        e = object2.next();
                        if (!Q0.Z.isInstance(e) || (l = Q0.a0.getLong(e)) <= 0L) continue;
                    }
                    break block8;
                    {
                        try {
                            if (!Q0.a(e) || !B2.a((Context)this.a, e) || ((Boolean)Q0.b0.invoke(e, null)).booleanValue() || hashMap.containsKey(l) || this.t.contains(l) || !((HashSet)object).add(l)) continue;
                            object3 = new J2(null, null, e, l);
                            arrayList.add(object3);
                            continue;
                        }
                        catch (ReflectiveOperationException reflectiveOperationException) {
                            Log.w((String)"SoulHook102", (String)"loaded like snapshot unavailable", (Throwable)reflectiveOperationException);
                            break block8;
                            break;
                        }
                    }
                }
            } else if (object != null) {
                this.c((View)object, arrayList, new HashSet());
            }
        }
        return arrayList;
    }

    public final void l(boolean bl) {
        boolean bl2;
        Serializable serializable = this.j;
        ((ArrayList)serializable).clear();
        ((ArrayList)serializable).addAll(this.k());
        if (((ArrayList)serializable).isEmpty()) {
            if (!bl) {
                Toast.makeText((Context)this.a, (CharSequence)"\u5f53\u524d\u8303\u56f4\u5185\u6ca1\u6709\u53ef\u70b9\u8d5e\u7684\u5e16\u5b50", (int)0).show();
            }
            return;
        }
        this.k = this.g();
        this.q = bl2 = this.a();
        this.r = bl;
        Object object = bl2 ? Q0.t(this.k) : null;
        this.s = object;
        ++this.p;
        this.o = 0;
        this.n = 0;
        this.m = true;
        this.c.setEnabled(true);
        object = this.q ? "loaded" : "visible";
        int n = ((ArrayList)serializable).size();
        serializable = new StringBuilder("square like batch started: scope=");
        ((StringBuilder)serializable).append((String)object);
        ((StringBuilder)serializable).append("; count=");
        ((StringBuilder)serializable).append(n);
        Log.i((String)"SoulHook102", (String)((StringBuilder)serializable).toString());
        this.f();
    }

    public final void m() {
        View view;
        float f;
        View view2 = this.g();
        int n = 0;
        int n2 = view2 != null ? 1 : 0;
        if (this.m && this.r && !this.b()) {
            this.e(false);
        }
        if (this.m && this.q && !this.j()) {
            this.e(false);
        }
        if (n2 == 0 && this.m) {
            this.e(false);
        }
        n2 = n2 != 0 ? n : 8;
        view2 = this.c;
        if (view2.getVisibility() != n2) {
            view2.setVisibility(n2);
        }
        if ((f = view2.getAlpha()) != (view = this.b).getAlpha()) {
            view2.setAlpha(view.getAlpha());
        }
        if (view2.getTranslationX() != view.getTranslationX()) {
            view2.setTranslationX(view.getTranslationX());
        }
        if (view2.getTranslationY() != view.getTranslationY()) {
            view2.setTranslationY(view.getTranslationY());
        }
    }
}

