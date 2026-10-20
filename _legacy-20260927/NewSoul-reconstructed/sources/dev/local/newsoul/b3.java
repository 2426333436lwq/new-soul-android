/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.graphics.Color
 *  android.graphics.Typeface
 *  android.media.MediaPlayer
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.SpannableString
 *  android.text.style.ForegroundColorSpan
 *  android.text.style.RelativeSizeSpan
 *  android.text.style.StyleSpan
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ScrollView
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import dev.local.newsoul.A0;
import dev.local.newsoul.C0;
import dev.local.newsoul.G1;
import dev.local.newsoul.L;
import dev.local.newsoul.L2;
import dev.local.newsoul.P2;
import dev.local.newsoul.V2;
import dev.local.newsoul.X2;
import dev.local.newsoul.c1_0;
import dev.local.newsoul.c3;
import dev.local.newsoul.d3;
import dev.local.newsoul.h3;
import dev.local.newsoul.m1_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.q2_0;
import dev.local.newsoul.u2_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x1_0;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public final class b3 {
    public final Object a;
    public final Object b;
    public final Activity c;
    public final String d;
    public final String e;
    public final x1_0 f;
    public final boolean g;
    public q2 h;
    public q2 i;
    public int j;
    public String k;
    public MediaPlayer l;
    public boolean m;
    public boolean n;
    public boolean o;
    public TextView p;
    public final d3 q;

    /*
     * Enabled aggressive block sorting
     */
    public b3(d3 d32, Object object) {
        Activity activity;
        this.q = d32;
        this.k = "";
        this.a = object;
        this.c = activity = (Activity)d3.e(object, "getActivity");
        Object object2 = d32.d;
        d32 = null;
        this.d = object2 = (String)((Method)object2).invoke(null, null);
        this.e = (String)d3.g(object, "L");
        this.b = d3.c(object);
        this.j();
        this.f = new x1_0(activity.getFilesDir(), (String)object2, 1);
        int n = activity.getResources().getIdentifier("tv_title", "id", activity.getPackageName());
        if (n != 0) {
            d32 = activity.findViewById(n);
        }
        boolean bl = d32 instanceof TextView;
        boolean bl2 = false;
        if (bl ? (double)Color.luminance((int)((TextView)d32).getCurrentTextColor()) > 0.45 : (activity.getResources().getConfiguration().uiMode & 0x30) == 32) {
            bl2 = true;
        }
        this.g = bl2;
    }

    public final void a(c3 c32, c3 c33) {
        if (this.n) {
            return;
        }
        this.n = true;
        this.o = true;
        q2 q22 = this.h;
        TextView textView = w2.n(q22.g, "\u64cd\u4f5c\u5df2\u63d0\u4ea4\uff0c\u5173\u95ed\u540e\u4ecd\u4f1a\u7ee7\u7eed\uff1b\u8bf7\u7a0d\u540e\u6253\u5f00\u8bed\u97f3\u5e93\u67e5\u770b\u7ed3\u679c\u3002", 13, q22.j);
        q22.h.addView((View)textView);
        if (q22.e.getButton(-2) != null) {
            q22.e.getButton(-2).setText((CharSequence)"\u5173\u95ed");
        }
        if (q22.e.getButton(-1) != null) {
            q22.e.getButton(-1).setEnabled(false);
            q22.e.getButton(-1).setText((CharSequence)"\u5904\u7406\u4e2d\u2026");
        }
        this.q.b.execute(new m1_0(this, c32, q22, textView, c33, 5));
    }

    public final void b() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.i();
        Object object = this.h;
        if (object != null && (object = ((q2)object).e) != null) {
            object.dismiss();
        }
        object = this.q;
        if (((d3)object).f == this) {
            ((d3)object).f = null;
        }
    }

    public final void c() {
        if (this.o) {
            d3.i((Context)this.c, "\u64cd\u4f5c\u4ecd\u5728\u540e\u53f0\u8fdb\u884c\uff0c\u8bf7\u7a0d\u540e\u6253\u5f00\u8bed\u97f3\u5e93\u67e5\u770b\u7ed3\u679c");
        }
        this.b();
    }

    public final void d(h3 h32) {
        this.j();
        q2 q22 = this.g();
        String string = h32.b;
        Context context = q22.g;
        Object object = w2.n(context, string, 17, q22.i);
        q22.t = "\u70b9\u51fb\u8bd5\u542c\u53ef\u64ad\u653e\u672c\u673a\u8bed\u97f3\u3002\u4fee\u6539\u5907\u6ce8\u548c\u5220\u9664\u53ea\u5f71\u54cd\u672c\u673a\u8bed\u97f3\u5e93\uff1b\u53ea\u6709\u70b9\u51fb\u201c\u53d1\u9001\u5230\u5f53\u524d\u804a\u5929\u201d\u624d\u4f1a\u53d1\u9001\u3002";
        object.setGravity(17);
        object.setTypeface(Typeface.DEFAULT, 1);
        object.setPadding(0, w2.i(context, 20), 0, w2.i(context, 8));
        string = q22.h;
        string.addView((View)object, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        object = new StringBuilder();
        ((StringBuilder)object).append(h32.c);
        ((StringBuilder)object).append(" \u79d2 \u00b7 \u5df2\u4fdd\u5b58\u5728\u672c\u673a");
        object = w2.n(context, ((StringBuilder)object).toString(), 13, q22.j);
        object.setGravity(17);
        object.setPadding(0, 0, 0, w2.i(context, 24));
        string.addView((View)object, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        this.p = d3.a(q22, "\u8bd5\u542c", new q2_0(this, h32, 0));
        object = d3.a(q22, "\u4fee\u6539\u5907\u6ce8", new q2_0(this, h32, 4));
        TextView textView = this.p;
        int n = q22.k;
        textView.setTextColor(n);
        object.setTextColor(n);
        w2.a(q22, (LinearLayout)string, this.p, (TextView)object);
        object = d3.a(q22, "\u5220\u9664", new q2_0(this, h32, 5));
        int n2 = this.g ? -23645 : -5036514;
        object.setTextColor(n2);
        object.setGravity(17);
        string.addView((View)object, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        h32 = d3.a(q22, "\u53d1\u9001\u5230\u5f53\u524d\u804a\u5929", new q2_0(this, h32, 7));
        h32.setTextColor(n);
        h32.setGravity(17);
        object = q22.c;
        object.getClass();
        ((L2)object).b((View)h32, 7, L2.e(7));
        object = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)object).topMargin = w2.i(context, 16);
        ((LinearLayout.LayoutParams)object).bottomMargin = w2.i(context, 12);
        string.addView((View)h32, (ViewGroup.LayoutParams)object);
        q22.g("\u8bed\u97f3\u8be6\u60c5", "\u8fd4\u56de\u8bed\u97f3\u5e93");
        this.k(new u2_0(3, this));
        h32 = q22.e.getButton(-1);
        h32.setTextColor(n);
        h32.setMinHeight(w2.i(context, 48));
        h32.setOnClickListener(new A0(5, this));
    }

    public final void e(String string, String string2, P2 p2, c3 c32) {
        q2 q22 = this.g();
        Context context = q22.g;
        EditText editText = new EditText(context);
        editText.setTextColor(q22.i);
        editText.setHintTextColor(q22.j);
        L2 l2 = q22.c;
        l2.getClass();
        l2.b((View)editText, 8, L2.e(8));
        editText.setPadding(w2.i(context, 12), w2.i(context, 10), w2.i(context, 12), w2.i(context, 10));
        editText.setHint((CharSequence)"\u7ed9\u8fd9\u6761\u8bed\u97f3\u5199\u4e2a\u5907\u6ce8");
        editText.setText((CharSequence)string2);
        editText.setMinLines(2);
        editText.setMaxLines(4);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(120)});
        q22.h.addView((View)editText, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
        q22.d.setNegativeButton((CharSequence)"\u53d6\u6d88", (DialogInterface.OnClickListener)new L(1));
        q22.g(string, "\u4fdd\u5b58");
        this.k(c32);
        q22.e.getButton(-2).setOnClickListener((View.OnClickListener)new C0(this, c32, 6));
        q22.e.getButton(-1).setOnClickListener((View.OnClickListener)new G1(this, p2, editText, 2));
    }

    public final void f() {
        this.j();
        q2 q22 = this.g();
        q22.t = "\u4fdd\u5b58\u8bed\u97f3\n\u957f\u6309\u804a\u5929\u4e2d\u7684\u8bed\u97f3\u6d88\u606f\uff0c\u9009\u62e9\u300c\u5b58\u5165\u8bed\u97f3\u5e93\u300d\u3002\n\n\u6253\u5f00\u8bed\u97f3\u5e93\n\u8fdb\u5165\u666e\u901a\u79c1\u804a\uff0c\u957f\u6309\u804a\u5929\u8f93\u5165\u533a\u7684\u8bed\u97f3\u56fe\u6807\u3002\n\n\u4f7f\u7528\u5df2\u4fdd\u5b58\u7684\u8bed\u97f3\n\u70b9\u51fb\u6761\u76ee\u53ef\u8bd5\u542c\u3001\u4fee\u6539\u5907\u6ce8\u6216\u5220\u9664\u3002\u53ea\u6709\u70b9\u51fb\u300c\u53d1\u9001\u5230\u5f53\u524d\u804a\u5929\u300d\u624d\u4f1a\u53d1\u9001\u3002\n\n\u8bed\u97f3\u4fdd\u5b58\u5728\u672c\u673a\uff0c\u6309\u8d26\u53f7\u5206\u522b\u7ba1\u7406\u3002";
        LinearLayout linearLayout = q22.h;
        int n = q22.j;
        linearLayout.addView((View)w2.n(q22.g, "\u6b63\u5728\u8bfb\u53d6\u8bed\u97f3\u5e93\u2026", 14, n));
        this.i = q22;
        q22.g("\u8bed\u97f3\u5e93", "\u5173\u95ed");
        this.k(new u2_0(1, this));
        this.q.b.execute(new V2(this, q22, 0));
    }

    public final q2 g() {
        this.i();
        q2 q22 = this.h;
        if (q22 != null && q22.e != null) {
            if (q22 == this.i && q22.h.getParent() instanceof ScrollView) {
                this.j = ((ScrollView)this.h.h.getParent()).getScrollY();
            }
            this.h.e.setOnCancelListener(null);
            this.h.e.setOnDismissListener(null);
            this.h.e.dismiss();
        }
        this.p = null;
        this.h = q22 = new q2(this.c, this.g);
        return q22;
    }

    public final void h(q2 object, LinearLayout linearLayout, ArrayList arrayList) {
        int n;
        Context context;
        linearLayout.removeAllViews();
        String string = this.k.trim().toLowerCase(Locale.ROOT);
        Iterator iterator = arrayList.iterator();
        int n2 = 0;
        while (true) {
            boolean bl = iterator.hasNext();
            context = ((q2)object).g;
            n = ((q2)object).j;
            if (!bl) break;
            h3 h32 = (h3)iterator.next();
            if (!h32.b.toLowerCase(Locale.ROOT).contains(string)) continue;
            StringBuilder stringBuilder = new StringBuilder();
            String string2 = h32.b;
            stringBuilder.append(string2);
            stringBuilder.append("\n");
            stringBuilder.append(h32.c);
            stringBuilder.append(" \u79d2");
            h32 = d3.a((q2)object, stringBuilder.toString(), new q2_0(this, h32, 6));
            stringBuilder = new SpannableString(h32.getText());
            stringBuilder.setSpan(new StyleSpan(1), 0, string2.length(), 0);
            stringBuilder.setSpan(new ForegroundColorSpan(n), string2.length() + 1, stringBuilder.length(), 0);
            stringBuilder.setSpan(new RelativeSizeSpan(0.87f), string2.length() + 1, stringBuilder.length(), 0);
            h32.setText(stringBuilder);
            h32.setTextSize(16.0f);
            h32.setLineSpacing(w2.i(context, 4), 1.0f);
            w2.b((q2)object, linearLayout, (TextView)h32);
            ++n2;
        }
        if (n2 == 0) {
            object = arrayList.isEmpty() ? "\u8fd8\u6ca1\u6709\u4fdd\u5b58\u8bed\u97f3" : "\u6ca1\u6709\u5339\u914d\u7684\u8bed\u97f3\uff0c\u8bf7\u5c1d\u8bd5\u5176\u4ed6\u5907\u6ce8";
            linearLayout.addView((View)w2.n(context, (String)object, 14, n));
        }
    }

    public final void i() {
        MediaPlayer mediaPlayer = this.l;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.l = null;
        }
        if ((mediaPlayer = this.p) != null) {
            mediaPlayer.setText((CharSequence)"\u8bd5\u542c");
        }
    }

    public final void j() {
        Object object;
        Object object2;
        Object object3;
        if (!this.m && (object3 = this.c) != null && !object3.isFinishing() && !object3.isDestroyed() && ((Boolean)d3.e(object3 = this.a, "isResumed")).booleanValue() && ((String)(object2 = this.d)).equals(object = this.q.d.invoke(null, null)) && (object = this.e) != null && !((String)object).isEmpty() && !((String)object2).equals(object) && ((String)object).equals(d3.g(object3, "L")) && (object2 = this.b) != null && object2 == d3.c(object3) && (Integer)d3.e(object2, "getChatType") == 0 && ((String)object).equals(d3.e(object2, "getToUserId"))) {
            return;
        }
        throw new IllegalStateException("\u804a\u5929\u6216\u8d26\u53f7\u5df2\u53d8\u5316\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u8bed\u97f3\u5e93");
    }

    public final void k(c3 c32) {
        q2 q22 = this.h;
        q22.e.setCanceledOnTouchOutside(false);
        q22.e.setOnCancelListener((DialogInterface.OnCancelListener)new X2(this, q22, c32));
        q22.e.setOnDismissListener((DialogInterface.OnDismissListener)new c1_0(this, q22, 2));
    }
}

