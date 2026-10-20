/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.v2_0;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

/*
 * Renamed from dev.local.newsoul.k0
 */
public final class k0_0 {
    public final Object a;

    public k0_0(v2_0 v2_02) {
        this.a = v2_02;
    }

    public k0_0(String object) {
        String[] stringArray = object;
        if (object == null) {
            stringArray = "";
        }
        if (stringArray.length() <= 12000) {
            object = new LinkedHashSet();
            stringArray = stringArray.split("\\R");
            int n = stringArray.length;
            for (int i = 0; i < n; ++i) {
                String string = stringArray[i].trim().toLowerCase(Locale.ROOT);
                if (string.isEmpty()) continue;
                if (string.length() <= 100) {
                    ((AbstractCollection)object).add(string);
                    continue;
                }
                throw new IllegalArgumentException("\u6bcf\u4e2a\u5c4f\u853d\u8bcd\u6700\u591a100\u4e2a\u5b57\u7b26");
            }
            if (((AbstractCollection)object).size() <= 100) {
                this.a = Collections.unmodifiableList(new ArrayList(object));
                return;
            }
            throw new IllegalArgumentException("\u6700\u591a\u4fdd\u5b58100\u4e2a\u5c4f\u853d\u8bcd");
        }
        throw new IllegalArgumentException("\u5c4f\u853d\u8bcd\u5185\u5bb9\u8fc7\u957f");
    }

    public boolean a(String string) {
        if (string != null && !string.isEmpty()) {
            string = string.toLowerCase(Locale.ROOT);
            Iterator iterator = ((List)this.a).iterator();
            while (iterator.hasNext()) {
                if (!string.contains((String)iterator.next())) continue;
                return true;
            }
        }
        return false;
    }
}

