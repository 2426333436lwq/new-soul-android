/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.TextView;
import dev.local.newsoul.d2_0;
import java.util.ArrayList;

/*
 * Renamed from dev.local.newsoul.c2
 */
public final class c2_0
implements View.OnClickListener {
    public final d2_0 a;
    public final String[] b;
    public final ArrayList c;
    public final int d;
    public final int[] e;
    public final TextView f;

    public /* synthetic */ c2_0(d2_0 d2_02, String[] stringArray, ArrayList arrayList, int n, int[] nArray, TextView textView) {
        this.a = d2_02;
        this.b = stringArray;
        this.c = arrayList;
        this.d = n;
        this.e = nArray;
        this.f = textView;
    }

    public final void onClick(View object) {
        String string;
        object = this.a;
        object.getClass();
        Object object2 = this.c;
        int n = this.d;
        this.b[0] = string = (String)((ArrayList)object2).remove(n);
        this.e[0] = n;
        object2 = new StringBuilder("\u5df2\u5220\u9664\u201c");
        ((StringBuilder)object2).append(string);
        ((StringBuilder)object2).append("\u201d \u00b7 \u64a4\u9500");
        object2 = ((StringBuilder)object2).toString();
        string = this.f;
        string.setText((CharSequence)object2);
        string.setVisibility(0);
        ((d2_0)object).run();
    }
}

