/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnLongClickListener
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import dev.local.newsoul.b3;
import dev.local.newsoul.d3;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public final class O2
implements View.OnLongClickListener {
    public final d3 a;
    public final WeakReference b;
    public final Activity c;

    public /* synthetic */ O2(d3 d32, WeakReference weakReference, Activity activity) {
        this.a = d32;
        this.b = weakReference;
        this.c = activity;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean onLongClick(View object) {
        Object object2 = this.b;
        object = this.a;
        object.getClass();
        try {
            Object object3 = ((Reference)object2).get();
            if (object3 == null) {
                return false;
            }
            object2 = new b3((d3)object, object3);
            object3 = ((d3)object).f;
            if (object3 != null) {
                ((b3)object3).b();
            }
            ((d3)object).f = object2;
            ((b3)object2).f();
            return true;
        }
        catch (Throwable throwable) {
            d3.f((Context)this.c, "\u6682\u65f6\u65e0\u6cd5\u6253\u5f00\u8bed\u97f3\u5e93", throwable);
        }
        return true;
    }
}

