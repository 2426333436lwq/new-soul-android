package dev.local.newsoul;

import android.view.View;
import android.widget.TextView;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0068r0 {
    public static final WeakHashMap a = new WeakHashMap();
    public static final WeakHashMap b = new WeakHashMap();

    public static void a(TextView textView, boolean z) {
        View view;
        Object obj = new Object();
        WeakHashMap weakHashMap = b;
        weakHashMap.put(textView, obj);
        if (z) {
            textView.post(new RunnableC0024g(textView, obj, 2));
            return;
        }
        weakHashMap.remove(textView);
        ViewOnLongClickListenerC0065q0 viewOnLongClickListenerC0065q0 = (ViewOnLongClickListenerC0065q0) a.remove(textView);
        if (viewOnLongClickListenerC0065q0 == null || (view = (View) viewOnLongClickListenerC0065q0.b.get()) == null) {
            return;
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(viewOnLongClickListenerC0065q0.c);
    }
}
