/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  android.view.View
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import dev.local.newsoul.A;
import dev.local.newsoul.C;
import dev.local.newsoul.D;
import dev.local.newsoul.E;
import dev.local.newsoul.N2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.V;
import dev.local.newsoul.a_0;
import dev.local.newsoul.d1_0;
import dev.local.newsoul.d3;
import dev.local.newsoul.u_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

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
    public d1_0 m;
    public d3 n;

    public static Object b(Object object, String string) {
        return object.getClass().getMethod(string, null).invoke(object, null);
    }

    public static Object c(int n, Object arrayList) {
        if (arrayList != null) {
            Class<?> clazz = arrayList.getClass();
            if (n == 1 && clazz.getName().equals("cn.soulapp.imlib.msg.chat.TextMsg")) {
                if ((arrayList = (String)F.g(arrayList, "text")) != null && !((String)((Object)arrayList)).trim().isEmpty() && ((String)((Object)arrayList)).length() <= 500) {
                    return clazz.getConstructor(String.class).newInstance(arrayList);
                }
                throw new IllegalArgumentException("Invalid text");
            }
            if (n == 2 && clazz.getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg")) {
                return F.e(arrayList);
            }
            if (n == 3 && clazz.getName().equals("cn.soulapp.imlib.msg.chat.ImgMsgs")) {
                Object object = (List)F.g(arrayList, "imgMsgList");
                if (object != null && !object.isEmpty()) {
                    arrayList = new ArrayList<Object>();
                    object = object.iterator();
                    while (object.hasNext()) {
                        arrayList.add(F.e(object.next()));
                    }
                    return clazz.getConstructor(List.class).newInstance(arrayList);
                }
                throw new IllegalArgumentException("Empty images");
            }
            if (n == 5 && clazz.getName().equals("cn.soulapp.imlib.msg.chat.AudioMsg")) {
                String string = (String)F.g(arrayList, "url");
                arrayList = (Integer)F.g(arrayList, "duration");
                n = (Integer)((Object)arrayList);
                if (Q0.N(string) && n > 0) {
                    return clazz.getConstructor(String.class, String.class, Integer.TYPE, String.class).newInstance(string, "", arrayList, "");
                }
                throw new IllegalArgumentException("Invalid audio");
            }
            throw new IllegalArgumentException("Unsupported message body");
        }
        throw new IllegalArgumentException("Missing body");
    }

    public static Object d(Object object) {
        return F.c((Integer)F.b(object, "getMsgType"), F.b(object, "getMsgContent"));
    }

    public static Object e(Object object) {
        if (object != null && object.getClass().getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg")) {
            if (Q0.N((String)F.g(object, "imageUrl")) && (Integer)F.g(object, "imageW") > 0 && (Integer)F.g(object, "imageH") > 0) {
                Object obj = object.getClass().getConstructor(null).newInstance(null);
                for (int i = 0; i < 3; ++i) {
                    String string = (new String[]{"imageUrl", "imageW", "imageH"})[i];
                    object.getClass().getField(string).set(obj, F.g(object, string));
                }
                return obj;
            }
            throw new IllegalArgumentException("Image not uploaded");
        }
        throw new IllegalArgumentException("Invalid image");
    }

    public static boolean f(Object object, String object2, String string) {
        boolean bl = false;
        if (object == null) {
            Q0.r("repeat.eligible", "reason=null_message");
            return false;
        }
        String string2 = (String)F.b(object, "getFrom");
        String string3 = (String)F.b(object, "getTo");
        boolean bl2 = true;
        if (object2 != null && !((String)object2).isEmpty() && string != null && !string.isEmpty() && (((String)object2).equals(string2) && string.equals(string3) || string.equals(string2) && ((String)object2).equals(string3))) {
            object2 = F.b(object, "getChatMessage");
            if (object2 == null) {
                Q0.r("repeat.eligible", "reason=null_chat");
                return false;
            }
            int n = (Integer)F.b(object, "getMsgType");
            int n2 = (Integer)F.b(object2, "getMsgType");
            int n3 = (Integer)F.b(object2, "getSnapChat");
            object2 = (String)object2.getClass().getMethod("getStringTransExt", String.class).invoke(object2, "isSnap");
            boolean bl3 = (Boolean)F.b(object, "isAnonymityChat");
            boolean bl4 = !(n != 1 || bl3 || n3 != 0 || object2 != null && !((String)object2).isEmpty() && !"0".equals(object2) && !"false".equals(object2) || n2 != 1 && n2 != 2 && n2 != 3 && n2 != 5);
            bl2 = bl;
            if (object2 != null) {
                bl2 = bl;
                if (!((String)object2).isEmpty()) {
                    bl2 = bl;
                    if (!"0".equals(object2)) {
                        bl2 = bl;
                        if (!"false".equals(object2)) {
                            bl2 = true;
                        }
                    }
                }
            }
            object = new StringBuilder("chat_match=true; envelope=");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append("; type=");
            ((StringBuilder)object).append(n2);
            ((StringBuilder)object).append("; snap=");
            ((StringBuilder)object).append(n3);
            ((StringBuilder)object).append("; snap_marked=");
            ((StringBuilder)object).append(bl2);
            ((StringBuilder)object).append("; anonymous=");
            ((StringBuilder)object).append(bl3);
            ((StringBuilder)object).append("; accepted=");
            ((StringBuilder)object).append(bl4);
            Q0.r("repeat.eligible", ((StringBuilder)object).toString());
            return bl4;
        }
        boolean bl5 = object2 != null && !((String)object2).isEmpty();
        if (string == null || string.isEmpty()) {
            bl2 = false;
        }
        object = new StringBuilder("reason=chat_mismatch; account_present=");
        ((StringBuilder)object).append(bl5);
        ((StringBuilder)object).append("; peer_present=");
        ((StringBuilder)object).append(bl2);
        Q0.r("repeat.eligible", ((StringBuilder)object).toString());
        return false;
    }

    public static Object g(Object object, String object2) {
        object2 = object.getClass().getDeclaredField((String)object2);
        ((AccessibleObject)object2).setAccessible(true);
        return ((Field)object2).get(object);
    }

    public final Object a(Object arrayList, Object object, Object object2) {
        int n;
        Object object32;
        if (!(arrayList instanceof List)) {
            Q0.r("repeat.menu.skip", "reason=not_list");
            return arrayList;
        }
        List list = arrayList;
        int n2 = list.size();
        Object object4 = new StringBuilder("count=");
        ((StringBuilder)object4).append(n2);
        Q0.r("repeat.menu.original", ((StringBuilder)object4).toString());
        if (list.isEmpty()) {
            Q0.r("repeat.menu.skip", "reason=empty_menu");
            return arrayList;
        }
        for (Object object32 : list) {
            n2 = (Integer)this.c.invoke(object32, null);
            if (n2 != 5133057 && n2 != 5133058 && n2 != 5133059) continue;
            Q0.r("repeat.menu.skip", "reason=already_added");
            return arrayList;
        }
        object4 = (String)this.b.invoke(null, null);
        if (!F.f(object2, (String)object4, object32 = (String)F.g(object, "L"))) {
            return arrayList;
        }
        F.d(F.b(object2, "getChatMessage"));
        Object object5 = (Context)F.b(object, "getContext");
        n2 = object5.getResources().getIdentifier("c_ct_resend_menu", "drawable", object5.getPackageName());
        if (n2 == 0) {
            Q0.r("repeat.menu.skip", "reason=missing_repeat_icon");
            return arrayList;
        }
        boolean bl = this.m.u;
        arrayList = new StringBuilder("repeat_icon=true; forward_ready=");
        ((StringBuilder)((Object)arrayList)).append(bl);
        Q0.r("repeat.menu.resources", ((StringBuilder)((Object)arrayList)).toString());
        arrayList = new ArrayList<Object>(list);
        Object object6 = this.l.newInstance("\u590d\u8bfb", n2, 5133057);
        arrayList.add(object6);
        WeakHashMap weakHashMap = this.a;
        weakHashMap.put(object6, new E(object, object2, (String)object4, (String)object32));
        if (this.m.u) {
            n = object5.getResources().getIdentifier("c_ct_longclick_menu_share", "drawable", object5.getPackageName());
            bl = n != 0;
            object6 = new StringBuilder("share_icon=");
            ((StringBuilder)object6).append(bl);
            Q0.r("repeat.menu.forward", ((StringBuilder)object6).toString());
            if (n != 0) {
                object6 = this.l.newInstance("\u8f6c\u53d1", n, 5133058);
                arrayList.add(object6);
                weakHashMap.put(object6, new E(object, object2, (String)object4, (String)object32));
                Log.i((String)"SoulHook102", (String)"message forward menu added");
            }
        }
        if (d3.g && (Integer)F.b(F.b(object2, "getChatMessage"), "getMsgType") == 5) {
            n = object5.getResources().getIdentifier("c_ct_chat_icon_photo_download", "drawable", object5.getPackageName());
            object5 = this.l;
            if (n != 0) {
                n2 = n;
            }
            object5 = object5.newInstance("\u5b58\u5165\u8bed\u97f3\u5e93", n2, 5133059);
            arrayList.add(object5);
            weakHashMap.put(object5, new E(object, object2, (String)object4, (String)object32));
        }
        object = String.valueOf(F.b(F.b(object2, "getChatMessage"), "getMsgType"));
        bl = ((String)object4).equals(F.b(object2, "getFrom"));
        object2 = new StringBuilder("repeat menu added; type=");
        ((StringBuilder)object2).append((String)object);
        ((StringBuilder)object2).append("; own=");
        ((StringBuilder)object2).append(bl);
        Log.i((String)"SoulHook102", (String)((StringBuilder)object2).toString());
        int n3 = arrayList.size();
        n2 = arrayList.size();
        n = list.size();
        object = new StringBuilder("count=");
        ((StringBuilder)object).append(n3);
        ((StringBuilder)object).append("; added=");
        ((StringBuilder)object).append(n2 - n);
        Q0.r("repeat.menu.return", ((StringBuilder)object).toString());
        A.b(arrayList);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void h(XposedModule xposedModule, ClassLoader object) {
        Class<?> clazz;
        Class<?> clazz2;
        Class<?> clazz3;
        block15: {
            Throwable throwable5;
            block17: {
                block16: {
                    int n;
                    clazz3 = object.loadClass("cn.soulapp.android.component.chat.fragment.ConversationFragment");
                    clazz2 = object.loadClass("cn.soulapp.imlib.msg.ImMessage");
                    Method[] methodArray = object.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
                    Object object2 = object.loadClass("cn.soulapp.android.component.chat.utils.y4");
                    Object object3 = object.loadClass("cn.soulapp.android.component.chat.window.ChatLongClickPopUp$a");
                    for (n = 0; n < 4; ++n) {
                        clazz3.getDeclaredField((new String[]{"L", "Q", "O", "s"})[n]);
                    }
                    clazz = object.loadClass("cn.soulapp.android.component.chat.utils.u0");
                    Object object4 = Integer.TYPE;
                    this.l = object3.getConstructor(new Class[]{String.class, object4, object4});
                    this.c = object3.getDeclaredMethod("b", null);
                    this.b = object.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", null);
                    this.d = methodArray.getMethod("create", String.class);
                    this.e = clazz2.getMethod("createChatSendMsg", new Class[]{methodArray, String.class});
                    this.f = methodArray.getMethod("setMsgType", new Class[]{object4});
                    this.g = methodArray.getMethod("setMsgContent", Serializable.class);
                    this.h = ((Class)object2).getDeclaredMethod("u", new Class[]{methodArray});
                    object4 = ((Class)object2).getDeclaredMethod("p1", clazz2);
                    this.i = object4;
                    ((AccessibleObject)object4).setAccessible(true);
                    this.j = ((Class)object2).getDeclaredMethod("Y1", String.class, object.loadClass("cn.soulapp.lib_input.view.IMediaKeyBoard"), object.loadClass("cn.soulapp.android.client.component.middle.platform.model.api.user.ImUserBean"));
                    this.k = object.loadClass("cn.soulapp.android.chat.utils.f").getMethod("a", clazz2);
                    object2 = new Object();
                    try {
                        ((d1_0)object2).e(xposedModule, (ClassLoader)object);
                        ((d1_0)object2).u = true;
                        V.i("message.forward", null);
                    }
                    catch (Throwable throwable2) {
                        V.i("message.forward", throwable2);
                        Log.e((String)"SoulHook102", (String)"message forward unavailable");
                    }
                    this.m = object2;
                    p = ((d1_0)object2).u;
                    object2 = new d3();
                    try {
                        ((d3)object2).d = object.loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
                        ((d3)object2).e = object.loadClass("cn.soulapp.android.component.chat.utils.y4").getMethod("q2", String.class, Integer.TYPE, object.loadClass("cn.soulapp.imlib.msg.ImMessage"));
                        GenericDeclaration genericDeclaration = object.loadClass("cn.soulapp.android.component.chat.fragment.ConversationFragment");
                        object4 = genericDeclaration.getDeclaredMethod("onResume", null);
                        Object object5 = xposedModule.hook((Executable)object4).setId("new-soul/voice-library-entry");
                        methodArray = XposedInterface.ExceptionMode.PROTECTIVE;
                        object5 = object5.setExceptionMode((XposedInterface.ExceptionMode)methodArray);
                        N2 n2 = new N2((d3)object2, 0);
                        object5.intercept((XposedInterface.Hooker)n2);
                        genericDeclaration = genericDeclaration.getDeclaredMethod("onPause", null);
                        methodArray = xposedModule.hook(genericDeclaration).setId("new-soul/voice-library-close").setExceptionMode((XposedInterface.ExceptionMode)methodArray);
                        object5 = new N2((d3)object2, 1);
                        methodArray.intercept((XposedInterface.Hooker)object5);
                        xposedModule.deoptimize((Executable)object4);
                        xposedModule.deoptimize(genericDeclaration);
                        d3.g = true;
                        Log.i((String)"SoulHook102", (String)"voice library installed; local storage and native audio upload");
                    }
                    catch (Throwable throwable3) {
                        Log.e((String)"SoulHook102", (String)"voice library unavailable", (Throwable)throwable3);
                    }
                    this.n = object2;
                    object4 = object.loadClass("uj.c0");
                    object2 = Integer.TYPE;
                    object4 = clazz.getDeclaredMethod("p", new Class[]{clazz2, clazz3, Context.class, object4, View.class, object2, View.class, object3, object2});
                    methodArray = xposedModule.hook((Executable)object4).setId("new-soul/repeat-click");
                    object3 = XposedInterface.ExceptionMode.PROTECTIVE;
                    methodArray.setExceptionMode(object3).intercept((XposedInterface.Hooker)new C(this, 0));
                    Q0.k(xposedModule, (Method)object4, "repeat.click");
                    object4 = clazz.getDeclaredMethod("k", clazz3, View.class, clazz2);
                    xposedModule.hook((Executable)object4).setId("new-soul/repeat-menu").setExceptionMode(object3).intercept((XposedInterface.Hooker)new C(this, 1));
                    Q0.k(xposedModule, (Method)object4, "repeat.menu");
                    try {
                        object2 = clazz3.getDeclaredMethod("ac", new Class[]{View.class, clazz2, object2});
                        object3 = xposedModule.hook(object2).setId("new-soul/repeat-context").setExceptionMode(object3);
                        object4 = new a_0(11);
                        object3.intercept((XposedInterface.Hooker)object4);
                        Q0.k(xposedModule, (Method)object2, "repeat.context");
                        V.i("repeat.context", null);
                    }
                    catch (Throwable throwable4) {
                        V.i("repeat.context", throwable4);
                    }
                    object4 = object.loadClass("cn.soulapp.android.component.chat.window.ChatLongClickPopUp");
                    methodArray = ((Class)object4).getDeclaredMethods();
                    int n2 = methodArray.length;
                    object = null;
                    for (n = 0; n < n2; ++n) {
                        object2 = methodArray[n];
                        object3 = object;
                        if (((Method)object2).getReturnType() == object4) {
                            object3 = object;
                            if (Arrays.equals(((Method)object2).getParameterTypes(), new Class[]{List.class})) {
                                if (object != null) {
                                    object = new IllegalStateException("Ambiguous menu renderer");
                                    throw object;
                                }
                                object3 = object2;
                            }
                        }
                        object = object3;
                        continue;
                    }
                    if (object == null) break block16;
                    try {
                        object3 = xposedModule.hook(object).setId("new-soul/action-marks-chat").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE);
                        object2 = new D(this, object4, 0);
                        object3.intercept((XposedInterface.Hooker)object2);
                        Q0.k(xposedModule, (Method)object, "repeat.renderer");
                        V.i("repeat.renderer", null);
                        break block15;
                    }
                    catch (Throwable throwable5) {}
                    break block17;
                }
                object = new NoSuchMethodException("Menu renderer");
                throw object;
            }
            V.i("repeat.renderer", throwable5);
            Log.w((String)"SoulHook102", (String)"chat action marks unavailable");
        }
        object = Integer.TYPE;
        Q0.G(xposedModule, clazz3, "ac", new Class[]{View.class, clazz2, object}, true, 1, -1);
        Q0.G(xposedModule, clazz, "y", new Class[]{clazz3, List.class, View.class, clazz2, object}, false, 3, 1);
        o = true;
        V.i("repeat", null);
        Log.i((String)"SoulHook102", (String)"repeat native menu installed; text/image/audio");
    }

    public final Object i(Object object) {
        block7: {
            Throwable throwable2;
            block8: {
                block5: {
                    Object object2;
                    boolean bl;
                    Object object3;
                    block6: {
                        object3 = (u_0)u_0.g.get();
                        bl = true;
                        if (object3 != null && !((u_0)object3).e && !((u_0)object3).f) {
                            ((u_0)object3).e = true;
                        } else {
                            object3 = null;
                        }
                        if (object3 == null) break block7;
                        object2 = ((u_0)object3).b;
                        Object object4 = ((u_0)object3).a;
                        if (!o) break block7;
                        try {
                            object3 = ((u_0)object3).c;
                            if (!(object3 instanceof View) || !((View)object3).isAttachedToWindow()) break block5;
                            String string = (String)this.b.invoke(null, null);
                            object3 = (String)F.g(object4, "L");
                            E e = new E(object4, object2, string, (String)object3);
                            this.k(e);
                            object3 = this.a(object, object4, object2);
                            if (object3 != object) break block6;
                            bl = false;
                        }
                        catch (Throwable throwable2) {}
                    }
                    object2 = new StringBuilder("added=");
                    ((StringBuilder)object2).append(bl);
                    Q0.r("repeat.fallback", ((StringBuilder)object2).toString());
                    return object3;
                    break block8;
                }
                return object;
            }
            Q0.s("repeat.fallback.skipped", throwable2);
        }
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void j(E object) {
        this.k((E)object);
        Object object2 = F.b(((E)object).b, "getChatMessage");
        Object object3 = F.d(object2);
        Object object4 = (Integer)F.b(object2, "getMsgType");
        int n = (Integer)object4;
        Object object5 = ((E)object).a;
        object2 = F.g(object5, "Q");
        if (object2 == null) throw new IllegalStateException("Sender unavailable");
        if (n == 1) {
            object = F.g(object5, "O");
            object4 = F.g(object5, "s");
            if (object == null || object4 == null) throw new IllegalStateException("Chat not ready");
            this.j.invoke(object2, F.g(object3, "text"), object4, object);
        } else {
            object5 = this.d;
            object = ((E)object).d;
            object5 = ((Method)object5).invoke(null, object);
            this.f.invoke(object5, object4);
            this.g.invoke(object5, object3);
            this.h.invoke(null, object5);
            object = this.e.invoke(null, object5, object);
            if (!((Boolean)this.k.invoke(null, object)).booleanValue()) {
                return;
            }
            this.i.invoke(object2, object);
        }
        object = new StringBuilder("repeat dispatched; type=");
        ((StringBuilder)object).append(n);
        Log.i((String)"SoulHook102", (String)((StringBuilder)object).toString());
    }

    public final Activity k(E e) {
        Object object;
        String string;
        Object object2 = e.a;
        Activity activity = (Activity)F.b(object2, "getActivity");
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed() && ((Boolean)F.b(object2, "isResumed")).booleanValue() && (string = e.c).equals(object = this.b.invoke(null, null)) && ((String)(object = e.d)).equals(object2 = F.g(object2, "L")) && F.f(e.b, string, (String)object)) {
            return activity;
        }
        throw new IllegalStateException("Chat changed");
    }
}

