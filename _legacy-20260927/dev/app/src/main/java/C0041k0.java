package dev.local.newsoul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: renamed from: dev.local.newsoul.C0041k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0041k0 {
    public final Object a;

    public C0041k0(String str) {
        str = str == null ? "" : str;
        if (str.length() > 12000) {
            throw new IllegalArgumentException("屏蔽词内容过长");
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : str.split("\\R")) {
            String lowerCase = str2.trim().toLowerCase(Locale.ROOT);
            if (!lowerCase.isEmpty()) {
                if (lowerCase.length() > 100) {
                    throw new IllegalArgumentException("每个屏蔽词最多100个字符");
                }
                linkedHashSet.add(lowerCase);
            }
        }
        if (linkedHashSet.size() > 100) {
            throw new IllegalArgumentException("最多保存100个屏蔽词");
        }
        this.a = Collections.unmodifiableList(new ArrayList(linkedHashSet));
    }

    public boolean a(String str) {
        if (str != null && !str.isEmpty()) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Iterator it = ((List) this.a).iterator();
            while (it.hasNext()) {
                if (lowerCase.contains((String) it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public C0041k0(C0086v2 c0086v2) {
        this.a = c0086v2;
    }
}
