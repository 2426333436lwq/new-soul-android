package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class R0 extends ViewGroup {
    public final int a;

    public R0(Context context) {
        super(context);
        this.a = AbstractC0090w2.i(context, 8);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        boolean z2 = getLayoutDirection() == 1;
        int i6 = 0;
        int i7 = 0;
        int iMax = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i9 = this.a;
            if (i6 > 0 && i6 + measuredWidth > i5) {
                i7 += iMax + i9;
                i6 = 0;
                iMax = 0;
            }
            int i10 = z2 ? (i5 - i6) - measuredWidth : i6;
            childAt.layout(i10, i7, i10 + measuredWidth, i7 + measuredHeight);
            i6 += measuredWidth + i9;
            iMax = Math.max(iMax, measuredHeight);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int i3 = 0;
        int iMax = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            int i6 = childAt.getLayoutParams().width;
            int iMin = i6 == -1 ? size : Math.min(size, i6);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            int i7 = this.a;
            if (i4 > 0 && i4 + iMin > size) {
                i3 += iMax + i7;
                iMax = 0;
                i4 = 0;
            }
            i4 += iMin + i7;
            iMax = Math.max(iMax, childAt.getMeasuredHeight());
        }
        setMeasuredDimension(size, View.resolveSize(i3 + iMax, i2));
    }
}
