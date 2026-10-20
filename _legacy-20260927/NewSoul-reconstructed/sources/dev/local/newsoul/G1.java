/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.EditText
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import dev.local.newsoul.P2;
import dev.local.newsoul.b3;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;

public final class G1
implements View.OnClickListener {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public /* synthetic */ G1(TextView textView, TextView textView2) {
        this.a = 1;
        this.c = textView;
        this.d = textView2;
        this.b = "\u6b63\u5e38\u68c0\u6d4b\u9879";
    }

    public /* synthetic */ G1(Object object, Object object2, Object object3, int n) {
        this.a = n;
        this.c = object;
        this.d = object2;
        this.b = object3;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                P2 p2 = (P2)this.d;
                EditText editText = (EditText)this.b;
                object = (b3)this.c;
                if (!((b3)object).n) {
                    try {
                        ((b3)object).j();
                        p2.a(x1_0.e(editText.getText().toString()));
                    }
                    catch (Throwable throwable) {
                        String string = throwable instanceof IllegalArgumentException ? throwable.getMessage() : "\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
                        editText.setError((CharSequence)string);
                    }
                }
                return;
            }
            case 1: {
                object = (TextView)this.c;
                int n = object.getVisibility();
                int n2 = 0;
                n = n != 0 ? 1 : 0;
                if (n == 0) {
                    n2 = 8;
                }
                object.setVisibility(n2);
                String string = (String)this.b;
                if (n != 0) {
                    object = new StringBuilder("\u6536\u8d77");
                    ((StringBuilder)object).append(string);
                    object = ((StringBuilder)object).toString();
                } else {
                    object = string;
                }
                TextView textView = (TextView)this.d;
                textView.setText((CharSequence)object);
                object = n != 0 ? "\uff0c\u6536\u8d77" : "\uff0c\u5c55\u5f00";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(string);
                stringBuilder.append((String)object);
                textView.setContentDescription((CharSequence)stringBuilder.toString());
                return;
            }
            case 0: 
        }
        object = (q2)this.c;
        q2 q22 = new q2(((q2)object).a, ((q2)object).b);
        int n = q22.i;
        Context context = q22.g;
        object = w2.n(context, "", 15, n);
        object.setText((CharSequence)this.d);
        object.setLineSpacing((float)w2.i(context, 4), 1.0f);
        object.setGravity(0x800003);
        object.setBreakStrategy(0);
        object.setHyphenationFrequency(0);
        q22.h.addView((View)object);
        q22.g((String)this.b, "\u77e5\u9053\u4e86");
    }
}

