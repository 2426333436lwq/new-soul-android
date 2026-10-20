package dev.local.newsoul;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.h2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0031h2 {
    public final TextView d;
    public final TextView e;
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final V1 f = new V1(this, 1);

    public C0031h2(C0067q2 c0067q2, LinearLayout linearLayout) {
        TextView textViewC = AbstractC0090w2.c(linearLayout, "部分功能未就绪，原因尚未确认。");
        this.e = textViewC;
        textViewC.setVisibility(8);
        TextView textViewC2 = AbstractC0090w2.c(linearLayout, "功能兼容性检测");
        this.d = textViewC2;
        textViewC2.setTextColor(c0067q2.k);
        textViewC2.setMinHeight(AbstractC0090w2.i(c0067q2.g, 48));
        textViewC2.setGravity(16);
        textViewC2.setOnClickListener(new ViewOnClickListenerC0072s0(c0067q2, 2));
        AbstractC0090w2.g(textViewC2);
        textViewC2.setVisibility(8);
    }

    public final void a() {
        TextView textView = this.e;
        V1 v1 = this.f;
        textView.removeCallbacks(v1);
        if (textView.isAttachedToWindow()) {
            textView.post(v1);
        } else {
            b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b() {
        boolean z = false;
        boolean z2 = false;
        for (CompoundButton compoundButton : this.a) {
            boolean z3 = true;
            z |= !compoundButton.isEnabled();
            if (compoundButton.isEnabled() || this.b.contains(compoundButton)) {
                z3 = false;
            }
            z2 |= z3;
        }
        Iterator it = this.c.iterator();
        boolean asBoolean = false;
        while (it.hasNext()) {
            asBoolean |= ((BooleanSupplier) it.next()).getAsBoolean();
        }
        int i = (z || asBoolean) ? 0 : 8;
        TextView textView = this.e;
        int visibility = textView.getVisibility();
        int i2 = z2 ? 0 : 8;
        TextView textView2 = this.d;
        if (visibility == i2 && textView2.getVisibility() == i) {
            return;
        }
        textView.setAccessibilityLiveRegion(AbstractC0090w2.h((View) textView.getParent()) ? 1 : 0);
        textView.setVisibility(z2 ? 0 : 8);
        textView2.setVisibility(i);
    }
}
