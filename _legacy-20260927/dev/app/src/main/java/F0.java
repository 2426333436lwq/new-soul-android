package dev.local.newsoul;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class F0 {
    public static final int a = Color.rgb(0, 198, 184);

    public static int a(Context context, int i) {
        return Math.round(i * context.getResources().getDisplayMetrics().density);
    }

    public static TextView b(Context context, String str, int i, int i2) {
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(i);
        textView.setTextColor(i2);
        textView.setGravity(16);
        return textView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [int[], java.io.Serializable, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v0, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r25v0, types: [dev.local.newsoul.L2] */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3 */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r25v5 */
    /* JADX WARN: Type inference failed for: r25v6 */
    /* JADX WARN: Type inference failed for: r25v7 */
    /* JADX WARN: Type inference failed for: r26v0, types: [android.app.Dialog, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v22, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r4v21, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [dev.local.newsoul.L2] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public static void c(Activity activity, boolean z, boolean z2, C0071s c0071s, RunnableC0048m runnableC0048m) {
        Dialog dialog;
        String string;
        L2 r25;
        String str;
        TextView textView;
        int i;
        int i2;
        String str2;
        int i3;
        View e0;
        boolean z3 = (activity.getResources().getConfiguration().uiMode & 48) == 32;
        L2 l2 = new L2(z3, activity.getResources().getDisplayMetrics().density);
        int i4 = z3 ? -1710610 : -14342864;
        int i5 = z3 ? -7171416 : -9211005;
        Dialog dialog2 = new Dialog(activity);
        dialog2.requestWindowFeature(1);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(a(activity, 20), a(activity, 10), a(activity, 20), a(activity, 20));
        l2.b(linearLayout, 1, 24.0f);
        View view = new View(activity);
        int i6 = z3 ? -12237481 : -2631715;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i6);
        gradientDrawable.setCornerRadius(a(activity, 3));
        view.setBackground(gradientDrawable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a(activity, 32), a(activity, 4));
        layoutParams.gravity = 1;
        layoutParams.bottomMargin = a(activity, 8);
        linearLayout.addView(view, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(activity);
        linearLayout2.setGravity(16);
        TextView textViewB = b(activity, z ? "骰子" : "猜拳", 20, i4);
        textViewB.setTypeface(null, 1);
        linearLayout2.addView(textViewB, new LinearLayout.LayoutParams(0, a(activity, 48), 1.0f));
        if (z2) {
            TextView textViewB2 = b(activity, "仅预览", 12, i5);
            textViewB2.setPadding(a(activity, 8), 0, a(activity, 8), 0);
            linearLayout2.addView(textViewB2, new LinearLayout.LayoutParams(-2, a(activity, 32)));
        }
        TextView textViewB3 = b(activity, "×", 28, i5);
        textViewB3.setGravity(17);
        l2.b(textViewB3, 12, L2.e(12));
        textViewB3.setContentDescription("取消");
        textViewB3.setOnClickListener(new A0(0, dialog2));
        linearLayout2.addView(textViewB3, new LinearLayout.LayoutParams(a(activity, 48), a(activity, 48)));
        linearLayout.addView(linearLayout2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final int[] r10 = {-1};
        TextView textViewB4 = b(activity, z2 ? "预览" : "发送", 17, -16369099);
        textViewB4.setGravity(17);
        textViewB4.setTypeface(null, 1);
        textViewB4.setEnabled(false);
        textViewB4.setAlpha(0.35f);
        l2.b(textViewB4, 7, 16.0f);
        final Z z4 = new Z(arrayList, arrayList2, r10, textViewB4, 1);
        int i7 = 2;
        int[] iArr = z ? new int[]{1, 2, 3, 4, 5, 6} : new int[]{3, 2, 1};
        int i8 = 0;
        L2 r6 = l2;
        while (true) {
            if (i8 >= (z ? i7 : 1)) {
                break;
            }
            LinearLayout linearLayout3 = new LinearLayout(activity);
            int i9 = 0;
            L2 r7 = r6;
            while (i9 < 3) {
                final int i10 = iArr[(i8 * 3) + i9];
                int[] iArr2 = iArr;
                FrameLayout frameLayout = new FrameLayout(activity);
                if (z) {
                    r25 = r7;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i10);
                    dialog = dialog2;
                    sb.append(" 点");
                    string = sb.toString();
                } else {
                    L2 r26 = r7;
                    dialog = dialog2;
                    if (i10 == 3) {
                        string = "石头";
                        r25 = r26;
                    } else if (i10 == 2) {
                        string = "剪刀";
                        r25 = r26;
                    } else {
                        string = "布";
                        r25 = r26;
                    }
                }
                frameLayout.setContentDescription(string);
                if (z) {
                    i = i5;
                    textView = textViewB4;
                    i2 = 2;
                    i3 = 17;
                    e0 = new E0(activity, i10);
                } else {
                    if (i10 == 3) {
                        str = "finger_stone";
                    } else {
                        str = i10 == 2 ? "finger_scissor" : "finger_cloth";
                    }
                    textView = textViewB4;
                    i = i5;
                    int identifier = activity.getResources().getIdentifier(str, "drawable", activity.getPackageName());
                    if (identifier != 0) {
                        ImageView imageView = new ImageView(activity);
                        imageView.setImageResource(identifier);
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        e0 = imageView;
                        i2 = 2;
                        i3 = 17;
                    } else {
                        if (i10 == 3) {
                            str2 = "✊";
                            i2 = 2;
                        } else {
                            i2 = 2;
                            str2 = i10 == 2 ? "✌" : "✋";
                        }
                        TextView textViewB5 = b(activity, str2, 36, i4);
                        i3 = 17;
                        textViewB5.setGravity(17);
                        e0 = textViewB5;
                    }
                }
                e0.setImportantForAccessibility(i2);
                int i11 = i4;
                frameLayout.addView(e0, new FrameLayout.LayoutParams(a(activity, 60), a(activity, 60), i3));
                TextView textViewB6 = b(activity, "✓", 12, -16369099);
                textViewB6.setGravity(i3);
                textViewB6.setTag("tick");
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(a);
                gradientDrawable2.setCornerRadius(a(activity, 10));
                textViewB6.setBackground(gradientDrawable2);
                textViewB6.setImportantForAccessibility(2);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(a(activity, 20), a(activity, 20), 53);
                int iA = a(activity, 7);
                layoutParams2.rightMargin = iA;
                layoutParams2.topMargin = iA;
                frameLayout.addView(textViewB6, layoutParams2);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, a(activity, 92), 1.0f);
                if (i9 != 2) {
                    layoutParams3.rightMargin = a(activity, 10);
                }
                linearLayout3.addView(frameLayout, layoutParams3);
                arrayList.add(frameLayout);
                arrayList2.add(Integer.valueOf(i10));
                frameLayout.setFocusable(true);
                frameLayout.setOnClickListener( new B0(r10, i10, z4));
                i9++;
                iArr = iArr2;
                r7 = r25;
                dialog2 = dialog;
                textViewB4 = textView;
                i5 = i;
                i4 = i11;
            }
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.topMargin = a(activity, 10);
            linearLayout.addView(linearLayout3, layoutParams4);
            i8++;
            iArr = iArr;
            r6 = r7;
            dialog2 = dialog2;
            i5 = i5;
            i4 = i4;
            i7 = 2;
        }
        L2 r27 = r6;
        TextView textView2 = textViewB4;
        Dialog r28 = dialog2;
        LinearLayout linearLayout4 = new LinearLayout(activity);
        linearLayout4.setGravity(17);
        TextView textViewB7 = b(activity, "⤨", 25, i5);
        textViewB7.setImportantForAccessibility(2);
        linearLayout4.addView(textViewB7, new LinearLayout.LayoutParams(a(activity, 30), a(activity, 44)));
        linearLayout4.addView(b(activity, "随机", 15, i4));
        linearLayout4.setContentDescription("随机结果");
        linearLayout4.setFocusable(true);
        arrayList.add(linearLayout4);
        arrayList2.add(0);
        linearLayout4.setOnClickListener(new C0(r10, z4, 0));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, a(activity, 48));
        layoutParams5.topMargin = a(activity, 12);
        linearLayout.addView(linearLayout4, layoutParams5);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, a(activity, 52));
        layoutParams6.topMargin = a(activity, 16);
        linearLayout.addView(textView2, layoutParams6);
        textView2.setOnClickListener(new D0(r10, textView2, r28, c0071s, 0));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            r27.b((View) it.next(), 5, 16.0f);
        }
        z4.run();
        r28.setContentView(linearLayout);
        r28.setOnDismissListener(new DialogInterfaceOnDismissListenerC0037j0(1, runnableC0048m));
        r28.setCanceledOnTouchOutside(true);
        Window window = r28.getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        window.addFlags(2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        window.setAttributes(attributes);
        window.setGravity(81);
        r28.show();
        window.setLayout(Math.min(activity.getResources().getDisplayMetrics().widthPixels - a(activity, 24), a(activity, 420)), -2);
        WindowManager.LayoutParams attributes2 = window.getAttributes();
        attributes2.y = a(activity, 12);
        window.setAttributes(attributes2);
    }
}
