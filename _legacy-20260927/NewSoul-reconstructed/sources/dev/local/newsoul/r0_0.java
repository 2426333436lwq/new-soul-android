/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.TextView;
import dev.local.newsoul.g_0;
import dev.local.newsoul.q0_0;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.r0
 */
public abstract class r0_0 {
    public static final WeakHashMap a = new WeakHashMap();
    public static final WeakHashMap b = new WeakHashMap();

    public static void a(TextView textView, boolean bl) {
        Object object = new Object();
        Object object2 = b;
        ((WeakHashMap)object2).put(textView, object);
        if (!bl) {
            ((WeakHashMap)object2).remove(textView);
            object2 = (q0_0)a.remove(textView);
            if (object2 != null && (textView = (View)((q0_0)object2).b.get()) != null) {
                textView.setOnLongClickListener(null);
                textView.setLongClickable(((q0_0)object2).c);
            }
            return;
        }
        textView.post((Runnable)new g_0(textView, object, 2));
    }
}

