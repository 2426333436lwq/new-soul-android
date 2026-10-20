package dev.local.newsoul;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Switch;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: dev.local.newsoul.r2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0070r2 extends Switch {
    public final ArrayList a;
    public boolean b;
    public boolean c;

    public C0070r2(Context context) {
        super(context);
        this.a = new ArrayList();
    }

    public final void a(Runnable runnable) {
        this.a.add(runnable);
        runnable.run();
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = this.c;
        if (motionEvent.getActionMasked() == 1) {
            this.c = true;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } finally {
            this.c = z;
        }
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public final void setChecked(boolean z) {
        super.setChecked(z);
        ArrayList arrayList = this.a;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        ArrayList arrayList = this.a;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        boolean z = this.c;
        this.c = true;
        try {
            super.toggle();
        } finally {
            this.c = z;
        }
    }
}
