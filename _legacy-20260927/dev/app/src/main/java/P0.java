package dev.local.newsoul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class P0 {
    public final String a;
    public final String b;
    public final List<String> c;
    public final int d;
    public final int e;

    public P0(String str, String str2, ArrayList arrayList, int i) {
        this.a = str;
        this.b = str2;
        this.c = arrayList;
        this.d = i;
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((String) it.next()).contains("Hook 未就绪")) {
                i2++;
            }
        }
        this.e = i2;
    }

    public final String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        for (String strSubstring : this.c) {
            if ((strSubstring.contains("Hook 点缺失") || strSubstring.contains("Hook 未就绪")) == z) {
                int iIndexOf = strSubstring.indexOf(10);
                if (sb.length() > 0) {
                    sb.append("\n\n");
                }
                if (iIndexOf >= 0) {
                    strSubstring = strSubstring.substring(0, iIndexOf);
                }
                sb.append(strSubstring);
            }
        }
        return sb.toString();
    }

    public final String b() {
        int i = 0;
        for (String str : this.c) {
            if (str.contains("Hook 点缺失") || str.contains("Hook 未就绪")) {
                i++;
            }
        }
        String str2 = i == 0 ? "关键检测项已注册，待功能实测" : "部分检测项需要检查（" + i + " 项）";
        String str3 = this.b;
        String str4 = this.a;
        return str2 + "\nSoul " + str4 + ((str3 == null || str3.equals(str4)) ? "" : "\n上次检测 ".concat(str3)) + (str4.startsWith("6.37.0") ? "" : "\n当前版本与适配基线不同，请优先检查异常项");
    }
}
