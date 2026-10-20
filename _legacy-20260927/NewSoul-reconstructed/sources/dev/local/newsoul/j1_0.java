/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 */
package dev.local.newsoul;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import dev.local.newsoul.i1_0;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.j1
 */
public abstract class j1_0 {
    public static final WeakHashMap a = new WeakHashMap();

    public static ImageView a(View view) {
        if (view instanceof ImageView) {
            return (ImageView)view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                view = j1_0.a(viewGroup.getChildAt(i));
                if (view == null) continue;
                return view;
            }
        }
        return null;
    }

    public static void b(View view, boolean bl) {
        if (view == null) {
            return;
        }
        WeakHashMap weakHashMap = a;
        i1_0 i1_02 = (i1_0)((Object)weakHashMap.get(view));
        if (!bl) {
            if (i1_02 != null) {
                view.getOverlay().remove((Drawable)i1_02);
                i1_02.d = false;
            }
            return;
        }
        if (i1_02 != null) {
            if (!i1_02.d) {
                view.getOverlay().add((Drawable)i1_02);
                i1_02.d = true;
            }
            return;
        }
        i1_02 = new i1_0(view.getResources().getDisplayMetrics().density);
        weakHashMap.put(view, i1_02);
        view.getOverlay().add((Drawable)i1_02);
        i1_02.setBounds(0, 0, view.getWidth(), view.getHeight());
        view.addOnLayoutChangeListener((View.OnLayoutChangeListener)new Object());
        view.invalidate();
    }
}

