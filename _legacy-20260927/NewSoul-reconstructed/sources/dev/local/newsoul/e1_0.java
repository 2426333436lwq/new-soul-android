/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/*
 * Renamed from dev.local.newsoul.e1
 */
public final class e1_0 {
    public final String a;
    public final String b;
    public final int c;
    public final Object d;
    public final long e;
    public boolean f;

    public e1_0(String string, String string2, int n, Object object, long l) {
        if (!(string == null || string.isEmpty() || string2 == null || string2.isEmpty() || object == null || n != 1 && n != 2 && n != 3 && n != 5)) {
            this.a = string;
            this.b = string2;
            this.c = n;
            this.d = object;
            this.e = l;
            return;
        }
        throw new IllegalArgumentException("Invalid forward");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final ArrayList a(String abstractCollection, ArrayList iterator, long l) {
        synchronized (this) {
            Throwable throwable2;
            block6: {
                block5: {
                    try {
                        if (!this.b((String)((Object)abstractCollection), l)) return null;
                        if (((ArrayList)((Object)iterator)).isEmpty()) return null;
                        if (((ArrayList)((Object)iterator)).size() > 9) break block5;
                        abstractCollection = new LinkedHashSet();
                        iterator = ((ArrayList)((Object)iterator)).iterator();
                        while (iterator.hasNext()) {
                            String string = (String)iterator.next();
                            if (!this.c(string, 0)) return null;
                            boolean bl = abstractCollection.add(string);
                            if (bl) continue;
                        }
                    }
                    catch (Throwable throwable2) {}
                    return null;
                    this.f = true;
                    return new ArrayList(abstractCollection);
                    break block6;
                }
                return null;
            }
            throw throwable2;
        }
    }

    public final boolean b(String string, long l) {
        long l2;
        boolean bl = !this.f && this.a.equals(string) && l >= (l2 = this.e) && l - l2 <= 600000L;
        return bl;
    }

    public final boolean c(String string, int n) {
        boolean bl = n == 0 && string != null && !string.isEmpty() && !this.a.equals(string) && !this.b.equals(string);
        return bl;
    }
}

