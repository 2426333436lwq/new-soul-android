/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContextWrapper
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.TextView;
import dev.local.newsoul.o_0;
import dev.local.newsoul.r0_0;
import dev.local.newsoul.z0_0;
import java.lang.ref.WeakReference;

/*
 * Renamed from dev.local.newsoul.q0
 */
public final class q0_0
implements View.OnLongClickListener {
    public final WeakReference a;
    public final WeakReference b;
    public final boolean c;

    public q0_0(TextView textView, View view) {
        this.a = new WeakReference<TextView>(textView);
        this.b = new WeakReference<View>(view);
        this.c = view.isLongClickable();
    }

    public final boolean onLongClick(View view) {
        TextView textView = (TextView)this.a.get();
        if (textView != null && r0_0.a.get(textView) == this && textView.isShown() && textView.isAttachedToWindow() && view.isEnabled()) {
            view = view.getContext();
            int n = 0;
            while (true) {
                Object var4_4 = null;
                textView = var4_4;
                if (n >= 12) break;
                textView = var4_4;
                if (view == null) break;
                if (view instanceof Activity) {
                    textView = (Activity)view;
                    break;
                }
                if (!(view instanceof ContextWrapper)) {
                    textView = var4_4;
                    break;
                }
                textView = ((ContextWrapper)view).getBaseContext();
                if (textView == view) {
                    textView = var4_4;
                    break;
                }
                ++n;
                view = textView;
            }
            if (textView != null && !textView.isFinishing() && !textView.isDestroyed()) {
                z0_0.a((Activity)textView, o_0.b((Activity)textView));
                return true;
            }
        }
        return false;
    }
}

