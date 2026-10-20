package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: dev.local.newsoul.b2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0007b2 extends LinearLayout {
    public final /* synthetic */ Activity a;
    public final /* synthetic */ TextView b;
    public final /* synthetic */ EditText c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0007b2(Context context, Activity activity, TextView textView, EditText editText) {
        super(context);
        this.a = activity;
        this.b = textView;
        this.c = editText;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        Activity activity = this.a;
        int i3 = AbstractC0090w2.i(activity, 64);
        TextView textView = this.b;
        int i4 = (View.MeasureSpec.getMode(i) == 0 || size >= AbstractC0090w2.i(activity, 128) + Math.max(i3, textView.getPaddingRight() + (textView.getPaddingLeft() + ((int) Math.ceil((double) textView.getPaint().measureText(textView.getText().toString())))))) ? 0 : 1;
        setOrientation(i4);
        EditText editText = this.c;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) editText.getLayoutParams();
        layoutParams.width = i4 != 0 ? -1 : 0;
        layoutParams.weight = i4 != 0 ? 0.0f : 1.0f;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams2.setMarginStart(i4 != 0 ? 0 : AbstractC0090w2.i(activity, 8));
        layoutParams2.leftMargin = i4 != 0 ? 0 : AbstractC0090w2.i(activity, 8);
        layoutParams2.topMargin = i4 != 0 ? AbstractC0090w2.i(activity, 8) : 0;
        layoutParams2.gravity = i4 != 0 ? 8388613 : 16;
        layoutParams.height = -2;
        layoutParams2.height = -2;
        super.onMeasure(i, i2);
        if (i4 == 0) {
            int iMax = Math.max(editText.getMeasuredHeight(), textView.getMeasuredHeight());
            layoutParams.height = iMax;
            layoutParams2.height = iMax;
            super.onMeasure(i, i2);
        }
    }
}
