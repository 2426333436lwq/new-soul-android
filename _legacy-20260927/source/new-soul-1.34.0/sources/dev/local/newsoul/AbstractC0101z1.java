package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.z1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0101z1 {
    public static final WeakHashMap a = new WeakHashMap();

    public static void a(View view, String str, String str2) throws IllegalAccessException {
        b(view);
        TextView textView = (TextView) e(view, "tv_nikename");
        TextView textView2 = (TextView) e(view, "tv_nikename_flag");
        View viewE = e(view, "container");
        if (viewE == null || textView == null || textView2 == null) {
            return;
        }
        C0097y1 c0097y1 = new C0097y1();
        a.put(view, c0097y1);
        if ("nickname".equals(str)) {
            c0097y1.c = new WeakReference(textView);
            c0097y1.d = new WeakReference(textView2);
            c0097y1.h = textView.getText();
            c0097y1.i = textView2.getText();
            c0097y1.f = textView.getVisibility();
            c0097y1.g = textView2.getVisibility();
            if (textView.getVisibility() != 0 || textView.getText().length() == 0) {
                textView.setText(str2);
            }
            textView.setVisibility(0);
            textView2.setText((c0097y1.g != 0 || c0097y1.i.length() <= 0) ? "已撤回" : String.valueOf(c0097y1.i).concat(" · 已撤回"));
            textView2.setVisibility(0);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) viewE.getParent();
        ViewGroup.LayoutParams layoutParams = viewE.getLayoutParams();
        Class<?> cls = layoutParams.getClass();
        c0097y1.e = cls.getField("bottomToBottom").getInt(layoutParams);
        TextView textView3 = new TextView(view.getContext());
        textView3.setId(View.generateViewId());
        textView3.setText("对方已撤回 · 已保留");
        textView3.setTextSize(10.0f);
        textView3.setTextColor(textView.getTextColors());
        textView3.setImportantForAccessibility(1);
        Class cls2 = Integer.TYPE;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) cls.getConstructor(cls2, cls2).newInstance(-2, -2);
        cls.getField("topToBottom").setInt(marginLayoutParams, viewE.getId());
        cls.getField("startToStart").setInt(marginLayoutParams, viewE.getId());
        cls.getField("bottomToBottom").setInt(marginLayoutParams, 0);
        marginLayoutParams.topMargin = d(view.getContext(), 2);
        marginLayoutParams.bottomMargin = d(view.getContext(), 8);
        c0097y1.a = new WeakReference(viewE);
        c0097y1.b = new WeakReference(textView3);
        cls.getField("bottomToBottom").setInt(layoutParams, -1);
        viewE.setLayoutParams(layoutParams);
        viewGroup.addView(textView3, marginLayoutParams);
    }

    public static void b(View view) {
        C0097y1 c0097y1 = (C0097y1) a.remove(view);
        if (c0097y1 == null) {
            return;
        }
        try {
            WeakReference weakReference = c0097y1.b;
            if (weakReference != null) {
                View view2 = (View) weakReference.get();
                View view3 = (View) c0097y1.a.get();
                if (view2 != null && (view2.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) view2.getParent()).removeView(view2);
                }
                if (view3 != null) {
                    ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
                    layoutParams.getClass().getField("bottomToBottom").setInt(layoutParams, c0097y1.e);
                    view3.setLayoutParams(layoutParams);
                }
            }
            WeakReference weakReference2 = c0097y1.c;
            if (weakReference2 != null) {
                TextView textView = (TextView) weakReference2.get();
                TextView textView2 = (TextView) c0097y1.d.get();
                if (textView != null) {
                    textView.setText(c0097y1.h);
                    textView.setVisibility(c0097y1.f);
                }
                if (textView2 != null) {
                    textView2.setText(c0097y1.i);
                    textView2.setVisibility(c0097y1.g);
                }
            }
        } catch (Throwable th) {
            Log.w("SoulHook102", "recall style reset failed", th);
        }
    }

    public static void c(View view) {
        view.setClickable(false);
        view.setLongClickable(false);
        view.setFocusable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                c(viewGroup.getChildAt(i));
            }
        }
    }

    public static int d(Context context, int i) {
        return Math.round(i * context.getResources().getDisplayMetrics().density);
    }

    public static View e(View view, String str) {
        return view.findViewById(g(view.getContext(), str, "id"));
    }

    public static View f(Context context, String str) throws IllegalAccessException {
        View viewInflate = LayoutInflater.from(context).inflate(g(context, "item_chat_message_receive", "layout"), (ViewGroup) new FrameLayout(context), false);
        FrameLayout frameLayout = (FrameLayout) e(viewInflate, "container");
        TextView textView = (TextView) e(viewInflate, "tv_nikename");
        TextView textView2 = (TextView) e(viewInflate, "tv_nikename_flag");
        textView.setText("示例好友");
        textView.setVisibility(0);
        textView2.setVisibility(8);
        TextView textView3 = new TextView(context);
        textView3.setText("这是一条已撤回的消息");
        textView3.setTextSize(14.0f);
        textView3.setTextColor(context.getResources().getColor(g(context, "color_s_02", "color"), context.getTheme()));
        textView3.setBackgroundResource(g(context, "icon_bubble_white", "drawable"));
        textView3.setPadding(d(context, 12), d(context, 10), d(context, 12), d(context, 10));
        frameLayout.addView(textView3, new FrameLayout.LayoutParams(-2, -2));
        c(viewInflate);
        a(viewInflate, str, "示例好友");
        return viewInflate;
    }

    public static int g(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str, str2, "cn.soulapp.android");
        if (identifier != 0) {
            return identifier;
        }
        throw new IllegalStateException("Missing Soul resource: ".concat(str));
    }
}
