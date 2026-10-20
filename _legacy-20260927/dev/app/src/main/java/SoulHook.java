package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class SoulHook extends XposedModule {
    private static final String TAG = "SoulHook102";
    private static final String TARGET = "cn.soulapp.android";
    static boolean settingsHookReady;
    private boolean installed;
    private boolean targetProcess;
    private final AtomicBoolean toastShown = new AtomicBoolean();

    private void info(String str) {
        Log.i(TAG, str);
        log(4, TAG, str);
    }

    private void installSettingsHook(ClassLoader classLoader) {
        try {
            Method declaredMethod = classLoader.loadClass("cn.soulapp.android.h5.activity.H5Activity").getDeclaredMethod("m1", Boolean.TYPE);
            if (!Modifier.isPrivate(declaredMethod.getModifiers())) {
                throw new IllegalStateException("Unexpected H5Activity.m1 signature");
            }
            hook(declaredMethod).setId("soul-demo/private-h5-m1").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0094x2(this, 1));
            info("private hook installed: H5Activity.m1(boolean)");
            settingsHookReady = true;
        } catch (Throwable th) {
            Log.e(TAG, "Soul settings hook unavailable; check app version", th);
            log(6, TAG, "Soul settings hook unavailable; check app version", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$installSettingsHook$1(Activity activity) {
        int i = 0;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        try {
            WeakHashMap weakHashMap = AbstractC0090w2.a;
            if (weakHashMap.containsKey(activity)) {
                return;
            }
            View viewK = AbstractC0090w2.k(activity, "topic_title");
            View viewK2 = AbstractC0090w2.k(activity, "rlWebRoot");
            View viewK3 = AbstractC0090w2.k(activity, "contentLayout");
            View viewK4 = AbstractC0090w2.k(activity, "titleLay");
            if ((viewK instanceof TextView) && (viewK2 instanceof RelativeLayout) && viewK3 != null && viewK4 != null && viewK3.getParent() == viewK2 && (viewK3.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
                weakHashMap.put(activity, Boolean.TRUE);
                TextView textView = (TextView) viewK;
                RelativeLayout relativeLayout = (RelativeLayout) viewK2;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) viewK3.getLayoutParams());
                boolean[] zArr = {false};
                E1 e1 = new E1(activity, viewK3, textView);
                viewK3.addOnAttachStateChangeListener(new F1(viewK3, e1, i));
                if (viewK3.isAttachedToWindow()) {
                    viewK3.post(e1);
                }
                I1 i1 = new I1(activity, relativeLayout, textView, viewK3, layoutParams, zArr, viewK4);
                textView.addTextChangedListener(new C0003a2(i1, i));
                relativeLayout.getViewTreeObserver().addOnPreDrawListener(new M1(i, i1));
                i1.run();
                return;
            }
            Log.w(TAG, "Settings layout does not match; leaving page unchanged");
        } catch (Throwable th) {
            Log.e(TAG, "Settings observation failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$installSettingsHook$2(XposedInterface.Chain chain) throws Throwable {
        Object objProceed = chain.proceed();
        Activity activity = (Activity) chain.getThisObject();
        activity.getWindow().getDecorView().post(new RunnableC0048m(activity, 5));
        info("private hook hit: H5Activity.m1(boolean)");
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object lambda$onPackageReady$0(XposedInterface.Chain chain) throws Throwable {
        Object objProceed = chain.proceed();
        Activity activity = (Activity) chain.getThisObject();
        if (TARGET.equals(activity.getPackageName())) {
            V.h(activity);
            V.d("page.resume", activity.getClass().getName());
            Q0.f(activity);
            WeakHashMap weakHashMap = AbstractC0090w2.a;
            SharedPreferences sharedPreferences = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
            if (sharedPreferences.getBoolean("page_log", true)) {
                info("onResume: ".concat(activity.getClass().getName()));
            }
            if (sharedPreferences.getBoolean("startup_toast", true) && this.toastShown.compareAndSet(false, true)) {
                Toast.makeText(activity, "New Soul 已生效", 1).show();
            }
        }
        return objProceed;
    }

    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam moduleLoadedParam) {
        boolean z = !moduleLoadedParam.isSystemServer() && TARGET.equals(moduleLoadedParam.getProcessName());
        this.targetProcess = z;
        if (z) {
            info("module loaded; framework=" + getFrameworkName() + "; version=" + getFrameworkVersion() + "; API=" + getApiVersion());
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:104:0x03d8 A[Catch: all -> 0x03fa, TryCatch #12 {all -> 0x03fa, blocks: (B:102:0x03ce, B:104:0x03d8, B:106:0x03e0, B:109:0x03fc, B:111:0x0401, B:112:0x0408), top: B:164:0x03ce }] */
    /* JADX WARN: Code duplicated, block: B:106:0x03e0 A[Catch: all -> 0x03fa, LOOP:2: B:105:0x03de->B:106:0x03e0, LOOP_END, TryCatch #12 {all -> 0x03fa, blocks: (B:102:0x03ce, B:104:0x03d8, B:106:0x03e0, B:109:0x03fc, B:111:0x0401, B:112:0x0408), top: B:164:0x03ce }] */
    /* JADX WARN: Code duplicated, block: B:111:0x0401 A[Catch: all -> 0x03fa, TryCatch #12 {all -> 0x03fa, blocks: (B:102:0x03ce, B:104:0x03d8, B:106:0x03e0, B:109:0x03fc, B:111:0x0401, B:112:0x0408), top: B:164:0x03ce }] */
    /* JADX WARN: Instruction removed from duplicated block: B:104:0x03d8, please report this as an issue */
    public void onPackageReady(XposedModuleInterface.PackageReadyParam packageReadyParam) {
        String[][] strArr;
        int i;
        String str;
        String str2;
        Class<?> clsLoadClass;
        int i2;
        int i3;
        boolean z;
        int i4 = 1;
        final int i5 = 0;
        final int i6 = 1;
        if (!this.targetProcess || this.installed || !packageReadyParam.isFirstPackage() || !TARGET.equals(packageReadyParam.getPackageName())) {
            return;
        }
        synchronized (System.getProperties()) {
            try {
                String property = System.getProperty("newsoul.active.module");
                if (property != null) {
                    info("Skipping duplicate module; active=".concat(property));
                    return;
                }
                System.setProperty("newsoul.active.module", "dev.local.newsoul.public");
                info("active variant=dev.local.newsoul.public");
                ScheduledExecutorService scheduledExecutorService = V.a;
                V.l = getFrameworkName() + " / " + getFrameworkVersion() + " / API " + getApiVersion();
                try {
                    hook(Application.class.getDeclaredMethod("onCreate", null)).setId("new-soul/debug-init").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(13));
                } catch (Throwable th) {
                    V.i("debug.lifecycle", th);
                }
                AbstractC0025g0.a = this;
                ClassLoader classLoader = packageReadyParam.getClassLoader();
                if (!Q0.W) {
                    try {
                        Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.component.startup.main.MainActivity");
                        XposedInterface.HookBuilder id = hook(clsLoadClass2.getDeclaredMethod("onCreate", Bundle.class)).setId("new-soul/cold-launch-flag");
                        XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
                        id.setExceptionMode(exceptionMode).intercept(new y2(this, i5));
                        hook(clsLoadClass2.getDeclaredMethod("onNewIntent", Intent.class)).setId("new-soul/relaunch-flag").setExceptionMode(exceptionMode).intercept(new y2(this, i6));
                        Log.i(TAG, "new soul: startup intent hooks installed");
                    } catch (Throwable th2) {
                        Log.e(TAG, "new soul: startup intent hooks unavailable", th2);
                    }
                    try {
                        Class<?> clsLoadClass3 = classLoader.loadClass("tc.e$a");
                        Method declaredMethod = clsLoadClass3.getDeclaredMethod("j", String.class);
                        Method declaredMethod2 = clsLoadClass3.getDeclaredMethod("m", String.class);
                        Method declaredMethod3 = clsLoadClass3.getDeclaredMethod("onApplicationForeground", null);
                        Method declaredMethod4 = classLoader.loadClass("ea.a").getDeclaredMethod("a", null);
                        if (declaredMethod.getReturnType() != Boolean.TYPE || declaredMethod3.getReturnType() != Void.TYPE || !Context.class.isAssignableFrom(declaredMethod4.getReturnType())) {
                            throw new IllegalStateException("Unexpected Soul hot startup signature");
                        }
                        hook(declaredMethod).setId("new-soul/hot-splash-gate").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new D(declaredMethod4, this, i6));
                        deoptimize(declaredMethod2);
                        deoptimize(declaredMethod3);
                        Q0.X = true;
                        log(4, TAG, "new soul: hot splash hook installed (Soul 6.37.0)");
                        try {
                            Class<?> clsLoadClass4 = classLoader.loadClass("cn.soulapp.android.component.startup.main.MainScheduler");
                            Method declaredMethod5 = clsLoadClass4.getDeclaredMethod("v", null);
                            Method declaredMethod6 = clsLoadClass4.getDeclaredMethod("s", null);
                            Field declaredField = clsLoadClass4.getDeclaredField("a");
                            Class<?> returnType = declaredMethod5.getReturnType();
                            Class<?> cls = Void.TYPE;
                            if (returnType != cls || declaredMethod6.getReturnType() != cls) {
                                throw new IllegalStateException("Unexpected Soul startup method signature");
                            }
                            declaredField.setAccessible(true);
                            declaredMethod6.setAccessible(true);
                            hook(declaredMethod5).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0016e(declaredField, declaredMethod6, this, 3));
                            Q0.W = true;
                            Log.i(TAG, "new soul: splash hook installed (Soul 6.37.0)");
                            log(4, TAG, "new soul: splash hook installed (Soul 6.37.0)");
                        } catch (Throwable th3) {
                            Log.e(TAG, "new soul: splash hook unavailable; original startup retained", th3);
                            log(6, TAG, "new soul: splash hook unavailable", th3);
                        }
                    } catch (Throwable th4) {
                        log(6, TAG, "new soul: hot splash hook unavailable", th4);
                    }
                }
                ClassLoader classLoader2 = packageReadyParam.getClassLoader();
                Set set = AbstractC0036j.b;
                AbstractC0036j.g = this;
                try {
                    Class<?> clsLoadClass5 = classLoader2.loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
                    Class<?> clsLoadClass6 = classLoader2.loadClass("com.chad.library.adapter.base.BaseQuickAdapter");
                    final Class<?> clsLoadClass7 = classLoader2.loadClass("la.a");
                    final Class<?> clsLoadClass8 = classLoader2.loadClass("ia.a");
                    HashSet hashSet = new HashSet(Arrays.asList("addData", "setData", "setList", "replaceData", "setNewInstance", "setNewData", "setDiffNewData"));
                    Method[] declaredMethods = clsLoadClass6.getDeclaredMethods();
                    int length = declaredMethods.length;
                    int i7 = 0;
                    int i8 = 0;
                    while (i8 < length) {
                        final Method method = declaredMethods[i8];
                        if (hashSet.contains(method.getName()) && method.getReturnType() == Void.TYPE) {
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length != 0) {
                                if (method.getName().equals("setDiffNewData")) {
                                    try {
                                        i4 = 1;
                                        if (parameterTypes.length != 1) {
                                        }
                                        z = true;
                                    } catch (Throwable th5) {
                                        AbstractC0036j.c("feed data filter", th5);
                                        try {
                                            AbstractC0036j.f = classLoader2.loadClass("fa.a").getDeclaredMethod("b", null);
                                            AbstractC0036j.h(classLoader2, "jb.e", "a", "b", "e");
                                            AbstractC0036j.h(classLoader2, "mb.d", "a", "b", "f");
                                            AbstractC0036j.h(classLoader2, "lb.h", "b", "c", "a");
                                        } catch (Throwable th6) {
                                            AbstractC0036j.c("request hooks", th6);
                                        }
                                        strArr = new String[][]{new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent", "ad_feed"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView", "ad_detail"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView", "ad_video"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView", "ad_tag"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView", "ad_tag_web"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView", "ad_group"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView", "ad_planet"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView", "ad_planet"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView", "ad_me"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView", "ad_envelope"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView", "ad_notice"}, new String[]{"cn.soulapp.android.ad.base.view.SearchBannerAdView", "ad_search"}, new String[]{"cn.soulapp.android.ad.base.view.FloatAdView", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.FloatAdViewV2", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.DraggableFloatAdView", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.FloatEntranceView", "ad_float"}};
                                        for (i = 0; i < 16; i++) {
                                            String[] strArr2 = strArr[i];
                                            str = strArr2[0];
                                            str2 = strArr2[1];
                                            try {
                                                clsLoadClass = classLoader2.loadClass(str);
                                                if (View.class.isAssignableFrom(clsLoadClass)) {
                                                    throw new IllegalStateException("not a View");
                                                }
                                                for (Constructor<?> constructor : clsLoadClass.getDeclaredConstructors()) {
                                                    AbstractC0036j.g.hook(constructor).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(0, str2));
                                                }
                                                set.add(str2);
                                            } catch (Throwable th7) {
                                                AbstractC0036j.c(str, th7);
                                            }
                                        }
                                        AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.main.ad.AdPostViewHolder", "ad_feed", false);
                                        AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.main.ad.AdExpressViewHolder", "ad_feed", false);
                                        AbstractC0036j.g(classLoader2, "pi.k", "ad_conversation", true);
                                        AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.video.adapter.a", "ad_video", true);
                                        AbstractC0036j.f(classLoader2, "cn.soulapp.android.ad.dialog.InterstitialAdDialog");
                                        AbstractC0036j.f(classLoader2, "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog");
                                        try {
                                            AbstractC0036j.g.hook(View.class.getDeclaredMethod("setVisibility", Integer.TYPE)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(0));
                                        } catch (Throwable th8) {
                                            AbstractC0036j.c("view visibility", th8);
                                        }
                                        AbstractC0036j.e("ad controls installed; ready=" + set.size() + "/18 (excluding splash)");
                                        ClassLoader classLoader3 = packageReadyParam.getClassLoader();
                                        AbstractC0075t.f = this;
                                        try {
                                            Class<?> clsLoadClass9 = classLoader3.loadClass("cn.soulapp.imlib.msg.chat.DiceFingerMsg");
                                            AbstractC0075t.h = clsLoadClass9.getDeclaredConstructor(Integer.TYPE);
                                            AbstractC0075t.i = clsLoadClass9.getField("number");
                                            AbstractC0075t.f.hook(AbstractC0075t.h).setId("new-soul/game-message-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(4));
                                            Class<?> clsLoadClass10 = classLoader3.loadClass("cn.soulapp.android.component.chat.utils.y4");
                                            Field declaredField2 = clsLoadClass10.getDeclaredField("a");
                                            declaredField2.setAccessible(true);
                                            AbstractC0075t.a = AbstractC0075t.a(clsLoadClass10, declaredField2, "w0", true);
                                            boolean zA = AbstractC0075t.a(clsLoadClass10, declaredField2, "h1", false);
                                            AbstractC0075t.b = zA;
                                            String str3 = "chat games installed; dice=" + AbstractC0075t.a + " finger=" + zA;
                                            Log.i(TAG, str3);
                                            AbstractC0075t.f.log(4, TAG, str3);
                                        } catch (Throwable th9) {
                                            AbstractC0075t.f.log(6, TAG, "chat games unavailable", th9);
                                        }
                                        ClassLoader classLoader4 = packageReadyParam.getClassLoader();
                                        try {
                                            Class<?> clsLoadClass11 = classLoader4.loadClass("cn.soulapp.imlib.msg.ImMessage");
                                            Class<?> clsLoadClass12 = classLoader4.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
                                            Class<?> clsLoadClass13 = classLoader4.loadClass("cn.soulapp.imlib.ChatManager");
                                            Q0.l = classLoader4.loadClass("lf.b").getDeclaredMethod("b", null);
                                            Q0.m = classLoader4.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
                                            Q0.n = clsLoadClass11.getMethod("getChatMessage", null);
                                            Q0.p = clsLoadClass11.getMethod("getFrom", null);
                                            Q0.q = clsLoadClass11.getMethod("getTo", null);
                                            Q0.r = clsLoadClass11.getMethod("getMsgId", null);
                                            Q0.s = clsLoadClass11.getMethod("isAnonymityChat", null);
                                            Q0.o = clsLoadClass12.getMethod("getMsgType", null);
                                            Q0.t = clsLoadClass12.getMethod("getMsgContent", null);
                                            Q0.u = clsLoadClass12.getMethod("getStringTransExt", String.class);
                                            clsLoadClass13.getMethod("M", null);
                                            Q0.v = clsLoadClass13.getMethod("F", String.class);
                                            Q0.w = classLoader4.loadClass("cn.soulapp.imlib.Conversation").getMethod("getMessage", String.class);
                                            Q0.x = clsLoadClass13.getMethod("K", String.class);
                                            Class<?> clsLoadClass14 = classLoader4.loadClass("cn.soulapp.imlib.i0");
                                            Q0.y = clsLoadClass14.getMethod("v", null);
                                            Q0.z = clsLoadClass14.getMethod("w", null);
                                            Q0.A = classLoader4.loadClass("cn.soulapp.imlib.listener.MsgListener").getMethod("onRefreshUi", null);
                                            Q0.K(this, classLoader4, clsLoadClass11);
                                            Method declaredMethod7 = clsLoadClass13.getDeclaredMethod("O", List.class);
                                            XposedInterface.HookBuilder id2 = hook(declaredMethod7).setId("new-soul/chat-anti-recall");
                                            XposedInterface.ExceptionMode exceptionMode2 = XposedInterface.ExceptionMode.PROTECTIVE;
                                            id2.setExceptionMode(exceptionMode2).intercept(new C0000a(9));
                                            deoptimize(declaredMethod7);
                                            Method declaredMethod8 = classLoader4.loadClass("cn.soulapp.imlib.handler.RoamHandler").getDeclaredMethod("a", clsLoadClass11);
                                            hook(declaredMethod8).setId("new-soul/chat-anti-recall-roam").setExceptionMode(exceptionMode2).intercept(new C0000a(10));
                                            deoptimize(declaredMethod8);
                                            Q0.k = true;
                                            Log.i(TAG, "private chat anti-recall hooks installed");
                                        } catch (Throwable th10) {
                                            log(6, TAG, "private chat anti-recall unavailable", th10);
                                        }
                                        AbstractC0026g1.a(this, packageReadyParam.getClassLoader());
                                        Q0.x(this, packageReadyParam.getClassLoader());
                                        try {
                                            new F().h(this, packageReadyParam.getClassLoader());
                                        } catch (Throwable th11) {
                                            V.i("repeat", th11);
                                            Log.e(TAG, "repeat unavailable");
                                            log(6, TAG, "repeat unavailable");
                                        }
                                        Q0.z(this, packageReadyParam.getClassLoader());
                                        Q0.E(this, packageReadyParam.getClassLoader());
                                        Q0.D(this, packageReadyParam.getClassLoader());
                                        Q0.A(this, packageReadyParam.getClassLoader());
                                        Q0.B(this, packageReadyParam.getClassLoader());
                                        Q0.C(this, packageReadyParam.getClassLoader());
                                        AbstractC0081u1.g(this, packageReadyParam.getClassLoader());
                                        AbstractC0061p0.e(this, packageReadyParam.getClassLoader());
                                        B2.c(this, packageReadyParam.getClassLoader());
                                        Q0.F(this, packageReadyParam.getClassLoader());
                                        S.a = this;
                                        AbstractC0056o.c(this, packageReadyParam.getClassLoader());
                                        packageReadyParam.getClassLoader();
                                        try {
                                            hook(Activity.class.getDeclaredMethod("onResume", null)).setId("soul-demo/activity-onResume").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0094x2(this, 0));
                                            this.installed = true;
                                            info("hook installed: android.app.Activity.onResume");
                                            installSettingsHook(packageReadyParam.getClassLoader());
                                        } catch (Throwable th12) {
                                            Log.e(TAG, "hook installation failed", th12);
                                            log(6, TAG, "hook installation failed", th12);
                                            return;
                                        }
                                    }
                                } else {
                                    i4 = 1;
                                }
                                final int length2 = parameterTypes.length - i4;
                                int i9 = i8;
                                final boolean zIsAssignableFrom = Collection.class.isAssignableFrom(parameterTypes[length2]);
                                final boolean z2 = parameterTypes[length2] == Object.class;
                                if (zIsAssignableFrom || z2) {
                                    i3 = i9;
                                    length = length;
                                    final Class<?> cls2 = clsLoadClass5;
                                    clsLoadClass5 = clsLoadClass5;
                                    declaredMethods = declaredMethods;
                                    hashSet = hashSet;
                                    AbstractC0036j.g.hook(method).setId("new-soul/feed-data/" + method.getName() + "/" + i7).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept( new c(cls2, length2, zIsAssignableFrom, clsLoadClass7, clsLoadClass8, method, z2));
                                    z = true;
                                    i7++;
                                } else {
                                    i3 = i9;
                                    z = true;
                                }
                            }
                            i3 = i8;
                            z = true;
                        } else {
                            i3 = i8;
                            z = true;
                        }
                        i8 = i3 + 1;
                        hashSet = hashSet;
                        length = length;
                        declaredMethods = declaredMethods;
                        clsLoadClass5 = clsLoadClass5;
                    }
                    if (i7 == 0) {
                        throw new IllegalStateException("no adapter mutation methods");
                    }
                    set.add("ad_feed");
                    AbstractC0036j.e("feed data filter installed; methods=" + i7);
                    AbstractC0036j.f = classLoader2.loadClass("fa.a").getDeclaredMethod("b", null);
                    AbstractC0036j.h(classLoader2, "jb.e", "a", "b", "e");
                    AbstractC0036j.h(classLoader2, "mb.d", "a", "b", "f");
                    AbstractC0036j.h(classLoader2, "lb.h", "b", "c", "a");
                    strArr = new String[][]{new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent", "ad_feed"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView", "ad_detail"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView", "ad_video"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView", "ad_tag"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView", "ad_tag_web"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView", "ad_group"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView", "ad_planet"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView", "ad_planet"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView", "ad_me"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView", "ad_envelope"}, new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView", "ad_notice"}, new String[]{"cn.soulapp.android.ad.base.view.SearchBannerAdView", "ad_search"}, new String[]{"cn.soulapp.android.ad.base.view.FloatAdView", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.FloatAdViewV2", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.DraggableFloatAdView", "ad_float"}, new String[]{"cn.soulapp.android.ad.base.view.FloatEntranceView", "ad_float"}};
                    i = 0;
                    while (i < 16) {
                        String[] strArr3 = strArr[i];
                        str = strArr3[0];
                        str2 = strArr3[1];
                        clsLoadClass = classLoader2.loadClass(str);
                        if (!View.class.isAssignableFrom(clsLoadClass)) {
                            throw new IllegalStateException("not a View");
                        }
                        try {
                            Constructor<?>[] declaredConstructors = clsLoadClass.getDeclaredConstructors();
                            int constructorIdx = 0;
                            while (constructorIdx < declaredConstructors.length) {
                                Constructor<?> constructor = declaredConstructors[constructorIdx];
                                AbstractC0036j.g.hook(constructor).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(0, str2));
                                constructorIdx++;
                            }
                            set.add(str2);
                        } catch (Throwable thx) {
                            AbstractC0036j.c(str, thx);
                        }
                        i++;
                    }
                    AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.main.ad.AdPostViewHolder", "ad_feed", false);
                    AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.main.ad.AdExpressViewHolder", "ad_feed", false);
                    AbstractC0036j.g(classLoader2, "pi.k", "ad_conversation", true);
                    AbstractC0036j.g(classLoader2, "cn.soulapp.android.component.square.video.adapter.a", "ad_video", true);
                    AbstractC0036j.f(classLoader2, "cn.soulapp.android.ad.dialog.InterstitialAdDialog");
                    AbstractC0036j.f(classLoader2, "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog");
                    AbstractC0036j.g.hook(View.class.getDeclaredMethod("setVisibility", Integer.TYPE)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(0));
                    AbstractC0036j.e("ad controls installed; ready=" + set.size() + "/18 (excluding splash)");
                    ClassLoader classLoader5 = packageReadyParam.getClassLoader();
                    AbstractC0075t.f = this;
                    Class<?> clsLoadClass15 = classLoader5.loadClass("cn.soulapp.imlib.msg.chat.DiceFingerMsg");
                    AbstractC0075t.h = clsLoadClass15.getDeclaredConstructor(Integer.TYPE);
                    AbstractC0075t.i = clsLoadClass15.getField("number");
                    AbstractC0075t.f.hook(AbstractC0075t.h).setId("new-soul/game-message-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(4));
                    Class<?> clsLoadClass16 = classLoader5.loadClass("cn.soulapp.android.component.chat.utils.y4");
                    Field declaredField3 = clsLoadClass16.getDeclaredField("a");
                    declaredField3.setAccessible(true);
                    AbstractC0075t.a = AbstractC0075t.a(clsLoadClass16, declaredField3, "w0", true);
                    boolean zA2 = AbstractC0075t.a(clsLoadClass16, declaredField3, "h1", false);
                    AbstractC0075t.b = zA2;
                    String str4 = "chat games installed; dice=" + AbstractC0075t.a + " finger=" + zA2;
                    Log.i(TAG, str4);
                    AbstractC0075t.f.log(4, TAG, str4);
                    ClassLoader classLoader6 = packageReadyParam.getClassLoader();
                    Class<?> clsLoadClass17 = classLoader6.loadClass("cn.soulapp.imlib.msg.ImMessage");
                    Class<?> clsLoadClass18 = classLoader6.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
                    Class<?> clsLoadClass19 = classLoader6.loadClass("cn.soulapp.imlib.ChatManager");
                    Q0.l = classLoader6.loadClass("lf.b").getDeclaredMethod("b", null);
                    Q0.m = classLoader6.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
                    Q0.n = clsLoadClass17.getMethod("getChatMessage", null);
                    Q0.p = clsLoadClass17.getMethod("getFrom", null);
                    Q0.q = clsLoadClass17.getMethod("getTo", null);
                    Q0.r = clsLoadClass17.getMethod("getMsgId", null);
                    Q0.s = clsLoadClass17.getMethod("isAnonymityChat", null);
                    Q0.o = clsLoadClass18.getMethod("getMsgType", null);
                    Q0.t = clsLoadClass18.getMethod("getMsgContent", null);
                    Q0.u = clsLoadClass18.getMethod("getStringTransExt", String.class);
                    clsLoadClass19.getMethod("M", null);
                    Q0.v = clsLoadClass19.getMethod("F", String.class);
                    Q0.w = classLoader6.loadClass("cn.soulapp.imlib.Conversation").getMethod("getMessage", String.class);
                    Q0.x = clsLoadClass19.getMethod("K", String.class);
                    Class<?> clsLoadClass110 = classLoader6.loadClass("cn.soulapp.imlib.i0");
                    Q0.y = clsLoadClass110.getMethod("v", null);
                    Q0.z = clsLoadClass110.getMethod("w", null);
                    Q0.A = classLoader6.loadClass("cn.soulapp.imlib.listener.MsgListener").getMethod("onRefreshUi", null);
                    Q0.K(this, classLoader6, clsLoadClass17);
                    Method declaredMethod9 = clsLoadClass19.getDeclaredMethod("O", List.class);
                    XposedInterface.HookBuilder id3 = hook(declaredMethod9).setId("new-soul/chat-anti-recall");
                    XposedInterface.ExceptionMode exceptionMode3 = XposedInterface.ExceptionMode.PROTECTIVE;
                    id3.setExceptionMode(exceptionMode3).intercept(new C0000a(9));
                    deoptimize(declaredMethod9);
                    Method declaredMethod10 = classLoader6.loadClass("cn.soulapp.imlib.handler.RoamHandler").getDeclaredMethod("a", clsLoadClass17);
                    hook(declaredMethod10).setId("new-soul/chat-anti-recall-roam").setExceptionMode(exceptionMode3).intercept(new C0000a(10));
                    deoptimize(declaredMethod10);
                    Q0.k = true;
                    Log.i(TAG, "private chat anti-recall hooks installed");
                    AbstractC0026g1.a(this, packageReadyParam.getClassLoader());
                    Q0.x(this, packageReadyParam.getClassLoader());
                    new F().h(this, packageReadyParam.getClassLoader());
                    Q0.z(this, packageReadyParam.getClassLoader());
                    Q0.E(this, packageReadyParam.getClassLoader());
                    Q0.D(this, packageReadyParam.getClassLoader());
                    Q0.A(this, packageReadyParam.getClassLoader());
                    Q0.B(this, packageReadyParam.getClassLoader());
                    Q0.C(this, packageReadyParam.getClassLoader());
                    AbstractC0081u1.g(this, packageReadyParam.getClassLoader());
                    AbstractC0061p0.e(this, packageReadyParam.getClassLoader());
                    B2.c(this, packageReadyParam.getClassLoader());
                    Q0.F(this, packageReadyParam.getClassLoader());
                    S.a = this;
                    AbstractC0056o.c(this, packageReadyParam.getClassLoader());
                    packageReadyParam.getClassLoader();
                    hook(Activity.class.getDeclaredMethod("onResume", null)).setId("soul-demo/activity-onResume").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0094x2(this, 0));
                    this.installed = true;
                    info("hook installed: android.app.Activity.onResume");
                    installSettingsHook(packageReadyParam.getClassLoader());
                } catch (Throwable th13) {
                }
            } catch (Throwable th14) {
                throw th14;
            }
        }
    }
}
