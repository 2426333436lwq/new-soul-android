/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.view.ViewTreeObserver;
import dev.local.newsoul.o_0;

/*
 * Renamed from dev.local.newsoul.l
 */
public final class l_0
implements ViewTreeObserver.OnGlobalLayoutListener {
    public final Activity a;

    public /* synthetic */ l_0(Activity activity) {
        this.a = activity;
    }

    public final void onGlobalLayout() {
        o_0.a(this.a);
    }
}

