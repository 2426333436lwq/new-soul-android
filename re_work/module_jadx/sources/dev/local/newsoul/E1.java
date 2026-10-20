package dev.local.newsoul;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class E1 implements Runnable {
    public final /* synthetic */ Activity a;
    public final /* synthetic */ View b;
    public final /* synthetic */ TextView c;

    public E1(Activity activity, View view, TextView textView) {
        this.a = activity;
        this.b = view;
        this.c = textView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WebView webViewA0;
        Activity activity = this.a;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        View view = this.b;
        if (view.isAttachedToWindow()) {
            if (view.isShown()) {
                TextView textView = this.c;
                if ("设置".contentEquals(textView.getText()) && (webViewA0 = Q0.a0(view)) != null) {
                    final boolean z = ((double) Color.luminance(textView.getCurrentTextColor())) > 0.45d;
                    webViewA0.evaluateJavascript("(()=>{if(location.origin!=='https://app.soulapp.cn'||location.pathname!='/setting/'||!document.body)return false;const want='" + (z ? "theme-night" : "theme-daytime") + "';const other='" + (z ? "theme-daytime" : "theme-night") + "';const b=document.body;const changed=!b.classList.contains(want)||b.classList.contains(other);if(changed){b.classList.remove(other);b.classList.add(want);}return changed;})()", new ValueCallback() { // from class: dev.local.newsoul.D1
                        @Override // android.webkit.ValueCallback
                        public final void onReceiveValue(Object obj) {
                            if ("true".equals((String) obj)) {
                                Log.i("SoulHook102", "settings web theme synchronized; dark=" + z);
                            }
                        }
                    });
                }
            }
            view.postDelayed(this, 750L);
        }
    }
}
