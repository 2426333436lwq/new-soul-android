package dev.local.newsoul;

import android.view.View;
import android.widget.TextView;
import java.util.function.Consumer;

/* JADX INFO: loaded from: Z1.class */
public final /* synthetic */ class Z1 implements View.OnLongClickListener {
    public final Consumer a;
    public final TextView b;

    public /* synthetic */ Z1(K1 C0042k1, TextView textView) {
        this.a = C0042k1;
        this.b = textView;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.a.accept(this.b);
        return true;
    }
}
