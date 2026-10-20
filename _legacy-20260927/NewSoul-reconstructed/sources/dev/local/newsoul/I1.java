/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Color
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dev.local.newsoul.o1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import java.io.Serializable;

public final class I1
implements Runnable {
    public final int a;
    public final TextView b;
    public final Activity c;
    public final Object d;
    public final View e;
    public final Object f;
    public final Object g;
    public final Serializable h;

    public /* synthetic */ I1(Activity activity, RelativeLayout relativeLayout, TextView textView, View view, RelativeLayout.LayoutParams layoutParams, boolean[] blArray, View view2) {
        this.a = 0;
        this.c = activity;
        this.d = relativeLayout;
        this.b = textView;
        this.e = view;
        this.g = layoutParams;
        this.h = blArray;
        this.f = view2;
    }

    public /* synthetic */ I1(Button button, String[] stringArray, TextView textView, Thread[] threadArray, Activity activity, q2 q22, String string) {
        this.a = 1;
        this.b = button;
        this.d = stringArray;
        this.e = textView;
        this.f = threadArray;
        this.c = activity;
        this.g = q22;
        this.h = string;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        View view;
        boolean bl;
        View view2;
        switch (this.a) {
            default: {
                TextView textView = this.b;
                textView.setEnabled(false);
                textView.setAlpha(0.5f);
                String[] stringArray = (String[])this.d;
                stringArray[0] = null;
                TextView textView2 = (TextView)this.e;
                textView2.setText((CharSequence)"\u6b63\u5728\u68c0\u67e5\u2026");
                Object object = (String)((Object)this.h);
                textView = (Button)textView;
                ((Thread[])this.f)[0] = object = new Thread((Runnable)new o1_0(this.c, (q2)this.g, (Button)textView, textView2, (String)object, stringArray), "NewSoul-UpdateCheck");
                ((Thread)object).start();
                return;
            }
            case 0: 
        }
        Activity activity = this.c;
        RelativeLayout relativeLayout = (RelativeLayout)this.d;
        Object object = this.b;
        View view3 = (View)this.f;
        try {
            if (activity.isFinishing()) return;
            if (activity.isDestroyed()) {
                return;
            }
            view2 = relativeLayout.findViewWithTag((Object)"dev.local.newsoul.settings.entry.v2");
            bl = "\u8bbe\u7f6e".contentEquals(object.getText());
            view = this.e;
        }
        catch (Throwable throwable) {}
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.g;
        if (!bl) {
            if (view2 == null) return;
            relativeLayout.removeView(view2);
            view3 = new RelativeLayout.LayoutParams(layoutParams);
            view.setLayoutParams((ViewGroup.LayoutParams)view3);
            return;
        }
        float f = Color.luminance((int)object.getCurrentTextColor());
        bl = (double)f > 0.45;
        object = (boolean[])this.h;
        if (view2 != null && object[0] == bl) {
            return;
        }
        if (view2 != null) {
            relativeLayout.removeView(view2);
        }
        object[0] = (TextView)bl;
        view2 = w2.j(activity, bl);
        view2.setId(View.generateViewId());
        view2.setTag((Object)"dev.local.newsoul.settings.entry.v2");
        object = new RelativeLayout.LayoutParams(-1, w2.i((Context)activity, 66));
        object.addRule(3, view3.getId());
        relativeLayout.addView(view2, (ViewGroup.LayoutParams)object);
        view3 = new RelativeLayout.LayoutParams(layoutParams);
        view3.removeRule(10);
        view3.addRule(3, view2.getId());
        view.setLayoutParams((ViewGroup.LayoutParams)view3);
        Log.i((String)"SoulHook102", (String)"settings entry inserted; trigger=private H5Activity.m1");
        return;
        Log.e((String)"SoulHook102", (String)"Settings entry failed", (Throwable)throwable);
    }
}

