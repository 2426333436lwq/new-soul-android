package dev.local.newsoul;

import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: renamed from: dev.local.newsoul.a2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0003a2 implements TextWatcher {
    public final /* synthetic */ int a;
    public final /* synthetic */ Runnable b;

    public /* synthetic */ C0003a2(Runnable runnable, int i) {
        this.a = i;
        this.b = runnable;
    }

    private final void a(Editable editable) {
    }

    private final void b(CharSequence charSequence, int i, int i2, int i3) {
    }

    private final void c(CharSequence charSequence, int i, int i2, int i3) {
    }

    private final void d(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        switch (this.a) {
            case 0:
                this.b.run();
                break;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = this.a;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        switch (this.a) {
            case 0:
                break;
            default:
                this.b.run();
                break;
        }
    }
}
