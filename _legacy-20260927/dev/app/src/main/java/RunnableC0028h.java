package dev.local.newsoul;

import android.util.Log;
import android.view.View;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: dev.local.newsoul.RunnableC0028h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0028h implements Runnable {
    public final /* synthetic */ int a;

    public /* synthetic */ RunnableC0028h(int i) {
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                Map<Object, Object> map = AbstractC0036j.d;
                synchronized (map) {
                    try {
                        for (Map.Entry entry : map.entrySet()) {
                            AbstractC0036j.a((View) entry.getKey(), (C0032i) entry.getValue());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 1:
                try {
                    Iterator it = new ArrayList((List) Q0.z.invoke(Q0.y.invoke(null, null), null)).iterator();
                    while (it.hasNext()) {
                        try {
                            Q0.A.invoke(it.next(), null);
                        } catch (Throwable th2) {
                            Log.w("SoulHook102", "recall view refresh skipped", th2);
                        }
                        break;
                    }
                    return;
                } catch (Throwable th3) {
                    Log.w("SoulHook102", "recall view refresh unavailable", th3);
                    return;
                }
            case 2:
                try {
                    Y y = new Y(new File(V.i.getNoBackupFilesDir(), "newsoul-debug"), Q0.M());
                    V.k = y;
                    V.g = y.b();
                    long j = V.j.getLong("until", 0L);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    V.h = V.j.getString("status", "未开启");
                    if (j > jCurrentTimeMillis && j - jCurrentTimeMillis <= 1800000 && V.g < 4194304) {
                        V.f = j;
                        V.h = "正在记录";
                        V.k("process_resume");
                    } else if (j != 0) {
                        V.l("已自动停止");
                    }
                    return;
                } catch (Throwable unused) {
                    V.l("记录不可用，请清除后重试");
                    return;
                }
            case 3:
                try {
                    if (V.f != 0 && !V.a()) {
                        V.l("已到时间上限，记录已停止");
                    }
                    V.f();
                    return;
                } catch (Throwable unused2) {
                    V.l("记录失败，已停止");
                    return;
                }
            default:
                B2.o = false;
                for (Map.Entry entry2 : new ArrayList<>(B2.k.entrySet())) {
                    if (entry2.getKey() != null) {
                        B2.b((View) entry2.getKey(), (A2) entry2.getValue());
                    }
                }
                return;
        }
    }
}
