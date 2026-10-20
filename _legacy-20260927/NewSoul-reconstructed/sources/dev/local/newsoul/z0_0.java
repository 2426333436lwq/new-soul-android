/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.text.SpannableString
 *  android.text.style.ForegroundColorSpan
 *  android.text.style.StyleSpan
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 */
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
import dev.local.newsoul.p0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.t0_0;
import dev.local.newsoul.u0_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.y0_0;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/*
 * Renamed from dev.local.newsoul.z0
 */
public abstract class z0_0 {
    public static final WeakHashMap a = new WeakHashMap();

    public static void a(Activity activity, boolean bl) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            WeakHashMap weakHashMap = a;
            WeakReference weakReference = (WeakReference)weakHashMap.get(activity);
            weakReference = weakReference == null ? null : (AlertDialog)weakReference.get();
            if (weakReference != null && weakReference.isShowing()) {
                return;
            }
            q2 q22 = new q2(activity, bl);
            boolean bl2 = false;
            weakReference = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
            SpannableString spannableString = new SpannableString((CharSequence)"\u53ea\u6539\u53d8\u672c\u673a\u81ea\u5df1\u4e2a\u4eba\u4e3b\u9875\u7684\u5173\u6ce8\u3001\u88ab\u5173\u6ce8\u3001\u8bbf\u5ba2\u6570\u91cf\u53ca\u52a0\u5165\u5929\u6570\uff0c\u4e0d\u6539\u53d8\u771f\u5b9e\u5173\u6ce8\u5173\u7cfb\u3001\u8bbf\u95ee\u8bb0\u5f55\u6216\u6ce8\u518c\u65f6\u95f4\uff0c\u4e5f\u4e0d\u4f1a\u6539\u53d8\u4ed6\u4eba\u770b\u5230\u7684\u6570\u636e\u3002\n\n\u957f\u6309\u81ea\u5df1\u4e3b\u9875\u7684\u5bf9\u5e94\u533a\u57df\uff0c\u4e5f\u53ef\u4ee5\u5feb\u901f\u6253\u5f00\u6b64\u9875\u9762\u3002");
            spannableString.setSpan((Object)new ForegroundColorSpan(q22.k), 64, spannableString.length(), 33);
            spannableString.setSpan((Object)new StyleSpan(1), 64, spannableString.length(), 33);
            q22.t = spannableString;
            if (p0_0.a || p0_0.b || p0_0.c) {
                bl2 = true;
            }
            LinearLayout linearLayout = q22.h;
            int n = q22.j;
            spannableString = q22.g;
            if (!bl2) {
                linearLayout.addView((View)w2.n((Context)spannableString, "\u5f53\u524d\u7248\u672c\u7684\u4e3b\u9875\u6570\u91cf\u663e\u793a\u5165\u53e3\u672a\u5c31\u7eea", 14, n));
            }
            y0_0 y0_02 = new y0_0(q22, (SharedPreferences)weakReference, "\u5173\u6ce8", "profile_disguise_follow_");
            y0_0 y0_03 = new y0_0(q22, (SharedPreferences)weakReference, "\u88ab\u5173\u6ce8", "profile_disguise_followed_");
            y0_0 y0_04 = new y0_0(q22, (SharedPreferences)weakReference, "\u8bbf\u5ba2", "profile_disguise_visitor_");
            y0_0 y0_05 = new y0_0(q22, (SharedPreferences)weakReference, "\u5929\u6570", "profile_disguise_days_");
            if (!p0_0.d && !p0_0.e) {
                linearLayout.addView((View)w2.n((Context)spannableString, "\u5f53\u524d\u7248\u672c\u7684\u5929\u6570\u663e\u793a\u5165\u53e3\u672a\u5c31\u7eea", 14, n));
            }
            q22.c(new t0_0(y0_02, y0_03, y0_04, y0_05, 0));
            q22.g("\u4fe1\u606f\u4f2a\u88c5", "\u4fdd\u5b58");
            weakHashMap.put(activity, new WeakReference<AlertDialog>(q22.e));
            q22.e.getWindow().setSoftInputMode(19);
            linearLayout.setFocusableInTouchMode(true);
            linearLayout.requestFocus();
            q22.e.getButton(-1).setOnClickListener((View.OnClickListener)new u0_0(y0_02, y0_03, y0_04, y0_05, (SharedPreferences)weakReference, q22, activity));
        }
    }
}

