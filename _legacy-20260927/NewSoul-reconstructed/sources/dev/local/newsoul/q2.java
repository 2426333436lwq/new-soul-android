/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.DialogInterface$OnKeyListener
 *  android.graphics.Color
 *  android.graphics.Rect
 *  android.graphics.Typeface
 *  android.graphics.Typeface$Builder
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnAttachStateChangeListener
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.WindowInsets
 *  android.view.WindowInsetsController
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  android.window.OnBackInvokedDispatcher
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import dev.local.newsoul.F1;
import dev.local.newsoul.I0;
import dev.local.newsoul.L;
import dev.local.newsoul.L2;
import dev.local.newsoul.N0;
import dev.local.newsoul.c1_0;
import dev.local.newsoul.j2_0;
import dev.local.newsoul.k2_0;
import dev.local.newsoul.k_0;
import dev.local.newsoul.l2_0;
import dev.local.newsoul.m2_0;
import dev.local.newsoul.n2_0;
import dev.local.newsoul.o2_0;
import dev.local.newsoul.p2_0;
import dev.local.newsoul.s0_0;
import dev.local.newsoul.w2;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

public final class q2 {
    public final Activity a;
    public final boolean b;
    public final L2 c;
    public final AlertDialog.Builder d;
    public AlertDialog e;
    public LinearLayout f;
    public final Context g;
    public final LinearLayout h;
    public final int i;
    public final int j;
    public final int k;
    public final IdentityHashMap l = new IdentityHashMap();
    public final ArrayList m = new ArrayList();
    public final ArrayList n = new ArrayList();
    public q2 o;
    public final ArrayList p = new ArrayList();
    public boolean q;
    public BooleanSupplier r;
    public q2 s;
    public CharSequence t;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public q2(Activity activity, boolean bl) {
        AlertDialog.Builder builder;
        this.a = activity;
        this.b = bl;
        int n = bl ? -1644817 : Color.rgb((int)51, (int)51, (int)51);
        this.i = n;
        n = bl ? -14145480 : -1184275;
        int n2 = activity.getResources().getIdentifier("color_s_04", "color", activity.getPackageName());
        int n3 = n;
        if (n2 != 0) {
            try {
                n3 = activity.getResources().getColor(n2, activity.getTheme());
            }
            catch (RuntimeException runtimeException) {
                n3 = n;
            }
        }
        n = bl ? -15198168 : -1;
        n2 = bl ? -8224106 : -10066317;
        this.j = w2.q(n2, bl, n, n3);
        n2 = bl ? w2.b : -16746133;
        this.k = w2.q(n2, bl, n, n3);
        n = bl ? 16974374 : 16974394;
        this.d = builder = new AlertDialog.Builder((Context)activity, n);
        builder = builder.getContext();
        this.g = builder;
        this.c = new L2(bl, builder.getResources().getDisplayMetrics().density);
        builder = new LinearLayout((Context)builder);
        this.h = builder;
        builder.setOrientation(1);
        builder.setTag((Object)this);
        builder.setPadding(w2.i((Context)activity, 16), w2.i((Context)activity, 8), w2.i((Context)activity, 16), w2.i((Context)activity, 16));
    }

    public final void a(Runnable runnable) {
        this.m.add(runnable);
    }

    public final void b() {
        View view;
        WindowInsets windowInsets;
        if (Build.VERSION.SDK_INT >= 30 && (windowInsets = (view = this.e.getWindow().getDecorView()).getRootWindowInsets()) != null && k_0.i(windowInsets, k_0.a()) && (windowInsets = k_0.e(view)) != null) {
            k_0.g((WindowInsetsController)windowInsets, k_0.a());
            return;
        }
        this.f();
    }

    public final void c(BooleanSupplier booleanSupplier) {
        if (this.e == null) {
            this.r = booleanSupplier;
            this.d.setNegativeButton((CharSequence)"\u53d6\u6d88", (DialogInterface.OnClickListener)new L(1));
            return;
        }
        throw new IllegalStateException("Protect the draft before showing it");
    }

    public final void d() {
        Iterator iterator = this.n.iterator();
        while (iterator.hasNext()) {
            ((j2_0)((Object)iterator.next())).b();
        }
        iterator = this.m.iterator();
        while (iterator.hasNext()) {
            ((Runnable)iterator.next()).run();
        }
    }

    public final void e() {
        Object object = this.o;
        if (object != null) {
            ((q2)object).e();
            return;
        }
        this.d();
        object = this.p.iterator();
        while (object.hasNext()) {
            ((q2)object.next()).d();
        }
    }

    public final void f() {
        Activity activity;
        if (this.e.isShowing() && !(activity = this.a).isFinishing() && !activity.isDestroyed()) {
            q2 q22;
            if (!this.r.getAsBoolean()) {
                this.e.dismiss();
                return;
            }
            q2 q23 = this.s;
            if (q23 != null && q23.e.isShowing()) {
                return;
            }
            q23 = this.e.getCurrentFocus();
            this.s = q22 = new q2(activity, this.b);
            activity = q22.h;
            int n = q22.i;
            activity.addView((View)w2.n(q22.g, "\u6709\u5c1a\u672a\u4fdd\u5b58\u7684\u4fee\u6539\u3002\u653e\u5f03\u540e\u8fd9\u4e9b\u4fee\u6539\u4e0d\u4f1a\u751f\u6548\u3002", 15, n));
            q22.d.setNegativeButton((CharSequence)"\u653e\u5f03\u4fee\u6539", (DialogInterface.OnClickListener)new o2_0(this));
            q22.g("\u653e\u5f03\u672a\u4fdd\u5b58\u7684\u4fee\u6539\uff1f", "\u7ee7\u7eed\u7f16\u8f91");
            q22.e.setCanceledOnTouchOutside(false);
            q22.e.setOnDismissListener((DialogInterface.OnDismissListener)new c1_0(this, q23, 1));
        }
    }

    public final void g(String string, String string2) {
        ScrollView scrollView = new ScrollView(this.g);
        scrollView.addView((View)this.h);
        this.i(string, string2, (ViewGroup)scrollView);
    }

    public final void h(View view, String[] object, boolean bl, IntConsumer intConsumer) {
        Activity activity;
        if (view.isAttachedToWindow() && view.isShown() && !(activity = this.a).isFinishing() && !activity.isDestroyed()) {
            Context context = this.g;
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(0, w2.i(context, 4), 0, w2.i(context, 4));
            Object object2 = new ScrollView(context);
            L2 l2 = this.c;
            l2.b((View)object2, 1, 14.0f);
            object2.setClipToOutline(true);
            object2.addView((View)linearLayout);
            activity = new PopupWindow(context);
            activity.setContentView((View)object2);
            activity.setBackgroundDrawable((Drawable)new ColorDrawable(0));
            activity.setFocusable(true);
            activity.setOutsideTouchable(true);
            activity.setInputMethodMode(2);
            boolean bl2 = this.b;
            int n = bl2 ? 4 : 8;
            activity.setElevation((float)w2.i(context, n));
            int n2 = w2.i(context, 176);
            for (n = 0; n < ((String[])object).length; ++n) {
                object2 = object[n];
                int n3 = bl ? this.i : this.j;
                object2 = w2.n(context, (String)object2, 16, n3);
                object2.setGravity(8388627);
                object2.setPadding(w2.i(context, 16), w2.i(context, 12), w2.i(context, 16), w2.i(context, 12));
                object2.setMinHeight(w2.i(context, 48));
                l2.b((View)object2, 12, L2.e(12));
                object2.setEnabled(bl);
                w2.g((View)object2);
                object2.setOnClickListener((View.OnClickListener)new l2_0(this, (PopupWindow)activity, view, intConsumer, n));
                n3 = (int)Math.ceil(object2.getPaint().measureText((String)object[n]));
                n3 = Math.max(n2, w2.i(context, 32) + n3);
                linearLayout.addView((View)object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                if (n < ((Rect)object).length - 1) {
                    View view2 = new View(context);
                    n2 = bl2 ? -13224378 : -1447442;
                    view2.setBackgroundColor(n2);
                    object2 = new LinearLayout.LayoutParams(-1, w2.i(context, 1));
                    object2.rightMargin = n2 = w2.i(context, 16);
                    object2.leftMargin = n2;
                    linearLayout.addView(view2, (ViewGroup.LayoutParams)object2);
                }
                n2 = n3;
            }
            object = new Rect();
            view.getWindowVisibleDisplayFrame(object);
            n = Math.min(view.getRootView().getWidth(), object.width());
            n = Math.min(n2, Math.max(w2.i(context, 48), n - w2.i(context, 24)));
            linearLayout.measure(View.MeasureSpec.makeMeasureSpec((int)n, (int)0x40000000), View.MeasureSpec.makeMeasureSpec((int)0, (int)0));
            activity.setWidth(n);
            activity.setHeight(Math.min(linearLayout.getMeasuredHeight(), Math.max(w2.i(context, 48), object.height() - w2.i(context, 24))));
            object = new p2_0(0, activity);
            view.addOnAttachStateChangeListener((View.OnAttachStateChangeListener)object);
            activity.setOnDismissListener((PopupWindow.OnDismissListener)new m2_0(view, (p2_0)object));
            try {
                activity.showAsDropDown(view, 0, w2.i(context, 4), 0x800005);
                return;
            }
            catch (RuntimeException runtimeException) {
                view.removeOnAttachStateChangeListener((View.OnAttachStateChangeListener)object);
                throw runtimeException;
            }
        }
    }

    public final void i(String object, String object2, ViewGroup viewGroup) {
        Object object3;
        LinearLayout linearLayout;
        Context context = this.g;
        this.f = linearLayout = new LinearLayout(context);
        linearLayout.setGravity(16);
        Activity activity = this.a;
        linearLayout.setPadding(w2.i((Context)activity, 24), w2.i((Context)activity, 14), w2.i((Context)activity, 24), w2.i((Context)activity, 8));
        TextView textView = w2.n(context, (String)object, 18, this.i);
        ArrayList arrayList = this.p;
        boolean bl = arrayList.isEmpty();
        int n = 0;
        if (bl) {
            textView.setTypeface(Typeface.DEFAULT, 1);
        } else {
            try {
                object3 = new Typeface.Builder(activity.getAssets(), "fonts/tivf-bold-round.ttf");
                object3 = object3.setWeight(500).build();
            }
            catch (RuntimeException runtimeException) {
                object3 = null;
            }
            if (object3 == null) {
                object3 = Typeface.create((String)"sans-serif-medium", (int)0);
            }
            textView.setTypeface((Typeface)object3);
        }
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 28) {
            N0.b(textView);
        }
        linearLayout.addView((View)textView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
        object3 = this.t;
        if (object3 != null) {
            linearLayout.addView((View)w2.l(this, (String)object, (CharSequence)object3));
        }
        int n3 = arrayList.isEmpty() ? 58 : 70;
        linearLayout.setMinimumHeight(w2.i(context, n3));
        object = this.d.setCustomTitle((View)linearLayout).setView((View)viewGroup).setPositiveButton((CharSequence)object2, (DialogInterface.OnClickListener)new L(1)).create();
        this.e = object;
        object.show();
        if (this.r != null) {
            this.e.setCanceledOnTouchOutside(false);
            this.e.getButton(-2).setOnClickListener((View.OnClickListener)new s0_0(this, 3));
            this.e.setOnKeyListener((DialogInterface.OnKeyListener)new n2_0(this));
            if (n2 >= 33) {
                object = I0.c(this.e);
                object2 = new k2_0(this);
                I0.f((OnBackInvokedDispatcher)object, (k2_0)object2);
                this.e.getWindow().getDecorView().addOnAttachStateChangeListener((View.OnAttachStateChangeListener)new F1(object, object2, 1));
            }
        }
        object2 = this.e.getWindow();
        object = this.c;
        object.getClass();
        viewGroup = ((L2)object).c(1, L2.e(1));
        L2.i(object2.getDecorView(), (Drawable)viewGroup);
        object2.setBackgroundDrawable((Drawable)viewGroup);
        for (n3 = n; n3 < 3; ++n3) {
            n = (new int[]{-1, -2, -3})[n3];
            object2 = this.e.getButton(n);
            if (object2 == null) continue;
            ((L2)object).b((View)object2, 9, L2.e(9));
            object2.setTextColor(this.k);
            object2.setMinHeight(w2.i(context, 48));
            object2.setMinWidth(w2.i(context, 48));
        }
    }
}

