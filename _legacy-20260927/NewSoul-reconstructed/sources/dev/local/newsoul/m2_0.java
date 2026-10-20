/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.widget.PopupWindow$OnDismissListener
 */
package dev.local.newsoul;

import android.view.View;
import android.widget.PopupWindow;
import dev.local.newsoul.p2_0;

/*
 * Renamed from dev.local.newsoul.m2
 */
public final class m2_0
implements PopupWindow.OnDismissListener {
    public final View a;
    public final View.OnAttachStateChangeListener b;

    public /* synthetic */ m2_0(View view, p2_0 p2_02) {
        this.a = view;
        this.b = p2_02;
    }

    public final void onDismiss() {
        this.a.removeOnAttachStateChangeListener(this.b);
    }
}

