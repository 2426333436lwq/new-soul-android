package dev.local.newsoul;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/* JADX INFO: renamed from: dev.local.newsoul.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0048m implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Activity b;

    public /* synthetic */ RunnableC0048m(Activity activity, int i) {
        this.a = i;
        this.b = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                AbstractC0056o.a(this.b);
                break;
            case 1:
                AbstractC0075t.d.remove(this.b);
                break;
            case 2:
                AbstractC0075t.b(this.b, null, true);
                break;
            case 3:
                AbstractC0075t.b(this.b, null, false);
                break;
            case 4:
                Toast.makeText(this.b, "提示预览：New Soul 已生效", 0).show();
                Log.i("SoulHook102", "settings preview clicked");
                break;
            default:
                SoulHook.lambda$installSettingsHook$1(this.b);
                break;
        }
    }
}
