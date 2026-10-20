/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.MotionEvent
 *  android.widget.Switch
 */
package dev.local.newsoul;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Switch;
import java.util.ArrayList;

/*
 * Renamed from dev.local.newsoul.r2
 */
public final class r2_0
extends Switch {
    public final ArrayList a = new ArrayList();
    public boolean b;
    public boolean c;

    public r2_0(Context context) {
        super(context);
    }

    public final void a(Runnable runnable) {
        this.a.add(runnable);
        runnable.run();
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean bl = this.c;
        if (motionEvent.getActionMasked() == 1) {
            this.c = true;
        }
        try {
            boolean bl2 = super.onTouchEvent(motionEvent);
            return bl2;
        }
        finally {
            this.c = bl;
        }
    }

    public final void setChecked(boolean bl) {
        super.setChecked(bl);
        Object object = this.a;
        if (object != null) {
            object = ((ArrayList)object).iterator();
            while (object.hasNext()) {
                ((Runnable)object.next()).run();
            }
        }
    }

    public final void setEnabled(boolean bl) {
        super.setEnabled(bl);
        Object object = this.a;
        if (object != null) {
            object = ((ArrayList)object).iterator();
            while (object.hasNext()) {
                ((Runnable)object.next()).run();
            }
        }
    }

    public final void toggle() {
        boolean bl = this.c;
        this.c = true;
        try {
            super.toggle();
            return;
        }
        finally {
            this.c = bl;
        }
    }
}

