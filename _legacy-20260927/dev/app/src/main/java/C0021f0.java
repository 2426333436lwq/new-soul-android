package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.C0021f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0021f0 {
    public final C0067q2 a;
    public final C0070r2 b;
    public final TextView c;
    public final TextView d;
    public final TextView e;
    public final TextView f;
    public boolean g;
    public boolean h;

    public C0021f0(Activity activity, boolean z) {
        C0067q2 c0067q2 = new C0067q2(activity, z);
        this.a = c0067q2;
        c0067q2.t = "遇到问题时，开启调试模式，再返回出现问题的页面复现一次，然后导出记录并发给作者。如需排查启动问题，可在开启后重启 Soul。\n\n每次最多记录 30 分钟，总大小上限 4 MB，到达上限会自动停止。关闭调试不会删除已有记录；清除记录会同时停止调试。\n\n记录应用与系统版本、页面、功能执行状态及错误位置，不记录聊天正文、语音内容、账号凭据或 API 密钥。";
        LinearLayout linearLayoutR = AbstractC0090w2.r(c0067q2, "记录", null, null);
        C0070r2 c0070r2D = AbstractC0090w2.d(linearLayoutR, null, "debug_session", "调试模式", "", c0067q2.i);
        this.b = c0070r2D;
        this.c = AbstractC0090w2.c(linearLayoutR, "");
        Context context = c0067q2.g;
        int i = c0067q2.k;
        TextView textViewN = AbstractC0090w2.n(context, "导出记录", 15, i);
        this.e = textViewN;
        TextView textViewN2 = AbstractC0090w2.n(context, "清除记录", 15, i);
        this.f = textViewN2;
        AbstractC0090w2.g(textViewN);
        AbstractC0090w2.g(textViewN2);
        AbstractC0090w2.a(c0067q2, linearLayoutR, textViewN, textViewN2);
        TextView textViewC = AbstractC0090w2.c(linearLayoutR, "");
        this.d = textViewC;
        textViewC.setVisibility(8);
        textViewC.setAccessibilityLiveRegion(1);
        c0070r2D.setOnCheckedChangeListener(new b0(this));
        final int i2 = 0;
        textViewN.setOnClickListener(new c0(this, i2));
        final int i3 = 1;
        textViewN2.setOnClickListener(new c0(this, i3));
        c0067q2.g("调试", "关闭");
        b();
        AbstractC0025g0.b.postDelayed(new RunnableC0017e0(this), 1000L);
    }

    public final void a(String str) {
        TextView textView = this.d;
        textView.setText(str);
        textView.setVisibility(0);
        b();
    }

    public final void b() {
        String str;
        boolean z = true;
        this.g = true;
        this.b.setChecked(V.a());
        this.g = false;
        TextView textView = this.c;
        if (V.a()) {
            str = "正在记录 · 剩余 " + Math.max(1L, ((V.f - System.currentTimeMillis()) + 59999) / 60000) + " 分钟";
        } else {
            str = V.h;
        }
        textView.setText(str + "\n记录大小 " + String.format(Locale.ROOT, "%.1f KB", Double.valueOf(V.g / 1024.0d)));
        boolean z2 = this.h || AbstractC0025g0.d.containsKey(this.a.a);
        this.b.setEnabled(!z2);
        this.e.setEnabled(!z2 && V.g());
        TextView textView2 = this.f;
        if (z2 || (!V.g() && !V.a())) {
            z = false;
        }
        textView2.setEnabled(z);
        TextView textView3 = this.e;
        textView3.setAlpha(textView3.isEnabled() ? 1.0f : 0.5f);
        TextView textView4 = this.f;
        textView4.setAlpha(textView4.isEnabled() ? 1.0f : 0.5f);
    }
}
