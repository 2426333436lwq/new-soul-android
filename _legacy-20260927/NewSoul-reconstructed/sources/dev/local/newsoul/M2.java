/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.widget.Button
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import dev.local.newsoul.T0;
import dev.local.newsoul.Y;
import dev.local.newsoul.q2;

public final class M2
implements Runnable {
    public final Activity a;
    public final q2 b;
    public final TextView c;
    public final String d;
    public final TextView e;
    public final boolean f;
    public final Y g;
    public final String h;
    public final String[] i;

    public /* synthetic */ M2(Activity activity, q2 q22, Button button, String string, TextView textView, Y y, String string2, String[] stringArray) {
        this.a = activity;
        this.b = q22;
        this.c = button;
        this.d = string;
        this.e = textView;
        this.f = false;
        this.g = y;
        this.h = string2;
        this.i = stringArray;
    }

    @Override
    public final void run() {
        Object object = this.a;
        if (!object.isFinishing() && !object.isDestroyed() && (object = this.b.e) != null && object.isShowing()) {
            object = this.c;
            object.setEnabled(true);
            object.setAlpha(1.0f);
            object = this.d;
            TextView textView = this.e;
            if (object != null) {
                textView.setText((CharSequence)"\u68c0\u6d4b\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
            } else {
                boolean bl = this.f;
                Y y = this.g;
                String string = this.h;
                if (bl) {
                    object = (String)y.a;
                    StringBuilder stringBuilder = new StringBuilder("\u53d1\u5e03\u6e20\u9053\u7248\u672c\uff1a");
                    stringBuilder.append((String)object);
                    textView.setText((CharSequence)stringBuilder.toString());
                } else if (string == null) {
                    object = (String)y.a;
                    StringBuilder stringBuilder = new StringBuilder("\u6700\u65b0\u7248\u672c\uff1a");
                    stringBuilder.append((String)object);
                    stringBuilder.append("\n\u5f53\u524d\u7248\u672c\u672a\u77e5");
                    textView.setText((CharSequence)stringBuilder.toString());
                } else {
                    int n = T0.c((String)y.a, string);
                    object = n > 0 ? "\u53d1\u73b0\u65b0\u7248\u672c" : (n == 0 ? "\u5f53\u524d\u5df2\u662f\u6700\u65b0\u7248" : "\u5f53\u524d\u7248\u672c\u9ad8\u4e8e\u53d1\u5e03\u7248\u672c");
                    StringBuilder stringBuilder = new StringBuilder("\u6700\u65b0\u7248\u672c\uff1a");
                    stringBuilder.append((String)y.a);
                    stringBuilder.append("\n");
                    stringBuilder.append((String)object);
                    textView.setText((CharSequence)stringBuilder.toString());
                }
                if (bl || string == null || T0.c((String)y.a, string) > 0) {
                    object = (String)y.b;
                    this.i[0] = object;
                }
            }
        }
    }
}

