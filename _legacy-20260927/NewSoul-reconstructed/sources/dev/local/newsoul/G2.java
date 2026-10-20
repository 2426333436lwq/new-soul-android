/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.SystemClock
 */
package dev.local.newsoul;

import android.os.SystemClock;
import java.util.Map;
import java.util.function.Predicate;

public final class G2
implements Predicate {
    public final boolean test(Object object) {
        object = (Map.Entry)object;
        boolean bl = SystemClock.elapsedRealtime() - (Long)object.getValue() > 30000L;
        return bl;
    }
}

