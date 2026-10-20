package dev.local.newsoul;

import android.content.SharedPreferences;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class P1 implements IntConsumer {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ SharedPreferences b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ P1(SharedPreferences sharedPreferences, String[] strArr, C1 c1, RunnableC0050m1 runnableC0050m1) {
        this.b = sharedPreferences;
        this.c = strArr;
        this.d = c1;
        this.e = runnableC0050m1;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        switch (this.a) {
            case 0:
                SharedPreferences.Editor editorEdit = this.b.edit();
                String[] strArr = (String[]) this.c;
                editorEdit.putString("feed_filter_gender", strArr[i]).apply();
                ((C1) this.d).b(strArr[i], strArr, true);
                ((Runnable) this.e).run();
                B2.d();
                break;
            default:
                View view = (View) this.c;
                if (view.isAttachedToWindow() && view.isShown()) {
                    C0067q2 c0067q2 = (C0067q2) this.d;
                    if (!c0067q2.a.isFinishing() && !c0067q2.a.isDestroyed()) {
                        int i2 = 0;
                        boolean z = i == 0;
                        SharedPreferences.Editor editorEdit2 = this.b.edit();
                        for (C0027g2 c0027g2 : (List) this.e) {
                            if (c0027g2.c.getAsBoolean()) {
                                editorEdit2.putBoolean(c0027g2.a, z);
                                i2++;
                            }
                        }
                        if (i2 > 0) {
                            editorEdit2.apply();
                            AbstractC0036j.i.post(new RunnableC0028h(0));
                        }
                        c0067q2.e();
                        break;
                    }
                }
                break;
        }
    }

    public /* synthetic */ P1(View view, C0067q2 c0067q2, SharedPreferences sharedPreferences, ArrayList arrayList) {
        this.c = view;
        this.d = c0067q2;
        this.b = sharedPreferences;
        this.e = arrayList;
    }
}
