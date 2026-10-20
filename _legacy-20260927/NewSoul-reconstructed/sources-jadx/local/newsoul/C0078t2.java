package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: dev.local.newsoul.t2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0078t2 extends LinearLayout {
    public final /* synthetic */ Context a;
    public final /* synthetic */ C0086v2 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0078t2(C0086v2 c0086v2, Context context, Context context2) {
        super(context);
        this.b = c0086v2;
        this.a = context2;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Context context = this.a;
        int i3 = AbstractC0090w2.i(context, 48);
        for (TextView textView : this.b.d) {
            if (textView != null) {
                i3 = Math.max(i3, AbstractC0090w2.i(context, 24) + ((int) Math.ceil(textView.getPaint().measureText(textView.getText().toString()))));
            }
        }
        int iMax = i3 * 5;
        if (View.MeasureSpec.getMode(i) != 0) {
            iMax = Math.max(iMax, View.MeasureSpec.getSize(i));
        }
        int i4 = 0;
        while (i4 < getChildCount()) {
            ((LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams()).width = (iMax / 5) + (i4 < iMax % 5 ? 1 : 0);
            i4++;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMax, 1073741824), i2);
    }
}
