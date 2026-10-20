package dev.local.newsoul;

import android.view.View;
import android.widget.EditText;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: dev.local.newsoul.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC0092x0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ViewOnClickListenerC0092x0(Object obj, int i, int i2) {
        this.a = i2;
        this.c = obj;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                C0096y0 c0096y0 = (C0096y0) this.c;
                if (c0096y0.e()) {
                    int i = c0096y0.g;
                    String[] strArr = c0096y0.e;
                    long j = Integer.parseInt(strArr[i]);
                    int i2 = c0096y0.g;
                    strArr[i2] = String.valueOf(Math.max(i2 == 0 ? 0L : -2147483648L, Math.min(2147483647L, j + ((long) this.b))));
                    EditText editText = c0096y0.c;
                    editText.setText(strArr[c0096y0.g]);
                    editText.setSelection(editText.length());
                    break;
                }
                break;
            case 1:
                IntConsumer intConsumer = ((C1) this.c).h;
                if (intConsumer != null) {
                    intConsumer.accept(this.b);
                }
                break;
            default:
                ((C0086v2) this.c).b(this.b, true);
                break;
        }
    }
}
