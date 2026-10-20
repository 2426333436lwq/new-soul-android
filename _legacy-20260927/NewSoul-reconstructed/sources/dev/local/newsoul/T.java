/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package dev.local.newsoul;

import android.view.View;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.Y;
import java.io.File;
import java.io.IOException;

public final class T
implements Runnable {
    public final int a;
    public final Object b;

    public /* synthetic */ T(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        int n;
        switch (this.a) {
            default: {
                Q0.d((View)this.b);
                return;
            }
            case 0: 
        }
        Runnable runnable = (Runnable)this.b;
        V.l("\u8bb0\u5f55\u5df2\u6e05\u9664");
        Object object = V.c;
        synchronized (object) {
            object.clear();
            n = 0;
            V.m = 0;
        }
        try {
            File[] fileArray;
            if (V.k == null) {
                fileArray = new File(V.i.getNoBackupFilesDir(), "newsoul-debug");
                V.k = object = new Y((File)fileArray, Q0.M());
            }
            object = fileArray = ((File)V.k.a).listFiles();
            if (fileArray == null) {
                object = new File[]{};
            }
            int n2 = ((File[])object).length;
            while (n < n2) {
                if (object[n].delete()) {
                    ++n;
                    continue;
                }
                object = new IOException("Clear failed");
                throw object;
            }
            V.g = 0L;
        }
        catch (Throwable throwable) {
            V.h = "\u6e05\u9664\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
        }
        V.b.post(runnable);
    }
}

