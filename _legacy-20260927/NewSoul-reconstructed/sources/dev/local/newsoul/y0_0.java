/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.graphics.drawable.StateListDrawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.C1;
import dev.local.newsoul.L2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.v0_0;
import dev.local.newsoul.w0_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x0_0;

/*
 * Renamed from dev.local.newsoul.y0
 */
public final class y0_0 {
    public static final String[] h = new String[]{"absolute", "delta"};
    public final String a;
    public final r2_0 b;
    public final EditText c;
    public final String d;
    public final String[] e = new String[2];
    public final String f;
    public int g;

    public y0_0(q2 q22, SharedPreferences object, String string, String charSequence) {
        int n;
        Object object2;
        Object object3;
        StateListDrawable stateListDrawable;
        r2_0 r2_02;
        this.a = charSequence;
        this.d = string;
        Object object4 = w2.r(q22, string, null, null);
        this.b = r2_02 = w2.d((LinearLayout)object4, null, ((String)charSequence).concat("enabled"), "\u4f2a\u88c5".concat(string), "", q22.i);
        r2_02.setChecked(object.getBoolean(((String)charSequence).concat("enabled"), false));
        Context context = q22.g;
        r2_02 = new LinearLayout(context);
        r2_02.setOrientation(1);
        r2_02.setPadding(0, w2.i(context, 8), 0, 0);
        object4.addView((View)r2_02, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        object4 = new C1(q22, null, new String[]{"\u76f4\u63a5\u4fee\u6539", "\u539f\u6709\u589e\u51cf"}, 13);
        object4.setContentDescription((CharSequence)string.concat("\u4fee\u6539\u65b9\u5f0f"));
        r2_02.addView((View)object4, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        this.g = "delta".equals(object.getString(((String)charSequence).concat("mode"), "absolute")) ? 1 : 0;
        int n2 = 0;
        while (true) {
            stateListDrawable = h;
            if (n2 >= 2) break;
            object3 = this.e;
            stateListDrawable = stateListDrawable[n2];
            object2 = new StringBuilder();
            ((StringBuilder)object2).append((String)charSequence);
            ((StringBuilder)object2).append((String)stateListDrawable);
            object3[n2] = String.valueOf(object.getInt(((StringBuilder)object2).toString(), 0));
            ++n2;
        }
        ((C1)((Object)object4)).b(stateListDrawable[this.g], (String[])stateListDrawable, false);
        object = new EditText(context);
        this.c = object;
        object.setTextColor(q22.i);
        object.setHintTextColor(q22.j);
        object.setTextSize(16.0f);
        object.setMinHeight(w2.i(context, 48));
        object.setSingleLine(true);
        object.setPadding(w2.i(context, 14), w2.i(context, 10), w2.i(context, 14), w2.i(context, 10));
        object2 = q22.c;
        object2.getClass();
        float f = L2.e(8);
        stateListDrawable = new StateListDrawable();
        int[] nArray = new int[]{-16842910};
        int[] nArray2 = new int[]{16842908};
        object3 = new int[0];
        boolean bl = ((L2)object2).a;
        n2 = bl ? -12237486 : -6710877;
        int n3 = bl ? -11828613 : -13474465;
        int n4 = bl ? -10461074 : -9934732;
        for (n = 0; n < 3; ++n) {
            float f2 = ((L2)object2).b;
            charSequence = L2.g(0, f * f2, false);
            float f4 = n == 1 ? 1.5f : 1.0f;
            charSequence.setStroke(Math.max(1, Math.round(f4 * f2)), (new int[]{n2, n3, n4})[n]);
            stateListDrawable.addState((new int[][]{nArray, nArray2, (int[])object3})[n], (Drawable)charSequence);
        }
        charSequence = new LayerDrawable(new Drawable[]{((L2)object2).c(8, f), stateListDrawable});
        n = object.getPaddingLeft();
        n3 = object.getPaddingTop();
        n4 = object.getPaddingRight();
        n2 = object.getPaddingBottom();
        L2.i((View)object, (Drawable)charSequence);
        object.setBackground((Drawable)charSequence);
        object.setPadding(n, n3, n4, n2);
        object = new LinearLayout(context);
        object.setGravity(16);
        charSequence = new StringBuilder("\u51cf\u5c11");
        ((StringBuilder)charSequence).append(string);
        ((StringBuilder)charSequence).append("\u6570\u91cf");
        charSequence = this.c(q22, "\u2212", ((StringBuilder)charSequence).toString(), -1);
        object3 = new StringBuilder("\u589e\u52a0");
        ((StringBuilder)object3).append(string);
        ((StringBuilder)object3).append("\u6570\u91cf");
        string = this.c(q22, "+", ((StringBuilder)object3).toString(), 1);
        object.addView((View)charSequence, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i(context, 48), -2));
        charSequence = new LinearLayout.LayoutParams(0, -2, 1.0f);
        ((LinearLayout.LayoutParams)charSequence).rightMargin = n2 = w2.i(context, 8);
        ((LinearLayout.LayoutParams)charSequence).leftMargin = n2;
        object.addView((View)this.c, (ViewGroup.LayoutParams)charSequence);
        object.addView((View)string, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(w2.i(context, 48), -2));
        string = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)string).topMargin = w2.i(context, 12);
        r2_02.addView((View)object, (ViewGroup.LayoutParams)string);
        this.d();
        object = this.b;
        ((r2_0)((Object)object)).a(new g_0((Object)r2_02, object, 10));
        this.b.setOnCheckedChangeListener(new v0_0(this, q22));
        ((C1)((Object)object4)).h = new w0_0(this, (C1)((Object)object4));
        this.f = this.b();
    }

    public final void a(SharedPreferences.Editor editor) {
        String[] stringArray = new StringBuilder();
        String string = this.a;
        stringArray.append(string);
        stringArray.append("enabled");
        Object object = editor.putBoolean(stringArray.toString(), this.b.isChecked());
        stringArray = new StringBuilder();
        stringArray.append(string);
        stringArray.append("mode");
        CharSequence charSequence = stringArray.toString();
        stringArray = h;
        object.putString((String)charSequence, stringArray[this.g]);
        for (int i = 0; i < 2; ++i) {
            object = stringArray[i];
            charSequence = new StringBuilder();
            ((StringBuilder)charSequence).append(string);
            ((StringBuilder)charSequence).append((String)object);
            editor.putInt(((StringBuilder)charSequence).toString(), Integer.parseInt(this.e[i]));
        }
    }

    public final String b() {
        boolean bl = this.b.isChecked();
        int n = this.g;
        Object object = this.c;
        String[] stringArray = this.e;
        Object object2 = n == 0 ? object.getText() : stringArray[0];
        String string = String.valueOf(object2);
        object2 = this.g == 1 ? object.getText() : stringArray[1];
        object = String.valueOf(object2);
        object2 = new StringBuilder();
        ((StringBuilder)object2).append(bl);
        ((StringBuilder)object2).append(":");
        ((StringBuilder)object2).append(n);
        ((StringBuilder)object2).append(":");
        ((StringBuilder)object2).append(string);
        ((StringBuilder)object2).append(":");
        ((StringBuilder)object2).append((String)object);
        return ((StringBuilder)object2).toString();
    }

    public final TextView c(q2 object, String string, String string2, int n) {
        int n2 = ((q2)object).i;
        Context context = ((q2)object).g;
        string = w2.n(context, string, 22, n2);
        string.setGravity(17);
        string.setMinHeight(w2.i(context, 48));
        string.setContentDescription((CharSequence)string2);
        object = ((q2)object).c;
        object.getClass();
        ((L2)object).b((View)string, 10, L2.e(10));
        w2.g((View)string);
        string.setOnClickListener((View.OnClickListener)new x0_0(this, n, 0));
        return string;
    }

    public final void d() {
        String string = this.g == 0 ? "\u663e\u793a\u6570\u91cf" : "\u589e\u51cf\u6570\u91cf";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d);
        stringBuilder.append(string);
        string = stringBuilder.toString();
        stringBuilder = this.c;
        stringBuilder.setContentDescription(string);
        int n = this.g == 1 ? 4096 : 0;
        stringBuilder.setInputType(n | 2);
        stringBuilder.setGravity(17);
        stringBuilder.setText(this.e[this.g]);
        stringBuilder.setSelection(stringBuilder.length());
        stringBuilder.setError(null);
    }

    public final boolean e() {
        boolean bl;
        String string;
        EditText editText = this.c;
        try {
            String[] stringArray = this.e;
            int n = this.g;
            string = editText.getText().toString();
            bl = this.g == 1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            editText.setError((CharSequence)illegalArgumentException.getMessage());
            return false;
        }
        stringArray[n] = String.valueOf(Q0.P(string, bl));
        editText.setError(null);
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean f() {
        boolean bl;
        String string;
        if (this.b.isChecked()) {
            return this.e();
        }
        try {
            String[] stringArray = this.e;
            int n = this.g;
            string = this.c.getText().toString();
            bl = this.g == 1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return true;
        }
        stringArray[n] = String.valueOf(Q0.P(string, bl));
        return true;
    }
}

