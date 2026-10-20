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
import android.app.Activity;
import android.widget.PopupWindow;
import java.util.function.IntConsumer;

/* 本文件合并了仅大小写不同的两个类 L2 / l2（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: L2.class */
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
        int C0035i2 = i - 1;
        if (C0035i2 == 0) {
            return 16.0f;
        }
        float f = 20.0f;
        if (C0035i2 != 1) {
            f = 20.0f;
            if (C0035i2 != 4) {
                f = 22.0f;
                if (C0035i2 != 6) {
                    if (C0035i2 == 7) {
                        return 12.0f;
                    }
                    if (C0035i2 != 9) {
                        return C0035i2 != 10 ? 14.0f : 22.0f;
                    }
                    return 16.0f;
                }
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
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                f(layerDrawable.getDrawable(i));
            }
            return;
        }
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            if (Build.VERSION.SDK_INT < 29) {
                f(stateListDrawable.getCurrent());
                return;
            }
            for (int C0035i2 = 0; C0035i2 < H0.b(stateListDrawable); C0035i2++) {
                f(H0.e(stateListDrawable, C0035i2));
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
        int C0035i2;
        Drawable drawableC = c(i, f);
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        i(view, drawableC);
        view.setBackground(drawableC);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        boolean z = this.a;
        float C0023f2 = this.b;
        if (i == 4) {
            view.setForeground(null);
            view.setElevation((z ? 0.5f : 1.0f) * C0023f2);
        }
        if (i == 3) {
            view.setElevation((!this.c || z) ? 0.0f : C0023f2);
        }
        boolean z2 = view instanceof TextView;
        if (z2 && !(view instanceof EditText) && (i == 5 || i == 7 || i == 9 || i == 10)) {
            int iRound = Math.round((i == 10 ? 12 : 14) * C0023f2);
            int iRound2 = i == 10 ? Math.round(10.0f * C0023f2) : 0;
            view.setPadding(Math.max(view.getPaddingLeft(), iRound), Math.max(view.getPaddingTop(), iRound2), Math.max(view.getPaddingRight(), iRound), Math.max(view.getPaddingBottom(), iRound2));
            TextView textView = (TextView) view;
            textView.setMinHeight(Math.max(textView.getMinHeight(), Math.round(C0023f2 * 48.0f)));
        }
        if (z2) {
            if (i == 5 || i == 7) {
                TextView textView2 = (TextView) view;
                int i3 = z ? -8947832 : -7171426;
                if (i == 7) {
                    C0035i2 = -15188933;
                } else {
                    C0035i2 = z ? -1644817 : -13421773;
                }
                textView2.setTextColor(new ColorStateList(new int[][]{new int[]{-16842910}, new int[]{R.attr.state_selected}, new int[]{R.attr.state_checked}, new int[0]}, new int[]{i3, -15188933, -15188933, C0035i2}));
            }
        }
    }

    public final Drawable c(int i, float f) {
        Drawable drawableH;
        float C0023f2 = this.b;
        float f3 = f * C0023f2;
        if (i == 13) {
            return null;
        }
        boolean z = this.a;
        boolean z2 = true;
        if (i == 4) {
            GradientDrawable gradientDrawableG = g(z ? -14408650 : -657672, f3, false);
            gradientDrawableG.setStroke(Math.max(1, Math.round(C0023f2 * 0.5f)), z ? 268435455 : 134217728);
            return new RippleDrawable(ColorStateList.valueOf(z ? 419430399 : 335575403), gradientDrawableG, g(-1, f3, false));
        }
        if (i == 9 || i == 10) {
            return new RippleDrawable(ColorStateList.valueOf(z ? 587202559 : 570456427), null, g(-1, f3, false));
        }
        if (i == 10) {
            float f4 = C0023f2 * 6.0f;
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
            if (i != 7) {
                z2 = false;
            }
            drawableH = h(i, f3, z2, false);
        }
        if (i == 5 || i == 7 || i == 9 || i == 11 || i == 8) {
            return new RippleDrawable(ColorStateList.valueOf(z ? 587202559 : 570471842), drawableH, g(-1, f3, false));
        }
        return drawableH;
    }

    public final int d() {
        int i;
        if (this.a) {
            i = -15198168;
        } else {
            i = this.c ? -657672 : -1;
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004e  */
    /* JADX WARN: Code duplicated, block: B:22:0x0055  */
    public final Drawable h(int i, float f, boolean z, boolean z2) {
        int C0035i2;
        boolean z3 = i == 2;
        int iD = i == 11 ? -619177944 : d();
        boolean z4 = this.a;
        if (i == 3) {
            if (this.c) {
                C0035i2 = z4 ? -14408650 : -1;
                iD = C0035i2;
            } else if (z4) {
                iD = -14145480;
            } else {
                iD = -1184275;
            }
        } else if (i == 2) {
            C0035i2 = z4 ? -15198168 : -526086;
            iD = C0035i2;
        } else if (i == 5 || i == 8) {
            if (z4) {
                iD = -14145480;
            } else {
                iD = -1184275;
            }
        }
        int i3 = iD;
        if (z) {
            i3 = iD;
            if (i == 5) {
                if (z2) {
                    i3 = z4 ? -12427174 : -4926002;
                } else {
                    i3 = -16730718;
                }
            }
        }
        if (i == 7) {
            if (z2) {
                i3 = -4926002;
                if (z4) {
                    i3 = -12427174;
                }
            } else {
                i3 = -11214901;
            }
        }
        GradientDrawable gradientDrawableG = g(i3, f, z3);
        if (z && i == 8) {
            gradientDrawableG.setStroke(Math.max(1, Math.round(this.b)), z2 ? -8284786 : a());
        }
        return gradientDrawableG;
    }
}

/* JADX INFO: loaded from: l2.class */
final /* synthetic */ class l2 implements View.OnClickListener {
    public final C0067q2 a;
    public final PopupWindow b;
    public final View c;
    public final IntConsumer d;
    public final int e;

    public /* synthetic */ l2(C0067q2 q2Var, PopupWindow popupWindow, View view, IntConsumer intConsumer, int i) {
        this.a = q2Var;
        this.b = popupWindow;
        this.c = view;
        this.d = intConsumer;
        this.e = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        C0067q2 q2Var = this.a;
        q2Var.getClass();
        this.b.dismiss();
        View view2 = this.c;
        if (view2.isAttachedToWindow() && view2.isShown()) {
            Activity activity = q2Var.a;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            this.d.accept(this.e);
        }
    }
}
