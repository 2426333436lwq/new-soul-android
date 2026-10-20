package dev.local.newsoul;

import android.view.View;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ T(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        switch (this.a) {
            case 0:
                Runnable runnable = (Runnable) this.b;
                V.l("记录已清除");
                ArrayList arrayList = V.c;
                synchronized (arrayList) {
                    arrayList.clear();
                    V.m = 0;
                    break;
                }
                try {
                    if (V.k == null) {
                        V.k = new Y(new File(V.i.getNoBackupFilesDir(), "newsoul-debug"), Q0.M());
                    }
                    File[] fileArrListFiles = ((File) V.k.a).listFiles();
                    if (fileArrListFiles == null) {
                        fileArrListFiles = new File[0];
                    }
                    for (File file : fileArrListFiles) {
                        if (!file.delete()) {
                            throw new IOException("Clear failed");
                        }
                    }
                    V.g = 0L;
                } catch (Throwable unused) {
                    V.h = "清除失败，请重试";
                }
                V.b.post(runnable);
                return;
            default:
                Q0.d((View) this.b);
                return;
        }
    }
}
