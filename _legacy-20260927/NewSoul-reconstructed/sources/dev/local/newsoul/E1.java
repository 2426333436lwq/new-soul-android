/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.graphics.Color
 *  android.view.View
 *  android.webkit.ValueCallback
 *  android.webkit.WebView
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.TextView;
import dev.local.newsoul.D1;
import dev.local.newsoul.Q0;

public final class E1
implements Runnable {
    public final Activity a;
    public final View b;
    public final TextView c;

    public E1(Activity activity, View view, TextView textView) {
        this.a = activity;
        this.b = view;
        this.c = textView;
    }

    @Override
    public final void run() {
        View view;
        Object object = this.a;
        if (!object.isFinishing() && !object.isDestroyed() && (view = this.b).isAttachedToWindow()) {
            WebView webView;
            if (view.isShown() && "\u8bbe\u7f6e".contentEquals((object = this.c).getText()) && (webView = Q0.a0(view)) != null) {
                boolean bl = (double)Color.luminance((int)object.getCurrentTextColor()) > 0.45;
                String string = "theme-daytime";
                object = bl ? "theme-night" : "theme-daytime";
                if (!bl) {
                    string = "theme-night";
                }
                StringBuilder stringBuilder = new StringBuilder("(()=>{if(location.origin!=='https://app.soulapp.cn'||location.pathname!='/setting/'||!document.body)return false;const want='");
                stringBuilder.append((String)object);
                stringBuilder.append("';const other='");
                stringBuilder.append(string);
                stringBuilder.append("';const b=document.body;const changed=!b.classList.contains(want)||b.classList.contains(other);if(changed){b.classList.remove(other);b.classList.add(want);}return changed;})()");
                webView.evaluateJavascript(stringBuilder.toString(), (ValueCallback)new D1(bl));
            }
            view.postDelayed((Runnable)this, 750L);
        }
    }
}

