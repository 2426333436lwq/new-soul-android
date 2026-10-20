package dev.local.newsoul;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public final class C1 extends FrameLayout {
    public final M0 a;
    public final LinearLayout b;
    public final TextView[] c;
    public int d;
    public float e;
    public float f;
    public ValueAnimator g;
    public IntConsumer h;

    /* JADX WARN: Illegal instructions before constructor call */
    public C1(C0067q2 c0067q2, TextView textView, String[] strArr, int i) {
        super(c0067q2.g);
        Context context = c0067q2.g;
        this.d = -1;
        L2 l2 = c0067q2.c;
        M0 m0 = new M0(context, l2, true);
        this.a = m0;
        int i2 = AbstractC0090w2.i(context, 4);
        m0.setPadding(i2, i2, i2, i2);
        addView(m0, new FrameLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(context);
        this.b = linearLayout;
        linearLayout.setGravity(16);
        m0.addView(linearLayout, new FrameLayout.LayoutParams(-1, -2));
        AbstractC0090w2.v(linearLayout, textView, strArr.length);
        this.c = new TextView[strArr.length];
        int[][] iArr = {new int[]{R.attr.state_selected}, new int[0]};
        int i3 = c0067q2.k;
        int i4 = c0067q2.i;
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{i3, i4});
        for (int i5 = 0; i5 < strArr.length; i5++) {
            TextView textViewN = AbstractC0090w2.n(context, strArr[i5], i, i4);
            this.c[i5] = textViewN;
            textViewN.setGravity(17);
            textViewN.setTextColor(colorStateList);
            textViewN.setPadding(AbstractC0090w2.i(context, 14), AbstractC0090w2.i(context, 10), AbstractC0090w2.i(context, 14), AbstractC0090w2.i(context, 10));
            textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
            float f = l2.b;
            textViewN.setForeground(new RippleDrawable(ColorStateList.valueOf(l2.a ? 419430399 : 402684267), null, new InsetDrawable((Drawable) L2.g(-1, 999.0f * f, false), 0, Math.round(f * 3.0f), 0, Math.round(f * 3.0f))));
            AbstractC0090w2.w(textViewN, i5);
            textViewN.setOnClickListener(new ViewOnClickListenerC0092x0(this, i5, 1));
            this.b.addView(textViewN, new LinearLayout.LayoutParams(0, -1, 1.0f));
        }
    }

    public final void a(boolean z) {
        final float left;
        int i = 0;
        ValueAnimator valueAnimator = this.g;
        this.g = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        int i2 = this.d;
        TextView[] textViewArr = this.c;
        M0 m0 = this.a;
        if (i2 < 0) {
            left = 0.0f;
        } else {
            left = textViewArr[this.d].getLeft() + m0.getPaddingLeft();
        }
        int i3 = this.d;
        final float width = i3 < 0 ? 0.0f : textViewArr[i3].getWidth();
        if (z && isAttachedToWindow() && ValueAnimator.areAnimatorsEnabled()) {
            final float f = this.f;
            if (f > 0.0f && width > 0.0f) {
                final float f2 = this.e;
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                this.g = valueAnimatorOfFloat;
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
                valueAnimatorOfFloat.addUpdateListener(new A1(this, f2, left, f, width));
                valueAnimatorOfFloat.addListener(new B1(this, i));
                valueAnimatorOfFloat.start();
                return;
            }
        }
        this.e = left;
        this.f = width;
        m0.c(left, width, false);
    }

    public final void b(String str, String[] strArr, boolean z) {
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                i = -1;
                break;
            } else if (strArr[i].equals(str)) {
                break;
            } else {
                i++;
            }
        }
        if (i == this.d) {
            return;
        }
        this.d = i;
        int i2 = 0;
        while (true) {
            TextView[] textViewArr = this.c;
            if (i2 >= textViewArr.length) {
                a(z);
                return;
            } else {
                textViewArr[i2].setSelected(i2 == this.d);
                i2++;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ValueAnimator valueAnimator = this.g;
        this.g = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.g == null || z) {
            a(false);
        }
    }
}
