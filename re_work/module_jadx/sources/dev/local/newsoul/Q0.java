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
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
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
            Method declaredMethod = classLoader.loadClass("lf.b").getDeclaredMethod("b", null);
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.utils.ImageDownloader");
            Class cls = Boolean.TYPE;
            H(xposedModule, clsLoadClass.getDeclaredMethod("f", String.class, cls, cls, classLoader.loadClass("cn.soulapp.android.square.utils.ImageMarkProcessor"), classLoader.loadClass("cn.soulapp.android.square.utils.b")), "download_image_no_watermark", 2, -1, declaredMethod);
            L = true;
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "image watermark control unavailable", th);
        }
        try {
            H(xposedModule, classLoader.loadClass("cn.soulapp.android.square.utils.h2").getDeclaredMethod("b", String.class, Context.class, Long.TYPE, Boolean.TYPE), "download_video_no_watermark", 3, 1, null);
            M = true;
        } catch (Throwable th2) {
            xposedModule.log(6, "SoulHook102", "video watermark control unavailable", th2);
        }
    }

    public static void B(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment");
            Class<?> clsLoadClass3 = classLoader.loadClass(clsLoadClass2.getName().concat("$Operate"));
            Field declaredField = clsLoadClass.getDeclaredField("E");
            Field declaredField2 = clsLoadClass2.getDeclaredField("q");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            Field field = classLoader.loadClass("cn.soulapp.android.square.post.bean.Post").getField("content");
            Field field2 = clsLoadClass3.getField("a");
            I(xposedModule, classLoader, clsLoadClass3, field2);
            Class cls = Integer.TYPE;
            Constructor<?> constructor = clsLoadClass3.getConstructor(cls, String.class, Boolean.TYPE);
            Method declaredMethod = clsLoadClass.getDeclaredMethod("initViews", View.class);
            Method declaredMethod2 = clsLoadClass.getDeclaredMethod("P0", clsLoadClass3, View.class, cls);
            Method method = clsLoadClass.getMethod("dismiss", null);
            XposedInterface.HookBuilder id = xposedModule.hook(declaredMethod2).setId("new-soul/post-copy-click");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new C0004b(field2, declaredField, field, method, 1));
            xposedModule.hook(declaredMethod).setId("new-soul/post-copy-action").setExceptionMode(exceptionMode).intercept(new C0022f1(declaredField, declaredField2, field2, constructor, 1));
            xposedModule.deoptimize(declaredMethod2);
            N = true;
            Log.i("SoulHook102", "post copy hooks installed");
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "post copy hooks unavailable", th);
        }
    }

    public static void C(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment");
            Field declaredField = clsLoadClass.getDeclaredField("E");
            if (declaredField.getType() != classLoader.loadClass("cn.soulapp.android.square.post.bean.Post")) {
                throw new NoSuchFieldException("Unexpected share post field");
            }
            declaredField.setAccessible(true);
            Method declaredMethod = clsLoadClass.getDeclaredMethod("r0", null);
            Method declaredMethod2 = clsLoadClass.getDeclaredMethod("initViews", View.class);
            if (declaredMethod.getReturnType() != Void.TYPE || !Modifier.isPrivate(declaredMethod.getModifiers())) {
                throw new NoSuchMethodException("Unexpected share permission signature");
            }
            xposedModule.hook(declaredMethod).setId("new-soul/post-share-permission").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0042k1(declaredField, 0));
            xposedModule.deoptimize(declaredMethod2);
            O = true;
            Log.i("SoulHook102", "post share control installed");
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "post share control unavailable", th);
        }
    }

    public static void D(XposedModule xposedModule, ClassLoader classLoader) {
        S = xposedModule;
        T = classLoader;
        try {
            xposedModule.hook(classLoader.loadClass("cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity").getDeclaredMethod("init", Bundle.class)).setId("new-soul/privacy-photo-preview").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(16));
            P = true;
            w("privacy photo controls installed");
        } catch (Throwable th) {
            S.log(6, "SoulHook102", "privacy photo controls unavailable", th);
        }
        Q = J(classLoader, "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", "privacy");
        R = J(classLoader, "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", "snap");
    }

    public static void E(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.chat.bean.SocialGraceScoreBean");
            String[] strArr = {"getCanNotSendBeforeReply", "getCreateSessionLimit", "getForbidCall", "getForbidPic"};
            Method[] methodArr = new Method[4];
            for (int i2 = 0; i2 < 4; i2++) {
                Method declaredMethod = clsLoadClass.getDeclaredMethod(strArr[i2], null);
                methodArr[i2] = declaredMethod;
                if (declaredMethod.getReturnType() != Boolean.TYPE) {
                    throw new NoSuchMethodException("Unexpected social score gate " + strArr[i2]);
                }
            }
            for (int i3 = 0; i3 < 4; i3++) {
                Method method = methodArr[i3];
                xposedModule.hook(method).setId("new-soul/social-score-" + method.getName()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(2, method));
            }
            String[] strArr2 = {"uj.c0", "uj.c0$q", "cn.soulapp.android.component.chat.fragment.ConversationFragment$d1"};
            for (int i4 = 0; i4 < 3; i4++) {
                String str = strArr2[i4];
                for (Method method2 : classLoader.loadClass(str).getDeclaredMethods()) {
                    if (Arrays.asList(method2.getParameterTypes()).contains(clsLoadClass) || str.endsWith("$d1")) {
                        xposedModule.deoptimize(method2);
                    }
                }
            }
            U = true;
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "social score model unavailable", th);
        }
        try {
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils");
            Method declaredMethod2 = clsLoadClass2.getDeclaredMethod("b", classLoader.loadClass("androidx.fragment.app.FragmentManager"), classLoader.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a"));
            if (!Modifier.isStatic(declaredMethod2.getModifiers()) || declaredMethod2.getReturnType() != Void.TYPE) {
                throw new NoSuchMethodException("Unexpected social score dialog signature");
            }
            xposedModule.hook(declaredMethod2).setId("new-soul/social-score-dialog").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(18));
            for (Method method3 : clsLoadClass2.getDeclaredMethods()) {
                xposedModule.deoptimize(method3);
            }
            V = true;
            Log.i("SoulHook102", "social score hooks: model=" + U + "; dialog=" + V);
        } catch (Throwable th2) {
            xposedModule.log(6, "SoulHook102", "social score dialog unavailable", th2);
        }
    }

    public static void F(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.post.bean.Post");
            Z = clsLoadClass;
            a0 = clsLoadClass.getField("id");
            b0 = Z.getMethod("hasDeclare", null);
            y(classLoader, Z);
            c0 = Z.getMethod("getAdResponse", null);
            d0 = Z.getField("incentiveModel");
            XposedInterface.HookBuilder id = xposedModule.hook(classLoader.loadClass("cn.soulapp.android.component.startup.main.MainActivity").getDeclaredMethod("onCreate", Bundle.class)).setId("new-soul/square-like-lifecycle");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new C0000a(19));
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.component.square.widget.SquareFloatingButton");
            Class cls = Integer.TYPE;
            xposedModule.hook(clsLoadClass2.getDeclaredConstructor(Context.class, AttributeSet.class, cls)).setId("new-soul/square-like-envelope-create").setExceptionMode(exceptionMode).intercept(new C0000a(20));
            xposedModule.hook(clsLoadClass2.getDeclaredMethod("onVisibilityChanged", View.class, cls)).setId("new-soul/square-like-envelope-visibility").setExceptionMode(exceptionMode).intercept(new C0000a(21));
            Y = true;
        } catch (Throwable th) {
            Log.e("SoulHook102", "square like unavailable", th);
            xposedModule.log(6, "SoulHook102", "square like unavailable", th);
        }
    }

    public static void G(XposedModule xposedModule, Class cls, String str, Class[] clsArr, final boolean z2, final int i2, final int i3) {
        final String str2 = z2 ? "repeat.caller" : "repeat.show";
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod.getReturnType() != Void.TYPE || Modifier.isStatic(declaredMethod.getModifiers()) == z2) {
                throw new NoSuchMethodException("Unexpected boundary signature");
            }
            xposedModule.hook(declaredMethod).setId("new-soul/".concat(str2)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.v
                public final Object intercept(XposedInterface.Chain chain) {
                    String str3 = str2;
                    int i4 = i2;
                    if (!V.a()) {
                        return chain.proceed();
                    }
                    int i5 = i3;
                    Object arg = i5 < 0 ? null : chain.getArg(i5);
                    boolean z3 = z2;
                    C0095y c0095yA = A.a(z3, arg);
                    C0099z c0099z = c0095yA.a;
                    if (!z3) {
                        try {
                            c0099z.c++;
                            A.b(arg);
                        } catch (Throwable th) {
                            try {
                                c0095yA.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    String str4 = str3 + ".enter";
                    String strO = Q0.O(chain.getArg(i4));
                    int size = arg instanceof List ? ((List) arg).size() : -1;
                    Q0.r(str4, strO + "; count=" + size + "; source=" + Q0.X());
                    try {
                        Object objProceed = chain.proceed();
                        Q0.r(str3 + ".exit", c0099z.a());
                        c0095yA.close();
                        return objProceed;
                    } catch (Throwable th3) {
                        try {
                            Q0.s(str3 + ".host_failed", th3);
                            throw th3;
                        } catch (Throwable th4) {
                            Q0.r(str3 + ".exit", c0099z.a());
                            throw th4;
                        }
                    }
                }
            });
            V.i(str2, null);
            k(xposedModule, declaredMethod, str2);
        } catch (Throwable th) {
            V.i(str2, th);
        }
    }

    public static void H(final XposedModule xposedModule, Method method, final String str, final int i2, final int i3, final Method method2) {
        if (!Modifier.isStatic(method.getModifiers()) || method.getReturnType() != Void.TYPE) {
            throw new IllegalStateException("Unexpected download signature: ".concat(String.valueOf(method)));
        }
        xposedModule.hook(method).setId("new-soul/".concat(str)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.X0
            public final Object intercept(XposedInterface.Chain chain) {
                Method method3 = method2;
                String str2 = str;
                int i4 = i3;
                try {
                    Context context = (Context) (i4 >= 0 ? chain.getArg(i4) : method3.invoke(null, null));
                    if (context != null) {
                        WeakHashMap weakHashMap = AbstractC0090w2.a;
                        if (context.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getBoolean(str2, true)) {
                            Object[] array = chain.getArgs().toArray();
                            Boolean bool = Boolean.FALSE;
                            int i5 = i2;
                            array[i5] = bool;
                            Log.i("SoulHook102", "watermark bypass: " + str2 + "; original=" + String.valueOf(chain.getArg(i5)));
                            return chain.proceed(array);
                        }
                    }
                } catch (Throwable th) {
                    xposedModule.log(5, "SoulHook102", "download preference unavailable", th);
                }
                return chain.proceed();
            }
        });
        xposedModule.log(4, "SoulHook102", "watermark control installed: ".concat(str));
    }

    public static void I(XposedModule xposedModule, ClassLoader classLoader, Class cls, Field field) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a");
            Method declaredMethod = clsLoadClass.getDeclaredMethod("a", cls);
            Field declaredField = clsLoadClass.getDeclaredField("c");
            declaredField.setAccessible(true);
            xposedModule.hook(declaredMethod).setId("new-soul/action-marks-post").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new B(declaredField, field, 1));
            xposedModule.deoptimize(declaredMethod);
        } catch (Throwable th) {
            Log.w("SoulHook102", "post action mark unavailable", th);
        }
    }

    public static boolean J(ClassLoader classLoader, String str, String str2, String str3) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str);
            Method declaredMethod = clsLoadClass.getDeclaredMethod(str2, null);
            if (declaredMethod.getReturnType() != Void.TYPE || Modifier.isStatic(declaredMethod.getModifiers())) {
                throw new NoSuchMethodException("Unexpected repeat guard signature: ".concat(String.valueOf(declaredMethod)));
            }
            S.hook(declaredMethod).setId("new-soul/privacy-repeat-".concat(str3)).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(1, str3));
            m(clsLoadClass);
            for (Class<?> cls : clsLoadClass.getDeclaredClasses()) {
                m(cls);
            }
            if ("snap".equals(str3)) {
                S.hook(clsLoadClass.getDeclaredMethod("init", Bundle.class)).setId("new-soul/privacy-repeat-snap-preview").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(17));
            }
            w("privacy repeat guard installed: route=" + str3);
            return true;
        } catch (Throwable th) {
            S.log(6, "SoulHook102", "privacy repeat guard unavailable: ".concat(str3), th);
            return false;
        }
    }

    public static void K(XposedModule xposedModule, ClassLoader classLoader, Class cls) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.chat.widget.AbsChatDualItem");
        Class<?> clsLoadClass2 = classLoader.loadClass(clsLoadClass.getName().concat("$ViewHolder"));
        Field field = clsLoadClass2.getField("itemView");
        Field declaredField = clsLoadClass.getDeclaredField("e");
        declaredField.setAccessible(true);
        Method method = null;
        for (Method method2 : clsLoadClass.getDeclaredMethods()) {
            Class<?>[] parameterTypes = method2.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == clsLoadClass2 && parameterTypes[1] == cls && parameterTypes[2] == Integer.TYPE && parameterTypes[3] == List.class) {
                if (method != null) {
                    throw new IllegalStateException("Ambiguous chat row binding");
                }
                method = method2;
            }
        }
        if (method == null) {
            throw new NoSuchMethodException("AbsChatDualItem bind");
        }
        xposedModule.hook(method).setId("new-soul/chat-recall-mark").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new B(field, declaredField, 0));
        xposedModule.deoptimize(method);
    }

    public static String L(Object obj) {
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest((String.valueOf(m.invoke(null, null)) + "\n" + String.valueOf(p.invoke(obj, null)) + "\n" + String.valueOf(q.invoke(obj, null)) + "\n" + String.valueOf(r.invoke(obj, null))).getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArrDigest) {
            sb.append(String.format(Locale.ROOT, "%02x", Integer.valueOf(b2 & 255)));
        }
        return sb.toString();
    }

    public static PublicKey M() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNOIwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/HRJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loOB0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk06ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCFQbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE=")));
    }

    public static boolean N(String str) {
        if (str == null) {
            return false;
        }
        try {
            URI uri = new URI(str);
            return ("https".equalsIgnoreCase(uri.getScheme()) || "http".equalsIgnoreCase(uri.getScheme())) && uri.getHost() != null && !uri.getHost().isEmpty() && uri.getUserInfo() == null;
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    public static String O(Object obj) {
        if (!V.a()) {
            return "";
        }
        if (obj == null) {
            return "message_present=false";
        }
        try {
            Object objInvoke = obj.getClass().getMethod("getChatMessage", null).invoke(obj, null);
            if (objInvoke == null) {
                return "message_present=true; chat_present=false";
            }
            Object objInvoke2 = objInvoke.getClass().getMethod("getMsgType", null).invoke(objInvoke, null);
            if (!(objInvoke2 instanceof Integer)) {
                objInvoke2 = "unknown";
            }
            return "message_present=true; type=".concat(String.valueOf(objInvoke2));
        } catch (Throwable th) {
            return "message_present=true; type=unavailable; error=".concat(th.getClass().getName());
        }
    }

    public static int P(String str, boolean z2) {
        try {
            int i2 = Integer.parseInt(str.trim());
            if (!z2 && i2 < 0) {
                throw new NumberFormatException();
            }
            return i2;
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException(z2 ? "请输入 -2147483648 至 2147483647 的整数" : "请输入 0 至 2147483647 的整数");
        }
    }

    public static int Q(ArrayList arrayList, ClassLoader classLoader, String str, String str2, String str3, Boolean bool, String... strArr) {
        Class<?> clsLoadClass;
        String str4 = str2 + "#" + str3 + "(" + TextUtils.join(",", strArr) + ")";
        try {
            Class<?> clsLoadClass2 = classLoader.loadClass(str2);
            int length = strArr.length;
            Class<?>[] clsArr = new Class[length];
            for (int i2 = 0; i2 < length; i2++) {
                String str5 = strArr[i2];
                if ("boolean".equals(str5)) {
                    clsLoadClass = Boolean.TYPE;
                } else if ("int".equals(str5)) {
                    clsLoadClass = Integer.TYPE;
                } else {
                    clsLoadClass = "long".equals(str5) ? Long.TYPE : classLoader.loadClass(str5);
                }
                clsArr[i2] = clsLoadClass;
            }
            try {
                clsLoadClass2.getDeclaredMethod(str3, clsArr);
            } catch (NoSuchMethodException unused) {
                clsLoadClass2.getMethod(str3, clsArr);
            }
            arrayList.add(str + "：" + (bool.booleanValue() ? "已注册 · 待功能实测" : "签名存在 · Hook 未就绪") + "\n  " + str4);
            return 0;
        } catch (Throwable th) {
            arrayList.add(str + "：Hook 点缺失（" + th.getClass().getSimpleName() + "）\n  " + str4);
            return 1;
        }
    }

    public static boolean R(Activity activity) {
        if (activity.getIntent().getBooleanExtra("isSender", false)) {
            return false;
        }
        WeakHashMap weakHashMap = AbstractC0090w2.a;
        return activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getBoolean("privacy_allow_repeat", false);
    }

    public static boolean S(Context context, Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (!v(obj2)) {
            return false;
        }
        Object objInvoke = n.invoke(obj2, null);
        if (((Integer) o.invoke(objInvoke, null)).intValue() == 9 && !"1".equals(u.invoke(objInvoke, "isSnap"))) {
            String str = (String) r.invoke(obj2, null);
            Object objInvoke2 = v.invoke(obj, p.invoke(obj2, null));
            Object objInvoke3 = objInvoke2 == null ? null : w.invoke(objInvoke2, str);
            if (objInvoke3 == null) {
                objInvoke3 = x.invoke(obj, str);
            }
            if (v(objInvoke3) && u(objInvoke3) && p.invoke(obj2, null).equals(p.invoke(objInvoke3, null)) && q.invoke(obj2, null).equals(q.invoke(objInvoke3, null)) && str.equals(r.invoke(objInvoke3, null))) {
                String strL = L(objInvoke3);
                SharedPreferences sharedPreferences = context.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0);
                if (!sharedPreferences.contains(strL) && !sharedPreferences.edit().putBoolean(strL, true).commit()) {
                    return false;
                }
                Log.i("SoulHook102", "recall retained; source=private; type=".concat(String.valueOf(o.invoke(n.invoke(objInvoke3, null), null))));
                return true;
            }
        }
        return false;
    }

    public static Uri T(Activity activity, Bitmap bitmap) throws Throwable {
        ContentResolver contentResolver = activity.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", "NewSoul_" + System.currentTimeMillis() + ".png");
        contentValues.put("mime_type", "image/png");
        contentValues.put("relative_path", "Pictures/New Soul");
        boolean z2 = true;
        contentValues.put("is_pending", (Integer) 1);
        Uri uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uriInsert == null) {
            return null;
        }
        try {
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert, "w");
            if (outputStreamOpenOutputStream != null) {
                try {
                    if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream)) {
                        try {
                            outputStreamOpenOutputStream.close();
                            contentValues.clear();
                            contentValues.put("is_pending", (Integer) 0);
                            contentResolver.update(uriInsert, contentValues, null, null);
                            return uriInsert;
                        } catch (Throwable th) {
                            th = th;
                            if (!z2) {
                                contentResolver.delete(uriInsert, null, null);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    if (outputStreamOpenOutputStream != null) {
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
            throw new IllegalStateException("Bitmap compression failed");
        } catch (Throwable th4) {
            th = th4;
            z2 = false;
        }
    }

    public static P0 U(Context context) {
        String str;
        ArrayList arrayList;
        int i2;
        int i3;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            long longVersionCode = Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
            str = packageInfo.versionName + " (" + longVersionCode + ")";
            while (true) {
                String[][] strArr = AbstractC0036j.a;
                if (i3 >= 19) {
                    break;
                }
                String[] strArr2 = strArr[i3];
                if (AbstractC0036j.b.contains(strArr2[0])) {
                    i2++;
                } else {
                    arrayList.add(strArr2[1] + "：Hook 未就绪 · 请查看 SoulHook102 安装日志");
                }
                i3++;
            }
        } catch (Exception unused) {
            str = "未知版本";
        }
        SharedPreferences sharedPreferencesO = AbstractC0090w2.o(context);
        String string = sharedPreferencesO.getString("hook_diagnostics_last_host_version", null);
        ClassLoader classLoader = context.getClassLoader();
        arrayList = new ArrayList();
        boolean z2 = true;
        int iQ = Q(arrayList, classLoader, "设置入口", "cn.soulapp.android.h5.activity.H5Activity", "m1", Boolean.valueOf(SoulHook.settingsHookReady), "boolean") + Q(arrayList, classLoader, "冷启动广告", "cn.soulapp.android.component.startup.main.MainScheduler", "v", Boolean.valueOf(W), new String[0]) + Q(arrayList, classLoader, "热启动广告", "tc.e$a", "j", Boolean.valueOf(X), "java.lang.String") + Q(arrayList, classLoader, "广告位识别", "fa.a", "b", Boolean.valueOf(!AbstractC0036j.b.isEmpty()), new String[0]);
        i2 = 0;
        i3 = 1;
        if (!AbstractC0061p0.a && !AbstractC0061p0.b && !AbstractC0061p0.c) {
            z2 = false;
        }
        String str2 = "Hook 已注册，待实测";
        String str3 = z2 ? "Hook 已注册，待实测" : "Hook 未就绪";
        arrayList.add("信息伪装 · " + str3 + "\n个人页 " + AbstractC0061p0.a + " / 新个人页 " + AbstractC0061p0.b + " / 旧个人页 " + AbstractC0061p0.c);
        if (!AbstractC0061p0.d && !AbstractC0061p0.e) {
            str2 = "Hook 未就绪";
        }
        arrayList.add("天数伪装 · " + str2 + "\n个人页 " + AbstractC0061p0.d + " / 新个人页 " + AbstractC0061p0.e);
        StringBuilder sb = new StringBuilder("广告子项已注册 ");
        sb.append(i2);
        sb.append("/18");
        arrayList.add(sb.toString());
        int iQ2 = iQ + Q(arrayList, classLoader, "自定义骰子", "cn.soulapp.android.component.chat.utils.y4", "w0", Boolean.valueOf(AbstractC0075t.a), new String[0]) + Q(arrayList, classLoader, "自定义猜拳", "cn.soulapp.android.component.chat.utils.y4", "h1", Boolean.valueOf(AbstractC0075t.b), new String[0]) + Q(arrayList, classLoader, "消息防撤回", "cn.soulapp.imlib.ChatManager", "O", Boolean.valueOf(k), "java.util.List") + Q(arrayList, classLoader, "自己撤回", "cn.soulapp.android.component.chat.helper.WithDrawHandler", "d", Boolean.valueOf(AbstractC0026g1.a), "cn.soulapp.imlib.msg.ImMessage") + Q(arrayList, classLoader, "隐藏输入状态", "cn.soulapp.imlib.ChatManager", "p0", Boolean.valueOf(b), "cn.soulapp.imlib.msg.ImMessage", "boolean") + Q(arrayList, classLoader, "匹配音乐", "cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper", "k", Boolean.valueOf(c), new String[0]) + Q(arrayList, classLoader, "聊天礼物限制", "cn.soulapp.android.lib.common.bean.ChatLimitModel", "isLimit", Boolean.valueOf(e), new String[0]) + Q(arrayList, classLoader, "奇遇铃送礼限制", "cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig", "getShowPopup", Boolean.valueOf(f), new String[0]) + Q(arrayList, classLoader, "聊天礼物会话额度", "cn.soulapp.imlib.Conversation", "getIntExt", Boolean.valueOf(g), "java.lang.String");
        arrayList.add("聊天礼物弹窗 · ".concat(h ? "签名匹配，Hook 已注册" : "Hook 未就绪"));
        int iQ3 = iQ2 + Q(arrayList, classLoader, "聊天复读/转发", "cn.soulapp.android.component.chat.utils.u0", "k", Boolean.valueOf(F.o), "cn.soulapp.android.component.chat.fragment.ConversationFragment", "android.view.View", "cn.soulapp.imlib.msg.ImMessage") + Q(arrayList, classLoader, "底栏长按设置", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Boolean.valueOf(AbstractC0056o.a), "android.os.Bundle") + Q(arrayList, classLoader, "消息转发选择", "cn.soulapp.android.component.chat.SelectConversationActivity", "F", Boolean.valueOf(F.p), "int") + Q(arrayList, classLoader, "语音库发送", "cn.soulapp.android.component.chat.utils.y4", "q2", Boolean.valueOf(d3.g), "java.lang.String", "int", "cn.soulapp.imlib.msg.ImMessage") + Q(arrayList, classLoader, "聊天隐私媒体", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "init", Boolean.valueOf(P), "android.os.Bundle") + Q(arrayList, classLoader, "隐私媒体多次查看", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", Boolean.valueOf(Q), new String[0]) + Q(arrayList, classLoader, "闪照多次查看", "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", Boolean.valueOf(R), new String[0]) + Q(arrayList, classLoader, "图片下载", "cn.soulapp.android.square.utils.ImageDownloader", "f", Boolean.valueOf(L), "java.lang.String", "boolean", "boolean", "cn.soulapp.android.square.utils.ImageMarkProcessor", "cn.soulapp.android.square.utils.b") + Q(arrayList, classLoader, "视频下载", "cn.soulapp.android.square.utils.h2", "b", Boolean.valueOf(M), "java.lang.String", "android.content.Context", "long", "boolean") + Q(arrayList, classLoader, "帖子分享限制", "cn.soulapp.android.square.SeedsShareDialogFragment", "r0", Boolean.valueOf(O), new String[0]) + Q(arrayList, classLoader, "礼仪分本地限制", "cn.soulapp.android.component.chat.bean.SocialGraceScoreBean", "getCreateSessionLimit", Boolean.valueOf(U), new String[0]) + Q(arrayList, classLoader, "礼仪分限制弹窗", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils", "b", Boolean.valueOf(V), "androidx.fragment.app.FragmentManager", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a") + Q(arrayList, classLoader, "帖子文案复制", "cn.soulapp.android.square.SeedsShareDialogFragment", "initViews", Boolean.valueOf(N), "android.view.View") + Q(arrayList, classLoader, "广场帖子筛选", "cn.soulapp.android.component.square.main.squarepost.SquareAdapter", "d", Boolean.valueOf(B2.i), "cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder", "java.lang.Object") + Q(arrayList, classLoader, "广场一键点赞", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Boolean.valueOf(Y), "android.os.Bundle") + Q(arrayList, classLoader, "已加载帖子点赞请求", "gs0.g", "subscribe", Boolean.valueOf(B), "io.reactivex.functions.Consumer", "io.reactivex.functions.Consumer");
        sharedPreferencesO.edit().putString("hook_diagnostics_last_host_version", str).apply();
        return new P0(str, string, arrayList, iQ3);
    }

    public static void V(Activity activity, C0067q2 c0067q2, ImageView imageView, TextView textView, TextView textView2, TextView textView3, String str, String str2) {
        Drawable drawable;
        try {
            Context contextCreatePackageContext = activity.createPackageContext("dev.local.newsoul.public", 2);
            int identifier = contextCreatePackageContext.getResources().getIdentifier(str, "drawable", contextCreatePackageContext.getPackageName());
            drawable = identifier == 0 ? null : contextCreatePackageContext.getResources().getDrawable(identifier, null);
        } catch (Throwable unused) {
        }
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(str2);
            imageView.setVisibility(0);
            textView.setVisibility(8);
            Y(c0067q2, textView2, true);
            Y(c0067q2, textView3, false);
            return;
        }
        textView.setVisibility(0);
        imageView.setImageDrawable(null);
        imageView.setVisibility(8);
        textView.setText("收款码加载失败，请重新打开此页面");
        textView.setTextColor(c0067q2.j);
        Y(c0067q2, textView2, false);
        Y(c0067q2, textView3, false);
    }

    public static void W(Activity activity, final W0 w0, Object obj, final H2 h2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final int i2 = 0;
        final int i3 = 1;
        J.invoke(I.invoke(F.newInstance(obj), 0), Proxy.newProxyInstance(E.getClassLoader(), new Class[]{E}, new V0(activity, new Runnable() { // from class: dev.local.newsoul.U0
            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        W0 w1 = w0;
                        w1.getClass();
                        try {
                            Q0.K.invoke(w1.b, null);
                        } catch (ReflectiveOperationException e2) {
                            Log.w("SoulHook102", "loaded like UI refresh failed", e2);
                        }
                        h2.b(true);
                        break;
                    default:
                        W0 w2 = w0;
                        w2.getClass();
                        try {
                            Q0.K.invoke(w2.b, null);
                        } catch (ReflectiveOperationException e3) {
                            Log.w("SoulHook102", "loaded like UI refresh failed", e3);
                        }
                        h2.b(false);
                        break;
                }
            }
        })), Proxy.newProxyInstance(E.getClassLoader(), new Class[]{E}, new V0(activity, new Runnable() { // from class: dev.local.newsoul.U0
            @Override // java.lang.Runnable
            public final void run() {
                switch (i3) {
                    case 0:
                        W0 w1 = w0;
                        w1.getClass();
                        try {
                            Q0.K.invoke(w1.b, null);
                        } catch (ReflectiveOperationException e2) {
                            Log.w("SoulHook102", "loaded like UI refresh failed", e2);
                        }
                        h2.b(true);
                        break;
                    default:
                        W0 w2 = w0;
                        w2.getClass();
                        try {
                            Q0.K.invoke(w2.b, null);
                        } catch (ReflectiveOperationException e3) {
                            Log.w("SoulHook102", "loaded like UI refresh failed", e3);
                        }
                        h2.b(false);
                        break;
                }
            }
        })));
    }

    public static String X() {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (!className.startsWith("dev.local.newsoul.") && !className.equals(Thread.class.getName()) && (className.startsWith("cn.soulapp.") || className.startsWith("uj.") || className.contains("patch"))) {
                if (sb.length() > 0) {
                    sb.append(" <- ");
                }
                sb.append(className);
                sb.append('.');
                sb.append(stackTraceElement.getMethodName());
                sb.append(':');
                sb.append(stackTraceElement.getLineNumber());
                i2++;
                if (i2 == 6) {
                    break;
                }
            }
        }
        return sb.length() == 0 ? "unavailable" : sb.toString();
    }

    public static void Y(C0067q2 c0067q2, TextView textView, boolean z2) {
        c0067q2.c.b(textView, 5, 10.0f);
        textView.setSelected(z2);
    }

    public static void Z(String str, Throwable th) {
        Log.w("SoulHook102", "gift chat " + str + " unavailable", th);
    }

    public static boolean a(Object obj) {
        return c0.invoke(obj, null) == null && d0.get(obj) == null;
    }

    public static WebView a0(View view) {
        if (view instanceof WebView) {
            return (WebView) view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 >= viewGroup.getChildCount()) {
                return null;
            }
            WebView webViewA0 = a0(viewGroup.getChildAt(i2));
            if (webViewA0 != null) {
                return webViewA0;
            }
            i2++;
        }
    }

    public static boolean b(View view) {
        if (view == null || !view.isAttachedToWindow() || !view.isShown() || view.getAlpha() == 0.0f) {
            return false;
        }
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect) && rect.width() > 0 && rect.height() > 0;
    }

    public static boolean c(float f2, float f3, float f4, boolean z2) {
        if (z2 || f3 <= (-f4)) {
            return false;
        }
        return (f3 >= f4 && f2 >= 0.02f) || f2 >= 0.28f;
    }

    public static void d(View view) {
        if (view != null) {
            try {
                if (view.isAttachedToWindow() && (view.getParent() instanceof RelativeLayout)) {
                    Context context = view.getContext();
                    while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                    if (context instanceof Activity) {
                        Activity activity = (Activity) context;
                        if ("cn.soulapp.android.component.startup.main.MainActivity".equals(activity.getClass().getName()) && view.getId() == activity.getResources().getIdentifier("message_button", "id", activity.getPackageName())) {
                            I2 i2I = i(view);
                            if (i2I != null) {
                                i2I.i();
                                return;
                            }
                            RelativeLayout relativeLayout = (RelativeLayout) view.getParent();
                            I2 i2 = new I2(activity, view);
                            E2 e2 = i2.c;
                            e2.setTag("new.soul.square.like");
                            float f2 = 68;
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(activity.getResources().getDisplayMetrics().density * f2), Math.round(f2 * activity.getResources().getDisplayMetrics().density));
                            layoutParams.addRule(2, view.getId());
                            layoutParams.addRule(7, view.getId());
                            relativeLayout.addView(e2, layoutParams);
                            i2.i();
                            Log.i("SoulHook102", "square like attached beside envelope; native parent=RelativeLayout");
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e("SoulHook102", "square like envelope attachment failed", th);
            }
        }
    }

    public static boolean e(Object obj) {
        if (obj == null) {
            return false;
        }
        int iIntValue = ((Integer) i.invoke(obj, null)).intValue();
        String str = (String) j.invoke(obj, null);
        boolean z2 = (str == null || str.isEmpty()) ? false : true;
        if (iIntValue < 0 || iIntValue >= 19 || iIntValue == 6) {
            return false;
        }
        if (iIntValue == 0 || iIntValue == 1 || iIntValue == 5 || iIntValue == 10 || iIntValue == 11 || iIntValue >= 14 || z2) {
            return q((iIntValue == 10 || iIntValue == 11) ? "chat_remove_bell_gift_gate" : "chat_remove_gift_gate");
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0031  */
    public static void f(Activity activity) {
        if (a) {
            return;
        }
        boolean z2 = true;
        a = true;
        try {
            P0 p0U = U(activity);
            int i2 = p0U.e;
            int i3 = p0U.d;
            String str = p0U.b;
            String string = activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getString("hook_diagnostics_last_shown_version", null);
            String str2 = p0U.a;
            if (string == null) {
                if (str2.startsWith("6.37.0")) {
                    z2 = false;
                }
            } else if (string.equals(str2)) {
                z2 = false;
            }
            Log.i("SoulHook102", "hook scan: host=" + str2 + "; baseline=6.37.0; previous=" + str + "; missing=" + i3 + "; unready=" + i2);
            if (str == null || !str.equals(str2) || i3 > 0 || i2 > 0) {
                Iterator it = p0U.c.iterator();
                while (it.hasNext()) {
                    Log.i("SoulHook102", "hook scan: " + ((String) it.next()));
                }
            }
            if (z2) {
                Application application = activity.getApplication();
                application.registerActivityLifecycleCallbacks(new O0(application, str));
            }
        } catch (Throwable th) {
            Log.e("SoulHook102", "hook compatibility scan failed", th);
        }
    }

    public static TextView g(C0067q2 c0067q2, String str) {
        Context context = c0067q2.g;
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(15);
        textView.setTextColor(c0067q2.i);
        textView.setGravity(17);
        textView.setMinHeight(p(context, 48));
        textView.setFocusable(true);
        textView.setContentDescription(str);
        Y(c0067q2, textView, false);
        return textView;
    }

    public static void h(View view, ArrayList arrayList) {
        Rect rect = new Rect();
        if (view.isShown() && view.getGlobalVisibleRect(rect)) {
            if (C.isInstance(view)) {
                try {
                    Object objInvoke = G.invoke(view, null);
                    if (D.isInstance(objInvoke)) {
                        arrayList.add(new W0(view, objInvoke));
                        return;
                    }
                } catch (ReflectiveOperationException unused) {
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    h(viewGroup.getChildAt(i2), arrayList);
                }
            }
        }
    }

    public static I2 i(View view) {
        if (view == null || !(view.getParent() instanceof ViewGroup)) {
            return null;
        }
        View viewFindViewWithTag = ((ViewGroup) view.getParent()).findViewWithTag("new.soul.square.like");
        if (!(viewFindViewWithTag instanceof E2)) {
            return null;
        }
        I2 i2 = ((E2) viewFindViewWithTag).a;
        if (i2.b == view) {
            return i2;
        }
        return null;
    }

    public static void j(XposedModule xposedModule, Method method) {
        try {
            xposedModule.deoptimize(method);
        } catch (Throwable th) {
            Z("deopt " + method.getName(), th);
        }
    }

    public static void k(XposedModule xposedModule, Method method, String str) {
        try {
            boolean zDeoptimize = xposedModule.deoptimize(method);
            V.j(str.concat(".deoptimize"), "success=" + zDeoptimize + "; method=" + method.getDeclaringClass().getName() + "." + method.getName());
        } catch (Throwable th) {
            V.i(str.concat(".deoptimize"), th);
        }
    }

    public static void l(XposedModule xposedModule, ClassLoader classLoader, String str, String str2) {
        try {
            for (Method method : classLoader.loadClass(str).getDeclaredMethods()) {
                if (str2 == null || str2.equals(method.getName())) {
                    if (!Modifier.isAbstract(method.getModifiers()) && !Modifier.isNative(method.getModifiers())) {
                        j(xposedModule, method);
                    }
                }
            }
        } catch (Throwable th) {
            Z("callers ".concat(str), th);
        }
    }

    public static void m(Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            int modifiers = method.getModifiers();
            if (!Modifier.isAbstract(modifiers) && !Modifier.isNative(modifiers)) {
                S.deoptimize(method);
            }
        }
    }

    public static void n(XposedModule xposedModule, Activity activity, Intent intent, String str) {
        if (intent == null || !AbstractC0090w2.o(activity).getBoolean("block_splash", true)) {
            return;
        }
        intent.putExtra("display_ad", false);
        String str2 = "new soul: " + str + " display_ad=false";
        Log.i("SoulHook102", str2);
        xposedModule.log(4, "SoulHook102", str2);
    }

    public static int o(Activity activity, int i2) {
        return Math.round(i2 * activity.getResources().getDisplayMetrics().density);
    }

    public static int p(Context context, int i2) {
        return Math.round(i2 * context.getResources().getDisplayMetrics().density);
    }

    public static boolean q(String str) {
        try {
            return AbstractC0090w2.o((Context) d.invoke(null, null)).getBoolean(str, true);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void r(String str, String str2) {
        if (V.a()) {
            C0099z c0099z = (C0099z) A.b.get();
            V.d(str, "trace=" + (c0099z == null ? 0L : c0099z.a) + "; " + str2);
        }
    }

    public static void s(String str, Throwable th) {
        C0099z c0099z = (C0099z) A.b.get();
        V.d(str, ("trace=" + (c0099z == null ? 0L : c0099z.a)) + "\n" + V.c(th));
    }

    public static W0 t(View view) {
        W0 w0 = null;
        if (B && view != null && view.isShown()) {
            ArrayList<W0> arrayList = new ArrayList();
            h(view, arrayList);
            long j2 = 0;
            for (W0 w1 : arrayList) {
                Rect rect = new Rect();
                if (w1.a.getGlobalVisibleRect(rect)) {
                    long jWidth = ((long) rect.width()) * ((long) rect.height());
                    if (jWidth > j2) {
                        w0 = w1;
                        j2 = jWidth;
                    }
                }
            }
        }
        return w0;
    }

    public static boolean u(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = n.invoke(obj, null);
        return (((Integer) o.invoke(objInvoke, null)).intValue() == 9 || t.invoke(objInvoke, null) == null) ? false : true;
    }

    public static boolean v(Object obj) {
        if (obj == null || ((Boolean) s.invoke(obj, null)).booleanValue() || n.invoke(obj, null) == null) {
            return false;
        }
        String str = (String) m.invoke(null, null);
        String str2 = (String) p.invoke(obj, null);
        return (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || str2.equals(str) || !str.equals((String) q.invoke(obj, null))) ? false : true;
    }

    public static void w(String str) {
        Log.i("SoulHook102", str);
        S.log(4, "SoulHook102", str);
    }

    public static void x(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            d = classLoader.loadClass("lf.b").getDeclaredMethod("b", null);
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.imlib.msg.ImMessage");
            Method method = clsLoadClass.getMethod("getChatMessage", null);
            Method method2 = clsLoadClass.getMethod("getMsgType", null);
            Method method3 = classLoader.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("getMsgType", null);
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.imlib.ChatManager");
            Method declaredMethod = clsLoadClass2.getDeclaredMethod("p0", clsLoadClass, Boolean.TYPE);
            xposedModule.hook(declaredMethod).setId("new-soul/hide-typing").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0016e(method2, method, method3));
            xposedModule.deoptimize(clsLoadClass2.getDeclaredMethod("o0", clsLoadClass));
            xposedModule.deoptimize(declaredMethod);
            b = true;
            Log.i("SoulHook102", "typing command control installed");
        } catch (Throwable th) {
            xposedModule.log(6, "SoulHook102", "typing control unavailable", th);
        }
        try {
            Class<?> clsLoadClass3 = classLoader.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper");
            Method[] methodArr = {clsLoadClass3.getDeclaredMethod("k", null), clsLoadClass3.getDeclaredMethod("onPrepared", MediaPlayer.class)};
            for (int i2 = 0; i2 < 2; i2++) {
                Method method4 = methodArr[i2];
                xposedModule.hook(method4).setId("new-soul/match-music-" + method4.getName()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(3));
                xposedModule.deoptimize(method4);
            }
            xposedModule.deoptimize(clsLoadClass3.getDeclaredMethod("l", null));
            c = true;
            Log.i("SoulHook102", "soul match music control installed");
        } catch (Throwable th2) {
            xposedModule.log(6, "SoulHook102", "match music control unavailable", th2);
        }
    }

    public static void y(ClassLoader classLoader, Class cls) {
        try {
            C = classLoader.loadClass("androidx.recyclerview.widget.RecyclerView");
            D = classLoader.loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
            E = classLoader.loadClass("io.reactivex.functions.Consumer");
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.square.post.b1");
            F = clsLoadClass.getConstructor(cls);
            I = clsLoadClass.getMethod("h", Integer.TYPE);
            G = C.getMethod("getAdapter", null);
            H = D.getMethod("getData", null);
            K = D.getMethod("notifyDataSetChanged", null);
            Class<?> returnType = I.getReturnType();
            Class cls2 = E;
            J = returnType.getMethod("subscribe", cls2, cls2);
            B = true;
        } catch (Throwable th) {
            Log.e("SoulHook102", "loaded post likes unavailable", th);
        }
    }

    public static void z(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.lib.common.bean.ChatLimitModel");
            i = clsLoadClass.getMethod("getType", null);
            j = clsLoadClass.getMethod("getItemIdentity", null);
            Method method = clsLoadClass.getMethod("isLimit", null);
            xposedModule.hook(method).setId("new-soul/gift-chat-model").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(5));
            e = true;
            j(xposedModule, method);
        } catch (Throwable th) {
            Z("model", th);
        }
        try {
            Method method2 = classLoader.loadClass("cn.soulapp.imlib.Conversation").getMethod("getIntExt", String.class);
            xposedModule.hook(method2).setId("new-soul/gift-chat-session").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(6));
            g = true;
            j(xposedModule, method2);
        } catch (Throwable th2) {
            Z("session", th2);
        }
        if (e) {
            try {
                Class[] clsArr = {classLoader.loadClass("cn.soulapp.android.lib.common.bean.ChatLimitModel"), classLoader.loadClass("cn.soulapp.imlib.Conversation"), String.class, String.class, classLoader.loadClass("cn.soulapp.android.component.chat.dialog.LimitGiftDialog$OnStartGiftSendListener"), Boolean.TYPE};
                Method method3 = null;
                for (Method method4 : classLoader.loadClass("cn.soulapp.android.component.chat.utils.ChatUtils").getDeclaredMethods()) {
                    if (Modifier.isStatic(method4.getModifiers()) && method4.getReturnType() == Void.TYPE && Arrays.equals(method4.getParameterTypes(), clsArr)) {
                        if (method3 != null) {
                            throw new IllegalStateException("ambiguous gift dialog entry");
                        }
                        method3 = method4;
                    }
                }
                if (method3 == null) {
                    throw new NoSuchMethodException("gift dialog signature");
                }
                xposedModule.hook(method3).setId("new-soul/gift-chat-dialog").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(8));
                h = true;
                j(xposedModule, method3);
            } catch (Throwable th3) {
                Z("dialog", th3);
            }
        }
        try {
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig");
            Method declaredMethod = clsLoadClass2.getDeclaredMethod("getShowPopup", null);
            if (declaredMethod.getReturnType() != Boolean.class && declaredMethod.getReturnType() != Boolean.TYPE) {
                throw new NoSuchMethodException("unexpected getShowPopup return type");
            }
            xposedModule.hook(declaredMethod).setId("new-soul/bell-gift-popup-config").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(7));
            f = true;
            j(xposedModule, declaredMethod);
            try {
                for (Class<?> cls : classLoader.loadClass("cn.soulapp.android.component.home.lovebell.LoveBellProGiftDialog").getDeclaredClasses()) {
                    for (Method method5 : cls.getDeclaredMethods()) {
                        if (Arrays.asList(method5.getParameterTypes()).contains(clsLoadClass2)) {
                            j(xposedModule, method5);
                        }
                    }
                }
            } catch (Throwable th4) {
                Z("bell callers", th4);
            }
        } catch (Throwable th5) {
            Z("bell popup config", th5);
        }
        l(xposedModule, classLoader, "cn.soulapp.android.component.chat.style.BaseConversationStyleProcess", "checkLimitOpt");
        l(xposedModule, classLoader, "cn.soulapp.android.component.chat.helper.MsgLimitManager", null);
        l(xposedModule, classLoader, "cn.soulapp.android.component.chat.fragment.ConversationFragment", "v4");
        Log.i("SoulHook102", "gift chat hooks: model=" + e + "; session=" + g + "; dialog=" + h + "; bell=" + f);
    }
}
