package dev.local.newsoul;

import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0076t0 implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0076t0(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
        this.e = obj4;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        switch (this.a) {
            case 0:
                C0096y0 c0096y0 = (C0096y0) this.b;
                if (!c0096y0.f.equals(c0096y0.b())) {
                    return true;
                }
                C0096y0 c0096y1 = (C0096y0) this.c;
                if (!c0096y1.f.equals(c0096y1.b())) {
                    return true;
                }
                C0096y0 c0096y2 = (C0096y0) this.d;
                if (!c0096y2.f.equals(c0096y2.b())) {
                    return true;
                }
                C0096y0 c0096y3 = (C0096y0) this.e;
                return c0096y3.f.equals(c0096y3.b()) ^ true;
            default:
                ArrayList arrayList = (ArrayList) this.c;
                Runnable runnable = (Runnable) this.e;
                EditText editText = (EditText) this.b;
                String strTrim = editText.getText().toString().trim();
                if (strTrim.isEmpty()) {
                    return true;
                }
                try {
                    String str = String.join("\n", arrayList) + "\n" + strTrim;
                    if (str == null) {
                        str = "";
                    }
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
                    Collections.unmodifiableList(new ArrayList(linkedHashSet));
                    HashSet hashSet = new HashSet();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        hashSet.add(((String) it.next()).toLowerCase(Locale.ROOT));
                    }
                    String[] strArrSplit = strTrim.split("\\R");
                    int length = strArrSplit.length;
                    int i = 0;
                    while (true) {
                        String[] strArr = (String[]) this.d;
                        if (i >= length) {
                            editText.setText("");
                            editText.setError(null);
                            runnable.run();
                            editText.postDelayed(new RunnableC0024g(strArr, (RunnableC0015d2) runnable, 11), 1800L);
                            return true;
                        }
                        String strTrim2 = strArrSplit[i].trim();
                        if (!strTrim2.isEmpty() && hashSet.add(strTrim2.toLowerCase(Locale.ROOT))) {
                            arrayList.add(strTrim2);
                            strArr[0] = strTrim2;
                        }
                        i++;
                    }
                } catch (IllegalArgumentException e) {
                    editText.setError(e.getMessage());
                    return false;
                }
                break;
        }
    }
}
