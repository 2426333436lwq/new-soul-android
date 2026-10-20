/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import dev.local.newsoul.y1_0;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.z1
 */
public abstract class z1_0 {
    public static final WeakHashMap a = new WeakHashMap();

    public static void a(View object, String string, String string2) {
        z1_0.b(object);
        Object object2 = (TextView)z1_0.e(object, "tv_nikename");
        Object object3 = (TextView)z1_0.e(object, "tv_nikename_flag");
        View view = z1_0.e(object, "container");
        if (view != null && object2 != null && object3 != null) {
            Object object4 = new Object();
            a.put(object, object4);
            if ("nickname".equals(string)) {
                ((y1_0)object4).c = new WeakReference<TextView>((TextView)object2);
                ((y1_0)object4).d = new WeakReference<TextView>((TextView)object3);
                ((y1_0)object4).h = object2.getText();
                ((y1_0)object4).i = object3.getText();
                ((y1_0)object4).f = object2.getVisibility();
                ((y1_0)object4).g = object3.getVisibility();
                if (object2.getVisibility() != 0 || object2.getText().length() == 0) {
                    object2.setText((CharSequence)string2);
                }
                object2.setVisibility(0);
                object = ((y1_0)object4).g == 0 && ((y1_0)object4).i.length() > 0 ? String.valueOf(((y1_0)object4).i).concat(" \u00b7 \u5df2\u64a4\u56de") : "\u5df2\u64a4\u56de";
                object3.setText((CharSequence)object);
                object3.setVisibility(0);
            } else {
                string = (ViewGroup)view.getParent();
                string2 = view.getLayoutParams();
                object3 = string2.getClass();
                ((y1_0)object4).e = ((Class)object3).getField("bottomToBottom").getInt(string2);
                TextView textView = new TextView(object.getContext());
                textView.setId(View.generateViewId());
                textView.setText((CharSequence)"\u5bf9\u65b9\u5df2\u64a4\u56de \u00b7 \u5df2\u4fdd\u7559");
                textView.setTextSize(10.0f);
                textView.setTextColor(object2.getTextColors());
                textView.setImportantForAccessibility(1);
                object2 = Integer.TYPE;
                object2 = (ViewGroup.MarginLayoutParams)((Class)object3).getConstructor(new Class[]{object2, object2}).newInstance(-2, -2);
                ((Class)object3).getField("topToBottom").setInt(object2, view.getId());
                ((Class)object3).getField("startToStart").setInt(object2, view.getId());
                ((Class)object3).getField("bottomToBottom").setInt(object2, 0);
                object2.topMargin = z1_0.d(object.getContext(), 2);
                object2.bottomMargin = z1_0.d(object.getContext(), 8);
                ((y1_0)object4).a = new WeakReference<View>(view);
                ((y1_0)object4).b = new WeakReference<TextView>(textView);
                ((Class)object3).getField("bottomToBottom").setInt(string2, -1);
                view.setLayoutParams((ViewGroup.LayoutParams)string2);
                string.addView((View)textView, (ViewGroup.LayoutParams)object2);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(View object) {
        Throwable throwable2;
        block7: {
            View view;
            WeakReference weakReference;
            block5: {
                block6: {
                    if ((object = (y1_0)a.remove(object)) == null) {
                        return;
                    }
                    try {
                        weakReference = object.b;
                        if (weakReference == null) break block5;
                        view = (View)weakReference.get();
                        weakReference = (View)object.a.get();
                        if (view == null || !(view.getParent() instanceof ViewGroup)) break block6;
                        ((ViewGroup)view.getParent()).removeView(view);
                    }
                    catch (Throwable throwable2) {
                        break block7;
                    }
                }
                if (weakReference != null) {
                    view = weakReference.getLayoutParams();
                    view.getClass().getField("bottomToBottom").setInt(view, object.e);
                    weakReference.setLayoutParams((ViewGroup.LayoutParams)view);
                }
            }
            if ((weakReference = object.c) == null) return;
            weakReference = (TextView)weakReference.get();
            view = (TextView)object.d.get();
            if (weakReference != null) {
                weakReference.setText(object.h);
                weakReference.setVisibility(object.f);
            }
            if (view == null) return;
            view.setText(object.i);
            view.setVisibility(object.g);
            return;
        }
        Log.w((String)"SoulHook102", (String)"recall style reset failed", (Throwable)throwable2);
    }

    public static void c(View view) {
        view.setClickable(false);
        view.setLongClickable(false);
        view.setFocusable(false);
        if (view instanceof ViewGroup) {
            view = (ViewGroup)view;
            for (int i = 0; i < view.getChildCount(); ++i) {
                z1_0.c(view.getChildAt(i));
            }
        }
    }

    public static int d(Context context, int n) {
        return Math.round((float)n * context.getResources().getDisplayMetrics().density);
    }

    public static View e(View view, String string) {
        return view.findViewById(z1_0.g(view.getContext(), string, "id"));
    }

    public static View f(Context context, String string) {
        int n = z1_0.g(context, "item_chat_message_receive", "layout");
        View view = LayoutInflater.from((Context)context).inflate(n, (ViewGroup)new FrameLayout(context), false);
        FrameLayout frameLayout = (FrameLayout)z1_0.e(view, "container");
        TextView textView = (TextView)z1_0.e(view, "tv_nikename");
        TextView textView2 = (TextView)z1_0.e(view, "tv_nikename_flag");
        textView.setText((CharSequence)"\u793a\u4f8b\u597d\u53cb");
        textView.setVisibility(0);
        textView2.setVisibility(8);
        textView2 = new TextView(context);
        textView2.setText((CharSequence)"\u8fd9\u662f\u4e00\u6761\u5df2\u64a4\u56de\u7684\u6d88\u606f");
        textView2.setTextSize(14.0f);
        textView2.setTextColor(context.getResources().getColor(z1_0.g(context, "color_s_02", "color"), context.getTheme()));
        textView2.setBackgroundResource(z1_0.g(context, "icon_bubble_white", "drawable"));
        textView2.setPadding(z1_0.d(context, 12), z1_0.d(context, 10), z1_0.d(context, 12), z1_0.d(context, 10));
        frameLayout.addView((View)textView2, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        z1_0.c(view);
        z1_0.a(view, string, "\u793a\u4f8b\u597d\u53cb");
        return view;
    }

    public static int g(Context context, String string, String string2) {
        int n = context.getResources().getIdentifier(string, string2, "cn.soulapp.android");
        if (n != 0) {
            return n;
        }
        throw new IllegalStateException("Missing Soul resource: ".concat(string));
    }
}

