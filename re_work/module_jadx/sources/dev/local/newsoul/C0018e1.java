package dev.local.newsoul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: dev.local.newsoul.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0018e1 {
    public final String a;
    public final String b;
    public final int c;
    public final Object d;
    public final long e;
    public boolean f;

    public C0018e1(String str, String str2, int i, Object obj, long j) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || obj == null || !(i == 1 || i == 2 || i == 3 || i == 5)) {
            throw new IllegalArgumentException("Invalid forward");
        }
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = obj;
        this.e = j;
    }

    public final synchronized ArrayList a(String str, ArrayList arrayList, long j) {
        if (b(str, j) && !arrayList.isEmpty() && arrayList.size() <= 9) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!c(str2, 0) || !linkedHashSet.add(str2)) {
                    return null;
                }
            }
            this.f = true;
            return new ArrayList(linkedHashSet);
        }
        return null;
    }

    public final boolean b(String str, long j) {
        if (!this.f && this.a.equals(str)) {
            long j2 = this.e;
            if (j >= j2 && j - j2 <= 600000) {
                return true;
            }
        }
        return false;
    }

    public final boolean c(String str, int i) {
        return (i != 0 || str == null || str.isEmpty() || this.a.equals(str) || this.b.equals(str)) ? false : true;
    }
}
