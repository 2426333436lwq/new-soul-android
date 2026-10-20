package dev.local.newsoul;

import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class Q {
    public final TextView a;
    public final TextView b;
    public final TextView c;

    public Q(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.a = textView;
        this.b = textView2;
        TextView textViewC = AbstractC0090w2.c(linearLayout, "");
        this.c = textViewC;
        textViewC.setAccessibilityLiveRegion(1);
        textViewC.setVisibility(8);
    }

    public final void a(String str, boolean z) {
        TextView textView = this.c;
        textView.setText(str);
        textView.setVisibility(0);
        boolean z2 = !z;
        TextView textView2 = this.a;
        textView2.setEnabled(z2);
        TextView textView3 = this.b;
        textView3.setEnabled(z2);
        textView2.setAlpha(z ? 0.5f : 1.0f);
        textView3.setAlpha(z ? 0.5f : 1.0f);
    }
}
