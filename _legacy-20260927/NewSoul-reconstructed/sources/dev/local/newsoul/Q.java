/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.w2;

public final class Q {
    public final TextView a;
    public final TextView b;
    public final TextView c;

    public Q(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.a = textView;
        this.b = textView2;
        linearLayout = w2.c(linearLayout, "");
        this.c = linearLayout;
        linearLayout.setAccessibilityLiveRegion(1);
        linearLayout.setVisibility(8);
    }

    public final void a(String string, boolean bl) {
        TextView textView = this.c;
        textView.setText((CharSequence)string);
        textView.setVisibility(0);
        boolean bl2 = bl ^ true;
        string = this.a;
        string.setEnabled(bl2);
        textView = this.b;
        textView.setEnabled(bl2);
        float f = 1.0f;
        float f2 = bl ? 0.5f : 1.0f;
        string.setAlpha(f2);
        f2 = f;
        if (bl) {
            f2 = 0.5f;
        }
        textView.setAlpha(f2);
    }
}

