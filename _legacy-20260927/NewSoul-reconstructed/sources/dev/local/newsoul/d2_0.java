/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.R0;
import dev.local.newsoul.c2_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import java.util.ArrayList;
import java.util.Locale;

/*
 * Renamed from dev.local.newsoul.d2
 */
public final class d2_0
implements Runnable {
    public final EditText a;
    public final ArrayList b;
    public final TextView c;
    public final R0 d;
    public final q2 e;
    public final Activity f;
    public final String[] g;
    public final String[] h;
    public final int[] i;
    public final TextView j;
    public final TextView k;

    public d2_0(EditText editText, ArrayList arrayList, TextView textView, R0 r0, q2 q22, Activity activity, String[] stringArray, String[] stringArray2, int[] nArray, TextView textView2, TextView textView3) {
        this.a = editText;
        this.b = arrayList;
        this.c = textView;
        this.d = r0;
        this.e = q22;
        this.f = activity;
        this.g = stringArray;
        this.h = stringArray2;
        this.i = nArray;
        this.j = textView2;
        this.k = textView3;
    }

    @Override
    public final void run() {
        q2 q22;
        Object object = this.a.getText().toString().trim();
        String string = object.matches("(?s).*\\R.*") ? "" : object.toLowerCase(Locale.ROOT);
        ArrayList arrayList = this.b;
        Object object2 = arrayList.iterator();
        int n = 0;
        while (object2.hasNext()) {
            n |= ((String)object2.next()).equalsIgnoreCase((String)object);
        }
        object2 = n != 0 ? "\u5df2\u6dfb\u52a0" : "\u6dfb\u52a0";
        Object object3 = this.c;
        object3.setText((CharSequence)object2);
        boolean bl = !object.isEmpty() && n == 0;
        object3.setEnabled(bl);
        object3.setSelected(object3.isEnabled());
        object = this.d;
        object.removeAllViews();
        n = 0;
        int n2 = 0;
        while (true) {
            int n3 = arrayList.size();
            q22 = this.e;
            object3 = this.f;
            if (n2 >= n3) break;
            String string2 = (String)arrayList.get(n2);
            if (string2.toLowerCase(Locale.ROOT).contains(string)) {
                LinearLayout linearLayout = new LinearLayout(q22.g);
                linearLayout.setGravity(16);
                linearLayout.setMinimumHeight(w2.i((Context)object3, 48));
                n3 = string2.codePointCount(0, string2.length()) <= 8 ? 1 : 0;
                TextView textView = w2.n(q22.g, string2, 16, q22.i);
                textView.setPadding(w2.i((Context)object3, 10), w2.i((Context)object3, 8), 0, w2.i((Context)object3, 8));
                linearLayout.addView((View)textView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
                object2 = n3 != 0 ? "\u00d7" : "\u5220\u9664";
                object2 = w2.m(q22, (String)object2, "\u5220\u9664\u5c4f\u853d\u8bcd\uff1a".concat(string2));
                int n4 = n3 != 0 ? 48 : 64;
                linearLayout.addView((View)object2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i((Context)object3, n4), -1));
                object2.setMinHeight(w2.i((Context)object3, 48));
                if (n3 != 0) {
                    q22.c.b((View)linearLayout, 5, 16.0f);
                    linearLayout.setSelected(string2.equals(this.g[0]));
                }
                n4 = (int)Math.ceil(textView.getPaint().measureText(string2));
                n4 = w2.i((Context)object3, 64) + n4;
                n3 = n3 != 0 ? n4 : -1;
                object.addView((View)linearLayout, new ViewGroup.LayoutParams(n3, -2));
                object3 = this.i;
                q22 = this.j;
                object2.setOnClickListener((View.OnClickListener)new c2_0(this, this.h, this.b, n2, (int[])object3, (TextView)q22));
                ++n;
            }
            ++n2;
        }
        if (string.isEmpty()) {
            n2 = arrayList.size();
            object2 = new StringBuilder();
            ((StringBuilder)object2).append(n2);
            ((StringBuilder)object2).append("/100");
            object2 = ((StringBuilder)object2).toString();
        } else {
            n2 = arrayList.size();
            object2 = new StringBuilder("\u5339\u914d ");
            ((StringBuilder)object2).append(n);
            ((StringBuilder)object2).append(" \u4e2a \u00b7 \u5171 ");
            ((StringBuilder)object2).append(n2);
            ((StringBuilder)object2).append(" \u4e2a");
            object2 = ((StringBuilder)object2).toString();
        }
        this.k.setText((CharSequence)object2);
        if (n == 0) {
            object2 = q22.g;
            string = string.isEmpty() ? "\u6682\u65e0\u5c4f\u853d\u8bcd" : "\u5217\u8868\u4e2d\u6ca1\u6709\u8fd9\u4e2a\u8bcd\uff0c\u53ef\u70b9\u51fb\u6dfb\u52a0";
            string = w2.n((Context)object2, string, 14, q22.j);
            string.setPadding(0, w2.i((Context)object3, 16), 0, w2.i((Context)object3, 16));
            object.addView((View)string, new ViewGroup.LayoutParams(-1, -2));
        }
    }
}

