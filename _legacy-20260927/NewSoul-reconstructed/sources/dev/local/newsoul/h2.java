/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.CompoundButton
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.V1;
import dev.local.newsoul.q2;
import dev.local.newsoul.s0_0;
import dev.local.newsoul.w2;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public final class h2 {
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final TextView d;
    public final TextView e;
    public final V1 f = new V1(this, 1);

    public h2(q2 q22, LinearLayout linearLayout) {
        TextView textView;
        this.e = textView = w2.c(linearLayout, "\u90e8\u5206\u529f\u80fd\u672a\u5c31\u7eea\uff0c\u539f\u56e0\u5c1a\u672a\u786e\u8ba4\u3002");
        textView.setVisibility(8);
        linearLayout = w2.c(linearLayout, "\u529f\u80fd\u517c\u5bb9\u6027\u68c0\u6d4b");
        this.d = linearLayout;
        linearLayout.setTextColor(q22.k);
        linearLayout.setMinHeight(w2.i(q22.g, 48));
        linearLayout.setGravity(16);
        linearLayout.setOnClickListener((View.OnClickListener)new s0_0(q22, 2));
        w2.g((View)linearLayout);
        linearLayout.setVisibility(8);
    }

    public final void a() {
        TextView textView = this.e;
        V1 v1 = this.f;
        textView.removeCallbacks((Runnable)v1);
        if (textView.isAttachedToWindow()) {
            textView.post((Runnable)v1);
        } else {
            this.b();
        }
    }

    public final void b() {
        int n;
        int n2;
        Object object;
        TextView textView = this.a.iterator();
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        while (textView.hasNext()) {
            object = (CompoundButton)textView.next();
            boolean bl = object.isEnabled();
            n2 = 1;
            n = n4 | bl ^ true;
            n4 = !object.isEnabled() && !this.b.contains(object) ? n2 : 0;
            n5 |= n4;
            n4 = n;
        }
        object = this.c.iterator();
        n = 0;
        while (object.hasNext()) {
            n |= ((BooleanSupplier)object.next()).getAsBoolean();
        }
        n4 = n4 == 0 && n == 0 ? 8 : 0;
        textView = this.e;
        n2 = textView.getVisibility();
        n = n5 != 0 ? 0 : 8;
        object = this.d;
        if (n2 == n && object.getVisibility() == n4) {
            return;
        }
        textView.setAccessibilityLiveRegion(w2.h((View)textView.getParent()) ? 1 : 0);
        n5 = n5 != 0 ? n3 : 8;
        textView.setVisibility(n5);
        object.setVisibility(n4);
    }
}

