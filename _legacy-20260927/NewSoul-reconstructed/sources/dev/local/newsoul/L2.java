/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.InsetDrawable
 *  android.graphics.drawable.LayerDrawable
 *  android.graphics.drawable.RippleDrawable
 *  android.graphics.drawable.StateListDrawable
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.widget.EditText
 *  android.widget.TextView
 */
package dev.local.newsoul;

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
import dev.local.newsoul.D2;
import dev.local.newsoul.G0;
import dev.local.newsoul.H0;
import java.util.WeakHashMap;

public final class L2 {
    public static final WeakHashMap d = new WeakHashMap();
    public static final WeakHashMap e = new WeakHashMap();
    public final boolean a;
    public final float b;
    public boolean c;

    public L2(boolean bl, float f) {
        this.a = bl;
        this.b = f;
    }

    public static float e(int n) {
        if (n != 0) {
            if (--n != 0) {
                float f;
                float f2 = f = 20.0f;
                if (n != 1) {
                    f2 = f;
                    if (n != 4) {
                        f2 = 22.0f;
                        if (n != 6) {
                            if (n != 7) {
                                if (n != 9) {
                                    if (n != 10) {
                                        return 14.0f;
                                    }
                                    return 22.0f;
                                }
                                return 16.0f;
                            }
                            return 12.0f;
                        }
                    }
                }
                return f2;
            }
            return 16.0f;
        }
        throw null;
    }

    public static void f(Drawable drawable) {
        if (!(drawable instanceof G0)) {
            if (drawable instanceof InsetDrawable) {
                L2.f(((InsetDrawable)drawable).getDrawable());
            } else {
                int n;
                boolean bl = drawable instanceof LayerDrawable;
                int n2 = 0;
                if (bl) {
                    drawable = (LayerDrawable)drawable;
                    for (n = 0; n < drawable.getNumberOfLayers(); ++n) {
                        L2.f(drawable.getDrawable(n));
                    }
                } else if (drawable instanceof StateListDrawable) {
                    drawable = (StateListDrawable)drawable;
                    if (Build.VERSION.SDK_INT >= 29) {
                        for (n = n2; n < H0.b((StateListDrawable)drawable); ++n) {
                            L2.f(H0.e((StateListDrawable)drawable, n));
                        }
                    } else {
                        L2.f(drawable.getCurrent());
                    }
                }
            }
            return;
        }
        ((Object)((Object)((G0)drawable))).getClass();
        throw null;
    }

    public static GradientDrawable g(int n, float f, boolean bl) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(n);
        if (bl) {
            gradientDrawable.setCornerRadii(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f});
        } else {
            gradientDrawable.setCornerRadius(f);
        }
        return gradientDrawable;
    }

    public static void i(View view, Drawable drawable) {
        Drawable drawable2 = d.put(view, drawable);
        if (drawable2 != drawable) {
            L2.f(drawable2);
        }
        if (e.put(view, Boolean.TRUE) != null) {
            return;
        }
        view.addOnAttachStateChangeListener((View.OnAttachStateChangeListener)new D2(1));
    }

    public final int a() {
        int n = this.a ? -16730718 : -16746133;
        return n;
    }

    public final void b(View view, int n, float f) {
        boolean bl;
        Drawable drawable = this.c(n, f);
        int n2 = view.getPaddingLeft();
        int n3 = view.getPaddingTop();
        int n4 = view.getPaddingRight();
        int n5 = view.getPaddingBottom();
        L2.i(view, drawable);
        view.setBackground(drawable);
        view.setPadding(n2, n3, n4, n5);
        boolean bl2 = this.a;
        float f2 = this.b;
        if (n == 4) {
            view.setForeground(null);
            f = bl2 ? 0.5f : 1.0f;
            view.setElevation(f * f2);
        }
        if (n == 3) {
            f = this.c && !bl2 ? f2 : 0.0f;
            view.setElevation(f);
        }
        if ((bl = view instanceof TextView) && !(view instanceof EditText) && (n == 5 || n == 7 || n == 9 || n == 10)) {
            n4 = n == 10 ? 12 : 14;
            n3 = Math.round((float)n4 * f2);
            n4 = n == 10 ? Math.round(10.0f * f2) : 0;
            view.setPadding(Math.max(view.getPaddingLeft(), n3), Math.max(view.getPaddingTop(), n4), Math.max(view.getPaddingRight(), n3), Math.max(view.getPaddingBottom(), n4));
            drawable = (TextView)view;
            drawable.setMinHeight(Math.max(drawable.getMinHeight(), Math.round(f2 * 48.0f)));
        }
        if (bl && (n == 5 || n == 7)) {
            view = (TextView)view;
            n4 = bl2 ? -8947832 : -7171426;
            n = n == 7 ? -15188933 : (bl2 ? -1644817 : -13421773);
            view.setTextColor(new ColorStateList((int[][])new int[][]{{-16842910}, {0x10100A1}, {0x10100A0}, new int[0]}, new int[]{n4, -15188933, -15188933, n}));
        }
    }

    public final Drawable c(int n, float f) {
        float f2 = this.b;
        float f4 = f * f2;
        if (n == 13) {
            return null;
        }
        boolean bl = this.a;
        boolean bl2 = true;
        boolean bl3 = true;
        if (n == 4) {
            n = bl ? -14408650 : -657672;
            GradientDrawable gradientDrawable = L2.g(n, f4, false);
            int n2 = Math.max(1, Math.round(f2 * 0.5f));
            n = bl ? 0xFFFFFFF : 0x8000000;
            gradientDrawable.setStroke(n2, n);
            n = bl ? 0x18FFFFFF : 335575403;
            return new RippleDrawable(ColorStateList.valueOf((int)n), (Drawable)gradientDrawable, (Drawable)L2.g(-1, f4, false));
        }
        if (n != 9 && n != 10) {
            if (n == 10) {
                Drawable drawable = this.c(9, f);
                f = f2 * 6.0f;
                return new InsetDrawable(drawable, 0, Math.round(f), 0, Math.round(f));
            }
            if (n != 12 && n != 6) {
                StateListDrawable stateListDrawable;
                if (n != 5 && n != 8 && n != 7) {
                    if (n != 7) {
                        bl3 = false;
                    }
                    stateListDrawable = this.h(n, f4, bl3, false);
                } else {
                    stateListDrawable = new StateListDrawable();
                    Drawable drawable = this.h(n, f4, true, true);
                    stateListDrawable.addState(new int[]{-16842910, 0x10100A1}, drawable);
                    drawable = this.h(n, f4, true, true);
                    stateListDrawable.addState(new int[]{-16842910, 0x10100A0}, drawable);
                    bl3 = n == 7;
                    drawable = this.h(n, f4, bl3, true);
                    stateListDrawable.addState(new int[]{-16842910}, drawable);
                    drawable = this.h(n, f4, true, false);
                    stateListDrawable.addState(new int[]{0x10100A1}, drawable);
                    drawable = this.h(n, f4, true, false);
                    stateListDrawable.addState(new int[]{0x10100A0}, drawable);
                    drawable = this.h(n, f4, true, false);
                    stateListDrawable.addState(new int[]{16842908}, drawable);
                    bl3 = n == 7 ? bl2 : false;
                    drawable = this.h(n, f4, bl3, false);
                    stateListDrawable.addState(new int[0], drawable);
                }
                if (n != 5 && n != 7 && n != 9 && n != 11 && n != 8) {
                    return stateListDrawable;
                }
                n = bl ? 0x22FFFFFF : 570471842;
                return new RippleDrawable(ColorStateList.valueOf((int)n), (Drawable)stateListDrawable, (Drawable)L2.g(-1, f4, false));
            }
            n = bl ? 0x22FFFFFF : 570471842;
            return new RippleDrawable(ColorStateList.valueOf((int)n), null, (Drawable)L2.g(-1, f4, false));
        }
        n = bl ? 0x22FFFFFF : 570456427;
        return new RippleDrawable(ColorStateList.valueOf((int)n), null, (Drawable)L2.g(-1, f4, false));
    }

    public final int d() {
        int n = this.a ? -15198168 : (this.c ? -657672 : -1);
        return n;
    }

    /*
     * Unable to fully structure code
     */
    public final Drawable h(int var1_1, float var2_2, boolean var3_3, boolean var4_4) {
        block13: {
            block16: {
                block17: {
                    block14: {
                        block15: {
                            var8_5 = var1_1 == 2;
                            var6_6 = var1_1 == 11 ? -619177944 : this.d();
                            var9_7 = this.a;
                            if (var1_1 != 3) break block14;
                            if (!this.c) break block15;
                            if (var9_7) {
                                var5_8 = -14408650;
lbl8:
                                // 4 sources

                                while (true) {
                                    var6_6 = var5_8;
                                    break block13;
                                    break;
                                }
                            }
                            var5_8 = -1;
                            ** GOTO lbl8
                        }
                        if (var9_7) lbl-1000:
                        // 2 sources

                        {
                            while (true) {
                                var6_6 = -14145480;
                                break block13;
                                break;
                            }
                        }
                        while (true) {
                            var6_6 = -1184275;
                            break block13;
                            break;
                        }
                    }
                    if (var1_1 != 2) break block16;
                    if (!var9_7) break block17;
                    var5_8 = -15198168;
                    ** GOTO lbl8
                }
                var5_8 = -526086;
                ** while (true)
            }
            if (var1_1 == 5 || var1_1 == 8) {
                if (!var9_7) ** continue;
                ** continue;
            }
        }
        var7_9 = -4926002;
        var5_8 = var6_6;
        if (var3_3) {
            var5_8 = var6_6;
            if (var1_1 == 5) {
                var5_8 = var4_4 ? (var9_7 ? -12427174 : -4926002) : -16730718;
            }
        }
        if (var1_1 == 7) {
            if (var4_4) {
                var5_8 = var7_9;
                if (var9_7) {
                    var5_8 = -12427174;
                }
            } else {
                var5_8 = -11214901;
            }
        }
        var10_10 = L2.g(var5_8, var2_2, var8_5);
        if (var3_3 && var1_1 == 8) {
            var5_8 = Math.max(1, Math.round(this.b));
            var1_1 = var4_4 != false ? -8284786 : this.a();
            var10_10.setStroke(var5_8, var1_1);
        }
        return var10_10;
    }
}

