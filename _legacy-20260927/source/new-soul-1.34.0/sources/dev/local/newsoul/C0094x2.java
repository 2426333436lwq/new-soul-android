package dev.local.newsoul;

import io.github.libxposed.api.XposedInterface;

/* JADX INFO: renamed from: dev.local.newsoul.x2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0094x2 implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ SoulHook b;

    public /* synthetic */ C0094x2(SoulHook soulHook, int i) {
        this.a = i;
        this.b = soulHook;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            case 0:
                return this.b.lambda$onPackageReady$0(chain);
            default:
                return this.b.lambda$installSettingsHook$2(chain);
        }
    }
}
