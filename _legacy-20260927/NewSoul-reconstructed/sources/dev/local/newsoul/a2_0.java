/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.text.TextWatcher
 */
package dev.local.newsoul;

import android.text.Editable;
import android.text.TextWatcher;

/*
 * Renamed from dev.local.newsoul.a2
 */
public final class a2_0
implements TextWatcher {
    public final int a;
    public final Runnable b;

    public /* synthetic */ a2_0(Runnable runnable, int n) {
        this.a = n;
        this.b = runnable;
    }

    private final void a(Editable editable) {
    }

    private final void b(CharSequence charSequence, int n, int n2, int n3) {
    }

    private final void c(CharSequence charSequence, int n, int n2, int n3) {
    }

    private final void d(CharSequence charSequence, int n, int n2, int n3) {
    }

    public final void afterTextChanged(Editable editable) {
        switch (this.a) {
            default: {
                return;
            }
            case 0: 
        }
        this.b.run();
    }

    public final void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        n = this.a;
    }

    public final void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        switch (this.a) {
            default: {
                this.b.run();
            }
            case 0: 
        }
    }
}

