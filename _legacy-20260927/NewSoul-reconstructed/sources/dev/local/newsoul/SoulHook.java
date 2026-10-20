/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.TextWatcher
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 *  io.github.libxposed.api.XposedModuleInterface$ModuleLoadedParam
 *  io.github.libxposed.api.XposedModuleInterface$PackageReadyParam
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.B2;
import dev.local.newsoul.D;
import dev.local.newsoul.E1;
import dev.local.newsoul.F;
import dev.local.newsoul.F1;
import dev.local.newsoul.I1;
import dev.local.newsoul.M1;
import dev.local.newsoul.Q0;
import dev.local.newsoul.S;
import dev.local.newsoul.V;
import dev.local.newsoul.a2_0;
import dev.local.newsoul.a_0;
import dev.local.newsoul.c_0;
import dev.local.newsoul.e_0;
import dev.local.newsoul.f_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.m_0;
import dev.local.newsoul.o_0;
import dev.local.newsoul.p0_0;
import dev.local.newsoul.t_0;
import dev.local.newsoul.u1_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.x2_0;
import dev.local.newsoul.y2_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SoulHook
extends XposedModule {
    private static final String TAG = "SoulHook102";
    private static final String TARGET = "cn.soulapp.android";
    static boolean settingsHookReady;
    private boolean installed;
    private boolean targetProcess;
    private final AtomicBoolean toastShown = new AtomicBoolean();

    public static /* synthetic */ void a(Activity activity) {
        SoulHook.lambda$installSettingsHook$1(activity);
    }

    public static /* synthetic */ Object b(SoulHook soulHook, XposedInterface.Chain chain) {
        return soulHook.lambda$installSettingsHook$2(chain);
    }

    public static /* synthetic */ Object c(SoulHook soulHook, XposedInterface.Chain chain) {
        return soulHook.lambda$onPackageReady$0(chain);
    }

    private void info(String string) {
        Log.i((String)TAG, (String)string);
        this.log(4, TAG, string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void installSettingsHook(ClassLoader object) {
        Throwable throwable2;
        block3: {
            try {
                object = ((ClassLoader)object).loadClass("cn.soulapp.android.h5.activity.H5Activity").getDeclaredMethod("m1", Boolean.TYPE);
                if (Modifier.isPrivate(((Method)object).getModifiers())) {
                    object = this.hook((Executable)object).setId("soul-demo/private-h5-m1").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    x2_0 x2_02 = new x2_0(this, 1);
                    object.intercept((XposedInterface.Hooker)x2_02);
                    this.info("private hook installed: H5Activity.m1(boolean)");
                    settingsHookReady = true;
                    return;
                }
            }
            catch (Throwable throwable2) {
                break block3;
            }
            object = new IllegalStateException("Unexpected H5Activity.m1 signature");
            throw object;
        }
        Log.e((String)TAG, (String)"Soul settings hook unavailable; check app version", (Throwable)throwable2);
        this.log(6, TAG, "Soul settings hook unavailable; check app version", throwable2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void lambda$installSettingsHook$1(Activity object) {
        Throwable throwable2;
        block5: {
            if (object.isFinishing()) return;
            if (object.isDestroyed()) return;
            try {
                WeakHashMap weakHashMap = w2.a;
                if (weakHashMap.containsKey(object)) {
                    return;
                }
                View view = w2.k(object, "topic_title");
                Object object2 = w2.k(object, "rlWebRoot");
                View view2 = w2.k(object, "contentLayout");
                View view3 = w2.k(object, "titleLay");
                if (view instanceof TextView && object2 instanceof RelativeLayout && view2 != null && view3 != null && view2.getParent() == object2 && view2.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    weakHashMap.put(object, Boolean.TRUE);
                    weakHashMap = (TextView)view;
                    view = (RelativeLayout)object2;
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams)view2.getLayoutParams());
                    object2 = new E1((Activity)object, view2, (TextView)weakHashMap);
                    F1 f1 = new F1(view2, object2, 0);
                    view2.addOnAttachStateChangeListener((View.OnAttachStateChangeListener)f1);
                    if (view2.isAttachedToWindow()) {
                        view2.post((Runnable)object2);
                    }
                    object2 = new I1((Activity)object, (RelativeLayout)view, (TextView)weakHashMap, view2, layoutParams, new boolean[]{false}, view3);
                    object = new a2_0((Runnable)object2, 0);
                    weakHashMap.addTextChangedListener((TextWatcher)object);
                    view2 = view.getViewTreeObserver();
                    object = new M1(0, object2);
                    view2.addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)object);
                    ((I1)object2).run();
                    return;
                }
            }
            catch (Throwable throwable2) {
                break block5;
            }
            Log.w((String)TAG, (String)"Settings layout does not match; leaving page unchanged");
            return;
        }
        Log.e((String)TAG, (String)"Settings observation failed", (Throwable)throwable2);
    }

    private /* synthetic */ Object lambda$installSettingsHook$2(XposedInterface.Chain chain) {
        Object object = chain.proceed();
        chain = (Activity)chain.getThisObject();
        chain.getWindow().getDecorView().post((Runnable)new m_0((Activity)chain, 5));
        this.info("private hook hit: H5Activity.m1(boolean)");
        return object;
    }

    private Object lambda$onPackageReady$0(XposedInterface.Chain chain) {
        Object object = chain.proceed();
        if (TARGET.equals((chain = (Activity)chain.getThisObject()).getPackageName())) {
            V.h((Context)chain);
            V.d("page.resume", chain.getClass().getName());
            Q0.f((Activity)chain);
            WeakHashMap weakHashMap = w2.a;
            weakHashMap = chain.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
            if (weakHashMap.getBoolean("page_log", true)) {
                this.info("onResume: ".concat(chain.getClass().getName()));
            }
            if (weakHashMap.getBoolean("startup_toast", true) && this.toastShown.compareAndSet(false, true)) {
                Toast.makeText((Context)chain, (CharSequence)"New Soul \u5df2\u751f\u6548", (int)1).show();
            }
        }
        return object;
    }

    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam object) {
        boolean bl = !object.isSystemServer() && TARGET.equals(object.getProcessName());
        this.targetProcess = bl;
        if (bl) {
            object = this.getFrameworkName();
            String string = this.getFrameworkVersion();
            int n = this.getApiVersion();
            StringBuilder stringBuilder = new StringBuilder("module loaded; framework=");
            stringBuilder.append((String)object);
            stringBuilder.append("; version=");
            stringBuilder.append(string);
            stringBuilder.append("; API=");
            stringBuilder.append(n);
            this.info(stringBuilder.toString());
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onPackageReady(XposedModuleInterface.PackageReadyParam var1_1) {
        block53: {
            block55: {
                block51: {
                    block49: {
                        if (this.targetProcess == false) return;
                        if (this.installed != false) return;
                        if (var1_1.isFirstPackage() == false) return;
                        if (!"cn.soulapp.android".equals(var1_1.getPackageName())) {
                            return;
                        }
                        var8_4 = System.getProperties();
                        synchronized (var8_4) {
                            var9_14 = System.getProperty("newsoul.active.module");
                            if (var9_14 != null) {
                                this.info("Skipping duplicate module; active=".concat((String)var9_14));
                                return;
                            }
                            System.setProperty("newsoul.active.module", "dev.local.newsoul.public");
                            // MONITOREXIT @DISABLED, blocks:[0, 22] lbl16 : MonitorExitStatement: MONITOREXIT : var8_4
                            this.info("active variant=dev.local.newsoul.public");
                            var8_4 = V.a;
                        }
                        var8_4 = this.getFrameworkName();
                        var10_17 = this.getFrameworkVersion();
                        var2_18 = this.getApiVersion();
                        var9_14 = new StringBuilder();
                        var9_14.append((String)var8_4);
                        var9_14.append(" / ");
                        var9_14.append((String)var10_17);
                        var9_14.append(" / API ");
                        var9_14.append(var2_18);
                        V.l = var9_14.toString();
                        try {
                            var8_4 = this.hook(Application.class.getDeclaredMethod("onCreate", null)).setId("new-soul/debug-init").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                            var9_14 = new a_0(13);
                            var8_4.intercept(var9_14);
                        }
                        catch (Throwable var8_5) {
                            V.i("debug.lifecycle", var8_5);
                        }
                        g0_0.a = this;
                        var8_4 = var1_1.getClassLoader();
                        if (!Q0.W) {
                            block50: {
                                block48: {
                                    block47: {
                                        block46: {
                                            try {
                                                var9_14 = var8_4.loadClass("cn.soulapp.android.component.startup.main.MainActivity");
                                                var11_19 /* !! */  = this.hook(var9_14.getDeclaredMethod("onCreate", new Class[]{Bundle.class})).setId("new-soul/cold-launch-flag");
                                                var10_17 = XposedInterface.ExceptionMode.PROTECTIVE;
                                                var11_19 /* !! */  = var11_19 /* !! */ .setExceptionMode((XposedInterface.ExceptionMode)var10_17);
                                                var12_20 = new y2_0(this, 0);
                                                var11_19 /* !! */ .intercept((XposedInterface.Hooker)var12_20);
                                                var9_14 = this.hook(var9_14.getDeclaredMethod("onNewIntent", new Class[]{Intent.class})).setId("new-soul/relaunch-flag").setExceptionMode((XposedInterface.ExceptionMode)var10_17);
                                                var10_17 = new y2_0(this, 1);
                                                var9_14.intercept((XposedInterface.Hooker)var10_17);
                                                Log.i((String)"SoulHook102", (String)"new soul: startup intent hooks installed");
                                            }
                                            catch (Throwable var9_15) {
                                                Log.e((String)"SoulHook102", (String)"new soul: startup intent hooks unavailable", (Throwable)var9_15);
                                            }
                                            try {
                                                var10_17 = var8_4.loadClass("tc.e$a");
                                                var12_20 = var10_17.getDeclaredMethod("j", new Class[]{String.class});
                                                var9_14 = var10_17.getDeclaredMethod("m", new Class[]{String.class});
                                                var10_17 = var10_17.getDeclaredMethod("onApplicationForeground", null);
                                                var11_19 /* !! */  = var8_4.loadClass("ea.a").getDeclaredMethod("a", null);
                                                if (var12_20.getReturnType() != Boolean.TYPE || var10_17.getReturnType() != Void.TYPE || !Context.class.isAssignableFrom(var11_19 /* !! */ .getReturnType())) break block46;
                                                var13_21 = this.hook((Executable)var12_20).setId("new-soul/hot-splash-gate").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                                                var12_20 = new D(var11_19 /* !! */ , (Object)this, 1);
                                                var13_21.intercept((XposedInterface.Hooker)var12_20);
                                                this.deoptimize((Executable)var9_14);
                                                this.deoptimize((Executable)var10_17);
                                                Q0.X = true;
                                                this.log(4, "SoulHook102", "new soul: hot splash hook installed (Soul 6.37.0)");
                                            }
                                            catch (Throwable var9_16) {
                                                break block47;
                                            }
                                        }
                                        var9_14 = new IllegalStateException("Unexpected Soul hot startup signature");
                                        throw var9_14;
                                    }
                                    this.log(6, "SoulHook102", "new soul: hot splash hook unavailable", var9_16);
                                    try {
                                        var9_14 = var8_4.loadClass("cn.soulapp.android.component.startup.main.MainScheduler");
                                        var10_17 = var9_14.getDeclaredMethod("v", null);
                                        var8_4 = var9_14.getDeclaredMethod("s", null);
                                        var9_14 = var9_14.getDeclaredField("a");
                                        var11_19 /* !! */  = var10_17.getReturnType();
                                        var12_20 = Void.TYPE;
                                        if (var11_19 /* !! */  != var12_20 || var8_4.getReturnType() != var12_20) break block48;
                                        var9_14.setAccessible(true);
                                        var8_4.setAccessible(true);
                                        var11_19 /* !! */  = this.hook((Executable)var10_17).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                                        var10_17 = new e_0((AnnotatedElement)var9_14, (AccessibleObject)var8_4, (Object)this, 3);
                                        var11_19 /* !! */ .intercept((XposedInterface.Hooker)var10_17);
                                        Q0.W = true;
                                        Log.i((String)"SoulHook102", (String)"new soul: splash hook installed (Soul 6.37.0)");
                                        this.log(4, "SoulHook102", "new soul: splash hook installed (Soul 6.37.0)");
                                        break block49;
                                    }
                                    catch (Throwable var8_6) {
                                        break block50;
                                    }
                                }
                                var8_4 = new IllegalStateException("Unexpected Soul startup method signature");
                                throw var8_4;
                            }
                            Log.e((String)"SoulHook102", (String)"new soul: splash hook unavailable; original startup retained", (Throwable)var8_6);
                            this.log(6, "SoulHook102", "new soul: splash hook unavailable", var8_6);
                        }
                    }
                    var12_20 = var1_1.getClassLoader();
                    var11_19 /* !! */  = j_0.b;
                    j_0.g = this;
                    var10_17 = var12_20.loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
                    var8_4 = var12_20.loadClass("com.chad.library.adapter.base.BaseQuickAdapter");
                    var13_21 = var12_20.loadClass("la.a");
                    var14_22 = var12_20.loadClass("ia.a");
                    var9_14 = new HashSet(Arrays.asList(new String[]{"addData", "setData", "setList", "replaceData", "setNewInstance", "setNewData", "setDiffNewData"}));
                    var8_4 = var8_4.getDeclaredMethods();
                    var2_18 = ((Method[])var8_4).length;
                    var4_23 = 0;
                    break block51;
                    {
                        catch (Throwable var1_2) {}
                        throw var1_2;
                    }
                }
                for (var3_24 = 0; var3_24 < var2_18; ++var3_24) {
                    block52: {
                        var15_28 = var8_4[var3_24];
                        if (!var9_14.contains(var15_28.getName()) || var15_28.getReturnType() != Void.TYPE || ((Object[])(var16_29 = var15_28.getParameterTypes())).length == 0) continue;
                        var6_26 = var15_28.getName().equals("setDiffNewData");
                        if (!var6_26) break block52;
                        try {
                            var5_25 = ((Object[])var16_29).length;
                            if (var5_25 != 1) {
                                continue;
                            }
                        }
                        catch (Throwable var8_7) {
                            break block53;
                        }
                    }
                    var5_25 = ((Object[])var16_29).length - 1;
                    var7_27 = Collection.class.isAssignableFrom((Class<?>)var16_29[var5_25]);
                    var6_26 = var16_29[var5_25] == Object.class;
                    if (!var7_27 && !var6_26) continue;
                    var18_31 = j_0.g.hook((Executable)var15_28);
                    var16_29 = var15_28.getName();
                    var17_30 = new StringBuilder();
                    var17_30.append("new-soul/feed-data/");
                    var17_30.append((String)var16_29);
                    var17_30.append("/");
                    var17_30.append(var4_23);
                    var16_29 = var18_31.setId(var17_30.toString()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    var17_30 = new c_0((Class)var10_17, var5_25, var7_27, (Class)var13_21, (Class)var14_22, (Method)var15_28, var6_26);
                    var16_29.intercept((XposedInterface.Hooker)var17_30);
                    ++var4_23;
                }
                if (var4_23 == 0) break block55;
                try {
                    var11_19 /* !! */ .add("ad_feed");
                    var8_4 = new StringBuilder();
                    var8_4.append("feed data filter installed; methods=");
                    var8_4.append(var4_23);
                    j_0.e(var8_4.toString());
                }
                catch (Throwable var8_8) {
                    break block53;
                }
            }
            var8_4 = new IllegalStateException("no adapter mutation methods");
            throw var8_4;
        }
        j_0.c("feed data filter", (Throwable)var8_4);
        try {
            j_0.f = var12_20.loadClass("fa.a").getDeclaredMethod("b", null);
            j_0.h((ClassLoader)var12_20, "jb.e", "a", "b", "e");
            j_0.h((ClassLoader)var12_20, "mb.d", "a", "b", "f");
            j_0.h((ClassLoader)var12_20, "lb.h", "b", "c", "a");
        }
        catch (Throwable var8_9) {
            j_0.c("request hooks", var8_9);
        }
        var16_29 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent", "ad_feed"};
        var9_14 = new XposedInterface.HookBuilder[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView", "ad_detail"};
        var21_32 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView", "ad_video"};
        var15_28 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView", "ad_tag"};
        var18_31 = new XposedInterface.HookBuilder[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView", "ad_tag_web"};
        var8_4 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView", "ad_planet"};
        var14_22 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView", "ad_me"};
        var20_33 = new String[]{"cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView", "ad_envelope"};
        var22_34 = new String[]{"cn.soulapp.android.ad.base.view.SearchBannerAdView", "ad_search"};
        var10_17 = new String[]{"cn.soulapp.android.ad.base.view.FloatAdView", "ad_float"};
        var13_21 = new XposedInterface.HookBuilder[]{"cn.soulapp.android.ad.base.view.FloatAdViewV2", "ad_float"};
        var23_35 = new String[]{"cn.soulapp.android.ad.base.view.DraggableFloatAdView", "ad_float"};
        var19_36 = new String[]{"cn.soulapp.android.ad.base.view.FloatEntranceView", "ad_float"};
        var2_18 = 0;
        while (true) {
            if (var2_18 >= 16) ** GOTO lbl222
            var24_37 /* !! */  = (new String[][]{var16_29, var9_14, var21_32, var15_28, var18_31, {"cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView", "ad_group"}, var8_4, {"cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView", "ad_planet"}, var14_22, var20_33, {"cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView", "ad_notice"}, var22_34, var10_17, var13_21, var23_35, var19_36})[var2_18];
            var17_30 = var24_37 /* !! */ [0];
            var24_37 /* !! */  = var24_37 /* !! */ [1];
            try {
                block54: {
                    var25_39 = var12_20.loadClass((String)var17_30);
                    if (View.class.isAssignableFrom((Class<?>)var25_39)) {
                        var25_39 = var25_39.getDeclaredConstructors();
                        var4_23 = var25_39.length;
                        break block54;
                    } else {
                        var24_37 /* !! */  = new IllegalStateException("not a View");
                        throw var24_37 /* !! */ ;
                    }
lbl222:
                    // 1 sources

                    j_0.g((ClassLoader)var12_20, "cn.soulapp.android.component.square.main.ad.AdPostViewHolder", "ad_feed", false);
                    j_0.g((ClassLoader)var12_20, "cn.soulapp.android.component.square.main.ad.AdExpressViewHolder", "ad_feed", false);
                    j_0.g((ClassLoader)var12_20, "pi.k", "ad_conversation", true);
                    j_0.g((ClassLoader)var12_20, "cn.soulapp.android.component.square.video.adapter.a", "ad_video", true);
                    j_0.f((ClassLoader)var12_20, "cn.soulapp.android.ad.dialog.InterstitialAdDialog");
                    j_0.f((ClassLoader)var12_20, "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog");
                    try {
                        var9_14 = j_0.g.hook((Executable)View.class.getDeclaredMethod("setVisibility", new Class[]{Integer.TYPE})).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                        var8_4 = new a_0(0);
                        var9_14.intercept((XposedInterface.Hooker)var8_4);
                    }
                    catch (Throwable var8_10) {
                        j_0.c("view visibility", var8_10);
                    }
                    var2_18 = var11_19 /* !! */ .size();
                    var8_4 = new StringBuilder("ad controls installed; ready=");
                    var8_4.append(var2_18);
                    var8_4.append("/18 (excluding splash)");
                    j_0.e(var8_4.toString());
                    var8_4 = var1_1.getClassLoader();
                    t_0.f = this;
                    try {
                        var9_14 = var8_4.loadClass("cn.soulapp.imlib.msg.chat.DiceFingerMsg");
                        t_0.h = var9_14.getDeclaredConstructor(new Class[]{Integer.TYPE});
                        t_0.i = var9_14.getField("number");
                        var10_17 = t_0.f.hook((Executable)t_0.h).setId("new-soul/game-message-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                        var9_14 = new a_0(4);
                        var10_17.intercept((XposedInterface.Hooker)var9_14);
                        var8_4 = var8_4.loadClass("cn.soulapp.android.component.chat.utils.y4");
                        var9_14 = var8_4.getDeclaredField("a");
                        var9_14.setAccessible(true);
                        t_0.a = t_0.a((Class)var8_4, (Field)var9_14, "w0", true);
                        t_0.b = var7_27 = t_0.a((Class)var8_4, (Field)var9_14, "h1", false);
                        var6_26 = t_0.a;
                        var8_4 = new StringBuilder("chat games installed; dice=");
                        var8_4.append(var6_26);
                        var8_4.append(" finger=");
                        var8_4.append(var7_27);
                        var8_4 = var8_4.toString();
                        Log.i((String)"SoulHook102", (String)var8_4);
                        t_0.f.log(4, "SoulHook102", (String)var8_4);
                    }
                    catch (Throwable var8_11) {
                        t_0.f.log(6, "SoulHook102", "chat games unavailable", var8_11);
                    }
                    var9_14 = var1_1.getClassLoader();
                    try {
                        var8_4 = var9_14.loadClass("cn.soulapp.imlib.msg.ImMessage");
                        var11_19 /* !! */  = var9_14.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
                        var10_17 = var9_14.loadClass("cn.soulapp.imlib.ChatManager");
                        Q0.l = var9_14.loadClass("lf.b").getDeclaredMethod("b", null);
                        Q0.m = var9_14.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
                        Q0.n = var8_4.getMethod("getChatMessage", null);
                        Q0.p = var8_4.getMethod("getFrom", null);
                        Q0.q = var8_4.getMethod("getTo", null);
                        Q0.r = var8_4.getMethod("getMsgId", null);
                        Q0.s = var8_4.getMethod("isAnonymityChat", null);
                        Q0.o = var11_19 /* !! */ .getMethod("getMsgType", null);
                        Q0.t = var11_19 /* !! */ .getMethod("getMsgContent", null);
                        Q0.u = var11_19 /* !! */ .getMethod("getStringTransExt", new Class[]{String.class});
                        var10_17.getMethod("M", null);
                        Q0.v = var10_17.getMethod("F", new Class[]{String.class});
                        Q0.w = var9_14.loadClass("cn.soulapp.imlib.Conversation").getMethod("getMessage", new Class[]{String.class});
                        Q0.x = var10_17.getMethod("K", new Class[]{String.class});
                        var11_19 /* !! */  = var9_14.loadClass("cn.soulapp.imlib.i0");
                        Q0.y = var11_19 /* !! */ .getMethod("v", null);
                        Q0.z = var11_19 /* !! */ .getMethod("w", null);
                        Q0.A = var9_14.loadClass("cn.soulapp.imlib.listener.MsgListener").getMethod("onRefreshUi", null);
                        Q0.K(this, (ClassLoader)var9_14, (Class)var8_4);
                        var11_19 /* !! */  = var10_17.getDeclaredMethod("O", new Class[]{List.class});
                        var12_20 = this.hook((Executable)var11_19 /* !! */ ).setId("new-soul/chat-anti-recall");
                        var10_17 = XposedInterface.ExceptionMode.PROTECTIVE;
                        var13_21 = var12_20.setExceptionMode((XposedInterface.ExceptionMode)var10_17);
                        var12_20 = new a_0(9);
                        var13_21.intercept((XposedInterface.Hooker)var12_20);
                        this.deoptimize((Executable)var11_19 /* !! */ );
                        var8_4 = var9_14.loadClass("cn.soulapp.imlib.handler.RoamHandler").getDeclaredMethod("a", new Class[]{var8_4});
                        var9_14 = this.hook((Executable)var8_4).setId("new-soul/chat-anti-recall-roam").setExceptionMode((XposedInterface.ExceptionMode)var10_17);
                        var10_17 = new a_0(10);
                        var9_14.intercept((XposedInterface.Hooker)var10_17);
                        this.deoptimize((Executable)var8_4);
                        Q0.k = true;
                        Log.i((String)"SoulHook102", (String)"private chat anti-recall hooks installed");
                    }
                    catch (Throwable var8_12) {
                        this.log(6, "SoulHook102", "private chat anti-recall unavailable", var8_12);
                    }
                    g1_0.a(this, var1_1.getClassLoader());
                    Q0.x(this, var1_1.getClassLoader());
                    var9_14 = var1_1.getClassLoader();
                    try {
                        var8_4 = new F();
                        var8_4.h(this, (ClassLoader)var9_14);
                    }
                    catch (Throwable var8_13) {
                        V.i("repeat", var8_13);
                        Log.e((String)"SoulHook102", (String)"repeat unavailable");
                        this.log(6, "SoulHook102", "repeat unavailable");
                    }
                    Q0.z(this, var1_1.getClassLoader());
                    Q0.E(this, var1_1.getClassLoader());
                    Q0.D(this, var1_1.getClassLoader());
                    Q0.A(this, var1_1.getClassLoader());
                    Q0.B(this, var1_1.getClassLoader());
                    Q0.C(this, var1_1.getClassLoader());
                    u1_0.g(this, var1_1.getClassLoader());
                    p0_0.e(this, var1_1.getClassLoader());
                    B2.c(this, var1_1.getClassLoader());
                    Q0.F(this, var1_1.getClassLoader());
                    S.a = this;
                    o_0.c(this, var1_1.getClassLoader());
                    var1_1.getClassLoader();
                    try {
                        var9_14 = this.hook(Activity.class.getDeclaredMethod("onResume", null)).setId("soul-demo/activity-onResume").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                        var8_4 = new x2_0(this, 0);
                        var9_14.intercept((XposedInterface.Hooker)var8_4);
                        this.installed = true;
                        this.info("hook installed: android.app.Activity.onResume");
                        this.installSettingsHook(var1_1.getClassLoader());
                        return;
                    }
                    catch (Throwable var1_3) {
                        Log.e((String)"SoulHook102", (String)"hook installation failed", (Throwable)var1_3);
                        this.log(6, "SoulHook102", "hook installation failed", var1_3);
                    }
                    return;
                }
                for (var3_24 = 0; var3_24 < var4_23; ++var3_24) {
                    var26_40 = var25_39[var3_24];
                    var26_40 = j_0.g.hook(var26_40).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    var27_41 = new f_0(0, var24_37 /* !! */ );
                    var26_40.intercept((XposedInterface.Hooker)var27_41);
                    continue;
                }
                var11_19 /* !! */ .add((String[])var24_37 /* !! */ );
            }
            catch (Throwable var24_38) {}
            j_0.c((String)var17_30, var24_38);
            ++var2_18;
        }
    }
}

