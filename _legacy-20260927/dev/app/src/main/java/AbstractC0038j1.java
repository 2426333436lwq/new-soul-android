package dev.local.newsoul;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0038j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0038j1 {
    public static final WeakHashMap a = new WeakHashMap();

    public static ImageView a(View view) {
        if (view instanceof ImageView) {
            return (ImageView) view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ImageView imageViewA = a(viewGroup.getChildAt(i));
            if (imageViewA != null) {
                return imageViewA;
            }
        }
        return null;
    }

    public static void b(View view, boolean z) {
        if (view == null) {
            return;
        }
        WeakHashMap weakHashMap = a;
        C0034i1 c0034i1 = (C0034i1) weakHashMap.get(view);
        if (!z) {
            if (c0034i1 != null) {
                view.getOverlay().remove(c0034i1);
                c0034i1.d = false;
                return;
            }
            return;
        }
        if (c0034i1 != null) {
            if (c0034i1.d) {
                return;
            }
            view.getOverlay().add(c0034i1);
            c0034i1.d = true;
            return;
        }
        C0034i1 c0034i2 = new C0034i1(view.getResources().getDisplayMetrics().density);
        weakHashMap.put(view, c0034i2);
        view.getOverlay().add(c0034i2);
        c0034i2.setBounds(0, 0, view.getWidth(), view.getHeight());
        view.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0030h1());
        view.invalidate();
    }
}
