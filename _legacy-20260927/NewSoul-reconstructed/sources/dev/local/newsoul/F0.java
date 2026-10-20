/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface$OnDismissListener
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.view.WindowManager$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.A0;
import dev.local.newsoul.B0;
import dev.local.newsoul.C0;
import dev.local.newsoul.D0;
import dev.local.newsoul.E0;
import dev.local.newsoul.L2;
import dev.local.newsoul.Z;
import dev.local.newsoul.j0_0;
import dev.local.newsoul.m_0;
import dev.local.newsoul.s_0;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class F0 {
    public static final int a = Color.rgb((int)0, (int)198, (int)184);

    public static int a(Context context, int n) {
        return Math.round((float)n * context.getResources().getDisplayMetrics().density);
    }

    public static TextView b(Context context, String string, int n, int n2) {
        context = new TextView(context);
        context.setText((CharSequence)string);
        context.setTextSize((float)n);
        context.setTextColor(n2);
        context.setGravity(16);
        return context;
    }

    public static void c(Activity activity, boolean bl, boolean bl2, s_0 object, m_0 m_02) {
        int n;
        boolean bl3 = (activity.getResources().getConfiguration().uiMode & 0x30) == 32;
        L2 l2 = new L2(bl3, activity.getResources().getDisplayMetrics().density);
        int n2 = bl3 ? -1710610 : -14342864;
        int n3 = bl3 ? -7171416 : -9211005;
        Dialog dialog = new Dialog((Context)activity);
        dialog.requestWindowFeature(1);
        LinearLayout linearLayout = new LinearLayout((Context)activity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(F0.a((Context)activity, 20), F0.a((Context)activity, 10), F0.a((Context)activity, 20), F0.a((Context)activity, 20));
        l2.b((View)linearLayout, 1, 24.0f);
        Object object2 = new View((Context)activity);
        int n4 = bl3 ? -12237481 : -2631715;
        Object object3 = new GradientDrawable();
        object3.setColor(n4);
        object3.setCornerRadius((float)F0.a((Context)activity, 3));
        object2.setBackground((Drawable)object3);
        object3 = new LinearLayout.LayoutParams(F0.a((Context)activity, 32), F0.a((Context)activity, 4));
        object3.gravity = 1;
        object3.bottomMargin = F0.a((Context)activity, 8);
        linearLayout.addView(object2, (ViewGroup.LayoutParams)object3);
        object3 = new LinearLayout((Context)activity);
        object3.setGravity(16);
        object2 = bl ? "\u9ab0\u5b50" : "\u731c\u62f3";
        object2 = F0.b((Context)activity, (String)object2, 20, n2);
        object2.setTypeface(null, 1);
        object3.addView(object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, F0.a((Context)activity, 48), 1.0f));
        if (bl2) {
            object2 = F0.b((Context)activity, "\u4ec5\u9884\u89c8", 12, n3);
            object2.setPadding(F0.a((Context)activity, 8), 0, F0.a((Context)activity, 8), 0);
            object3.addView(object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, F0.a((Context)activity, 32)));
        }
        object2 = F0.b((Context)activity, "\u00d7", 28, n3);
        object2.setGravity(17);
        l2.b((View)object2, 12, L2.e(12));
        object2.setContentDescription((CharSequence)"\u53d6\u6d88");
        object2.setOnClickListener((View.OnClickListener)new A0(0, dialog));
        object3.addView(object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(F0.a((Context)activity, 48), F0.a((Context)activity, 48)));
        linearLayout.addView((View)object3);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        int[] nArray = new int[]{-1};
        object2 = bl2 ? "\u9884\u89c8" : "\u53d1\u9001";
        TextView textView = F0.b((Context)activity, (String)object2, 17, -16369099);
        textView.setGravity(17);
        textView.setTypeface(null, 1);
        textView.setEnabled(false);
        textView.setAlpha(0.35f);
        l2.b((View)textView, 7, 16.0f);
        Z z = new Z(arrayList, arrayList2, nArray, textView, 1);
        if (bl) {
            object3 = new int[6];
            object3[0] = (GradientDrawable)true;
            object3[1] = (GradientDrawable)2;
            object3[2] = (GradientDrawable)3;
            object3[3] = (GradientDrawable)4;
            object3[4] = (GradientDrawable)5;
            object3[5] = (GradientDrawable)6;
        } else {
            object3 = new int[3];
            object3[0] = (GradientDrawable)3;
            object3[1] = (GradientDrawable)2;
            object3[2] = (GradientDrawable)true;
        }
        for (n4 = 0; n4 < (n = bl ? 2 : 1); ++n4) {
            LinearLayout linearLayout2 = new LinearLayout((Context)activity);
            for (n = 0; n < 3; ++n) {
                int n5;
                GradientDrawable gradientDrawable = object3[n4 * 3 + n];
                FrameLayout frameLayout = new FrameLayout((Context)activity);
                if (bl) {
                    object2 = new StringBuilder();
                    object2.append((int)gradientDrawable);
                    object2.append(" \u70b9");
                    object2 = object2.toString();
                } else {
                    object2 = gradientDrawable == 3 ? "\u77f3\u5934" : (gradientDrawable == 2 ? "\u526a\u5200" : "\u5e03");
                }
                frameLayout.setContentDescription((CharSequence)object2);
                if (bl) {
                    object2 = new E0((Context)activity, (int)gradientDrawable);
                } else {
                    object2 = gradientDrawable == 3 ? "finger_stone" : (gradientDrawable == 2 ? "finger_scissor" : "finger_cloth");
                    n5 = activity.getResources().getIdentifier((String)object2, "drawable", activity.getPackageName());
                    if (n5 != 0) {
                        object2 = new ImageView((Context)activity);
                        object2.setImageResource(n5);
                        object2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    } else {
                        object2 = gradientDrawable == 3 ? "\u270a" : (gradientDrawable == 2 ? "\u270c" : "\u270b");
                        object2 = F0.b((Context)activity, (String)object2, 36, n2);
                        object2.setGravity(17);
                    }
                }
                object2.setImportantForAccessibility(2);
                frameLayout.addView(object2, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(F0.a((Context)activity, 60), F0.a((Context)activity, 60), 17));
                object2 = F0.b((Context)activity, "\u2713", 12, -16369099);
                object2.setGravity(17);
                object2.setTag((Object)"tick");
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(a);
                gradientDrawable2.setCornerRadius((float)F0.a((Context)activity, 10));
                object2.setBackground((Drawable)gradientDrawable2);
                object2.setImportantForAccessibility(2);
                gradientDrawable2 = new FrameLayout.LayoutParams(F0.a((Context)activity, 20), F0.a((Context)activity, 20), 53);
                gradientDrawable2.rightMargin = n5 = F0.a((Context)activity, 7);
                gradientDrawable2.topMargin = n5;
                frameLayout.addView(object2, (ViewGroup.LayoutParams)gradientDrawable2);
                object2 = new LinearLayout.LayoutParams(0, F0.a((Context)activity, 92), 1.0f);
                if (n != 2) {
                    object2.rightMargin = F0.a((Context)activity, 10);
                }
                linearLayout2.addView((View)frameLayout, (ViewGroup.LayoutParams)object2);
                arrayList.add(frameLayout);
                arrayList2.add((int)gradientDrawable);
                frameLayout.setFocusable(true);
                frameLayout.setOnClickListener((View.OnClickListener)new B0(nArray, (int)gradientDrawable, z));
            }
            object2 = new LinearLayout.LayoutParams(-1, -2);
            object2.topMargin = F0.a((Context)activity, 10);
            linearLayout.addView((View)linearLayout2, (ViewGroup.LayoutParams)object2);
        }
        object2 = new LinearLayout((Context)activity);
        object2.setGravity(17);
        object3 = F0.b((Context)activity, "\u2928", 25, n3);
        object3.setImportantForAccessibility(2);
        object2.addView((View)object3, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(F0.a((Context)activity, 30), F0.a((Context)activity, 44)));
        object2.addView((View)F0.b((Context)activity, "\u968f\u673a", 15, n2));
        object2.setContentDescription((CharSequence)"\u968f\u673a\u7ed3\u679c");
        object2.setFocusable(true);
        arrayList.add(object2);
        arrayList2.add(0);
        object2.setOnClickListener((View.OnClickListener)new C0(nArray, z, 0));
        object3 = new LinearLayout.LayoutParams(-1, F0.a((Context)activity, 48));
        object3.topMargin = F0.a((Context)activity, 12);
        linearLayout.addView(object2, (ViewGroup.LayoutParams)object3);
        object2 = new LinearLayout.LayoutParams(-1, F0.a((Context)activity, 52));
        object2.topMargin = F0.a((Context)activity, 16);
        linearLayout.addView((View)textView, (ViewGroup.LayoutParams)object2);
        textView.setOnClickListener((View.OnClickListener)new D0((Serializable)nArray, textView, dialog, object, 0));
        object = arrayList.iterator();
        while (object.hasNext()) {
            l2.b((View)object.next(), 5, 16.0f);
        }
        z.run();
        dialog.setContentView((View)linearLayout);
        dialog.setOnDismissListener((DialogInterface.OnDismissListener)new j0_0(1, m_02));
        dialog.setCanceledOnTouchOutside(true);
        object = dialog.getWindow();
        object.setBackgroundDrawableResource(17170445);
        object.addFlags(2);
        m_02 = object.getAttributes();
        ((WindowManager.LayoutParams)m_02).dimAmount = 0.5f;
        object.setAttributes((WindowManager.LayoutParams)m_02);
        object.setGravity(81);
        dialog.show();
        object.setLayout(Math.min(activity.getResources().getDisplayMetrics().widthPixels - F0.a((Context)activity, 24), F0.a((Context)activity, 420)), -2);
        m_02 = object.getAttributes();
        ((WindowManager.LayoutParams)m_02).y = F0.a((Context)activity, 12);
        object.setAttributes((WindowManager.LayoutParams)m_02);
    }
}

