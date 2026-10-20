/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.webkit.ValueCallback
 */
package dev.local.newsoul;

import android.util.Log;
import android.webkit.ValueCallback;

public final class D1
implements ValueCallback {
    public final boolean a;

    public /* synthetic */ D1(boolean bl) {
        this.a = bl;
    }

    public final void onReceiveValue(Object object) {
        if ("true".equals((String)object)) {
            object = new StringBuilder("settings web theme synchronized; dark=");
            ((StringBuilder)object).append(this.a);
            Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
        }
    }
}

