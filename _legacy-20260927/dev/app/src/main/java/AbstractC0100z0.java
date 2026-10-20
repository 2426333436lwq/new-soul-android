package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: dev.local.newsoul.AbstractC0100z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0100z0 {
    public static final WeakHashMap a = new WeakHashMap();

    public static void a(final Activity activity, boolean z) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        WeakHashMap weakHashMap = a;
        WeakReference weakReference = (WeakReference) weakHashMap.get(activity);
        AlertDialog alertDialog = weakReference == null ? null : (AlertDialog) weakReference.get();
        if (alertDialog == null || !alertDialog.isShowing()) {
            final C0067q2 c0067q2 = new C0067q2(activity, z);
            final SharedPreferences sharedPreferences = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
            SpannableString spannableString = new SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。");
            spannableString.setSpan(new ForegroundColorSpan(c0067q2.k), 64, spannableString.length(), 33);
            spannableString.setSpan(new StyleSpan(1), 64, spannableString.length(), 33);
            c0067q2.t = spannableString;
            boolean z2 = AbstractC0061p0.a || AbstractC0061p0.b || AbstractC0061p0.c;
            LinearLayout linearLayout = c0067q2.h;
            int i = c0067q2.j;
            Context context = c0067q2.g;
            if (!z2) {
                linearLayout.addView(AbstractC0090w2.n(context, "当前版本的主页数量显示入口未就绪", 14, i));
            }
            final C0096y0 c0096y0 = new C0096y0(c0067q2, sharedPreferences, "关注", "profile_disguise_follow_");
            final C0096y0 c0096y1 = new C0096y0(c0067q2, sharedPreferences, "被关注", "profile_disguise_followed_");
            final C0096y0 c0096y2 = new C0096y0(c0067q2, sharedPreferences, "访客", "profile_disguise_visitor_");
            final C0096y0 c0096y3 = new C0096y0(c0067q2, sharedPreferences, "天数", "profile_disguise_days_");
            if (!AbstractC0061p0.d && !AbstractC0061p0.e) {
                linearLayout.addView(AbstractC0090w2.n(context, "当前版本的天数显示入口未就绪", 14, i));
            }
            c0067q2.c(new C0076t0(c0096y0, c0096y1, c0096y2, c0096y3, 0));
            c0067q2.g("信息伪装", "保存");
            weakHashMap.put(activity, new WeakReference(c0067q2.e));
            c0067q2.e.getWindow().setSoftInputMode(19);
            linearLayout.setFocusableInTouchMode(true);
            linearLayout.requestFocus();
            c0067q2.e.getButton(-1).setOnClickListener(new u0(c0096y3, c0096y3, c0096y3, c0096y3, sharedPreferences, c0067q2, activity));
        }
    }
}
