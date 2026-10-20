package dev.local.newsoul;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class L2 {
    public static final WeakHashMap d = new WeakHashMap();
    public static final WeakHashMap e = new WeakHashMap();
    public final boolean a;
    public final float b;
    public boolean c;

    public L2(boolean z, float f) {
        this.a = z;
        this.b = f;
    }

    public static float e(int i) {
        if (i == 0) {
            throw null;
        }
        int i2 = i - 1;
        if (i2 == 0) {
            return 16.0f;
        }
        float f = 20.0f;
        if (i2 != 1 && i2 != 4) {
            f = 22.0f;
            if (i2 != 6) {
                if (i2 == 7) {
                    return 12.0f;
                }
                if (i2 != 9) {
                    return i2 != 10 ? 14.0f : 22.0f;
                }
                return 16.0f;
            }
        }
        return f;
    }

    public static void f(Drawable drawable) {
        if (drawable instanceof G0) {
            ((G0) drawable).getClass();
            throw null;
        }
        if (drawable instanceof InsetDrawable) {
            f(((InsetDrawable) drawable).getDrawable());
            return;
        }
        int i = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            while (i < layerDrawable.getNumberOfLayers()) {
                f(layerDrawable.getDrawable(i));
                i++;
            }
            return;
        }
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            if (Build.VERSION.SDK_INT < 29) {
                f(stateListDrawable.getCurrent());
                return;
            }
            while (i < stateListDrawable.getStateCount()) {
                f(stateListDrawable.getStateDrawable(i));
                i++;
            }
        }
    }

    public static GradientDrawable g(int i, float f, boolean z) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        if (z) {
            gradientDrawable.setCornerRadii(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f});
        } else {
            gradientDrawable.setCornerRadius(f);
        }
        return gradientDrawable;
    }

    public static void i(View view, Drawable drawable) {
        Drawable drawable2 = (Drawable) d.put(view, drawable);
        if (drawable2 != drawable) {
            f(drawable2);
        }
        if (e.put(view, Boolean.TRUE) != null) {
            return;
        }
        view.addOnAttachStateChangeListener(new D2(1));
    }

    public final int a() {
        return this.a ? -16730718 : -16746133;
    }

    public final void b(View view, int i, float f) {
        int i2;
        Drawable drawableC = c(i, f);
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        i(view, drawableC);
        view.setBackground(drawableC);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        boolean z = this.a;
        float f2 = this.b;
        if (i == 4) {
            view.setForeground(null);
            view.setElevation((z ? 0.5f : 1.0f) * f2);
        }
        if (i == 3) {
            view.setElevation((!this.c || z) ? 0.0f : f2);
        }
        boolean z2 = view instanceof TextView;
        if (z2 && !(view instanceof EditText) && (i == 5 || i == 7 || i == 9 || i == 10)) {
            int iRound = Math.round((i == 10 ? 12 : 14) * f2);
            int iRound2 = i == 10 ? Math.round(10.0f * f2) : 0;
            view.setPadding(Math.max(view.getPaddingLeft(), iRound), Math.max(view.getPaddingTop(), iRound2), Math.max(view.getPaddingRight(), iRound), Math.max(view.getPaddingBottom(), iRound2));
            TextView textView = (TextView) view;
            textView.setMinHeight(Math.max(textView.getMinHeight(), Math.round(f2 * 48.0f)));
        }
        if (z2) {
            if (i == 5 || i == 7) {
                TextView textView2 = (TextView) view;
                int[][] iArr = {new int[]{-16842910}, new int[]{R.attr.state_selected}, new int[]{R.attr.state_checked}, new int[0]};
                int i3 = z ? -8947832 : -7171426;
                if (i == 7) {
                    i2 = -15188933;
                } else {
                    i2 = z ? -1644817 : -13421773;
                }
                textView2.setTextColor(new ColorStateList(iArr, new int[]{i3, -15188933, -15188933, i2}));
            }
        }
    }

    public final Drawable c(int i, float f) {
        Drawable drawableH;
        float f2 = this.b;
        float f3 = f * f2;
        if (i == 13) {
            return null;
        }
        boolean z = this.a;
        if (i == 4) {
            GradientDrawable gradientDrawableG = g(z ? -14408650 : -657672, f3, false);
            gradientDrawableG.setStroke(Math.max(1, Math.round(f2 * 0.5f)), z ? 268435455 : 134217728);
            return new RippleDrawable(ColorStateList.valueOf(z ? 419430399 : 335575403), gradientDrawableG, g(-1, f3, false));
        }
        if (i == 9 || i == 10) {
            return new RippleDrawable(ColorStateList.valueOf(z ? 587202559 : 570456427), null, g(-1, f3, false));
        }
        if (i == 10) {
            float f4 = f2 * 6.0f;
            return new InsetDrawable(c(9, f), 0, Math.round(f4), 0, Math.round(f4));
        }
        if (i == 12 || i == 6) {
            return new RippleDrawable(ColorStateList.valueOf(z ? 587202559 : 570471842), null, g(-1, f3, false));
        }
        if (i == 5 || i == 8 || i == 7) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{-16842910, R.attr.state_selected}, h(i, f3, true, true));
            stateListDrawable.addState(new int[]{-16842910, R.attr.state_checked}, h(i, f3, true, true));
            stateListDrawable.addState(new int[]{-16842910}, h(i, f3, i == 7, true));
            stateListDrawable.addState(new int[]{R.attr.state_selected}, h(i, f3, true, false));
            stateListDrawable.addState(new int[]{R.attr.state_checked}, h(i, f3, true, false));
            stateListDrawable.addState(new int[]{R.attr.state_focused}, h(i, f3, true, false));
            stateListDrawable.addState(new int[0], h(i, f3, i == 7, false));
            drawableH = stateListDrawable;
        } else {
            drawableH = h(i, f3, i == 7, false);
        }
        if (i == 5 || i == 7 || i == 9 || i == 11 || i == 8) {
            return new RippleDrawable(ColorStateList.valueOf(z ? 587202559 : 570471842), drawableH, g(-1, f3, false));
        }
        return drawableH;
    }

    public final int d() {
        if (this.a) {
            return -15198168;
        }
        return this.c ? -657672 : -1;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0030  */
    /* JADX WARN: Code duplicated, block: B:20:0x0032  */
    public final Drawable h(int i, float f, boolean z, boolean z2) {
        int i2;
        boolean z3 = i == 2;
        int iD = i == 11 ? -619177944 : d();
        boolean z4 = this.a;
        if (i == 3) {
            if (this.c) {
                i2 = z4 ? -14408650 : -1;
                iD = i2;
            } else if (z4) {
                iD = -14145480;
            } else {
                iD = -1184275;
            }
        } else if (i == 2) {
            i2 = z4 ? -15198168 : -526086;
            iD = i2;
        } else if (i == 5 || i == 8) {
            if (z4) {
                iD = -14145480;
            } else {
                iD = -1184275;
            }
        }
        int i3 = -4926002;
        if (z && i == 5) {
            if (z2) {
                iD = z4 ? -12427174 : -4926002;
            } else {
                iD = -16730718;
            }
        }
        if (i == 7) {
            if (!z2) {
                i3 = -11214901;
            } else if (z4) {
                i3 = -12427174;
            }
            iD = i3;
        }
        GradientDrawable gradientDrawableG = g(iD, f, z3);
        if (z && i == 8) {
            gradientDrawableG.setStroke(Math.max(1, Math.round(this.b)), z2 ? -8284786 : a());
        }
        return gradientDrawableG;
    }
}
