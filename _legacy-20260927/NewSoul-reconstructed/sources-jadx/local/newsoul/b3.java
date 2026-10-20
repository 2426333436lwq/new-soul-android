package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class b3 {
    public final Object a;
    public final Object b;
    public final Activity c;
    public final String d;
    public final String e;
    public final C0093x1 f;
    public final boolean g;
    public C0067q2 h;
    public C0067q2 i;
    public int j;
    public String k = "";
    public MediaPlayer l;
    public boolean m;
    public boolean n;
    public boolean o;
    public TextView p;
    public final /* synthetic */ d3 q;

    public b3(d3 d3Var, Object obj) {
        this.q = d3Var;
        this.a = obj;
        Activity activity = (Activity) d3.e(obj, "getActivity");
        this.c = activity;
        String str = (String) d3Var.d.invoke(null, null);
        this.d = str;
        this.e = (String) d3.g(obj, "L");
        this.b = d3.c(obj);
        j();
        this.f = new C0093x1(activity.getFilesDir(), str, 1);
        int identifier = activity.getResources().getIdentifier("tv_title", "id", activity.getPackageName());
        View viewFindViewById = identifier != 0 ? activity.findViewById(identifier) : null;
        boolean z = false;
        if (!(viewFindViewById instanceof TextView) ? (activity.getResources().getConfiguration().uiMode & 48) == 32 : Color.luminance(((TextView) viewFindViewById).getCurrentTextColor()) > 0.45d) {
            z = true;
        }
        this.g = z;
    }

    public final void a(c3 c3Var, c3 c3Var2) {
        if (this.n) {
            return;
        }
        this.n = true;
        this.o = true;
        C0067q2 c0067q2 = this.h;
        TextView textViewN = AbstractC0090w2.n(c0067q2.g, "操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。", 13, c0067q2.j);
        c0067q2.h.addView(textViewN);
        if (c0067q2.e.getButton(-2) != null) {
            c0067q2.e.getButton(-2).setText("关闭");
        }
        if (c0067q2.e.getButton(-1) != null) {
            c0067q2.e.getButton(-1).setEnabled(false);
            c0067q2.e.getButton(-1).setText("处理中…");
        }
        this.q.b.execute(new RunnableC0050m1(this, c3Var, c0067q2, textViewN, c3Var2, 5));
    }

    public final void b() {
        AlertDialog alertDialog;
        if (this.m) {
            return;
        }
        this.m = true;
        i();
        C0067q2 c0067q2 = this.h;
        if (c0067q2 != null && (alertDialog = c0067q2.e) != null) {
            alertDialog.dismiss();
        }
        d3 d3Var = this.q;
        if (d3Var.f == this) {
            d3Var.f = null;
        }
    }

    public final void c() {
        if (this.o) {
            d3.i(this.c, "操作仍在后台进行，请稍后打开语音库查看结果");
        }
        b();
    }

    public final void d(h3 h3Var) {
        j();
        C0067q2 c0067q2G = g();
        String str = h3Var.b;
        Context context = c0067q2G.g;
        TextView textViewN = AbstractC0090w2.n(context, str, 17, c0067q2G.i);
        c0067q2G.t = "点击试听可播放本机语音。修改备注和删除只影响本机语音库；只有点击“发送到当前聊天”才会发送。";
        textViewN.setGravity(17);
        textViewN.setTypeface(Typeface.DEFAULT, 1);
        textViewN.setPadding(0, AbstractC0090w2.i(context, 20), 0, AbstractC0090w2.i(context, 8));
        LinearLayout linearLayout = c0067q2G.h;
        linearLayout.addView(textViewN, new LinearLayout.LayoutParams(-1, -2));
        TextView textViewN2 = AbstractC0090w2.n(context, h3Var.c + " 秒 · 已保存在本机", 13, c0067q2G.j);
        textViewN2.setGravity(17);
        textViewN2.setPadding(0, 0, 0, AbstractC0090w2.i(context, 24));
        linearLayout.addView(textViewN2, new LinearLayout.LayoutParams(-1, -2));
        this.p = d3.a(c0067q2G, "试听", new Q2(this, h3Var, 0));
        TextView textViewA = d3.a(c0067q2G, "修改备注", new Q2(this, h3Var, 4));
        TextView textView = this.p;
        int i = c0067q2G.k;
        textView.setTextColor(i);
        textViewA.setTextColor(i);
        AbstractC0090w2.a(c0067q2G, linearLayout, this.p, textViewA);
        TextView textViewA2 = d3.a(c0067q2G, "删除", new Q2(this, h3Var, 5));
        textViewA2.setTextColor(this.g ? -23645 : -5036514);
        textViewA2.setGravity(17);
        linearLayout.addView(textViewA2, new LinearLayout.LayoutParams(-1, -2));
        TextView textViewA3 = d3.a(c0067q2G, "发送到当前聊天", new Q2(this, h3Var, 7));
        textViewA3.setTextColor(i);
        textViewA3.setGravity(17);
        L2 l2 = c0067q2G.c;
        l2.getClass();
        l2.b(textViewA3, 7, L2.e(7));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = AbstractC0090w2.i(context, 16);
        layoutParams.bottomMargin = AbstractC0090w2.i(context, 12);
        linearLayout.addView(textViewA3, layoutParams);
        c0067q2G.g("语音详情", "返回语音库");
        k(new U2(3, this));
        Button button = c0067q2G.e.getButton(-1);
        button.setTextColor(i);
        button.setMinHeight(AbstractC0090w2.i(context, 48));
        button.setOnClickListener(new A0(5, this));
    }

    public final void e(String str, String str2, P2 p2, c3 c3Var) {
        C0067q2 c0067q2G = g();
        Context context = c0067q2G.g;
        EditText editText = new EditText(context);
        editText.setTextColor(c0067q2G.i);
        editText.setHintTextColor(c0067q2G.j);
        L2 l2 = c0067q2G.c;
        l2.getClass();
        l2.b(editText, 8, L2.e(8));
        editText.setPadding(AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10), AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10));
        editText.setHint("给这条语音写个备注");
        editText.setText(str2);
        editText.setMinLines(2);
        editText.setMaxLines(4);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(120)});
        c0067q2G.h.addView(editText, new LinearLayout.LayoutParams(-1, -2));
        c0067q2G.d.setNegativeButton("取消", new L(1));
        c0067q2G.g(str, "保存");
        k(c3Var);
        c0067q2G.e.getButton(-2).setOnClickListener(new C0(this, c3Var, 6));
        c0067q2G.e.getButton(-1).setOnClickListener(new G1(this, p2, editText, 2));
    }

    public final void f() {
        j();
        C0067q2 c0067q2G = g();
        c0067q2G.t = "保存语音\n长按聊天中的语音消息，选择「存入语音库」。\n\n打开语音库\n进入普通私聊，长按聊天输入区的语音图标。\n\n使用已保存的语音\n点击条目可试听、修改备注或删除。只有点击「发送到当前聊天」才会发送。\n\n语音保存在本机，按账号分别管理。";
        c0067q2G.h.addView(AbstractC0090w2.n(c0067q2G.g, "正在读取语音库…", 14, c0067q2G.j));
        this.i = c0067q2G;
        c0067q2G.g("语音库", "关闭");
        k(new U2(1, this));
        this.q.b.execute(new V2(this, c0067q2G, 0));
    }

    public final C0067q2 g() {
        i();
        C0067q2 c0067q2 = this.h;
        if (c0067q2 != null && c0067q2.e != null) {
            if (c0067q2 == this.i && (c0067q2.h.getParent() instanceof ScrollView)) {
                this.j = ((ScrollView) this.h.h.getParent()).getScrollY();
            }
            this.h.e.setOnCancelListener(null);
            this.h.e.setOnDismissListener(null);
            this.h.e.dismiss();
        }
        this.p = null;
        C0067q2 c0067q3 = new C0067q2(this.c, this.g);
        this.h = c0067q3;
        return c0067q3;
    }

    public final void h(C0067q2 c0067q2, LinearLayout linearLayout, ArrayList arrayList) {
        Context context;
        int i;
        linearLayout.removeAllViews();
        String lowerCase = this.k.trim().toLowerCase(Locale.ROOT);
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (true) {
            boolean zHasNext = it.hasNext();
            context = c0067q2.g;
            i = c0067q2.j;
            if (!zHasNext) {
                break;
            }
            h3 h3Var = (h3) it.next();
            if (h3Var.b.toLowerCase(Locale.ROOT).contains(lowerCase)) {
                StringBuilder sb = new StringBuilder();
                String str = h3Var.b;
                sb.append(str);
                sb.append("\n");
                sb.append(h3Var.c);
                sb.append(" 秒");
                TextView textViewA = d3.a(c0067q2, sb.toString(), new Q2(this, h3Var, 6));
                SpannableString spannableString = new SpannableString(textViewA.getText());
                spannableString.setSpan(new StyleSpan(1), 0, str.length(), 0);
                spannableString.setSpan(new ForegroundColorSpan(i), str.length() + 1, spannableString.length(), 0);
                spannableString.setSpan(new RelativeSizeSpan(0.87f), str.length() + 1, spannableString.length(), 0);
                textViewA.setText(spannableString);
                textViewA.setTextSize(16.0f);
                textViewA.setLineSpacing(AbstractC0090w2.i(context, 4), 1.0f);
                AbstractC0090w2.b(c0067q2, linearLayout, textViewA);
                i2++;
            }
        }
        if (i2 == 0) {
            linearLayout.addView(AbstractC0090w2.n(context, arrayList.isEmpty() ? "还没有保存语音" : "没有匹配的语音，请尝试其他备注", 14, i));
        }
    }

    public final void i() {
        MediaPlayer mediaPlayer = this.l;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.l = null;
        }
        TextView textView = this.p;
        if (textView != null) {
            textView.setText("试听");
        }
    }

    public final void j() {
        Activity activity;
        String str;
        Object obj;
        if (!this.m && (activity = this.c) != null && !activity.isFinishing() && !activity.isDestroyed()) {
            Object obj2 = this.a;
            if (((Boolean) d3.e(obj2, "isResumed")).booleanValue()) {
                Object objInvoke = this.q.d.invoke(null, null);
                String str2 = this.d;
                if (str2.equals(objInvoke) && (str = this.e) != null && !str.isEmpty() && !str2.equals(str) && str.equals(d3.g(obj2, "L")) && (obj = this.b) != null && obj == d3.c(obj2) && ((Integer) d3.e(obj, "getChatType")).intValue() == 0 && str.equals(d3.e(obj, "getToUserId"))) {
                    return;
                }
            }
        }
        throw new IllegalStateException("聊天或账号已变化，请重新打开语音库");
    }

    public final void k(final c3 c3Var) {
        final C0067q2 c0067q2 = this.h;
        c0067q2.e.setCanceledOnTouchOutside(false);
        c0067q2.e.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: dev.local.newsoul.X2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                c3 c3Var2 = c3Var;
                b3 b3Var = this.a;
                if (b3Var.m || b3Var.h != c0067q2) {
                    return;
                }
                if (b3Var.n) {
                    b3Var.c();
                    return;
                }
                try {
                    b3Var.j();
                    c3Var2.run();
                } catch (Throwable unused) {
                    b3Var.b();
                }
            }
        });
        c0067q2.e.setOnDismissListener(new DialogInterfaceOnDismissListenerC0010c1(this, c0067q2, 2));
    }
}
