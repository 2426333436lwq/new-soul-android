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
import android.view.View;

/* 本文件合并了仅大小写不同的两个类 D / d（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: D.class */
public final /* synthetic */ class D implements XposedInterface.Hooker {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ D(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0134  */
    /* JADX WARN: Code duplicated, block: B:49:0x019e  */
    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        char c;
        char c2;
        int i;
        Object objProceed;
        switch (this.a) {
            case 0:
                Class cls = (Class) this.c;
                F f = (F) this.b;
                f.getClass();
                C0095y yVarA = V.a() ? A.a(false, chain.getArg(0)) : null;
                if (yVarA != null) {
                    try {
                        yVarA.a.d++;
                        Q0.r("repeat.render.enter", "source=" + Q0.X());
                    } catch (Throwable th) {
                        if (yVarA != null) {
                            try {
                                yVarA.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
                Object arg = chain.getArg(0);
                Object objI = f.i(arg);
                A.b(objI);
                Object objProceed2 = objI == arg ? chain.proceed() : chain.proceed(new Object[]{objI});
                try {
                    List list = (List) objI;
                    if (list == null) {
                        Q0.r("repeat.render", "entries=null");
                        if (yVarA != null) {
                            yVarA.close();
                        }
                    } else {
                        if (V.a()) {
                            Iterator it = list.iterator();
                            int C0035i2 = 0;
                            while (true) {
                                int i3 = C0035i2;
                                if (it.hasNext()) {
                                    int iIntValue = ((Integer) f.c.invoke(it.next(), null)).intValue();
                                    if (iIntValue == 5133057 || iIntValue == 5133058) {
                                        i = i3 + 1;
                                    } else {
                                        i = i3;
                                        if (iIntValue == 5133059) {
                                            i = i3 + 1;
                                        }
                                    }
                                    C0035i2 = i;
                                } else {
                                    Q0.r("repeat.render", "count=" + list.size() + "; custom=" + i3);
                                }
                            }
                        }
                        Field[] declaredFields = cls.getDeclaredFields();
                        int length = declaredFields.length;
                        char c3 = 21251;
                        char c4 = 21249;
                        int i4 = 0;
                        while (i4 < length) {
                            Field field = declaredFields[i4];
                            if (GridLayout.class.isAssignableFrom(field.getType())) {
                                field.setAccessible(true);
                                GridLayout gridLayout = (GridLayout) field.get(chain.getThisObject());
                                int childCount = gridLayout == null ? -1 : gridLayout.getChildCount();
                                Q0.r("repeat.render.grid", "children=" + childCount + "; entries=" + list.size());
                                if (gridLayout != null && gridLayout.getChildCount() == list.size()) {
                                    for (int i5 = 0; i5 < list.size(); i5++) {
                                        int iIntValue2 = ((Integer) f.c.invoke(list.get(i5), null)).intValue();
                                        ImageView imageViewA = AbstractC0038j1.a(gridLayout.getChildAt(i5));
                                        if (iIntValue2 == 5133059 && imageViewA != null) {
                                            imageViewA.setImageDrawable(new e3(imageViewA.getResources().getDisplayMetrics().density));
                                        }
                                        AbstractC0038j1.b(imageViewA, iIntValue2 == 5133057 || iIntValue2 == 5133058 || iIntValue2 == 5133059);
                                    }
                                }
                                c = 21251;
                                c2 = 21249;
                            } else {
                                char c5 = c4;
                                c = c3;
                                c2 = c5;
                            }
                            i4++;
                            char c6 = c;
                            c4 = c2;
                            c3 = c6;
                        }
                        if (yVarA != null) {
                            yVarA.close();
                        }
                    }
                } catch (Throwable th3) {
                    Q0.s("repeat.render.failed", th3);
                    Log.w("SoulHook102", "chat action marks unavailable");
                }
                return objProceed2;
            default:
                Object objInvoke = ((Method) this.b).invoke(null, null);
                if ((objInvoke instanceof Context) && AbstractC0090w2.o((Context) objInvoke).getBoolean("block_splash", true)) {
                    Log.i("SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
                    ((XposedModule) this.c).log(4, "SoulHook102", "new soul: hot splash blocked; HotADManager gate=false");
                    objProceed = Boolean.FALSE;
                } else {
                    objProceed = chain.proceed();
                }
                return objProceed;
        }
    }
}

/* JADX INFO: loaded from: d.class */
final /* synthetic */ class d implements XposedInterface.Hooker {
    public final boolean a;
    public final String b;

    public /* synthetic */ d(String str, boolean z) {
        this.a = z;
        this.b = str;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        View view;
        Object objProceed = chain.proceed();
        Object arg = this.a ? chain.getArg(0) : chain.getThisObject();
        try {
            view = (View) AbstractC0036j.d(arg.getClass(), "itemView").get(arg);
        } catch (Exception e) {
            try {
                Object obj = AbstractC0036j.d(arg.getClass(), "binding").get(arg);
                view = (View) obj.getClass().getMethod("getRoot", null).invoke(obj, null);
            } catch (Exception C0019e2) {
                view = null;
            }
        }
        if (view != null) {
            AbstractC0036j.i(view, this.b);
        }
        return objProceed;
    }
}
