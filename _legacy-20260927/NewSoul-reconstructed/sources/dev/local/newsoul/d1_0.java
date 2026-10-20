/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.SystemClock
 *  android.view.View
 *  android.widget.TextView
 *  android.widget.Toast
 *  io.github.libxposed.api.XposedInterface$ExceptionMode
 *  io.github.libxposed.api.XposedInterface$Hooker
 *  io.github.libxposed.api.XposedModule
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.F;
import dev.local.newsoul.V;
import dev.local.newsoul.a1_0;
import dev.local.newsoul.b1_0;
import dev.local.newsoul.c1_0;
import dev.local.newsoul.e1_0;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * Renamed from dev.local.newsoul.d1
 */
public final class d1_0 {
    public Class a;
    public Class b;
    public Class c;
    public Constructor d;
    public Method e;
    public Method f;
    public Method g;
    public Method h;
    public Method i;
    public Method j;
    public Method k;
    public Method l;
    public Method m;
    public Method n;
    public Method o;
    public Method p;
    public Method q;
    public String r;
    public e1_0 s;
    public Dialog t;
    public boolean u;

    public static Object a(Object object, String string) {
        return object.getClass().getMethod(string, null).invoke(object, null);
    }

    public static void c(Activity activity, Throwable throwable) {
        V.d("message.forward.failed", V.c(throwable));
        d1_0.l((Context)activity, "\u8f6c\u53d1\u672a\u5b8c\u6210\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u6d88\u606f");
    }

    public static Object d(Object object, String string) {
        for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Object object2 = clazz.getDeclaredField(string);
                ((AccessibleObject)object2).setAccessible(true);
                object2 = ((Field)object2).get(object);
                return object2;
            }
            catch (NoSuchFieldException noSuchFieldException) {
                continue;
            }
        }
        throw new NoSuchFieldException(string);
    }

    public static boolean f(Activity activity) {
        return activity.getIntent().hasExtra("dev.local.newsoul.message_forward_token");
    }

    public static LinkedHashMap h(Activity activity, e1_0 e1_02) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
        for (Object object4 : d1_0.j(activity).values()) {
            block7: {
                block8: {
                    Object object2;
                    String string;
                    block12: {
                        block11: {
                            block10: {
                                Object object3;
                                block9: {
                                    block6: {
                                        if (!(object4 instanceof String) || ((AbstractMap)linkedHashMap).containsKey(object4)) break block7;
                                        string = (String)object4;
                                        for (Object object4 : (List)d1_0.a(d1_0.d(activity, "b"), "getAllData")) {
                                            object2 = d1_0.d(object4, "conversation");
                                            if (object2 == null || !string.equals(d1_0.a(object2, "getToUserId")) || !e1_02.c(string, (Integer)d1_0.a(object2, "getChatType")) || d1_0.d(object4, "user") == null) continue;
                                            break block6;
                                        }
                                        object4 = null;
                                    }
                                    if (object4 == null) break block8;
                                    object3 = d1_0.d(object4, "user");
                                    object2 = (String)d1_0.d(object3, "alias");
                                    if (object2 == null) break block9;
                                    object4 = object2;
                                    if (!((String)object2).isEmpty()) break block10;
                                }
                                object4 = (String)d1_0.d(object3, "signature");
                            }
                            if (object4 == null) break block11;
                            object2 = object4;
                            if (!((String)object4).isEmpty()) break block12;
                        }
                        object2 = "\u6240\u9009\u7528\u6237";
                    }
                    ((AbstractMap)linkedHashMap).put(string, object2);
                    continue;
                }
                throw new IllegalStateException("Recipient changed");
            }
            throw new IllegalStateException("Invalid selection");
        }
        return linkedHashMap;
    }

    public static Map j(Activity activity) {
        return (Map)d1_0.a(d1_0.d(activity, "presenter"), "m0");
    }

    public static void l(Context context, String string) {
        Toast.makeText((Context)context, (CharSequence)string, (int)0).show();
    }

    public static void n(Activity object) {
        int n = d1_0.j((Activity)object).size();
        TextView textView = (TextView)d1_0.d(object, "e");
        if (n == 0) {
            object = "\u5b8c\u6210";
        } else {
            object = new StringBuilder("\u5b8c\u6210(");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append(")");
            object = ((StringBuilder)object).toString();
        }
        textView.setText((CharSequence)object);
        boolean bl = n > 0;
        textView.setEnabled(bl);
        float f = n > 0 ? 1.0f : 0.5f;
        textView.setAlpha(f);
    }

    public final void b(Activity object) {
        Object object2;
        if (!(object.isFinishing() || object.isDestroyed() || (object2 = this.t) != null && object2.isShowing())) {
            e1_0 e1_02 = this.i((Activity)object);
            if (e1_02 == null) {
                d1_0.l((Context)object, "\u8f6c\u53d1\u5df2\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u6d88\u606f");
                object.finish();
                return;
            }
            LinkedHashMap linkedHashMap = d1_0.h((Activity)object, e1_02);
            if (!((AbstractMap)linkedHashMap).isEmpty() && ((AbstractMap)linkedHashMap).size() <= 9) {
                ArrayList arrayList = new ArrayList(linkedHashMap.keySet());
                String string = this.r;
                int n = e1_02.c;
                object2 = n == 1 ? "\u6587\u5b57\u6d88\u606f" : (n == 5 ? "\u8bed\u97f3" : "\u7167\u7247");
                GenericDeclaration genericDeclaration = this.d;
                int n2 = arrayList.size();
                Object object3 = String.join((CharSequence)"\u3001", linkedHashMap.values());
                StringBuilder stringBuilder = new StringBuilder("\u5c06\u8fd9\u6761");
                stringBuilder.append((String)object2);
                stringBuilder.append("\u8f6c\u53d1\u7ed9\u4ee5\u4e0b ");
                stringBuilder.append(n2);
                stringBuilder.append(" \u4eba\uff1f\n\n");
                stringBuilder.append((String)object3);
                object2 = (Dialog)genericDeclaration.newInstance(object, stringBuilder.toString());
                object3 = this.b.getClassLoader();
                genericDeclaration = this.b;
                object = new b1_0(this, (Activity)object, string, e1_02, linkedHashMap, arrayList, (Dialog)object2);
                object = Proxy.newProxyInstance((ClassLoader)object3, new Class[]{genericDeclaration}, (InvocationHandler)object);
                this.f.invoke(object2, object);
                this.t = object2;
                object2.setOnDismissListener((DialogInterface.OnDismissListener)new c1_0(this, object2, 0));
                object2.show();
                n2 = arrayList.size();
                object = new StringBuilder("type=");
                ((StringBuilder)object).append(n);
                ((StringBuilder)object).append("; targets=");
                ((StringBuilder)object).append(n2);
                V.d("message.forward.confirmation", ((StringBuilder)object).toString());
            }
        }
    }

    public final void e(XposedModule xposedModule, ClassLoader object) {
        StringBuilder stringBuilder;
        int n;
        this.a = ((ClassLoader)object).loadClass("cn.soulapp.android.component.chat.SelectConversationActivity");
        Object object2 = ((ClassLoader)object).loadClass("cn.soulapp.android.component.chat.dialog.j1");
        this.b = ((ClassLoader)object).loadClass("cn.soulapp.android.chat.listener.OnClickConfirmListener");
        this.d = ((Class)object2).getConstructor(Context.class, String.class);
        this.f = ((Class)object2).getMethod("d", this.b);
        this.g = ((ClassLoader)object).loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
        Object object3 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
        Object object4 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.msg.ImMessage");
        this.h = object3.getMethod("create", String.class);
        this.i = object3.getMethod("setMsgContent", Serializable.class);
        object2 = Integer.TYPE;
        this.j = object3.getMethod("setMsgType", new Class[]{object2});
        this.k = object3.getMethod("setTransExtMap", Map.class);
        this.l = ((ClassLoader)object).loadClass("cn.soulapp.android.component.chat.utils.y4").getMethod("u", new Class[]{object3});
        this.m = ((Class)object4).getMethod("createChatSendMsg", new Class[]{object3, String.class});
        object3 = ((ClassLoader)object).loadClass("cn.soulapp.imlib.i0");
        this.n = object3.getMethod("v", null);
        this.o = object3.getMethod("o", null);
        this.p = ((ClassLoader)object).loadClass("cn.soulapp.imlib.ChatManager").getMethod("q0", new Class[]{object4});
        object4 = ((ClassLoader)object).loadClass("uj.k1");
        this.q = ((Class)object4).getMethod("g2", ((ClassLoader)object).loadClass("cn.soulapp.android.chat.bean.UserConversation"));
        ((Class)object4).getMethod("m0", null);
        object3 = ((ClassLoader)object).loadClass("cn.soulapp.android.component.chat.adapter.ConversationAdapter");
        object4 = Boolean.TYPE;
        object3.getMethod("k", new Class[]{object4});
        this.c = object = ((ClassLoader)object).loadClass("com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener");
        this.e = object3.getMethod("setOnItemClickListener", new Class[]{object});
        this.a.getDeclaredField("b");
        this.a.getDeclaredField("e");
        object2 = this.a.getDeclaredMethod("F", new Class[]{object2});
        object3 = xposedModule.hook(object2).setId("new-soul/message-forward-select");
        object = XposedInterface.ExceptionMode.PROTECTIVE;
        object3 = object3.setExceptionMode((XposedInterface.ExceptionMode)object);
        int n2 = 0;
        object3.intercept((XposedInterface.Hooker)new a1_0(this, 0));
        xposedModule.deoptimize(object2);
        object2 = this.a.getDeclaredMethod("loadConversationList", new Class[]{List.class, List.class, object4});
        xposedModule.hook(object2).setId("new-soul/message-forward-list").setExceptionMode((XposedInterface.ExceptionMode)object).intercept((XposedInterface.Hooker)new a1_0(this, 1));
        xposedModule.deoptimize(object2);
        object2 = this.a.getDeclaredMethod("init", Bundle.class);
        xposedModule.hook(object2).setId("new-soul/message-forward-ui").setExceptionMode((XposedInterface.ExceptionMode)object).intercept((XposedInterface.Hooker)new a1_0(this, 2));
        xposedModule.deoptimize(object2);
        Method method = this.a.getDeclaredMethod("E", View.class);
        object3 = this.a.getDeclaredMethod("S", null);
        for (n = 0; n < 2; ++n) {
            object = (new Method[]{method, object3})[n];
            object2 = xposedModule.hook((Executable)object);
            object4 = ((Method)object).getName();
            stringBuilder = new StringBuilder("new-soul/message-forward-complete-");
            stringBuilder.append((String)object4);
            object2.setId(stringBuilder.toString()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new a1_0(this, 3));
            xposedModule.deoptimize((Executable)object);
        }
        method = this.a.getDeclaredMethod("D", Object.class);
        object = this.a.getDeclaredMethod("onClick", View.class);
        for (n = n2; n < 2; ++n) {
            object2 = (new Method[]{method, object})[n];
            object3 = xposedModule.hook((Executable)object2);
            object4 = ((Method)object2).getName();
            stringBuilder = new StringBuilder("new-soul/message-forward-navigation-");
            stringBuilder.append((String)object4);
            object3.setId(stringBuilder.toString()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new a1_0(this, 4));
            xposedModule.deoptimize((Executable)object2);
        }
        xposedModule.hook((Executable)Activity.class.getDeclaredMethod("onDestroy", null)).setId("new-soul/message-forward-cleanup").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept((XposedInterface.Hooker)new a1_0(this, 5));
    }

    public final void g(Activity object, String string, String string2, int n, Object object2) {
        if (this.u && !object.isFinishing() && !object.isDestroyed() && string.equals(this.g.invoke(null, null))) {
            this.s = new e1_0(string, string2, n, F.c(n, object2), SystemClock.elapsedRealtime());
            this.r = UUID.randomUUID().toString();
            string = new Intent((Context)object, this.a).putExtra("dev.local.newsoul.message_forward_token", this.r).putExtra("isChoice", false).putExtra("isFromPreview", true).putExtra("onlyFinishAfterShare", true);
            try {
                object.startActivity((Intent)string);
            }
            catch (RuntimeException runtimeException) {
                this.s = null;
                this.r = null;
                throw runtimeException;
            }
            object = new StringBuilder("type=");
            ((StringBuilder)object).append(n);
            V.d("message.forward.picker_open", ((StringBuilder)object).toString());
            return;
        }
        throw new IllegalStateException("Forward unavailable");
    }

    public final e1_0 i(Activity activity) {
        e1_0 e1_02;
        Object object = this.s;
        e1_0 e1_03 = e1_02 = null;
        if (object != null) {
            object = this.r;
            e1_03 = e1_02;
            if (object != null) {
                e1_03 = e1_02;
                if (((String)object).equals(activity.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                    e1_03 = e1_02;
                    if (this.s.b((String)this.g.invoke(null, null), SystemClock.elapsedRealtime())) {
                        e1_03 = this.s;
                    }
                }
            }
        }
        return e1_03;
    }

    public final void k(e1_0 object, String string) {
        Object object2 = this.h.invoke(null, string);
        Method method = this.i;
        Object object3 = ((e1_0)object).d;
        int n = ((e1_0)object).c;
        method.invoke(object2, F.c(n, object3));
        this.k.invoke(object2, new HashMap());
        this.j.invoke(object2, n);
        this.l.invoke(null, object2);
        object = this.m.invoke(null, object2, string);
        this.p.invoke(this.o.invoke(this.n.invoke(null, null), null), object);
    }

    public final void m(Activity object, int n) {
        Object e;
        Object object2;
        Object object3 = this.i((Activity)object);
        if (object3 == null) {
            d1_0.l((Context)object, "\u8f6c\u53d1\u5df2\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u6d88\u606f");
            object.finish();
            return;
        }
        Object object4 = (List)d1_0.a(d1_0.d(object, "b"), "getAllData");
        if (n >= 0 && n < object4.size() && (object2 = d1_0.d(e = object4.get(n), "conversation")) != null && d1_0.d(e, "user") != null) {
            object4 = (String)d1_0.a(object2, "getToUserId");
            if (!((e1_0)object3).c((String)object4, (Integer)d1_0.a(object2, "getChatType"))) {
                return;
            }
            object3 = d1_0.j((Activity)object);
            if (!object3.containsValue(object4) && object3.size() >= 9) {
                d1_0.l((Context)object, "\u6700\u591a\u9009\u62e99\u4eba");
                return;
            }
            this.q.invoke(d1_0.d(object, "presenter"), e);
            d1_0.a(d1_0.d(object, "b"), "notifyDataSetChanged");
            d1_0.n((Activity)object);
            n = d1_0.j((Activity)object).size();
            object = new StringBuilder("count=");
            ((StringBuilder)object).append(n);
            V.d("message.forward.selection", ((StringBuilder)object).toString());
        }
    }
}

