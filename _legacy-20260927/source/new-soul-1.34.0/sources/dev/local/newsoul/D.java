package dev.local.newsoul;

import android.content.Context;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class D implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ D(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    public final Object intercept(XposedInterface.Chain chain) throws IllegalAccessException, InvocationTargetException {
        char c;
        char c2;
        char c3;
        switch (this.a) {
            case 0:
                Class cls = (Class) this.c;
                F f = (F) this.b;
                f.getClass();
                Object[] objArr = null;
                C0095y c0095yA = V.a() ? A.a(false, chain.getArg(0)) : null;
                if (c0095yA != null) {
                    try {
                        c0095yA.a.d++;
                        Q0.r("repeat.render.enter", "source=" + Q0.X());
                    } catch (Throwable th) {
                        if (c0095yA == null) {
                            throw th;
                        }
                        try {
                            c0095yA.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                }
                Object arg = chain.getArg(0);
                Object objI = f.i(arg);
                A.b(objI);
                Object objProceed = objI == arg ? chain.proceed() : chain.proceed(new Object[]{objI});
                try {
                    List list = (List) objI;
                    if (list == null) {
                        Q0.r("repeat.render", "entries=null");
                        if (c0095yA != null) {
                            c0095yA.close();
                        }
                    } else {
                        char c4 = 21250;
                        char c5 = 21249;
                        char c6 = 21251;
                        if (V.a()) {
                            Iterator it = list.iterator();
                            int i = 0;
                            while (it.hasNext()) {
                                int iIntValue = ((Integer) f.c.invoke(it.next(), null)).intValue();
                                if (iIntValue == 5133057 || iIntValue == 5133058 || iIntValue == 5133059) {
                                    i++;
                                }
                            }
                            Q0.r("repeat.render", "count=" + list.size() + "; custom=" + i);
                        }
                        Field[] declaredFields = cls.getDeclaredFields();
                        int length = declaredFields.length;
                        int i2 = 0;
                        while (i2 < length) {
                            Field field = declaredFields[i2];
                            if (GridLayout.class.isAssignableFrom(field.getType())) {
                                field.setAccessible(true);
                                GridLayout gridLayout = (GridLayout) field.get(chain.getThisObject());
                                int childCount = gridLayout == null ? -1 : gridLayout.getChildCount();
                                Q0.r("repeat.render.grid", "children=" + childCount + "; entries=" + list.size());
                                if (gridLayout != null && gridLayout.getChildCount() == list.size()) {
                                    int i3 = 0;
                                    while (i3 < list.size()) {
                                        int iIntValue2 = ((Integer) f.c.invoke(list.get(i3), objArr)).intValue();
                                        ImageView imageViewA = AbstractC0038j1.a(gridLayout.getChildAt(i3));
                                        if (iIntValue2 == 5133059 && imageViewA != null) {
                                            imageViewA.setImageDrawable(new e3(imageViewA.getResources().getDisplayMetrics().density));
                                        }
                                        AbstractC0038j1.b(imageViewA, iIntValue2 == 5133057 || iIntValue2 == 5133058 || iIntValue2 == 5133059);
                                        i3++;
                                        objArr = null;
                                    }
                                }
                                c = 21251;
                                c2 = 21249;
                                c3 = 21250;
                            } else {
                                c3 = c4;
                                c = c6;
                                c2 = c5;
                            }
                            i2++;
                            c5 = c2;
                            c4 = c3;
                            c6 = c;
                            objArr = null;
                        }
                        if (c0095yA != null) {
                            c0095yA.close();
                        }
                    }
                } catch (Throwable th3) {
                    Q0.s("repeat.render.failed", th3);
                    Log.w("SoulHook102", "chat action marks unavailable");
                }
                return objProceed;
            default:
                Object objInvoke = ((Method) this.b).invoke(null, null);
                if (!(objInvoke instanceof Context) || !AbstractC0090w2.o((Context) objInvoke).getBoolean("block_splash", true)) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
                ((XposedModule) this.c).log(4, "SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
                return Boolean.FALSE;
        }
    }
}
