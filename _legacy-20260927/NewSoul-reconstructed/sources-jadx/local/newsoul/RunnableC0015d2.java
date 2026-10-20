package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: renamed from: dev.local.newsoul.d2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0015d2 implements Runnable {
    public final /* synthetic */ EditText a;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ TextView c;
    public final /* synthetic */ R0 d;
    public final /* synthetic */ C0067q2 e;
    public final /* synthetic */ Activity f;
    public final /* synthetic */ String[] g;
    public final /* synthetic */ String[] h;
    public final /* synthetic */ int[] i;
    public final /* synthetic */ TextView j;
    public final /* synthetic */ TextView k;

    public RunnableC0015d2(EditText editText, ArrayList arrayList, TextView textView, R0 r0, C0067q2 c0067q2, Activity activity, String[] strArr, String[] strArr2, int[] iArr, TextView textView2, TextView textView3) {
        this.a = editText;
        this.b = arrayList;
        this.c = textView;
        this.d = r0;
        this.e = c0067q2;
        this.f = activity;
        this.g = strArr;
        this.h = strArr2;
        this.i = iArr;
        this.j = textView2;
        this.k = textView3;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public final void run() {
        C0067q2 c0067q2;
        Activity activity;
        String str;
        String strTrim = this.a.getText().toString().trim();
        String lowerCase = strTrim.matches("(?s).*\\R.*") ? "" : strTrim.toLowerCase(Locale.ROOT);
        ArrayList arrayList = this.b;
        Iterator it = arrayList.iterator();
        int i = 0;
        boolean zEqualsIgnoreCase = false;
        while (it.hasNext()) {
            zEqualsIgnoreCase |= ((String) it.next()).equalsIgnoreCase(strTrim);
        }
        String str2 = zEqualsIgnoreCase ? "已添加" : "添加";
        TextView textView = this.c;
        textView.setText(str2);
        textView.setEnabled((strTrim.isEmpty() || zEqualsIgnoreCase) ? false : true);
        textView.setSelected(textView.isEnabled());
        R0 r0 = this.d;
        r0.removeAllViews();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = arrayList.size();
            c0067q2 = this.e;
            activity = this.f;
            if (i3 >= size) {
                break;
            }
            String str3 = (String) arrayList.get(i3);
            if (str3.toLowerCase(Locale.ROOT).contains(lowerCase)) {
                int i4 = i2 + 1;
                LinearLayout linearLayout = new LinearLayout(c0067q2.g);
                linearLayout.setGravity(16);
                linearLayout.setMinimumHeight(AbstractC0090w2.i(activity, 48));
                int i5 = str3.codePointCount(i, str3.length()) <= 8 ? 1 : i;
                TextView textViewN = AbstractC0090w2.n(c0067q2.g, str3, 16, c0067q2.i);
                textViewN.setPadding(AbstractC0090w2.i(activity, 10), AbstractC0090w2.i(activity, 8), i, AbstractC0090w2.i(activity, 8));
                linearLayout.addView(textViewN, new LinearLayout.LayoutParams(i, -2, 1.0f));
                TextView textViewM = AbstractC0090w2.m(c0067q2, i5 != 0 ? "×" : "删除", "删除屏蔽词：".concat(str3));
                linearLayout.addView(textViewM, new LinearLayout.LayoutParams(AbstractC0090w2.i(activity, i5 != 0 ? 48 : 64), -1));
                textViewM.setMinHeight(AbstractC0090w2.i(activity, 48));
                if (i5 != 0) {
                    c0067q2.c.b(linearLayout, 5, 16.0f);
                    linearLayout.setSelected(str3.equals(this.g[0]));
                }
                int i6 = AbstractC0090w2.i(activity, 64) + ((int) Math.ceil(textViewN.getPaint().measureText(str3)));
                if (i5 == 0) {
                    i6 = -1;
                }
                r0.addView(linearLayout, new ViewGroup.LayoutParams(i6, -2));
                final int[] iArr = this.i;
                final TextView textView2 = this.j;
                final String[] strArr = this.h;
                final ArrayList arrayList2 = this.b;
                final int i7 = i3;
                textViewM.setOnClickListener(new View.OnClickListener() { // from class: dev.local.newsoul.c2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        RunnableC0015d2 runnableC0015d2 = this.a;
                        runnableC0015d2.getClass();
                        ArrayList arrayList3 = arrayList2;
                        int i8 = i7;
                        String str4 = (String) arrayList3.remove(i8);
                        strArr[0] = str4;
                        iArr[0] = i8;
                        TextView textView3 = textView2;
                        textView3.setText("已删除“" + str4 + "” · 撤销");
                        textView3.setVisibility(0);
                        runnableC0015d2.run();
                    }
                });
                i2 = i4;
            }
            i3++;
            i = 0;
        }
        if (lowerCase.isEmpty()) {
            str = arrayList.size() + "/100";
        } else {
            str = "匹配 " + i2 + " 个 · 共 " + arrayList.size() + " 个";
        }
        this.k.setText(str);
        if (i2 == 0) {
            TextView textViewN2 = AbstractC0090w2.n(c0067q2.g, lowerCase.isEmpty() ? "暂无屏蔽词" : "列表中没有这个词，可点击添加", 14, c0067q2.j);
            textViewN2.setPadding(0, AbstractC0090w2.i(activity, 16), 0, AbstractC0090w2.i(activity, 16));
            r0.addView(textViewN2, new ViewGroup.LayoutParams(-1, -2));
        }
    }
}
