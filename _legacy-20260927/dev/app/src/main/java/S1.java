package dev.local.newsoul;

import android.view.View;
import android.app.Activity;
import android.util.Log;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* 本文件合并了仅大小写不同的两个类 S1 / s1（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: S1.class */
public final /* synthetic */ class S1 implements View.OnClickListener {
    public final int a;
    public final C0070r2 b;

    public /* synthetic */ S1(C0070r2 r2Var, int i) {
        this.a = i;
        this.b = r2Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                C0070r2 r2Var = this.b;
                if (r2Var.isEnabled()) {
                    r2Var.toggle();
                }
                break;
            default:
                C0070r2 r2Var2 = this.b;
                if (r2Var2.isEnabled()) {
                    r2Var2.toggle();
                }
                break;
        }
    }
}

/* JADX INFO: loaded from: s1.class */
final /* synthetic */ class s1 implements XposedInterface.Hooker {
    public final Field a;
    public final Field b;
    public final Field c;
    public final Constructor d;
    public final Field e;
    public final Field f;
    public final Field g;
    public final Field h;
    public final Field i;

    public /* synthetic */ s1(Field field, Field field2, Field field3, Constructor constructor, Field field4, Field field5, Field field6, Field field7, Field field8) {
        this.a = field;
        this.b = field2;
        this.c = field3;
        this.d = constructor;
        this.e = field4;
        this.f = field5;
        this.g = field6;
        this.h = field7;
        this.i = field8;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        C0077t1 t1Var;
        Object objProceed;
        String str;
        Field field = this.a;
        Field field2 = this.b;
        Field field3 = this.c;
        Constructor constructor = this.d;
        Field field4 = this.e;
        Field field5 = this.f;
        Field field6 = this.g;
        if (AbstractC0081u1.f) {
            Object thisObject = chain.getThisObject();
            try {
                Activity activity = (Activity) AbstractC0081u1.c(thisObject, "getActivity");
                Object obj = field.get(thisObject);
                t1Var = null;
                if (activity != null) {
                    t1Var = null;
                    if ("cn.soulapp.android.component.home.user.UserHomeActivity".equals(activity.getClass().getName())) {
                        t1Var = null;
                        if (obj != null) {
                            t1Var = null;
                            if (field2.get(thisObject) == null) {
                                t1Var = null;
                                if (field3.getInt(thisObject) == 2) {
                                    String str2 = (String) AbstractC0081u1.f(obj.getClass(), "userIdEcpt").get(obj);
                                    String str3 = (String) AbstractC0081u1.f(obj.getClass(), "alias").get(obj);
                                    if (str3 != null) {
                                        str = str3;
                                        if (str3.trim().isEmpty()) {
                                            str = (String) AbstractC0081u1.f(obj.getClass(), "signature").get(obj);
                                        }
                                    } else {
                                        str = (String) AbstractC0081u1.f(obj.getClass(), "signature").get(obj);
                                    }
                                    t1Var = null;
                                    if (str2 != null) {
                                        t1Var = null;
                                        if (!str2.trim().isEmpty()) {
                                            t1Var = null;
                                            if (str != null) {
                                                t1Var = null;
                                                if (!str.trim().isEmpty()) {
                                                    int identifier = activity.getResources().getIdentifier("icon_share_collect", "drawable", activity.getPackageName());
                                                    t1Var = null;
                                                    if (identifier != 0) {
                                                        String strD = AbstractC0081u1.d();
                                                        Object objNewInstance = constructor.newInstance(0, "插眼…", Boolean.FALSE);
                                                        field4.setInt(objNewInstance, 5133072);
                                                        field5.setInt(objNewInstance, identifier);
                                                        List list = (List) field6.get(thisObject);
                                                        ArrayList arrayList = new ArrayList();
                                                        if (list != null) {
                                                            for (Object obj2 : list) {
                                                                if (field4.getInt(obj2) != 5133072) {
                                                                    arrayList.add(obj2);
                                                                }
                                                            }
                                                        }
                                                        arrayList.add(0, objNewInstance);
                                                        field6.set(thisObject, arrayList);
                                                        t1Var = new C0077t1(strD, str2, str, objNewInstance);
                                                        try {
                                                            AbstractC0081u1.c.put(thisObject, t1Var);
                                                        } catch (Exception e) {
                                                            e = e;
                                                            Log.w("SoulHook102", "profile bookmark menu unavailable", e);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception C0019e2) {
                t1Var = null;
            }
            Object objProceed2 = chain.proceed();
            objProceed = objProceed2;
            if (t1Var != null) {
                Activity activity2 = (Activity) AbstractC0081u1.c(thisObject, "getActivity");
                objProceed = objProceed2;
                if (activity2 != null) {
                    AbstractC0081u1.b.execute(new RunnableC0050m1(t1Var, activity2, thisObject, this.h, this.i, 0));
                    objProceed = objProceed2;
                }
            }
        } else {
            objProceed = chain.proceed();
        }
        return objProceed;
    }
}
