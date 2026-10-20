package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: renamed from: dev.local.newsoul.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0014d1 {
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
    public C0018e1 s;
    public Dialog t;
    public boolean u;

    public static Object a(Object obj, String str) {
        return obj.getClass().getMethod(str, null).invoke(obj, null);
    }

    public static void c(Activity activity, Throwable th) {
        V.d("message.forward.failed", V.c(th));
        l(activity, "转发未完成，请重新选择消息");
    }

    public static Object d(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException(str);
    }

    public static boolean f(Activity activity) {
        return activity.getIntent().hasExtra("dev.local.newsoul.message_forward_token");
    }

    public static LinkedHashMap h(Activity activity, C0018e1 c0018e1) throws NoSuchFieldException {
        Object next;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : j(activity).values()) {
            if (!(obj instanceof String) || linkedHashMap.containsKey(obj)) {
                throw new IllegalStateException("Invalid selection");
            }
            String str = (String) obj;
            Iterator it = ((List) a(d(activity, "b"), "getAllData")).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Object objD = d(next, "conversation");
                if (objD != null && str.equals(a(objD, "getToUserId")) && c0018e1.c(str, ((Integer) a(objD, "getChatType")).intValue()) && d(next, "user") != null) {
                    break;
                }
            }
            if (next == null) {
                throw new IllegalStateException("Recipient changed");
            }
            Object objD2 = d(next, "user");
            String str2 = (String) d(objD2, "alias");
            if (str2 == null || str2.isEmpty()) {
                str2 = (String) d(objD2, "signature");
            }
            if (str2 == null || str2.isEmpty()) {
                str2 = "所选用户";
            }
            linkedHashMap.put(str, str2);
        }
        return linkedHashMap;
    }

    public static Map j(Activity activity) {
        return (Map) a(d(activity, "presenter"), "m0");
    }

    public static void l(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static void n(Activity activity) {
        String str;
        int size = j(activity).size();
        TextView textView = (TextView) d(activity, "e");
        if (size == 0) {
            str = "完成";
        } else {
            str = "完成(" + size + ")";
        }
        textView.setText(str);
        textView.setEnabled(size > 0);
        textView.setAlpha(size > 0 ? 1.0f : 0.5f);
    }

    public final void b(final Activity activity) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        String str;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Dialog dialog = this.t;
        if (dialog == null || !dialog.isShowing()) {
            final C0018e1 c0018e1I = i(activity);
            if (c0018e1I == null) {
                l(activity, "转发已过期，请重新选择消息");
                activity.finish();
                return;
            }
            final LinkedHashMap linkedHashMapH = h(activity, c0018e1I);
            if (linkedHashMapH.isEmpty() || linkedHashMapH.size() > 9) {
                return;
            }
            final ArrayList arrayList = new ArrayList(linkedHashMapH.keySet());
            final String str2 = this.r;
            int i = c0018e1I.c;
            if (i == 1) {
                str = "文字消息";
            } else {
                str = i == 5 ? "语音" : "照片";
            }
            final Dialog dialog2 = (Dialog) this.d.newInstance(activity, "将这条" + str + "转发给以下 " + arrayList.size() + " 人？\n\n" + String.join("、", linkedHashMapH.values()));
            this.f.invoke(dialog2, Proxy.newProxyInstance(this.b.getClassLoader(), new Class[]{this.b}, new InvocationHandler() { // from class: dev.local.newsoul.b1
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    Activity activity2 = activity;
                    String str3 = str2;
                    LinkedHashMap linkedHashMap = linkedHashMapH;
                    List list = arrayList;
                    Dialog dialog3 = dialog2;
                    C0014d1 c0014d1 = this.a;
                    c0014d1.getClass();
                    int i2 = 0;
                    if (method.getDeclaringClass() == Object.class) {
                        if (method.getName().equals("hashCode")) {
                            return Integer.valueOf(System.identityHashCode(obj));
                        }
                        if (method.getName().equals("equals")) {
                            return Boolean.valueOf(obj == objArr[0]);
                        }
                        return "NewSoulMessageForwardConfirm";
                    }
                    if (method.getName().equals("onClickConfirm")) {
                        try {
                            if (!activity2.isFinishing() && !activity2.isDestroyed() && str3.equals(c0014d1.r)) {
                                C0018e1 c0018e1I2 = c0014d1.i(activity2);
                                C0018e1 c0018e1 = c0018e1I;
                                if (c0018e1I2 == c0018e1 && C0014d1.h(activity2, c0018e1).keySet().equals(linkedHashMap.keySet())) {
                                    ArrayList<String> arrayListA = c0018e1.a((String) c0014d1.g.invoke(null, null), (ArrayList) list, SystemClock.elapsedRealtime());
                                    if (arrayListA == null) {
                                        throw new IllegalStateException("Expired confirmation");
                                    }
                                    for (String str4 : arrayListA) {
                                        if (!c0018e1.a.equals(c0014d1.g.invoke(null, null))) {
                                            throw new IllegalStateException("Account changed");
                                        }
                                        c0014d1.k(c0018e1, str4);
                                        i2++;
                                    }
                                    V.d("message.forward.dispatched", "type=" + c0018e1.c + "; targets=" + i2);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("已提交转发给 ");
                                    sb.append(i2);
                                    sb.append(" 人，请在目标会话查看");
                                    C0014d1.l(activity2, sb.toString());
                                }
                            }
                            throw new IllegalStateException("Selection changed");
                        } catch (Throwable th) {
                            try {
                                V.d("message.forward.failed", ("submitted=0; total=" + list.size()) + "\n" + V.c(th));
                                C0014d1.l(activity2, "转发中断，已提交 0/" + list.size() + "，请先核对目标会话");
                            } finally {
                                dialog3.dismiss();
                                activity2.finish();
                            }
                        }
                    }
                    return null;
                }
            }));
            this.t = dialog2;
            dialog2.setOnDismissListener(new DialogInterfaceOnDismissListenerC0010c1(this, dialog2, 0));
            dialog2.show();
            V.d("message.forward.confirmation", "type=" + i + "; targets=" + arrayList.size());
        }
    }

    public final void e(XposedModule xposedModule, ClassLoader classLoader) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        this.a = classLoader.loadClass("cn.soulapp.android.component.chat.SelectConversationActivity");
        Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.component.chat.dialog.j1");
        this.b = classLoader.loadClass("cn.soulapp.android.chat.listener.OnClickConfirmListener");
        this.d = clsLoadClass.getConstructor(Context.class, String.class);
        this.f = clsLoadClass.getMethod("d", this.b);
        this.g = classLoader.loadClass("cn.soulapp.imlib.j0").getMethod("g", null);
        Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage");
        Class<?> clsLoadClass3 = classLoader.loadClass("cn.soulapp.imlib.msg.ImMessage");
        this.h = clsLoadClass2.getMethod("create", String.class);
        this.i = clsLoadClass2.getMethod("setMsgContent", Serializable.class);
        Class cls = Integer.TYPE;
        this.j = clsLoadClass2.getMethod("setMsgType", cls);
        this.k = clsLoadClass2.getMethod("setTransExtMap", Map.class);
        this.l = classLoader.loadClass("cn.soulapp.android.component.chat.utils.y4").getMethod("u", clsLoadClass2);
        this.m = clsLoadClass3.getMethod("createChatSendMsg", clsLoadClass2, String.class);
        Class<?> clsLoadClass4 = classLoader.loadClass("cn.soulapp.imlib.i0");
        this.n = clsLoadClass4.getMethod("v", null);
        this.o = clsLoadClass4.getMethod("o", null);
        this.p = classLoader.loadClass("cn.soulapp.imlib.ChatManager").getMethod("q0", clsLoadClass3);
        Class<?> clsLoadClass5 = classLoader.loadClass("uj.k1");
        this.q = clsLoadClass5.getMethod("g2", classLoader.loadClass("cn.soulapp.android.chat.bean.UserConversation"));
        clsLoadClass5.getMethod("m0", null);
        Class<?> clsLoadClass6 = classLoader.loadClass("cn.soulapp.android.component.chat.adapter.ConversationAdapter");
        Class cls2 = Boolean.TYPE;
        clsLoadClass6.getMethod("k", cls2);
        Class<?> clsLoadClass7 = classLoader.loadClass("com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener");
        this.c = clsLoadClass7;
        this.e = clsLoadClass6.getMethod("setOnItemClickListener", clsLoadClass7);
        this.a.getDeclaredField("b");
        this.a.getDeclaredField("e");
        Method declaredMethod = this.a.getDeclaredMethod("F", cls);
        XposedInterface.HookBuilder id = xposedModule.hook(declaredMethod).setId("new-soul/message-forward-select");
        XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
        final int i = 0;
        id.setExceptionMode(exceptionMode).intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
            public final /* synthetic */ C0014d1 b;

            {
                this.b = this;
            }

            public final Object intercept(XposedInterface.Chain chain) {
                String str;
                switch (i) {
                    case 0:
                        C0014d1 c0014d1 = this.b;
                        c0014d1.getClass();
                        Activity activity = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                            break;
                        } catch (Throwable th) {
                            C0014d1.c(activity, th);
                        }
                        return null;
                    case 1:
                        C0014d1 c0014d2 = this.b;
                        c0014d2.getClass();
                        Activity activity2 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity2)) {
                            return chain.proceed();
                        }
                        ArrayList arrayList = new ArrayList();
                        try {
                            C0018e1 c0018e1I = c0014d2.i(activity2);
                            if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                for (Object obj : (List) chain.getArg(1)) {
                                    Object objD = C0014d1.d(obj, "conversation");
                                    if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                        arrayList.add(obj);
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            C0014d1.c(activity2, th2);
                        }
                        Object[] array = chain.getArgs().toArray();
                        array[1] = arrayList;
                        return chain.proceed(array);
                    case 2:
                        C0014d1 c0014d3 = this.b;
                        c0014d3.getClass();
                        Object objProceed = chain.proceed();
                        Activity activity3 = (Activity) chain.getThisObject();
                        if (C0014d1.f(activity3)) {
                            try {
                                if (c0014d3.i(activity3) == null) {
                                    activity3.finish();
                                } else {
                                    Object objD2 = C0014d1.d(activity3, "b");
                                    objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                    c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                    String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                    for (int i2 = 0; i2 < 3; i2++) {
                                        int identifier = activity3.getResources().getIdentifier(strArr[i2], "id", activity3.getPackageName());
                                        View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                        if (viewFindViewById != null) {
                                            viewFindViewById.setVisibility(8);
                                        }
                                    }
                                    View view = (View) C0014d1.d(activity3, "e");
                                    view.setVisibility(0);
                                    view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                    C0014d1.n(activity3);
                                }
                            } catch (Throwable th3) {
                                C0014d1.c(activity3, th3);
                                activity3.finish();
                            }
                        }
                        return objProceed;
                    case 3:
                        C0014d1 c0014d4 = this.b;
                        c0014d4.getClass();
                        Activity activity4 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity4)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d4.b(activity4);
                            break;
                        } catch (Throwable th4) {
                            C0014d1.c(activity4, th4);
                        }
                        return null;
                    case 4:
                        this.b.getClass();
                        if (C0014d1.f((Activity) chain.getThisObject())) {
                            return null;
                        }
                        return chain.proceed();
                    default:
                        C0014d1 c0014d5 = this.b;
                        c0014d5.getClass();
                        Activity activity5 = (Activity) chain.getThisObject();
                        if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                            c0014d5.s = null;
                            c0014d5.r = null;
                        }
                        Dialog dialog = c0014d5.t;
                        if (dialog != null && dialog.getContext() == activity5) {
                            c0014d5.t.dismiss();
                            c0014d5.t = null;
                        }
                        return chain.proceed();
                }
            }
        });
        xposedModule.deoptimize(declaredMethod);
        Method declaredMethod2 = this.a.getDeclaredMethod("loadConversationList", List.class, List.class, cls2);
        final int i2 = 1;
        xposedModule.hook(declaredMethod2).setId("new-soul/message-forward-list").setExceptionMode(exceptionMode).intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
            public final /* synthetic */ C0014d1 b;

            {
                this.b = this;
            }

            public final Object intercept(XposedInterface.Chain chain) {
                String str;
                switch (i2) {
                    case 0:
                        C0014d1 c0014d1 = this.b;
                        c0014d1.getClass();
                        Activity activity = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                            break;
                        } catch (Throwable th) {
                            C0014d1.c(activity, th);
                        }
                        return null;
                    case 1:
                        C0014d1 c0014d2 = this.b;
                        c0014d2.getClass();
                        Activity activity2 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity2)) {
                            return chain.proceed();
                        }
                        ArrayList arrayList = new ArrayList();
                        try {
                            C0018e1 c0018e1I = c0014d2.i(activity2);
                            if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                for (Object obj : (List) chain.getArg(1)) {
                                    Object objD = C0014d1.d(obj, "conversation");
                                    if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                        arrayList.add(obj);
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            C0014d1.c(activity2, th2);
                        }
                        Object[] array = chain.getArgs().toArray();
                        array[1] = arrayList;
                        return chain.proceed(array);
                    case 2:
                        C0014d1 c0014d3 = this.b;
                        c0014d3.getClass();
                        Object objProceed = chain.proceed();
                        Activity activity3 = (Activity) chain.getThisObject();
                        if (C0014d1.f(activity3)) {
                            try {
                                if (c0014d3.i(activity3) == null) {
                                    activity3.finish();
                                } else {
                                    Object objD2 = C0014d1.d(activity3, "b");
                                    objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                    c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                    String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                    for (int i3 = 0; i3 < 3; i3++) {
                                        int identifier = activity3.getResources().getIdentifier(strArr[i3], "id", activity3.getPackageName());
                                        View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                        if (viewFindViewById != null) {
                                            viewFindViewById.setVisibility(8);
                                        }
                                    }
                                    View view = (View) C0014d1.d(activity3, "e");
                                    view.setVisibility(0);
                                    view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                    C0014d1.n(activity3);
                                }
                            } catch (Throwable th3) {
                                C0014d1.c(activity3, th3);
                                activity3.finish();
                            }
                        }
                        return objProceed;
                    case 3:
                        C0014d1 c0014d4 = this.b;
                        c0014d4.getClass();
                        Activity activity4 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity4)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d4.b(activity4);
                            break;
                        } catch (Throwable th4) {
                            C0014d1.c(activity4, th4);
                        }
                        return null;
                    case 4:
                        this.b.getClass();
                        if (C0014d1.f((Activity) chain.getThisObject())) {
                            return null;
                        }
                        return chain.proceed();
                    default:
                        C0014d1 c0014d5 = this.b;
                        c0014d5.getClass();
                        Activity activity5 = (Activity) chain.getThisObject();
                        if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                            c0014d5.s = null;
                            c0014d5.r = null;
                        }
                        Dialog dialog = c0014d5.t;
                        if (dialog != null && dialog.getContext() == activity5) {
                            c0014d5.t.dismiss();
                            c0014d5.t = null;
                        }
                        return chain.proceed();
                }
            }
        });
        xposedModule.deoptimize(declaredMethod2);
        Method declaredMethod3 = this.a.getDeclaredMethod("init", Bundle.class);
        XposedInterface.HookBuilder exceptionMode2 = xposedModule.hook(declaredMethod3).setId("new-soul/message-forward-ui").setExceptionMode(exceptionMode);
        final int i3 = 2;
        exceptionMode2.intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
            public final /* synthetic */ C0014d1 b;

            {
                this.b = this;
            }

            public final Object intercept(XposedInterface.Chain chain) {
                String str;
                switch (i3) {
                    case 0:
                        C0014d1 c0014d1 = this.b;
                        c0014d1.getClass();
                        Activity activity = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                            break;
                        } catch (Throwable th) {
                            C0014d1.c(activity, th);
                        }
                        return null;
                    case 1:
                        C0014d1 c0014d2 = this.b;
                        c0014d2.getClass();
                        Activity activity2 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity2)) {
                            return chain.proceed();
                        }
                        ArrayList arrayList = new ArrayList();
                        try {
                            C0018e1 c0018e1I = c0014d2.i(activity2);
                            if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                for (Object obj : (List) chain.getArg(1)) {
                                    Object objD = C0014d1.d(obj, "conversation");
                                    if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                        arrayList.add(obj);
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            C0014d1.c(activity2, th2);
                        }
                        Object[] array = chain.getArgs().toArray();
                        array[1] = arrayList;
                        return chain.proceed(array);
                    case 2:
                        C0014d1 c0014d3 = this.b;
                        c0014d3.getClass();
                        Object objProceed = chain.proceed();
                        Activity activity3 = (Activity) chain.getThisObject();
                        if (C0014d1.f(activity3)) {
                            try {
                                if (c0014d3.i(activity3) == null) {
                                    activity3.finish();
                                } else {
                                    Object objD2 = C0014d1.d(activity3, "b");
                                    objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                    c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                    String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                    for (int i4 = 0; i4 < 3; i4++) {
                                        int identifier = activity3.getResources().getIdentifier(strArr[i4], "id", activity3.getPackageName());
                                        View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                        if (viewFindViewById != null) {
                                            viewFindViewById.setVisibility(8);
                                        }
                                    }
                                    View view = (View) C0014d1.d(activity3, "e");
                                    view.setVisibility(0);
                                    view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                    C0014d1.n(activity3);
                                }
                            } catch (Throwable th3) {
                                C0014d1.c(activity3, th3);
                                activity3.finish();
                            }
                        }
                        return objProceed;
                    case 3:
                        C0014d1 c0014d4 = this.b;
                        c0014d4.getClass();
                        Activity activity4 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity4)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d4.b(activity4);
                            break;
                        } catch (Throwable th4) {
                            C0014d1.c(activity4, th4);
                        }
                        return null;
                    case 4:
                        this.b.getClass();
                        if (C0014d1.f((Activity) chain.getThisObject())) {
                            return null;
                        }
                        return chain.proceed();
                    default:
                        C0014d1 c0014d5 = this.b;
                        c0014d5.getClass();
                        Activity activity5 = (Activity) chain.getThisObject();
                        if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                            c0014d5.s = null;
                            c0014d5.r = null;
                        }
                        Dialog dialog = c0014d5.t;
                        if (dialog != null && dialog.getContext() == activity5) {
                            c0014d5.t.dismiss();
                            c0014d5.t = null;
                        }
                        return chain.proceed();
                }
            }
        });
        xposedModule.deoptimize(declaredMethod3);
        Method[] methodArr = {this.a.getDeclaredMethod("E", View.class), this.a.getDeclaredMethod("S", null)};
        for (int i4 = 0; i4 < 2; i4++) {
            Method method = methodArr[i4];
            final int i5 = 3;
            xposedModule.hook(method).setId("new-soul/message-forward-complete-" + method.getName()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
                public final /* synthetic */ C0014d1 b;

                {
                    this.b = this;
                }

                public final Object intercept(XposedInterface.Chain chain) {
                    String str;
                    switch (i5) {
                        case 0:
                            C0014d1 c0014d1 = this.b;
                            c0014d1.getClass();
                            Activity activity = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity)) {
                                return chain.proceed();
                            }
                            try {
                                c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                                break;
                            } catch (Throwable th) {
                                C0014d1.c(activity, th);
                            }
                            return null;
                        case 1:
                            C0014d1 c0014d2 = this.b;
                            c0014d2.getClass();
                            Activity activity2 = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity2)) {
                                return chain.proceed();
                            }
                            ArrayList arrayList = new ArrayList();
                            try {
                                C0018e1 c0018e1I = c0014d2.i(activity2);
                                if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                    for (Object obj : (List) chain.getArg(1)) {
                                        Object objD = C0014d1.d(obj, "conversation");
                                        if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                            arrayList.add(obj);
                                        }
                                    }
                                }
                            } catch (Throwable th2) {
                                C0014d1.c(activity2, th2);
                            }
                            Object[] array = chain.getArgs().toArray();
                            array[1] = arrayList;
                            return chain.proceed(array);
                        case 2:
                            C0014d1 c0014d3 = this.b;
                            c0014d3.getClass();
                            Object objProceed = chain.proceed();
                            Activity activity3 = (Activity) chain.getThisObject();
                            if (C0014d1.f(activity3)) {
                                try {
                                    if (c0014d3.i(activity3) == null) {
                                        activity3.finish();
                                    } else {
                                        Object objD2 = C0014d1.d(activity3, "b");
                                        objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                        c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                        String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                        for (int i6 = 0; i6 < 3; i6++) {
                                            int identifier = activity3.getResources().getIdentifier(strArr[i6], "id", activity3.getPackageName());
                                            View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                            if (viewFindViewById != null) {
                                                viewFindViewById.setVisibility(8);
                                            }
                                        }
                                        View view = (View) C0014d1.d(activity3, "e");
                                        view.setVisibility(0);
                                        view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                        C0014d1.n(activity3);
                                    }
                                } catch (Throwable th3) {
                                    C0014d1.c(activity3, th3);
                                    activity3.finish();
                                }
                            }
                            return objProceed;
                        case 3:
                            C0014d1 c0014d4 = this.b;
                            c0014d4.getClass();
                            Activity activity4 = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity4)) {
                                return chain.proceed();
                            }
                            try {
                                c0014d4.b(activity4);
                                break;
                            } catch (Throwable th4) {
                                C0014d1.c(activity4, th4);
                            }
                            return null;
                        case 4:
                            this.b.getClass();
                            if (C0014d1.f((Activity) chain.getThisObject())) {
                                return null;
                            }
                            return chain.proceed();
                        default:
                            C0014d1 c0014d5 = this.b;
                            c0014d5.getClass();
                            Activity activity5 = (Activity) chain.getThisObject();
                            if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                                c0014d5.s = null;
                                c0014d5.r = null;
                            }
                            Dialog dialog = c0014d5.t;
                            if (dialog != null && dialog.getContext() == activity5) {
                                c0014d5.t.dismiss();
                                c0014d5.t = null;
                            }
                            return chain.proceed();
                    }
                }
            });
            xposedModule.deoptimize(method);
        }
        Method[] methodArr2 = {this.a.getDeclaredMethod("D", Object.class), this.a.getDeclaredMethod("onClick", View.class)};
        while (i < 2) {
            Method method2 = methodArr2[i];
            final int i6 = 4;
            xposedModule.hook(method2).setId("new-soul/message-forward-navigation-" + method2.getName()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
                public final /* synthetic */ C0014d1 b;

                {
                    this.b = this;
                }

                public final Object intercept(XposedInterface.Chain chain) {
                    String str;
                    switch (i6) {
                        case 0:
                            C0014d1 c0014d1 = this.b;
                            c0014d1.getClass();
                            Activity activity = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity)) {
                                return chain.proceed();
                            }
                            try {
                                c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                                break;
                            } catch (Throwable th) {
                                C0014d1.c(activity, th);
                            }
                            return null;
                        case 1:
                            C0014d1 c0014d2 = this.b;
                            c0014d2.getClass();
                            Activity activity2 = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity2)) {
                                return chain.proceed();
                            }
                            ArrayList arrayList = new ArrayList();
                            try {
                                C0018e1 c0018e1I = c0014d2.i(activity2);
                                if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                    for (Object obj : (List) chain.getArg(1)) {
                                        Object objD = C0014d1.d(obj, "conversation");
                                        if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                            arrayList.add(obj);
                                        }
                                    }
                                }
                            } catch (Throwable th2) {
                                C0014d1.c(activity2, th2);
                            }
                            Object[] array = chain.getArgs().toArray();
                            array[1] = arrayList;
                            return chain.proceed(array);
                        case 2:
                            C0014d1 c0014d3 = this.b;
                            c0014d3.getClass();
                            Object objProceed = chain.proceed();
                            Activity activity3 = (Activity) chain.getThisObject();
                            if (C0014d1.f(activity3)) {
                                try {
                                    if (c0014d3.i(activity3) == null) {
                                        activity3.finish();
                                    } else {
                                        Object objD2 = C0014d1.d(activity3, "b");
                                        objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                        c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                        String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                        for (int i7 = 0; i7 < 3; i7++) {
                                            int identifier = activity3.getResources().getIdentifier(strArr[i7], "id", activity3.getPackageName());
                                            View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                            if (viewFindViewById != null) {
                                                viewFindViewById.setVisibility(8);
                                            }
                                        }
                                        View view = (View) C0014d1.d(activity3, "e");
                                        view.setVisibility(0);
                                        view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                        C0014d1.n(activity3);
                                    }
                                } catch (Throwable th3) {
                                    C0014d1.c(activity3, th3);
                                    activity3.finish();
                                }
                            }
                            return objProceed;
                        case 3:
                            C0014d1 c0014d4 = this.b;
                            c0014d4.getClass();
                            Activity activity4 = (Activity) chain.getThisObject();
                            if (!C0014d1.f(activity4)) {
                                return chain.proceed();
                            }
                            try {
                                c0014d4.b(activity4);
                                break;
                            } catch (Throwable th4) {
                                C0014d1.c(activity4, th4);
                            }
                            return null;
                        case 4:
                            this.b.getClass();
                            if (C0014d1.f((Activity) chain.getThisObject())) {
                                return null;
                            }
                            return chain.proceed();
                        default:
                            C0014d1 c0014d5 = this.b;
                            c0014d5.getClass();
                            Activity activity5 = (Activity) chain.getThisObject();
                            if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                                c0014d5.s = null;
                                c0014d5.r = null;
                            }
                            Dialog dialog = c0014d5.t;
                            if (dialog != null && dialog.getContext() == activity5) {
                                c0014d5.t.dismiss();
                                c0014d5.t = null;
                            }
                            return chain.proceed();
                    }
                }
            });
            xposedModule.deoptimize(method2);
            i++;
        }
        final int i7 = 5;
        xposedModule.hook(Activity.class.getDeclaredMethod("onDestroy", null)).setId("new-soul/message-forward-cleanup").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker(this) { // from class: dev.local.newsoul.a1
            public final /* synthetic */ C0014d1 b;

            {
                this.b = this;
            }

            public final Object intercept(XposedInterface.Chain chain) {
                String str;
                switch (i7) {
                    case 0:
                        C0014d1 c0014d1 = this.b;
                        c0014d1.getClass();
                        Activity activity = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d1.m(activity, ((Integer) chain.getArg(0)).intValue());
                            break;
                        } catch (Throwable th) {
                            C0014d1.c(activity, th);
                        }
                        return null;
                    case 1:
                        C0014d1 c0014d2 = this.b;
                        c0014d2.getClass();
                        Activity activity2 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity2)) {
                            return chain.proceed();
                        }
                        ArrayList arrayList = new ArrayList();
                        try {
                            C0018e1 c0018e1I = c0014d2.i(activity2);
                            if (c0018e1I != null && (chain.getArg(1) instanceof List)) {
                                for (Object obj : (List) chain.getArg(1)) {
                                    Object objD = C0014d1.d(obj, "conversation");
                                    if (C0014d1.d(obj, "user") != null && objD != null && c0018e1I.c((String) C0014d1.a(objD, "getToUserId"), ((Integer) C0014d1.a(objD, "getChatType")).intValue())) {
                                        arrayList.add(obj);
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            C0014d1.c(activity2, th2);
                        }
                        Object[] array = chain.getArgs().toArray();
                        array[1] = arrayList;
                        return chain.proceed(array);
                    case 2:
                        C0014d1 c0014d3 = this.b;
                        c0014d3.getClass();
                        Object objProceed = chain.proceed();
                        Activity activity3 = (Activity) chain.getThisObject();
                        if (C0014d1.f(activity3)) {
                            try {
                                if (c0014d3.i(activity3) == null) {
                                    activity3.finish();
                                } else {
                                    Object objD2 = C0014d1.d(activity3, "b");
                                    objD2.getClass().getMethod("k", Boolean.TYPE).invoke(objD2, Boolean.TRUE);
                                    c0014d3.e.invoke(objD2, Proxy.newProxyInstance(c0014d3.c.getClassLoader(), new Class[]{c0014d3.c}, new V0(c0014d3, activity3)));
                                    String[] strArr = {"ic_user_follow", "tv_mutily_choose", "tv_share"};
                                    for (int i8 = 0; i8 < 3; i8++) {
                                        int identifier = activity3.getResources().getIdentifier(strArr[i8], "id", activity3.getPackageName());
                                        View viewFindViewById = identifier == 0 ? null : activity3.findViewById(identifier);
                                        if (viewFindViewById != null) {
                                            viewFindViewById.setVisibility(8);
                                        }
                                    }
                                    View view = (View) C0014d1.d(activity3, "e");
                                    view.setVisibility(0);
                                    view.setOnClickListener(new C0(c0014d3, activity3, 1));
                                    C0014d1.n(activity3);
                                }
                            } catch (Throwable th3) {
                                C0014d1.c(activity3, th3);
                                activity3.finish();
                            }
                        }
                        return objProceed;
                    case 3:
                        C0014d1 c0014d4 = this.b;
                        c0014d4.getClass();
                        Activity activity4 = (Activity) chain.getThisObject();
                        if (!C0014d1.f(activity4)) {
                            return chain.proceed();
                        }
                        try {
                            c0014d4.b(activity4);
                            break;
                        } catch (Throwable th4) {
                            C0014d1.c(activity4, th4);
                        }
                        return null;
                    case 4:
                        this.b.getClass();
                        if (C0014d1.f((Activity) chain.getThisObject())) {
                            return null;
                        }
                        return chain.proceed();
                    default:
                        C0014d1 c0014d5 = this.b;
                        c0014d5.getClass();
                        Activity activity5 = (Activity) chain.getThisObject();
                        if (c0014d5.a.isInstance(activity5) && C0014d1.f(activity5) && !activity5.isChangingConfigurations() && (str = c0014d5.r) != null && str.equals(activity5.getIntent().getStringExtra("dev.local.newsoul.message_forward_token"))) {
                            c0014d5.s = null;
                            c0014d5.r = null;
                        }
                        Dialog dialog = c0014d5.t;
                        if (dialog != null && dialog.getContext() == activity5) {
                            c0014d5.t.dismiss();
                            c0014d5.t = null;
                        }
                        return chain.proceed();
                }
            }
        });
    }

    public final void g(Activity activity, String str, String str2, int i, Object obj) {
        if (!this.u || activity.isFinishing() || activity.isDestroyed() || !str.equals(this.g.invoke(null, null))) {
            throw new IllegalStateException("Forward unavailable");
        }
        this.s = new C0018e1(str, str2, i, F.c(i, obj), SystemClock.elapsedRealtime());
        this.r = UUID.randomUUID().toString();
        try {
            activity.startActivity(new Intent(activity, (Class<?>) this.a).putExtra("dev.local.newsoul.message_forward_token", this.r).putExtra("isChoice", false).putExtra("isFromPreview", true).putExtra("onlyFinishAfterShare", true));
            V.d("message.forward.picker_open", "type=" + i);
        } catch (RuntimeException e) {
            this.s = null;
            this.r = null;
            throw e;
        }
    }

    public final C0018e1 i(Activity activity) {
        String str;
        if (this.s == null || (str = this.r) == null || !str.equals(activity.getIntent().getStringExtra("dev.local.newsoul.message_forward_token")) || !this.s.b((String) this.g.invoke(null, null), SystemClock.elapsedRealtime())) {
            return null;
        }
        return this.s;
    }

    public final void k(C0018e1 c0018e1, String str) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = this.h.invoke(null, str);
        Method method = this.i;
        Object obj = c0018e1.d;
        int i = c0018e1.c;
        method.invoke(objInvoke, F.c(i, obj));
        this.k.invoke(objInvoke, new HashMap());
        this.j.invoke(objInvoke, Integer.valueOf(i));
        this.l.invoke(null, objInvoke);
        this.p.invoke(this.o.invoke(this.n.invoke(null, null), null), this.m.invoke(null, objInvoke, str));
    }

    public final void m(Activity activity, int i) throws IllegalAccessException, InvocationTargetException {
        Object obj;
        Object objD;
        C0018e1 c0018e1I = i(activity);
        if (c0018e1I == null) {
            l(activity, "转发已过期，请重新选择消息");
            activity.finish();
            return;
        }
        List list = (List) a(d(activity, "b"), "getAllData");
        if (i < 0 || i >= list.size() || (objD = d((obj = list.get(i)), "conversation")) == null || d(obj, "user") == null) {
            return;
        }
        String str = (String) a(objD, "getToUserId");
        if (c0018e1I.c(str, ((Integer) a(objD, "getChatType")).intValue())) {
            Map mapJ = j(activity);
            if (!mapJ.containsValue(str) && mapJ.size() >= 9) {
                l(activity, "最多选择9人");
                return;
            }
            this.q.invoke(d(activity, "presenter"), obj);
            a(d(activity, "b"), "notifyDataSetChanged");
            n(activity);
            V.d("message.forward.selection", "count=" + j(activity).size());
        }
    }
}
