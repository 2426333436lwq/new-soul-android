/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.i1_0;
import dev.local.newsoul.j1_0;

/*
 * Renamed from dev.local.newsoul.h1
 */
public final class h1_0
implements View.OnLayoutChangeListener {
    public final void onLayoutChange(View object, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        if ((object = (i1_0)((Object)j1_0.a.get(object))) != null) {
            object.setBounds(0, 0, n3 - n, n4 - n2);
        }
    }
}

