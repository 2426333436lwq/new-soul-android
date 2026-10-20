package dev.local.newsoul;

import android.util.Log;
import android.webkit.ValueCallback;

/* JADX INFO: loaded from: D1.class */
public final /* synthetic */ class D1 implements ValueCallback {
    public final boolean a;

    @Override // android.webkit.ValueCallback
    public final void onReceiveValue(Object obj) {
        if ("true".equals((String) obj)) {
            Log.i("SoulHook102", "settings web theme synchronized; dark=" + this.a);
        }
    }


    public D1(boolean p0) {
        super();
        this.a = p0;
    }
}
