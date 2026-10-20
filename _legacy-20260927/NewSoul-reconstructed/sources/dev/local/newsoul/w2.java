/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.res.ColorStateList
 *  android.graphics.Color
 *  android.graphics.Typeface
 *  android.graphics.drawable.RippleDrawable
 *  android.os.Build$VERSION
 *  android.os.SystemClock
 *  android.text.TextUtils
 *  android.util.Log
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$AccessibilityDelegate
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageButton
 *  android.widget.ImageView$ScaleType
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ProgressBar
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import dev.local.newsoul.C0;
import dev.local.newsoul.G1;
import dev.local.newsoul.K1;
import dev.local.newsoul.L2;
import dev.local.newsoul.N0;
import dev.local.newsoul.N1;
import dev.local.newsoul.O1;
import dev.local.newsoul.S1;
import dev.local.newsoul.T1;
import dev.local.newsoul.U1;
import dev.local.newsoul.V1;
import dev.local.newsoul.Z1;
import dev.local.newsoul.f2_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.h2;
import dev.local.newsoul.i2_0;
import dev.local.newsoul.m1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.q_0;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.v2_0;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;

public abstract class w2 {
    public static final WeakHashMap a = new WeakHashMap();
    public static final int b = Color.rgb((int)0, (int)181, (int)162);

    public static void a(q2 q22, LinearLayout linearLayout, TextView textView, TextView textView2) {
        Context context = q22.g;
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        w2.x(q22, textView);
        w2.x(q22, textView2);
        linearLayout2.addView((View)textView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
        textView = new View(context);
        int n = q22.b ? -12961206 : -2302750;
        textView.setBackgroundColor(n);
        textView.setImportantForAccessibility(2);
        linearLayout2.addView((View)textView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i(context, 1), w2.i(context, 28)));
        linearLayout2.addView((View)textView2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView((View)linearLayout2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
    }

    public static void b(q2 q22, LinearLayout linearLayout, TextView textView) {
        int n;
        L2 l2 = q22.c;
        l2.getClass();
        l2.b((View)textView, 4, L2.e(4));
        q22 = q22.g;
        textView.setMinHeight(w2.i((Context)q22, 56));
        textView.setPadding(w2.i((Context)q22, 16), w2.i((Context)q22, 14), w2.i((Context)q22, 16), w2.i((Context)q22, 14));
        l2 = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)l2).rightMargin = n = w2.i((Context)q22, 2);
        ((LinearLayout.LayoutParams)l2).leftMargin = n;
        n = linearLayout.getChildCount() == 0 ? 2 : 8;
        ((LinearLayout.LayoutParams)l2).topMargin = w2.i((Context)q22, n);
        ((LinearLayout.LayoutParams)l2).bottomMargin = w2.i((Context)q22, 2);
        linearLayout.addView((View)textView, (ViewGroup.LayoutParams)l2);
    }

    public static TextView c(LinearLayout linearLayout, String string) {
        q2 q22 = (q2)linearLayout.getTag();
        string = w2.n(linearLayout.getContext(), string, 13, q22.j);
        string.setPadding(0, w2.i(linearLayout.getContext(), 4), 0, w2.i(linearLayout.getContext(), 8));
        string.setLineSpacing((float)w2.i(linearLayout.getContext(), 2), 1.0f);
        linearLayout.addView((View)string, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        return string;
    }

    public static r2_0 d(LinearLayout linearLayout, SharedPreferences object, String object2, String object3, String string, int n) {
        Context context = linearLayout.getContext();
        r2_0 r2_02 = new r2_0(context);
        r2_02.setContentDescription((CharSequence)object3);
        r2_02.setPadding(w2.i(context, 4), 0, 0, 0);
        r2_02.setMinWidth(w2.i(context, 48));
        r2_02.setMinHeight(w2.i(context, 48));
        boolean bl = true;
        boolean bl2 = object != null && !((String)object2).startsWith("ad_") && !((String)object2).startsWith("privacy_") && !((String)object2).equals("square_like_all_loaded") && !((String)object2).equals("square_auto_like") && !((String)object2).equals("feed_keywords_enabled_v1") && !((String)object2).equals("feed_filter_enabled") && !((String)object2).equals("feed_filter_keep_unknown");
        if (object == null || !object.getBoolean((String)object2, bl2)) {
            bl = false;
        }
        r2_02.setChecked(bl);
        q2 q22 = (q2)linearLayout.getTag();
        L2 l2 = q22.c;
        l2.getClass();
        Object object4 = new int[][]{{-16842910, 0x10100A0}, {-16842910}, {0x10100A0}, new int[0]};
        bl = l2.a;
        int n2 = bl ? -11111569 : -6636105;
        int n3 = bl ? -10329489 : -4735550;
        r2_02.setThumbTintList(new ColorStateList((int[][])object4, new int[]{n2, n3, -16730718, -6576982}));
        n2 = bl ? -13285051 : -2956578;
        n3 = bl ? -12895415 : -2104860;
        int n4 = bl ? -13080993 : -6431791;
        int n5 = bl ? -11513760 : -3748912;
        r2_02.setTrackTintList(new ColorStateList((int[][])object4, new int[]{n2, n3, n4, n5}));
        l2 = new LinearLayout(context);
        l2.setGravity(16);
        l2.setMinimumHeight(w2.i(context, 52));
        object4 = string.contains("\u672a\u5c31\u7eea") ? (Object)"\uff08\u672a\u5c31\u7eea\uff09" : (Object)"";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)object3);
        stringBuilder.append((String)object4);
        object4 = w2.n(context, stringBuilder.toString(), 16, n);
        object4.setMinHeight(w2.i(context, 48));
        object4.setGravity(16);
        object4.setImportantForAccessibility(2);
        object4.setFocusable(false);
        l2.setImportantForAccessibility(2);
        object4.setOnClickListener((View.OnClickListener)new S1(r2_02, 0));
        object4.setFocusable(false);
        l2.setOnClickListener(new S1(r2_02, 1));
        l2.setFocusable(false);
        w2.p((View)object4);
        w2.p((View)l2);
        l2.addView((View)object4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
        string = string.replace("\u5f53\u524d\u7248\u672c\u672a\u5c31\u7eea", "").replace("\u672a\u5c31\u7eea", "").replaceAll("[\\s\u00b7]+$", "").trim();
        if (!string.isEmpty()) {
            l2.addView((View)w2.l(q22, (String)object3, string));
        }
        l2.addView((View)r2_02, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView((View)l2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        r2_02.setOnCheckedChangeListener(new U1((SharedPreferences)object, r2_02, (String)object2));
        if (object != null) {
            q22.m.add(new q_0(r2_02, (SharedPreferences)object, (String)object2, bl2));
        }
        r2_02.a(new m1_0(object4, (Object)r2_02, l2, object3, object, 4));
        if (object == null) {
            return r2_02;
        }
        object3 = q22.l;
        object2 = (h2)((IdentityHashMap)object3).get(linearLayout);
        object = object2;
        if (object2 == null) {
            object = new h2(q22, linearLayout);
            ((IdentityHashMap)object3).put(linearLayout, object);
        }
        object.a.add(r2_02);
        r2_02.a(new V1((h2)object, 0));
        return r2_02;
    }

    public static void e(r2_0 r2_02, q2 object, Runnable runnable) {
        LinearLayout linearLayout = (LinearLayout)r2_02.getParent();
        Context context = ((q2)object).g;
        TextView textView = w2.n(context, "\u9884\u89c8", 14, ((q2)object).k);
        textView.setMinWidth(w2.i(context, 48));
        textView.setMinHeight(w2.i(context, 48));
        textView.setGravity(17);
        textView.setContentDescription((CharSequence)"\u9884\u89c8".concat(String.valueOf(r2_02.getContentDescription())));
        object = ((q2)object).c;
        object.getClass();
        ((L2)object).b((View)textView, 10, L2.e(10));
        textView.setOnClickListener((View.OnClickListener)new C0((Object)r2_02, runnable, 3));
        w2.g((View)textView);
        object = new LinearLayout.LayoutParams(-2, -2);
        ((LinearLayout.LayoutParams)object).leftMargin = w2.i(context, 8);
        ((LinearLayout.LayoutParams)object).rightMargin = w2.i(context, 4);
        linearLayout.addView((View)textView, linearLayout.indexOfChild((View)r2_02), (ViewGroup.LayoutParams)object);
        r2_02.a(new g_0(textView, (Object)r2_02, 10));
    }

    public static void f(q2 q22, r2_0 object, BooleanSupplier booleanSupplier) {
        object = new g_0(object, booleanSupplier, 9);
        ((g_0)object).run();
        q22.a((Runnable)object);
    }

    public static void g(View view) {
        view.setFocusable(true);
        w2.p(view);
        view.setAccessibilityDelegate(new View.AccessibilityDelegate());
    }

    public static boolean h(View view) {
        if (view.isAttachedToWindow()) {
            View view2 = view;
            if (view.isShown()) {
                while (view2 != null) {
                    if (view2.getImportantForAccessibility() == 4) {
                        return false;
                    }
                    if (view2.getParent() instanceof View) {
                        view2 = (View)view2.getParent();
                        continue;
                    }
                    view2 = null;
                }
                return true;
            }
        }
        return false;
    }

    public static int i(Context context, int n) {
        return Math.round((float)n * context.getResources().getDisplayMetrics().density);
    }

    public static FrameLayout j(Activity activity, boolean bl) {
        int n = bl ? Color.rgb((int)18, (int)18, (int)31) : -1;
        int n2 = bl ? Color.rgb((int)104, (int)104, (int)129) : Color.rgb((int)51, (int)51, (int)51);
        int n3 = w2.q(n2, bl, n);
        n2 = bl ? Color.rgb((int)53, (int)53, (int)71) : Color.rgb((int)191, (int)191, (int)191);
        FrameLayout frameLayout = new FrameLayout((Context)activity);
        frameLayout.setBackgroundColor(n);
        LinearLayout linearLayout = new LinearLayout((Context)activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(w2.i((Context)activity, 22), 0, w2.i((Context)activity, 22), 0);
        linearLayout.addView((View)new i2_0((Context)activity, n3, false), (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i((Context)activity, 28), w2.i((Context)activity, 28)));
        TextView textView = w2.n((Context)activity, "New Soul", 17, n3);
        textView.setTypeface(Typeface.DEFAULT, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.leftMargin = w2.i((Context)activity, 18);
        linearLayout.addView((View)textView, (ViewGroup.LayoutParams)layoutParams);
        linearLayout.addView((View)new i2_0((Context)activity, n2, true), (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i((Context)activity, 12), w2.i((Context)activity, 20)));
        frameLayout.addView((View)linearLayout, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        textView = new View((Context)activity);
        n = bl ? Color.rgb((int)36, (int)36, (int)52) : Color.rgb((int)240, (int)240, (int)240);
        textView.setBackgroundColor(n);
        layoutParams = new FrameLayout.LayoutParams(-1, 1, 80);
        layoutParams.rightMargin = n = w2.i((Context)activity, 22);
        layoutParams.leftMargin = n;
        frameLayout.addView((View)textView, (ViewGroup.LayoutParams)layoutParams);
        linearLayout.setImportantForAccessibility(4);
        frameLayout.setContentDescription((CharSequence)"New Soul");
        frameLayout.setOnClickListener((View.OnClickListener)new N1(activity, bl, 0));
        w2.g((View)frameLayout);
        return frameLayout;
    }

    public static View k(Activity object, String string) {
        int n = object.getResources().getIdentifier(string, "id", object.getPackageName());
        object = n == 0 ? null : object.findViewById(n);
        return object;
    }

    public static ImageButton l(q2 q22, String string, CharSequence charSequence) {
        ImageButton imageButton = new ImageButton(q22.g);
        Object object = q22.g;
        imageButton.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i((Context)object, 48), w2.i((Context)object, 48)));
        imageButton.setPadding(w2.i((Context)object, 15), w2.i((Context)object, 15), w2.i((Context)object, 15), w2.i((Context)object, 15));
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        object = q22.a;
        int n = object.getResources().getIdentifier("c_ic_icon_question", "drawable", object.getPackageName());
        if (n != 0) {
            imageButton.setImageDrawable(object.getDrawable(n));
        } else {
            imageButton.setImageResource(17301568);
        }
        imageButton.setImageTintList(ColorStateList.valueOf((int)q22.j));
        object = q22.c;
        object.getClass();
        ((L2)object).b((View)imageButton, 12, L2.e(12));
        object = new StringBuilder();
        ((StringBuilder)object).append(string);
        ((StringBuilder)object).append("\uff0c\u67e5\u770b\u8bf4\u660e");
        imageButton.setContentDescription((CharSequence)((StringBuilder)object).toString());
        imageButton.setOnClickListener((View.OnClickListener)new G1(q22, charSequence, string, 0));
        return imageButton;
    }

    public static TextView m(q2 object, String string, String string2) {
        int n = ((q2)object).k;
        string = w2.n(((q2)object).g, string, 15, n);
        string.setGravity(17);
        object = ((q2)object).c;
        object.getClass();
        ((L2)object).b((View)string, 12, L2.e(12));
        string.setContentDescription((CharSequence)string2);
        w2.g((View)string);
        return string;
    }

    public static TextView n(Context context, String string, int n, int n2) {
        context = new TextView(context);
        context.setText((CharSequence)string);
        context.setTextSize((float)n);
        context.setTextColor(n2);
        return context;
    }

    public static SharedPreferences o(Context context) {
        return context.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
    }

    public static void p(View view) {
        if (view.getForeground() == null && !(view.getBackground() instanceof RippleDrawable)) {
            TypedValue typedValue = new TypedValue();
            if (view.getContext().getTheme().resolveAttribute(16843534, typedValue, true) && typedValue.resourceId != 0) {
                view.setForeground(view.getContext().getDrawable(typedValue.resourceId));
            }
        }
    }

    public static int q(int n, boolean bl, int ... nArray) {
        int n2 = bl ? -1 : -16777216;
        for (int i = 0; i <= 100; ++i) {
            float f = (float)i / 100.0f;
            float f2 = Color.red((int)n);
            int n3 = Math.round((float)(Color.red((int)n2) - Color.red((int)n)) * f + f2);
            f2 = Color.green((int)n);
            int n4 = Math.round((float)(Color.green((int)n2) - Color.green((int)n)) * f + f2);
            f2 = Color.blue((int)n);
            int n5 = Color.rgb((int)n3, (int)n4, (int)Math.round((float)(Color.blue((int)n2) - Color.blue((int)n)) * f + f2));
            int n6 = nArray.length;
            n4 = 1;
            for (n3 = 0; n3 < n6; ++n3) {
                double d;
                int n7 = nArray[n3];
                double d2 = Color.luminance((int)n5);
                if (!((Math.max(d2, d = (double)Color.luminance((int)n7)) + 0.05) / (Math.min(d2, d) + 0.05) < 4.5)) continue;
                n4 = 0;
            }
            if (n4 == 0) continue;
            return n5;
        }
        return n2;
    }

    public static LinearLayout r(q2 q22, String string, String string2, K1 object) {
        LinearLayout linearLayout = new LinearLayout(q22.g);
        linearLayout.setOrientation(1);
        Context context = q22.g;
        linearLayout.setPadding(w2.i(context, 12), w2.i(context, 4), w2.i(context, 12), w2.i(context, 6));
        float f = q22.q ? 14.0f : 12.0f;
        L2 l2 = q22.c;
        l2.b((View)linearLayout, 3, f);
        TextView textView = w2.n(context, string, 15, q22.i);
        textView.setTypeface(Typeface.DEFAULT, 1);
        if (Build.VERSION.SDK_INT >= 28) {
            N0.b(textView);
        }
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(16);
        linearLayout2.setMinimumHeight(w2.i(context, 48));
        textView.setPadding(0, w2.i(context, 8), 0, w2.i(context, 8));
        textView.setGravity(16);
        linearLayout2.setPadding(0, 0, 0, 0);
        linearLayout2.addView((View)textView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (string2 != null) {
            linearLayout2.addView((View)w2.l(q22, string, string2));
        }
        if (object != null) {
            string2 = w2.n(context, "\u22ee", 24, q22.j);
            string2.setGravity(17);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append("\uff0c\u66f4\u591a\u64cd\u4f5c");
            string2.setContentDescription((CharSequence)stringBuilder.toString());
            string2.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i(context, 48), w2.i(context, 48)));
            l2.b((View)string2, 12, L2.e(12));
            string2.setOnClickListener((View.OnClickListener)new C0(object, string2, 4));
            w2.g((View)string2);
            linearLayout2.addView((View)string2);
            textView.setMinHeight(w2.i(context, 48));
            textView.setGravity(16);
            textView.setOnLongClickListener((View.OnLongClickListener)new Z1((K1)object, (TextView)string2));
            object = new StringBuilder();
            ((StringBuilder)object).append(string);
            ((StringBuilder)object).append("\uff0c\u66f4\u591a\u64cd\u4f5c");
            string2.setTooltipText((CharSequence)((StringBuilder)object).toString());
        }
        linearLayout.addView((View)linearLayout2);
        string = new View(context);
        int n = q22.b ? -12961206 : -2302750;
        string.setBackgroundColor(n);
        if (!q22.q) {
            linearLayout.addView((View)string, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, w2.i(context, 1)));
        }
        string2 = new LinearLayout(context);
        string2.setOrientation(1);
        string2.setTag((Object)q22);
        string2.setPadding(0, w2.i(context, 4), 0, 0);
        linearLayout.addView((View)string2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        string = new LinearLayout.LayoutParams(-1, -2);
        q22 = q22.h;
        if (q22.getChildCount() > 0) {
            ((LinearLayout.LayoutParams)string).topMargin = w2.i(context, 16);
        }
        q22.addView((View)linearLayout, (ViewGroup.LayoutParams)string);
        return string2;
    }

    public static void s(TextView textView, String string) {
        if (TextUtils.equals((CharSequence)textView.getText(), (CharSequence)string)) {
            return;
        }
        int n = w2.h((View)textView) && textView.getText().length() > 0 ? 1 : 0;
        textView.setAccessibilityLiveRegion(n);
        textView.setText((CharSequence)string);
    }

    public static void t(Activity activity, boolean bl, boolean bl2, String string) {
        q2 q22 = new q2(activity, bl);
        String string2 = bl2 ? "\u68c0\u6d4b\u5230 Soul \u7248\u672c\u53d8\u5316" : "\u6b63\u5728\u68c0\u6d4b\u529f\u80fd\u517c\u5bb9\u6027";
        string2 = w2.r(q22, string2, null, null);
        Context context = q22.g;
        string2.addView((View)new ProgressBar(context), (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, w2.i((Context)activity, 56)));
        string2.addView((View)w2.n(context, "\u6b63\u5728\u68c0\u67e5\u5f53\u524d Soul \u7248\u672c\u7684\u529f\u80fd\u9002\u914d\u72b6\u6001\u2026", 13, q22.j));
        q22.d.setNeutralButton((CharSequence)"\u91cd\u65b0\u68c0\u6d4b", null);
        q22.g("\u529f\u80fd\u517c\u5bb9\u6027\u68c0\u6d4b", "\u5173\u95ed");
        q22.e.getButton(-3).setVisibility(8);
        q22.e.getButton(-3).setOnClickListener((View.OnClickListener)new O1(q22, activity, bl, bl2, string));
        new Thread((Runnable)new T1(activity, q22, (LinearLayout)string2, bl2, string, SystemClock.elapsedRealtime(), bl), "NewSoul-HookScan").start();
    }

    public static void u(Activity activity, boolean bl) {
        try {
            v2_0 v2_02 = new v2_0(activity, bl);
            v2_02.c();
            Log.i((String)"SoulHook102", (String)"settings panel opened");
        }
        catch (Throwable throwable) {
            Log.e((String)"SoulHook102", (String)"Settings panel failed", (Throwable)throwable);
        }
    }

    public static void v(LinearLayout linearLayout, TextView textView, int n) {
        linearLayout.setId(View.generateViewId());
        if (textView != null) {
            textView.setLabelFor(linearLayout.getId());
        }
        linearLayout.setImportantForAccessibility(1);
        linearLayout.setAccessibilityDelegate((View.AccessibilityDelegate)new f2_0(n, 0));
    }

    public static void w(TextView textView, int n) {
        textView.setFocusable(true);
        w2.p((View)textView);
        textView.setAccessibilityDelegate((View.AccessibilityDelegate)new f2_0(n, 1));
    }

    public static void x(q2 q22, TextView textView) {
        L2 l2 = q22.c;
        l2.getClass();
        l2.b((View)textView, 9, L2.e(9));
        q22 = q22.g;
        textView.setMinHeight(w2.i((Context)q22, 52));
        textView.setGravity(17);
        textView.setSingleLine(false);
        textView.setEllipsize(null);
        textView.setPadding(textView.getPaddingLeft(), w2.i((Context)q22, 8), textView.getPaddingRight(), w2.i((Context)q22, 8));
    }
}

