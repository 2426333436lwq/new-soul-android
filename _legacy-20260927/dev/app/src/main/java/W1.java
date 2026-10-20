package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class W1 implements View.OnClickListener {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ Runnable c;
    public final /* synthetic */ EditText d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ W1(ArrayList arrayList, SharedPreferences sharedPreferences, RunnableC0024g runnableC0024g, Activity activity, C0067q2 c0067q2, EditText editText) {
        this.b = arrayList;
        this.e = sharedPreferences;
        this.c = runnableC0024g;
        this.f = activity;
        this.g = c0067q2;
        this.d = editText;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                String[] strArr = (String[]) this.e;
                if (strArr[0] == null) {
                    return;
                }
                ArrayList arrayList = this.b;
                Iterator it = arrayList.iterator();
                boolean zEqualsIgnoreCase = false;
                while (it.hasNext()) {
                    zEqualsIgnoreCase |= ((String) it.next()).equalsIgnoreCase(strArr[0]);
                }
                if (!zEqualsIgnoreCase) {
                    try {
                        String str = String.join("\n", arrayList) + "\n" + strArr[0];
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
                        arrayList.add(Math.min(((int[]) this.f)[0], arrayList.size()), strArr[0]);
                    } catch (IllegalArgumentException e) {
                        this.d.setError(e.getMessage());
                        return;
                    }
                }
                strArr[0] = null;
                ((TextView) this.g).setVisibility(8);
                this.c.run();
                return;
            default:
                SharedPreferences sharedPreferences = (SharedPreferences) this.e;
                Runnable runnable = this.c;
                Activity activity = (Activity) this.f;
                C0067q2 c0067q2 = (C0067q2) this.g;
                String strJoin = String.join("\n", this.b);
                String str3 = strJoin == null ? "" : strJoin;
                try {
                    if (str3.length() > 12000) {
                        throw new IllegalArgumentException("屏蔽词内容过长");
                    }
                    LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                    for (String str4 : str3.split("\\R")) {
                        String lowerCase2 = str4.trim().toLowerCase(Locale.ROOT);
                        if (!lowerCase2.isEmpty()) {
                            if (lowerCase2.length() > 100) {
                                throw new IllegalArgumentException("每个屏蔽词最多100个字符");
                            }
                            linkedHashSet2.add(lowerCase2);
                        }
                    }
                    if (linkedHashSet2.size() > 100) {
                        throw new IllegalArgumentException("最多保存100个屏蔽词");
                    }
                    List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(linkedHashSet2));
                    sharedPreferences.edit().putString("feed_keywords_v1", strJoin).apply();
                    B2.d();
                    runnable.run();
                    Toast.makeText(activity, "已保存 " + listUnmodifiableList.size() + " 个屏蔽词", 0).show();
                    c0067q2.e.dismiss();
                    return;
                } catch (IllegalArgumentException e2) {
                    this.d.setError(e2.getMessage());
                    return;
                }
        }
    }

    public /* synthetic */ W1(String[] strArr, ArrayList arrayList, EditText editText, int[] iArr, TextView textView, RunnableC0015d2 runnableC0015d2) {
        this.e = strArr;
        this.b = arrayList;
        this.d = editText;
        this.f = iArr;
        this.g = textView;
        this.c = runnableC0015d2;
    }
}
