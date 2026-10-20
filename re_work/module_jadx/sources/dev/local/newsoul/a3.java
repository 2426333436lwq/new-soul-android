package dev.local.newsoul;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a3 implements TextWatcher {
    public final /* synthetic */ C0067q2 a;
    public final /* synthetic */ LinearLayout b;
    public final /* synthetic */ List c;
    public final /* synthetic */ b3 d;

    public a3(b3 b3Var, C0067q2 c0067q2, LinearLayout linearLayout, ArrayList arrayList) {
        this.d = b3Var;
        this.a = c0067q2;
        this.b = linearLayout;
        this.c = arrayList;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        b3 b3Var = this.d;
        if (b3Var.m) {
            return;
        }
        C0067q2 c0067q2 = b3Var.h;
        C0067q2 c0067q3 = this.a;
        if (c0067q2 != c0067q3) {
            return;
        }
        b3Var.k = charSequence.toString();
        b3Var.j = 0;
        b3Var.h(c0067q3, this.b, (ArrayList) this.c);
        LinearLayout linearLayout = c0067q3.h;
        if (linearLayout.getParent() instanceof ScrollView) {
            ((ScrollView) linearLayout.getParent()).scrollTo(0, 0);
        }
    }
}
