/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.TextView;
import dev.local.newsoul.K1;
import java.util.function.Consumer;

public final class Z1
implements View.OnLongClickListener {
    public final Consumer a;
    public final TextView b;

    public /* synthetic */ Z1(K1 k1, TextView textView) {
        this.a = k1;
        this.b = textView;
    }

    public final boolean onLongClick(View view) {
        this.a.accept(this.b);
        return true;
    }
}

