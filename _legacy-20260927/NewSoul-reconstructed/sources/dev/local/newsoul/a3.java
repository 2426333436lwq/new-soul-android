/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.widget.LinearLayout
 *  android.widget.ScrollView
 */
package dev.local.newsoul;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import dev.local.newsoul.b3;
import dev.local.newsoul.q2;
import java.util.ArrayList;
import java.util.List;

public final class a3
implements TextWatcher {
    public final q2 a;
    public final LinearLayout b;
    public final List c;
    public final b3 d;

    public a3(b3 b32, q2 q22, LinearLayout linearLayout, ArrayList arrayList) {
        this.d = b32;
        this.a = q22;
        this.b = linearLayout;
        this.c = arrayList;
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }

    public final void onTextChanged(CharSequence object, int n, int n2, int n3) {
        q2 q22;
        q2 q23;
        b3 b32 = this.d;
        if (!b32.m && (q23 = b32.h) == (q22 = this.a)) {
            b32.k = object.toString();
            b32.j = 0;
            object = (ArrayList)this.c;
            b32.h(q22, this.b, (ArrayList)object);
            object = q22.h;
            if (object.getParent() instanceof ScrollView) {
                ((ScrollView)object.getParent()).scrollTo(0, 0);
            }
        }
    }
}

