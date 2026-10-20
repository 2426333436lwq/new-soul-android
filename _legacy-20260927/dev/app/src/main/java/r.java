package dev.local.newsoul;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements c3 {
    public final /* synthetic */ Object a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ r(Object obj, Object obj2, Object obj3) {
        this.a = obj;
        this.b = obj2;
        this.c = obj3;
    }

    @Override // dev.local.newsoul.c3
    public void run() throws IOException {
        b3 b3Var = (b3) this.a;
        h3 h3Var = (h3) this.b;
        String str = (String) this.c;
        C0093x1 c0093x1 = b3Var.f;
        String str2 = h3Var.a;
        synchronized (c0093x1) {
            h3 h3VarG = c0093x1.g(str2);
            c0093x1.k(new h3(str2, C0093x1.e(str), h3VarG.c, h3VarG.d, h3VarG.e));
        }
    }
}
