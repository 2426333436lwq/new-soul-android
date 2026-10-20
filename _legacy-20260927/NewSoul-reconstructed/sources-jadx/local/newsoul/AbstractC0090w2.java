package dev.local.newsoul;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.w2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0090w2 {
    public static final WeakHashMap a = new WeakHashMap();
    public static final int b = Color.rgb(0, 181, 162);

    public static void a(C0067q2 c0067q2, LinearLayout linearLayout, TextView textView, TextView textView2) {
        Context context = c0067q2.g;
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        x(c0067q2, textView);
        x(c0067q2, textView2);
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(0, -2, 1.0f));
        View view = new View(context);
        view.setBackgroundColor(c0067q2.b ? -12961206 : -2302750);
        view.setImportantForAccessibility(2);
        linearLayout2.addView(view, new LinearLayout.LayoutParams(i(context, 1), i(context, 28)));
        linearLayout2.addView(textView2, new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
    }

    public static void b(C0067q2 c0067q2, LinearLayout linearLayout, TextView textView) {
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textView, 4, L2.e(4));
        Context context = c0067q2.g;
        textView.setMinHeight(i(context, 56));
        textView.setPadding(i(context, 16), i(context, 14), i(context, 16), i(context, 14));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = i(context, 2);
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i(context, linearLayout.getChildCount() == 0 ? 2 : 8);
        layoutParams.bottomMargin = i(context, 2);
        linearLayout.addView(textView, layoutParams);
    }

    public static TextView c(LinearLayout linearLayout, String str) {
        TextView textViewN = n(linearLayout.getContext(), str, 13, ((C0067q2) linearLayout.getTag()).j);
        textViewN.setPadding(0, i(linearLayout.getContext(), 4), 0, i(linearLayout.getContext(), 8));
        textViewN.setLineSpacing(i(linearLayout.getContext(), 2), 1.0f);
        linearLayout.addView(textViewN, new LinearLayout.LayoutParams(-1, -2));
        return textViewN;
    }

    public static C0070r2 d(LinearLayout linearLayout, final SharedPreferences sharedPreferences, final String str, String str2, String str3, int i) {
        Context context = linearLayout.getContext();
        final C0070r2 c0070r2 = new C0070r2(context);
        c0070r2.setContentDescription(str2);
        c0070r2.setPadding(i(context, 4), 0, 0, 0);
        c0070r2.setMinWidth(i(context, 48));
        c0070r2.setMinHeight(i(context, 48));
        boolean z = (sharedPreferences == null || str.startsWith("ad_") || str.startsWith("privacy_") || str.equals("square_like_all_loaded") || str.equals("square_auto_like") || str.equals("feed_keywords_enabled_v1") || str.equals("feed_filter_enabled") || str.equals("feed_filter_keep_unknown")) ? false : true;
        c0070r2.setChecked(sharedPreferences != null && sharedPreferences.getBoolean(str, z));
        C0067q2 c0067q2 = (C0067q2) linearLayout.getTag();
        L2 l2 = c0067q2.c;
        l2.getClass();
        int[][] iArr = {new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910}, new int[]{R.attr.state_checked}, new int[0]};
        boolean z2 = l2.a;
        c0070r2.setThumbTintList(new ColorStateList(iArr, new int[]{z2 ? -11111569 : -6636105, z2 ? -10329489 : -4735550, -16730718, -6576982}));
        c0070r2.setTrackTintList(new ColorStateList(iArr, new int[]{z2 ? -13285051 : -2956578, z2 ? -12895415 : -2104860, z2 ? -13080993 : -6431791, z2 ? -11513760 : -3748912}));
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(16);
        linearLayout2.setMinimumHeight(i(context, 52));
        TextView textViewN = n(context, str2 + (str3.contains("未就绪") ? "（未就绪）" : ""), 16, i);
        textViewN.setMinHeight(i(context, 48));
        textViewN.setGravity(16);
        textViewN.setImportantForAccessibility(2);
        textViewN.setFocusable(false);
        linearLayout2.setImportantForAccessibility(2);
        final int i2 = 0;
        textViewN.setOnClickListener(new View.OnClickListener() { // from class: dev.local.newsoul.S1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        C0070r2 c0070r3 = c0070r2;
                        if (c0070r3.isEnabled()) {
                            c0070r3.toggle();
                        }
                        break;
                    default:
                        C0070r2 c0070r4 = c0070r2;
                        if (c0070r4.isEnabled()) {
                            c0070r4.toggle();
                        }
                        break;
                }
            }
        });
        textViewN.setFocusable(false);
        final int i3 = 1;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: dev.local.newsoul.S1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                    case 0:
                        C0070r2 c0070r3 = c0070r2;
                        if (c0070r3.isEnabled()) {
                            c0070r3.toggle();
                        }
                        break;
                    default:
                        C0070r2 c0070r4 = c0070r2;
                        if (c0070r4.isEnabled()) {
                            c0070r4.toggle();
                        }
                        break;
                }
            }
        });
        linearLayout2.setFocusable(false);
        p(textViewN);
        p(linearLayout2);
        linearLayout2.addView(textViewN, new LinearLayout.LayoutParams(0, -2, 1.0f));
        String strTrim = str3.replace("当前版本未就绪", "").replace("未就绪", "").replaceAll("[\\s·]+$", "").trim();
        if (!strTrim.isEmpty()) {
            linearLayout2.addView(l(c0067q2, str2, strTrim));
        }
        linearLayout2.addView(c0070r2, new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
        c0070r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: dev.local.newsoul.U1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                SharedPreferences sharedPreferences2 = sharedPreferences;
                if (sharedPreferences2 == null || c0070r2.b) {
                    return;
                }
                SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
                String str4 = str;
                editorEdit.putBoolean(str4, z3).apply();
                AbstractC0036j.i.post(new RunnableC0028h(0));
                B2.d();
                Log.i("SoulHook102", "setting changed: " + str4 + "=" + z3);
            }
        });
        if (sharedPreferences != null) {
            c0067q2.m.add(new RunnableC0064q(c0070r2, sharedPreferences, str, z));
        }
        c0070r2.a(new RunnableC0050m1(textViewN, c0070r2, linearLayout2, str2, sharedPreferences, 4));
        if (sharedPreferences == null) {
            return c0070r2;
        }
        IdentityHashMap identityHashMap = c0067q2.l;
        C0031h2 c0031h2 = (C0031h2) identityHashMap.get(linearLayout);
        if (c0031h2 == null) {
            c0031h2 = new C0031h2(c0067q2, linearLayout);
            identityHashMap.put(linearLayout, c0031h2);
        }
        c0031h2.a.add(c0070r2);
        c0070r2.a(new V1(c0031h2, 0));
        return c0070r2;
    }

    public static void e(C0070r2 c0070r2, C0067q2 c0067q2, Runnable runnable) {
        LinearLayout linearLayout = (LinearLayout) c0070r2.getParent();
        Context context = c0067q2.g;
        TextView textViewN = n(context, "预览", 14, c0067q2.k);
        textViewN.setMinWidth(i(context, 48));
        textViewN.setMinHeight(i(context, 48));
        textViewN.setGravity(17);
        textViewN.setContentDescription("预览".concat(String.valueOf(c0070r2.getContentDescription())));
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textViewN, 10, L2.e(10));
        textViewN.setOnClickListener(new C0(c0070r2, runnable, 3));
        g(textViewN);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = i(context, 8);
        layoutParams.rightMargin = i(context, 4);
        linearLayout.addView(textViewN, linearLayout.indexOfChild(c0070r2), layoutParams);
        c0070r2.a(new RunnableC0024g(textViewN, c0070r2, 10));
    }

    public static void f(C0067q2 c0067q2, C0070r2 c0070r2, BooleanSupplier booleanSupplier) {
        RunnableC0024g runnableC0024g = new RunnableC0024g(c0070r2, booleanSupplier, 9);
        runnableC0024g.run();
        c0067q2.a(runnableC0024g);
    }

    public static void g(View view) {
        view.setFocusable(true);
        p(view);
        view.setAccessibilityDelegate(new C0019e2());
    }

    public static boolean h(View view) {
        if (!view.isAttachedToWindow() || !view.isShown()) {
            return false;
        }
        while (view != null) {
            if (view.getImportantForAccessibility() == 4) {
                return false;
            }
            view = view.getParent() instanceof View ? (View) view.getParent() : null;
        }
        return true;
    }

    public static int i(Context context, int i) {
        return Math.round(i * context.getResources().getDisplayMetrics().density);
    }

    public static FrameLayout j(Activity activity, boolean z) {
        int iRgb = z ? Color.rgb(18, 18, 31) : -1;
        int iQ = q(z ? Color.rgb(104, 104, 129) : Color.rgb(51, 51, 51), z, iRgb);
        int iRgb2 = z ? Color.rgb(53, 53, 71) : Color.rgb(191, 191, 191);
        FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.setBackgroundColor(iRgb);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(i(activity, 22), 0, i(activity, 22), 0);
        linearLayout.addView(new C0035i2(activity, iQ, false), new LinearLayout.LayoutParams(i(activity, 28), i(activity, 28)));
        TextView textViewN = n(activity, "New Soul", 17, iQ);
        textViewN.setTypeface(Typeface.DEFAULT, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.leftMargin = i(activity, 18);
        linearLayout.addView(textViewN, layoutParams);
        linearLayout.addView(new C0035i2(activity, iRgb2, true), new LinearLayout.LayoutParams(i(activity, 12), i(activity, 20)));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        View view = new View(activity);
        view.setBackgroundColor(z ? Color.rgb(36, 36, 52) : Color.rgb(240, 240, 240));
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, 1, 80);
        int i = i(activity, 22);
        layoutParams2.rightMargin = i;
        layoutParams2.leftMargin = i;
        frameLayout.addView(view, layoutParams2);
        linearLayout.setImportantForAccessibility(4);
        frameLayout.setContentDescription("New Soul");
        frameLayout.setOnClickListener(new N1(activity, z, 0));
        g(frameLayout);
        return frameLayout;
    }

    public static View k(Activity activity, String str) {
        int identifier = activity.getResources().getIdentifier(str, "id", activity.getPackageName());
        if (identifier == 0) {
            return null;
        }
        return activity.findViewById(identifier);
    }

    public static ImageButton l(C0067q2 c0067q2, String str, CharSequence charSequence) {
        ImageButton imageButton = new ImageButton(c0067q2.g);
        Context context = c0067q2.g;
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(i(context, 48), i(context, 48)));
        imageButton.setPadding(i(context, 15), i(context, 15), i(context, 15), i(context, 15));
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Activity activity = c0067q2.a;
        int identifier = activity.getResources().getIdentifier("c_ic_icon_question", "drawable", activity.getPackageName());
        if (identifier != 0) {
            imageButton.setImageDrawable(activity.getDrawable(identifier));
        } else {
            imageButton.setImageResource(R.drawable.ic_menu_help);
        }
        imageButton.setImageTintList(ColorStateList.valueOf(c0067q2.j));
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(imageButton, 12, L2.e(12));
        imageButton.setContentDescription(str + "，查看说明");
        imageButton.setOnClickListener(new G1(c0067q2, charSequence, str, 0));
        return imageButton;
    }

    public static TextView m(C0067q2 c0067q2, String str, String str2) {
        TextView textViewN = n(c0067q2.g, str, 15, c0067q2.k);
        textViewN.setGravity(17);
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textViewN, 12, L2.e(12));
        textViewN.setContentDescription(str2);
        g(textViewN);
        return textViewN;
    }

    public static TextView n(Context context, String str, int i, int i2) {
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(i);
        textView.setTextColor(i2);
        return textView;
    }

    public static SharedPreferences o(Context context) {
        return context.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
    }

    public static void p(View view) {
        if (view.getForeground() != null || (view.getBackground() instanceof RippleDrawable)) {
            return;
        }
        TypedValue typedValue = new TypedValue();
        if (!view.getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true) || typedValue.resourceId == 0) {
            return;
        }
        view.setForeground(view.getContext().getDrawable(typedValue.resourceId));
    }

    public static int q(int i, boolean z, int... iArr) {
        int i2 = z ? -1 : -16777216;
        for (int i3 = 0; i3 <= 100; i3++) {
            float f = i3 / 100.0f;
            int iRgb = Color.rgb(Math.round(((Color.red(i2) - Color.red(i)) * f) + Color.red(i)), Math.round(((Color.green(i2) - Color.green(i)) * f) + Color.green(i)), Math.round(((Color.blue(i2) - Color.blue(i)) * f) + Color.blue(i)));
            boolean z2 = true;
            for (int i4 : iArr) {
                double dLuminance = Color.luminance(iRgb);
                double dLuminance2 = Color.luminance(i4);
                if ((Math.max(dLuminance, dLuminance2) + 0.05d) / (Math.min(dLuminance, dLuminance2) + 0.05d) < 4.5d) {
                    z2 = false;
                }
            }
            if (z2) {
                return iRgb;
            }
        }
        return i2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static LinearLayout r(C0067q2 c0067q2, String str, String str2, final K1 k1) {
        int i;
        LinearLayout linearLayout = new LinearLayout(c0067q2.g);
        linearLayout.setOrientation(1);
        Context context = c0067q2.g;
        linearLayout.setPadding(i(context, 12), i(context, 4), i(context, 12), i(context, 6));
        float f = c0067q2.q ? 14.0f : 12.0f;
        L2 l2 = c0067q2.c;
        l2.b(linearLayout, 3, f);
        TextView textViewN = n(context, str, 15, c0067q2.i);
        textViewN.setTypeface(Typeface.DEFAULT, 1);
        if (Build.VERSION.SDK_INT >= 28) {
            textViewN.setAccessibilityHeading(true);
        }
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(16);
        linearLayout2.setMinimumHeight(i(context, 48));
        textViewN.setPadding(0, i(context, 8), 0, i(context, 8));
        textViewN.setGravity(16);
        linearLayout2.setPadding(0, 0, 0, 0);
        linearLayout2.addView(textViewN, new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (str2 != null) {
            linearLayout2.addView(l(c0067q2, str, str2));
        }
        if (k1 != null) {
            final TextView textViewN2 = n(context, "⋮", 24, c0067q2.j);
            textViewN2.setGravity(17);
            textViewN2.setContentDescription(str + "，更多操作");
            textViewN2.setLayoutParams(new LinearLayout.LayoutParams(i(context, 48), i(context, 48)));
            l2.b(textViewN2, 12, L2.e(12));
            textViewN2.setOnClickListener(new C0(k1, textViewN2, 4));
            g(textViewN2);
            linearLayout2.addView(textViewN2);
            textViewN.setMinHeight(i(context, 48));
            textViewN.setGravity(16);
            textViewN.setOnLongClickListener(new View.OnLongClickListener() { // from class: dev.local.newsoul.Z1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    k1.accept(textViewN2);
                    return true;
                }
            });
            textViewN2.setTooltipText(str + "，更多操作");
        }
        linearLayout.addView(linearLayout2);
        View view = new View(context);
        view.setBackgroundColor(c0067q2.b ? -12961206 : -2302750);
        if (c0067q2.q) {
            i = 1;
        } else {
            i = 1;
            linearLayout.addView(view, new LinearLayout.LayoutParams(-1, i(context, 1)));
        }
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setOrientation(i);
        linearLayout3.setTag(c0067q2);
        linearLayout3.setPadding(0, i(context, 4), 0, 0);
        linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout4 = c0067q2.h;
        if (linearLayout4.getChildCount() > 0) {
            layoutParams.topMargin = i(context, 16);
        }
        linearLayout4.addView(linearLayout, layoutParams);
        return linearLayout3;
    }

    public static void s(TextView textView, String str) {
        if (TextUtils.equals(textView.getText(), str)) {
            return;
        }
        textView.setAccessibilityLiveRegion((!h(textView) || textView.getText().length() <= 0) ? 0 : 1);
        textView.setText(str);
    }

    public static void t(final Activity activity, final boolean z, final boolean z2, final String str) {
        final C0067q2 c0067q2 = new C0067q2(activity, z);
        final LinearLayout linearLayoutR = r(c0067q2, z2 ? "检测到 Soul 版本变化" : "正在检测功能兼容性", null, null);
        Context context = c0067q2.g;
        linearLayoutR.addView(new ProgressBar(context), new LinearLayout.LayoutParams(-1, i(activity, 56)));
        linearLayoutR.addView(n(context, "正在检查当前 Soul 版本的功能适配状态…", 13, c0067q2.j));
        c0067q2.d.setNeutralButton("重新检测", (DialogInterface.OnClickListener) null);
        c0067q2.g("功能兼容性检测", "关闭");
        c0067q2.e.getButton(-3).setVisibility(8);
        c0067q2.e.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: dev.local.newsoul.O1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                c0067q2.e.dismiss();
                AbstractC0090w2.t(activity, z, z2, str);
            }
        });
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        new Thread(new Runnable() { // from class: dev.local.newsoul.T1
            @Override // java.lang.Runnable
            public final void run() {
                final P0 p0;
                final Activity activity2 = activity;
                final C0067q2 c0067q3 = c0067q2;
                try {
                    P0 p0U = Q0.U(activity2);
                    final boolean z3 = z2;
                    if (z3) {
                        int i = p0U.d;
                        p0 = new P0(p0U.a, str, (ArrayList) p0U.c, i);
                    } else {
                        p0 = p0U;
                    }
                    long jMax = Math.max(0L, 450 - (SystemClock.elapsedRealtime() - jElapsedRealtime));
                    View decorView = activity2.getWindow().getDecorView();
                    final boolean z4 = z;
                    decorView.postDelayed(new Runnable() { // from class: dev.local.newsoul.Y1
                        @Override // java.lang.Runnable
                        public final void run() {
                            C0067q2 c0067q4 = c0067q3;
                            if (c0067q4.e.isShowing()) {
                                final Activity activity3 = activity2;
                                if (activity3.isFinishing() || activity3.isDestroyed()) {
                                    return;
                                }
                                c0067q4.e.dismiss();
                                final boolean z5 = z4;
                                C0067q2 c0067q5 = new C0067q2(activity3, z5);
                                LinearLayout linearLayoutR2 = AbstractC0090w2.r(c0067q5, z3 ? "Soul 更新检测结果" : "宿主兼容检测", "检查当前 Soul 的功能适配状态，不会触发发送、点赞或下载。功能是否真正生效仍需进入对应页面验证。", null);
                                P0 p1 = p0;
                                String strB = p1.b();
                                Context context2 = c0067q5.g;
                                int i2 = c0067q5.i;
                                TextView textViewN = AbstractC0090w2.n(context2, strB, 16, i2);
                                textViewN.setTextIsSelectable(true);
                                linearLayoutR2.addView(textViewN);
                                String strA = p1.a(true);
                                if (!strA.isEmpty()) {
                                    TextView textViewN2 = AbstractC0090w2.n(context2, strA, 14, i2);
                                    textViewN2.setPadding(0, AbstractC0090w2.i(activity3, 12), 0, AbstractC0090w2.i(activity3, 12));
                                    textViewN2.setTextIsSelectable(true);
                                    linearLayoutR2.addView(textViewN2);
                                }
                                String strA2 = p1.a(false);
                                boolean zIsEmpty = strA2.isEmpty();
                                int i3 = c0067q5.k;
                                if (!zIsEmpty) {
                                    TextView textViewN3 = AbstractC0090w2.n(context2, "正常检测项", 15, i3);
                                    textViewN3.setMinHeight(AbstractC0090w2.i(context2, 48));
                                    textViewN3.setGravity(16);
                                    AbstractC0090w2.g(textViewN3);
                                    TextView textViewN4 = AbstractC0090w2.n(context2, strA2, 13, i2);
                                    textViewN4.setTextIsSelectable(true);
                                    textViewN4.setVisibility(8);
                                    textViewN3.setContentDescription("正常检测项，展开");
                                    textViewN3.setOnClickListener(new G1(textViewN4, textViewN3));
                                    linearLayoutR2.addView(textViewN3, new LinearLayout.LayoutParams(-1, -2));
                                    linearLayoutR2.addView(textViewN4, new LinearLayout.LayoutParams(-1, -2));
                                }
                                AbstractC0090w2.c(linearLayoutR2, "已注册不代表功能实测通过。");
                                TextView textViewN5 = AbstractC0090w2.n(context2, "复制检测结果", 15, i3);
                                textViewN5.setGravity(17);
                                textViewN5.setMinHeight(AbstractC0090w2.i(activity3, 48));
                                AbstractC0090w2.g(textViewN5);
                                textViewN5.setOnClickListener(new C0(activity3, p1, 2));
                                linearLayoutR2.addView(textViewN5);
                                c0067q5.d.setNeutralButton("重新检测", new DialogInterface.OnClickListener() { // from class: dev.local.newsoul.J1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public final void onClick(DialogInterface dialogInterface, int i4) {
                                        AbstractC0090w2.t(activity3, z5, false, null);
                                    }
                                });
                                c0067q5.g("功能兼容性检测", "返回");
                                activity3.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).edit().putString("hook_diagnostics_last_shown_version", p1.a).apply();
                            }
                        }
                    }, jMax);
                } catch (Throwable th) {
                    Log.e("SoulHook102", "Hook diagnostics failed", th);
                    activity2.runOnUiThread(new H((Object) c0067q3, activity2, (Object) linearLayoutR, 11));
                }
            }
        }, "NewSoul-HookScan").start();
    }

    public static void u(Activity activity, boolean z) {
        try {
            new C0086v2(activity, z).c();
            Log.i("SoulHook102", "settings panel opened");
        } catch (Throwable th) {
            Log.e("SoulHook102", "Settings panel failed", th);
        }
    }

    public static void v(LinearLayout linearLayout, TextView textView, int i) {
        linearLayout.setId(View.generateViewId());
        if (textView != null) {
            textView.setLabelFor(linearLayout.getId());
        }
        linearLayout.setImportantForAccessibility(1);
        linearLayout.setAccessibilityDelegate(new C0023f2(i, 0));
    }

    public static void w(TextView textView, int i) {
        textView.setFocusable(true);
        p(textView);
        textView.setAccessibilityDelegate(new C0023f2(i, 1));
    }

    public static void x(C0067q2 c0067q2, TextView textView) {
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textView, 9, L2.e(9));
        Context context = c0067q2.g;
        textView.setMinHeight(i(context, 52));
        textView.setGravity(17);
        textView.setSingleLine(false);
        textView.setEllipsize(null);
        textView.setPadding(textView.getPaddingLeft(), i(context, 8), textView.getPaddingRight(), i(context, 8));
    }
}
