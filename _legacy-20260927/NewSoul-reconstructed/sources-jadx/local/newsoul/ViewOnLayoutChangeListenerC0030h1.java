package dev.local.newsoul;

import android.view.View;

/* JADX INFO: renamed from: dev.local.newsoul.h1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnLayoutChangeListenerC0030h1 implements View.OnLayoutChangeListener {
    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C0034i1 c0034i1 = (C0034i1) AbstractC0038j1.a.get(view);
        if (c0034i1 != null) {
            c0034i1.setBounds(0, 0, i3 - i, i4 - i2);
        }
    }
}
