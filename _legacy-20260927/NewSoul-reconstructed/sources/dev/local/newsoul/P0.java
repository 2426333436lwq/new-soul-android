/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.util.ArrayList;
import java.util.List;

public final class P0 {
    public final String a;
    public final String b;
    public final List c;
    public final int d;
    public final int e;

    public P0(String object, String string, ArrayList arrayList, int n) {
        this.a = object;
        this.b = string;
        this.c = arrayList;
        this.d = n;
        object = arrayList.iterator();
        n = 0;
        while (object.hasNext()) {
            if (!((String)object.next()).contains("Hook \u672a\u5c31\u7eea")) continue;
            ++n;
        }
        this.e = n;
    }

    public final String a(boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : this.c) {
            boolean bl2 = string.contains("Hook \u70b9\u7f3a\u5931") || string.contains("Hook \u672a\u5c31\u7eea");
            if (bl2 != bl) continue;
            int n = string.indexOf(10);
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\n\n");
            }
            if (n >= 0) {
                string = string.substring(0, n);
            }
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public final String b() {
        CharSequence charSequence;
        Object object = this.c.iterator();
        int n = 0;
        while (object.hasNext()) {
            charSequence = (String)object.next();
            if (!((String)charSequence).contains("Hook \u70b9\u7f3a\u5931") && !((String)charSequence).contains("Hook \u672a\u5c31\u7eea")) continue;
            ++n;
        }
        if (n == 0) {
            charSequence = "\u5173\u952e\u68c0\u6d4b\u9879\u5df2\u6ce8\u518c\uff0c\u5f85\u529f\u80fd\u5b9e\u6d4b";
        } else {
            charSequence = new StringBuilder("\u90e8\u5206\u68c0\u6d4b\u9879\u9700\u8981\u68c0\u67e5\uff08");
            ((StringBuilder)charSequence).append(n);
            ((StringBuilder)charSequence).append(" \u9879\uff09");
            charSequence = ((StringBuilder)charSequence).toString();
        }
        String string = "";
        object = this.b;
        String string2 = this.a;
        object = object != null && !((String)object).equals(string2) ? "\n\u4e0a\u6b21\u68c0\u6d4b ".concat((String)object) : "";
        if (!string2.startsWith("6.37.0")) {
            string = "\n\u5f53\u524d\u7248\u672c\u4e0e\u9002\u914d\u57fa\u7ebf\u4e0d\u540c\uff0c\u8bf7\u4f18\u5148\u68c0\u67e5\u5f02\u5e38\u9879";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)charSequence);
        stringBuilder.append("\nSoul ");
        stringBuilder.append(string2);
        stringBuilder.append((String)object);
        stringBuilder.append(string);
        return stringBuilder.toString();
    }
}

