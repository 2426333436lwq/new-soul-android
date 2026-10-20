package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: dev.local.newsoul.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0022f1 implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ AccessibleObject b;
    public final /* synthetic */ AccessibleObject c;
    public final /* synthetic */ AccessibleObject d;
    public final /* synthetic */ Executable e;

    public /* synthetic */ C0022f1(AccessibleObject accessibleObject, AccessibleObject accessibleObject2, AccessibleObject accessibleObject3, Executable executable, int i) {
        this.a = i;
        this.b = accessibleObject;
        this.c = accessibleObject2;
        this.d = accessibleObject3;
        this.e = executable;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.a) {
            case 0:
                Method method = (Method) this.b;
                Method method2 = (Method) this.c;
                Method method3 = (Method) this.d;
                Method method4 = (Method) this.e;
                Object arg = chain.getArg(0);
                try {
                    String str = (String) method.invoke(null, null);
                    if (arg != null && str != null && !str.isEmpty() && str.equals(method2.invoke(arg, null)) && !((Boolean) method3.invoke(arg, null)).booleanValue() && AbstractC0090w2.o((Context) method4.invoke(null, null)).getBoolean("chat_own_recall_no_time_limit", true)) {
                        ThreadLocal threadLocal = AbstractC0026g1.b;
                        Object obj = threadLocal.get();
                        threadLocal.set(arg);
                        try {
                            Object objProceed = chain.proceed();
                        } finally {
                            if (obj == null) {
                                threadLocal.remove();
                            } else {
                                threadLocal.set(obj);
                            }
                        }
                    }
                } catch (Throwable th) {
                    Log.w("SoulHook102", "own recall eligibility unavailable", th);
                }
                return chain.proceed();
            default:
                Field field = (Field) this.b;
                Field field2 = (Field) this.c;
                Constructor constructor = (Constructor) this.e;
                try {
                    if (field.get(chain.getThisObject()) != null) {
                        List list = (List) field2.get(chain.getThisObject());
                        ArrayList arrayList = new ArrayList();
                        Field field3 = (Field) this.d;
                        if (list != null) {
                            for (Object obj2 : list) {
                                if (field3.getInt(obj2) != -220922) {
                                    arrayList.add(obj2);
                                }
                            }
                        }
                        int size = arrayList.size();
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (field3.getInt(arrayList.get(i)) == 26) {
                                size = i;
                                Object objNewInstance = constructor.newInstance(51, "复制文案", Boolean.FALSE);
                                field3.setInt(objNewInstance, -220922);
                                arrayList.add(size, objNewInstance);
                                field2.set(chain.getThisObject(), arrayList);
                                Log.i("SoulHook102", "post copy action added; count=" + arrayList.size());
                            }
                            break;
                        }
                        Object objNewInstance2 = constructor.newInstance(51, "复制文案", Boolean.FALSE);
                        field3.setInt(objNewInstance2, -220922);
                        arrayList.add(size, objNewInstance2);
                        field2.set(chain.getThisObject(), arrayList);
                        Log.i("SoulHook102", "post copy action added; count=" + arrayList.size());
                    }
                    break;
                } catch (Throwable th2) {
                    Log.e("SoulHook102", "post copy action unavailable", th2);
                }
                return chain.proceed();
        }
    }
}
