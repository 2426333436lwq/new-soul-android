/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.view.View
 */
package dev.local.newsoul;

import android.content.SharedPreferences;
import android.view.View;
import dev.local.newsoul.P1;
import dev.local.newsoul.g2_0;
import dev.local.newsoul.q2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public final class K1
implements Consumer {
    public final q2 a;
    public final SharedPreferences b;
    public final List c;

    public /* synthetic */ K1(q2 q22, SharedPreferences sharedPreferences, ArrayList arrayList) {
        this.a = q22;
        this.b = sharedPreferences;
        this.c = arrayList;
    }

    public final void accept(Object object) {
        if ((object = (View)object).isAttachedToWindow() && object.isShown()) {
            q2 q22 = this.a;
            if (!q22.a.isFinishing() && !q22.a.isDestroyed()) {
                Object object2 = this.c;
                Iterator iterator = object2.iterator();
                boolean bl = false;
                while (iterator.hasNext()) {
                    bl |= ((g2_0)iterator.next()).c.getAsBoolean();
                }
                object2 = new P1((View)object, q22, this.b, (ArrayList)object2);
                q22.h((View)object, new String[]{"\u5c4f\u853d\u672c\u7ec4", "\u53d6\u6d88\u5c4f\u853d\u672c\u7ec4"}, bl, (IntConsumer)object2);
            }
        }
    }
}

