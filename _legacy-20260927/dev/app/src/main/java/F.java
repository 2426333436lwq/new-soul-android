package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class F {
    public static boolean o;
    public static boolean p;
    public final WeakHashMap a = new WeakHashMap();
    public Method b;
    public Method c;
    public Method d;
    public Method e;
    public Method f;
    public Method g;
    public Method h;
    public Method i;
    public Method j;
    public Method k;
    public Constructor l;
    public C0014d1 m;
    public d3 n;

    public static Object b(Object obj, String str) {
        try {
        return obj.getClass().getMethod(str, null).invoke(obj, null);
            } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static Object c(int i, Object obj) {
        try {
        if (obj == null) {
            throw new IllegalArgumentException("Missing body");
        }
        Class<?> cls = obj.getClass();
        if (i == 1 && cls.getName().equals("cn.soulapp.imlib.msg.chat.TextMsg")) {
            String str = (String) g(obj, "text");
            if (str == null || str.trim().isEmpty() || str.length() > 500) {
                throw new IllegalArgumentException("Invalid text");
            }
            return cls.getConstructor(String.class).newInstance(str);
        }
        if (i == 2 && cls.getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg")) {
            return e(obj);
        }
        if (i == 3 && cls.getName().equals("cn.soulapp.imlib.msg.chat.ImgMsgs")) {
            List list = (List) g(obj, "imgMsgList");
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Empty images");
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(e(it.next()));
            }
            return cls.getConstructor(List.class).newInstance(arrayList);
        }
        if (i != 5 || !cls.getName().equals("cn.soulapp.imlib.msg.chat.AudioMsg")) {
            throw new IllegalArgumentException("Unsupported message body");
        }
        String str2 = (String) g(obj, "url");
        Integer num = (Integer) g(obj, "duration");
        int iIntValue = num.intValue();
        if (!Q0.N(str2) || iIntValue <= 0) {
            throw new IllegalArgumentException("Invalid audio");
        }
        return cls.getConstructor(String.class, String.class, Integer.TYPE, String.class).newInstance(str2, "", num, "");
            } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InstantiationException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static Object d(Object obj) {
        return c(((Integer) b(obj, "getMsgType")).intValue(), b(obj, "getMsgContent"));
    }

    public static Object e(Object obj) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        try {
        if (obj == null || !obj.getClass().getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg")) {
            throw new IllegalArgumentException("Invalid image");
        }
        if (!Q0.N((String) g(obj, "imageUrl")) || ((Integer) g(obj, "imageW")).intValue() <= 0 || ((Integer) g(obj, "imageH")).intValue() <= 0) {
            throw new IllegalArgumentException("Image not uploaded");
        }
        Object objNewInstance = obj.getClass().getConstructor(null).newInstance(null);
        String[] strArr = {"imageUrl", "imageW", "imageH"};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            obj.getClass().getField(str).set(objNewInstance, g(obj, str));
        }
        return objNewInstance;
            } catch (NoSuchFieldException | NoSuchMethodException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static boolean f(Object obj, String str, String str2) {
        try {
        boolean z = false;
        if (obj == null) {
            Q0.r("repeat.eligible", "reason=null_message");
            return false;
        }
        String str3 = (String) b(obj, "getFrom");
        String str4 = (String) b(obj, "getTo");
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || !((str.equals(str3) && str2.equals(str4)) || (str2.equals(str3) && str.equals(str4)))) {
            Q0.r("repeat.eligible", "reason=chat_mismatch; account_present=" + ((str == null || str.isEmpty()) ? false : true) + "; peer_present=" + ((str2 == null || str2.isEmpty()) ? false : true));
            return false;
        }
        Object objB = b(obj, "getChatMessage");
        if (objB == null) {
            Q0.r("repeat.eligible", "reason=null_chat");
            return false;
        }
        int iIntValue = ((Integer) b(obj, "getMsgType")).intValue();
        int iIntValue2 = ((Integer) b(objB, "getMsgType")).intValue();
        int iIntValue3 = ((Integer) b(objB, "getSnapChat")).intValue();
        String str5 = (String) objB.getClass().getMethod("getStringTransExt", String.class).invoke(objB, "isSnap");
        boolean zBooleanValue = ((Boolean) b(obj, "isAnonymityChat")).booleanValue();
        boolean z2 = iIntValue == 1 && !zBooleanValue && iIntValue3 == 0 && (str5 == null || str5.isEmpty() || "0".equals(str5) || "false".equals(str5)) && (iIntValue2 == 1 || iIntValue2 == 2 || iIntValue2 == 3 || iIntValue2 == 5);
        if (str5 != null && !str5.isEmpty() && !"0".equals(str5) && !"false".equals(str5)) {
            z = true;
        }
        Q0.r("repeat.eligible", "chat_match=true; envelope=" + iIntValue + "; type=" + iIntValue2 + "; snap=" + iIntValue3 + "; snap_marked=" + z + "; anonymous=" + zBooleanValue + "; accepted=" + z2);
        return z2;
            } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public static Object g(Object obj, String str) throws NoSuchFieldException {
        try {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
            } catch (IllegalAccessException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public final Object a(Object obj, Object obj2, Object obj3) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        try {
        String str;
        int i;
        if (!(obj instanceof List)) {
            Q0.r("repeat.menu.skip", "reason=not_list");
            return obj;
        }
        List list = (List) obj;
        Q0.r("repeat.menu.original", "count=" + list.size());
        if (list.isEmpty()) {
            Q0.r("repeat.menu.skip", "reason=empty_menu");
            return obj;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) this.c.invoke(it.next(), null)).intValue();
            if (iIntValue == 5133057 || iIntValue == 5133058 || iIntValue == 5133059) {
                Q0.r("repeat.menu.skip", "reason=already_added");
                return obj;
            }
        }
        String str2 = (String) this.b.invoke(null, null);
        String str3 = (String) g(obj2, "L");
        if (!f(obj3, str2, str3)) {
            return obj;
        }
        d(b(obj3, "getChatMessage"));
        Context context = (Context) b(obj2, "getContext");
        int identifier = context.getResources().getIdentifier("c_ct_resend_menu", "drawable", context.getPackageName());
        if (identifier == 0) {
            Q0.r("repeat.menu.skip", "reason=missing_repeat_icon");
            return obj;
        }
        Q0.r("repeat.menu.resources", "repeat_icon=true; forward_ready=" + this.m.u);
        ArrayList arrayList = new ArrayList(list);
        Object objNewInstance = this.l.newInstance("复读", Integer.valueOf(identifier), 5133057);
        arrayList.add(objNewInstance);
        WeakHashMap weakHashMap = this.a;
        weakHashMap.put(objNewInstance, new E(obj2, obj3, str2, str3));
        if (this.m.u) {
            i = identifier;
            int identifier2 = context.getResources().getIdentifier("c_ct_longclick_menu_share", "drawable", context.getPackageName());
            str = "count=";
            Q0.r("repeat.menu.forward", "share_icon=" + (identifier2 != 0));
            if (identifier2 != 0) {
                Object objNewInstance2 = this.l.newInstance("转发", Integer.valueOf(identifier2), 5133058);
                arrayList.add(objNewInstance2);
                weakHashMap.put(objNewInstance2, new E(obj2, obj3, str2, str3));
                Log.i("SoulHook102", "message forward menu added");
            }
        } else {
            str = "count=";
            i = identifier;
        }
        if (d3.g && ((Integer) b(b(obj3, "getChatMessage"), "getMsgType")).intValue() == 5) {
            int identifier3 = context.getResources().getIdentifier("c_ct_chat_icon_photo_download", "drawable", context.getPackageName());
            Constructor constructor = this.l;
            if (identifier3 != 0) {
                i = identifier3;
            }
            Object objNewInstance3 = constructor.newInstance("存入语音库", Integer.valueOf(i), 5133059);
            arrayList.add(objNewInstance3);
            weakHashMap.put(objNewInstance3, new E(obj2, obj3, str2, str3));
        }
        Log.i("SoulHook102", "repeat menu added; type=" + String.valueOf(b(b(obj3, "getChatMessage"), "getMsgType")) + "; own=" + str2.equals(b(obj3, "getFrom")));
        Q0.r("repeat.menu.return", str + arrayList.size() + "; added=" + (arrayList.size() - list.size()));
        A.b(arrayList);
        return arrayList;
            } catch (NoSuchFieldException e) {
            sneakyThrow(e);
            throw new AssertionError();
        }
    }

    public final void h(XposedModule xposedModule, ClassLoader classLoader) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.chat.fragment.ConversationFragment");
        Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.imlib.msg.ImMessage");
        Class<?> clsLoadClass3 = classLoader.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
        Class<?> clsLoadClass4 = classLoader.loadClass("cn.soulapp.android.component.chat.utils.y4");
        Class<?> clsLoadClass5 = classLoader.loadClass("cn.soulapp.android.component.chat.window.ChatLongClickPopUp$a");
        String[] strArr = {"L", "Q", "O", "s"};
        for (int i = 0; i < 4; i++) {
            clsLoadClass.getDeclaredField(strArr[i]);
        }
        Class<?> clsLoadClass6 = classLoader.loadClass("cn.soulapp.android.component.chat.utils.u0");
        Class cls = Integer.TYPE;
        this.l = clsLoadClass5.getConstructor(String.class, cls, cls);
        this.c = clsLoadClass5.getDeclaredMethod("b", null);
        this.b = classLoader.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
        this.d = clsLoadClass3.getMethod("create", String.class);
        this.e = clsLoadClass2.getMethod("createChatSendMsg", clsLoadClass3, String.class);
        this.f = clsLoadClass3.getMethod("setMsgType", cls);
        this.g = clsLoadClass3.getMethod("setMsgContent", Serializable.class);
        this.h = clsLoadClass4.getDeclaredMethod("u", clsLoadClass3);
        Method declaredMethod = clsLoadClass4.getDeclaredMethod("p1", clsLoadClass2);
        this.i = declaredMethod;
        declaredMethod.setAccessible(true);
        this.j = clsLoadClass4.getDeclaredMethod("Y1", String.class, classLoader.loadClass("cn.soulapp.lib_input.view.IMediaKeyBoard"), classLoader.loadClass("cn.soulapp.android.client.component.middle.platform.model.api.user.ImUserBean"));
        this.k = classLoader.loadClass("cn.soulapp.android.chat.utils.f").getMethod("a", clsLoadClass2);
        C0014d1 c0014d1 = new C0014d1();
        try {
            c0014d1.e(xposedModule, classLoader);
            c0014d1.u = true;
            V.i("message.forward", null);
        } catch (Throwable th) {
            V.i("message.forward", th);
            Log.e("SoulHook102", "message forward unavailable");
        }
        this.m = c0014d1;
        p = c0014d1.u;
        final d3 d3Var = new d3();
        try {
            d3Var.d = classLoader.loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
            d3Var.e = classLoader.loadClass("cn.soulapp.android.component.chat.utils.y4").getMethod("q2", String.class, Integer.TYPE, classLoader.loadClass("cn.soulapp.imlib.msg.ImMessage"));
            Class<?> clsLoadClass7 = classLoader.loadClass("cn.soulapp.android.component.chat.fragment.ConversationFragment");
            Method declaredMethod2 = clsLoadClass7.getDeclaredMethod("onResume", null);
            XposedInterface.HookBuilder id = xposedModule.hook(declaredMethod2).setId("new-soul/voice-library-entry");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            final int i2 = 0;
            id.setExceptionMode(exceptionMode).intercept(new N2(d3Var, i2));
            Method declaredMethod3 = clsLoadClass7.getDeclaredMethod("onPause", null);
            final int i3 = 1;
            xposedModule.hook(declaredMethod3).setId("new-soul/voice-library-close").setExceptionMode(exceptionMode).intercept(new N2(d3Var, i3));
            xposedModule.deoptimize(declaredMethod2);
            xposedModule.deoptimize(declaredMethod3);
            d3.g = true;
            Log.i("SoulHook102", "voice library installed; local storage and native audio upload");
        } catch (Throwable th2) {
            Log.e("SoulHook102", "voice library unavailable", th2);
        }
        this.n = d3Var;
        Class<?> clsLoadClass8 = classLoader.loadClass("uj.c0");
        Class cls2 = Integer.TYPE;
        Method declaredMethod4 = clsLoadClass6.getDeclaredMethod("p", clsLoadClass2, clsLoadClass, Context.class, clsLoadClass8, View.class, cls2, View.class, clsLoadClass5, cls2);
        XposedInterface.HookBuilder id2 = xposedModule.hook(declaredMethod4).setId("new-soul/repeat-click");
        XposedInterface.ExceptionMode exceptionMode2 = XposedInterface.ExceptionMode.PROTECTIVE;
        final int i4 = 0;
        id2.setExceptionMode(exceptionMode2).intercept(new C(this, i4));
        Q0.k(xposedModule, declaredMethod4, "repeat.click");
        Method declaredMethod5 = clsLoadClass6.getDeclaredMethod("k", clsLoadClass, View.class, clsLoadClass2);
        final int i5 = 1;
        xposedModule.hook(declaredMethod5).setId("new-soul/repeat-menu").setExceptionMode(exceptionMode2).intercept(new C(this, i5));
        Q0.k(xposedModule, declaredMethod5, "repeat.menu");
        try {
            Method declaredMethod6 = clsLoadClass.getDeclaredMethod("ac", View.class, clsLoadClass2, cls2);
            xposedModule.hook(declaredMethod6).setId("new-soul/repeat-context").setExceptionMode(exceptionMode2).intercept(new C0000a(11));
            Q0.k(xposedModule, declaredMethod6, "repeat.context");
            V.i("repeat.context", null);
        } catch (Throwable th3) {
            V.i("repeat.context", th3);
        }
        try {
            Class<?> clsLoadClass9 = classLoader.loadClass("cn.soulapp.android.component.chat.window.ChatLongClickPopUp");
            Method method = null;
            for (Method method2 : clsLoadClass9.getDeclaredMethods()) {
                if (method2.getReturnType() == clsLoadClass9 && Arrays.equals(method2.getParameterTypes(), new Class[]{List.class})) {
                    if (method != null) {
                        throw new IllegalStateException("Ambiguous menu renderer");
                    }
                    method = method2;
                }
            }
            if (method == null) {
                throw new NoSuchMethodException("Menu renderer");
            }
            xposedModule.hook(method).setId("new-soul/action-marks-chat").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new D(this, clsLoadClass9, 0));
            Q0.k(xposedModule, method, "repeat.renderer");
            V.i("repeat.renderer", null);
            Class cls3 = Integer.TYPE;
            Q0.G(xposedModule, clsLoadClass, "ac", new Class[]{View.class, clsLoadClass2, cls3}, true, 1, -1);
            Q0.G(xposedModule, clsLoadClass6, "y", new Class[]{clsLoadClass, List.class, View.class, clsLoadClass2, cls3}, false, 3, 1);
            o = true;
            V.i("repeat", null);
            Log.i("SoulHook102", "repeat native menu installed; text/image/audio");
        } catch (Throwable th4) {
            V.i("repeat.renderer", th4);
            Log.w("SoulHook102", "chat action marks unavailable");
        }
    }

    public final Object i(Object obj) {
        C0079u c0079u = (C0079u) C0079u.g.get();
        boolean z = true;
        if (c0079u == null || c0079u.e || c0079u.f) {
            c0079u = null;
        } else {
            c0079u.e = true;
        }
        if (c0079u != null) {
            Object obj2 = c0079u.b;
            Object obj3 = c0079u.a;
            if (o) {
                try {
                    Object obj4 = c0079u.c;
                    if ((obj4 instanceof View) && ((View) obj4).isAttachedToWindow()) {
                        k(new E(obj3, obj2, (String) this.b.invoke(null, null), (String) g(obj3, "L")));
                        Object objA = a(obj, obj3, obj2);
                        if (objA == obj) {
                            z = false;
                        }
                        Q0.r("repeat.fallback", "added=" + z);
                        return objA;
                    }
                    return obj;
                } catch (Throwable th) {
                    Q0.s("repeat.fallback.skipped", th);
                }
            }
        }
        return obj;
    }

    public final void j(E e) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        k(e);
        Object objB = b(e.b, "getChatMessage");
        Object objD = d(objB);
        Integer num = (Integer) b(objB, "getMsgType");
        int iIntValue = num.intValue();
        Object obj = e.a;
        Object objG = g(obj, "Q");
        if (objG == null) {
            throw new IllegalStateException("Sender unavailable");
        }
        if (iIntValue == 1) {
            Object objG2 = g(obj, "O");
            Object objG3 = g(obj, "s");
            if (objG2 == null || objG3 == null) {
                throw new IllegalStateException("Chat not ready");
            }
            this.j.invoke(objG, g(objD, "text"), objG3, objG2);
        } else {
            Method method = this.d;
            String str = e.d;
            Object objInvoke = method.invoke(null, str);
            this.f.invoke(objInvoke, num);
            this.g.invoke(objInvoke, objD);
            this.h.invoke(null, objInvoke);
            Object objInvoke2 = this.e.invoke(null, objInvoke, str);
            if (!((Boolean) this.k.invoke(null, objInvoke2)).booleanValue()) {
                return;
            } else {
                this.i.invoke(objG, objInvoke2);
            }
        }
        Log.i("SoulHook102", "repeat dispatched; type=" + iIntValue);
    }

    public final Activity k(E e) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Object obj = e.a;
        Activity activity = (Activity) b(obj, "getActivity");
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed() && ((Boolean) b(obj, "isResumed")).booleanValue()) {
            Object objInvoke = this.b.invoke(null, null);
            String str = e.c;
            if (str.equals(objInvoke)) {
                Object objG = g(obj, "L");
                String str2 = e.d;
                if (str2.equals(objG) && f(e.b, str, str2)) {
                    return activity;
                }
            }
        }
        throw new IllegalStateException("Chat changed");
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {
        throw (T) ex;
    }
}
