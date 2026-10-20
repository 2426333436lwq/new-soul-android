package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: dev.local.newsoul.ViewOnLongClickListenerC0065q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnLongClickListenerC0065q0 implements View.OnLongClickListener {
    public final WeakReference a;
    public final WeakReference b;
    public final boolean c;

    public ViewOnLongClickListenerC0065q0(TextView textView, View view) {
        this.a = new WeakReference(textView);
        this.b = new WeakReference(view);
        this.c = view.isLongClickable();
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        Activity activity;
        Context baseContext;
        TextView textView = (TextView) this.a.get();
        if (textView != null && AbstractC0068r0.a.get(textView) == this && textView.isShown() && textView.isAttachedToWindow() && view.isEnabled()) {
            Context context = view.getContext();
            int i = 0;
            while (true) {
                activity = null;
                if (i < 12 && context != null) {
                    if (!(context instanceof Activity)) {
                        if (!(context instanceof ContextWrapper) || (baseContext = ((ContextWrapper) context).getBaseContext()) == context) {
                            break;
                        }
                        i++;
                        context = baseContext;
                    } else {
                        activity = (Activity) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                AbstractC0100z0.a(activity, AbstractC0056o.b(activity));
                return true;
            }
        }
        return false;
    }
}
