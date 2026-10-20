package dev.local.newsoul;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: dev.local.newsoul.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0096y0 {
    public static final String[] h = {"absolute", "delta"};
    public final String a;
    public final C0070r2 b;
    public final EditText c;
    public final String d;
    public final String[] e = new String[2];
    public final String f;
    public int g;

    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v17 */
    public C0096y0(final C0067q2 c0067q2, SharedPreferences sharedPreferences, String str, String str2) {
        String[] strArr;
        ?? r6 = 0;
        this.a = str2;
        this.d = str;
        LinearLayout linearLayoutR = AbstractC0090w2.r(c0067q2, str, null, null);
        C0070r2 c0070r2D = AbstractC0090w2.d(linearLayoutR, null, str2.concat("enabled"), "伪装".concat(str), "", c0067q2.i);
        this.b = c0070r2D;
        c0070r2D.setChecked(sharedPreferences.getBoolean(str2.concat("enabled"), false));
        Context context = c0067q2.g;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(0, AbstractC0090w2.i(context, 8), 0, 0);
        linearLayoutR.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        final C1 c1 = new C1(c0067q2, null, new String[]{"直接修改", "原有增减"}, 13);
        c1.setContentDescription(str.concat("修改方式"));
        linearLayout.addView(c1, new LinearLayout.LayoutParams(-1, -2));
        this.g = "delta".equals(sharedPreferences.getString(str2.concat("mode"), "absolute")) ? 1 : 0;
        int i = 0;
        while (true) {
            strArr = h;
            if (i >= 2) {
                break;
            }
            this.e[i] = String.valueOf(sharedPreferences.getInt(str2 + strArr[i], 0));
            i++;
        }
        c1.b(strArr[this.g], strArr, false);
        EditText editText = new EditText(context);
        this.c = editText;
        editText.setTextColor(c0067q2.i);
        editText.setHintTextColor(c0067q2.j);
        editText.setTextSize(16.0f);
        editText.setMinHeight(AbstractC0090w2.i(context, 48));
        editText.setSingleLine(true);
        editText.setPadding(AbstractC0090w2.i(context, 14), AbstractC0090w2.i(context, 10), AbstractC0090w2.i(context, 14), AbstractC0090w2.i(context, 10));
        L2 l2 = c0067q2.c;
        l2.getClass();
        float fE = L2.e(8);
        StateListDrawable stateListDrawable = new StateListDrawable();
        int[][] iArr = {new int[]{-16842910}, new int[]{R.attr.state_focused}, new int[0]};
        boolean z = l2.a;
        int[] iArr2 = {z ? -12237486 : -6710877, z ? -11828613 : -13474465, z ? -10461074 : -9934732};
        int i2 = 0;
        while (i2 < 3) {
            float f = l2.b;
            GradientDrawable gradientDrawableG = L2.g(r6, fE * f, r6);
            gradientDrawableG.setStroke(Math.max(1, Math.round((i2 == 1 ? 1.5f : 1.0f) * f)), iArr2[i2]);
            stateListDrawable.addState(iArr[i2], gradientDrawableG);
            i2++;
            r6 = 0;
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{l2.c(8, fE), stateListDrawable});
        int paddingLeft = editText.getPaddingLeft();
        int paddingTop = editText.getPaddingTop();
        int paddingRight = editText.getPaddingRight();
        int paddingBottom = editText.getPaddingBottom();
        L2.i(editText, layerDrawable);
        editText.setBackground(layerDrawable);
        editText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(16);
        View viewC = c(c0067q2, "−", "减少" + str + "数量", -1);
        View viewC2 = c(c0067q2, "+", "增加" + str + "数量", 1);
        linearLayout2.addView(viewC, new LinearLayout.LayoutParams(AbstractC0090w2.i(context, 48), -2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        int i3 = AbstractC0090w2.i(context, 8);
        layoutParams.rightMargin = i3;
        layoutParams.leftMargin = i3;
        linearLayout2.addView(this.c, layoutParams);
        linearLayout2.addView(viewC2, new LinearLayout.LayoutParams(AbstractC0090w2.i(context, 48), -2));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = AbstractC0090w2.i(context, 12);
        linearLayout.addView(linearLayout2, layoutParams2);
        d();
        C0070r2 c0070r2 = this.b;
        c0070r2.a(new RunnableC0024g(linearLayout, c0070r2, 10));
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: dev.local.newsoul.v0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                EditText editText2 = this.a.c;
                if (!z2) {
                    editText2.setError(null);
                }
                if (z2 || !editText2.hasFocus()) {
                    return;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) c0067q2.g.getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                }
                editText2.clearFocus();
            }
        });
        c1.h = new IntConsumer() { // from class: dev.local.newsoul.w0
            @Override // java.util.function.IntConsumer
            public final void accept(int i4) {
                C0096y0 c0096y0 = this.a;
                if (i4 != c0096y0.g && c0096y0.e()) {
                    c0096y0.g = i4;
                    String[] strArr2 = C0096y0.h;
                    c1.b(strArr2[i4], strArr2, true);
                    c0096y0.d();
                }
            }
        };
        this.f = b();
    }

    public final void a(SharedPreferences.Editor editor) {
        StringBuilder sb = new StringBuilder();
        String str = this.a;
        sb.append(str);
        sb.append("enabled");
        String[] strArr = h;
        editor.putBoolean(sb.toString(), this.b.isChecked()).putString(str + "mode", strArr[this.g]);
        for (int i = 0; i < 2; i++) {
            editor.putInt(str + strArr[i], Integer.parseInt(this.e[i]));
        }
    }

    public final String b() {
        boolean zIsChecked = this.b.isChecked();
        int i = this.g;
        EditText editText = this.c;
        Object[] objArr = this.e;
        return zIsChecked + ":" + i + ":" + String.valueOf(i == 0 ? editText.getText() : objArr[0]) + ":" + String.valueOf(this.g == 1 ? editText.getText() : objArr[1]);
    }

    public final TextView c(C0067q2 c0067q2, String str, String str2, int i) {
        int i2 = c0067q2.i;
        Context context = c0067q2.g;
        TextView textViewN = AbstractC0090w2.n(context, str, 22, i2);
        textViewN.setGravity(17);
        textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
        textViewN.setContentDescription(str2);
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textViewN, 10, L2.e(10));
        AbstractC0090w2.g(textViewN);
        textViewN.setOnClickListener(new ViewOnClickListenerC0092x0(this, i, 0));
        return textViewN;
    }

    public final void d() {
        String str = this.d + (this.g == 0 ? "显示数量" : "增减数量");
        EditText editText = this.c;
        editText.setContentDescription(str);
        editText.setInputType((this.g == 1 ? 4096 : 0) | 2);
        editText.setGravity(17);
        editText.setText(this.e[this.g]);
        editText.setSelection(editText.length());
        editText.setError(null);
    }

    public final boolean e() {
        EditText editText = this.c;
        try {
            this.e[this.g] = String.valueOf(Q0.P(editText.getText().toString(), this.g == 1));
            editText.setError(null);
            return true;
        } catch (IllegalArgumentException e) {
            editText.setError(e.getMessage());
            return false;
        }
    }

    public final boolean f() {
        if (this.b.isChecked()) {
            return e();
        }
        try {
            this.e[this.g] = String.valueOf(Q0.P(this.c.getText().toString(), this.g == 1));
        } catch (IllegalArgumentException unused) {
        }
        return true;
    }
}
