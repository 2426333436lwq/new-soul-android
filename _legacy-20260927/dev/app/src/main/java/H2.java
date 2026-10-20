package dev.local.newsoul;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class H2 implements P2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ H2(Object obj, Object obj2, int i) {
        this.b = obj;
        this.c = obj2;
        this.a = i;
    }

    @Override // dev.local.newsoul.P2
    public void a(final String str) throws IllegalAccessException, InvocationTargetException {
        final b3 b3Var = (b3) this.b;
        b3Var.j();
        final Context applicationContext = b3Var.c.getApplicationContext();
        final String str2 = (String) this.c;
        final int i = this.a;
        b3Var.a(new W2(b3Var, applicationContext, str2, str2, i), new U2(2, b3Var));
    }

    public void b(boolean z) {
        I2 i2 = (I2) this.b;
        HashSet hashSet = i2.t;
        J2 j2 = (J2) this.c;
        hashSet.remove(Long.valueOf(j2.d));
        if (!z) {
            i2.i.remove(Long.valueOf(j2.d));
        }
        if (i2.m) {
            if (this.a != i2.p) {
                return;
            }
            E2 e2 = i2.c;
            e2.removeCallbacks(i2.w);
            if (!z) {
                i2.d("点赞失败，已停止；本批成功 " + i2.o + " 条");
                return;
            }
            int i = i2.o + 1;
            i2.o = i;
            Log.i("SoulHook102", "loaded like response: success=" + i);
            e2.a(i2.j.size() - i2.n, true);
            e2.postDelayed(i2.v, 500L);
        }
    }
}
