package dev.local.newsoul;

import java.util.function.Predicate;

/* JADX INFO: renamed from: dev.local.newsoul.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0087w implements Predicate {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0087w(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                C0091x c0091x = (C0091x) obj;
                return c0091x.a.get() == null || c0091x.a.get() == this.b;
            default:
                return ((C0089w1) obj).a.equals((String) this.b);
        }
    }
}
