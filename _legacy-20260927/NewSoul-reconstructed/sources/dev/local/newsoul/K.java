/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.SharedPreferences$Editor
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import dev.local.newsoul.L;
import dev.local.newsoul.M;
import dev.local.newsoul.N;
import dev.local.newsoul.O;
import dev.local.newsoul.P;
import dev.local.newsoul.Q;
import dev.local.newsoul.S;
import dev.local.newsoul.q2;
import dev.local.newsoul.w2;
import java.util.Map;

public final class K
implements Runnable {
    public final int a;
    public final Activity b;
    public final Map c;
    public final P d;

    public /* synthetic */ K(Activity activity, P p, Map map) {
        this.a = 0;
        this.b = activity;
        this.d = p;
        this.c = map;
    }

    public /* synthetic */ K(Activity activity, Map map, P p) {
        this.a = 1;
        this.b = activity;
        this.c = map;
        this.d = p;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        Activity activity;
        boolean bl;
        block11: {
            bl = false;
            switch (this.a) {
                default: {
                    boolean bl2;
                    activity = this.b;
                    Map map = this.c;
                    SharedPreferences.Editor editor = w2.o((Context)activity).edit();
                    for (Map.Entry entry : map.entrySet()) {
                        Object v = entry.getValue();
                        if (v instanceof Boolean) {
                            editor.putBoolean((String)entry.getKey(), ((Boolean)v).booleanValue());
                            continue;
                        }
                        if (v instanceof Integer) {
                            editor.putInt((String)entry.getKey(), ((Integer)v).intValue());
                            continue;
                        }
                        editor.putString((String)entry.getKey(), (String)v);
                    }
                    bl = bl2 = editor.commit();
                    break block11;
                }
                case 0: {
                    Activity activity2 = this.b;
                    P p = this.d;
                    if (!S.d(activity2, p)) {
                        S.b(activity2, p, "\u5df2\u53d6\u6d88\u5bfc\u5165");
                        return;
                    }
                    Object object = (Q)p.e.get();
                    if (object != null) {
                        ((Q)object).a("\u914d\u7f6e\u5df2\u8bfb\u53d6\uff0c\u7b49\u5f85\u786e\u8ba4\u5bfc\u5165", true);
                    }
                    object = new q2(activity2, p.b);
                    LinearLayout linearLayout = ((q2)object).h;
                    Map map = this.c;
                    int n = map.size();
                    StringBuilder stringBuilder = new StringBuilder("\u5c06\u5bfc\u5165 ");
                    stringBuilder.append(n);
                    stringBuilder.append(" \u9879\u8bbe\u7f6e\u3002\u6587\u4ef6\u4e2d\u672a\u5305\u542b\u7684\u8bbe\u7f6e\u548c\u73b0\u6709\u5bc6\u94a5\u4fdd\u6301\u4e0d\u53d8\u3002");
                    String string = stringBuilder.toString();
                    Context context = ((q2)object).g;
                    n = ((q2)object).i;
                    linearLayout.addView((View)w2.n(context, string, 15, n));
                    if (Boolean.TRUE.equals(map.get("square_auto_like"))) {
                        linearLayout.addView((View)w2.n(context, "\u6b64\u914d\u7f6e\u4f1a\u5f00\u542f\u81ea\u52a8\u70b9\u8d5e\u3002", 14, n));
                    }
                    linearLayout.addView((View)w2.n(context, "\u5f53\u524d\u4e0d\u53ef\u7528\u7684\u914d\u7f6e\u9879\u4ec5\u4fdd\u5b58\uff0c\u4e0d\u542f\u7528\u5bf9\u5e94\u529f\u80fd\u3002", 13, ((q2)object).j));
                    ((q2)object).d.setNegativeButton((CharSequence)"\u53d6\u6d88", (DialogInterface.OnClickListener)new L(0));
                    ((q2)object).g("\u5bfc\u5165\u914d\u7f6e", "\u786e\u8ba4\u5bfc\u5165");
                    boolean[] blArray = new boolean[]{false};
                    ((q2)object).e.setOnDismissListener((DialogInterface.OnDismissListener)new M(blArray, activity2, p));
                    ((q2)object).e.getButton(-1).setOnClickListener((View.OnClickListener)new N(blArray, p, (q2)object, activity2, map));
                    return;
                }
            }
            catch (Exception exception) {}
        }
        S.b.post((Runnable)new O(this.d, bl, activity));
    }
}

