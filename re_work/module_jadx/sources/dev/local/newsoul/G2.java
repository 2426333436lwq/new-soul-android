package dev.local.newsoul;

import android.os.SystemClock;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class G2 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return SystemClock.elapsedRealtime() - ((Long) ((Map.Entry) obj).getValue()).longValue() > 30000;
    }
}
