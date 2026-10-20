package dev.local.newsoul;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class G1 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ G1(TextView textView, TextView textView2) {
        this.a = 1;
        this.c = textView;
        this.d = textView2;
        this.b = "正常检测项";
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                C0067q2 c0067q2 = (C0067q2) this.c;
                C0067q2 c0067q3 = new C0067q2(c0067q2.a, c0067q2.b);
                int i = c0067q3.i;
                Context context = c0067q3.g;
                TextView textViewN = AbstractC0090w2.n(context, "", 15, i);
                textViewN.setText((CharSequence) this.d);
                textViewN.setLineSpacing(AbstractC0090w2.i(context, 4), 1.0f);
                textViewN.setGravity(8388611);
                textViewN.setBreakStrategy(0);
                textViewN.setHyphenationFrequency(0);
                c0067q3.h.addView(textViewN);
                c0067q3.g((String) this.b, "知道了");
                break;
            case 1:
                TextView textView = (TextView) this.c;
                boolean z = textView.getVisibility() != 0;
                textView.setVisibility(z ? 0 : 8);
                String str = (String) this.b;
                String str2 = z ? "收起" + str : str;
                TextView textView2 = (TextView) this.d;
                textView2.setText(str2);
                textView2.setContentDescription(str + (z ? "，收起" : "，展开"));
                break;
            default:
                P2 p2 = (P2) this.d;
                EditText editText = (EditText) this.b;
                b3 b3Var = (b3) this.c;
                if (!b3Var.n) {
                    try {
                        b3Var.j();
                        p2.a(C0093x1.e(editText.getText().toString()));
                    } catch (Throwable th) {
                        editText.setError(th instanceof IllegalArgumentException ? th.getMessage() : "保存失败，请重试");
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ G1(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.c = obj;
        this.d = obj2;
        this.b = obj3;
    }
}
