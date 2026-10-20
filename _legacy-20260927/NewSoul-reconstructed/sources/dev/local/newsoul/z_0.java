/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.A;

/*
 * Renamed from dev.local.newsoul.z
 */
public final class z_0 {
    public final long a = A.a.incrementAndGet();
    public int b;
    public int c;
    public int d;

    public final String a() {
        int n = this.b;
        int n2 = this.c;
        int n3 = this.d;
        StringBuilder stringBuilder = new StringBuilder("menu_hits=");
        stringBuilder.append(n);
        stringBuilder.append("; show_hits=");
        stringBuilder.append(n2);
        stringBuilder.append("; render_hits=");
        stringBuilder.append(n3);
        return stringBuilder.toString();
    }
}

