package dev.local.newsoul;

import android.content.DialogInterface;
import io.github.libxposed.api.XposedModule;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class L implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;

    public /* synthetic */ L(int i) {
        this.a = i;
    }

    private final void a(DialogInterface dialogInterface, int i) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a) {
            case 0:
                XposedModule xposedModule = S.a;
                break;
        }
    }
}
