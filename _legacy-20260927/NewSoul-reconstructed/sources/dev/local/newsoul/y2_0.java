/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Intent;
import dev.local.newsoul.Q0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/*
 * Renamed from dev.local.newsoul.y2
 */
public final class y2_0
implements XposedInterface.Hooker {
    public final int a;
    public final XposedModule b;

    public /* synthetic */ y2_0(XposedModule xposedModule, int n) {
        this.a = n;
        this.b = xposedModule;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            default: {
                Activity activity = (Activity)chain.getThisObject();
                Intent intent = (Intent)chain.getArg(0);
                Q0.n(this.b, activity, intent, "onNewIntent");
                return chain.proceed();
            }
            case 0: 
        }
        Activity activity = (Activity)chain.getThisObject();
        Q0.f(activity);
        Intent intent = activity.getIntent();
        Q0.n(this.b, activity, intent, "onCreate");
        return chain.proceed();
    }
}

