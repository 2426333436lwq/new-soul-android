/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.window.OnBackInvokedCallback
 */
package dev.local.newsoul;

import android.window.OnBackInvokedCallback;
import dev.local.newsoul.q2;

/*
 * Renamed from dev.local.newsoul.k2
 */
public final class k2_0
implements OnBackInvokedCallback {
    public final q2 a;

    public /* synthetic */ k2_0(q2 q22) {
        this.a = q22;
    }

    public final void onBackInvoked() {
        this.a.b();
    }
}

