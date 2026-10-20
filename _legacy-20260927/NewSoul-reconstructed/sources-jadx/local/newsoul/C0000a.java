package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import io.github.libxposed.api.XposedInterface;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: dev.local.newsoul.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0000a implements XposedInterface.Hooker {
    public final /* synthetic */ int a;

    public /* synthetic */ C0000a(int i) {
        this.a = i;
    }

    /* JADX WARN: Code duplicated, block: B:147:0x0330  */
    /* JADX WARN: Code duplicated, block: B:237:0x04e4  */
    public final Object intercept(XposedInterface.Chain chain) {
        boolean z;
        int iIntValue;
        boolean z2;
        String str = null;
        boolean z3 = false;
        switch (this.a) {
            case 0:
                View view = (View) chain.getThisObject();
                C0032i c0032i = (C0032i) AbstractC0036j.d.get(view);
                if (c0032i == null || !AbstractC0036j.b(view.getContext(), c0032i.a)) {
                    return chain.proceed();
                }
                c0032i.b = ((Integer) chain.getArg(0)).intValue();
                c0032i.c = true;
                return chain.proceed(new Object[]{8});
            case 1:
                Object objProceed = chain.proceed();
                for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                    String className = stackTraceElement.getClassName();
                    if (className.equals("pi.d")) {
                        str = "ad_conversation";
                    } else if (className.startsWith("cn.soulapp.android.component.chat.limitdialog.RewardAdHelper")) {
                        str = "ad_reward_chat";
                    } else if (className.startsWith("cn.soulapp.android.component.square.video.drama.o0")) {
                        str = "ad_reward_drama";
                    } else if (className.startsWith("cn.soulapp.android.ad.h5.moduel.") || className.equals("cc.c") || className.startsWith("cn.soul.android.soul_rn_sdk.plugin.SRNAdPlugin") || className.startsWith("cn.soul.android.soul_rn_sdk.plugin.SoulRnAdPlugin")) {
                        str = "ad_reward_web";
                    } else if (className.startsWith("cn.soulapp.android.ad.base.view.AdFloatViewProvider")) {
                        str = "ad_float";
                    } else if (className.equals("cn.soulapp.android.ad.base.view.e")) {
                        str = "ad_interstitial";
                    }
                    if (str != null) {
                        AbstractC0036j.e.put(chain.getThisObject(), str);
                    }
                    return objProceed;
                }
                if (str != null) {
                    AbstractC0036j.e.put(chain.getThisObject(), str);
                }
                return objProceed;
            case 2:
                Object objProceed2 = chain.proceed();
                final Activity activity = (Activity) chain.getThisObject();
                try {
                    View decorView = activity.getWindow().getDecorView();
                    decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: dev.local.newsoul.l
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public final void onGlobalLayout() {
                            AbstractC0056o.a(activity);
                        }
                    });
                    decorView.post(new RunnableC0048m(activity, 0));
                    break;
                } catch (Throwable th) {
                    Log.w("SoulHook102", "bottom settings observation failed", th);
                }
                return objProceed2;
            case 3:
                if (!Q0.q("match_mute_background_music")) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "soul match background music blocked");
                return null;
            case 4:
                Integer num = (Integer) AbstractC0075t.c.get();
                if (num == null || num.intValue() <= 0) {
                    return chain.proceed();
                }
                String str2 = "game message constructed with selected result=" + num;
                Log.i("SoulHook102", str2);
                AbstractC0075t.f.log(4, "SoulHook102", str2);
                return chain.proceed(new Object[]{num});
            case 5:
                Object objProceed3 = chain.proceed();
                Boolean bool = Boolean.TRUE;
                boolean z4 = bool.equals(objProceed3) && Q0.e(chain.getThisObject());
                if (bool.equals(objProceed3)) {
                    Log.i("SoulHook102", "chat gate observed; type=" + String.valueOf(Q0.i.invoke(chain.getThisObject(), null)) + "; bypass=" + z4);
                }
                return z4 ? Boolean.FALSE : objProceed3;
            case 6:
                Object objProceed4 = chain.proceed();
                if (!"chat_limit_session_type".equals(chain.getArg(0)) || (iIntValue = ((Integer) objProceed4).intValue()) < 2 || iIntValue > 5 || !Q0.q("chat_remove_gift_gate")) {
                    return objProceed4;
                }
                return 1;
            case 7:
                Object objProceed5 = chain.proceed();
                if (!Q0.q("chat_remove_bell_gift_gate")) {
                    return objProceed5;
                }
                if (Boolean.TRUE.equals(objProceed5)) {
                    Log.i("SoulHook102", "special bell gift popup bypassed");
                }
                return Boolean.FALSE;
            case 8:
                if (!Q0.e(chain.getArg(0))) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "gift chat dialog suppressed");
                return null;
            case 9:
                try {
                    Context context = (Context) Q0.l.invoke(null, null);
                    if (AbstractC0090w2.o(context).getBoolean("chat_anti_recall", true)) {
                        Object arg = chain.getArg(0);
                        if (arg instanceof ArrayList) {
                            List list = (List) arg;
                            z = false;
                            for (int size = list.size() - 1; size >= 0; size--) {
                                try {
                                    if (Q0.S(context, chain.getThisObject(), list.get(size))) {
                                        list.remove(size);
                                        z = true;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    Log.e("SoulHook102", "recall interception failed", th);
                                    Object objProceed6 = chain.proceed();
                                    if (z) {
                                        new Handler(Looper.getMainLooper()).post(new RunnableC0028h(1 == true ? 1 : 0));
                                    }
                                    return objProceed6;
                                }
                            }
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z = false;
                }
                Object objProceed7 = chain.proceed();
                if (z && Q0.y != null) {
                    new Handler(Looper.getMainLooper()).post(new RunnableC0028h(1 == true ? 1 : 0));
                }
                return objProceed7;
            case 10:
                try {
                    Context context2 = (Context) Q0.l.invoke(null, null);
                    Object arg2 = chain.getArg(0);
                    if (AbstractC0090w2.o(context2).getBoolean("chat_anti_recall", true) && Q0.v(arg2) && Q0.u(arg2)) {
                        String strL = Q0.L(arg2);
                        SharedPreferences sharedPreferences = context2.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0);
                        if (sharedPreferences.contains(strL) || sharedPreferences.edit().putBoolean(strL, true).commit()) {
                            Log.i("SoulHook102", "recall retained; source=roam");
                            return null;
                        }
                    }
                } catch (Throwable th4) {
                    Log.e("SoulHook102", "roam recall interception failed", th4);
                }
                return chain.proceed();
            case 11:
                C0079u c0079u = new C0079u(chain.getThisObject(), chain.getArg(1), chain.getArg(0));
                try {
                    Object objProceed8 = chain.proceed();
                    c0079u.close();
                    return objProceed8;
                } catch (Throwable th5) {
                    try {
                        c0079u.close();
                        throw th5;
                    } catch (Throwable th6) {
                        th5.addSuppressed(th6);
                        throw th5;
                    }
                }
            case 12:
                Activity activity2 = (Activity) chain.getThisObject();
                P p = (P) S.d.get(activity2);
                if (p != null) {
                    if (p.a == ((Integer) chain.getArg(0)).intValue()) {
                        if (!p.f) {
                            return null;
                        }
                        p.f = false;
                        if (((Integer) chain.getArg(1)).intValue() != -1) {
                            S.b(activity2, p, "已取消配置操作");
                            return null;
                        }
                        Intent intent = (Intent) chain.getArg(2);
                        if (intent == null || intent.getData() == null) {
                            S.b(activity2, p, "未取得配置文件，请重新选择");
                            return null;
                        }
                        Uri data = intent.getData();
                        String str3 = p.c != null ? "正在导出配置…" : "正在读取配置…";
                        Q q = (Q) p.e.get();
                        if (q != null) {
                            q.a(str3, true);
                        }
                        S.c.execute(new H(data, p, activity2, 0));
                        return null;
                    }
                }
                return chain.proceed();
            case 13:
                V.h((Context) chain.getThisObject());
                return chain.proceed();
            case 14:
                Activity activity3 = (Activity) chain.getThisObject();
                C0001a0 c0001a0 = (C0001a0) AbstractC0025g0.d.get(activity3);
                if (c0001a0 != null) {
                    if (c0001a0.a == ((Integer) chain.getArg(0)).intValue()) {
                        if (!c0001a0.d) {
                            return null;
                        }
                        c0001a0.d = false;
                        Intent intent2 = (Intent) chain.getArg(2);
                        if (((Integer) chain.getArg(1)).intValue() != -1 || intent2 == null || intent2.getData() == null) {
                            AbstractC0025g0.b(activity3, c0001a0, "已取消导出");
                            return null;
                        }
                        Uri data2 = intent2.getData();
                        C0021f0 c0021f0 = (C0021f0) c0001a0.b.get();
                        if (c0021f0 != null) {
                            c0021f0.a("正在保存记录…");
                        }
                        V.a.execute(new H((Object) data2, activity3, (Object) c0001a0, (int) (1 == true ? 1 : 0)));
                        return null;
                    }
                }
                return chain.proceed();
            case 15:
                Object objProceed9 = chain.proceed();
                ThreadLocal threadLocal = AbstractC0026g1.b;
                if (threadLocal.get() != chain.getThisObject()) {
                    return objProceed9;
                }
                threadLocal.remove();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - ((Long) objProceed9).longValue() <= 120000) {
                    return objProceed9;
                }
                Log.i("SoulHook102", "own recall local age check bypassed");
                return Long.valueOf(jCurrentTimeMillis);
            case 16:
                Activity activity4 = (Activity) chain.getThisObject();
                SharedPreferences sharedPreferencesO = AbstractC0090w2.o(activity4);
                if (Q0.R(activity4)) {
                    activity4.getIntent().putExtra("limitViewTimes", 0);
                }
                Object objProceed10 = chain.proceed();
                if (sharedPreferencesO.getBoolean("privacy_allow_screenshot", false)) {
                    activity4.getWindow().clearFlags(8192);
                }
                int intExtra = activity4.getIntent().getIntExtra("type", -1);
                if (sharedPreferencesO.getBoolean("privacy_allow_download", false)) {
                    int intExtra2 = activity4.getIntent().getIntExtra("type", -1);
                    if (intExtra2 < 1 || intExtra2 > 4) {
                        z2 = false;
                    } else {
                        int intExtra3 = activity4.getIntent().getIntExtra("type", -1);
                        boolean z5 = intExtra3 == 1 || intExtra3 == 3;
                        View decorView2 = activity4.getWindow().getDecorView();
                        long[] jArr = {0, 250, 750, 1500};
                        int i = 0;
                        while (i < 4) {
                            decorView2.postDelayed(new U(activity4, z5), jArr[i]);
                            i++;
                            z3 = false;
                        }
                        z2 = z3;
                    }
                } else {
                    z2 = z3;
                }
                Q0.w("privacy photo preview: download=" + sharedPreferencesO.getBoolean("privacy_allow_download", z2) + " screenshot=" + sharedPreferencesO.getBoolean("privacy_allow_screenshot", z2) + " repeat=" + sharedPreferencesO.getBoolean("privacy_allow_repeat", z2) + " type=" + intExtra);
                return objProceed10;
            case 17:
                Activity activity5 = (Activity) chain.getThisObject();
                Q0.w("privacy snap preview: repeat=" + Q0.R(activity5) + " type=" + activity5.getIntent().getIntExtra("type", -1));
                return chain.proceed();
            case 18:
                if (!Q0.q("chat_remove_social_score_limit")) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "social score dialog suppressed");
                return null;
            case 19:
                Activity activity6 = (Activity) chain.getThisObject();
                if (!Q0.e0) {
                    activity6.getApplication().registerActivityLifecycleCallbacks(new C2());
                    Q0.e0 = true;
                    Log.i("SoulHook102", "square like lifecycle registered");
                }
                return chain.proceed();
            case 20:
                Object objProceed11 = chain.proceed();
                ((View) chain.getThisObject()).addOnAttachStateChangeListener(new D2(0));
                return objProceed11;
            default:
                Object objProceed12 = chain.proceed();
                I2 i2I = Q0.i((View) chain.getThisObject());
                if (i2I != null) {
                    i2I.m();
                }
                return objProceed12;
        }
    }
}
