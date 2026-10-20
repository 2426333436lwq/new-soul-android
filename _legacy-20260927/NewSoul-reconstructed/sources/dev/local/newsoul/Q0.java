/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.pm.PackageInfo
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.media.MediaPlayer
 *  android.net.Uri
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.provider.MediaStore$Images$Media
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.WebView
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$HookBuilder
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dev.local.newsoul.A;
import dev.local.newsoul.B;
import dev.local.newsoul.B2;
import dev.local.newsoul.E2;
import dev.local.newsoul.F;
import dev.local.newsoul.I2;
import dev.local.newsoul.N0;
import dev.local.newsoul.O0;
import dev.local.newsoul.P0;
import dev.local.newsoul.SoulHook;
import dev.local.newsoul.U0;
import dev.local.newsoul.V;
import dev.local.newsoul.V0;
import dev.local.newsoul.W0;
import dev.local.newsoul.X0;
import dev.local.newsoul.a_0;
import dev.local.newsoul.b_0;
import dev.local.newsoul.d3;
import dev.local.newsoul.e_0;
import dev.local.newsoul.f1_0;
import dev.local.newsoul.f_0;
import dev.local.newsoul.g1_0;
import dev.local.newsoul.h2_0;
import dev.local.newsoul.j_0;
import dev.local.newsoul.k1_0;
import dev.local.newsoul.o_0;
import dev.local.newsoul.p0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.t_0;
import dev.local.newsoul.v_0;
import dev.local.newsoul.w2;
import dev.local.newsoul.z_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public abstract class Q0 {
    public static Method A;
    public static boolean B;
    public static Class C;
    public static Class D;
    public static Class E;
    public static Constructor F;
    public static Method G;
    public static Method H;
    public static Method I;
    public static Method J;
    public static Method K;
    public static boolean L;
    public static boolean M;
    public static boolean N;
    public static boolean O;
    public static boolean P;
    public static boolean Q;
    public static boolean R;
    public static XposedModule S;
    public static ClassLoader T;
    public static boolean U;
    public static boolean V;
    public static volatile boolean W;
    public static volatile boolean X;
    public static boolean Y;
    public static Class Z;
    public static boolean a;
    public static Field a0;
    public static boolean b;
    public static Method b0;
    public static boolean c;
    public static Method c0;
    public static Method d;
    public static Field d0;
    public static boolean e;
    public static boolean e0;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static Method i;
    public static Method j;
    public static boolean k;
    public static Method l;
    public static Method m;
    public static Method n;
    public static Method o;
    public static Method p;
    public static Method q;
    public static Method r;
    public static Method s;
    public static Method t;
    public static Method u;
    public static Method v;
    public static Method w;
    public static Method x;
    public static Method y;
    public static Method z;

    public static void A(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Method method = classLoader.loadClass("lf.b").getDeclaredMethod("b", null);
            Class<?> clazz = classLoader.loadClass("cn.soulapp.android.square.utils.ImageDownloader");
            Class<Boolean> clazz2 = Boolean.TYPE;
            Q0.H(xposedModule, clazz.getDeclaredMethod("f", String.class, clazz2, clazz2, classLoader.loadClass("cn.soulapp.android.square.utils.ImageMarkProcessor"), classLoader.loadClass("cn.soulapp.android.square.utils.b")), "download_image_no_watermark", 2, -1, method);
            L = true;
        }
        catch (Throwable throwable) {
            xposedModule.log(6, "SoulHook102", "image watermark control unavailable", throwable);
        }
        try {
            Q0.H(xposedModule, classLoader.loadClass("cn.soulapp.android.square.utils.h2").getDeclaredMethod("b", String.class, Context.class, Long.TYPE, Boolean.TYPE), "download_video_no_watermark", 3, 1, null);
            M = true;
        }
        catch (Throwable throwable) {
            xposedModule.log(6, "SoulHook102", "video watermark control unavailable", throwable);
        }
    }

    public static void B(XposedModule xposedModule, ClassLoader object) {
        try {
            GenericDeclaration genericDeclaration = ((ClassLoader)object).loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
            AnnotatedElement annotatedElement = ((ClassLoader)object).loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment");
            GenericDeclaration genericDeclaration2 = ((ClassLoader)object).loadClass(((Class)annotatedElement).getName().concat("$Operate"));
            Field field = genericDeclaration.getDeclaredField("E");
            annotatedElement = ((Class)annotatedElement).getDeclaredField("q");
            ((AccessibleObject)field).setAccessible(true);
            ((AccessibleObject)annotatedElement).setAccessible(true);
            Object object2 = ((ClassLoader)object).loadClass("cn.soulapp.android.square.post.bean.Post").getField("content");
            Field field2 = genericDeclaration2.getField("a");
            Q0.I(xposedModule, (ClassLoader)object, genericDeclaration2, field2);
            XposedInterface.ExceptionMode exceptionMode = Integer.TYPE;
            object = genericDeclaration2.getConstructor(new Class[]{exceptionMode, String.class, Boolean.TYPE});
            Method method = genericDeclaration.getDeclaredMethod("initViews", View.class);
            genericDeclaration2 = genericDeclaration.getDeclaredMethod("P0", new Class[]{genericDeclaration2, View.class, exceptionMode});
            genericDeclaration = genericDeclaration.getMethod("dismiss", null);
            XposedInterface.HookBuilder hookBuilder = xposedModule.hook(genericDeclaration2).setId("new-soul/post-copy-click");
            exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            hookBuilder = hookBuilder.setExceptionMode(exceptionMode);
            b_0 b_02 = new b_0(field2, field, (Field)object2, (Method)genericDeclaration, 1);
            hookBuilder.intercept((XposedInterface.Hooker)b_02);
            method = xposedModule.hook((Executable)method).setId("new-soul/post-copy-action").setExceptionMode(exceptionMode);
            object2 = new f1_0(field, (AccessibleObject)annotatedElement, field2, (Executable)object, 1);
            method.intercept((XposedInterface.Hooker)object2);
            xposedModule.deoptimize(genericDeclaration2);
            N = true;
            Log.i((String)"SoulHook102", (String)"post copy hooks installed");
        }
        catch (Throwable throwable) {
            xposedModule.log(6, "SoulHook102", "post copy hooks unavailable", throwable);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void C(XposedModule xposedModule, ClassLoader object) {
        Throwable throwable2;
        block4: {
            try {
                Object object2 = ((ClassLoader)object).loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
                Field field = ((Class)object2).getDeclaredField("E");
                if (field.getType() != ((ClassLoader)object).loadClass("cn.soulapp.android.square.post.bean.Post")) {
                    object = new NoSuchFieldException("Unexpected share post field");
                    throw object;
                }
                ((AccessibleObject)field).setAccessible(true);
                Method method = ((Class)object2).getDeclaredMethod("r0", null);
                object = ((Class)object2).getDeclaredMethod("initViews", View.class);
                if (method.getReturnType() == Void.TYPE && Modifier.isPrivate(method.getModifiers())) {
                    method = xposedModule.hook((Executable)method).setId("new-soul/post-share-permission").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    object2 = new k1_0(field, 0);
                    method.intercept((XposedInterface.Hooker)object2);
                    xposedModule.deoptimize((Executable)object);
                    O = true;
                    Log.i((String)"SoulHook102", (String)"post share control installed");
                    return;
                }
            }
            catch (Throwable throwable2) {
                break block4;
            }
            object = new NoSuchMethodException("Unexpected share permission signature");
            throw object;
        }
        xposedModule.log(6, "SoulHook102", "post share control unavailable", throwable2);
    }

    public static void D(XposedModule xposedModule, ClassLoader classLoader) {
        S = xposedModule;
        T = classLoader;
        try {
            xposedModule = xposedModule.hook((Executable)classLoader.loadClass("cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity").getDeclaredMethod("init", Bundle.class)).setId("new-soul/privacy-photo-preview").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            a_0 a_02 = new a_0(16);
            xposedModule.intercept((XposedInterface.Hooker)a_02);
            P = true;
            Q0.w("privacy photo controls installed");
        }
        catch (Throwable throwable) {
            S.log(6, "SoulHook102", "privacy photo controls unavailable", throwable);
        }
        Q = Q0.J(classLoader, "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", "privacy");
        R = Q0.J(classLoader, "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", "snap");
    }

    /*
     * Exception decompiling
     */
    public static void E(XposedModule var0, ClassLoader var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Back jump on a try block [egrp 6[TRYBLOCK] [6 : 163->250)] java.lang.Throwable
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.insertExceptionBlocks(Op02WithProcessedDataAndRefs.java:2283)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:415)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static void F(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Object object;
            Z = object = classLoader.loadClass("cn.soulapp.android.square.post.bean.Post");
            a0 = object.getField("id");
            b0 = Z.getMethod("hasDeclare", null);
            Q0.y(classLoader, Z);
            c0 = Z.getMethod("getAdResponse", null);
            d0 = Z.getField("incentiveModel");
            Object object2 = xposedModule.hook((Executable)classLoader.loadClass("cn.soulapp.android.component.startup.main.MainActivity").getDeclaredMethod("onCreate", Bundle.class)).setId("new-soul/square-like-lifecycle");
            object = XposedInterface.ExceptionMode.PROTECTIVE;
            object2 = object2.setExceptionMode(object);
            Object object3 = new a_0(19);
            object2.intercept((XposedInterface.Hooker)object3);
            object3 = classLoader.loadClass("cn.soulapp.android.component.square.widget.SquareFloatingButton");
            object2 = Integer.TYPE;
            classLoader = xposedModule.hook(((Class)object3).getDeclaredConstructor(new Class[]{Context.class, AttributeSet.class, object2})).setId("new-soul/square-like-envelope-create").setExceptionMode(object);
            a_0 a_02 = new a_0(20);
            classLoader.intercept(a_02);
            classLoader = xposedModule.hook((Executable)((Class)object3).getDeclaredMethod("onVisibilityChanged", new Class[]{View.class, object2})).setId("new-soul/square-like-envelope-visibility").setExceptionMode(object);
            object = new a_0(21);
            classLoader.intercept((XposedInterface.Hooker)object);
            Y = true;
        }
        catch (Throwable throwable) {
            Log.e((String)"SoulHook102", (String)"square like unavailable", (Throwable)throwable);
            xposedModule.log(6, "SoulHook102", "square like unavailable", throwable);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void G(XposedModule object, Class clazz, String object2, Class[] object3, boolean bl, int n, int n2) {
        Throwable throwable2;
        String string;
        block3: {
            string = bl ? "repeat.caller" : "repeat.show";
            try {
                object3 = clazz.getDeclaredMethod((String)object2, (Class<?>)object3);
                if (((Method)object3).getReturnType() == Void.TYPE && Modifier.isStatic(((Method)object3).getModifiers()) != bl) {
                    clazz = object.hook((Executable)object3).setId("new-soul/".concat(string)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    object2 = new v_0(n2, bl, string, n);
                    clazz.intercept((XposedInterface.Hooker)object2);
                    dev.local.newsoul.V.i(string, null);
                    Q0.k(object, (Method)object3, string);
                    return;
                }
            }
            catch (Throwable throwable2) {
                break block3;
            }
            object = new NoSuchMethodException("Unexpected boundary signature");
            throw object;
        }
        dev.local.newsoul.V.i(string, throwable2);
    }

    public static void H(XposedModule xposedModule, Method method, String string, int n, int n2, Method method2) {
        if (Modifier.isStatic(method.getModifiers()) && method.getReturnType() == Void.TYPE) {
            xposedModule.hook((Executable)method).setId("new-soul/".concat(string)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new X0(n2, method2, string, xposedModule, n));
            xposedModule.log(4, "SoulHook102", "watermark control installed: ".concat(string));
            return;
        }
        throw new IllegalStateException("Unexpected download signature: ".concat(String.valueOf(method)));
    }

    public static void I(XposedModule xposedModule, ClassLoader object, Class object2, Field field) {
        try {
            XposedInterface.HookBuilder hookBuilder = ((ClassLoader)object).loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a");
            object = hookBuilder.getDeclaredMethod("a", new Class[]{object2});
            Field field2 = hookBuilder.getDeclaredField("c");
            ((AccessibleObject)field2).setAccessible(true);
            hookBuilder = xposedModule.hook((Executable)object).setId("new-soul/action-marks-post").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
            object2 = new B(field2, field, 1);
            hookBuilder.intercept((XposedInterface.Hooker)object2);
            xposedModule.deoptimize((Executable)object);
        }
        catch (Throwable throwable) {
            Log.w((String)"SoulHook102", (String)"post action mark unavailable", (Throwable)throwable);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean J(ClassLoader serializable, String object, String object2, String string) {
        Throwable throwable2;
        block6: {
            try {
                serializable = ((ClassLoader)((Object)serializable)).loadClass((String)object);
                object = ((Class)serializable).getDeclaredMethod((String)object2, null);
                if (((Method)object).getReturnType() == Void.TYPE && !Modifier.isStatic(((Method)object).getModifiers())) {
                    object = S.hook((Executable)object).setId("new-soul/privacy-repeat-".concat(string)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    object2 = new f_0(1, string);
                    object.intercept((XposedInterface.Hooker)object2);
                    Q0.m(serializable);
                    object = ((Class)serializable).getDeclaredClasses();
                    int n = ((Object)object).length;
                    for (int i = 0; i < n; ++i) {
                        Q0.m((Class)object[i]);
                    }
                    if ("snap".equals(string)) {
                        serializable = S.hook((Executable)((Class)serializable).getDeclaredMethod("init", Bundle.class)).setId("new-soul/privacy-repeat-snap-preview").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                        object = new a_0(17);
                        serializable.intercept((XposedInterface.Hooker)object);
                    }
                    serializable = new StringBuilder();
                    ((StringBuilder)serializable).append("privacy repeat guard installed: route=");
                    ((StringBuilder)serializable).append(string);
                    Q0.w(((StringBuilder)serializable).toString());
                    return true;
                }
            }
            catch (Throwable throwable2) {
                break block6;
            }
            serializable = new NoSuchMethodException("Unexpected repeat guard signature: ".concat(String.valueOf(object)));
            throw serializable;
        }
        S.log(6, "SoulHook102", "privacy repeat guard unavailable: ".concat(string), throwable2);
        return false;
    }

    public static void K(XposedModule xposedModule, ClassLoader genericDeclaration, Class clazz) {
        Class<?> clazz2 = ((ClassLoader)((Object)genericDeclaration)).loadClass("cn.soulapp.android.component.chat.widget.AbsChatDualItem");
        Class<?> clazz3 = ((ClassLoader)((Object)genericDeclaration)).loadClass(clazz2.getName().concat("$ViewHolder"));
        Field field = clazz3.getField("itemView");
        Field field2 = clazz2.getDeclaredField("e");
        ((AccessibleObject)field2).setAccessible(true);
        Method[] methodArray = clazz2.getDeclaredMethods();
        int n = methodArray.length;
        clazz2 = null;
        for (int i = 0; i < n; ++i) {
            Method method = methodArray[i];
            Class<?>[] classArray = method.getParameterTypes();
            genericDeclaration = clazz2;
            if (classArray.length == 4) {
                genericDeclaration = clazz2;
                if (classArray[0] == clazz3) {
                    genericDeclaration = clazz2;
                    if (classArray[1] == clazz) {
                        genericDeclaration = clazz2;
                        if (classArray[2] == Integer.TYPE) {
                            genericDeclaration = clazz2;
                            if (classArray[3] == List.class) {
                                if (clazz2 == null) {
                                    genericDeclaration = method;
                                } else {
                                    throw new IllegalStateException("Ambiguous chat row binding");
                                }
                            }
                        }
                    }
                }
            }
            clazz2 = genericDeclaration;
        }
        if (clazz2 != null) {
            xposedModule.hook(clazz2).setId("new-soul/chat-recall-mark").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new B(field, field2, 0));
            xposedModule.deoptimize(clazz2);
            return;
        }
        throw new NoSuchMethodException("AbsChatDualItem bind");
    }

    public static String L(Object object) {
        String string = String.valueOf(m.invoke(null, null));
        Object object2 = String.valueOf(p.invoke(object, null));
        String string2 = String.valueOf(q.invoke(object, null));
        String string3 = String.valueOf(r.invoke(object, null));
        object = new StringBuilder();
        ((StringBuilder)object).append(string);
        ((StringBuilder)object).append("\n");
        ((StringBuilder)object).append((String)object2);
        ((StringBuilder)object).append("\n");
        ((StringBuilder)object).append(string2);
        ((StringBuilder)object).append("\n");
        ((StringBuilder)object).append(string3);
        object = ((StringBuilder)object).toString();
        object2 = MessageDigest.getInstance("SHA-256").digest(((String)object).getBytes(StandardCharsets.UTF_8));
        object = new StringBuilder();
        for (Object object3 : object2) {
            ((StringBuilder)object).append(String.format(Locale.ROOT, "%02x", object3 & 0xFF));
        }
        return ((StringBuilder)object).toString();
    }

    public static PublicKey M() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNOIwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/HRJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loOB0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk06ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCFQbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE=")));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean N(String string) {
        boolean bl;
        URI uRI;
        boolean bl2;
        block6: {
            bl2 = false;
            if (string == null) {
                return false;
            }
            try {
                uRI = new URI(string);
                if ("https".equalsIgnoreCase(uRI.getScheme())) break block6;
                bl = bl2;
            }
            catch (URISyntaxException uRISyntaxException) {
                return bl2;
            }
            if (!"http".equalsIgnoreCase(uRI.getScheme())) return bl;
        }
        bl = bl2;
        if (uRI.getHost() == null) return bl;
        bl = bl2;
        if (uRI.getHost().isEmpty()) return bl;
        string = uRI.getUserInfo();
        bl = bl2;
        if (string != null) return bl;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String O(Object object) {
        if (!dev.local.newsoul.V.a()) {
            return "";
        }
        if (object == null) {
            return "message_present=false";
        }
        try {
            object = object.getClass().getMethod("getChatMessage", null).invoke(object, null);
            if (object == null) {
                return "message_present=true; chat_present=false";
            }
        }
        catch (Throwable throwable) {
            return "message_present=true; type=unavailable; error=".concat(throwable.getClass().getName());
        }
        if (!((object = object.getClass().getMethod("getMsgType", null).invoke(object, null)) instanceof Integer)) {
            object = "unknown";
        }
        return "message_present=true; type=".concat(String.valueOf(object));
    }

    public static int P(String object, boolean bl) {
        int n;
        block3: {
            try {
                n = Integer.parseInt(((String)object).trim());
                if (bl || n >= 0) break block3;
            }
            catch (NumberFormatException numberFormatException) {
                String string = bl ? "\u8bf7\u8f93\u5165 -2147483648 \u81f3 2147483647 \u7684\u6574\u6570" : "\u8bf7\u8f93\u5165 0 \u81f3 2147483647 \u7684\u6574\u6570";
                throw new IllegalArgumentException(string);
            }
            object = new NumberFormatException();
            throw object;
        }
        return n;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int Q(ArrayList arrayList, ClassLoader object, String string, String serializable, String string2, Boolean bl, String ... stringArray) {
        Throwable throwable2;
        String string3 = TextUtils.join((CharSequence)",", (Object[])stringArray);
        Class[] classArray = new StringBuilder();
        classArray.append((String)((Object)serializable));
        classArray.append("#");
        classArray.append(string2);
        classArray.append("(");
        classArray.append(string3);
        classArray.append(")");
        string3 = classArray.toString();
        Class<?> clazz = ((ClassLoader)object).loadClass((String)((Object)serializable));
        int n = stringArray.length;
        classArray = new Class[n];
        for (int i = 0; i < n; ++i) {
            serializable = stringArray[i];
            try {
                serializable = "boolean".equals(serializable) ? Boolean.TYPE : ("int".equals(serializable) ? Integer.TYPE : ("long".equals(serializable) ? Long.TYPE : ((ClassLoader)object).loadClass((String)((Object)serializable))));
                classArray[i] = serializable;
                continue;
            }
            catch (Throwable throwable2) {
            }
        }
        {
            try {
                clazz.getDeclaredMethod(string2, classArray);
            }
            catch (NoSuchMethodException noSuchMethodException) {
                clazz.getMethod(string2, classArray);
            }
            object = bl != false ? "\u5df2\u6ce8\u518c \u00b7 \u5f85\u529f\u80fd\u5b9e\u6d4b" : "\u7b7e\u540d\u5b58\u5728 \u00b7 Hook \u672a\u5c31\u7eea";
            serializable = new StringBuilder();
            ((StringBuilder)serializable).append(string);
            ((StringBuilder)serializable).append("\uff1a");
            ((StringBuilder)serializable).append((String)object);
            ((StringBuilder)serializable).append("\n  ");
            ((StringBuilder)serializable).append(string3);
            arrayList.add(((StringBuilder)serializable).toString());
            return 0;
        }
        String string4 = throwable2.getClass().getSimpleName();
        serializable = new StringBuilder();
        ((StringBuilder)serializable).append(string);
        ((StringBuilder)serializable).append("\uff1aHook \u70b9\u7f3a\u5931\uff08");
        ((StringBuilder)serializable).append(string4);
        ((StringBuilder)serializable).append("\uff09\n  ");
        ((StringBuilder)serializable).append(string3);
        arrayList.add(((StringBuilder)serializable).toString());
        return 1;
    }

    public static boolean R(Activity activity) {
        boolean bl;
        Object object = activity.getIntent();
        boolean bl2 = bl = false;
        if (!object.getBooleanExtra("isSender", false)) {
            object = w2.a;
            bl2 = bl;
            if (activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getBoolean("privacy_allow_repeat", false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public static boolean S(Context context, Object object, Object object2) {
        if (!Q0.v(object2)) {
            return false;
        }
        Object object3 = n.invoke(object2, null);
        if ((Integer)o.invoke(object3, null) == 9 && !"1".equals(u.invoke(object3, "isSnap"))) {
            String string = (String)r.invoke(object2, null);
            object3 = v.invoke(object, p.invoke(object2, null));
            object3 = object3 == null ? null : w.invoke(object3, string);
            Object object4 = object3;
            if (object3 == null) {
                object4 = x.invoke(object, string);
            }
            if (Q0.v(object4) && Q0.u(object4) && p.invoke(object2, null).equals(p.invoke(object4, null)) && q.invoke(object2, null).equals(q.invoke(object4, null)) && string.equals(r.invoke(object4, null))) {
                object = Q0.L(object4);
                if (!(context = context.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0)).contains((String)object) && !context.edit().putBoolean((String)object, true).commit()) {
                    return false;
                }
                Log.i((String)"SoulHook102", (String)"recall retained; source=private; type=".concat(String.valueOf(o.invoke(n.invoke(object4, null), null))));
                return true;
            }
        }
        return false;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Uri T(Activity object, Bitmap object2) {
        void var0_4;
        Uri uri;
        boolean bl;
        ContentResolver contentResolver;
        block12: {
            Throwable throwable2;
            block11: {
                contentResolver = object.getContentResolver();
                ContentValues contentValues = new ContentValues();
                long l = System.currentTimeMillis();
                object = new StringBuilder("NewSoul_");
                ((StringBuilder)object).append(l);
                ((StringBuilder)object).append(".png");
                contentValues.put("_display_name", ((StringBuilder)object).toString());
                contentValues.put("mime_type", "image/png");
                contentValues.put("relative_path", "Pictures/New Soul");
                bl = true;
                contentValues.put("is_pending", Integer.valueOf(1));
                uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (uri == null) {
                    return null;
                }
                object = contentResolver.openOutputStream(uri, "w");
                if (object == null) break block11;
                try {
                    boolean bl2 = object2.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)object);
                    if (!bl2) break block11;
                }
                catch (Throwable throwable2) {}
                try {
                    ((OutputStream)object).close();
                }
                catch (Throwable throwable3) {
                    break block12;
                }
                contentValues.clear();
                contentValues.put("is_pending", Integer.valueOf(0));
                contentResolver.update(uri, contentValues, null, null);
                return uri;
            }
            object2 = new IllegalStateException("Bitmap compression failed");
            throw object2;
            if (object == null) throw throwable2;
            try {
                ((OutputStream)object).close();
                throw throwable2;
            }
            catch (Throwable throwable4) {
                try {
                    throwable2.addSuppressed(throwable4);
                    throw throwable2;
                }
                catch (Throwable throwable5) {
                    bl = false;
                }
            }
        }
        if (bl) throw var0_4;
        contentResolver.delete(uri, null, null);
        throw var0_4;
    }

    public static P0 U(Context object) {
        CharSequence charSequence;
        String string;
        try {
            string = object.getPackageManager().getPackageInfo(object.getPackageName(), 0);
            long l = Build.VERSION.SDK_INT >= 28 ? N0.a((PackageInfo)string) : (long)((PackageInfo)string).versionCode;
            string = ((PackageInfo)string).versionName;
            charSequence = new StringBuilder();
            ((StringBuilder)charSequence).append(string);
            ((StringBuilder)charSequence).append(" (");
            ((StringBuilder)charSequence).append(l);
            ((StringBuilder)charSequence).append(")");
            string = ((StringBuilder)charSequence).toString();
        }
        catch (Exception exception) {
            string = "\u672a\u77e5\u7248\u672c";
        }
        SharedPreferences sharedPreferences = w2.o((Context)object);
        String string2 = sharedPreferences.getString("hook_diagnostics_last_host_version", null);
        ClassLoader classLoader = object.getClassLoader();
        ArrayList<String> arrayList = new ArrayList<String>();
        int n = Q0.Q(arrayList, classLoader, "\u8bbe\u7f6e\u5165\u53e3", "cn.soulapp.android.h5.activity.H5Activity", "m1", SoulHook.settingsHookReady, "boolean");
        int n2 = Q0.Q(arrayList, classLoader, "\u51b7\u542f\u52a8\u5e7f\u544a", "cn.soulapp.android.component.startup.main.MainScheduler", "v", W, new String[0]);
        int n3 = Q0.Q(arrayList, classLoader, "\u70ed\u542f\u52a8\u5e7f\u544a", "tc.e$a", "j", X, "java.lang.String");
        boolean bl = j_0.b.isEmpty();
        int n4 = 1;
        int n5 = Q0.Q(arrayList, classLoader, "\u5e7f\u544a\u4f4d\u8bc6\u522b", "fa.a", "b", bl ^ true, new String[0]);
        int n6 = 0;
        int n7 = 1;
        while (true) {
            object = j_0.a;
            if (n7 >= 19) break;
            if (j_0.b.contains((object = object[n7])[0])) {
                ++n6;
            } else {
                object = object[1];
                charSequence = new StringBuilder();
                ((StringBuilder)charSequence).append((String)object);
                ((StringBuilder)charSequence).append("\uff1aHook \u672a\u5c31\u7eea \u00b7 \u8bf7\u67e5\u770b SoulHook102 \u5b89\u88c5\u65e5\u5fd7");
                arrayList.add(((StringBuilder)charSequence).toString());
            }
            ++n7;
        }
        n7 = n4;
        if (!p0_0.a) {
            n7 = n4;
            if (!p0_0.b) {
                n7 = p0_0.c ? n4 : 0;
            }
        }
        CharSequence charSequence2 = "Hook \u5df2\u6ce8\u518c\uff0c\u5f85\u5b9e\u6d4b";
        charSequence = "Hook \u672a\u5c31\u7eea";
        object = n7 != 0 ? "Hook \u5df2\u6ce8\u518c\uff0c\u5f85\u5b9e\u6d4b" : "Hook \u672a\u5c31\u7eea";
        boolean bl2 = p0_0.a;
        bl = p0_0.b;
        boolean bl3 = p0_0.c;
        StringBuilder stringBuilder = new StringBuilder("\u4fe1\u606f\u4f2a\u88c5 \u00b7 ");
        stringBuilder.append((String)object);
        stringBuilder.append("\n\u4e2a\u4eba\u9875 ");
        stringBuilder.append(bl2);
        stringBuilder.append(" / \u65b0\u4e2a\u4eba\u9875 ");
        stringBuilder.append(bl);
        stringBuilder.append(" / \u65e7\u4e2a\u4eba\u9875 ");
        stringBuilder.append(bl3);
        arrayList.add(stringBuilder.toString());
        object = charSequence2;
        if (!p0_0.d) {
            object = p0_0.e ? charSequence2 : "Hook \u672a\u5c31\u7eea";
        }
        bl3 = p0_0.d;
        bl = p0_0.e;
        charSequence2 = new StringBuilder("\u5929\u6570\u4f2a\u88c5 \u00b7 ");
        ((StringBuilder)charSequence2).append((String)object);
        ((StringBuilder)charSequence2).append("\n\u4e2a\u4eba\u9875 ");
        ((StringBuilder)charSequence2).append(bl3);
        ((StringBuilder)charSequence2).append(" / \u65b0\u4e2a\u4eba\u9875 ");
        ((StringBuilder)charSequence2).append(bl);
        arrayList.add(((StringBuilder)charSequence2).toString());
        object = new StringBuilder("\u5e7f\u544a\u5b50\u9879\u5df2\u6ce8\u518c ");
        ((StringBuilder)object).append(n6);
        ((StringBuilder)object).append("/18");
        arrayList.add(((StringBuilder)object).toString());
        int n8 = Q0.Q(arrayList, classLoader, "\u81ea\u5b9a\u4e49\u9ab0\u5b50", "cn.soulapp.android.component.chat.utils.y4", "w0", t_0.a, new String[0]);
        int n9 = Q0.Q(arrayList, classLoader, "\u81ea\u5b9a\u4e49\u731c\u62f3", "cn.soulapp.android.component.chat.utils.y4", "h1", t_0.b, new String[0]);
        int n10 = Q0.Q(arrayList, classLoader, "\u6d88\u606f\u9632\u64a4\u56de", "cn.soulapp.imlib.ChatManager", "O", k, "java.util.List");
        n7 = Q0.Q(arrayList, classLoader, "\u81ea\u5df1\u64a4\u56de", "cn.soulapp.android.component.chat.helper.WithDrawHandler", "d", g1_0.a, "cn.soulapp.imlib.msg.ImMessage");
        n4 = Q0.Q(arrayList, classLoader, "\u9690\u85cf\u8f93\u5165\u72b6\u6001", "cn.soulapp.imlib.ChatManager", "p0", b, "cn.soulapp.imlib.msg.ImMessage", "boolean");
        int n11 = Q0.Q(arrayList, classLoader, "\u5339\u914d\u97f3\u4e50", "cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper", "k", c, new String[0]);
        n6 = Q0.Q(arrayList, classLoader, "\u804a\u5929\u793c\u7269\u9650\u5236", "cn.soulapp.android.lib.common.bean.ChatLimitModel", "isLimit", e, new String[0]);
        int n12 = Q0.Q(arrayList, classLoader, "\u5947\u9047\u94c3\u9001\u793c\u9650\u5236", "cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig", "getShowPopup", f, new String[0]);
        int n13 = Q0.Q(arrayList, classLoader, "\u804a\u5929\u793c\u7269\u4f1a\u8bdd\u989d\u5ea6", "cn.soulapp.imlib.Conversation", "getIntExt", g, "java.lang.String");
        object = charSequence;
        if (h) {
            object = "\u7b7e\u540d\u5339\u914d\uff0cHook \u5df2\u6ce8\u518c";
        }
        arrayList.add("\u804a\u5929\u793c\u7269\u5f39\u7a97 \u00b7 ".concat((String)object));
        int n14 = Q0.Q(arrayList, classLoader, "\u804a\u5929\u590d\u8bfb/\u8f6c\u53d1", "cn.soulapp.android.component.chat.utils.u0", "k", dev.local.newsoul.F.o, "cn.soulapp.android.component.chat.fragment.ConversationFragment", "android.view.View", "cn.soulapp.imlib.msg.ImMessage");
        int n15 = Q0.Q(arrayList, classLoader, "\u5e95\u680f\u957f\u6309\u8bbe\u7f6e", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", o_0.a, "android.os.Bundle");
        int n16 = Q0.Q(arrayList, classLoader, "\u6d88\u606f\u8f6c\u53d1\u9009\u62e9", "cn.soulapp.android.component.chat.SelectConversationActivity", "F", dev.local.newsoul.F.p, "int");
        int n17 = Q0.Q(arrayList, classLoader, "\u8bed\u97f3\u5e93\u53d1\u9001", "cn.soulapp.android.component.chat.utils.y4", "q2", d3.g, "java.lang.String", "int", "cn.soulapp.imlib.msg.ImMessage");
        int n18 = Q0.Q(arrayList, classLoader, "\u804a\u5929\u9690\u79c1\u5a92\u4f53", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "init", P, "android.os.Bundle");
        int n19 = Q0.Q(arrayList, classLoader, "\u9690\u79c1\u5a92\u4f53\u591a\u6b21\u67e5\u770b", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", Q, new String[0]);
        int n20 = Q0.Q(arrayList, classLoader, "\u95ea\u7167\u591a\u6b21\u67e5\u770b", "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", R, new String[0]);
        int n21 = Q0.Q(arrayList, classLoader, "\u56fe\u7247\u4e0b\u8f7d", "cn.soulapp.android.square.utils.ImageDownloader", "f", L, "java.lang.String", "boolean", "boolean", "cn.soulapp.android.square.utils.ImageMarkProcessor", "cn.soulapp.android.square.utils.b");
        int n22 = Q0.Q(arrayList, classLoader, "\u89c6\u9891\u4e0b\u8f7d", "cn.soulapp.android.square.utils.h2", "b", M, "java.lang.String", "android.content.Context", "long", "boolean");
        int n23 = Q0.Q(arrayList, classLoader, "\u5e16\u5b50\u5206\u4eab\u9650\u5236", "cn.soulapp.android.square.SeedsShareDialogFragment", "r0", O, new String[0]);
        int n24 = Q0.Q(arrayList, classLoader, "\u793c\u4eea\u5206\u672c\u5730\u9650\u5236", "cn.soulapp.android.component.chat.bean.SocialGraceScoreBean", "getCreateSessionLimit", U, new String[0]);
        int n25 = Q0.Q(arrayList, classLoader, "\u793c\u4eea\u5206\u9650\u5236\u5f39\u7a97", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils", "b", V, "androidx.fragment.app.FragmentManager", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a");
        int n26 = Q0.Q(arrayList, classLoader, "\u5e16\u5b50\u6587\u6848\u590d\u5236", "cn.soulapp.android.square.SeedsShareDialogFragment", "initViews", N, "android.view.View");
        int n27 = Q0.Q(arrayList, classLoader, "\u5e7f\u573a\u5e16\u5b50\u7b5b\u9009", "cn.soulapp.android.component.square.main.squarepost.SquareAdapter", "d", B2.i, "cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder", "java.lang.Object");
        int n28 = Q0.Q(arrayList, classLoader, "\u5e7f\u573a\u4e00\u952e\u70b9\u8d5e", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Y, "android.os.Bundle");
        int n29 = Q0.Q(arrayList, classLoader, "\u5df2\u52a0\u8f7d\u5e16\u5b50\u70b9\u8d5e\u8bf7\u6c42", "gs0.g", "subscribe", B, "io.reactivex.functions.Consumer", "io.reactivex.functions.Consumer");
        sharedPreferences.edit().putString("hook_diagnostics_last_host_version", string).apply();
        return new P0(string, string2, arrayList, n + n2 + n3 + n5 + n8 + n9 + n10 + n7 + n4 + n11 + n6 + n12 + n13 + n14 + n15 + n16 + n17 + n18 + n19 + n20 + n21 + n22 + n23 + n24 + n25 + n26 + n27 + n28 + n29);
    }

    /*
     * Unable to fully structure code
     */
    public static void V(Activity var0, q2 var1_2, ImageView var2_3, TextView var3_4, TextView var4_5, TextView var5_6, String var6_7, String var7_8) {
        block6: {
            block5: {
                try {
                    var0 = var0.createPackageContext("dev.local.newsoul.public", 2);
                    var8_9 = var0.getResources().getIdentifier(var6_7, "drawable", var0.getPackageName());
                    if (var8_9 != 0) break block5;
lbl5:
                    // 2 sources

                    while (true) {
                        var0 = null;
                        break block6;
                        break;
                    }
                }
                catch (Throwable var0_1) {
                    ** continue;
                }
            }
            var0 = var0.getResources().getDrawable(var8_9, null);
        }
        if (var0 == null) {
            var3_4.setVisibility(0);
            var2_3.setImageDrawable(null);
            var2_3.setVisibility(8);
            var3_4.setText((CharSequence)"\u6536\u6b3e\u7801\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u6b64\u9875\u9762");
            var3_4.setTextColor(var1_2.j);
            Q0.Y(var1_2, var4_5, false);
            Q0.Y(var1_2, var5_6, false);
            return;
        }
        var2_3.setImageDrawable((Drawable)var0);
        var2_3.setContentDescription((CharSequence)var7_8);
        var2_3.setVisibility(0);
        var3_4.setVisibility(8);
        Q0.Y(var1_2, var4_5, true);
        Q0.Y(var1_2, var5_6, false);
    }

    public static void W(Activity object, W0 object2, Object object3, h2_0 object4) {
        object3 = F.newInstance(object3);
        object3 = I.invoke(object3, 0);
        Object object5 = new U0((W0)object2, (h2_0)object4, 0);
        Object object6 = E.getClassLoader();
        Object object7 = E;
        object5 = new V0((Activity)object, (Runnable)object5);
        object6 = Proxy.newProxyInstance((ClassLoader)object6, new Class[]{object7}, (InvocationHandler)object5);
        object7 = new U0((W0)object2, (h2_0)object4, 1);
        object2 = E.getClassLoader();
        object4 = E;
        object = new V0((Activity)object, (Runnable)object7);
        object = Proxy.newProxyInstance((ClassLoader)object2, new Class[]{object4}, (InvocationHandler)object);
        J.invoke(object3, object6, object);
    }

    public static String X() {
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        int n = stackTraceElementArray.length;
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            StackTraceElement stackTraceElement = stackTraceElementArray[i];
            string = stackTraceElement.getClassName();
            int n3 = n2;
            if (!string.startsWith("dev.local.newsoul.")) {
                if (string.equals(Thread.class.getName())) {
                    n3 = n2;
                } else if (!(string.startsWith("cn.soulapp.") || string.startsWith("uj.") || string.contains("patch"))) {
                    n3 = n2;
                } else {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" <- ");
                    }
                    stringBuilder.append(string);
                    stringBuilder.append('.');
                    stringBuilder.append(stackTraceElement.getMethodName());
                    stringBuilder.append(':');
                    stringBuilder.append(stackTraceElement.getLineNumber());
                    n3 = ++n2;
                    if (n2 == 6) break;
                }
            }
            n2 = n3;
        }
        string = stringBuilder.length() == 0 ? "unavailable" : stringBuilder.toString();
        return string;
    }

    public static void Y(q2 q22, TextView textView, boolean bl) {
        q22.c.b((View)textView, 5, 10.0f);
        textView.setSelected(bl);
    }

    public static void Z(String string, Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder("gift chat ");
        stringBuilder.append(string);
        stringBuilder.append(" unavailable");
        Log.w((String)"SoulHook102", (String)stringBuilder.toString(), (Throwable)throwable);
    }

    public static boolean a(Object object) {
        boolean bl = c0.invoke(object, null) == null && d0.get(object) == null;
        return bl;
    }

    public static WebView a0(View view) {
        if (view instanceof WebView) {
            return (WebView)view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup;
            for (int i = 0; i < (viewGroup = (ViewGroup)view).getChildCount(); ++i) {
                if ((viewGroup = Q0.a0(viewGroup.getChildAt(i))) == null) continue;
                return viewGroup;
            }
        }
        return null;
    }

    public static boolean b(View view) {
        boolean bl;
        boolean bl2 = bl = false;
        if (view != null) {
            bl2 = bl;
            if (view.isAttachedToWindow()) {
                bl2 = bl;
                if (view.isShown()) {
                    if (view.getAlpha() == 0.0f) {
                        bl2 = bl;
                    } else {
                        Rect rect = new Rect();
                        bl2 = bl;
                        if (view.getGlobalVisibleRect(rect)) {
                            bl2 = bl;
                            if (rect.width() > 0) {
                                bl2 = bl;
                                if (rect.height() > 0) {
                                    bl2 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bl2;
    }

    public static boolean c(float f, float f2, float f4, boolean bl) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (!bl) {
            if (f2 <= -f4) {
                bl3 = bl2;
            } else {
                if (f2 >= f4 && f >= 0.02f) {
                    return true;
                }
                bl3 = bl2;
                if (f >= 0.28f) {
                    bl3 = true;
                }
            }
        }
        return bl3;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void d(View view) {
        Throwable throwable2;
        block8: {
            Context context;
            if (view == null) return;
            try {
                if (!view.isAttachedToWindow()) return;
                if (!(view.getParent() instanceof RelativeLayout)) {
                    return;
                }
                context = view.getContext();
                while (context instanceof ContextWrapper && !(context instanceof Activity)) {
                    context = ((ContextWrapper)context).getBaseContext();
                }
            }
            catch (Throwable throwable2) {
                break block8;
            }
            if (!(context instanceof Activity)) {
                return;
            }
            if (!"cn.soulapp.android.component.startup.main.MainActivity".equals((context = (Activity)context).getClass().getName())) return;
            if (view.getId() != context.getResources().getIdentifier("message_button", "id", context.getPackageName())) {
                return;
            }
            I2 i2 = Q0.i(view);
            if (i2 != null) {
                i2.i();
                return;
            }
            i2 = (RelativeLayout)view.getParent();
            I2 i22 = new I2((Activity)context, view);
            E2 e2 = i22.c;
            e2.setTag("new.soul.square.like");
            float f = 68;
            {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(context.getResources().getDisplayMetrics().density * f), Math.round(f * context.getResources().getDisplayMetrics().density));
                layoutParams.addRule(2, view.getId());
                layoutParams.addRule(7, view.getId());
                i2.addView(e2, (ViewGroup.LayoutParams)layoutParams);
                i22.i();
                Log.i((String)"SoulHook102", (String)"square like attached beside envelope; native parent=RelativeLayout");
                return;
            }
        }
        Log.e((String)"SoulHook102", (String)"square like envelope attachment failed", (Throwable)throwable2);
    }

    public static boolean e(Object object) {
        boolean bl;
        block5: {
            int n;
            boolean bl2;
            block6: {
                bl2 = false;
                if (object == null) {
                    return false;
                }
                n = (Integer)i.invoke(object, null);
                boolean bl3 = (object = (String)j.invoke(object, null)) != null && !((String)object).isEmpty();
                bl = bl2;
                if (n < 0) break block5;
                bl = bl2;
                if (n >= 19) break block5;
                bl = bl2;
                if (n == 6) break block5;
                if (n == 0 || n == 1 || n == 5 || n == 10 || n == 11 || n >= 14) break block6;
                bl = bl2;
                if (!bl3) break block5;
            }
            object = n != 10 && n != 11 ? "chat_remove_gift_gate" : "chat_remove_bell_gift_gate";
            bl = bl2;
            if (Q0.q((String)object)) {
                bl = true;
            }
        }
        return bl;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void f(Activity object) {
        Throwable throwable2;
        block7: {
            String string;
            CharSequence charSequence2;
            String string2;
            int n;
            int n2;
            Object object2;
            boolean bl;
            block8: {
                if (a) {
                    return;
                }
                bl = true;
                a = true;
                try {
                    object2 = Q0.U((Context)object);
                    n2 = ((P0)object2).e;
                    n = ((P0)object2).d;
                    string2 = ((P0)object2).b;
                }
                catch (Throwable throwable2) {
                    break block7;
                }
                {
                    charSequence2 = object.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getString("hook_diagnostics_last_shown_version", null);
                    string = ((P0)object2).a;
                    if (charSequence2 == null ? !string.startsWith("6.37.0") : !((String)charSequence2).equals(string)) break block8;
                }
                bl = false;
            }
            charSequence2 = new StringBuilder("hook scan: host=");
            ((StringBuilder)charSequence2).append(string);
            ((StringBuilder)charSequence2).append("; baseline=6.37.0; previous=");
            ((StringBuilder)charSequence2).append(string2);
            ((StringBuilder)charSequence2).append("; missing=");
            ((StringBuilder)charSequence2).append(n);
            ((StringBuilder)charSequence2).append("; unready=");
            ((StringBuilder)charSequence2).append(n2);
            Log.i((String)"SoulHook102", (String)((StringBuilder)charSequence2).toString());
            if (string2 == null || !string2.equals(string) || n > 0 || n2 > 0) {
                for (CharSequence charSequence2 : ((P0)object2).c) {
                    object2 = new StringBuilder();
                    ((StringBuilder)object2).append("hook scan: ");
                    ((StringBuilder)object2).append((String)charSequence2);
                    Log.i((String)"SoulHook102", (String)((StringBuilder)object2).toString());
                }
            }
            if (!bl) return;
            object2 = object.getApplication();
            object = new O0((Application)object2, string2);
            object2.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)object);
            return;
        }
        Log.e((String)"SoulHook102", (String)"hook compatibility scan failed", (Throwable)throwable2);
    }

    public static TextView g(q2 q22, String string) {
        Context context = q22.g;
        TextView textView = new TextView(context);
        textView.setText((CharSequence)string);
        textView.setTextSize((float)15);
        textView.setTextColor(q22.i);
        textView.setGravity(17);
        textView.setMinHeight(Q0.p(context, 48));
        textView.setFocusable(true);
        textView.setContentDescription((CharSequence)string);
        Q0.Y(q22, textView, false);
        return textView;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void h(View view, ArrayList arrayList) {
        Rect rect = new Rect();
        if (!view.isShown()) return;
        if (!view.getGlobalVisibleRect(rect)) {
            return;
        }
        if (C.isInstance(view)) {
            try {
                Object object = G.invoke(view, null);
                if (D.isInstance(object)) {
                    W0 w0 = new W0(view, object);
                    arrayList.add(w0);
                    return;
                }
            }
            catch (ReflectiveOperationException reflectiveOperationException) {}
        }
        if (!(view instanceof ViewGroup)) return;
        view = (ViewGroup)view;
        int n = 0;
        while (n < view.getChildCount()) {
            Q0.h(view.getChildAt(n), arrayList);
            ++n;
        }
    }

    public static I2 i(View view) {
        View view2;
        Object object = view2 = null;
        if (view != null) {
            if (!(view.getParent() instanceof ViewGroup)) {
                object = view2;
            } else {
                object = ((ViewGroup)view.getParent()).findViewWithTag((Object)"new.soul.square.like");
                if (!(object instanceof E2)) {
                    return null;
                }
                I2 i2 = ((E2)((Object)object)).a;
                object = view2;
                if (i2.b == view) {
                    object = i2;
                }
            }
        }
        return object;
    }

    public static void j(XposedModule xposedModule, Method object) {
        try {
            xposedModule.deoptimize((Executable)object);
        }
        catch (Throwable throwable) {
            String string = ((Method)object).getName();
            object = new StringBuilder("deopt ");
            ((StringBuilder)object).append(string);
            Q0.Z(((StringBuilder)object).toString(), throwable);
        }
    }

    public static void k(XposedModule object, Method object2, String string) {
        try {
            boolean bl = object.deoptimize((Executable)object2);
            object = string.concat(".deoptimize");
            String string2 = ((Method)object2).getDeclaringClass().getName();
            String string3 = ((Method)object2).getName();
            object2 = new StringBuilder("success=");
            ((StringBuilder)object2).append(bl);
            ((StringBuilder)object2).append("; method=");
            ((StringBuilder)object2).append(string2);
            ((StringBuilder)object2).append(".");
            ((StringBuilder)object2).append(string3);
            dev.local.newsoul.V.j((String)object, ((StringBuilder)object2).toString());
        }
        catch (Throwable throwable) {
            dev.local.newsoul.V.i(string.concat(".deoptimize"), throwable);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void l(XposedModule xposedModule, ClassLoader object, String string, String string2) {
        Throwable throwable2;
        block4: {
            int n;
            Method[] methodArray;
            try {
                methodArray = ((ClassLoader)object).loadClass(string).getDeclaredMethods();
                n = methodArray.length;
            }
            catch (Throwable throwable2) {
                break block4;
            }
            for (int i = 0; i < n; ++i) {
                object = methodArray[i];
                if (string2 != null && !string2.equals(((Method)object).getName()) || Modifier.isAbstract(((Method)object).getModifiers()) || Modifier.isNative(((Method)object).getModifiers())) continue;
                Q0.j(xposedModule, (Method)object);
            }
            return;
        }
        Q0.Z("callers ".concat(string), throwable2);
    }

    public static void m(Class methodArray) {
        for (Method method : methodArray.getDeclaredMethods()) {
            int n = method.getModifiers();
            if (Modifier.isAbstract(n) || Modifier.isNative(n)) continue;
            S.deoptimize((Executable)method);
        }
    }

    public static void n(XposedModule xposedModule, Activity object, Intent intent, String string) {
        if (intent != null && w2.o((Context)object).getBoolean("block_splash", true)) {
            intent.putExtra("display_ad", false);
            object = new StringBuilder("new soul: ");
            ((StringBuilder)object).append(string);
            ((StringBuilder)object).append(" display_ad=false");
            object = ((StringBuilder)object).toString();
            Log.i((String)"SoulHook102", (String)object);
            xposedModule.log(4, "SoulHook102", (String)object);
        }
    }

    public static int o(Activity activity, int n) {
        return Math.round((float)n * activity.getResources().getDisplayMetrics().density);
    }

    public static int p(Context context, int n) {
        return Math.round((float)n * context.getResources().getDisplayMetrics().density);
    }

    public static boolean q(String string) {
        try {
            boolean bl = w2.o((Context)d.invoke(null, null)).getBoolean(string, true);
            return bl;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static void r(String string, String string2) {
        if (!dev.local.newsoul.V.a()) {
            return;
        }
        Object object = (z_0)dev.local.newsoul.A.b.get();
        long l = object == null ? 0L : ((z_0)object).a;
        object = new StringBuilder("trace=");
        ((StringBuilder)object).append(l);
        ((StringBuilder)object).append("; ");
        ((StringBuilder)object).append(string2);
        dev.local.newsoul.V.d(string, ((StringBuilder)object).toString());
    }

    public static void s(String string, Throwable object) {
        Object object2 = (z_0)dev.local.newsoul.A.b.get();
        long l = object2 == null ? 0L : ((z_0)object2).a;
        object2 = new StringBuilder("trace=");
        ((StringBuilder)object2).append(l);
        object2 = ((StringBuilder)object2).toString();
        object = dev.local.newsoul.V.c((Throwable)object);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)object2);
        stringBuilder.append("\n");
        stringBuilder.append((String)object);
        dev.local.newsoul.V.d(string, stringBuilder.toString());
    }

    public static W0 t(View view) {
        boolean bl = B;
        Object object = null;
        Rect rect = null;
        Object object2 = object;
        if (bl) {
            object2 = object;
            if (view != null) {
                if (!view.isShown()) {
                    object2 = object;
                } else {
                    object2 = new ArrayList();
                    Q0.h(view, object2);
                    object = object2.iterator();
                    long l = 0L;
                    view = rect;
                    while (true) {
                        long l2;
                        object2 = view;
                        if (!object.hasNext()) break;
                        object2 = (W0)object.next();
                        rect = new Rect();
                        if (!object2.a.getGlobalVisibleRect(rect) || (l2 = (long)rect.width() * (long)rect.height()) <= l) continue;
                        view = object2;
                        l = l2;
                    }
                }
            }
        }
        return object2;
    }

    public static boolean u(Object object) {
        boolean bl = (Integer)o.invoke(object = n.invoke(object, null), null) != 9 && t.invoke(object, null) != null;
        return bl;
    }

    public static boolean v(Object object) {
        boolean bl;
        boolean bl2 = bl = false;
        if (object != null) {
            bl2 = bl;
            if (!((Boolean)s.invoke(object, null)).booleanValue()) {
                if (n.invoke(object, null) == null) {
                    bl2 = bl;
                } else {
                    String string = (String)m.invoke(null, null);
                    String string2 = (String)p.invoke(object, null);
                    object = (String)q.invoke(object, null);
                    bl2 = bl;
                    if (string != null) {
                        bl2 = bl;
                        if (!string.isEmpty()) {
                            bl2 = bl;
                            if (string2 != null) {
                                bl2 = bl;
                                if (!string2.isEmpty()) {
                                    bl2 = bl;
                                    if (!string2.equals(string)) {
                                        bl2 = bl;
                                        if (string.equals(object)) {
                                            bl2 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bl2;
    }

    public static void w(String string) {
        Log.i((String)"SoulHook102", (String)string);
        S.log(4, "SoulHook102", string);
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void x(XposedModule xposedModule, ClassLoader object) {
        Throwable throwable3;
        block6: {
            Object object2;
            GenericDeclaration genericDeclaration;
            Method method;
            Method method2;
            Object object3;
            Object object4;
            try {
                d = ((ClassLoader)object).loadClass("lf.b").getDeclaredMethod("b", null);
                object4 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.msg.ImMessage");
                object3 = ((Class)object4).getMethod("getChatMessage", null);
                method2 = ((Class)object4).getMethod("getMsgType", null);
                method = ((ClassLoader)object).loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("getMsgType", null);
                genericDeclaration = ((ClassLoader)object).loadClass("cn.soulapp.imlib.ChatManager");
                Method method3 = ((Class)genericDeclaration).getDeclaredMethod("p0", new Class[]{object4, Boolean.TYPE});
                object2 = xposedModule.hook((Executable)method3).setId("new-soul/hide-typing").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                e_0 e_02 = new e_0(method2, (Method)object3, method);
                object2.intercept((XposedInterface.Hooker)e_02);
                xposedModule.deoptimize((Executable)((Class)genericDeclaration).getDeclaredMethod("o0", new Class[]{object4}));
                xposedModule.deoptimize((Executable)method3);
                b = true;
                Log.i((String)"SoulHook102", (String)"typing command control installed");
            }
            catch (Throwable throwable2) {
                xposedModule.log(6, "SoulHook102", "typing control unavailable", throwable2);
            }
            try {
                object = ((ClassLoader)object).loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper");
                method2 = ((Class)object).getDeclaredMethod("k", null);
                genericDeclaration = ((Class)object).getDeclaredMethod("onPrepared", MediaPlayer.class);
                for (int i = 0; i < 2; ++i) {
                    method = (new Method[]{method2, genericDeclaration})[i];
                    object2 = xposedModule.hook((Executable)method);
                    object4 = method.getName();
                    object3 = new StringBuilder();
                    ((StringBuilder)object3).append("new-soul/match-music-");
                    ((StringBuilder)object3).append((String)object4);
                    object3 = object2.setId(((StringBuilder)object3).toString()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                    object2 = new a_0(3);
                    object3.intercept((XposedInterface.Hooker)object2);
                    xposedModule.deoptimize((Executable)method);
                }
            }
            catch (Throwable throwable3) {
                break block6;
            }
            {
                xposedModule.deoptimize((Executable)((Class)object).getDeclaredMethod("l", null));
                c = true;
                Log.i((String)"SoulHook102", (String)"soul match music control installed");
                return;
            }
        }
        xposedModule.log(6, "SoulHook102", "match music control unavailable", throwable3);
    }

    public static void y(ClassLoader object, Class clazz) {
        try {
            C = ((ClassLoader)object).loadClass("androidx.recyclerview.widget.RecyclerView");
            D = ((ClassLoader)object).loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
            E = ((ClassLoader)object).loadClass("io.reactivex.functions.Consumer");
            object = ((ClassLoader)object).loadClass("cn.soulapp.android.component.square.post.b1");
            F = ((Class)object).getConstructor(clazz);
            I = ((Class)object).getMethod("h", Integer.TYPE);
            G = C.getMethod("getAdapter", null);
            H = D.getMethod("getData", null);
            K = D.getMethod("notifyDataSetChanged", null);
            clazz = I.getReturnType();
            object = E;
            J = clazz.getMethod("subscribe", new Class[]{object, object});
            B = true;
        }
        catch (Throwable throwable) {
            Log.e((String)"SoulHook102", (String)"loaded post likes unavailable", (Throwable)throwable);
        }
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void z(XposedModule var0, ClassLoader var1_1) {
        block22: {
            block26: {
                block23: {
                    block25: {
                        block24: {
                            try {
                                var10_2 /* !! */  = var1_1.loadClass("cn.soulapp.android.lib.common.bean.ChatLimitModel");
                                Q0.i = var10_2 /* !! */ .getMethod("getType", null);
                                Q0.j = var10_2 /* !! */ .getMethod("getItemIdentity", null);
                                var10_2 /* !! */  = var10_2 /* !! */ .getMethod("isLimit", null);
                                var12_8 = var0.hook(var10_2 /* !! */ ).setId("new-soul/gift-chat-model").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                                var11_9 /* !! */  = new a_0(5);
                                var12_8.intercept((XposedInterface.Hooker)var11_9 /* !! */ );
                                Q0.e = true;
                                Q0.j((XposedModule)var0, (Method)var10_2 /* !! */ );
                            }
                            catch (Throwable var10_3) {
                                Q0.Z("model", var10_3);
                            }
                            try {
                                var11_9 /* !! */  = var1_1.loadClass("cn.soulapp.imlib.Conversation").getMethod("getIntExt", new Class[]{String.class});
                                var12_8 = var0.hook((Executable)var11_9 /* !! */ ).setId("new-soul/gift-chat-session").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                                var10_2 /* !! */  = new a_0(6);
                                var12_8.intercept((XposedInterface.Hooker)var10_2 /* !! */ );
                                Q0.g = true;
                                Q0.j((XposedModule)var0, (Method)var11_9 /* !! */ );
                            }
                            catch (Throwable var10_4) {
                                Q0.Z("session", var10_4);
                            }
                            if (!Q0.e) break block23;
                            var15_10 = var1_1.loadClass("cn.soulapp.android.lib.common.bean.ChatLimitModel");
                            var16_11 = var1_1.loadClass("cn.soulapp.imlib.Conversation");
                            var13_12 = var1_1.loadClass("cn.soulapp.android.component.chat.dialog.LimitGiftDialog$OnStartGiftSendListener");
                            var14_13 = Boolean.TYPE;
                            var17_14 = var1_1.loadClass("cn.soulapp.android.component.chat.utils.ChatUtils").getDeclaredMethods();
                            var3_15 = var17_14.length;
                            var10_2 /* !! */  = null;
                            for (var2_16 = 0; var2_16 < var3_15; ++var2_16) {
                                var12_8 = var17_14[var2_16];
                                var11_9 /* !! */  = var10_2 /* !! */ ;
                                if (Modifier.isStatic(var12_8.getModifiers())) {
                                    var11_9 /* !! */  = var10_2 /* !! */ ;
                                    if (var12_8.getReturnType() == Void.TYPE) {
                                        var11_9 /* !! */  = var10_2 /* !! */ ;
                                        if (Arrays.equals(var12_8.getParameterTypes(), new Class[]{var15_10, var16_11, String.class, String.class, var13_12, var14_13})) {
                                            if (var10_2 /* !! */  != null) {
                                                var10_2 /* !! */  = new IllegalStateException("ambiguous gift dialog entry");
                                                throw var10_2 /* !! */ ;
                                            }
                                            var11_9 /* !! */  = var12_8;
                                        }
                                    }
                                }
                                var10_2 /* !! */  = var11_9 /* !! */ ;
                                continue;
                            }
                            if (var10_2 /* !! */  == null) break block24;
                            try {
                                var12_8 = var0.hook((Executable)var10_2 /* !! */ ).setId("new-soul/gift-chat-dialog").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                                var11_9 /* !! */  = new a_0(8);
                                var12_8.intercept(var11_9 /* !! */ );
                                Q0.h = true;
                                Q0.j((XposedModule)var0, var10_2 /* !! */ );
                            }
                            catch (Throwable var10_5) {}
                            break block25;
                        }
                        var10_2 /* !! */  = new NoSuchMethodException("gift dialog signature");
                        throw var10_2 /* !! */ ;
                    }
                    Q0.Z("dialog", var10_5);
                }
                var10_2 /* !! */  = var1_1.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig");
                var13_12 = var10_2 /* !! */ .getDeclaredMethod("getShowPopup", null);
                if (var13_12.getReturnType() != Boolean.class && var13_12.getReturnType() != Boolean.TYPE) {
                    var10_2 /* !! */  = new NoSuchMethodException("unexpected getShowPopup return type");
                    throw var10_2 /* !! */ ;
                }
                var12_8 = var0.hook((Executable)var13_12).setId("new-soul/bell-gift-popup-config").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                var11_9 /* !! */  = new a_0(7);
                var12_8.intercept((XposedInterface.Hooker)var11_9 /* !! */ );
                Q0.f = true;
                Q0.j((XposedModule)var0, (Method)var13_12);
                {
                    catch (Throwable var10_6) {}
                }
                var11_9 /* !! */  = var1_1.loadClass("cn.soulapp.android.component.home.lovebell.LoveBellProGiftDialog").getDeclaredClasses();
                var4_17 = var11_9 /* !! */ .length;
                break block26;
                Q0.Z("bell popup config", var10_6);
                break block22;
            }
            ** for (var2_16 = 0;
            ; var2_16 < var4_17; ++var2_16)
lbl-1000:
            // 3 sources

            {
                for (Object var13_12 : var11_9 /* !! */ [var2_16].getDeclaredMethods()) {
                    try {
                        if (!Arrays.asList(var13_12.getParameterTypes()).contains(var10_2 /* !! */ )) continue;
                        Q0.j((XposedModule)var0, (Method)var13_12);
                    }
                    catch (Throwable var10_7) {
                        ** GOTO lbl-1000
                    }
                }
                continue;
lbl-1000:
                // 1 sources

                {
                    Q0.Z("bell callers", var10_7);
                    break;
                }
            }
        }
        Q0.l((XposedModule)var0, var1_1, "cn.soulapp.android.component.chat.style.BaseConversationStyleProcess", "checkLimitOpt");
        Q0.l((XposedModule)var0, var1_1, "cn.soulapp.android.component.chat.helper.MsgLimitManager", null);
        Q0.l((XposedModule)var0, var1_1, "cn.soulapp.android.component.chat.fragment.ConversationFragment", "v4");
        var6_19 = Q0.e;
        var7_20 = Q0.g;
        var9_21 = Q0.h;
        var8_22 = Q0.f;
        var0 = new StringBuilder("gift chat hooks: model=");
        var0.append(var6_19);
        var0.append("; session=");
        var0.append(var7_20);
        var0.append("; dialog=");
        var0.append(var9_21);
        var0.append("; bell=");
        var0.append(var8_22);
        Log.i((String)"SoulHook102", (String)var0.toString());
    }
}

